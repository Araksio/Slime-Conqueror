package game.and.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.util.*;
import java.math.*;

public class Write {
//     private String a="src\\main\\resources\\assets\\levels\\";

//en attente de patch 
//    public Write(int hauteur,int largeur,String fichierSible) throws IOException, InterruptedException {
//        final String chemin = a+fichierSible;
//        final File fichier =new File(chemin); 
//        final String[][] carte= new Tsprite(hauteur,largeur).getMap();
//         int compteur = 0;
//         fichier.delete();
//           fichier .createNewFile();// Creation du fichier
//            final FileWriter writer = new FileWriter(fichier);// creation d'un writer (un �crivain)
//
//
//            for(int i=0;i<carte.length;i++) {//cette partie , recopie les spryte sur le fichier de la carte
//                for(int l=1;l<8;l++) {
//                    for(int j=0;j<carte[i].length;j++) {
//
//                        File f = new File(carte[i][j]);
//
//                        BufferedReader b = new BufferedReader(new FileReader(f));
//
//                        String readLine = "";
//                       if(l>1) {
//                           b.skip(9*(l-1));
//                          
//
//                       }
//                        readLine = b.readLine();
//                         System.out.println(readLine);
//                            writer.write(readLine);
//
//                        b.close();
//                    }
//
//                    writer.write("\n");
//
//                    }
//            }
//            writer.close();
//                }

	
	
//partie en attendant le fixe de l aleatoire
	// generateur de secours
	
	public Write() throws IOException, InterruptedException {
	    
		//va ecrire les 3premiers lvl
		for(int i = 0;i<=2; i+=1) {
		String cheminEcrire= "src/main/resources/assets/levels/";
		String cheminLire= "src/main/resources/assets/mapSecours/";
		File fichierEcrire =new File(cheminEcrire+"map_level"+i+".txt");
		
		
		int randomFile = (int) ((Math.random()*4)+1);
		
		File fichierLire =new File(cheminLire+randomFile+".txt");
		
		Scanner scLire= new Scanner(fichierLire);
		PrintWriter ecriteurDeFichierEcrire= new PrintWriter(fichierEcrire);

		
		 for(int j =0;j<20;j+=1) {
			 ecriteurDeFichierEcrire.println(scLire.next());
		 }
		 //pour eviter le probleme de la 22eme ligne fantome
		 ecriteurDeFichierEcrire.print(scLire.next());
	  
	  
		  scLire.close();
		  ecriteurDeFichierEcrire.close();
		}
	
	
	
	
	
	
	
	
	}
	
	
	
    }