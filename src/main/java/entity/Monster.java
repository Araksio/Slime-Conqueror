package entity;

import game.and.map.*;
import other.components.*;

public class Monster extends Entiter{
  double droppedXP;
	Monster(String _nom, LV _lv, Pos _position, Stat _statistiques, Effect _curentEffect,GameType _entityType,Double _droppedXP) {
		super(_nom, _lv, _position, _statistiques, _curentEffect,GameType.MONSTER);
		droppedXP = _droppedXP;
	}
	
	public Monster(String _nom, Pos _position, Stat _statistiques,GameType _entityType,Double _droppedXP) {
		super(_nom, _position, _statistiques,GameType.MONSTER);
		droppedXP = _droppedXP;
	}

	public double getDroppedXP() {
		return droppedXP;
	}

	public void setDroppedXP(double droppedXP) {
		this.droppedXP = droppedXP;
	}
	
   
	
	

}
