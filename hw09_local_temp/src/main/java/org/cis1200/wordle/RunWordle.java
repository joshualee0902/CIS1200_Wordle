package org.cis1200.wordle;

import javax.swing.*;
import java.awt.*;

public class RunWordle implements Runnable {

    public void run() {
        final JFrame frame = new JFrame("Wordle");
        frame.setLocation(600, 600);
        frame.setFocusable(true);
        frame.requestFocusInWindow();

        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("1 out of 6 tries...");
        status_panel.add(status);

        final GameBoard board = new GameBoard(status);
        frame.add(board, BorderLayout.CENTER);

        final LetterBoard lboard = new LetterBoard();
        frame.add(lboard, BorderLayout.EAST);

        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        final JFrame instructions = new Instructions();

        final JButton ibutton = new JButton("Instructions");
        ibutton.addActionListener(e -> instructions.setVisible(true));
        control_panel.add(ibutton);

        final JButton save = new JButton("Save");
        save.addActionListener(e -> board.writeToFile());
        control_panel.add(save);

        final JButton load = new JButton("Load");
        load.addActionListener(e -> {
            board.load();
            lboard.reset();
            lboard.checkAlphabet(board.getList());
        });
        control_panel.add(load);

        JSeparator s = new JSeparator();
        s.setOrientation(SwingConstants.HORIZONTAL);

        control_panel.add(s);

        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> {
            board.reset();
            lboard.reset();
        });
        control_panel.add(reset);

        final JButton undo = new JButton("Undo");
        undo.addActionListener(e -> {
            board.undo();
            lboard.reset();
            for (int i = 1; i < board.getNumGuess(); i++) {
                lboard.checkAlphabet(board.getMoves().get(i));
            }
        });
        control_panel.add(undo);

        final JButton enter = new JButton("Enter");
        enter.addActionListener(e -> {
            board.enter();
            boolean b = board.getGameOver();
            lboard.checkAlphabet(board.getList());
            board.checkReset(b);
            lboard.checkReset(b);
        });
        control_panel.add(enter);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
