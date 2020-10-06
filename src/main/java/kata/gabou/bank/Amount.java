package kata.gabou.bank;

import java.util.Objects;

public class Amount {
    public static final int MAX_CENTS = 100;
    public static final int MIN_CENTS = 0;
    private int integerPart;
    private int decimalPart;

    public Amount(int integerPart, int decimalPart) {

        this.integerPart = integerPart;
        this.decimalPart = decimalPart;
    }

    public void add(Amount amount) {
        integerPart += amount.integerPart;
        decimalPart += amount.decimalPart;

        if (decimalPart >=MAX_CENTS) {
            integerPart += decimalPart / MAX_CENTS;
            decimalPart = decimalPart % MAX_CENTS;
        }
    }

    public void substract(Amount amount) {
        integerPart -= amount.integerPart;
        decimalPart -= amount.decimalPart;
        
        if (decimalPart < MIN_CENTS) {
            integerPart -= 1;
            decimalPart += MAX_CENTS;
        }
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
