package no.uib.inf101.tetris.controller;

import no.uib.inf101.tetris.model.GameState;

/**
 * The interface defines the methods that the controller
 * components of the Tetris game needs in order to control the current state of
 * the game.
 * Managing moving, rotating, dropping the tetrominos and
 * the game timing and controlling the game state.
 */
public interface ControllableTetrisModel {

    /**
     * Move the falling tetromino in the specified direction.
     * 
     * @param deltaRow The number of rows to move the tetromino down.
     *                 Positive value moves it down, negative value moves it up.
     * @param deltaCol The number of columns to move the Tetromino to the right.
     *                 Positive value moves it to the right, negative value moves it
     *                 to the left.
     * @return true if the tetromino was moved, false if it could not be moved.
     */
    public boolean moveTetromino(int deltaRow, int deltaCol);

    /**
     * Rotates the falling tetromino 90 degrees clockwise.
     * 
     * @return true if the rotation was successful, false otherwise
     */
    public boolean rotatedTetromino();

    /**
     * Drops the currently falling Tetromino straight down to the lowest
     * possible position where it can land.
     */
    public void dropTetromino();

    /**
     * Provides the interval in milliseconds at which the falling Tetromino
     * should automatic move one row down.
     * 
     * @return The drop timer interval in milliseconds.
     */
    public int dropTimer();

    /**
     * Advances the internal clock by one tick.
     */
    public void clockTick();

    /**
     * Retrieves the current state of the game.
     * 
     * @return The current Game state. Indicating if it's over, active or in
     *         another state.
     */
    public GameState getGameState();

    /**
     * Starts a new game.
     */
    public void startGame();

    /**
     * Resets the game to its initial state.
     * Preperation for a new game to start.
     */
    public void resetGame();

}
