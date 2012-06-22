/**
 * ServeurRequetes.java    19/04/2012
 */

package jeux.cartes.serveur;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import jeux.cartes.rmi.RequetesReseau;
import jeux.cartes.carte.Deck;
import jeux.cartes.client.Joueur;

/**
 * Passerelle RMI vers le serveur
 * @author Aboulkacem Hamid & Loïc Martinez
 */
public class ServeurRequetes extends UnicastRemoteObject implements
RequetesReseau {

	/** Nom du serveur RMI */
	private String name;

	/** Liste des joueurs connectés */
	private static List<Joueur> joueurs;
	
	/** Deck */
	private static Deck deck = new Deck();

	/** Logger */
	private static Logger logger = Logger.getLogger(ServeurRequetes.class);
	
	/**
	 * Constructeur
	 * @param name, le nom du serveur RMI
	 * @param port, le port du createRegistry
	 * @throws RemoteException, si un problème survient via RMI
	 */
	public ServeurRequetes(String name, int port) throws RemoteException {
		this.name = name;

		joueurs = new ArrayList<Joueur>();
		
		// On réserve le port
		LocateRegistry.createRegistry(port);

		lancer();
	}

	/**
	 * Permet de lancer le serveur
	 */
	public void lancer() {
		try {
			Naming.rebind("rmi://" + InetAddress.getLocalHost().getHostAddress() + "/" + name, this);
		} catch (MalformedURLException e) {
			logger.error("ServeurRequetes.lancer() : MalformedURLException");
		} catch (RemoteException e) {
			logger.error("ServeurRequetes.lancer() : RemoteException");
		} catch (UnknownHostException ex) {
			logger.error("ServeurRequetes.lancer() : UnknownHostException");
		}
	}

	/**
	 * Permet d'ajouter un joueur connecté
	 * @param joueur, le joueur à ajouter
	 */
	public void addJoueur(Joueur joueur) {
		joueurs.add(joueur);
	}
	
	/**
	 * Permet d'arrêter le serveur
	 */
	public void arreter() {
		try {
			if (Naming.list(name).length > 0) {
				Naming.unbind(name);
				UnicastRemoteObject.unexportObject(this, true);
			}
		} catch (RemoteException e) {
			logger.error("ServeurRequetes.arreter() : RemoteException");
		} catch (MalformedURLException e) {
			logger.error("ServeurRequetes.arreter() : MalformedURLException");
		} catch (NotBoundException e) {
			logger.error("ServeurRequetes.arreter() : NotBoundException");
		}
	}
}