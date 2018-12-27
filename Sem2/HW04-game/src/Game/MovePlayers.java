package Game;

import BattleBoard.BattleBoard;

import java.util.Scanner;

public abstract class MovePlayers {

    final int huPlayer = 1;
    final int aiPlayer = 2;

    private int MoveHuPlayer(int board[]) {
        Scanner move = new Scanner(System.in);
        int place = move.nextInt();//проверка на выход за массив
        while (!BattleBoard.PlaceIsEmpty(board, place)) {
            place = move.nextInt();
        }
        return place;
    }

    abstract int MoveAIPlayer(int newBoard[], int player);

    public void Play(int board[]) {

        boolean win = false;
        int player = huPlayer;

        while (!BattleBoard.BoardIsFull(board)) {
            if (player == huPlayer) {
                System.out.print("Your move: ");
                int place = MoveHuPlayer(board);
                board[place] = huPlayer;
                BattleBoard.DisplayBoard(board);
                System.out.println();
                if (BattleBoard.Win(board, huPlayer)) {
                    System.out.println("You win!");
                    win = true;
                }
                player = aiPlayer;
            }
            else {
                System.out.println("AI's move");
                int place = MoveAIPlayer(board, aiPlayer);
                board[place] = aiPlayer;
                BattleBoard.DisplayBoard(board);
                System.out.println();
                if (BattleBoard.Win(board, aiPlayer)) {
                    System.out.println("AI win!");
                    win = true;
                }
                player = huPlayer;
            }

            if (win) {
                break;
            }
        }


    }
}
