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

    public ArrayList<Jugador> getMembres() {
        return membres;
    }
    public void posa(Jugador Player){
        if(!membres.contains(Player)){
            membres.add(Player);
            Player.setEquip(this);
        }
        else {System.out.println(Player.getNom()+ " ja esta a l'equip");}
    }
    public void lleva(Jugador Player){
        if(membres.contains(Player) && Player.getEquip()!=null){
            membres.remove(Player);
            Player.setEquip(null);
        }else if(Player.getEquip()==null) {
            System.out.println(Player+" eliminat de l'equip");
        }
        else {System.out.println(Player+ " no pertany al equip");}
    }
// TODO Separar els membres en diferents files
    public String toString() {
        String membres = "";
        for(int i=0;i<this.getMembres().size();i++){
            membres+="\t- "+this.getMembres().get(i).toString()+"\n";
        }
        return "Equip "+this.nom+" :\n"+ membres;
    }
}
