package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import enums.Enums.StatutChambre;
import models.Chambre;

public class ChambreDAO {
    private Connection conn;

    public ChambreDAO() throws SQLException, ClassNotFoundException {
        conn = DatabaseService.getConnection();
    }

    public List<Chambre> getAllChambres() throws Exception, ClassNotFoundException {
        List<Chambre> chambres = new ArrayList<>();
        String sql = "SELECT * FROME chambre";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Chambre c = new Chambre(
                    rs.getInt("id"),
                    rs.getString("numero"),
                    rs.getInt("etage"),
                    rs.getInt("type_chambre_id"),
                    StatutChambre.valueOf(rs.getString("statut").toUpperCase()),
                    rs.getString("description")
                );
                chambres.add(c);
            }
        return chambres;
    }

    public Chambre getChambreById(int id)  throws Exception, ClassNotFoundException {
        String sql = "SELECT * FROM chambre WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            return new Chambre(
                    rs.getInt("id"),
                    rs.getString("numero"),
                    rs.getInt("etage"),
                    rs.getInt("type_chambre_id"),
                    StatutChambre.valueOf(rs.getString("statut").toUpperCase()),
                    rs.getString("description")
                );
        }
        return null;
    }

     public boolean addClient(Chambre c) throws Exception, ClassNotFoundException  {
        String sql = "INSERT INTO chambre (numero, etage, type_chambre_id, statut, description) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c.getNumero());
            ps.setInt(2, c.getEtage());
            ps.setInt(3, c.getTypeChambreId());
            ps.setString(4,  c.getStatut().name());
            ps.setString(5, c.getDescription());
            return ps.executeUpdate() > 0;
    }

    public boolean updateClient(Chambre c) throws Exception, ClassNotFoundException {
        String sql = "UPDATE client SET nom=?, prenom=?, email=?, telephone=?, adresse=?, date_naissance=?, nationalite=?, numero_passeport=?, type_client=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
             ps.setString(1, c.getNumero());
            ps.setInt(2, c.getEtage());
            ps.setInt(3, c.getTypeChambreId());
            ps.setString(4,  c.getStatut().name());
            ps.setString(5, c.getDescription());
            return ps.executeUpdate() > 0;
    }

    public boolean deleteChambre(int id) throws Exception, ClassNotFoundException  {
        String sql = "DELETE FROM chambre WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    }
}
