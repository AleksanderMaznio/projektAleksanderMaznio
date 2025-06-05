import java.io.*;
import java.util.Scanner;

public class OdczytZapis {
    private static final String NAZWA_PLIKU = "pojazdy.txt";

    public static void zapiszPojazdy() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(NAZWA_PLIKU))) {
            for (Osobowka o: DodajPojazd.listaOsobowek){
                writer.println(
                        o.getId()+";"+
                        o.getMarka()+";"+
                        o.getModel()+";"+
                        o.getRokProdukcji()+";"+
                        o.getLiczbaMiejsc()
                );
            }
            System.out.println("Zapisano pojazdy do pliku");
        } catch (IOException e) {
            System.err.println("Błąd zapisu: " + e.getMessage());
        }
    }
        public static void wczytajPojazdy(){
        try {
            File plik=new File("pojazdy.txt");
            Scanner scanner = new Scanner(plik);
            while (scanner.hasNextLine()){
                String linia = scanner.nextLine();
                System.out.println(linia);
            }

        }catch (FileNotFoundException e){
            System.out.println("Plik nie znaleziony!");
            e.printStackTrace();
        }

    }
    public static void wczytajPrzyUruchomieniu(){
        try {
            File plik=new File("pojazdy.txt");
            Scanner scanner = new Scanner(plik);
            while (scanner.hasNextLine()){
                String linia = scanner.nextLine();
                String[] tablica=linia.split(";");
                DodajPojazd.listaOsobowek.add(new Osobowka(
                Integer.parseInt(tablica[0]),
                        tablica[1],
                        tablica[2],
                        Integer.parseInt(tablica[3]),
                        Integer.parseInt(tablica[4])
                ));

            }

        }catch (FileNotFoundException e){
            System.out.println("Plik nie znaleziony!");
            e.printStackTrace();
        }

    }
}