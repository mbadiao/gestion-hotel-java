package models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import enums.Enums.StatutReservation;

public class Reservation {
    private int id;
    private int clientId;
    private Client client;
    private LocalDate dateCheckin;
    private LocalDate dateCheckout;
    private int nombreAdultes;
    private int nombreEnfants;
    private StatutReservation statut;
    private BigDecimal montantTotal;
    private BigDecimal acompte;
    private String commentaires;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ReservationChambre> chambres;
    
    public Reservation() {
        this.statut = StatutReservation.EN_ATTENTE;
        this.nombreAdultes = 1;
        this.nombreEnfants = 0;
        this.acompte = BigDecimal.ZERO;
        this.chambres = new ArrayList<>();
    }

    public Reservation(int clientId, Client client, LocalDate dateCheckin, LocalDate dateCheckout, int nombreAdultes, int nombreEnfants, StatutReservation statut, BigDecimal montantTotal, BigDecimal acompte, String commentaires, List<ReservationChambre> chambres) {
        this();
        this.clientId = clientId;
        this.client = client;
        this.dateCheckin = dateCheckin;
        this.dateCheckout = dateCheckout;
        this.nombreAdultes = nombreAdultes;
        this.nombreEnfants = nombreEnfants;
        this.statut = statut;
        this.montantTotal = montantTotal;
        this.acompte = acompte;
        this.commentaires = commentaires;
        this.chambres = chambres;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getClientId() { return clientId; }
    public void setClientId(int clientId) { this.clientId = clientId; }
    
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    
    public LocalDate getDateCheckin() { return dateCheckin; }
    public void setDateCheckin(LocalDate dateCheckin) { this.dateCheckin = dateCheckin; }
    
    public LocalDate getDateCheckout() { return dateCheckout; }
    public void setDateCheckout(LocalDate dateCheckout) { this.dateCheckout = dateCheckout; }
    
    public int getNombreAdultes() { return nombreAdultes; }
    public void setNombreAdultes(int nombreAdultes) { this.nombreAdultes = nombreAdultes; }
    
    public int getNombreEnfants() { return nombreEnfants; }
    public void setNombreEnfants(int nombreEnfants) { this.nombreEnfants = nombreEnfants; }
    
    public StatutReservation getStatut() { return statut; }
    public void setStatut(StatutReservation statut) { this.statut = statut; }
    
    public BigDecimal getMontantTotal() { return montantTotal; }
    public void setMontantTotal(BigDecimal montantTotal) { this.montantTotal = montantTotal; }
    
    public BigDecimal getAcompte() { return acompte; }
    public void setAcompte(BigDecimal acompte) { this.acompte = acompte; }
    
    public String getCommentaires() { return commentaires; }
    public void setCommentaires(String commentaires) { this.commentaires = commentaires; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public List<ReservationChambre> getChambres() { return chambres; }
    public void setChambres(List<ReservationChambre> chambres) { this.chambres = chambres; }
    
    @Override
    public String toString() {
        return "Réservation #" + id + " - " + dateCheckin + " au " + dateCheckout;
    }
}