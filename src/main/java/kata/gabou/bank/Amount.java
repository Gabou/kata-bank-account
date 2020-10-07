package kata.gabou.bank;

import java.util.Objects;
import java.util.Optional;

public class Amount {
    public static final int MAX_CENTS = 100;
    public static final int MIN_CENTS = 0;
    private final int integerPart;
    private final int decimalPart;

    public Amount(int integerPart, int decimalPart) {

        this.integerPart = integerPart;
        this.decimalPart = decimalPart;
    }

    public Optional<Amount> add(Amount amount) {
        int newIntegerPart = this.integerPart + amount.integerPart;
        int newDecimalPart = this.decimalPart + amount.decimalPart;

        if (newDecimalPart >=MAX_CENTS) {
            newIntegerPart += newDecimalPart / MAX_CENTS;
            newDecimalPart = newDecimalPart % MAX_CENTS;
        }
        return Optional.of(new Amount(newIntegerPart, newDecimalPart));
    }

    public Optional<Amount> substract(Amount amount) {

        if(noSavings() || amount.greaterThan(this)) {
            return Optional.empty();
        }

        int newIntegerPart = integerPart - amount.integerPart;
        int newDecimalPart = decimalPart - amount.decimalPart;

        if (newDecimalPart < MIN_CENTS) {
            newIntegerPart -= 1;
            newDecimalPart += MAX_CENTS;
        }
        return Optional.of(new Amount(newIntegerPart, newDecimalPart));
    }

    private boolean greaterThan(Amount amount) {
        return this.integerPart > amount.integerPart || (this.integerPart == amount.integerPart && this.decimalPart > amount.decimalPart);
    }

    private boolean noSavings() {
        return integerPart == 0 && decimalPart == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return integerPart == amount.integerPart &&
                decimalPart == amount.decimalPart;
    }

    @Override
    public int hashCode() {
        return Objects.hash(integerPart, decimalPart);
    }

    @Override
    public String toString() {
        return integerPart + "," + decimalPart ;
    }
}
