package kata.gabou.bank;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class AmountTest {

    @Test
    public void add_an_amount() {
        Amount originalAmount = new Amount(BigDecimal.valueOf(1.50));
        Amount amountToAdd = new Amount(BigDecimal.valueOf(1.75));

        Amount newAmount = originalAmount.add(amountToAdd);
        assertThat(newAmount).isEqualTo(new Amount(BigDecimal.valueOf(3.25)));
    }

    @Test
    public void subtract_an_amount() {
        Amount originalAmount = new Amount(BigDecimal.valueOf(1.75));
        Amount amountToSubtract = new Amount(BigDecimal.valueOf(1.50));

        Amount newAmount = originalAmount.subtract(amountToSubtract);

        assertThat(newAmount).isEqualTo(new Amount(BigDecimal.valueOf(0.25)));
    }

    @Test
    public void amount_has_savings() {
        Amount amount = new Amount(BigDecimal.valueOf(1.75));

        boolean noSavings = amount.noSavings();

        assertThat(noSavings).isFalse();
    }

    @Test
    public void amount_has_no_savings() {
        Amount amount = new Amount(BigDecimal.valueOf(0));

        boolean noSavings = amount.noSavings();

        assertThat(noSavings).isTrue();
    }

}
