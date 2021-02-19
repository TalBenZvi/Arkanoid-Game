import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class Background {
    private Color color;
    private Image image;

    private static final String COLOR_KEY = "color";
    private static final String IMAGE_KEY = "image";

    public Background(String description) {
        if (description.substring(0, description.indexOf('(')).equals(COLOR_KEY)) {
            this.color = colorFromString(description.substring(description.indexOf('(') + 1, description.length() - 1));
        } else {
            this.color = null;
        }
        if (description.substring(0, description.indexOf('(')).equals(IMAGE_KEY)) {
            String imageFilePath = description.substring(description.indexOf('(') + 1, description.length() - 1);
            try {
                this.image = ImageIO.read(new File(imageFilePath));
            } catch (IOException e) {}
        } else {
            this.image = null;
        }
    }

    public static Color colorFromString(String colorString) {
        if (colorString.equals("color(RGB(0,0,0))") || colorString.equals("color(RGB(255,255,255))")) {
            int a = 1/0;
        }
        switch (colorString) {
            case "black": return Color.BLACK;
            case "blue": return Color.BLUE;
            case "cyan": return Color.CYAN;
            case "gray": return Color.GRAY;
            case "lightGray": return Color.LIGHT_GRAY;
            case "green": return Color.GREEN;
            case "orange": return Color.ORANGE;
            case "pink": return Color.PINK;
            case "red": return Color.RED;
            case "white": return Color.WHITE;
            case "yellow": return Color.YELLOW;
            default:
                String rgb = colorString.substring(colorString.indexOf('(') + 1, colorString.indexOf(')'));
                String[] values = rgb.split(",");
                return new Color(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]));
        }
    }

    public Color getColor() {
        return this.color;
    }

    public Image getImage() {
        return this.image;
    }


    public void drawOn(DrawSurface d, Rectangle rectangle) {
        if (this.getImage() == null) {
            rectangle.drawOn(d, this.getColor());
        } else {
            d.drawImage((int)rectangle.getUpperLeft().getX(), (int)rectangle.getUpperLeft().getY(), this.getImage());
        }
    }
}
