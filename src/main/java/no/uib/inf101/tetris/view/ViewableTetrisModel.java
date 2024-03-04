package no.uib.inf101.tetris.view;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.GameState;
import no.uib.inf101.tetris.model.tetromino.RandomTetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;

public interface ViewableTetrisModel{ 

    
    GridDimension getDimension();
    
    Iterable<GridCell<Character>> getTilesOnBoard();

    Iterable<GridCell<Character>> getFallingTiles();

    GameState getGameState();
}



