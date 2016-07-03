package triggers.impls;

import triggers.Trigger;
import triggers.impls.val.BooleanValue;

/**
 * Created by dmitry on 13.06.16.
 */

public class OrTrigger implements Trigger<BooleanValue, BooleanValue> {
    @Override
    public BooleanValue process(BooleanValue p1, BooleanValue p2) {
        return new BooleanValue(p1.get() || p2.get());
    }
}
