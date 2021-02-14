

public class WinnerGameState implements GameState {
    private final TennisGame tennisGame;

    public WinnerGameState(TennisGame tennisGame) {
        this.tennisGame = tennisGame;
    }

    @Override
    public GameState serverScores() {
        throw new IllegalStateException();
    }

    @Override
    public GameState receiverScores() {
        throw new IllegalStateException();
    }

    @Override
    public String formatScore() {
        return "winner " + (tennisGame.getServerScore() > tennisGame.getReceiverScore() ? "in" : "out");
    }


}
