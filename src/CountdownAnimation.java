import biuoop.DrawSurface;

/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countStart;
    private SpriteCollection levelScreen;
    private double countdownLeft;
    private int displayNumber;
    private boolean stop;

    private static final double FPS = 60;
    private static final int TEXT_SIZE = 30;
    private static final int TEXT_X = 393;

    /**
     * This is a constructor method that initiates a CountdownAnimation object using countdown parameters.
     *
     * @param numOfSeconds the number of seconds the animation takes
     * @param countFrom the number that the animation counts from
     * @param levelScreen the level's sprites that will be displayed under the countdown animation
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection levelScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countStart = countFrom;
        this.levelScreen = levelScreen;
        this.countdownLeft = countFrom;
        this.displayNumber = countFrom;
        this.stop = false;
    }

    /**
     * This is a method that returns the number of seconds this countdown animation takes.
     *
     * @return the number of seconds this countdown animation takes
     */
    public double getNumOfSeconds() {
        return this.numOfSeconds;
    }

    /**
     * This is a method that returns the number that this countdown animation counts from.
     *
     * @return the number that this countdown animation counts from
     */
    public int getCountStart() {
        return this.countStart;
    }

    /**
     * This is a method that returns the level's sprites that will be displayed under this countdown animation.
     *
     * @return the level's sprites that will be displayed under this countdown animation
     */
    public SpriteCollection getLevelScreen() {
        return this.levelScreen;
    }

    /**
     * This is a method that returns the number of seconds left for the countdown.
     *
     * @return the number of seconds left for the countdown
     */
    public double getCountdownLeft() {
        return this.countdownLeft;
    }

    /**
     * This is a method that returns the number that is displayed in this countdown animation.
     *
     * @return the number that is displayed in this countdown animation
     */
    public int getDisplayNumber() {
        return this.displayNumber;
    }

    /**
     * This is a method that lowers the amount of seconds left for this countdown by a given amount.
     *
     * @param seconds the given amount of seconds
     */
    private void countDownBy(double seconds) {
        this.countdownLeft -= seconds;
    }

    /**
     * This is a method that lowers this countdown animation's display number by 1.
     */
    private void lowerDisplayNumber() {
        this.displayNumber--;
    }

    /**
     * This is a method that draws one frame of the animation on a given draw surface.
     *
     * @param d the given draw surface
     */
    public void doOneFrame(DrawSurface d) {
        this.getLevelScreen().drawAllOn(d);
        d.drawText(TEXT_X, d.getHeight() / 2, Integer.toString(this.getDisplayNumber()), TEXT_SIZE);
        countDownBy((double) this.getCountStart() / (FPS * this.getNumOfSeconds()));
        if (this.getCountdownLeft() <= this.getDisplayNumber() - 1) {
            lowerDisplayNumber();
        }
        if (this.getDisplayNumber() == 0) {
            this.stop = true;
        }
    }

    /**
     * This is a method that return whether or not the animation should stop.
     *
     * @return true if the animation should stop
     */
    public boolean shouldStop() {
        return this.stop;
    }
}