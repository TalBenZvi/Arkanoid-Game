import java.io.*;

public class HighScoreTrackingListener {
    private static final String FILE_PATH = "highscores.txt";
    private static final String HIGH_SCORE_TEXT = "The highest score so far is: ";

    public HighScoreTrackingListener() {}

    public void gameEnded(int score) {
        Integer highScore = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_PATH)));
            String line;
            line = br.readLine();
            if (line != null) {
                highScore = Integer.parseInt(line.substring(HIGH_SCORE_TEXT.length()));
            }
        } catch (IOException e) {} finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {}
            }
        }
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(FILE_PATH)));
            if (highScore == null || highScore < score) {
                pw.println("The highest score so far is: " + score);
            } else {
                pw.println("The highest score so far is: " + highScore);
            }
        } catch (IOException e) {}
        finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    public static void main(String[] args) {
        HighScoreTrackingListener rtl = new HighScoreTrackingListener();
        rtl.gameEnded(3);
        rtl.gameEnded(47);
        rtl.gameEnded(29);
        rtl.gameEnded(3);
        rtl.gameEnded(38);
    }
}
