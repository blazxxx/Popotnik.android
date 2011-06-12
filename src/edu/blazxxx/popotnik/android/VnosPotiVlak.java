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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class VnosPotiVlak extends Activity{
	
	Globalne app;
	public String zacetekVlakStr,konecVlakStr;
	EditText zacetekVlak,konecVlak,datum;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vnos_poti_vlak);
        app =(Globalne) getApplication();
        
        zacetekVlak = (EditText) findViewById(R.id.edittxtZacetnaPostajaVlak);
        konecVlak = (EditText) findViewById(R.id.edittxtKoncnaPostajaVlak);
        datum = (EditText) findViewById(R.id.edittxtDatumVlak);
        
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
    			if((datum.getText().toString()=="")||(konecVlak.getText().toString()=="")||(zacetekVlak.getText().toString()==""))
    			{
    				Toast.makeText(this,"Ni vnešenih vseh potrebnih podatkov!!",Toast.LENGTH_SHORT).show();
    				VnosPotiVlak.this.setResult(RESULT_CANCELED);
    				//VnosPotiVlak.this.finish();
    			}
    			else
    			{
    				app.SetKonec(konecVlak.getText().toString());
    				app.SetZacetek(zacetekVlak.getText().toString());
    				app.SetDatum(datum.getText().toString());
    				app.SetStanje("");
    				app.SetStanje("Pot vlak");
    				VnosPotiVlak.this.setResult(RESULT_OK);
    				VnosPotiVlak.this.finish();
    			}
    	
		}
	
	}

}