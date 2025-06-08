package models;

import enums.Enums.StatutFacture;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Facture {
    private int id;
    private int reservationId;
    private String numeroFacture;
    private double montantTotal;
    private double montantPaye;
    private StatutFacture statut;
    private LocalDate dateEmission;
    private LocalDate dateEcheance;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Facture() {
    }

    public Facture(int id, int reservationId, String numeroFacture, double montantTotal, double montantPaye, StatutFacture statut, LocalDate dateEmission, LocalDate dateEcheance, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.reservationId = reservationId;
        this.numeroFacture = numeroFacture;
        this.montantTotal = montantTotal;
        this.montantPaye = montantPaye;
        this.statut = statut;
        this.dateEmission = dateEmission;
        this.dateEcheance = dateEcheance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    

    public int getId() {    return id; }

    public void setId(int id) {    this.id = id; }

    public int getReservationId() {    return reservationId; }

    public void setReservationId(int reservationId) {    this.reservationId = reservationId; }

    public String getNumeroFacture() {    return numeroFacture; }

    public void setNumeroFacture(String numeroFacture) {    this.numeroFacture = numeroFacture; }

    public double getMontantTotal() {    return montantTotal; }

    public void setMontantTotal(double montantTotal) {    this.montantTotal = montantTotal; }

    public double getMontantPaye() {    return montantPaye; }

    public void setMontantPaye(double montantPaye) {    this.montantPaye = montantPaye; }

    public StatutFacture getStatut() {    return statut; }

    public void setStatut(StatutFacture statut) {    this.statut = statut; }

    public LocalDate getDateEmission() {    return dateEmission; }

    public void setDateEmission(LocalDate dateEmission) {    this.dateEmission = dateEmission; }

    public LocalDate getDateEcheance() {    return dateEcheance; }

    public void setDateEcheance(LocalDate dateEcheance) {    this.dateEcheance = dateEcheance; }

    public LocalDateTime getCreatedAt() {    return createdAt; }

    public void setCreatedAt(LocalDateTime createdAt) {    this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() {    return updatedAt; }

    public void setUpdatedAt(LocalDateTime updatedAt) {    this.updatedAt = updatedAt; }

}