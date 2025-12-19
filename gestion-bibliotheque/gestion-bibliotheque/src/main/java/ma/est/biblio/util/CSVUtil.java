package ma.est.biblio.util;

import ma.est.biblio.model.Livre;

import java.io.*;
import java.util.List;

public class CSVUtil {

  
    public static void exportLivres(List<Livre> livres, String path) {

        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {

            pw.println("ISBN,TITRE,AUTEUR,CATEGORIE,TOTAL,DISPONIBLE");

            for (Livre l : livres) {
                pw.println(
                        l.getIsbn() + "," +
                        l.getTitre() + "," +
                        l.getAuteur() + "," +
                        l.getCategorie() + "," +
                        l.getNbExemplairesTotal() + "," +
                        l.getNbExemplairesDisponibles()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
