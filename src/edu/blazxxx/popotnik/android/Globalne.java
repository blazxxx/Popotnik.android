package edu.blazxxx.popotnik.android;

import android.app.Application;

public class Globalne extends Application{
	
	public String tipPrevoza="", zacetek="",konec="",stanje="",casPrihoda="",casOdhoda="",datum="";
	public String imeLokala="",naslov="",delovnik="",stran="",telefon="";
	public int id;
	public long DBid;
	private long dbID;
	final static long NO_ID=-1;
	public int stevilo=0;
	public Globalne()
	{
		id=(int) NO_ID;
		tipPrevoza="";
		zacetek="";
		konec="";
		stanje="";
		casPrihoda="";
		casOdhoda="";
		datum="";
		imeLokala="";
		naslov="";
		delovnik="";
		stran="";
		telefon="";
	}
	public long GetDBid()
	{
		return DBid;
	}
	public void SetDBid(long value)
	{
		DBid=value;
	}
	public int GetStevilo()
	{
		return stevilo;
	}
	public void SetStevilo(int value)
	{
		stevilo=value;
	}
	public String GetZacetek()
	{
		return zacetek;
	}
	public void SetZacetek(String value)
	{
		 zacetek=value;
	}
	public void SetKonec(String value)
	{
		 konec=value;
	}
	public String GetKonec()
	{
		return konec;
	}
	public String GetStanje()
	{
		return stanje;
	}
	public void SetStanje(String vrednost)
	{
		stanje=vrednost;
	}
	public void SetTipPrevoza(String value)
	{
		tipPrevoza=value;
	}
	public String GetTipPrevoza()
	{
		return tipPrevoza;
	}
	public String GetDatum()
	{
		return datum;
	}
	public void SetDatum(String vrednost)
	{
		datum=vrednost;
	}
	public String GetCasPrihoda()
	{
		return casPrihoda;
	}
	public void SetCasPrihoda(String vrednost)
	{
		casPrihoda=vrednost;
	}
	public String GetCasOdhoda()
	{
		return casOdhoda;
	}
	public void SetCasOdhoda(String vrednost)
	{
		casOdhoda=vrednost;
	}
	public void SetID(int value)
	{
		id=value;
	}
	public int GetID()
	{
		return id;
	}
	public void SetImeLokala(String value)
	{
		imeLokala=value;
	}
	public String GetImeLokala()
	{
		return imeLokala;
	}
	public void SetNaslov(String value)
	{
		naslov=value;
	}
	public String GetNaslov()
	{
		return naslov;
	}
	public void SetDelovnik(String value)
	{
		delovnik=value;
	}
	public String GetDelovnik()
	{
		return delovnik;
	}
	public void SetStran(String value)
	{
		stran=value;
	}
	public String GetStran()
	{
		return stran;
	}
	public void SetTelefon(String value)
	{
		telefon=value;
	}
	public String GetTelefon()
	{
		return telefon;
	}
	public void setDbID(long dbID) {
		this.dbID = dbID;
	}
	public long getDbID() {
		return dbID;
	}

}
