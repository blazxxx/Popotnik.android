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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

public class VnosPotiAvto extends Activity{
	
	Globalne app;
	public String konecAvtoStr;
	EditText konecAvto;
	TimePicker casOdhoda;
	DatePicker datum;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vnos_poti_avto);
        app =(Globalne) getApplication();
        
        konecAvto = (EditText) findViewById(R.id.edittxtKonec);
        casOdhoda = (TimePicker) findViewById(R.id.timePicker1Avto);
        datum = (DatePicker) findViewById(R.id.datePicker1Avto);
        
        
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
			VnosPotiAvto.this.setResult(RESULT_CANCELED);
			VnosPotiAvto.this.finish();
			return true;

  		default:
  			break;
  		}

  		return false;
  	}

    public void naKlik2(View v)
	{
    	if (v.getId()==R.id.ibtnNajdi)
		{
    		String datumStr = "" + datum.getDayOfMonth() + ". " + datum.getMonth()+1 + ". " + datum.getYear();
    		String ura = "" + casOdhoda.getCurrentHour() + ":" + casOdhoda.getCurrentMinute();
    			
    		if((datumStr=="")||(konecAvto.getText().toString()=="")||(ura==""))
    			{
    				Toast.makeText(this,"Ni vnešenih vseh potrebnih podatkov!!",Toast.LENGTH_SHORT).show();
    				VnosPotiAvto.this.setResult(RESULT_CANCELED);
    			}
    			else
    			{
    				app.SetKonec(konecAvto.getText().toString());
    				app.SetCasOdhoda(ura);
    				app.SetDatum(datumStr);
    				app.SetStanje("Pot avto");
    				//Toast.makeText(this,ura,Toast.LENGTH_SHORT).show();
    				VnosPotiAvto.this.setResult(RESULT_OK);
    				VnosPotiAvto.this.finish();
    			}
    	
		}
	
	}

}