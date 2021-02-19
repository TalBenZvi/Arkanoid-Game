import biuoop.DrawSurface;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelSpecificationFileReader {
    //Window
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    //File Indicators
    private static final String START_LEVEL_INDICATOR = "START_LEVEL";
    private static final String END_LEVEL_INDICATOR = "END_LEVEL";
    private static final String START_BLOCKS_INDICATOR = "START_BLOCKS";
    private static final String END_BLOCKS_INDICATOR = "END_BLOCKS";
    private static final char KEY_AND_VALUE_SEPARATOR = ':';
    private static final String VELOCITY_SEPARATOR = " ";
    private static final char ANGLE_AND_SPEED_SEPARATOR = ',';

    //Property Keys
    private static final String BALL_VELOCITIES_KEY = "ball_velocities";
    private static final String PADDLE_SPEED_KEY = "paddle_speed";
    private static final String PADDLE_WIDTH_KEY = "paddle_width";
    private static final String LEVEL_NAME_KEY = "level_name";
    private static final String BACKGROUND_KEY = "background";
    private static final String NUMBER_OF_BLOCKS_KEY = "num_blocks";
    private static final String BLOCK_DEFINITIONS_KEY = "block_definitions";
    private static final String BLOCKS_START_X_KEY = "blocks_start_x";
    private static final String BLOCKS_START_Y_KEY = "blocks_start_y";
    private static final String ROW_HEIGHT_KEY = "row_height";
    private static final String SYMBOL_KEY = "symbol";
    private static final String WIDTH_KEY = "width";

    public static List<LevelInformation> getLevels(String filePath) {
        List<LevelInformation> levels = new ArrayList<>();
        Map<String, String> levelSpecification = new HashMap<>();
        BaseLevel level = new BaseLevel(0, 0, 0, null, null, 0);
        boolean isReadingLevel = false;
        boolean isReadingBlocks = false;
        List<Map<String, String>> blockTypes = new ArrayList<>();
        List<Map<String, String>> spacerTypes = new ArrayList<>();
        int x = 0;
        int y = 0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.equals("")) {
                    if(isReadingLevel) {
                        if (isReadingBlocks) {
                            if(line.equals(END_BLOCKS_INDICATOR)) {
                                isReadingBlocks = false;
                            } else {
                                for (int i = 0; i < line.length(); i++) {
                                    String symbol = String.valueOf(line.charAt(i));
                                    for (Map<String, String> blockType : blockTypes) {
                                        if(blockType.get(SYMBOL_KEY).equals(symbol)) {
                                            level.addBlock(new Block(x, y, blockType));
                                            x += Integer.parseInt(blockType.get(WIDTH_KEY));
                                        }
                                    }
                                    for (Map<String, String> spacerType : spacerTypes) {
                                        if(spacerType.get(SYMBOL_KEY).equals(symbol)) {
                                            x += Integer.parseInt(spacerType.get(WIDTH_KEY));
                                        }
                                    }
                                }
                                y += Integer.parseInt(levelSpecification.get(ROW_HEIGHT_KEY));
                                x = Integer.parseInt(levelSpecification.get(BLOCKS_START_X_KEY));
                            }
                        } else {
                            if (line.equals(END_LEVEL_INDICATOR)) {
                                isReadingLevel = false;
                                levels.add(level);
                            } else if (line.equals(START_BLOCKS_INDICATOR)) {
                                isReadingBlocks = true;
                                int numberOfBalls = 0;
                                List<Velocity> ballVelocities = new ArrayList<>();
                                String velocitiesDescription = levelSpecification.get(BALL_VELOCITIES_KEY);
                                String[] velocities = velocitiesDescription.split(VELOCITY_SEPARATOR);
                                for (String velocity : velocities) {
                                    double angle = Double.parseDouble(velocity.substring(0,
                                            velocity.indexOf(ANGLE_AND_SPEED_SEPARATOR)));
                                    double speed = Double.parseDouble(velocity.substring(velocity.indexOf(
                                            ANGLE_AND_SPEED_SEPARATOR) + 1));
                                    ballVelocities.add(Velocity.fromAngleAndSpeed(angle, speed));
                                    numberOfBalls++;
                                }
                                int paddleSpeed = Integer.parseInt(levelSpecification.get(PADDLE_SPEED_KEY));
                                int paddleWidth = Integer.parseInt(levelSpecification.get(PADDLE_WIDTH_KEY));
                                String levelName = levelSpecification.get(LEVEL_NAME_KEY);
                                String backgroundDescription = levelSpecification.get(BACKGROUND_KEY);
                                Sprite background = new Sprite() {
                                    @Override
                                    public void drawOn(DrawSurface d) {
                                        new Background(backgroundDescription).drawOn(d, new Rectangle(new Point(0, 0),
                                                WINDOW_WIDTH, WINDOW_HEIGHT));
                                    }

                                    @Override
                                    public void timePassed() {}

                                    @Override
                                    public void addToLevel(GameLevel g) {
                                        g.addSprite(this);
                                    }
                                };
                                int numberOfBlocks = Integer.parseInt(levelSpecification.get(NUMBER_OF_BLOCKS_KEY));
                                level = new BaseLevel(numberOfBalls, paddleSpeed, paddleWidth, levelName, background,
                                        numberOfBlocks);
                                for (Velocity ballVelocity : ballVelocities) {
                                    level.addBallVelocity(ballVelocity);
                                }
                                String blockDefinitionsFilePath = levelSpecification.get(BLOCK_DEFINITIONS_KEY);
                                blockTypes = BlockDefinitionsFileReader.getBlockTypes(blockDefinitionsFilePath);
                                spacerTypes = BlockDefinitionsFileReader.getSpacerTypes(blockDefinitionsFilePath);
                                x = Integer.parseInt(levelSpecification.get(BLOCKS_START_X_KEY));
                                y = Integer.parseInt(levelSpecification.get(BLOCKS_START_Y_KEY));
                            } else {
                                String key = line.substring(0, line.indexOf(KEY_AND_VALUE_SEPARATOR));
                                String value = line.substring(line.indexOf(KEY_AND_VALUE_SEPARATOR) + 1);
                                levelSpecification.put(key, value);
                            }
                        }
                    } else {
                        if(line.equals(START_LEVEL_INDICATOR)) {
                            isReadingLevel = true;
                            levelSpecification = new HashMap<>();
                        }
                    }
                }
            }
            return levels;
        } catch (IOException e) {
            return null;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {}
            }
        }
    }

    public static void main(String[] args) {

    }
}
