package Project4.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Wolfle {

    public static final int ROWS = 6;
    public static final int COLS = 5;

    private Alphabet alphabet;
    private Grid grid;
    private String secretWord;
    private boolean isGameOverCorrectGuess;
    private boolean isGameOverNoMoreGuesses;
    private String[] validWords;
    private String[] wolfleWords;

    /**
     * constructs a Wolfle game with the given secret word. Initializes the
     * alphabet, grid, and game state. Loads the valid words and wolfle words
     * from files. If the secret word is empty, selects a random word from
     * wolfleWords.
     *
     * @param secretWordForTesting is the secret word to use for testing. If
     *                             empty, a random word from wolfleWords is used.
     * @throws IllegalArgumentException if the secret word is invalid or files
     *                                  cannot be accessed.
     */
    public Wolfle(String secretWordForTesting) {
        alphabet = new Alphabet();
        grid = new Grid(ROWS, COLS);
        isGameOverCorrectGuess = false;
        isGameOverNoMoreGuesses = false;

        validWords = loadWords("word-files/ValidWordList.txt");
        wolfleWords = loadWords("word-files/WolfleWordList.txt");

        if (secretWordForTesting.length() == 0) {
            int randomIndex = (int) (Math.random() * wolfleWords.length);
            secretWord = wolfleWords[randomIndex];
        } else {
            if (contains(wolfleWords, secretWordForTesting)) {
                secretWord = secretWordForTesting;
            } else {
                throw new IllegalArgumentException("Invalid secret word");
            }
        }
    }

    /**
     * loads words from the specified file into an array.
     *
     * @param filePath is the path of the file to load.
     * @return an array of strings representing the words in the file.
     * @throws IllegalArgumentException if the file cannot be accessed.
     */
    private String[] loadWords(String filePath) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            int wordCount = 0;
            while (scanner.hasNextLine()) {
                wordCount++;
                scanner.nextLine();
            }

            String[] words = new String[wordCount];
            scanner = new Scanner(file);
            int index = 0;
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().trim();
                words[index] = word;
                index = index + 1;
            }
            scanner.close();
            return words;
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Unable to access file: " + filePath);
        }
    }

    /**
     * checks if a value exists in an array.
     *
     * @param array is the array to search.
     * @param value is the value to find.
     * @return true if the value is found in the array, false otherwise.
     */
    private boolean contains(String[] array, String value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * returns the secret word for the game.
     *
     * @return the secret word.
     */
    public String getSecretWord() {
        return secretWord;
    }

    /**
     * checks if the game is over due to a correct guess.
     *
     * @return true if the game is over due to a correct guess, false otherwise.
     */
    public boolean isGameOverCorrectGuess() {
        return isGameOverCorrectGuess;
    }

    /**
     * checks if the game is over due to no more guesses.
     *
     * @return true if the game is over due to no more guesses, false otherwise.
     */
    public boolean isGameOverNoMoreGuesses() {
        return isGameOverNoMoreGuesses;
    }

    /**
     * returns the status of a letter in the alphabet by its index.
     *
     * @param index is the index of the letter in the alphabet.
     * @return the status of the letter at the specified index.
     * @throws IllegalArgumentException if the index is out of bounds.
     */
    public Letter.Status getLetterStatus(int index) {
        if (index < 0 || index >= Alphabet.MAX_LETTERS) {
            throw new IllegalArgumentException("Invalid index");
        }
        return alphabet.getStatus(index);
    }

    /**
     * returns the status of a letter in the grid at the specified row and column.
     *
     * @param row is the row of the letter in the grid.
     * @param col is the column of the letter in the grid.
     * @return the status of the letter at the specified position.
     * @throws IllegalArgumentException if the row or column is out of bounds.
     */
    public Letter.Status getGridLetterStatus(int row, int col) {
        if (row < 0 || row >= ROWS) {
            throw new IllegalArgumentException("Invalid row");
        }
        if (col < 0 || col >= COLS) {
            throw new IllegalArgumentException("Invalid col");
        }
        return grid.getStatus(row, col);
    }

    /**
     * evaluates a guess by determining the status of each letter in the guess
     * compared to the secret word.
     *
     * @param guess is the guessed word to evaluate.
     * @return an array of Letters representing the status of each guessed letter.
     * @throws IllegalArgumentException if the guess is null.
     */
    public Letter[] evaluateGuess(String guess) {
        if (guess == null) {
            throw new IllegalArgumentException("Null guess");
        }

        if (!contains(validWords, guess)) {
            return null;
        }

        Letter[] evaluatedLetters = new Letter[COLS];
        for (int i = 0; i < COLS; i++) {
            char guessedChar = guess.charAt(i);
            evaluatedLetters[i] = new Letter(guessedChar);

            if (secretWord.charAt(i) == guessedChar) {
                evaluatedLetters[i].setStatus(Letter.Status.IN_POSITION);
            } else if (secretWord.indexOf(guessedChar) != -1) {
                evaluatedLetters[i].setStatus(Letter.Status.IN_WORD);
            } else {
                evaluatedLetters[i].setStatus(Letter.Status.NOT_IN_WORD);
            }
        }
        return evaluatedLetters;
    }

    /**
     * processes a guess by updating the grid and alphabet based on the evaluated
     * letters.
     *
     * @param guess   is the guessed word.
     * @param gridRow is the row in the grid to update.
     * @return true if the guess is valid, false otherwise.
     * @throws IllegalArgumentException if the guess is null or the grid row is
     *                                  invalid.
     */
    public boolean processGuess(String guess, int gridRow) {
        if (guess == null) {
            throw new IllegalArgumentException("Null guess");
        }

        if (gridRow < 0 || gridRow >= ROWS) {
            throw new IllegalArgumentException("Invalid grid row");
        }

        Letter[] evaluatedLetters = evaluateGuess(guess);

        if (evaluatedLetters == null) {
            return false;
        }

        for (int col = 0; col < COLS; col++) {
            char character = evaluatedLetters[col].getLetter();
            Letter.Status status = evaluatedLetters[col].getStatus();

            grid.updateLetter(gridRow, col, character, status);
            int alphabetIndex = character - 'A';
            alphabet.updateStatus(alphabetIndex, status);
        }

        if (guess.equals(secretWord)) {
            isGameOverCorrectGuess = true;
        } else if (gridRow == ROWS - 1) {
            isGameOverNoMoreGuesses = true;
        }

        return true;
    }
}
