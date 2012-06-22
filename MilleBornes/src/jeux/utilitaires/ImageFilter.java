/**
 * ImageFilter.java    22/06/2012
 */

package jeux.utilitaires;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileFilter;

/**
 * Filtre sur les extensions de fichiers
 * @author Loïc Martinez
 * @version 1.0
 */

public class ImageFilter extends FileFilter {

	/** Description du fichier */
	private String description;
	
	/** Extension du fichier */
	private List<String> extensions;
	
	/**
	 * Constructeur
	 * @param description, la description du fichier
	 * @param extension, l'extension du fichier
	 */
	public ImageFilter(String description) {
		this.description = description;
		this.extensions = new ArrayList<String>();
	}
	
	/**
	 * Permet d'ajouter une extension
	 * @param extension, l'extension à ajouter
	 */
	public void addExtension(String extension) {
		this.extensions.add(extension);
	}
	
	/**
	 * Permet d'afficher ce que l'on avec le filtre
	 */
	public boolean accept(File file) {
		
		// On veut aussi voir les dossiers
		if (file.isDirectory()) { 
			return true; 
		} 
		
		String nomFichier = file.getName().toLowerCase(); 

		for (String extension : extensions) {
	         if (nomFichier.endsWith(extension)) {
	            return true;
	         }
	      }
	      return false;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
}