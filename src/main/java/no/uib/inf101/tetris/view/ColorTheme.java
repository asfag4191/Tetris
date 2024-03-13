package no.uib.inf101.tetris.view;

import java.awt.Color;

/**
 * Interface for color themes.
 */
public interface ColorTheme {

    /**
     * Gets the color associated with the given cell content character.
     * 
     * @param Cellcontent The character representing the cell content.
     * @return The color for the cell.
     */
    Color getCellColor(char Cellcontent);

    /**
     * Returns the color of the frame.
     * 
     * @return the color of the frame.
     */
    Color getFrameColor();

    /**
     * Returns the backgorund color.
     * 
     * @return the backgorund color .
     */
    Color getBackgroundColor();
}