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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class VnosPotiAvtobus extends Activity{
	
	Globalne app;
	public String zacetekAvtobusStr,konecAvtobusStr;
	DatePicker datum;
	ArrayAdapter adapter;
	Spinner spinnerZacetek, spinnerKonec;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vnos_poti_avtobus);
        app =(Globalne) getApplication();
        
        spinnerZacetek = (Spinner) findViewById(R.id.spinner1Avtobus);
        spinnerKonec = (Spinner) findViewById(R.id.spinner2Avtobus);
        datum = (DatePicker) findViewById(R.id.datePicker1Avtobus);
        adapter = ArrayAdapter.createFromResource(
				this, R.array.podatkiVozniredAvtobus, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerZacetek.setAdapter(adapter);
        spinnerKonec.setAdapter(adapter);
        /*zacetekAvtobus = (EditText) findViewById(R.id.edittxtZacetnaPostajaAvtobus);
        konecAvtobus = (EditText) findViewById(R.id.edittxtKoncnaPostajaAvtobus);
        datum = (EditText) findViewById(R.id.edittxtDatumAvtobus);*/
        

        
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
			VnosPotiAvtobus.this.setResult(RESULT_CANCELED);
			VnosPotiAvtobus.this.finish();
			return true;

  		default:
  			break;
  		}

  		return false;
  	}

    public void klikAvtobusPostaja(View v)
	{
    	if (v.getId()==R.id.ibtnNajdiAvtobus)
		{
    		int mesec =datum.getMonth()+1;
			int dan = datum.getDayOfMonth();
			int leto = datum.getYear();
			String datumStr = "" + dan +". " + mesec + ". " + leto;
    				
    			
    				app.SetKonec(spinnerKonec.getSelectedItem().toString());
    				app.SetZacetek(spinnerZacetek.getSelectedItem().toString());
    				//Toast.makeText(this,spinnerZacetek.getSelectedItem().toString()+ "  " + spinnerKonec.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
    				//Toast.makeText(this,"" + app.GetZacetek(),Toast.LENGTH_LONG).show();
    				app.SetDatum(datumStr);
    				app.SetStanje("");
    				app.SetStanje("Pot avtobus");
    				Intent myIntent = new Intent(this, VozniRedAvtobusov.class);
            	    startActivity(myIntent);
    				
    	
		}
	
	}

}