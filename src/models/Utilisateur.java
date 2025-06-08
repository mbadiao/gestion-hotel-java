package models;

import java.time.LocalDateTime;

public class Utilisateur {
    private int id;
    private String email;
    private String motDePasse;
    private String role;
    private Integer clientId;
    private Integer employeId;
    private boolean actif;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Utilisateur() {
    }

    public Utilisateur(int id, String email, String motDePasse, String role, Integer clientId, Integer employeId, boolean actif, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
        this.clientId = clientId;
        this.employeId = employeId;
        this.actif = actif;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {  return id; }

    public void setId(int id) {  this.id = id; }

    public String getEmail() {  return email; }

    public void setEmail(String email) {  this.email = email; }

    public String getMotDePasse() {  return motDePasse; }

    public void setMotDePasse(String motDePasse) {  this.motDePasse = motDePasse; }

    public String getRole() {  return role; }

    public void setRole(String role) {  this.role = role; }

    public Integer getClientId() {  return clientId; }

    public void setClientId(Integer clientId) {  this.clientId = clientId; }

    public Integer getEmployeId() {  return employeId; }

    public void setEmployeId(Integer employeId) {  this.employeId = employeId; }

    public boolean isActif() {  return actif; }

    public void setActif(boolean actif) {  this.actif = actif; }

    public LocalDateTime getCreatedAt() {  return createdAt; }

    public void setCreatedAt(LocalDateTime createdAt) {  this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() {  return updatedAt; }

    public void setUpdatedAt(LocalDateTime updatedAt) {  this.updatedAt = updatedAt; }

}