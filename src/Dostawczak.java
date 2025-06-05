public class Dostawczak extends Pojazd {
    private double pojemność;

    public double getPojemność() {
        return pojemność;
    }

    public void setPojemność(double pojemność) {
        this.pojemność = pojemność;
    }

    public Dostawczak( String marka, String model, int rokProdukcji, double pojemność) {
        super(marka, model, rokProdukcji);
        this.pojemność = pojemność;
    }

    @Override
    public String toString() {
        return super.toString()+ "Pojemność: " + pojemność;
    }
}

