public class DeuceGameState implements GameState {
    private final TennisGame tennisGame;

    public DeuceGameState(TennisGame tennisGame) {
        this.tennisGame = tennisGame;
    }

    @Override
    public GameState serverScores() {
        var updatedGame = tennisGame.serverScored();
        return new AdvantageGameState(updatedGame);
    }

    @Override
    public GameState receiverScores() {
        var updatedGame = tennisGame.receiverScored();
        return new AdvantageGameState(updatedGame);
    }

    @Override
    public String formatScore() {
        return "deuce";
    }
}
