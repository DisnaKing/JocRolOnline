package personatges;

import Altres.Equip;

import java.util.Objects;

public class Jugador {
    private String nom;
    private int puntsAtac;
    private int puntsDefensa;
    private int vides;
    private Equip equip;
    public Jugador(String nom, int puntsAtac, int puntsDefensa, int vides){
        System.out.println("Sóc el constructor de personatges Jugador pero estic creant un "+this.getClass().getSimpleName());
        this.nom = nom;
        this.puntsAtac=puntsAtac;
        this.puntsDefensa=puntsDefensa;
        this.vides=vides;

    }

    // Getters & Setters

    public String getNom(){
        return nom;
    }
    protected void setNom(String nom){
        this.nom=nom;
    }
    public int getPuntsAtac(){
        return puntsAtac;
    }
    protected void setPuntsAtac(int puntsAtac){
        this.puntsAtac=puntsAtac;
    }
    public int getPuntsDefensa(){
        return puntsDefensa;
    }
    protected void setPuntsDefensa(int puntsDefensa){
        this.puntsDefensa=puntsDefensa;
    }
    public int getVides(){
        return vides;
    }
    protected void setVides(int vides){
        this.vides=vides;
    }
    public Equip getEquip() {
        return equip;
    }

    public void setEquip(Equip equip) {
        if (equip==null && this.equip!=null) { // TODO el metode no te final ja que les funcions que crida criden a aquesta
            this.equip=null;
            equip.lleva(this);
        } else if (equip!=null){
            this.equip=equip;
            equip.posa(this);
        }
    }

    public String toString() {
        return this.nom+" [ "+this.equip.getNom()+" ] ("+this.getClass().getSimpleName()+", PA:"+this.puntsAtac+" , PD:"+this.puntsDefensa+" , PV:"+this.vides+")";
    }


    /**
     * Calcula els parametres del jugador colpejat
     * @param puntsDefensa del jugador colpejat
     * @param puntsAtac jel jugador que ataca
     */
    public void esColpejatAmb(int puntsDefensa, int puntsAtac){
        int menosVides = Math.max((puntsAtac - this.puntsDefensa), 0);
        System.out.println(this.nom+" es colpejat amb "+puntsAtac+" i es defen amb "+puntsDefensa+" Vides: "+this.vides+" - "+menosVides+" = "+(this.vides-menosVides));
        this.vides-=menosVides;
    }

    /**
     * Ataca a un jugador
     * @param player jugador al que ataca
     */
    public void ataca(Jugador player){
        System.out.println("----- ABANS DE L'ATAC ------"); // Stats abans de l'atac
        System.out.println(this);
        System.out.println(player.toString());
        System.out.println("----- Atac -----"); // Ataquen els dos Jugadors
        player.esColpejatAmb(player.puntsDefensa,this.puntsAtac);
        this.esColpejatAmb(this.getPuntsDefensa(), player.puntsAtac);
        System.out.println("----- DESPRES DE L'ATAC -----"); // Stats després de l'atac
        System.out.println("Atacant: "+this);
        System.out.println("Atacat: "+player);
    }
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return puntsAtac == jugador.puntsAtac && puntsDefensa == jugador.puntsDefensa && vides == jugador.vides && Objects.equals(nom, jugador.nom) && Objects.equals(equip, jugador.equip);
    }
}
