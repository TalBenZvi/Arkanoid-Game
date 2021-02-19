/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class BallRemover implements HitListener {
    private GameLevel level;
    private Counter remainingBalls;

    /**
     * This is a constructor method that initiates a BallRemover object using a given level and a given counter.
     *
     * @param level the given level
     * @param remainingBalls the amount of balls in the level
     */
    public BallRemover(GameLevel level, Counter remainingBalls) {
        this.level = level;
        this.remainingBalls = remainingBalls;
    }

    /**
     * This is a method that returns this ball remover's level.
     *
     * @return this ball remover's level
     */
    public GameLevel getLevel() {
        return this.level;
    }

    /**
     * This is a method that returns this ball remover's remaining balls counter.
     *
     * @return this ball remover's remaining balls counter
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    /**
     * This is a method that notifies this ball remover that a given block was hit by a given ball.
     * <p>
     * The method removes the given ball from the level
     * </p>
     * @param beingHit the given block
     * @param hitter the given ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromLevel(this.getLevel());
        this.getRemainingBalls().decrease(1);
    }
}