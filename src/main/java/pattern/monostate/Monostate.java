package pattern.monostate;

/**
 * @author myeongju.jung
 */
public class Monostate {
    private static int x = 0;

    public Monostate() {

    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }
}
