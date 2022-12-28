package org.cis1200.wordle;

import javax.swing.*;
import java.awt.*;

public class Instructions extends JFrame {

    public static final int BOARD_WIDTH = 400;
    public static final int BOARD_HEIGHT = 600;

    /**
     * Creates a {@code Instructions} that explains how to play the game
     */
    public Instructions() {
        this.setTitle("Instructions");
        this.setLocation(0, 0);
        // this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        this.setSize(600, 300);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrPane = new JScrollPane(panel);

        String text = "<html>HOW TO PLAY WORDLE</html>";

        Font head1 = new Font(text, Font.PLAIN, 40);

        JLabel l = new JLabel(text, SwingConstants.LEFT);
        l.setFont(head1);
        panel.add(l);

        String text10 = "<html><br/>Guess the Wordle in 6 tries.</html>";

        Font head2 = new Font(text, Font.PLAIN, 20);

        JLabel l10 = new JLabel(text10, SwingConstants.LEFT);
        l10.setFont(head2);
        panel.add(l10);

        String text1 = "<html>-Each guess must be a valid 5-letter word." +
                "<br/>-The color of the tiles will change to show how " +
                "close your guess was to the word.</html>";

        JLabel l1 = new JLabel(text1, SwingConstants.LEFT);
        panel.add(l1);

        String text20 = "<html><br/>After you have guessed a word:</html>";
        JLabel l20 = new JLabel(text20, SwingConstants.LEFT);
        l20.setFont(head2);
        panel.add(l20);

        String text2 = "<html>-A GREEN tile means the letter is in the" +
                "correct spot.<br/>-A YELLOW tile means the letter is " +
                "in the word but in the wrong spot.<br/>-A GRAY tile " +
                "means the letter is not in the word.</html>";

        JLabel l2 = new JLabel(text2, SwingConstants.LEFT);
        panel.add(l2);

        JLabel image1 = new JLabel(new ImageIcon("files/gameboard.png"));
        panel.add(image1);

        String text30 = "<html><br/>Buttons:</html>";
        JLabel l30 = new JLabel(text30, SwingConstants.LEFT);
        l30.setFont(head2);
        panel.add(l30);

        String text3 = "<html>-The Instructions button provides you " +
                "with you a window on how to play the game.<br/>" +
                "-The Save button lets you save the current state of " +
                "your game so you can come back later by pressing the " +
                "Load button.<br/>-The Load button lets you retrieve a " +
                "previous game and start off from where you left.<br/>-The " +
                "Reset button sets the game to its initial state so you " +
                "can play a new game.<br/>-The Undo button scraps your " +
                "previous attempt and allows you to make a new guess." +
                "<br/>-The Enter button lets you input the word you " +
                "want to guess.</html>";

        JLabel l3 = new JLabel(text3, SwingConstants.LEFT);
        panel.add(l3);

        String text40 = "<html><br/>Letterboard:</html>";
        JLabel l40 = new JLabel(text40, SwingConstants.LEFT);
        l40.setFont(head2);
        panel.add(l40);

        String text4 = "<html>The letterboard provides you information on the " +
                "alphabets you can use.<br/>-If an alphabet is GREEN, you have " +
                "all the possible letters in the word guessed.<br/>-If an " +
                "alphabet is YELLOW, you know that the letter is in the word " +
                "but it is yet to be guessed in the right position.<br/>" +
                "-If an alphabet is GRAY, the letter is not in the word.</html>";

        JLabel l4 = new JLabel(text4, SwingConstants.LEFT);
        panel.add(l4);

        JLabel image2 = new JLabel(new ImageIcon("files/letterboard.png"));
        panel.add(image2);

        String text5 = "<html><br/>HAVE FUN!!</html>";
        JLabel l5 = new JLabel(text5, SwingConstants.LEFT);
        l5.setFont(head1);
        panel.add(l5);

        this.add(scrPane);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}
