package inici;
import teclat.Teclat;


public class JocDeRol {
    public static void main(String[] args) {
        int opcio = -1;
        while (opcio != 0){
            System.out.println("JOC DE ROL\n1. Configuracio\n2. Jugar\n0. Sortir");
            opcio=Teclat.scInt();
            switch (opcio){
                case 1:
                    menuConfiguracio();
                    break;
                case 2:
                    menuJugar();
                    break;
                case 0:
                    System.out.println("Sortint del joc de rol");
                    break;
                default:
                    System.out.println("Opcio incorrecta");
                    break;
            }
        }
    }

    static public void menuConfiguracio(){
        int opcio = -1;
        while (opcio != 0) {
            System.out.println("CONFIGURACIO\n1. Jugadors\n2. Equips\n3. Poders\n0. Sortir");
            opcio = Teclat.scInt();
            switch (opcio){
                case 1:
                    Jugadors.menu();
                    break;
                case 2:
                    Equips.menu();
                    break;
                case 3:
                    Poders.menu();
                    break;
                case 0:
                    System.out.println("Sortint de la configuracio");
                    break;
                default:
                    System.out.println("Opcio incorrecta");
                    break;
            }
        }

    }

}