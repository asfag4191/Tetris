package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.controller.ControllableTetrisModel;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;

public class TetrisModel implements ViewableTetrisModel, ControllableTetrisModel {
    //referanser til objekt av 'TetrisBoard'
    private TetrisBoard tetrisBoard;
    private TetrominoFactory tetrominoFactory;
    private Tetromino currentTetromino;
    
    public TetrisModel(TetrisBoard tetrisBoard, TetrominoFactory tetrominoFactory) {
        if (tetrisBoard == null || tetrominoFactory == null) {
            throw new IllegalArgumentException("tetrisBoard and tetrominoFactory cannot be null");
        }

        this.tetrisBoard = tetrisBoard;
        this.tetrominoFactory = tetrominoFactory;
        this.currentTetromino = this.tetrominoFactory.getNext().shiftedToTopCenterOf(tetrisBoard);
    }

    private boolean legalMove(Tetromino tetromino) {
        for (GridCell<Character> cell : tetromino) {
            if (!tetrisBoard.get(cell.pos()).equals('-') || !tetrisBoard.positionIsOnGrid(cell.pos())) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public boolean moveTetromino(int deltaRow, int deltaCol) {
        Tetromino newFallingTetromino = this.currentTetromino.shiftedBy(deltaRow, deltaCol);
        if (!legalMove(newFallingTetromino)) {
            return false;
        }
        this.currentTetromino = newFallingTetromino;
        return true;
    }
    
    //gir tilgang til dimensjonene av "tetrisBoard" ved å kalle "getDimension" metoden
    @Override
    public GridDimension getDimension() {
        return this.tetrisBoard;
    }

    //gir tilgang til alle "tiles" på "tetrisBoard" ved å kalle "getTilesOnBoard" metoden
    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard(){
        return this.tetrisBoard;
    }
    @Override
    public Iterable<GridCell<Character>> getFallingTiles() {
        return currentTetromino;
    }
    
}
