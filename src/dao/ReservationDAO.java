package dao;

import models.*;
import enums.Enums.StatutReservation;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ReservationDAO {

    public static Reservation getById(int id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM reservation WHERE id = ?";
        ResultSet rs = DatabaseService.executeQuery(query, id);

        if (rs.next()) {
            return mapResultSetToReservation(rs);
        }
        return null;
    }

    public static List<Reservation> getAll() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM reservation ORDER BY created_at DESC";
        ResultSet rs = DatabaseService.executeQuery(query);

        List<Reservation> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapResultSetToReservation(rs));
        }
        return list;
    }

    public static int insert(Reservation reservation) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO reservation (client_id, date_debut, date_fin, nombre_adultes, nombre_enfants, statut, montant_total, acompte, created_at, updated_at) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        long id = DatabaseService.executeInsertWithGeneratedKey(query,
                reservation.getClientId(),
                Date.valueOf(reservation.getDateCheckin()),
                Date.valueOf(reservation.getDateCheckout()),
                reservation.getNombreAdultes(),
                reservation.getNombreEnfants(),
                reservation.getStatut().name(),
                reservation.getMontantTotal(),
                reservation.getAcompte(),
                Timestamp.valueOf(reservation.getCreatedAt()),
                Timestamp.valueOf(reservation.getUpdatedAt())
        );

        reservation.setId((int) id);

        // Insertion des chambres liées
        for (ReservationChambre rc : reservation.getChambres()) {
            rc.setReservationId((int) id);
            ReservationChambreDAO.insert(rc);
        }

        return (int) id;
    }

    public static boolean update(Reservation reservation) throws SQLException, ClassNotFoundException {
        String query = "UPDATE reservation SET client_id = ?, date_debut = ?, date_fin = ?, nombre_adultes = ?, nombre_enfants = ?, statut = ?, montant_total = ?, acompte = ?, updated_at = ? WHERE id = ?";

        int rows = DatabaseService.executeUpdate(query,
                reservation.getClientId(),
                Date.valueOf(reservation.getDateCheckin()),
                Date.valueOf(reservation.getDateCheckout()),
                reservation.getNombreAdultes(),
                reservation.getNombreEnfants(),
                reservation.getStatut().name(),
                reservation.getMontantTotal(),
                reservation.getAcompte(),
                Timestamp.valueOf(reservation.getUpdatedAt()),
                reservation.getId()
        );

        return rows > 0;
    }

    public static boolean delete(int id) throws SQLException, ClassNotFoundException {
        // Supprimer d'abord les chambres liées
        ReservationChambreDAO.deleteByReservationId(id);

        String query = "DELETE FROM reservation WHERE id = ?";
        int rows = DatabaseService.executeUpdate(query, id);
        return rows > 0;
    }

    private static Reservation mapResultSetToReservation(ResultSet rs) throws SQLException, ClassNotFoundException {
        Reservation r = new Reservation();
        r.setId(rs.getInt("id"));
        r.setClientId(rs.getInt("client_id"));
        r.setClient(ClientDAO.findById(rs.getInt("client_id")));
        java.sql.Date dateDebutSql = rs.getDate("date_debut");
        if (dateDebutSql != null) {
            r.setDateCheckin(dateDebutSql.toLocalDate());
        } else {
            r.setDateCheckin(null);
        }
        java.sql.Date dateFinSql = rs.getDate("date_fin");
        if (dateFinSql != null) {
            r.setDateCheckout(dateFinSql.toLocalDate());
        } else {
            r.setDateCheckout(null);
        }
        r.setNombreAdultes(rs.getInt("nombre_adultes"));
        r.setNombreEnfants(rs.getInt("nombre_enfants"));
        r.setStatut(StatutReservation.valueOf(rs.getString("statut").toUpperCase()));
        r.setMontantTotal(rs.getBigDecimal("montant_total"));
        r.setAcompte(rs.getBigDecimal("acompte"));
        r.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        r.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        r.setChambres(ReservationChambreDAO.getByReservationId(r.getId()));
        return r;
    }
}
