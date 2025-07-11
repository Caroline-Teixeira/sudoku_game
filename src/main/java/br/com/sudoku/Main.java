package br.com.sudoku;

import br.com.sudoku.view.Menu;

public class Main {

    // Jogo no terminal
    public static void main(String[] args) {
        
        System.out.println("Bem-vindo ao Sudoku!");
        Menu game = new Menu();
        game.start();
    }
	
    
    
}