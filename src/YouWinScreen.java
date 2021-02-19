import biuoop.DrawSurface;

/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class YouWinScreen implements Animation {
    private Counter score;

    private static final int TEXT_X = 10;
    private static final int TEXT_SIZE = 32;

    /**
     * This is a constructor method that initiates a YouWinScreen object using a given score counter.
     *
     * @param score the given score counter
     */
    public YouWinScreen(Counter score) {
        this.score = score;
    }

    /**
     * This is a method that returns this screen's level's score.
     *
     * @return this screen's level's score
     */
    public Counter getScore() {
        return this.score;
    }

    /**
     * This is a method that draws one frame of the animation on a given draw surface.
     *
     * @param d the given draw surface
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(TEXT_X, d.getHeight() / 2, "You Win! Your score is " + this.getScore().getValue(), TEXT_SIZE);
    }

    /**
     * This is a method that return whether or not the animation should stop.
     *
     * @return true if the animation should stop
     */
    public boolean shouldStop() {
        return false;
    }
}