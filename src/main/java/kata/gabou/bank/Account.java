package kata.gabou.bank;

public class Account {
    private Amount amount;
    private AccountHistory history;

    public Account(Amount amount, AccountHistory history) {
        this.amount = amount;
        this.history = history;
    }

    public void makeAn(Operation operation) {
        if (operation.type() == OperationType.DEPOSIT) {
            this.amount.add(operation.amount());
        } else if (operation.type() == OperationType.WITHDRAWAL) {
            this.amount.substract(operation.amount());
        }
        history.add(operation, Amount.of(amount));
    }

    public Amount amount() {
        return amount;
    }

    public AccountHistory history() {
        return history;
    }
}
