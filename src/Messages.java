import java.util.Scanner;

/**
 * Displays pre-determined messages
 */
public class Messages {

    public static void afficherIntro() {

        System.out.println("                             |||");
        System.out.println("                             |||");
        System.out.println("                             |||");
        System.out.println("                            =====");
        System.out.println("      _   _              //=======\\\\");
        System.out.println("     | |_| |__   ___    /// |   | \\\\\\");
        System.out.println("     | __| '_ \\ / _ \\  ///  |   |  \\\\\\");
        System.out.println("     | |_| | | |  ___ ///   |   |   \\\\\\            _");
        System.out.println("      \\__|_| |_|\\__| |    __|  _| _  ___ _ __   __| |");
        System.out.println("                   | |   / _ \\/ _` |/ _ \\ '_ \\ / _` |");
        System.out.println("                   | |__|  __/ (_| |  __/ | | | (_| |");
        System.out.println("                   |_____\\___|\\__, |\\___|_| |_|\\__,_|");
        System.out.println("                            | |___/");
        System.out.println("                           _|   |");
        System.out.println("                     ___  / _|  |   ________  _____");
        System.out.println("                    / _ \\| |_   |  |__  / _ \\| ____|");
        System.out.println("                   | (_) |  _|  |    / / | | |  _|");
        System.out.println("                    \\___/|_||   |   / /| |_| | |___");
        System.out.println("                            |   |  /____\\___/|_____|");
        System.out.println("                            \\   /");
        System.out.println("                              V");
        System.out.println("Press ENTER to start");
        Scanner s = new Scanner(System.in);
        s.nextLine();
    }

    public static void afficherVictoire() {

        System.out.println("          Congratulations! You found the six pieces");
        System.out.println("          who complete the Hexaforce, the world is saved!");
        System.out.println("                           Δ");
        System.out.println("                          Δ Δ");
        System.out.println("                         Δ Δ Δ");
        System.out.println("                          \\o/");
        System.out.println("                           |");
        System.out.println("                          / \\");
    }

    public static void afficherDefaite() {

        System.out.println("          Noooo! Zoe died before collecting all six pieces of the Hexaforce...");
        System.out.println("             	Better luck next time!");
        System.out.println("                           o ");
        System.out.println("                          /|\\");
        System.out.println("                          / \\");
    }
   
}