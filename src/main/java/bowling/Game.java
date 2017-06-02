package bowling;

/**
 * @author myeongju.jung
 */
@SuppressWarnings("WeakerAccess")
public class Game {
    private int itsCurrentFrame = 1;
    private boolean firstThrowInFrame = true;
    private Scorer itsScorer = new Scorer();

    public int score() {
        return scoreForFrame(getCurrentFrame() - 1);
    }

    public void add(int pins) {
        itsScorer.addThrow(pins);
        adjustCurrentFrame(pins);
    }

    // 맞추다. 조정하다
    private void adjustCurrentFrame(int pins) {
        if (firstThrowInFrame) {
            // 스트라이크
            if (pins == 10) {
                itsCurrentFrame++;
            } else {
                firstThrowInFrame = false;
            }
        } else {
            firstThrowInFrame = true;
            itsCurrentFrame++;
        }
        itsCurrentFrame = Math.min(11, itsCurrentFrame);
    }

    public int scoreForFrame(int theFrame) {
        return itsScorer.scoreForFrame(theFrame);
    }


    public int getCurrentFrame() {
        return itsCurrentFrame;
    }
}
