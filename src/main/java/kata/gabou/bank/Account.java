package kata.gabou.bank;

import kata.gabou.bank.history.AccountHistory;
import kata.gabou.bank.operations.Operation;

import java.math.BigDecimal;

public class Account {
    private final AccountHistory history;

    public Account(AccountHistory history) {
        this.history = history;
    }

    public void makeAn(Operation operation) {
        try {
            history.add(operation);
        } catch (NotEnoughSavingsException exception) {
            System.err.println(exception.getMessage());
        }
    }

    public BigDecimal amount() {
        return history.balance();
    }

    public AccountHistory history() {
        return history;
    }
}
