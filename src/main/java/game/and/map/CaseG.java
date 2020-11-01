package game.and.map;

public class CaseG {
	private String nam;
	private boolean verif=true;
	
	public CaseG(String _nam) {
		nam=_nam;
	}
	public String toString() {
		return nam;
	}
	public boolean getVerif() {
		return verif;
	}
	public void setVerif(boolean verif) {
		this.verif = verif;
	}
	public String getnam() {
		return nam;
	}
	public void setnam(String nam) {
		this.nam = nam;
	}
	
}
