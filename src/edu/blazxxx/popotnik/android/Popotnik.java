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
import android.widget.ImageButton;
import android.widget.Toast;

public class Popotnik extends Activity{
	
	public static final int TEST_START_ACTIVITY_ID = 1;
	Intent prvi,drugi,tretji;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    

	public void onPause()
	{
		super.onPause();
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
        switch (requestCode) 
        {
            case TEST_START_ACTIVITY_ID: 
            	Toast toast = Toast.makeText(this,"OK"+resultCode, Toast.LENGTH_LONG);
            	//toast.show();
            	break;
    	
        }
	}
    
    
    public void naKlik(View v)
	{
    	if (v.getId()==R.id.ibtnAvto)
		{
    		prvi = new Intent(this,Avto.class);
			startActivity(prvi);
		}
		if (v.getId()==R.id.ibtnAvtobus)
		{
			drugi = new Intent(this,Avtobus.class);
			startActivity(drugi);
		}
		if (v.getId()==R.id.ibtnVlak)
		{
			tretji = new Intent(this,Vlak.class);
			startActivity(tretji);
		}
	}
    
    private static final int EXIT_DIALOG=1; 
    @Override
  	protected Dialog onCreateDialog(int id) {
  		AlertDialog.Builder builder;
  		switch (id) {
  		case EXIT_DIALOG:
  			builder = new AlertDialog.Builder(this);
  			builder.setMessage("Ali želiti zapustiti Popotnika?")
  			.setCancelable(false)
  			.setPositiveButton("Da", new DialogInterface.OnClickListener() {

  				@Override
  				public void onClick(DialogInterface dialog, int id) {
  					
  					Popotnik.this.setResult(RESULT_CANCELED);
  					Popotnik.this.finish();
  				}
  				
  			})
  			.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
  				public void onClick(DialogInterface dialog, int id) {
  					Popotnik.this.setResult(RESULT_OK);
  					dialog.cancel();
  				}
  			});
  			return builder.create();
  	
  		}
  		return null;
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
  			showDialog(EXIT_DIALOG);
  			return true;

  		default:
  			break;
  		}

  		return false;
  	}

}