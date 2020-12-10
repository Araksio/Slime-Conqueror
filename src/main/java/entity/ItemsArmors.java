package entity;

/**
 * cette classe comporte les diférentes varibles importante pour leur fonctionement en jeu
 * 
 * @author Rémi
 */
public class ItemsArmors extends Item {
	
	private int Protection;
	private Rarity RarityOfArmors;

	ItemsArmors(int _UniqueID, int _ID,int _inventoryID, String _Nom, int _Type, int _CurrentDurability, int _MaxDurability, int _Protectio, Rarity _RarityOfArmors) {
		super(_UniqueID, _ID, _inventoryID, _Nom, 1, _CurrentDurability, _MaxDurability);
		Protection = _Protectio;
		RarityOfArmors = _RarityOfArmors;
	}
	
	public int getProtection()
	{
		return this.Protection;
	}
	
	public Rarity getRarity()
	{
		return this.RarityOfArmors;
	}
	
	public void setProtection(int _Protection)
	{
		this.Protection = _Protection;
	}
	
	public void setRarity (Rarity  _Rarity )
	{
		this.RarityOfArmors = _Rarity;
	}
	
	
	/**
	 * permet l'utilisation des objets
	 * 
	 * @author Rémi
	 */
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





	

