package bowling;

/**
 * @author myeongju.jung
 */
public class Frame {
    private int itsScore = 0;

    public int getScore() {
        return itsScore;
    }

    public void add(int pins) {
        itsScore += pins;
    }
}
