package bowling;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author myeongju.jung
 */
public class TestGame {
    Game g;

    @Before
    public void setUp() throws Exception {
        g = new Game();
    }

    @Test
    public void testTwoThrowsNoMark() throws Exception {
        g.add(5);
        g.add(4);
        assertEquals(9, g.score());
        assertEquals(2, g.getCurrentFrame());
    }

    @Test
    public void testFourThrowsNoMark() throws Exception {
        g.add(5);
        g.add(4);
        g.add(7);
        g.add(2);
        assertEquals(18, g.score());
        assertEquals(9, g.scoreForFrame(1));
        assertEquals(18, g.scoreForFrame(2));
        assertEquals(3, g.getCurrentFrame());
    }

    @Test
    public void testSimpleSpare() throws Exception {
        g.add(3);
        g.add(7);
        g.add(3);
        assertEquals(13, g.scoreForFrame(1));
        assertEquals(2, g.getCurrentFrame());
    }

    @Test
    public void testSimpleFrameAfterSpare() throws Exception {
        g.add(3);
        g.add(7);
        g.add(3);
        g.add(2);
        assertEquals(13, g.scoreForFrame(1));
        assertEquals(18, g.scoreForFrame(2));
        assertEquals(18, g.score());
        assertEquals(3, g.getCurrentFrame());
    }
}
