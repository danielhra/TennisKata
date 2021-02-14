public class TennisGame {

    private final int serverScore;
    private final int receiverScore;
    private  GameState state;

    private TennisGame(int serverScore, int receiverScore) {
        this.serverScore = serverScore;
        this.receiverScore = receiverScore;
        this.state = new SimpleGameState();
    }

    public TennisGame() {
        this.serverScore = 0;
        this.receiverScore = 0;
        this.state = new SimpleGameState();
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public String evaluateScore() {
        return state.formatScore(this);
    }

    public TennisGame serverScored() {
        TennisGame updatedGame = new TennisGame(serverScore + 1, receiverScore);
        state.serverScores(updatedGame);
        return updatedGame;
    }


    public TennisGame receiverScored() {
        TennisGame updatedGame = new TennisGame(serverScore, receiverScore + 1);
        state.receiverScores(updatedGame);
        return updatedGame;
    }

    public int getServerScore() {
        return serverScore;
    }

    public int getReceiverScore() {
        return receiverScore;
    }

    public GameState getState() {
        return state;
    }
}
