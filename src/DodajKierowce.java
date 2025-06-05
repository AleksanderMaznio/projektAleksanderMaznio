import java.util.ArrayList;
import java.util.Scanner;

public class DodajKierowce {
    static ArrayList<Kierowca>listaKierowcow=new ArrayList<>();

    public static void DodawanieOsob(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj Id:");
        int a=scanner.nextInt();
        scanner.nextLine(); // czyszczenie bufora
        System.out.println("Podaj imie: ");
        String b=scanner.nextLine();
        System.out.println("Podaj nazwisko");
        String c=scanner.nextLine();

        Kierowca nowyKierowca=new Kierowca(a,b,c);
        listaKierowcow.add(nowyKierowca);
    }
}
