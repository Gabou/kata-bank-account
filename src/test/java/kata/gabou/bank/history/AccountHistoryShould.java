package kata.gabou.bank.history;

import kata.gabou.bank.operations.Deposit;
import kata.gabou.bank.operations.Withdrawal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class AccountHistoryShould {

    @Test
    void save_operation() {
        AccountHistory accountHistory = new AccountHistory();
        BigDecimal operationAmount = new BigDecimal("5.60");
        LocalDate operationDate = LocalDate.of(2020, 5, 4);
        BigDecimal balance = new BigDecimal("10.0");
        accountHistory.add(new Deposit(operationAmount, operationDate), balance);
        accountHistory.add(new Withdrawal(operationAmount, operationDate), balance);

        List<OperationHistory> expected = Arrays.asList(
                new OperationHistory(new Deposit(operationAmount, operationDate), balance),
                new OperationHistory(new Withdrawal(operationAmount, operationDate), balance));

        Assertions.assertThat(accountHistory.operations()).isEqualTo(expected);

    }
}
