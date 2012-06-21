/**
 * Distance.java    21/06/2012
 */

package jeux.cartes.carte;

/**
 * Distance pour avancer
 * @author Loïc Martinez
 * @version 1.0
 */

public class Distance extends Carte {
	
	/** Distance de la carte */
	private int distance;
	
	/**
	 * Constructeur
	 * @param nomCarte, le nom de la carte
	 * @param distance, la valeur de la carte
	 */
	public Distance(String nomCarte, int distance) {
		super(nomCarte);
		this.distance = distance;
	}
	
	/**
	 * @return distance
	 */
	public int getDistance() {
		return distance;
	}
}
