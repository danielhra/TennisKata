public class AdvantageGameState implements GameState {
    private final TennisGame tennisGame;

    public AdvantageGameState(TennisGame tennisGame) {

        this.tennisGame = tennisGame;
    }

    @Override
    public GameState serverScores() {
        TennisGame updatedGame = tennisGame.serverScored();
        if(serverWon(updatedGame)){
            return new WinnerGameState(updatedGame);
        }
        return new DeuceGameState(tennisGame);
    }

    private boolean serverWon(TennisGame updatedGame) {
        return updatedGame.getServerScore() == updatedGame.getReceiverScore() + 2;
    }

    @Override
    public GameState receiverScores() {
        TennisGame updatedGame = tennisGame.receiverScored();
        if (receiverWon(updatedGame)) {
            return new WinnerGameState(updatedGame);
        }

        return new DeuceGameState(tennisGame);
    }

    private boolean receiverWon(TennisGame updatedGame) {
        return updatedGame.getReceiverScore() == updatedGame.getServerScore() + 2;
    }

    @Override
    public String formatScore() {
        return "advantage " + (serverIsAhead() ? "in" : "out");
    }

    private boolean serverIsAhead() {
        return tennisGame.getServerScore() > tennisGame.getReceiverScore();
    }
}
