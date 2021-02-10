package kata.gabou.bank.operations;

import kata.gabou.bank.Amount;
import kata.gabou.bank.NotEnoughSavingsException;

public interface Operation {
    Amount execute(Amount amount) throws NotEnoughSavingsException;
}
