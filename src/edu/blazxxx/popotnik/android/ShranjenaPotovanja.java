package edu.blazxxx.popotnik.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import edu.blazxxx.popotnik.android.Database.DBAdapter;

public class ShranjenaPotovanja extends Activity{
	DBAdapter db;
	ArrayAdapter<String> adapter;
	ListView list;
	public static final int TEST_START_ACTIVITY_ID = 1;
	Intent prvi,drugi,tretji;
	Globalne app;
	
	@Override
	public void onStart() {
		super.onStart();
		NaloziVsaPotovanja();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		NaloziVsaPotovanja();
	

	}
	@Override
	public void onPause() {
		super.onPause();
		NaloziVsaPotovanja();
	
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listmenu);
        app =(Globalne) getApplication();
        db = new DBAdapter(this);
        NaloziVsaPotovanja();
        
        list.setOnItemClickListener(new OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      	    
       	   Intent myIntent = null;
       	
       	    app.SetDBid(id+1); 
       	    Log.i("ID",String.valueOf(id));
       		myIntent = new Intent(view.getContext(), ShranjenoPotovanje.class);
      	    startActivity(myIntent);
       	    

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
  			ShranjenaPotovanja.this.setResult(RESULT_CANCELED);
  			ShranjenaPotovanja.this.finish();
  			return true;

  		default:
  			break;
  		}

  		return false;
  	}
  	public void NaloziVsaPotovanja() {
		db.open();
		
		Cursor c = db.getAll();
		c.isAfterLast();
		Globalne tmp = new Globalne();
		int stevilo = c.getCount();
		app.SetStevilo(stevilo);
		String[] vsebina = new String[stevilo];
		for(int i=1;i<=stevilo;i++)
		{
			vsebina[i-1]="Potovanje "+i;
			Log.i(vsebina[i-1],vsebina[i-1]);
		}
		list = (ListView)findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(this, R.layout.listitemshranjeno, vsebina);
        list.setAdapter(adapter);
        db.close();
		/*Globalne tmp;
		c = db.getAll();
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) 
		{
			tmp = new Globalne();
			tmp.SetZacetek(c.getString(DBAdapter.POS_ZACETEK));
			tmp.SetKonec(c.getString(DBAdapter.POS_KONEC));
			tmp.setDbID(c.getLong(DBAdapter.POS__ID));
			//lista.add(tmp); 
		}
		c.close();
		*/
	}
}