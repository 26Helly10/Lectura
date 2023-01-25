package proiect.proiect_java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proiect.proiect_java.model.Librarie;

import java.util.Optional;

@Repository
public interface LibrarieRepository extends JpaRepository<Librarie, Long> {
    Optional<Librarie> findByNumeLibrarie(String numeLibrarie);

}
