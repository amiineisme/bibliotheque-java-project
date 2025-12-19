package ma.est.biblio.dao;

import ma.est.biblio.model.Utilisateur;

public interface UtilisateurDAO {
    Utilisateur findByLogin(String login);
    void save(Utilisateur utilisateur); 
}
