package models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import enums.Enums.CategorieService;

public class Service {
    private int id;
    private String nom;
    private String description;
    private BigDecimal prix;
    private CategorieService categorie;
    private boolean disponible;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public Service() {
        this.disponible = true;
    }
    
    public Service(String nom, String description, BigDecimal prix, CategorieService categorie) {
        this();
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.categorie = categorie;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public BigDecimal getPrix() { return prix; }
    public void setPrix(BigDecimal prix) { this.prix = prix; }
    
    public CategorieService getCategorie() { return categorie; }
    public void setCategorie(CategorieService categorie) { this.categorie = categorie; }
    
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    @Override
    public String toString() {
        return nom + " - " + prix + "€";
    }
}