package proiect.proiect_java.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import proiect.proiect_java.exception.LibrarieCuAcelasiNumeException;
import proiect.proiect_java.model.Librarie;
import proiect.proiect_java.repository.LibrarieRepository;

import java.sql.Driver;
import java.util.Optional;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibrarieServiceTest {

    @Mock
    private LibrarieRepository librarieRepository;

    @InjectMocks
    private LibrarieService librarieService;

    @Test
    @DisplayName("Creare entitate librarie cand o librarie cu acelasi nume exista")
    void create_scenariu_negativ() {
        //arrange
        Librarie librarie= new Librarie();
        librarie.setNumeLibrarie("Panseluta");
        when(librarieRepository.findByNumeLibrarie(librarie.getNumeLibrarie()))
                .thenReturn(Optional.of(librarie));
        //act
        LibrarieCuAcelasiNumeException exception = assertThrows(LibrarieCuAcelasiNumeException.class,
                    ()-> librarieService.create(librarie));
        //assert
        assertEquals("Exista in baza o librarie cu acelasi nume", exception.getMessage());

        verify(librarieRepository, times(0)).save(librarie);

    }
    //--------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Creare entitate librarie cand o librarie cu acelasi nume nu exista")
    void create_scenariu_pozitiv() {
        //arrange
        Librarie librarie= new Librarie("Panseluta");
        when(librarieRepository.findByNumeLibrarie(librarie.getNumeLibrarie()))
                .thenReturn(Optional.empty());
        Librarie librarieSalvata= new Librarie(1,"Panseluta");
        when(librarieRepository.save(librarie)).thenReturn(librarieSalvata);
        //act
        Librarie rezultat= librarieService.create(librarie);
        //assert
        assertNotNull(rezultat);
        assertEquals(librarieSalvata.getIdLibrarie(), rezultat.getIdLibrarie());
        assertEquals(librarieSalvata.getNumeLibrarie(), rezultat.getNumeLibrarie());
        verify(librarieRepository).findByNumeLibrarie(librarie.getNumeLibrarie());
        verify(librarieRepository).save(librarie);

    }
    //-----------------------------------------------------------------------------------------------
    @Test
    @DisplayName("afisare a tuturor elementelor librarie")
    void getLibrarie() {
        //arrange
        Librarie librarie= new Librarie(1,"Art");
        when(librarieRepository.findAll()).thenReturn(List.of(librarie));
        //act
        List<Librarie> rezultat =librarieService.getLibrarie();
        //assets
        assertNotNull(rezultat);
        assertEquals(1, rezultat.size());
        verify(librarieRepository).findAll();
    }

    @Test
    @DisplayName("cazul in care exista in baza o librarie cu id ul x")
    void findById_daca_exista_libraria() {
        //arrange
        Librarie librarie= new Librarie();
        librarie.setIdLibrarie(1);
        when(librarieRepository.findById(1L)).thenReturn(Optional.of(librarie));
        //act
        Optional<Librarie> rezultat= librarieService.findById(1);
        //assets
        assertNotNull(rezultat);
        assertTrue(rezultat.isPresent());
        assertEquals(librarie.getIdLibrarie(), rezultat.get().getIdLibrarie());
    }
    @Test
    @DisplayName("cazul in care nu exista in baza o librarie cu id ul x")
    void findById_daca_nu_exista_libraria() {
        //arrange
        when(librarieRepository.findById(1L)).thenReturn(Optional.empty());
        //act
        Optional<Librarie> rezultat= librarieService.findById(1);
        //assets
        assertNotNull(rezultat);
        assertTrue(rezultat.isEmpty());

    }
}