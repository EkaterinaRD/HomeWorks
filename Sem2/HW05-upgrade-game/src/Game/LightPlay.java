package Game;

import BaseGame.BattleBoard;

import java.util.Random;

public class LightPlay extends MovePlayers {

    /*public LightPlay(BattleBoard board) {
        super(board);
    }*/

    @Override
    public int MoveAIPlayer(BattleBoard board) {
        Random rnd = new Random();
        int bound = 9;
        int place = rnd.nextInt(bound);
        while (!board.PlaceIsEmpty(place)) {
            place = rnd.nextInt(bound);
        }

        return place;
    }
}
