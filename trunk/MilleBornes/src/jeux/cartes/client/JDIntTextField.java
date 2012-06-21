/**
 * JDLoginError.java	01/02/2012
 */

package jeux.cartes.client;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Fenêtre modale pour une erreur de login
 * @author Loïc Martinez
 * @version 1.0
 */

public class JDIntTextField extends JDialog implements ActionListener {

	/** Constantes */
	private static final String OK = "OK";
	private static final int LARGEUR = 320;
	private static final int HAUTEUR = 110;
	private static final int ABSCISSE = 480;
	private static final int ORDONNEE = 280;

	/** Composants de la JDialog */
	private JLabel label;
	private JTextField textField;
	private JButton bouton;
	private JPanel panLabel, panBouton;
	private int result;
	private boolean resultCorrect = false;

	/**
	 * Constructeur
	 * @param erreur, le message à afficher
	 */
	public JDIntTextField(String titre, String question) {
		setProperties(titre);
		addComponents(FlowLayout.CENTER, question);
		setEnter();
		setVisible(true);
	}

	/**
	 * Configuration des propriétés de la JFrame
	 */
	private void setProperties(String titre) {
		setTitle(titre);
		setBounds(ABSCISSE, ORDONNEE, LARGEUR, HAUTEUR);
		setResizable(false);
		setModal(true);
		getContentPane().setLayout(new FlowLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	/**
	 * Ajout du label et du bouton
	 * @param alignement, la position des éléments
	 * @param erreur, la valeur du JLabel
	 */
	private void addComponents(int alignement, String question) {
		panLabel = new JPanel(new FlowLayout(alignement));
		panLabel.setPreferredSize(new Dimension(LARGEUR, 30));
		label = new JLabel(question);
		panLabel.add(label);

		textField = new JTextField(10);
		panLabel.add(textField);

		add(panLabel);

		panBouton = new JPanel(new FlowLayout(alignement));
		panBouton.setPreferredSize(new Dimension(LARGEUR, 50));
		bouton = new JButton(OK);
		bouton.addActionListener(this);
		panBouton.add(bouton);
		add(panBouton);
	}

	/**
	 * Si on appuie sur Entrée, alors ça déclenche le listener
	 * du bouton bouton
	 */
	public void setEnter() {
		getRootPane().setDefaultButton(bouton);
	}

	/**
	 * Permet de connaître le résultat entré dans le JTextField
	 * @return result, le résultat entré
	 */
	public int getResult() {
		return result;
	}

	/**
	 * Permet de gérer les actions sur les ActionListeners
	 * @param ev, l'évènement déclenché
	 */
	public void actionPerformed(ActionEvent ev) {
		if (ev.getSource() == bouton) {
			do {
				try {
					result = Integer.parseInt(textField.getText().trim());
					resultCorrect = true;
				} catch (NumberFormatException ex) {
					new JDError("Veuillez entrer un nombre !");
				}
			} while (! resultCorrect);
			setVisible(false);
			dispose();
		}
	}
}
