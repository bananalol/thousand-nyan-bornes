/**
 * Joueur.java    22/06/2012
 */

package jeux.cartes.client;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

import jeux.cartes.carte.Carte;
import jeux.utilitaires.Configuration;

/**
 * Représente un joueur
 * @author Loïc Martinez
 * @version 1.0
 */

public class Joueur implements Serializable {

	/** Image du joueur */
	private byte[] imageJoueur; // Car serializable

	/** Pseudo du joueur */
	private String pseudo;

	/** Score du joueur */
	private int score;

	/** Fichier de configuration */
	private Configuration conf;

	/** Constantes */
	private static final int SCORE_INITIAL = 0;
	private static final String CONFIG_CLIENT = "configClient.properties";

	/**
	 * Constructeur
	 * @param pseudo, le pseudo joueur
	 * @throws IOException
	 */
	public Joueur(String pseudo) throws IOException {
		conf = new Configuration(CONFIG_CLIENT);

		File f = new File(conf.valueOf("playerPicture"));
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(ImageIO.read(f), "png", baos);
		imageJoueur = baos.toByteArray();
		
		this.pseudo = pseudo;
		this.score = SCORE_INITIAL;
	}

	/**
	 * Constructeur
	 * @param urlImageJoueur, le chemin vers l'image du joueur
	 * @param pseudo, le pseudo joueur
	 * @throws IOException
	 */
	public Joueur(String pseudo, String defaultPicture) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		File f = new File(Joueur.class.getResource(
				defaultPicture).getPath());
		
		ImageIO.write(ImageIO.read(f), "png", baos);
		imageJoueur = baos.toByteArray();
		
		this.pseudo = pseudo;
		this.score = SCORE_INITIAL;
	}

	/**
	 * @return l'image du joueur
	 */
	//public BufferedImage getImageJoueur() {
		//return imageJoueur;
	//}

	/**
	 * @return le pseudo du joueur
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * @return le score du joueur
	 */
	public int getScore() {
		return score;
	}
}
