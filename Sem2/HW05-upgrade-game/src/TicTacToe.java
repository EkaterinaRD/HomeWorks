import Game.*;

import java.util.Scanner;
import java.util.ServiceLoader;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

public class TicTacToe {

    private static void PrintMainMenu() {
        System.out.println("q - exit");
        System.out.println("s - start game");
        System.out.print("Choose point: ");
    }

    private static void PrintGameMenu() {
        System.out.println("l - light game");
        System.out.println("m - medium game");
        System.out.println("h - hard game");
        System.out.println("b - back");
        System.out.print("Choose level: ");
    }

    public static void main(String[] args) {

        MutablePicoContainer Pica = new DefaultPicoContainer();

//        BattleBoard board = new BattleBoard();

        Scanner menu = new Scanner(System.in);
        String point, level;
        boolean quit = false;

        ServiceLoader<MovePlayers> loader = ServiceLoader.load(MovePlayers.class);
        Pica.addComponent(Process.class);
        Process game;


        while (!quit) {
            PrintMainMenu();
            point = menu.next();
            switch (point) {
                case "q":
                    quit = true;
                    break;
                case "s":
                    PrintGameMenu();
                    level = menu.next();
                    switch (level) {
                        case "l":
                            System.out.println("You chosen light game");
                            Pica.addComponent(LightPlay.class);
                            break;
                        case "m":
                            System.out.println("You chosen medium game");
                            Pica.addComponent(loader.findFirst());
                            break;
                        case "h":
                            System.out.println("You chosen hard game");
                            Pica.addComponent(HardPlay.class);
                            break;
                        case "b":
                            break;
                    }
                    game = (Process)Pica.getComponent(Process.class);
                    game.Start();
                    break;
            }
        }
    }
}
