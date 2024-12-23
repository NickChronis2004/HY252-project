package Model;

/**
 * Represents a general finding in the game.
 */
public class Finding {
    private final String name;
    private final int value;

    /**
     * Creates a new finding.
     *
     * Preconditions:
     * - `name` is not null and not empty.
     * - `value` is greater than or equal to 0.
     *
     * Postconditions:
     * - The finding's name and value are initialized.
     *
     * @param name The name of the finding.
     * @param value The value of the finding.
     */
    public Finding(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Gets the name of the finding.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - Returns the name of the finding as a non-null string.
     *
     * @return The name of the finding.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the value of the finding.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - Returns the value of the finding as a non-negative integer.
     *
     * @return The value of the finding.
     */
    public int getValue() {
        return value;
    }
}

/**
 * Represents a rare finding in the game.
 */
class RareFinding extends Finding {
    /**
     * Creates a new rare finding.
     *
     * Preconditions:
     * - `name` is not null and not empty.
     * - `value` is greater than or equal to 0.
     *
     * Postconditions:
     * - A rare finding is initialized with the specified name and value.
     *
     * @param name The name of the rare finding.
     * @param value The value of the rare finding.
     */
    public RareFinding(String name, int value) {
        super(name, value);
    }
}

/**
 * Represents a Snake Goddess finding in the game.
 */
class SnakeGoddess extends Finding {
    /**
     * Creates a new Snake Goddess finding.
     *
     * Preconditions:
     * - `name` is not null and not empty.
     * - `value` is greater than or equal to 0.
     *
     * Postconditions:
     * - A Snake Goddess finding is initialized with the specified name and value.
     *
     * @param name The name of the finding.
     * @param value The value of the finding.
     */
    public SnakeGoddess(String name, int value) {
        super(name, value);
    }
}

/**
 * Represents a fresco in the game.
 */
class Fresco extends Finding {
    private boolean photographed;

    /**
     * Creates a new fresco.
     *
     * Preconditions:
     * - `name` is not null and not empty.
     * - `value` is greater than or equal to 0.
     *
     * Postconditions:
     * - A fresco is initialized with the specified name and value.
     * - The fresco is not photographed.
     *
     * @param name The name of the fresco.
     * @param value The value of the fresco.
     */
    public Fresco(String name, int value) {
        super(name, value);
        this.photographed = false;
    }

    /**
     * Checks if the fresco has already been photographed.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - Returns true if the fresco has been photographed, false otherwise.
     *
     * @return True if the fresco is photographed, false otherwise.
     */
    public boolean isPhotographed() {
        return photographed;
    }

    /**
     * Confirms that the fresco has been photographed.
     *
     * Preconditions:
     * - The fresco has not been photographed yet.
     *
     * Postconditions:
     * - The fresco is marked as photographed.
     */
    public void photograph() {
        this.photographed = true;
    }
}