package triggers;

/**
 * Created by dmitry on 13.06.16.
 */
public interface Trigger<R, T> {
    R process(T p1, T p2);
}
