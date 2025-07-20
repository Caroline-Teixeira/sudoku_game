package br.com.sudoku.gui;

import javax.swing.*;
import java.awt.*;
import br.com.sudoku.core.DifficultyLevel;

public class StatusBar extends JLabel implements GameStatusListener {
    
    public StatusBar() {
        super("Status: Não iniciado | Dificuldade: Não definida");
        setFont(new Font("Arial", Font.PLAIN, 18));
        setHorizontalAlignment(SwingConstants.CENTER);
        setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
    }

    @Override
    public void onStatusChanged(String status, DifficultyLevel difficulty) {
        String difficultyText = difficulty != null ? difficulty.toString() : "Não definida";
        setText(String.format("Status: %s | Dificuldade: %s", status, difficultyText));
    }
}