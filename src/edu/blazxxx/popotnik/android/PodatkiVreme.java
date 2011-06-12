package edu.blazxxx.popotnik.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import edu.blazxxx.popotnik.android.Database.DBAdapter;

public class PodatkiVreme extends Activity{
	Globalne app;
	private static String html;
	TextView vreme,temperatura,vidnost,smer_vetra,kilometri_vetra, metri_vetra;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.podatki_vreme);
		PodatkiVreme.this.setResult(RESULT_OK);
		app =(Globalne) getApplication();
		vreme = (TextView) findViewById(R.id.textViewVreme2);
		temperatura = (TextView) findViewById(R.id.textViewVreme4);
		vidnost = (TextView) findViewById(R.id.textViewVreme6);
		smer_vetra = (TextView) findViewById(R.id.textViewVreme8);
		kilometri_vetra = (TextView) findViewById(R.id.textViewVreme11);
		metri_vetra = (TextView) findViewById(R.id.textViewVreme10);
		try {
			html=executeHttpGet();
		} catch (Exception e) {
			e.printStackTrace();
		}
		GetTemperatura();
		GetVreme();
		GetVidnost();
		GetSmerVetra();
		GetHitrostVetraMetri();
		GetHitrostVetraKilometri();

	}

	Menu mMenu;
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		mMenu = menu;
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.nazaj_menu, mMenu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.dialogTest:
			PodatkiVreme.this.setResult(RESULT_CANCELED);
			PodatkiVreme.this.finish();
			return true;

		default:
			break;
		}

		return false;
	}
	public String executeHttpGet() throws Exception {
		BufferedReader in = null;
		String page;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet();
			if(app.GetStanje()=="Bilje pri Novi Gorici")
			{
				request.setURI(new URI("http://meteo.arso.gov.si/uploads/probase/www/observ/surface/text/sl/observation_NOVA-GOR_latest.xml"));
			}
			else if(app.GetStanje()=="CELJE")
			{
				request.setURI(new URI("http://meteo.arso.gov.si/uploads/probase/www/observ/surface/text/sl/observation_CELJE_latest.xml"));
			}
			else if(app.GetStanje()=="Èrnomelj")
			{
				request.setURI(new URI("http://meteo.arso.gov.si/uploads/probase/www/observ/surface/text/sl/observation_CRNOMELJ_latest.xml"));
			}
			else if(app.GetStanje()=="Katarina nad Ljubljano")
			{
				request.setURI(new URI("http://meteo.arso.gov.si/uploads/probase/www/observ/surface/text/sl/observation_KATARINA_latest.xml"));
			}
			else if(app.GetStanje()=="Koèevje")
			{
				request.setURI(new URI("http://meteo.arso.gov.si/uploads/probase/www/observ/surface/text/sl/observation_KOCEVJE_latest.xml"));
			}
			else if(app.GetStanje()=="Kredarica")
			{
				request.setURI(new URI("http://meteo.arso.gov.si/uploads/probase/www/observ/surface/text/sl/observation_KREDA-ICA_latest.xml"));
			}
			else if(app.GetStanje()=="Letališèe Cerklje ob Krki")
			{
				request.setURI(new URI("http://meteo.arso.gov.si/uploads/probase/www/observ/surface/text/sl/observation_CERKLJE_LETAL-SCE_latest.xml"));
			}
			else if(app.GetStanje()=="Letališèe Edvarda Rusjana Maribor")
			{
				request.setURI(new URI("http://meteo.arso.gov.si/uploads/probase/www/observ/surface/text/sl/observation_MARIBOR_SLIVNICA_latest.xml"));
			}
			else if(app.GetStanje()=="Letališèe Jožeta Puènika Ljubljana")
			{
				request.setURI(new URI("http://meteo.arso.gov.si/uploads/probase/www/observ/surface/text/sl/observation_LJUBL-ANA_BRNIK_latest.xml"));
			}
			else if(app.GetStanje()=="Letališèe Lesce")
			{
				request.setURI(new URI("http://meteo.arso.gov.si/uploads/probase/www/observ/surface/text/sl/observation_LESCE_latest.xml"));
			}
			else if(app.GetStanje()=="Letališèe Portorož")
			{
				request.setURI(new URI("http://meteo.arso.gov.si/uploads/probase/www/observ/surface/text/sl/observation_PORTOROZ_SECOVLJE_latest.xml"));
			}
			else if(app.GetStanje()=="Lisca")
			{
				request.setURI(new URI("http://meteo.arso.gov.si/uploads/probase/www/observ/surface/text/sl/observation_LISCA_latest.xml"));
			}
			else if(app.GetStanje()=="Ljubljana")
			{
				request.setURI(new URI("http://meteo.arso.gov.si/uploads/probase/www/observ/surface/text/sl/observation_LJUBL-ANA_BEZIGRAD_latest.xml"));
			}
			else if(app.GetStanje()=="Murska Sobota")
			{
				request.setURI(new URI("http://meteo.arso.gov.si/uploads/probase/www/observ/surface/text/sl/observation_MURSK-SOB_latest.xml"));
			}
			else if(app.GetStanje()=="Novo mesto")
			{
				request.setURI(new URI("http://meteo.arso.gov.si/uploads/probase/www/observ/surface/text/sl/observation_NOVO-MES_latest.xml"));
			}
			else if(app.GetStanje()=="Postojna")
			{
				request.setURI(new URI("http://meteo.arso.gov.si/uploads/probase/www/observ/surface/text/sl/observation_POSTOJNA_latest.xml"));
			}
			else if(app.GetStanje()=="Rateèe")
			{
				request.setURI(new URI("http://meteo.arso.gov.si/uploads/probase/www/observ/surface/text/sl/observation_RATECE_latest.xml"));
			}
			else if(app.GetStanje()=="Slovenj Gradec")
			{
				request.setURI(new URI("http://meteo.arso.gov.si/uploads/probase/www/observ/surface/text/sl/observation_SLOVE-GRA_latest.xml"));
			}
			else if(app.GetStanje()=="Vogel")
			{
				request.setURI(new URI("http://meteo.arso.gov.si/uploads/probase/www/observ/surface/text/sl/observation_VOGEL_latest.xml"));
			}
			else if(app.GetStanje()=="Vojsko")
			{
				request.setURI(new URI("http://meteo.arso.gov.si/uploads/probase/www/observ/surface/text/sl/observation_VOJSKO_latest.xml"));
			}

			HttpResponse response = client.execute(request);
			in = new BufferedReader
			(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();
			page = sb.toString();
			System.out.println(page);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return page;

	}
	public void GetTemperatura(){

		String line = html;
		String pattern = "<metData>(.+?)<t>(.+?)</t>(.+?)</metData>";

		Pattern r = Pattern.compile(pattern);

		Matcher m = r.matcher(line);
		if (m.find( )) {
			temperatura.setText(m.group(2)+ " °C");


		} else {
			System.out.println("NO MATCH");
		}
	}
	public void GetVreme(){

		String line = html;
		String pattern = "<metData>(.+?)<nn_shortText>(.+?)</nn_shortText>(.+?)</metData>";

		Pattern r = Pattern.compile(pattern);

		Matcher m = r.matcher(line);
		if (m.find( )) {
			vreme.setText(m.group(2));


		} else {
			System.out.println("NO MATCH");
		}
	}
	public void GetVidnost(){

		String line = html;
		String pattern = "<metData>(.+?)<vis_value>(.+?)</vis_value>(.+?)</metData>";

		Pattern r = Pattern.compile(pattern);

		Matcher m = r.matcher(line);
		if (m.find( )) {
			vidnost.setText(m.group(2)+ " km");   

		} else {
			System.out.println("NO MATCH");
		}
	}
	public void GetSmerVetra(){

		String line = html;
		String pattern = "<metData>(.+?)<dd_longText>(.+?)</dd_longText>(.+?)</metData>";

		Pattern r = Pattern.compile(pattern);

		Matcher m = r.matcher(line);
		if (m.find( )) {

			smer_vetra.setText(m.group(2));   

		} else {
			System.out.println("NO MATCH");
		}
	}
	public void GetHitrostVetraMetri(){

		String line = html;
		String pattern = "<metData>(.+?)<ff_val>(.+?)</ff_val>(.+?)</metData>";
		

		Pattern r = Pattern.compile(pattern);

		Matcher m = r.matcher(line);
		if (m.find( )) {

			metri_vetra.setText(m.group(2)+ " m/s ");   

		} else {
			System.out.println("NO MATCH");
		}
	}
	public void GetHitrostVetraKilometri(){

		String line = html;
		String pattern = "<metData>(.+?)<ff_val_kmh>(.+?)</ff_val_kmh>(.+?)</metData>";

		Pattern r = Pattern.compile(pattern);

		Matcher m = r.matcher(line);
		if (m.find( )) {

			kilometri_vetra.setText("(" + m.group(2)+ " km/h)");   

		} else {
			System.out.println("NO MATCH");
		}
	}
}