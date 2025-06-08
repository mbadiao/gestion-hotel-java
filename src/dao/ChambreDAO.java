package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import enums.Enums.StatutChambre;
import models.Chambre;

public class ChambreDAO {

    public static Chambre findById(int id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM chambre WHERE id = ?";
        ResultSet rs = DatabaseService.executeQuery(query, id);

        if (rs.next()) {
            return mapResultSetToChambre(rs);
        }
        return null;
    }

    public static List<Chambre> findAll() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM chambre";
        ResultSet rs = DatabaseService.executeQuery(query);

        List<Chambre> chambres = new ArrayList<>();
        while (rs.next()) {
            chambres.add(mapResultSetToChambre(rs));
        }
        return chambres;
    }

    public static long insert(Chambre chambre) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO chambre (numero, etage, type_chambre_id, statut, description, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return DatabaseService.executeInsertWithGeneratedKey(query,
                chambre.getNumero(),
                chambre.getEtage(),
                chambre.getTypeChambreId(),
                chambre.getStatut().name(),
                chambre.getDescription(),
                chambre.getCreatedAt(),
                chambre.getUpdatedAt()
        );
    }

    public static int update(Chambre chambre) throws SQLException, ClassNotFoundException {
        String query = "UPDATE chambre SET numero = ?, etage = ?, type_chambre_id = ?, statut = ?, description = ?, updated_at = ? WHERE id = ?";
        return DatabaseService.executeUpdate(query,
                chambre.getNumero(),
                chambre.getEtage(),
                chambre.getTypeChambreId(),
                chambre.getStatut().name(),
                chambre.getDescription(),
                chambre.getUpdatedAt(),
                chambre.getId()
        );
    }

    public static int delete(int id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM chambre WHERE id = ?";
        return DatabaseService.executeUpdate(query, id);
    }

    private static Chambre mapResultSetToChambre(ResultSet rs) throws SQLException {
        Chambre chambre = new Chambre();
        chambre.setId(rs.getInt("id"));
        chambre.setNumero(rs.getString("numero"));
        chambre.setEtage(rs.getInt("etage"));
        chambre.setTypeChambreId(rs.getInt("type_chambre_id"));
        chambre.setStatut(StatutChambre.valueOf(rs.getString("statut")));
        chambre.setDescription(rs.getString("description"));
        chambre.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        chambre.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return chambre;
    }
}