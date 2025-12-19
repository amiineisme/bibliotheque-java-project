package ma.est.biblio.dao;

import ma.est.biblio.model.Emprunt;
import ma.est.biblio.util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpruntDAOImpl implements EmpruntDAO {

    @Override
    public void save(Emprunt e) {
    	String sql = "INSERT INTO emprunt\n" +
                "(adherent_id, livre_isbn, date_emprunt, date_retour_prevue, statut)\n" +
                "VALUES (?, ?, ?, ?, ?)";
        try {
            Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, e.getAdherentId());
            ps.setString(2, e.getLivreIsbn());
            ps.setDate(3, Date.valueOf(LocalDate.now()));
            ps.setDate(4, Date.valueOf(e.getDateRetourPrevue()));
            ps.setString(5, "EN_COURS");
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void cloturer(int id) {
        String sql = "UPDATE emprunt SET date_retour = ?, statut = ? WHERE id = ?";
        try {
            Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setString(2, "RETOURNE");
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Emprunt> findAll() {
        List<Emprunt> list = new ArrayList<>();
        String sql = "SELECT * FROM emprunt";

        try {
            Connection c = DBConnection.getConnection();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                list.add(new Emprunt(
                        rs.getInt("adherent_id"),
                        rs.getString("livre_isbn"),
                        rs.getDate("date_emprunt").toLocalDate(),
                        rs.getDate("date_retour_prevue").toLocalDate()
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
