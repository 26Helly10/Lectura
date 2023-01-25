package proiect.proiect_java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proiect.proiect_java.model.Carte;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarteRepository extends JpaRepository<Carte,Long> {
    Optional<Carte> findBynumeCarteAndEditura(String numeCarte, String editura);
    Optional<Carte> findBynumeCarteAndEdituraAndIdCarte(String numeCarte, String editura, long idCarte);

    List<Carte> findByNumeCarte(String numeCarte);

    List<Carte> findByGenCarte(String genCarte);

    List<Carte> findByEditura(String editura);

    List<Carte> findBySteluteCarte(int steluteCarte);

    List<Carte> findByCitit(String citit);

    List<Carte> findByInBiblioteca(String inBiblioteca);
}
