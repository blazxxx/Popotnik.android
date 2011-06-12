package edu.blazxxx.popotnik.android.Database;


import edu.blazxxx.popotnik.android.Globalne;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class DBAdapter implements BaseColumns {
	public static final  String TAG="DBAdapterStevec";

	public static final  String ZACETNA_LOKACIJA = "zacetna_lokacija_value";
	public static final  String ZACETEK = "Zacetna";
	public static final  String KONCNA_LOKACIJA = "koncna_lokacija_value";
	public static final  String KONEC = "Koncna";
	public static final  String TIP_PREVOZA = "tip_prevoza_value";
	public static final  String TIP = "Tip";
	public static final  String DATUM_PREVOZA = "datum_prevoza_value";
	public static final  String DATUM = "Datum";
	public static final  String CAS_ODHODA_PREVOZA = "cas_odhoda_value";
	public static final  String CAS_ODHODA = "Odhod";
	public static final  String CAS_PRIHODA_PREVOZA = "cas_prihoda_value";
	public static final  String CAS_PRIHODA = "Prihod";
	public static final  String IME_LOKALA = "ime_lokala_value";
	public static final  String IME = "Ime";
	public static final  String NASLOV_LOKALA = "naslov_lokala_value";
	public static final  String NASLOV = "Naslov";
	public static final  String DELOVNIK_LOKALA = "delovnik_lokala_value";		//i_value
	public static final  String DELOVNIK = "Delovnik";
	public static final  String STRAN_LOKALA = "stran_lokala_value";
	public static final  String STRAN = "Stran";
	public static final  String TELEFON_LOKALA = "telefon_lokala_value";
	public static final  String TELEFON = "Telefon";
	public static final  int POS__ID=0;
	public static final  int POS_ZACETEK=1;
	public static final  int POS_KONEC=2;
	public static final  int POS__TIP=3;
	public static final  int POS_DATUM=4;
	public static final  int POS_ODHOD=5;
	public static final  int POS__PRIHOD=6;
	public static final  int POS_IME=7;
	public static final  int POS_NASLOV=8;
	public static final  int POS__DELOVNIK=9;
	public static final  int POS_STRAN=10;
	public static final  int POS_TELEFON=11;

	public static final  String TABLE="potovanje";



	private final Context context;

	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public DBAdapter(Context ctx) 
	{
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}


	//---opens the database---
	public DBAdapter open() throws SQLException 
	{
		db = DBHelper.getWritableDatabase();
		return this;
	}

	//---closes the database---    
	public void close() 
	{
		DBHelper.close();
	}

	//---insert a stevec
	public long insertPotovanje(Globalne podatki) 
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(ZACETEK , "Zacetna"); 
		initialValues.put(ZACETNA_LOKACIJA, podatki.GetZacetek());	
		initialValues.put(KONEC, "Koncna"); 
		initialValues.put(KONCNA_LOKACIJA, podatki.GetKonec());
		initialValues.put(TIP, "Tip"); 
		initialValues.put(TIP_PREVOZA, podatki.GetTipPrevoza());
		initialValues.put(DATUM, "Datum"); 
		initialValues.put(DATUM_PREVOZA, podatki.GetDatum());
		initialValues.put(NASLOV, "Naslov"); 
		initialValues.put(CAS_ODHODA, "Odhod"); 
		initialValues.put(CAS_ODHODA_PREVOZA, podatki.GetCasOdhoda());
		initialValues.put(CAS_PRIHODA, "Prihod"); 
		initialValues.put(CAS_PRIHODA_PREVOZA, podatki.GetCasPrihoda());
		initialValues.put(IME, "Ime");
		initialValues.put(IME_LOKALA, podatki.GetImeLokala());
		initialValues.put(NASLOV, "Naslov");
		initialValues.put(NASLOV_LOKALA, podatki.GetNaslov());
		initialValues.put(DELOVNIK, "Delovnik"); 
		initialValues.put(DELOVNIK_LOKALA, podatki.GetDelovnik());
		initialValues.put(STRAN, "Stran"); 
		initialValues.put(STRAN_LOKALA, podatki.GetStran());
		initialValues.put(TELEFON, "Telefon"); 
		initialValues.put(TELEFON_LOKALA, podatki.GetTelefon());
		return db.insert(TABLE, null, initialValues);
	}

	//---deletes a particular title---
	public boolean deleteStevec(long rowId) 
	{
		return db.delete(TABLE, _ID + "=" + rowId, null) > 0;
	}

	//---retrieves all the titles---
	public Cursor getAll() 
	{
		return db.query(TABLE, new String[] {
				_ID,       
				ZACETEK,     
				ZACETNA_LOKACIJA,
				KONEC,
				KONCNA_LOKACIJA,
				TIP,
				TIP_PREVOZA,
				DATUM,
				DATUM_PREVOZA,
				CAS_ODHODA,
				CAS_ODHODA_PREVOZA,
				CAS_PRIHODA,
				CAS_PRIHODA_PREVOZA,
				IME,
				IME_LOKALA,
				NASLOV,
				NASLOV_LOKALA,
				DELOVNIK,
				DELOVNIK_LOKALA,
				STRAN,
				STRAN_LOKALA,
				TELEFON,
				TELEFON_LOKALA},    
				null, 
				null, 
				null, 
				null, 
				null);
	}

	//---retrieves a particular title---
	public Cursor getPotovanje(long rowId) throws SQLException 
	{
		Cursor mCursor =
			db.query(true, TABLE, new String[] {
					_ID,       
					ZACETEK,     
					ZACETNA_LOKACIJA,
					KONEC,
					KONCNA_LOKACIJA,
					TIP,
					TIP_PREVOZA,
					DATUM,
					DATUM_PREVOZA,
					CAS_ODHODA,
					CAS_ODHODA_PREVOZA,
					CAS_PRIHODA,
					CAS_PRIHODA_PREVOZA,
					IME,
					IME_LOKALA,
					NASLOV,
					NASLOV_LOKALA,
					DELOVNIK,
					DELOVNIK_LOKALA,
					STRAN,
					STRAN_LOKALA,
					TELEFON,
					TELEFON_LOKALA}, 
					_ID + "=" + rowId, 
					null,
					null, 
					null, 
					null, 
					null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	//---update---
	public boolean updatePotovanje(Globalne podatki) 
	{
		ContentValues args = new ContentValues();
		args.put(ZACETEK , "Zacetna"); 
		args.put(ZACETNA_LOKACIJA, podatki.GetZacetek()); 
		args.put(KONEC, "Koncna"); 
		args.put(KONCNA_LOKACIJA, podatki.GetKonec());
		args.put(TIP, "Tip"); 
		args.put(TIP_PREVOZA, podatki.GetTipPrevoza());
		args.put(DATUM, "Datum"); 
		args.put(DATUM_PREVOZA, podatki.GetDatum());
		args.put(NASLOV, "Naslov"); 
		args.put(CAS_ODHODA, "Odhod"); 
		args.put(CAS_ODHODA_PREVOZA, podatki.GetCasOdhoda());
		args.put(CAS_PRIHODA, "Prihoda"); 
		args.put(CAS_PRIHODA_PREVOZA, podatki.GetCasPrihoda());
		args.put(IME, "Ime");
		args.put(IME_LOKALA, podatki.GetImeLokala());
		args.put(NASLOV, "Naslov"); 
		args.put(NASLOV_LOKALA, podatki.GetNaslov());
		args.put(DELOVNIK, "Delovnik"); 
		args.put(DELOVNIK_LOKALA, podatki.GetDelovnik());
		args.put(STRAN, "Stran"); 
		args.put(STRAN_LOKALA, podatki.GetStran());
		args.put(TELEFON, "Telefon"); 
		args.put(TELEFON_LOKALA, podatki.GetTelefon());
		return db.update(TABLE, args, 
				_ID + "=" + podatki.GetID(), null) > 0;
	}


}
