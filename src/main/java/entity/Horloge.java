package entity;

import game.and.map.*;
import other.components.*;

public class Horloge {

	private int day;
	private int heure;
	private int minute;
	
	Horloge(int _day, int _heure, int _minute)
	{
		day = _day;
		heure = _heure;
		minute = _minute;
	}
	
	public static void println(String T)
	{
		System.out.println(T);
	}	
	
	public int getDay()
	{
		return this.day;
	}
	
	public int getHours()
	{
		return this.heure;
	}
	
	public int getMinute()
	{
		return this.minute;
	}
	
	public void setDay(int d)
	{
		this.day = d;
	}
	
	public void setHours(int h)
	{
		this.heure = h;
	}
	
	public void setMinute(int m)
	{
		this.minute = m; 
	}
	
	public void addHours(int h)
	{
		this.heure = this.heure + h;
	}
	
	public void addDays(int d)
	{
		this.day = this.day + d;
	}
	
	public void addMinute(int m)
	{
		this.minute = this.minute + m;
	}
	
	public void showHours() {
		println("" + this.getHours() + ":" + this.getMinute() );
	}
	
	public void addtimes(int h, int m)
	{
		this.heure = this.heure + h;
		this.minute = this.minute + m;
	}
	
	public void VerifyTimes()
	{
		if(this.minute >= 60)
		{
			while(this.minute >= 60)
			{
				this.heure++;
				this.minute = this.minute-60;
			}
		}
		if (this.heure >= 24)
		{
			while(this.heure >= 24)
			{
				this.day++;
				this.heure = this.heure - 24;
			}
		}
		
	}
	
	public void VerifySleep()
	{
		if(this.heure > 21) {
			this.heure = 7;
			this.minute = 0;
			this.day++;
			
			println(" C'est l'heure de dormir. Tu regagnes ton carton, et entammes une nuit tourment�e par les rats.");
		}
	}
	
	
	
}
