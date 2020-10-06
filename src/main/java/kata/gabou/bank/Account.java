package kata.gabou.bank;

public class Account {
    private Amount amount;

    public Account(Amount amount) {
        this.amount = amount;
    }

    public void makeAn(Operation operation) {
        if (operation.type() == OperationType.DEPOSIT) {
            this.amount.add(operation.amount());
        } else if (operation.type() == OperationType.WITHDRAWAL) {
            this.amount.substract(operation.amount());
        }
    }

    public Amount amount() {
        return amount;
    }
}
