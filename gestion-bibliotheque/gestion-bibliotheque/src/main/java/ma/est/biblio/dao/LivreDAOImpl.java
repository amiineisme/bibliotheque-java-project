package ma.est.biblio.dao;

import ma.est.biblio.model.Livre;
import ma.est.biblio.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreDAOImpl implements LivreDAO {

    @Override
    public void save(Livre livre) {
        String sql = "INSERT INTO livre VALUES (?,?,?,?,?,?)";

        try {
            Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, livre.getIsbn());
            ps.setString(2, livre.getTitre());
            ps.setString(3, livre.getAuteur());
            ps.setString(4, livre.getCategorie());
            ps.setInt(5, livre.getNbExemplairesTotal());
            ps.setInt(6, livre.getNbExemplairesDisponibles());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Livre> findAll() {
        List<Livre> livres = new ArrayList<>();
        String sql = "SELECT * FROM livre";

        try {
            Connection c = DBConnection.getConnection();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                livres.add(new Livre(
                        rs.getString("isbn"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getString("categorie"),
                        rs.getInt("nb_exemplaires_total"),
                        rs.getInt("nb_exemplaires_disponibles")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return livres;
    }

    @Override
    public void delete(String isbn) {
        String sql = "DELETE FROM livre WHERE isbn = ?";
        try {
            Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, isbn);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
