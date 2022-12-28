package org.cis1200.wordle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Wordle {
    private static ArrayList<String> words;
    private static ArrayList<String> targetWords;
    private String targetWord;
    private ArrayList<Character> targetWordArrayList;
    private HashMap<Integer, String> moves;
    private int numGuess;
    private boolean gameOver;
    private boolean correct;

    /**
     * Creates a {@code Wordle} with every information at default
     */
    public Wordle() {
        reset();
    }

    /**
     * Creates a {@code Wordle} with every information at default
     * except the target word
     *
     * For testing purposes
     *
     * @param target Target word for the wordle
     */
    public Wordle(String target) {
        txtToArrayList();
        targetWord = target;
        targetWordArrayList = targetWordToArrayList(targetWord);
        moves = new HashMap<>();
        numGuess = 1;
        gameOver = false;
        correct = false;
        System.out.println("\n" + targetWord);
    }

    /**
     * Resets the game at its default state
     */
    public void reset() {
        txtToArrayList();
        targetWord = getTargetWord();
        targetWordArrayList = targetWordToArrayList(targetWord);
        moves = new HashMap<>();
        numGuess = 1;
        gameOver = false;
        correct = false;
        System.out.println("\n" + targetWord);
    }

    /**
     * Resets the game at its default state with the target word
     *
     * @param target Target word for Wordle
     */
    public void reset(String target) {
        txtToArrayList();
        targetWord = target;
        targetWordArrayList = targetWordToArrayList(targetWord);
        moves = new HashMap<>();
        numGuess = 1;
        gameOver = false;
        correct = false;
        System.out.println(targetWord);
    }

    /**
     * Reads from a text file and creates an ArrayList to pick a target word from
     */
    public void wordsArrayList(String filepath) {
        ArrayList<String> temp = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filepath));
            while (scanner.hasNext()) {
                temp.add(scanner.next());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        if (filepath.equals("files/targetWords.txt")) {
            targetWords = temp;
        } else if (filepath.equals("files/validWords.txt")) {
            words = temp;
        }
    }

    /**
     * Calls wordsArrayList() for the two ArrayLists that need to be initialized
     * every game
     */
    public void txtToArrayList() {
        wordsArrayList("files/targetWords.txt");
        wordsArrayList("files/validWords.txt");
    }

    /**
     * Generates a target word the user needs to guess
     *
     * @return Target word
     */
    public static String getTargetWord() {
        return targetWords.get(new Random().nextInt(targetWords.size()));
    }

    /**
     * Creates an ArrayList containing character of the target word
     *
     * @param s Target word
     * @return ArrayList with characters of target word
     */
    public static ArrayList<Character> targetWordToArrayList(String s) {
        ArrayList<Character> output = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            output.add(s.charAt(i));
        }
        return output;
    }

    /**
     * Validates the guessed word
     *
     * @param guess Word that the user guessed
     * @return If the guessed word is valid or not
     */
    public boolean guessWordValid(String guess) {
        return guess.length() == 5 && words.contains(guess) && !gameOver;
    }

    /**
     * Allocates color information for each character of the guessed word
     * and changes the state of game variables
     *
     * @param guess Word that the user guessed
     * @return ArrayList containing HashMap with individual character and color info
     */
    public ArrayList<HashMap<Character, String>> guessWord(String guess) {
        ArrayList<HashMap<Character, String>> list = new ArrayList<>();

        if (guessWordValid(guess)) { // if guess is valid
            moves.put(numGuess, guess); // register move to the moves hashmap
            // add character with its corresponding color to the hashmap
            for (int i = 0; i < guess.length(); i++) {
                HashMap<Character, String> map = new LinkedHashMap<>();
                if (targetWordArrayList.get(i) == guess.charAt(i)) {
                    map.put(guess.charAt(i), "GREEN");
                } else if (targetWordArrayList.get(i) != guess.charAt(i)
                        && targetWordArrayList.contains(guess.charAt(i))) {
                    map.put(guess.charAt(i), "YELLOW");
                } else {
                    map.put(guess.charAt(i), "GRAY");
                }
                list.add(map);
            }

            // display colors of each character to the console
            System.out.println("Word guessed: " + guess);
            for (HashMap<Character, String> temp : list) {
                for (Map.Entry<Character, String> entry : temp.entrySet()) {
                    System.out.println("<" + entry.getKey() + ", " + entry.getValue() + ">");
                }
            }

            // track the colors of the five characters
            Set<String> colorSet = new HashSet<>();
            for (HashMap<Character, String> temp : list) {
                for (Map.Entry<Character, String> entry : temp.entrySet()) {
                    colorSet.add(entry.getValue());
                }
            }

            // if the user guessed the correct word
            if (colorSet.size() == 1 && colorSet.contains("GREEN")) {
                correct = true;
                gameOver = true;
                System.out.println("Correct!");
            }

            numGuess++;

            // if user used all their guesses
            if (numGuess == 7) {
                if (!correct) {
                    gameOver = true;
                    System.out.println("\nGAME OVER");
                    System.out.println("The correct word was: " + targetWord);
                }
                numGuess = 6;
            }
        } else {
            System.out.println("Invalid input");
        }
        return list;
    }

    /**
     * Prints the game state to the terminal
     */
    public void printGameState() {
        System.out.println(numGuess + " out of 6 tries...");
    }

    /**
     * Undoes the previous action the user took
     */
    public void undo() {
        if (numGuess <= 1) {
            // still lets user undo infinitely; will simply display this message
            // continuously
            System.out.println("Can't undo on first guess.");
        } else if (numGuess >= 6) {
            System.out.println("Can't undo when game is over.");
        } else {
            moves.remove(numGuess);
            numGuess--;
            System.out.println("Undo!");
        }
    }

    /**
     * @return Target word of Wordle
     */
    public String returnTargetWord() {
        return this.targetWord;
    }

    /**
     * @return Number of guesses user took
     */
    public int getNumGuess() {
        return this.numGuess;
    }

    /**
     * @return If game is over or not
     */
    public boolean getGameOver() {
        return this.gameOver;
    }

    /**
     * @return If the guessed word is correct
     */
    public boolean getCorrect() {
        return this.correct;
    }

    public static void main(String[] args) {
        Wordle w = new Wordle();
        w.printGameState();
        w.undo();
        w.guessWord("sorry");
        w.printGameState();
        w.undo();
        w.printGameState();
        w.guessWord("adieu");
        w.printGameState();
        w.guessWord("glass");
        w.printGameState();
        w.guessWord("happy");
        w.printGameState();
        w.guessWord("phone");
        w.printGameState();
        w.guessWord("stick");
        w.printGameState();
        w.guessWord("cheek");
    }
}
