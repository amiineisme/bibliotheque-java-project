package ma.est.biblio.model;

import java.time.LocalDate;

public class Emprunt {

    private int id;
    private int adherentId;
    private String livreIsbn;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetour;
    private String statut;

    public Emprunt(int adherentId, String livreIsbn,
                   LocalDate dateEmprunt,
                   LocalDate dateRetourPrevue) {
        this.adherentId = adherentId;
        this.livreIsbn = livreIsbn;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.statut = "EN_COURS";
    }

    public int getAdherentId() { return adherentId; }
    public String getLivreIsbn() { return livreIsbn; }
    public LocalDate getDateRetourPrevue() { return dateRetourPrevue; }
}
