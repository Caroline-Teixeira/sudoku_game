package br.com.sudoku.model;

public class Cell {
    
    private int value;  // 0 para vazio, 1-9 para preenchido
    private final boolean fixedByGame; // número iniciado pelo jogo

    public Cell(int value, boolean fixedByGame) {
        if (value < 0 || value > 9) {
            throw new IllegalArgumentException("Valor deve estar entre 0 e 9");
        }
        this.fixedByGame = fixedByGame;
        this.value = value; // Atribui o valor inicial diretamente, com validação prévia
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) { // Usado para alterações posteriores
        if (!fixedByGame && value >= 0 && value <= 9) {
            this.value = value;
        }
    }

    public boolean isFixedByGame() {
        return fixedByGame;
    }
}