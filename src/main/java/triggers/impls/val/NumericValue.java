package triggers.impls.val;

import triggers.Value;

import java.math.BigDecimal;

/**
 * Created by dmitry on 26.06.16.
 */
public class NumericValue implements Value<BigDecimal> {
    private BigDecimal value;

    public NumericValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public BigDecimal get() {
        return value;
    }

    public int compare(NumericValue numericValue) {
        return this.value.compareTo(numericValue.value);
    }
}
