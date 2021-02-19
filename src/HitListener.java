/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public interface HitListener {

    /**
     * This is a method that notifies the hit listener that a given block was hit by a given ball.
     *
     * @param beingHit the given block
     * @param hitter the given ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}
