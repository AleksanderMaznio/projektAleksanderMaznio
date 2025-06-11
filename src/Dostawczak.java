public class Dostawczak extends Pojazd {
    private float pojemność;

    public float getPojemność() {
        return pojemność;
    }


    public void setPojemność(float pojemność) {
        this.pojemność = pojemność;
    }

    public Dostawczak( String marka, String model, int rokProdukcji, float pojemność) {
        super(marka, model, rokProdukcji);
        this.pojemność = pojemność;
    }

    @Override
    public String toString() {
        return super.toString()+ "Pojemność: " + pojemność;
    }
}

