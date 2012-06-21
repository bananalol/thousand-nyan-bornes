/**
 * Configuration.java    08/03/2012
 */

package jeux.utilitaires;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * Permet de gérer un fichier de configuration (création si non existence etc.)
 * @author Loïc Martinez
 * @version 1.0
 */

public class Configuration implements Serializable {

    /** Le nom du fichier à créer */
    private String nomFichier;

    /** Le répertoire courant */
    private static final String repertoireCourant =
            System.getProperty("user.dir") + "/";

    /** Logger */
    private static Logger logger = Logger.getLogger(Configuration.class);
    
    /**
     * Créé le fichier s'il n'existe pas déjà
     * @param nomFichier, le nom du fichier à créer
     */
    public Configuration(String nomFichier, String[] attributs) {
        try {
            File f = new File(repertoireCourant + nomFichier);
            this.nomFichier = nomFichier;
            if (!f.exists()) {
                f.createNewFile();
                ecrireAttributs(f, attributs);
            }
        } catch (IOException e) {
            logger.error("Configuration.Configuration() : IOException");
        }
    }

    /**
     * Permet de juste lire le fichier
     * @param nomFichier, le nom du fichier à lire
     */
    public Configuration(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    /**
     * Ecrit les attributs dans le fichier
     * @param f, le fichier
     * @param att, le tableau d'attributs
     */
    public void ecrireAttributs(File f, String[] att) {
        try {
            FileWriter fw = new FileWriter(f);
            BufferedWriter output = new BufferedWriter(fw);
            for (int i = 0; i < att.length; i++) {
                output.write(att[i] + "=" + "\n");
            }
            output.flush();
            output.close();
        } catch (IOException e) {
            logger.error("Configuration.ecrireAttributs() : IOException");
        }
    }

    /**
     * Modifie la valeur des attributs
     * @param attribut, le nom de l'attribut
     * @param valeur, la valeur à attribuer à attribut
     */
    public void modifierValeur(String attribut, String valeur) {
        try {
            Properties p = new Properties();
            FileInputStream inStream = new FileInputStream(repertoireCourant
                    + getNomFichier());
            p.load(inStream);
            changeValue(p, attribut, valeur);
            FileOutputStream fos = new FileOutputStream(repertoireCourant
                    + getNomFichier());
            p.store(fos, null);
            inStream.close();
            fos.close();
        } catch (IOException e) {
            logger.error("Configuration.modifierValeur() : IOException");
        }
    }

    /**
     * Change la valeur d'un attribut d'un objet Properties
     * @param p, l'objet Properties à modifier
     * @param attribut, le nom de l'attribut
     * @param valeur, la valeur à attribuer à attribut
     * @return p, l'objet Properties modifié
     */
    public Properties changeValue(Properties p, String attribut,
            String valeur) {
        Iterator<Object> it = p.keySet().iterator();
        while (it.hasNext()) {
            String propertyName = it.next().toString();
            if (propertyName.equals(attribut)) {
                p.setProperty(attribut, valeur);
            } else {
                String propertyValue = p.getProperty(propertyName);
                p.setProperty(propertyName, propertyValue);
            }
        }
        return p;
    }

    /**
     * Retourne la valeur d'un attribut
     * @param attribut, l'attribut dont on veut la valeur
     * @return la valeur de l'attribut
     */
    public String valueOf(String attribut) {
        try {
            Properties p = new Properties();
            FileInputStream inStream;
            inStream = new FileInputStream(repertoireCourant + getNomFichier());
            try {
                p.load(inStream);
                return p.getProperty(attribut);
            } catch (IOException e) {
                logger.error("Configuration.valueOf() : IOException");
            }
        } catch (FileNotFoundException e) {
            logger.error("Configuration.valueOf() : FileNotFoundException");
        }
        return null;
    }

    /**
     * Getter nomFichier
     * @return nomFichier, le nom du fichier
     */
    public String getNomFichier() {
        return nomFichier;
    }
}
