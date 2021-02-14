import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WinnerGameStateTest {

    WinnerGameState sut;

    @Test()
    void shouldThrowExceptionIfServerTriesToScore() {
        sut = new WinnerGameState(TennisGame.of(4, 0));
        Assertions.assertThrows(IllegalStateException.class, () -> sut.serverScores());
    }

    @Test()
    void shouldThrowExceptionIfReceiverTriesToScore() {
        sut = new WinnerGameState(TennisGame.of(4, 0));
        Assertions.assertThrows(IllegalStateException.class, () -> sut.receiverScores());
    }

    @Test
    void shouldShowServerWon() {
        sut = new WinnerGameState(TennisGame.of(4, 0));

        assertThat(sut.formatScore()).isEqualTo("winner in");
    }

    @Test
    void shouldShowReceiverWon() {
        sut = new WinnerGameState(TennisGame.of(0, 4));

        assertThat(sut.formatScore()).isEqualTo("winner out");
    }
}