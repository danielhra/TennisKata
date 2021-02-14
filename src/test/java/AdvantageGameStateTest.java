import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdvantageGameStateTest {

    private final AdvantageGameState sut = new AdvantageGameState();
    ;

    @Test
    void shouldChangeStateToDeuceIfServerWasAheadAndReceiverScored() {

        TennisGame tennisGame = TennisGameTest.instantiateGame(4, 4);
        sut.receiverScores(tennisGame);

        assertThat(tennisGame.getState()).isInstanceOf(DeuceGameState.class);
    }

    @Test
    void shouldChangeStateToDeuceIfReceiverWasAheadAndServerScored() {
        TennisGame tennisGame = TennisGameTest.instantiateGame(4, 4);
        sut.serverScores(tennisGame);

        assertThat(tennisGame.getState()).isInstanceOf(DeuceGameState.class);
    }


    @Test
    void shouldChangeStateToWinnerIfReceiverWasAheadAndReceiverScored() {
        TennisGame tennisGame = TennisGameTest.instantiateGame(3, 5);
        sut.receiverScores(tennisGame);

        assertThat(tennisGame.getState()).isInstanceOf(WinnerGameState.class);
    }

    @Test
    void shouldChangeStateToWinnerIfServerWasAheadAndServerScored() {
        TennisGame tennisGame = TennisGameTest.instantiateGame(5, 3);
        sut.serverScores(tennisGame);
        assertThat(tennisGame.getState()).isInstanceOf(WinnerGameState.class);
    }


    @Test
    void shouldFormatTextToAdvantageInWhenServerIsAhead() {
        TennisGame tennisGame = TennisGameTest.instantiateGame(4, 3);
        assertThat(sut.formatScore(tennisGame)).isEqualTo("advantage in");
    }

    @Test
    void shouldFormatTextToAdvantageOutWhenReceiverIsAhead() {
        TennisGame tennisGame = TennisGameTest.instantiateGame(5, 6);
        assertThat(sut.formatScore(tennisGame)).isEqualTo("advantage out");
    }
}