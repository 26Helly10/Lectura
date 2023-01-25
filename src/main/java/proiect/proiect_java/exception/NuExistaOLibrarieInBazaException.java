package proiect.proiect_java.exception;

public class NuExistaOLibrarieInBazaException extends RuntimeException{

    public NuExistaOLibrarieInBazaException(){
        super("Nu exista in baza o librarie cu acest id");
    }
}
