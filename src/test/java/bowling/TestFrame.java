package bowling;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author myeongju.jung
 */
public class TestFrame {

    @Test
    public void testScoreNoThrows() {
        Frame f = new Frame();
        assertEquals(0, f.getScore());
    }

    @Test
    public void testAddOnThrows() throws Exception {
        Frame f = new Frame();
        f.add(5);
        assertEquals(5, f.getScore());
    }
}
