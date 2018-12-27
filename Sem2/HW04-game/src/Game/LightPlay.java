package Game;

import BattleBoard.BattleBoard;

import java.util.Random;

public class LightPlay extends MovePlayers {

    @Override
    int MoveAIPlayer(int newBoard[], int player) {
        Random rnd = new Random();
        int bound = 9;
        int place = rnd.nextInt(bound);
        while(!BattleBoard.PlaceIsEmpty(newBoard, place)) {
            place = rnd.nextInt(bound);
        }
        return place;
    }
}
