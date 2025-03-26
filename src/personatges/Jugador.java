package personatges;

import Altres.*;

import java.util.ArrayList;
import java.util.Objects;

public class Jugador {
    private String nom;
    private int puntsAtac;
    private int puntsDefensa;
    private int vides;
    private Equip equip;
    private ArrayList<Poders> poders;
    public Jugador(String nom, int puntsAtac, int puntsDefensa, int vides){

        System.out.println("Sóc el constructor de personatges Jugador pero estic creant un "+this.getClass().getSimpleName());
        this.nom = nom;
        this.puntsAtac=puntsAtac;
        this.puntsDefensa=puntsDefensa;
        this.vides=vides;
        this.poders = new ArrayList<>();

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

    public ArrayList<Poders> getPoders() {
        return poders;
    }

    public void setEquip(Equip equip) {
        if (equip == null) {
            if (this.equip != null) {
                Equip oldEquip = this.equip; // Evitar referències incorrectes
                this.equip = null;
                oldEquip.lleva(this); // Elimina de l'antic equip
                System.out.println(this.getNom()+" ha segut eliminat de l'equip "+oldEquip.getNom()+"\n");
            } else {
                System.out.println("Equip eliminat");
            }
        } else {
            this.equip = equip;
            equip.posa(this);
        }
    }

    public String toString() {
        StringBuilder poders = new StringBuilder();
        for (Poders poder : this.getPoders()){
            poders.append("\t- ").append(poder).append("\n");
        }
        String equip;
        if(this.equip==null){
            equip = "Sense equip";
        }else{
            equip = this.equip.getNom();
        }
        return this.nom+" [ "+equip+" ] ("+this.getClass().getSimpleName()+", PA:"+this.puntsAtac+" , PD:"+this.puntsDefensa+" , PV:"+this.vides+")\n"+poders;
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
    public void ataca(Jugador player) {
        int pa = getPuntsAtac();
        int pd = getPuntsDefensa();
        int sumaPaPodersJ1 = 0;
        for (Poders poder : this.getPoders()) {
            sumaPaPodersJ1 += poder.getBonusAtac();
        }
        int sumaPaPodersJ2 = 0;
        for (Poders poder : player.getPoders()) {
            sumaPaPodersJ2 += poder.getBonusAtac();
        }
        int sumaPdPodersJ1 = 0;
        for (Poders poder : this.getPoders()) {
            sumaPdPodersJ1 += poder.getBonusDefensa();
        }
        int sumaPdPodersJ2 = 0;
        for (Poders poder : player.getPoders()) {
            sumaPdPodersJ2 += poder.getBonusDefensa();
        }
        System.out.println("----- ABANS DE L'ATAC ------"); // Stats abans de l'atac
        System.out.println(this);
        System.out.println(player.toString());
        System.out.println("----- Atac -----"); // Ataquen els dos Jugadors
        player.esColpejatAmb(sumaPdPodersJ2, sumaPaPodersJ1 + pa);
        this.esColpejatAmb(sumaPdPodersJ1 + pd, sumaPaPodersJ2);
        System.out.println("----- DESPRES DE L'ATAC -----"); // Stats després de l'atac
        System.out.println("Atacant: " + this);
        System.out.println("Atacat: " + player);
    }
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return puntsAtac == jugador.puntsAtac && puntsDefensa == jugador.puntsDefensa && vides == jugador.vides && Objects.equals(nom, jugador.nom) && Objects.equals(equip, jugador.equip);
    }

    public void posa(Poders poder){
        this.poders.add(poder);
    }
    public void lleva(Poders poder){
        this.poders.remove(poder);
    }
}
