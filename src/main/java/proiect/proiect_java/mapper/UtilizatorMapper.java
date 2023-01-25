package proiect.proiect_java.mapper;

import org.springframework.stereotype.Component;
import proiect.proiect_java.dto.CreateUtilizatorDto;
import proiect.proiect_java.dto.UpdateUtilizatorDto;
import proiect.proiect_java.model.Utilizator;

@Component
public class UtilizatorMapper {

    public Utilizator createUtilizatorDtotoUtilizator(CreateUtilizatorDto createUtilizatorDto){
        return new Utilizator(
                createUtilizatorDto.getNumeUtilizator(),
                createUtilizatorDto.getEmail(),
                createUtilizatorDto.getClub()
        );
    }

    public Utilizator updateUtilizatorDtotoUtilizator(UpdateUtilizatorDto updateUtilizatorDto){
        return new Utilizator(updateUtilizatorDto.getIdUtilizator(),
                updateUtilizatorDto.getNumeUtilizator(),
                updateUtilizatorDto.getEmail(),
                updateUtilizatorDto.getClub());
    }
}
