/**
 * AccueilMilleBornes.java    21/06/2012
 */

package jeux.cartes;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import jeux.utilitaires.Configuration;

/**
 * Fenêtre d'accueil du jeu de 1000 bornes
 * @author Loïc Martinez
 * @version 1.0
 */

public class AccueilMilleBornes extends JFrame {
	
	/** Constantes */
	private static final String TITRE = "Identification";
	private static final int LARGEUR = 290;
	private static final int HAUTEUR = 290;
	private static final int ABSCISSE = 500;
	private static final int ORDONNEE = 200;
	private static final String ID = "Identifiant";
	private static final String CNX = "Connexion";
	private static final String QUITTER = "Quitter";
	private final static String[] attributsConfig = {"address", "port"};

	/** Composants de la JFrame */
	private JLabel labelId;
	private JTextField fieldId;
	private JButton boutonConnexion, boutonQuitter;
	private JPanel firstPan, panImage, pan, panBouton;
	private JMenuBar menuBar;
	private JMenu menuFichier;
	private JMenu menuAide;
	private JMenu menuOption;
	private JMenuItem menuQuitter;
	private JMenuItem menuPreferences;
	private JMenuItem menuAPropos;

	/** Configuration du client */
	private Configuration conf;

	/** Passerelle Client - Serveur via RMI */
	private RequetesClient reqClient;
}