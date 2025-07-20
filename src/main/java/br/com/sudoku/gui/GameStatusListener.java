package br.com.sudoku.gui;

import br.com.sudoku.core.DifficultyLevel;

public interface GameStatusListener {

    void onStatusChanged(String status, DifficultyLevel difficultyLevel); 
    // escuta os eventos do jogo na classe service (Oberver pattern)
}
