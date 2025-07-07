package br.com.sudoku.model;

public class SudokuGame {

    private final Board board; // Tabuleiro do jogo
    private GameStatus status; 

    // Construtor padrão que inicializa o tabuleiro
    public SudokuGame() {
        this.board = new Board();
        this.status = GameStatus.NOT_STARTED;
    }

    public Board getBoard() {
        return board;
    }

    public GameStatus getStatus() {
        return status;
    }

    // Atualiza o status do jogo
    public void setStatus(GameStatus status) {
        this.status = status;
    }




}
