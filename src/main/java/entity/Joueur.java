package entity;

import game.and.map.*;
import other.components.*;

public class Joueur extends Entiter{
	
		
		
		private Sous_Inventaire Inventaire[] = new  Sous_Inventaire [6];         // Tableau qui regroupe tout les items dans l'inventaire du Joueur
		private Competence Competences[];   // Tableau qui regroupe la liste des competence que le joueur poss�de
		private StuffPlayer CurrentStuff;   // Stuff Actuellement Equipe sur le Joueur (il est dans l'inventaire avec une �toile pour sigaler qu'il est equipe sur le joeur)
		private Money PlayerMoney;          // Argent Actuel que le joueur poss�de sur lui (peut �tre prochain ajout avec l'argent en banque stocker dans cette classe
		private Tallent PlayerTalent;       // Tallent Unique que le joeur poss�de
		
		Joueur(String _nom, LV _lv,Pos _position,Stat _statistiques,Effect _curentEffect,GameType _entityType,Sous_Inventaire _Inventaire[],Competence _Competences[],StuffPlayer _CurrentStuff,Money _PlayerMoney,Tallent _PlayerTalent)
		{
			super(_nom, _lv, _position, _statistiques, _curentEffect,GameType.PLAYER);
			Inventaire = _Inventaire;
			Competences =  _Competences;
			CurrentStuff = _CurrentStuff;
			PlayerMoney = _PlayerMoney;
			PlayerTalent = _PlayerTalent;	
			Inventaire[0] = new Sous_Inventaire("Armes");
			Inventaire[1] = new Sous_Inventaire("Armures/Tenue");
			Inventaire[2] = new Sous_Inventaire("Potion");
			Inventaire[3] = new Sous_Inventaire("Items Spéciaux");
			Inventaire[4] = new Sous_Inventaire("Craft");
			Inventaire[5] = new Sous_Inventaire("Loot");
		}
		
		Joueur(String _nom, Pos _position,Stat _statistiques,Sous_Inventaire _Inventaire[])
		{
			super(_nom, _position, _statistiques);
			Inventaire = _Inventaire;
			Inventaire = _Inventaire;
			Inventaire[0] = new Sous_Inventaire("Armes");
			Inventaire[1] = new Sous_Inventaire("Armures/Tenue");
			Inventaire[2] = new Sous_Inventaire("Potion");
			Inventaire[3] = new Sous_Inventaire("Items Spéciaux");
			Inventaire[4] = new Sous_Inventaire("Craft");
			Inventaire[5] = new Sous_Inventaire("Loot");
			
		}
		
		public Joueur(String _nom,Stat _Statistiques, Pos _Position,GameType _entityType,Sous_Inventaire _Inventaire[])
		{
			super(_nom, _Position, _Statistiques,GameType.PLAYER);
			Inventaire = _Inventaire;
			Inventaire[0] = new Sous_Inventaire("Armes");
			Inventaire[1] = new Sous_Inventaire("Armures/Tenue");
			Inventaire[2] = new Sous_Inventaire("Potion");
			Inventaire[3] = new Sous_Inventaire("Items Spéciaux");
			Inventaire[4] = new Sous_Inventaire("Craft");
			Inventaire[5] = new Sous_Inventaire("Loot");
		}
		
		public Joueur(String _nom,Stat _Statistiques, Pos _Position,GameType _entityType,Sous_Inventaire _Inventaire[],Money _PlayerMoney)
		{
			super(_nom, _Position, _Statistiques,GameType.PLAYER);
			Inventaire = _Inventaire;
			PlayerMoney = _PlayerMoney;
			Inventaire[0] = new Sous_Inventaire("Armes");
			Inventaire[1] = new Sous_Inventaire("Armures/Tenue");
			Inventaire[2] = new Sous_Inventaire("Potion");
			Inventaire[3] = new Sous_Inventaire("Items Spéciaux");
			Inventaire[4] = new Sous_Inventaire("Craft");
			Inventaire[5] = new Sous_Inventaire("Loot");
		}
		
		public Joueur(String _nom,Stat _Statistiques, Pos _Position,GameType _entityType)
		{
			super(_nom, _Position, _Statistiques,GameType.PLAYER);
		}
		
		public static void println(String T)
		{
			System.out.println(T);
		}	

		public void InspectPlayer()
		{
			println("L'inventaire du joueur est composer de " +  this.ItemsCountOnInventory() + " Objets");
			}
		
		public Sous_Inventaire [] getInventaire()
		{
			return this.Inventaire;
		}
		
		public Competence [] getCompetences()
		{
			return this.Competences;
		}
		
		public StuffPlayer getCurrentStuff()
		{
			return this.CurrentStuff;
		}
		
		public Money getPlayerMoney()
		{
			return this.PlayerMoney;
		}
		
		public Tallent getPlayerTalent()
		{
			return this.PlayerTalent;
		}	
		
		public void setInventaire(Sous_Inventaire [] _Inventaire)
		{
			this.Inventaire = _Inventaire;
		}
		
		public void setCompetences(Competence [] _Competences)
		{
			this.Competences = _Competences;
		}
		
		public void setCurrentStuff(StuffPlayer _CurrentStuff)
		{
			this.CurrentStuff = _CurrentStuff;
		}
		
		public void setPlayerMoney(Money _PlayerMoney)
		{
			this.PlayerMoney = _PlayerMoney;
		}
		
		public void setPlayerTalent(Tallent _PlayerTalent)
		{
			this.PlayerTalent = _PlayerTalent;
		}
		
		
		public void addItems(Item IT)
		{
			this.getInventaire()[IT.getType()].addItems(IT);
		}
		
		public void addItems(Item IT, int Quantity)
		{
			this.getInventaire()[IT.getType()].addItems(IT,Quantity);
		}
		
		
		
		public void showInventory()
		{
			 this.getInventaire()[0].showInventoryComplette();
			 this.getInventaire()[1].showInventoryComplette();
			 this.getInventaire()[2].showInventoryComplette();
			 this.getInventaire()[3].showInventoryComplette();
			 this.getInventaire()[4].showInventoryComplette();
			 this.getInventaire()[5].showInventoryComplette();
		}
		
		
		public int ItemsCountOnInventory()
		{
			return (this.getInventaire()[0].TotalItems() + this.getInventaire()[1].TotalItems() + this.getInventaire()[2].TotalItems() + this.getInventaire()[3].TotalItems() + this.getInventaire()[4].TotalItems()  + this.getInventaire()[5].TotalItems());
		}
		
		/*
			private byte regenDelay;
			public void regenerateHealth() {
				if(this.hp < this.maxHp) this.regenDelay++;
				else this.regenDelay = 0;
			
				if(this.regenDelay == 50) {
					this.hp++;
					this.regenDelay = 0;
				}
			}

		 */
		
		
		
		
		
		
		
		
		
		

	}


