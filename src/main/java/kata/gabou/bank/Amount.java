package kata.gabou.bank;

import java.util.Objects;

public class Amount {
    private int integerPart;
    private int decimalPart;

    public Amount(int integerPart, int decimalPart) {

        this.integerPart = integerPart;
        this.decimalPart = decimalPart;
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

    public void add(Amount amount) {
        integerPart += amount.integerPart;
    }
}
