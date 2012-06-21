/**
 * AccueilMilleBornes.java    21/06/2012
 */

package jeux.cartes;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class AccueilMilleBornes extends JFrame implements ActionListener {
	
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
	
	public AccueilMilleBornes() {
		// On initialise le titre, la position etc.
        setProperties();
        getContentPane().setLayout(new FlowLayout());
		setVisible(true);
	}
	
	/**
     * Configuration des propriétés de la JFrame
     */
    private void setProperties() {
        // Configuration de la JFrame
        setTitle(TITRE);
        setLocation(ABSCISSE, ORDONNEE);
        setSize(LARGEUR, HAUTEUR);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addMenuBar();

        // Ajout du 1er JPanel pour qu'il y ait un peu d'espace
        // avec le haut de la JFrame
        firstPan = new JPanel(new FlowLayout());
        firstPan.setPreferredSize(new Dimension(LARGEUR, 10));
        add(firstPan);
    }
    
    /**
     * Ajoute le menu Fichier, Options, Aide...
     */
    private void addMenuBar() {
        menuBar = new JMenuBar();
        menuFichier = new JMenu("Fichier");
        menuBar.add(menuFichier);

        menuOption = new JMenu("Options");
        menuBar.add(menuOption);

        menuAide = new JMenu("Aide");
        menuBar.add(menuAide);

        setJMenuBar(menuBar);

        menuQuitter = new JMenuItem("Quitter");
        menuFichier.add(menuQuitter);
        menuQuitter.addActionListener(this);

        menuPreferences = new JMenuItem("Préférences");
        menuOption.add(menuPreferences);
        menuPreferences.addActionListener(this);

        menuAPropos = new JMenuItem("A propos...");
        menuAide.add(menuAPropos);
        menuAPropos.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent ev) {
		if (ev.getSource() == menuQuitter) {
			dispose();
		}
	}
}