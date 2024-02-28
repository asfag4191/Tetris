package no.uib.inf101.tetris.model.tetromino;

import java.util.Random;

public class RandomTetrominoFactory implements TetrominoFactory {

    private final Random random;

    public RandomTetrominoFactory() {
        this.random = new Random();
    }

    @Override
    public Tetromino getNext(){
        char randomSymbol= switch (random.nextInt(7)) {
            case 0 -> 'L';
            case 1 -> 'J';
            case 2 -> 'S';
            case 3 -> 'Z';
            case 4 -> 'T';
            case 5 -> 'I';
            case 6 -> 'O';
            default -> throw new IllegalArgumentException("Invalid symbol");
        };
        return Tetromino.newTetromino(randomSymbol);


    }
    }
    
