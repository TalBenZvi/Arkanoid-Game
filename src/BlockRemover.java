/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class BlockRemover implements HitListener {
    private GameLevel level;
    private Counter remainingBlocks;

    /**
     * This is a constructor method that initiates a BlockRemover object using a given level and a given counter.
     *
     * @param level the given level
     * @param remainingBlocks the amount of blocks in the level
     */
    public BlockRemover(GameLevel level, Counter remainingBlocks) {
        this.level = level;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * This is a method that returns this block remover's level.
     *
     * @return this block remover's level
     */
    public GameLevel getLevel() {
        return this.level;
    }

    /**
     * This is a method that returns this block remover's remaining blocks counter.
     *
     * @return this block remover's remaining blocks counter
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * This is a method that notifies this block remover that a given block was hit by a given ball.
     * <p>
     * The method removes the given block from the level
     * </p>
     * @param beingHit the given block
     * @param hitter the given ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromLevel(this.getLevel());
        beingHit.removeHitListener(this);
        this.getRemainingBlocks().decrease(1);
    }
}