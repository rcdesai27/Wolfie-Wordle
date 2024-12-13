import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Project4.src.Letter;

//TODO: Add documentation to pass checkstyle

/**
 * Tests Letter class
 * 
 * @author Suzanne Balik
 * @author Michelle Glatz
 * @author Roshni Desai
 */
public class LetterTest {

    /** letterH */
    private Letter letterH;

    /** default letter ('_') */
    private Letter defaultLetter;

    /**
     * Sets up fields for testing
     */
    @BeforeEach
    public void setUp() {
        letterH = new Letter('H');
        defaultLetter = new Letter();
    }

    /**
     * Tests values for public constants and enum values
     */
    @Test
    public void testClassConstants() {
        assertEquals('A', Letter.FIRST_LETTER, "Test FIRST_LETTER constant");
        assertEquals('Z', Letter.LAST_LETTER, "Test LAST_LETTER constant");

        // Testing enums, test will fail if they don't exist
        Letter.Status status;
        status = Letter.Status.valueOf("NOT_GUESSED");
        status = Letter.Status.valueOf("NOT_IN_WORD");
        status = Letter.Status.valueOf("IN_WORD");
        status = Letter.Status.valueOf("IN_POSITION");
    }

    /**
     * Test invalid constructor parameter
     */
    @Test
    public void testInvalidConstructorParameter() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Letter('x'),
                "Invalid letter");
        assertEquals("Invalid letter", exception.getMessage(),
                "Invalid letter - exception message");

        exception = assertThrows(IllegalArgumentException.class, () -> new Letter(' '),
                "Invalid letter");
        assertEquals("Invalid letter", exception.getMessage(),
                "Invalid letter - exception message");

    }

    /**
     * Test invalid setLetter parameter
     */
    @Test
    public void testInvalidSetLetterParameter() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> defaultLetter.setLetter(' '), "Invalid letter");
        assertEquals("Invalid letter", exception.getMessage(),
                "Invalid letter - exception message");

        exception = assertThrows(IllegalArgumentException.class,
                () -> defaultLetter.setLetter('a'), "Invalid letter");
        assertEquals("Invalid letter", exception.getMessage(),
                "Invalid letter - exception message");

        exception = assertThrows(IllegalArgumentException.class,
                () -> defaultLetter.setLetter('1'), "Invalid letter");
        assertEquals("Invalid letter", exception.getMessage(),
                "Invalid letter - exception message");

    }

    /**
     * Test getLetter for letter H
     */
    @Test
    public void testGetLetterForLetterH() {
        assertEquals('H', letterH.getLetter(), "Test getLetter for letter H");
    }

    /**
     * Tests getLetter for default letter
     */
    @Test
    public void testGetLetterForDefaultLetter() {
        assertEquals('_', defaultLetter.getLetter(), "Test getLetter for default letter");
    }

    /**
     * Test getStatus for letter H
     */
    @Test
    public void testGetStatusForLetterH() {
        assertEquals(Letter.Status.NOT_GUESSED, letterH.getStatus(), "Test getStatus for letter H");
    }

    /**
     * Tests getStatus for default letter
     */
    @Test
    public void testGetStatusForDefaultLetter() {
        assertEquals(Letter.Status.NOT_GUESSED, defaultLetter.getStatus(),
                "Test getStatus for default letter");
    }

    /**
     * Test setLetter by changing letter H to a 'X'
     */
    @Test
    public void testSetLetterHtoX() {
        letterH.setLetter('X');
        assertEquals('X', letterH.getLetter(), "Test setLetter to change letter H");
        letterH.setLetter('H');
        assertEquals('H', letterH.getLetter(), "Test setLetter to reset letter H");
    }

    /**
     * Tests setLetter by changing default letter to 'Z'
     */
    @Test
    public void testSetLetterDefaultToZ() {
        defaultLetter.setLetter('Z');
        assertEquals('Z', defaultLetter.getLetter(), "Test setLetter for default letter to Z");
    }

    /**
     * Test setStatus by changing status of letter H to IN_WORD
     */
    @Test
    public void testSetStatusForLetterH() {
        letterH.setStatus(Letter.Status.IN_WORD);
        assertEquals(Letter.Status.IN_WORD, letterH.getStatus(),
                "Test setStatus to change status of letter H");
    }

    /**
     * Tests setStatus by changing status of default letter to NOT_IN_WORD
     */
    @Test
    public void testSetStatusForDefaultLetter() {
        defaultLetter.setStatus(Letter.Status.NOT_IN_WORD);
        assertEquals(Letter.Status.NOT_IN_WORD, defaultLetter.getStatus(),
                "Test setStatus for default letter to NOT_IN_WORD");
    }

    /**
     * Test toString for letter H
     */
    @Test
    public void testToStringForLetterH() {
        assertEquals("H NOT_GUESSED", letterH.toString(), "Test toString for letter H");
    }

    /**
     * Tests toString for default letter after changing its status to IN_POSITION
     */
    @Test
    public void testToStringForDefaultLetter() {
        defaultLetter.setStatus(Letter.Status.IN_POSITION);
        assertEquals("_ IN_POSITION", defaultLetter.toString(),
                "Test toString for default letter after status change");
    }

    /**
     * Test equals for letter H with itself
     */
    @Test
    public void testEqualsLetterHwithItself() {
        assertTrue(letterH.equals(letterH), "Test equals for letter H with itself");
    }

    /**
     * Test equals for letter H with another letter H object
     */
    @Test
    public void testEqualsLetterHwithAnotherLetterHObject() {
        Letter letterH2 = new Letter('H');
        assertTrue(letterH2.equals(letterH),
                "Test equals for letter H with another letter H object");
    }

    /**
     * Test equals for letter H with null
     */
    @Test
    public void testEqualsLetterHwithNull() {
        assertFalse(letterH.equals(null),
                "Test equals for letter H with null");
    }

    /**
     * Test equals for letter H with a String
     */
    @Test
    public void testEqualsLetterHwithString() {
        assertFalse(letterH.equals("letterH"),
                "Test equals for letter H with String");
    }

    /**
     * Test equals for letter H with another Letter H that has a different status
     */
    @Test
    public void testEqualsLetterHwithAnotherLetterHwithDifferentStatus() {
        Letter letterH2 = new Letter('H');
        letterH2.setStatus(Letter.Status.IN_WORD);
        assertFalse(letterH2.equals(letterH),
                "Test equals for letter H with another letter H with a different status");
    }

    /**
     * Test equals for letter H with a different letter that has the same status
     */
    @Test
    public void testEqualsLetterHwithAnotherLetterwithSameStatus() {
        Letter letterM = new Letter('M');
        assertFalse(letterM.equals(letterH),
                "Test equals for NOT_GUESSED letters M and H");
    }

    /**
     * Tests equals for letters with different characters and statuses
     */
    @Test
    public void testEqualsLetterHwithAnotherLetterwithDifferentStatus() {
        Letter letterM = new Letter('M');
        letterM.setStatus(Letter.Status.IN_WORD);
        assertFalse(letterH.equals(letterM),
                "Test equals for different characters and statuses");
    }
}
