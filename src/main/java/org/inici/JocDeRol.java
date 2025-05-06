package org.inici;

import org.Altres.Equip;
import org.Altres.Partida.Partida;

import org.personatges.Alien;
import org.personatges.Guerrer;
import org.personatges.Huma;
import org.personatges.Jugador;
import org.teclat.Teclat;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe principal que gestiona el joc de rol.
 * Permet configurar jugadors, equips i poders, així com jugar partides
 * de forma manual o automatitzada.
 */
public class JocDeRol {

    /**
     * Mètode principal que inicia el joc.
     * Mostra un menú principal amb les opcions de configuració, jugar i eixir.
     *
     * @param args Arguments de línia de comandaments (no utilitzats)
     */
    public static void main(String[] args) {
        //menuPartida();

        // TODO Arreglar Cargar partida
        prova();
        partida();
    }

    public  static void prova(){
        Alien rob = new Alien("paco", 20, 5, 100);
        Huma humano = new Huma("rob", 20, 5, 100);
        Guerrer guerrer = new Guerrer("mac", 20, 5, 100);
        Jugadors.llistaJugadors.add(rob);
        Jugadors.llistaJugadors.add(humano);
        Jugadors.llistaJugadors.add(guerrer);
    }

    public static void menuPartida(){
        String opcio = "-1";
        while (!opcio.equals("0")) {
            // Menú principal del joc
            System.out.println("JOC DE ROL\n1. Configuracio\n2. Jugar\n0. Sortir");
            opcio = Teclat.scString();

            switch (opcio) {
                case "1":
                    menuConfiguracio(); // Accés a la configuració
                    break;
                case "2":
                    menuJugar(); // Accés al mode de joc
                    break;
                case "0":
                    System.out.println("Sortint de la partida");
                    break;
                default:
                    System.out.println("Opcio incorrecta");
                    break;
            }
        }
    }

    public static ArrayList<Jugador> clonarLista(ArrayList<Jugador>jugadorsTotals){
        ArrayList<Jugador> jugadorsPartida = new ArrayList<>();

        for (Jugador p : jugadorsTotals) {
            jugadorsPartida.add(p.clone());
        }

        return jugadorsPartida;
    }

    /**
     * Funció per jugar de forma automatitzada.
     * Els jugadors ataquen aleatòriament fins que només en queda un.
     * El joc selecciona automàticament atacants i objectius.
     */
    public static void automatitzat() {
        // Llista per emmagatzemar jugadors eliminats
        ArrayList<Jugador> jugadorsMorts = new ArrayList<>();
        ArrayList<Jugador> jugadorsPartida = clonarLista(Jugadors.llistaJugadors);

        // Continuem fins que només quedi un jugador amb vida
        while (jugadorsPartida.size()-1 != jugadorsMorts.size()) {
            Random rand = new Random();
            Jugador atacant, atacat;
            int atacantRandom, atacatRandom;

            // Seleccionem atacant aleatori (que encara estigui viu)
            do {
                atacantRandom = rand.nextInt(jugadorsPartida.size());
                atacant = jugadorsPartida.get(atacantRandom);
            } while (jugadorsMorts.contains(atacant));

            // Seleccionem objectiu aleatori (diferent de l'atacant i viu)
            do {
                atacatRandom = rand.nextInt(jugadorsPartida.size());
                atacat = jugadorsPartida.get(atacatRandom);
            } while (jugadorsMorts.contains(atacat) || atacant.equals(atacat));

            // Realitzem l'atac
            try {
                atacant.ataca(atacat);
            } catch (Exception e) {
                // Ignorem errors en l'atac
            }

            // Comprovem si algun jugador ha mort
            comprovarVides(atacant, atacat, jugadorsMorts);
        }

        // Determinem el guanyador
        // Determinem el guanyador
        guanyador(jugadorsPartida,jugadorsMorts);
    }

    /**
     * Funció per jugar de forma manual.
     * Els jugadors seleccionen els seus objectius d'atac.
     * El joc continua fins que només queda un jugador amb vida.
     */
    public static void manual() {
        ArrayList<Jugador> jugadorsMorts = new ArrayList<>();
        ArrayList<Jugador> jugadorsPartida = clonarLista(Jugadors.llistaJugadors);


        while (jugadorsPartida.size()-1 != jugadorsMorts.size()) {
            // Cada jugador viu realitza un atac per torn
            for (int i = 0; i < jugadorsPartida.size(); i++) {
                Jugador atacant = jugadorsPartida.get(i);
                Jugador atacat = atacant;
                int atacatSeleccionat;

                if (jugadorsMorts.contains(atacant)) {
                    continue; // Si el jugador està mort, passa el torn
                }

                // Selecció d'objectiu per part de l'usuari
                do {
                    System.out.println("\u001B[32m---------------\u001B[0m Atacant : "+ atacant.getNom()+"\u001B[32m---------------\u001B[0m\n");
                    try {
                        Jugadors.consultar(jugadorsPartida); // Mostrem la llista de jugadors
                        atacatSeleccionat = Teclat.scInt()-1;
                        atacat = jugadorsPartida.get(atacatSeleccionat);

                        if (atacat.equals(atacant)) {
                            System.out.println(" \u001B[31m X \u001B[0mNo et pots atacar a tu mateix\n");
                        }
                    } catch (Exception e) {
                        Teclat.nl();
                        System.out.println("Error al seleccionar objectiu");
                    }

                } while (jugadorsMorts.contains(atacat) || atacant.equals(atacat));

                // Realitzem l'atac
                try {
                    atacant.ataca(atacat);
                } catch (Exception e) {
                    // Ignorem errors en l'atac
                }

                // Comprovem si algun jugador ha mort
                comprovarVides(atacant, atacat, jugadorsMorts);
            }
        }

        // Determinem el guanyador
        guanyador(jugadorsPartida,jugadorsMorts);
    }

    /**
     * Notifica qui és el guanyador de la partida
     *
     * @param jugadors Llista dels jugadors de la partida
     * @param jugadorsMorts Llista dels jugadors que han mort durant la partida
     */
    public static void guanyador (ArrayList<Jugador> jugadors, ArrayList<Jugador> jugadorsMorts){

        String guanyador = null;
        String tipus = null;

        for (Jugador jugador : jugadors) {
            if (!jugadorsMorts.contains(jugador)) {
                guanyador = jugador.getNom();
                tipus = jugador.getClass().getSimpleName();
            }
        }
        System.out.println("El guanyador es: " + guanyador + ", " + tipus);
    }

    /**
     * Comprova si un jugador ha mort i l'afegeix a la llista de jugadors morts.
     *
     * @param atacant Jugador que realitza l'atac
     * @param atacat Jugador que rep l'atac
     * @param jugadorsMorts Llista de jugadors eliminats
     */
    public static void comprovarVides(Jugador atacant, Jugador atacat, ArrayList<Jugador> jugadorsMorts) {
        if (atacant.getVides() <= 0 && !jugadorsMorts.contains(atacant)) {
            jugadorsMorts.add(atacant);
            System.out.println(atacant.getNom() + " ha estat eliminat!");
        }
        if (atacat.getVides() <= 0 && !jugadorsMorts.contains(atacat)) {
            jugadorsMorts.add(atacat);
            System.out.println(atacat.getNom() + " ha estat eliminat!");
        }
    }

    /**
     * Pregunta a l'usuari si vol guardar la partida.
     *
     * @return true si l'usuari vol guardar, false en cas contrari
     */
    static public boolean volGuardar() {
        System.out.println("Vols guardar la partida? (S/N)");
        Character opcio = Teclat.scChar();
        if (opcio.equals('S') || opcio.equals('s')) {
            Partida.guardar();
            return true;
        } else if (opcio.equals('N') || opcio.equals('n')) {
            return false;
        } else {
            System.out.println("Opcio incorrecta");
            return volGuardar(); // Tornem a preguntar si l'opció és incorrecta
        }
    }

    /**
     * Mostra el menú per seleccionar el tipus de partida (automatitzada o manual).
     */
    static public void menuJugar() {
        String opcio;
        do {
            System.out.println("Inici del joc\n1 - Automatitzat\n2 - Manual\n0 - Eixir");
            opcio = Teclat.scString();
            switch (opcio) {
                case "1":
                    automatitzat();
                    volGuardar();
                    break;
                case "2":
                    manual();
                    volGuardar();
                    break;
                case "0":
                    System.out.println("Tornant al menu");
                    break;
                default:
                    System.out.println("Opcio incorrecta");
                    break;
            }
        }while (!opcio.equals("0"));
    }

    /**
     * Mostra el menú per seleccionar entre nova partida o carregar una existent.
     */
    static public void partida() {
        String opcio = "-1";
        while (!opcio.equals("0")) {
            System.out.println("1- Nova Partida\n2- Carregar Partida");
            opcio = Teclat.scString();
            switch (opcio) {
                case "1":
                    menuPartida();
                    break;
                case "2":
                    System.out.println("En desenvolupament");
                    Partida.carregar();
                    menuPartida();
                    break;
                case "0":
                    System.out.println("Sortint del joc de rol");
                    break;
                default:
                    System.out.println("Opcio incorrecta");
                    break;
            }
        }
    }

    /**
     * Mostra el menú de configuració del joc.
     * Permet accedir a la configuració de jugadors, equips i poders.
     */
    static public void menuConfiguracio() {
        String opcio = "-1";
        while (!opcio.equals("0")) {
            System.out.println("CONFIGURACIO\n1. Jugadors\n2. Equips\n3. Poders\n0. Sortir");
            opcio = Teclat.scString();
            switch (opcio) {
                case "1":
                    Jugadors.menu(); // Menú de gestió de jugadors
                    break;
                case "2":
                    Equips.menu(); // Menú de gestió d'equips
                    break;
                case "3":
                    Poders.menu(); // Menú de gestió de poders
                    break;
                case "0":
                    System.out.println("Sortint de la configuracio");
                    break;
                default:
                    System.out.println("Opcio incorrecta");
                    break;
            }
        }
    }
    public static int pos(String nom, char tipus) {
        int pos = -1;
        switch (tipus) {
            case 'J':
                Jugador jugadorABuscar = new Jugador(nom, 0, 0, 0);
                try {
                    if (Jugadors.llistaJugadors.contains(jugadorABuscar)) {

                        // Busquem el jugador a la llista
                        pos = Jugadors.llistaJugadors.indexOf(jugadorABuscar);
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Error al buscar el jugador: " + e.getMessage());
                }
                return pos;
            case 'P':
                org.Altres.Poders poderABuscar = new org.Altres.Poders(nom, 0, 0);
                try {
                    if (org.inici.Poders.llistaPoders.contains(poderABuscar)){

                        // Busquem el jugador a la llista
                        pos = org.inici.Poders.llistaPoders.indexOf(poderABuscar);
                        break;

                    }
                } catch (Exception e) {
                    System.out.println("Error al buscar el jugador: " + e.getMessage());
                }
                return pos;
            case 'E':
                Equip equipABuscar = new Equip(nom);
                try {
                    if (Equips.llistaEquips.contains(equipABuscar)){

                        // Busquem el jugador a la llista
                        pos = Equips.llistaEquips.indexOf(equipABuscar);
                        break;

                    }
                } catch (Exception e) {
                    System.out.println("Error al buscar el jugador: " + e.getMessage());
                }
                return pos;

        }
        return pos;
    }
}