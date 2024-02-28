package no.uib.inf101.tetris.model.tetromino;

public class PatternedTetrominoFactory implements TetrominoFactory {
    private String pattern;
    private int currentIndex;

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

