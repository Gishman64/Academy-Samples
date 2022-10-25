package pattern;

public interface Command<C> {
    C calc(C ctx);
}
