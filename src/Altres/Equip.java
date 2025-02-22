package Altres;

import personatges.Jugador;

import java.util.ArrayList;

public class Equip {
    private String nom;
    ArrayList<Jugador> membres = new ArrayList<>();
    public Equip(String nom){
        this.nom=nom;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public void posa(Jugador Player){
        if(!membres.contains(Player)){
            membres.add(Player);
            System.out.println("Jugador afegit al equip");
        }
        else {System.out.println("El jugador ja esta a l'equip");}
    }
    public void lleva(Jugador Player){
        if(membres.contains(Player)){
            membres.remove(Player);
            System.out.println("Jugador expulsat");
        }
        else {System.out.println("El jugador no pertany al equip");}
    }
    public String toString() {
        return "Equip "+this.nom+" :\n\t- "+membres.toString();
    }
}
