package entity;

import game.and.map.*;
import other.components.*;

public class Rarity {
	
	private int RarityID;
	private String RarityCall[] = new String [12];
	private String RarityCallVcourt[] = new String [12];
	private float RarityBoost[] = new float [12];
	private double RarityProba[] = new double [12];
	private double RarityProbaCumul[] = new double [12];
	//private int RankID;
	//private float RankBoost[] = new float [12];
	//private double RankProba[] = new double [12];
	
	
	Rarity(int _RarityID)
	{
		RarityID = _RarityID;
	}
	
	Rarity()
	{
		RarityID = this.GenerateRandomRarityV2();
	}
	
	
	public int getRarityID()
	{
		return this.RarityID;
	}
	
	public static void println(String T)
	{
		System.out.println(T);
	}	
	
	public String getRarityCall()
	{
		RarityCall[1] = "Basique";
		RarityCall[2] = "Commune";
		RarityCall[3] = "Peu Commune";
		RarityCall[4] = "Rare";
		RarityCall[5] = "Super Rare";
		RarityCall[6] = "Epique";
		RarityCall[7] = "Ultra Rare";
		RarityCall[8] = "L�gendaire";
		RarityCall[9] = "Divine";
		RarityCall[10] = "D�moniaque";
		RarityCall[11] = "Rainbow";
		
		return RarityCall[this.RarityID];
	}
	
	public String getRarityCallVcourt(int RID)
	{
		RarityCallVcourt[1] = "B";
		RarityCallVcourt[2] = "C";
		RarityCallVcourt[3] = "PC";
		RarityCallVcourt[4] = "R";
		RarityCallVcourt[5] = "SR";
		RarityCallVcourt[6] = "E";
		RarityCallVcourt[7] = "UR";
		RarityCallVcourt[8] = "L";
		RarityCallVcourt[9] = "DIV";
		RarityCallVcourt[10] = "DEM";
		RarityCallVcourt[11] = "R";
		
		return RarityCall[RID];
	}
	
	public float getRarityBoost(int RID)
	{
		RarityBoost[1] = (float) 0.75;
		RarityBoost[2] = (float) 1;
		RarityBoost[3] = (float) 1.2;
		RarityBoost[4] = (float) 1.4;
		RarityBoost[5] = (float) 1.7;
		RarityBoost[6] = (float) 2;
		RarityBoost[7] = (float) 3;
		RarityBoost[8] = (float) 5;
		RarityBoost[9] = (float) 15;
		RarityBoost[10] = (float) 15;
		RarityBoost[11] = (float) 50;
		
		return RarityBoost[RID];
	}
	
	public double getRarityProba(int RID)
	{
		RarityProba[1] = (double) 0.27;
		RarityProba[2] = (double) 0.25;
		RarityProba[3] = (double) 0.20;
		RarityProba[4] = (double) 0.154955;
		RarityProba[5] = (double) 0.08;
		RarityProba[6] = (double) 0.04;
		RarityProba[7] = (double) 0.004;
		RarityProba[8] = (double) 0.001;
		RarityProba[9] = (double) 0.00002;
		RarityProba[10] = (double) 0.00002;
		RarityProba[11] = (double) 0.000005;
		
		return RarityProba[RID];
	}
	
	public double getRarityProbaCumul(int RID)
	{
		RarityProbaCumul[1] = (double) 270000;
		RarityProbaCumul[2] = (double) 520000;
		RarityProbaCumul[3] = (double) 720000;
		RarityProbaCumul[4] = (double) 874955;
		RarityProbaCumul[5] = (double) 954955;
		RarityProbaCumul[6] = (double) 994955;
		RarityProbaCumul[7] = (double) 998955;
		RarityProbaCumul[8] = (double) 999955;
		RarityProbaCumul[9] = (double) 999975;
		RarityProbaCumul[10] = (double) 999995;
		RarityProbaCumul[11] = (double) 1000000;
		
		return RarityProbaCumul[RID];
	}
	
	public Rarity GenerateRandomRarity()
	{
		int ValMin = 0;
		int ValMax = 1000000;
		Rarity R4;
		
		double d2 = ((int) ((Math.random() * (ValMax - ValMin) + ValMin + 1)));
		
		for(int i = 1; i < 12; i++)
		{
			if(d2 <= RarityProbaCumul[i])
			{
				R4 = new Rarity(i);
				return R4;
			}
			else
			{
				
			}
		}
		
		R4 = new Rarity(11);
		return R4;
	}
	
	public int GenerateRandomRarityV2()
	{
		int ValMin = 0;
		int ValMax = 1000000;
		
		double d2 = ((double) ((Math.random() * (ValMax - ValMin) + ValMin + 1)));
		
		d2 = (double)Math.round(d2);
			
		for(int i = 1; i < 12; i++)
		{
			
			if(d2 <= getRarityProbaCumul(i))
			{
				return i;
			}
			else
			{
				
			}
		}
		
		return 11;
	}
	
	
	
	
	 

}
