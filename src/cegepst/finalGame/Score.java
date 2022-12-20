package cegepst.finalGame;

public class Score {

    private static Score instance;
    private int score;
    private int level;

    public static Score getInstance() {
        if (instance == null) {
            instance = new Score();
        }
        return instance;
    }

    private Score() {
        score = 0;
        level = 1;
    }

    public boolean hasScoreForNextLevel() {
        return score >= level * 500;
    }

    public int getScore() {
        return score;
    }

    public int getScoreForLevel() {
        return score - 500 * (level - 1);
    }

    public int getLevel() {
        return level;
    }

    public void nextLevel() {
        level++;
    }

    public void increment(int increment) {
        setScore(score + increment);
    }

    public void setScore(int score) {
        this.score = score;
    }
}
