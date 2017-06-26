package pattern.command;

import java.util.LinkedList;

/**
 * @author myeongju.jung
 */
public class ActiveObjectEngine {
    LinkedList<Command> commands = new LinkedList<>();

    public void addCommand(Command c) {
        commands.add(c);
    }

    public void run() throws Exception {
        while (!commands.isEmpty()) {
            Command c = commands.getFirst();
            commands.removeFirst();
            c.execute();
        }
    }
}
