package models;

import enums.Enums.StatutEvenement;
import java.time.LocalDateTime;

public class Evenement {
    private int id;
    private String nom;
    private String description;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String lieu;
    private int capaciteMax;
    private double prix;
    private int organisateurId;
    private StatutEvenement statut;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Evenement() {
    }

    public Evenement(int id, String nom, String description, LocalDateTime dateDebut, LocalDateTime dateFin, String lieu, int capaciteMax, double prix, int organisateurId, StatutEvenement statut, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lieu = lieu;
        this.capaciteMax = capaciteMax;
        this.prix = prix;
        this.organisateurId = organisateurId;
        this.statut = statut;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    

    public int getId() {    return id; }

    public void setId(int id) {    this.id = id; }

    public String getNom() {    return nom; }

    public void setNom(String nom) {    this.nom = nom; }

    public String getDescription() {    return description; }

    public void setDescription(String description) {    this.description = description; }

    public LocalDateTime getDateDebut() {    return dateDebut; }

    public void setDateDebut(LocalDateTime dateDebut) {    this.dateDebut = dateDebut; }

    public LocalDateTime getDateFin() {    return dateFin; }

    public void setDateFin(LocalDateTime dateFin) {    this.dateFin = dateFin; }

    public String getLieu() {    return lieu; }

    public void setLieu(String lieu) {    this.lieu = lieu; }

    public int getCapaciteMax() {    return capaciteMax; }

    public void setCapaciteMax(int capaciteMax) {    this.capaciteMax = capaciteMax; }

    public double getPrix() {    return prix; }

    public void setPrix(double prix) {    this.prix = prix; }

    public int getOrganisateurId() {    return organisateurId; }

    public void setOrganisateurId(int organisateurId) {    this.organisateurId = organisateurId; }

    public StatutEvenement getStatut() {    return statut; }

    public void setStatut(StatutEvenement statut) {    this.statut = statut; }

    public LocalDateTime getCreatedAt() {    return createdAt; }

    public void setCreatedAt(LocalDateTime createdAt) {    this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() {    return updatedAt; }

    public void setUpdatedAt(LocalDateTime updatedAt) {    this.updatedAt = updatedAt; }

    
}