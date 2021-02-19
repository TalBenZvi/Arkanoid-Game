import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockDefinitionsFileReader {
    private static final String PROPERTY_SEPARATOR = " ";
    private static final char KEY_AND_VALUE_SEPARATOR = ':';
    private static final String DEFAULT_VALUES_TOKEN = "default";
    private static final String BLOCK_DEFINITIONS_TOKEN = "bdef";
    private static final String SPACER_DEFINITIONS_TOKEN = "sdef";

    public static List<Map<String, String>> getBlockTypes(String filePath) {
        List<Map<String, String>> blockTypes = new ArrayList<>();
        Map<String, String> defaultProperties = new HashMap<>();
        Map<String, String> blockType;
        String[] properties;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.equals("") && line.charAt(0) != '#') {
                    if (line.substring(0, DEFAULT_VALUES_TOKEN.length()).equals(DEFAULT_VALUES_TOKEN)) {
                        properties = line.substring(DEFAULT_VALUES_TOKEN.length() + 1).split(PROPERTY_SEPARATOR);
                        for (String property : properties) {
                            String key = property.substring(0, property.indexOf(KEY_AND_VALUE_SEPARATOR));
                            String value = property.substring(property.indexOf(KEY_AND_VALUE_SEPARATOR) + 1);
                            defaultProperties.put(key, value);
                        }
                    } else if (line.substring(0, BLOCK_DEFINITIONS_TOKEN.length()).equals(BLOCK_DEFINITIONS_TOKEN)) {
                        blockType = new HashMap<>();
                        properties = line.substring(BLOCK_DEFINITIONS_TOKEN.length() + 1).split(PROPERTY_SEPARATOR);
                        for (String property : properties) {
                            String key = property.substring(0, property.indexOf(KEY_AND_VALUE_SEPARATOR));
                            String value = property.substring(property.indexOf(KEY_AND_VALUE_SEPARATOR) + 1);
                            blockType.put(key, value);
                        }
                        blockType.putAll(defaultProperties);
                        blockTypes.add(blockType);
                    }
                }
            }
            return blockTypes;
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

    public static List<Map<String, String>> getSpacerTypes(String filePath) {
        List<Map<String, String>> spacerTypes = new ArrayList<>();
        Map<String, String> spacerType;
        String[] properties;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.equals("") && line.charAt(0) != '#') {
                    if (line.substring(0, SPACER_DEFINITIONS_TOKEN.length()).equals(SPACER_DEFINITIONS_TOKEN)) {
                        spacerType = new HashMap<>();
                        properties = line.substring(SPACER_DEFINITIONS_TOKEN.length() + 1).split(PROPERTY_SEPARATOR);
                        for (String property : properties) {
                            String key = property.substring(0, property.indexOf(KEY_AND_VALUE_SEPARATOR));
                            String value = property.substring(property.indexOf(KEY_AND_VALUE_SEPARATOR) + 1);
                            spacerType.put(key, value);
                        }
                        spacerTypes.add(spacerType);
                    }
                }
            }
            return spacerTypes;
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
        List<Map<String, String>> blockTypes =
             BlockDefinitionsFileReader.getBlockTypes("block_def.txt");
        for (Map<String, String> blockType : blockTypes) {
            for (String key : blockType.keySet()) {
                System.out.println(key + ": " + blockType.get(key));
            }
            System.out.println();
        }
    }
}
