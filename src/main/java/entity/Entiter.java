package entity;

import game.and.map.*;
import other.components.*;

public class Entiter {
	
	private String nom;              // nom de l'entiter
	private LV lv;                      // lv  de l'entiter
	private Pos position;			//Position de l'entiter (MAP et x,y,z)
	private Stat statistiques;          // Toute les Statistiques de l'entiter (ATK, DEF, etc ..)
	private Effect curentEffect;        // Effet de potion ou de sort avec une dur�e stoquer dans une matrice
	private GameType entityType;
	
	Entiter(String _nom, LV _lv,Pos _position,Stat _statistiques,Effect _curentEffect,GameType _entityType){
		nom = _nom;
		lv = _lv;
		position = _position;
		statistiques = _statistiques;
		curentEffect = _curentEffect;
		entityType = _entityType;
	}
	
	Entiter(String _nom,Pos _position,Stat _statistiques,GameType _entityType){
		nom = _nom;
		position = _position;
		statistiques = _statistiques;
		entityType = _entityType;
	}
	
	Entiter(String _nom,Pos _position,Stat _statistiques){
		nom = _nom;
		position = _position;
		statistiques = _statistiques;
	
	}
	
	

	public static void println(String T)
	{
		System.out.println(T);
	}
	
	public String getNom()
	{
		return this.nom;
	}
	
	public LV getLv()
	{
		return this.lv;
	}
	
	public Stat getStat()
	{
		return this.statistiques;
	}
	
	public Pos getPos()
	{
		return this.position;
	}
	
	public Effect getEffect()
	{
		return this.curentEffect;
	}
	
	public GameType getType()
	{
		return this.entityType;
	}
	
	public void InspectEntitie()
	{
		println("Identifiant de l'entiter : " + this.getNom());
		println("LV de l'entiter : " + this.getLv());
		println("Ses Statiques sont : ");
		this.getStat().showAllMaxStat();
	}
	
	public void setNom(String _nom)
	{
		this.nom = _nom;
	}
	
	public void setLV(LV _LV)
	{
		this.lv = _LV;
	}
	public void setStat(Stat _Stat)
	{
		this.statistiques = _Stat;
	}
	
	public void setPos(Pos _Pos)
	{
		this.position = _Pos;
	}
	public void setCurentEffect(Effect _CurentEffect)
	{
		this.curentEffect = _CurentEffect;
	}
	
	public void SetType(GameType _entityType)
	{
		this.entityType = _entityType;
	}
}
