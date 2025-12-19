package ma.est.biblio.service;

import ma.est.biblio.dao.EmpruntDAO;
import ma.est.biblio.dao.EmpruntDAOImpl;
import ma.est.biblio.util.DBConnection;

import java.sql.*;
import java.time.LocalDate;

public class EmpruntService {

    private EmpruntDAO dao = new EmpruntDAOImpl();

    
    public void emprunter(int adherentId, String isbn) {

        try {
            Connection c = DBConnection.getConnection();

         
            PreparedStatement ps0 = c.prepareStatement(
                    "SELECT bloque FROM adherent WHERE id=?"
            );
            ps0.setInt(1, adherentId);
            ResultSet rs0 = ps0.executeQuery();

            if (rs0.next() && rs0.getBoolean("bloque")) {
                throw new RuntimeException("Adhérent bloqué pour retard");
            }

       
            PreparedStatement ps1 = c.prepareStatement(
                    "SELECT nb_exemplaires_disponibles FROM livre WHERE isbn=?"
            );
            ps1.setString(1, isbn);
            ResultSet rs1 = ps1.executeQuery();

            if (!rs1.next() || rs1.getInt(1) <= 0) {
                throw new RuntimeException("Livre indisponible");
            }

            PreparedStatement ps2 = c.prepareStatement(
                    "SELECT COUNT(*) FROM emprunt WHERE adherent_id=? AND statut='EN_COURS'"
            );
            ps2.setInt(1, adherentId);
            ResultSet rs2 = ps2.executeQuery();
            rs2.next();

            if (rs2.getInt(1) >= 3) {
                throw new RuntimeException("Limite de 3 emprunts atteinte");
            }

         
            dao.save(new ma.est.biblio.model.Emprunt(
                    adherentId,
                    isbn,
                    LocalDate.now(),
                    LocalDate.now().plusDays(14)
            ));

            PreparedStatement ps3 = c.prepareStatement(
                    "UPDATE livre SET nb_exemplaires_disponibles = nb_exemplaires_disponibles - 1 WHERE isbn=?"
            );
            ps3.setString(1, isbn);
            ps3.executeUpdate();

            
            verifierRetardsEtBloquer();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

  
    public void retourner(int empruntId, String isbn) {

        try {
         
            dao.cloturer(empruntId);

            Connection c = DBConnection.getConnection();

            PreparedStatement ps = c.prepareStatement(
                    "UPDATE livre SET nb_exemplaires_disponibles = nb_exemplaires_disponibles + 1 WHERE isbn=?"
            );
            ps.setString(1, isbn);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du retour");
        }
    }

    
    public void verifierRetardsEtBloquer() {

    	String sql = "UPDATE adherent\n" +
                "SET bloque = TRUE\n" +
                "WHERE id IN (\n" +
                "    SELECT adherent_id\n" +
                "    FROM emprunt\n" +
                "    WHERE statut = 'EN_COURS'\n" +
                "    AND DATEDIFF(CURDATE(), date_retour_prevue) > 10\n" +
                ")";
        try {
            Connection c = DBConnection.getConnection();
            Statement st = c.createStatement();
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
