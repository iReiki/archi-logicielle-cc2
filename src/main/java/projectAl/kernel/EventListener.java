package projectAl.kernel;

public interface EventListener<E extends Event> {
    void listenTo(E event);
}
