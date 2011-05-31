package edu.blazxxx.popotnik.android.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public  class DatabaseHelper extends SQLiteOpenHelper 
{	

	public static final  String TAG="DatabaseHelper";
	private static final int DATABASE_VERSION = 2;
	private static final String DATABASE_NAME = "mojtest";
	private static final String DATABASE_CREATE =
		"create table "+DBAdapter.TABLE+" ("+DBAdapter._ID+" integer primary key autoincrement, "
		+ DBAdapter.ZACETEK+" TEXT not null, "+DBAdapter.ZACETNA_LOKACIJA+" STRING, " + DBAdapter.KONEC + " TEXT not null, "
		+DBAdapter.KONCNA_LOKACIJA + " STRING, " + DBAdapter.TIP + " TEXT not null, " + DBAdapter.TIP_PREVOZA + " STRING, "
		+ DBAdapter.DATUM + " TEXT not null, " + DBAdapter.DATUM_PREVOZA + " STRING, "+ DBAdapter.CAS_ODHODA + " TEXT not null, " + DBAdapter.CAS_ODHODA_PREVOZA + " STRING, "
		+ DBAdapter.CAS_PRIHODA + " TEXT not null, " + DBAdapter.CAS_PRIHODA_PREVOZA + " STRING, "+ DBAdapter.IME + " TEXT not null, " + DBAdapter.IME_LOKALA + " STRING, "
		+ DBAdapter.NASLOV + " TEXT not null, " + DBAdapter.NASLOV_LOKALA + " STRING, " + DBAdapter.DELOVNIK + " TEXT not null, "
		+DBAdapter.DELOVNIK_LOKALA + " STRING, " + DBAdapter.STRAN + " TEXT not null, " + DBAdapter.STRAN_LOKALA + " STRING, "
		+DBAdapter.TELEFON + " TEXT not null, "+ DBAdapter.TELEFON_LOKALA + "STRING);";

	DatabaseHelper(Context context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, 
			int newVersion) 
	{
		Log.w(TAG, "Upgrading database from version " + oldVersion 
				+ " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS "+DBAdapter.TABLE);
		onCreate(db);
	}
}
