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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jeux.utilitaires.Configuration;

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
    private static final int LARGEUR = 290;
    private static final int HAUTEUR = 130;
    private static final int ABSCISSE = 480;
    private static final int ORDONNEE = 280;

    /** Composants de la JDialog */
    private JLabel labelAdresse;
    private JTextField textFieldAdresse;
    private JButton valider, annuler;
    private JPanel firstPan, panLabel, panBouton;

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
        panLabel = new JPanel(new FlowLayout(alignement));
        panLabel.setPreferredSize(new Dimension(LARGEUR, 30));
        labelAdresse = new JLabel("Adresse du serveur ");
        panLabel.add(labelAdresse);

        textFieldAdresse = new JTextField(10);
        panLabel.add(textFieldAdresse);

        add(panLabel);

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
        }
    }
}
