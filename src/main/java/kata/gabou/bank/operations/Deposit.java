package kata.gabou.bank.operations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Deposit implements Operation {

    private final BigDecimal amount;
    private final LocalDate date;

    public Deposit(BigDecimal amount, LocalDate date) {
        this.amount = amount;
        this.date = date;
    }

    public Deposit(BigDecimal amount) {
        this(amount, LocalDate.now());
    }

    @Override
    public BigDecimal execute(BigDecimal amount) {
            return amount.add(this.amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deposit operation = (Deposit) o;
        return Objects.equals(amount, operation.amount) &&
                Objects.equals(date, operation.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, date);
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "amount=" + amount +
                ", date=" + date +
                '}';
    }
}
