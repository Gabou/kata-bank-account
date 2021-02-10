package kata.gabou.bank.operations;

import kata.gabou.bank.Amount;
import kata.gabou.bank.NotEnoughSavingsException;

import java.time.LocalDate;
import java.util.Objects;

public class Withdrawal implements Operation {

    private final Amount amount;
    private final LocalDate date;

    public Withdrawal(Amount amount, LocalDate date) {
        this.amount = amount;
        this.date = date;
    }

    public Withdrawal(Amount amount) {
        this(amount, LocalDate.now());
    }

    @Override
    public Amount execute(Amount amount) throws NotEnoughSavingsException {
        if(!amount.noSavings() && amount.greaterOrEqualThan(this.amount)) {
            return amount.subtract(this.amount);
        }
        throw new NotEnoughSavingsException("Not possible to subtract amount because there is no savings or amount to subtract : (" + this.amount + ") is greater than original amount (" + amount + ")");
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
