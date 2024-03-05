package no.uib.inf101.tetris.model;


import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.tetromino.Tetromino;

public class TetrisBoard extends Grid<Character> {

    //private ArrayList<ArrayList<Character>> grid;
    //private int cols;


    public TetrisBoard(int rows, int cols) {
        super(rows, cols, '-');
 
    }

    public String prettyString() {
        StringBuilder result = new StringBuilder();
    
        for (int row = 0; row < rows(); row++) {
            for (int col = 0; col < cols(); col++) {
                result.append(get(new CellPosition(row, col)));
            }
            result.append("\n");
        }
    
        return result.toString().trim();
    }

    // Metode som sjekker om et element eksisterer i en rad på brettet
    public boolean rowContains(int row, char element) {
        for (int col = 0; col < cols(); col++) {
            if (get(new CellPosition(row, col)) == element) {
                return true;
            }
        }
        return false;
    }
    
    // Metode som setter alle rutene i en rad til en gitt verdi
    public void setRow(int row, char value) {
        for (int col = 0; col < cols(); col++) {
            set(new CellPosition(row, col), value);
        }
    }
    
    public void copyRowInto(int sourceRow, int destinationRow) {
        for (int col = 0; col < cols(); col++) {
            set(new CellPosition(destinationRow, col), get(new CellPosition(sourceRow, col)));
        }
    }
    // Metode som kopierer alle verdiene fra én rad inn i en annen
    public int removeFullRows() {
        int removedRowsCount = 0; // Tellevariabel for antall rader som blir forkastet
        int a = rows() - 1; // a starter ved nederste rad på brettet
        int b = rows() - 1; // b starter også ved nederste rad på brettet
    
        // Så lenge a er en rad på brettet
        while (a >= 0) {
            // Så lenge b er en rad på brettet og raden b ikke inneholder en blank rute
            while (b >= 0 && !rowContains(b, '-')) {
                // Teller opp at denne rekken ble forkastet
                removedRowsCount++;
                // La b gå til neste rad
                b--;
            }
    
            // Hvis b fremdeles er på brettet
            if (b >= 0 && b<rows()) {
                // Kopier rekken b står ved inn i rekken a står ved
                copyRowInto(b, a);
            } else {
                // Fyll rekken a står ved med blanke ruter
                setRow(a, '-');
            }
    
            // La a og b gå til neste rekke (oppover på brettet)
            a--;
            
            b--;
        }
        // Returnerer antall rader som ble forkastet
        return removedRowsCount;
    }
    
    
    public static TetrisBoard getTetrisBoardWithContents(String[] contents) {
        int rows = contents.length;
        int cols = contents[0].length();
        TetrisBoard board = new TetrisBoard(rows, cols);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                board.set(new CellPosition(row, col), contents[row].charAt(col));
            }
        }
        return board;
    }
}
    

