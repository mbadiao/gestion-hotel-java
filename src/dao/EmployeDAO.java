package dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import models.Employe;
import enums.Enums.Departement;
import enums.Enums.StatutEmploye;

public class EmployeDAO {

    public static int create(Employe employe) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO employe (nom, prenom, email, telephone, poste, departement, salaire, date_embauche, statut) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return (int) DatabaseService.executeInsertWithGeneratedKey(query,
                employe.getNom(),
                employe.getPrenom(),
                employe.getEmail(),
                employe.getTelephone(),
                employe.getPoste(),
                employe.getDepartement().name(),
                employe.getSalaire(),
                Date.valueOf(employe.getDateEmbauche()),
                employe.getStatut().name()
        );
    }

    public static Employe findById(int id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM employe WHERE id = ?";
        ResultSet rs = DatabaseService.executeQuery(query, id);

        if (rs.next()) {
            return mapResultSetToEmploye(rs);
        }

        return null;
    }

    public static List<Employe> findAll() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM employe ORDER BY nom ASC";
        ResultSet rs = DatabaseService.executeQuery(query);

        List<Employe> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapResultSetToEmploye(rs));
        }

        return list;
    }

    public static boolean update(Employe employe) throws SQLException, ClassNotFoundException {
        String query = "UPDATE employe SET nom = ?, prenom = ?, email = ?, telephone = ?, poste = ?, departement = ?, salaire = ?, date_embauche = ?, statut = ? " +
                       "WHERE id = ?";
        int rows = DatabaseService.executeUpdate(query,
                employe.getNom(),
                employe.getPrenom(),
                employe.getEmail(),
                employe.getTelephone(),
                employe.getPoste(),
                employe.getDepartement().name(),
                employe.getSalaire(),
                Date.valueOf(employe.getDateEmbauche()),
                employe.getStatut().name(),
                employe.getId()
        );
        return rows > 0;
    }

    public static boolean delete(int id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM employe WHERE id = ?";
        int rows = DatabaseService.executeUpdate(query, id);
        return rows > 0;
    }

    public static List<Employe> findByStatut(StatutEmploye statut) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM employe WHERE statut = ?";
        ResultSet rs = DatabaseService.executeQuery(query, statut.name());

        List<Employe> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapResultSetToEmploye(rs));
        }

        return list;
    }

    private static Employe mapResultSetToEmploye(ResultSet rs) throws SQLException {
        Employe emp = new Employe();
        emp.setId(rs.getInt("id"));
        emp.setNom(rs.getString("nom"));
        emp.setPrenom(rs.getString("prenom"));
        emp.setEmail(rs.getString("email"));
        emp.setTelephone(rs.getString("telephone"));
        emp.setPoste(rs.getString("poste"));
        emp.setDepartement(Departement.valueOf(rs.getString("departement")));
        emp.setSalaire(rs.getBigDecimal("salaire"));
        emp.setDateEmbauche(rs.getDate("date_embauche").toLocalDate());
        emp.setStatut(StatutEmploye.valueOf(rs.getString("statut")));
        
        Timestamp createdAt = rs.getTimestamp("created_at");
        Timestamp updatedAt = rs.getTimestamp("updated_at");
        if (createdAt != null) emp.setCreatedAt(createdAt.toLocalDateTime());
        if (updatedAt != null) emp.setUpdatedAt(updatedAt.toLocalDateTime());

        return emp;
    }
}
