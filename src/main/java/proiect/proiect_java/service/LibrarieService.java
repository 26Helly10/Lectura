package proiect.proiect_java.service;

import org.springframework.stereotype.Service;
import proiect.proiect_java.exception.ClubDejaExistentException;
import proiect.proiect_java.exception.LibrarieCuAcelasiNumeException;
import proiect.proiect_java.model.Librarie;
import proiect.proiect_java.repository.LibrarieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LibrarieService {
    private final LibrarieRepository librarieRepository;

    public LibrarieService(LibrarieRepository librarieRepository){
        this.librarieRepository=librarieRepository;
    }

    public Librarie create(Librarie librarie){
        Optional<Librarie> LibrarieDejaExistenta= librarieRepository.findByNumeLibrarie(librarie.getNumeLibrarie());
        if (LibrarieDejaExistenta.isPresent()){
            throw new LibrarieCuAcelasiNumeException();
        }
        return librarieRepository.save(librarie);
    }

    public List<Librarie> getLibrarie(){
        return librarieRepository.findAll();
    }

    public Optional<Librarie> findById(long idLibrarie) {
        return librarieRepository.findById(idLibrarie);
    }
}
