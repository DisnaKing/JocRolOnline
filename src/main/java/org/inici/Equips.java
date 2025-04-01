package inici;

import Altres.Equip;
import personatges.Jugador;
import teclat.Teclat;
import java.util.ArrayList;

public class Equips {
    static ArrayList<Equip>llistaEquips = new ArrayList<>();

    public static void menu(){
        int opcio;
        do {
            System.out.println("EQUIPS\n1. Crear\n2. Consultar\n3. Eliminar\n0. Eixir");
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
                    System.out.println("Sortint de la gestio d'equips");
                    break;
                default:
                    System.out.println("Opcio incorrecta");
                    break;
            }
        }while (opcio!=0);
    }

    public static void  eliminar(){
        System.out.println("Quin equip vols eliminar?");
        consultar();
        int opcio=Teclat.scInt()-1;
        llistaEquips.remove(opcio);
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
        llistaEquips.add(new Equip(nom));
    }

}
