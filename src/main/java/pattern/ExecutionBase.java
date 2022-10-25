package pattern;

import java.util.Stack;

public class ExecutionBase<C> {
    private C ctxBase;
    private Stack<Command<C>> history;
    private Stack<Command<C>> undo;

    public ExecutionBase(C ctxBase) {
        this.ctxBase = ctxBase;
        this.history = new Stack<>();
    }

    ExecutionBase<C> call(Command<C> cmd) {
        history.push(cmd);
        this.ctxBase = cmd.calc(ctxBase);
        return this;
    }

    ExecutionBase<C> undo() {
        var cmd = history.pop();
        if(cmd != null) {
            undo.push(cmd);
            //cmd lambda has no backup!
        }
        return this;
    }

    public C get() {
        return this.ctxBase;
    }
}
