package Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the board of the game.
 */
public class Board {
    private final List<Path> paths;
    private final Map<String, RareFinding> rareFindings;
    private final Deck deck;
    private final Card[] lastPlayedCards; //contains the last played cards for each path

    /**
     * Creates a new board with the given paths and deck.
     *
     * Preconditions:
     * - `paths` is not null and contains valid Path objects.
     * - `deck` is not null and contains a valid deck of cards.
     *
     * Postconditions:
     * - `paths` is initialized with the provided list.
     * - `deck` is initialized with the provided deck.
     * - `rareFindings` is an empty map.
     *
     * @param paths The list of paths on the board.
     * @param deck The deck of cards.
     */
    public Board(List<Path> paths, Deck deck) {
        this.paths = paths;
        this.deck = deck;
        this.rareFindings = new HashMap<>();
        this.lastPlayedCards = new Card[paths.size()];
    }

    /**
     * Initializes the board with paths, findings, and the deck.
     *
     * Preconditions:
     * - The `deck` is properly initialized and contains cards.
     * - `paths` contains valid Path objects.
     *
     * Postconditions:
     * - The deck is shuffled.
     * - Findings are distributed across the paths.
     */
    public void initializeBoard() {
        deck.shuffle();
        addFindingsToPaths();
    }


    /**
     * Gets the path associated with a given palace.
     *
     * Preconditions:
     * - `palace` is a valid non-null string.
     *
     * Postconditions:
     * - Returns the Path associated with the given palace name, or null if not found.
     *
     * @param palace The name of the palace.
     * @return The path associated with the palace.
     */
    public Path getPath(String palace) {
        for (Path path : paths) {
            if (path.getPalaceName().equalsIgnoreCase(palace)) {
                return path;
            }
        }
        return null;
    }


    /**
     * Gets the list of paths on the board.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - Returns the list of paths on the board.
     *
     * @return The list of paths on the board.
     */
    public List<Path> getPaths() {
        return paths;
    }


    /**
     * Gets the deck of the game.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - Returns the deck associated with the board.
     *
     * @return The deck of the game.
     */
    public Deck getDeck() {
        return deck;
    }


    /**
     * Adds findings to the paths on the board.
     *
     * Preconditions:
     * - `paths` is not null and contains valid Path objects.
     * - `rareFindings` is properly initialized.
     *
     * Postconditions:
     * - Findings are distributed across the paths based on predefined rules.
     */
    public void addFindingsToPaths() {
        // place rare findings
        for (Path path : paths) {
            String palaceName = path.getPalaceName();
            RareFinding rareFinding = rareFindings.get(palaceName);

            // randomly select a valid position for the rare finding
            int[] validPositions = {2, 4, 6, 8, 9};
            int randomIndex = (int) (Math.random() * validPositions.length);
            int positionIndex = validPositions[randomIndex];

            // check if the position is available
            if (path.getPosition(positionIndex) instanceof FindingPosition) {
                FindingPosition findingPosition = (FindingPosition) path.getPosition(positionIndex);
                findingPosition.setFinding(rareFinding);
            }
        }

        // place regular findings (statues and frescoes)
        List<Finding> regularFindings = deck.generateRegularFindings(); // statues and frescoes
        for (Finding finding : regularFindings) {
            boolean placed = false;
            while (!placed) {
                // select a random path
                int randomPathIndex = (int) (Math.random() * paths.size());
                Path randomPath = paths.get(randomPathIndex);

                // select a random position
                int randomPositionIndex = (int) (Math.random() * randomPath.getLength());
                if (randomPath.getPosition(randomPositionIndex) instanceof FindingPosition) {
                    FindingPosition position = (FindingPosition) randomPath.getPosition(randomPositionIndex);
                    // check if the position is empty
                    if (position.getFinding() == null) {
                        position.setFinding(finding);
                        placed = true;
                    }
                }
            }
        }
    }


    /**
     * Sets the last played card for a specific path.
     *
     * Preconditions:
     * - `pathIndex` is a valid index (0 <= pathIndex < paths.size()).
     * - `card` is not null.
     *
     * Postconditions:
     * - The last played card for the given path is updated.
     *
     * @param pathIndex The index of the path.
     * @param card The card to set as the last played.
     */
    public void setLastPlayedCard(int pathIndex, Card card) {
        if (pathIndex < 0 || pathIndex >= paths.size()) {
            throw new IllegalArgumentException("Invalid path index.");
        }
        lastPlayedCards[pathIndex] = card;
    }

    /**
     * Gets the last played card for a specific path.
     *
     * Preconditions:
     * - `pathIndex` is a valid index (0 <= pathIndex < paths.size()).
     *
     * Postconditions:
     * - Returns the last played card for the given path, or null if no card has been played.
     *
     * @param pathIndex The index of the path.
     * @return The last played card for the path.
     */
    public Card getLastPlayedCard(int pathIndex) {
        if (pathIndex < 0 || pathIndex >= paths.size()) {
            throw new IllegalArgumentException("Invalid path index.");
        }
        return lastPlayedCards[pathIndex];
    }

    /**
     * Gets the path by its index.
     *
     * Preconditions:
     * - `pathIndex` is a valid index (0 <= pathIndex < paths.size()).
     *
     * Postconditions:
     * - Returns the Path object at the specified index.
     *
     * @param path The index of the path.
     * @return The Path object.
     */
    public int getPathIndex(Path path) {
        for (int i = 0; i < paths.size(); i++) {
            if (paths.get(i).equals(path)) {
                return i;
            }
        }
        throw new IllegalArgumentException("path not found in board.");
    }
}