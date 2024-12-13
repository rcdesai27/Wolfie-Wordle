package Project4.src;

public class Alphabet {

    /**
     * the maximum number of letters in the alphabet.
     */
    public static final int MAX_LETTERS = 26;

    /**
     * an array of Letter objects representing each letter of the alphabet.
     */
    private Letter[] letters;

    /**
     * constructs an Alphabet object by initializing an array of Letter objects.
     * Fills the array with letters from A to Z, each initialized with the default
     * status NOT_GUESSED.
     */
    public Alphabet() {
        letters = new Letter[MAX_LETTERS];

        for (int i = 0; i < MAX_LETTERS; i++) {
            char currentChar = (char) ('A' + i);
            letters[i] = new Letter(currentChar);
        }
    }

    /**
     * returns the array of Letter objects.
     *
     * @return an array of Letter objects.
     */
    public Letter[] getLetters() {
        return letters;
    }

    /**
     * returns the character of the Letter at the given index.
     *
     * @param index is the position of the Letter to retrieve.
     * @return the character of the Letter at the specified index.
     * @throws IllegalArgumentException if the index is less than 0 or greater than
     *                                  or equal to MAX_LETTERS.
     */
    public char getLetter(int index) {
        if (index < 0 || index >= MAX_LETTERS) {
            throw new IllegalArgumentException("Invalid index");
        }
        return letters[index].getLetter();
    }

    /**
     * returns the status of the Letter at the given index.
     *
     * @param index is the position of the Letter to retrieve.
     * @return the status of the Letter at the specified index.
     * @throws IllegalArgumentException if the index is less than 0 or greater than
     *                                  or equal to MAX_LETTERS.
     */
    public Letter.Status getStatus(int index) {
        if (index < 0 || index >= MAX_LETTERS) {
            throw new IllegalArgumentException("Invalid index");
        }
        return letters[index].getStatus();
    }

    /**
     * updates the status of the Letter at the given index based on specific
     * conditions.
     *
     * @param index  is the position of the Letter to update.
     * @param status is the new status to assign to the Letter.
     * @throws IllegalArgumentException if the index is less than 0 or greater than
     *                                  or equal to MAX_LETTERS.
     */
    public void updateStatus(int index, Letter.Status status) {
        if (index < 0 || index >= MAX_LETTERS) {
            throw new IllegalArgumentException("Invalid index");
        }

        Letter.Status currentStatus = letters[index].getStatus();

        if (currentStatus == Letter.Status.NOT_GUESSED) {
            letters[index].setStatus(status);
            return;
        }

        if (currentStatus == Letter.Status.IN_WORD && status == Letter.Status.IN_POSITION) {
            letters[index].setStatus(status);
            return;
        }
    }

    /**
     * checks if this Alphabet object is equal to another object by comparing the
     * letters array.
     *
     * @param o is the object to compare with this Alphabet.
     * @return true if the given object is an Alphabet with the same letters array,
     *         false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Alphabet alphabet = (Alphabet) o;
        for (int i = 0; i < MAX_LETTERS; i++) {
            if (!letters[i].equals(alphabet.letters[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * converts this Alphabet object to a string representation for debugging or
     * testing.
     *
     * @return a string representation of the Alphabet, with each Letter on a new
     *         line.
     */
    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < MAX_LETTERS; i++) {
            result = result + letters[i].toString() + "\n";
        }

        return result;
    }
}
