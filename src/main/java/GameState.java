public interface GameState {
    void serverScores(TennisGame tennisGame);
    void receiverScores(TennisGame tennisGame);
    String formatScore(TennisGame tennisGame);
}
