package inici;

import Altres.Equip;
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
        e1.posa(h1);
        e1.posa(a1);
        e2.posa(g1);
        System.out.println(e1);
        e1.lleva(a1);
        System.out.println(e1);
    }
}