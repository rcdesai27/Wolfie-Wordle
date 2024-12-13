import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Project4.src.Grid;
import Project4.src.Letter;

//TODO: Add documentation to pass checkstyle

/**
 * Tests Grid class
 * 
 * @author Suzanne Balik
 * @author Michelle Glatz
 * @author Roshni Desai
 */
public class GridTest {

        /** Grid for testing */
        private Grid grid;

        /**
         * Sets up field for testing
         */
        @BeforeEach
        public void setUp() {
                grid = new Grid(2, 3);
        }

        /**
         * Test getRows
         */
        @Test
        public void testGetRows() {
                assertEquals(2, grid.getRows(), "Test getRows");

        }

        /**
         * Test getCols
         */
        @Test
        public void testGetCols() {
                assertEquals(3, grid.getCols(), "Test getCols");

        }

        /**
         * test getGrid after grid constructed
         */
        @Test
        public void testGetGrid() {
                Letter[][] letterGrid = grid.getGrid();
                for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < 3; j++) {
                                assertEquals(letterGrid[i][j], new Letter());
                        }
                }
        }

        /**
         * Test getStatus after grid constructed
         */
        @Test
        public void testGetStatusAfterConstructed() {
                for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < 3; j++) {
                                assertEquals(Letter.Status.NOT_GUESSED, grid.getStatus(i, j),
                                                "Test getStatus for all letters after construction");
                        }
                }
        }

        /**
         * Test getStatus after a letter in the grid is updated to another status
         */
        @Test
        public void testGetStatusAfterUpdated() {
                grid.updateLetter(1, 2, 'Q', Letter.Status.IN_WORD);
                assertEquals(Letter.Status.IN_WORD, grid.getStatus(1, 2), "Test getStatus after update");
        }

        /**
         * Test getLetter after grid constructed
         */
        @Test
        public void testGetLetterAfterConstructed() {
                for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < 3; j++) {
                                assertEquals('_', grid.getLetter(i, j),
                                                "Test getLetter for all letters after construction");
                        }
                }
        }

        /**
         * Test getLetter after a letter in the grid is updated to another letter
         */
        @Test
        public void testGetLetterAfterUpdated() {
                grid.updateLetter(0, 1, 'A', Letter.Status.IN_WORD);
                assertEquals('A', grid.getLetter(0, 1), "Test getLetter after update");
        }

        /**
         * Test updateLetter
         */
        @Test
        public void testUpdateLetter() {
                grid.updateLetter(0, 0, 'S', Letter.Status.IN_WORD);
                assertEquals(Letter.Status.IN_WORD, grid.getStatus(0, 0),
                                "Test updateLetter(0, 0, 'S', Letter.Status.IN_WORD)");
                assertEquals('S', grid.getLetter(0, 0),
                                "Test updateLetter(0, 0, 'S', Letter.Status.IN_WORD)");
                for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < 3; j++) {
                                if (i != 0 && j != 0) {
                                        assertEquals('_', grid.getLetter(i, j),
                                                        "Test letters that weren't updated are unchanged");
                                }
                        }
                }

        }

        /**
         * Test toString after constructed
         */
        @Test
        public void testToStringAfterConstructor() {
                String expected = "|_ NOT_GUESSED|_ NOT_GUESSED|_ NOT_GUESSED|\n" +
                                "|_ NOT_GUESSED|_ NOT_GUESSED|_ NOT_GUESSED|\n";
                assertEquals(expected, grid.toString(), "Test toString after constructing grid");
        }

        /**
         * Test toString after updated
         */
        @Test
        public void testToStringAfterUpdated() {
                String expected = "|A NOT_GUESSED|B NOT_IN_WORD|C IN_WORD|\n" +
                                "|X IN_POSITION|_ NOT_GUESSED|_ NOT_GUESSED|\n";
                grid.updateLetter(0, 0, 'A', Letter.Status.NOT_GUESSED);
                grid.updateLetter(0, 1, 'B', Letter.Status.NOT_IN_WORD);
                grid.updateLetter(0, 2, 'C', Letter.Status.IN_WORD);
                grid.updateLetter(1, 0, 'X', Letter.Status.IN_POSITION);
                assertEquals(expected, grid.toString(), "Test toString after updating grid");
        }

        /**
         * Test equals with equal grids
         */
        @Test
        public void testEquals1() {
                Grid grid2 = new Grid(2, 3);
                assertTrue(grid.equals(grid2), "Test equal grids");
        }

        /**
         * Test equals for grid with itself
         */
        @Test
        public void testEqualsGridWithItself() {
                assertTrue(grid.equals(grid), "Test equals for grid with itself");
        }

        /**
         * Test equals for grid with another equal grid object
         */
        @Test
        public void testEqualsWithEqualGrid() {
                Grid grid2 = new Grid(2, 3);
                assertTrue(grid.equals(grid2), "Test equal grid with an equal grid");
        }

        /**
         * Test equals for grid with another grid with different rows
         */
        @Test
        public void testEqualsDifferentRows() {
                Grid grid2 = new Grid(3, 3);
                assertFalse(grid.equals(grid2), "Test equals for grids with different rows");
        }

        /**
         * Test equals for grid with another grid with different cols
         */
        @Test
        public void testEqualsDifferentCols() {
                Grid grid2 = new Grid(2, 4);
                assertFalse(grid.equals(grid2), "Test equals for grids with different cols");
        }

        /**
         * Test equals for grid with another grid of same size but
         * different letters with different statuses
         */
        @Test
        public void testEqualsDifferentLetters() {
                Grid grid2 = new Grid(2, 3);
                grid2.updateLetter(1, 2, 'Q', Letter.Status.NOT_GUESSED);
                assertFalse(grid.equals(grid2),
                                "Test equal grid with an equal size grid with different letters");
        }

        /**
         * Test equals for grid with null
         */
        @Test
        public void testEqualsNull() {
                assertFalse(grid.equals(null), "Test equals for grid with null ");
        }

        /**
         * Test equals for grid with a String
         */
        @Test
        public void testEqualsString() {
                assertFalse(grid.equals("grid"), "Test equals for grid with a String ");
        }

        /**
         * Test the Grid methods with invalid values
         */
        @Test
        public void testInvalidMethods() {

                // Invalid test cases are provided for you below - You do NOT
                // need to add additional invalid tests. Just make sure these
                // pass!

                // Test invalid constructor parameters
                Exception exception = assertThrows(IllegalArgumentException.class, () -> new Grid(0, 0),
                                "invalid rows");
                assertEquals("Invalid rows", exception.getMessage(), "invalid rows - exception message");
                exception = assertThrows(IllegalArgumentException.class, () -> new Grid(2, -1),
                                "invalid cols");
                assertEquals("Invalid cols", exception.getMessage(), "invalid cols - exception message");

                // Test invalid row and col - parameters for getLetter(), getStatus(), and
                // updateLetter()
                exception = assertThrows(IllegalArgumentException.class,
                                () -> grid.getLetter(2, 1),
                                "grid.getLetter(2, 1)");
                assertEquals("Invalid row", exception.getMessage(),
                                "Testing grid.getLetter(2, 1) - exception message");

                exception = assertThrows(IllegalArgumentException.class,
                                () -> grid.getStatus(2, 1),
                                "grid.getStatus(2, 1)");
                assertEquals("Invalid row", exception.getMessage(),
                                "Testing grid.getStatus(2, 1) - exception message");

                exception = assertThrows(IllegalArgumentException.class,
                                () -> grid.updateLetter(2, 1, 'A', Letter.Status.IN_WORD),
                                "grid.updateLetter(2, 1, 'A', Letter.Status.IN_WORD)");
                assertEquals("Invalid row", exception.getMessage(),
                                "Testing grid.updateLetter(2, 1, 'A', Letter.Status.IN_WORD) - exception message");

                exception = assertThrows(IllegalArgumentException.class,
                                () -> grid.getLetter(1, 3),
                                "grid.getLetter(1, 3)");
                assertEquals("Invalid col", exception.getMessage(),
                                "Testing grid.getLetter(1, 3) - exception message");

                exception = assertThrows(IllegalArgumentException.class,
                                () -> grid.getStatus(1, 3),
                                "grid.getStatus(1, 3)");
                assertEquals("Invalid col", exception.getMessage(),
                                "Testing grid.getStatus(1, 3) - exception message");

                exception = assertThrows(IllegalArgumentException.class,
                                () -> grid.updateLetter(1, 3, 'A', Letter.Status.IN_WORD),
                                "grid.updateLetter(1, 3, 'A', Letter.Status.IN_WORD)");
                assertEquals("Invalid col", exception.getMessage(),
                                "Testing grid.updateLetter(1, 3, 'A', Letter.Status.IN_WORD) - exception message");

                exception = assertThrows(IllegalArgumentException.class,
                                () -> grid.getLetter(-1, 1),
                                "grid.getLetter(-1, 1)");
                assertEquals("Invalid row", exception.getMessage(),
                                "Testing grid.getLetter(-1, 1) - exception message");

                exception = assertThrows(IllegalArgumentException.class,
                                () -> grid.getStatus(-1, 1),
                                "grid.getStatus(-1, 1)");
                assertEquals("Invalid row", exception.getMessage(),
                                "Testing grid.getStatus(-1, 1) - exception message");

                exception = assertThrows(IllegalArgumentException.class,
                                () -> grid.updateLetter(-1, 1, 'A', Letter.Status.IN_WORD),
                                "grid.updateLetter(-1, 1, 'A', Letter.Status.IN_WORD)");
                assertEquals("Invalid row", exception.getMessage(),
                                "Testing grid.updateLetter(-1, 1, 'A', Letter.Status.IN_WORD) - exception message");

                exception = assertThrows(IllegalArgumentException.class,
                                () -> grid.getLetter(1, -1),
                                "grid.getLetter(1, -1)");
                assertEquals("Invalid col", exception.getMessage(),
                                "Testing grid.getLetter(1, -1) - exception message");

                exception = assertThrows(IllegalArgumentException.class,
                                () -> grid.getStatus(1, -1),
                                "grid.getStatus(1, -1)");
                assertEquals("Invalid col", exception.getMessage(),
                                "Testing grid.getStatus(1, -1) - exception message");

                exception = assertThrows(IllegalArgumentException.class,
                                () -> grid.updateLetter(1, -1, 'A', Letter.Status.IN_WORD),
                                "grid.updateLetter(1, -1, 'A', Letter.Status.IN_WORD)");
                assertEquals("Invalid col", exception.getMessage(),
                                "Testing grid.updateLetter(1, -1, 'A', Letter.Status.IN_WORD) - exception message");

                exception = assertThrows(IllegalArgumentException.class,
                                () -> grid.getLetter(-1, -1),
                                "grid.getLetter(-1, -1)");
                assertEquals("Invalid row", exception.getMessage(),
                                "Testing grid.getLetter(-1, -1) - exception message");

                exception = assertThrows(IllegalArgumentException.class,
                                () -> grid.getStatus(-1, -1),
                                "grid.getStatus(-1, -1)");
                assertEquals("Invalid row", exception.getMessage(),
                                "Testing grid.getStatus(-1, -1) - exception message");

                exception = assertThrows(IllegalArgumentException.class,
                                () -> grid.updateLetter(-1, -1, 'A', Letter.Status.IN_WORD),
                                "grid.updateLetter(-1, -1, 'A', Letter.Status.IN_WORD)");
                assertEquals("Invalid row", exception.getMessage(),
                                "Testing grid.updateLetter(-1, -1, 'A', Letter.Status.IN_WORD) - exception message");

        }

}
