package personatges;

import Altres.Equip;

public class Alien extends Jugador{ // Tenen bonificacions en atac i penalitzacions en defensa
    public Alien(String nom, int puntsAtac, int puntsDefensa, int vides){
        super(nom,puntsAtac,puntsDefensa,vides);
        System.out.println("Sóc el constructor de personatges Alien pero estic creant un "+this.getClass().getSimpleName());
    }
    public void ataca(Jugador player){ // Si te +20 vides → +3 atac && -3 defensa
        int pa;
        int pd;
        if(this.getVides()>20){
            pa = getPuntsAtac()+3;
            pd = getPuntsDefensa()-3;
        }
        else {pa=getPuntsAtac();pd=getPuntsDefensa();}
        System.out.println("----- ABANS DE L'ATAC ------");
        System.out.println(this);
        System.out.println(player.toString());
        System.out.println("----- Atac -----");
        player.esColpejatAmb(player.getPuntsDefensa(),pa);
        this.esColpejatAmb(pd, player.getPuntsAtac());
        System.out.println("----- DESPRES DE L'ATAC -----");
        System.out.println("Atacant: "+this);
        System.out.println("Atacat: "+player);
    }
    public String toString() {
        return super.toString();
    }
}
