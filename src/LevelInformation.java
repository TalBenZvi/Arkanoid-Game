import java.util.List;

/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public interface LevelInformation {
    /**
     * This is a method that returns the level's number of balls.
     *
     * @return the level's number of balls
     */
    int numberOfBalls();
    /**
     * This is a method that returns the initial velocities of the level's balls.
     *
     * @return the initial velocities of the level's balls
     */
    List<Velocity> initialBallVelocities();

    /**
     * This is a method that returns the level's paddle's speed.
     *
     * @return the level's paddle's speed
     */
    int paddleSpeed();

    /**
     * This is a method that returns the level's paddle's width.
     *
     * @return the level's paddle's width
     */
    int paddleWidth();
    /**
     * This is a method that returns the level's name.
     *
     * @return the level's name
     */
    String levelName();
    /**
     * This is a method that returns the level's background.
     *
     * @return the level's background
     */
    Sprite getBackground();
    /**
     * This is a method that returns the level's blocks.
     *
     * @return the level's blocks
     */
    List<Block> blocks();
    /**
     * This is a method that returns the number of blocks to remove in order to win the level.
     *
     * @return the number of blocks to remove in order to win the level
     */
    int numberOfBlocksToRemove();
}