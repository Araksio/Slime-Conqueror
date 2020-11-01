package game.and.map;

public class CreatAlea {
	private int types=100;
	private int compteurM=0;
	private int compteurP=0;
	private int compteur0=0;

	private int tempCompt0=0;
	private CaseG[][] m=new CaseG[19][19];
	public CreatAlea() {
		creat();	
	}
	

	public String ligne(int l) {
		String r="";
		for(int i=0;i<19;i++) {
			r=r+m[l][i];
		}
		System.out.println(r);
		return r;
	}
	
	private void creat(){//methode pour gerer les taut d'apparition
		System.out.println("P= "+compteurP);
		for(int i=0;i<19;i++) {
			for(int j=0;j<19;j++) {
				int a=(int)(Math.random()*types);
				if(a<=70){
					compteur0++;
				m[i][j]=new CaseG("0");//case vide
				}
				else if(a<=80) {
					
				m[i][j]=new CaseG("1");//case mur
				}
				else if(a<=88) {
					if(compteurM<40) {
					compteurM++;
					m[i][j]=new CaseG("M");//case monstre	
					}else {
						m[i][j]=new CaseG("0");
					}
				}
				else{
					compteurP++;
				m[i][j]=new CaseG("P");//case joueur
					types=88;//pour ne cre� qu'un seule joueur sur la map
				
				}
			}
		}
		verifJ();
		verif0();
	}
	private void verifJ() {//verifie qu'il existe un joueur de generer
		
		if(compteurP==0) {
			remise0();
			creat();
		}
	}
	
	
	private void remise0() {//remet les compteur a 0
		compteurM=0;
		compteurP=0;
		compteur0=0;
		types=100;
	}
	
	private void verif0() {// verifie si il ny a pas de zone ou les mur peuvent bloquer le joueur
		tempCompt0=0;
		first0();
		
		if(tempCompt0 != compteur0) {// si le nombre de bloc vide mie sur la map est diferent du nombre qui sont comter c'est qu'il y a un endroit ou le joueur na pas acces
			System.out.println("TC= "+tempCompt0+" C= "+compteur0);
			remise0();
			creat();
			
		}
	}
	private void compt0(int l,int c) {//compte tout les bloc vide qui sont connecter entre eux
		System.out.println(m[l][c]+" "+l+","+c);
		if(m[l][c].getVerif() && (m[l][c].getnam()=="0" ||  m[l][c].getnam()=="M" || m[l][c].getnam()=="P")) {
			m[l][c].setVerif(false);
			if(m[l][c].getnam()=="0") {
				tempCompt0++;
			}
			
				if(l>=1) {
					compt0(l-1,c);
				}
				if(l<=17) {
					compt0(l+1,c);
				}
				if(c>=1) {
					compt0(l,c-1);
				}
				if(c<=1) {
					compt0(l,c+1);
				}
			
		}
		
			
	
		
		
	}
	private void first0() {//trouve les premier bloc vide a partire du quelle on commence a les compter
		boolean boucle=true;
		for(int i = 0; boucle && i < 19; i++) {
			  
			  if(m[0][i].getnam()=="P" ){
				  boucle = false;
				  compt0(0,i);
			  }
			}
	}
}

