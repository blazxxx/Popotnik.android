package edu.blazxxx.popotnik.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class VnosPotiVlak extends Activity{
	
	Globalne app;
	public String zacetekVlakStr,konecVlakStr;
	Spinner zacetekVlak,konecVlak;
	DatePicker datumVlak;
	ArrayAdapter adapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vnos_poti_vlak);
        app =(Globalne) getApplication();
        
        datumVlak = (DatePicker) findViewById(R.id.datePicker1);
        zacetekVlak = (Spinner) findViewById(R.id.spinner1);
        konecVlak = (Spinner) findViewById(R.id.spinner2);
        
        adapter = ArrayAdapter.createFromResource(
				this, R.array.podatkiVozniredVlak, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		zacetekVlak.setAdapter(adapter);
		konecVlak.setAdapter(adapter);

        
    }
    

	public void onPause()
	{
		super.onPause();
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
			VnosPotiVlak.this.setResult(RESULT_CANCELED);
			VnosPotiVlak.this.finish();
			return true;

  		default:
  			break;
  		}

  		return false;
  	}

    public void klikVlakPostaja(View v)
	{
    	if (v.getId()==R.id.ibtnNajdiVlak)
		{
    			int mesec =datumVlak.getMonth()+1;
    			int dan = datumVlak.getDayOfMonth();
    			int leto = datumVlak.getYear();
    			String datum = "" +dan +". " + mesec + ". " + leto;
    			//Toast.makeText(this,datum,Toast.LENGTH_LONG).show();
    				app.SetKonec(konecVlak.getSelectedItem().toString());
    				app.SetZacetek(zacetekVlak.getSelectedItem().toString());
    				app.SetDatum(datum);
    				app.SetStanje("");
    				app.SetStanje("Pot vlak");
    				Intent myIntent = new Intent(this, VozniRedVlakov.class);
            	    startActivity(myIntent);
    			
    	
		}
	
	}

}