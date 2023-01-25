package proiect.proiect_java.mapper;

import org.springframework.stereotype.Component;
import proiect.proiect_java.dto.CreateLibrarieDto;
import proiect.proiect_java.model.Librarie;

@Component
public class LibrarieMapper {
    public Librarie createLibrarieDtotoLibrarie(CreateLibrarieDto createLibrarieDto){
        return new Librarie(
                createLibrarieDto.getNumeLibrarie()
                );
    }
}
