package ma.est.biblio.dao;

import ma.est.biblio.model.Utilisateur;
import ma.est.biblio.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UtilisateurDAOImpl implements UtilisateurDAO {

    @Override
    public void save(Utilisateur u) {

        String sql = "INSERT INTO utilisateur(login, password_hash, role, actif) VALUES (?, ?, ?, ?)";

        try (
            Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, u.getLogin());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getRole());
            ps.setInt(4, u.isActif() ? 1 : 0);

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e); 
        }
    }

    @Override
    public Utilisateur findByLogin(String login) {

        String sql = "SELECT * FROM utilisateur WHERE login = ?";

        try (
            Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Utilisateur u = new Utilisateur();
                u.setId(rs.getInt("id"));
                u.setLogin(rs.getString("login"));
                u.setPassword(rs.getString("password_hash"));
                u.setRole(rs.getString("role"));
                u.setActif(rs.getInt("actif") == 1);
                return u;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
