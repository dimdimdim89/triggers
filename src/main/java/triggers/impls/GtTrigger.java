package triggers.impls;

import triggers.Trigger;
import triggers.impls.val.BooleanValue;
import triggers.impls.val.NumericValue;

/**
 * Created by dmitry on 26.06.16.
 */
public class GtTrigger implements Trigger<BooleanValue, NumericValue> {
    @Override
    public BooleanValue process(NumericValue p1, NumericValue p2) {
        return new BooleanValue(p1.compare(p2) > 0);
    }
}
