package edu.blazxxx.popotnik.android.Database;


import edu.blazxxx.popotnik.android.PodatkiOPotovanju;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class DBAdapter implements BaseColumns {
	public static final  String TAG="DBAdapterStevec";

	public static final  String ZACETNA_LOKACIJA = "i_value";
	public static final  String ZACETEK = "s_name";
	public static final  String KONCNA_LOKACIJA = "i_value";
	public static final  String KONEC = "s_name";
	public static final  String TIP_PREVOZA = "i_value";
	public static final  String TIP = "s_name";
	public static final  String NASLOV_LOKALA = "i_value";
	public static final  String NASLOV = "s_name";
	public static final  String DELOVNIK_LOKALA = "i_value";
	public static final  String DELOVNIK = "s_name";
	public static final  String STRAN_LOKALA = "i_value";
	public static final  String STRAN = "s_name";
	public static final  String TELEFON_LOKALA = "i_value";
	public static final  String TELEFON = "s_name";
	public static final  int POS__ID=0;
	public static final  int POS_NAME=1;
	public static final  int POS_VALUE=2;

	public static final  String TABLE="rezultat";



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
	public long insertStevc(PodatkiOPotovanju podatki) 
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(ZACETEK , "Zaèetna lokacija"); 
		initialValues.put(ZACETNA_LOKACIJA, podatki.getZacetna()); 
		initialValues.put(KONEC, "Konèna lokacija"); 
		initialValues.put(KONCNA_LOKACIJA, podatki.getKoncna());
		initialValues.put(TIP, "Tip"); 
		initialValues.put(TIP_PREVOZA, podatki.getTip());
		initialValues.put(NASLOV, "Naslov"); 
		initialValues.put(NASLOV_LOKALA, podatki.getNaslov());
		initialValues.put(DELOVNIK, "Delovnik"); 
		initialValues.put(DELOVNIK_LOKALA, podatki.getDelovnik());
		initialValues.put(STRAN, "Stran"); 
		initialValues.put(STRAN_LOKALA, podatki.getStran());
		initialValues.put(TELEFON, "Telefon"); 
		initialValues.put(TELEFON_LOKALA, podatki.getTelefon());
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
	public Cursor getStevec(long rowId) throws SQLException 
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
	public boolean updateStevec(PodatkiOPotovanju podatki) 
	{
		ContentValues args = new ContentValues();
		args.put(ZACETEK , "Zaèetna lokacija"); 
		args.put(ZACETNA_LOKACIJA, podatki.getZacetna()); 
		args.put(KONEC, "Konèna lokacija"); 
		args.put(KONCNA_LOKACIJA, podatki.getKoncna());
		args.put(TIP, "Tip"); 
		args.put(TIP_PREVOZA, podatki.getTip());
		args.put(NASLOV, "Naslov"); 
		args.put(NASLOV_LOKALA, podatki.getNaslov());
		args.put(DELOVNIK, "Delovnik"); 
		args.put(DELOVNIK_LOKALA, podatki.getDelovnik());
		args.put(STRAN, "Stran"); 
		args.put(STRAN_LOKALA, podatki.getStran());
		args.put(TELEFON, "Telefon"); 
		args.put(TELEFON_LOKALA, podatki.getTelefon());
		return db.update(TABLE, args, 
				_ID + "=" + podatki.getID(), null) > 0;
	}


}
