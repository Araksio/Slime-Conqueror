package entity;

import game.and.map.*;
/**
 * cette classe comporte les diférentes varibles importante pour le fonctionement des monstres en jeu
 * 
 * @author Rémi
 */
public class Monster extends Entiter{
	
	double droppedXP;
	private MonsterType state;
  
	Monster(String _nom, LV _lv, Pos _position, Stat _statistiques,GameType _entityType,Double _droppedXP) {
		super(_nom, _lv, _position, _statistiques, GameType.MONSTER);
		droppedXP = _droppedXP;
	}
	
	public Monster(String _nom, Pos _position, Stat _statistiques,GameType _entityType, MonsterType _state, Double _droppedXP) {
		super(_nom, _position, _statistiques,GameType.MONSTER);
		state = _state;
		droppedXP = _droppedXP;
	}

	public double getDroppedXP() {
		return droppedXP;
	}

	public void setDroppedXP(double droppedXP) {
		this.droppedXP = droppedXP;
	}
	
	public MonsterType getState()
	{
		return this.state;
	}
	
	public void setState(MonsterType  _State)
	{
		this.state = _State;
	}


}
