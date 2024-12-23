package Model;

/**
 * Represents a card in the game.
 */
public abstract class Card {
    private final String palace;

    /**
     * Creates a new card.
     *
     * Preconditions:
     * - `palace` is not null and not empty.
     *
     * Postconditions:
     * - The palace name is set to the given value.
     *
     * @param palace The name of the palace the card belongs to.
     */
    public Card(String palace) {
        this.palace = palace;
    }

    /**
     * Gets the name of the palace the card belongs to.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - Returns the name of the palace as a non-null, non-empty string.
     *
     * @return the name of the palace.
     */
    public String getPalace() {
        return palace;
    }

    /**
     * Checks if the card is playable.
     *
     * Preconditions:
     * - `previousCard` is not null.
     *
     * Postconditions:
     * - Returns true if the card can be played after `previousCard`.
     *
     * @param previousCard The previous card played.
     * @return True if the card is playable, false otherwise.
     */
    public boolean isPlayable(Card previousCard) {
        return true;
    }
}

/**
 * Number card with a value between 1 and 10.
 */
public class NumberCard extends Card {
    private final int value;

    /**
     * Creates a new number card.
     *
     * Preconditions:
     * - `value` is between 1 and 10.
     * - `palace` is not null and not empty.
     *
     * Postconditions:
     * - A number card is initialized with the given palace and value.
     *
     * @param palace The name of the palace the card belongs to.
     * @param value The value of the card.
     */
    public NumberCard(String palace, int value) {
        super(palace);
        this.value = value;
    }

    /**
     * Gets the value of the card.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - Returns the value of the card as an integer between 1 and 10.
     *
     * @return card's value.
     */
    public int getValue() {
        return value;
    }

    @Override
    public boolean isPlayable(Card previousCard) {
        //check if the previous card is a number card
        if (previousCard instanceof NumberCard) {
            //if yes ,then check if the value of the previous card is greater than the value of the current card
            return this.value >= ((NumberCard) previousCard).getValue();
        }
        return false;
    }
}

/**
 * Ariadne card with a number of steps (steps = +2).
 */
public class AriadneCard extends Card {
    private static final int steps =+ 2;

    /**
     * Creates a new Ariadne card.
     *
     * Preconditions:
     * - `palace` is not null and not empty.
     *
     * Postconditions:
     * - An Ariadne card is initialized with the given palace and fixed steps.
     *
     * @param palace The name of the palace the card belongs to.
     */
    public AriadneCard(String palace) {
        super(palace);
    }

    /**
     * Gets the number of steps the card has.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - Returns the number of steps as a constant value (2).
     *
     * @return card's steps.
     */
    public int getSteps() {
        return steps;
    }

    @Override
    public boolean isPlayable(Card previousCard) {
        //Ariadne card can be played every time
        return true;
    }
}

/**
 * Minotaur card with a method to attack a player and a path.
 */
public class MinotaurCard extends Card {
    private static final int damage =- 2;

    /**
     * Creates a new Minotaur card.
     *
     * Preconditions:
     * - `palace` is not null and not empty.
     *
     * Postconditions:
     * - A Minotaur card is initialized with the given palace and fixed damage value.
     *
     * @param palace The name of the palace the card belongs to.
     */
    public MinotaurCard(String palace) {
        super(palace);
    }

    /**
     * Attacks a player and a path.
     *
     * Preconditions:
     * - `player` is not null.
     * - `path` is not null.
     *
     * Postconditions:
     * - Decreases the target player's progress on the given path.
     *
     * @param player The player to attack.
     * @param path   The path to attack.
     */
    public void attack(Player player, Path path) {
        //at first ,we have to get the type of pawn the attack is made on
        Pawn pawn = player.getPawnOnPath(path); //recognise the pawn on the path
        if (pawn == null) {
            System.out.println("There is no pawn on the path");
            return;
        }
        //check the position of the pawn
        int pawnPosition = pawn.getCurrentPosition();
        if (pawnPosition >= 7) {
            System.out.println("Cannot attack. Opponent is past the checkpoint.");
            return;
        }
        if (pawnPosition == 0) {
            System.out.println("Cannot attack. Opponent is at the start.");
            return;
        }
        //check if the pawn is Theseus
        if (pawn instanceof Theseus) {
            System.out.println("Theseus blocks the attack.");
            return;
        }
        //in every other case
        pawn.move(-2);
        System.out.println("Attack successful. The pawn has been moved back 2 steps.");
    }

    @Override
    public boolean isPlayable(Card previousCard) {
        //Minotaur card can be played every time
        return true;
    }
}