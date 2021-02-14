import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TennisGameTest {
    @Test
    void serverWinsEffortlessly() {
        var sut = new TennisGame();
        assertThat(sut.evaluateScore()).isEqualTo("love - love");
        sut = sut.serverScored();
        assertThat(sut.evaluateScore()).isEqualTo("fifteen - love");
        sut = sut.serverScored();
        assertThat(sut.evaluateScore()).isEqualTo("thirty - love");
        sut = sut.serverScored();
        assertThat(sut.evaluateScore()).isEqualTo("forty - love");
        sut = sut.serverScored();
        assertThat(sut.evaluateScore()).isEqualTo("winner in");
    }

    @Test
    void receiverWinsEffortlessly() {
        var sut = new TennisGame();
        assertThat(sut.evaluateScore()).isEqualTo("love - love");
        sut = sut.receiverScored();
        assertThat(sut.evaluateScore()).isEqualTo("love - fifteen");
        sut = sut.receiverScored();
        assertThat(sut.evaluateScore()).isEqualTo("love - thirty");
        sut = sut.receiverScored();
        assertThat(sut.evaluateScore()).isEqualTo("love - forty");
        sut = sut.receiverScored();
        assertThat(sut.evaluateScore()).isEqualTo("winner out");
    }

    @Test
    void shouldGetDeuce() {
        assertThat(instantiateGame(2, 3).serverScored().evaluateScore()).isEqualTo("deuce");
        assertThat(instantiateGame(3, 2).receiverScored().evaluateScore()).isEqualTo("deuce");
        assertThat(instantiateGame(38, 37).receiverScored().evaluateScore()).isEqualTo("deuce");
    }

    @Test
    void shouldGetAdvantage() {
        assertThat(instantiateGame(3, 3).serverScored().evaluateScore()).isEqualTo("advantage in");
        assertThat(instantiateGame(3, 3).receiverScored().evaluateScore()).isEqualTo("advantage out");

    }

    public static TennisGame instantiateGame(int serverScore, int receiverScore) {
        var tennisGame = new TennisGame();
        while(serverScore > 0 || receiverScore > 0){
            if(serverScore > 0){
                tennisGame = tennisGame.serverScored();
                serverScore--;
            }
            if(receiverScore > 0){
                tennisGame = tennisGame.receiverScored();
                receiverScore--;
            }
        }
        return tennisGame;

    }
}