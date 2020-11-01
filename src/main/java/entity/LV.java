package entity;

import game.and.map.*;
import other.components.*;

public class LV {
	
	private int Niveau;
	private double TotalXP;
	private double CurentXPforLV;
	private double XPneedForNextLV;
	
	LV(int _Niveau, double _TotalXP, double _CurentXPforLV, double _XPneedForNextLV)
	{
		Niveau = _Niveau;
		TotalXP = _TotalXP;
		CurentXPforLV = _CurentXPforLV;
		XPneedForNextLV = _XPneedForNextLV;
	}
	
	public int getNiveau()
	{
		return this.Niveau;
	}
	
	public double getTotalXP()
	{
		return this.TotalXP;
	}
	
	public double getCurentXPforLV()
	{
		return this.CurentXPforLV;
	}
	
	public double getXPneedForNextLV()
	{
		return this.XPneedForNextLV;
	}
	
	
	
	public void setNiveau(int _Niveau)
	{
		this.Niveau = _Niveau;
	}
	
	public void setTotalXP(double _TotalXP)
	{
		this.TotalXP = _TotalXP;
	}
	
	public void setCurentXPforLV(double _CurentXPforLV)
	{
		this.CurentXPforLV = _CurentXPforLV;
	}
	
	public void setXPneedForNextLV(double _XPneedForNextLV)
	{
		this.XPneedForNextLV = _XPneedForNextLV;
	}
	
	
	
	public void addXP(double _xp)
	{
		this.TotalXP = this.TotalXP + _xp;
		this.CurentXPforLV = this.CurentXPforLV + _xp;
	}
	
	public void checkLVisAvalaible()
	{
		if(this.CurentXPforLV >= this.XPneedForNextLV)
		{
			while(this.CurentXPforLV >= this.XPneedForNextLV)
			{
				this.CurentXPforLV = this.CurentXPforLV - this.XPneedForNextLV;
				Niveau++;
			}
		}
	}

}
