package triggers.impls.val;

import triggers.Value;

/**
 * Created by dmitry on 13.06.16.
 */
public class BooleanValue implements Value<Boolean> {
    private Boolean value;

    public BooleanValue() {
        value = false;
    }

    public BooleanValue(Boolean value) {
        this.value = value;
    }

    @Override
    public Boolean get() {
        return value;
    }
}
