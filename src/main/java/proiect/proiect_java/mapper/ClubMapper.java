package proiect.proiect_java.mapper;

import org.springframework.stereotype.Component;
import proiect.proiect_java.dto.CreateClubDto;
import proiect.proiect_java.model.Club;

@Component
public class ClubMapper {
    public Club createClubDtotoClub(CreateClubDto createClubDto){
        return new Club(
                createClubDto.getNumeClub(),
                createClubDto.getLibrarie());

    }
}
