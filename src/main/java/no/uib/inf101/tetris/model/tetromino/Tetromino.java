package no.uib.inf101.tetris.model.tetromino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

public final class Tetromino implements Iterable<GridCell<Character>> {

    public static void main(String[] args) {
        //Tetromino t = newTetromino('I');
        
    }
    private final char symbol;
    private final boolean[][] shape;
    private final CellPosition position;

    public Tetromino(char symbol, boolean[][] shape, CellPosition position){
        this.symbol = symbol;
        this.shape = shape;
        this.position = position;
    }

    //pakke privat
    static Tetromino newTetromino (char symbol){
        boolean[][] shape = switch (symbol) {
            case 'I' -> new boolean[][] {
                {false, false, false, false},
                {true,  true,  true,  true },
                {false, false, false, false},
                {false, false, false, false}    
            };
            case 'O' -> new boolean[][] {

                {false,false,false,false},
                {false,true, true, false},
                {false,true,true,false},
                {false,false,false,false}
            };
            case 'T' -> new boolean[][] {

                { false, false ,false},
                { true, true, true },
                { false,true, false }
            };
            case 'S' -> new boolean[][] {

                {false,false,false},
                { false, true,true },
                { true, true,false },
            };
            case 'Z' -> new boolean[][] {


                { false,  false, false },
                { true, true, false },
                { false, true, true }
            };
            case 'J' -> new boolean[][] {

                { false, false, false },
                { true,  true,  true },
                { false, false, true }
            };
            case 'L' -> new boolean[][] {

                { false, false, false },
                { true,  true,  true },
                { true, false, false }
            };
            default -> throw new IllegalArgumentException("Invalid symbol");
        };  
        CellPosition defaultPosition = new CellPosition(0, 0);
        Tetromino tetromino = switch (symbol) {
            case 'I' -> new Tetromino(symbol, shape, defaultPosition);
            case 'O' -> new Tetromino(symbol, shape, defaultPosition);
            case 'T' -> new Tetromino(symbol, shape, defaultPosition);
            case 'S' -> new Tetromino(symbol, shape, defaultPosition);
            case 'Z' -> new Tetromino(symbol, shape, defaultPosition);
            case 'J' -> new Tetromino(symbol, shape, defaultPosition);
            case 'L' -> new Tetromino(symbol, shape, defaultPosition);
            default -> throw new IllegalArgumentException("Invalid symbol");
        };
        return tetromino;
    }
    public Tetromino shiftedBy(int deltaRow, int deltaCol){
        CellPosition newPosition = new CellPosition(position.row() + deltaRow, position.col() + deltaCol);

        return new Tetromino(this.symbol, this.shape, newPosition);
    }

    public Tetromino shiftedToTopCenterOf(GridDimension gridDimension) {
        int deltaRow=-1;
        // midtposisjonen 
        int adjustedCol = (gridDimension.cols()-shape[0].length) / 2;

        // ny Cellposition med rad 0 og justert kolonneposisjon
        CellPosition newPosition = new CellPosition(deltaRow, adjustedCol);  // Corrected this line
        // returnerer med ny Cellposition
        return new Tetromino(symbol, shape, newPosition);
    }
    public Tetromino rotatedTetromino(){
        boolean [][] originalShape = shape; 
        int rows = originalShape.length;
        int cols = originalShape[0].length;

        boolean[][] newShape = new boolean[cols][rows];
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int rotatedRow = col;
                int rotatedCol = rows -1 - row;
                newShape[rotatedRow][rotatedCol] = originalShape[row][col];
            }
        }
        return new Tetromino(symbol, newShape, position);
    }

    @Override
    public Iterator<GridCell<Character>> iterator() {
        ArrayList<GridCell<Character>> listCell = new ArrayList<>();
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                //utført handlinger hvis True
                if (shape[row][col]) {
                    int realRow = position.row() + row;
                    int realCol = position.col() + col;
                    CellPosition realPosition = new CellPosition(realRow, realCol);
                    GridCell<Character> gridCell = new GridCell<>(realPosition, symbol);
                    listCell.add(gridCell);
                }
            }
        }
        return listCell.iterator();
    }
    @Override
    public int hashCode() {
        return Objects.hash(symbol, Arrays.deepHashCode(shape), position);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Tetromino tetromino = (Tetromino) object;
        return symbol == tetromino.symbol &&
                Arrays.deepEquals(shape, tetromino.shape) &&
                Objects.equals(position, tetromino.position);
    }

    




}


        
