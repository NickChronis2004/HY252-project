package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Model.Card;

/**
 * Represents the GUI of the game.
 */
public class GUI extends JFrame {

    private JButton[] playerCards = new JButton[4]; // buttons for player's cards
    private JLabel[] positions; // labels for board positions
    private JLayeredPane basic_panel; // main panel for game display

    private JLayeredPane player1; // panel for player's cards
    private JLabel pawn; // label for the player's pawn
    private JTextArea infobox; // text area for game messages

    private ClassLoader cldr; // class loader for resource loading
    private boolean enabled = true; // flag to enable/disable GUI interactions

    /**
     * Creates a new GUI.
     */
    public GUI() {
        cldr = this.getClass().getClassLoader();
        this.setResizable(false);
        this.setTitle("Game Demo");
        this.setPreferredSize(new Dimension(520, 500));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Gets the player's cards.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - Returns the player's cards.
     *
     * @return The player's cards.
     */
    public JButton[] getPlayerCards() {
        return playerCards;
    }

    /**
     * Sets the player's cards.
     *
     * Preconditions:
     * - `playerCards` is not null and contains at least 4 buttons.
     *
     * Postconditions:
     * - The player's cards are set to the given array.
     *
     * @param playerCards The player's cards.
     */
    public void setPlayerCards(JButton[] playerCards) {
        this.playerCards = playerCards;
    }

    /**
     * Updates the card display at a specific position.
     *
     * Preconditions:
     * - `c` is not null.
     * - `position` is a valid index for `playerCards`.
     *
     * Postconditions:
     * - The card at the specified position is updated.
     *
     * @param c The card to display.
     * @param position The position in the player's hand to update.
     */
    public void updateCard(Card c, int position) {
        //TODO: Implement updateCard method
    }

    /**
     * Updates the pawn's position on the board.
     *
     * Preconditions:
     * - `position` is within the bounds of the board.
     *
     * Postconditions:
     * - The pawn is moved to the specified position.
     *
     * @param position The new position of the pawn.
     */
    public void updatePawn(int position) {
        //TODO: Implement updatePawn method
    }

    /**
     * Updates the infobox with a new message.
     *
     * Preconditions:
     * - `message` is not null.
     *
     * Postconditions:
     * - The infobox displays the new message.
     *
     * @param message The message to display.
     */
    public void updateInfobox(String message) {
        infobox.setText(message); // Update the text area
        basic_panel.repaint(); // Refresh the panel
    }

    /**
     * Initializes the player's cards.
     *
     * Preconditions:
     * - `cards` is not null and contains at least 4 cards.
     *
     * Postconditions:
     * - The player's cards are displayed on the GUI.
     *
     * @param cards The list of cards to initialize.
     */
    public void initCards(ArrayList<Card> cards) {
        //TODO: Implement initCards method
    }

    /**
     * Initializes all GUI components.
     *
     * Preconditions:
     * - `cards` is not null.
     *
     * Postconditions:
     * - The GUI is fully initialized and ready for use.
     *
     * @param cards The cards to initialize the GUI with.
     */
    public void initComponents(ArrayList<Card> cards) {
        //TODO: Implement initComponents method
    }

    /**
     * Displays a winning message to the user.
     *
     * Preconditions:
     * - `message` is not null.
     *
     * Postconditions:
     * - The message is displayed in a dialog.
     *
     * @param message The winning message to display.
     */
    public void WinningMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}