package entity;

import java.util.ArrayList;

import game.and.map.*;
import other.components.*;

public class testCombat {
	
	public static void println(String T)
	{
		System.out.println(T);
	}	
	
	public static void main(String[] args) {
		
		String Pseudo = "Pitohui";
		Stat StatPlayer = new Stat(120,80,73,45,23,42,70);
		Pos Pos1 = new Pos(5,1,0,0,1);
		
		Joueur J1 = new Joueur(Pseudo,StatPlayer,Pos1,GameType.PLAYER);
		
		println("Voici l'affique du joueur : ");
        //J1.InspectPlayer();
        
        Rarity RH = new Rarity();
        Item I1 = new ItemsArmes(1,1,15,"�p� du turfu",1,1952,3000,500,RH);
        I1.showDurability();
        
        println("" + Items.Rainbow_Excalibur.getRarity().getRarityCall()); 
        println("" + Items.Exatest1.getRarity().getRarityCall()); 
        println("" + Items.Exatest2.getRarity().getRarityCall()); 
        
        J1.getPos().showPos();
        J1.getPos().DeplacementDroite();
        
        J1.getPos().showPos();
        J1.getPos().DeplacementHaut(5);
        J1.getPos().showPos();
        
        

        Rarity R1 = new Rarity();
        Rarity R2 = new Rarity();
        Rarity R3 = new Rarity();
        Rarity R4 = new Rarity();
        Rarity R5 = new Rarity();
        Rarity R6 = new Rarity();
        Rarity R7 = new Rarity();
        Rarity R8 = new Rarity();
        Rarity R9 = new Rarity();
        Rarity R10 = new Rarity();
        
        println("R1 " + R1.getRarityCall());
        println("R2 " + R2.getRarityCall());
        println("R3 " + R3.getRarityCall());
        println("R4 " + R4.getRarityCall());
        println("R5 " + R5.getRarityCall());
        println("R6 " + R6.getRarityCall());
        println("R7 " + R7.getRarityCall());
        println("R8 " + R8.getRarityCall());
        println("R9 " + R9.getRarityCall());
        println("R10 " + R10.getRarityCall());
        
        
        Sous_Inventaire S1 [] = new Sous_Inventaire [6];
        S1[0] = new Sous_Inventaire("Armes");
        S1[1] = new Sous_Inventaire("Armures/Tenue");
        S1[2] = new Sous_Inventaire("Potion");
        S1[3] = new Sous_Inventaire("Items Sp�ciaux");
        S1[4] = new Sous_Inventaire("Craft");
        S1[5] = new Sous_Inventaire("Loot");
        
        Joueur J2 = new Joueur(Pseudo,Pos1,StatPlayer,S1);
        
        J2.addItems(Items.Rainbow_Excalibur);
        J2.addItems(Items.Demoniac_Excalibur);
        J2.addItems(Items.Exatest1);
        J2.addItems(Items.Exatest2);
        
        J2.showInventory();   
        

        
        
        
        J2.InspectPlayer();
        
        GameType GT = GameType.PLAYER;
        
        
        
        
        
        
             
        
        
        
		
		
	}

}
