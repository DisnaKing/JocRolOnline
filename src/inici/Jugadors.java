package inici;

import personatges.Alien;
import personatges.Guerrer;
import personatges.Huma;
import personatges.Jugador;
import teclat.Teclat;

import java.util.ArrayList;

public class Jugadors {
    static ArrayList<Jugador> llistaJugadors = new ArrayList<>();

    public static void menu(){
        int opcio;
        do {
            System.out.println("JUGADORS\n1. Crear\n2. Consultar\n3. Eliminar\n 4. Assignar a equip\n5. Llevar d'equip\n6. Assignar poder\n0. Eixir");
            opcio=Teclat.scInt();
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
                case 4:
                    assignarAEquip();
                    break;
                case 5:
                    llevarDEquip();
                    break;
                case 6:
                    assignarPoder();
                    break;
                case 0:
                    System.out.println("Sortint de la gestio de jugadors");
                    break;
                default:
                    System.out.println("Opcio incorrecta");
                    break;
            }
        }while (opcio!=0);
    }

    public static void assignarPoder(){
        System.out.println("Quin jugador vols assignar un poder?");
        consultar();
        int opcio=Teclat.scInt()-1;
        System.out.println("Quin poder vols assignar al jugador?");
        Poders.consultar();
        int opcioPoder=Teclat.scInt()-1;
        llistaJugadors.get(opcio).getPoders().add(Poders.llistaPoders.get(opcioPoder));
    }

    // TODO - solucionar stackOverflowError
    public static void llevarDEquip(){
        System.out.println("Quin jugador vols llevar de l'equip?");
        consultar();
        int opcio=Teclat.scInt()-1;
        llistaJugadors.get(opcio).getEquip().lleva(llistaJugadors.get(opcio));
    }


    // TODO - solucionar stackOverflowError
    public static void assignarAEquip(){
        if (Equips.llistaEquips.isEmpty()){
            System.out.println("No hi ha equips creats");
        }else {
            System.out.println("Quin jugador vols assignar a un equip?");
            consultar();
            int opcio=Teclat.scInt()-1;
            System.out.println("A quin equip vols assignar el jugador?");
            Equips.consultar();
            int opcioEquip=Teclat.scInt()-1;
            llistaJugadors.get(opcio).setEquip(Equips.llistaEquips.get(opcioEquip));
        }
    }

    public static void eliminar(){
        System.out.println("Quin jugador vols eliminar?");
        consultar();
        int opcio=Teclat.scInt()-1;
        llistaJugadors.remove(opcio);
        System.out.println("Jugador eliminat\n");
    }

    public static void consultar(){
        int i=1;
        for (Jugador jugador : llistaJugadors){
            System.out.println(i+" - "+jugador);
            i++;
        }
    }

    public static void crear(){
        int videsInicials=200;
        System.out.println("Tipus de jugador (H,G,A): ");
        String tipus=Teclat.scString();
        tipus=tipus.toUpperCase();
        System.out.println("Nom del jugador:");
        String nom=Teclat.scString();
        System.out.println("Punts d'atac:");
        int pa=Teclat.scInt();
        int pd = 100-pa;
        if (pd<0){
            pd=0;
        }

        switch (tipus){
            case "H":
                Huma nowHuma = new Huma(nom,pa,pd,videsInicials);
                if (! llistaJugadors.contains(nowHuma)){
                    llistaJugadors.add(nowHuma);
                }

                break;
            case "G":
                Guerrer nowGuerrer = new Guerrer(nom,pa,pd,videsInicials);
                if (! llistaJugadors.contains(nowGuerrer)){
                    llistaJugadors.add(nowGuerrer);
                }
                break;
            case "A":
                Alien nowAlien = new Alien(nom,pa,pd,videsInicials);
                if (! llistaJugadors.contains(nowAlien)){
                    llistaJugadors.add(nowAlien);
                }
                break;
            default:
                System.out.println("Tipus de jugador incorrecte");
                break;
        }

    }
}
