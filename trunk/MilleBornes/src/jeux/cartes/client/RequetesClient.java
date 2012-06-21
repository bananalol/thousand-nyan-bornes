/**
 * RequetesClient.java    19/04/2012
 */

package jeux.cartes.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import jeux.cartes.rmi.RequetesReseau;

import org.apache.log4j.Logger;

import jeux.utilitaires.Configuration;

/**
 * Permet la passerelle Client - Serveur via RMI
 * @author Loïc Martinez & Aboulkacem Hamid
 */
public class RequetesClient {

    /** Connexion avec le ServeurRequetes */
    private static RequetesReseau connexionServer = null;

    /** Configuration du client */
    private Configuration conf = new Configuration("configClient.properties");

    /** Logger */
    private static Logger logger = Logger.getLogger(RequetesClient.class);
    
    /**
     * Constructeur par défaut
     * @throws MalformedURLException, si problème durant le lookup
     * @throws RemoteException, si problème via RMI
     */
    public RequetesClient() throws NotBoundException, RemoteException {
        try {

            connexionServer = (RequetesReseau) Naming.lookup("rmi://"
                    + conf.valueOf("address") + "/serveur");

        } catch (MalformedURLException ex) {
            new JDError("Mauvaise URL");
            logger.error("RequetesClient.RequetesClient() : MalformedURLException");
        }
    }

    /**
     * @return connexionServer
     */
    public RequetesReseau getReseau() {
        return connexionServer;
    }
}