package models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import enums.Enums.Departement;
import enums.Enums.StatutEmploye;

public class Employe {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String poste;
    private Departement departement;
    private BigDecimal salaire;
    private LocalDate dateEmbauche;
    private StatutEmploye statut;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public Employe() {
        this.statut = StatutEmploye.ACTIF;
    }
    

    public Employe(String nom, String prenom, String email, String telephone, String poste, Departement departement, BigDecimal salaire, LocalDate dateEmbauche, StatutEmploye statut) {
        this();
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.poste = poste;
        this.departement = departement;
        this.salaire = salaire;
        this.dateEmbauche = dateEmbauche;
        this.statut = statut;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    
    public String getPoste() { return poste; }
    public void setPoste(String poste) { this.poste = poste; }
    
    public Departement getDepartement() { return departement; }
    public void setDepartement(Departement departement) { this.departement = departement; }
    
    public BigDecimal getSalaire() { return salaire; }
    public void setSalaire(BigDecimal salaire) { this.salaire = salaire; }
    
    public LocalDate getDateEmbauche() { return dateEmbauche; }
    public void setDateEmbauche(LocalDate dateEmbauche) { this.dateEmbauche = dateEmbauche; }
    
    public StatutEmploye getStatut() { return statut; }
    public void setStatut(StatutEmploye statut) { this.statut = statut; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public String getNomComplet() {
        return prenom + " " + nom;
    }
    
    @Override
    public String toString() {
        return getNomComplet() + " - " + poste;
    }
}