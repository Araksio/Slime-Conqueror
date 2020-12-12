package entity;

/**
 * cette classe permet la création et la modification de l'argent du joueur
 * 
 * @author Pitohui.G7
 *
 */

public class Money {

	private double MoneyOnPlayer;
	private double MoneyOnBank;

	public Money(double _MoneyOnPlayer, double _MoneyOnBank) {
		MoneyOnPlayer = _MoneyOnPlayer;
		MoneyOnBank = _MoneyOnBank;
	}

	public double getMoneyOnPlayer() {
		return this.MoneyOnPlayer;
	}

	public double MoneyOnBank() {
		return this.MoneyOnBank;
	}

	public void setMoneyOnPlayer(double _MoneyOnPlayer) {
		this.MoneyOnPlayer = _MoneyOnPlayer;
	}

	public void seMoneyOnBank(double _MoneyOnBank) {
		this.MoneyOnBank = _MoneyOnBank;
	}

	public void addMoney(double _Money) {
		this.MoneyOnPlayer = this.MoneyOnPlayer + _Money;
	}

	public void delMoney(double _Money) {
		this.MoneyOnPlayer = this.MoneyOnPlayer - _Money;
	}

	// fonction qui permet de transferer de l'argent du joueur a la banque
	public void TransfertMoneyOnBank(double _Money) {
		this.MoneyOnBank = this.MoneyOnBank + _Money;
		this.MoneyOnPlayer = this.MoneyOnPlayer - _Money;
	}

	// fonction qui permet de transferer de l'argent de la banque a joueur
	public void TransfertMoneyOnPlayer(double _Money) {
		this.MoneyOnPlayer = this.MoneyOnPlayer + _Money;
		this.MoneyOnBank = this.MoneyOnBank - _Money;
	}

	// fonction qui permet de savoir si un joeur a assez d'argent pour acheter un
	// objet
	public boolean CanBuy(double _Money) {
		if (_Money > MoneyOnPlayer) {
			return false;
		} else {
			return true;
		}
	}

}