package proiect.proiect_java.exception;

public class NuExistaUnClubInBazaExceltion extends RuntimeException{

    public NuExistaUnClubInBazaExceltion(){
        super("Nu exista in baza un Club cu acest id ");
    }
}
