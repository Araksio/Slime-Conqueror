package game.and.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Write {
	 private String a="src\\main\\resources\\assets\\levels\\";
	
	
	public Write(int hauteur,int largeur,String fichierSible) throws IOException {
		final String chemin = a+fichierSible;
        final File fichier =new File(chemin); 
        final String[][] carte= new Tsprite(hauteur,largeur).getMap();
         int compteur = 0;
        
           fichier .createNewFile();// Creation du fichier
            final FileWriter writer = new FileWriter(fichier);// creation d'un writer (un �crivain)
            
           
            for(int i=0;i<carte.length;i++) {//cette partie , recopie les spryte sur le fichier de la carte
            	for(int l=1;l<8;l++) {
            		for(int j=0;j<carte[i].length;j++) {
            			
            			File f = new File(carte[i][j]);

        	            BufferedReader b = new BufferedReader(new FileReader(f));

        	            String readLine = "";
        	           if(l>1) {
        	        	   b.skip(9*(l-1));
        	        	   
        	           }
        	            readLine = b.readLine();
        	             System.out.println(readLine);
        	            	writer.write(readLine);
        	            
        	            b.close();
            		}
            		 if(compteur<(carte.length*7)-1) {
                        compteur++;
                        writer.write("\n");
                    }
            }
            	}
            writer.close(); 
	}
}