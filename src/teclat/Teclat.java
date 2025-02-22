package teclat;
import java.util.Scanner;

public class Teclat {
    static Scanner sc = new Scanner(System.in);
    public String scString(){return sc.nextLine();}
    public int scInt(){return  sc.nextInt();}
    public char scChar(){return sc.nextLine().charAt(0);}
    public void intro(){sc.nextLine();}
}
