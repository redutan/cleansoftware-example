package bowling;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author myeongju.jung
 */
public class TestGame {
    private Game g;

    @Before
    public void setUp() throws Exception {
        g = new Game();
    }

    @Test
    public void testTwoThrowsNoMark() throws Exception {
        g.add(5);
        g.add(4);
        assertEquals(9, g.score());
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
    }

    @Test
    public void testSimpleSpare() throws Exception {
        g.add(3);
        g.add(7);
        g.add(3);
        assertEquals(13, g.scoreForFrame(1));
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
    }

    @Test
    public void testSimpleStrike() throws Exception {
        g.add(10);
        g.add(3);
        g.add(6);
        assertEquals(19, g.scoreForFrame(1));
        assertEquals(28, g.score());
    }

    @Test
    public void testPerfectGame() throws Exception {
        for (int i = 0; i < 12; i++) {
            g.add(10);
        }
        assertEquals(300, g.score());
    }

    @Test
    public void testEndOfArray() throws Exception {
        for (int i = 0; i < 9; i++) {
            g.add(0);
            g.add(0);
        }
        g.add(2);
        g.add(8);   // 10번째 프레임의 스페어
        g.add(10);  // 배열 마지막 위치에서의 스트라이크
        assertEquals(20, g.score());
    }

    @Test
    public void testSampleGame() throws Exception {
        g.add(1);
        g.add(4);
        g.add(4);
        g.add(5);
        g.add(6);
        g.add(4);
        g.add(5);
        g.add(5);
        g.add(10);
        g.add(0);
        g.add(1);
        g.add(7);
        g.add(3);
        g.add(6);
        g.add(4);
        g.add(10);
        g.add(2);
        g.add(8);
        g.add(6);
        assertEquals(133, g.score());
    }

    @Test
    public void testHeartBreak() throws Exception {
        for (int i = 0; i < 11; i++) {
            g.add(10);
        }
        g.add(9);
        assertEquals(299, g.score());
    }

    @Test
    public void testTenthFrameSpare() throws Exception {
        for (int i = 0; i < 9; i++) {
            g.add(10);
        }
        g.add(9);
        g.add(1);
        g.add(1);
        assertEquals(270, g.score());
    }
}
