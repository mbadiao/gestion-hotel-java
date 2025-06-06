package models;

import java.time.LocalDateTime;

import enums.Enums.StatutChambre;

public class Chambre {
    private int id;
    private String numero;
    private int etage;
    private int typeChambreId;
    private TypeChambre typeChambre;
    private StatutChambre statut;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public Chambre() {
        this.statut = StatutChambre.DISPONIBLE;
    }
    
    public Chambre(String numero, int etage, int typeChambreId) {
        this();
        this.numero = numero;
        this.etage = etage;
        this.typeChambreId = typeChambreId;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    
    public int getEtage() { return etage; }
    public void setEtage(int etage) { this.etage = etage; }
    
    public int getTypeChambreId() { return typeChambreId; }
    public void setTypeChambreId(int typeChambreId) { this.typeChambreId = typeChambreId; }
    
    public TypeChambre getTypeChambre() { return typeChambre; }
    public void setTypeChambre(TypeChambre typeChambre) { this.typeChambre = typeChambre; }
    
    public StatutChambre getStatut() { return statut; }
    public void setStatut(StatutChambre statut) { this.statut = statut; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    @Override
    public String toString() {
        return "Chambre " + numero + " - " + statut;
    }
}