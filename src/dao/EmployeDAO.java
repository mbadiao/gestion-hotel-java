package dao;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import models.Client;

public class EmployeDAO {


    private Connection conn;

    public EmployeDAO()  throws SQLException, ClassNotFoundException {
        conn = DatabaseService.getConnection();
    }

    public List<Client> getAllClients() throws Exception, ClassNotFoundException  {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Client c = new Client(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("email"),
                    rs.getString("telephone"),
                    rs.getString("adresse"),
                    rs.getDate("date_naissance"),
                    rs.getString("nationalite"),
                    rs.getString("numero_passeport"),
                    rs.getString("type_client")
                );
                clients.add(c);
            }
 
        return clients;
    }

    public Client getClientById(int id) throws Exception, ClassNotFoundException  {
        String sql = "SELECT * FROM client WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Client(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("email"),
                    rs.getString("telephone"),
                    rs.getString("adresse"),
                    rs.getDate("date_naissance"),
                    rs.getString("nationalite"),
                    rs.getString("numero_passeport"),
                    rs.getString("type_client")
                );
            }
       
        return null;
    }

    public boolean addClient(Client c) throws Exception, ClassNotFoundException  {
        String sql = "INSERT INTO client (nom, prenom, email, telephone, adresse, date_naissance, nationalite, numero_passeport, type_client) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c.getNom());
            ps.setString(2, c.getPrenom());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getTelephone());
            ps.setString(5, c.getAdresse());
            LocalDate naissance = c.getDateNaissance();
            ps.setDate(6, Date.valueOf(naissance));
            ps.setString(7, c.getNationalite());
            ps.setString(8, c.getNumeroPasseport());
            ps.setString(9, c.getTypeClient().name());
            return ps.executeUpdate() > 0;
    }

    public boolean updateClient(Client c) throws Exception, ClassNotFoundException {
        String sql = "UPDATE client SET nom=?, prenom=?, email=?, telephone=?, adresse=?, date_naissance=?, nationalite=?, numero_passeport=?, type_client=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c.getNom());
            ps.setString(2, c.getPrenom());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getTelephone());
            ps.setString(5, c.getAdresse());
            LocalDate naissance = c.getDateNaissance();
            ps.setDate(6, Date.valueOf(naissance));
            ps.setString(7, c.getNationalite());
            ps.setString(8, c.getNumeroPasseport());
            ps.setString(9, c.getTypeClient().name());
            ps.setInt(10, c.getId());
            return ps.executeUpdate() > 0;
    }

    public boolean deleteClient(int id) throws Exception, ClassNotFoundException  {
        String sql = "DELETE FROM client WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    }

}
