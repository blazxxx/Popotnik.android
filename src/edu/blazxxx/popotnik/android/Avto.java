package edu.blazxxx.popotnik.android;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MapView.LayoutParams;
import com.google.android.maps.Overlay;

public class Avto extends MapActivity implements OnClickListener{
	private LocationListener locListner;
	private static final String IME_XPATH = "//div[@class='name']/h1/a";
	private static final String NASLOV_XPATH = "//div[@class='name']/h2";
	Globalne app;
	List<Location> listGeoTock;
	List<String> listTock;
	List<Overlay> overlays;

	@Override
	protected boolean isRouteDisplayed() 
	{
		return false;
	}
	//debug
	//keytool -list -alias androiddebugkey -keystore /Users/matej/.android/debug.keystore -storepass android -keypass android
	//8D:22:34:2A:C0:70:9C:0C:B4:A1:AC:B3:C7:12:2D:1C
	//http://code.google.com/android/maps-api-signup.html
	MapController mapController;
	MyPositionOverlay positionOverlay;
	RisanjePoti pot;
	LokalOverlay lokal;
	MapView myMapView;
	GeoPoint p;
	Location location;
	LocationManager locationManager;
	List<Address> addresses;
	Button moznosti;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);

		app =(Globalne) getApplication();
		app.SetTipPrevoza("");
		app.SetTipPrevoza("Avto");



		myMapView = (MapView)findViewById(R.id.myMapView);
		View zoom = myMapView.getZoomControls();
		LinearLayout zoomLayout = (LinearLayout)findViewById(R.id.zoom);  
		LinearLayout tipPrikaza = (LinearLayout)findViewById(R.id.typeShow);  
		mapController = myMapView.getController();

		myMapView.setSatellite(true);
		myMapView.setStreetView(true);
		//myMapView.displayZoomControls(true);
		zoomLayout.addView(zoom, 
				new LinearLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, 
						LayoutParams.WRAP_CONTENT)); 
		myMapView.displayZoomControls(true);


		String context = Context.LOCATION_SERVICE;
		locationManager = (LocationManager)getSystemService(context);
		// Add the MyPositionOverlay

		positionOverlay = new MyPositionOverlay();
		pot = new RisanjePoti();
		lokal = new LokalOverlay(this);
		overlays = myMapView.getOverlays();
		overlays.add(positionOverlay);
		overlays.add(pot);
		overlays.add(lokal);



		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);

		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		String provider = locationManager.getBestProvider(criteria, true);

		location =locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		//Location location = locationManager.getLastKnownLocation(provider);
		my_updateWithNewLocation(location);

		locationManager.requestLocationUpdates(provider, 2000, 10, locationListener);

	}

	private final LocationListener locationListener = new LocationListener() 
	{   
		public void onLocationChanged(Location location) {
			my_updateWithNewLocation(location);
		}

		public void onProviderDisabled(String provider){
			my_updateWithNewLocation(null);
		}

		public void onProviderEnabled(String provider){ }
		public void onStatusChanged(String provider, int status, 
				Bundle extras){ }
	};

	private void my_updateWithNewLocation(Location location) {
		String latLongString;    TextView myLocationText;
		myLocationText = (TextView)findViewById(R.id.myLocationText);

		if (location != null) {
			positionOverlay.setLocation(location);

			Double geoLat = location.getLatitude()*1E6;
			Double geoLng = location.getLongitude()*1E6;
			GeoPoint point = new GeoPoint(geoLat.intValue(),geoLng.intValue());

			mapController.animateTo(point);	
			double lat = location.getLatitude();
			double lng = location.getLongitude();

			//Toast t = Toast.makeText(this, /*lat+ ", "+lng*/GetDataNaslov(Double.toString(lat),Double.toString(lng)), Toast.LENGTH_LONG);
			//t.show();
			app.setTockaPricetkaLat(lat);
			app.setTockaPricetkaLon(lng);
		}
	}

	Menu mMenu;
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		mMenu = menu;
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, mMenu);
		return true;

	}
	public static final int TEST_START_ACTIVITY_ID = 1;
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.dialogTest:
			Avto.this.setResult(RESULT_CANCELED);
			Avto.this.finish();
			return true;
		case R.id.itemSettings:
			Intent i = new Intent(this,MenuPreferencesAvto.class);
			startActivityForResult(i,TEST_START_ACTIVITY_ID);
			return true;

		default:
			break;
		}

		return false;
	}
	private List<GeoPoint> decodePoly(String encoded) {

		List<GeoPoint> poly = new ArrayList<GeoPoint>();
		int index = 0, len = encoded.length();
		int lat = 0, lng = 0;

		while (index < len) {
			int b, shift = 0, result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lat += dlat;

			shift = 0;
			result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lng += dlng;

			GeoPoint p = new GeoPoint((int) (((double) lat / 1E5) * 1E6),
					(int) (((double) lng / 1E5) * 1E6));
			poly.add(p) ;
		}

		return poly;
	}




	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		switch (requestCode) 
		{
		case TEST_START_ACTIVITY_ID: 

			if(resultCode==-1)
			{			
				if(app.GetStanje().toString() == "Pot avto")
				{
					try {
						String noviKonec="";
						char[] konec= app.GetKonec().toCharArray();
						for(int i=0;i<konec.length;i++)
						{
							if(konec[i]==' ')
							{
								noviKonec+="%20";
							}
							else
							{
								noviKonec+=konec[i];
							}

						}
						String a  ="http://maps.googleapis.com/maps/api/directions/xml?origin=";
						String b = app.getTockaPricetkaLat()+ "," + app.getTockaPricetkaLon();
						String c = "&destination="+ noviKonec +"&sensor=false";
						String urlNaslov =a +b + c;
						List<GeoPoint> tocke = null;
						Document document=null;
						DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
						DocumentBuilder builder;


						builder = factory.newDocumentBuilder();
						//Toast pre1= Toast.makeText(this, urlNaslov, Toast.LENGTH_LONG);
						//pre1.show();
						System.out.println(urlNaslov);
						document = builder.parse(urlNaslov);



						NodeList nodeList = document.getElementsByTagName("points");

						for(int index=0; index < nodeList.getLength()-1; index++) {
							if (nodeList.item(index).getNodeType() ==Node.ELEMENT_NODE)
							{

								Element tockeElementa = (Element) nodeList.item(index);
								tocke = decodePoly(tockeElementa.getFirstChild().getNodeValue().trim().toString());
								for(int i=0;i<tocke.size();i++)
								{
									/*Toast pre1= Toast.makeText(this, tocke.get(i).toString(), Toast.LENGTH_LONG);
								pre1.show();*/
									GeoPoint zacasni = tocke.get(i);
									pot.setLocation(zacasni);

								}
							}
						}
						/*Criteria criteria = new Criteria();
					criteria.setAccuracy(Criteria.ACCURACY_FINE);*/

					} catch (ParserConfigurationException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();	
					} catch (SAXException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				else if(app.GetStanje().toString() == "Shrani potovanje")
				{
					break;
				}
				else if(app.GetStanje().toString() == "Lokali")
				{
					/*try {

						String a  ="https://maps.googleapis.com/maps/api/place/search/xml?location=";
						String b = app.getTockaPricetkaLat()+ "," + app.getTockaPricetkaLon();
						String c = "&radius=50000&sensor=false&key=AIzaSyCtQVFjJee3AhImKy0-j1HjxmwexDxqgTo";
						String urlNaslov= a+b+c;
						Document document=null;
						DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
						DocumentBuilder builder = null;



						builder = factory.newDocumentBuilder();
						document = builder.parse(urlNaslov);
						NodeList locationList = document.getElementsByTagName("location");
						NodeList nameList = document.getElementsByTagName("name");
						NodeList vicinityList = document.getElementsByTagName("vicinity");
						for(int i=0; i < locationList.getLength(); i++) {
							if (locationList.item(i).getNodeType() ==Node.ELEMENT_NODE)
							{
								InformacijeUstanove info = new InformacijeUstanove();
								String naslov = vicinityList.item(i).getTextContent().toString();
								String ime = nameList.item(i).getTextContent().toString();
								info.setIme(ime);
								info.setNaslov(naslov);
								NodeList latitudeList = document.getElementsByTagName("lat"); 
								NodeList longitudeList = document.getElementsByTagName("lng");
								for(int j=0;j<latitudeList.getLength()-1;j++)
								{

									double lat = Double.parseDouble(latitudeList.item(j).getTextContent().toString());
									double lng = Double.parseDouble(longitudeList.item(j).getTextContent().toString());
									GeoPoint point = new GeoPoint((int)(lat * 1E6),(int)(lng * 1E6));


									info.setTocka(point);
									//Toast t = Toast.makeText(this,ime+ "   " + naslov +"   " + point, Toast.LENGTH_LONG);
									//t.show();
									lokal.setLocation(info);
								}
							}
						}
					} catch (ParserConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
						String[] poljeImen = null; 
						String[] poljeSpletnihNaslovov=null;
						String[] poljePravihNaslovov = null;
						try
						{
							String URLnaslov="http://www.studentska-prehrana.si/Pages/Directory.aspx";
							CleanerProperties props = new CleanerProperties();
							props.setTranslateSpecialEntities(true);
							props.setTransResCharsToNCR(true);
							props.setOmitComments(true);
							URL url = new URL(URLnaslov);
							URLConnection conn = url.openConnection();
							HtmlCleaner cleaner = new HtmlCleaner();
							TagNode node = cleaner.clean(new InputStreamReader(conn.getInputStream()));
							Object[] ime_nodes = node.evaluateXPath(IME_XPATH);
							Object[] naslov_nodes = node.evaluateXPath(NASLOV_XPATH);

							poljeImen = new String[ime_nodes.length];
							poljeSpletnihNaslovov = new String[naslov_nodes.length];
							poljePravihNaslovov = new String[naslov_nodes.length];
							System.out.println(ime_nodes.length);
							for(int i=0;i<ime_nodes.length;i++)
							{
								TagNode ime_node = (TagNode) ime_nodes[i];
								TagNode naslov_node = (TagNode) naslov_nodes[i];
								char[] ime = ime_node.getChildren().iterator().next().toString().trim().toCharArray();
								poljePravihNaslovov[i] = naslov_node.getChildren().iterator().next().toString().trim().toString();
								char[] naslov = poljePravihNaslovov[i].toCharArray();
								StringBuilder zacasniStr= new StringBuilder();
								for(int j=0;j<ime.length;j++)
								{

									char zacasni = ime[j];
									/*if(zacasni=='"')
									{

									}
									else
									{*/
										zacasniStr.append(zacasni);
									//}

								}
								StringBuilder zacasniStr2= new StringBuilder();
								for(int j=0;j<naslov.length;j++)
								{

									char zacasni = naslov[j];
									if((zacasni=='(')||(zacasni==')'))
									{

									}
									else if((zacasni == '/'))
									{
										break;
									}
									else if(zacasni == ' ')
									{
										
									}
									/*else if(zacasni== ' ')
								{
									zacasniStr2.append("%20");
								}*/
									else
									{
										zacasniStr2.append(zacasni);
									}
								}

								poljeImen[i] = zacasniStr.toString();
								poljeSpletnihNaslovov[i] = zacasniStr2.toString();
								/*System.out.println(i+". " +zacasniStr.toString()+"\n" + zacasniStr2.toString());
								System.out.println("----------------------------");*/

							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (XPatherException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//ParsanjeStrani(poljeImen1,poljeNaslovov1);

						for(int i=0;i<poljeSpletnihNaslovov.length/*-poljeSpletnihNaslovov.length+10*/;i++)
						{
							/*if((i==29)||(i==519)||(i==548)||(i==523) ||(i==552)||(poljeSpletnihNaslovov[i]=="Trubarjeva 7, Ljubljana"))
							{
								
							}
							else
							{
							System.out.println(i + poljeSpletnihNaslovov[i]);
							String zacasni = GetDataKoordinate(poljeSpletnihNaslovov[i]);
							String[] nekaj= zacasni.split(";");
							InformacijeUstanove info= new InformacijeUstanove();
							Toast t = Toast.makeText(this,zacasni, Toast.LENGTH_SHORT);
							t.show(); 
							Double lat=Double.parseDouble(nekaj[0])*1E6;
							Double lng=Double.parseDouble(nekaj[1])*1E6;
							GeoPoint point=new GeoPoint(lat.intValue(),lng.intValue());
							info.setTocka(point);
							info.setIme(poljeImen[i]);
							info.setNaslov(poljeSpletnihNaslovov[i]);
							lokal.setLocation(info);
							}*/
							//addDB(info);
							//GetDataNaslov(lat, longi)
							try {
							String urlNaslov= "http://maps.googleapis.com/maps/api/geocode/xml?address="+poljeSpletnihNaslovov[i]+"&sensor=true";
							Document document=null;
							DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
							DocumentBuilder builder = null;


							builder = factory.newDocumentBuilder();
							document = builder.parse(urlNaslov);
							NodeList locationList = document.getElementsByTagName("location");
							for(int k=0; k < locationList.getLength(); k++) {
								if (locationList.item(k).getNodeType() ==Node.ELEMENT_NODE)
								{
									
									InformacijeUstanove info= new InformacijeUstanove();
									NodeList koordinate = locationList.item(k).getChildNodes();
									Double lat = Double.parseDouble(koordinate.item(1).getTextContent().toString())*1E6;
									Double lng = Double.parseDouble(koordinate.item(3).getTextContent().toString())*1E6;
									//System.out.println(lat + "" + lng);
									GeoPoint point = new GeoPoint(lat.intValue(),lng.intValue());
									info.setTocka(point);
									info.setIme(poljeImen[i]);
									info.setNaslov(poljePravihNaslovov[i]);
									//System.out.println(info.getTocka() + "  " + info.getNaslov() +  " " + info.getIme());
									lokal.setLocation(info);
									//addDB(info);

								}
							}

						} catch (ParserConfigurationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						catch (SAXException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}


						}

						
					}



					/*else
					{
						Toast t = Toast.makeText(this,"EWO MENE!!!", Toast.LENGTH_SHORT);
						t.show(); 

						//int stevilo_vnosov = Integer.parseInt(BaseColumns._COUNT);
						for(int j=0;j<=db.getSize();j++)
						{

							InformacijeUstanove zacasni = (InformacijeUstanove) db.getPotovanje(j);
							String zacasniStr = GetDataKoordinate(zacasni.getNaslov());
							String[] nekaj= zacasniStr.split(";");
							Double lat=Double.parseDouble(nekaj[0])*1E6;
							Double lng=Double.parseDouble(nekaj[1])*1E6;
							GeoPoint point=new GeoPoint(lat.intValue(),lng.intValue());
							zacasni.setTocka(point);
							lokal.setLocation(zacasni);

						}
					}
					db.close();
				}*/
			}
		}
	}
	/*public void NaslovVKoordinate(String naslov)
	{
		GeoPoint point = null;
		try {
			String urlNaslov= "http://maps.googleapis.com/maps/api/geocode/xml?address="+naslov+"&sensor=true";
			Document document=null;
			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = null;



			builder = factory.newDocumentBuilder();
			document = builder.parse(urlNaslov);
			NodeList locationList = document.getElementsByTagName("location");
			for(int i=0; i < locationList.getLength(); i++) {
				if (locationList.item(i).getNodeType() ==Node.ELEMENT_NODE)
				{
					NodeList latitudeList = document.getElementsByTagName("lat"); 
					NodeList longitudeList = document.getElementsByTagName("lng");
					for(int j=0;j<latitudeList.getLength()-1;j++)
					{

						double lat = Double.parseDouble(latitudeList.item(j).getTextContent().toString());
						double lng = Double.parseDouble(longitudeList.item(j).getTextContent().toString());
						point = new GeoPoint((int)(lat * 1E6),(int)(lng * 1E6));
						Toast t = Toast.makeText(this,point.toString(), Toast.LENGTH_SHORT);
						t.show();
						//return point;
					}
				}
			}

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return point;
	}*/

	/*public void ParsanjeStrani(String[] poljeImen,String[] poljeNaslovov)
	{
		try
		{
			String URLnaslov="http://www.studentska-prehrana.si/Pages/Directory.aspx";
			CleanerProperties props = new CleanerProperties();
			props.setTranslateSpecialEntities(true);
			props.setTransResCharsToNCR(true);
			props.setOmitComments(true);
			URL url = new URL(URLnaslov);
			URLConnection conn = url.openConnection();
			HtmlCleaner cleaner = new HtmlCleaner();
			TagNode node = cleaner.clean(new InputStreamReader(conn.getInputStream()));
			Object[] ime_nodes = node.evaluateXPath(IME_XPATH);
			Object[] naslov_nodes = node.evaluateXPath(NASLOV_XPATH);

			poljeImen = new String[ime_nodes.length];
			poljeNaslovov = new String[naslov_nodes.length];
			System.out.println(ime_nodes.length);
			for(int i=0;i<ime_nodes.length;i++)
			{
				TagNode ime_node = (TagNode) ime_nodes[i];
				TagNode naslov_node = (TagNode) naslov_nodes[i];
				char[] ime = ime_node.getChildren().iterator().next().toString().trim().toCharArray();
				char[] naslov = naslov_node.getChildren().iterator().next().toString().trim().toCharArray();
				StringBuilder zacasniStr= new StringBuilder();
				for(int j=0;j<ime.length;j++)
				{

					char zacasni = ime[j];
					if(zacasni=='"')
					{

					}
					else
					{
						zacasniStr.append(zacasni);
					}

				}
				StringBuilder zacasniStr2= new StringBuilder();
				for(int j=0;j<naslov.length;j++)
				{

					char zacasni = naslov[j];
					if((zacasni=='(')||(zacasni==')'))
					{

					}
					else if((zacasni == '/')||(zacasni == ','))
					{
						break;
					}
					else if(zacasni== ' ')
					{
						zacasniStr2.append("%20");
					}
					else
					{
						zacasniStr2.append(zacasni);
					}
				}
				poljeImen[i] = zacasniStr.toString();
				poljeNaslovov[i] = zacasniStr2.toString();
				System.out.println(i+". " +zacasniStr.toString()+"\n" + zacasniStr2.toString());
				System.out.println("----------------------------");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPatherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	public void onClick(View arg0) {



	}





}