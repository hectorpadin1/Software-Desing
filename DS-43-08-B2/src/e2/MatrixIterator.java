package e2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {
    private final Matrix matrix;
    private final boolean horizontal;
    private int curIndex;

    public MatrixIterator(Matrix matrix) {
        this.matrix = new Matrix(matrix.copy());
        this.horizontal = true;
        this.curIndex = 0;
    }

    public MatrixIterator(Matrix matrix, boolean horizontal) {
        this.matrix = new Matrix(matrix.copy());
        this.horizontal = horizontal;
        this.curIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return matrix.getColumnCount() * matrix.getRowCount() > curIndex;
    }

    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException("There's no more items in the Matrix.");
        int col, row;
        if (horizontal) {
            col = Math.floorDiv(curIndex, matrix.getColumnCount());
            row = curIndex % matrix.getColumnCount();
        } else {
            col = curIndex % matrix.getRowCount();
            row = Math.floorDiv(curIndex, matrix.getRowCount());
        }
        curIndex++;
        return matrix.read(col, row);
    }
}
