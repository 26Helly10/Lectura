package proiect.proiect_java.exception;

public class ClubDejaExistentException extends RuntimeException{

 public ClubDejaExistentException(){
     super("Un club cu acelasi nume este deja existent.");
 }
}
