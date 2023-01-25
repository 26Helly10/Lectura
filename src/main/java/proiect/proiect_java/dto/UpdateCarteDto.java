package proiect.proiect_java.dto;

import proiect.proiect_java.model.Autor;
import proiect.proiect_java.model.Utilizator;

public class UpdateCarteDto extends CreateCarteDto{
    private long idCarte;

    public UpdateCarteDto() {
    }

    public UpdateCarteDto(String numeCarte, String genCarte, String editura, int steluteCarte, String citit, String inBiblioteca, Autor autor, Utilizator utilizator, long idCarte) {
        super(numeCarte, genCarte, editura, steluteCarte, citit, inBiblioteca, autor, utilizator);
        this.idCarte=idCarte;
    }

    public long getIdCarte() {
        return idCarte;
    }

    public void setIdCarte(long idCarte) {
        this.idCarte = idCarte;
    }
}
