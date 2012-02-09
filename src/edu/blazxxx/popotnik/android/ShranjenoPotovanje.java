package edu.blazxxx.popotnik.android;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.google.android.maps.GeoPoint;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
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

public class ShranjenoPotovanje extends Activity implements OnClickListener{
	Globalne app;
	public static final int TEST_START_ACTIVITY_ID = 1;
	public static final int TEST_START_ACTIVITY_ID2 = 2;
	Button shrani,spremeni,odstrani;
	Spinner podatek;
	TextView drugi,tretji,cetrti,peti,sesti,sedmi, osmi, deveti, deseti, enajsti, dvanajsti, trinajsti;
	ArrayAdapter adapter;
	DBAdapter db;


	public void addDB(Globalne g) {




		db.open();
		/*Toast toast = Toast.makeText(this,g.GetZacetek() + "  "  + g.GetKonec(), Toast.LENGTH_LONG);
		toast.show();*/
		g.setDbID(db.insertPotovanje(g));
		db.close();	
	}
	public void spremeni()
	{
		if(app.GetStanje()=="Potovanje")
		{
			dvanajsti.setVisibility(View.VISIBLE);
			trinajsti.setVisibility(View.VISIBLE);
			tretji.setText(zacasna.GetZacetek());
			peti.setText(zacasna.GetKonec());
			sedmi.setText(zacasna.GetTipPrevoza());
			deveti.setText(zacasna.GetCasOdhoda());
			enajsti.setText(zacasna.GetCasPrihoda());
			trinajsti.setText("\n" +zacasna.GetCasPrihoda()+"\n" + "\n");
		}
		else if(app.GetStanje()=="Lokal")
		{
			dvanajsti.setVisibility(View.GONE);
			trinajsti.setVisibility(View.GONE); 

			tretji.setText("\n" +zacasna.GetImeLokala());
			peti.setText("\n" +zacasna.GetNaslov());
			sedmi.setText("\n" +zacasna.GetDelovnik());
			deveti.setText("\n" +zacasna.GetStran());
			enajsti.setText("\n" +zacasna.GetTelefon()+"\n"+"\n");
		}
	}
	Globalne zacasna;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shranjeno_potovanje);
		ShranjenoPotovanje.this.setResult(RESULT_OK);
		app =(Globalne) getApplication();
		app.SetStanje("Shrani potovanje");
		podatek = (Spinner) findViewById(R.id.spinner1);
		drugi = (TextView) findViewById(R.id.txtShranjeno2);
		tretji = (TextView) findViewById(R.id.txtShranjeno3);
		cetrti = (TextView) findViewById(R.id.txtShranjeno4);
		peti = (TextView) findViewById(R.id.txtShranjeno5);
		sesti = (TextView) findViewById(R.id.txtShranjeno6);
		sedmi = (TextView) findViewById(R.id.txtShranjeno7);
		osmi = (TextView) findViewById(R.id.txtShranjeno8);
		deveti = (TextView) findViewById(R.id.txtShranjeno9);
		deseti = (TextView) findViewById(R.id.txtShranjeno10);
		enajsti = (TextView) findViewById(R.id.txtShranjeno11);
		dvanajsti = (TextView) findViewById(R.id.txtShranjeno12);
		trinajsti = (TextView) findViewById(R.id.txtShranjeno13);
		adapter = ArrayAdapter.createFromResource(
				this, R.array.podatki, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		podatek.setAdapter(adapter);
		shrani = (Button) findViewById(R.id.btnShraniShranjeno);
		spremeni = (Button) findViewById(R.id.btnSpremeniShranjeno);
		odstrani = (Button) findViewById(R.id.btnOdstraniShranjeno);
		odstrani.setOnClickListener(this);
		shrani.setOnClickListener(this);
		spremeni.setOnClickListener(this);
		db = new DBAdapter(this);
		db.open();
		zacasna = new Globalne();
		Cursor novi = db.getPotovanje((int)app.GetDBid());
		zacasna.SetZacetek(novi.getString(DBAdapter.POS_ZACETEK));
		zacasna.SetKonec(novi.getString(DBAdapter.POS_KONEC));
		zacasna.SetTipPrevoza(novi.getString(DBAdapter.POS__TIP));
		zacasna.SetDatum(novi.getString(DBAdapter.POS_DATUM));
		zacasna.SetCasOdhoda(novi.getString(DBAdapter.POS_ODHOD));
		zacasna.SetCasPrihoda(novi.getString(DBAdapter.POS__PRIHOD));
		zacasna.SetImeLokala(novi.getString(DBAdapter.POS_IME));
		zacasna.SetNaslov(novi.getString(DBAdapter.POS_NASLOV));
		zacasna.SetDelovnik(novi.getString(DBAdapter.POS__DELOVNIK));
		zacasna.SetStran(novi.getString(DBAdapter.POS_STRAN));
		zacasna.SetTelefon(novi.getString(DBAdapter.POS_TELEFON));
		zacasna.setDbID(novi.getInt(DBAdapter.POS__ID));
		app.SetZacetek(zacasna.GetZacetek());
		app.SetKonec(zacasna.GetKonec());
		app.SetTipPrevoza(zacasna.GetTipPrevoza());
		app.SetDatum(zacasna.GetDatum());
		app.SetImeLokala(zacasna.GetImeLokala());
		app.SetNaslov(zacasna.GetNaslov());
		app.SetDelovnik(zacasna.GetDelovnik());
		app.SetStran(zacasna.GetStran());
		app.SetTelefon(zacasna.GetTelefon());
		app.SetCasOdhoda(zacasna.GetCasOdhoda());
		app.SetCasPrihoda(zacasna.GetCasPrihoda());
		app.SetDBid(zacasna.getDbID());
		db.close();
		podatek.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			public void onItemSelected(AdapterView<?> parentView, View
					selectedItemView, int position, long id) {
				if(position==0)
				{  	
					/*app.SetStanje("");
					app.SetStanje("Potovanje");*/
					dvanajsti.setVisibility(View.VISIBLE);
					trinajsti.setVisibility(View.VISIBLE);
					drugi.setText("Zaèetna lokacija: ");
					tretji.setText(app.GetZacetek());
					cetrti.setText("\n" +
					"Konèna lokacija: ");
					peti.setText("\n" +app.GetKonec());
					sesti.setText("\n" +
					"Tip prevoza: ");
					sedmi.setText("\n" +app.GetTipPrevoza());
					osmi.setText("\n" +
					"Datum: ");
					deveti.setText("\n" +app.GetDatum());
					deseti.setText("\n" +
					"Èas odhoda: ");
					enajsti.setText("\n" +app.GetCasOdhoda());
					dvanajsti.setText("\n" +
					"Èas prihoda: ");
					trinajsti.setText("\n" +app.GetCasPrihoda()+"\n" + "\n");
				}
				else if (position==1)
				{
					/*app.SetStanje("");
					app.SetStanje("Lokal");*/
					drugi.setText("" +
					"Ime lokala: ");
					tretji.setText(app.GetImeLokala());
					cetrti.setText("\n" +
					"Kraj: ");
					peti.setText("\n" +app.GetNaslov());
					sesti.setText("\n" +
					"Delovni èas: ");
					sedmi.setText("\n" +app.GetDelovnik());
					osmi.setText("\n" +
					"Spletna stran lokala: ");
					deveti.setText("\n" +app.GetStran());
					deseti.setText("\n" +
					"Telefon lokala: ");
					enajsti.setText("\n" +app.GetTelefon()+"\n"+"\n");
					dvanajsti.setVisibility(View.GONE);
					trinajsti.setVisibility(View.GONE);  
				}
			}

			
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});



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
			ShranjenoPotovanje.this.setResult(RESULT_CANCELED);
			ShranjenoPotovanje.this.finish();
			return true;

		default:
			break;
		}

		return false;
	}
	
	public void onClick(View arg0) {
		if (arg0.getId()==R.id.btnSpremeniShranjeno) 
		{
			Intent i = new Intent(this,SpremeniPotovanje.class);
			startActivityForResult(i,TEST_START_ACTIVITY_ID2);
		}
		if (arg0.getId()==R.id.btnShraniShranjeno)
		{
			Globalne zacasni=new Globalne();
			zacasni.SetZacetek(app.GetZacetek());
			zacasni.SetKonec(app.GetKonec());
			zacasni.SetTipPrevoza(app.GetTipPrevoza());
			zacasni.SetDatum(app.GetDatum());
			zacasni.SetCasOdhoda(app.GetCasOdhoda());
			zacasni.SetCasPrihoda(app.GetCasPrihoda());
			zacasni.SetImeLokala(app.GetImeLokala());
			zacasni.SetNaslov(app.GetNaslov());
			zacasni.SetDelovnik(app.GetDelovnik());
			zacasni.SetStran(app.GetStran());
			zacasni.SetTelefon(app.GetTelefon());
			zacasni.setDbID(app.getDbID());
			//Toast.makeText(this,zacasni.GetZacetek() + "  in  " + zacasni.GetKonec(),Toast.LENGTH_SHORT).show();
			db.open();
			db.deletePotovanje((int)app.GetDBid());
			db.close();
			addDB(zacasni);
			ShranjenoPotovanje.this.finish();
		}
		if (arg0.getId()==R.id.btnOdstraniShranjeno)
		{

			db.open();
			//ArrayList<Globalne> lista=new ArrayList<Globalne>();
			//Globalne nekaj;
			db.deletePotovanje((int)app.GetDBid());
			db.close();
			ShranjenoPotovanje.this.finish();
		}


	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		switch (requestCode) 
		{
		case TEST_START_ACTIVITY_ID2: 
			if(resultCode==-1)
			{
				if(app.GetStanje()=="Potovanje")
				{
					dvanajsti.setVisibility(View.VISIBLE);
					trinajsti.setVisibility(View.VISIBLE);
					tretji.setText(app.GetZacetek());
					peti.setText("\n" +app.GetKonec());
					sedmi.setText("\n" +app.GetTipPrevoza());
					deveti.setText("\n" +app.GetCasOdhoda());
					enajsti.setText("\n" +app.GetCasPrihoda());
					trinajsti.setText("\n" +app.GetCasPrihoda()+"\n" + "\n");
				}
				else if(app.GetStanje()=="Lokal")
				{
					dvanajsti.setVisibility(View.GONE);
					trinajsti.setVisibility(View.GONE); 

					tretji.setText(app.GetImeLokala());
					peti.setText("\n" +app.GetNaslov());
					sedmi.setText("\n" +app.GetDelovnik());
					deveti.setText("\n" +app.GetStran());
					enajsti.setText("\n" +app.GetTelefon()+"\n"+"\n");
				}
			}
			else
			{
				break;
			}
		}
	}
}