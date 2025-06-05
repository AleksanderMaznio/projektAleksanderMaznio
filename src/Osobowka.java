public class Osobowka extends Pojazd {
    private int liczbaMiejsc;

    public Osobowka(int id, String marka, String model, int rokProdukcji, int liczbaMiejsc) {
        super(id, marka, model, rokProdukcji);
        this.liczbaMiejsc = liczbaMiejsc;
    }

    @Override
    public String toString() {
        return super.toString()+" Liczba miejsc: " + liczbaMiejsc+"\n";
    }
}


