package proiect.proiect_java.exception;

public class CarteInexistentaException extends RuntimeException{
    public CarteInexistentaException(){
        super("nu exista o carte cu acest id");
    }
}
