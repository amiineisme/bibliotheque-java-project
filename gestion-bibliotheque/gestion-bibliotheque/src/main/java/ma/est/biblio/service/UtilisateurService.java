package ma.est.biblio.service;

import ma.est.biblio.dao.UtilisateurDAO;
import ma.est.biblio.dao.UtilisateurDAOImpl;
import ma.est.biblio.model.Utilisateur;
import ma.est.biblio.util.LogUtil;
import ma.est.biblio.util.PasswordUtil;
import java.security.MessageDigest;

public class UtilisateurService {

    private UtilisateurDAO dao = new UtilisateurDAOImpl();
    private final UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl();



    public void inscrireUtilisateur(String email, String password) {

        if (!email.endsWith("@edu.umi.ac.ma")) {
            throw new RuntimeException("Email non valide");
        }

        String hashed = PasswordUtil.hash(password);

        Utilisateur u = new Utilisateur();
        u.setLogin(email);     
        u.setEmail(email);
        u.setPassword(hashed);
        u.setRole("USER");
        u.setActif(true);

        dao.save(u);

        LogUtil.log("INSCRIPTION", "Utilisateur=" + email);
    }

    public void inscrireUtilisateur(String login, String email, String password) {

        if (!email.matches("^[a-zA-Z0-9._%+-]+@edu\\.umi\\.ac\\.ma$")) {
            throw new RuntimeException("Email universitaire obligatoire (@edu.umi.ac.ma)");
        }

        if (utilisateurDAO.findByLogin(login) != null) {
            throw new RuntimeException("Login déjà utilisé");
        }

        Utilisateur u = new Utilisateur();
        u.setLogin(login);
        u.setEmail(email);
        u.setPassword(hashPassword(password)); 
        u.setRole("USER");
        u.setActif(true);

        utilisateurDAO.save(u);
    }
   

    public Utilisateur login(String login, String password, String role) {

        Utilisateur u = dao.findByLogin(login);

        if (u == null) return null;
        if (!u.isActif()) return null;
        if (!u.getRole().equals(role)) return null;

        boolean ok = PasswordUtil.verify(password, u.getPassword());
        if (!ok) return null;

        LogUtil.log("LOGIN", "Utilisateur=" + login + " | Role=" + role);
        return u;
    }
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


