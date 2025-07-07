package br.com.sudoku.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import br.com.sudoku.util.BoardTemplate;

public class Board {

    private final Map<Position, Cell> cells = new HashMap<>(); // chave (linha, coluna) e valor (célula)


    // Optional para evitar NullPointerException
    public Optional<Cell> findCell(int row, int col) {
        return Optional.ofNullable(cells.get(new Position(row, col)));
}

    // ----MÉTODOS------
    // configuração inicial do tabuleiro
    public void loadInitialSetup(List<Cell> fixedCells) {
        resetBoard();
        fixedCells.forEach(cell -> {
            Position position = new Position(cell.getRow(), cell.getCol()); // Classe record: cria uma instância de Position
            if (cells.containsKey(position))
                throw new InvalidMoveException("Posição inicial duplicada em " + position);
            cells.put(position, cell);
        });
    }

    // método para adicionar uma célula ao tabuleiro
    public void addCell(Cell cell) {
        if (cell.getValue() < 1 || cell.getValue() > 9)
            throw new InvalidMoveException("Número inválido. Deve ser de 1 a 9.");

        Position position = new Position(cell.getRow(), cell.getCol());
        Cell existingCell = cells.get(position); // verifica se já existe uma célula na posição

        if (existingCell != null && existingCell.isFixedByGame())
            throw new InvalidMoveException("Não é possível sobrescrever um número fixo.");

        cells.put(position, cell);
    }

    // método para remover uma célula do tabuleiro
    public void removeCellAt(int row, int col) {
        Position position = new Position(row, col);
        Cell cell = cells.get(position);

        if (cell == null)
            throw new InvalidMoveException("Não há número nesta posição.");
        if (cell.isFixedByGame())
            throw new InvalidMoveException("Número fixo não pode ser removido.");

        cells.remove(position);
    }

    // método para remover entradas do usuário
    public void clearUserInputs() {
        cells.values().removeIf(cell -> !cell.isFixedByGame()); 
    }


    private void resetBoard() { 
        cells.clear(); // limpa o tabuleiro, (configuração inicial, apenas para esta classe)
    }

    // Métodos para verificar conflitos
    private boolean isConflictingWithOthers(Cell currentCell, List<Cell> otherCells) {
    return otherCells.stream()
            .filter(otherCell -> currentCell != otherCell && currentCell.getValue() == otherCell.getValue()) // Não compara com a própria célula, e considera apenas células com o mesmo valor
            .anyMatch(otherCell ->
                    currentCell.getRow() == otherCell.getRow() || // mesma linha
                    currentCell.getCol() == otherCell.getCol() ||   // mesma coluna
                    (currentCell.getRow() / 3 == otherCell.getRow() / 3 && // mesmo bloco 3x3
                     currentCell.getCol() / 3 == otherCell.getCol() / 3)
            ); // divisão por 3 
    }

    public boolean hasConflict() {
        List<Cell> otherCells = cells.values().stream()
                .filter(cell -> cell.getValue() != 0)
                .toList();

        return otherCells.stream()
                .anyMatch(currentCell -> isConflictingWithOthers(currentCell, otherCells)); 
}

    // Método para imprimir
    public void printBoard(){
        String[] values = java.util.stream.IntStream.range(0,81) // IntStream de 0 a 80 (9x9)
            .mapToObj(i -> {
                int row = i / 9; // Calcula a linha (0 a 8)
                int col = i % 9; // Calcula a coluna (0 a 8); % para reiniciar a contagem de colunas após 9 
                Optional<Cell> cell = findCell(row, col); // Busca a célula na posição (linha, coluna)
                int value = cell.isPresent() ? cell.get().getValue() : 0; // Se a célula existir, pega o valor, senão, usa 0
                return value == 0 ? " " : String.valueOf(value); // Se o valor for 0, usa espaço em branco
            })
            .toArray(size -> new String[size]); // Converte o IntStream em um array de Strings 

        String formattedBoard = String.format(BoardTemplate.BOARD_TEMPLATE, (Object[]) values); // Formata o tabuleiro usando o template
        System.out.println(formattedBoard);
    }

    
}
