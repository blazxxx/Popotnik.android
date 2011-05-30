package edu.blazxxx.popotnik.android;

import android.app.Application;

public class Globalne extends Application{
	
	public String zacetekVlakStr,konecVlakStr,zacetekAvtobusStr,konecAvtobusStr,konecAvtoStr,stanje;
	
	public String GetZacetekVlakStr()
	{
		return zacetekVlakStr;
	}
	public String GetKonecVlakStr()
	{
		return konecVlakStr;
	}
	public String GetZacetekAvtobusStr()
	{
		return zacetekAvtobusStr;
	}
	public String GetKonecAvtobusStr()
	{
		return konecAvtobusStr;
	}
	public String GetKonecAvtoStr()
	{
		return konecAvtoStr;
	}
	public String GetStanje()
	{
		return stanje;
	}
	public void SetStanje(String vrednost)
	{
		stanje=vrednost;
	}
	public void SetZacetekAvtobusStr(String vrednost)
	{
		zacetekAvtobusStr=vrednost;
	}
	public void SetKonecAvtobusStr(String vrednost)
	{
		konecAvtobusStr=vrednost;
	}
	public void SetZacetekVlakStr(String vrednost)
	{
		zacetekVlakStr=vrednost;
	}
	public void SetKonecVlakStr(String vrednost)
	{
		konecVlakStr=vrednost;
	}
	public void SetKonecAvtoStr(String vrednost)
	{
		konecAvtoStr=vrednost;
	}

}
