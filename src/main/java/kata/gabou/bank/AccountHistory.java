package kata.gabou.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccountHistory {

    private List<OperationHistory> operations = new ArrayList<>();

    public void add(Operation operation, final Amount balance) {
        operations.add(new OperationHistory(operation, balance));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountHistory that = (AccountHistory) o;
        return Objects.equals(operations, that.operations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operations);
    }

    @Override
    public String toString() {
        return "AccountHistory{" +
                "operations=" + operations +
                '}';
    }
}
