package xyz.lokaj.productPlacement.exception;

public class IllegalOrderStateException extends RuntimeException {
    public IllegalOrderStateException(Long id) {
        super("Nie można już zmienić stanu dla zamówienia o id:"+id);
    }
}
