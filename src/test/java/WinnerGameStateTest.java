import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WinnerGameStateTest {

    WinnerGameState sut;

    @Test()
    void shouldThrowExceptionIfServerTriesToScore() {
        sut = new WinnerGameState();
        Assertions.assertThrows(IllegalStateException.class, () -> sut.serverScores(null));
    }

    @Test()
    void shouldThrowExceptionIfReceiverTriesToScore() {
        sut = new WinnerGameState();
        Assertions.assertThrows(IllegalStateException.class, () -> sut.receiverScores(null));
    }

    @Test
    void shouldShowServerWon() {
        sut = new WinnerGameState();
        assertThat(sut.formatScore(TennisGameTest.instantiateGame(4, 0))).isEqualTo("winner in");
    }

    @Test
    void shouldShowReceiverWon() {
        sut = new WinnerGameState();

        assertThat(sut.formatScore(TennisGameTest.instantiateGame(0, 4))).isEqualTo("winner out");
    }
}