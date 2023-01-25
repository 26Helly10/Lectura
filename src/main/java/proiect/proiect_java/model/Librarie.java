package proiect.proiect_java.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Librarie")
public class Librarie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idLibrarie")
    private long idLibrarie;

    private String numeLibrarie;



    public Librarie() {
    }

    public Librarie(long idLibrarie, String numeLibrarie) {
        this.idLibrarie = idLibrarie;
        this.numeLibrarie = numeLibrarie;

    }

    public Librarie(String numeLibrarie) {
        this.numeLibrarie = numeLibrarie;
    }

    public long getIdLibrarie() {
        return idLibrarie;
    }

    public void setIdLibrarie(long idLibrarie) {
        this.idLibrarie = idLibrarie;
    }

    public String getNumeLibrarie() {
        return numeLibrarie;
    }

    public void setNumeLibrarie(String numeLibrarie) {
        this.numeLibrarie = numeLibrarie;
    }

}
