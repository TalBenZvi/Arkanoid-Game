import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MenuAnimation<T> implements Menu<T> {
    private String menuTitle;
    private KeyboardSensor sensor;
    private List<Selection<T>> selections;
    private T status;
    private boolean stop;

    //Window
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    //Title
    private static final int TITLE_X = 150;
    private static final int TITLE_Y = 120;
    private static final int TITLE_SIZE = 100;

    //Blocks
    private static final int BLOCK_WIDTH = 600;
    private static final int FIRST_BLOCK_Y = 150;
    private static final int SPACE_HEIGHT = 20;
    private static final Color[] BLOCK_COLORS = {new Color(200, 0, 0), new Color(0, 200, 0), new Color(104, 104, 207)};

    //Text
    private static final int TEXT_X = 120;
    private static final int FIRST_TEXT_Y = 165;
    private static final int TEXT_SIZE = 40;

    public MenuAnimation(String menuTitle, KeyboardSensor sensor) {
        this.menuTitle = menuTitle;
        this.sensor = sensor;
        this.selections = new ArrayList<>();
        this.stop = false;
    }

    public String getMenuTitle() {
        return this.menuTitle;
    }

    public KeyboardSensor getKeyboardSensor() {
        return this.sensor;
    }

    public List<Selection<T>> getSelections() {
        return this.selections;
    }

    public void setStatus(T status) {
        this.status = status;
    }

    public void doOneFrame(DrawSurface d) {
        d.drawText(TITLE_X, TITLE_Y, this.getMenuTitle(), TITLE_SIZE);
        int numOfSelections = this.getSelections().size();
        if (numOfSelections == 0) {
            return;
        }
        int blockHeight = (WINDOW_HEIGHT - FIRST_BLOCK_Y - numOfSelections * SPACE_HEIGHT) / numOfSelections;
        for (int i = 0; i < numOfSelections; i++) {
            d.setColor(BLOCK_COLORS[i % BLOCK_COLORS.length]);
            d.fillRectangle((WINDOW_WIDTH - BLOCK_WIDTH) / 2, FIRST_BLOCK_Y + i * (blockHeight + SPACE_HEIGHT),
                    BLOCK_WIDTH, blockHeight);
            d.setColor(Color.BLACK);
            d.drawText(TEXT_X, FIRST_TEXT_Y + i * (blockHeight + SPACE_HEIGHT) + blockHeight / 2, "Press '"
                    + this.getSelections().get(i).getKey() + "' to " + this.getSelections().get(i).getMessage(),
                TEXT_SIZE);
        }
        for (Selection<T> selection : this.getSelections()) {
            if (this.getKeyboardSensor().isPressed(selection.getKey())) {
                this.setStatus(selection.getReturnValue());
                this.stop = true;
            }
        }
    }

    public boolean shouldStop() {
        return this.stop;
    }

    public void addSelection(String key, String message, T returnVal) {
        this.getSelections().add(new Selection<>(key, message, returnVal));
    }

    public T getStatus() {
        return this.status;
    }

    public static void main(String[] args) {
        AnimationRunner runner = new AnimationRunner();
        Menu<String> menu = new MenuAnimation<String>("Main Menu", runner.getGUI().getKeyboardSensor());
        menu.addSelection("a", "First Choice", "option a");
        menu.addSelection("b", "Second Choice", "option b");
        menu.addSelection("c", "Third Choice", "option c");
        runner.run(menu);
        runner.getGUI().close();
    }
}
