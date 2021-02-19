import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;

    private static final int FPS = 60;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    /**
     * This is a constructor method that initiates an AnimationRunner object.
     */
    public AnimationRunner() {
        this.gui = new GUI("Arkanoid Game", WINDOW_WIDTH, WINDOW_HEIGHT);
        this.framesPerSecond = FPS;
    }

    /**
     * This is a method that returns this animation runner's GUI.
     *
     * @return this animation runner's GUI
     */
    public GUI getGUI() {
        return this.gui;
    }

    /**
     * This is a method that returns this animation runner's number of frames per second.
     *
     * @return this animation runner's number of frames per second
     */
    public int getFramesPerSecond() {
        return this.framesPerSecond;
    }

    /**
     * This is a method that runs a given animation.
     *
     * @param animation the given animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.getFramesPerSecond();
        Sleeper sleeper = new Sleeper();
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = this.getGUI().getDrawSurface();
            animation.doOneFrame(d);
            this.getGUI().show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}