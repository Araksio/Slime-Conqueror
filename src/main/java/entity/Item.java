package entity;

import game.and.map.*;
import other.components.*;

public class Item{
	
	private int UniqueID;             // ID unique serviras pour identifier un objet en particulier dans la base de donn�e
	private int ID;                   // ID de l'object pr�defini (exemple toute les pommes ont l'id 5)
	private String Nom;               // Nom de l'objet
	private int Type;                 // type d'objet sert a s�pare les clase dans l'inventaire
	private int CurrentDurability;    // durabilit� actuelle de l'objet
	private int MaxDurability;        // durabilit� maximum qu'a l'objet au debut
	private int InventoryID;          // ID de l'object dans l'inventaire
	
	Item(int _UniqueID,int _ID, int _inventoryID, String _Nom,int _Type,int _CurrentDurability,int _MaxDurability)
	{
		UniqueID = _UniqueID;
		UniqueID = _ID;
		InventoryID = _inventoryID;
		Nom = _Nom;
		Type = _Type;
		CurrentDurability = _CurrentDurability;
		MaxDurability = _MaxDurability;
		
	}
	
	public static void println(String T)
	{
		System.out.println(T);
	}
	
	
	public void showDurability()
	{
		println("La durabilit� de l'arme est de : " + this.getCurrentDurability() + "/" + this.getMaxDurability());
	}	
	
	public int getUniqueID()
	{
		return this.UniqueID;
	}
	
	public int getID()
	{
		return this.ID;
	}
	
	public int getInventoryID()
	{
		return this.InventoryID;
	}
	
	public String getNom()
	{
		return this.Nom;
	}
	
	public int getType()
	{
		return this.Type;
	}
	
	public int getCurrentDurability()
	{
		return this.CurrentDurability;
	}
	
	public int getMaxDurability()
	{
		return this.MaxDurability;
	}
	
	
	
	public void setUniqueID(int _UniqueID)
	{
		this.UniqueID = _UniqueID;
	}
	
	public void setInventoryID(int _InventoryID)
	{
		this.InventoryID = _InventoryID;
	}
	
	public void setID(int _ID)
	{
		this.ID = _ID;
	}
	
	public void setNom(String _Nom)
	{
		this.Nom = _Nom;
	}
	
	public void setType(int _Type)
	{
		this.Type = _Type;
	}
	
	public void setCurrentDurability(int _CurrentDurability)
	{
		this.CurrentDurability = _CurrentDurability;
	}
	
	public void setMaxDurability(int _MaxDurability)
	{
		this.MaxDurability = _MaxDurability;
	}
}
