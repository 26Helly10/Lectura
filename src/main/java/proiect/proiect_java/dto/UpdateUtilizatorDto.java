package proiect.proiect_java.dto;

import proiect.proiect_java.model.Club;

public class UpdateUtilizatorDto extends CreateUtilizatorDto{
    private long idUtilizator;

    public UpdateUtilizatorDto() {
    }

    public UpdateUtilizatorDto(String numeUtilizator, String email, Club club, long idUtilizator ){
        super(numeUtilizator,email,club);
        this.idUtilizator=idUtilizator;
    }

    public long getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(long idUtilizator) {
        this.idUtilizator = idUtilizator;
    }
}
