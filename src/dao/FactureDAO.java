package dao;

import models.Facture;
import enums.Enums.StatutFacture;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FactureDAO {

    public static int create(Facture f) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO facture (reservation_id, numero_facture, montant_total, montant_paye, statut, date_emission, date_echeance) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?)";
        return (int) DatabaseService.executeInsertWithGeneratedKey(query,
                f.getReservationId(),
                f.getNumeroFacture(),
                f.getMontantTotal(),
                f.getMontantPaye(),
                f.getStatut().name(),
                Date.valueOf(f.getDateEmission()),
                Date.valueOf(f.getDateEcheance())
        );
    }

    public static Facture findById(int id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM facture WHERE id = ?";
        ResultSet rs = DatabaseService.executeQuery(query, id);
        if (rs.next()) {
            return mapResultSetToFacture(rs);
        }
        return null;
    }

    public static List<Facture> findAll() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM facture ORDER BY date_emission DESC";
        ResultSet rs = DatabaseService.executeQuery(query);

        List<Facture> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapResultSetToFacture(rs));
        }
        return list;
    }

    public static boolean update(Facture f) throws SQLException, ClassNotFoundException {
        String query = "UPDATE facture SET reservation_id = ?, numero_facture = ?, montant_total = ?, montant_paye = ?, statut = ?, date_emission = ?, date_echeance = ? " +
                       "WHERE id = ?";
        int rows = DatabaseService.executeUpdate(query,
                f.getReservationId(),
                f.getNumeroFacture(),
                f.getMontantTotal(),
                f.getMontantPaye(),
                f.getStatut().name(),
                Date.valueOf(f.getDateEmission()),
                Date.valueOf(f.getDateEcheance()),
                f.getId()
        );
        return rows > 0;
    }

    public static boolean delete(int id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM facture WHERE id = ?";
        int rows = DatabaseService.executeUpdate(query, id);
        return rows > 0;
    }

    public static List<Facture> findByReservationId(int reservationId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM facture WHERE reservation_id = ?";
        ResultSet rs = DatabaseService.executeQuery(query, reservationId);

        List<Facture> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapResultSetToFacture(rs));
        }
        return list;
    }

    public static List<Facture> findByStatut(StatutFacture statut) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM facture WHERE statut = ?";
        ResultSet rs = DatabaseService.executeQuery(query, statut.name());

        List<Facture> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapResultSetToFacture(rs));
        }
        return list;
    }

    private static Facture mapResultSetToFacture(ResultSet rs) throws SQLException {
        Facture f = new Facture();
        f.setId(rs.getInt("id"));
        f.setReservationId(rs.getInt("reservation_id"));
        f.setNumeroFacture(rs.getString("numero_facture"));
        f.setMontantTotal(rs.getDouble("montant_total"));
        f.setMontantPaye(rs.getDouble("montant_paye"));
        f.setStatut(StatutFacture.valueOf(rs.getString("statut")));
        f.setDateEmission(rs.getDate("date_emission").toLocalDate());
        f.setDateEcheance(rs.getDate("date_echeance").toLocalDate());

        Timestamp createdAt = rs.getTimestamp("created_at");
        Timestamp updatedAt = rs.getTimestamp("updated_at");
        if (createdAt != null) f.setCreatedAt(createdAt.toLocalDateTime());
        if (updatedAt != null) f.setUpdatedAt(updatedAt.toLocalDateTime());

        return f;
    }
}
