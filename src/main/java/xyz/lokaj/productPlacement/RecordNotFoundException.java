package xyz.lokaj.productPlacement;

class RecordNotFoundException extends RuntimeException {
    RecordNotFoundException(Long id) {
        super("Nie znaleziono wpisu o id: "+id);
    }
}
