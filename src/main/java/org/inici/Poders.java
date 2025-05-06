package org.inici;


import org.teclat.*;

import java.util.ArrayList;

import static org.inici.JocDeRol.pos;

public class Poders {
    static public ArrayList<org.Altres.Poders>llistaPoders = new ArrayList<>();

    public static void menu(){
        String opcio;
        do {
            System.out.println("PODERS\n1. Crear\n2. Consultar\n3. Eliminar\n0. Eixir");
            opcio = Teclat.scString();
            switch (opcio){
                case "1":
                    crear();
                    break;
                case "2":
                    consultar();
                    break;
                case "3":
                    eliminar();
                    break;
                case "0":
                    System.out.println("Sortint de la gestio de poders");
                    break;
                default:
                    System.out.println("Opcio incorrecta");
                    break;
            }
        }while (!opcio.equals("0"));
    }
    public static void eliminar(){
        System.out.println("Quin poder vols eliminar?");
        consultar();
        System.out.println("Introdueix el nom:");
        String nom = Teclat.scString();
        try {
            llistaPoders.remove(pos(nom,'P'));
        } catch (Exception e) {
            Teclat.nl();
            System.out.println("Poder no trobat");
            incorrecto(3);
        }
    }

    public static void consultar(){
        int i=1;
        for (org.Altres.Poders poder : llistaPoders){
            System.out.println(i+" - "+poder);
            i++;
        }
    }

    public static void crear(){
        try {
            System.out.println("Nom del poder?");
            String nom = Teclat.scString();
            System.out.println("Punts d'atac del poder?");
            int pa = Teclat.scInt();
            System.out.println("Punts de defensa del poder?");
            int pd = Teclat.scInt();
            org.Altres.Poders poder = new org.Altres.Poders(nom, pa, pd);
            llistaPoders.add(poder);
        } catch (Exception e) {
            incorrecto(1);
        }
    }
    public static void incorrecto(int funcion){
        Teclat.nl(); // Nova línia
        System.out.println("Opcio incorrecta introduida, vol tornar a provar? (S)");
        char siOno = Teclat.scChar();

        // Si l'usuari vol reintentar, cridem al mètode corresponent
        if (siOno == 'S' || siOno == 's') {
            switch (funcion){
                case 1:
                    crear(); // Reintentar crear
                    break;
                case 3:
                    eliminar(); // Reintentar eliminar
                    break;
            }
        }
    }

}
