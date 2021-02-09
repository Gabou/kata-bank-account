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
        Amount newBalance = null;
        Amount operationAmount = operation.amount();
        if (operation.type().equals(OperationType.DEPOSIT)) {
            newBalance = balance.add(operationAmount);
        } else if (operation.type().equals(OperationType.WITHDRAWAL)) {
            if(!balance.noSavings() && balance.greaterOrEqualThan(operationAmount)) {
                newBalance = balance.subtract(operationAmount);
            }
        }

        if (newBalance != null){
            history.add(operation, newBalance);
            this.balance = newBalance;
        }
    }

    public Amount amount() {
        return balance;
    }

    public AccountHistory history() {
        return history;
    }
}
