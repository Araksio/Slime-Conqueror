package entity;

import game.and.map.*;
import other.components.*;

public class ItemsLoot extends Item {
	
	private Rarity RarityOfItems;

	ItemsLoot(int _UniqueID, int _ID, int _inventoryID, String _Nom, int _Type, int _CurrentDurability, int _MaxDurability, Rarity _RarityOfItems) {
		super(_UniqueID, _ID,_inventoryID,_Nom, 5, 1, 1);
		RarityOfItems = _RarityOfItems;
	}
	
	ItemsLoot(int _UniqueID, int _ID, int _inventoryID, String _Nom, int _Type, int _CurrentDurability, int _MaxDurability, String _Description, Rarity _RarityOfItems) {
		super(_UniqueID, _ID,_inventoryID,_Nom, 5, 1, 1,_Description);
		RarityOfItems = _RarityOfItems;
	}
	
	public Rarity getRarity()
	{
		return this.RarityOfItems;
	}
	
	public void setRarity (Rarity  _Rarity )
	{
		this.RarityOfItems = _Rarity;
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
