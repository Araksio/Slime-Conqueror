package entity;

import game.and.map.*;
import other.components.*;

public class ItemsArmes extends Item {
	
	private int Degat;
	private Rarity RarityOfWaepons;

	ItemsArmes(int _UniqueID, int _ID, int _inventoryID, String _Nom, int _Type, int _CurrentDurability, int _MaxDurability, int _Degat, Rarity _RarityOfWaepons) {
		super(_UniqueID, _ID,_inventoryID,_Nom, 0, _CurrentDurability, _MaxDurability);
		Degat = _Degat;
		RarityOfWaepons = _RarityOfWaepons;
	}
	
	public int getDegat()
	{
		return this.Degat;
	}
	
	public Rarity getRarity()
	{
		return this.RarityOfWaepons;
	}
	
	public void setDegat(int _Degat)
	{
		this.Degat = _Degat;
	}
	
	public void setRarity (Rarity  _Rarity )
	{
		this.RarityOfWaepons = _Rarity;
	}
	
	
	
	public void Use()
	{
		int D = this.getCurrentDurability();
		D--;
		this.setCurrentDurability(D);
		
		if (this.getCurrentDurability() <= 0)
		{
			this.Destruct();
		}
	}
	
	public void Destruct()
	{
		
	}

}
