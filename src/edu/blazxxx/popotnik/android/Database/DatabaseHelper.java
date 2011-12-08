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
		+DBAdapter.ZACETNA_LOKACIJA+" STRING, "
		+DBAdapter.KONCNA_LOKACIJA + " STRING, " + DBAdapter.TIP_PREVOZA + " STRING, "
		 + DBAdapter.DATUM_PREVOZA + " STRING, " + DBAdapter.CAS_ODHODA_PREVOZA + " STRING, "
		 + DBAdapter.CAS_PRIHODA_PREVOZA + " STRING, "+ DBAdapter.IME_LOKALA + " STRING, "
		 + DBAdapter.NASLOV_LOKALA + " STRING, " 
		+DBAdapter.DELOVNIK_LOKALA + " STRING, " + DBAdapter.STRAN_LOKALA + " STRING, "
		+ DBAdapter.TELEFON_LOKALA + " STRING);";

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
