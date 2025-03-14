package teclat;
import java.util.Scanner;

public class Teclat {
    static Scanner sc = new Scanner(System.in);
    static public String scString(){return sc.nextLine();}
    static public int scInt(){
        int resultat;
        resultat=sc.nextInt();sc.nextLine();
        return resultat;
    }
    static public char scChar(){return sc.nextLine().charAt(0);}
    static public void intro(){sc.nextLine();}
}
