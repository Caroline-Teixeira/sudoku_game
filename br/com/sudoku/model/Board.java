package br.com.sudoku.model;

import java.util.HashMap;

import java.util.Map;


public class Board {

    public static final int BOARD_SIZE = 81; // 9x9 = 81 células
    private final Map<Position, Cell> cells = new HashMap<>(); // chave (linha, coluna) e valor (célula)

    public Map<Position, Cell> getCells() {
        return cells; // para somente a classe BoardService acessar
    }

    

    
}
