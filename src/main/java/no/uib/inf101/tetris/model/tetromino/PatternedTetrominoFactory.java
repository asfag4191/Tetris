package no.uib.inf101.tetris.model.tetromino;

/**
 * Implements TetrominoFactory that generates Tetrominos based on a pattern.
 * 
 */
public class PatternedTetrominoFactory implements TetrominoFactory {
    private String pattern;
    private int currentIndex;

    /**
     * Creates a new PatternedTetrominoFactory with a specified pattern.
     * It will create Tetrominos based on the pattern, in order.
     * 
     * @param pattern A string representated the sequence of Tetrominos to be
     *                generated.
     *                Each character in the string needs to be a valid Tetromino
     *                symbol.
     */
    public PatternedTetrominoFactory(String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            throw new IllegalArgumentException("Pattern cannot be null or empty");
        }
        this.pattern = pattern;
        this.currentIndex = 0;
    }

    @Override
    public Tetromino getNext() {
        char symbol = pattern.charAt(currentIndex);
        currentIndex = (currentIndex + 1) % pattern.length();
        return Tetromino.newTetromino(symbol);
    }
}
