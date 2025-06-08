package models;

import java.time.LocalDateTime;

public class EvenementParticipant {
    private int id;
    private int evenementId;
    private int clientId;
    private LocalDateTime dateInscription;
    private String statut;

    public EvenementParticipant() {
    }

    public EvenementParticipant(int id, int evenementId, int clientId, LocalDateTime dateInscription, String statut) {
        this.id = id;
        this.evenementId = evenementId;
        this.clientId = clientId;
        this.dateInscription = dateInscription;
        this.statut = statut;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvenementId() {
        return evenementId;
    }

    public void setEvenementId(int evenementId) {
        this.evenementId = evenementId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDateTime dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
    

}