package inici;

import teclat.Teclat;

import java.util.ArrayList;

public class Poders {
    static ArrayList<Altres.Poders>llistaPoders = new ArrayList<>();

    public static void menu(){
        int opcio=-1;
        do {
            System.out.println("PODERS\n1. Crear\n2. Consultar\n3. Eliminar\n0. Eixir");
            try {
                opcio = Teclat.scInt();
            }
            catch (Exception e){
                System.out.println("Opcio incorrecta");
                opcio=-1;
            }
            JocDeRol.clearConsole();
            switch (opcio){
                case 1:
                    crear();
                    break;
                case 2:
                    consultar();
                    break;
                case 3:
                    eliminar();
                    break;
                case 0:
                    System.out.println("Sortint de la gestio de poders");
                    break;
                default:
                    System.out.println("Opcio incorrecta");
                    break;
            }
        }while (opcio!=0);
    }
    public static void eliminar(){
        System.out.println("Quin poder vols eliminar?");
        consultar();
        int opcio=teclat.Teclat.scInt()-1;
        llistaPoders.remove(opcio);
    }

    public static void consultar(){
        int i=1;
        for (Altres.Poders poder : llistaPoders){
            System.out.println(i+" - "+poder);
            i++;
        }
    }

    public static void crear(){
        System.out.println("Nom del poder?");
        String nom=teclat.Teclat.scString();
        System.out.println("Punts d'atac del poder?");
        int pa=teclat.Teclat.scInt();
        System.out.println("Punts de defensa del poder?");
        int pd=teclat.Teclat.scInt();
        Altres.Poders poder = new Altres.Poders(nom,pa,pd);
        llistaPoders.add(poder);
    }
}
