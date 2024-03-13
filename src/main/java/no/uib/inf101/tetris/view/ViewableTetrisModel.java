package no.uib.inf101.tetris.view;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.GameState;

/**
 * The interface defines the methods that the view
 * components of the Tetris game needs in order to display the current state of
 * the game.
 */

public interface ViewableTetrisModel {

    /**
     * @return the dimension of the game board.
     */
    GridDimension getDimension();

    /**
     * @return the tiles on the game board.
     */
    Iterable<GridCell<Character>> getTilesOnBoard();

    /**
     * @return the tiles of the falling tetromino.
     */
    Iterable<GridCell<Character>> getFallingTiles();

    /**
     * @return the current game state.
     */
    GameState getGameState();

    /**
     * @return the current score, from removing full rows. 
     */
    int getscore();

}
