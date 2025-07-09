package br.com.sudoku.gui;

public interface GameStatusListener {

    void onStatusChanged(String status); // escuta os eventos do jogo na classe service
}
