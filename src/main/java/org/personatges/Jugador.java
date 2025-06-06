package org.personatges;

import org.Altres.*;
import org.inici.Equips;
import org.inici.Jugadors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Jugador implements Cloneable, Serializable {
    private final String nom;
    private int puntsAtac;
    private int puntsDefensa;
    private int vides;
    private String tipus;
    private Equip equip;

    static public int videsInicials = 200;
    private final ArrayList<Poders> poders;


    // Constructor sin parámetro equip: usa null por defecto
    public Jugador(String nom, int puntsAtac, int puntsDefensa, int vides) {
        this(nom, puntsAtac, puntsDefensa, vides, null);
        System.out.println("Sóc el constructor de personatges Jugador pero estic creant un " + this.getClass().getSimpleName());

    }

    // Constructor con equip como parámetro
    public Jugador(String nom, int puntsAtac, int puntsDefensa, int vides, Equip equip) {
        System.out.println("Sóc el constructor de personatges Jugador pero estic creant un " + this.getClass().getSimpleName());
        this.nom = nom;
        this.puntsAtac = puntsAtac;
        this.puntsDefensa = puntsDefensa;
        this.vides = vides;
        this.equip = equip;
        this.poders = new ArrayList<>();
        this.tipus = this.getClass().getSimpleName();
    }

    // Getters & Setters
    public String getNom() {
        return nom;
    }

    public int getPuntsAtac() {
        return puntsAtac;
    }

    protected void setPuntsAtac(int puntsAtac) {
        this.puntsAtac = puntsAtac;
    }

    public int getPuntsDefensa() {
        return puntsDefensa;
    }

    protected void setPuntsDefensa(int puntsDefensa) {
        this.puntsDefensa = puntsDefensa;
    }

    public int getVides() {
        return vides;
    }

    protected void setVides(int vides) {
        this.vides = vides;
    }

    public Equip getEquip() {
        return equip;
    }

    public ArrayList<Poders> getPoders() {
        return poders;
    }

    public void setEquip(Equip equip) {
        if (equip != null) {
            this.equip = equip;
            if (!equip.getMembres().contains(this)) {
                equip.posa(this);
            }
        } else {
            if (this.equip != null) {
                if (this.getEquip().getMembres().contains(this)) {
                    this.getEquip().lleva(this);
                }
            }
            this.equip = null;
        }
    }

    public String toString() {
        StringBuilder poders = new StringBuilder();
        for (Poders poder : this.getPoders()) {
            poders.append("\t- ").append(poder).append("\n");
        }
        String equip;
        if (this.equip == null) {
            equip = "Sense equip";
        } else {
            equip = this.equip.getNom();
        }
        return this.nom + " [ " + equip + " ] (" + this.getClass().getSimpleName() + ", PA:" + this.puntsAtac + " , PD:" + this.puntsDefensa + " , PV:" + this.vides + ")\n" + poders;
    }

    // Método clone
    @Override
    public Jugador clone() {
        try {
            return (Jugador) super.clone(); // copia superficial
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Esto no debería ocurrir si implementas Cloneable
        }
    }

    /**
     * Calcula els parametres del jugador colpejat
     *
     * @param puntsAtac jel jugador que ataca
     */
    protected void esColpejatAmb(int puntsAtac) {

        // Calcular variables de poders

        // Calculem la defensa del jugador atacat
        int defensa = sumaPoders(this, 'd');

        //Comprovar que l'atac no es negatiu
        if (puntsAtac < 0) {
            puntsAtac = 0;
        }

        // Calculem la vida que li restem al jugador atacat
        int vidaResta = puntsAtac - defensa;
        if (vidaResta < 0) vidaResta = 0;

        //Establim vides finals
        int videsFinals = this.getVides() - vidaResta;

        // Comprovem que les vides no son negatives
        if (videsFinals < 0) {
            videsFinals = 0;
        }
        this.setVides(videsFinals);

        // Atac
        System.out.println(this.nom + " es colpejat amb " + puntsAtac + " i es defen amb " + defensa + ". Vides: " + this.getVides() + "- " + vidaResta + " = " + videsFinals);
    }

    public static int sumaPoders(Jugador j, char A) {
        int valor = 0;

        // Diferenciem si volem obtindre els punts d'atac o de defensa
        if (A == 'a') {
            int sumaPaPodersJ1 = 0;

            for (Poders poder : j.getPoders()) {
                sumaPaPodersJ1 += poder.getBonusAtac();
            }

            valor = j.getPuntsAtac() + sumaPaPodersJ1;

        } else if (A == 'd') {
            int sumaPdPodersJ1 = 0;

            for (Poders poder : j.getPoders()) {
                sumaPdPodersJ1 += poder.getBonusDefensa();
            }

            valor = j.getPuntsDefensa() + sumaPdPodersJ1;
        }
        return valor;
    }

    /**
     * <p>Realiza un ataque entre el jugador actual y otro jugador.</p>
     *
     * <p>El ataque se realiza en los siguientes pasos:</p>
     * <ul>
     *     <li>Se calculan los puntos de ataque y defensa de ambos jugadores, sumando sus bonificadores.</li>
     *     <li>Ambos jugadores se atacan mutuamente.</li>
     *     <li>Se muestran las estadísticas antes y después del ataque.</li>
     * </ul>
     *
     * @param player El jugador que será atacado y que también contraatacará.
     */
    public void ataca(Jugador player) {
        // Calcular variables de poders

        // Jugador que ataca
        int atacJ1 = sumaPoders(this, 'a');

        // Jugador que és atacat
        int atacJ2 = sumaPoders(player, 'a');

        // Comenca l'atac

        //Stats abans de l'atac
        System.out.println("\n\u001B[32m------\u001B[0m ABANS DE L'ATAC \u001B[32m------\u001B[0m\n"); // Stats abans de l'atac
        System.out.println(this);
        System.out.println(player);

        // Atac
        System.out.println("\u001B[31m ----- \u001B[0m Atac \u001B[31m ----- \u001B[0m\n"); // Ataquen els dos Jugadors
        player.esColpejatAmb(atacJ1);

        // Comprovar que el jugador atacat no ha mort
        if (player.getVides() > 0) {
            this.esColpejatAmb(atacJ2);
        } else {
            System.out.println("\u001B[31m" + player.getNom() + " ha mort i no pot contraatacar.\u001B[0m\n");
        }

        // Stats després de l'atac
        System.out.println("\n\u001B[32m------\u001B[0m DESPRES DE L'ATAC \u001B[32m------\u001B[0m\n");
        System.out.println("Atacant: " + this);
        System.out.println("Atacat: " + player);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Jugador)) return false; // Cambiado para aceptar subclases
        Jugador jugador = (Jugador) obj;
        return Objects.equals(this.nom, jugador.nom); // Compara por el atributo relevante
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom); // Usa el mismo atributo que en equals
    }

    public void posa(Poders poder) {
        this.poders.add(poder);
    }

    public void lleva(Poders poder) {
        this.poders.remove(poder);
    }


}
