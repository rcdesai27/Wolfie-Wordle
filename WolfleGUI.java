import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * This class creates a graphical representation of a Wolfle game.
 * 
 * @author Suzanne Balik
 * @author Michelle Glatz
 */
public class WolfleGUI extends JFrame implements ActionListener {

    /** Width of GUI */
    public static final int WIDTH = 715;

    /** Height of GUI */
    public static final int HEIGHT = 440;

    /** X position of upperleft hand corner of GUI */
    public static final int X = 100;

    /** Y position of upperleft hand corner of GUI */
    public static final int Y = 100;

    /** Font used for letter buttons */
    public static final Font LETTER = new Font("Courier", 1, 20);

    /** Font used for message and letters in guessed words */
    public static final Font WORD = new Font("Courier", 1, 30);

    /** Background color of letter that is not in the secret word */
    public static final Color NOT_IN_WORD = Color.LIGHT_GRAY;

    /**
     * Background color of letter that is in the secret word but not in the correct
     * position
     */
    public static final Color IN_WORD = Color.BLACK;

    /**
     * Background color of letter that is in the correct position in the secret word
     */
    public static final Color IN_POSITION = Color.RED;

    /** Foreground color of letter that has been guessed */
    public static final Color GUESSED = Color.WHITE;

    /** Number of rows of letters */
    public static final int LTR_ROWS = 3;

    /** Number of columns of letters */
    public static final int LTR_COLS = 9;

    /** Message label */
    private JLabel messageLabel;

    /** Blank label used for layout */
    private JLabel blankLabel;

    /** Labels for letters in guessed words */
    private JLabel[][] wordLabels;

    /** Letter buttons */
    private JButton[] letterButtons;

    /** Enter button */
    private JButton enterButton;

    /** Back button */
    private JButton backButton;

    /** Top panel */
    private JPanel topPanel;

    /** Panel for guessed words */
    private JPanel wordPanel;

    /** Blank panel used for layout */
    private JPanel blankEastPanel;

    /** Blank panel used for layout */
    private JPanel blankWestPanel;

    /** Bottom panel */
    private JPanel bottomPanel;

    /** Panel for letter and back buttons */
    private JPanel letterPanel;

    /** Current row for guessed words */
    private int currentRow;

    /** Current column for guessed words */
    private int currentCol;

    /** Wolfle game instance */
    private Wolfle wolfle;

    /**
     * Creates GUI used to play Wolfle game
     * 
     * @param secretWordForTesting secret word used for testing
     */
    public WolfleGUI(String secretWordForTesting) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("Wolfle");
        setSize(WIDTH, HEIGHT);
        setLocation(X, Y);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        wolfle = new Wolfle(secretWordForTesting);

        // Set up top panel with message and word grid
        messageLabel = new JLabel("Guess the secret word!", JLabel.CENTER);
        messageLabel.setFont(WORD);
        blankLabel = new JLabel("     ", JLabel.CENTER);
        wordPanel = new JPanel();
        wordPanel.setLayout(new GridLayout(Wolfle.ROWS, Wolfle.COLS));
        wordLabels = new JLabel[Wolfle.ROWS][Wolfle.COLS];
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        Border empty = BorderFactory.createEmptyBorder(2, 2, 2, 2);
        for (int i = 0; i < Wolfle.ROWS; i++) {
            for (int j = 0; j < Wolfle.COLS; j++) {
                wordLabels[i][j] = new JLabel(" ", JLabel.CENTER);
                wordLabels[i][j].setBorder(new CompoundBorder(empty, border));
                wordLabels[i][j].setFont(WORD);
                wordLabels[i][j].setOpaque(true);
                wordPanel.add(wordLabels[i][j]);
            }
        }
        blankEastPanel = new JPanel();
        blankEastPanel.setLayout(new GridLayout(Wolfle.ROWS, 2));
        for (int i = 0; i < Wolfle.ROWS; i++) {
            JLabel blank = new JLabel("   ");
            blank.setFont(WORD);
            blankEastPanel.add(blank);
            blank = new JLabel("    ");
            blank.setFont(WORD);
            blankEastPanel.add(blank);
        }
        blankWestPanel = new JPanel();
        blankWestPanel.setLayout(new GridLayout(Wolfle.ROWS, 2));
        for (int i = 0; i < Wolfle.ROWS; i++) {
            JLabel blank = new JLabel("   ");
            blank.setFont(WORD);
            blankWestPanel.add(blank);
            blank = new JLabel("    ");
            blank.setFont(WORD);
            blankWestPanel.add(blank);
        }
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(messageLabel, BorderLayout.NORTH);
        topPanel.add(wordPanel, BorderLayout.CENTER);
        topPanel.add(blankEastPanel, BorderLayout.EAST);
        topPanel.add(blankWestPanel, BorderLayout.WEST);
        topPanel.add(blankLabel, BorderLayout.SOUTH);

        // Setup bottom panel with letter, back, and enter buttons
        letterPanel = new JPanel();
        letterPanel.setLayout(new GridLayout(LTR_ROWS, LTR_COLS));

        // Create letter buttons
        letterButtons = new JButton[Alphabet.MAX_LETTERS];
        for (int i = 0; i < Alphabet.MAX_LETTERS; i++) {
            letterButtons[i] = new JButton("" + (char) ('A' + i));
            letterButtons[i].setFont(LETTER);
            letterButtons[i].addActionListener(this);
        }
        // Fill in row 1 with A - I
        for (int i = 0; i < LTR_COLS; i++) {
            letterPanel.add(letterButtons[i]);
        }
        // Fill in row 2 with J - R
        for (int i = LTR_COLS; i < 2 * LTR_COLS; i++) {
            letterPanel.add(letterButtons[i]);
        }
        // Fill in row 3 with S - Z and back button
        for (int i = 2 * LTR_COLS; i < Alphabet.MAX_LETTERS; i++) {
            letterPanel.add(letterButtons[i]);
        }
        backButton = new JButton("BACK");
        backButton.addActionListener(this);
        backButton.setEnabled(false);
        letterPanel.add(backButton);

        // Create enter button
        enterButton = new JButton("ENTER");
        enterButton.setFont(LETTER);
        enterButton.addActionListener(this);
        enterButton.setEnabled(false);

        // Add buttons to bottom panel
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(letterPanel, BorderLayout.CENTER);
        bottomPanel.add(enterButton, BorderLayout.SOUTH);

        Container c = getContentPane();
        c.add(topPanel, BorderLayout.CENTER);
        c.add(bottomPanel, BorderLayout.SOUTH);

        currentRow = 0;
        currentCol = 0;

        setVisible(true);
    }

    /**
     * Handle processing of back, enter, and letter buttons
     * 
     * @param e event to be processed
     */
    public void actionPerformed(ActionEvent e) {

        // Process BACK button
        if (e.getSource() == backButton) {
            currentCol--;
            wordLabels[currentRow][currentCol].setText(" ");
            for (int i = 0; i < Alphabet.MAX_LETTERS; i++) {
                letterButtons[i].setEnabled(true);
            }
            if (currentCol == 0) {
                backButton.setEnabled(false);
            }
            enterButton.setEnabled(false);
        }

        // Process ENTER button
        if (e.getSource() == enterButton) {
            // Get guess
            String guess = "";
            for (int i = 0; i < Wolfle.COLS; i++) {
                guess += wordLabels[currentRow][i].getText();
            }

            // Handle invalid guess
            if (!wolfle.processGuess(guess, currentRow)) {
                messageLabel.setText("INVALID WORD - GUESS AGAIN!");
            }
            // Process valid guess
            else {
                backButton.setEnabled(false);
                enterButton.setEnabled(false);
                if (currentRow < Wolfle.ROWS) {
                    // Update letter buttons
                    for (int i = 0; i < Alphabet.MAX_LETTERS; i++) {
                        Letter.Status status = wolfle.getLetterStatus(i);
                        if (status == Letter.Status.NOT_IN_WORD) {
                            letterButtons[i].setBackground(NOT_IN_WORD);
                            letterButtons[i].setForeground(GUESSED);
                        } else if (status == Letter.Status.IN_WORD) {
                            letterButtons[i].setBackground(IN_WORD);
                            letterButtons[i].setForeground(GUESSED);
                        } else if (status == Letter.Status.IN_POSITION) {
                            letterButtons[i].setBackground(IN_POSITION);
                            letterButtons[i].setForeground(GUESSED);
                        }
                    }

                    // Update word grid
                    for (int j = 0; j < Wolfle.COLS; j++) {
                        Letter.Status status = wolfle.getGridLetterStatus(currentRow, j);
                        wordLabels[currentRow][j].setForeground(GUESSED);
                        Border guessedBorder = BorderFactory.createLineBorder(Color.WHITE);
                        wordLabels[currentRow][j].setBorder(guessedBorder);
                        if (status == Letter.Status.NOT_IN_WORD) {
                            wordLabels[currentRow][j].setBackground(NOT_IN_WORD);
                        } else if (status == Letter.Status.IN_WORD) {
                            wordLabels[currentRow][j].setBackground(IN_WORD);
                        } else if (status == Letter.Status.IN_POSITION) {
                            wordLabels[currentRow][j].setBackground(IN_POSITION);
                        }
                    }
                    currentCol = 0;
                    // Check if game is over due to correct guess or no more guesses
                    if (wolfle.isGameOverCorrectGuess()) {
                        messageLabel.setText("You guessed correctly!");
                    } else if (wolfle.isGameOverNoMoreGuesses()) {
                        messageLabel.setText("The secret word is " +
                                wolfle.getSecretWord());
                    } else {
                        currentRow++;
                        messageLabel.setText("Guess the secret word!");
                        for (int i = 0; i < Alphabet.MAX_LETTERS; i++) {
                            letterButtons[i].setEnabled(true);
                        }
                    }
                }
                // In case there are no more rows, which should not happen
                else {
                    messageLabel.setText("The secret word is " +
                            wolfle.getSecretWord());
                }
            }
        }

        // Process letter button
        boolean found = false;
        for (int i = 0; i < Alphabet.MAX_LETTERS && !found; i++) {
            if (e.getSource() == letterButtons[i]) {
                found = true;
                wordLabels[currentRow][currentCol].setText("" + (char) ('A' + i));
                currentCol++;
                backButton.setEnabled(true);
                // If last letter added to a word, disable letters, enable ENTER button
                if (currentCol >= Wolfle.COLS) {
                    enterButton.setEnabled(true);
                    for (int j = 0; j < Alphabet.MAX_LETTERS; j++) {
                        letterButtons[j].setEnabled(false);
                    }
                }
            }
        }
    }

    /**
     * Start Wolfle GUI
     * 
     * @param args args[0] optional secret word for testing
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            new WolfleGUI("");
        } else if (args.length == 1) {
            new WolfleGUI(args[0]);
        } else {
            System.out.println("Usage: java -cp bin WolfleGUI <secret word>");
        }
    }
}
