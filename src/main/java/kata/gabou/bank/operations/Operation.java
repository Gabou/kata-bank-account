package kata.gabou.bank.operations;

import java.math.BigDecimal;

public interface Operation {
    BigDecimal process(BigDecimal amount);

    boolean canProcessWith(BigDecimal amount);
}
