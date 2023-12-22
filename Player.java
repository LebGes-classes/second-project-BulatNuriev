package gm.prcjt;

public class Player extends Fieldable {

    private static final String MOVE_LEFT = "a";
    private static final String MOVE_RIGHT = "d";
    private static final String MOVE_UP = "w";
    private static final String MOVE_DOWN = "s";
    private static final String NOT_MOVE = "q";
    private int rowIndex;
    private int columnIndex;
    private Field field;
    private Game game;

    @Override
    public String getSymbol() {
        return " ☺ ";
    }

    public Player(int rowIndex, int columnIndex, Game game) {

        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.game = game;
        this.field = game.getField();
        field.setFieldable(rowIndex, columnIndex, this);

    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public Boolean makeMove(String command){

        Boolean isIncorrectMove = true;

        switch (command){

            case MOVE_LEFT:
                isIncorrectMove = movePlayer(0, -1);
                break;
            case MOVE_RIGHT:
                isIncorrectMove = movePlayer(0, 1);
                break;
            case MOVE_UP:
                isIncorrectMove = movePlayer(-1, 0);
                break;
            case MOVE_DOWN:
                isIncorrectMove = movePlayer(1, 0);
                break;
            case NOT_MOVE:
                isIncorrectMove = false;
                break;

            default:
                showError(command);
                break;
        }

        return isIncorrectMove;
    }

    private Boolean movePlayer(int deltaRowIndex, int deltaColumnIndex ){

        int newRowIndex = rowIndex + deltaRowIndex;
        int newColumnIndex = columnIndex + deltaColumnIndex;

        //проверка на то, чтобы игрок не вышел за границы поля, также в конце проверка на нахождение в этом месте врага
        if ((newRowIndex >= 0) && (newRowIndex < field.getRows()) && (newColumnIndex >= 0) && (newColumnIndex < field.getColumns()) && !((field.getFieldable(newRowIndex, newColumnIndex)) instanceof Enemy)){

            //не выходит ли тут поинт, если выходит то идет в счет
            if (field.getFieldable(newRowIndex, newColumnIndex) instanceof Points){

                Points points = (Points) field.getFieldable(newRowIndex, newColumnIndex);
                game.setPointGathered(points.getPoint());
                game.getPointArrayList().remove(points);
                swapPlayer(newRowIndex, newColumnIndex);

            }

            if (field.getFieldable(newRowIndex, newColumnIndex) instanceof Empty){
                swapPlayer(newRowIndex, newColumnIndex);
            }
            return false;
        }
        else{
            return true;
        }

    }
    private void swapPlayer(int newRowIndex, int newColumnIndex){
        field.setFieldable(newRowIndex, newColumnIndex, this);
        field.setFieldable(rowIndex, columnIndex, new Empty());
        rowIndex = newRowIndex;
        columnIndex = newColumnIndex;
    }

    private void showError(String command){

        System.out.println("Вы ввели неправильную" + command + "команду, попробуйте снова!");

    }
}
