package entity;


/**
 * cette classe permet la création et la modification du level associer a un joueur
 * @author Pitohui.G7
 *
 */

public class LV {
	
	private int Niveau;
	private double TotalXP;
	private double CurrentXPforLV;
	private double XPneedForNextLV;
	private int pointsBonus;
	
	public LV(int _Niveau, double _TotalXP, double _CurrentXPforLV, double _XPneedForNextLV, int _pointsBonus)
	{
		Niveau = _Niveau;
		TotalXP = _TotalXP;
		CurrentXPforLV = _CurrentXPforLV;
		XPneedForNextLV = _XPneedForNextLV;
		pointsBonus = _pointsBonus;
	}
	
	public int getNiveau()
	{
		return this.Niveau;
	}
	
	public double getTotalXP()
	{
		return this.TotalXP;
	}
	
	public double getCurrentXPforLV()
	{
		return this.CurrentXPforLV;
	}
	
	public double getXPneedForNextLV()
	{
		return this.XPneedForNextLV;
	}
	
	
	
	public void setNiveau(int _Niveau)
	{
		this.Niveau = _Niveau;
	}
	
	public void setTotalXP(double _TotalXP)
	{
		this.TotalXP = _TotalXP;
	}
	
	public void setCurrentXPforLV(double _CurrentXPforLV)
	{
		this.CurrentXPforLV = _CurrentXPforLV;
	}
	
	public void setXPneedForNextLV(double _XPneedForNextLV)
	{
		this.XPneedForNextLV = _XPneedForNextLV;
	}
	
	
	// fonction qui permet d'ajouter de l'xp
	public void addXP(double _xp)
	{
		this.TotalXP = this.TotalXP + _xp;
		this.CurrentXPforLV = this.CurrentXPforLV + _xp;
	}
	
	// fonction qui permet de savoir en fonction de l'xp du joueur si il peu monter au prochain level et augmente le level si c'est possible
	public boolean checkLVisAvalaible()
	{
		if(this.CurrentXPforLV >= this.XPneedForNextLV)
		{
			while(this.CurrentXPforLV >= this.XPneedForNextLV)
			{
				this.CurrentXPforLV = this.CurrentXPforLV - this.XPneedForNextLV;
				Niveau++;
				return true;
			}
		}
		else
		{
			return false;
		}
		return false;
	}

	public int getPointsBonus() {
		return pointsBonus;
	}

	public void setPointsBonus(int pointsBonus) {
		this.pointsBonus = pointsBonus;
	}

}