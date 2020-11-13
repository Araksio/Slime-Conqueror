package entity;

import game.and.map.*;
import other.components.*;

public class Competence {
	
	private String name; // nom de la compétence
	private double cooldown; // temps avant de pouvoir utiliser de nouveau la compétence
	private int cost; // coût en mp de l'utilisation de la competence
	private long next;
	
	public Competence(String _name, double _cooldown, int _cost)
	{
		name = _name;
		cooldown = _cooldown;
		cost = _cost;
	}
	
	public static void println(String T)
	{
		System.out.println(T);
	}
	
	public String getNom()
	{
		return this.name;
	}
	
	public double getCooldown()
	{
		return this.cooldown;
	}
	
	public int getCost()
	{
		return this.cost;
	}
	
	public long getNext()
	{
		return this.next;
	}
	
	public void setNom(String _Nom)
	{
		this.name = _Nom;
	}
	
	public void setCooldown(double _Cooldown)
	{
		this.cooldown = _Cooldown;
	}
	
	public void setCost(int _Cost)
	{
		this.cost = _Cost;
	}
	
	public void setNext(long _Next)
	{
		this.next = _Next;
	}
	
	public boolean getCoolDownIsOver()
	{
		if(System.currentTimeMillis() > this.next)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void UseCompetence()
	{
		long NextUse = (long) (System.currentTimeMillis() + (this.cooldown * 1000));
		this.setNext(NextUse);
	}
	

}
