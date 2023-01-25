package proiect.proiect_java.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name ="Utilizator")
public class Utilizator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUtilizator")
    private long idUtilizator;
    private String numeUtilizator;
    private String email;

    @ManyToOne()
    @JoinColumn(name="idClub")
    private Club club;

    public Utilizator() {
    }

    public Utilizator(String numeUtilizator, String email, Club club) {
        this.numeUtilizator = numeUtilizator;
        this.email = email;
        this.club = club;
    }

    public Utilizator(long idUtilizator, String numeUtilizator, String email, Club club) {
        this.idUtilizator = idUtilizator;
        this.numeUtilizator = numeUtilizator;
        this.email = email;
        this.club = club;
    }

    public long getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(long idUtilizator) {
        this.idUtilizator = idUtilizator;
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
