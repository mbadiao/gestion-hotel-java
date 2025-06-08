package dao;

import models.Maintenance;
import enums.Enums.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceDAO {

    public static int create(Maintenance m) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO maintenance (chambre_id, employe_id, type_maintenance, description, priorite, statut, " +
                       "date_planifiee, date_debut, date_fin, cout, commentaires, created_at, updated_at) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return (int) DatabaseService.executeInsertWithGeneratedKey(query,
                m.getChambreId(),
                m.getEmployeId(),
                m.getTypeMaintenance().name(),
                m.getDescription(),
                m.getPriorite().name(),
                m.getStatut().name(),
                toTimestamp(m.getDatePlanifiee()),
                toTimestamp(m.getDateDebut()),
                toTimestamp(m.getDateFin()),
                m.getCout(),
                m.getCommentaires(),
                toTimestamp(m.getCreatedAt()),
                toTimestamp(m.getUpdatedAt())
        );
    }

    public static Maintenance findById(int id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM maintenance WHERE id = ?";
        ResultSet rs = DatabaseService.executeQuery(query, id);
        if (rs.next()) {
            return mapResultSetToMaintenance(rs);
        }
        return null;
    }

    public static List<Maintenance> findAll() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM maintenance ORDER BY date_planifiee DESC";
        ResultSet rs = DatabaseService.executeQuery(query);

        List<Maintenance> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapResultSetToMaintenance(rs));
        }
        return list;
    }

    public static List<Maintenance> findByChambreId(int chambreId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM maintenance WHERE chambre_id = ?";
        ResultSet rs = DatabaseService.executeQuery(query, chambreId);

        List<Maintenance> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapResultSetToMaintenance(rs));
        }
        return list;
    }

    public static boolean update(Maintenance m) throws SQLException, ClassNotFoundException {
        String query = "UPDATE maintenance SET chambre_id = ?, employe_id = ?, type_maintenance = ?, description = ?, " +
                       "priorite = ?, statut = ?, date_planifiee = ?, date_debut = ?, date_fin = ?, cout = ?, commentaires = ?, " +
                       "created_at = ?, updated_at = ? WHERE id = ?";

        int rows = DatabaseService.executeUpdate(query,
                m.getChambreId(),
                m.getEmployeId(),
                m.getTypeMaintenance().name(),
                m.getDescription(),
                m.getPriorite().name(),
                m.getStatut().name(),
                toTimestamp(m.getDatePlanifiee()),
                toTimestamp(m.getDateDebut()),
                toTimestamp(m.getDateFin()),
                m.getCout(),
                m.getCommentaires(),
                toTimestamp(m.getCreatedAt()),
                toTimestamp(m.getUpdatedAt()),
                m.getId()
        );

        return rows > 0;
    }

    public static boolean delete(int id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM maintenance WHERE id = ?";
        int rows = DatabaseService.executeUpdate(query, id);
        return rows > 0;
    }

    private static Maintenance mapResultSetToMaintenance(ResultSet rs) throws SQLException {
        Maintenance m = new Maintenance();
        m.setId(rs.getInt("id"));
        m.setChambreId(rs.getInt("chambre_id"));
        int employeId = rs.getInt("employe_id");
        m.setEmployeId(rs.wasNull() ? null : employeId);
        m.setTypeMaintenance(TypeMaintenance.valueOf(rs.getString("type_maintenance")));
        m.setDescription(rs.getString("description"));
        m.setPriorite(PrioriteMaintenance.valueOf(rs.getString("priorite")));
        m.setStatut(StatutMaintenance.valueOf(rs.getString("statut")));
        m.setDatePlanifiee(toLocalDateTime(rs.getTimestamp("date_planifiee")));
        m.setDateDebut(toLocalDateTime(rs.getTimestamp("date_debut")));
        m.setDateFin(toLocalDateTime(rs.getTimestamp("date_fin")));
        m.setCout(rs.getDouble("cout"));
        m.setCommentaires(rs.getString("commentaires"));
        m.setCreatedAt(toLocalDateTime(rs.getTimestamp("created_at")));
        m.setUpdatedAt(toLocalDateTime(rs.getTimestamp("updated_at")));
        return m;
    }

    private static Timestamp toTimestamp(LocalDateTime dateTime) {
        return dateTime != null ? Timestamp.valueOf(dateTime) : null;
    }

    private static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }
}
