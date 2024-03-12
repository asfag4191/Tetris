package no.uib.inf101.tetris.model.tetromino;

/**
 * A factory for creating tetrominos.
 * It's responsible for generating Tetrominos for use in a Tetris game.
 * The factory can be used to generate Tetrominos in a random manner, or in a
 * predefined pattern.
 */
public interface TetrominoFactory {
    /**
     * Generates the next Tetromino to be used in the game.
     * It could be generated in a random manner, or in a predefined pattern.
     * 
     * @return The next tetromino to be used in the game.
     */

    Tetromino getNext();
}
