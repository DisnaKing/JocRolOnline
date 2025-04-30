package org.personatges;
import org.Altres.Equip;
import org.Altres.Poders;

public class Alien extends Jugador{ // Tenen bonificacions en atac i penalitzacions en defensa
    public Alien(String nom, int puntsAtac, int puntsDefensa, int vides){
        super(nom,puntsAtac,puntsDefensa,vides);
        Equip equip;
        System.out.println("Sóc el constructor de personatges Alien pero estic creant un "+this.getClass().getSimpleName());
    }

    @Override
    public void ataca(Jugador player){ // Si te +20 vides → +3 atacs && -3 defensa

        // Calcular bufo d'aliens
        if (this.getVides() > 20) {
            this.setPuntsAtac(this.getPuntsAtac() + 3);
            this.setPuntsDefensa(this.getPuntsDefensa() - 3);
        }

        // Calcular variables de poders

        // Jugador que ataca

        int sumaPaPodersJ1 = 0;

        for (Poders poder : this.getPoders()) {
            sumaPaPodersJ1 += poder.getBonusAtac();
        }

        int atacJ1 = this.getPuntsAtac() + sumaPaPodersJ1;


        // Jugador que és atacat

        int sumaPaPodersJ2 = 0;

        for (Poders poder : player.getPoders()) {
            sumaPaPodersJ2 += poder.getBonusAtac();
        }

        int atacJ2 = player.getPuntsAtac() + sumaPaPodersJ2;

        // Atac
        System.out.println("----- ABANS DE L'ATAC ------"); // Stats abans de l'atac
        System.out.println(this);
        System.out.println(player);
        System.out.println("----- Atac -----"); // Ataquen els dos Jugadors
        player.esColpejatAmb(atacJ1);
        if (player.getVides() > 0) { // Comprovar que el jugador atacat no ha mort
            this.esColpejatAmb(atacJ2);
        } else {
            System.out.println(player.getNom() + " ha mort i no pot contraatacar.");
        }
        System.out.println("----- DESPRES DE L'ATAC -----"); // Stats després de l'atac
        System.out.println("Atacant: " + this);
        System.out.println("Atacat: " + player);
    }
    public String toString() {
        return super.toString();
    }
}
