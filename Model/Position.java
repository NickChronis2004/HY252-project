package Model;

/**
 * Represents a position in a path.
 */
public class Position {
    private final int score;

    /**
     * Creates a new position.
     *
     * Preconditions:
     * - `score` is a valid integer (positive or negative).
     *
     * Postconditions:
     * - The position's score is set to the given value.
     *
     * @param score The score of the position.
     */
    public Position(int score) {
        this.score = score;
    }

    /**
     * Gets the score of the position.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - Returns the score of the position as an integer.
     *
     * @return The score of the position.
     */
    public int getScore() {
        return score;
    }
}

/**
 * Represents a simple position in a path.
 */
class SimplePosition extends Position {

    /**
     * Creates a new simple position.
     *
     * Preconditions:
     * - `score` is a valid integer (positive or negative).
     *
     * Postconditions:
     * - A simple position is created with the specified score.
     *
     * @param score The score of the position.
     */
    public SimplePosition(int score) {
        super(score);
    }
}

/**
 * Represents a position in a path that contains a finding.
 */
class FindingPosition extends Position {
    private Finding finding; // The finding in this position.

    /**
     * Creates a new finding position.
     *
     * Preconditions:
     * - `score` is a valid integer (positive or negative).
     *
     * Postconditions:
     * - A finding position is created with the specified score.
     * - The finding is initially null.
     *
     * @param score The score of the position.
     */
    public FindingPosition(int score) {
        super(score);
        this.finding = null;
    }

    /**
     * Gets the finding in this position.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - Returns the finding in this position, or null if no finding exists.
     *
     * @return The finding or null if there is no finding in this position.
     */
    public Finding getFinding() {
        return finding;
    }

    /**
     * Sets a finding in this position.
     *
     * Preconditions:
     * - `finding` is not null.
     *
     * Postconditions:
     * - The finding in this position is updated to the given value.
     *
     * @param finding The finding to set.
     */
    public void setFinding(Finding finding) {
        this.finding = finding;
    }

    /**
     * Reveals the finding in this position.
     *
     * Preconditions:
     * - The finding is not null.
     *
     * Postconditions:
     * - Returns the finding in this position without modifying its state.
     *
     * @return The finding in this position.
     */
    public Finding revealFinding() {
        return finding;
    }


    /**
     * Checks if the finding position is occupied by a pawn.
     *
     * preconditions:
     * - none
     *
     * postconditions:
     * - returns true if the position is occupied by a pawn, false otherwise
     *
     * @return true if the position is occupied, false otherwise
     */
    public boolean isOccupied() {return finding != null;}
}