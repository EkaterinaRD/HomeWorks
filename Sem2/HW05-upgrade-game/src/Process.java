import BaseGame.BattleBoard;
import Game.*;

public class Process {

    private MovePlayers game;
    private BattleBoard board = new BattleBoard();

    public Process(MovePlayers game) {
        this.game = game;
    }

    public void Start() {
        game.Play(board);
    }
}
