package proiect.proiect_java.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="Autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAutor")
    private long idAutor;
    private String numeAutor;
    private String taraOrigine;

    @ManyToOne()
    @JoinColumn(name = "idUtilizator")
    private Utilizator utilizator;

    public Autor() {
    }

    public Autor(long idAutor) {
        this.idAutor = idAutor;
    }

    public Autor(String numeAutor, String taraOrigine, Utilizator utilizator) {
        this.numeAutor = numeAutor;
        this.taraOrigine = taraOrigine;
        this.utilizator= utilizator;
    }

    public Autor(long idAutor, String numeAutor, String taraOrigine, Utilizator utilizator) {
        this.idAutor = idAutor;
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

    public long getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(long idAutor) {
        this.idAutor = idAutor;
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
