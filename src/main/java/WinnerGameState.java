

public class WinnerGameState implements GameState {

    @Override
    public void serverScores(TennisGame tennisGame) {
        throw new IllegalStateException();
    }

    @Override
    public void receiverScores(TennisGame tennisGame) {
        throw new IllegalStateException();
    }

    @Override
    public String formatScore(TennisGame tennisGame) {
        return "winner " + (tennisGame.getServerScore() > tennisGame.getReceiverScore() ? "in" : "out");
    }

}
