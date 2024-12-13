package Project4.src;

public class Letter {

    /**
     * Enum to represent the status of a letter in the Wolfle game
     * Valid statuses include:
     * - NOT_GUESSED: letter has not been guessed
     * - NOT_IN_WORD: letter is not in the word
     * - IN_WORD: letter is in the word but not in the correct position
     * - IN_POSITION: letter is in the word and in the correct position
     */
    public enum Status {
        NOT_GUESSED, NOT_IN_WORD, IN_WORD, IN_POSITION
    }

    /**
     * constant for the first valid letter, 'A'
     */
    public static final char FIRST_LETTER = 'A';

    /**
     * constant for the last valid letter, 'Z'
     */
    public static final char LAST_LETTER = 'Z';

    /**
     * the character representing this letter A-Z or underscore
     */
    private char letter;

    /**
     * the status of this letter NOT_GUESSED, NOT_IN_WORD, IN_WORD, IN_POSITION
     */
    private Status status;

    /**
     * default constructor that initializes the letter to an underscore ('_') and
     * sets the status to NOT_GUESSED
     */
    public Letter() {
        this('_');
    }

    /**
     * constructor that initializes the letter and status to the given values.
     *
     * @param letter the character to set for this letter, must be A-Z or '_'
     * @param status the status to set for this letter
     * @throws IllegalArgumentException if the letter is not A-Z or '_' or if the
     *                                  status is invalid
     */
    public Letter(char letter) {
        setLetter(letter);
        this.status = Status.NOT_GUESSED;
    }

    /**
     * gets the character for this letter.
     *
     * @return the character representing this letter
     */
    public char getLetter() {
        return letter;
    }

    /**
     * gets the status for this letter
     *
     * @return the current status of this letter.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * sets the character for this letter
     *
     * @param letter the character to set for this letter, must be A-Z or '_'.
     * @throws IllegalArgumentException if the letter is not A-Z or '_'
     */
    public void setLetter(char letter) {
        if (letter >= FIRST_LETTER && letter <= LAST_LETTER) {
            this.letter = letter;
        } else if (letter == '_') {
            this.letter = letter;
        } else {
            throw new IllegalArgumentException("Invalid letter");
        }
    }

    /**
     * sets the status for this letter
     *
     * @param status the status to set for this letter, must be a valid Status
     *               value.
     * @throws IllegalArgumentException if the status is null or invalid
     */
    public void setStatus(Status status) {
        if (status == Status.NOT_GUESSED || status == Status.NOT_IN_WORD ||
                status == Status.IN_WORD || status == Status.IN_POSITION) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid status");
        }
    }

    /**
     * compares this Letter to another object for equality
     *
     * @param o the object to compare to this Letter
     * @return true if the object is a Letter with the same character and status,
     *         false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Letter letterObj = (Letter) o;
        return letter == letterObj.letter && status == letterObj.status;
    }

    /**
     * converts this Letter to a string representation
     *
     * @return a string consisting of the letter followed by a space and the
     *         status, like "A NOT_GUESSED"
     */
    @Override
    public String toString() {
        return letter + " " + status;
    }
}
