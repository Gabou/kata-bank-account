package kata.gabou.bank;

import java.util.Objects;

public class OperationHistory {
    private final Operation operation;
    private final Amount balance;

    public OperationHistory(Operation operation, Amount balance) {
        this.operation = operation;
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationHistory that = (OperationHistory) o;
        return Objects.equals(operation, that.operation) &&
                Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operation, balance);
    }

    @Override
    public String toString() {
        return "OperationHistory{" +
                "operation=" + operation +
                ", balance=" + balance +
                '}';
    }
}
