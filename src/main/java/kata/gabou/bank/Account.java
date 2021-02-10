package kata.gabou.bank;

import kata.gabou.bank.history.AccountHistory;
import kata.gabou.bank.operations.Operation;

public class Account {
    private Amount balance;
    private AccountHistory history;

    public Account(Amount balance, AccountHistory history) {
        this.balance = balance;
        this.history = history;
    }

    public void makeAn(Operation operation) {
        try {
            Amount newBalance = operation.execute(balance);
            history.add(operation, newBalance);
            this.balance = newBalance;
        } catch (NotEnoughSavingsException exception) {
            System.err.println(exception.getMessage());
        }
    }

    public Amount amount() {
        return balance;
    }

    public AccountHistory history() {
        return history;
    }
}
