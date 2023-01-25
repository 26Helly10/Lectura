package proiect.proiect_java.mapper;

import org.springframework.stereotype.Component;
import proiect.proiect_java.dto.CreateCarteDto;
import proiect.proiect_java.dto.UpdateCarteDto;
import proiect.proiect_java.model.Carte;

@Component
public class CarteMapper {
    public Carte createCarteDtotoCarte(CreateCarteDto createCarteDto){
        return new Carte(
                createCarteDto.getNumeCarte(),
                createCarteDto.getGenCarte(),
                createCarteDto.getEditura(),
                createCarteDto.getSteluteCarte(),
                createCarteDto.getCitit(),
                createCarteDto.getInBiblioteca(),
                createCarteDto.getAutor(),
                createCarteDto.getUtilizator()
        );
    }

    public Carte updateCarteDtotoCarte(UpdateCarteDto updateCarteDto){
        return new Carte(
                updateCarteDto.getIdCarte(),
                updateCarteDto.getNumeCarte(),
                updateCarteDto.getGenCarte(),
                updateCarteDto.getEditura(),
                updateCarteDto.getSteluteCarte(),
                updateCarteDto.getCitit(),
                updateCarteDto.getInBiblioteca(),
                updateCarteDto.getAutor(),
                updateCarteDto.getUtilizator()
        );
    }
}
