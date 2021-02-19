import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * This is a constructor method that initiates a KeyPressStoppableAnimation object using given parameters.
     *
     * @param sensor the given keyboard sensor
     * @param key the key that upon being pressed would stop the animation
     * @param animation the given animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * This is a method that returns this animation's keyboard sensor.
     *
     * @return this animation's keyboard sensor
     */
    public KeyboardSensor getKeyboardSensor() {
        return this.keyboardSensor;
    }

    /**
     * This is a method that returns the key that upon being pressed would stop the animation.
     *
     * @return the key that upon being pressed would stop the animation
     */
    public String getKey() {
        return this.key;
    }

    /**
     * This is a method that returns the animation that is played.
     *
     * @return the animation that is played
     */
    public Animation getAnimation() {
        return this.animation;
    }

    /**
     * This is a method that draws one frame of the animation on a given draw surface.
     *
     * @param d the given draw surface
     */
    public void doOneFrame(DrawSurface d) {
        if (!this.getKeyboardSensor().isPressed(this.getKey())) {
            isAlreadyPressed = false;
        }
        if (!this.isAlreadyPressed) {
            if (this.getKeyboardSensor().isPressed(this.getKey())) {
                this.stop = true;
            }
            this.getAnimation().doOneFrame(d);
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