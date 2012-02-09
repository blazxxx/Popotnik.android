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
import android.widget.Toast;

public class MenuPreferencesAvto extends Activity implements OnItemClickListener{
	private static final int VNESENA_POT_ID=0;
	private static final int SHRANI_POTOVANJE_ID=1;
private String[] Vsebina = {"Podatki potovanja",
"Prikaži lokale",
"Shrani potovanje",
"Shranjena potovanja",
"Novo potovanje",
"Vreme"
};

Globalne app;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listmenu);
        app =(Globalne) getApplication();
        app.SetStanje("");
        app.SetStanje("Pot avto");
        ListView list = (ListView)findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listitem, Vsebina);
        list.setAdapter(adapter);
        MenuPreferencesAvto.this.setResult(RESULT_CANCELED);
        //list.setClickable(true);
        list.setOnItemClickListener(new OnItemClickListener() {
      	  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      	    
      	   Intent myIntent = null;
      	    
      	   if(((TextView) view).getText().equals("Podatki potovanja")){
      	    myIntent = new Intent(view.getContext(), VnosPotiAvto.class);
      	    startActivityForResult(myIntent, VNESENA_POT_ID);
      	   }
      	    
      	   if(((TextView) view).getText().equals("Prikaži pot")){
      	    myIntent = new Intent(view.getContext(), Avto.class);
      	    MenuPreferencesAvto.this.finish();
      	  startActivity(myIntent);
      	   }

      	   if(((TextView) view).getText().equals("Prikaži lokale")){
      		  app.SetStanje("");
      		   app.SetStanje("Lokali");
      		 MenuPreferencesAvto.this.setResult(RESULT_OK);
				MenuPreferencesAvto.this.finish();
      	   }
      	   if(((TextView) view).getText().equals("Shrani potovanje")){
         	    myIntent = new Intent(view.getContext(), ShraniPotovanje.class);
         	    MenuPreferencesAvto.this.finish();
         	   startActivityForResult(myIntent,SHRANI_POTOVANJE_ID);
         	   }
      	   if(((TextView) view).getText().equals("Shranjena potovanja")){
         	    myIntent = new Intent(view.getContext(), ShranjenaPotovanja.class);
         	    //MenuPreferencesAvto.this.finish();
         	    startActivity(myIntent);
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
   	   MenuPreferencesAvto.this.finish();
        	   }
      	if(((TextView) view).getText().equals("Vreme")){
     	    myIntent = new Intent(view.getContext(), Vreme.class);
     	   startActivity(myIntent);
     	   MenuPreferencesAvto.this.finish();
     	   }
      	   
      	   

      	  }
      	 });
    }

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data){

		// See which child activity is calling us back.

		switch (requestCode) {

		case VNESENA_POT_ID: 
			if(resultCode == -1)
			{
				/*Toast toast = Toast.makeText(this,"resultCode="+resultCode , Toast.LENGTH_LONG);
				toast.show();*/
				MenuPreferencesAvto.this.setResult(RESULT_OK);
				MenuPreferencesAvto.this.finish();
				break;
			}
			else
			{
				break;
			}
		case SHRANI_POTOVANJE_ID:
			if(resultCode == -1)
			{
				/*Toast toast = Toast.makeText(this,"resultCode="+resultCode , Toast.LENGTH_LONG);
				toast.show();*/
				MenuPreferencesAvto.this.setResult(RESULT_OK);
				MenuPreferencesAvto.this.finish();
				break;
			}
			else
			{
				break;
			}
			
		}
	}
}