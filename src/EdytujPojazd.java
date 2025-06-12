import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Klasa służąca do edycji danych pojazdów (osobowych i dostawczych).
 */
public class EdytujPojazd {

    /**
     * Główne menu edycji pojazdów.
     * Pozwala wybrać typ pojazdu do edycji lub powrócić do menu głównego.
     */
    public static void menuEdycji() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- MENU EDYCJI POJAZDÓW ---");
        System.out.println("1. Edytuj osobowkę");
        System.out.println("2. Edytuj dostawczaka");
        System.out.println("3. Wróć do menu głównego");
        System.out.print("Wybierz opcję: ");

        int wybor = scanner.nextInt();
        scanner.nextLine(); // Wyczyść bufor

        try {
            OdczytZapis.wczytajPrzyUruchomieniu(); // Odśwież dane z pliku

            switch (wybor) {
                case 1:
                    edytujOsobowke();
                    break;
                case 2:
                    edytujDostawczaka();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Nieprawidłowy wybór!");
            }
        } catch (IOException e) {
            System.out.println("Błąd operacji na pliku: " + e.getMessage());
        }
    }

    /**
     * Edycja danych pojazdu typu osobówka.
     *
     * @throws IOException jeśli wystąpi problem z odczytem/zapisem pliku.
     */
    private static void edytujOsobowke() throws IOException {
        if (DodajPojazd.listaOsobowek.isEmpty()) {
            System.out.println("\nBrak osobowek w bazie!");
            return;
        }

        System.out.println("\nLista osobowek:");
        for (int i = 0; i < DodajPojazd.listaOsobowek.size(); i++) {
            Osobowka o = DodajPojazd.listaOsobowek.get(i);
            System.out.printf("%d. ID: %d | %s %s (rocznik: %d, miejsca: %d)\n",
                    i + 1, o.getId(), o.getMarka(), o.getModel(),
                    o.getRokProdukcji(), o.getLiczbaMiejsc());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPodaj numer osobowki do edycji: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // Wyczyść bufor

        if (index < 0 || index >= DodajPojazd.listaOsobowek.size()) {
            System.out.println("Nieprawidłowy numer!");
            return;
        }

        Osobowka o = DodajPojazd.listaOsobowek.get(index);
        System.out.println("\nEdycja pojazdu ID: " + o.getId());

        // Edycja marki
        System.out.print("Nowa marka [" + o.getMarka() + "]: ");
        String nowaMarka = scanner.nextLine();
        if (!nowaMarka.isEmpty()) {
            o.setMarka(nowaMarka);
        }

        // Edycja modelu
        System.out.print("Nowy model [" + o.getModel() + "]: ");
        String nowyModel = scanner.nextLine();
        if (!nowyModel.isEmpty()) {
            o.setModel(nowyModel);
        }

        // Edycja rocznika
        System.out.print("Nowy rocznik [" + o.getRokProdukcji() + "]: ");
        String rocznikInput = scanner.nextLine();
        if (!rocznikInput.isEmpty()) {
            try {
                int nowyRocznik = Integer.parseInt(rocznikInput);
                if (nowyRocznik >= 1900 && nowyRocznik <= Calendar.getInstance().get(Calendar.YEAR)) {
                    o.setRokProdukcji(nowyRocznik);
                } else {
                    System.out.println("Nieprawidłowy rocznik! Pozostawiono poprzednią wartość.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Nieprawidłowy format roku! Pozostawiono poprzednią wartość.");
            }
        }

        // Edycja liczby miejsc
        System.out.print("Nowa liczba miejsc [" + o.getLiczbaMiejsc() + "]: ");
        String miejscaInput = scanner.nextLine();
        if (!miejscaInput.isEmpty()) {
            try {
                int noweMiejsca = Integer.parseInt(miejscaInput);
                if (noweMiejsca > 0) {
                    o.setLiczbaMiejsc(noweMiejsca);
                } else {
                    System.out.println("Liczba miejsc musi być dodatnia! Pozostawiono poprzednią wartość.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Nieprawidłowy format! Pozostawiono poprzednią wartość.");
            }
        }

        OdczytZapis.zapiszPojazdy();
        System.out.println("\nPojazd został zaktualizowany!");
    }

    /**
     * Edycja danych pojazdu typu dostawczak.
     *
     * @throws IOException jeśli wystąpi problem z odczytem/zapisem pliku.
     */
    private static void edytujDostawczaka() throws IOException {
        if (DodajPojazd.lisaDostawczakow.isEmpty()) {
            System.out.println("\nBrak dostawczaków w bazie!");
            return;
        }

        System.out.println("\nLista dostawczaków:");
        for (int i = 0; i < DodajPojazd.lisaDostawczakow.size(); i++) {
            Dostawczak d = DodajPojazd.lisaDostawczakow.get(i);
            System.out.printf("%d. ID: %d | %s %s (rocznik: %d, ładowność: %.2f)\n",
                    i + 1, d.getId(), d.getMarka(), d.getModel(),
                    d.getRokProdukcji(), d.getLadownosc());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPodaj numer dostawczaka do edycji: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // Wyczyść bufor

        if (index < 0 || index >= DodajPojazd.lisaDostawczakow.size()) {
            System.out.println("Nieprawidłowy numer!");
            return;
        }

        Dostawczak d = DodajPojazd.lisaDostawczakow.get(index);
        System.out.println("\nEdycja pojazdu ID: " + d.getId());

        // Edycja marki
        System.out.print("Nowa marka [" + d.getMarka() + "]: ");
        String nowaMarka = scanner.nextLine();
        if (!nowaMarka.isEmpty()) {
            d.setMarka(nowaMarka);
        }

        // Edycja modelu
        System.out.print("Nowy model [" + d.getModel() + "]: ");
        String nowyModel = scanner.nextLine();
        if (!nowyModel.isEmpty()) {
            d.setModel(nowyModel);
        }

        // Edycja rocznika
        System.out.print("Nowy rocznik [" + d.getRokProdukcji() + "]: ");
        String rocznikInput = scanner.nextLine();
        if (!rocznikInput.isEmpty()) {
            try {
                int nowyRocznik = Integer.parseInt(rocznikInput);
                if (nowyRocznik >= 1900 && nowyRocznik <= Calendar.getInstance().get(Calendar.YEAR)) {
                    d.setRokProdukcji(nowyRocznik);
                } else {
                    System.out.println("Nieprawidłowy rocznik! Pozostawiono poprzednią wartość.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Nieprawidłowy format roku! Pozostawiono poprzednią wartość.");
            }
        }

        // Edycja ładowności
        System.out.print("Nowa ładowność [" + d.getLadownosc() + "]: ");
        String ladownoscInput = scanner.nextLine();
        if (!ladownoscInput.isEmpty()) {
            try {
                float nowaLadownosc = Float.parseFloat(ladownoscInput.replace(",", "."));
                if (nowaLadownosc >= 0) {
                    d.setLadownosc(nowaLadownosc);
                } else {
                    System.out.println("Ładowność nie może być ujemna! Pozostawiono poprzednią wartość.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Nieprawidłowy format! Pozostawiono poprzednią wartość.");
            }
        }

        OdczytZapis.zapiszPojazdy();
        System.out.println("\nPojazd został zaktualizowany!");
    }
}