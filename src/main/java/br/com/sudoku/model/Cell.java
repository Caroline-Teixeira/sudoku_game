package br.com.sudoku.model;

public class Cell {
    
 
    private final int row;
    private final int col;
    private int value;
    private final boolean isFixedByGame;

    public Cell(int row, int col, int value, boolean isFixedByGame) {
        validateValue(value); // método de validar o valor antes de atribuí-lo
        this.row = row;
        this.col = col;
        this.value = value;
        this.isFixedByGame = isFixedByGame;
    }

    //Getters
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

    // Método para definir o valor da célula
    public static void validateValue(int value) {
        if (value < 0 || value > 9) {
            throw new InvalidMoveException("Valor deve ser entre 0 e 9.");
        }
    }

    // Setter: jogo em andamento
    public void setValue(int value) {
    if (isFixedByGame) {
        throw new InvalidMoveException("Não é possível alterar o valor de uma célula fixa.");

    }
    validateValue(value); // explicação: valida o valor antes de atribuí-lo
    this.value = value; // atribui o valor à célula
}


    
}
