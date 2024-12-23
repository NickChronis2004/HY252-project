package Controller;

import Model.*;

import java.util.Random;
import java.util.Scanner;


/**
 * Controller class for the game.
 * Controls the flow of the game.
 * Ensures that all the rules are followed.
 * Handles the game logic.
 */
public class controller {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private final Random random;
    private Player currentPlayer;

    /**
     * Creates a new controller with two players.
     *
     * Preconditions:
     * - `board` is not null.
     * - `player1` is not null.
     * - `player2` is not null.
     *
     * Postconditions:
     * - `random` is initialized.
     * - `currentPlayer` is null initially.
     *
     * @param board The board to control.
     * @param player1 The first player.
     * @param player2 The second player.
     */
    public controller(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.random = new Random();
        this.currentPlayer = null;
    }

    /**
     * Initializes the board with paths, findings, and the deck.
     *
     * Preconditions:
     * - The board is properly initialized with paths and a deck.
     *
     * Postconditions:
     * - The board is fully set up for the game.
     */
    public void setupBoard() {
        board.initializeBoard();
    }

    /**
     * Randomly selects a player to start the game.
     *
     * Preconditions:
     * - `player1` and `player2` are not null.
     * - `currentPlayer` is null.
     *
     * Postconditions:
     * - `currentPlayer` is either `player1` or `player2`.
     */
    public void randomStart() {
        if (random.nextBoolean()) {
            currentPlayer = player1;
        } else {
            currentPlayer = player2;
        }
    }

    /**
     * Gets the current player.
     *
     * Preconditions:
     * - `currentPlayer` is not null.
     *
     * Postconditions:
     * - Returns the player whose turn it is to play.
     *
     * @return The current player.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }


    /**
     * Plays a turn for the current player.
     *
     * Preconditions:
     * - `currentPlayer` is not null.
     *
     * Postconditions:
     * - The turn logic is executed for the current player.
     */
    public void turn() {
        System.out.println("Turn: " + currentPlayer.getName());
        System.out.println("Available cards: ");
        // print the cards in the player's hand
        for (int i = 0; i < currentPlayer.getCards().size(); i++) {
            System.out.println(i + 1 + ": " + currentPlayer.getCards().get(i));
        }
        //select a card to play
        Card playCard = null;
        while (playCard == null) {
            System.out.println("Choose a card to play (1-" + currentPlayer.getCards().size() + "): ");
            int choice = new Scanner(System.in).nextInt();
            if (choice >= 1 && choice <= currentPlayer.getCards().size()) {
                playCard = currentPlayer.getCards().get(choice - 1);
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        // validate the selected card
        try {
            System.out.println("Select a path by entering its index:");
            Scanner scanner = new Scanner(System.in);

            // validate user input
            int selectedPath;
            while (true) {
                if (scanner.hasNextInt()) {
                    selectedPath = scanner.nextInt();
                    if (selectedPath >= 0 && selectedPath < board.getPaths().size()) {
                        break; // valid path index
                    } else {
                        System.out.println("Invalid path index. Try again:");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number:");
                    scanner.next(); // discard invalid input
                }
            }
            scanner.close();
            // play the card on the selected path
            currentPlayer.playCard(playCard, selectedPath, board);
            System.out.println(currentPlayer.getName() + " played: " + playCard);
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot play this card: " + e.getMessage());
            return; // end the turn if the card is not valid
        }
    }


    /**
     * Checks if the move of the pawn is valid according to the rules.
     *
     * Preconditions:
     * - `pawn` is not null.
     * - `card` is not null.
     *
     * Postconditions:
     * - Returns true if the move is valid, false otherwise.
     *
     * @param pawn The pawn to validate.
     * @param card The card to validate.
     * @return true if the move is valid, false otherwise.
     */
    public boolean validMove(Pawn pawn, Card card) {
        if (pawn == null || card == null) {
            throw new IllegalArgumentException("pawn and card cannot be null.");
        }

        Path currentPath = pawn.getCurrentPath();
        if (currentPath == null) {
            throw new IllegalStateException("pawn is not on a path.");
        }

        // get the path index
        int pathIndex = board.getPathIndex(currentPath); // ensure this method exists or implement it

        // get the last played card on this path
        Card lastPlayedCard = board.getLastPlayedCard(pathIndex);

        // check if the card is playable after the last played card
        return card.isPlayable(lastPlayedCard);
    }



    /**
     * Switches to the next player.
     *
     * Preconditions:
     * - `currentPlayer` is not null.
     * - `player1` and `player2` are not null.
     *
     * Postconditions:
     * - `currentPlayer` is switched to the other player.
     */
    public void nextTurn() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }


    /**
     * Checks if the player has won.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - Returns true if the player has won, false otherwise.
     *
     * @return true if the player has won, false otherwise.
     */
    public boolean Winner() {
        // check if all paths are completed
        boolean allPathsCompleted = board.getPaths().stream().allMatch(Path::isCompleted);

        // check which player has the highest score
        int player1Score = player1.getScore();
        int player2Score = player2.getScore();

        if (allPathsCompleted) {
            if (player1Score > player2Score) {
                System.out.println(player1.getName() + " has won the game!");
                return true;
            } else if (player2Score > player1Score) {
                System.out.println(player2.getName() + " has won the game!");
                return true;
            } else {
                System.out.println("It's a draw!");
                return false;
            }
        }

        return false; // game is not yet over
    }


    /**
     * Checks if the game is over.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - Returns true if the game is over, false otherwise.
     *
     * @return true if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        // check if all paths are completed
        boolean allPathsCompleted = board.getPaths().stream().allMatch(Path::isCompleted);

        // check if the deck is empty
        boolean deckEmpty = board.getDeck().getRemainingCards() == 0;

        // if all paths are completed or the deck is empty, the game is over
        return allPathsCompleted || deckEmpty;
    }


    /**
     * Moves a pawn forward by a specified number of steps.
     *
     * Preconditions:
     * - `pawn` is not null.
     * - `steps` is greater than 0.
     *
     * Postconditions:
     * - The pawn's position is updated by the specified number of steps.
     * - If the pawn reaches the end of the path, it is marked as completed.
     *
     * @param pawn The pawn to move.
     * @param steps The number of steps to move the pawn.
     */
    public void movePawn(Pawn pawn, int steps) {
        if (pawn == null) {
            throw new IllegalArgumentException("Pawn cannot be null.");
        }
        if (steps <= 0) {
            throw new IllegalArgumentException("Steps must be greater than 0.");
        }

        // move the pawn
        pawn.move(steps);

        // check if the pawn has reached the end of the path
        Path path = pawn.getCurrentPath();
        if (path != null && pawn.getCurrentPosition() >= path.getLength()) {
            System.out.println("Pawn has reached the end of the path!");
            // mark the path as completed or handle accordingly
            path.setCompleted(true);
        }
    }


    //draw method is in the Deck class

    /**
     * Plays a card on a pawn.
     *
     * Preconditions:
     * - `pawn` is not null.
     * - `card` is not null.
     * - `card` is playable on the given pawn (validity checked externally).
     *
     * Postconditions:
     * - The card is removed from the player's hand.
     * - The card is played on the specified pawn.
     * - The card is added to the discard pile.
     *
     * @param pawn The pawn to play the card on.
     * @param card The card to play.
     */
    void playCard(Pawn pawn, Card card) {
        if (pawn == null) {
            throw new IllegalArgumentException("Pawn cannot be null.");
        }
        if (card == null) {
            throw new IllegalArgumentException("Card cannot be null.");
        }

        // check if the player has the card
        if (!currentPlayer.getCards().contains(card)) {
            throw new IllegalArgumentException("The player does not have this card.");
        }

        // remove the card from the player's hand
        currentPlayer.getCards().remove(card);

        // add the card to the discard pile
        board.getDeck().discard(card);

        // apply the effect of the card
        if (card instanceof NumberCard) {
            // move the pawn forward by the card's value
            int steps = ((NumberCard) card).getValue();
            pawn.move(steps);
        } else if (card instanceof AriadneCard) {
            // move the pawn forward by a fixed number of steps (e.g., +2)
            pawn.move(2);
        } else if (card instanceof MinotaurCard) {
            // attack logic (e.g., decrease progress or destroy something)
            Path currentPath = pawn.getCurrentPath();
            ((MinotaurCard) card).attack(currentPlayer, currentPath);
        } else {
            throw new IllegalArgumentException("Unsupported card type.");
        }

        // optionally, update the board's last played card
        int pathIndex = board.getPaths().indexOf(pawn.getCurrentPath());
        if (pathIndex != -1) {
            board.setLastPlayedCard(pathIndex, card);
        }
    }


    /**
     * Counts the score of the game.
     *
     * Preconditions:
     * - `points` is greater than or equal to 0.
     *
     * Postconditions:
     * - Returns 1 if player 1 has score >= points, 2 if player 2 has score >= points, 0 if it's a draw.
     *
     * @param points The points to count the score.
     * @return 1 if player 1 has score >= points, 2 if player 2 has score >= points, 0 if it's a draw.
     */
    public int countScore(int points) {
         if (player1.getScore() >= points) {
            System.out.println("Player 1 wins!");
            return 1;
        } else if (player2.getScore() >= points) {
            System.out.println("Player 2 wins!");
            return 2;
        } else {
            System.out.println("It's a draw!");
            return 0;
        }
    }
}