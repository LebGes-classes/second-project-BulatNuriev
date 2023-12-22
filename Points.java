package gm.prcjt;

import java.util.Objects;

public class Points extends Fieldable{

    private int point;
    private int rowIndex;
    private int columnIndex;

    public int getPoint() {
        return point;
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

    public Points(int point, int rowIndex, int columnIndex) {
        this.point = point;
        this.rowIndex = rowIndex;
        this.columnIndex= columnIndex;
    }

    @Override
    public String getSymbol() {
        return String.valueOf(" " + point + " ");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Points points = (Points) o;
        return rowIndex == points.rowIndex && columnIndex == points.columnIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, columnIndex);
    }
}
