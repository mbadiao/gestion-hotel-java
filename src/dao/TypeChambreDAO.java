package dao;

import models.TypeChambre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeChambreDAO {

    public static  TypeChambre findById(int id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM type_chambre WHERE id = ?";
        ResultSet rs = DatabaseService.executeQuery(query, id);

        if (rs.next()) {
            return mapResultSetToTypeChambre(rs);
        }
        return null;
    }

    public static  List<TypeChambre> findAll() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM type_chambre";
        ResultSet rs = DatabaseService.executeQuery(query);

        List<TypeChambre> types = new ArrayList<>();
        while (rs.next()) {
            types.add(mapResultSetToTypeChambre(rs));
        }
        return types;
    }

    public static  long insert(TypeChambre typeChambre) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO type_chambre (nom, description, prix_base, capacite_max, equipements, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return DatabaseService.executeInsertWithGeneratedKey(query,
                typeChambre.getNom(),
                typeChambre.getDescription(),
                typeChambre.getPrixBase(),
                typeChambre.getCapaciteMax(),
                typeChambre.getEquipements(),
                typeChambre.getCreatedAt(),
                typeChambre.getUpdatedAt()
        );
    }

    public static int update(TypeChambre typeChambre) throws SQLException, ClassNotFoundException {
        String query = "UPDATE type_chambre SET nom = ?, description = ?, prix_base = ?, capacite_max = ?, equipements = ?, updated_at = ? WHERE id = ?";
        return DatabaseService.executeUpdate(query,
                typeChambre.getNom(),
                typeChambre.getDescription(),
                typeChambre.getPrixBase(),
                typeChambre.getCapaciteMax(),
                typeChambre.getEquipements(),
                typeChambre.getUpdatedAt(),
                typeChambre.getId()
        );
    }

    public static int delete(int id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM type_chambre WHERE id = ?";
        return DatabaseService.executeUpdate(query, id);
    }

    private static TypeChambre mapResultSetToTypeChambre(ResultSet rs) throws SQLException {
        TypeChambre type = new TypeChambre();
        type.setId(rs.getInt("id"));
        type.setNom(rs.getString("nom"));
        type.setDescription(rs.getString("description"));
        type.setPrixBase(rs.getBigDecimal("prix_base"));
        type.setCapaciteMax(rs.getInt("capacite_max"));
        type.setEquipements(rs.getString("equipements"));
        type.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        type.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return type;
    }
}
