package no.uib.inf101.tetris.view;

import java.awt.Color;

/**
 * Interface for color themes.
 */
public interface ColorTheme {
    //Må ha et navn på paramteren for å kunne bruke den i metoden
        Color getCellColor(char Cellcontent);
    
        Color getFrameColor();
    
        Color getBackgroundColor();
    }