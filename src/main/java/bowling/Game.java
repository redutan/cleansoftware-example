package bowling;

/**
 * @author myeongju.jung
 */
public class Game {
    private int ball;
    private int firstThrow;

    private int itsScore = 0;
    private int[] itsThrows = new int[21];
    private int itsCurrentThrow = 0;
    private int itsCurrentFrame = 1;
    private boolean firstThrowInFrame = true;

    public int score() {
        return scoreForFrame(getCurrentFrame() - 1);
    }

    public void add(int pins) {
        itsThrows[itsCurrentThrow++] = pins;
        itsScore += pins;
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
        ball = 0;
        int score = 0;
        for (int currentFrame = 0; currentFrame < theFrame; currentFrame++) {
            if (strike()) {
                ball++;
                score += 10 + nextTwoBalls();
            } else {
                score += handleSecondThrow();
            }
        }
        return score;
    }

    private boolean strike() {
        return itsThrows[ball] == 10;
    }

    private int nextTwoBalls() {
        return itsThrows[ball] + itsThrows[ball + 1];
    }

    private int handleSecondThrow() {
        int score = 0;
        // 스페어는 다음 프레임의 첫번째 투구에 필요하다.
        if (spare()) {
            ball += 2;
            score += 10 + nextBall();
        } else {
            score += twoBallsInFrame();
            ball += 2;
        }
        return score;
    }

    private boolean spare() {
        return (itsThrows[ball] + itsThrows[ball + 1]) == 10;
    }

    private int nextBall() {
        return itsThrows[ball];
    }

    private int twoBallsInFrame() {
        return itsThrows[ball] + itsThrows[ball + 1];
    }

    public int getCurrentFrame() {
        return itsCurrentFrame;
    }
}
