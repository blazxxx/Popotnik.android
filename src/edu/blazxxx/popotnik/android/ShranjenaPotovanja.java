package edu.blazxxx.popotnik.android;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import edu.blazxxx.popotnik.android.Database.DBAdapter;

public class ShranjenaPotovanja extends Activity{
	DBAdapter db;
	PotovanjaArrayAdapter adapter;
	ListView list;
	public static final int TEST_START_ACTIVITY_ID = 1;
	Intent prvi,drugi,tretji;
	Globalne app;
	int[] ID;
	
	@Override
	public void onStart() {
		super.onStart();
		//NaloziVsaPotovanja();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		NaloziVsaPotovanja();
		list.invalidate();
	

	}
	@Override
	public void onPause() {
		super.onPause();
		//NaloziVsaPotovanja();
	
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listmenu);
        list = (ListView)findViewById(R.id.list);
        app =(Globalne) getApplication();
        db = new DBAdapter(this);
        NaloziVsaPotovanja();
        
        list.setOnItemClickListener(new OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      	    Potovanje t = adapter.getItem(position);
       	   Intent myIntent = null;
       	    app.SetDBid(t.getDbID()); 
       	    Log.i("ID",String.valueOf(t.getDbID()));
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
		ID = new int[stevilo];
		app.SetStevilo(stevilo);
		int stevec=1;
		//String[] vsebina = new String[stevilo];
		ArrayList<Potovanje> lista = new ArrayList<Potovanje>();
		Potovanje tmpp;
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			int id=c.getInt(DBAdapter.POS__ID);
			ID[stevec-1]=id;
			tmpp = new Potovanje();
			tmpp.setDbID(c.getInt(DBAdapter.POS__ID));
			tmpp.setTipPrevoza(c.getString(DBAdapter.POS__TIP));
			tmpp.setDatum(c.getString(DBAdapter.POS_DATUM));
			tmpp.setStanje("Potovanje " + stevec);
			tmpp.setZacetek(c.getString(DBAdapter.POS_ZACETEK));
			tmpp.setKonec(c.getString(DBAdapter.POS_KONEC));
			//dodsj ostale
			lista.add(tmpp);
		//	vsebina[stevec]=id+" "+c.getString(DBAdapter.POS_DATUM)+" "+"-"+" ";
			stevec++;
		}
		/*String[] vsebina = new String[stevilo];
		for(int i=1;i<=stevilo;i++)
		{
			vsebina[i-1]="Potovanje "+i;
			Log.i(vsebina[i-1],vsebina[i-1]);
		}
		*/
		//list = (ListView)findViewById(R.id.list);
        adapter = new PotovanjaArrayAdapter(this, R.layout.listitemshranjeno, lista);
        list.setAdapter(adapter);
        db.close();
	}
  	
}