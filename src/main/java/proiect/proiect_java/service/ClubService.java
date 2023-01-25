package proiect.proiect_java.service;

import org.springframework.stereotype.Service;
import proiect.proiect_java.exception.ClubDejaExistentException;
import proiect.proiect_java.exception.NuExistaOLibrarieInBazaException;
import proiect.proiect_java.model.Club;
import proiect.proiect_java.model.Librarie;
import proiect.proiect_java.repository.ClubRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClubService {
    private final ClubRepository clubRepository;
    private LibrarieService librarieService;

    public ClubService(ClubRepository clubRepository, LibrarieService librarieService){
        this.clubRepository=clubRepository;
        this.librarieService=librarieService;
    }

    public Club create(Club club){
        Optional<Librarie> librarie= librarieService.findById(club.getLibrarie().getIdLibrarie());
        if(librarie.isEmpty()){
            throw new NuExistaOLibrarieInBazaException();
        }
        Optional<Club> ClubDejaExistent= clubRepository.findBynumeClub(club.getNumeClub());
        if (ClubDejaExistent.isPresent()){
            throw new ClubDejaExistentException();
        }
        club.setLibrarie(librarie.get());
        Club clubsalvat= clubRepository.save(club);
        return clubsalvat;
    }

    public List<Club> get(String numeClub ){

        List<Club> clubs= new ArrayList<>();
        if(numeClub !=null && !numeClub.isEmpty()) {
            clubs=clubRepository.findByNumeClub(numeClub);
        }else {
            clubs=clubRepository.findAll();
        }
        return clubs;
    }

    public Optional<Club> findById(long idClub) {
        return clubRepository.findById(idClub);
    }
}
