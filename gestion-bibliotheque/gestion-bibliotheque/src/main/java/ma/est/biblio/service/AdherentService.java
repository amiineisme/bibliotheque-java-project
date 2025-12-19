package ma.est.biblio.service;

import ma.est.biblio.dao.AdherentDAO;
import ma.est.biblio.dao.AdherentDAOImpl;
import ma.est.biblio.model.Adherent;

import java.util.List;

public class AdherentService {

    private AdherentDAO dao = new AdherentDAOImpl();

    public void ajouterAdherent(Adherent a) {
        dao.save(a);
    }

    public List<Adherent> getAdherents() {
        return dao.findAll();
    }

    public void supprimerAdherent(int id) {
        dao.delete(id);
    }
}
