/**
 * Parade.java    21/06/2012
 */

package jeux.cartes.carte;

/**
 * Pour contrer une attaque
 * @author Loïc Martinez
 * @version 1.0
 */

public class Parade extends Carte {
	
	/** Nom des parades */
	public static final String REPARATION = "reparation";
	public static final String ROUE_DE_SECOURS = "roue_de_secours";
	public static final String ESSENCE = "essence";
	public static final String FIN_LIMITE = "fin_limite";
	public static final String FEU_VERT = "feu_vert";
	
	/**
	 * Constrcuteur
	 * @param nomParade, le nom de la parade
	 */
	public Parade(String nomParade) {
		super(nomParade);
	}
}
