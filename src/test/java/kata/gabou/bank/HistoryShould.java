package kata.gabou.bank;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static kata.gabou.bank.OperationType.DEPOSIT;
import static kata.gabou.bank.OperationType.WITHDRAWAL;

public class HistoryShould {

    @Test
    void keep_operations() {
        AccountHistory history = new AccountHistory();
        history.add(new Operation(DEPOSIT, new Amount(5,0)));
        history.add(new Operation(WITHDRAWAL, new Amount(4,0)));

        List<Operation> expected = Arrays.asList(new Operation(DEPOSIT, new Amount(5,0)), new Operation(WITHDRAWAL, new Amount(4,0)));
        Assertions.assertThat(history.list()).isEqualTo(expected);
    }

    @Test
    void keep_operations_with_date() {
        AccountHistory history = new AccountHistory();
        history.add(new Operation(DEPOSIT, new Amount(5,0),LocalDate.of(2020,8,21)));
        history.add(new Operation(WITHDRAWAL, new Amount(4,0),LocalDate.of(2020,8,24)));

        List<Operation> expected = Arrays.asList(
                new Operation(DEPOSIT, new Amount(5,0),LocalDate.of(2020,8,21)),
                new Operation(WITHDRAWAL, new Amount(4,0),LocalDate.of(2020,8,24))
        );
        Assertions.assertThat(history.list()).isEqualTo(expected);
    }
}
