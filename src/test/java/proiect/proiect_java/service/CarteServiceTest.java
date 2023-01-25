package proiect.proiect_java.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.annotation.RequestParam;
import proiect.proiect_java.exception.CarteDejaExistentaInBazaException;
import proiect.proiect_java.exception.CarteInexistentaException;
import proiect.proiect_java.exception.NuExistaAcestAutorInBazaException;
import proiect.proiect_java.exception.NuExistaAcestUtilizatorInBazaException;
import proiect.proiect_java.model.Autor;
import proiect.proiect_java.model.Carte;
import proiect.proiect_java.model.Utilizator;
import proiect.proiect_java.repository.CarteRepository;

import java.util.Optional;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class CarteServiceTest {

    @InjectMocks
    private CarteService carteService;

    @Mock
    private CarteRepository carteRepository;

    @Mock
    private AutorService autorService;

    @Mock
    private UtilizatorService utilizatorService;

    @Test
    @DisplayName("Scenariu negativ: Cand nu exista un autor cu id x in baza ")
    void create_fara_autor() {
        //Arrange
        Carte carte= new Carte();
        Autor autor= new Autor();
        Utilizator utilizator= new Utilizator();
        utilizator.setIdUtilizator(1);
        autor.setIdAutor(1);
        carte.setAutor(autor);
        carte.setUtilizator(utilizator);

        when(autorService.findById(autor.getIdAutor())).thenReturn(Optional.empty());

        //Act
        NuExistaAcestAutorInBazaException exception = assertThrows(NuExistaAcestAutorInBazaException.class,
                () -> carteService.create(carte));

        //Assert

        assertEquals("Nu Exista Acest Autor In Baza Exception", exception.getMessage());

        verify(autorService).findById(autor.getIdAutor());

        verify(carteRepository, times(0)).save(carte);

        verifyNoInteractions(carteRepository);

    }
    //-------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Scenariu negativ: Cand nu exista un utilizator cu id x in baza ")
    void create_fara_utilizator() {
        //Arrange
        Carte carte= new Carte();
        Autor autor= new Autor();
        Utilizator utilizator= new Utilizator();
        utilizator.setIdUtilizator(1);
        carte.setAutor(autor);
        carte.setUtilizator(utilizator);
        Optional<Autor> autorOptional =Optional.of(autor);
        when(autorService.findById(autor.getIdAutor()))
                .thenReturn(autorOptional);

        when(utilizatorService.findById(utilizator.getIdUtilizator())).thenReturn(Optional.empty());

        //Act
        NuExistaAcestUtilizatorInBazaException exception = assertThrows(NuExistaAcestUtilizatorInBazaException.class,
                () -> carteService.create(carte));

        //Assert

        assertEquals("Nu exista acest utilizator in baza", exception.getMessage());

        verify(utilizatorService).findById(utilizator.getIdUtilizator());

        verify(carteRepository, times(0)).save(carte);

        verifyNoInteractions(carteRepository);

    }

    //--------------------------------------------------------------------------------------------------------------

    @Test
    @DisplayName("Scenariu negativ: cand exista deja o carte in baza si numele x si editura y")
    void create_Carte_deja_existenta() {
        //Arrange
        Carte carte= new Carte();
        Autor autor= new Autor();
        Utilizator utilizator= new Utilizator();
        carte.setAutor(autor);
        carte.setUtilizator(utilizator);
        carte.setNumeCarte("Harry Potter");
        carte.setEditura("Egmond");
        Optional<Autor> autorOptional =Optional.of(autor);

        when(autorService.findById(autor.getIdAutor()))
                .thenReturn(autorOptional);
        Optional<Utilizator> utilizatorOptional =Optional.of(utilizator);

        when(utilizatorService.findById(utilizator.getIdUtilizator()))
                .thenReturn(utilizatorOptional);

        when(carteRepository.findBynumeCarteAndEditura(carte.getNumeCarte(), carte.getEditura()))
                .thenReturn(Optional.of(carte));
        //Act
        CarteDejaExistentaInBazaException exception = assertThrows(CarteDejaExistentaInBazaException.class,
                ()-> carteService.create(carte));



        //Assert

        assertEquals("Exista deja o carte cu acelasi nume si de la aceeasi editura.", exception.getMessage());


        verify(carteRepository, times(0)).save(carte);

        //verifyNoInteractions(carteRepository);

    }
    //---------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Scenariu pozitiv: creare carte")
    void create_Carte() {
        //Arrange
        Autor autor= new Autor();
        Utilizator utilizator= new Utilizator();
        //carte.setNumeCarte("Harry Potter");
       // carte.setGenCarte("Fantezie");
        //carte.setEditura("Egmond");
        //carte.setSteluteCarte(5);
        //carte.setCitit("da");
        //carte.setInBiblioteca("da");
        ///carte.setAutor(autor);
        //carte.setUtilizator(utilizator);
        Optional<Autor> autorOptional =Optional.of(autor);

        when(autorService.findById(autor.getIdAutor()))
                .thenReturn(autorOptional);

        Optional<Utilizator> utilizatorOptional =Optional.of(utilizator);

        when(utilizatorService.findById(utilizator.getIdUtilizator()))
                .thenReturn(utilizatorOptional);
        Carte carte= new Carte("Harry Potter","Fantezie", "Egmond",5,"da","da",autor,utilizator);

        when(carteRepository.findBynumeCarteAndEditura(carte.getNumeCarte(), carte.getEditura()))
                .thenReturn(Optional.empty());

        Carte carteSalvata= new Carte(1,"Harry Potter","Fantezie", "Egmond",5,"da","da",autor,utilizator);
        when(carteRepository.save(carte)).thenReturn(carteSalvata);
        //act
        Carte rezultat= carteService.create(carte);

        //Assert
        assertNotNull(rezultat);
        assertEquals(carteSalvata.getIdCarte(), rezultat.getIdCarte());
        assertEquals(carteSalvata.getNumeCarte(), rezultat.getNumeCarte());
        assertEquals(carteSalvata.getGenCarte(), rezultat.getGenCarte());
        assertEquals(carteSalvata.getEditura(), rezultat.getEditura());
        assertEquals(carteSalvata.getSteluteCarte(), rezultat.getSteluteCarte());
        assertEquals(carteSalvata.getCitit(), rezultat.getCitit());
        assertEquals(carteSalvata.getInBiblioteca(), rezultat.getInBiblioteca());
        assertEquals(carteSalvata.getAutor(), rezultat.getAutor());
        assertEquals(carteSalvata.getUtilizator(), rezultat.getUtilizator());
        verify(carteRepository).findBynumeCarteAndEditura(carte.getNumeCarte(),carte.getEditura());
        //verify(carteRepository).save(carte);

    }

    //-------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Scenariu negativ: exista in baza o alta carte cu numele x si editura y")
    void update_cu_nume_si_editura_identice_cu_alta_carte() {
        Autor autor= new Autor();
        Utilizator utilizator= new Utilizator();
        Carte carteVeche= new Carte(1,"Harry Potter 1","Fantezie", "Art",5,"da","da",autor,utilizator);
        Carte carteNoua= new Carte(1,"Harry Potter","Fantezie", "Egmond",5,"da","da",autor,utilizator);
        Carte altaCarte= new Carte(2,"Harry Potter","Fantezie", "Egmond",4,"da","nu",autor,utilizator);

        when(carteRepository.findById(carteNoua.getIdCarte())).thenReturn(Optional.of(carteVeche));

        when(carteRepository.findBynumeCarteAndEdituraAndIdCarte(carteNoua.getNumeCarte(),carteNoua.getEditura(),carteNoua.getIdCarte()))
                .thenReturn(Optional.of(altaCarte));
        //act
        CarteDejaExistentaInBazaException exception= assertThrows(CarteDejaExistentaInBazaException.class,
                ()-> carteService.update(carteNoua));

        //assert
        verify(carteRepository).findById(carteNoua.getIdCarte());
        verify(carteRepository).findBynumeCarteAndEdituraAndIdCarte(carteNoua.getNumeCarte(), carteNoua.getEditura(), carteNoua.getIdCarte());
        verify(carteRepository, times(0)).save(carteNoua);

    }
    //------------------------------------------------------------------------------------------

    @Test
    @DisplayName("Scenariu negativ: nu exista autor")
    void update_nu_exista_autor() {
        Autor autor= new Autor();
        Utilizator utilizator= new Utilizator();
        Carte carteVeche= new Carte(1,"Harry Potter 1","Fantezie", "Art",5,"da","da",autor,utilizator);
        Carte carteNoua= new Carte(1,"Harry Potter","Fantezie", "Egmond",5,"da","da",autor,utilizator);

        when(carteRepository.findById(carteNoua.getIdCarte())).thenReturn(Optional.of(carteVeche));

        when(carteRepository.findBynumeCarteAndEdituraAndIdCarte(carteNoua.getNumeCarte(),carteNoua.getEditura(),carteNoua.getIdCarte()))
                .thenReturn(Optional.empty());

        when(autorService.findById(autor.getIdAutor())).thenReturn(Optional.empty());

        //Act
        NuExistaAcestAutorInBazaException exception = assertThrows(NuExistaAcestAutorInBazaException.class,
                () -> carteService.update(carteNoua));

        //Assert

        assertEquals("Nu Exista Acest Autor In Baza Exception", exception.getMessage());

        verify(autorService).findById(autor.getIdAutor());
        verify(carteRepository).findBynumeCarteAndEdituraAndIdCarte(carteNoua.getNumeCarte(), carteNoua.getEditura(), carteNoua.getIdCarte());
        verify(carteRepository, times(0)).save(carteNoua);

    }
    //-------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Scenariu negativ: nu exista utilizator")
    void update_nu_exista_utilizator() {
        Autor autor= new Autor();
        Utilizator utilizator= new Utilizator();
        Carte carteVeche= new Carte(1,"Harry Potter 1","Fantezie", "Art",5,"da","da",autor,utilizator);
        Carte carteNoua= new Carte(1,"Harry Potter","Fantezie", "Egmond",5,"da","da",autor,utilizator);

        when(carteRepository.findById(carteNoua.getIdCarte())).thenReturn(Optional.of(carteVeche));

        when(carteRepository.findBynumeCarteAndEdituraAndIdCarte(carteNoua.getNumeCarte(),carteNoua.getEditura(),carteNoua.getIdCarte()))
                .thenReturn(Optional.empty());

        when(autorService.findById(autor.getIdAutor())).thenReturn(Optional.of(autor));

        when(utilizatorService.findById(utilizator.getIdUtilizator())).thenReturn(Optional.empty());


        //Act
        NuExistaAcestUtilizatorInBazaException exception = assertThrows(NuExistaAcestUtilizatorInBazaException.class,
                () -> carteService.update(carteNoua));

        //Assert

        assertEquals("Nu exista acest utilizator in baza", exception.getMessage());

        verify(autorService).findById(autor.getIdAutor());
        verify(autorService).findById(utilizator.getIdUtilizator());
        verify(carteRepository).findBynumeCarteAndEdituraAndIdCarte(carteNoua.getNumeCarte(), carteNoua.getEditura(), carteNoua.getIdCarte());
        verify(carteRepository, times(0)).save(carteNoua);

    }
    //-------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Scenariu pozitiv: update reusit")
    void update_reusit() {
        Autor autor= new Autor();
        Utilizator utilizator= new Utilizator();
        Carte carteVeche= new Carte(1,"Harry Potter 1","Fantezie", "Art",5,"da","da",autor,utilizator);
        Carte carteNoua= new Carte(1,"Harry Potter","Fantezie", "Egmond",5,"da","da",autor,utilizator);

        when(carteRepository.findById(carteNoua.getIdCarte())).thenReturn(Optional.of(carteVeche));

        when(carteRepository.findBynumeCarteAndEdituraAndIdCarte(carteNoua.getNumeCarte(),carteNoua.getEditura(),carteNoua.getIdCarte()))
                .thenReturn(Optional.empty());

        when(autorService.findById(autor.getIdAutor())).thenReturn(Optional.of(autor));

        when(utilizatorService.findById(utilizator.getIdUtilizator())).thenReturn(Optional.of(utilizator));

        when(carteRepository.save(carteNoua)).thenReturn(carteNoua);
        //Act
        Carte rezultat= carteService.update(carteNoua);

        //Assert
        assertNotNull(rezultat);
        assertEquals(carteNoua.getIdCarte(), rezultat.getIdCarte());
        assertEquals(carteNoua.getNumeCarte(), rezultat.getNumeCarte());
        assertEquals(carteNoua.getGenCarte(), rezultat.getGenCarte());
        assertEquals(carteNoua.getEditura(), rezultat.getEditura());
        assertEquals(carteNoua.getSteluteCarte(), rezultat.getSteluteCarte());
        assertEquals(carteNoua.getCitit(), rezultat.getCitit());
        assertEquals(carteNoua.getInBiblioteca(), rezultat.getInBiblioteca());
        assertEquals(carteNoua.getAutor(), rezultat.getAutor());
        assertEquals(carteNoua.getUtilizator(), rezultat.getUtilizator());
        verify(carteRepository).findBynumeCarteAndEdituraAndIdCarte(carteNoua.getNumeCarte(), carteNoua.getEditura(), carteNoua.getIdCarte());
        verify(carteRepository).save(carteNoua);

    }

    //-------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Scenariu negativ: nu exista id in baza")
    void update_inexistent_id () {
        Autor autor= new Autor();
        Utilizator utilizator= new Utilizator();
        Carte carteSalvata= new Carte(1,"Harry Potter","Fantezie", "Egmond",5,"da","da",autor,utilizator);
        when(carteRepository.findById(carteSalvata.getIdCarte())).thenReturn(Optional.empty());

        //act
        CarteInexistentaException exception= assertThrows(CarteInexistentaException.class,
                ()-> carteService.update(carteSalvata));

        //assert
        verify(carteRepository).findById(carteSalvata.getIdCarte());
        verify(carteRepository, times(0)).findBynumeCarteAndEditura(carteSalvata.getNumeCarte(), carteSalvata.getEditura());
        verify(carteRepository, times(0)).save(carteSalvata);

    }
    //-------------------------------------------------------------------------------------------

    @Test
    @DisplayName("afisarea tuturor")
    void getAll() {
        //arrange
        String numeCarte ="Harry Potter";
        String genCarte="Fantezie";
        String editura="Egmond";
        int steluteCarte=5;
        String citit="da";
        String inBiblioteca="da";
        Autor autor= new Autor();
        Utilizator utilizator= new Utilizator();
        Carte carte= new Carte(1,"Harry Potter","Fantezie", "Egmond",5,"da","da",autor,utilizator);

        when(carteRepository.findAll()).thenReturn(List.of(carte));
        //act
        List<Carte> rezultat =carteService.get(null,null,null,0,null,null);
        //assert
        assertNotNull(rezultat);
        assertEquals(1, rezultat.size());
        verify(carteRepository).findAll();
    }

    //-------------------------------------------------------------------------------------------
    @Test
    @DisplayName("afisarea in functie de nume")
    void getname() {
        //arrange
        String numeCarte ="Harry Potter";
        String genCarte="Fantezie";
        String editura="Egmond";
        int steluteCarte=5;
        String citit="da";
        String inBiblioteca="da";
        Autor autor= new Autor();
        Utilizator utilizator= new Utilizator();
        Carte carte= new Carte(1,"Harry Potter","Fantezie", "Egmond",5,"da","da",autor,utilizator);

        when(carteRepository.findByNumeCarte(numeCarte)).thenReturn(List.of(carte));
        //act
        List<Carte> rezultat =carteService.get(numeCarte,null,null,0,null,null);
        //assert
        assertNotNull(rezultat);
        assertEquals(1, rezultat.size());
        verify(carteRepository).findByNumeCarte(numeCarte);
    }

    //----------------------------------------------------------------------------------
    @Test
    @DisplayName("afisarea in functie de gen")
    void getgen() {
        //arrange
        String numeCarte ="Harry Potter";
        String genCarte="Fantezie";
        String editura="Egmond";
        int steluteCarte=5;
        String citit="da";
        String inBiblioteca="da";
        Autor autor= new Autor();
        Utilizator utilizator= new Utilizator();
        Carte carte= new Carte(1,"Harry Potter","Fantezie", "Egmond",5,"da","da",autor,utilizator);

        when(carteRepository.findByGenCarte(genCarte)).thenReturn(List.of(carte));
        //act
        List<Carte> rezultat =carteService.get(null,genCarte,null,0,null,null);
        //assert
        assertNotNull(rezultat);
        assertEquals(1, rezultat.size());
        verify(carteRepository).findByGenCarte(genCarte);
    }

    //----------------------------------------------------------------------------------
    @Test
    @DisplayName("afisarea in functie de editura")
    void geteditura() {
        //arrange
        String numeCarte ="Harry Potter";
        String genCarte="Fantezie";
        String editura="Egmond";
        int steluteCarte=5;
        String citit="da";
        String inBiblioteca="da";
        Autor autor= new Autor();
        Utilizator utilizator= new Utilizator();
        Carte carte= new Carte(1,"Harry Potter","Fantezie", "Egmond",5,"da","da",autor,utilizator);

        when(carteRepository.findByEditura(editura)).thenReturn(List.of(carte));
        //act
        List<Carte> rezultat =carteService.get(null,null,editura,0,null,null);
        //assert
        assertNotNull(rezultat);
        assertEquals(1, rezultat.size());
        verify(carteRepository).findByEditura(editura);
    }

    //----------------------------------------------------------------------------------
    @Test
    @DisplayName("afisarea in functie de rating")
    void getstelute() {
        //arrange
        String numeCarte ="Harry Potter";
        String genCarte="Fantezie";
        String editura="Egmond";
        int steluteCarte=5;
        String citit="da";
        String inBiblioteca="da";
        Autor autor= new Autor();
        Utilizator utilizator= new Utilizator();
        Carte carte= new Carte(1,"Harry Potter","Fantezie", "Egmond",5,"da","da",autor,utilizator);

        when(carteRepository.findBySteluteCarte(steluteCarte)).thenReturn(List.of(carte));
        //act
        List<Carte> rezultat =carteService.get(null,null,null,steluteCarte,null,null);
        //assert
        assertNotNull(rezultat);
        assertEquals(1, rezultat.size());
        verify(carteRepository).findBySteluteCarte(steluteCarte);
    }
    //----------------------------------------------------------------------------------
    @Test
    @DisplayName("afisarea in functie de citit")
    void getcitit() {
        //arrange
        String numeCarte ="Harry Potter";
        String genCarte="Fantezie";
        String editura="Egmond";
        int steluteCarte=5;
        String citit="da";
        String inBiblioteca="da";
        Autor autor= new Autor();
        Utilizator utilizator= new Utilizator();
        Carte carte= new Carte(1,"Harry Potter","Fantezie", "Egmond",5,"da","da",autor,utilizator);

        when(carteRepository.findByCitit(citit)).thenReturn(List.of(carte));
        //act
        List<Carte> rezultat =carteService.get(null,null,null,0,citit,null);
        //assert
        assertNotNull(rezultat);
        assertEquals(1, rezultat.size());
        verify(carteRepository).findByCitit(citit);
    }
    //----------------------------------------------------------------------------------
    @Test
    @DisplayName("afisarea in functie de inBiblioteca")
    void getinBiblioteca() {
        //arrange
        String numeCarte ="Harry Potter";
        String genCarte="Fantezie";
        String editura="Egmond";
        int steluteCarte=5;
        String citit="da";
        String inBiblioteca="da";
        Autor autor= new Autor();
        Utilizator utilizator= new Utilizator();
        Carte carte= new Carte(1,"Harry Potter","Fantezie", "Egmond",5,"da","da",autor,utilizator);

        when(carteRepository.findByInBiblioteca(inBiblioteca)).thenReturn(List.of(carte));
        //act
        List<Carte> rezultat =carteService.get(null,null,null,0,null,inBiblioteca);
        //assert
        assertNotNull(rezultat);
        assertEquals(1, rezultat.size());
        verify(carteRepository).findByInBiblioteca(inBiblioteca);
    }
}