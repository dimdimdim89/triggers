package triggers.impls;

import triggers.Trigger;
import triggers.Value;
import triggers.impls.val.BooleanValue;

/**
 * Created by dmitry on 13.06.16.
 */
public class AndTrigger implements Trigger<Value<Boolean>, Value<Boolean>> {
    @Override
    public BooleanValue process(Value<Boolean> p1, Value<Boolean> p2) {
        return new BooleanValue(p1.get() && p2.get());
    }
}
