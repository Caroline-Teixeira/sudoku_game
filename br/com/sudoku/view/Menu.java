package br.com.sudoku.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.sudoku.model.Cell;
import br.com.sudoku.model.SudokuGame;
import br.com.sudoku.service.SudokuGameService;

public class Menu {

    private final SudokuGameService service;
    private final Scanner scanner;
    
    
    // Construtor
    public Menu() {
        this.service = new SudokuGameService(new SudokuGame());
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        


        while (true) {
            System.out.println("=== Menu do Jogo Sudoku ===");
            System.out.println("1. Iniciar Jogo");
            System.out.println("2. Adicionar Célula");
            System.out.println("3. Remover Célula");
            System.out.println("4. Limpar Jogadas");
            System.out.println("5. Sair");
            System.out.println("===========================");
            System.out.println("Escolha uma opção (1-5): ");
            
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    startGame();
                    printGame();
                }
                case 2 -> {
                    addCell(); 
                    printGame();         
                }   
                case 3 -> {
                    removeCell();
                    printGame();
                }
                case 4 -> {
                    clearUserInputs();
                    printGame();
                }
                
                case 5 -> {
                    System.out.println("Saindo do jogo...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
                    
            }

        }

    }
    // Início
    private void startGame(){
        List<Cell> initialCells = new ArrayList<>();
        initialCells.add(new Cell (0,0,5, true));
        initialCells.add(new Cell(0, 1, 3, true));
        initialCells.add(new Cell(1, 0, 6, true));

        service.startGame(initialCells);
        System.out.println("Novo jogo. Status do jogo: " + service.getStatusNow());

    }

    // nova célula
    private void addCell() {
        System.out.print("Digite uma linha (0-8): ");
        int row = scanner.nextInt();
        System.out.print("Digite uma coluna (0-8): ");
        int col = scanner.nextInt();
        System.out.print("Número a inserir na célula (1-9): ");
        int value = scanner.nextInt();
        scanner.nextLine(); 

        try {
            Cell cell = new Cell(row, col, value, false);  // se a célua é inserida pelo jogador
            service.addCell(cell);
            System.out.println("Célula adicionada. Status do Jogo: " + service.getStatusNow());

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // remover célula
    private void removeCell(){
        System.out.print("Digite uma linha (0-8): ");
        int row = scanner.nextInt();
        System.out.print("Digite uma coluna (0-8): ");
        int col = scanner.nextInt();
        scanner.nextLine(); 

        try{
            service.removeCell(row, col);
            System.out.println("Célula removida com sucesso. Status do jogo: " + service.getStatusNow());
            
        } catch (IllegalArgumentException e){
            System.out.println("Erro: " + e.getMessage());

        }
    }

    // remover entradas do usuário
    private void clearUserInputs(){
        service.clearUserInputs();
        System.out.println("Tabuleiro limpo. Status do jogo: " + service.getStatusNow());

    }

    // imprime o tabuleiro
    private void printGame() {
        System.out.println(" ");
        service.printGame();
        
    }


}
