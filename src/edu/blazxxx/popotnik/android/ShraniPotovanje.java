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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ShraniPotovanje extends Activity implements OnClickListener{

	public static final int TEST_START_ACTIVITY_ID = 1;
	Button shrani,spremeni;
	Spinner podatek;
	TextView prvi,drugi,tretji,cetrti,peti,sesti,sedmi;
	ArrayAdapter adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shrani_potovanje);
		podatek = (Spinner) findViewById(R.id.spinner1);
		prvi = (TextView) findViewById(R.id.txtShrani1);
		drugi = (TextView) findViewById(R.id.txtShrani2);
		tretji = (TextView) findViewById(R.id.txtShrani3);
		cetrti = (TextView) findViewById(R.id.txtShrani4);
		peti = (TextView) findViewById(R.id.txtShrani5);
		sesti = (TextView) findViewById(R.id.txtShrani6);
		sedmi = (TextView) findViewById(R.id.txtShrani7);
		adapter = ArrayAdapter.createFromResource(
				this, R.array.podatki, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		podatek.setAdapter(adapter);
		shrani = (Button) findViewById(R.id.btnShrani);
		spremeni = (Button) findViewById(R.id.btnSpremeni);
		shrani.setOnClickListener(this);
		spremeni.setOnClickListener(this);
		podatek.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if(arg2==0)
    			{
    				drugi.setText("Zacetna lokacija:");
    				cetrti.setText("Koncna lokacija:");
    				sesti.setText("Tip prevoza:");
    			}
    			else if (arg2==1)
    			{
    				drugi.setText("Kraj:");
    				cetrti.setText("Delovni èas:");
    				sesti.setText("Tip lokala:");
    			}
				
			}
		});
		
		

	}

	Menu mMenu;
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		mMenu = menu;
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, mMenu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.dialogTest:
			ShraniPotovanje.this.setResult(RESULT_CANCELED);
			ShraniPotovanje.this.finish();
			return true;

		default:
			break;
		}

		return false;
	}
	@Override
	public void onClick(View arg0) {
		if (arg0.getId()==R.id.btnSpremeni) 
		{
			if(podatek.getSelectedItemId()==0)
			{
				drugi.setText("Zacetna lokacija:");
				cetrti.setText("Koncna lokacija:");
				sesti.setText("Tip prevoza:");
			}
			else if (podatek.getSelectedItemId()==1)
			{
				drugi.setText("Zacetna lokacija:");
				cetrti.setText("Koncna lokacija:");
				sesti.setText("Tip prevoza:");
			}
		}

	}

}