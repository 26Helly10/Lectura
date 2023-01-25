package proiect.proiect_java.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import proiect.proiect_java.exception.CarteDejaExistentaInBazaException;
import proiect.proiect_java.exception.CarteInexistentaException;
import proiect.proiect_java.exception.NuExistaAcestAutorInBazaException;
import proiect.proiect_java.exception.NuExistaAcestUtilizatorInBazaException;
import proiect.proiect_java.model.Autor;
import proiect.proiect_java.model.Carte;
import proiect.proiect_java.model.Utilizator;
import proiect.proiect_java.repository.AutorRepository;
import proiect.proiect_java.repository.CarteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarteService {
    private CarteRepository carteRepository;
    private AutorService autorService;

    private UtilizatorService utilizatorService;


    public CarteService(CarteRepository carteRepository, AutorService autorService, UtilizatorService utilizatorService){
        this.carteRepository= carteRepository;
        this.autorService = autorService;
        this.utilizatorService =utilizatorService;
    }

    //create
    public Carte create(Carte carte)
    {
        Optional<Autor> autor = autorService.findById(carte.getAutor().getIdAutor());
        if (autor.isEmpty()){
            throw new NuExistaAcestAutorInBazaException();}
        Optional<Utilizator> utilizator =utilizatorService.findById(carte.getUtilizator().getIdUtilizator());
        if(utilizator.isEmpty()){
            throw new NuExistaAcestUtilizatorInBazaException();}
        Optional<Carte> CarteDejaExistentaInBaza = carteRepository.findBynumeCarteAndEditura(carte.getNumeCarte(), carte.getEditura());
        if(CarteDejaExistentaInBaza.isPresent()){
            throw new CarteDejaExistentaInBazaException();}
        carte.setAutor(autor.get());
        carte.setUtilizator(utilizator.get());
        Carte carteSalvata = carteRepository.save(carte);
        return carteRepository.save(carte);}
    public Carte update(Carte carte){
        Optional<Carte> existaCarte= carteRepository.findById(carte.getIdCarte());
        if (existaCarte.isEmpty()){
            throw new CarteInexistentaException();}
        Optional<Carte> CarteDejaExistentaInBaza = carteRepository.findBynumeCarteAndEdituraAndIdCarte(carte.getNumeCarte(), carte.getEditura(), carte.getIdCarte());
        if(CarteDejaExistentaInBaza.isPresent()){
            throw new CarteDejaExistentaInBazaException();}
        Optional<Autor> autor = autorService.findById(carte.getAutor().getIdAutor());
        if (autor.isEmpty()){
            throw new NuExistaAcestAutorInBazaException();}
        Optional<Utilizator> utilizator =utilizatorService.findById(carte.getUtilizator().getIdUtilizator());
        if(utilizator.isEmpty()){
            throw new NuExistaAcestUtilizatorInBazaException();}
        return carteRepository.save(carte);}

    public List<Carte> get(String numeCarte, String genCarte, String editura, int steluteCarte, String citit, String inBiblioteca){
        List<Carte> carti= new ArrayList<>();

        if (numeCarte!=null &&!numeCarte.isEmpty()){
            carti=carteRepository.findByNumeCarte(numeCarte);}
        if (genCarte!=null &&!genCarte.isEmpty()){
            carti=carteRepository.findByGenCarte(genCarte);}
        if (editura!=null &&!editura.isEmpty()){
            carti=carteRepository.findByEditura(editura);}
        if (steluteCarte>0 ){
            carti=carteRepository.findBySteluteCarte(steluteCarte);
        }
        if (citit!=null && !citit.isEmpty()){
            carti=carteRepository.findByCitit(citit);
        }
        if (inBiblioteca!=null && !inBiblioteca.isEmpty()){
            carti=carteRepository.findByInBiblioteca(inBiblioteca);
        }
        if(carti.isEmpty()){
            carti=carteRepository.findAll();}
        return carti;}}
