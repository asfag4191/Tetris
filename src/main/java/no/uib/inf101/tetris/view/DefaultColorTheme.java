package no.uib.inf101.tetris.view;

import java.awt.Color;

/**
 * Default color theme for the Tetris game.
 */
public class DefaultColorTheme implements ColorTheme{

    @Override
    public Color getCellColor(char Cellcontent) {
        Color color = switch (Cellcontent) {
                case 'r' -> Color.RED;
                case 'g' -> Color.GREEN;
                case 'y' -> Color.YELLOW;
                case 'b' -> Color.BLUE;
                case '-' -> Color.BLACK;
                case 'L' -> Color.ORANGE;
                case 'I' -> Color.CYAN;
                case 'O' -> Color.YELLOW;
                case 'T' -> Color.MAGENTA;
                case 'S' -> Color.GREEN;
                case 'Z' -> Color.RED;
                case 'J' -> Color.BLUE;
                



                default -> throw new IllegalArgumentException(
                    "No available color for '" + Cellcontent + "'");
    };
    return color;
    }

    @Override
    public Color getFrameColor() {
        return new Color(0, 0, 0, 0);
    }

    @Override
    public Color getBackgroundColor() {
        return null;
    }
    
}
