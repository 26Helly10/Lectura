package proiect.proiect_java.mapper;

import org.springframework.stereotype.Component;
import proiect.proiect_java.dto.CreateAutorDto;
import proiect.proiect_java.model.Autor;

@Component
public class AutorMapper {
    public Autor createAutorDtotoAutor(CreateAutorDto createAutorDto){
        return new Autor(
                createAutorDto.getNumeAutor(),
                createAutorDto.getTaraOrigine(),
                createAutorDto.getUtilizator()
        );
    }
}
