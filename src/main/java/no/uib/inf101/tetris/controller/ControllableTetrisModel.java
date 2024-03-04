package no.uib.inf101.tetris.controller;

import no.uib.inf101.tetris.model.GameState;

/**
     * Move the falling tetromino in the specified direction.
     *
     * @param deltaRow the change in the row (vertical) direction
     * @param deltaCol the change in the column (horizontal) direction
     * @return true if the move was successful, false otherwise
     */

public interface ControllableTetrisModel {
  
    public boolean moveTetromino(int deltaRow, int deltaCol);

    public boolean rotatedTetromino();

    public void dropTetromino();

    public GameState getGameState();

    public int dropTimer();

    public void clockTick();


}


