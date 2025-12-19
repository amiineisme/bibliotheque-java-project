package ma.est.biblio.model;

public class Livre {

    private String isbn;
    private String titre;
    private String auteur;
    private String categorie;
    private int nbExemplairesTotal;
    private int nbExemplairesDisponibles;

    

    public Livre() {
    }

    public Livre(String isbn, String titre, String auteur,
                 String categorie, int total, int disponibles) {

        this.isbn = isbn;
        this.titre = titre;
        this.auteur = auteur;
        this.categorie = categorie;
        this.nbExemplairesTotal = total;
        this.nbExemplairesDisponibles = disponibles;
    }

   

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getNbExemplairesTotal() {
        return nbExemplairesTotal;
    }

    public void setNbExemplairesTotal(int nbExemplairesTotal) {
        this.nbExemplairesTotal = nbExemplairesTotal;
    }

    public int getNbExemplairesDisponibles() {
        return nbExemplairesDisponibles;
    }

    public void setNbExemplairesDisponibles(int nbExemplairesDisponibles) {
        this.nbExemplairesDisponibles = nbExemplairesDisponibles;
    }

  

    public boolean estDisponible() {
        return nbExemplairesDisponibles > 0;
    }

    @Override
    public String toString() {
        return titre + " (" + isbn + ")";
    }
}
