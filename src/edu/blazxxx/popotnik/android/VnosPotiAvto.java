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

public class VnosPotiAvto extends Activity{
	
	Globalne app;
	public String zacetekAvtoStr,konecAvtoStr;
	EditText zacetekAvto,konecAvto;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vnos_poti_avto);
        app =(Globalne) getApplication();
        
        //zacetekAvto = (EditText) findViewById(R.id.edittxtZacetek);
        konecAvto = (EditText) findViewById(R.id.edittxtKonec);
        
        
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
  		inflater.inflate(R.menu.zacetni_menu, mMenu);
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
    			if(konecAvto.getText().toString()=="")
    			{
    				Toast.makeText(this,"Ni vne�enih vseh potrebnih podatkov!!",Toast.LENGTH_SHORT).show();
    				VnosPotiAvto.this.setResult(RESULT_CANCELED);
    				VnosPotiAvto.this.finish();
    			}
    			else
    			{
    				//zacetekAvtoStr =zacetekAvto.getText().toString();
    				konecAvtoStr =konecAvto.getText().toString();
    				app.SetKonecAvtoStr(konecAvtoStr);
    				//app.SetZacetekStr(zacetekAvtoStr);
    				app.SetStanje("Pot");
    				VnosPotiAvto.this.setResult(RESULT_OK);
    				VnosPotiAvto.this.finish();
    			}
    	
		}
	
	}

}