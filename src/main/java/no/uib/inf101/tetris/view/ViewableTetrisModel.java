package no.uib.inf101.tetris.view;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.GameState;

/**
 * The interface defines the methods that the view
 * components of the Tetris game needs in order to display the current state of the game.
 */

public interface ViewableTetrisModel{ 

    GridDimension getDimension();
    
    Iterable<GridCell<Character>> getTilesOnBoard();

    Iterable<GridCell<Character>> getFallingTiles();

    GameState getGameState();

    int getscore();

    //boolean rotatedTetromino();



}



