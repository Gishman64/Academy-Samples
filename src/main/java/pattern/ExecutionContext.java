package pattern;

public class ExecutionContext<C> {
    C context;
    C backup;

    public ExecutionContext(C context) {
        this.context = context;
    }

    ExecutionContext exec(Command<ExecutionContext<C>> command) {
        return command.calc(this);
    }

    public C get() {
        return this.context;
    }
}
