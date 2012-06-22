/**
 * Joueur.java    22/06/2012
 */

package jeux.cartes.client;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jeux.cartes.carte.Carte;
import jeux.utilitaires.Configuration;

/**
 * Représente un joueur
 * @author Loïc Martinez
 * @version 1.0
 */

public class Joueur {

	/** Image du joueur */
	private BufferedImage imageJoueur;
	
	/** Pseudo du joueur */
	private String pseudo;
	
	/** Score du joueur */
	private int score;
	
	/** Fichier de configuration */
	private Configuration conf;
	
	/** Constantes */
	private static final int SCORE_INITIAL = 0;

	private static final String CONFIG_CLIENT = "configClient.properties";
	
	public Joueur(String urlImageJoueur, String pseudo) throws IOException {
		conf = new Configuration(CONFIG_CLIENT);
		this.imageJoueur = ImageIO.read(new File(conf.valueOf("playerPicture")));
		this.pseudo = pseudo;
		this.score = SCORE_INITIAL;
	}
}
