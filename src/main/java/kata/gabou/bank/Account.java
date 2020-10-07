package kata.gabou.bank;

import kata.gabou.bank.history.AccountHistory;

import java.util.Optional;

public class Account {
    private Amount balance;
    private AccountHistory history;

    public Account(Amount balance, AccountHistory history) {
        this.balance = balance;
        this.history = history;
    }

    public void makeAn(Operation operation) {
        Optional<Amount> newBalance = Optional.empty();
        if (operation.type().equals(OperationType.DEPOSIT)) {
            newBalance = balance.add(operation.amount());
        } else if (operation.type().equals(OperationType.WITHDRAWAL)) {
            newBalance = balance.substract(operation.amount());
        }
        newBalance.ifPresent(amount -> {
            history.add(operation, amount);
            this.balance = amount;
        });
    }

    public Amount amount() {
        return balance;
    }

    public AccountHistory history() {
        return history;
    }
}
