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
private String[] Countries = {"Poišèi najbližjo postajo",
"Doloèi pot",
"Vozni red",
"Prikaži lokale",
"Shrani potovanje",
"Shranjena potovanja"
};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listmenu);
        
        ListView list = (ListView)findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listitem, Countries);
        list.setAdapter(adapter);
        list.setClickable(true);
        //list.setOnItemClickListener(this);
        list.setOnItemClickListener(new OnItemClickListener() {
        	  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        	    
        	   Intent myIntent = null;
        	    
        	   if(((TextView) view).getText().equals("Poišèi najbližjo postajo")){
        	    myIntent = new Intent(view.getContext(), Avto.class);
        	   }
        	   
        	   if(((TextView) view).getText().equals("Doloèi pot")){
           	    myIntent = new Intent(view.getContext(), VnosPotiAvtobus.class);
           	   }
        	   
        	   if(((TextView) view).getText().equals("Vozni red")){
        	    myIntent = new Intent(view.getContext(), Avto.class);
        	   }

        	   if(((TextView) view).getText().equals("Prikaži lokale")){
        	    myIntent = new Intent(view.getContext(), Avto.class);
        	   }
        	   if(((TextView) view).getText().equals("Shrani potovanje")){
           	    myIntent = new Intent(view.getContext(), Avto.class);
           	   }
        	   if(((TextView) view).getText().equals("Shranjena potovanja")){
           	    myIntent = new Intent(view.getContext(), Avto.class);
           	   }

        	   startActivity(myIntent);
        	   MenuPreferencesAvtobus.this.finish();

        	  }
        	 });

    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		
	}
}