package proiect.proiect_java.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import proiect.proiect_java.exception.NuExistaAcestUtilizatorInBazaException;
import proiect.proiect_java.exception.NuExistaUnClubInBazaExceltion;
import proiect.proiect_java.exception.UtilizatorDejaExistentException;
import proiect.proiect_java.model.Carte;
import proiect.proiect_java.model.Club;
import proiect.proiect_java.model.Librarie;
import proiect.proiect_java.model.Utilizator;
import proiect.proiect_java.repository.UtilizatorRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UtilizatorServiceTest {
    @InjectMocks
    private UtilizatorService utilizatorService;

    @Mock
    private ClubService clubService;

    @Mock
    UtilizatorRepository utilizatorRepository;

    //---------------------------------------------------------------------------------------
    @Test
    @DisplayName("Scenariu negativ: nu exista club")
    void creare_club_inexistent() {
        //Arrange
        Utilizator utilizator= new Utilizator();
        Club club= new Club();
        club.setIdClub(1);
        utilizator.setClub(club);
        when(clubService.findById(club.getIdClub())).thenReturn(Optional.empty());
        //act
        NuExistaUnClubInBazaExceltion exceltion=assertThrows(NuExistaUnClubInBazaExceltion.class,
                ()->utilizatorService.create(utilizator));
        //Assert
        assertEquals("Nu exista in baza un Club cu acest id ", exceltion.getMessage());
        verify(clubService).findById(club.getIdClub());
        verify(utilizatorRepository, times(0)).save(utilizator);
        verifyNoInteractions(utilizatorRepository);
    }
    //---------------------------------------------------------------------------------------
    @Test
    @DisplayName("Scenariu negativ: exista deja cineva cu adresa de email")
    void creare_utilizator_deja_in_baza() {
        //Arrange
        Utilizator utilizator= new Utilizator();
        Club club= new Club();
        utilizator.setClub(club);
        utilizator.setEmail("adresa@gmail.com");
        Optional<Club> clubOptional= Optional.of(club);

        when(clubService.findById(club.getIdClub()))
                .thenReturn(clubOptional);

        when(utilizatorRepository.findByEmail(utilizator.getEmail())).thenReturn(Optional.of(utilizator));

        //act
        UtilizatorDejaExistentException exceltion=assertThrows(UtilizatorDejaExistentException.class,
                ()->utilizatorService.create(utilizator));
        //Assert
        assertEquals("Exista in baza un utilizator cu aceasta adresa de email", exceltion.getMessage());
        verify(utilizatorRepository, times(0)).save(utilizator);

    }

    //---------------------------------------------------------------------------------------
    @Test
    @DisplayName("Scenariu utilizator cu acelasi mail in baza")
    void creare_utilizator() {
        //Arrange
        Utilizator utilizator= new Utilizator();
        Club club= new Club();
        utilizator.setClub(club);
        utilizator.setEmail("adresa@gmail.com");
        Optional<Club> clubOptional= Optional.of(club);

        when(clubService.findById(club.getIdClub()))
                .thenReturn(clubOptional);
        when(utilizatorRepository.findByEmail(utilizator.getEmail())).thenReturn(Optional.of(utilizator));

        //act
        UtilizatorDejaExistentException exceltion=assertThrows(UtilizatorDejaExistentException.class,
                ()->utilizatorService.create(utilizator));
        //Assert
        assertEquals("Exista in baza un utilizator cu aceasta adreza de email", exceltion.getMessage());
        verify(utilizatorRepository, times(0)).save(utilizator);

    }
 //---------------------------------------------------------------------------------------
    @Test
    @DisplayName("Utilizator creat")
    void create_scenariu_pozitiv() {
        //arrange
        Club club= new Club();
        Utilizator utilizator= new Utilizator("Eugen","adresa@email.com", club);
        Optional<Club> clubOptional= Optional.of(club);
        when(clubService.findById(club.getIdClub()))
                .thenReturn(clubOptional);
        when(utilizatorRepository.findByEmail(utilizator.getEmail())).thenReturn(Optional.empty());
        Utilizator utilizatorSalvat= new Utilizator(1, "Eugen","adresa@email.com", club);
        when(utilizatorRepository.save(utilizator)).thenReturn(utilizatorSalvat);
        //act
        Utilizator rezultat= utilizatorService.create(utilizator);
        //assert
        assertNotNull(rezultat);
        assertEquals(utilizatorSalvat.getIdUtilizator(), rezultat.getIdUtilizator());
        assertEquals(utilizatorSalvat.getNumeUtilizator(), rezultat.getNumeUtilizator());
        assertEquals(utilizatorSalvat.getEmail(), rezultat.getEmail());
        assertEquals(utilizatorSalvat.getClub(), rezultat.getClub());
        verify(utilizatorRepository).findByEmail(utilizator.getEmail());
        verify(utilizatorRepository).save(utilizator);
    }
    //---------------------------------------------------------------------------------------
    @Test
    @DisplayName("Scenariu negativ:nu exista in baza un utilizator cu aceast id ")
    void uptade_id_inexistent() {
        //arrange
        Club club= new Club();
        Utilizator utilizatorVechi= new Utilizator(1, "Eugennn","adresa@email.com", club);
        when(utilizatorRepository.findById(utilizatorVechi.getIdUtilizator())).thenReturn(Optional.empty());
        //act
        NuExistaAcestUtilizatorInBazaException exception=assertThrows(NuExistaAcestUtilizatorInBazaException.class,
                ()-> utilizatorService.uptade(utilizatorVechi));
        //assert
        verify(utilizatorRepository).findById(utilizatorVechi.getIdUtilizator());
        verify(utilizatorRepository,times(0)).findByEmail(utilizatorVechi.getEmail());
        verify(utilizatorRepository,times(0)).save(utilizatorVechi);

    }
    //---------------------------------------------------------------------------------------
    @Test
    @DisplayName("Scenariu negativ: exista in baza un utilizator cu aceasta adresa de email ")
    void uptade_email_existent() {
        //arrangr
        Club club= new Club();
        Utilizator utilizatorVechi= new Utilizator(1, "Eugennn","adresa@email.com", club);
        Utilizator utilizatorNou= new Utilizator(1, "Eugen","adresa2@email.com", club);
        Utilizator altUtilizator= new Utilizator(2, "Eugen","adresa2@email.com", club);

        when(utilizatorRepository.findById(utilizatorNou.getIdUtilizator())).thenReturn(Optional.of(utilizatorVechi));
        when(utilizatorRepository.findByEmailAndIdUtilizatorNot(utilizatorNou.getEmail(), utilizatorNou.getIdUtilizator()))
                .thenReturn(Optional.of(altUtilizator));
        //act
        UtilizatorDejaExistentException exception= assertThrows(UtilizatorDejaExistentException.class,
                ()->utilizatorService.uptade(utilizatorNou));
        //assert
        verify(utilizatorRepository).findById(utilizatorNou.getIdUtilizator());
        verify(utilizatorRepository).findByEmailAndIdUtilizatorNot(utilizatorNou.getEmail(), utilizatorNou.getIdUtilizator());
        verify(utilizatorRepository, times(0)).save(utilizatorNou);

    }
    //---------------------------------------------------------------------------------------
    @Test
    @DisplayName("Scenariu negativ: nu exista in baza un club cu acel id ")
    void uptade_clun_inexistent() {
        //arrangr
        Club club= new Club();
        Utilizator utilizatorVechi= new Utilizator(1, "Eugennn","adresa@email.com", club);
        Utilizator utilizatorNou= new Utilizator(1, "Eugen","adresa2@email.com", club);

        when(utilizatorRepository.findById(utilizatorNou.getIdUtilizator())).thenReturn(Optional.of(utilizatorVechi));
        when(utilizatorRepository.findByEmailAndIdUtilizatorNot(utilizatorNou.getEmail(), utilizatorNou.getIdUtilizator()))
                .thenReturn(Optional.empty());

        when(clubService.findById(club.getIdClub())).thenReturn(Optional.empty());
        //act
        NuExistaUnClubInBazaExceltion exception= assertThrows(NuExistaUnClubInBazaExceltion.class,
                ()->utilizatorService.uptade(utilizatorNou));
        //assert
        assertEquals("Nu exista in baza un Club cu acest id ", exception.getMessage());
        verify(utilizatorRepository).findById(utilizatorNou.getIdUtilizator());
        verify(utilizatorRepository).findByEmailAndIdUtilizatorNot(utilizatorNou.getEmail(), utilizatorNou.getIdUtilizator());
        verify(utilizatorRepository, times(0)).save(utilizatorNou);

    }

    //---------------------------------------------------------------------------------------
    @Test
    @DisplayName("Scenariu pozitiv: uptade utilizator ")
    void uptade_utilizator() {
        //arrangr
        Club club= new Club();
        Utilizator utilizatorVechi= new Utilizator(1, "Eugennn","adresa@email.com", club);
        Utilizator utilizatorNou= new Utilizator(1, "Eugen","adresa2@email.com", club);

        when(utilizatorRepository.findById(utilizatorNou.getIdUtilizator())).thenReturn(Optional.of(utilizatorVechi));
        when(utilizatorRepository.findByEmailAndIdUtilizatorNot(utilizatorNou.getEmail(), utilizatorNou.getIdUtilizator()))
                .thenReturn(Optional.empty());

        when(clubService.findById(club.getIdClub())).thenReturn(Optional.of(club));

        when(utilizatorRepository.save(utilizatorNou)).thenReturn(utilizatorNou);
        //act
        Utilizator rezultat= utilizatorService.uptade(utilizatorNou);
        //assert
        assertNotNull(rezultat);
        assertEquals(utilizatorNou.getIdUtilizator(), rezultat.getIdUtilizator());
        assertEquals(utilizatorNou.getNumeUtilizator(), rezultat.getNumeUtilizator());
        assertEquals(utilizatorNou.getEmail(), rezultat.getEmail());
        assertEquals(utilizatorNou.getClub(), rezultat.getClub());
        verify(utilizatorRepository).findById(utilizatorNou.getIdUtilizator());
        verify(utilizatorRepository).findByEmailAndIdUtilizatorNot(utilizatorNou.getEmail(), utilizatorNou.getIdUtilizator());
        verify(utilizatorRepository).save(utilizatorNou);

    }
    //---------------------------------------------------------------------------------------
    @Test
    @DisplayName("Afisarea utilizatorilor din baza")
    void getUtilizator() {
        //arrange
        Club club= new Club();
        Utilizator utilizatorVechi= new Utilizator(1, "Eugennn","adresa@email.com", club);
        when(utilizatorRepository.findAll()).thenReturn(List.of(utilizatorVechi));
        //act
        List<Utilizator> rezultat =utilizatorService.getUtilizator();
        //assets
        assertNotNull(rezultat);
        assertEquals(1, rezultat.size());
        verify(utilizatorRepository).findAll();
    }
    //---------------------------------------------------------------------------------------
    @Test
    @DisplayName("cazul in care exista in baza un utilizator cu id ul x")
    void findById_daca_exista_utilizatorul() {
        //arrange
        Club club= new Club();
        Utilizator utilizatorVechi= new Utilizator(1, "Eugennn","adresa@email.com", club);
        when(utilizatorService.findById(1L)).thenReturn(Optional.of(utilizatorVechi));
        //act
        Optional<Utilizator> rezultat= utilizatorService.findById(1);
        //assets
        assertNotNull(rezultat);
        assertTrue(rezultat.isPresent());
        assertEquals(utilizatorVechi.getIdUtilizator(), rezultat.get().getIdUtilizator());
    }
    //---------------------------------------------------------------------------------------
    @Test
    @DisplayName("cazul in care nu exista in baza un utilizator cu id ul x")
    void findById_daca_nu_exista_utilizator() {
        //arrange
        when(utilizatorRepository.findById(1L)).thenReturn(Optional.empty());
        //act
        Optional<Utilizator> rezultat= utilizatorService.findById(1);
        //assets
        assertNotNull(rezultat);
        assertTrue(rezultat.isEmpty());

    }
}