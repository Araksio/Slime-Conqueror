package entity;

/**
 * cette classe permet la création et la modification des stat d'une entité
 * 
 * @author Pitohui.G7
 *
 */

public class Stat {

	// Stat Maximal du joueur

	private int MaxHP; // Vie du Joueur
	private int MaxATK; // Attaque du Joueur
	private int MaxDEF; // Deffence du Joueur
	private int MaxMP; // Mana Point du Joueur
	private int MaxSPA; // Attaque Sp�ciale (Magie) Du Joueur
	private int MaxSPD; // Deffence Sp�ciale (Magie) Du Joueur
	private int MaxSPE; // Vitesse Du Joueur

	// Stat Actuelle du joueur

	private int CurrentHP; // Vie du Joueur
	private int CurrentATK; // Attaque du Joueur
	private int CurrentDEF; // Deffence du Joueur
	private int CurrentMP; // Mana Point du Joueur
	private int CurrentSPA; // Attaque Sp�ciale (Magie) Du Joueur
	private int CurrentSPD; // Deffence Sp�ciale (Magie) Du Joueur
	private int CurrentSPE; // Vitesse Du Joueur

	private String Space20 = "                      ";

	public Stat(int _HP, int _ATK, int _DEF, int _MP, int _SPA, int _SPD, int _SPE) {
		MaxHP = _HP;
		MaxATK = _ATK;
		MaxDEF = _DEF;
		MaxMP = _MP;
		MaxSPA = _SPA;
		MaxSPD = _SPD;
		MaxSPE = _SPE;

		CurrentHP = _HP;
		CurrentATK = _ATK;
		CurrentDEF = _DEF;
		CurrentMP = _MP;
		CurrentSPA = _SPA;
		CurrentSPD = _SPD;
		CurrentSPE = _SPE;
	}

	public static void println(String T) {
		System.out.println(T);
	}

	// Fonction dev qui permet d'affichier les stat normale de l'entité dans la
	// console
	public void showAllMaxStat() {
		println(Space20 + "HP : " + this.getMaxHP());
		println(Space20 + "ATK : " + this.getMaxATK());
		println(Space20 + "DEF : " + this.getMaxDEF());
		println(Space20 + "MP : " + this.getMaxMP());
		println(Space20 + "SPA : " + this.getMaxSPA());
		println(Space20 + "SPD : " + this.getMaxSPD());
		println(Space20 + "SPE : " + this.getMaxSPE());
	}

	// Fonction a utiliser après un combat par exemple pour faire en sorte que les
	// stat actuelle revienne a la normale en cas d'utilisation de compretence ou de
	// sort
	public void ResetStatAfterBattle() {
		this.CurrentATK = this.MaxATK;
		this.CurrentDEF = this.MaxDEF;
		this.CurrentSPA = this.MaxSPA;
		this.CurrentSPD = this.MaxSPD;
		this.CurrentSPE = this.MaxSPE;
	}

	public int getMaxHP() {
		return this.MaxHP;
	}

	public int getMaxATK() {
		return this.MaxATK;
	}

	public int getMaxDEF() {
		return this.MaxDEF;
	}

	public int getMaxMP() {
		return this.MaxMP;
	}

	public int getMaxSPA() {
		return this.MaxSPA;
	}

	public int getMaxSPD() {
		return this.MaxSPD;
	}

	public int getMaxSPE() {
		return this.MaxSPE;
	}

	public int getCurrentHP() {
		return this.CurrentHP;
	}

	public int getCurrentATK() {
		return this.CurrentATK;
	}

	public int getCurrentDEF() {
		return this.CurrentDEF;
	}

	public int getCurrentMP() {
		return this.CurrentMP;
	}

	public int getCurrentSPA() {
		return this.CurrentSPA;
	}

	public int getCurrentSPD() {
		return this.CurrentSPD;
	}

	public int getCurrentSPE() {
		return this.CurrentSPE;
	}

	public void setMaxHP(int _MaxHP) {
		this.MaxHP = _MaxHP;
	}

	public void setMaxATK(int _MaxATK) {
		this.MaxATK = _MaxATK;
	}

	public void setMaxDEF(int _MaxDEF) {
		this.MaxDEF = _MaxDEF;
	}

	public void setMaxMP(int _MaxMP) {
		this.MaxMP = _MaxMP;
	}

	public void setMaxSPA(int _MaxSPA) {
		this.MaxSPA = _MaxSPA;
	}

	public void setMaxSPD(int _MaxSPD) {
		this.MaxSPD = _MaxSPD;
	}

	public void setMaxSPE(int _MaxSPE) {
		this.MaxSPE = _MaxSPE;
	}

	public void setCurrentHP(int _CurrentHP) {
		this.CurrentHP = _CurrentHP;
	}

	public void setCurrentATK(int _CurrentATK) {
		this.CurrentATK = _CurrentATK;
	}

	public void setCurrentDEF(int _CurrentDEF) {
		this.CurrentDEF = _CurrentDEF;
	}

	public void setCurrentMP(int _CurrentMP) {
		this.CurrentMP = _CurrentMP;
	}

	public void setCurrentSPA(int _CurrentSPA) {
		this.CurrentSPA = _CurrentSPA;
	}

	public void setCurrentSPD(int _CurrentSPD) {
		this.CurrentSPD = _CurrentSPD;
	}

	public void setCurrentSPE(int _CurrentSPE) {
		this.CurrentSPE = _CurrentSPE;
	}

}