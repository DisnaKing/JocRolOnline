package org.inici;

import org.Altres.Equip;
import org.teclat.Teclat;
import java.util.ArrayList;

import static org.inici.JocDeRol.pos;

public class Equips {
    static public ArrayList<Equip>llistaEquips = new ArrayList<>();

    public static void menu(){
        String opcio;
        do {
            System.out.println("EQUIPS\n1. Crear\n2. Consultar\n3. Eliminar\n0. Eixir");
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
                    System.out.println("Sortint de la gestio d'equips");
                    break;
                default:
                    System.out.println("Opcio incorrecta");
                    break;
            }
        }while (!opcio.equals("0"));
    }

    public static void  eliminar(){
        System.out.println("Quin equip vols eliminar?");
        consultar();
        System.out.println("Introdueix el nom:");
        String nom = Teclat.scString();
        try {
            llistaEquips.remove(pos(nom,'P'));
        } catch (Exception e) {
            Teclat.nl();
            System.out.println("Equip no trobat");
            incorrecto(3);
        }
    }

    public static void consultar(){
        int i=1;
        for (Equip equip : llistaEquips){
            System.out.println(i+" - "+equip);
            i++;
        }
    }

    public static void crear(){
        System.out.println("Nom de l'equip?");
        String nom=Teclat.scString();
        try {
            llistaEquips.add(new Equip(nom));
        } catch (Exception e) {
            incorrecto(3);
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
