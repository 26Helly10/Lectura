package proiect.proiect_java.exception;

public class AutorCuAcelasiNumeException extends RuntimeException{
    public AutorCuAcelasiNumeException(){
        super("Exista deja un autor cu acelasi nume.");
    }
}
