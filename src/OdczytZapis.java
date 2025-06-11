import java.io.*;
import java.util.Scanner;

public class OdczytZapis {
    private static final String NAZWA_PLIKU = "pojazdy.txt";
    private static final String TYP_OSOBOWKA = "OSOBOWKA";
    private static final String TYP_DOSTAWCZAK = "DOSTAWCZAK";
    private static int najwyzszeId = 0;

    public static void zapiszPojazdy() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(NAZWA_PLIKU))) {
            // Zapisz osobowki
            for (Osobowka o : DodajPojazd.listaOsobowek) {
                writer.println(String.format("%s;%d;%s;%s;%d;%d",
                        TYP_OSOBOWKA,
                        o.getId(),
                        o.getMarka(),
                        o.getModel(),
                        o.getRokProdukcji(),
                        o.getLiczbaMiejsc()));
            }

            // Zapisz dostawczaki
            for (Dostawczak d : DodajPojazd.lisaDostawczakow) {
                writer.println(String.format("%s;%d;%s;%s;%d;%.2f",
                        TYP_DOSTAWCZAK,
                        d.getId(),
                        d.getMarka(),
                        d.getModel(),
                        d.getRokProdukcji(),
                        d.getPojemność()));
            }

            System.out.println("Zapisano pojazdy do pliku (" +
                    DodajPojazd.listaOsobowek.size() + " osobowek, " +
                    DodajPojazd.lisaDostawczakow.size() + " dostawczaków)");

        } catch (IOException e) {
            System.err.println("Błąd zapisu do pliku: " + e.getMessage());
        }
    }

    public static void wczytajPrzyUruchomieniu() {
        DodajPojazd.listaOsobowek.clear();
        DodajPojazd.lisaDostawczakow.clear();
        najwyzszeId = 0;

        try {
            File plik = new File(NAZWA_PLIKU);

            if (!plik.exists()) {
                System.out.println("Brak pliku danych - zostanie utworzony przy pierwszym zapisie");
                return;
            }

            try (Scanner scanner = new Scanner(plik)) {
                while (scanner.hasNextLine()) {
                    String linia = scanner.nextLine().trim();
                    if (linia.isEmpty()) continue;
                    linia = linia.replace(",", ".");
                    String[] dane = linia.split(";");
                    if (dane.length < 6) {
                        System.err.println("Nieprawidłowy format linii: " + linia);
                        continue;
                    }

                    try {
                        int id = Integer.parseInt(dane[1]);
                        if (id > najwyzszeId) {
                            najwyzszeId = id;
                        }

                        switch (dane[0]) {
                            case TYP_OSOBOWKA:
                                Osobowka o = new Osobowka(
                                        dane[2],
                                        dane[3],
                                        Integer.parseInt(dane[4]),
                                        Integer.parseInt(dane[5]));
                                o.setId(id);
                                DodajPojazd.listaOsobowek.add(o);
                                break;
                            case TYP_DOSTAWCZAK:
                                Dostawczak d = new Dostawczak(
                                        dane[2],
                                        dane[3],
                                        Integer.parseInt(dane[4]),
                                        Float.parseFloat(dane[5]));
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

    public static int getNoweId() {
        return ++najwyzszeId;
    }
}