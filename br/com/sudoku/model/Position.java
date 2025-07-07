package br.com.sudoku.model;

public record Position(int row, int col) {

    public Position { // Validação dos parâmetros para classe Board
        if (row < 0 || row > 8) 
            throw new IllegalArgumentException("Linha deve ser entre 0 e 8.");
        if (col < 0 || col > 8) 
            throw new IllegalArgumentException("Coluna deve ser entre 0 e 8.");

}
}