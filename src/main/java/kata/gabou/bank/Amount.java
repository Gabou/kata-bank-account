package kata.gabou.bank;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

public class Amount {
    private final BigDecimal value;

    public Amount(BigDecimal value) {
        this.value = value;
    }

    public Optional<Amount> add(Amount amount) {
        BigDecimal newValue = value.add(amount.value);
        return Optional.of(new Amount(newValue));
    }

    public Optional<Amount> subtract(Amount amount) {

        if(noSavings() || amount.greaterThan(this)) {
            return Optional.empty();
        }
        BigDecimal newValue = value.subtract(amount.value);
        return Optional.of(new Amount(newValue));
    }

    private boolean greaterThan(Amount amount) {
        return this.value.compareTo(amount.value) > 0;
    }

    private boolean noSavings() {
        return value.compareTo(BigDecimal.ZERO) == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return Objects.equals(value, amount.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
