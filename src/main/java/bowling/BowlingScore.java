package bowling;

public class BowlingScore {
    final public static int numFrames = 10;
    final public static int maxPoints = 10;

    public int getScore() {
        return score;
    }

    public int getFrameIndex() {
        return frameIndex;
    }

    public boolean addRoll(int points) {
        if(points < 0 || points + frameScore > maxPoints)
            return false;
        if(frameIndex >= numFrames)
            return false;

        frameRollCount += 1;
        frameScore += points;
        addToScore(points);
        updateBonuses(points);
        if(frameIndex == numFrames - 1)
            handleLastFrame();
        if(frameRollCount == 2 + extraRolls || hadStrike)
            startNextFrame();
        return true;
    }

    private void startNextFrame() {
        frameIndex += 1;
        frameRollCount = 0;
        frameScore = 0;
    }

    private void handleLastFrame() {
        if(hadStrike || hadSpare) {
            if(extraRolls == 0)
                extraRolls = 1;
            hadStrike = hadSpare = false;
            frameScore = 0;
        }
    }

    private void updateBonuses(int points) {
        hadStrikeBefore = hadStrike;
        hadStrike = (frameRollCount == 1 && points == maxPoints);
        hadSpare = (frameRollCount == 2 && frameScore == maxPoints);
    }

    private void addToScore(int points) {
        score += points;
        applyBonus(hadStrike, points);
        applyBonus(hadStrikeBefore, points);
        applyBonus(hadSpare, points);
    }

    private void applyBonus(boolean hasBonus, int points) {
        if(hasBonus)
            score += points;
    }

    private int score = 0;
    private int frameIndex = 0;
    private int frameScore = 0;
    private int frameRollCount = 0;
    private boolean hadStrike = false;
    private boolean hadStrikeBefore = false;
    private boolean hadSpare = false;
    private int extraRolls = 0;
}
