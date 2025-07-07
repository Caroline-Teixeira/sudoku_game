package br.com.sudoku.model;

public class InvalidMoveException extends RuntimeException{

    public InvalidMoveException(String message) {
        super(message);
        // Log para movimentos inválidos

}
}