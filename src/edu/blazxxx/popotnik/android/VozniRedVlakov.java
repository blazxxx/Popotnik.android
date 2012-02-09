package edu.blazxxx.popotnik.android;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class VozniRedVlakov extends Activity{
	
	ListView list;
	Globalne app;
	VozniRedArrayAdapter adapter;
	private String[] Odhod =    {"04:51" , "06:06" , "06:17" , "07:06" , "07:25" , "08:04" , "09:05" , "10:24" , "11:03" , "12:03" , "13:05" , "13:58" , "14:08" , "15:10" , "16:06" , "17:05" , "17:23" , "18:05" , "19:05" , "20:07" , "21:05" , "22:05" , "23:02"};
	private String[] Prihod =   {"05:15" , "06:31" , "06:48" , "07:30" , "07:55" , "08:31" , "09:32" , "10:49" , "11:28" , "12:28" , "13:30" , "14:30" , "14:35" , "15:40" , "16:32" , "17:33" , "17:56" , "18:32" , "19:32" , "20:33" , "21:32" , "22:32" , "23:27"};
	private String[] Trajanje = {"24 min", "25 min", "31 min", "24 min", "30 min", "27 min", "27 min", "25 min", "25 min", "25 min", "25 min", "32 min", "27 min", "30 min", "26 min", "28 min", "33 min", "27 min", "27 min", "26 min", "27 min", "27 min", "25 min"};
	TextView krajOdhoda, krajPrihoda;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app =(Globalne) getApplication();
        setContentView(R.layout.voznired_item);
        krajOdhoda = (TextView) findViewById(R.id.textView1Voznired);
        krajPrihoda = (TextView) findViewById(R.id.textView2Voznired);
        list = (ListView)findViewById(R.id.list);
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
			VozniRedVlakov.this.setResult(RESULT_CANCELED);
			VozniRedVlakov.this.finish();
			return true;

  		default:
  			break;
  		}

  		return false;
  	}


}