package kata.gabou.bank;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return type == operation.type &&
                Objects.equals(amount, operation.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, amount);
    }

    @Override
    public String toString() {
        return "Operation{" +
                "type=" + type +
                ", amount=" + amount +
                '}';
    }
}
