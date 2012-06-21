/**
 * Attaque.java    21/06/2012
 */

package jeux.cartes.carte;

/**
 * Carte d'attaque
 * @author Loïc Martinez
 * @version 1.0
 */

public class Attaque extends Carte {
	
	/** Nom des attaques */
	public static final String LIMITE_DE_VITESSE = "limite";
	public static final String ACCIDENT = "accident";
	public static final String PANNE = "panne";
	public static final String CREVAISON = "crevaison";
	public static final String FEU_ROUGE = "feu_rouge";
	
	/** Attaque blocante */
	private boolean attaqueBlocante;
	
	/**
	 * Constructeur
	 * @param attaqueName, le nom de l'attaque
	 * @param attaqueBlocante, si l'attaque est blocante ou pas
	 *        Ex : Limitation pas bloquant, Crevaison si
	 */
	public Attaque(String attaqueName, boolean attaqueBlocante) {
		super(attaqueName);
		this.attaqueBlocante = attaqueBlocante;
	}
	
	/**
	 * @return attaqueBlocante
	 */
	public boolean getAttaqueBlocante() {
		return attaqueBlocante;
	}
}
