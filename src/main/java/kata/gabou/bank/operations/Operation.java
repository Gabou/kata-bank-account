package kata.gabou.bank.operations;

import kata.gabou.bank.NotEnoughSavingsException;

import java.math.BigDecimal;

public interface Operation {
    BigDecimal execute(BigDecimal amount) throws NotEnoughSavingsException;
}
