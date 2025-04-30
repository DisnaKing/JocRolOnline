package org.Altres.Partida;

// paquets projecte
import org.Altres.Equip;
import org.Altres.Poders;
import org.inici.Equips;
import org.personatges.Jugador;
import org.inici.Jugadors;

// paquets externs
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Partida {
    // Guardar partida
    static public void guardar(){

        // TODO Els jugadors no guarden els equips

        System.out.println("Guardant partida...");

        // Convertimos los jugadores reales en jugadores serializables
        ArrayList<JugadorSerializable> jugadorsSerialitzables = new ArrayList<>();
        for (Jugador j : org.inici.Jugadors.llistaJugadors) {
            jugadorsSerialitzables.add(new JugadorSerializable(j));
        }

        // Serializamos
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(jugadorsSerialitzables);

        // Guardar en un archivo
        try (FileWriter writer = new FileWriter("src/main/resources/Jugadors.json")) {
            writer.write(json);
            System.out.println("Partida Guardada.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Carregar partida
    static public void carregar(){
        System.out.println("Carregant partida...");

        try {

            // Leer el archivo JSON
            java.io.Reader reader = new java.io.FileReader("src/main/resources/Jugadors.json");
            Gson gson = new Gson();
            JugadorSerializable[] jugadorsArray = gson.fromJson(reader, JugadorSerializable[].class);

            // Limpiamos las listas actuales
            Jugadors.llistaJugadors.clear();

            for (JugadorSerializable js : jugadorsArray) {
                // Creamos el jugador real (de momento sin equipo)
                Jugador j = new Jugador(js.nom, js.puntsAtac, js.puntsDefensa, js.vides, null);
                // Copiamos los poders
                for (Poders p : js.poders) {
                    j.posa(p);
                }
                // TODO Crear equips abans d'asignar-los
                // Si tiene un equipo, lo buscamos y lo asignamos
                if (js.equip != null) {
                    if (!Equips.llistaEquips.contains(js.equip)){

                    Equip equipo = new Equip(js.equip);
                    for (Equip e : org.inici.Equips.llistaEquips) {
                        if (e.getNom().equals(js.equip)) {
                            j.setEquip(e);
                            break;
                        }
                    }
                }
                // Añadimos el jugador a la lista global
                org.inici.Jugadors.llistaJugadors.add(j);
                System.out.println("Partida carregada correctament.");

            }
        }catch (IOException e) {
            System.out.println("Hi han problemes al carregar la partida");
        }








            // Convertir la cadena JSON a un array de Jugador
            ArrayList lista = gson.fromJson("Jugadors", ArrayList.class);
            for (Object obj : lista) {
                if (obj instanceof Jugador) {
                    Jugadors.llistaJugadors.add((Jugador) obj);
                }
            }

            for(org.personatges.Jugador jugador : Jugadors.llistaJugadors){
                System.out.println(jugador);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
