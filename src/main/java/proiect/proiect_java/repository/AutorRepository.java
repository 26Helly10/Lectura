package proiect.proiect_java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import proiect.proiect_java.model.Autor;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor,Long> {

    Optional<Autor> findBynumeAutor(String numeAutor);

    List<Autor> findByNumeAutorAndTaraOrigine(String numeAutor, String taraOrigine);

    List<Autor> findByNumeAutor(String numeAutor);

    List<Autor> findByTaraOrigine(String taraOrigine);
}
