package ma.est.biblio.dao;

import ma.est.biblio.model.Adherent;
import java.util.List;

public interface AdherentDAO {
    void save(Adherent a);
    List<Adherent> findAll();
    void delete(int id);
}
