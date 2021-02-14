public class DeuceGameState implements GameState {
    @Override
    public void serverScores(TennisGame tennisGame) {
        tennisGame.setState(new AdvantageGameState());
    }

    @Override
    public void receiverScores(TennisGame tennisGame) {
        tennisGame.setState(new AdvantageGameState());
    }

    @Override
    public String formatScore(TennisGame tennisGame) {
        return "deuce";
    }
}
