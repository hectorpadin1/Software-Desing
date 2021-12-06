package e2;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {

    @Test
    public void firstConstructorTest() {
        assertEquals("[0]\n", new Matrix(1, 1).toString());
        assertEquals("[0]\n[0]\n", new Matrix(2, 1).toString());
        assertEquals("[0, 0]\n[0, 0]\n[0, 0]\n", new Matrix(3, 2).toString());
        assertThrows(IllegalArgumentException.class, () -> new Matrix(0, 0));
        assertThrows(IllegalArgumentException.class, () -> new Matrix(0, 1));
        assertThrows(IllegalArgumentException.class, () -> new Matrix(1, 0));
    }

    @Test
    public void secondConstructorTest() {
        int[][] matrix1 = {{1}, {1}, {1}};
        int[][] matrix2 = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
        int[][] matrix3 = {{1, 2}, {1, 2}, {1, 2}, {1, 2}, {1, 2}, {1, 2}, {1, 2}, {1, 2}, {1, 2}, {1, 2}, {1, 2}};
        int[][] matrix4 = {{1}, {1, 2}, {1, 2, 3}};
        int[][] matrix5 = {{}, {}, {}};
        int[][] matrix6 = {};

        assertEquals("[1]\n[1]\n[1]\n", new Matrix(matrix1).toString());
        assertEquals("[1, 2, 3]\n[1, 2, 3]\n[1, 2, 3]\n", new Matrix(matrix2).toString());
        assertEquals("[1, 2]\n[1, 2]\n[1, 2]\n[1, 2]\n[1, 2]\n[1, 2]\n[1, 2]\n[1, 2]\n[1, 2]\n[1, 2]\n[1, 2]\n", new Matrix(matrix3).toString());
        assertThrows(IllegalArgumentException.class, () -> new Matrix(matrix4));
        assertThrows(IllegalArgumentException.class, () -> new Matrix(matrix5));
        assertThrows(IllegalArgumentException.class, () -> new Matrix(matrix6));
    }

    @Test
    public void countersTest() {
        assertEquals(1, new Matrix(1, 2).getRowCount());
        assertEquals(10, new Matrix(10, 14).getRowCount());
        assertEquals(55, new Matrix(55, 85).getRowCount());
        assertEquals(5, new Matrix(11, 5).getColumnCount());
        assertEquals(10, new Matrix(42, 10).getColumnCount());
        assertEquals(82, new Matrix(53, 82).getColumnCount());
    }

    @Test
    public void readWriteTest() {
        int r = 4, c = 3;
        Matrix m = new Matrix(r, c);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                m.write(i, j, (i + 1) * (j + 1));
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                assertEquals((i + 1) * (j + 1), m.read(i, j));
            }
        }
        assertThrows(IllegalArgumentException.class, () -> m.write(r + 1, c, 0));
        assertThrows(IllegalArgumentException.class, () -> m.write(r, c + 1, 0));
        assertThrows(IllegalArgumentException.class, () -> m.write(r + 1, c + 1, 0));
        assertThrows(IllegalArgumentException.class, () -> m.write(-1, -1, 0));
        assertThrows(IllegalArgumentException.class, () -> m.read(r + 1, c));
        assertThrows(IllegalArgumentException.class, () -> m.read(r, c + 1));
        assertThrows(IllegalArgumentException.class, () -> m.read(r + 1, c + 1));
        assertThrows(IllegalArgumentException.class, () -> m.read(0, -1));
    }

    @Test
    public void copyTest() {
        Matrix m = new Matrix(2, 2);
        m.write(0, 0, 1);
        int[][] copy = m.copy();
        assertEquals(copy[0][0], m.read(0, 0));
        copy[0][0] = 2;
        assertNotEquals(copy[0][0], m.read(0, 0));
        assertEquals(copy.length, m.getRowCount());
        assertEquals(copy[0].length, m.getColumnCount());
    }

    @Test
    public void rowColCopyTest() {
        Matrix m = new Matrix(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        });
        assertArrayEquals(new int[]{1, 2, 3, 4}, m.getRow(0));
        assertArrayEquals(new int[]{5, 6, 7, 8}, m.getRow(1));
        assertArrayEquals(new int[]{9, 10, 11, 12}, m.getRow(2));
        assertArrayEquals(new int[]{13, 14, 15, 16}, m.getRow(3));
        assertArrayEquals(new int[]{1, 5, 9, 13}, m.getColumn(0));
        assertArrayEquals(new int[]{2, 6, 10, 14}, m.getColumn(1));
        assertArrayEquals(new int[]{3, 7, 11, 15}, m.getColumn(2));
        assertArrayEquals(new int[]{4, 8, 12, 16}, m.getColumn(3));
        assertThrows(IllegalArgumentException.class, () -> m.getColumn(-1));
        assertThrows(IllegalArgumentException.class, () -> m.getColumn(5));
        assertThrows(IllegalArgumentException.class, () -> m.getRow(-1));
        assertThrows(IllegalArgumentException.class, () -> m.getRow(5));
    }

    @Test
    public void iteratorTest() {
        Matrix m = new Matrix(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        });
        Iterator<Integer> it = m.columnRowIterator();
        for (int i = 0; i < m.getRowCount(); i++) {
            for (int j = 0; j < m.getColumnCount(); j++) {
                assertEquals(m.read(i, j), it.next());
            }
        }
        it = m.rowColumnIterator();
        for (int i = 0; i < m.getColumnCount(); i++) {
            for (int j = 0; j < m.getRowCount(); j++) {
                assertEquals(m.read(j, i), it.next());
            }
        }
        assertThrows(NoSuchElementException.class, it::next);
        assertThrows(UnsupportedOperationException.class, it::remove);
    }

    @Test
    public void additionTest() {
        Matrix m1 = new Matrix(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        });
        Matrix m2 = new Matrix(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        });
        Matrix mr = new Matrix(new int[][]{
                {2, 4, 6, 8},
                {10, 12, 14, 16},
                {18, 20, 22, 24},
                {26, 28, 30, 32}
        });
        assertEquals(mr.toString(), MatrixAddition.add(m1, m2).toString());
        assertThrows(ArithmeticException.class,() -> MatrixAddition.add(new Matrix(1,1), new Matrix(2, 1)));
    }
}
