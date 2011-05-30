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
private String[] Countries = {"Vnesi potovanje",
//"Prika�i pot",
"Prika�i lokale",
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
        //list.setClickable(true);
        list.setOnItemClickListener(new OnItemClickListener() {
      	  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      	    
      	   Intent myIntent = null;
      	    
      	   if(((TextView) view).getText().equals("Vnesi potovanje")){
      	    myIntent = new Intent(view.getContext(), VnosPotiAvto.class);
      	    startActivityForResult(myIntent, VNESENA_POT_ID);
      	   }
      	    
      	   if(((TextView) view).getText().equals("Prika�i pot")){
      	    myIntent = new Intent(view.getContext(), Avto.class);
      	    MenuPreferencesAvto.this.finish();
      	  startActivity(myIntent);
      	   }

      	   if(((TextView) view).getText().equals("Prika�i lokale")){
      	    myIntent = new Intent(view.getContext(), Avto.class);
      	    MenuPreferencesAvto.this.finish();
      	    startActivity(myIntent);
      	   }
      	   if(((TextView) view).getText().equals("Shrani potovanje")){
         	    myIntent = new Intent(view.getContext(), ShraniPotovanje.class);
         	    MenuPreferencesAvto.this.finish();
         	   startActivity(myIntent);
         	   }
      	   if(((TextView) view).getText().equals("Shranjena potovanja")){
         	    myIntent = new Intent(view.getContext(), ShranjenaPotovanja.class);
         	    //MenuPreferencesAvto.this.finish();
         	    startActivity(myIntent);
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

		case VNESENA_POT_ID: 
			if(resultCode == -1)
			{
				Toast toast = Toast.makeText(this,"resultCode="+resultCode , Toast.LENGTH_LONG);
				toast.show();
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