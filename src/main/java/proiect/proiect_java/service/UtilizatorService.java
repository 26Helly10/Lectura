package proiect.proiect_java.service;

import org.springframework.stereotype.Service;
import proiect.proiect_java.exception.NuExistaAcestUtilizatorInBazaException;
import proiect.proiect_java.exception.NuExistaUnClubInBazaExceltion;
import proiect.proiect_java.exception.UtilizatorDejaExistentException;
import proiect.proiect_java.model.Club;
import proiect.proiect_java.model.Utilizator;
import proiect.proiect_java.repository.UtilizatorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UtilizatorService {
    private UtilizatorRepository utilizatorRepository;
    private ClubService clubService;

    public UtilizatorService(UtilizatorRepository utilizatorRepository, ClubService clubService){
        this.utilizatorRepository= utilizatorRepository;
        this.clubService=clubService;
    }
    
    //Create Utilizator
    public Utilizator create(Utilizator utilizator){
        Optional<Club> club = clubService.findById(utilizator.getClub().getIdClub());
        if(club.isEmpty()){
            throw new NuExistaUnClubInBazaExceltion();
        }

        Optional<Utilizator> UtilizatorInBaza = utilizatorRepository.findByEmail(utilizator.getEmail());
        if(UtilizatorInBaza.isPresent()){
            throw new UtilizatorDejaExistentException();
        }

        utilizator.setClub(club.get());
        return utilizatorRepository.save(utilizator);}
    public Utilizator uptade(Utilizator utilizator){

        Optional<Utilizator> existaUtilizatorInBaza=utilizatorRepository.findById(utilizator.getIdUtilizator());
        if (existaUtilizatorInBaza.isEmpty()){
            throw new NuExistaAcestUtilizatorInBazaException();
        }
        Optional<Utilizator> utilizatorCuEmailIdentic= utilizatorRepository.findByEmailAndIdUtilizatorNot(utilizator.getEmail(),utilizator.getIdUtilizator());
        if(utilizatorCuEmailIdentic.isPresent()){
            throw new UtilizatorDejaExistentException();
        }
        Optional<Club> club = clubService.findById(utilizator.getClub().getIdClub());
        if(club.isEmpty()){
            throw new NuExistaUnClubInBazaExceltion();
        }
        return utilizatorRepository.save(utilizator);
    }
    public List<Utilizator> getUtilizator(){return utilizatorRepository.findAll();}
    public Optional<Utilizator> findById(long idUtilizator) {
        return utilizatorRepository.findById(idUtilizator);
    }
}
