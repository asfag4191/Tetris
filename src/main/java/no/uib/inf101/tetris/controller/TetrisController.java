package no.uib.inf101.tetris.controller;

import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import no.uib.inf101.tetris.view.TetrisView;


public class TetrisController implements KeyListener {
    private final ControllableTetrisModel tetrisModel;
    private final TetrisView tetrisView;


    /**
     * @param tetrisModel
     * @param tetrisView
     */
    
    public TetrisController(ControllableTetrisModel tetrisModel, TetrisView tetrisView) {
        this.tetrisModel = tetrisModel;
        this.tetrisView = tetrisView;
        tetrisView.addKeyListener(this);
        tetrisView.setFocusable(true);
    }

    public void  keyTyped(KeyEvent e){
}
    public void keyPressed(KeyEvent e){
        //int keyEvent = e.getKeyCode();
        // Left arrow was pressed
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            tetrisModel.moveTetromino(0, -1);
        }
        // Right arrow was pressed
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            tetrisModel.moveTetromino(0, 1);
        }
        // Down arrow was pressed
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            tetrisModel.moveTetromino(1, 0);
        }
        // Up arrow was pressed
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            //tetrisModel.rotateTetromino();
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            //tetrisModel.moveTetromino(0, 0);, droppmetode skal lages
        }
        //updates the view
        tetrisView.repaint();
    }

    public void keyReleased(KeyEvent e){
}

    }

    




