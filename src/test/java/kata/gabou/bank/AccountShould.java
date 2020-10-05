package kata.gabou.bank;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountShould {

    @Test
    void receive_a_deposit() {
        Account account = new Account();
        Amount amount = new Amount(0,0);
        account.deposit(amount);
        assertThat(account.amount()).isEqualTo(new Amount(0,0));
    }
}
