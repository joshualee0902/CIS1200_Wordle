package org.cis1200.wordle;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * You can use this file (and others) to test your
 * implementation.
 */

public class GameTest {

    private Wordle w;

    @BeforeEach
    public void setUp() {
        w = new Wordle("stick");
    }

    @Test
    public void testSixGuessesCorrectEnd() {
        w.guessWord("adieu");
        w.guessWord("sorry");
        w.guessWord("glass");
        w.guessWord("happy");
        w.guessWord("phone");
        w.guessWord("stick");

        assertEquals("stick", w.returnTargetWord());
        assertEquals(6, w.getNumGuess());
        assertTrue(w.getGameOver());
        assertTrue(w.getCorrect());
    }

    @Test
    public void testSixGuessesWrongEnd() {
        w.guessWord("adieu");
        w.guessWord("sorry");
        w.guessWord("glass");
        w.guessWord("happy");
        w.guessWord("phone");
        w.guessWord("paper");

        assertEquals("stick", w.returnTargetWord());
        assertEquals(6, w.getNumGuess());
        assertTrue(w.getGameOver());
        assertFalse(w.getCorrect());
    }

    @Test
    public void testGuessWordAllGreen() {
        ArrayList<HashMap<Character, String>> expected = new ArrayList<>();

        HashMap<Character, String> hmap1 = new HashMap<>();
        hmap1.put('s', "GREEN");
        expected.add(hmap1);
        HashMap<Character, String> hmap2 = new HashMap<>();
        hmap2.put('t', "GREEN");
        expected.add(hmap2);
        HashMap<Character, String> hmap3 = new HashMap<>();
        hmap3.put('i', "GREEN");
        expected.add(hmap3);
        HashMap<Character, String> hmap4 = new HashMap<>();
        hmap4.put('c', "GREEN");
        expected.add(hmap4);
        HashMap<Character, String> hmap5 = new HashMap<>();
        hmap5.put('k', "GREEN");
        expected.add(hmap5);

        assertEquals(expected, w.guessWord("stick"));
    }

    @Test
    public void testGuessWordMix() {
        ArrayList<HashMap<Character, String>> expected = new ArrayList<>();

        HashMap<Character, String> hmap1 = new HashMap<>();
        hmap1.put('d', "GRAY");
        expected.add(hmap1);
        HashMap<Character, String> hmap2 = new HashMap<>();
        hmap2.put('i', "YELLOW");
        expected.add(hmap2);
        HashMap<Character, String> hmap3 = new HashMap<>();
        hmap3.put('c', "YELLOW");
        expected.add(hmap3);
        HashMap<Character, String> hmap4 = new HashMap<>();
        hmap4.put('e', "GRAY");
        expected.add(hmap4);
        HashMap<Character, String> hmap5 = new HashMap<>();
        hmap5.put('s', "YELLOW");
        expected.add(hmap5);

        assertEquals(expected, w.guessWord("dices"));
    }

    @Test
    public void testGuessWordAllGray() {
        ArrayList<HashMap<Character, String>> expected = new ArrayList<>();

        HashMap<Character, String> hmap1 = new HashMap<>();
        hmap1.put('p', "GRAY");
        expected.add(hmap1);
        HashMap<Character, String> hmap2 = new HashMap<>();
        hmap2.put('h', "GRAY");
        expected.add(hmap2);
        HashMap<Character, String> hmap3 = new HashMap<>();
        hmap3.put('o', "GRAY");
        expected.add(hmap3);
        HashMap<Character, String> hmap4 = new HashMap<>();
        hmap4.put('n', "GRAY");
        expected.add(hmap4);
        HashMap<Character, String> hmap5 = new HashMap<>();
        hmap5.put('e', "GRAY");
        expected.add(hmap5);

        assertEquals(expected, w.guessWord("phone"));
    }

    @Test
    public void testGuessWordInvalidReturnsEmptyArrayList() {
        ArrayList<HashMap<Character, String>> expected = new ArrayList<>();

        assertEquals(expected, w.guessWord("dkwod"));
    }

    @Test
    public void testGuessWordAllInvalid() {
        w.guessWord("aaaaa");
        w.guessWord("bbbbb");
        w.guessWord("ccccc");
        w.guessWord("ddddd");
        w.guessWord("eeeee");
        w.guessWord("fffff");

        assertEquals("stick", w.returnTargetWord());
        assertEquals(1, w.getNumGuess());
        assertFalse(w.getGameOver());
        assertFalse(w.getCorrect());
    }

    @Test
    public void testGuessWordSomeNotFiveLetters() {
        w.guessWord("as");
        w.guessWord("black");
        w.guessWord("white");
        w.guessWord("phone");
        w.guessWord("see");
        w.guessWord("fffff");

        assertEquals("stick", w.returnTargetWord());
        assertEquals(4, w.getNumGuess());
        assertFalse(w.getGameOver());
        assertFalse(w.getCorrect());
    }

    @Test
    public void testGuessWordAfterGameIsOver() {
        w.guessWord("adieu");
        w.guessWord("black");
        w.guessWord("white");
        w.guessWord("stick");
        w.guessWord("board");

        assertEquals("stick", w.returnTargetWord());
        assertEquals(5, w.getNumGuess());
        assertTrue(w.getGameOver());
        assertTrue(w.getCorrect());
    }

    @Test
    public void testReset() {
        w.guessWord("adieu");
        w.guessWord("sorry");
        w.guessWord("glass");
        w.guessWord("happy");
        w.guessWord("phone");
        assertEquals(6, w.getNumGuess());

        w.reset("stick");
        assertEquals(1, w.getNumGuess());

        assertEquals("stick", w.returnTargetWord());
        assertEquals(1, w.getNumGuess());
        assertFalse(w.getGameOver());
        assertFalse(w.getCorrect());
    }

    @Test
    public void testResetGuessesRightAfter() {
        w.guessWord("adieu");
        w.guessWord("sorry");
        w.guessWord("glass");
        w.guessWord("happy");
        w.guessWord("phone");
        assertEquals(6, w.getNumGuess());

        w.reset("stick");
        w.guessWord("adieu");
        w.guessWord("sorry");
        w.guessWord("glass");
        w.guessWord("happy");
        w.guessWord("phone");
        w.guessWord("stick");

        assertEquals("stick", w.returnTargetWord());
        assertEquals(6, w.getNumGuess());
        assertTrue(w.getGameOver());
        assertTrue(w.getCorrect());
    }

    @Test
    public void testResetGuessesWrongAfter() {
        w.guessWord("adieu");
        w.guessWord("sorry");
        w.guessWord("glass");
        w.guessWord("happy");
        w.guessWord("phone");
        assertEquals(6, w.getNumGuess());

        w.reset("stick");
        w.guessWord("adieu");
        w.guessWord("sorry");
        w.guessWord("glass");
        w.guessWord("happy");
        w.guessWord("phone");
        w.guessWord("board");

        assertEquals("stick", w.returnTargetWord());
        assertEquals(6, w.getNumGuess());
        assertTrue(w.getGameOver());
        assertFalse(w.getCorrect());
    }

    @Test
    public void testUndo() {
        w.guessWord("adieu");
        w.guessWord("sorry");
        w.guessWord("glass");
        w.guessWord("happy");
        assertEquals(5, w.getNumGuess());

        w.undo();
        w.guessWord("phone");
        w.guessWord("paper");
        w.guessWord("stick");

        assertEquals("stick", w.returnTargetWord());
        assertEquals(6, w.getNumGuess());
        assertTrue(w.getGameOver());
        assertTrue(w.getCorrect());
    }

    @Test
    public void testUndoBeforeFirstGuess() {
        w.undo();
        assertEquals(1, w.getNumGuess());
        w.guessWord("adieu");
        w.guessWord("sorry");
        w.guessWord("glass");
        w.guessWord("happy");
        assertEquals(5, w.getNumGuess());

        w.guessWord("phone");
        w.guessWord("paper");

        assertEquals("stick", w.returnTargetWord());
        assertEquals(6, w.getNumGuess());
        assertTrue(w.getGameOver());
        assertFalse(w.getCorrect());
    }

    @Test
    public void testUndoAfterGameIsOver() {
        w.guessWord("adieu");
        w.guessWord("sorry");
        w.guessWord("glass");
        w.guessWord("happy");
        w.guessWord("paper");
        w.guessWord("phone");
        w.undo();

        assertEquals("stick", w.returnTargetWord());
        assertEquals(6, w.getNumGuess());
        assertTrue(w.getGameOver());
        assertFalse(w.getCorrect());
    }

}
