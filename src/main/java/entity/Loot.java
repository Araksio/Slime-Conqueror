package entity;

import java.util.ArrayList;

/**
 * cette classe permet la création et la modification des loot des monstres
 * 
 * @author Pitohui.G7
 *
 */

public class Loot {

	ArrayList<Item> LootItemsOfMonster = new ArrayList<Item>();
	ArrayList<Integer> LootQuantityItemsOfMonster = new ArrayList<Integer>();
	String LootMessage;

	public Loot(ArrayList<Item> _LootItemsOfMonster, ArrayList<Integer> _LootQuantityItemsOfMonster,
			String _LootMessage) {
		LootItemsOfMonster = _LootItemsOfMonster;
		LootQuantityItemsOfMonster = _LootQuantityItemsOfMonster;
		LootMessage = _LootMessage;
	}

	public static void println(String T) {
		System.out.println(T);
	}

	public ArrayList<Item> getListOfItemsLoot() {
		return this.LootItemsOfMonster;
	}

	public ArrayList<Integer> getListOfQuantityItemsLoot() {
		return this.LootQuantityItemsOfMonster;
	}

	public String getLootMessage() {
		return this.LootMessage;
	}

	public void setListOfItemsLoot(ArrayList<Item> _ListOfItemsLoot) {
		this.LootItemsOfMonster = _ListOfItemsLoot;
	}

	public void setListOfQuantityItemsLoot(ArrayList<Integer> _ListOfQuantityItemsLoot) {
		this.LootQuantityItemsOfMonster = _ListOfQuantityItemsLoot;
	}

	public void setLootMessage(String _LootMessage) {
		this.LootMessage = _LootMessage;
	}

	public void addItems(Item I, int Quantity) {
		LootItemsOfMonster.add(I);
		LootQuantityItemsOfMonster.add(Quantity);
	}

	public void AutoGenerateLootMessage() {
		String Message = null;
		for (Item I : LootItemsOfMonster) {
			int pos = LootItemsOfMonster.indexOf(I);
			Message = Message + " " + LootQuantityItemsOfMonster.get(pos) + " " + I.getNom();
		}

		this.LootMessage = Message;
	}

}
