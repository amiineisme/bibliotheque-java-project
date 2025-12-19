package ma.est.biblio.model;

public class Adherent {

    private int id;
    private String nom;
    private String email;
    private boolean bloque;

    public Adherent(int id, String nom, String email, boolean bloque) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.bloque = bloque;
    }

    public Adherent(String nom, String email) {
        this.nom = nom;
        this.email = email;
        this.bloque = false;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getEmail() { return email; }
    public boolean isBloque() { return bloque; }
}
