package kata.gabou.bank;

public class Account {
    private Amount amount;

    public void deposit(Amount amount) {
        this.amount = amount;
    }

    public Amount amount() {
        return amount;
    }
}
