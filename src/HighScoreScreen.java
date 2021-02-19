import biuoop.DrawSurface;
import biuoop.GUI;

import java.io.*;

/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class HighScoreScreen implements Animation {
    private static final String HIGH_SCORE_FILE_PATH = "highscores.txt";
    private static final int TEXT_X = 30;
    private static final int TEXT_Y = 300;
    private static final int TEXT_SIZE = 50;

    public HighScoreScreen() {}

    public void doOneFrame(DrawSurface d) {
        File highScoreFile = new File(HIGH_SCORE_FILE_PATH);
        if (!highScoreFile.exists()) {
            d.drawText(TEXT_X, TEXT_Y, "The highest score so far is: 0", TEXT_SIZE);
        } else {
            BufferedReader br = null;
            try {

                br = new BufferedReader(new InputStreamReader(new FileInputStream(HIGH_SCORE_FILE_PATH)));
                String line = br.readLine();;
                d.drawText(TEXT_X, TEXT_Y, line , TEXT_SIZE);
            } catch (IOException e) {} finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {}
                }
            }
        }
    }

    public boolean shouldStop() {
        return false;
    }
}