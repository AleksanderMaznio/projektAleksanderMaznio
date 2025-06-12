import java.io.*;
import java.util.Scanner;

public class OdczytZapis {
    private static final String NAZWA_PLIKU = "pojazdy.txt";
    private static final String TYP_OSOBOWKA = "OSOBOWKA";
    private static final String TYP_DOSTAWCZAK = "DOSTAWCZAK";

    // Zmienna przechowująca najwyższe ID pojazdu wczytane z pliku
    private static int najwyzszeId = 0;

    /**
     * Metoda zapisuje wszystkie pojazdy (osobówki i dostawczaki) do pliku tekstowego.
     */
    public static void zapiszPojazdy() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(NAZWA_PLIKU))) {
            // Zapisz wszystkie osobówki do pliku
            for (Osobowka o : DodajPojazd.listaOsobowek) {
                writer.println(String.format("%s;%d;%s;%s;%d;%d",
                        TYP_OSOBOWKA,
                        o.getId(),
                        o.getMarka(),
                        o.getModel(),
                        o.getRokProdukcji(),
                        o.getLiczbaMiejsc()));
            }

            // Zapisz wszystkie dostawczaki do pliku
            for (Dostawczak d : DodajPojazd.lisaDostawczakow) {
                writer.println(String.format("%s;%d;%s;%s;%d;%.2f",
                        TYP_DOSTAWCZAK,
                        d.getId(),
                        d.getMarka(),
                        d.getModel(),
                        d.getRokProdukcji(),
                        d.getLadownosc()));
            }

            System.out.println("Zapisano pojazdy do pliku (" +
                    DodajPojazd.listaOsobowek.size() + " osobowek, " +
                    DodajPojazd.lisaDostawczakow.size() + " dostawczaków)");

        } catch (IOException e) {
            System.err.println("Błąd zapisu do pliku: " + e.getMessage());
        }
    }

    /**
     * Metoda wczytuje pojazdy z pliku przy uruchomieniu programu.
     * Wczytane pojazdy są dodawane do odpowiednich list w klasie DodajPojazd.
     */
    public static void wczytajPrzyUruchomieniu() {
        // Wyczyść listy przed wczytaniem nowych danych
        DodajPojazd.listaOsobowek.clear();
        DodajPojazd.lisaDostawczakow.clear();
        najwyzszeId = 0;

        try {
            File plik = new File(NAZWA_PLIKU);

            // Jeśli plik nie istnieje, wypisz info i zakończ wczytywanie
            if (!plik.exists()) {
                System.out.println("Brak pliku danych - zostanie utworzony przy pierwszym zapisie");
                return;
            }

            // Odczyt linii z pliku
            try (Scanner scanner = new Scanner(plik)) {
                while (scanner.hasNextLine()) {
                    String linia = scanner.nextLine().trim();

                    if (linia.isEmpty()) continue;

                    // Zamień przecinki na kropki, by poprawnie sparsować floaty
                    linia = linia.replace(",", ".");

                    String[] dane = linia.split(";");

                    // Sprawdź czy linia ma wystarczającą ilość pól
                    if (dane.length < 6) {
                        System.err.println("Nieprawidłowy format linii: " + linia);
                        continue;
                    }

                    try {
                        // Parsuj ID i aktualizuj najwyższeId jeśli trzeba
                        int id = Integer.parseInt(dane[1]);
                        if (id > najwyzszeId) {
                            najwyzszeId = id;
                        }

                        // Rozpoznaj typ pojazdu i utwórz odpowiedni obiekt
                        switch (dane[0]) {
                            case TYP_OSOBOWKA:
                                Osobowka o = new Osobowka(
                                        dane[2],                             // marka
                                        dane[3],                             // model
                                        Integer.parseInt(dane[4]),          // rok produkcji
                                        Integer.parseInt(dane[5])           // liczba miejsc
                                );
                                o.setId(id);
                                DodajPojazd.listaOsobowek.add(o);
                                break;

                            case TYP_DOSTAWCZAK:
                                Dostawczak d = new Dostawczak(
                                        dane[2],                             // marka
                                        dane[3],                             // model
                                        Integer.parseInt(dane[4]),          // rok produkcji
                                        Float.parseFloat(dane[5])           // pojemność (ładowność)
                                );
                                d.setId(id);
                                DodajPojazd.lisaDostawczakow.add(d);
                                break;

                            default:
                                System.err.println("Nieznany typ pojazdu: " + dane[0]);
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Błąd parsowania liczby w linii: " + linia);
                    }
                }
            }

            System.out.println("Wczytano " + DodajPojazd.listaOsobowek.size() +
                    " osobowek i " + DodajPojazd.lisaDostawczakow.size() +
                    " dostawczaków");

        } catch (IOException e) {
            System.err.println("Błąd podczas wczytywania pliku: " + e.getMessage());
        }
    }

    /**
     * Generuje nowe, unikalne ID pojazdu inkrementując najwyższe dotychczas użyte ID.
     *
     * @return nowe unikalne ID
     */
    public static int getNoweId() {
        return ++najwyzszeId;
    }
}
