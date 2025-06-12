import java.io.IOException;
import java.util.Scanner;

public class UsunPojazd {

    // Główne menu usuwania pojazdów
    public static void usuwanie() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- MENU USUWANIA POJAZDÓW ---");
        System.out.println("1. Usuń osobowkę");
        System.out.println("2. Usuń dostawczaka");
        System.out.println("3. Usuń po ID");
        System.out.print("Wybierz opcję: ");

        int wybor = scanner.nextInt();
        scanner.nextLine(); // Wyczyść bufor

        try {
            // Odświeżenie danych z pliku
            OdczytZapis.wczytajPrzyUruchomieniu();

            // Obsługa wybranej opcji
            switch (wybor) {
                case 1:
                    usunOsobowke();
                    break;
                case 2:
                    usunDostawczaka();
                    break;
                case 3:
                    usunPoId();
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór!");
            }
        } catch (IOException e) {
            System.out.println("Błąd operacji na pliku: " + e.getMessage());
        }
    }

    // Usuwanie osobówki na podstawie numeru z listy
    private static void usunOsobowke() throws IOException {
        if (DodajPojazd.listaOsobowek.isEmpty()) {
            System.out.println("\nBrak osobowek w bazie!");
            return;
        }

        // Wyświetlenie listy osobówek
        System.out.println("\nLista osobowek:");
        for (int i = 0; i < DodajPojazd.listaOsobowek.size(); i++) {
            Osobowka o = DodajPojazd.listaOsobowek.get(i);
            System.out.printf("%d. ID: %d | %s %s (rocznik: %d, miejsca: %d)\n",
                    i + 1, o.getId(), o.getMarka(), o.getModel(),
                    o.getRokProdukcji(), o.getLiczbaMiejsc());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPodaj numer osobowki do usunięcia: ");
        int index = scanner.nextInt() - 1;

        // Usunięcie pojazdu z listy
        if (index >= 0 && index < DodajPojazd.listaOsobowek.size()) {
            int usunieteId = DodajPojazd.listaOsobowek.get(index).getId();
            DodajPojazd.listaOsobowek.remove(index);
            OdczytZapis.zapiszPojazdy();
            System.out.printf("\nOsobowka o ID %d została usunięta!\n", usunieteId);
        } else {
            System.out.println("\nNieprawidłowy numer!");
        }
    }

    // Usuwanie dostawczaka na podstawie numeru z listy
    private static void usunDostawczaka() throws IOException {
        if (DodajPojazd.lisaDostawczakow.isEmpty()) {
            System.out.println("\nBrak dostawczaków w bazie!");
            return;
        }

        // Wyświetlenie listy dostawczaków
        System.out.println("\nLista dostawczaków:");
        for (int i = 0; i < DodajPojazd.lisaDostawczakow.size(); i++) {
            Dostawczak d = DodajPojazd.lisaDostawczakow.get(i);
            System.out.printf("%d. ID: %d | %s %s (rocznik: %d, ładowność: %.2f)\n",
                    i + 1, d.getId(), d.getMarka(), d.getModel(),
                    d.getRokProdukcji(), d.getLadownosc());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPodaj numer dostawczaka do usunięcia: ");
        int index = scanner.nextInt() - 1;

        // Usunięcie pojazdu z listy
        if (index >= 0 && index < DodajPojazd.lisaDostawczakow.size()) {
            int usunieteId = DodajPojazd.lisaDostawczakow.get(index).getId();
            DodajPojazd.lisaDostawczakow.remove(index);
            OdczytZapis.zapiszPojazdy();
            System.out.printf("\nDostawczak o ID %d został usunięty!\n", usunieteId);
        } else {
            System.out.println("\nNieprawidłowy numer!");
        }
    }

    // Usuwanie pojazdu na podstawie ID (z osobówek lub dostawczaków)
    public static void usunPoId() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPodaj ID pojazdu do usunięcia: ");
        int id = scanner.nextInt();

        boolean znaleziono = false;

        // Przeszukiwanie listy osobówek
        for (int i = 0; i < DodajPojazd.listaOsobowek.size(); i++) {
            if (DodajPojazd.listaOsobowek.get(i).getId() == id) {
                DodajPojazd.listaOsobowek.remove(i);
                znaleziono = true;
                break;
            }
        }

        // Jeśli nie znaleziono w osobówkach, sprawdź dostawczaki
        if (!znaleziono) {
            for (int i = 0; i < DodajPojazd.lisaDostawczakow.size(); i++) {
                if (DodajPojazd.lisaDostawczakow.get(i).getId() == id) {
                    DodajPojazd.lisaDostawczakow.remove(i);
                    znaleziono = true;
                    break;
                }
            }
        }

        // Wyświetlenie wyniku operacji
        if (znaleziono) {
            OdczytZapis.zapiszPojazdy();
            System.out.printf("\nPojazd o ID %d został usunięty!\n", id);
        } else {
            System.out.println("\nNie znaleziono pojazdu o podanym ID!");
        }
    }
}
