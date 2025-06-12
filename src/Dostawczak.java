public class Dostawczak extends Pojazd {
    private float ladownosc;

    public Dostawczak(String marka, String model, int rokProdukcji, float ladownosc) {
        super(marka, model, rokProdukcji);
        this.ladownosc = ladownosc;
    }

    public float getLadownosc() {
        return ladownosc;
    }

    public void setLadownosc(float ladownosc) {
        this.ladownosc = ladownosc;
    }

    @Override
    public String toString() {
        return super.toString() + "  Ładowność: " + ladownosc;
    }
}
