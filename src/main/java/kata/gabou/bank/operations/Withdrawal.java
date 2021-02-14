package kata.gabou.bank.operations;

import kata.gabou.bank.NotEnoughSavingsException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Withdrawal implements Operation {

    private final BigDecimal amount;
    private final LocalDate date;

    public Withdrawal(BigDecimal amount, LocalDate date) {
        this.amount = amount;
        this.date = date;
    }

    public Withdrawal(BigDecimal amount) {
        this(amount, LocalDate.now());
    }

    @Override
    public BigDecimal process(BigDecimal amount) throws NotEnoughSavingsException {
            return amount.subtract(this.amount);
    }

    @Override
    public boolean canProcessWith(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) != 0 && amount.compareTo(this.amount) >= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Withdrawal operation = (Withdrawal) o;
        return Objects.equals(amount, operation.amount) &&
                Objects.equals(date, operation.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, date);
    }

    @Override
    public String toString() {
        return "Withdrawal{" +
                "amount=" + amount +
                ", date=" + date +
                '}';
    }
}
