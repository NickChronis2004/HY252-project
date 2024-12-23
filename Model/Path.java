package Model;

import java.util.List;

/**
 * Represents a path in the game.
 */
public class Path {
    private final String palaceName;
    private final List<Position> positions;
    private boolean completed;

    /**
     * Creates a new path.
     *
     * Preconditions:
     * - `palaceName` is not null or empty.
     * - `positions` is not null and contains valid Position objects.
     *
     * Postconditions:
     * - The path is initialized with a palace name and a list of positions.
     *
     * @param palaceName The name of the palace this path belongs to.
     * @param positions The positions in this path.
     */
    public Path(String palaceName, List<Position> positions) {
        this.palaceName = palaceName;
        this.positions = positions;
        this.completed = false;
    }


    /**
     * Gets the palace name.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - Returns the name of the palace associated with this path.
     *
     * @return The palace name.
     */
    public String getPalaceName() {
        return palaceName;
    }

    /**
     * Gets the length of the path.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - Returns the number of positions in the path.
     *
     * @return The length of the path.
     */
    public int getLength() {
        return positions.size();
    }


    /**
     * Gets the position at the specified index in the path.
     *
     * Preconditions:
     * - `index` is a valid index (0 <= index < length of positions).
     *
     * Postconditions:
     * - Returns the position at the specified index.
     *
     * @param index The index of the position.
     * @return The position at the specified index.
     */
    public Position getPosition(int index) {
        if (index < 0 || index >= positions.size()) {
            throw new IllegalArgumentException("Invalid index for position.");
        }
        return positions.get(index);
    }

    /**
     * Checks if the path is completed.
     *
     * preconditions:
     * - none
     *
     * postconditions:
     * - returns true if the path is completed, false otherwise
     *
     * @return true if the path is completed, false otherwise
     */
    public boolean isCompleted() {
        // assume the path is completed if all positions are occupied or pawns have reached the end
        for (Position position : positions) {
            if (position instanceof SimplePosition || position instanceof FindingPosition) {
                // check if there are any pawns left to move
                assert position instanceof FindingPosition;
                if (!((FindingPosition) position).isOccupied()) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Sets the completion status of the path.
     *
     * preconditions:
     * - none
     *
     * postconditions:
     * - the completion status of the path is updated
     *
     * @param completed true if the path should be marked as completed, false otherwise
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}