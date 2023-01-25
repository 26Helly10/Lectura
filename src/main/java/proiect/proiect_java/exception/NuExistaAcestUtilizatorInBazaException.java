package proiect.proiect_java.exception;

public class NuExistaAcestUtilizatorInBazaException extends RuntimeException{
    public NuExistaAcestUtilizatorInBazaException(){
        super("Nu exista acest utilizator in baza");
    }
}
