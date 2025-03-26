package inici;
import Altres.Equip;
import personatges.Alien;
import personatges.Huma;
import personatges.Jugador;
import teclat.Teclat;

import java.util.Random;


public class JocDeRol {
    public static void main(String[] args) {
        clearConsole();
        Alien pepe = new Alien("Pepe", 100, 5, 100);
        Huma guillem = new Huma("Guillem", 10, 0, 100);
        Equip pacos = new Equip("Pacos");
        Jugadors.llistaJugadors.add(pepe);
        Jugadors.llistaJugadors.add(guillem);
        Equips.llistaEquips.add(pacos);
        int opcio = -1;
        while (opcio != 0){
            System.out.println("JOC DE ROL\n1. Configuracio\n2. Jugar\n0. Sortir");
            opcio=Teclat.scInt();
            clearConsole();
            switch (opcio){
                case 1:
                    menuConfiguracio();
                    break;
                case 2:
                    menuJugar();
                    break;
                case 0:
                    System.out.println("Sortint del joc de rol");
                    break;
                default:
                    System.out.println("Opcio incorrecta");
                    break;
            }
        }
    }


    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }



    public static void automatitzat(){
        while (Jugadors.llistaJugadors.size()>1){
            Random rand = new Random();
            int atacantRandom = rand.nextInt(Jugadors.llistaJugadors.size());
            int atacatRandom = rand.nextInt(Jugadors.llistaJugadors.size());
            if (atacantRandom!=atacatRandom){
                if (Jugadors.llistaJugadors.get(atacantRandom).getVides()>0){
                    Jugadors.llistaJugadors.remove(atacantRandom);
                } else if (Jugadors.llistaJugadors.get(atacatRandom).getVides()>0) {
                    Jugadors.llistaJugadors.remove(atacatRandom);
                }else {
                    Jugadors.llistaJugadors.get(atacantRandom).ataca(Jugadors.llistaJugadors.get(atacatRandom));
                }
            }
            if (Jugadors.llistaJugadors.size()==1){
                System.out.println("El guanyador es: "+Jugadors.llistaJugadors.getFirst());
            }
        }
    }

    public static void manual(){
        while (Jugadors.llistaJugadors.size()>1){
            for (int i = 0; i < Jugadors.llistaJugadors.size(); i++) {
                Jugador atacant = Jugadors.llistaJugadors.get(i);
                Jugador atacat = atacant;

                while (atacant.equals(atacat)) {
                    System.out.println(atacant + " a qui vols atacar?");
                    Jugadors.consultar();
                    atacant = Jugadors.llistaJugadors.get(Teclat.scInt() - 1);
                }
                // Comprovar que no hi ha ningun jugador eliminat
                comprovarVides(atacant,atacat);
                // Atac
                try {atacant.ataca(atacat);} catch (Exception _) {}
                // Comprovar que no hi ha ningun jugador eliminat
                comprovarVides(atacant,atacat);

                if (Jugadors.llistaJugadors.size()==1){
                    System.out.println("El guanyador es: "+Jugadors.llistaJugadors.getFirst());
                }
            }
        }
    }
    public static void comprovarVides(Jugador atacant, Jugador atacat){
        if (atacant.getVides()<=0){
            Jugadors.llistaJugadors.remove(atacant);
        }
        if (atacat.getVides()<=0){
            Jugadors.llistaJugadors.remove(atacat);
        }
    }

    static public void menuJugar(){
        System.out.println("Inici del joc\n1 - Automatitzat\n2 - Manual");
        int opcio = Teclat.scInt();
        switch (opcio){
            case 1:
                automatitzat();
                break;
            case 2:
                manual();
                break;
            default:
                System.out.println("Opcio incorrecta");
        }
    }

    static public void menuConfiguracio(){
        int opcio = -1;
        while (opcio != 0) {
            System.out.println("CONFIGURACIO\n1. Jugadors\n2. Equips\n3. Poders\n0. Sortir");
            opcio = Teclat.scInt();
            switch (opcio){
                case 1:
                    Jugadors.menu();
                    break;
                case 2:
                    Equips.menu();
                    break;
                case 3:
                    Poders.menu();
                    break;
                case 0:
                    System.out.println("Sortint de la configuracio");
                    break;
                default:
                    System.out.println("Opcio incorrecta");
                    break;
            }
        }

    }

}