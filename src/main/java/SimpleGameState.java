public class SimpleGameState implements GameState {

    @Override
    public void serverScores(TennisGame tennisGame) {
        selectNextState(tennisGame);
    }

    private void selectNextState(TennisGame tennisGame) {
        if (isDeuce(tennisGame)) {
            tennisGame.setState(new DeuceGameState());
        } else if (hasWon(tennisGame.getServerScore())) {
            tennisGame.setState(new WinnerGameState());
        }
    }

    @Override
    public void receiverScores(TennisGame tennisGame) {
        if (isDeuce(tennisGame)) {
            tennisGame.setState(new DeuceGameState());
        } else if (hasWon(tennisGame.getReceiverScore())) {
            tennisGame.setState(new WinnerGameState());
        }
    }

    private boolean hasWon(int serverScore) {
        return serverScore >= 4;
    }

    private boolean isDeuce(TennisGame tennisGame) {
        return tennisGame.getServerScore() == 3 && tennisGame.getReceiverScore() == 3;
    }


    @Override
    public String formatScore(TennisGame tennisGame) {
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
