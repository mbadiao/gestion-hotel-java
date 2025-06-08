package dao;

import models.ReservationChambre;
import models.Chambre;

import java.sql.*;
import java.util.*;

public class ReservationChambreDAO {

    public static List<ReservationChambre> getByReservationId(int reservationId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM reservation_chambre WHERE reservation_id = ?";
        ResultSet rs = DatabaseService.executeQuery(query, reservationId);

        List<ReservationChambre> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapResultSetToReservationChambre(rs));
        }

        return list;
    }

    public static int insert(ReservationChambre rc) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO reservation_chambre (reservation_id, chambre_id, prix_nuit, created_at) VALUES (?, ?, ?, ?)";
        long id = DatabaseService.executeInsertWithGeneratedKey(query,
                rc.getReservationId(),
                rc.getChambreId(),
                rc.getPrixNuit(),
                Timestamp.valueOf(rc.getCreatedAt())
        );
        rc.setId((int) id);
        return (int) id;
    }

    public static boolean deleteByReservationId(int reservationId) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM reservation_chambre WHERE reservation_id = ?";
        int rows = DatabaseService.executeUpdate(query, reservationId);
        return rows > 0;
    }

    private static ReservationChambre mapResultSetToReservationChambre(ResultSet rs) throws SQLException, ClassNotFoundException {
        ReservationChambre rc = new ReservationChambre();
        rc.setId(rs.getInt("id"));
        rc.setReservationId(rs.getInt("reservation_id"));
        rc.setChambreId(rs.getInt("chambre_id"));
        rc.setPrixNuit(rs.getBigDecimal("prix_nuit"));
        rc.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

        // Chargement de la chambre liée
        Chambre chambre = ChambreDAO.findById((rs.getInt("chambre_id")));
        rc.setChambre(chambre);

        return rc;
    }
}
