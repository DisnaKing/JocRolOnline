package org.personatges;
// TODO Arreglar bufo
import org.Altres.Equip;
import org.Altres.Poders;

public class Alien extends Jugador{ // Tenen bonificacions en atac i penalitzacions en defensa
    public Alien(String nom, int puntsAtac, int puntsDefensa, int vides){
        super(nom,puntsAtac,puntsDefensa,vides);
        System.out.println("Sóc el constructor de personatges Alien pero estic creant un "+this.getClass().getSimpleName());
    }

    @Override
    public void ataca(Jugador player){ // Si te +20 vides → +3 atac && -3 defensa
        int pa;
        int pd;
        if(this.getVides()>20){
            pa = getPuntsAtac()+3;
            pd = getPuntsDefensa()-3;
        }
        else {pa=getPuntsAtac();pd=getPuntsDefensa();}
        int sumaPaPodersJ1 =0;
        for (Poders poder : this.getPoders()) {
            sumaPaPodersJ1+=poder.getBonusAtac();
        }
        int sumaPaPodersJ2 =0;
        for (Poders poder : player.getPoders()) {
            sumaPaPodersJ2+=poder.getBonusAtac();
        }
        int sumaPdPodersJ1 =0;
        for (Poders poder : this.getPoders()) {
            sumaPdPodersJ1+=poder.getBonusDefensa();
        }
        int sumaPdPodersJ2 =0;
        for (Poders poder : player.getPoders()) {
            sumaPdPodersJ2+=poder.getBonusDefensa();
        }
        System.out.println("----- ABANS DE L'ATAC ------"); // Stats abans de l'atac
        System.out.println(this);
        System.out.println(player.toString());
        System.out.println("----- Atac -----"); // Ataquen els dos Jugadors
        player.esColpejatAmb(sumaPdPodersJ2,sumaPaPodersJ1+pa);
        this.esColpejatAmb(sumaPdPodersJ1+pd, sumaPaPodersJ2);
        System.out.println("----- DESPRES DE L'ATAC -----"); // Stats després de l'atac
        System.out.println("Atacant: "+this);
        System.out.println("Atacat: "+player);
    }
    public String toString() {
        return super.toString();
    }
}
