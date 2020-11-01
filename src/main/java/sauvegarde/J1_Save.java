package sauvegarde;

import entity.Joueur;
import entity.Pos;
import entity.Stat;
import game.and.map.GameType;

public class J1_Save {
	
	String Pseudo = "Pitohui";
	Stat StatPlayer = new Stat(120,80,73,45,23,42,70);
	Pos Pos1 = new Pos(5,1,0,0,1);
	
	public Joueur J = new Joueur(Pseudo,StatPlayer,Pos1,GameType.PLAYER); 	

}
