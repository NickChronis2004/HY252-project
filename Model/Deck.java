package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Represents the deck of the game.
 */
public class Deck {
    private final List<Card> cards;
    private final Stack<Card> discards;

    /**
     * Creates a new empty deck.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - The deck is initialized with an empty list of cards.
     * - The discard pile is empty.
     */
    public Deck() {
        this.cards = new ArrayList<>();
        this.discards = new Stack<>();
    }

    /**
     * Shuffles the deck.
     *
     * Preconditions:
     * - The deck contains cards.
     *
     * Postconditions:
     * - The order of the cards in the deck is randomized.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Draws a card from the deck.
     *
     * Preconditions:
     * - The deck is not empty.
     *
     * Postconditions:
     * - Removes a card from the top of the deck.
     * - Returns the drawn card.
     *
     * @return The top card of the deck.
     * @throws IllegalStateException if the deck is empty.
     */
    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Cannot draw from an empty deck.");
        }
        return cards.remove(cards.size() - 1);
    }

    /**
     * Discards a card to the discard pile.
     *
     * Preconditions:
     * - `card` is not null.
     *
     * Postconditions:
     * - Adds the given card to the top of the discard pile.
     *
     * @param card The card to discard.
     */
    public void discard(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Card cannot be null.");
        }
        discards.push(card);
    }

    /**
     * Gets the number of cards left in the deck.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - Returns the number of cards currently in the deck as a non-negative integer.
     *
     * @return The number of cards left in the deck.
     */
    public int getRemainingCards() {
        return cards.size();
    }

    /**
     * Reshuffles all discarded cards back into the main deck.
     *
     * Preconditions:
     * - The discard pile is not empty.
     *
     * Postconditions:
     * - All cards from the discard pile are added back to the deck.
     * - The deck is shuffled.
     */
    public void reshuffleDiscards() {
        while (!discards.isEmpty()) {
            cards.add(discards.pop());
        }
        shuffle();
    }

    /**
     * Generates the regular findings (statues and frescoes).
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - Returns a list of regular findings (statues and frescoes).
     *
     * @return A list of regular findings.
     */
    public List<Finding> generateRegularFindings() {
        List<Finding> findings = new ArrayList<>();

        // add statues (10 statues)
        for (int i = 0; i < 10; i++) {
            findings.add(new SnakeGoddess("Statue " + (i + 1), 10)); // example value of 10
        }

        // add frescoes (6 frescoes with varying values)
        findings.add(new Fresco("Fresco 1", 20));
        findings.add(new Fresco("Fresco 2", 20));
        findings.add(new Fresco("Fresco 3", 15));
        findings.add(new Fresco("Fresco 4", 15));
        findings.add(new Fresco("Fresco 5", 15));
        findings.add(new Fresco("Fresco 6", 20));

        return findings;
    }
}