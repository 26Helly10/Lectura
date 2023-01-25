package proiect.proiect_java.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import proiect.proiect_java.exception.ClubDejaExistentException;
import proiect.proiect_java.exception.NuExistaOLibrarieInBazaException;
import proiect.proiect_java.exception.NuExistaUnClubInBazaExceltion;
import proiect.proiect_java.model.Autor;
import proiect.proiect_java.model.Club;
import proiect.proiect_java.model.Librarie;
import proiect.proiect_java.model.Utilizator;
import proiect.proiect_java.repository.ClubRepository;
import proiect.proiect_java.repository.LibrarieRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClubServiceTest {

    @InjectMocks
    private ClubService clubService;
    @Mock
    private ClubRepository clubRepository;

    @Mock
    private LibrarieService librarieService;

    @Test
    @DisplayName("nu exista in baza o librarie cu id x")
    void create_nu_exista_librarie() {
        //arrage
        Club club=new Club();
        Librarie librarie = new Librarie();
        club.setLibrarie(librarie);
        when(librarieService.findById(librarie.getIdLibrarie())).thenReturn(Optional.empty());

        //act
        NuExistaOLibrarieInBazaException exceltion =assertThrows(NuExistaOLibrarieInBazaException.class,
                ()-> clubService.create(club));

        //assert
        assertEquals("Nu exista in baza o librarie cu acest id", exceltion.getMessage());
        verify(librarieService).findById(librarie.getIdLibrarie());
        verify(clubRepository, times(0)).save(club);
        verifyNoInteractions(clubRepository);
    }

    //-----------------------------------------------------------------------------------------------
    @Test
    @DisplayName("exista deja un club cu numele x")
    void create_exista_club_cu_numele_dat() {
        //arrage
        Club club=new Club();
        Librarie librarie = new Librarie();
        librarie.setIdLibrarie(1);
        club.setNumeClub("Brotaceii");
        club.setLibrarie(librarie);
        Optional<Librarie> librarieOptional=Optional.of(librarie);
        when(librarieService.findById(librarie.getIdLibrarie())).thenReturn(librarieOptional);
        when(clubRepository.findBynumeClub(club.getNumeClub())).thenReturn(Optional.of(club));
        //act
        ClubDejaExistentException exceltion =assertThrows(ClubDejaExistentException.class,
                ()-> clubService.create(club));

        //assert
        assertEquals("Un club cu acelasi nume este deja existent.", exceltion.getMessage());

        verify(clubRepository, times(0)).save(club);

    }
//---------------------------------------------------------------------------------------------------
@Test
@DisplayName("Scenariu pozitiv:creare club")
void creare_club() {
    //Arrange
    Librarie librarie= new Librarie();
    Club club= new Club("Brotacei",librarie);
    Optional<Librarie> librarieOptional=Optional.of(librarie);
    when(librarieService.findById(librarie.getIdLibrarie())).thenReturn(librarieOptional);
    when(clubRepository.findBynumeClub(club.getNumeClub())).thenReturn(Optional.empty());
    Club clubSalvat= new Club(1,"Brotacei",librarie);
    when(clubRepository.save(club)).thenReturn(clubSalvat);
    //act
    Club rezultat =clubService.create(club);
    //Assert
    assertNotNull(rezultat);
    assertEquals(clubSalvat.getIdClub(), rezultat.getIdClub());
    assertEquals(clubSalvat.getNumeClub(), rezultat.getNumeClub());
    assertEquals(clubSalvat.getLibrarie(), rezultat.getLibrarie());
    verify(clubRepository).save(club);

}
    //-----------------------------------------------------------------------------------------------
    @Test
    @DisplayName("afisare a tuturor elementelor club in functie de nume")
    void getClubByNume() {
        //arrange
        String numeClub="brotacei";
        Librarie librarie= new Librarie();
        Club club= new Club(1,"brotacei",librarie);
        when(clubRepository.findByNumeClub(numeClub)).thenReturn(List.of(club));
        //act
        List<Club> rezultat =clubService.get(numeClub);
        //assets
        assertEquals(1, rezultat.size());
        assertEquals(club, rezultat.get(0));

        verify(clubRepository).findByNumeClub(numeClub);
        verify(clubRepository, never()).findAll();
    }
    //-----------------------------------------------------------------------------------------------
    @Test
    @DisplayName("afisare a tuturor elementelor club ")
    void getClub() {
        //arrange

        Librarie librarie= new Librarie();
        Club club= new Club(1,"brotacei",librarie);
        when(clubRepository.findAll()).thenReturn(List.of(club));
        //act
        List<Club> rezultat =clubService.get(null);
        //assets
        assertEquals(1, rezultat.size());
        assertEquals(club, rezultat.get(0));


        verify(clubRepository).findAll();
    }
    //---------------------------------------------------------------------------
    @Test
    @DisplayName("cazul in care exista in baza un Club cu id ul x")
    void findById_daca_exista_club() {
        //arrange
        Librarie librarie= new Librarie();
        Club club= new Club(1,"brotacei",librarie);
        when(clubRepository.findById(1L)).thenReturn(Optional.of(club));
        //act
        Optional<Club> rezultat= clubService.findById(1);
        //assets
        assertNotNull(rezultat);
        assertTrue(rezultat.isPresent());
        assertEquals(club.getIdClub(), rezultat.get().getIdClub());
    }
    //---------------------------------------------------------------------------------------
    @Test
    @DisplayName("cazul in care nu exista in baza un club cu id ul x")
    void findById_daca_nu_exista_club() {
        //arrange
        when(clubRepository.findById(1L)).thenReturn(Optional.empty());
        //act
        Optional<Club> rezultat= clubService.findById(1);
        //assets
        assertNotNull(rezultat);
        assertTrue(rezultat.isEmpty());

    }
}