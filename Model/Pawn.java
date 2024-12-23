package Model;

/**
 * Represents a pawn in the game.
 */
public abstract class Pawn {
    private boolean isHidden;
    private int currentPosition;
    private Path currentPath;

    /**
     * Creates a new pawn.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - The pawn is hidden.
     * - The pawn's current position is set to 0.
     * - The pawn is not assigned to any path (currentPath is null).
     */
    public Pawn() {
        this.isHidden = true;
        this.currentPosition = 0;
        this.currentPath = null;
    }

    /**
     * Moves the pawn by the specified number of steps.
     *
     * Preconditions:
     * - `steps` is a non-zero integer.
     *
     * Postconditions:
     * - The current position of the pawn is updated by the specified steps.
     *
     * @param steps The number of steps to move the pawn.
     */
    public void move(int steps) {
        this.currentPosition += steps;
    }

    /**
     * Checks if the pawn is hidden.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - Returns true if the pawn is hidden, false otherwise.
     *
     * @return True if the pawn is hidden, false otherwise.
     */
    public boolean isHidden() {
        return isHidden;
    }

    /**
     * Reveals the pawn.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - The pawn is no longer hidden.
     */
    public void reveal() {
        this.isHidden = false;
    }

    /**
     * Gets the current position of the pawn.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - Returns the current position of the pawn.
     *
     * @return The current position of the pawn.
     */
    public int getCurrentPosition() {
        return currentPosition;
    }

    /**
     * Gets the current path of the pawn.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - Returns the path the pawn is currently on, or null if it has no path.
     *
     * @return The current path of the pawn.
     */
    public Path getCurrentPath() {
        return currentPath;
    }

    /**
     * Sets the current path of the pawn.
     *
     * Preconditions:
     * - `path` is not null.
     *
     * Postconditions:
     * - The current path of the pawn is updated to the specified path.
     *
     * @param path The path to set for the pawn.
     */
    public void setCurrentPath(Path path) {
        if (path == null) {
            throw new IllegalArgumentException("Path cannot be null.");
        }
        this.currentPath = path;
    }

    public abstract boolean destroyBox(FindingPosition findingPosition);

    public abstract Finding openBox(FindingPosition findingPosition);
}


/**
 * Represents an archaeologist pawn.
 */
class Archaeologist extends Pawn {

    @Override
    public boolean destroyBox(FindingPosition findingPosition) {
        return false;
    }

    /**
     * Opens a box and retrieves the finding inside.
     *
     * Preconditions:
     * - `findingPosition` is not null.
     * - The `findingPosition` contains a valid finding.
     *
     * Postconditions:
     * - The finding in the specified position is revealed and returned.
     *
     * @param findingPosition The position of the box to open.
     * @return The finding revealed by the box, or null if the position is empty.
     */
    @Override
    public Finding openBox(FindingPosition findingPosition) {
        if (findingPosition == null) {
            throw new IllegalArgumentException("Finding position cannot be null.");
        }
        // reveal and return the finding
        return findingPosition.revealFinding();
    }

}

/**
 * Represents the Theseus pawn.
 */
class Theseus extends Pawn {
    private int remainingDestroys = 3;

    /**
     * Destroys a box in the specified position, removing its finding.
     *
     * Preconditions:
     * - `findingPosition` is not null.
     * - `remainingDestroys` is greater than 0.
     *
     * Postconditions:
     * - The finding in the specified position is destroyed (set to null).
     * - The number of remaining destroys is decreased by 1.
     *
     * @param findingPosition The position of the box to destroy.
     * @return True if the box was successfully destroyed, false otherwise.
     */
    @Override
    public boolean destroyBox(FindingPosition findingPosition) {
        if (findingPosition == null) {
            throw new IllegalArgumentException("Finding position cannot be null.");
        }
        if (remainingDestroys <= 0) {
            return false; // No destroys left
        }
        remainingDestroys--;
        findingPosition.setFinding(null); // destroy the finding
        return true;
    }


    /**
     * Gets the number of remaining destroys Theseus has.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - Returns the number of remaining destroys as an integer.
     *
     * @return The number of remaining destroys.
     */
    public int getRemainingDestroys() {
        return remainingDestroys;
    }

    @Override
    public Finding openBox(FindingPosition findingPosition) {
        return null;
    }
}