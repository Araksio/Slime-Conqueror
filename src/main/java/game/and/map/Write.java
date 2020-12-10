package game.and.map;

import java.io.File;
import java.io.IOException;
import java.io.*;
import java.util.*;

public class Write {

	/**
	 * La classe write recupere aleatoirement un fichier map qu'il reecrit dans le fichier de la map de jeu.
	 * @param nbrLevel
	 * @author Mouchet-petit Nicolas principalement , Esteban un tout petit peu 
	 */
	public Write(int nbrLevel) throws IOException, InterruptedException {
	    
		for(int i = 0;i<2; i+=1) {
		String cheminEcrire= "src/main/resources/assets/levels/";
		String cheminLire= "src/main/resources/assets/mapSecours/";
		File fichierEcrire =new File(cheminEcrire+"map_level"+i+".txt");
		
		
		int randomFile = (int) ((Math.random()*countFiles(cheminLire))+1);
		
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
	/**
	 * countFiles compte le nombre de fichier dans le dossiers parent
	 * @param parent
	 * @return Le nombre de fichier 
	 *  @author Mouchet-petit Nicolas
	 */
	private int countFiles (String parent)  {//regarde le nombre de spyte existant pour qu'il soit tousse possiblement choisie
	    File file = new File (parent);
	    return file.list().length;
	}
	
	
	
   }