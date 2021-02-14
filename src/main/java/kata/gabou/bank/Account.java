package kata.gabou.bank;

import kata.gabou.bank.history.AccountHistory;
import kata.gabou.bank.operations.Operation;

import java.math.BigDecimal;

public class Account {
    private BigDecimal balance;
    private AccountHistory history;

    public Account(BigDecimal balance, AccountHistory history) {
        this.balance = balance;
        this.history = history;
    }

    public void makeAn(Operation operation) {
        try {

            if(!operation.canProcessWith(balance)) {
                throw new NotEnoughSavingsException("Not enough Savings to process operation");
            }

            BigDecimal newBalance = operation.process(balance);
            history.add(operation, newBalance);
            this.balance = newBalance;
        } catch (NotEnoughSavingsException exception) {
            System.err.println(exception.getMessage());
        }
    }

    public BigDecimal amount() {
        return balance;
    }

    public AccountHistory history() {
        return history;
    }
}
