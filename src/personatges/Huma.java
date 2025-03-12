package personatges;

public class Huma extends Jugador {    // No tenen bonificacions en defensa i atac
    public Huma(String nom, int puntsAtac, int puntsDefensa, int vides) {
        super(nom, puntsAtac, puntsDefensa, vides);
        System.out.println("Sóc el constructor de personatges Huma pero estic creant un " + this.getClass().getSimpleName());
        if (this.getVides() > 100) {
        setVides(100);
        }
    }
    public String toString() {
        return super.toString();
    }
}
