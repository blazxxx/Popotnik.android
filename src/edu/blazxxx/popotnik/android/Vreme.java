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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Vreme extends Activity{
	
	private String[] Vsebina = {"Bilje pri Novi Gorici",
			"Celje",
			"Èrnomelj",
			"Katarina nad Ljubljano",
			"Koèevje",
			"Kredarica",
			"Letališèe Cerklje ob Krki",
			"Letališèe Edvarda Rusjana Maribor",
			"Letališèe Jožeta Puènika Ljubljana",
			"Letališèe Lesce",
			"Letališèe Portorož",
			"Lisca",
			"Ljubljana",
			"Murska Sobota",
			"Novo mesto",
			"Postojna",
			"Rateèe",
			"Slovenj Gradec",
			"Vogel",
			"Vojsko"
			};
	Globalne app;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vrememenu);
        app =(Globalne) getApplication();
        ListView list = (ListView)findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listitem, Vsebina);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new OnItemClickListener() {
        	  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        	    
        	   Intent myIntent = null;
        	    
        	   if(((TextView) view).getText().equals("Bilje pri Novi Gorici")){
        		   app.SetStanje("");
        		   app.SetStanje("Bilje pri Novi Gorici");
        	   }
        	    
        	   if(((TextView) view).getText().equals("Celje")){
        		   app.SetStanje("");
        		   app.SetStanje("CELJE");
        	   }

        	   if(((TextView) view).getText().equals("Èrnomelj")){
        		   app.SetStanje("");
        		   app.SetStanje("Èrnomelj");
        	   }
        	   if(((TextView) view).getText().equals("Katarina nad Ljubljano")){
        		   app.SetStanje("");
        		   app.SetStanje("Katarina nad Ljubljano");
           	   }
        	   if(((TextView) view).getText().equals("Koèevje")){
        		   app.SetStanje("");
        		   app.SetStanje("Koèevje");
           	   }
        	 if(((TextView) view).getText().equals("Kredarica")){
        		 app.SetStanje("");
        		 app.SetStanje("Kredarica");
          	   }
        	if(((TextView) view).getText().equals("Letališèe Cerklje ob Krki")){
        		app.SetStanje("");
        		app.SetStanje("Letališèe Cerklje ob Krki");
       	   }
        	if(((TextView) view).getText().equals("Letališèe Edvarda Rusjana Maribor")){
        		app.SetStanje("");
        		app.SetStanje("Letališèe Edvarda Rusjana Maribor");
     	   }
     	    
     	   if(((TextView) view).getText().equals("Letališèe Jožeta Puènika Ljubljana")){
     		  app.SetStanje("");
     		  app.SetStanje("Letališèe Jožeta Puènika Ljubljana");
     	   }

     	   if(((TextView) view).getText().equals("Letališèe Lesce")){
     		  app.SetStanje("");
     		  app.SetStanje("Letališèe Lesce");
     	   }
     	   if(((TextView) view).getText().equals("Letališèe Portorož")){
     		  app.SetStanje("");
     		  app.SetStanje("Letališèe Portorož");
        	   }
     	   if(((TextView) view).getText().equals("Lisca")){
     		  app.SetStanje("");
     		  app.SetStanje("Lisca");
        	   }
     	 if(((TextView) view).getText().equals("Ljubljana")){
     		app.SetStanje("");
     		app.SetStanje("Ljubljana");
       	   }
     	if(((TextView) view).getText().equals("Murska Sobota")){
     		app.SetStanje("");
     		app.SetStanje("Murska Sobota");
    	   }
     	if(((TextView) view).getText().equals("Novo mesto")){
     		app.SetStanje("");
     		app.SetStanje("Novo mesto");
  	   }

  	   if(((TextView) view).getText().equals("Postojna")){
  		 app.SetStanje(""); 
  		 app.SetStanje("Postojna");
  	   }
  	   if(((TextView) view).getText().equals("Rateèe")){
  		 app.SetStanje("");
  		 app.SetStanje("Rateèe");
     	   }
  	   if(((TextView) view).getText().equals("Slovenj Gradec")){
  		 app.SetStanje("");
  		 app.SetStanje("Slovenj Gradec");
     	   }
  	 if(((TextView) view).getText().equals("Vogel")){
  		app.SetStanje("");
  		app.SetStanje("Vogel");
    	   }
  	if(((TextView) view).getText().equals("Vojsko")){
  		app.SetStanje("");
  		app.SetStanje("Vojsko");
 	   } 
        	   
  		Intent novi= new Intent(view.getContext(),PodatkiVreme.class);
  		startActivity(novi);
        	  }
        	 });
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
			Vreme.this.setResult(RESULT_CANCELED);
			Vreme.this.finish();
			return true;
  		default:
  			break;
  		}
  		return false;
  	}
}