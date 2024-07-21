package practicumopdracht;

import javafx.application.Application;
import java.time.LocalDate;

/**
 * Main class of the project
 * @author Huseyin Altunbas
 */
public class Main {

    public static String studentNaam = "Huseyin Altunbas";
    public static String studentNummer = "500854440";
    private static boolean jaIkAccepteer = true;

    public static boolean launchedFromMain;

    public static void main(String[] args) {
        launchedFromMain = true;

        if(!jaIkAccepteer) {
            showTegridy();

            return;
        }

        Application.launch(MainApplication.class, args);
    }

    private static void showTegridy() {
        System.out.println("Integriteitsverklaring\n---");
        System.out.printf("Datum: %s%n", LocalDate.now());
        System.out.printf("Naam: %s%n", studentNaam);
        System.out.printf("Studentnummer: %s%n", studentNummer);
        System.out.println("---");

        String integriteitsVerklaring =
            "Ik verklaar naar eer en geweten dat ik deze practicumopdracht zelf zal maken en geen plagiaat zal plegen " +
            "door code van anderen over te nemen.\n\n" +

            "Ik ben me ervan bewust dat:\n"+
            "\t- Er (geautomatiseerd) op fraude wordt gescanned\n" +
            "\t- Verdachte situaties worden gemeld aan de examencommissie\n" +
            "\t- Fraude kan leiden tot het ongeldig verklaren van deze practicumopdracht voor alle studenten\n\n" +

            "Door 'jaIkAccepteer' in de Main-class op 'true' te zetten, onderteken ik deze verklaring.";

        System.out.println(integriteitsVerklaring);
    }
}
