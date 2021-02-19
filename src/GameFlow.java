import biuoop.KeyboardSensor;

import java.util.List;

/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private HighScoreTrackingListener highScoreTrackingListener;

    /**
     * This is a constructor method that initiates an GameFlow object using given keyboard and animation runner.
     *
     * @param ks the given keyboard
     * @param ar the given animation runner
     */
    public GameFlow(KeyboardSensor ks, AnimationRunner ar) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.highScoreTrackingListener = new HighScoreTrackingListener();
    }

    /**
     * This is a method that returns this game's keyboard sensor.
     *
     * @return this game's keyboard sensor
     */
    public KeyboardSensor getKeyboardSensor() {
        return this.keyboardSensor;
    }

    /**
     * This is a method that returns this game's animation runner.
     *
     * @return this game's animation runner
     */
    public AnimationRunner getAnimationRunner() {
        return this.animationRunner;
    }

    public HighScoreTrackingListener getRecordTrackingListener() {
        return this.highScoreTrackingListener;
    }

    /**
     * This is a method that run a given list of levels.
     *
     * @param levels the given list of levels
     */
    public void runLevels(List<LevelInformation> levels) {
        Counter score = new Counter(0);
        boolean hasWon = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.getKeyboardSensor(), this.getAnimationRunner(), score);
            level.initialize();
            level.run();
            if (level.getRemainingBalls().getValue() == 0) {
                hasWon = false;
                break;
            }
        }
        this.getRecordTrackingListener().gameEnded(score.getValue());
        if (hasWon) {
            this.getAnimationRunner().run(new KeyPressStoppableAnimation(this.getKeyboardSensor(),
                    KeyboardSensor.SPACE_KEY, new YouWinScreen(score)));
        } else {
            this.getAnimationRunner().run(new KeyPressStoppableAnimation(this.getKeyboardSensor(),
                    KeyboardSensor.SPACE_KEY, new GameOverScreen(score)));
        }
        this.getAnimationRunner().run(new KeyPressStoppableAnimation(this.getKeyboardSensor(),
                KeyboardSensor.SPACE_KEY, new HighScoreScreen()));
    }
}