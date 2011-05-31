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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SpremeniPotovanje extends Activity implements OnClickListener{

	Globalne app;
	public static final int TEST_START_ACTIVITY_ID = 1;
	Button spremeni;
	Spinner podatek;
	TextView prviTxt,drugiTxt,tretjiTxt,cetrtiTxt,petiTxt,sestiTxt,sedmiTxt,osmiTxt,devetiTxt,desetiTxt,enajstiTxt;
	EditText prviEdit, drugiEdit, tretjiEdit,cetrtiEdit,petiEdit,sestiEdit, sedmiEdit,osmiEdit,devetiEdit,desetiEdit,enajstiEdit;
	ArrayAdapter adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spremeni_potovanje);
		app =(Globalne) getApplication();
		podatek = (Spinner) findViewById(R.id.spinner1);
		prviTxt = (TextView) findViewById(R.id.txtSpremeni2);
		drugiTxt = (TextView) findViewById(R.id.txtSpremeni3);
		tretjiTxt = (TextView) findViewById(R.id.txtSpremeni4);
		cetrtiTxt = (TextView) findViewById(R.id.txtSpremeni5);
		petiTxt = (TextView) findViewById(R.id.txtSpremeni6);
		sestiTxt = (TextView) findViewById(R.id.txtSpremeni7);
		sedmiTxt = (TextView) findViewById(R.id.txtSpremeni8);
		osmiTxt = (TextView) findViewById(R.id.txtSpremeni9);
		devetiTxt = (TextView) findViewById(R.id.txtSpremeni10);
		desetiTxt = (TextView) findViewById(R.id.txtSpremeni11);
		enajstiTxt = (TextView) findViewById(R.id.txtSpremeni12);
		prviEdit = (EditText) findViewById(R.id.editTxtSpremeni1);
		drugiEdit = (EditText) findViewById(R.id.editTxtSpremeni2);
		tretjiEdit = (EditText) findViewById(R.id.editTxtSpremeni3);
		cetrtiEdit = (EditText) findViewById(R.id.editTxtSpremeni4);
		petiEdit = (EditText) findViewById(R.id.editTxtSpremeni5);
		sestiEdit = (EditText) findViewById(R.id.editTxtSpremeni6);
		sedmiEdit = (EditText) findViewById(R.id.editTxtSpremeni7);
		osmiEdit = (EditText) findViewById(R.id.editTxtSpremeni8);
		devetiEdit = (EditText) findViewById(R.id.editTxtSpremeni9);
		desetiEdit = (EditText) findViewById(R.id.editTxtSpremeni10);
		enajstiEdit = (EditText) findViewById(R.id.editTxtSpremeni11);
		adapter = ArrayAdapter.createFromResource(
				this, R.array.podatki, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		podatek.setAdapter(adapter);
		spremeni = (Button) findViewById(R.id.btnSpremeniSpremeni);
		spremeni.setOnClickListener(this);
		prviEdit.setText(app.GetZacetek());
		drugiEdit.setText(app.GetKonec());
		tretjiEdit.setText(app.GetTipPrevoza());
		cetrtiEdit.setText(app.GetCasOdhoda());
		petiEdit.setText(app.GetCasPrihoda());
		sestiEdit.setText(app.GetNaslov());
		sedmiEdit.setText(app.GetDelovnik());
		osmiEdit.setText(app.GetStran());
		devetiEdit.setText(app.GetTelefon());
		desetiEdit.setText(app.GetDatum());
		enajstiEdit.setText(app.GetImeLokala());
		SpremeniPotovanje.this.setResult(RESULT_OK);
		podatek.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView, View
					selectedItemView, int position, long id) {
				if(position==0)
				{
					prviEdit.setVisibility(View.VISIBLE);
					prviTxt.setVisibility(View.VISIBLE);
					drugiEdit.setVisibility(View.VISIBLE);
					drugiTxt.setVisibility(View.VISIBLE);
					tretjiEdit.setVisibility(View.VISIBLE);
					tretjiTxt.setVisibility(View.VISIBLE);
					cetrtiEdit.setVisibility(View.VISIBLE);
					cetrtiTxt.setVisibility(View.VISIBLE);
					petiEdit.setVisibility(View.VISIBLE);
					petiTxt.setVisibility(View.VISIBLE);
					desetiTxt.setVisibility(View.VISIBLE);
					desetiEdit.setVisibility(View.VISIBLE);

					sestiEdit.setVisibility(View.GONE);
					sestiTxt.setVisibility(View.GONE);
					sedmiEdit.setVisibility(View.GONE);
					sedmiTxt.setVisibility(View.GONE);
					osmiEdit.setVisibility(View.GONE);
					osmiTxt.setVisibility(View.GONE);
					devetiEdit.setVisibility(View.GONE);
					devetiTxt.setVisibility(View.GONE);
					enajstiTxt.setVisibility(View.GONE);
					enajstiEdit.setVisibility(View.GONE);

					prviTxt.setText("Zaèetna lokacija: ");
					drugiTxt.setText("Konèna lokacija:  ");
					tretjiTxt.setText("Tip prevoza:      ");
					desetiTxt.setText("Datum:            ");
					cetrtiTxt.setText("Èas odhoda:       ");
					petiTxt.setText("Èas prihoda:      ");	
					
				}
				else if (position==1)
				{
					prviEdit.setVisibility(View.GONE);
					prviTxt.setVisibility(View.GONE);
					drugiEdit.setVisibility(View.GONE);
					drugiTxt.setVisibility(View.GONE);
					tretjiEdit.setVisibility(View.GONE);
					tretjiTxt.setVisibility(View.GONE);
					cetrtiEdit.setVisibility(View.GONE);
					cetrtiTxt.setVisibility(View.GONE);
					petiEdit.setVisibility(View.GONE);
					petiTxt.setVisibility(View.GONE);
					desetiTxt.setVisibility(View.GONE);
					desetiEdit.setVisibility(View.GONE);

					sestiEdit.setVisibility(View.VISIBLE);
					sestiTxt.setVisibility(View.VISIBLE);
					sedmiEdit.setVisibility(View.VISIBLE);
					sedmiTxt.setVisibility(View.VISIBLE);
					osmiEdit.setVisibility(View.VISIBLE);
					osmiTxt.setVisibility(View.VISIBLE);
					devetiEdit.setVisibility(View.VISIBLE);
					devetiTxt.setVisibility(View.VISIBLE);
					enajstiTxt.setVisibility(View.VISIBLE);
					enajstiEdit.setVisibility(View.VISIBLE);

					enajstiTxt.setText("Ime:              ");
					sestiTxt.setText("Kraj:             ");
					sedmiTxt.setText("Delovni èas:      ");
					osmiTxt.setText("Spletna stran:    ");
					devetiTxt.setText("Telefon:          ");
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

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
			SpremeniPotovanje.this.setResult(RESULT_CANCELED);
			SpremeniPotovanje.this.finish();
			return true;

		default:
			break;
		}

		return false;
	}
	@Override
	public void onClick(View arg0) {
		if (arg0.getId()==R.id.btnSpremeniSpremeni) 
		{
			app.SetZacetek(prviEdit.getText().toString());
			app.SetKonec(drugiEdit.getText().toString());
			app.SetTipPrevoza(tretjiEdit.getText().toString());
			app.SetCasOdhoda(cetrtiEdit.getText().toString());
			app.SetCasPrihoda(petiEdit.getText().toString());
			app.SetNaslov(sestiEdit.getText().toString());
			app.SetDelovnik(sedmiEdit.getText().toString());
			app.SetStran(osmiEdit.getText().toString());
			app.SetTelefon(devetiEdit.getText().toString());
			app.SetDatum(desetiEdit.getText().toString());
			app.SetImeLokala(enajstiEdit.getText().toString());
			SpremeniPotovanje.this.finish();
		}

	}

}