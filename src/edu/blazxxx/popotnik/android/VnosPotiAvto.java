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

public class VnosPotiAvto extends Activity{
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vnos_poti_avto);
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

}