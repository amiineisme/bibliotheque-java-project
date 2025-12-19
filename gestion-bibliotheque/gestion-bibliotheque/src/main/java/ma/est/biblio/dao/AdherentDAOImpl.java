package ma.est.biblio.dao;
import javax.swing.*;

import ma.est.biblio.model.Adherent;
import ma.est.biblio.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdherentDAOImpl implements AdherentDAO {

    @Override
    public void save(Adherent a) {
        String sql = "INSERT INTO adherent (nom, email, bloque) VALUES (?, ?, ?)";

        try {
            Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, a.getNom());
            ps.setString(2, a.getEmail());
            ps.setBoolean(3, a.isBloque());
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Email déjà existant",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Adherent> findAll() {
        List<Adherent> list = new ArrayList<>();
        String sql = "SELECT * FROM adherent";

        try {
            Connection c = DBConnection.getConnection();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                list.add(new Adherent(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getBoolean("bloque")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM adherent WHERE id = ?";

        try {
            Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
