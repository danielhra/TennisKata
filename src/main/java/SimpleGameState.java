public class SimpleGameState implements GameState {

    private final TennisGame tennisGame;

    public SimpleGameState(TennisGame tennisGame) {
        this.tennisGame = tennisGame;
    }

    @Override
    public GameState serverScores() {
        TennisGame updatedGame = tennisGame.serverScored();
        return getGameState(updatedGame, updatedGame.getServerScore());
    }

    @Override
    public GameState receiverScores() {
        TennisGame updatedGame = tennisGame.receiverScored();
        return getGameState(updatedGame, updatedGame.getReceiverScore());
    }

    private GameState getGameState(TennisGame updatedGame, int score) {
        if (isDeuce(updatedGame)) {
            return new DeuceGameState(updatedGame);
        }

        if (hasWon(score)) {
            return new WinnerGameState(updatedGame);
        }
        return this;
    }

    private boolean hasWon(int serverScore) {
        return serverScore >= 4;
    }

    private boolean isDeuce(TennisGame updatedGame) {
        return updatedGame.getServerScore() == 3 && updatedGame.getReceiverScore() == 3;
    }


    @Override
    public String formatScore() {
        return transformToFormat(tennisGame.getServerScore()) + " - " + transformToFormat(tennisGame.getReceiverScore());
    }

    private String transformToFormat(int score) {
        switch (score) {
            case 0:
                return "love";
            case 1:
                return "fifteen";
            case 2:
                return "thirty";
            case 3:
                return "forty";
            default:
                throw new IllegalStateException();
        }
    }
}
