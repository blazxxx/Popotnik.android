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

public class VnosPotiAvtobus extends Activity{
	
	Globalne app;
	public String zacetekAvtobusStr,konecAvtobusStr;
	EditText zacetekAvtobus,konecAvtobus;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vnos_poti_avtobus);
        app =(Globalne) getApplication();
        
        zacetekAvtobus = (EditText) findViewById(R.id.edittxtZacetnaPostajaAvtobus);
        konecAvtobus = (EditText) findViewById(R.id.edittxtKoncnaPostajaAvtobus);
        
        
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
    			if((konecAvtobus.getText().toString()=="")||(zacetekAvtobus.getText().toString()==""))
    			{
    				Toast.makeText(this,"Ni vne�enih vseh potrebnih podatkov!!",Toast.LENGTH_SHORT).show();
    				VnosPotiAvtobus.this.setResult(RESULT_CANCELED);
    				VnosPotiAvtobus.this.finish();
    			}
    			else
    			{
    				zacetekAvtobusStr =zacetekAvtobus.getText().toString();
    				konecAvtobusStr =konecAvtobus.getText().toString();
    				app.SetKonecAvtobusStr(konecAvtobusStr);
    				app.SetZacetekAvtobusStr(zacetekAvtobusStr);
    				app.SetStanje("PotAvtobus");
    				Toast.makeText(this,app.GetKonecAvtobusStr() + "  in  " + app.GetZacetekAvtobusStr(),Toast.LENGTH_SHORT).show();
    				Intent novi = new Intent(this,VozniRedAvtobusov.class);
    				startActivity(novi);
    				VnosPotiAvtobus.this.setResult(RESULT_OK);
    				VnosPotiAvtobus.this.finish();
    			}
    	
		}
	
	}

}