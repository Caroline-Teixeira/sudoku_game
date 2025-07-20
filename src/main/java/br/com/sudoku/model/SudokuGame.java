package br.com.sudoku.model;

public class SudokuGame {

  private final Board board; 
  private GameStatus status;

  
  public SudokuGame() {
    this.board = new Board();
    this.status = GameStatus.NAO_INICIADO;
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
