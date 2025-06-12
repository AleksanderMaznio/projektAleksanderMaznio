public class Osobowka extends Pojazd {
    private int liczbaMiejsc;

    public Osobowka(String marka, String model, int rokProdukcji, int liczbaMiejsc) {
        super(marka, model, rokProdukcji); // ID przypisuje nadklasa Pojazd
        this.liczbaMiejsc = liczbaMiejsc;
    }

    public int getLiczbaMiejsc() {
        return liczbaMiejsc;
    }

    public void setLiczbaMiejsc(int liczbaMiejsc) {
        this.liczbaMiejsc = liczbaMiejsc;
    }

    @Override
    public String toString() {
        return super.toString() + "  Liczba miejsc: " + liczbaMiejsc;
    }
}
