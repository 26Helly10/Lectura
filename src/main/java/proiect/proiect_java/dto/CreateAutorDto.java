package proiect.proiect_java.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import proiect.proiect_java.model.Utilizator;

public class CreateAutorDto {
    @NotBlank
    @Size(max = 250)
    private String numeAutor;
    @NotBlank
    @Size(max = 250)
    private String taraOrigine;

    @NotNull
    private Utilizator utilizator;

    public CreateAutorDto() {
    }

    public CreateAutorDto(String numeAutor, String taraOrigine, Utilizator utilizator) {
        this.numeAutor = numeAutor;
        this.taraOrigine = taraOrigine;
        this.utilizator=utilizator;
    }

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }

    public String getNumeAutor() {
        return numeAutor;
    }

    public void setNumeAutor(String numeAutor) {
        this.numeAutor = numeAutor;
    }

    public String getTaraOrigine() {
        return taraOrigine;
    }

    public void setTaraOrigine(String taraOrigine) {
        this.taraOrigine = taraOrigine;
    }
}
