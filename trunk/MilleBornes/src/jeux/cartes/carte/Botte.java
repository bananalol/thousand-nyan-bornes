/**
 * Botte.java    21/06/2012
 */

package jeux.cartes.carte;

/**
 * Carte botte
 * @author Loïc Martinez
 * @version 1.0
 */

public abstract class Botte extends Carte {
	
	/**
	 * Constructeur
	 * @param protection, la protection
	 */
	public Botte(String protection) {
		// On contruit la carte avec l'image de la protection
		super(protection);
	}
}
