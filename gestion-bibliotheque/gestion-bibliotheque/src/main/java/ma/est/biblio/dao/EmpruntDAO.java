package ma.est.biblio.dao;

import ma.est.biblio.model.Emprunt;
import java.util.List;

public interface EmpruntDAO {
    void save(Emprunt e);
    void cloturer(int empruntId);
    List<Emprunt> findAll();
}
