package org.cis1200.wordle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Block extends JTextField {
    private final int row;
    private final int column;
    private String color;
    private final Color white = new Color(255, 255, 255);
    private final Color black = new Color(0, 0, 0);
    private final Color green = new Color(106, 170, 100);
    private final Color yellow = new Color(201, 180, 88);
    private final Color gray = new Color(120, 124, 126);

    /**
     * Creates a {@code Block} with rows and columns as parameters
     *
     * @param r Row the Block is in
     * @param c Column the Block is in
     */
    public Block(int r, int c) {
        this.row = r;
        this.column = c;
        this.color = "WHITE";
        this.setBackground(white);
        this.setForeground(black);
        this.setDisabledTextColor(black);
        this.setHorizontalAlignment(CENTER);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (getText().length() >= 1) { // set maximum number of characters to 1 per block
                    e.consume();
                }
            }
        });
    }

    /**
     * Sets the background color of a Block
     *
     * @param color The color the Block should turn into
     */
    public void setColor(String color) {
        if ("GREEN".equals(color)) {
            this.setBackground(green);
            this.color = "GREEN";
        } else if ("YELLOW".equals(color)) {
            this.setBackground(yellow);
            this.color = "YELLOW";
        } else if ("GRAY".equals(color)) {
            this.setBackground(gray);
            this.color = "GRAY";
        }
    }

    /**
     * Returns a String color of the block
     *
     * @return Color of the block
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Resets the Block to its default state
     */
    public void clear() {
        this.setText("");
        this.setBackground(white);
    }
}
