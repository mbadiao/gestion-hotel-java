package dao;

import models.EvenementParticipant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EvenementParticipantDAO {

    public static int create(EvenementParticipant ep) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO evenement_participant (evenement_id, client_id, date_inscription, statut) " +
                       "VALUES (?, ?, ?, ?)";
        return (int) DatabaseService.executeInsertWithGeneratedKey(query,
                ep.getEvenementId(),
                ep.getClientId(),
                Timestamp.valueOf(ep.getDateInscription()),
                ep.getStatut()
        );
    }

    public static EvenementParticipant findById(int id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM evenement_participant WHERE id = ?";
        ResultSet rs = DatabaseService.executeQuery(query, id);
        if (rs.next()) {
            return mapResultSetToEvenementParticipant(rs);
        }
        return null;
    }

    public static List<EvenementParticipant> findAll() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM evenement_participant ORDER BY date_inscription DESC";
        ResultSet rs = DatabaseService.executeQuery(query);

        List<EvenementParticipant> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapResultSetToEvenementParticipant(rs));
        }
        return list;
    }

    public static List<EvenementParticipant> findByEvenementId(int evenementId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM evenement_participant WHERE evenement_id = ?";
        ResultSet rs = DatabaseService.executeQuery(query, evenementId);

        List<EvenementParticipant> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapResultSetToEvenementParticipant(rs));
        }
        return list;
    }

    public static List<EvenementParticipant> findByClientId(int clientId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM evenement_participant WHERE client_id = ?";
        ResultSet rs = DatabaseService.executeQuery(query, clientId);

        List<EvenementParticipant> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapResultSetToEvenementParticipant(rs));
        }
        return list;
    }

    public static boolean update(EvenementParticipant ep) throws SQLException, ClassNotFoundException {
        String query = "UPDATE evenement_participant SET evenement_id = ?, client_id = ?, date_inscription = ?, statut = ? WHERE id = ?";
        int rows = DatabaseService.executeUpdate(query,
                ep.getEvenementId(),
                ep.getClientId(),
                Timestamp.valueOf(ep.getDateInscription()),
                ep.getStatut(),
                ep.getId()
        );
        return rows > 0;
    }

    public static boolean delete(int id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM evenement_participant WHERE id = ?";
        int rows = DatabaseService.executeUpdate(query, id);
        return rows > 0;
    }

    private static EvenementParticipant mapResultSetToEvenementParticipant(ResultSet rs) throws SQLException {
        EvenementParticipant ep = new EvenementParticipant();
        ep.setId(rs.getInt("id"));
        ep.setEvenementId(rs.getInt("evenement_id"));
        ep.setClientId(rs.getInt("client_id"));
        Timestamp inscription = rs.getTimestamp("date_inscription");
        if (inscription != null) {
            ep.setDateInscription(inscription.toLocalDateTime());
        }
        ep.setStatut(rs.getString("statut"));
        return ep;
    }
}
