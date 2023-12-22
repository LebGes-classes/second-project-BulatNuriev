package gm.prcjt;

public class Field {
    private int rows;
    private int columns;

    private Fieldable[][] feild;

    public Field(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        feild = new Fieldable[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setFieldable(int x, int y, Fieldable object) {

        feild[x][y] = object;
    }

    public Fieldable getFieldable(int x, int y) {
        return feild[x][y];
    }

    public void showField() {


        for (int i = 0; i < rows; i++) {

            System.out.println();

            for (int j = 0; j < columns; j++) {

                System.out.print(feild[i][j].getSymbol());
            }
        }

        System.out.println();

    }
}
