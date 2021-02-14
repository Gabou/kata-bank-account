package kata.gabou.bank.operations;

import kata.gabou.bank.NotEnoughSavingsException;

import java.math.BigDecimal;

public interface Operation {
    BigDecimal process(BigDecimal amount) throws NotEnoughSavingsException;

    boolean canProcessWith(BigDecimal amount);
}
