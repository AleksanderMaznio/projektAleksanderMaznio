import java.util.Scanner;

public class Menu {

    public static void pokazMenu() {
        // Wczytanie danych przy uruchomieniu
        OdczytZapis.wczytajPrzyUruchomieniu();


        Scanner scanner = new Scanner(System.in);
        boolean p = true; // zmienna sterująca pętlą głównego menu
        int wybor;

        System.out.println("=== MENU SYSTEMU ZARZĄDZANIA FLOTĄ POJAZDÓW ===");

        while (p) {
            // Wyświetlenie opcji menu
            System.out.println("1. Dodaj pojazd");
            System.out.println("2. Usuń pojazd");
            System.out.println("3. Wypisz listę pojazdów");
            System.out.println("4. Edytuj pojazd");
            System.out.println("5. Zakończ");
            System.out.print("Wybierz opcję: ");

            // Walidacja wejścia użytkownika
            while (!scanner.hasNextInt()) {
                System.out.print("To nie jest liczba. Spróbuj jeszcze raz: ");
                scanner.next();
            }

            wybor = scanner.nextInt();
            scanner.nextLine(); // Czyszczenie bufora wejściowego

            // Obsługa wybranej opcji
            switch (wybor) {
                case 1:
                    System.out.println("Dodawanie pojazdu...");
                    DodajPojazd.dodawanie();
                    OdczytZapis.zapiszPojazdy();
                    break;

                case 2:
                    System.out.println("Usuwanie pojazdu...");
                    UsunPojazd.usuwanie();
                    break;

                case 3:
                    System.out.println("Lista pojazdów:");
                    System.out.println("Wybierz pojazdy, których listę chcesz zobaczyć");
                    System.out.println("1. Osobowki");
                    System.out.println("2. Dostawczaki");

                    while (!scanner.hasNextInt()) {
                        System.out.print("To nie jest liczba. Spróbuj jeszcze raz: ");
                        scanner.next();
                    }

                    int co = scanner.nextInt();
                    scanner.nextLine(); // Czyszczenie bufora

                    if (co == 1) {
                        System.out.println("\nLista osobówek:");
                        for (Osobowka o : DodajPojazd.listaOsobowek) {
                            System.out.println(o); // Wypisuje wynik metody toString()
                        }
                    } else if (co == 2) {
                        System.out.println("\nLista dostawczaków:");
                        for (Dostawczak d : DodajPojazd.lisaDostawczakow) {
                            System.out.println(d);
                        }
                    } else {
                        System.out.println("Nieprawidłowy wybór!");
                    }
                    break;

                case 4:
                    EdytujPojazd.menuEdycji();
                    break;

                case 5:
                    System.out.println("Zamykanie programu...");
                    p = false;
                    break;

                default:
                    System.out.println("Nieprawidłowa opcja. Spróbuj ponownie.");
            }

            System.out.println(); // Pusta linia dla lepszej czytelności między iteracjami
        }

        scanner.close(); // Zamknięcie skanera po zakończeniu działania programu
    }
}
