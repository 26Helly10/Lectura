package proiect.proiect_java.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import proiect.proiect_java.model.Club;

import java.util.ArrayList;
import java.util.List;

public class CreateLibrarieDto {

    @NotBlank
    @Size(max = 250)
    private String numeLibrarie;

    public CreateLibrarieDto() {
    }

    public CreateLibrarieDto(String numeLibrarie) {
        this.numeLibrarie = numeLibrarie;
    }

    public String getNumeLibrarie() {
        return numeLibrarie;
    }

    public void setNumeLibrarie(String numeLibrarie) {
        this.numeLibrarie = numeLibrarie;
    }
}
