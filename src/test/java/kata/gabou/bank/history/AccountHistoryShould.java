package kata.gabou.bank.history;

import kata.gabou.bank.Amount;
import kata.gabou.bank.Operation;
import kata.gabou.bank.OperationType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class AccountHistoryShould {

    @Test
    void save_operation() {
        AccountHistory accountHistory = new AccountHistory();
        Amount operationAmount = new Amount(5, 60);
        LocalDate operationDate = LocalDate.of(2020, 5, 4);
        Amount balance = new Amount(10, 0);
        accountHistory.add(new Operation(OperationType.DEPOSIT, operationAmount, operationDate), balance);
        accountHistory.add(new Operation(OperationType.WITHDRAWAL, operationAmount, operationDate), balance);

        List<OperationHistory> expected = Arrays.asList(
                new OperationHistory(new Operation(OperationType.DEPOSIT, operationAmount, operationDate), balance),
                new OperationHistory(new Operation(OperationType.WITHDRAWAL, operationAmount, operationDate), balance));

        Assertions.assertThat(accountHistory.operations()).isEqualTo(expected);

    }
}
