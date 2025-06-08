package models;

import enums.Enums.PrioriteMaintenance;
import enums.Enums.StatutMaintenance;
import enums.Enums.TypeMaintenance;
import java.time.LocalDateTime;

public class Maintenance {
    private int id;
    private int chambreId;
    private Integer employeId;
    private TypeMaintenance typeMaintenance;
    private String description;
    private PrioriteMaintenance priorite;
    private StatutMaintenance statut;
    private LocalDateTime datePlanifiee;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private double cout;
    private String commentaires;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Maintenance() {
    }

    public Maintenance(int id, int chambreId, Integer employeId, TypeMaintenance typeMaintenance, String description, PrioriteMaintenance priorite, StatutMaintenance statut, LocalDateTime datePlanifiee, LocalDateTime dateDebut, LocalDateTime dateFin, double cout, String commentaires, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.chambreId = chambreId;
        this.employeId = employeId;
        this.typeMaintenance = typeMaintenance;
        this.description = description;
        this.priorite = priorite;
        this.statut = statut;
        this.datePlanifiee = datePlanifiee;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.cout = cout;
        this.commentaires = commentaires;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChambreId() {
        return chambreId;
    }

    public void setChambreId(int chambreId) {
        this.chambreId = chambreId;
    }

    public Integer getEmployeId() {
        return employeId;
    }

    public void setEmployeId(Integer employeId) {
        this.employeId = employeId;
    }

    public TypeMaintenance getTypeMaintenance() {
        return typeMaintenance;
    }

    public void setTypeMaintenance(TypeMaintenance typeMaintenance) {
        this.typeMaintenance = typeMaintenance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PrioriteMaintenance getPriorite() {
        return priorite;
    }

    public void setPriorite(PrioriteMaintenance priorite) {
        this.priorite = priorite;
    }

    public StatutMaintenance getStatut() {
        return statut;
    }

    public void setStatut(StatutMaintenance statut) {
        this.statut = statut;
    }

    public LocalDateTime getDatePlanifiee() {
        return datePlanifiee;
    }

    public void setDatePlanifiee(LocalDateTime datePlanifiee) {
        this.datePlanifiee = datePlanifiee;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public double getCout() {
        return cout;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    
}