/**
 * requetesReseau.java    19/04/2012
 */

package jeux.cartes.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import jeux.cartes.client.Joueur;

/**
 * Méthode à écrire dans ServeurRequetes (to override)
 * @author Loïc Martinez
 */

public interface RequetesReseau extends Remote {
	
	/**
     * Permet de se connecter à la partie
     * @param joueur, le joueur à enregistrer
     * @throws RemoteException 
     */
	public void addJoueur(Joueur joueur) throws RemoteException;
}