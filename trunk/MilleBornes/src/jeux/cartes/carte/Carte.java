/**
 * Carte.java    21/06/2012
 */

package jeux.cartes.carte;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

/**
 * Représente une carte
 * @author Loïc Martinez
 * @version 1.0
 */

public class Carte {

	/** Image de la carte */
	private BufferedImage picture = null;
	
	/** Nom de la carte */
	private String pictureName;
	
	/** Logger */
	private static Logger logger = Logger.getLogger(Carte.class);
	
	/**
	 * Constructeur
	 * @param pictureName, nom de l'image
	 */
	public Carte(String pictureName) {
		try {
			this.picture = ImageIO.read
					(Carte.class.getResource(
							"pictures/" + pictureName + ".png"));
			this.pictureName = pictureName;
		} catch (IOException ex) {
			logger.error("Carte.Carte() : IOException");
		}
	}
	
	/**
	 * @return the picture
	 */
	public BufferedImage getPicture() {
		return picture;
	}
	
	/**
	 * @return the pictureName
	 */
	public String getPictureName() {
		return pictureName;
	}
}
