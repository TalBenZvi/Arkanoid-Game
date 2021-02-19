/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class RunGame {
    private static final String DEFAULT_FILE_PATH = "resources/level_def.txt";

    public static void main(String[] args) {
        ProgramFlow programFlow;
        if (args.length == 0) {
            programFlow = new ProgramFlow(DEFAULT_FILE_PATH);
        } else {
            programFlow = new ProgramFlow(args[0]);
        }
        programFlow.runProgram();
    }
}
