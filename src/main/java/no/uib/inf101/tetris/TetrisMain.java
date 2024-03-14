package no.uib.inf101.tetris;

import javax.swing.JFrame;

import no.uib.inf101.tetris.controller.TetrisController;
import no.uib.inf101.tetris.model.TetrisBoard;
import no.uib.inf101.tetris.model.TetrisModel;
import no.uib.inf101.tetris.model.tetromino.RandomTetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.TetrisView;

/**
 * The main class for the Tetris game application.
 */
public class TetrisMain {

  /**
   * The title for the Tetris game window.
   */
  public static final String WINDOW_TITLE = "INF101 Tetris";

  /**
   * The main method to start the game.
   * 
   * @param args Command line arguments.
   */
  public static void main(String[] args) {
    TetrisBoard tetrisBoard = new TetrisBoard(15, 10);

    TetrominoFactory tetrominoFactory = new RandomTetrominoFactory();

    TetrisModel tetrisModel = new TetrisModel(tetrisBoard, tetrominoFactory);
    TetrisView view = new TetrisView(tetrisModel);
    TetrisController controller = new TetrisController(tetrisModel, view);

    JFrame frame = new JFrame(WINDOW_TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.setContentPane(view);

    frame.pack();
    frame.setVisible(true);
  }

  /**
   * Static instance for global use.
   * This factory is used to create random Tetrimino pieces.
   */
  static TetrominoFactory tetrominoFactory = new RandomTetrominoFactory();

}
