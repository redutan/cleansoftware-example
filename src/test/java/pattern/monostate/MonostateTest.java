package pattern.monostate;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author myeongju.jung
 */
public class MonostateTest {
    @Test
    public void testInstance() throws Exception {
        Monostate m = new Monostate();
        for (int i = 0; i < 10; i++) {
            m.setX(i);
            assertEquals(i, m.getX());
        }
    }

    @Test
    public void testInstanceBehaveAsOne() throws Exception {
        Monostate m1 = new Monostate();
        Monostate m2 = new Monostate();
        for (int i = 0; i < 10; i++) {
            m1.setX(i);
            assertEquals(i, m2.getX());
        }
    }
}
