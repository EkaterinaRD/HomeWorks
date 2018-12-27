import BattleBoard.BattleBoard;
import Game.HardPlay;
import Game.LightPlay;
import Game.MovePlayers;

import java.util.Scanner;

public class TicTacToe {

    private static void PrintMenu() {
        System.out.println("Menu: ");
        System.out.println("q - exit");
        System.out.println("s - start game");
    }

    public static void main(String[] args) {
        String point;
        boolean quite = false;

        MovePlayers[] games = {new LightPlay(), new HardPlay()};

        int board[] = {0, 0, 0,
                       0, 0, 0,
                       0, 0, 0};

        PrintMenu();

        while (!quite) {
            Scanner menu = new Scanner(System.in);
            point = menu.next();
            switch (point) {
                case "q":
                    quite = true;
                    break;
                case "s":
                    System.out.println("l - Light play");
                    System.out.println("h - Hard play");
                    System.out.println("b - back");
                    System.out.print("Choose level: ");
                    Scanner choose = new Scanner(System.in);
                    String level = choose.next();
                    switch (level) {
                        case "l":
                            System.out.println("You choose Light Play");
                            games[0].Play(board);
                            break;
                        case "h":
                            System.out.println("You choose Hard Play");
                            games[1].Play(board);
                            break;
                        case "b":
                            break;
                    }
                    PrintMenu();
                    break;
            }
        }
    }
}
