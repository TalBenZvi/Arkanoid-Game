import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class Paddle implements Sprite, Collidable {
    private Rectangle rectangle;
    private java.awt.Color color;
    private biuoop.KeyboardSensor keyboard;
    private int speed;
    private int leftBorder;
    private int rightBorder;
    private GameLevel level;

    private static final int FAR_LEFT_DEGREES = 300;
    private static final int NEAR_LEFT_DEGREES = 330;
    private static final int FAR_RIGHT_DEGREES = 60;
    private static final int NEAR_RIGHT_DEGREES = 30;

    /**
     * This is a constructor method that initiates a Paddle object using a rectangle, color, speed, gui, frame width.
     *
     * @param rectangle the paddle's collision rectangle
     * @param color the paddle's color
     * @param speed the paddle's speed
     * @param gui the paddle's level's gui
     * @param frameWidth the paddle's level's frameWidth
     */
    public Paddle(Rectangle rectangle, java.awt.Color color, int speed, GUI gui, int frameWidth) {
        this.rectangle = new Rectangle(rectangle.getUpperLeft(), rectangle.getWidth(), rectangle.getHeight());
        this.color = color;
        this.keyboard = gui.getKeyboardSensor();
        this.speed = speed;
        this.leftBorder = frameWidth;
        this.rightBorder = gui.getDrawSurface().getWidth() - frameWidth;
        this.level = null;
    }

    /**
     * This is a constructor method that initiates a Paddle using point, width, height, color, speed, gui, frame width.
     *
     * @param upperLeft the paddle's collision rectangle's upper left point
     * @param width the paddle's collision rectangle's width
     * @param height the paddle's collision rectangle's height
     * @param color the paddle's color
     * @param speed the paddle's speed
     * @param gui the paddle's level's gui
     * @param frameWidth the paddle's level's frameWidth
     */
    public Paddle(Point upperLeft, double width, double height, java.awt.Color color, int speed, GUI gui,
                  int frameWidth) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.keyboard = gui.getKeyboardSensor();
        this.speed = speed;
        this.leftBorder = frameWidth;
        this.rightBorder = gui.getDrawSurface().getWidth() - frameWidth;
        this.level = null;
    }

    /**
     * This is a method that sets this paddle's collision rectangle using a given upper left point, width and height.
     *
     * @param upperLeft the paddle's collision rectangle's upper left point
     * @param width the paddle's collision rectangle's width
     * @param height the paddle's collision rectangle's height
     */
    public void setCollisionRectangle(Point upperLeft, double width, double height) {
        this.rectangle = new Rectangle(upperLeft, width, height);
    }

    /**
     * This is a method that returns this paddle's collision rectangle.
     *
     * @return this paddle's collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.rectangle.getUpperLeft(), this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    /**
     * This is a method that returns this paddle's color.
     *
     * @return this paddle's color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * This is a method that returns this paddle's keyboard.
     *
     * @return this paddle's keyboard
     */
    public biuoop.KeyboardSensor getKeyboard() {
        return this.keyboard;
    }

    /**
     * This is a method that returns this paddle's speed.
     *
     * @return this paddle's speed
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * This is a method that returns this paddle's left border.
     *
     * @return this paddle's left border
     */
    public int getLeftBorder() {
        return this.leftBorder;
    }

    /**
     * This is a method that returns this paddle's right border.
     *
     * @return this paddle's right border
     */
    public int getRightBorder() {
        return this.rightBorder;
    }

    /**
     * This is a method that returns this paddle's level.
     *
     * @return this paddle's level
     */
    public GameLevel getLevel() {
        return this.level;
    }

    /**
     * This is a method that moves this paddle to the left, keeping it inside its borders.
     */
    public void moveLeft() {
        if (this.getLevel() == null) {
            return;
        }
        /*
        In order to fix a bug where moving the paddle into a ball gets the ball stuck inside the paddle, the
        method makes sure that every ball that the paddle is moving into is pushed along with it and has its
        velocity updated. It also makes sure not to push the ball out of its borders
         */
        List<Ball> balls = this.getLevel().getBalls();
        for (Ball ball : balls) {
            if (new Rectangle(new Point(this.getCollisionRectangle().getUpperLeft().getX() - this.getSpeed(),
                    this.getCollisionRectangle().getUpperLeft().getY()), this.getCollisionRectangle().getWidth(),
                    this.getCollisionRectangle().getHeight()).contains(ball.getCenter())) {
                ball.setVelocity(Velocity.fromAngleAndSpeed(300, ball.getVelocity().getSpeed()));
                if (this.getCollisionRectangle().getUpperLeft().getX() - this.getSpeed() - 1
                        <= this.getLeftBorder()) {
                    ball.setCenter(this.getLeftBorder() + 1, ball.getY());
                } else {
                    ball.setCenter(this.getCollisionRectangle().getUpperLeft().getX() - this.getSpeed() - 1,
                            ball.getY());
                }
            }
        }
        //The method makes sure that the paddle doesn't exit its borders
        if (this.getCollisionRectangle().getUpperLeft().getX() - this.getSpeed() < this.getLeftBorder()) {
            this.setCollisionRectangle(new Point(this.getLeftBorder(),
                    this.getCollisionRectangle().getUpperLeft().getY()), this.getCollisionRectangle().getWidth(),
                    this.getCollisionRectangle().getHeight());
        } else {
            this.setCollisionRectangle(new Point(this.getCollisionRectangle().getUpperLeft().getX() - this.getSpeed(),
                    this.getCollisionRectangle().getUpperLeft().getY()), this.getCollisionRectangle().getWidth(),
                    this.getCollisionRectangle().getHeight());
        }
    }

    /**
     * This is a method that moves this paddle to the right, keeping it inside its borders.
     */
    public void moveRight() {
        if (this.getLevel() == null) {
            return;
        }
        /*
        In order to fix a bug where moving the paddle into a ball gets the ball stuck inside the paddle, the
        method makes sure that every ball that the paddle is moving into is pushed along with it and has its
        velocity updated. It also makes sure not to push the ball out of its borders
         */
        List<Ball> balls = this.getLevel().getBalls();
        for (Ball ball : balls) {
            if (new Rectangle(new Point(this.getCollisionRectangle().getUpperLeft().getX() + this.getSpeed(),
                    this.getCollisionRectangle().getUpperLeft().getY()), this.getCollisionRectangle().getWidth(),
                    this.getCollisionRectangle().getHeight()).contains(ball.getCenter())) {
                ball.setVelocity(Velocity.fromAngleAndSpeed(60, ball.getVelocity().getSpeed()));
                if (this.getCollisionRectangle().getUpperRight().getX() + this.getSpeed() + 1
                        >= this.getRightBorder()) {
                    ball.setCenter(this.getRightBorder() - 1, ball.getY());
                } else {
                    ball.setCenter(this.getCollisionRectangle().getUpperRight().getX() + this.getSpeed() + 1,
                            ball.getY());
                }
            }
        }
        //The method makes sure that the paddle doesn't exit its borders
        if (this.getCollisionRectangle().getUpperRight().getX() + this.getSpeed() > this.getRightBorder()) {
            this.setCollisionRectangle(new Point(this.getRightBorder() - this.getCollisionRectangle().getWidth(),
                    this.getCollisionRectangle().getUpperLeft().getY()), this.getCollisionRectangle().getWidth(),
                    this.getCollisionRectangle().getHeight());
        } else {
            this.setCollisionRectangle(new Point(this.getCollisionRectangle().getUpperLeft().getX() + this.getSpeed(),
                    this.getCollisionRectangle().getUpperLeft().getY()), this.getCollisionRectangle().getWidth(),
                    this.getCollisionRectangle().getHeight());
        }
    }

    /**
     * This is a method that notifies this paddle that time has passed.
     * <p>
     * if the left/right key is pressed (but not both), the method moves the paddle to the left/right
     * </p>
     */
    public void timePassed() {
        if (!(this.getKeyboard().isPressed(KeyboardSensor.LEFT_KEY)
                && this.getKeyboard().isPressed(KeyboardSensor.RIGHT_KEY))) {
            if (this.getKeyboard().isPressed(KeyboardSensor.LEFT_KEY)) {
                moveLeft();
            } else if (this.getKeyboard().isPressed(KeyboardSensor.RIGHT_KEY)) {
                moveRight();
            }
        }
    }

    /**
     * This is a method that draws this paddle on a given surface.
     * <p>
     * The method assumes that the given draw surface does not equal null
     * </p>
     * @param d the given surface
     */
    public void drawOn(DrawSurface d) {
        this.getCollisionRectangle().drawOn(d, this.getColor());
        this.getCollisionRectangle().drawFrameOn(d, java.awt.Color.BLACK);
    }

    /**
     * This is a method that returns the velocity a given ball gets after colliding with this paddle
     * <p>
     * The method returns the velocity a given ball gets after colliding with this paddle at a given point with a given
     * velocity. If the given ball collided with this paddle's bottom edge its velocity is reversed vertically.
     * This paddle's width is divided into 5 equal regions:
     * region 1: the object's velocity's angle is changed to 300 degrees
     * region 2: the object's velocity's angle is changed to 330 degrees
     * region 3: the object's velocity is reversed vertically.
     * region 4: the object's velocity's angle is changed to 30 degrees
     * region 5: the object's velocity's angle is changed to 60 degrees
     * The method assumes that the given point and the given velocity do not equal null
     * </p>
     * @param hitter the given ball
     * @param collisionPoint the collision point
     * @param currentVelocity the object's velocity before the collision
     * @return the object's velocity after the collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (this.getCollisionRectangle().getBottomEdge().contains(collisionPoint)) {
            return new Velocity(currentVelocity.getDX(), -currentVelocity.getDY());
        }
        int region = 0;
        for (int i = 1; i <= 5; i++) {
            if (this.getCollisionRectangle().getUpperLeft().getX() + this.getCollisionRectangle().getWidth()
                    * (i - 1) / 5 <= collisionPoint.getX() && collisionPoint.getX()
                    <= this.getCollisionRectangle().getUpperLeft().getX()
                    + this.getCollisionRectangle().getWidth() * i / 5) {
                region = i;
            }
        }
        switch (region) {
            case 1:
                return Velocity.fromAngleAndSpeed(FAR_LEFT_DEGREES, currentVelocity.getSpeed());
            case 2:
                return Velocity.fromAngleAndSpeed(NEAR_LEFT_DEGREES, currentVelocity.getSpeed());
            case 3:
                return new Velocity(currentVelocity.getDX(), -currentVelocity.getDY());
            case 4:
                return Velocity.fromAngleAndSpeed(NEAR_RIGHT_DEGREES, currentVelocity.getSpeed());
            case 5:
                return Velocity.fromAngleAndSpeed(FAR_RIGHT_DEGREES, currentVelocity.getSpeed());
            default:
                return new Velocity(currentVelocity.getDX(), currentVelocity.getDY());
        }
    }

    /**
     * This is a method that adds this paddle to a given level.
     * <p>
     * The method assumes that the given level does not equal null
     * </p>
     * @param g the given level
     */
    public void addToLevel(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
        this.level = g;
    }
}
