package kata.gabou.bank;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

public class AmountTest {

    @Test
    public void add_an_amount() {
        Amount originalAmount = new Amount(BigDecimal.valueOf(1.50));
        Amount amountToAdd = new Amount(BigDecimal.valueOf(1.75));

        Optional<Amount> newAmount = originalAmount.add(amountToAdd);
        Assertions.assertThat(newAmount).isPresent();
        Assertions.assertThat(newAmount.get()).isEqualTo(new Amount(BigDecimal.valueOf(3.25)));
    }

    @Test
    public void subtract_an_amount() {
        Amount originalAmount = new Amount(BigDecimal.valueOf(1.75));
        Amount amountToSubtract = new Amount(BigDecimal.valueOf(1.50));

        Optional<Amount> newAmount = originalAmount.subtract(amountToSubtract);

        Assertions.assertThat(newAmount).isPresent();
        Assertions.assertThat(newAmount.get()).isEqualTo(new Amount(BigDecimal.valueOf(0.25)));
    }

    @Test
    public void cannot_subtract_an_amount_when_it_will_be_under_zero() {
        Amount originalAmount = new Amount(BigDecimal.valueOf(1.00));
        Amount amountToSubtract = new Amount(BigDecimal.valueOf(1.50));

        Optional<Amount> newAmount = originalAmount.subtract(amountToSubtract);

        Assertions.assertThat(newAmount).isEmpty();
    }

}
