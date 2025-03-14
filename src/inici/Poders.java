package inici;

import java.util.ArrayList;

public class Poders {
    static ArrayList<Altres.Poders>llistaPoders;

    public static void menu(){
        int opcio=-1;
        do {
            System.out.println("PODERS\n1. Crear\n2. Consultar\n3. Eliminar\n0. Eixir");
            opcio=teclat.Teclat.scInt();
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
        int opcio=teclat.Teclat.scInt();
        llistaPoders.remove(opcio);
    }

    public static void consultar(){
        for (int i=0;i<llistaPoders.size();i++){
            System.out.println(i+". "+llistaPoders.get(i));
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
