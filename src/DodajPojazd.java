import java.util.*;
import java.io.IOException;

public class DodajPojazd {
    // Listy przechowujące pojazdy w pamięci
    static ArrayList<Osobowka> listaOsobowek = new ArrayList<>();
    static ArrayList<Dostawczak> lisaDostawczakow = new ArrayList<>();

    // Metoda główna do dodawania pojazdów
    public static void dodawanie() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- MENU DODAWANIA POJAZDU ---");
        System.out.println("1. Dodaj osobówkę");
        System.out.println("2. Dodaj dostawczaka");
        System.out.print("Wybierz opcję: ");

        int wybor = scanner.nextInt();
        scanner.nextLine(); // Czyścimy bufor

        try {
            // Wczytaj dane z pliku, aby zachować aktualność list
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

    // Dodaje nową osobówkę z walidacją danych
    private static void dodajOsobowke() throws IOException, PojazdException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nPodaj markę: ");
        String marka = scanner.nextLine();

        System.out.print("Podaj model: ");
        String model = scanner.nextLine();

        System.out.print("Podaj rocznik: ");
        int rocznik=0;
        try {
             rocznik = scanner.nextInt();
        }catch (Exception e){
            e.getMessage();
        }

        if (rocznik < 1900 || rocznik > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new ZlyRocznikException(); // walidacja rocznika
        }

        System.out.print("Podaj liczbę miejsc: ");
        int miejsca = 0;
        try {
            miejsca = scanner.nextInt();
        }catch (Exception e){
            e.getMessage();
        }
        if (miejsca <= 0) {
            throw new ZlaLiczbaMiejscException(); // walidacja liczby miejsc
        }

        // Tworzenie i dodanie nowej osobówki
        Osobowka nowa = new Osobowka(marka, model, rocznik, miejsca);
        nowa.setId(OdczytZapis.getNoweId()); // unikalne ID
        listaOsobowek.add(nowa);

        OdczytZapis.zapiszPojazdy();
        System.out.printf("\nDodano osobówkę! ID: %d\n", nowa.getId());
    }

    // Dodaje nowego dostawczaka z walidacją danych
    private static void dodajDostawczaka() throws IOException, PojazdException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nPodaj markę: ");
        String marka = scanner.nextLine();

        System.out.print("Podaj model: ");
        String model = scanner.nextLine();

        System.out.print("Podaj rocznik: ");
        int rocznik=0;
        try {
            rocznik = scanner.nextInt();
        }catch (Exception e){
            e.getMessage();
        }

        if (rocznik < 1900 || rocznik > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new ZlyRocznikException(); // walidacja rocznika
        }

        System.out.print("Podaj ładowność: ");
        float ladownosc;

        try {
            ladownosc = scanner.nextFloat();
            if (ladownosc < 0) {
                throw new ZlaLadownoscException(); // Ładowność nie może być ujemna
            }
        } catch (InputMismatchException e) {
            throw new ZlaLadownoscException(); // Wpisano literę lub niepoprawny format
        }


        // Tworzenie i dodanie nowego dostawczaka
        Dostawczak nowy = new Dostawczak(marka, model, rocznik, ladownosc);
        nowy.setId(OdczytZapis.getNoweId()); // unikalne ID
        lisaDostawczakow.add(nowy);

        OdczytZapis.zapiszPojazdy();
        System.out.printf("\nDodano dostawczaka! ID: %d\n", nowy.getId());
    }
}
