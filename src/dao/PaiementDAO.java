package dao;

import models.Paiement;
import enums.Enums.ModePaiement;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PaiementDAO {

    public static int create(Paiement p) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO paiement (facture_id, montant, mode_paiement, reference_transaction, date_paiement, statut) " +
                       "VALUES (?, ?, ?, ?, ?, ?)";

        return (int) DatabaseService.executeInsertWithGeneratedKey(query,
                p.getFactureId(),
                p.getMontant(),
                p.getModePaiement().name(),
                p.getReferenceTransaction(),
                toTimestamp(p.getDatePaiement()),
                p.getStatut()
        );
    }

    public static Paiement findById(int id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM paiement WHERE id = ?";
        ResultSet rs = DatabaseService.executeQuery(query, id);
        if (rs.next()) {
            return mapResultSetToPaiement(rs);
        }
        return null;
    }

    public static List<Paiement> findAll() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM paiement ORDER BY date_paiement DESC";
        ResultSet rs = DatabaseService.executeQuery(query);

        List<Paiement> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapResultSetToPaiement(rs));
        }
        return list;
    }

    public static List<Paiement> findByFactureId(int factureId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM paiement WHERE facture_id = ?";
        ResultSet rs = DatabaseService.executeQuery(query, factureId);

        List<Paiement> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapResultSetToPaiement(rs));
        }
        return list;
    }

    public static boolean update(Paiement p) throws SQLException, ClassNotFoundException {
        String query = "UPDATE paiement SET facture_id = ?, montant = ?, mode_paiement = ?, reference_transaction = ?, " +
                       "date_paiement = ?, statut = ? WHERE id = ?";

        int rows = DatabaseService.executeUpdate(query,
                p.getFactureId(),
                p.getMontant(),
                p.getModePaiement().name(),
                p.getReferenceTransaction(),
                toTimestamp(p.getDatePaiement()),
                p.getStatut(),
                p.getId()
        );

        return rows > 0;
    }

    public static boolean delete(int id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM paiement WHERE id = ?";
        int rows = DatabaseService.executeUpdate(query, id);
        return rows > 0;
    }

    private static Paiement mapResultSetToPaiement(ResultSet rs) throws SQLException {
        Paiement p = new Paiement();
        p.setId(rs.getInt("id"));
        p.setFactureId(rs.getInt("facture_id"));
        p.setMontant(rs.getDouble("montant"));
        p.setModePaiement(ModePaiement.valueOf(rs.getString("mode_paiement")));
        p.setReferenceTransaction(rs.getString("reference_transaction"));
        p.setDatePaiement(toLocalDateTime(rs.getTimestamp("date_paiement")));
        p.setStatut(rs.getString("statut"));
        return p;
    }

    private static Timestamp toTimestamp(LocalDateTime dateTime) {
        return dateTime != null ? Timestamp.valueOf(dateTime) : null;
    }

    private static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }
}
