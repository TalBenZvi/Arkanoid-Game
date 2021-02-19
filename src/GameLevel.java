import biuoop.KeyboardSensor;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class GameLevel implements Animation {
    private LevelInformation levelInformation;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private List<Ball> balls;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;

    //Window and frame
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int FRAME_WIDTH = 25;
    private static final Color FRAME_COLOR = Color.GRAY;
    private static final Color DEATH_REGION_COLOR = Color.RED;

    //Paddle
    private static final int PADDLE_HEIGHT = 20;
    private static final Color PADDLE_COLOR = Color.ORANGE;

    //Balls
    private static final int BALL_SIZE = 3;
    private static final Color BALL_COLOR = Color.WHITE;

    //Score
    private static final int LEVEL_CLEAR_SCORE = 100;

    //Countdown
    private static final double COUNT_DOWN_SECONDS = 2;
    private static final int COUNT_DOWN_FROM = 3;


    /**
     * This is a constructor method that initiates a GameLevel object using given level parameters.
     *
     * @param levelInformation the level's information
     * @param keyboardSensor the level's keyboard sensor
     * @param animationRunner the level's animation runner
     * @param score the initial score (from previous levels)
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboardSensor, AnimationRunner animationRunner,
                     Counter score) {
        this.levelInformation = levelInformation;
        this.runner = animationRunner;
        this.keyboard = keyboardSensor;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.balls = new ArrayList<>();
        this.remainingBlocks = new Counter(0);
        this.remainingBalls = new Counter(0);
        this.score = score;
    }

    /**
     * This is a method that returns this level's information.
     *
     * @return this level's information
     */
    public LevelInformation getLevelInformation() {
        return this.levelInformation;
    }

    /**
     * This is a method that returns this level's animation runner.
     *
     * @return this level's animation runner
     */
    public AnimationRunner getAnimationRunner() {
        return this.runner;
    }

    /**
     * This is a method that returns this level's keyboard sensor.
     *
     * @return this level's keyboard sensor
     */
    public KeyboardSensor getKeyboard() {
        return this.keyboard;
    }

    /**
     * This is a method that returns this level's sprites.
     *
     * @return this level's sprites
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * This is a method that returns this level's game environment.
     *
     * @return this level's game environment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * This is a method that returns this level's balls.
     *
     * @return this level's balls
     */
    public List<Ball> getBalls() {
        return this.balls;
    }

    /**
     * This is a method that returns this level's remaining blocks counter.
     *
     * @return this level's remaining blocks counter
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * This is a method that returns this level's remaining balls counter.
     *
     * @return this level's remaining balls counter
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    /**
     * This is a method that returns this level's score counter.
     *
     * @return this level's score counter
     */
    public Counter getScore() {
        return this.score;
    }

    /**
     * This is a method that adds a given collidable to this level's game environment.
     * <p>
     * The method assumes that the given collidable does not equal null
     * </p>
     * @param c the given collidable
     */
    public void addCollidable(Collidable c) {
        this.getEnvironment().addCollidable(c);
    }

    /**
     * This is a method that adds a given sprite to this level's sprites.
     * <p>
     * The method assumes that the given sprite does not equal null
     * </p>
     * @param s the given sprite
     */
    public void addSprite(Sprite s) {
        this.getSprites().addSprite(s);
    }

    /**
     * This is a method that adds a given ball to this level's balls.
     * <p>
     * The method assumes that the given ball does not equal null
     * </p>
     * @param b the given ball
     */
    public void addBall(Ball b) {
        this.getBalls().add(b);
    }

    /**
     * This is a method that removes a given collidable from this level's game environment.
     * <p>
     * The method assumes that the given collidable does not equal null
     * </p>
     * @param c the given collidable
     */
    public void removeCollidable(Collidable c) {
        this.getEnvironment().removeCollidable(c);
    }

    /**
     * This is a method that removes a given sprite from this level's sprites.
     * <p>
     * The method assumes that the given sprite does not equal null
     * </p>
     * @param s the given sprite
     */
    public void removeSprite(Sprite s) {
        this.getSprites().removeSprite(s);
    }

    /**
     * This is a method that removes a given ball from this level's balls.
     * <p>
     * The method assumes that the given ball does not equal null
     * </p>
     * @param b the given ball
     */
    public void removeBall(Ball b) {
        this.getBalls().remove(b);
    }

    /**
     * This is a method that creates this level's balls equally spaced on top of its paddle.
     */
    private void createBalls() {
        int numberOfBalls = this.getLevelInformation().numberOfBalls();
        List<Velocity> ballVelocities = this.getLevelInformation().initialBallVelocities();
        int paddleWidth = this.getLevelInformation().paddleWidth();
        int paddleX = (WINDOW_WIDTH - paddleWidth) / 2;
        for (int i = 0; i < numberOfBalls; i++) {
            Ball ball = new Ball(paddleX + ((i + 1) * paddleWidth) / (numberOfBalls + 1), WINDOW_HEIGHT - FRAME_WIDTH
                    - PADDLE_HEIGHT - 1, BALL_SIZE, BALL_COLOR);
            ball.setVelocity(ballVelocities.get(i));
            ball.addToLevel(this);
        }
        this.getRemainingBalls().increase(numberOfBalls);
    }

    /**
     * This is a method that initializes this level's paddle in the middle of the window.
     */
    private void initializePaddle() {
        int paddleSpeed = this.getLevelInformation().paddleSpeed();
        int paddleWidth = this.getLevelInformation().paddleWidth();
        int paddleX = (WINDOW_WIDTH - paddleWidth) / 2;
        Paddle paddle = new Paddle(new Point(paddleX, WINDOW_HEIGHT - FRAME_WIDTH - PADDLE_HEIGHT), paddleWidth,
                PADDLE_HEIGHT, PADDLE_COLOR, paddleSpeed, this.getAnimationRunner().getGUI(), FRAME_WIDTH);
        paddle.addToLevel(this);
    }

    /**
     * This is a method that initializes this level's blocks.
     */
    private void initializeBlocks() {
        List<Block> blocks = this.getLevelInformation().blocks();
        BlockRemover blockRemover = new BlockRemover(this, this.getRemainingBlocks());
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.getScore());
        for (Block block : blocks) {
            block.addToLevel(this);
            block.addHitListener(scoreTrackingListener);
            block.addHitListener(blockRemover);
        }
        this.getRemainingBlocks().increase(this.getLevelInformation().blocks().size());
    }

    /**
     * This is a method that initializes this level's frame by adding the 4 blocks on the edges.
     */
    private void initializeFrame() {
        //Top
        new Block(new Point(0, 0), WINDOW_WIDTH, FRAME_WIDTH, FRAME_COLOR).addToLevel(this);
        //Left
        new Block(new Point(0, FRAME_WIDTH), FRAME_WIDTH, WINDOW_HEIGHT - FRAME_WIDTH, FRAME_COLOR).addToLevel(this);
        //Right
        new Block(new Point(WINDOW_WIDTH - FRAME_WIDTH, FRAME_WIDTH), FRAME_WIDTH, WINDOW_HEIGHT - FRAME_WIDTH,
                FRAME_COLOR).addToLevel(this);
        //Bottom
        BallRemover ballRemover = new BallRemover(this, this.getRemainingBalls());
        Block deathRegion = new Block(new Point(FRAME_WIDTH, WINDOW_HEIGHT - FRAME_WIDTH), WINDOW_WIDTH - 2
                * FRAME_WIDTH, FRAME_WIDTH, DEATH_REGION_COLOR);
        deathRegion.addToLevel(this);
        deathRegion.addHitListener(ballRemover);
    }
    /**
     * This is a method that initializes this level's background, paddle, blocks, frame and indicators.
     */
    public void initialize() {
        this.addSprite(this.getLevelInformation().getBackground());
        initializePaddle();
        initializeBlocks();
        initializeFrame();
        new ScoreIndicator(this.getScore()).addToLevel(this);
        new LivesIndicator(this.getRemainingBalls()).addToLevel(this);
        new LevelNameIndicator(this.getLevelInformation().levelName()).addToLevel(this);
    }
    /**
     * This is a method that draws one frame of the animation on a given draw surface.
     *
     * @param d the given draw surface
     */
    public void doOneFrame(DrawSurface d) {
        this.getSprites().drawAllOn(d);
        this.getSprites().notifyAllTimePassed();
        if (this.getKeyboard().isPressed("p")) {
            this.getAnimationRunner().run(new KeyPressStoppableAnimation(this.getKeyboard(), KeyboardSensor.SPACE_KEY,
                    new PauseScreen()));
        }
        if (this.getRemainingBlocks().getValue() <= this.getLevelInformation().blocks().size()
                - this.getLevelInformation().numberOfBlocksToRemove()) {
            this.getScore().increase(LEVEL_CLEAR_SCORE);
            this.running = false;
        }
        if (this.getRemainingBalls().getValue() == 0) {
            this.running = false;
        }
    }

    /**
     * This is a method that return whether or not the animation should stop.
     *
     * @return true if the animation should stop
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * This is a method that runs this level.
     */
    public void run() {
        createBalls();
        this.getAnimationRunner().run(new CountdownAnimation(COUNT_DOWN_SECONDS, COUNT_DOWN_FROM, this.getSprites()));
        this.running = true;
        this.getAnimationRunner().run(this);
    }
}
