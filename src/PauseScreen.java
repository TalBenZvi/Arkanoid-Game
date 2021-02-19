import biuoop.DrawSurface;

/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class PauseScreen implements Animation {
    private static final int TEXT_X = 10;
    private static final int TEXT_SIZE = 32;

    /**
     * This is a constructor method that initiates a PauseScreen object.
     */
    public PauseScreen() { }

    /**
     * This is a method that draws one frame of the animation on a given draw surface.
     *
     * @param d the given draw surface
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(TEXT_X, d.getHeight() / 2, "paused -- press space to continue", TEXT_SIZE);
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