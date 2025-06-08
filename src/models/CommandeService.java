package models;

import enums.Enums.StatutCommande;
import java.time.LocalDateTime;

public class CommandeService {
    private int id;
    private int reservationId;
    private int serviceId;
    private int quantite;
    private double prixUnitaire;
    private double montantTotal;
    private StatutCommande statut;
    private LocalDateTime dateCommande;
    private LocalDateTime dateLivraison;
    private String commentaires;

    public CommandeService() {
    }

    public CommandeService(int id, int reservationId, int serviceId, int quantite, double prixUnitaire, double montantTotal, StatutCommande statut, LocalDateTime dateCommande, LocalDateTime dateLivraison, String commentaires) {
        this.id = id;
        this.reservationId = reservationId;
        this.serviceId = serviceId;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.montantTotal = montantTotal;
        this.statut = statut;
        this.dateCommande = dateCommande;
        this.dateLivraison = dateLivraison;
        this.commentaires = commentaires;
    }


    public int getId() {  return id; }

    public void setId(int id) {  this.id = id; }

    public int getReservationId() {  return reservationId; }

    public void setReservationId(int reservationId) {  this.reservationId = reservationId; }

    public int getServiceId() {  return serviceId; }

    public void setServiceId(int serviceId) {  this.serviceId = serviceId; }

    public int getQuantite() {  return quantite; }

    public void setQuantite(int quantite) {  this.quantite = quantite; }

    public double getPrixUnitaire() {  return prixUnitaire; }

    public void setPrixUnitaire(double prixUnitaire) {  this.prixUnitaire = prixUnitaire; }

    public double getMontantTotal() {  return montantTotal; }

    public void setMontantTotal(double montantTotal) {  this.montantTotal = montantTotal; }

    public StatutCommande getStatut() {  return statut; }

    public void setStatut(StatutCommande statut) {  this.statut = statut; }

    public LocalDateTime getDateCommande() {  return dateCommande; }

    public void setDateCommande(LocalDateTime dateCommande) {  this.dateCommande = dateCommande; }

    public LocalDateTime getDateLivraison() {  return dateLivraison; }

    public void setDateLivraison(LocalDateTime dateLivraison) {  this.dateLivraison = dateLivraison; }

    public String getCommentaires() {  return commentaires; }

    public void setCommentaires(String commentaires) {  this.commentaires = commentaires; }

    
}
