public abstract class Pojazd {
    private int id;
    private String marka;
    private String model;
    private int rokProdukcji;

    public Pojazd(int id, String marka, String model, int rokProdukcji) {
        this.id = id;
        this.marka = marka;
        this.model = model;
        this.rokProdukcji = rokProdukcji;
    }
    public void wypiszInformacje(){
        System.out.println("Id: " + id + " \nMarka: " + marka + " \nModel: " + model + " \nRok produkcji: " + rokProdukcji);
    }

    public int getId() {
        return id;
    }

    public int getRokProdukcji() {
        return rokProdukcji;
    }

    public String getModel() {
        return model;
    }

    public String getMarka() {
        return marka;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRokProdukcji(int rokProdukcji) {
        this.rokProdukcji = rokProdukcji;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    @Override
    public String toString() {
        return "id: " + id +
                " marka: " + marka +
                " model: " + model +
                " rokProdukcji " + rokProdukcji;
    }
}

