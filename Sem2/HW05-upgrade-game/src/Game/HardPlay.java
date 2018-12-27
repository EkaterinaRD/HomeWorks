package Game;

import BaseGame.BattleBoard;

import java.util.ArrayList;

public class HardPlay extends MovePlayers {

    /*public HardPlay(BattleBoard board) {
        super(board);
    }*/

    private int score = 0;
    class Move {
        int index;
        int place;
        int score;
    }

    @Override
    public int MoveAIPlayer(BattleBoard board) {
        int place = minimax(board.board, aiPlayer);
        return place;
    }

    private int minimax(int[] newBoard, int player) {

        ArrayList<Integer> availSpots = emptyIndices(newBoard);

        if (win(newBoard, aiPlayer)) {
            return score + 10;
        }
        else if (win(newBoard, huPlayer)) {
            return score - 10;
        }
        else if (availSpots.size() == 0) {
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
                int result = minimax(newBoard, huPlayer);
                move.score = result;
            }
            else {
                int result = minimax(newBoard, huPlayer);
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

    private ArrayList<Integer> emptyIndices(int[] board) {
        ArrayList<Integer> foo = new ArrayList<>();
        int len = board.length;
        for (int i = 0; i < len; i++) {
            if (board[i] == emptyPlace) {
                foo.add(i);
            }
        }

        return foo;
    }

    private boolean win(int[] board, int player) {
        if ((board[0] == player && board[1] == player && board[2] == player) ||
                (board[3] == player && board[4] == player && board[5] == player) ||
                (board[6] == player && board[7] == player && board[8] == player) ||
                (board[0] == player && board[3] == player && board[6] == player) ||
                (board[1] == player && board[4] == player && board[7] == player) ||
                (board[2] == player && board[5] == player && board[8] == player) ||
                (board[0] == player && board[4] == player && board[8] == player) ||
                (board[2] == player && board[4] == player && board[6] == player) ) {
            return true;
        }

        return false;
    }
}
