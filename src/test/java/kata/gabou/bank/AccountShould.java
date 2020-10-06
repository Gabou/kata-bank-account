package kata.gabou.bank;

import kata.gabou.bank.history.AccountHistory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static kata.gabou.bank.OperationType.DEPOSIT;
import static kata.gabou.bank.OperationType.WITHDRAWAL;
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
    void receive_a_deposit(List<Amount> depositAmount, Amount newAccountAmount) {
        Account account = new Account(new Amount(0,0), new AccountHistory());
        for (Amount amount : depositAmount) {
            account.makeAn(new Operation(DEPOSIT,amount));
        }
        assertThat(account.amount()).isEqualTo(newAccountAmount);
    }

    private static Stream<Arguments> amountWithdrawalProvider() {
        return Stream.of(
                arguments(new Amount(10,0), Collections.singletonList(new Amount(1, 0)), new Amount(9,0)),
                arguments(new Amount(10,50), Collections.singletonList(new Amount(0, 25)), new Amount(10,25)),
                arguments(new Amount(10,50), Collections.singletonList(new Amount(0, 80)), new Amount(9,70)),
                arguments(new Amount(10,0), Collections.singletonList(new Amount(10, 0)), new Amount(0,0))
        );
    }

    @ParameterizedTest
    @MethodSource("amountWithdrawalProvider")
    void allow_a_withdrawal(Amount accountAmount, List<Amount> withdrawalAmount, Amount newAccountAmount) {
        Account account = new Account(accountAmount, new AccountHistory());
        for (Amount amount : withdrawalAmount) {
            account.makeAn(new Operation(WITHDRAWAL,amount));
        }
        assertThat(account.amount()).isEqualTo(newAccountAmount);
    }

    private static Stream<Arguments> notAllowedAmountWithdrawalProvider() {
        return Stream.of(
                arguments(new Amount(0,0), Collections.singletonList(new Amount(1, 0)), new Amount(0,0)),
                arguments(new Amount(0,50), Collections.singletonList(new Amount(1, 0)), new Amount(0,50))
        );
    }

    @ParameterizedTest
    @MethodSource("notAllowedAmountWithdrawalProvider")
    void prevent_a_withdrawal_when_there_is_not_enough_savings(Amount accountAmount, List<Amount> withdrawalAmount, Amount newAccountAmount) {
        Account account = new Account(accountAmount, new AccountHistory());
        for (Amount amount : withdrawalAmount) {
            account.makeAn(new Operation(WITHDRAWAL,amount));
        }
        assertThat(account.amount()).isEqualTo(newAccountAmount);
    }

    @Test
    void give_account_history_operations() {
        Account account = new Account(new Amount(0,0), new AccountHistory());
        account.makeAn(new Operation(DEPOSIT, new Amount(5,0), LocalDate.of(2020,4,2)));
        account.makeAn(new Operation(WITHDRAWAL, new Amount(4,0), LocalDate.of(2020,5,5)));
        AccountHistory historyExpected = new AccountHistory();
        historyExpected.add(new Operation(DEPOSIT, new Amount(5,0), LocalDate.of(2020,4,2)), new Amount(5,0));
        historyExpected.add(new Operation(WITHDRAWAL, new Amount(4,0), LocalDate.of(2020,5,5)), new Amount(1,0));
        assertThat(account.history()).isEqualTo(historyExpected);
    }

    @Test
    void not_put_prevented_operation_in_history() {
        Account account = new Account(new Amount(0,0), new AccountHistory());
        account.makeAn(new Operation(DEPOSIT, new Amount(5,0), LocalDate.of(2020,4,2)));
        account.makeAn(new Operation(WITHDRAWAL, new Amount(4,0), LocalDate.of(2020,5,5)));
        account.makeAn(new Operation(WITHDRAWAL, new Amount(4,0), LocalDate.of(2020,7,5)));
        AccountHistory historyExpected = new AccountHistory();
        historyExpected.add(new Operation(DEPOSIT, new Amount(5,0), LocalDate.of(2020,4,2)), new Amount(5,0));
        historyExpected.add(new Operation(WITHDRAWAL, new Amount(4,0), LocalDate.of(2020,5,5)), new Amount(1,0));
        assertThat(account.history()).isEqualTo(historyExpected);
    }
}
