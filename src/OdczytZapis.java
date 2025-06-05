import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OdczytZapis {
    private static final String NAZWA_PLIKU = "pojazdy.txt";
    private static final String TYP_OSOBOWKA = "OSOBOWKA";
    private static final String TYP_DOSTAWCZAK = "DOSTAWCZAK";

    // Zapis obu typów pojazdów
    public static void zapiszPojazdy() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(NAZWA_PLIKU))) {
            // Zapisz osobowki
            if (DodajPojazd.listaOsobowek != null) {
                for (Osobowka o : DodajPojazd.listaOsobowek) {
                    writer.println(String.format("%s;%d;%s;%s;%d;%d",
                            TYP_OSOBOWKA,
                            o.getId(),
                            o.getMarka(),
                            o.getModel(),
                            o.getRokProdukcji(),
                            o.getLiczbaMiejsc()));
                }
            }

            // Zapisz dostawczaki
            if (DodajPojazd.lisaDostawczakow != null) {
                for (Dostawczak d : DodajPojazd.lisaDostawczakow) {
                    writer.println(String.format("%s;%d;%s;%s;%d;%.2f",
                            TYP_DOSTAWCZAK,
                            d.getId(),
                            d.getMarka(),
                            d.getModel(),
                            d.getRokProdukcji(),
                            d.getPojemność()));
                }
            }

            System.out.println("Zapisano pojazdy do pliku (" +
                    (DodajPojazd.listaOsobowek.size() + " osobowek, " +
                            DodajPojazd.lisaDostawczakow.size() + " dostawczaków"));

        } catch (IOException e) {
            System.err.println("Błąd zapisu do pliku: " + e.getMessage());
        }
    }

    // Wczytywanie z rozróżnieniem typów
    public static void wczytajPrzyUruchomieniu() {
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

                    String[] dane = linia.split(";");
                    if (dane.length < 6) {
                        System.err.println("Nieprawidłowy format linii: " + linia);
                        continue;
                    }

                    try {
                        switch (dane[0]) {
                            case TYP_OSOBOWKA:
                                DodajPojazd.listaOsobowek.add(new Osobowka(

                                        dane[2],
                                        dane[3],
                                        Integer.parseInt(dane[4]),
                                        Integer.parseInt(dane[5])
                                ));
                                break;
                            case TYP_DOSTAWCZAK:
                                DodajPojazd.lisaDostawczakow.add(new Dostawczak(

                                        dane[2],
                                        dane[3],
                                        Integer.parseInt(dane[4]),
                                        Double.parseDouble(dane[5])
                                ));
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

    // Wyświetlanie zawartości pliku z podziałem na typy
    public static void wczytajPojazdy() {
        try {
            File plik = new File(NAZWA_PLIKU);
            if (!plik.exists() || plik.length() == 0) {
                System.out.println("Plik jest pusty lub nie istnieje!");
                return;
            }

            try (Scanner scanner = new Scanner(plik)) {
                System.out.println("\nZawartość pliku:");
                while (scanner.hasNextLine()) {
                    String linia = scanner.nextLine();
                    String[] parts = linia.split(";");
                    if (parts.length > 0) {
                        String typ = parts[0];
                        System.out.println("[" + typ + "] " + linia.substring(typ.length() + 1));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Błąd odczytu pliku: " + e.getMessage());
        }
    }
}