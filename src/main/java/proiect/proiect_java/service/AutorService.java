package proiect.proiect_java.service;

import org.springframework.stereotype.Service;
import proiect.proiect_java.exception.AutorCuAcelasiNumeException;
import proiect.proiect_java.exception.NuExistaAcestAutorInBazaException;
import proiect.proiect_java.exception.NuExistaAcestUtilizatorInBazaException;
import proiect.proiect_java.model.Autor;
import proiect.proiect_java.model.Utilizator;
import proiect.proiect_java.repository.AutorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutorService {
    private final AutorRepository autorRepository;
    private UtilizatorService utilizatorService;

    public AutorService(AutorRepository autorRepository, UtilizatorService utilizatorService){
        this.autorRepository= autorRepository;
        this.utilizatorService=utilizatorService;
    }
    //create
    public Autor create(Autor autor){
        Optional<Utilizator> utilizator =utilizatorService.findById(autor.getUtilizator().getIdUtilizator());
        if(utilizator.isEmpty()){
            throw new NuExistaAcestUtilizatorInBazaException();
        }
        Optional<Autor> autorCuAcelasiNume = autorRepository.findBynumeAutor(autor.getNumeAutor());
        if (autorCuAcelasiNume.isPresent()){
            throw new AutorCuAcelasiNumeException();
        }
        autor.setUtilizator(utilizator.get());
        return autorRepository.save(autor);
    }
    //get
    public List<Autor> get(String numeAutor, String taraOrigine){
        List<Autor> autors = new ArrayList<>();

        if(numeAutor !=null && !numeAutor.isEmpty()) {
            if (taraOrigine!=null &&!taraOrigine.isEmpty()){
                autors=autorRepository.findByNumeAutorAndTaraOrigine(numeAutor,taraOrigine);
            }else{
                autors=autorRepository.findByNumeAutor(numeAutor);
            }
        }else{
            if (taraOrigine!=null &&!taraOrigine.isEmpty()){
                autors=autorRepository.findByTaraOrigine(taraOrigine);
            }else{
                autors=autorRepository.findAll();
            }
        }
        return autors;
    }

    public Optional<Autor> findById(long idAutor) {
        return autorRepository.findById(idAutor);
    }
}
