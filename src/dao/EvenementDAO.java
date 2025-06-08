package dao;

import models.Evenement;
import enums.Enums.StatutEvenement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EvenementDAO {

    public static int create(Evenement evt) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO evenement (nom, description, date_debut, date_fin, lieu, capacite_max, prix, organisateur_id, statut) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return (int) DatabaseService.executeInsertWithGeneratedKey(query,
                evt.getNom(),
                evt.getDescription(),
                Timestamp.valueOf(evt.getDateDebut()),
                Timestamp.valueOf(evt.getDateFin()),
                evt.getLieu(),
                evt.getCapaciteMax(),
                evt.getPrix(),
                evt.getOrganisateurId(),
                evt.getStatut().name()
        );
    }

    public static Evenement findById(int id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM evenement WHERE id = ?";
        ResultSet rs = DatabaseService.executeQuery(query, id);
        if (rs.next()) {
            return mapResultSetToEvenement(rs);
        }
        return null;
    }

    public static List<Evenement> findAll() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM evenement ORDER BY date_debut ASC";
        ResultSet rs = DatabaseService.executeQuery(query);

        List<Evenement> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapResultSetToEvenement(rs));
        }
        return list;
    }

    public static boolean update(Evenement evt) throws SQLException, ClassNotFoundException {
        String query = "UPDATE evenement SET nom = ?, description = ?, date_debut = ?, date_fin = ?, lieu = ?, capacite_max = ?, prix = ?, organisateur_id = ?, statut = ? " +
                       "WHERE id = ?";
        int rows = DatabaseService.executeUpdate(query,
                evt.getNom(),
                evt.getDescription(),
                Timestamp.valueOf(evt.getDateDebut()),
                Timestamp.valueOf(evt.getDateFin()),
                evt.getLieu(),
                evt.getCapaciteMax(),
                evt.getPrix(),
                evt.getOrganisateurId(),
                evt.getStatut().name(),
                evt.getId()
        );
        return rows > 0;
    }

    public static boolean delete(int id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM evenement WHERE id = ?";
        int rows = DatabaseService.executeUpdate(query, id);
        return rows > 0;
    }

    public static List<Evenement> findByStatut(StatutEvenement statut) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM evenement WHERE statut = ?";
        ResultSet rs = DatabaseService.executeQuery(query, statut.name());

        List<Evenement> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapResultSetToEvenement(rs));
        }
        return list;
    }

    private static Evenement mapResultSetToEvenement(ResultSet rs) throws SQLException {
        Evenement evt = new Evenement();
        evt.setId(rs.getInt("id"));
        evt.setNom(rs.getString("nom"));
        evt.setDescription(rs.getString("description"));
        evt.setDateDebut(rs.getTimestamp("date_debut").toLocalDateTime());
        evt.setDateFin(rs.getTimestamp("date_fin").toLocalDateTime());
        evt.setLieu(rs.getString("lieu"));
        evt.setCapaciteMax(rs.getInt("capacite_max"));
        evt.setPrix(rs.getDouble("prix"));
        evt.setOrganisateurId(rs.getInt("organisateur_id"));
        evt.setStatut(StatutEvenement.valueOf(rs.getString("statut")));

        Timestamp createdAt = rs.getTimestamp("created_at");
        Timestamp updatedAt = rs.getTimestamp("updated_at");
        if (createdAt != null) evt.setCreatedAt(createdAt.toLocalDateTime());
        if (updatedAt != null) evt.setUpdatedAt(updatedAt.toLocalDateTime());

        return evt;
    }
}

