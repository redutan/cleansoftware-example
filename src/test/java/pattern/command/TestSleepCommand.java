package pattern.command;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author myeongju.jung
 */
public class TestSleepCommand {
    private boolean commandExecuted = false;

    @Test
    public void testSleep() throws Exception {
        // given
        Command wakeup = () -> commandExecuted = true;
        ActiveObjectEngine e = new ActiveObjectEngine();
        SleepCommand c = new SleepCommand(1_000L, e, wakeup);
        e.addCommand(c);
        long start = System.currentTimeMillis();
        // when
        e.run();
        long stop = System.currentTimeMillis();
        long sleepTime = (stop - start);
        // then
        assertTrue("SleepTime " + sleepTime + " nanoseconds. expected >= 1000", sleepTime >= 1_000L);
        assertTrue("SleepTime " + sleepTime + " nanoseconds. expected < 1100", sleepTime < 1_100L);
        assertTrue("Command Executed", commandExecuted);
    }
}
