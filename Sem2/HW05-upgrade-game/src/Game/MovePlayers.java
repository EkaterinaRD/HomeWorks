package Game;

import BaseGame.BattleBoard;
import java.util.Scanner;

public abstract class MovePlayers {

    /*BattleBoard board;
    public MovePlayers(BattleBoard board) {
        this.board = board;
    }*/

    final int emptyPlace = 0;
    final int huPlayer = 1;
    final int aiPlayer = 2;

    private int MoveHuPlayer(BattleBoard board) {

        Scanner move = new Scanner(System.in);
        int place = move.nextInt();
        while (!board.PlaceIsEmpty(place)) {
            place = move.nextInt();
        }
        return place;
    }

    public abstract int MoveAIPlayer(BattleBoard board);

    public void Play(BattleBoard board) {
        boolean win = false;
        int currentPlayer = huPlayer;
        int place;

        while (!board.BoardIsFull()) {
            if (currentPlayer == huPlayer) {
                System.out.print("Your move: ");
                place = MoveHuPlayer(board);
                board.board[place] = huPlayer;
                board.DisplayBoard();
                System.out.println();
                if (board.Win(huPlayer)) {
                    System.out.println("You won!");
                    win = true;
                }
                currentPlayer = aiPlayer;
            }
            else {
                System.out.println("AI's move: ");
                place = MoveAIPlayer(board
                );
                board.board[place] = aiPlayer;
                board.DisplayBoard();
                System.out.println();
                if (board.Win(aiPlayer)) {
                    System.out.println("AI won!");
                    win = true;
                }
                currentPlayer = huPlayer;
            }

            if (win) {
                break;
            }
        }
    }
}
