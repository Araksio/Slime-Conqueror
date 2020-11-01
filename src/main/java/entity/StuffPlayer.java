package entity;

import game.and.map.*;
import other.components.*;

public class StuffPlayer {
	
	// Armes Du Joueur
	
	private Item CurrentMainWeapons;        // Armes Principal du Joueur
	private Item CurrentSecondaryWeapons;   // Armes Secondaire du Joueur
	
	// Armures du Joueur
	
	private Item CurrentCasque;             // Casque que porte le joueur Actuellement
	private Item CurrentPlastron;           // Plastron que porte le joueur Actuellement
	private Item CurrentJambiere;           // Jambiere que porte le joueur Actuellement
	private Item CurrentBottes;             // Bottes que porte le joueur Actuellement
	
	// Objet Magique du joueur
	
	private Item CurrentCollier;
	private Item CurrentBracelet;
	
	StuffPlayer(Item _CurrentMainWeapons, Item _CurrentSecondaryWeapons, Item _CurrentCasque, Item _CurrentPlastron, Item _CurrentJambiere, Item _CurrentBottes, Item _CurrentCollier, Item _CurrentBracelet)
	{
		CurrentMainWeapons = _CurrentMainWeapons;
		CurrentSecondaryWeapons = _CurrentSecondaryWeapons;
		
		CurrentCasque = _CurrentCasque;
		CurrentPlastron = _CurrentPlastron;
		CurrentJambiere = _CurrentJambiere;
		CurrentBottes = _CurrentBottes;
		
		CurrentCollier = _CurrentCollier;
		CurrentBracelet = _CurrentBracelet;
	}
	
	public static void println(String T)
	{
		System.out.println(T);
	}	
	
	public void showCurrentStuff()
	{
		println("Le stuff que votre personnage a �quiper et le suivant :");
		println("Arme Pricipal : " + this.CurrentMainWeapons.getNom());
		println("Arme Secondaire : " + this.CurrentSecondaryWeapons.getNom());
		println("Casque : " + this.CurrentCasque.getNom());
		println("Plaston : " + this.CurrentPlastron.getNom());
		println("Jambiere : " + this.CurrentJambiere.getNom());
		println("Bottes : " + this.CurrentBottes.getNom());
		println("Collier : " + this.CurrentCollier.getNom());
		println("Bracelet : " + this.CurrentBracelet.getNom());
		
		// Ajouter les stat des Items equiper
	}
	
	
	
	public Item getCurrentMainWeapons()
	{
		return this.CurrentMainWeapons;
	}
	
	public Item getCurrentSecondaryWeapons()
	{
		return this.CurrentSecondaryWeapons;
	}
	
	public Item getCurrentCasque()
	{
		return this.CurrentCasque;
	}
	
	public Item getCurrentPlastron()
	{
		return this.CurrentPlastron;
	}
	
	public Item getCurrentJambiere()
	{
		return this.CurrentJambiere;
	}
	
	public Item CurrentBottes()
	{
		return this.CurrentBottes;
	}
	
	public Item getCurrentCollier()
	{
		return this.CurrentCollier;
	}
	
	public Item getCurrentBottes()
	{
		return this.CurrentBottes;
	}
	
	
	
	public void setMainWeapons(Item _MainWeapons)
	{
		this.CurrentMainWeapons = _MainWeapons;
	}
	
	public void setSecondaryWeapons(Item _SecondaryWeapons)
	{
		this.CurrentSecondaryWeapons = _SecondaryWeapons;
	}
	
	public void setMainCasque(Item _Casque)
	{
		this.CurrentCasque = _Casque;
	}
	
	public void setPlastron(Item _Plastron)
	{
		this.CurrentPlastron = _Plastron;
	}
	
	public void setJambiere(Item _Jambiere)
	{
		this.CurrentJambiere = _Jambiere;
	}
	
	public void setBottes(Item _Bottes)
	{
		this.CurrentBottes = _Bottes;
	}
	
	public void setCollier(Item _Collier)
	{
		this.CurrentCollier = _Collier;
	}
	
	public void Bottes(Item _Bottes)
	{
		this.CurrentBottes = _Bottes;
	}
	
	
	

}
