public class AdvantageGameState implements GameState {

    @Override
    public void serverScores(TennisGame tennisGame) {
        if (serverWon(tennisGame)) {
            tennisGame.setState(new WinnerGameState());
        } else {
            tennisGame.setState(new DeuceGameState());
        }
    }

    private boolean serverWon(TennisGame tennisGame) {
        return tennisGame.getServerScore() == tennisGame.getReceiverScore() + 2;
    }

    @Override
    public void receiverScores(TennisGame tennisGame) {
        if (receiverWon(tennisGame)) {
            tennisGame.setState(new WinnerGameState());
        } else {
            tennisGame.setState(new DeuceGameState());
        }

    }

    private boolean receiverWon(TennisGame tennisGame) {
        return tennisGame.getReceiverScore() == tennisGame.getServerScore() + 2;
    }

    @Override
    public String formatScore(TennisGame tennisGame) {
        return "advantage " + (serverIsAhead(tennisGame) ? "in" : "out");
    }

    private boolean serverIsAhead(TennisGame tennisGame) {
        return tennisGame.getServerScore() > tennisGame.getReceiverScore();
    }
}
