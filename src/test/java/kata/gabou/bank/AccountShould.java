package kata.gabou.bank;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class AccountShould {

    private static Stream<Arguments> amountDepositProvider() {
        return Stream.of(
                arguments(Collections.singletonList(new Amount(0, 0)), new Amount(0,0)),
                arguments(Arrays.asList(new Amount(1,0), new Amount(1,0)), new Amount(2,0)),
                arguments(Arrays.asList(new Amount(1,5), new Amount(1,2)), new Amount(2,7)),
                arguments(Arrays.asList(new Amount(1,90), new Amount(1,80)), new Amount(3,70))
        );
    }

    @ParameterizedTest
    @MethodSource("amountDepositProvider")
    void receive_a_deposit(List<Amount> depositAmount, Amount accountAmount) {
        Account account = new Account(new Amount(0,0));
        for (Amount amount : depositAmount) {
            account.deposit(amount);
        }
        assertThat(account.amount()).isEqualTo(accountAmount);
    }
}
