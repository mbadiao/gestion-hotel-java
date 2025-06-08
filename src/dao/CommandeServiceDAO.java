package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import enums.Enums.StatutCommande;
import models.CommandeService;

class CommandeServiceDAO {

    public CommandeService findById(int id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM commande_service WHERE id = ?";
        ResultSet rs = DatabaseService.executeQuery(query, id);

        if (rs.next()) {
            return mapResultSetToCommandeService(rs);
        }
        return null;
    }

    public List<CommandeService> findAll() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM commande_service";
        ResultSet rs = DatabaseService.executeQuery(query);

        List<CommandeService> commandes = new ArrayList<>();
        while (rs.next()) {
            commandes.add(mapResultSetToCommandeService(rs));
        }
        return commandes;
    }

    public long insert(CommandeService commande) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO commande_service (reservation_id, service_id, quantite, prix_unitaire, montant_total, statut, date_commande, date_livraison, commentaires) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return DatabaseService.executeInsertWithGeneratedKey(query,
                commande.getReservationId(),
                commande.getServiceId(),
                commande.getQuantite(),
                commande.getPrixUnitaire(),
                commande.getMontantTotal(),
                commande.getStatut().name(),
                commande.getDateCommande(),
                commande.getDateLivraison(),
                commande.getCommentaires()
        );
    }

    public int update(CommandeService commande) throws SQLException, ClassNotFoundException {
        String query = "UPDATE commande_service SET reservation_id = ?, service_id = ?, quantite = ?, prix_unitaire = ?, montant_total = ?, statut = ?, date_commande = ?, date_livraison = ?, commentaires = ? WHERE id = ?";
        return DatabaseService.executeUpdate(query,
                commande.getReservationId(),
                commande.getServiceId(),
                commande.getQuantite(),
                commande.getPrixUnitaire(),
                commande.getMontantTotal(),
                commande.getStatut().name(),
                commande.getDateCommande(),
                commande.getDateLivraison(),
                commande.getCommentaires(),
                commande.getId()
        );
    }

    public int delete(int id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM commande_service WHERE id = ?";
        return DatabaseService.executeUpdate(query, id);
    }

    private CommandeService mapResultSetToCommandeService(ResultSet rs) throws SQLException {
        CommandeService commande = new CommandeService();
        commande.setId(rs.getInt("id"));
        commande.setReservationId(rs.getInt("reservation_id"));
        commande.setServiceId(rs.getInt("service_id"));
        commande.setQuantite(rs.getInt("quantite"));
        commande.setPrixUnitaire(rs.getDouble("prix_unitaire"));
        commande.setMontantTotal(rs.getDouble("montant_total"));
        commande.setStatut(StatutCommande.valueOf(rs.getString("statut")));
        commande.setDateCommande(rs.getTimestamp("date_commande").toLocalDateTime());
        commande.setDateLivraison(rs.getTimestamp("date_livraison").toLocalDateTime());
        commande.setCommentaires(rs.getString("commentaires"));
        return commande;
    }
}
