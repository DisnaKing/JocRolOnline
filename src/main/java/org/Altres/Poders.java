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
    public boolean equals(Object o) {

        // Si l'objecto no es null y la clase es la mateixa comprovem els noms
        if (o == null || getClass() != o.getClass()) return false;
        org.Altres.Poders poder = (Poders) o;
        return this.getNom().equals(poder.getNom());
    }
}

