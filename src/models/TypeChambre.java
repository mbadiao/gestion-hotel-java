package models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TypeChambre {
    private int id;
    private String nom;
    private String description;
    private BigDecimal prixBase;
    private int capaciteMax;
    private String amenities;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public TypeChambre() {}
    
    public TypeChambre(String nom, String description, BigDecimal prixBase, int capaciteMax) {
        this.nom = nom;
        this.description = description;
        this.prixBase = prixBase;
        this.capaciteMax = capaciteMax;
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
    
    public String getAmenities() { return amenities; }
    public void setAmenities(String amenities) { this.amenities = amenities; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    @Override
    public String toString() {
        return nom + " - " + prixBase + "€";
    }
}