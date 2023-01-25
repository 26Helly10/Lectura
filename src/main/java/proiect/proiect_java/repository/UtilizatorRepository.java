package proiect.proiect_java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proiect.proiect_java.model.Utilizator;

import java.util.Optional;

public interface UtilizatorRepository extends JpaRepository<Utilizator, Long> {
    Optional<Utilizator> findByEmail(String email);

    Optional<Utilizator> findByEmailAndIdUtilizatorNot(String email, long idUtilizator);
}
