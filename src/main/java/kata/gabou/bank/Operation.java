package kata.gabou.bank;

import java.time.LocalDate;
import java.util.Objects;

public class Operation {

    private final OperationType type;
    private final Amount amount;
    private final LocalDate date;

    public Operation(OperationType type, Amount amount) {
        this.type = type;
        this.amount = amount;
        date = LocalDate.now();
    }

    public Operation(OperationType type, Amount amount, LocalDate date) {
        this.type = type;
        this.amount = amount;
        this.date = date;
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
                Objects.equals(amount, operation.amount) &&
                Objects.equals(date, operation.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, amount, date);
    }

    @Override
    public String toString() {
        return "Operation{" +
                "type=" + type +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
