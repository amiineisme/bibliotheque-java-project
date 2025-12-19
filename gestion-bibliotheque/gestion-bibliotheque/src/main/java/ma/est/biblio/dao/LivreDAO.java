package ma.est.biblio.dao;

import ma.est.biblio.model.Livre;
import java.util.List;

public interface LivreDAO {
    void save(Livre livre);
    List<Livre> findAll();
    void delete(String isbn);
}
