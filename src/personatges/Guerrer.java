package personatges;

import Altres.Equip;

public class Guerrer extends Huma { // Poden aguantar més ferides que la resta de jugadors
    public Guerrer(String nom, int puntsAtac, int puntsDefensa, int vides){
        super(nom,puntsAtac,puntsDefensa,vides);
        System.out.println("Sóc el constructor de personatges Guerrer pero estic creant un "+this.getClass().getSimpleName());
    }
    public void esColpejatAmb(int puntsDefensa, int puntsAtac){
        int menosVides = Math.max((puntsAtac - this.getPuntsDefensa()), 0);
        if (menosVides>5){
            System.out.println(this.getNom()+" es colpejat amb "+puntsAtac+" i es defen amb "+puntsDefensa+" Vides: "+this.getVides()+" - "+menosVides+" = "+(this.getVides()-menosVides));
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
