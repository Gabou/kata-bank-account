package kata.gabou.bank.history;

import kata.gabou.bank.NotEnoughSavingsException;
import kata.gabou.bank.operations.Deposit;
import kata.gabou.bank.operations.Operation;
import kata.gabou.bank.operations.Withdrawal;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AccountHistoryShould {

    @Test
    void save_operation() throws NotEnoughSavingsException {
        //Given
        AccountHistory accountHistory = new AccountHistory();
        BigDecimal operationAmount = new BigDecimal("5.60");
        LocalDate operationDate = LocalDate.of(2020, 5, 4);

        List<Operation> expected = Arrays.asList(
                new Deposit(operationAmount, operationDate),
                new Withdrawal(operationAmount, operationDate));

        //When
        accountHistory.add(new Deposit(operationAmount, operationDate));
        accountHistory.add(new Withdrawal(operationAmount, operationDate));

        //Then
        assertThat(accountHistory.operations()).isEqualTo(expected);

    }

    @Test
    void not_save_operation_when_not_enough_savings() throws NotEnoughSavingsException {
        //Given
        AccountHistory accountHistory = new AccountHistory();
        BigDecimal operationAmount = new BigDecimal("5.60");
        LocalDate operationDate = LocalDate.of(2020, 5, 4);

        List<Operation> expected = Arrays.asList(
                new Deposit(operationAmount, operationDate),
                new Withdrawal(operationAmount, operationDate));

        //When
        accountHistory.add(new Deposit(operationAmount, operationDate));
        accountHistory.add(new Withdrawal(operationAmount, operationDate));

        //Then
        assertThatThrownBy(() -> accountHistory.add(new Withdrawal(operationAmount, operationDate))).isInstanceOf(NotEnoughSavingsException.class);
        assertThat(accountHistory.operations()).isEqualTo(expected);

    }
}
