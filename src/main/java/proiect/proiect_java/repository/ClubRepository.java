package proiect.proiect_java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proiect.proiect_java.model.Club;

import java.util.List;
import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findBynumeClub(String numeClub);

    List<Club> findByNumeClub(String numeClub);
}
