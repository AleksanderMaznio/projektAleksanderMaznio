public class PojazdException extends Exception {
    public PojazdException(String message) {
        super(message);
    }
}

class ZlaLadownoscException extends PojazdException {
    public ZlaLadownoscException() {
        super("Ładowność nie może być mniejsza niż 0!");
    }
}

class ZlyRocznikException extends PojazdException {
    public ZlyRocznikException() {
        super("Rocznik musi być większy niż 1900 i nie większy niż aktualny rok!");
    }
}

class ZlaLiczbaMiejscException extends PojazdException {
    public ZlaLiczbaMiejscException() {
        super("Liczba miejsc musi być większa od 0!");
    }
}