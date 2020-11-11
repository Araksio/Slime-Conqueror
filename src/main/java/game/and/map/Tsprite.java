package game.and.map;

import java.io.File;

public class Tsprite {
	private String[][] map;
	private String a="src\\main\\resources\\assets\\levels\\";
	
	public Tsprite(int h,int l) {//h=nombre de spryte de hauteur; l=nombre de spryte en largeur un spryte 7*7
		
		map =new String[h][l];
		genererMap(h,l);
		
	}
	
	private void genererMap(int h,int l) {//attribue un spryte a chaque casse de la map
		map[0][0]=a+"cornHG"+alea(countFiles(a+"cornHG"),"CHG");
		map[0][l-1]=a+"cornHD"+alea(countFiles(a+"cornHD"),"CHD");
		map[h-1][0]=a+"cornBG"+alea(countFiles(a+"cornBG"),"CBG");
		map[h-1][l-1]=a+"cornBD"+alea(countFiles(a+"cornBD"),"CBD");
		for(int i=1;i<l-1;i++) {
			map[0][i]=a+"haut"+alea(countFiles(a+"haut"),"H");
			map[h-1][i]=a+"bas"+alea(countFiles(a+"bas"),"B");
		}
		for(int i=1;i<h-1;i++) {
			map[i][0]=a+"gauche"+alea(countFiles(a+"gauche"),"G");
			map[i][l-1]=a+"droite"+alea(countFiles(a+"droite"),"D");
		}
		for(int i=1;i<=h-2;i++) {
			for(int j=1;j<=l-2;j++) {
				map[i][j]=a+"millieu"+alea(countFiles(a+"millieu"),"M");
			}
		}
	}

	
	public String[][] getMap() {
		return map;
	}

	public void setMap(String[][] map) {
		this.map = map;
	}

	private String alea(int n,String add)  {// recupere un spyte aleatoirement dans le dossier asssocier
		int a=(int)(Math.random()*n)+1;
		
		String adr ="";
		for(int i=1;i<=n;i++) {
			 if(a==i){
				adr= "\\"+add+a+".txt";
			 }
		}
		return adr;
	}
	
	
	
	private int countFiles (String parent)  {//regarde le nombre de spyte existant pour qu'il soit tousse possiblement choisie
	    File file = new File (parent);
	    System.out.println(parent);
	   
	    return file.list().length;
	}
	
}
