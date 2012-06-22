/**
 * JDPreferences.java	01/02/2012
 */

package jeux.cartes.client;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import jeux.utilitaires.Configuration;
import jeux.utilitaires.ImageFilter;

/**
 * Fenêtre modale pour configuration du client
 * @author Loïc Martinez
 * @version 1.0
 */

public class JDPreferences extends JDialog implements ActionListener {

	/** Constantes */
	private static final String TITRE = "Préférences";
	private static final String OK = "OK";
	private static final String ANNULER = "Annuler";
	private static final int LARGEUR = 480;
	private static final int HAUTEUR = 160;
	private static final int ABSCISSE = 480;
	private static final int ORDONNEE = 280;

	/** Composants de la JDialog */
	private JLabel labelAdresse, labelImage;
	private JTextField textFieldAdresse, textFieldImage;
	private JButton valider, annuler, boutonImage;
	private JPanel firstPan, panLabelAdress, panLabelImage, panBouton;

	/** Configuration du client */
	private Configuration conf;

	/**
	 * Constructeur
	 * @param conf, la configuration à charger
	 */
	public JDPreferences(Configuration conf) {
		this.conf = conf;
		setProperties();
		addComponents(FlowLayout.CENTER);
		if (this.conf != null) {
			loadConfiguration();
		}
		setEnter();
		setVisible(true);
	}

	/**
	 * Configuration des propriétés de la JFrame
	 */
	private void setProperties() {
		setTitle(TITRE);
		setBounds(ABSCISSE, ORDONNEE, LARGEUR, HAUTEUR);
		setResizable(false);
		setModal(true);
		getContentPane().setLayout(new FlowLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		firstPan = new JPanel(new FlowLayout());
		firstPan.setPreferredSize(new Dimension(LARGEUR, 5));
		add(firstPan);
	}

	/**
	 * Ajout du label et du bouton
	 * @param alignement, la position des éléments
	 */
	private void addComponents(int alignement) {
		panLabelAdress = new JPanel(new FlowLayout(alignement));
		panLabelAdress.setPreferredSize(new Dimension(LARGEUR, 25));
		labelAdresse = new JLabel("Adresse du serveur ");
		panLabelAdress.add(labelAdresse);

		textFieldAdresse = new JTextField(30);
		panLabelAdress.add(textFieldAdresse);

		add(panLabelAdress);

		panLabelImage = new JPanel(new FlowLayout(alignement));
		panLabelImage.setPreferredSize(new Dimension(LARGEUR, 30));
		labelImage = new JLabel("            Image du joueur      ");
		panLabelImage.add(labelImage);

		textFieldImage = new JTextField(30);
		textFieldImage.setEnabled(false);
		panLabelImage.add(textFieldImage);

		boutonImage = new JButton("...");
		boutonImage.setPreferredSize(new Dimension(30, 17));
		boutonImage.addActionListener(this);
		panLabelImage.add(boutonImage);

		add(panLabelImage);

		panBouton = new JPanel(new FlowLayout(alignement));
		panBouton.setPreferredSize(new Dimension(LARGEUR, 50));
		valider = new JButton(OK);
		valider.addActionListener(this);
		panBouton.add(valider);
		annuler = new JButton(ANNULER);
		annuler.addActionListener(this);
		panBouton.add(annuler);
		add(panBouton);
	}

	/**
	 * Charge la configuration dans chaque JTextField
	 */
	public void loadConfiguration() {
		textFieldAdresse.setText(conf.valueOf("address"));
		textFieldImage.setText(conf.valueOf("playerPicture"));
	}

	/**
	 * Si on appuie sur Entrée, alors ça déclenche le listener
	 * du bouton bouton
	 */
	public void setEnter() {
		getRootPane().setDefaultButton(valider);
	}

	/**
	 * @return l'adresse du serveur
	 */
	public String getAddress() {
		return textFieldAdresse.getText();
	}

	/**
	 * Permet de gérer les actions sur les ActionListeners
	 */
	public void actionPerformed(ActionEvent ev) {
		if (ev.getSource() == valider) { // Ecrire avec Configuration
			// On modifie le fichier de configuration avec les valeurs entrées
			conf.modifierValeur("address", textFieldAdresse.getText());
			setVisible(false);
			dispose();
		} else if (ev.getSource() == annuler) { // Ne rien faire
			setVisible(false);
			dispose();
		} else if (ev.getSource() == boutonImage) {
			JFileChooser chooser = new JFileChooser();
			
			// Filtre du JFileChooser
			ImageFilter filtre = new ImageFilter("Fichiers png");
			
			filtre.addExtension(".png");
			
			chooser.addChoosableFileFilter(filtre);
			
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {	
				textFieldImage.setText(
						chooser.getSelectedFile().getAbsolutePath());
				conf.modifierValeur("playerPicture", textFieldImage.getText());
			}
		}
	}
}
