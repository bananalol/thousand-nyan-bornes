/**
 * Botte.java    21/06/2012
 */

package jeux.cartes.carte;

/**
 * Carte botte
 * @author Loïc Martinez
 * @version 1.0
 */

public class Botte extends Carte {
	
	/** Nom des bottes */
	public static final String AS_DU_VOLANT = "as_du_volant";
	public static final String CAMION_CITERNE = "camion_citerne";
	public static final String INCREVABLE = "increvable";
	public static final String PRIORITAIRE = "prioritaire";
	
	/**
	 * Constructeur
	 * @param protection, la protection
	 */
	public Botte(String protection) {
		// On contruit la carte avec l'image de la protection
		super(protection);
	}
}
