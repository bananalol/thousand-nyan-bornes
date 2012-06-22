/**
 * Message.java                                22 juin 2012
 *
 */
package jeux.cartes.client;

/**
 * TODO Commenter la responsabilité de cette classe
 * @author Eween
 *
 */
public class Message {
    
    /** Type de message */
    private String typeMessage; // Car serializable

    /** Contenu du message */
    private String contenuMessage;

    /** Constantes */
    private static final String TYPE_INITIAL = "Information";
    private static final String CONT_INITIAL = "Message vide";
    
    /** Constructeur par défaut */
    public Message() {
            this.typeMessage = TYPE_INITIAL;
            this.contenuMessage = CONT_INITIAL;
    }

    /**
     * Constructeur
     * @param typeMessage, ContenuMessage
     */
    public Message(String typeMessage, String contenuMessage) {
            this.typeMessage = typeMessage;
            this.contenuMessage = contenuMessage;
    }

    /**
     * @return le contenu d'un message
     */
    public String getContenuMessage() {
            return contenuMessage;
    }

    /**
     * @return le type d'un message
     */
    public String getTypeMessage() {
            return typeMessage;
    }
}
