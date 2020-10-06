package kata.gabou.bank;

import java.util.Objects;
import java.util.Optional;

public class Amount {
    public static final int MAX_CENTS = 100;
    public static final int MIN_CENTS = 0;
    private int integerPart;
    private int decimalPart;

    public Amount(int integerPart, int decimalPart) {

        this.integerPart = integerPart;
        this.decimalPart = decimalPart;
    }

    public Optional<Amount> add(Amount amount) {
        integerPart += amount.integerPart;
        decimalPart += amount.decimalPart;

        if (decimalPart >=MAX_CENTS) {
            integerPart += decimalPart / MAX_CENTS;
            decimalPart = decimalPart % MAX_CENTS;
        }
        return Optional.of(this);
    }

    public Optional<Amount> substract(Amount amount) {

        if(noSavings() || amount.greaterThan(this)) {
            return Optional.empty();
        }

        integerPart -= amount.integerPart;
        decimalPart -= amount.decimalPart;

        if (decimalPart < MIN_CENTS) {
            integerPart -= 1;
            decimalPart += MAX_CENTS;
        }
        return Optional.of(this);
    }

    private boolean greaterThan(Amount amount) {
        return this.integerPart > amount.integerPart || (this.integerPart == amount.integerPart && this.decimalPart > amount.decimalPart);
    }

    private boolean noSavings() {
        return integerPart == 0 && decimalPart == 0;
    }

    public static Amount of(Amount amount) {
        return new Amount(amount.integerPart, amount.decimalPart);
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
