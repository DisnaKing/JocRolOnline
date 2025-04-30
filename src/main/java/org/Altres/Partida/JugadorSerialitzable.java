package org.Altres.Partida;
import org.personatges.Jugador;
import org.Altres.Poders;

import java.util.ArrayList;

// Clase auxiliar para guardar solo lo necesario
class JugadorSerializable {
    String nom;
    int puntsAtac;
    int puntsDefensa;
    int vides;
    String equip; // Solo guardamos el nombre del equipo
    ArrayList<Poders> poders;

    JugadorSerializable(Jugador j) {
        this.nom = j.getNom();
        this.puntsAtac = j.getPuntsAtac();
        this.puntsDefensa = j.getPuntsDefensa();
        this.vides = j.getVides();
        this.equip = (j.getEquip() != null) ? j.getEquip().getNom() : null;
        this.poders = j.getPoders();
    }
}

