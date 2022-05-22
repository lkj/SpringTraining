package xyz.lokaj.productPlacement.exception;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(Long id) {
        super("Nie znaleziono wpisu o id: "+id);
    }
}
