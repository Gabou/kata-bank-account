package kata.gabou.bank;

public class NotEnoughSavingsException extends Exception {
    public NotEnoughSavingsException(String message) {
        super(message);
    }
}
