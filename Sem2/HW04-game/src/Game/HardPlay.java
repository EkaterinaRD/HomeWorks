package Game;

import BattleBoard.BattleBoard;

import java.util.ArrayList;

public class HardPlay extends MovePlayers {

    class Move{
        int index;
        int place;
        int score;
    }

    private int score = 0;

    private ArrayList<Integer> emptyIndices(int board[]) {
        ArrayList<Integer> foo = new ArrayList<>();
        int len = board.length;
        int empty_place = 0;
        for (int i = 0; i < len; i++) {
            if (board[i] == empty_place) {
                foo.add(i);
            }
        }

        return foo;
    }

    @Override
    int MoveAIPlayer(int newBoard[], int player) {

        ArrayList<Integer> availSpots = emptyIndices(newBoard);

        if(BattleBoard.Win(newBoard, huPlayer)) {
            return score - 10;
        }
        else if (BattleBoard.Win(newBoard, aiPlayer)) {
            return score + 10;
        }
        else if(availSpots.size() == 0) {
            return score;
        }

        ArrayList<Move> moves = new ArrayList<>();
        int sizeAvail = availSpots.size();
        for (int i = 0; i < sizeAvail; i++) {
            Move move = new Move();

            move.place = availSpots.get(i);
            move.index = newBoard[move.place];

            newBoard[move.place] = player;

            if (player == aiPlayer) {
                int result = MoveAIPlayer(newBoard, huPlayer);
                move.score = result;
            }
            else {
                int result = MoveAIPlayer(newBoard, aiPlayer);
                move.score = result;
            }

            newBoard[move.place] = move.index;
            moves.add(move);
        }

        int bestMove = 0;
        if (player == aiPlayer) {
            int bestScore = -10000;
            int size = moves.size();
            for (int i = 0; i < size; i++) {
                if (moves.get(i).score > bestScore) {
                    bestScore = moves.get(i).score;
                    bestMove = i;
                }
            }
        }
        else {
            int bestScore = 10000;
            int size = moves.size();
            for (int i = 0; i < size; i++) {
                if (moves.get(i).score < bestScore) {
                    bestScore = moves.get(i).score;
                    bestMove = i;
                }
            }
        }

        return moves.get(bestMove).place;
    }


}
