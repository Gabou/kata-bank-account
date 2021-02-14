package kata.gabou.bank.history;

import kata.gabou.bank.NotEnoughSavingsException;
import kata.gabou.bank.operations.Operation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccountHistory {

    private final List<Operation> operations = new ArrayList<>();

    public void add(Operation operation) throws NotEnoughSavingsException {

        BigDecimal balance = balance();
        if(!operation.canProcessWith(balance)) {
            throw new NotEnoughSavingsException("Not enough Savings to process operation");
        }

        operations.add(operation);
    }

    public BigDecimal balance() {
        return operations.stream().reduce(BigDecimal.ZERO, (amount, operation) -> operation.process(amount), (amount, amount2) -> amount);
    }

    public List<Operation> operations(){
        return operations;
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
