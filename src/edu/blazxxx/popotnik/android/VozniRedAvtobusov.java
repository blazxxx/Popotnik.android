package edu.blazxxx.popotnik.android;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class VozniRedAvtobusov extends Activity{
	
	ListView list;
	VozniRedArrayAdapter adapter;
	private String[] Odhod = {"05:05","13:05","16:25","21:05"};
	private String[] Prihod = {"05:20","13:20","16:38","21:20"};
	private String[] Trajanje = {"00:15 min","00:15 min","00:13 min","00:15 min"};
	TextView krajOdhoda, krajPrihoda;
	Globalne app;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voznired_item);
        app =(Globalne) getApplication();
        list = (ListView)findViewById(R.id.list);
        krajOdhoda = (TextView) findViewById(R.id.textView1Voznired);
        krajPrihoda = (TextView) findViewById(R.id.textView2Voznired);
        
        krajOdhoda.setText(app.GetZacetek());
        krajPrihoda.setText(app.GetKonec());
        int stevec = 1;
        ArrayList<Globalne> lista = new ArrayList<Globalne>();
        for(int i=0;i<Odhod.length;i++)
        {
        	Globalne zacasni = new Globalne();
        	zacasni.SetStanje("Vozni red " + stevec);
        	zacasni.SetCasOdhoda(Odhod[i]);
        	zacasni.SetCasPrihoda(Prihod[i]);
        	zacasni.SetDelovnik(Trajanje[i]);
        	lista.add(zacasni);
        	stevec++;
        
        }
        adapter = new VozniRedArrayAdapter(this, R.layout.voznired_layout, lista);
        list.setAdapter(adapter);
        
        
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
			VozniRedAvtobusov.this.setResult(RESULT_CANCELED);
			VozniRedAvtobusov.this.finish();
			return true;

  		default:
  			break;
  		}

  		return false;
  	}


}