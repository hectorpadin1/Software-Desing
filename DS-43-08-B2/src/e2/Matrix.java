package e2;

import java.util.Arrays;
import java.util.Iterator;

public class Matrix {

    private final int[][] matrix;

    public Matrix(int rows, int cols) {
        if (rows < 1 || cols < 1) throw new IllegalArgumentException("Matrix dimensions must be greater than zero.");
        matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    public Matrix(int[][] matrix) {
        int i = 0;
        if (matrix.length == 0) throw new IllegalArgumentException("Matrix dimensions must be greater than zero.");
        int lastSize = matrix[i].length;
        do {
            if (lastSize != matrix[i].length || lastSize == 0)
                throw new IllegalArgumentException("Matrix has to be rectangular.");
            lastSize = matrix[i++].length;
        } while (i < matrix.length);
        this.matrix = matrix;
    }

    public int getColumnCount() {
        return matrix[0].length;
    }

    public int getRowCount() {
        return matrix.length;
    }

    public int read(int rowPos, int colPos) {
        try {
            return matrix[rowPos][colPos];
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("The position received is not in the Matrix.");
        }
    }

    public void write(int rowPos, int colPos, int value) {
        try {
            matrix[rowPos][colPos] = value;
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("The position received is not in the Matrix.");
        }
    }

    public int[][] copy() {
        int[][] copy = new int[matrix.length][];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = Arrays.copyOf(matrix[i], matrix[i].length);
        }
        return copy;
    }

    public int[] getRow(int row) {
        try {
            return Arrays.copyOf(matrix[row], matrix[row].length);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("The row is not in the Matrix");
        }
    }

    public int[] getColumn(int col) {
        try {
            int[] copiedCol = new int[matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                copiedCol[i] = matrix[i][col];
            }
            return copiedCol;
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("The column is not in the Matrix");
        }
    }

    public Iterator<Integer> columnRowIterator() {
        return new MatrixIterator(this);
    }

    public Iterator<Integer> rowColumnIterator() {
        return new MatrixIterator(this, false);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : matrix) {
            sb.append(Arrays.toString(row)).append('\n');
        }
        return sb.toString();
    }
}
