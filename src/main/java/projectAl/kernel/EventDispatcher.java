package projectAl.kernel;

public interface EventDispatcher<E extends Event> {
    void dispatch(E event);
}
