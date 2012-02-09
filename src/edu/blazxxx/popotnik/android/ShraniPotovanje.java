package edu.blazxxx.popotnik.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import edu.blazxxx.popotnik.android.Database.DBAdapter;

public class ShraniPotovanje extends Activity implements OnClickListener{
	Globalne app;
	public static final int TEST_START_ACTIVITY_ID = 1;
	public static final int TEST_START_ACTIVITY_ID2 = 2;
	Button shrani,spremeni;
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
			tretji.setText(app.GetZacetek());
			peti.setText(app.GetKonec());
			sedmi.setText(app.GetTipPrevoza());
			deveti.setText(app.GetCasOdhoda());
			enajsti.setText(app.GetCasPrihoda());
			trinajsti.setText("\n" +app.GetCasPrihoda()+"\n" + "\n");
		}
		else if(app.GetStanje()=="Lokal")
		{
			dvanajsti.setVisibility(View.GONE);
			trinajsti.setVisibility(View.GONE); 

			tretji.setText("\n" +app.GetImeLokala());
			peti.setText("\n" +app.GetNaslov());
			sedmi.setText("\n" +app.GetDelovnik());
			deveti.setText("\n" +app.GetStran());
			enajsti.setText("\n" +app.GetTelefon()+"\n"+"\n");
		}
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shrani_potovanje);
		ShraniPotovanje.this.setResult(RESULT_OK);
		app =(Globalne) getApplication();
		app.SetStanje("Shrani potovanje");
		podatek = (Spinner) findViewById(R.id.spinner1);
		drugi = (TextView) findViewById(R.id.txtShrani2);
		tretji = (TextView) findViewById(R.id.txtShrani3);
		cetrti = (TextView) findViewById(R.id.txtShrani4);
		peti = (TextView) findViewById(R.id.txtShrani5);
		sesti = (TextView) findViewById(R.id.txtShrani6);
		sedmi = (TextView) findViewById(R.id.txtShrani7);
		osmi = (TextView) findViewById(R.id.txtShrani8);
		deveti = (TextView) findViewById(R.id.txtShrani9);
		deseti = (TextView) findViewById(R.id.txtShrani10);
		enajsti = (TextView) findViewById(R.id.txtShrani11);
		dvanajsti = (TextView) findViewById(R.id.txtShrani12);
		trinajsti = (TextView) findViewById(R.id.txtShrani13);
		adapter = ArrayAdapter.createFromResource(
				this, R.array.podatki, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		podatek.setAdapter(adapter);
		shrani = (Button) findViewById(R.id.btnShrani);
		spremeni = (Button) findViewById(R.id.btnSpremeni);
		shrani.setOnClickListener(this);
		spremeni.setOnClickListener(this);
		db = new DBAdapter(this);

		podatek.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			public void onItemSelected(AdapterView<?> parentView, View
					selectedItemView, int position, long id) {
				if(position==0)
				{  	
					app.SetStanje("");
					app.SetStanje("Potovanje");
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
					app.SetStanje("");
					app.SetStanje("Lokal");
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
			ShraniPotovanje.this.setResult(RESULT_CANCELED);
			ShraniPotovanje.this.finish();
			return true;

		default:
			break;
		}

		return false;
	}
	
	public void onClick(View arg0) {
		if (arg0.getId()==R.id.btnSpremeni) 
		{
			Intent i = new Intent(this,SpremeniPotovanje.class);
			startActivityForResult(i,TEST_START_ACTIVITY_ID2);
		}
		if (arg0.getId()==R.id.btnShrani)
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
			//Toast.makeText(this,zacasni.GetZacetek() + "  in  " + zacasni.GetKonec(),Toast.LENGTH_SHORT).show();
			addDB(zacasni);
			ShraniPotovanje.this.finish();
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