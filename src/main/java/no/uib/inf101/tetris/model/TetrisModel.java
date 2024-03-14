package no.uib.inf101.tetris.model;

import java.util.ArrayList;
import java.util.List;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.controller.ControllableTetrisModel;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;

/**
 * The TetrisModel class is responsible for managing the game state and the
 * tetrominos. Responsible for movement, rotation, and placement of the
 * tetrominos on the board, and attaching them to the board when they can't move
 * anymore.
 * 
 * The class implements the ViewableTetrisModel and ControllableTetrisModel,
 * which means that it can be used as a model in the MVC pattern.
 * 
 * The method called "clockTick" which is called by the
 * controller at regular intervals. This method is responsible for moving the
 * tetrominos down, and for attaching them to the board when they can't move
 * anymore.
 */

public class TetrisModel implements ViewableTetrisModel, ControllableTetrisModel {
    private TetrisBoard tetrisBoard;
    private TetrominoFactory tetrominoFactory;
    private Tetromino currentTetromino;
    private GameState gameState = GameState.ACTIVE_GAME;
    private int newscore = 0;

    /**
     * Initializes a new TetrisModel with the given game board and tetromino
     * factory.
     * Checks if the given game board and tetromino factory are null.
     * 
     * @param tetrisBoard      The game board.
     * @param tetrominoFactory The factory for generating tetrominos.
     */
    public TetrisModel(TetrisBoard tetrisBoard, TetrominoFactory tetrominoFactory) {
        if (tetrisBoard == null || tetrominoFactory == null) {
            throw new IllegalArgumentException("tetrisBoard and tetrominoFactory cannot be null");
        }

        this.tetrisBoard = tetrisBoard;
        this.tetrominoFactory = tetrominoFactory;
        this.currentTetromino = this.tetrominoFactory.getNext().shiftedToTopCenterOf(tetrisBoard);
        this.gameState = GameState.ACTIVE_GAME;
        this.gameState = GameState.WELCOME_SCREEN;
    }

    /**
     * Determine if the specified tetromino can be legally moved to the new
     * position.
     * 
     * @param tetromino The tetromino to check.
     * @return True if the move is legal, false otherwise.
     */
    // got helt from Gedvyde from my class, and then modified it to fit my code.
    private boolean legalMove(Tetromino tetromino) {
        List<GridCell<Character>> cells = new ArrayList<>();
        for (GridCell<Character> cell : tetromino) {
            cells.add(cell);
        }

        for (int row = 0; row < cells.size(); row++) {
            CellPosition pos = cells.get(row).pos();
            if (!tetrisBoard.positionIsOnGrid(pos)) {
                return false;
            } else if (tetrisBoard.get(pos) != '-') {
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

    @Override
    public GridDimension getDimension() {
        return this.tetrisBoard;
    }

    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard() {
        return this.tetrisBoard;
    }

    @Override
    public Iterable<GridCell<Character>> getFallingTiles() {
        return currentTetromino;
    }

    @Override
    public boolean rotatedTetromino() {
        Tetromino newrotatedTetromino = this.currentTetromino.rotatedTetromino();
        if (!legalMove(newrotatedTetromino)) {
            return false;
        }
        this.currentTetromino = newrotatedTetromino;
        return true;
    }

    @Override
    public void dropTetromino() {
        while (moveTetromino(1, 0)) {
        }
        attachTetrominoToBoard();
        fetchNewFallingTetromino();
    }

    /**
     * Attach the current tetromino to the board, it's called when it can't
     * be moved down anymore.
     * 
     * Iterates through the cells of the tetromino and sets the value of the
     * corresponding cell on the board to the value of the tetromino cell.
     * 
     * After attaching, it removes any full rows and updates the score.
     * Helping method to dropTetromino.
     */
    private void attachTetrominoToBoard() {
        for (GridCell<Character> cell : currentTetromino) {
            tetrisBoard.set(cell.pos(), cell.value());
        }
        int rowsRemoved = tetrisBoard.removeFullRows();
        newscore = updatedScore(rowsRemoved, newscore);
    }

    /**
     * Fetches a new falling tetromino from the tetromino factory and sets it as
     * the current tetromino. If the new tetromino can't be placed on the board,
     * the game state is set to GAME_OVER.
     * Helping method to dropTetromino.
     */
    private void fetchNewFallingTetromino() {
        Tetromino newTetromino = tetrominoFactory.getNext().shiftedToTopCenterOf(tetrisBoard);
        this.currentTetromino = newTetromino;
        if (!legalMove(newTetromino)) {
            gameState = GameState.GAME_OVER; // Setter spilltilstanden til GAME_OVER om nødvendig
        } else {
            this.currentTetromino = newTetromino;
        }
    }

    @Override
    public GameState getGameState() {
        return gameState;

    }

    @Override
    public int dropTimer() {
        return 1000;
    }

    @Override
    public void clockTick() {
        boolean moved = moveTetromino(1, 0);
        if (!moved) {
            attachTetrominoToBoard();
            fetchNewFallingTetromino();
        }
    }

    /**
     * Updates the score based on the number of lines removed.
     * 
     * @param linesRemoved The number of lines removed.
     * @param score        The current score.
     * @return The updated score.
     */
    private int updatedScore(int linesRemoved, int score) {
        switch (linesRemoved) {
            case 1:
                score += 100;
                break;
            case 2:
                score += 300;
                break;
            case 3:
                score += 500;
                break;
            case 4:
                score += 800;
                break;
        }
        return score;
    }

    @Override
    public void startGame() {
        newscore = 0;
        currentTetromino = this.tetrominoFactory.getNext().shiftedToTopCenterOf(tetrisBoard);
        gameState = GameState.ACTIVE_GAME;
        for (int row = 0; row < tetrisBoard.rows(); row++) {
            tetrisBoard.setRow(row, '-');
        }
    }

    /**
     * Sets the Game State to WELCOME_SCREEN
     */
    public void welcomeScreen() {
        gameState = GameState.WELCOME_SCREEN;
    }

    @Override
    public void resetGame() {
        // clears the board by setting all cells to '-'.
        for (int row = 0; row < tetrisBoard.rows(); row++) {
            tetrisBoard.setRow(row, '-');
        }
        newscore = 0;
        currentTetromino = tetrominoFactory.getNext().shiftedToTopCenterOf(tetrisBoard);
        gameState = GameState.ACTIVE_GAME;
    }

    @Override
    public int getscore() {
        return newscore;
    }

}
