/**
 * JDLoginError.java	01/02/2012
 */

package jeux.cartes;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Fenêtre modale pour une erreur de login
 * @author Loïc Martinez
 * @version 1.0
 */

public class JDError extends JDialog implements ActionListener {

    /** Constantes */
    private static final String TITRE = "Erreur";
    private static final String OK = "OK";
    private static final int LARGEUR = 320;
    private static final int HAUTEUR = 110;
    private static final int ABSCISSE = 480;
    private static final int ORDONNEE = 280;

    /** Composants de la JDialog */
    private JLabel label;
    private JButton bouton;
    private JPanel panLabel, panBouton;

    /**
     * Constructeur
     * @param erreur, le message à afficher
     */
    public JDError(String erreur) {
        setProperties();
        addComponents(FlowLayout.CENTER, erreur);
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
    }

    /**
     * Ajout du label et du bouton
     * @param alignement, la position des éléments
     * @param erreur, la valeur du JLabel
     */
    private void addComponents(int alignement, String erreur) {
        panLabel = new JPanel(new FlowLayout(alignement));
        panLabel.setPreferredSize(new Dimension(LARGEUR, 30));
        label = new JLabel(erreur);
        panLabel.add(label);
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
     * Permet de gérer les actions sur les ActionListeners
     * @param ev, l'évènement déclenché
     */
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == bouton) {
            setVisible(false);
            dispose();
        }
    }
}
