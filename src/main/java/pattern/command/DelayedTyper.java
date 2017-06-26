package pattern.command;

/**
 * @author myeongju.jung
 */
public class DelayedTyper implements Command {
    private long delay;
    private char ch;
    private static ActiveObjectEngine engine = new ActiveObjectEngine();
    private static boolean stop = false;

    public static void main(String[] args) throws Exception {
        engine.addCommand(new DelayedTyper(100, '1'));
        engine.addCommand(new DelayedTyper(300, '3'));
        engine.addCommand(new DelayedTyper(500, '5'));
        engine.addCommand(new DelayedTyper(700, '7'));
        engine.addCommand(new SleepCommand(10_000, engine, () -> stop = true));
        engine.run();
    }

    public DelayedTyper(long delay, char ch) {
        this.delay = delay;
        this.ch = ch;
    }

    @Override
    public void execute() throws Exception {
        System.out.print(ch);
        if (!stop) {
            delayAndRepeat();
        }
    }

    private void delayAndRepeat() {
        engine.addCommand(new SleepCommand(delay, engine, this));
    }
}
