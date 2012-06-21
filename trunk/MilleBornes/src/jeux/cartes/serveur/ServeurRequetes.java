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
import org.apache.log4j.Logger;

import jeux.cartes.rmi.RequetesReseau;

/**
 * Passerelle RMI vers le serveur
 * @author Aboulkacem Hamid & Loïc Martinez
 */
public class ServeurRequetes extends UnicastRemoteObject implements
RequetesReseau {

    /** Nom du serveur RMI */
    private String name;

    /** Permet de savoir si le registre a été créé ou non */
    private boolean registryCree = false;

    /**
     * Constructeur
     * @param name, le nom du serveur RMI
     * @throws RemoteException, si un problème survient via RMI
     */
    public ServeurRequetes(String name) throws RemoteException {
        this.name = name;
    }
    
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

        // On réserve le port
        LocateRegistry.createRegistry(port);

        lancer();
        registryCree = true;
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
     * Permet d'arrêter le serveur
     */
    public void arreter() {
        try {
            Naming.unbind(name);
            UnicastRemoteObject.unexportObject(this, true);
            // System.gc();
        } catch (RemoteException e) {
            logger.error("ServeurRequetes.arreter() : RemoteException");
        } catch (MalformedURLException e) {
            logger.error("ServeurRequetes.arreter() : MalformedURLException");
        } catch (NotBoundException e) {
            logger.error("ServeurRequetes.arreter() : NotBoundException");
        }
    }

    /**
     * Setter de registryCree
     * @param b, true ou false
     */
    public void setRegistryCree(boolean b) {
        registryCree = b;
    }

    /**
     * @return registryCree
     */
    public boolean getRegistryCree() {
        return registryCree;
    }
}