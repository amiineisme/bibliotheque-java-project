package ma.est.biblio;

import ma.est.biblio.util.DBConnection;

import java.sql.Connection;

public class TestDB {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            System.out.println("✅ Connexion MySQL réussie !");
        } catch (Exception e) {
            System.err.println("❌ Erreur de connexion");
            e.printStackTrace();
        }
    }
}
