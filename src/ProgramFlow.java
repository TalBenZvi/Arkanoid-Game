import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

public class ProgramFlow {
    private String filePath;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;

    public ProgramFlow(String filePath) {
        this.filePath = filePath;
        this.animationRunner = new AnimationRunner();
        this.keyboardSensor = this.animationRunner.getGUI().getKeyboardSensor();
    }

    public String getFilePath() {
        return this.filePath;
    }

    public AnimationRunner getAnimationRunner() {
        return this.animationRunner;
    }

    public KeyboardSensor getKeyboardSensor() {
        return this.keyboardSensor;
    }

    private Task<Void> createRunGameTask() {
        String filePath = this.getFilePath();
        return new Task<Void>() {
            public Void run() {
                GameFlow gameFlow = new GameFlow(keyboardSensor, animationRunner);
                List<LevelInformation> gameLevels = LevelSpecificationFileReader.getLevels(filePath);
                gameFlow.runLevels(gameLevels);
                return null;
            }
        };
    }

    private Task<Void> createShowHighScoreTask() {
        return new Task<Void>() {
            public Void run() {
                animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new HighScoreScreen()));
                return null;
            }
        };
    }

    private Task<Void> createQuitTask() {
        return new Task<Void>() {
            public Void run() {
                System.exit(0);
                return null;
            }
        };
    }

    private Menu<Task<Void>> createMenu() {
        Menu<Task<Void>> menu = new MenuAnimation<>("Main Menu", this.getKeyboardSensor());
        menu.addSelection("s", "start the game", createRunGameTask());
        menu.addSelection("h", "show the high score", createShowHighScoreTask());
        menu.addSelection("q", "quit", createQuitTask());
        return menu;
    }

    public void runProgram() {
        Menu<Task<Void>> menu;
        while (true) {
            menu = createMenu();
            this.getAnimationRunner().run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }

    public static void main (String[] args) {
        ProgramFlow programFlow = new ProgramFlow("resources/level_def.txt");
        programFlow.runProgram();
    }
}
