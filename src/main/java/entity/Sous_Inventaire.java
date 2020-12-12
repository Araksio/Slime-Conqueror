package entity;

import java.util.ArrayList;

/*
* cette classe permet de gerer les sous partie dont l'inventaire est constitué
* @author Pitohui.G7
*
*/

public class Sous_Inventaire {
	
	private String Nom; // permet de donner un nom au son inventaire (utile pour l'affichage)
	public Item CurrentSousInventory[]= new Item[999]; // créer un tableau de 999 element pour mettre des item
	public int CurrentSousInventoryNumber[]= new int[999]; // créer un tableau de 999 element pour mettre la quantité d'item a la même position que l'item dans l'autre tableau
	



	public int[] getCurrentSousInventoryNumber() {
		return CurrentSousInventoryNumber; 
	}

	public void setCurrentSousInventoryNumber(int[] currentSousInventoryNumber) {
		CurrentSousInventoryNumber = currentSousInventoryNumber;
	}

	public Sous_Inventaire(String _Nom)
	{
		Nom = _Nom;
	}
	
	public static void println(String T)
	{
		System.out.println(T);
	}
	
	public String getNom()
	{
		return this.Nom;
	}
	
	public Item [] getInventory()
	{
		return this.CurrentSousInventory;
	}
	
	// fonction qui permet de savoir la quantité d'un item en particulier dans le sous inventaire
	public int getQuantityOfAnItem(Item IT)
	{
		for(int i = 0; i < 999; i++)
		{
			if(this.CurrentSousInventory[i].getID() == IT.getID())
			{
				return CurrentSousInventoryNumber[i];
			}
		}
		return 0;
	}
	
	// fonction qui permet de savoir si un item existe dans l'inventaire
	public boolean ItemsExistOnInventory(Item IT)
	{
		for(int i = 0; i < 999; i++)
		{
			if(this.CurrentSousInventory[i].getID() == IT.getID())
			{
				return true;
			}
		}
		return false;
	}
	
	// fonction qui permet d'ajouter un item en 1 exemplaire dans l'inventaire
	public void addItems(Item IT)
	{
	
		int IID = IT.getInventoryID();
		println("ID" + IID);
		if(this.CurrentSousInventory[IID] == null)
		{
			this.CurrentSousInventory[IID] = IT;
			this.CurrentSousInventoryNumber[IID]++;
		}
		else
		{
			this.CurrentSousInventoryNumber[IID]++;
		}
	}
	
	// fonction qui permet d'ajouter un item en plusieurs exemplaires (Quantity) dans l'inventaire
	public void addItems(Item IT,int quantity)
	{
		int IID = IT.getInventoryID();
		if(this.CurrentSousInventory[IID] == null)
		{
			this.CurrentSousInventory[IID] = IT;
			this.CurrentSousInventoryNumber[IID] = this.CurrentSousInventoryNumber[IID] + quantity;;
		}
		else
		{
			this.CurrentSousInventoryNumber[IID] = this.CurrentSousInventoryNumber[IID] + quantity;
		}
	}
	
	// fonction qui permet de savoir la quantité d'objet (y compris en plusieurs exemplaire) dans l'inventaire
	public int totalItems()
	{
		int total = 0;
		for(int i = 0; i < 999; i++)
		{
			total = total + this.CurrentSousInventoryNumber[i];
		}
		
		return total;
	}
	
	// fonction dev qui permet d'affichier le sous inventaire dans la console
	public void showInventory()
	{
		println("Inventaire des : " + this.Nom);
		for(int i = 0; i < 999; i++)
		{
			if(this.CurrentSousInventory[i] != null)
			{
				println("Item ID: " + i + " | " + this.CurrentSousInventory[i].getNom() + " | " + CurrentSousInventoryNumber[i]);
			}
			else
			{
				println("Item ID: " + i );
			}
		}
	}
	
	// fonction dev qui permet d'affichier le sous inventaire dans la console
	public void showInventoryComplette()
	{
		println("");
		println("Inventaire des : " + this.Nom);
		println("------------------------------");
		for(int i = 0; i < 999; i++)
		{
			if(this.CurrentSousInventory[i] != null)
			{
				println("Item ID: " + i + " | " + this.CurrentSousInventory[i].getNom() + " | " + CurrentSousInventoryNumber[i]);
			}
		}
		println("------------------------------");
		println("");
	}
	
	// fonction dev qui permet d'affichier le sous inventaire dans la console de manière simple
	public String showInventorySimple()
    {
        for(int i = 0; i < 999; i++)
        {
            if(this.CurrentSousInventory[i] != null)
            {
                return "Item ID: " + i + " | " + this.CurrentSousInventory[i].getNom() + " | " + CurrentSousInventoryNumber[i];
            }
        }
        return null;
    }
	
	// fonction dev qui permet de recuperer le sous inventaire sous forme d'arraylist d'item
	public ArrayList<Item> GetSousInventaire()
    {
        ArrayList<Item> AllItem = new ArrayList<Item>();
        for(int i = 0; i < 999; i++)
        {
            if(this.CurrentSousInventory[i] != null)
            {
                AllItem.add(this.CurrentSousInventory[i]);
            }
        }
        return AllItem;
    }
	
	// nouvelle fonction qui permet de savoir le nombre d'item dans l'inventaire
	public int totalItemsv2()
    {
        int total = 0;
        for(int i = 0; i < 999; i++)
        {
            if(this.CurrentSousInventory[i] != null)
            {
                total = total + 1;
            }
        }

        return total;
    }
	
	// fonction dev qui permet de recuperer les quantité d'item dans l'inventaire	
	public ArrayList<Integer> GetSousInventaireQuantity()
    {
        ArrayList<Integer> AllItemQuantity = new ArrayList<Integer>();
        for(int i = 0; i < 999; i++)
        {
            if(this.CurrentSousInventory[i] != null)
            {
                AllItemQuantity.add(this.CurrentSousInventoryNumber[i]);
            }
        }
        return AllItemQuantity;
    }


}