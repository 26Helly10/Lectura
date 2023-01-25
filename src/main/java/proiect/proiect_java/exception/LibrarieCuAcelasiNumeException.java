package proiect.proiect_java.exception;

public class LibrarieCuAcelasiNumeException extends RuntimeException{
    public LibrarieCuAcelasiNumeException(){
        super("Exista in baza o librarie cu acelasi nume");
    }
}
