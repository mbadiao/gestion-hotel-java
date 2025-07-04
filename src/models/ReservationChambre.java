package models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReservationChambre {
    private int id;
    private int reservationId;
    private int chambreId;
    private Chambre chambre;
    private BigDecimal prixNuit;
    private LocalDateTime createdAt;
    
    public ReservationChambre() {}

    public ReservationChambre(int id, int reservationId, int chambreId, Chambre chambre, BigDecimal prixNuit, LocalDateTime createdAt) {
        this.id = id;
        this.reservationId = reservationId;
        this.chambreId = chambreId;
        this.chambre = chambre;
        this.prixNuit = prixNuit;
        this.createdAt = createdAt;
    }

    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getReservationId() { return reservationId; }
    public void setReservationId(int reservationId) { this.reservationId = reservationId; }
    
    public int getChambreId() { return chambreId; }
    public void setChambreId(int chambreId) { this.chambreId = chambreId; }
    
    public Chambre getChambre() { return chambre; }
    public void setChambre(Chambre chambre) { this.chambre = chambre; }
    
    public BigDecimal getPrixNuit() { return prixNuit; }
    public void setPrixNuit(BigDecimal prixNuit) { this.prixNuit = prixNuit; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
