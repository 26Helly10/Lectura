package proiect.proiect_java.exception;


public class CarteDejaExistentaInBazaException extends RuntimeException {
    public CarteDejaExistentaInBazaException(){
        super("Exista deja o carte cu acelasi nume si de la aceeasi editura.");
    }
}
