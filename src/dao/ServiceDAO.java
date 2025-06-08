package dao;

import enums.Enums.CategorieService;
import models.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {

    public static Service findById(int id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM service WHERE id = ?";
        ResultSet rs = DatabaseService.executeQuery(query, id);

        if (rs.next()) {
            return mapResultSetToService(rs);
        }
        return null;
    }

    public static List<Service> findAll() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM service";
        ResultSet rs = DatabaseService.executeQuery(query);

        List<Service> services = new ArrayList<>();
        while (rs.next()) {
            services.add(mapResultSetToService(rs));
        }
        return services;
    }

    public static long insert(Service service) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO service (nom, description, prix, categorie, disponible, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return DatabaseService.executeInsertWithGeneratedKey(query,
                service.getNom(),
                service.getDescription(),
                service.getPrix(),
                service.getCategorie().name(),
                service.isDisponible(),
                service.getCreatedAt(),
                service.getUpdatedAt()
        );
    }

    public static int update(Service service) throws SQLException, ClassNotFoundException {
        String query = "UPDATE service SET nom = ?, description = ?, prix = ?, categorie = ?, disponible = ?, updated_at = ? WHERE id = ?";
        return DatabaseService.executeUpdate(query,
                service.getNom(),
                service.getDescription(),
                service.getPrix(),
                service.getCategorie().name(),
                service.isDisponible(),
                service.getUpdatedAt(),
                service.getId()
        );
    }

    public static int delete(int id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM service WHERE id = ?";
        return DatabaseService.executeUpdate(query, id);
    }

    private static Service mapResultSetToService(ResultSet rs) throws SQLException {
        Service service = new Service();
        service.setId(rs.getInt("id"));
        service.setNom(rs.getString("nom"));
        service.setDescription(rs.getString("description"));
        service.setPrix(rs.getBigDecimal("prix"));
        service.setCategorie(CategorieService.valueOf(rs.getString("categorie")));
        service.setDisponible(rs.getBoolean("disponible"));
        service.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        service.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return service;
    }
}
