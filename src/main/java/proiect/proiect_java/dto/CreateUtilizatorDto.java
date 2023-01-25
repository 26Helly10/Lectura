package proiect.proiect_java.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import proiect.proiect_java.model.Club;

public class CreateUtilizatorDto {

    @NotBlank
    @Size(max = 250)
    private String numeUtilizator;

    @NotBlank
    @Size(max = 270)
    private String email;

    @NotNull
    private Club club;

    public CreateUtilizatorDto() {
    }

    public CreateUtilizatorDto(String numeUtilizator, String email, Club club) {
        this.numeUtilizator = numeUtilizator;
        this.email = email;
        this.club = club;
    }

    public String getNumeUtilizator() {
        return numeUtilizator;
    }

    public void setNumeUtilizator(String numeUtilizator) {
        this.numeUtilizator = numeUtilizator;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
