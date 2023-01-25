package proiect.proiect_java.dto;

import jakarta.validation.constraints.*;
import proiect.proiect_java.model.Autor;
import proiect.proiect_java.model.Utilizator;

public class CreateCarteDto {

    @NotBlank
    @Size(max= 400)
    private String numeCarte;
    //private String numeAutor;
    @NotBlank
    @Size(max= 300)
    private String genCarte;
    @NotBlank
    @Size(max=150)
    private String editura;
    @Min(value = 0)
    @Max(value = 5)
    private int steluteCarte;
    @NotBlank
    @Size(max=3)
    private String citit;
    @NotBlank
    @Size(max = 3)
    private String inBiblioteca;

    @NotNull
    private Autor autor;

    @NotNull
    private Utilizator utilizator;

    public CreateCarteDto() {
    }

    public CreateCarteDto(String numeCarte, String genCarte, String editura, int steluteCarte, String citit, String inBiblioteca, Autor autor, Utilizator utilizator) {
        this.numeCarte = numeCarte;
        this.genCarte = genCarte;
        this.editura = editura;
        this.steluteCarte = steluteCarte;
        this.citit = citit;
        this.inBiblioteca = inBiblioteca;
        this.autor = autor;
        this.utilizator=utilizator;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
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

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }
}
