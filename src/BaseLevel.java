import java.util.ArrayList;
import java.util.List;

/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class BaseLevel implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    /**
     * This is a constructor method that initiates a BaseLevel object using given level parameters.
     *
     * @param numberOfBalls the number of balls
     * @param paddleSpeed the paddle's speed
     * @param paddleWidth the paddle's width
     * @param levelName the level's name
     * @param background the level's background
     * @param numberOfBlocksToRemove the number of blocks to remove in order to win the level
     */
    public BaseLevel(int numberOfBalls, int paddleSpeed, int paddleWidth, String levelName, Sprite background,
                     int numberOfBlocksToRemove) {
        this.numberOfBalls = numberOfBalls;
        this.initialBallVelocities = new ArrayList<>();
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.levelName = levelName;
        this.background = background;
        this.blocks = new ArrayList<>();
        this.numberOfBlocksToRemove = numberOfBlocksToRemove;
    }

    /**
     * This is a method that adds a given velocity to this level's list of ball velocities.
     *
     * @param velocity the given velocity
     */
    public void addBallVelocity(Velocity velocity) {
        this.initialBallVelocities().add(velocity);
    }

    /**
     * This is a method that adds a given block to this level's list of blocks.
     *
     * @param block the given block
     */
    public void addBlock(Block block) {
        this.blocks().add(block);
    }

    /**
     * This is a method that returns this level's number of balls.
     *
     * @return this level's number of balls
     */
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * This is a method that returns this level's initial ball velocities.
     *
     * @return this level's initial ball velocities
     */
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }

    /**
     * This is a method that returns this level's paddle's speed.
     *
     * @return this level's paddle's speed
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * This is a method that returns this level's paddle's width.
     *
     * @return this level's paddle's width
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * This is a method that returns this level's name.
     *
     * @return this level's name
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * This is a method that returns this level's background.
     *
     * @return this level's background
     */
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * This is a method that returns this level's blocks.
     *
     * @return this level's blocks
     */
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * This is a method that returns this level's number of blocks to remove.
     *
     * @return this level's number of blocks to remove
     */
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}
