import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Project4.src.Letter;
import Project4.src.Wolfle;

//TODO: Add documentation to pass checkstyle

/**
 * Tests Wolfle class
 * 
 * @author Suzanne Balik
 * @author Michelle Glatz
 * @author Roshni Desai
 */
public class WolfleTest {

        /** Wolfle object for tests */
        private Wolfle wolfle;

        /**
         * Sets up field for testing
         *
         * @throws FileNotFoundException if Wolfle data files are missing
         */
        @BeforeEach
        public void setUp() throws FileNotFoundException {
                wolfle = new Wolfle("ELEGY");
        }

        /**
         * Tests values for public constants
         */
        @Test
        public void testClassConstants() {
                assertEquals(6, Wolfle.ROWS, "Test ROWS constant");
                assertEquals(5, Wolfle.COLS, "Test COLS constant");
        }

        /**
         * Test getSecretWord
         */
        @Test
        public void testGetSecretWord() {
                assertEquals("ELEGY", wolfle.getSecretWord(), "Test getSecretWord");
        }

        /**
         * Test isGameOverCorrectGuess
         */
        @Test
        public void testIsGameOverCorrectGuess() {
                assertFalse(wolfle.isGameOverCorrectGuess(),
                                "Test isGameOverCorrectGuess after construction");
                wolfle.processGuess("ELEGY", 0);
                assertTrue(wolfle.isGameOverCorrectGuess(), "Test isGameOverCorrectGuess true");
        }

        @Test
        public void testIsGameOverCorrectGuessOnLastGuess() {
                wolfle.processGuess("ALBUM", 0);
                assertFalse(wolfle.isGameOverCorrectGuess(),
                                "Test isGameOverCorrectGuess after guess 1");
                wolfle.processGuess("GRUNT", 1);
                assertFalse(wolfle.isGameOverCorrectGuess(),
                                "Test isGameOverCorrectGuess after guess 2");
                wolfle.processGuess("STORY", 2);
                assertFalse(wolfle.isGameOverCorrectGuess(),
                                "Test isGameOverCorrectGuess after guess 3");
                wolfle.processGuess("FLINT", 3);
                assertFalse(wolfle.isGameOverCorrectGuess(),
                                "Test isGameOverCorrectGuess after guess 4");
                wolfle.processGuess("TANGO", 4);
                assertFalse(wolfle.isGameOverCorrectGuess(),
                                "Test isGameOverCorrectGuess after guess 5");
                wolfle.processGuess("ELEGY", 5);
                assertTrue(wolfle.isGameOverCorrectGuess(),
                                "Test isGameOverCorrectGuess true on last guess");
        }

        /**
         * Test isGameOverNoMoreGuesses
         */
        @Test
        public void testIsGameOverNoMoreGuesses() {
                assertFalse(wolfle.isGameOverNoMoreGuesses(),
                                "Test isGameOverNoMoreGuesses after construction");
                wolfle.processGuess("ALBUM", 0);
                assertFalse(wolfle.isGameOverNoMoreGuesses(),
                                "Test isGameOverNoMoreGuesses after 1 incorrect guess");
                wolfle.processGuess("ALBUM", 1);
                assertFalse(wolfle.isGameOverNoMoreGuesses(),
                                "Test isGameOverNoMoreGuesses after 2 incorrect guesses");
                wolfle.processGuess("ALBUM", 2);
                assertFalse(wolfle.isGameOverNoMoreGuesses(),
                                "Test isGameOverNoMoreGuesses after 3 incorrect guesses");
                wolfle.processGuess("ALBUM", 3);
                assertFalse(wolfle.isGameOverNoMoreGuesses(),
                                "Test isGameOverNoMoreGuesses after 4 incorrect guesses");
                wolfle.processGuess("ALBUM", 4);
                assertFalse(wolfle.isGameOverNoMoreGuesses(),
                                "Test isGameOverNoMoreGuesses after 5 incorrect guesses");
                wolfle.processGuess("ALBUM", 5);
                assertTrue(wolfle.isGameOverNoMoreGuesses(), "Test isGameOverNoMoreGuesses true");
                assertFalse(wolfle.isGameOverCorrectGuess(),
                                "Test isGameOverCorrectGuess after no more guesses");
        }

        /**
         * Test getLetterStatus after wolfle constructed
         */
        @Test
        public void testGetLetterStatusAfterConstructed() {
                for (int i = 0; i < 26; i++) {
                        assertEquals(Letter.Status.NOT_GUESSED, wolfle.getLetterStatus(i),
                                        "Test getLetterStatus for all letters after construction");
                }
        }

        /**
         * Test getLetterStatus after letter in the alphabet is updated to IN_POSITION
         * or NOT_IN_WORD
         * The L (index 11) is IN_POSITION, the A (index 0) is NOT_IN_WORD
         */
        @Test
        public void testGetLetterStatusAfterUpdated() {
                wolfle.processGuess("ALBUM", 0);
                assertEquals(Letter.Status.IN_POSITION, wolfle.getLetterStatus(11),
                                "Test getLetterStatus after letter L guessed correctly");
                assertEquals(Letter.Status.NOT_IN_WORD, wolfle.getLetterStatus(0),
                                "Test getLetterStatus after guessed letter not in word");
        }

        /**
         * Test getLetterStatus after a letter in the alphabet is updated to IN_WORD
         */
        @Test
        public void testGetLetterStatusAfterGuessWithOneLetterInWord() {
                wolfle.processGuess("GRUNT", 0);
                assertEquals(Letter.Status.IN_WORD, wolfle.getLetterStatus(6),
                                "Test getLetterStatus after correct letter G guessed in wrong position");
                assertEquals(Letter.Status.NOT_IN_WORD, wolfle.getLetterStatus(17),
                                "Test getLetterStatus after R guessed incorrectly");
                assertEquals(Letter.Status.NOT_IN_WORD, wolfle.getLetterStatus(20),
                                "Test getLetterStatus after U guessed incorrectly");
                assertEquals(Letter.Status.NOT_IN_WORD, wolfle.getLetterStatus(13),
                                "Test getLetterStatus after N guessed incorrectly");
                assertEquals(Letter.Status.NOT_IN_WORD, wolfle.getLetterStatus(19),
                                "Test getLetterStatus after T guessed incorrectly");
        }

        /**
         * Test getGridLetterStatus after wolfle constructed
         */
        @Test
        public void testGetGridLetterStatusAfterConstructed() {
                for (int i = 0; i < 6; i++) {
                        for (int j = 0; j < 5; j++) {
                                assertEquals(Letter.Status.NOT_GUESSED, wolfle.getGridLetterStatus(i, j),
                                                "Test getGridLetterStatus for all grid spaces after construction");
                        }
                }
        }

        /**
         * Test getGridLetterStatus after a letter in the secret word is guessed
         * correctly
         */
        @Test
        public void testGetGridLetterStatusAfterUpdated() {
                wolfle.processGuess("ALBUM", 0);
                assertEquals(Letter.Status.IN_POSITION, wolfle.getGridLetterStatus(0, 1),
                                "Test getGridLetterStatus after second letter guessed correctly");
                assertEquals(Letter.Status.NOT_IN_WORD, wolfle.getGridLetterStatus(0, 0),
                                "Test getGridLetterStatus after first guessed letter not in word");
        }

        /**
         * Test getGridLetterStatus after a letter in the grid is updated to IN_WORD
         */
        @Test
        public void testGetGridLetterStatusAfterGuess() {
                wolfle.processGuess("GRUNT", 0);
                assertEquals(Letter.Status.IN_WORD, wolfle.getGridLetterStatus(0, 0),
                                "Test getGridLetterStatus after correct letter G guessed in wrong position");
                assertEquals(Letter.Status.NOT_IN_WORD, wolfle.getGridLetterStatus(0, 1),
                                "Test getGridLetterStatus after R guessed incorrectly");
                assertEquals(Letter.Status.NOT_IN_WORD, wolfle.getGridLetterStatus(0, 2),
                                "Test getGridLetterStatus after U guessed incorrectly");
                assertEquals(Letter.Status.NOT_IN_WORD, wolfle.getGridLetterStatus(0, 3),
                                "Test getGridLetterStatus after N guessed incorrectly");
                assertEquals(Letter.Status.NOT_IN_WORD, wolfle.getGridLetterStatus(0, 4),
                                "Test getgridLetterStatus after T guessed incorrectly");
        }

        /**
         * Test evaluateGuess with 2 of same letter in word and 2 guessed
         */
        @Test
        public void testEvaluateGuessTwoSameLetter() {
                Letter[] guess = wolfle.evaluateGuess("EAGLE");
                Letter[] expected = new Letter[5];
                expected[0] = new Letter('E');
                expected[1] = new Letter('A');
                expected[2] = new Letter('G');
                expected[3] = new Letter('L');
                expected[4] = new Letter('E');
                expected[0].setStatus(Letter.Status.IN_POSITION);
                expected[1].setStatus(Letter.Status.NOT_IN_WORD);
                expected[2].setStatus(Letter.Status.IN_WORD);
                expected[3].setStatus(Letter.Status.IN_WORD);
                expected[4].setStatus(Letter.Status.IN_WORD);
                assertArrayEquals(expected, guess,
                                "Test evaluateGuess with duplicate letter in word and guessed");
        }

        /**
         * Test evaluateGuess with 2 of same letter in word and 3 guessed
         */
        @Test
        public void testEvaluateGuessThreeSameLetter() {
                Letter[] guess = wolfle.evaluateGuess("EAGLE");
                guess = wolfle.evaluateGuess("EERIE");
                Letter[] expected = new Letter[5];
                expected[0] = new Letter('E');
                expected[1] = new Letter('E');
                expected[2] = new Letter('R');
                expected[3] = new Letter('I');
                expected[4] = new Letter('E');
                expected[0].setStatus(Letter.Status.IN_POSITION);
                expected[1].setStatus(Letter.Status.IN_WORD);
                expected[2].setStatus(Letter.Status.NOT_IN_WORD);
                expected[3].setStatus(Letter.Status.NOT_IN_WORD);
                expected[4].setStatus(Letter.Status.NOT_IN_WORD);
                assertArrayEquals(expected, guess,
                                "Test evaluateGuess with 3 duplicate letters but only 2 correct");
        }

        /**
         * Test evaluateGuess with all letters guessed
         */
        @Test
        public void testEvaluateGuessAllLettersPresent() {
                Wolfle wolfle2 = new Wolfle("TASTE");
                Letter[] guess = wolfle2.evaluateGuess("STATE");
                // TODO: Test that returned letter array is correct
                // 1. Create expected Letter array
                // 2. Add an assertArrayEquals to test the evaluateGuessResult
                fail("No test added");
        }

        /**
         * Test evaluateGuess with three letters guessed, 2 in word correct position
         */
        @Test
        public void testEvaluateGuessDuplicateLettersCorrectOneExtra() {
                Wolfle wolfle2 = new Wolfle("EAGLE");
                Letter[] guess = wolfle2.evaluateGuess("EERIE");
                Letter[] expected = new Letter[5];
                expected[0] = new Letter('E');
                expected[1] = new Letter('E');
                expected[2] = new Letter('R');
                expected[3] = new Letter('I');
                expected[4] = new Letter('E');
                expected[0].setStatus(Letter.Status.IN_POSITION);
                expected[1].setStatus(Letter.Status.NOT_IN_WORD);
                expected[2].setStatus(Letter.Status.NOT_IN_WORD);
                expected[3].setStatus(Letter.Status.NOT_IN_WORD);
                expected[4].setStatus(Letter.Status.IN_POSITION);
                assertArrayEquals(expected, guess,
                                "Test evaluateGuess guess 3 duplicate letters, 2 in word in correct position");
        }

        /**
         * Test processGuess with invalid word
         */
        @Test
        public void testProcessGuessInvalidWord() {
                assertEquals(false, wolfle.processGuess("NOTIT", 0),
                                "Test processGuess not a word");
                assertEquals(Letter.Status.NOT_GUESSED, wolfle.getLetterStatus(13),
                                "Test processGuess letter N NOT_GUESSED");
                assertEquals(Letter.Status.NOT_GUESSED, wolfle.getGridLetterStatus(0, 0),
                                "Test processGuess grid(0,0) NOT_GUESSED");

        }

        /**
         * Test processGuess for letter in word after in position
         */
        @Test
        public void testProcessGuessInWordAfterInPosition() {
                wolfle.processGuess("RANGE", 0);
                assertEquals(Letter.Status.IN_POSITION, wolfle.getLetterStatus(6),
                                "Test processGuess Letter G IN_POSITION");
                wolfle.processGuess("GRUNT", 1);
                assertEquals(Letter.Status.IN_POSITION, wolfle.getLetterStatus(6),
                                "Test processGuess Letter G IN_POSITION");
                assertEquals(Letter.Status.IN_WORD, wolfle.getGridLetterStatus(1, 0),
                                "Test processGuess grid(1,0) IN_WORD");

        }

        /**
         * Test processGuess with valid word
         */
        @Test
        public void testProcessGuessValidWord() {
                assertEquals(true, wolfle.processGuess("EAGLE", 0),
                                "Test processGuess valid word");
                assertEquals(Letter.Status.IN_POSITION, wolfle.getLetterStatus(4),
                                "Test processGuess letter E IN_POSITION");
                assertEquals(Letter.Status.IN_POSITION, wolfle.getGridLetterStatus(0, 0),
                                "Test processGuess grid(0,0) IN_POSITION");
                assertEquals(Letter.Status.NOT_IN_WORD, wolfle.getLetterStatus(0),
                                "Test processGuess letter A NOT_IN_WORD");
                assertEquals(Letter.Status.NOT_IN_WORD, wolfle.getGridLetterStatus(0, 1),
                                "Test processGuess grid(0,1) NOT_IN_WORD");
                assertEquals(Letter.Status.IN_WORD, wolfle.getLetterStatus(6),
                                "Test processGuess letter G IN_WORD");
                assertEquals(Letter.Status.IN_WORD, wolfle.getGridLetterStatus(0, 2),
                                "Test processGuess grid(0,2) IN_WORD");
                assertEquals(Letter.Status.IN_WORD, wolfle.getLetterStatus(11),
                                "Test processGuess letter L IN_WORD");
                assertEquals(Letter.Status.IN_WORD, wolfle.getGridLetterStatus(0, 3),
                                "Test processGuess grid(0,3) IN_WORD");
                assertEquals(Letter.Status.IN_WORD, wolfle.getGridLetterStatus(0, 4),
                                "Test processGuess grid(0,4) IN_WORD");
        }

        /**
         * Test processGuess with 2 of same letter in word and 3 guessed
         */
        @Test
        public void testProcessGuessThreeSameLetter() {
                wolfle.processGuess("EAGLE", 0);
                wolfle.processGuess("EERIE", 1);

                assertEquals(Letter.Status.IN_POSITION, wolfle.getLetterStatus(4),
                                "Test letter E IN_POSITION");
                assertEquals(Letter.Status.NOT_IN_WORD, wolfle.getLetterStatus(17),
                                "Test letter R NOT_IN_WORD");
                assertEquals(Letter.Status.NOT_IN_WORD, wolfle.getGridLetterStatus(1, 2),
                                "Test grid letter status");
                assertEquals(Letter.Status.IN_POSITION, wolfle.getGridLetterStatus(0, 0),
                                "Test first row grid letter status");
        }

        /**
         * Test the Wolfle methods with invalid values
         */
        @Test
        public void testExceptions() {

                Exception e = assertThrows(IllegalArgumentException.class,
                                () -> wolfle.getLetterStatus(-1));
                assertEquals("Invalid index", e.getMessage(),
                                "getLetterStatus Invalid index IllegalArgumentException message for negative index");
                e = assertThrows(IllegalArgumentException.class,
                                () -> wolfle.getLetterStatus(26));
                assertEquals("Invalid index", e.getMessage(),
                                "getLetterStatus Invalid index IllegalArgumentException message for index too big");
                e = assertThrows(IllegalArgumentException.class,
                                () -> wolfle.getGridLetterStatus(-1, -2));
                assertEquals("Invalid row", e.getMessage(),
                                "getGridLetterStatus Invalid row IllegalArgumentException message for negative row");
                e = assertThrows(IllegalArgumentException.class,
                                () -> wolfle.getGridLetterStatus(6, -2));
                assertEquals("Invalid row", e.getMessage(),
                                "getGridLetterStatus Invalid row IllegalArgumentException message for row too big");
                e = assertThrows(IllegalArgumentException.class,
                                () -> wolfle.getGridLetterStatus(4, -1));
                assertEquals("Invalid col", e.getMessage(),
                                "getGridLetterStatus Invalid col IllegalArgumentException message for negative col");
                e = assertThrows(IllegalArgumentException.class,
                                () -> wolfle.getGridLetterStatus(4, 5));
                assertEquals("Invalid col", e.getMessage(),
                                "getGridLetterStatus Invalid col IllegalArgumentException message for col too big");
                e = assertThrows(IllegalArgumentException.class,
                                () -> wolfle.evaluateGuess(null));
                assertEquals("Null guess", e.getMessage(),
                                "evaluateGuess Null guess IllegalArgumentException message");
                e = assertThrows(IllegalArgumentException.class,
                                () -> wolfle.processGuess(null, 8));
                assertEquals("Null guess", e.getMessage(),
                                "processGuess Null guess IllegalArgumentException message");
                e = assertThrows(IllegalArgumentException.class,
                                () -> wolfle.processGuess("HAPPY", -1));
                assertEquals("Invalid grid row", e.getMessage(),
                                "processGuess Invalid grid row IllegalArgumentException message for negative row");
                e = assertThrows(IllegalArgumentException.class,
                                () -> wolfle.processGuess("HAPPY", 6));
                assertEquals("Invalid grid row", e.getMessage(),
                                "processGuess Invalid grid row IllegalArgumentException message for row too big");
                e = assertThrows(IllegalArgumentException.class,
                                () -> new Wolfle("xxx"));
                assertEquals("Invalid secret word", e.getMessage(),
                                "Wolfle Invalid secret word IllegalArgumentException message for invalid secret word");

        }

}