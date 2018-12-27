package BaseGame;

public class BattleBoard {

    private final int emptyPlace = 0;
    private final int huPlayer = 1;
    private final int aiPlayer = 2;

    public int[] board = {0, 0, 0,
                   0, 0, 0,
                   0, 0, 0};
    final int lenBoard = board.length;

    public void DisplayBoard() {
        for (int i = 0; i < lenBoard; i++) {
            if (board[i] == emptyPlace) {
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

    public boolean PlaceIsEmpty(int place) {
        if (board[place] != emptyPlace) {
            return false;
        }

        return true;
    }

    public boolean BoardIsFull() {
        for (int i = 0; i < lenBoard; i++) {
            if (board[i] == emptyPlace) {
                return false;
            }
        }

        return true;
    }

    public boolean Win(int player) {
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
