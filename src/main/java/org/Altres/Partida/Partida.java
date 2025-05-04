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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;


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
    static public void carregar() {
        System.out.println("Carregant partida...");

        Gson gson = null;
        try {

            // Leer el archivo JSON
            java.io.Reader reader = new java.io.FileReader("src/main/resources/Jugadors.json");
            gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Jugadors.json"));
            String jsonContent = br.lines().collect(Collectors.joining());
            System.out.println("Contingut JSON: " + jsonContent);
            JugadorSerializable[] jugadorsArray = gson.fromJson(jsonContent, JugadorSerializable[].class);

            // Limpiamos las listas actuales
            Jugadors.llistaJugadors.clear();

            for (JugadorSerializable js : jugadorsArray) {
                // Creamos el jugador real (de momento sin equipo)
                Jugador j;
                switch (js.tipus) {
                    case "Alien":
                        j = new org.personatges.Alien(js.nom, js.puntsAtac, js.puntsDefensa, js.vides);
                        break;
                    case "Guerrer":
                        j = new org.personatges.Guerrer(js.nom, js.puntsAtac, js.puntsDefensa, js.vides);
                        break;
                    case "Huma":
                        j = new org.personatges.Huma(js.nom, js.puntsAtac, js.puntsDefensa, js.vides);
                        break;
                    default:
                        System.out.println("Tipus desconegut: " + js.tipus + ". Creant com a Jugador.");
                        j = new Jugador(js.nom, js.puntsAtac, js.puntsDefensa, js.vides);
                }
                // Copiamos los poders
                for (Poders p : js.poders) {
                    j.posa(p);
                }

                // Si tiene un equipo, lo buscamos y lo asignamos
                if (js.equip != null) {

                    // Crear equips abans d'asignar-los
                    if (Equips.llistaEquips.isEmpty()){
                        Equip equip = new Equip(js.equip);
                        Equips.llistaEquips.add(equip);
                        j.setEquip(equip);
                    }else {
                        if (!Equips.contains(js.equip)){
                            Equip equip = new Equip(js.equip);
                            j.setEquip(equip);
                        }
                    }

                    // Añadimos el jugador a la lista global
                    org.inici.Jugadors.llistaJugadors.add(j);
                    System.out.println("Partida carregada correctament.");

                }
            }
        } catch (IOException e) {
            System.out.println("Hi han problemes al carregar la partida");
        }

        for (org.personatges.Jugador jugador : Jugadors.llistaJugadors) {
            System.out.println(jugador);
        }
    }
    }

