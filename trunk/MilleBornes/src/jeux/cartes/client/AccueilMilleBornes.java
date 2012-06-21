/**
 * AccueilMilleBornes.java    21/06/2012
 */

package jeux.cartes.client;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import jeux.cartes.serveur.ServeurRequetes;
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
	private static final String PSEUDO = "Pseudo";
	private static final String CNX = "Connexion";
	private static final String QUITTER = "Quitter";
	private static final String SERVEUR = "Créer le serveur";
	private static final String[] attributsConfig = {"address"};

	/** Composants de la JFrame */
	private JLabel labelId;
	private JTextField fieldId;
	private JButton boutonConnexion, boutonQuitter;
	private JPanel firstPan, panImage, pan, panCheckBox, panBouton;
	private JCheckBox checkBoxServeur;
	private JMenuBar menuBar;
	private JMenu menuFichier;
	private JMenu menuAide;
	private JMenu menuOption;
	private JMenuItem menuQuitter;
	private JMenuItem menuPreferences;
	private JMenuItem menuAPropos;

	/** Nombre de joueurs */
	private int nombreDeJoueurs;

	/** Logger */
	private static Logger logger = Logger.getLogger(AccueilMilleBornes.class);

	/** Configuration du client */
	private Configuration conf;

	/** Serveur */
	ServeurRequetes serveur = null;

	/** Passerelle Client - Serveur via RMI */
	private RequetesClient reqClient;

	public AccueilMilleBornes() {
		// On initialise le titre, la position etc.
		setProperties();
		conf = new Configuration("configClient.properties", attributsConfig);
		getContentPane().setLayout(new FlowLayout());
		setPicture(FlowLayout.CENTER);
		setLabelAndField(FlowLayout.CENTER);
		setCheckBox(FlowLayout.CENTER);
		setButton(FlowLayout.LEFT);
		setEnter();
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

	/**
	 * Permet l'ajout du logo dans la JFrame
	 * @param alignement, la position
	 */
	private void setPicture(int alignement) {

		panImage = new JPanel(new FlowLayout(alignement));
		panImage.setPreferredSize(new Dimension(LARGEUR, 20));

		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read
					(AccueilMilleBornes.class.getResource("pictures/accueil_nyan_cat.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			add(picLabel);

			add(panImage);
		} catch (IOException e) {
			logger.error("AccueilMilleBornes.setPicture() : IOException");
		}
	}

	/**
	 * Ajoute les labels et textField associés à la JFrame
	 * @param alignement, la position
	 */
	private void setLabelAndField(int alignement) {

		pan = new JPanel(new FlowLayout(alignement));
		pan.setPreferredSize(new Dimension(LARGEUR, 25));

		labelId = new JLabel(PSEUDO + "  ");

		fieldId = new JTextField(10);

		pan.add(labelId);
		pan.add(fieldId);

		add(pan);
	}

	/**
	 * Ajoute le checkBox associé à la JFrame
	 * @param alignement, la position
	 */
	private void setCheckBox(int alignement) {

		panCheckBox = new JPanel(new FlowLayout(alignement));
		panCheckBox.setPreferredSize(new Dimension(LARGEUR, 30));

		checkBoxServeur = new JCheckBox(SERVEUR);
		panCheckBox.add(checkBoxServeur);

		add(panCheckBox);
	}

	/**
	 * Ajoute les boutons à la JFrame
	 * @param alignement, la position
	 */
	private void setButton(int alignement) {

		boutonConnexion = new JButton(CNX);
		boutonConnexion.addActionListener(this);
		boutonQuitter = new JButton(QUITTER);
		boutonQuitter.addActionListener(this);
		panBouton = new JPanel(new FlowLayout(alignement));

		panBouton.add(boutonConnexion);
		panBouton.add(boutonQuitter);
		add(panBouton);
	}

	/**
	 * Si on appuie sur Entrée, alors on déclenche le listener
	 * du boutonConnexion
	 */
	public void setEnter() {
		getRootPane().setDefaultButton(boutonConnexion);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		if (ev.getSource() == boutonConnexion) {
			if (! fieldId.getText().trim().equals("")) {
				if (checkBoxServeur.isSelected()) {
					// On créé le serveur
					try {
						serveur = new ServeurRequetes("serveurMilleBornes", 1099);

						// On demande le nombre de joueurs
						do {
							nombreDeJoueurs = new JDIntTextField("Joueurs ?",
									"Nombre de joueurs").getResult();
						} while (nombreDeJoueurs < 2 || nombreDeJoueurs > 4);

					} catch (RemoteException ex) {
						logger.error("AccueilMilleBornes.actionPerformed() : " +
								"RemoteException");
						new JDError("Serveur déjà créé !");
					}
				}
				// On se connecte au serveur

			} else {
				new JDError("Veuillez entrer un pseudo !");
			}
		} else if (ev.getSource() == menuQuitter
				|| ev.getSource() == boutonQuitter) {
			// Arrête le serveur par la même occasion
			System.exit(0);
		} else if (ev.getSource() == menuPreferences) {
			new JDPreferences(conf);
		}
	}
}