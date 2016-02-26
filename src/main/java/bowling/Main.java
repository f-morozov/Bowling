package bowling;

import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        BowlingGame game = new BowlingGame(System.in, System.out);
        try {
            game.runGame();
        } catch(NoSuchElementException exception) {
            System.out.println("\nInput closed.");
        }
    }
}
