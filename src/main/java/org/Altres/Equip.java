package org.Altres;


import org.inici.Equips;
import org.personatges.Jugador;

import java.util.ArrayList;
import java.util.Objects;

public class Equip {
    private final String nom;
    ArrayList<Jugador> membres = new ArrayList<>();
    public Equip(String nom){
        this.nom=nom;
    }
    public String getNom() {
        return nom;
    }

    public ArrayList<Jugador> getMembres() {
        return membres;
    }
    public void posa(Jugador Player){
        if(! membres.contains(Player)){
            membres.add(Player);
            Player.setEquip(this);
        }
        else {System.out.println(Player.getNom()+ " ja esta a l'equip");}
    }
    public void lleva(Jugador player) {
        if (membres.contains(player)) {
            membres.remove(player);
            if (player.getEquip() == this) { // Assegurar-se que el jugador pertany a aquest equip
                player.setEquip(null);
                System.out.println(player.getNom() + " ha sigut eliminat de l'equip " + this.getNom());
            }
        } else {
            System.out.println(player.getNom() + " no pertany a aquest equip");
        }
    }
    public String toString() {
        StringBuilder membres = new StringBuilder();
        for(int i=0;i<this.getMembres().size();i++){
            membres.append("\t- ").append(this.getMembres().get(i).toString());
        }
        return "Equip "+this.nom+" :\n"+ membres;
    }

    public boolean equals(Object o) {

        // Si l'objecto no es null y la clase es la mateixa comprovem els noms
        if (o == null || getClass() != o.getClass()) return false;
        org.Altres.Equip equip = (Equip) o;
        return this.getNom().equals(equip.getNom());
    }
}
