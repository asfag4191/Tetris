package no.uib.inf101.tetris.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import java.awt.event.ActionEvent;

import no.uib.inf101.tetris.midi.TetrisSong;
import no.uib.inf101.tetris.model.GameState;
import no.uib.inf101.tetris.view.TetrisView;

/**
 * The controller for the Tetris game. Handling user inputs through key events
 * and managing the timing for the moving terominos down the gameboard.
 */
public class TetrisController implements KeyListener {
    private final ControllableTetrisModel tetrisModel;
    private final TetrisView tetrisView;
    private final Timer timer;
    private TetrisSong tetrisSong;

    /**
     * Constructs the TetrisController
     * 
     * @param tetrisModel
     * @param tetrisView
     */

    public TetrisController(ControllableTetrisModel tetrisModel, TetrisView tetrisView) {
        this.tetrisModel = tetrisModel;
        this.tetrisView = tetrisView;
        tetrisView.addKeyListener(this);
        tetrisView.setFocusable(true);
        this.timer = new Timer(tetrisModel.dropTimer(), this::clockTick);
        this.timer.start();
        this.tetrisSong = new TetrisSong();
        tetrisSong.run();

    }

    /**
     * Handles key press events to control game actions, moving the tetromino
     * Starts a new game, or resets the game if it is over.
     * 
     * @param e The KeyEvent is triggered when the user presses a key.
     */
    public void keyPressed(KeyEvent e) {
        if (tetrisModel.getGameState() == GameState.GAME_OVER) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                tetrisModel.resetGame();
                tetrisModel.startGame();
                tetrisView.repaint();
            }
            return;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            tetrisModel.moveTetromino(0, -1);

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            tetrisModel.moveTetromino(0, 1);

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            tetrisModel.moveTetromino(1, 0);
            timer.restart();

        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            tetrisModel.rotatedTetromino();

        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (tetrisModel.getGameState() == GameState.ACTIVE_GAME) {
                tetrisModel.dropTetromino();
                if (true)
                    timer.restart();
            }
            if (tetrisModel.getGameState() == GameState.WELCOME_SCREEN) {
                tetrisModel.startGame();

            } 
        }

        tetrisView.repaint();
    }

    /**
     * Unused method from the KeyListener interface
     * @param e The KeyEvent is triggered when the user releases a key.
     */
    public void keyReleased(KeyEvent e) {
    }

    /**
     * Unused method from the KeyListener interface
     * @param e The KeyEvent is triggered when the user types a key.
     */
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Helper method to update the delay for the game's timer.
     * Delay based on the current game state.
     */
    public void updateTimerDelay() {
        int delay = tetrisModel.dropTimer(); 
        timer.setDelay(delay);
        timer.setInitialDelay(delay);
    }

    /**
     * The method is called every time the timer ticks. It updates the game state and
     * repaints the view.
     * 
     * @param e The ActionEvent is triggered when the timer ticks.
     */
    public void clockTick(ActionEvent e) {
        if (tetrisModel.getGameState() == GameState.ACTIVE_GAME) {
            tetrisModel.clockTick(); 
            tetrisView.repaint(); 
            updateTimerDelay(); 
        }

    }

}
