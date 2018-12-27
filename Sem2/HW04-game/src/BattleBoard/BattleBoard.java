package BattleBoard;

public class BattleBoard {

    static int empty_place = 0;
    static int huPlayer = 1;
    static int aiPlayer = 2;

    public static void DisplayBoard(int board[]) {
        int len = board.length;
        for (int i = 0; i < len; i++) {
            if (board[i] == empty_place) {
                System.out.print("_ ");
            }
            else if (board[i] == huPlayer) {
                System.out.print("X ");
            }
            else if (board[i] == aiPlayer) {
                System.out.print("O ");
            }

            if (i == 2 || i == 5 || i == 8) {
                System.out.println();
            }
        }
    }

    /*
    0 | 1 | 2
    ---------
    3 | 4 | 5
    ---------
    6 | 7 | 8
     */

    public static boolean Win(int board[], int player) {
        if ((board[0] == player && board[1] == player && board[2] == player) ||
        (board[3] == player && board[4] == player && board[5] == player) ||
        (board[6] == player && board[7] == player && board[8] == player) ||
        (board[0] == player && board[3] == player && board[6] == player) ||
        (board[1] == player && board[4] == player && board[7] == player) ||
        (board[2] == player && board[5] == player && board[8] == player) ||
        (board[0] == player && board[4] == player && board[8] == player) ||
        (board[2] == player && board[4] == player && board[6] == player)) {
            return true;
        }

        return false;
    }

    public static boolean BoardIsFull(int board[]) {
        int len = board.length;
        for (int i = 0; i < len; i++) {
            if (board[i] == empty_place) {
                return false;
            }
        }

        return true;
    }

    public static boolean PlaceIsEmpty(int board[], int place) {
        if (board[place] != empty_place) {
            return false;
        }
        return true;
    }
}
