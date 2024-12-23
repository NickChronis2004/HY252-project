package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a player in the game.
 */
public class Player {
    private final String name;
    private final List<Card> cards;
    private final List<Pawn> pawns;
    private int score;
    private List<Finding> findings;

    /**
     * Creates a new player with name, cards, pawns, score, and findings.
     *
     * Preconditions:
     * - `name` is not null and not empty.
     * - `pawns` is not null and contains valid Pawn objects.
     *
     * Postconditions:
     * - The player's name is initialized.
     * - The player's cards list is empty.
     * - The player's score is set to 0.
     * - The player's findings list is empty.
     *
     * @param name The name of the player.
     * @param pawns The list of pawns assigned to the player.
     */
    public Player(String name, List<Pawn> pawns) {
        this.name = name;
        this.cards = new ArrayList<>();
        this.pawns = pawns;
        this.score = 0;
        this.findings = new ArrayList<>();
    }

    /**
     * Gets the name of the player.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - Returns the player's name as a string.
     *
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the player's hand of cards.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - Returns the list of cards the player currently has in hand.
     *
     * @return The list of cards in the player's hand.
     */
    public List<Card> getCards() {
        return cards;
    }


    /**
     * Adds score to the player's score.
     *
     * Preconditions:
     * - `points` is greater than or equal to 0.
     *
     * Postconditions:
     * - The player's score is increased by the specified number of points.
     *
     * @param points The amount of points to add to the player's score.
     */
    public void addScore(int points) {
        this.score += points;
    }

    /**
     * Gets the player's current score.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - Returns the player's score as an integer.
     *
     * @return The player's score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Plays a card on a path.
     *
     * Preconditions:
     * - `card` is not null.
     * - `path` is not null.
     * - `card` is playable on the given path (validity checked externally).
     *
     * Postconditions:
     * - The card is removed from the player's hand.
     * - The card is played on the specified path.
     *
     * @param card The card to play.
     * @param pathIndex The index of the path to play the card on.
     * @return The card that was played.
     */
    public Card playCard(Card card, int pathIndex, Board board) {
        // check if the player holds the card
        if (!cards.contains(card)) {
            throw new IllegalArgumentException("The card is not in the player's hand");
        }

        // check if the card is playable on the path
        Card lastCard = board.getLastPlayedCard(pathIndex);
        if (!card.isPlayable(lastCard)) {
            throw new IllegalArgumentException("The card is not playable on the path");
        }

        // remove the card from the player's hand
        cards.remove(card);

        // update the path or move the pawn
        Path path = board.getPathByIndex(pathIndex);
        Pawn pawn = getPawnOnPath(path);
        if (pawn == null) {
            throw new IllegalStateException("No pawn available on this path");
        }

        if (card instanceof NumberCard) {
            pawn.move(((NumberCard) card).getValue());
        } else if (card instanceof AriadneCard) {
            pawn.move(2);
        }

        // update the last played card for the path
        board.setLastPlayedCard(pathIndex, card);

        return card;
    }


    /**
     * Draws a card from the deck.
     *
     * Preconditions:
     * - `deck` is not null.
     * - The deck contains at least one card.
     *
     * Postconditions:
     * - A card is drawn from the deck.
     * - The drawn card is added to the player's hand.
     *
     * @param deck The deck to draw from.
     * @return The card that was drawn.
     */
    public Card drawCard(Deck deck) {
        if (deck == null) {
            throw new IllegalArgumentException("deck cannot be null.");
        }
        Card drawnCard = deck.draw(); // draw a card from the deck
        cards.add(drawnCard); // add the card to the player's hand
        return drawnCard;
    }


    /**
     * Checks if the player has an available pawn on a path.
     *
     * Preconditions:
     * - `path` is not null.
     *
     * Postconditions:
     * - Returns true if at least one pawn is available on the given path.
     *
     * @param path The path to check.
     * @return True if the player has an available pawn, false otherwise.
     */
    public boolean hasAvailablePawn(Path path) {
        if (path == null) {
            throw new IllegalArgumentException("path cannot be null.");
        }
        for (Pawn pawn : pawns) {
            // check if pawn is on the path and not at the end
            if (pawn.getCurrentPath() == path && pawn.getCurrentPosition() < path.getLength()) {
                return true;
            }
        }
        return false;
    }


    /**
     * Retrieves the first pawn of the player that is currently on the specified path.
     *
     * Preconditions:
     * - `path` is not null.
     *
     * Postconditions:
     * - Returns the first pawn on the specified path, or null if no pawn is found.
     *
     * @param path The path to search for a pawn.
     * @return The pawn on the specified path, or null if no pawn exists there.
     */
    public Pawn getPawnOnPath(Path path) {
        for (Pawn pawn : pawns) {
            // check if the pawn is on the specified path
            if (pawn.getCurrentPath() == path) {
                return pawn;
            }
        }
        return null; // no pawn found in this path
    }

    /**
     * Allows the player to select a path from the available ones.
     *
     * preconditions:
     * - none
     *
     * postconditions:
     * - returns the selected path
     *
     * @return the selected path
     */
    public Path selectPath() {
        System.out.println("Available paths:");
        Board board = new Board(new ArrayList<>(), new Deck());
        for (Path path : board.getPaths()) {
            if (!path.isCompleted()) {
                System.out.println("- " + path.getPalaceName());
            }
        }

        // get input from the user
        System.out.print("Select a path by entering the palace name: ");
        Scanner scanner = new Scanner(System.in);
        String palaceName = scanner.nextLine();

        Path selectedPath = board.getPath(palaceName);
        if (selectedPath == null || selectedPath.isCompleted()) {
            throw new IllegalArgumentException("Invalid or completed path selected.");
        }

        return selectedPath;
    }
}