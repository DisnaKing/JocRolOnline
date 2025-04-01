package org.inici;

import org.personatges.Alien;
import org.personatges.Huma;
import org.personatges.Jugador;
import org.teclat.Teclat;
import org.Altres.*;

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
            try {
                opcio = Teclat.scInt();
            }
            catch (Exception e){
                System.out.println("Opcio incorrecta");
                opcio=-1;
            }
            clearConsole();
            switch (opcio){
                case 1:
                    menuConfiguracio();
                    break;
                case 2:
                    partida();
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
    static public boolean volGuardar(){
        System.out.println("Vols guardar la partida? (S/N)");
        Character opcio = Teclat.scChar();
        if (opcio.equals('S')){
            Partida.guardar();
            return true;
        } else if (opcio.equals('N')){
            return false;
        } else {
            System.out.println("Opcio incorrecta");
            return volGuardar();
        }
    }
    static public void menuJugar(){
        System.out.println("Inici del joc\n1 - Automatitzat\n2 - Manual");
        int opcio;
        try {
            opcio = Teclat.scInt();
        }
        catch (Exception e){
            System.out.println("Opcio incorrecta");
            opcio=-1;
        }
        switch (opcio){
            case 1:
                automatitzat();
                if (volGuardar()){Partida.guardar();}
                break;
            case 2:
                manual();
                if (volGuardar()){Partida.guardar();}
                break;
            default:
                System.out.println("Opcio incorrecta");
                break;
        }
    }
    static  public  void partida(){
        System.out.println("1- Nova Partida\n2- Carregar Partida");
        int partida;
        try {
            partida = Teclat.scInt();
        }
        catch (Exception e){
            System.out.println("Opcio incorrecta");
            partida=-1;
        }
        switch (partida){
            case 1:
                menuJugar();
                break;
            case 2:
                System.out.println("Carregar partida");
                Partida.carregar();
                menuJugar();
                break;
            default:
                System.out.println("Opcio incorrecta");
                break;
        }
    }
    static public void menuConfiguracio(){
        int opcio = -1;
        while (opcio != 0) {
            System.out.println("CONFIGURACIO\n1. Jugadors\n2. Equips\n3. Poders\n0. Sortir");
            try {
                opcio = Teclat.scInt();
            }
            catch (Exception e){
                System.out.println("Opcio incorrecta");
                opcio=-1;
            }
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