package dao;

import enums.Enums.TypeClient;
import models.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    public Client findById(int id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM client WHERE id = ?";
        ResultSet rs = DatabaseService.executeQuery(query, id);

        if (rs.next()) {
            return mapResultSetToClient(rs);
        }
        return null;
    }

    public List<Client> findAll() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM client";
        ResultSet rs = DatabaseService.executeQuery(query);

        List<Client> clients = new ArrayList<>();
        while (rs.next()) {
            clients.add(mapResultSetToClient(rs));
        }
        return clients;
    }

    public long insert(Client client) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO client (nom, prenom, email, telephone, adresse, date_naissance, nationalite, numero_passeport, type_client, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return DatabaseService.executeInsertWithGeneratedKey(query,
                client.getNom(),
                client.getPrenom(),
                client.getEmail(),
                client.getTelephone(),
                client.getAdresse(),
                client.getDateNaissance(),
                client.getNationalite(),
                client.getNumeroPasseport(),
                client.getTypeClient().name(),
                client.getCreatedAt(),
                client.getUpdatedAt()
        );
    }

    public int update(Client client) throws SQLException, ClassNotFoundException {
        String query = "UPDATE client SET nom = ?, prenom = ?, email = ?, telephone = ?, adresse = ?, date_naissance = ?, nationalite = ?, numero_passeport = ?, type_client = ?, updated_at = ? WHERE id = ?";
        return DatabaseService.executeUpdate(query,
                client.getNom(),
                client.getPrenom(),
                client.getEmail(),
                client.getTelephone(),
                client.getAdresse(),
                client.getDateNaissance(),
                client.getNationalite(),
                client.getNumeroPasseport(),
                client.getTypeClient().name(),
                client.getUpdatedAt(),
                client.getId()
        );
    }

    public int delete(int id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM client WHERE id = ?";
        return DatabaseService.executeUpdate(query, id);
    }

    private Client mapResultSetToClient(ResultSet rs) throws SQLException {
        Client client = new Client();
        client.setId(rs.getInt("id"));
        client.setNom(rs.getString("nom"));
        client.setPrenom(rs.getString("prenom"));
        client.setEmail(rs.getString("email"));
        client.setTelephone(rs.getString("telephone"));
        client.setAdresse(rs.getString("adresse"));
        client.setDateNaissance(rs.getDate("date_naissance").toLocalDate());
        client.setNationalite(rs.getString("nationalite"));
        client.setNumeroPasseport(rs.getString("numero_passeport"));
        client.setTypeClient(TypeClient.valueOf(rs.getString("type_client")));
        client.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        client.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return client;
    }
}
