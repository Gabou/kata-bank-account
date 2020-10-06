package kata.gabou.bank;

public class Operation {

    private final OperationType type;
    private final Amount amount;

    public Operation(OperationType type, Amount amount) {
        this.type = type;
        this.amount = amount;
    }

    public OperationType type() {
        return type;
    }

    public Amount amount() {
        return amount;
    }
}
