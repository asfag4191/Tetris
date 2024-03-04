package no.uib.inf101.tetris.controller;

import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import java.awt.event.ActionEvent;

import no.uib.inf101.tetris.midi.TetrisSong;
import no.uib.inf101.tetris.model.GameState;
import no.uib.inf101.tetris.view.TetrisView;


public class TetrisController implements KeyListener {
    private final ControllableTetrisModel tetrisModel;
    private final TetrisView tetrisView;
    private final Timer timer;
    private TetrisSong tetrisSong;


    /**
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
        this.tetrisSong= new TetrisSong();
        tetrisSong.run();

    }

    public void keyPressed(KeyEvent e) {
        if (tetrisModel.getGameState() == GameState.GAME_OVER) {
            return; // Returnerer tidlig hvis spillet er over
        }
        
        // Hvis spillet ikke er over, håndter tastetrykkene
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            tetrisModel.moveTetromino(0, -1);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            tetrisModel.moveTetromino(0, 1);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            tetrisModel.moveTetromino(1, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            tetrisModel.rotatedTetromino();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            tetrisModel.dropTetromino();
        }
        
        // Oppdaterer visningen
        tetrisView.repaint();
    }
    
public void keyReleased(KeyEvent e){
}
public void  keyTyped(KeyEvent e){
}

//hjelpemetode for å hente riktig delay, oppdatere 
public void updateTimerDelay(){
    int delay = tetrisModel.dropTimer(); // Antar at 'dropTimer()' returnerer ønsket delay
    timer.setDelay(delay);
    timer.setInitialDelay(delay);
}

public void clockTick(ActionEvent e){
    if (tetrisModel.getGameState() == GameState.ACTIVE_GAME) {
        tetrisModel.clockTick();          // Oppdaterer modellen
        tetrisView.repaint();             // Tegner visningen på nytt
        updateTimerDelay();      // Oppdaterer timer forsinkelsen, om nødvendig
    }

}




}



    




