package models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TypeChambre {
    private int id;
    private String nom;
    private String description;
    private BigDecimal prixBase;
    private int capaciteMax;
    private String equipements;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public TypeChambre() {}

    public TypeChambre(int id, String nom, String description, BigDecimal prixBase, int capaciteMax, String equipements, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prixBase = prixBase;
        this.capaciteMax = capaciteMax;
        this.equipements = equipements;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
   
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public BigDecimal getPrixBase() { return prixBase; }
    public void setPrixBase(BigDecimal prixBase) { this.prixBase = prixBase; }
    
    public int getCapaciteMax() { return capaciteMax; }
    public void setCapaciteMax(int capaciteMax) { this.capaciteMax = capaciteMax; }
    
    public String getEquipements() { return equipements; }
    public void setEquipements(String equipements) { this.equipements = equipements; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    @Override
    public String toString() {
        return nom + " - " + prixBase + "€";
    }
}