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

public class MenuPreferencesVlak extends Activity implements OnItemClickListener{
private String[] Countries = {"Poišèi najbližjo postajo",
"Doloèi pot",
"Vozni red",
"Prikaži lokale",
"Shrani potovanje",
"Shranjena potovanja"
};

private static final int SHRANI_POTOVANJE_ID=1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listmenu);
        
        ListView list = (ListView)findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listitem, Countries);
        list.setAdapter(adapter);
        list.setClickable(true);
        list.setOnItemClickListener(new OnItemClickListener() {
      	  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      	    
      	   Intent myIntent = null;
      	    
      	   if(((TextView) view).getText().equals("Poišèi najbližjo postajo")){
      	    myIntent = new Intent(view.getContext(), Vlak.class);
      	    startActivity(myIntent);
      	  MenuPreferencesVlak.this.finish();
      	   }
      	    
      	 if(((TextView) view).getText().equals("Doloèi pot")){
        	    myIntent = new Intent(view.getContext(), VnosPotiVlak.class);
        	    startActivity(myIntent);
        	    MenuPreferencesVlak.this.finish();
        	   }
      	 
      	   if(((TextView) view).getText().equals("Vozni red")){
      	    myIntent = new Intent(view.getContext(), Vlak.class);
      	    startActivity(myIntent);
      	  MenuPreferencesVlak.this.finish();
      	   }

      	   if(((TextView) view).getText().equals("Prikaži lokale")){
      	    myIntent = new Intent(view.getContext(), Vlak.class);
      	    startActivity(myIntent);
      	  MenuPreferencesVlak.this.finish();
      	   }
      	   if(((TextView) view).getText().equals("Shrani potovanje")){
         	    myIntent = new Intent(view.getContext(), ShraniPotovanje.class);
         	   startActivityForResult(myIntent,SHRANI_POTOVANJE_ID);
         	   }
      	   if(((TextView) view).getText().equals("Shranjena potovanja")){
         	    myIntent = new Intent(view.getContext(), ShranjenaPotovanja.class);
         	   startActivity(myIntent);
         	   MenuPreferencesVlak.this.finish();
         	   }


      	  }
      	 });
    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data){

		// See which child activity is calling us back.

		switch (requestCode) {
		case SHRANI_POTOVANJE_ID:
			if(resultCode == -1)
			{
				/*Toast toast = Toast.makeText(this,"resultCode="+resultCode , Toast.LENGTH_LONG);
				toast.show();*/
				MenuPreferencesVlak.this.setResult(RESULT_OK);
				MenuPreferencesVlak.this.finish();
				break;
			}
			else
			{
				break;
			}
			
		}
	}
}