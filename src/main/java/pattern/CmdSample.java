package pattern;

public class CmdSample {
    public static void main(String[] args) {
        ExecutionContext<Integer> executor = new ExecutionContext<>(1);

//        executor.exec((ExecutionContext<Integer> e) -> {
//                    e.backup = e.context;
//                    e.context = e.context + 2;
//                    return (ExecutionContext<Integer>) e;
//                })
//                .exec((ExecutionContext<Integer> e) -> {
//                    e.backup = e.context;
//                    e.context = e.context * 2;
//                    return e;
//                });

        ExecutionBase<String> executionBase = new ExecutionBase<>("Base");

        System.out.println(executionBase
                .call(s -> s + " 322")
                .call(s -> s +   " FUCK OFF!!")
                .get());
    }
}
