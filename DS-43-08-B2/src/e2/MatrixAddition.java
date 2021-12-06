package e2;

import java.util.Iterator;

public class MatrixAddition {

    public static Matrix add(Matrix m1, Matrix m2) {
        if (m1.getColumnCount() != m2.getColumnCount() || m1.getRowCount() != m2.getRowCount())
            throw new ArithmeticException("Matrix dimensions are not the same.");
        Iterator<Integer> i1 = m1.columnRowIterator(), i2 = m2.columnRowIterator();
        Matrix mr = new Matrix(m1.getRowCount(), m1.getColumnCount());
        int col = 0, row = 0, sum;
        while(i1.hasNext()) {
            sum = i1.next() + i2.next();
            mr.write(row, col, sum);
            if(++col == m1.getColumnCount()) {
                col = 0;
                row++;
            }
        }
        return mr;
    }

}
