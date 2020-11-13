package entity;

import java.util.ArrayList;

import game.and.map.*;
import other.components.*;

public class Sous_Inventaire {
	
	private String Nom;
	public Item CurrentSousInventory[]= new Item[999];
	public int CurrentSousInventoryNumber[]= new int[999];
	



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
	
	public int totalItems()
	{
		int total = 0;
		for(int i = 0; i < 999; i++)
		{
			total = total + this.CurrentSousInventoryNumber[i];
		}
		
		return total;
	}
	
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
