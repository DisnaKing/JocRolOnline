package inici;

import Altres.Equip;
import teclat.Teclat;
import java.util.ArrayList;

public class Equips {
    static ArrayList<Equip>llistaEquips;

    public static void menu(){
        int opcio=-1;
        do {
            System.out.println("EQUIPS\n1. Crear\n2. Consultar\n3. Eliminar\n0. Eixir");
            opcio=Teclat.scInt();
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
        int opcio=Teclat.scInt();
        llistaEquips.remove(opcio);
    }

    public static void consultar(){
        for (int i=0;i<llistaEquips.size();i++){
            System.out.println(i+". "+llistaEquips.get(i));
        }
    }

    public static void crear(){
        System.out.println("Nom de l'equip?");
        String nom=Teclat.scString();
        llistaEquips.add(new Equip(nom));
    }

}
