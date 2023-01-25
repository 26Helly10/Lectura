package proiect.proiect_java.service;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import proiect.proiect_java.exception.AutorCuAcelasiNumeException;
import proiect.proiect_java.exception.NuExistaAcestUtilizatorInBazaException;
import proiect.proiect_java.exception.UtilizatorDejaExistentException;
import proiect.proiect_java.model.Autor;
import proiect.proiect_java.model.Club;
import proiect.proiect_java.model.Librarie;
import proiect.proiect_java.model.Utilizator;
import proiect.proiect_java.repository.AutorRepository;

import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class AutorServiceTest {

    @InjectMocks
    private AutorService autorService;

    @Mock
    private AutorRepository autorRepository;

    @Mock
    private UtilizatorService utilizatorService;
    //---------------------------------------------------------------------------
    @Test
    @DisplayName("Scenariu negativ: nu exista utilizator in baza cu id x")
    void create_nu_exista_utilizator() {
        //Arrange
        Autor autor=new Autor();
        Utilizator utilizator= new Utilizator();
        autor.setUtilizator(utilizator);
        when(utilizatorService.findById(utilizator.getIdUtilizator())).thenReturn(Optional.empty());

        //act
        NuExistaAcestUtilizatorInBazaException exception=assertThrows(NuExistaAcestUtilizatorInBazaException.class,
                ()->autorService.create(autor));
        //assert
        assertEquals("Nu exista acest utilizator in baza", exception.getMessage());
        verify(utilizatorService).findById(utilizator.getIdUtilizator());
        verify(autorRepository, times(0)).save(autor);
        verifyNoInteractions(autorRepository);
    }
    //---------------------------------------------------------------------------
    @Test
    @DisplayName("Scenariu negativ: nu exista utilizator in baza cu id x")
    void create_() {
        //Arrange
        Autor autor=new Autor();
        Utilizator utilizator= new Utilizator();
        autor.setUtilizator(utilizator);
        when(utilizatorService.findById(utilizator.getIdUtilizator())).thenReturn(Optional.empty());

        //act
        NuExistaAcestUtilizatorInBazaException exception=assertThrows(NuExistaAcestUtilizatorInBazaException.class,
                ()->autorService.create(autor));
        //assert
        assertEquals("Nu exista acest utilizator in baza", exception.getMessage());
        verify(utilizatorService).findById(utilizator.getIdUtilizator());
        verify(autorRepository, times(0)).save(autor);
        verifyNoInteractions(autorRepository);
    }
    //---------------------------------------------------------------------------
   @Test
    @DisplayName("Scenariu negativ: exista un autor cu acelasi nume in baza")
    void create_autor_cu_acelasi_nume() {
        //Arrange
        Autor autor =new Autor();
        Utilizator utilizator= new Utilizator();
        utilizator.setIdUtilizator(1);
        autor.setNumeAutor("Mircea");
        autor.setUtilizator(utilizator);
        Optional<Utilizator> utilizatorOptional= Optional.of(utilizator);

        when(utilizatorService.findById(utilizator.getIdUtilizator()))
                .thenReturn(utilizatorOptional);

        when(autorRepository.findBynumeAutor(autor.getNumeAutor())).thenReturn(Optional.of(autor));
        //act
        AutorCuAcelasiNumeException exception=assertThrows(AutorCuAcelasiNumeException.class,
                ()->autorService.create(autor));
        //assert
        assertEquals("Exista deja un autor cu acelasi nume.", exception.getMessage());
        verify(autorRepository, times(0)).save(autor);

    }
    //---------------------------------------------------------------------------
   @Test
   @DisplayName("Scenariu pozitiv:creare autor")
   void creare_autor() {
       //Arrange
       Utilizator utilizator= new Utilizator();
       Autor autor= new Autor("Autor","Romania",utilizator);
       Optional<Utilizator> utilizatorOptional= Optional.of(utilizator);

       when(utilizatorService.findById(utilizator.getIdUtilizator()))
               .thenReturn(utilizatorOptional);
       when(autorRepository.findBynumeAutor(autor.getNumeAutor()))
               .thenReturn(Optional.empty());
       Autor autorSalvat= new Autor(1,"Autor","Romania",utilizator);
       when(autorRepository.save(autor)).thenReturn(autorSalvat);
       //act
      Autor rezultat =autorService.create(autor);
       //Assert
       assertNotNull(rezultat);
       assertEquals(autorSalvat.getIdAutor(), rezultat.getIdAutor());
       assertEquals(autorSalvat.getNumeAutor(), rezultat.getNumeAutor());
       assertEquals(autorSalvat.getTaraOrigine(), rezultat.getTaraOrigine());
       assertEquals(autorSalvat.getUtilizator(), rezultat.getUtilizator());
       verify(autorRepository).save(autor);

   }
    //---------------------------------------------------------------------------
    @Test
    @DisplayName("afisarea autorilor in functie de nume")
    void getByNumeAutor() {
        //arrange
        String numeAutor= "Autor";
        Utilizator utilizator= new Utilizator();
        Autor autor= new Autor(1,"Autor","Romania",utilizator);
        when(autorRepository.findByNumeAutor(numeAutor)).thenReturn(List.of(autor));

        //act
        List<Autor> rezultat=autorService.get(numeAutor, null);

        //assert
        assertNotNull(rezultat);
        assertEquals(1, rezultat.size());
        assertEquals(autor, rezultat.get(0));

        verify(autorRepository).findByNumeAutor(numeAutor);
        verify(autorRepository, never()).findByNumeAutorAndTaraOrigine(any(), any());
        verify(autorRepository, never()).findByTaraOrigine(any());
        verify(autorRepository, never()).findAll();
    }

    //---------------------------------------------------------------------------
    @Test
    @DisplayName("afisarea autorilor in functie de tara")
    void getByTaraOrigine() {
        //arrange
        String taraOrigine= "Romania";
        Utilizator utilizator= new Utilizator();
        Autor autor= new Autor(1,"Autor","Romania",utilizator);
        when(autorRepository.findByTaraOrigine(taraOrigine)).thenReturn(List.of(autor));

        //act
        List<Autor> rezultat=autorService.get(null, taraOrigine);

        //assert
        assertNotNull(rezultat);
        assertEquals(1, rezultat.size());
        assertEquals(autor, rezultat.get(0));

        verify(autorRepository).findByTaraOrigine(taraOrigine);
        verify(autorRepository, never()).findByNumeAutorAndTaraOrigine(any(), any());
        verify(autorRepository, never()).findBynumeAutor(any());
        verify(autorRepository, never()).findAll();
    }
    //---------------------------------------------------------------------------
    @Test
    @DisplayName("afisarea autorilor in functie de nume si tara")
    void getByNumesiTaraOrigine() {
        //arrange
        String taraOrigine= "Romania";
        String numeAutor= "Autor";
        Utilizator utilizator= new Utilizator();
        Autor autor= new Autor(1,"Autor","Romania",utilizator);
        when(autorRepository.findByNumeAutorAndTaraOrigine(numeAutor, taraOrigine)).thenReturn(List.of(autor));

        //act
        List<Autor> rezultat=autorService.get(numeAutor, taraOrigine);

        //assert
        assertNotNull(rezultat);
        assertEquals(1, rezultat.size());
        assertEquals(autor, rezultat.get(0));

        verify(autorRepository).findByNumeAutorAndTaraOrigine(numeAutor,taraOrigine);
        verify(autorRepository, never()).findByTaraOrigine( any());
        verify(autorRepository, never()).findBynumeAutor(any());
        verify(autorRepository, never()).findAll();
    }
    //---------------------------------------------------------------------------
    @Test
    @DisplayName("afisarea autorilor ")
    void getAll() {
        //arrange

        Utilizator utilizator= new Utilizator();
        Autor autor= new Autor(1,"Autor","Romania",utilizator);
        when(autorRepository.findAll()).thenReturn(List.of(autor));

        //act
        List<Autor> rezultat=autorService.get(null, null);

        //assert
        assertNotNull(rezultat);
        assertEquals(1, rezultat.size());

        verify(autorRepository).findAll();
    }
    //---------------------------------------------------------------------------
    @Test
    @DisplayName("cazul in care exista in baza un autor cu id ul x")
    void findById_daca_exista_autor() {
        //arrange
        Utilizator utilizator= new Utilizator();
        Autor autor= new Autor(1,"Autor","Romania",utilizator);
        when(autorService.findById(1L)).thenReturn(Optional.of(autor));
        //act
        Optional<Autor> rezultat= autorService.findById(1);
        //assets
        assertNotNull(rezultat);
        assertTrue(rezultat.isPresent());
        assertEquals(autor.getIdAutor(), rezultat.get().getIdAutor());
    }
    //---------------------------------------------------------------------------------------
    @Test
    @DisplayName("cazul in care nu exista in baza un autor cu id ul x")
    void findById_daca_nu_exista_autor() {
        //arrange
        when(autorRepository.findById(1L)).thenReturn(Optional.empty());
        //act
        Optional<Autor> rezultat= autorService.findById(1);
        //assets
        assertNotNull(rezultat);
        assertTrue(rezultat.isEmpty());

    }
}