package proiect.proiect_java.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Club_de_lectura")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idClub")
    private long idClub;
    private String numeClub;

    @ManyToOne()
    @JoinColumn(name="idLibrarie")
    private Librarie librarie;

    public Club() {
    }

    public Club(long idClub) {
        this.idClub = idClub;
    }

    public Club(String numeClub, Librarie librarie) {
        this.numeClub = numeClub;
        this.librarie=librarie;
    }

    public Club(long idClub, String numeClub, Librarie librarie) {
        this.idClub = idClub;
        this.numeClub = numeClub;
        this.librarie=librarie;
    }

    public Librarie getLibrarie() {
        return librarie;
    }

    public void setLibrarie(Librarie librarie) {
        this.librarie = librarie;
    }

    public long getIdClub() {
        return idClub;
    }

    public void setIdClub(long idClub) {
        this.idClub = idClub;
    }

    public String getNumeClub() {
        return numeClub;
    }

    public void setNumeClub(String numeClub) {
        this.numeClub = numeClub;
    }
}
