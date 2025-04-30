package org.personatges;

import org.Altres.Poders;

public class Guerrer extends Huma { // Poden aguantar més ferides que la resta de jugadors
    public Guerrer(String nom, int puntsAtac, int puntsDefensa, int vides){
        super(nom,puntsAtac,puntsDefensa,vides);
        System.out.println("Sóc el constructor de personatges Guerrer pero estic creant un "+this.getClass().getSimpleName());
    }
    @Override
    public void esColpejatAmb(int puntsAtac) {
        int sumaPodersJ1 = 0;

        for (Poders poder : this.getPoders()) {
            sumaPodersJ1 += poder.getBonusDefensa();
        }

        int defensa = this.getPuntsDefensa() + sumaPodersJ1;
        int menosVides = Math.max((puntsAtac - defensa), 0);
        if (menosVides>5){
            System.out.println(this.getNom()+" es colpejat amb "+puntsAtac+" i es defen amb "+defensa+" Vides: "+this.getVides()+" - "+menosVides+" = "+(this.getVides()-menosVides));
            this.setVides(getVides()-menosVides);
        }
        else {
            System.out.println("No tens els suficients PA per a ferme mal.");
        }

    }
    public String toString() {
        return super.toString();
    }
}
