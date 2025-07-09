package br.com.sudoku.gui;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JLabel implements GameStatusListener {

    // exibe o estado atual do jogo.
    public StatusBar() {
        super("Status: Não iniciado");
        setFont(new Font("Arial", Font.PLAIN, 18));
        setHorizontalAlignment(SwingConstants.CENTER);
        setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
    }

    @Override
    public void onStatusChanged(String status) {
        setText(status);
    }
}
