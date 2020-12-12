package entity;

import game.and.map.*;
import other.components.*;

public class Pos {
	
	private int CurrentMapID;
	private int floor;
	private int x;
	private int y;
	private int z;
	
	public Pos(int _CurrentMapID, int _floor, int _x, int _y, int _z)
	{
		CurrentMapID = _CurrentMapID;
		floor = _floor;
		x = _x;
		y = _y;
		z = _z;
	}
	
	
	public static void println(String T)
	{
		System.out.println(T);
	}
	
	public void showPos()
	{
		println("Position Actuelle du joueur dans le monde " + this.getCurrentMapID() + " a l'�tage num�ro " + this.getFloor());
		println("A la position Suivante :  ");
		println("x : " + this.getX());
		println("y : " + this.getY());
		println("z : " + this.getZ());
	}
	
	// d�placement g�n�rique de 1 cas par appel de m�thode
	
	public int DeplacementDroite()
	{
		this.x++;
		return this.x;
	}
	
	public int DeplacementGauche()
	{
		this.x--;
		return this.x;
	}
	
	public int DeplacementHaut()
	{
		this.y++;
		return this.y;
	}
	
	public int DeplacementBas()
	{
		this.y--;
		return this.y;
	}
	
	public int Monter()
	{
		this.z++;
		return z;
	}
	
	public int Descendre()
	{
		this.z--;
		return z;
	}
	
	// d�placement Version 2 qui permet de ce d�placer de plusieurs case en un seul appel de m�thode
	
	public int DeplacementDroite(int NCase)
	{
		this.x = this.x + NCase;
		return this.x;
	}
	
	public int DeplacementGauche(int NCase)
	{
		this.x = this.x - NCase;
		return this.x;
	}
	
	public int DeplacementHaut(int NCase)
	{
		this.y = this.y + NCase;
		return this.y;
	}
	
	public int DeplacementBas(int NCase)
	{
		this.y = this.y - NCase;
		return this.y;
	}
	
	public int Monter(int NCase)
	{
		this.z = this.z + NCase;
		return z;
	}
	
	public int Descendre(int NCase)
	{
		this.z = this.z - NCase;
		return z;
	}
	
	public void NextFloor()
	{
		floor++;
		this.x = 0;
		this.y = 0;
		this.z = 1;
	}
	
	
	
	public int getCurrentMapID()
	{
		return this.CurrentMapID;
	}
	
	public int getFloor()
	{
		return this.floor;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public int getZ()
	{
		return this.z;
	}
	
	public void setCurrentMapID(int _CurrentMapID)
	{
		// Verification que la nouvelle map existe
		this.CurrentMapID = _CurrentMapID;
	}
	
	public void setFloor(int _Floor)
	{
		// Verification que l'�tage existe
		this.floor = _Floor;
	}
	
	public void setX(int _x)
	{
		this.x = _x;
	}
	
	public void setY(int _y)
	{
		this.y = _y;
	}
	
	public void setZ(int _z)
	{
		this.z = _z;
	}

}
