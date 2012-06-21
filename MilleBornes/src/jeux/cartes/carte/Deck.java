/**
 * Deck.java    21/06/2012
 */

package jeux.cartes.carte;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Le paquet de cartes
 * @author Loïc Martinez
 * @version 1.0
 */

public class Deck {

	/**
	 * Distances
	 * 10x 25km
	 * 10x 50km
	 * 10x 75km
	 * 12x 100km
	 * 4x 200km
	 * 
	 * Bottes
	 * 1x As du volant
	 * 1x Camion-citerne
	 * 1x Increvable
	 * 1x Prioritaire
	 * 
	 * Attaques
	 * 3x Accident
	 * 3x Panne d'essence
	 * 3x Crevaison
	 * 4x Limite de vitesse
	 * 5x Feu rouge
	 * 
	 * Parades
	 * 6x Réparations
	 * 6x Essence
	 * 6x Roue de secours
	 * 6x Fin de limite
	 * 14x Feu vert
	 */
	
	private static final int NB_25_50_75 = 10;

	private static final int NB_100 = 12;

	private static final int NB_200_LIMITE_VITESSE = 4;

	private static final int NB_ACCIDENT_PANNE_CREVAISON = 3;

	private static final int NB_FEUX_ROUGES = 5;

	private static final int NB_REPARATION_ESSENCE_ROUE_FIN_LIMITE = 6;

	private static final int NB_FEUX_VERTS = 14;
	
	/** Liste de cartes */
	private List<Carte> deck;
	
	/**
	 * Constructeur
	 */
	public Deck() {
		deck = new ArrayList<Carte>();
		// On ajoute toutes les cartes au deck
		addCards();
	}
	
	/**
	 * Ajoute les cartes au deck
	 */
	private void addCards() {
		
		for (int i = 0; i < NB_25_50_75; i++) {
			deck.add(new Distance(Distance.KM_25, 25));
			deck.add(new Distance(Distance.KM_50, 50));
			deck.add(new Distance(Distance.KM_75, 75));
		}
		
		for (int i = 0; i < NB_100; i++) {
			deck.add(new Distance(Distance.KM_100, 100));
		}
		
		for (int i = 0; i < NB_200_LIMITE_VITESSE; i++) {
			deck.add(new Distance(Distance.KM_200, 200));
			deck.add(new Attaque(Attaque.LIMITE_DE_VITESSE, false));
		}
		
		for (int i = 0; i < NB_ACCIDENT_PANNE_CREVAISON; i++) {
			deck.add(new Attaque(Attaque.ACCIDENT, true));
			deck.add(new Attaque(Attaque.PANNE, true));
			deck.add(new Attaque(Attaque.CREVAISON, true));
		}
		
		for (int i = 0; i < NB_FEUX_ROUGES; i++) {
			deck.add(new Attaque(Attaque.FEU_ROUGE, true));
		}
		
		for (int i = 0; i < NB_REPARATION_ESSENCE_ROUE_FIN_LIMITE; i++) {
			deck.add(new Parade(Parade.REPARATION));
			deck.add(new Parade(Parade.ROUE_DE_SECOURS));
			deck.add(new Parade(Parade.ESSENCE));
			deck.add(new Parade(Parade.FIN_LIMITE));
		}
		
		for (int i = 0; i < NB_FEUX_VERTS; i++) {
			deck.add(new Parade(Parade.FEU_VERT));
		}
		
		deck.add(new Botte(Botte.AS_DU_VOLANT));
		deck.add(new Botte(Botte.CAMION_CITERNE));
		deck.add(new Botte(Botte.INCREVABLE));
		deck.add(new Botte(Botte.PRIORITAIRE));
		
		// On mélange le deck
		Collections.shuffle(deck);
		
		System.out.println(deck.size());
	}
}
