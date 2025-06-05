public class Dostawczak extends Pojazd {
    private double pojemność;

    public Dostawczak(int id, String marka, String model, int rokProdukcji, double pojemność) {
        super(id, marka, model, rokProdukcji);
        this.pojemność = pojemność;
    }

    @Override
    public String toString() {
        return super.toString()+ " Pojemność: " + pojemność;
    }
}

