import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AdvantageGameStateTest {

    private AdvantageGameState sut;

    @Test
    void shouldChangeStateToDeuceIfServerWasAheadAndReceiverScored() {
        sut = new AdvantageGameState(TennisGame.of(4, 3));
        assertThat(sut.receiverScores()).isInstanceOf(DeuceGameState.class);
        assertThat(sut.receiverScores().formatScore()).isEqualTo("deuce");
    }

    @Test
    void shouldChangeStateToDeuceIfReceiverWasAheadAndServerScored() {
        sut = new AdvantageGameState(TennisGame.of(3, 4));
        assertThat(sut.serverScores()).isInstanceOf(DeuceGameState.class);
        assertThat(sut.serverScores().formatScore()).isEqualTo("deuce");
    }


    @Test
    void shouldChangeStateToWinnerIfReceiverWasAheadAndReceiverScored() {
        sut = new AdvantageGameState(TennisGame.of(3, 4));
        assertThat(sut.receiverScores()).isInstanceOf(WinnerGameState.class);
        assertThat(sut.receiverScores().formatScore()).isEqualTo("winner out");
    }

    @Test
    void shouldChangeStateToWinnerIfServerWasAheadAndServerScored() {
        sut = new AdvantageGameState(TennisGame.of(4, 3));
        assertThat(sut.serverScores()).isInstanceOf(WinnerGameState.class);
        assertThat(sut.serverScores().formatScore()).isEqualTo("winner in");
    }


    @Test
    void shouldFormatTextToAdvantageInWhenServerIsAhead() {
        sut = new AdvantageGameState(TennisGame.of(4, 3));
        assertThat(sut.formatScore()).isEqualTo("advantage in");
    }

    @Test
    void shouldFormatTextToAdvantageOutWhenReceiverIsAhead() {
        sut = new AdvantageGameState(TennisGame.of(5, 6));
        assertThat(sut.formatScore()).isEqualTo("advantage out");
    }
}