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
                arguments(Collections.singletonList(new BigDecimal("0.0")), new BigDecimal("0.0")),
                arguments(Arrays.asList(new BigDecimal("1.0"), new BigDecimal("1.0")), new BigDecimal("2.0")),
                arguments(Arrays.asList(new BigDecimal("1.5"), new BigDecimal("1.2")), new BigDecimal("2.7")),
                arguments(Arrays.asList(new BigDecimal("1.90"), new BigDecimal("1.80")), new BigDecimal("3.70"))
        );
    }

    @ParameterizedTest
    @MethodSource("amountDepositProvider")
    void receive_a_deposit(List<BigDecimal> depositAmount, BigDecimal newAccountAmount) {
        //Given
        Account account = new Account(new AccountHistory());

        //When
        for (BigDecimal amount : depositAmount) {
            account.makeAn(new Deposit(amount));
        }

        //Then
        assertThat(account.amount()).isEqualTo(newAccountAmount);
    }

    private static Stream<Arguments> amountWithdrawalProvider() {
        return Stream.of(
                arguments(new BigDecimal("10.0"), new BigDecimal("1.0"), new BigDecimal("9.0")),
                arguments(new BigDecimal("10.50"), new BigDecimal("0.25"), new BigDecimal("10.25")),
                arguments(new BigDecimal("10.50"), new BigDecimal("0.80"), new BigDecimal("9.70")),
                arguments(new BigDecimal("10.0"), new BigDecimal("10.0"), new BigDecimal("0.0"))
        );
    }

    @ParameterizedTest
    @MethodSource("amountWithdrawalProvider")
    void allow_a_withdrawal(BigDecimal accountAmount, BigDecimal withdrawalAmount, BigDecimal newAccountAmount) {
        //Given
        Account account = new Account(new AccountHistory());
        account.makeAn(new Deposit(accountAmount));

        //When
        account.makeAn(new Withdrawal(withdrawalAmount));

        //Then
        assertThat(account.amount()).isEqualTo(newAccountAmount);
    }

    private static Stream<Arguments> notAllowedAmountWithdrawalProvider() {
        return Stream.of(
                arguments(new BigDecimal("0.0"), new BigDecimal("1.0"), new BigDecimal("0.0")),
                arguments(new BigDecimal("0.50"), new BigDecimal("1.0"), new BigDecimal("0.50"))
        );
    }

    @ParameterizedTest
    @MethodSource("notAllowedAmountWithdrawalProvider")
    void prevent_a_withdrawal_when_there_is_not_enough_savings(BigDecimal accountAmount, BigDecimal withdrawalAmount, BigDecimal newAccountAmount) {
        //Given
        Account account = new Account(new AccountHistory());
        account.makeAn(new Deposit(accountAmount));

        //When
        account.makeAn(new Withdrawal(withdrawalAmount));

        //Then
        assertThat(account.amount()).isEqualTo(newAccountAmount);
    }

    @Test
    void give_account_history_operations() throws NotEnoughSavingsException {
        //Given
        Account account = new Account(new AccountHistory());

        AccountHistory historyExpected = new AccountHistory();
        historyExpected.add(new Deposit(new BigDecimal("5.0"), LocalDate.of(2020,4,2)));
        historyExpected.add(new Withdrawal(new BigDecimal("4.0"), LocalDate.of(2020,5,5)));

        //When
        account.makeAn(new Deposit(new BigDecimal("5.0"), LocalDate.of(2020,4,2)));
        account.makeAn(new Withdrawal(new BigDecimal("4.0"), LocalDate.of(2020,5,5)));

        //Then
        assertThat(account.history()).isEqualTo(historyExpected);
    }

    @Test
    void not_put_prevented_operation_in_history() throws NotEnoughSavingsException {
        //Given
        Account account = new Account(new AccountHistory());

        AccountHistory historyExpected = new AccountHistory();
        historyExpected.add(new Deposit(new BigDecimal("5.0"), LocalDate.of(2020,4,2)));
        historyExpected.add(new Withdrawal(new BigDecimal("4.0"), LocalDate.of(2020,5,5)));

        //When
        account.makeAn(new Deposit(new BigDecimal("5.0"), LocalDate.of(2020,4,2)));
        account.makeAn(new Withdrawal(new BigDecimal("4.0"), LocalDate.of(2020,5,5)));
        account.makeAn(new Withdrawal(new BigDecimal("4.0"), LocalDate.of(2020,7,5)));

        //Then
        assertThat(account.history()).isEqualTo(historyExpected);
    }
}
