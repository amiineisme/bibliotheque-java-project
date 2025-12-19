package ma.est.biblio.service;

import ma.est.biblio.dao.UtilisateurDAO;
import ma.est.biblio.dao.UtilisateurDAOImpl;
import ma.est.biblio.model.Utilisateur;

public class AuthService {

    private UtilisateurDAO userDAO = new UtilisateurDAOImpl();

    public Utilisateur login(String login, String password) {

        Utilisateur user = userDAO.findByLogin(login);

        if (user != null
                && user.isActif()
                && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }
}
