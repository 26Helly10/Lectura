package proiect.proiect_java.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Carte")
public class Carte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCarte;
    private String numeCarte;
    //private String numeAutor;
    private String genCarte;
    private String editura;
    private int steluteCarte;
    private String citit;
    private String inBiblioteca;

    @ManyToOne()
    @JoinColumn(name = "idAutor")
    private Autor autor;

    @ManyToOne()
    @JoinColumn(name = "idUtilizator")
    private Utilizator utilizator;


    public Carte(long idCarte, String numeCarte, String genCarte, String editura, int steluteCarte, String citit, String inBiblioteca, Autor autor, Utilizator utilizator) {
        this.idCarte = idCarte;
        this.numeCarte = numeCarte;
        this.genCarte = genCarte;
        this.editura = editura;
        this.steluteCarte = steluteCarte;
        this.citit = citit;
        this.inBiblioteca = inBiblioteca;
        this.autor = autor;
        this.utilizator = utilizator;
    }

    public Carte() {
    }

    public Carte(String numeCarte, String genCarte, String editura, int steluteCarte, String citit, String inBiblioteca, Autor autor, Utilizator utilizator) {
        this.numeCarte = numeCarte;
        this.genCarte = genCarte;
        this.editura = editura;
        this.steluteCarte = steluteCarte;
        this.citit = citit;
        this.inBiblioteca = inBiblioteca;
        this.autor = autor;
        this.utilizator=utilizator;
    }

    public long getIdCarte() {
        return idCarte;
    }

    public void setIdCarte(long idCarte) {
        this.idCarte = idCarte;
    }

    public String getNumeCarte() {
        return numeCarte;
    }

    public void setNumeCarte(String numeCarte) {
        this.numeCarte = numeCarte;
    }

    public String getGenCarte() {
        return genCarte;
    }

    public void setGenCarte(String genCarte) {
        this.genCarte = genCarte;
    }

    public String getEditura() {
        return editura;
    }

    public void setEditura(String editura) {
        this.editura = editura;
    }

    public int getSteluteCarte() {
        return steluteCarte;
    }

    public void setSteluteCarte(int steluteCarte) {
        this.steluteCarte = steluteCarte;
    }

    public String getCitit() {
        return citit;
    }

    public void setCitit(String citit) {
        this.citit = citit;
    }

    public String getInBiblioteca() {
        return inBiblioteca;
    }

    public void setInBiblioteca(String inBiblioteca) {
        this.inBiblioteca = inBiblioteca;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }
}
