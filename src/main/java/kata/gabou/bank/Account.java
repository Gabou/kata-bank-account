package kata.gabou.bank;

public class Account {
    private Amount amount;

    public Account(Amount amount) {
        this.amount = amount;
    }

    public void deposit(Amount amount) {
        this.amount.add(amount);
    }

    public Amount amount() {
        return amount;
    }
}
