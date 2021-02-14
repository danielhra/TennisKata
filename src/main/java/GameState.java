public interface GameState {
    GameState serverScores();
    GameState receiverScores();
    String formatScore();
}
