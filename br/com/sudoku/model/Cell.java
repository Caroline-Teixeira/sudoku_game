package br.com.sudoku.model;

public class Cell {
    
 
    private final int row;
    private final int col;
    private int value;
    private final boolean isFixedByGame;

    public Cell(int row, int col, int value, boolean isFixedByGame) {
        if (row < 0 || row > 8) {
            throw new IllegalArgumentException("Linha deve ser entre 0 e 8.");
        }
        if (col < 0 || col > 8) {
            throw new IllegalArgumentException("Coluna deve ser entre 0 e 8.");
        }
        if (value < 0 || value != 0 && value > 9) { // Aceita 0 (vazio) ou 1 a 9
            throw new IllegalArgumentException("O valor deve ser 0 (vazio) ou entre 1 e 9.");
        }
        this.row = row;
        this.col = col;
        this.value = value;
        this.isFixedByGame = isFixedByGame;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getValue() {
        return value;
    }

    public boolean isFixedByGame() {
        return isFixedByGame;
    }

    public void setValue(int value) { // Usado para alterações posteriores
        if (!isFixedByGame && (value == 0 || value >= 1 && value <= 9)) {
            this.value = value;
        }
}
}