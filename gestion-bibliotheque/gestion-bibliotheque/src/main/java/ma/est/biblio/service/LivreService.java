package ma.est.biblio.service;

import ma.est.biblio.dao.LivreDAO;
import ma.est.biblio.dao.LivreDAOImpl;
import ma.est.biblio.model.Livre;
import ma.est.biblio.util.DBConnection;
import ma.est.biblio.util.LogUtil;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class LivreService {

    private LivreDAO dao = new LivreDAOImpl();


    public void ajouterLivre(Livre livre) {
        dao.save(livre);
    }

    public List<Livre> getLivres() {
        return dao.findAll();
    }

    public void supprimerLivre(String isbn) {
        dao.delete(isbn);
        LogUtil.log("SUPPRESSION LIVRE", "ISBN=" + isbn);
    
    }


    public void importerCSV(String path) {

    	String sql = "INSERT INTO livre\n" +
                "VALUES (?, ?, ?, ?, ?, ?)\n" +
                "ON DUPLICATE KEY UPDATE isbn = isbn";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            Connection c = DBConnection.getConnection();
            String line;

            br.readLine(); 

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                PreparedStatement ps = c.prepareStatement(sql);
                ps.setString(1, data[0]);
                ps.setString(2, data[1]); 
                ps.setString(3, data[2]);
                ps.setString(4, data[3]); 
                ps.setInt(5, Integer.parseInt(data[4]));
                ps.setInt(6, Integer.parseInt(data[5])); 
                ps.executeUpdate();
            }

        } catch (Exception e) {
            throw new RuntimeException("Erreur import CSV");
        }
    }
}
