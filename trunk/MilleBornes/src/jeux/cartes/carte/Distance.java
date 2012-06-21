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
	
	/** Nombre de kilomètres */
	public static final String KM_25 = "25km";
	public static final String KM_50 = "50km";
	public static final String KM_75 = "75km";
	public static final String KM_100 = "100km";
	public static final String KM_200 = "200km";
	
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
