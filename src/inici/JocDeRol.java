package inici;

import Altres.Equip;
import Altres.Poders;
import personatges.*;

public class JocDeRol {
    public static void main(String[] args) {
        provaFase4();
    }

    private static void provaFase4(){
        Equip e1=new Equip("ELs peps");
        Equip e2=new Equip("Jaimitos");
        Huma h1 = new Huma("Guillem",40,20,150);
        Alien a1 = new Alien("Andy",40,20,70);
        Guerrer g1 = new Guerrer("Andreu",40,35,150);
        Poders p1 = new Poders("BufoVida",5,0);
        Poders p2 = new Poders("bufoDeff",0,5);
        Poders p3 = new Poders("pyroBlast",10,-5);

        // Afegir poders
        h1.posa(p1);
        h1.posa(p2);
        g1.posa(p2);
        a1.posa(p3);

        // Afegir jugadors als equips
        e1.posa(h1);
        e1.posa(a1);
        e2.posa(g1);

        System.out.println(h1);
    }
}