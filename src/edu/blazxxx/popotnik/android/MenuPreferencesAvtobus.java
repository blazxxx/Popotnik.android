package edu.blazxxx.popotnik.android;

import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MenuPreferencesAvtobus extends Activity implements OnItemClickListener{
private String[] Vsebina = {/*"Poišèi najbližjo postajo",
"Doloèi pot",*/
"Vozni redi",
"Shrani potovanje",
"Shranjena potovanja",
"Novo potovanje",
"Vreme"
};
Globalne app;
private static final int SHRANI_POTOVANJE_ID=1;
private static final int VNESENA_POT_ID=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listmenu);
        app =(Globalne) getApplication();
        ListView list = (ListView)findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listitem, Vsebina);
        list.setAdapter(adapter);
        list.setClickable(true);
        MenuPreferencesAvtobus.this.setResult(RESULT_CANCELED);
        //list.setOnItemClickListener(this);
        list.setOnItemClickListener(new OnItemClickListener() {
        	  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        	    
        	   Intent myIntent = null;
        	    
        	   if(((TextView) view).getText().equals("Poišèi najbližjo postajo")){
        	    myIntent = new Intent(view.getContext(), Avtobus.class);
        	    startActivity(myIntent);
         	   MenuPreferencesAvtobus.this.finish();
        	   }
        	   
        	   if(((TextView) view).getText().equals("Doloèi pot")){
           	    myIntent = new Intent(view.getContext(), VnosPotiAvtobus.class);
           	    	startActivityForResult(myIntent, VNESENA_POT_ID);
             	   MenuPreferencesAvtobus.this.finish();
           	   }
        	   
        	   if(((TextView) view).getText().equals("Vozni redi")){
        	    myIntent = new Intent(view.getContext(), VnosPotiAvtobus.class);
        	    startActivity(myIntent);
         	   MenuPreferencesAvtobus.this.finish();
        	   }

        	   if(((TextView) view).getText().equals("Prikaži lokale")){
        	    myIntent = new Intent(view.getContext(), Avtobus.class);
        	    startActivity(myIntent);
         	   MenuPreferencesAvtobus.this.finish();
        	   }
        	   if(((TextView) view).getText().equals("Shrani potovanje")){
           	    myIntent = new Intent(view.getContext(), ShraniPotovanje.class);
           	    startActivityForResult(myIntent,SHRANI_POTOVANJE_ID);
      	   MenuPreferencesAvtobus.this.finish();
           	   }
        	   if(((TextView) view).getText().equals("Shranjena potovanja")){
           	    myIntent = new Intent(view.getContext(), ShranjenaPotovanja.class);
           	 startActivity(myIntent);
      	   MenuPreferencesAvtobus.this.finish();
        	   }
           	   
      	 if(((TextView) view).getText().equals("Novo potovanje")){
        	    myIntent = new Intent(view.getContext(), Popotnik.class);
 			app.SetZacetek("");
 			app.SetKonec("");
 			app.SetTipPrevoza("");
 			app.SetDatum("");
 			app.SetCasOdhoda("");
 			app.SetCasPrihoda("");
 			app.SetImeLokala("");
 			app.SetNaslov("");
 			app.SetDelovnik("");
 			app.SetStran("");
 			app.SetTelefon("");
        	 startActivity(myIntent);
   	   MenuPreferencesAvtobus.this.finish();

        	  }
      	if(((TextView) view).getText().equals("Vreme")){
     	    myIntent = new Intent(view.getContext(), Vreme.class);
     	   startActivity(myIntent);
     	   MenuPreferencesAvtobus.this.finish();
     	   }
        	  }
        });

    }

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		
	}
}