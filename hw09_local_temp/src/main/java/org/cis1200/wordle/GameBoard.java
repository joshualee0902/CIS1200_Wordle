package org.cis1200.wordle;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static javax.swing.JOptionPane.showMessageDialog;

public class GameBoard extends JPanel {
    private Wordle wordle;
    private JLabel status;
    private LinkedHashMap<Integer, ArrayList<HashMap<Character, String>>> moves;
    private ArrayList<HashMap<Character, String>> list;
    private ArrayList<String> guessedWords;
    public static final int BOARD_WIDTH = 600;
    public static final int BOARD_HEIGHT = 600;
    private final Block[][] board = new Block[6][5];
    private int currentRow;
    private int numGuess;
    private boolean gameOver;

    /**
     * Creates a {@code GameBoard} with a JLabel status as a parameter
     *
     * @param statusInit Initial status of the GameBoard
     */
    public GameBoard(JLabel statusInit) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setFocusable(true);
        setLayout(new GridLayout(6, 5));

        wordle = new Wordle();
        status = statusInit;
        currentRow = 0;
        numGuess = 1;
        gameOver = false;
        moves = new LinkedHashMap<>();
        guessedWords = new ArrayList<>();
        setBoard();
    }

    /**
     * Resets the GameBoard to its default state
     */
    public void reset() {
        wordle.reset();
        status.setText("1 out of 6 tries...");
        currentRow = 0;
        numGuess = 1;
        gameOver = false;
        moves = new LinkedHashMap<>();
        guessedWords = new ArrayList<>();
        this.removeAll();
        revalidate();
        setBoard();
        repaint();
    }

    /**
     * Updates the status of the GameBoard
     */
    public void updateStatus() {
        status.setText(numGuess + " out of 6 tries...");
    }

    /**
     * Takes the actions after a user decides to guess a word
     *
     * @param s Word that the user guessed
     */
    public void enterInput(String s) {
        if (wordle.guessWordValid(s)) { // if guess is valid
            if (guessedWords.contains(s)) { // if user already guessed the word
                showMessageDialog(null, "You already guessed this word");
                for (int j = 0; j < 5; j++) { // clear the boxes and reset the try
                    (board[currentRow][j]).clear();
                }
            } else {
                guessedWords.add(s); // add word to array of guessed words
                list = wordle.guessWord(s); // create an arraylist of the characters and
                                            // corresponding color
                moves.put(numGuess, list); // register the move to the linkedhashmap
                if (wordle.getGameOver()) {
                    this.gameOver = true;
                }
                ArrayList<String> colorList = new ArrayList<>();
                for (HashMap<Character, String> temp : list) { // track the colors of the five
                                                               // characters
                    for (Map.Entry<Character, String> entry : temp.entrySet()) {
                        colorList.add(entry.getValue());
                    }
                }

                for (int j = 0; j < 5; j++) {
                    (board[currentRow][j]).setEnabled(false); // make the row un-editable once the
                                                              // guess has been made
                    // set the text of the box equal to the character that was guessed
                    if ((board[currentRow][j]).getText().equals("")) {
                        (board[currentRow][j])
                                .setText(Character.toString(s.charAt(j)).toUpperCase());
                    } else {
                        (board[currentRow][j])
                                .setText((board[currentRow][j]).getText().toUpperCase());
                    }
                    (board[currentRow][j]).setColor(colorList.get(j)); // set the box with the
                                                                       // corresponding color
                    if (currentRow < 5) {
                        (board[currentRow + 1][j]).setEnabled(true); // make the next row editable
                    }
                }

                if (wordle.getGameOver() && wordle.getCorrect()) { // user guesses the right word
                    status.setText("Game over: Correct Answer");
                    showMessageDialog(null, "CORRECT!! YOU GUESSED IT!");
                } else if (wordle.getGameOver() && !wordle.getCorrect()) { // user uses all tries
                    status.setText("Game over: Out of guesses");
                    showMessageDialog(
                            null, "GAME OVER ...\n" + "The word was: "
                                    + wordle.returnTargetWord()
                    );
                }
                // update game state variables
                numGuess++;
                currentRow++;
                updateStatus();
            }
        } else {
            showMessageDialog(null, "Invalid input");
        }
    }

    /**
     * Takes the individual characters from the Blocks and builds a word;
     * Calls enterInput() to take following actions
     */
    public void enter() {
        String s = "";
        for (int j = 0; j < 5; j++) {
            s += (board[currentRow][j]).getText();
        }
        enterInput(s);
    }

    /**
     * Checks if the GameBoard needs to be reset
     *
     * @param b If the GameBoard needs to be reset or not
     */
    public void checkReset(boolean b) {
        if (b) {
            reset();
        }
    }

    /**
     * Sets up the GameBoard
     */
    private void setBoard() {
        // allocate a Block into each slot of the array
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                Block block = new Block(i, j);
                add(block);
                board[i][j] = block;

                if (i > 0) {
                    block.setEnabled(false); // make all rows except the first one initially
                                             // un-editable
                }
            }

        }
    }

    /**
     * Writes the target word and words the user guessed into a text file
     */
    public void writeToFile() {
        String output = "";
        for (Map.Entry<Integer, ArrayList<HashMap<Character, String>>> move : moves.entrySet()) {
            for (HashMap<Character, String> hmap : move.getValue()) {
                for (Map.Entry<Character, String> entry : hmap.entrySet()) {
                    output += entry.getKey() + "\n"; // write each character of the guessed words in
                                                     // its own lines
                }
            }
        }

        BufferedWriter bw = null;
        try {
            File file = new File("files/log.txt");
            if (!file.exists()) {
                file.createNewFile(); // create file if doesn't exist
            }
            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(output);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (Exception ex) {
                System.out.println("Error in closing the BufferedWriter" + ex);
            }
        }
    }

    /**
     * Undoes the previous action the user took
     */
    public void undo() {
        if (numGuess <= 1) {
            // still lets user undo infinitely; will simply display this message
            // continuously
            showMessageDialog(null, "Can't undo on first guess.");
        } else {
            wordle.undo(); // call undo on the core game-state
            numGuess--; // reduce number of guesses by 1
            guessedWords.remove(numGuess - 1); // remove the last word in the array
            moves.remove(numGuess); // remove the latest move
            for (int j = 0; j < 5; j++) {
                (board[currentRow - 1][j]).setEnabled(true); // make the previous row editable
                (board[currentRow - 1][j]).clear(); // clear the current row
                (board[currentRow][j]).setEnabled(false); // make the current row uneditable
            }
            currentRow--;
            updateStatus();
        }
    }

    /**
     * @return LinkedHashMap of moves the user took
     */
    public LinkedHashMap<Integer, ArrayList<HashMap<Character, String>>> getMoves() {
        return this.moves;
    }

    /**
     * @return List of characters of the word the user last guessed
     */
    public ArrayList<HashMap<Character, String>> getList() {
        return this.list;
    }

    /**
     * @return Number of guesses the user took
     */
    public int getNumGuess() {
        return this.numGuess;
    }

    /**
     * @return If the game is over or not
     */
    public boolean getGameOver() {
        return this.gameOver;
    }

    /**
     * Loads the previous game that the user saved;
     * Reads from a text file and sets the GameBoard as it was
     */
    public void load() {
        reset();
        try (BufferedReader br = new BufferedReader(new FileReader("files/log.txt"))) {
            int ctr = 0;
            String word = "";
            for (String line; (line = br.readLine()) != null;) {
                ctr++;
                word += line;
                if ((ctr % 5 == 0) && (ctr >= 5)) {
                    enterInput(word);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }

}
