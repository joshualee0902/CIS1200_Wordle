package org.cis1200.wordle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LetterBoard extends JPanel {
    public static final int BOARD_WIDTH = 400;
    public static final int BOARD_HEIGHT = 600;
    private final Block[][] board = new Block[5][6];

    /**
     * Creates a {@code LetterBoard} with alphabet Blocks that indicate
     * if the guessed letters are correct, within the target word, or wrong
     */
    public LetterBoard() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setFocusable(true);
        setLayout(new GridLayout(5, 6));

        setBoard();
    }

    /**
     * Resets the LetterBoard to its default state
     */
    public void reset() {
        this.removeAll();
        revalidate();
        setBoard();
        repaint();
    }

    /**
     * Sets the background of each alphabet to its correct state (color)
     *
     * @param list ArrayList that contains information of what color
     *             each alphabet should have
     */
    public void checkAlphabet(ArrayList<HashMap<Character, String>> list) {
        // change the block of the color when the block is originally not green
        for (HashMap<Character, String> temp : list) {
            for (Map.Entry<Character, String> entry : temp.entrySet()) {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 6; j++) {
                        if (entry.getKey().toString().equals(board[i][j].getText().toLowerCase())) {
                            if (!(board[i][j].getColor().equals("GREEN"))) {
                                (board[i][j]).setColor(entry.getValue());
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Checks if the LetterBoard needs to be reset
     *
     * @param b If the LetterBoard needs to be reset or not
     */
    public void checkReset(boolean b) {
        if (b) {
            reset();
        }
    }

    /**
     * Sets up the LetterBoard
     */
    private void setBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                Block block = new Block(i, j);
                add(block);
                board[i][j] = block;
                block.setEnabled(false);
            }

        }

        (board[0][0]).setText("A");
        (board[0][1]).setText("B");
        (board[0][2]).setText("C");
        (board[0][3]).setText("D");
        (board[0][4]).setText("E");
        (board[0][5]).setText("F");
        (board[1][0]).setText("G");
        (board[1][1]).setText("H");
        (board[1][2]).setText("I");
        (board[1][3]).setText("J");
        (board[1][4]).setText("K");
        (board[1][5]).setText("L");
        (board[2][0]).setText("M");
        (board[2][1]).setText("N");
        (board[2][2]).setText("O");
        (board[2][3]).setText("P");
        (board[2][4]).setText("Q");
        (board[2][5]).setText("R");
        (board[3][0]).setText("S");
        (board[3][1]).setText("T");
        (board[3][2]).setText("U");
        (board[3][3]).setText("V");
        (board[3][4]).setText("W");
        (board[3][5]).setText("X");
        (board[4][0]).setText("Y");
        (board[4][1]).setText("Z");
        // unused boxes
        (board[4][2]).setBackground(new Color(120, 124, 126));
        (board[4][3]).setBackground(new Color(120, 124, 126));
        (board[4][4]).setBackground(new Color(120, 124, 126));
        (board[4][5]).setBackground(new Color(120, 124, 126));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}
