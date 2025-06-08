package models;

import enums.Enums.ModePaiement;
import java.time.LocalDateTime;


public class Paiement {
    private int id;
    private int factureId;
    private double montant;
    private ModePaiement modePaiement;
    private String referenceTransaction;
    private LocalDateTime datePaiement;
    private String statut;

    public Paiement() {
    }

    public Paiement(int id, int factureId, double montant, ModePaiement modePaiement, String referenceTransaction, LocalDateTime datePaiement, String statut) {
        this.id = id;
        this.factureId = factureId;
        this.montant = montant;
        this.modePaiement = modePaiement;
        this.referenceTransaction = referenceTransaction;
        this.datePaiement = datePaiement;
        this.statut = statut;
    }

   

    public int getId() {  return id; }

    public void setId(int id) {  this.id = id; }

    public int getFactureId() {  return factureId; }

    public void setFactureId(int factureId) {  this.factureId = factureId; }

    public double getMontant() {  return montant; }

    public void setMontant(double montant) {  this.montant = montant; }

    public ModePaiement getModePaiement() {  return modePaiement; }

    public void setModePaiement(ModePaiement modePaiement) {  this.modePaiement = modePaiement; }

    public String getReferenceTransaction() {  return referenceTransaction; }

    public void setReferenceTransaction(String referenceTransaction) {  this.referenceTransaction = referenceTransaction; }

    public LocalDateTime getDatePaiement() {  return datePaiement; }

    public void setDatePaiement(LocalDateTime datePaiement) {  this.datePaiement = datePaiement; }

    public String getStatut() {  return statut; }

    public void setStatut(String statut) {  this.statut = statut; }
 
}