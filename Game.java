package gm.prcjt;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private int rows;
    private int columns;
    private int numberOfEnemies;
    private int pointsNeeded;
    private int movesLeft;
    private int pointGathered;
    private Field field;
    private boolean isGameFinished = false;
    private int numberOfPoints;
    private ArrayList<Points> pointArrayList = new ArrayList<Points>();//для хранения всех поинтов
    private ArrayList<Enemy> enemyArrayList = new ArrayList<Enemy>();
    private Random randomNumber = new Random();
    private Player player;
    private Scanner scanner = new Scanner(System.in);
    private Boolean isInCorrectCommand = true;


    public Game(int rows, int columns, int numberOfEnemies, int pointsNeeded, int movesLeft, int numberOfPoints) {
        this.rows = rows;
        this.columns = columns;
        this.numberOfEnemies = numberOfEnemies;
        this.pointsNeeded = pointsNeeded;
        this.movesLeft = movesLeft;
        this.numberOfPoints = numberOfPoints;
        field = new Field(rows, columns);

    }

    public Field getField(){
        return this.field;
    }

    public ArrayList<Points> getPointArrayList(){
        return this.pointArrayList;
    }

    public void setPointGathered(int pointToAdd){
        this.pointGathered += pointToAdd;
    }

    //заполнение поля пустыми объектами
    public void fillFiledWithEmptyObjects() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                field.setFieldable(i, j, new Empty());
            }
        }
    }

    public void startGame() {

        possessPlayer();
        possessEnemies();
        possessPoints();

        while (!isGameFinished) {

            showField();
            playerTurn();
            if (isInCorrectCommand){
                inCorrectCommand();
                continue;
            }

            computerTurn();
            checkIfGameNotFinished();

        }
    }

    private void inCorrectCommand() {
        System.out.println("Вы ввели некорректную команду, повторите комнанду пожалуйста!");
    }

    private void possessPlayer() {
        int playerRowPosition = randomNumber.nextInt(rows);
        int playerColumnPosotoin = randomNumber.nextInt(columns);

        player = new Player(playerRowPosition, playerColumnPosotoin, this);
    }

    private void possessEnemies() {

        generateEnemies();

    }

    private void generateEnemies() {

        for (int i = numberOfEnemies - enemyArrayList.size(); i > 0; ) {

            //ранодомное расположение
            int enemyRowPosition = randomNumber.nextInt(rows);
            int enemyColumnPosotoin = randomNumber.nextInt(columns);

            if (field.getFieldable(enemyRowPosition, enemyColumnPosotoin) instanceof Empty) {

                Enemy enemy = new Enemy(enemyRowPosition, enemyColumnPosotoin);
                field.setFieldable(enemyRowPosition, enemyColumnPosotoin, enemy);
                enemyArrayList.add(enemy);
                i--;

            }

        }
    }

    private void possessPoints() {

        generatePoints();

    }


    private void showField() {

        System.out.println("\n\nХодов осталось: " + movesLeft
                + ", очков собрано: " + pointGathered
                + "/" + pointsNeeded);
        field.showField();

    }

    private void playerTurn() {

        System.out.println("Введите свою команду: ");
        String command = scanner.nextLine();
        isInCorrectCommand = player.makeMove(command);


    }

    private void computerTurn() {

        enemyMove();
        generatePoints();
        movesLeft--;
    }

    private void generatePoints() {

        for (int i = numberOfPoints - pointArrayList.size(); i > 0; ) {

            //ранодомное расположение, а также то, сколько очков в поинте
            int pointNumberOfPoints = randomNumber.nextInt(9) + 1;
            int pointRowPosition = randomNumber.nextInt(rows);
            int pointColumnPosotoin = randomNumber.nextInt(columns);

            if (field.getFieldable(pointRowPosition, pointColumnPosotoin) instanceof Player) {

                pointGathered = pointGathered + pointNumberOfPoints;
                i--;

            } else if (field.getFieldable(pointRowPosition, pointColumnPosotoin) instanceof Empty) {

                Points points = new Points(pointNumberOfPoints, pointRowPosition, pointColumnPosotoin);
                field.setFieldable(pointRowPosition, pointColumnPosotoin, points);
                pointArrayList.add(points);
                i--;

            }

        }

    }

    //ход врага, подсчет его положения, а также использование рандома
    private void enemyMove() {

        int rowIndex = 0;
        int columnIndex = 0;
        int newRowIndex = 0;
        int newColumnIndex = 0;
        int regenerateIndex = 0;
        boolean isNeededToRegenerate = true;

        for (Enemy enemy : enemyArrayList) {

            rowIndex = enemy.getRowIndex();
            columnIndex = enemy.getColumnIndex();

        do {
            int deltaRow = randomNumber.nextInt(3)-1;
            int deltaColumn = randomNumber.nextInt(3)-1;

            newRowIndex = rowIndex + deltaRow;
            newColumnIndex = columnIndex + deltaColumn;

            if ((newRowIndex < 0) || (newColumnIndex < 0) || (newRowIndex >= field.getRows()) || (newColumnIndex >= field.getColumns()) || field.getFieldable(newRowIndex, newColumnIndex) instanceof Player || field.getFieldable(newRowIndex, newColumnIndex) instanceof Enemy){
                regenerateIndex++;
                isNeededToRegenerate = true;
            }
            else{
                if(field.getFieldable(newRowIndex, newColumnIndex) instanceof Points){
                    Points points = (Points) field.getFieldable(newRowIndex, newColumnIndex);
                    pointArrayList.remove(points);

                    field.setFieldable(newRowIndex, newColumnIndex, enemy);
                    field.setFieldable(rowIndex, columnIndex, new Empty());
                    enemy.setRowIndex(newRowIndex);//меняем координаты врага
                    enemy.setColumnIndex(newColumnIndex);
                    isNeededToRegenerate = swapEnemy(rowIndex, columnIndex, newRowIndex, newColumnIndex, enemy);
                }
                else{
                    field.setFieldable(newRowIndex, newColumnIndex, enemy);
                    field.setFieldable(rowIndex, columnIndex, new Empty());
                    enemy.setRowIndex(newRowIndex);
                    enemy.setColumnIndex(newColumnIndex);
                    isNeededToRegenerate = swapEnemy(rowIndex, columnIndex, newRowIndex, newColumnIndex, enemy);
                }
            }

        } while (isNeededToRegenerate && regenerateIndex <= 10);

        }
    }
    private boolean swapEnemy(int rowIndex, int columnIndex, int newRowIndex, int newColumnIndex, Enemy enemy){

        field.setFieldable(newRowIndex, newColumnIndex, enemy);
        field.setFieldable(rowIndex, columnIndex, new Empty());
        enemy.setRowIndex(newRowIndex);
        enemy.setColumnIndex(newColumnIndex);

        return false;
    }

    private void checkIfGameNotFinished() {

        if (movesLeft == 0) {

            System.out.println("Нет больше ходов, вы проиграли!");
            isGameFinished = true;
        } else if (pointGathered >= 100) {

            System.out.println("Вы набрали небходимое кол-во очков. Поздравляю, вы выиграли!");
            isGameFinished = true;

        }

    }

}
