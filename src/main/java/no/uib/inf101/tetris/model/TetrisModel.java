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
 * tetrominos. It is also responsible for checking if the tetrominos can move
 * and rotate, and for attaching them to the board when they can't move anymore.
 * 
 * The class implements the ViewableTetrisModel and ControllableTetrisModel,
 * which means that it can be used as a model in the MVC pattern.
 * 
 * The class also has a method called "clockTick" which is called by the
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
     * 
     * 
     * @param tetrisBoard
     * @param tetrominoFactory
     */
    public TetrisModel(TetrisBoard tetrisBoard, TetrominoFactory tetrominoFactory) {
        if (tetrisBoard == null || tetrominoFactory == null) {
            throw new IllegalArgumentException("tetrisBoard and tetrominoFactory cannot be null");
        }

        this.tetrisBoard = tetrisBoard;
        this.tetrominoFactory = tetrominoFactory;
        this.currentTetromino = this.tetrominoFactory.getNext().shiftedToTopCenterOf(tetrisBoard);
        this.gameState = GameState.ACTIVE_GAME;
        this.gameState=GameState.WELCOME_SCREEN;
    }

    /**
     * 
     * @param tetromino
     * @return
     */
    private boolean legalMove(Tetromino tetromino) {
        List<GridCell<Character>> cells = new ArrayList<>();
        for (GridCell<Character> cell : tetromino) {
            cells.add(cell);
        }

        for (int i = 0; i < cells.size(); i++) {
            CellPosition pos = cells.get(i).pos();
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

    // gir tilgang til dimensjonene av "tetrisBoard" ved å kalle "getDimension"
    // metoden
    @Override
    public GridDimension getDimension() {
        return this.tetrisBoard;
    }

    // gir tilgang til alle "tiles" på "tetrisBoard" ved å kalle "getTilesOnBoard"
    // metoden
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

        // Check if the rotated tetromino is a legal move
        if (!legalMove(newrotatedTetromino)) {
            return false;
        }
        this.currentTetromino = newrotatedTetromino;
        return true; // Rotation was successful
    }

    // public void newFallingTetromino(){
    // this.currentTetromino =
    // this.tetrominoFactory.getNext().shiftedToTopCenterOf(tetrisBoard);
    // }

    @Override
    public void dropTetromino() {
        while (moveTetromino(1, 0)) {
            // Beveger tetrominoen nedover til den ikke kan flyttes lenger
        }
        attachTetrominoToBoard(); // Fester tetrominoen til brettet
        fetchNewFallingTetromino(); // Henter en ny tetromino
    }

    private void attachTetrominoToBoard() {
        for (GridCell<Character> cell : currentTetromino) {
            tetrisBoard.set(cell.pos(), cell.value());
        }
        int rowsRemoved = tetrisBoard.removeFullRows();
        newscore = updatedScore(rowsRemoved, newscore);
        // Her kan du implementere sjekk for fullførte linjer og fjerne dem
    }

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
            // Hvis brikken ikke fikk lov til å flytte seg, fest den til brettet
            attachTetrominoToBoard();
            fetchNewFallingTetromino();
        }
    }

    /**
     * @param linesRemoved
     * @param score
     * @return
     */
    public int updatedScore(int linesRemoved, int score) {
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
    public void startGame(){
        newscore=0;
        currentTetromino = this.tetrominoFactory.getNext().shiftedToTopCenterOf(tetrisBoard);
        gameState = GameState.ACTIVE_GAME;
        for(int row=0;row<tetrisBoard.rows();row++){
            tetrisBoard.setRow(row,'-');
        }}
        

    public void welcomeScreen(){
        gameState = GameState.WELCOME_SCREEN;
    }

    @Override
    public void resetGame() {
        // Start ved å nullstille brettet
        for (int row = 0; row < tetrisBoard.rows(); row++) {
            tetrisBoard.setRow(row, '-');
        }
        // Nullstill poengsum og annen spilltilstand
        newscore = 0;
        // Hente en ny falling tetromino og sentrere den på toppen av brettet
        currentTetromino = tetrominoFactory.getNext().shiftedToTopCenterOf(tetrisBoard);
        
        // Sett spilltilstanden til ACTIVE_GAME eller WELCOME_SCREEN, avhengig av hvordan du vil håndtere restart
        gameState = GameState.ACTIVE_GAME;
    }
      



    @Override
    public int getscore() {
        return newscore;
    }

}
