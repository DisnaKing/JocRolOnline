package org.Altres;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
public class Partida {
    static public void guardar(){
        System.out.println("Guardant partida...");
        // Convertir a JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(org.inici.Jugadors.llistaJugadors);

        // Guardar en un archivo
        try (FileWriter writer = new FileWriter("Jugadors.json")) {
            writer.write(json);
            System.out.println("Partida Guardada.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static public void carregar(){
        System.out.println("Carregant partida...");
        // Aquí es pot implementar la lògica per carregar la partida
        // Potser llegir d'un fitxer o carregar des d'una base de dades.
    }



}
