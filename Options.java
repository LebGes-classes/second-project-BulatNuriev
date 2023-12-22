package gm.prcjt;

import java.util.Scanner;

public class Options {

    static Scanner scanner = new Scanner(System.in);
    static int command;
    public static void showOptions(){

        do {
            System.out.println("Сделайте свой выбор\n"+
                    "1 - Показать текущие настройки\n"+
                    "2 - Изменить настройки\n" +
                    "3 - Выход");
            command = scanner.nextInt();

            switch (command){
                case 1:
                    System.out.println("\n Текущие настройки:\n" +
                            "Строки: " + Main.rows +
                            "\nСтолбцы: " + Main.columns +
                            "\nВраги: " + Main.numberOfEnemies+
                            "\nОчки: " + Main.pointsneeded +
                            "\nХоды: " + Main.moves +
                            "\nПоинты" + Main.getNumberOfPoints);
                    break;
                case 2:
                    String value;

                    System.out.println("Введите новое значение для строк, иначе оставьте поле заполнения пустым ["+Main.rows+"] : ");

                    scanner.nextLine();

                    value = scanner.nextLine();
                    if(!value.isBlank()){
                        Main.rows = Integer.parseInt(value);
                    }

                    Main.rows = scanner.nextInt();
                    System.out.println("Введите новое значение для столбцов, иначе оставьте поле заполнения пустым ["+Main.columns+"] : ");
                    value = scanner.nextLine();
                    if(!value.isBlank()){
                        Main.columns = Integer.parseInt(value);
                    }
                    System.out.println("Введите новое значение для врагов, иначе оставьте поле заполнения пустым ["+Main.numberOfEnemies+"] : ");
                    value = scanner.nextLine();
                    if(!value.isBlank()){
                        Main.numberOfEnemies = Integer.parseInt(value);
                    };
                    System.out.println("Введите новое значение для очков, иначе оставьте поле заполнения пустым ["+Main.pointsneeded+"] : ");
                    value = scanner.nextLine();
                    if(!value.isBlank()){
                        Main.pointsneeded = Integer.parseInt(value);
                    }
                    System.out.println("Введите новое значение для ходов, иначе оставьте поле заполнения пустым ["+Main.moves+"] : ");
                    value = scanner.nextLine();
                    if(!value.isBlank()){
                        Main.moves = Integer.parseInt(value);
                    }
                    System.out.println("Введите новое значение для поинтов, иначе оставьте поле заполнения пустым ["+Main.getNumberOfPoints+"] : ");
                    value = scanner.nextLine();
                    if(!value.isBlank()){
                        Main.getNumberOfPoints = Integer.parseInt(value);
                    }

                    if (isValuesNotPlayable()){
                        System.out.println("Значение, которые вы ввели не производится, измените значение и попробуйте снова!");
                    }

                    break;

                case 3:
                    break;
                default:
                    System.out.println("Команда не найдена, повторите попытку, изменив выбор!");
                    break;
            }
        }
        while(command != 3);
    }

    private static boolean isValuesNotPlayable(){

        int fieldSize = Main.rows*Main.columns;
        int allGameObjects = Main.numberOfEnemies + Main.getNumberOfPoints + 1;
        boolean isValuesNotPlayable = ((allGameObjects / fieldSize) > 0.75);
        return isValuesNotPlayable;

    }
}
