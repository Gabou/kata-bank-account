package kata.gabou.bank.operations;

import kata.gabou.bank.Amount;

import java.time.LocalDate;
import java.util.Objects;

public class Deposit implements Operation {

    private final Amount amount;
    private final LocalDate date;

    public Deposit(Amount amount, LocalDate date) {
        this.amount = amount;
        this.date = date;
    }

    public Deposit(Amount amount) {
        this(amount, LocalDate.now());
    }

    @Override
    public Amount execute(Amount amount) {
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
