import java.util.*;
import java.io.IOException;

public class DodajPojazd {
    static ArrayList<Osobowka> listaOsobowek = new ArrayList<>();
    static ArrayList<Dostawczak> lisaDostawczakow = new ArrayList<>();

    public static void dodawanie() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- MENU DODAWANIA POJAZDU ---");
        System.out.println("1. Dodaj osobowkę");
        System.out.println("2. Dodaj dostawczaka");
        System.out.print("Wybierz opcję: ");

        int wybor = scanner.nextInt();
        scanner.nextLine(); // Wyczyść bufor

        try {
            // Odśwież dane z pliku przed dodaniem nowego pojazdu
            OdczytZapis.wczytajPrzyUruchomieniu();

            if (wybor == 1) {
                dodajOsobowke();
            } else if (wybor == 2) {
                dodajDostawczaka();
            } else {
                System.out.println("Nieprawidłowy wybór!");
            }
        } catch (IOException e) {
            System.out.println("Błąd operacji na pliku: " + e.getMessage());
        } catch (PojazdException e) {
            System.out.println("Błąd danych: " + e.getMessage());
        }
    }

    private static void dodajOsobowke() throws IOException, PojazdException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nPodaj markę: ");
        String marka = scanner.nextLine();

        System.out.print("Podaj model: ");
        String model = scanner.nextLine();

        System.out.print("Podaj rocznik: ");
        int rocznik = scanner.nextInt();
        if (rocznik < 1900 || rocznik > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new ZlyRocznikException();
        }

        System.out.print("Podaj liczbę miejsc: ");
        int miejsca = scanner.nextInt();
        if (miejsca <= 0) {
            throw new ZlaLiczbaMiejscException();
        }

        // Utwórz nową osobowkę z unikalnym ID
        Osobowka nowa = new Osobowka(marka, model, rocznik, miejsca);
        nowa.setId(OdczytZapis.getNoweId());
        listaOsobowek.add(nowa);

        // Zapisz zmiany do pliku
        OdczytZapis.zapiszPojazdy();
        System.out.printf("\nDodano osobowkę! ID: %d\n", nowa.getId());
    }

    private static void dodajDostawczaka() throws IOException, PojazdException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nPodaj markę: ");
        String marka = scanner.nextLine();

        System.out.print("Podaj model: ");
        String model = scanner.nextLine();

        System.out.print("Podaj rocznik: ");
        int rocznik = scanner.nextInt();
        if (rocznik < 1900 || rocznik > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new ZlyRocznikException();
        }

        System.out.print("Podaj ładowność: ");
        float ladownosc = scanner.nextFloat();
        if (ladownosc < 0) {
            throw new ZlaLadownoscException();
        }

        // Utwórz nowego dostawczaka z unikalnym ID
        Dostawczak nowy = new Dostawczak(marka, model, rocznik, ladownosc);
        nowy.setId(OdczytZapis.getNoweId());
        lisaDostawczakow.add(nowy);

        // Zapisz zmiany do pliku
        OdczytZapis.zapiszPojazdy();
        System.out.printf("\nDodano dostawczaka! ID: %d\n", nowy.getId());
    }
}