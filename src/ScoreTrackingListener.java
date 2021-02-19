/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    private static final int BLOCK_HIT_SCORE = 5;

    /**
     * This is a constructor method that initiates a ScoreTrackingListener object using a given counter.
     *
     * @param scoreCounter the given counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This is a method that returns this score tracking listener's score counter.
     *
     * @return this score tracking listener's score counter
     */
    public Counter getCurrentScore() {
        return this.currentScore;
    }

    /**
     * This is a method that notifies this score tracking listener that a given block was hit by a given ball.
     * <p>
     * The method increases the score by a constant amount
     * </p>
     * @param beingHit the given block
     * @param hitter the given ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.getCurrentScore().increase(BLOCK_HIT_SCORE);
    }
}