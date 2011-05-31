package edu.blazxxx.popotnik.android;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.google.android.maps.MapView.LayoutParams;  
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Avto extends MapActivity{
	private LocationListener locListner;

	Globalne app;

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
	MapView myMapView;
	GeoPoint p;
	Location location;
	LocationManager locationManager;
	List<Address> addresses;

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
		List<Overlay> overlays = myMapView.getOverlays();
		overlays.add(positionOverlay);


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

		locationManager.requestLocationUpdates(provider, 2000, 10,   
				locationListener);





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
			GeoPoint point = new GeoPoint(geoLat.intValue(), 
					geoLng.intValue());

			mapController.animateTo(point);

			double lat = location.getLatitude();
			double lng = location.getLongitude();
			latLongString = "Lat:" + lat + "\nLong:" + lng;

			myLocationText.setText("Trenutni položaj je:" + 
					latLongString); 
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

	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		switch (requestCode) 
		{
		case TEST_START_ACTIVITY_ID: 
			if(resultCode==-1)
			{
				if(app.GetStanje().toString() == "Pot avto")
				{
					Geocoder geoCoder = new Geocoder(this, Locale.getDefault());    
					try {
						List<Address> addresses = geoCoder.getFromLocationName(
								app.GetKonec(), 1);
						String add = "";
						if (addresses.size() > 0) {
							p = new GeoPoint(
									(int) (addresses.get(0).getLatitude() * 1E6), 
									(int) (addresses.get(0).getLongitude() * 1E6));
							mapController.animateTo(p);    
							myMapView.invalidate();
						}    
					} catch (IOException e) {
						Toast toast = Toast.makeText(this,"NAPAKA!!! NE NAJDE LOKACIJE!!!" , Toast.LENGTH_LONG);
						toast.show();
						e.printStackTrace();
					}
				}
				else if(app.GetStanje().toString() == "Shrani potovanje")
				{
					break;
				}
			}
		}
	}

}