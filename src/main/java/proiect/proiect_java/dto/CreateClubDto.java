package proiect.proiect_java.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import proiect.proiect_java.model.Librarie;

public class CreateClubDto {

    @NotBlank
    @Size(max =250)
    private String numeClub;

    @NotNull
    private Librarie librarie;

    public CreateClubDto(String numeClub, Librarie librarie) {

        this.numeClub = numeClub;
        this.librarie=librarie;
    }

    public CreateClubDto() {
    }

    public String getNumeClub() {
        return numeClub;
    }

    public Librarie getLibrarie() {
        return librarie;
    }

    public void setLibrarie(Librarie librarie) {
        this.librarie = librarie;
    }

    public void setNumeClub(String numeClub) {
        this.numeClub = numeClub;
    }
}
