package entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import org.junit.Assert.*;





/**
 * test de la fonction testCheck de la classe LV 
 */
class LVTest {

	
	@Test
	void testCheckLVisAvalaible() {
		
		
		
		//on instancie une classe pour tester la fonction checkLvisAvailable 
// LV(int _Niveau, double _TotalXP, double _CurrentXPforLV, double _XPneedForNextLV, int _pointsBonus)
		
		
		LV l = new LV(1,0.0,20.0,20.0,0); //cas ou on a currentXpforLv == XPneedForNextLv
		assertTrue(l.checkLVisAvalaible()); // pas d erreur
		
		LV l2 = new LV(1,0.0,21.0,20.0,0); //cas ou on a currentXpforLv > XPneedForNextLv
		assertTrue(l2.checkLVisAvalaible());//pas d erreur
		
		LV l3 = new LV(1,0.0,10.0,20.0,0); //cas ou on a currentXpforLv < XPneedForNextLv
		assertFalse(l3.checkLVisAvalaible());//pas d erreur
		
		LV l4 = new LV(1,0.0,20.0000000000000000000000000000000000000001,20.0,0); //cas ou on a currentXpforLv > XPneedForNextLv tres legerement
		assertTrue(l4.checkLVisAvalaible());//pas d erreur (parce que c est un >= et non un > )
		
//		LV l5 = new LV(1,0.0,19.999999999999999,20.0,0); //cas ou on a currentXpforLv < XPneedForNextLv tres legerement
//		assertFalse(l5.checkLVisAvalaible());//erreur : a partir de 15 chiffre apres la virgules (dans le cas ou on a 2 chiffres avant la virgule)car ca arrondit

		LV l6 = new LV(1,0.0,-5,-4096.0,0); //avec des negatif
		assertTrue(l6.checkLVisAvalaible());//pas d erreur		
		
		LV l7 = new LV(1,0.0,-5000,-20.0,0); // avec des negatifs
		assertFalse(l7.checkLVisAvalaible());//pas d erreur
		
//		LV l8 = new LV(1,0.0,(Double.MAX_VALUE-1),(Double.MAX_VALUE),0); // test avec des valeurs max 
//		assertFalse(l8.checkLVisAvalaible());//erreur ca me donne quand meme true, pourquoi?
		
		
//		LV l9 = new LV(1,0.0,Double.MIN_VALUE-1,0,0); //test aavec une valeur minimal -1
//		assertTrue(l9.checkLVisAvalaible());// erreur car min_value -1 = la valeur  maximum possible	
		
		
		
		
		
		
		
//		if(this.CurrentXPforLV >= this.XPneedForNextLV)
//		{
//			while(this.CurrentXPforLV >= this.XPneedForNextLV)
//			{
//				this.CurrentXPforLV = this.CurrentXPforLV - this.XPneedForNextLV;
//				Niveau++;
//				return true;
//			}
//		}
//	
//		return false;

		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
