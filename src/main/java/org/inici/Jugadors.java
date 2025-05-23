/**
 * Classe que gestiona la llista de jugadors del sistema.
 * Permet realitzar operacions CRUD sobre els jugadors i gestionar-ne les relacions amb equips i poders.
 *
 * @author [Nom de l'autor]
 * @version 1.0
 */
package org.inici;

import org.personatges.Alien;
import org.personatges.Guerrer;
import org.personatges.Huma;
import org.personatges.Jugador;
import org.teclat.Teclat;

import java.util.ArrayList;

import static org.inici.JocDeRol.pos;

public class Jugadors {
    // Llista estàtica que emmagatzema tots els jugadors del sistema
    static public ArrayList<Jugador> llistaJugadors = new ArrayList<>();

    /**
     * Mostra el menú principal de gestió de jugadors i gestiona les opcions seleccionades.
     * Conté un bucle que es repeteix fins que l'usuari selecciona l'opció d'eixir.
     */
    public static void menu(){
        String opcio;
        do {
            // Mostrem les opcions del menú
            System.out.println("JUGADORS\n1. Crear\n2. Consultar\n3. Eliminar\n4. Assignar a equip\n5. Llevar d'equip\n6. Assignar poder\n7. Cambiar vides inicials\n0. Eixir");

            // Llegim l'opció de l'usuari
            opcio = Teclat.scString();

            // Gestionem l'opció seleccionada
            switch (opcio){
                case "1":
                    crear(); // Crea un nou jugador
                    break;
                case "2":
                    consultar(Jugadors.llistaJugadors); // Mostra tots els jugadors
                    break;
                case "3":
                    eliminar(); // Elimina un jugador
                    break;
                case "4":
                    assignarAEquip(); // Assigna un jugador a un equip
                    break;
                case "5":
                    llevarDEquip(); // Elimina un jugador d'un equip
                    break;
                case "6":
                    assignarPoder(); // Assigna un poder a un jugador
                    break;
                case "7":
                    videsInicials(); // Modifica les vides inicials dels jugadors
                    break;
                case "0":
                    System.out.println("Sortint de la gestio de jugadors");
                    break;
                default:
                    System.out.println("Opcio incorrecta");
                    break;
            }
        } while (!opcio.equals("0")); // Continuem fins que l'usuari seleccione eixir
    }

    /**
     * Assigna un poder de la llista de poders disponibles a un jugador específic.
     * @throws Exception Si es produeix un error en la selecció del jugador o del poder
     */
    public static void assignarPoder(){
        try{
            // Demanem quin jugador volem eliminar
            System.out.println("A quin jugador vols asignar el poder? ");
            consultar(Jugadors.llistaJugadors); // Mostrem la llista
            System.out.println("Introdueix el nom:");

            // Llegim la selecció
            String nomJugador = Teclat.scString();

            // Demanem quin poder volem assignar
            System.out.println("Quin poder vols assignar al jugador?");
            Poders.consultar(); // Mostrem la llista de poders
            System.out.println("Escriu el nom: ");

            // llegim el poder
            String nomPoder = Teclat.scString();


            // Busquem el jugador a la llista y li posem el poder
            try{
                    llistaJugadors.get(pos(nomJugador,'J')).posa(Poders.llistaPoders.get(pos(nomPoder,'P')));

            } catch (Exception e) {
                Teclat.nl();
                System.out.println("Jugador o Poder no trobat");
                incorrecto(3);
            }

        } catch (Exception e) {
            incorrecto(6); // Gestionem errors
        }
    }

    /**
     * Elimina un jugador de l'equip al que pertany, deixant-lo sense equip assignat.
     * @throws Exception Si es produeix un error en la selecció del jugador
     */
    public static void llevarDEquip(){
        try{
            // Demanem quin jugador volem treure de l'equip
            System.out.println("Quin jugador vols llevar de l'equip?");
            consultar(Jugadors.llistaJugadors); // Mostrem la llista
            System.out.println("Introdueix el nom:");

            // Llegim la selecció
            String nomJugador = Teclat.scString();

            try{

                llistaJugadors.get(pos(nomJugador,'J')).setEquip(null);
                System.out.println("Equip eliminat\n");

            } catch (Exception e) {
                Teclat.nl();

                System.out.println("Jugador o Equip no trobat");
            }
        } catch (Exception e) {
            incorrecto(5); // Gestionem errors
        }
    }

    /**
     * Assigna un jugador a un equip existent.
     * Comprova que hi ha equips creats i que el jugador no tinga ja un equip assignat.
     * @throws Exception Si es produeix un error en la selecció del jugador o de l'equip
     */
    public static void assignarAEquip(){
        try {
            // Comprovem si hi ha equips creats
            if (Equips.llistaEquips.isEmpty()){

                System.out.println("No hi ha equips creats");

            } else {
                // Demanem quin jugador volem assignar
                System.out.println("Quin jugador vols assignar a un equip?");
                consultar(Jugadors.llistaJugadors); // Mostrem jugadors
                System.out.println("Introdueix el nom:");

                // Llegim la selecció
                String  jugador = Teclat.scString();

                // Demanem a quin equip l'assignarem
                System.out.println("A quin equip vols assignar el jugador?");
                Equips.consultar(); // Mostrem equips
                System.out.println("Introdueix el nom:");

                // Llegim la selecció
                String equip = Teclat.scString();

                try {

                    // Comprovem que el jugador no tinga ja un equip assignat
                    if (llistaJugadors.get(pos(jugador,'J')).getEquip() == null){

                        // Assignem l'equip al jugador
                        llistaJugadors.get(pos(jugador,'J')).setEquip(Equips.llistaEquips.get(pos(equip,'E')));

                    }else {

                        // Si el jugador ja te un equip assignat, no el podem assignar a un altre
                        System.out.println("El jugador ja te un equip assignat");
                    }
                } catch (Exception e) {
                    Teclat.nl();
                    System.out.println("Jugador o Equip no trobat");
                    incorrecto(3);
                }
            }
        } catch (Exception e) {
            incorrecto(4); // Gestionem errors
        }
    }



    /**
     * Elimina un jugador de la llista de jugadors.
     * @throws Exception Si es produeix un error en la selecció del jugador
     */
    public static void eliminar(){

        // Demanem quin jugador volem eliminar
        System.out.println("Quin jugador vols eliminar? ");
        consultar(Jugadors.llistaJugadors); // Mostrem la llista
        System.out.println("Introdueix el nom:");

        // Llegim la selecció
        String nomJugador = Teclat.scString();

        // Busquem el jugador a la llista i l'eliminem
        try {
            llistaJugadors.remove(pos(nomJugador,'J')); // Busquem el jugador a la llista i l'eliminem
        } catch (Exception e) {
            Teclat.nl();
            System.out.println("Jugador no trobat");
            incorrecto(3);
        }
    }

    /**
     * Mostra per pantalla la llista de jugadors amb el seu número d'ordre.
     */
    public static void consultar(ArrayList<Jugador>llistaJugadors){
        int i = 1;
        // Recorrem tots els jugadors i els mostrem numerats
        for (Jugador jugador : llistaJugadors){
            System.out.println(i + " - " + jugador);
            i++;
        }
    }

    /**
     * Estableix el nombre de vides inicials per als nous jugadors que es creen.
     */
    public static void videsInicials(){
        try {
            System.out.println("ATENCIÓ: Els jugadors creats anteriorment mantindran les vides inicials que ja tenen");

            // Demanem el nombre de vides
            System.out.println("Quantes vides vols per defecte?");
            int vides = Teclat.scInt();

            // Actualitzem el valor estàtic
            Jugador.videsInicials = vides;
            System.out.println("Vides inicials canviades a " + vides);
        } catch (Exception e) {
            incorrecto(7);
        }
    }

    /**
     * Crea un nou jugador del tipus especificat (Huma, Guerrer o Alien).
     * Demana les dades necessàries i l'afegeix a la llista si no existeix ja.
     * @throws Exception Si es produeix un error en la introducció de dades
     */
    public static void crear(){
        // Obtenim les vides inicials actuals
        int videsInicials = Jugador.videsInicials;
        try {
            // Demanem el tipus de jugador
            System.out.println("Tipus de jugador (H,G,A): ");
            String tipus = Teclat.scString();
            tipus = tipus.toUpperCase(); // Convertim a majúscules

            // Demanem el nom
            System.out.println("Nom del jugador:");
            String nom = Teclat.scString();

            // Demanem punts d'atac i calculem punts de defensa
            System.out.println("Punts d'atac:");
            int pa = Teclat.scInt();
            int pd = 100 - pa; // La defensa és el complement a 100 de l'atac
            if (pd < 0) {
                pd = 0; // Assegurem que no siga negatiu
            }

            // Creem el jugador segons el tipus especificat
            switch (tipus){
                case "H": // Cas humà
                    Huma nowHuma = new Huma(nom, pa, pd, videsInicials);
                    if (!llistaJugadors.contains(nowHuma)){
                        llistaJugadors.add(nowHuma);
                    }
                    break;
                case "G": // Cas guerrer
                    Guerrer nowGuerrer = new Guerrer(nom, pa, pd, videsInicials);
                    if (!llistaJugadors.contains(nowGuerrer)){
                        llistaJugadors.add(nowGuerrer);
                    }
                    break;
                case "A": // Cas alien
                    Alien nowAlien = new Alien(nom, pa, pd, videsInicials);
                    if (!llistaJugadors.contains(nowAlien)){
                        llistaJugadors.add(nowAlien);
                    }
                    break;
                default:
                    System.out.println("Tipus de jugador incorrecte");
                    break;
            }
        } catch (Exception e) {
            incorrecto(1); // Gestionem errors
        }
    }

    /**
     * Gestiona els errors produïts en altres mètodes, permetent reintentar l'operació.
     * @param funcion Número que identifica el mètode que va produir l'error
     */
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
                case 2:
                    consultar(Jugadors.llistaJugadors); // Reintentar consultar
                    break;
                case 3:
                    eliminar(); // Reintentar eliminar
                    break;
                case 4:
                    assignarAEquip(); // Reintentar assignar a equip
                    break;
                case 5:
                    llevarDEquip(); // Reintentar llevar d'equip
                    break;
                case 6:
                    assignarPoder(); // Reintentar assignar poder
                    break;
                case 7:
                    videsInicials();
                    break;
            }
        }
    }
}