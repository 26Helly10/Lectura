package proiect.proiect_java.exception;

public class UtilizatorDejaExistentException extends RuntimeException{

    public UtilizatorDejaExistentException(){
        super("Exista in baza un utilizator cu aceasta adresa de email");
    }

}
