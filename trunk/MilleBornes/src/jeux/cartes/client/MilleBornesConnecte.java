/**
 * MilleBornesConnecte.java    22/06/2012
 */

package jeux.cartes.client;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * L'interface de jeu
 * @author Loïc Martinez
 * @version 1.0
 */

public class MilleBornesConnecte extends JFrame {

	/** Conteneur général */
	private GridBagLayout general;
	private JPanel panelGeneral;
	
	/** Constantes */
	private static final String TITRE = "Identification";
	private static final int LARGEUR = 600;
	private static final int HAUTEUR = 600;
	private static final int ABSCISSE = 400;
	private static final int ORDONNEE = 100;
	
	public MilleBornesConnecte() {
		general = new GridBagLayout();
		panelGeneral = new JPanel(general);
		panelGeneral.setBackground(Color.BLACK);
		add(panelGeneral);
		setLocation(ABSCISSE, ORDONNEE);
		setSize(LARGEUR, HAUTEUR);
		setVisible(true);
	}
}
