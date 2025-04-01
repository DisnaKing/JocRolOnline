package org.Altres;


public class Poders {
    String nom;
    int bonusAtac;
    int bonusDefensa;

    public Poders(String nom, int bonusAtac, int bonusDefensa) {
        this.nom = nom;
        this.bonusAtac = bonusAtac;
        this.bonusDefensa = bonusDefensa;
    }

    public String getNom() {
        return nom;
    }

    public int getBonusAtac() {
        return bonusAtac;
    }

    public int getBonusDefensa() {
        return bonusDefensa;
    }

    public String toString(){
        return this.nom+" (BA:"+this.bonusAtac+", BD:"+this.bonusDefensa+")";
    }
}

