public abstract class Pojazd {
    // Statyczna zmienna przechowująca najwyższe przypisane ID
    private static int ostatnieId = 0;

    // Unikalne ID pojazdu (ustalane automatycznie)
    private int id;

    private String marka;
    private String model;
    private int rokProdukcji;

    // Konstruktor bazowy — ustawia dane i automatycznie nadaje unikalne ID
    public Pojazd(String marka, String model, int rokProdukcji) {
        this.id = ++ostatnieId; // Autoinkrementacja ID
        this.marka = marka;
        this.model = model;
        this.rokProdukcji = rokProdukcji;
    }

    // Metoda do wyświetlania szczegółowych informacji o pojeździe
    public void wypiszInformacje() {
        System.out.println("Id: " + id +
                "\nMarka: " + marka +
                "\nModel: " + model +
                "\nRok produkcji: " + rokProdukcji);
    }

    // === Gettery ===
    public int getId() {
        return id;
    }

    public String getMarka() {
        return marka;
    }

    public String getModel() {
        return model;
    }

    public int getRokProdukcji() {
        return rokProdukcji;
    }

    // === Settery ===
    public void setMarka(String marka) {
        this.marka = marka;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setRokProdukcji(int rokProdukcji) {
        this.rokProdukcji = rokProdukcji;
    }

    // UWAGA: Zmienianie ID ręcznie powinno być robione ostrożnie
    public void setId(int id) {
        this.id = id;
    }

    // Zwraca dane pojazdu jako tekst (do debugowania, listowania itp.)
    @Override
    public String toString() {
        return "id: " + id +
                " marka: " + marka +
                " model: " + model +
                " rokProdukcji: " + rokProdukcji;
    }

    // Ustawia wartość ostatniego ID (np. po wczytaniu danych z pliku)
    public static void ustawOstatnieId(int id) {
        if (id > ostatnieId) {
            ostatnieId = id;
        }
    }
}
