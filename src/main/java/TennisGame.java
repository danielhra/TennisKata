public class TennisGame {

    private final int serverScore;
    private final int receiverScore;

    private TennisGame(int serverScore, int receiverScore) {
        this.serverScore = serverScore;
        this.receiverScore = receiverScore;
    }

    public static TennisGame of(int serverScore, int receiverScore) {
        return new TennisGame(serverScore, receiverScore);
    }

    public String evaluateScore() {
        if (thereIsAWinner()) {
            return "winner " + getScorerWithMorePoints();
        }
        if (isDeuce()) {
            return "deuce";
        }
        if (anyPlayerScoredAfterDeuce()) {
            return "advantage " + getScorerWithMorePoints();
        }
        return scoreRepresentation(serverScore) + " - " + scoreRepresentation(receiverScore);
    }

    private boolean thereIsAWinner() {
        return (serverScore >= 4 && serverScore >= receiverScore + 2) ||
                (receiverScore >= 4 && receiverScore >= serverScore + 2);
    }

    private String getScorerWithMorePoints() {
        return serverScore > receiverScore ? "in" : "out";
    }

    private boolean anyPlayerScoredAfterDeuce() {
        return serverScore > 3 || receiverScore > 3;
    }

    private boolean isDeuce() {
        return serverScore == 3 && receiverScore == 3;
    }

    private String scoreRepresentation(int score) {
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

    public TennisGame serverScored() {
        return new TennisGame(serverScore + 1, receiverScore);
    }

    public TennisGame receiverScored() {
        return new TennisGame(serverScore, receiverScore + 1);
    }

    public int getServerScore() {
        return serverScore;
    }

    public int getReceiverScore() {
        return receiverScore;
    }
}
