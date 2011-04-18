package edu.blazxxx.popotnik.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Popotnik extends Activity {
	
	private static final int EXIT_DIALOG = 0;
	private Menu mMenu;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	mMenu = menu; //ni nujno
	MenuInflater inflater = getMenuInflater();
	inflater.inflate(R.menu.main_menu, mMenu);
	return true;
	}
    
    
    protected Dialog onCreateDialog(int id) {
    	switch (id) {
    	case EXIT_DIALOG:
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("Ali želiti zapustiti aplikacijo Popotnik?")
    	.setCancelable(false)
    	.setPositiveButton("Da", new DialogInterface.OnClickListener() {
    	public void onClick(DialogInterface dialog, int id) {
    	Popotnik.this.setResult(RESULT_CANCELED);
    	Popotnik.this.finish();}
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
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
	switch (item.getItemId()) {
	case R.id.dialogTest:
	showDialog(EXIT_DIALOG);
	return true;
	case R.id.itemSettings:
		return true;
	/*default:// Generic catch all for all the other menu resources
	if (!item.hasSubMenu()) {
	Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT)
	.show();
	return true;
	}
	break;
	}
	*/
	}
	return true;
}
}