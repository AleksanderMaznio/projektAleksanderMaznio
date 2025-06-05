import java.util.*;

public class DodajPojazd {

    static ArrayList<Osobowka> listaOsobowek = new ArrayList<>(); // przenieś listę poza metodę
    static ArrayList<Dostawczak> lisaDostawczakow = new ArrayList<>();

    public static void dodawanie() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Jaki pojazd chcesz dodać?");
        System.out.println("1. Osobowka");
        System.out.println("2. Dostawczak");

        int wybor = scanner.nextInt();
        scanner.nextLine(); // <-- WYCZYŚĆ bufor po nextInt()

        if (wybor == 1) {

            System.out.print("Podaj markę: ");
            String b = scanner.nextLine();

            System.out.print("Podaj model: ");
            String c = scanner.nextLine();

            System.out.print("Podaj rocznik: ");
            int d = scanner.nextInt();

            System.out.print("Podaj liczbę miejsc: ");
            int e = scanner.nextInt();

            Osobowka nowaOsobowka = new Osobowka( b, c, d, e);
            listaOsobowek.add(nowaOsobowka);

            System.out.println("Dodano osobówkę!");
        }else if(wybor==2){


            System.out.print("Podaj markę: ");
            String b = scanner.nextLine();

            System.out.print("Podaj model: ");
            String c = scanner.nextLine();

            System.out.print("Podaj rocznik: ");
            int d = scanner.nextInt();

            System.out.println("Podaj ładowność: ");
            double e = scanner.nextDouble();

            Dostawczak nowyDostawczak=new Dostawczak(b,c,d,e);
            lisaDostawczakow.add(nowyDostawczak);
            System.out.println("Dodano dostawczak");
        }else {
            System.out.println("Zła wartość!");
        }
    }
}