package inici;
import teclat.Teclat;


public class JocDeRol {
    public static void main(String[] args) {
        clearConsole();
        int opcio = -1;
        while (opcio != 0){
            System.out.println("JOC DE ROL\n1. Configuracio\n2. Jugar\n0. Sortir");
            opcio=Teclat.scInt();
            clearConsole();
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


    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }



    public static void automatitzat(){
        while (Jugadors.llistaJugadors.size()>1){
            int atacantRandom =(int) (Math.random()*(1-Jugadors.llistaJugadors.size()));
            int atacatRandom =(int) (Math.random()*(1-Jugadors.llistaJugadors.size()));
            if (atacantRandom!=atacatRandom){
                if (Jugadors.llistaJugadors.get(atacantRandom).getVides()>0){
                    Jugadors.llistaJugadors.remove(atacantRandom);
                } else if (Jugadors.llistaJugadors.get(atacatRandom).getVides()>0) {
                    Jugadors.llistaJugadors.remove(atacatRandom);
                }else {
                    Jugadors.llistaJugadors.get(atacantRandom).ataca(Jugadors.llistaJugadors.get(atacatRandom));
                }
            }
            if (Jugadors.llistaJugadors.size()==1){
                System.out.println("El guanyador es: "+Jugadors.llistaJugadors.getFirst());
            }
        }
    }

    public static void manual(){

    }

    static public void menuJugar(){
        System.out.println("Inici del joc\n1 - Automatitzat\n2 - Manual");
        int opcio = Teclat.scInt();
        switch (opcio){
            case 1:
                automatitzat();
                break;
            case 2:
                manual();
                break;
            default:
                System.out.println("Opcio incorrecta");
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