package gm.prcjt;

import java.util.Scanner;

public class Main {

    public static int rows = 12;
    public static int columns = 20;
    public static int numberOfEnemies = 20;
    public static int pointsneeded = 100;
    public static int moves = 40;
    public static int getNumberOfPoints = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;

        do {

            System.out.println("Приветсвую вас в игре!\nПожалуйста, сделайте выбор действия: ");
            System.out.println("1 - Начать новую игру");
            System.out.println("2 - Ваши возможности");
            System.out.println("3 - Выход из игры");

            command = scanner.nextLine();

            switch (command) {
                case "1":
                    StartNewGame();
                    break;
                case "2":
                    Options.showOptions();
                    break;
                case "3":
                    break;

                default:
                    System.out.println("Данной команды не существуе( Повторите попытку!");
            }
        }
        while (!command.equals("3"));
    }

    private static void StartNewGame() {

        Game game = new Game(rows, columns, numberOfEnemies, pointsneeded, moves, getNumberOfPoints);

        game.fillFiledWithEmptyObjects();


        game.startGame();

    }

    private static void openOptions() {

    }
}