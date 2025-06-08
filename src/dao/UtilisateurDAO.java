package dao;


import models.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO {

    public static Utilisateur findById(int id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM utilisateur WHERE id = ?";
        ResultSet rs = DatabaseService.executeQuery(query, id);

        if (rs.next()) {
            return mapResultSetToUtilisateur(rs);
        }
        return null;
    }

    public static List<Utilisateur> findAll() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM utilisateur";
        ResultSet rs = DatabaseService.executeQuery(query);

        List<Utilisateur> utilisateurs = new ArrayList<>();
        while (rs.next()) {
            utilisateurs.add(mapResultSetToUtilisateur(rs));
        }
        return utilisateurs;
    }

    public static long insert(Utilisateur utilisateur) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO utilisateur (email, mot_de_passe, role, client_id, employe_id, actif, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return DatabaseService.executeInsertWithGeneratedKey(query,
                utilisateur.getEmail(),
                utilisateur.getMotDePasse(),
                utilisateur.getRole(),
                utilisateur.getClientId(),
                utilisateur.getEmployeId(),
                utilisateur.isActif(),
                utilisateur.getCreatedAt(),
                utilisateur.getUpdatedAt()
        );
    }

    public static int update(Utilisateur utilisateur) throws SQLException, ClassNotFoundException {
        String query = "UPDATE utilisateur SET email = ?, mot_de_passe = ?, role = ?, client_id = ?, employe_id = ?, actif = ?, updated_at = ? WHERE id = ?";
        return DatabaseService.executeUpdate(query,
                utilisateur.getEmail(),
                utilisateur.getMotDePasse(),
                utilisateur.getRole(),
                utilisateur.getClientId(),
                utilisateur.getEmployeId(),
                utilisateur.isActif(),
                utilisateur.getUpdatedAt(),
                utilisateur.getId()
        );
    }

    public static int delete(int id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM utilisateur WHERE id = ?";
        return DatabaseService.executeUpdate(query, id);
    }

    private static Utilisateur mapResultSetToUtilisateur(ResultSet rs) throws SQLException {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(rs.getInt("id"));
        utilisateur.setEmail(rs.getString("email"));
        utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
        utilisateur.setRole(rs.getString("role"));
        utilisateur.setClientId(rs.getObject("client_id") != null ? rs.getInt("client_id") : null);
        utilisateur.setEmployeId(rs.getObject("employe_id") != null ? rs.getInt("employe_id") : null);
        utilisateur.setActif(rs.getBoolean("actif"));
        utilisateur.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        utilisateur.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return utilisateur;
    }
}
