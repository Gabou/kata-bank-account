package kata.gabou.bank;

import kata.gabou.bank.history.AccountHistory;
import kata.gabou.bank.operations.Deposit;
import kata.gabou.bank.operations.Withdrawal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class AccountShould {

    private static Stream<Arguments> amountDepositProvider() {
        return Stream.of(
                arguments(Collections.singletonList(new Amount(BigDecimal.valueOf(0.0))), new Amount(BigDecimal.valueOf(0.0))),
                arguments(Arrays.asList(new Amount(BigDecimal.valueOf(1.0)), new Amount(BigDecimal.valueOf(1.0))), new Amount(BigDecimal.valueOf(2.0))),
                arguments(Arrays.asList(new Amount(BigDecimal.valueOf(1.5)), new Amount(BigDecimal.valueOf(1.2))), new Amount(BigDecimal.valueOf(2.7))),
                arguments(Arrays.asList(new Amount(BigDecimal.valueOf(1.90)), new Amount(BigDecimal.valueOf(1.80))), new Amount(BigDecimal.valueOf(3.70)))
        );
    }

    @ParameterizedTest
    @MethodSource("amountDepositProvider")
    void receive_a_deposit(List<Amount> depositAmount, Amount newAccountAmount) {
        Account account = new Account(new Amount(BigDecimal.valueOf(0.0)), new AccountHistory());
        for (Amount amount : depositAmount) {
            account.makeAn(new Deposit(amount));
        }
        assertThat(account.amount()).isEqualTo(newAccountAmount);
    }

    private static Stream<Arguments> amountWithdrawalProvider() {
        return Stream.of(
                arguments(new Amount(BigDecimal.valueOf(10.0)), new Amount(BigDecimal.valueOf(1.0)), new Amount(BigDecimal.valueOf(9.0))),
                arguments(new Amount(BigDecimal.valueOf(10.50)), new Amount(BigDecimal.valueOf(0.25)), new Amount(BigDecimal.valueOf(10.25))),
                arguments(new Amount(BigDecimal.valueOf(10.50)), new Amount(BigDecimal.valueOf(0.80)), new Amount(BigDecimal.valueOf(9.70))),
                arguments(new Amount(BigDecimal.valueOf(10.0)), new Amount(BigDecimal.valueOf(10.0)), new Amount(BigDecimal.valueOf(0.0)))
        );
    }

    @ParameterizedTest
    @MethodSource("amountWithdrawalProvider")
    void allow_a_withdrawal(Amount accountAmount, Amount withdrawalAmount, Amount newAccountAmount) {
        Account account = new Account(accountAmount, new AccountHistory());
        account.makeAn(new Withdrawal(withdrawalAmount));
        assertThat(account.amount()).isEqualTo(newAccountAmount);
    }

    private static Stream<Arguments> notAllowedAmountWithdrawalProvider() {
        return Stream.of(
                arguments(new Amount(BigDecimal.valueOf(0.0)), new Amount(BigDecimal.valueOf(1.0)), new Amount(BigDecimal.valueOf(0.0))),
                arguments(new Amount(BigDecimal.valueOf(0.50)), new Amount(BigDecimal.valueOf(1.0)), new Amount(BigDecimal.valueOf(0.50)))
        );
    }

    @ParameterizedTest
    @MethodSource("notAllowedAmountWithdrawalProvider")
    void prevent_a_withdrawal_when_there_is_not_enough_savings(Amount accountAmount, Amount withdrawalAmount, Amount newAccountAmount) {
        Account account = new Account(accountAmount, new AccountHistory());
        account.makeAn(new Withdrawal(withdrawalAmount));
        assertThat(account.amount()).isEqualTo(newAccountAmount);
    }

    @Test
    void give_account_history_operations() {
        Account account = new Account(new Amount(BigDecimal.valueOf(0.0)), new AccountHistory());
        account.makeAn(new Deposit(new Amount(BigDecimal.valueOf(5.0)), LocalDate.of(2020,4,2)));
        account.makeAn(new Withdrawal(new Amount(BigDecimal.valueOf(4.0)), LocalDate.of(2020,5,5)));
        AccountHistory historyExpected = new AccountHistory();
        historyExpected.add(new Deposit(new Amount(BigDecimal.valueOf(5.0)), LocalDate.of(2020,4,2)), new Amount(BigDecimal.valueOf(5.0)));
        historyExpected.add(new Withdrawal(new Amount(BigDecimal.valueOf(4.0)), LocalDate.of(2020,5,5)), new Amount(BigDecimal.valueOf(1.0)));
        assertThat(account.history()).isEqualTo(historyExpected);
    }

    @Test
    void not_put_prevented_operation_in_history() {
        Account account = new Account(new Amount(BigDecimal.valueOf(0.0)), new AccountHistory());
        account.makeAn(new Deposit(new Amount(BigDecimal.valueOf(5.0)), LocalDate.of(2020,4,2)));
        account.makeAn(new Withdrawal(new Amount(BigDecimal.valueOf(4.0)), LocalDate.of(2020,5,5)));
        account.makeAn(new Withdrawal(new Amount(BigDecimal.valueOf(4.0)), LocalDate.of(2020,7,5)));
        AccountHistory historyExpected = new AccountHistory();
        historyExpected.add(new Deposit(new Amount(BigDecimal.valueOf(5.0)), LocalDate.of(2020,4,2)), new Amount(BigDecimal.valueOf(5.0)));
        historyExpected.add(new Withdrawal(new Amount(BigDecimal.valueOf(4.0)), LocalDate.of(2020,5,5)), new Amount(BigDecimal.valueOf(1.0)));
        assertThat(account.history()).isEqualTo(historyExpected);
    }
}
