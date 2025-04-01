package org.inici;


import org.teclat.*;

import java.util.ArrayList;

public class Poders {
    static ArrayList<org.Altres.Poders>llistaPoders = new ArrayList<>();

    public static void menu(){
        int opcio;
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
        int opcio = org.teclat.Teclat.scInt()-1;
        llistaPoders.remove(opcio);
    }

    public static void consultar(){
        int i=1;
        for (org.Altres.Poders poder : llistaPoders){
            System.out.println(i+" - "+poder);
            i++;
        }
    }

    public static void crear(){
        System.out.println("Nom del poder?");
        String nom=Teclat.scString();
        System.out.println("Punts d'atac del poder?");
        int pa=Teclat.scInt();
        System.out.println("Punts de defensa del poder?");
        int pd=Teclat.scInt();
        org.Altres.Poders poder = new org.Altres.Poders(nom,pa,pd);
        llistaPoders.add(poder);
    }
}
