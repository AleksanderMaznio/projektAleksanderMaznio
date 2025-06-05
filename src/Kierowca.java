public class Kierowca {
    private int id;
    private String imie;
    private String nazwisko;

    public Kierowca(int id, String imie, String nazwisko) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public int getId() {
        return id;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    @Override
    public String toString() {
        return "Kierowca:" +
                " id: " + id +
                " imie: '" + imie + '\'' +
                " nazwisko: '" + nazwisko + '\'';
    }
}
