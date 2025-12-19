import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    void MatrixTests() {
        int[][] arr = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
        };
        int[][] arr2 = {
                {1,2,3,4}
        };
        int[][] arr3 = {
                {5,5,5,5}
        };
        int[][] arr4 = {{}};
        int[][] arr5 = {
                {1,2,3},
                {4,5,6},
                {7,8,9},
                {10,11,12}
        };
        int[][] arr6= {
                {20},
        };
        Matrix m = new Matrix(3,4, arr);
        Matrix m2 = new Matrix(1,4, arr2);
        Matrix m3 = new Matrix(1,4, arr3);
        Matrix m4 = new Matrix(0,0, arr4);
        Matrix m5 = new Matrix(4,3, arr5);
        Matrix m6 = new Matrix(1,1, arr6);
        m.set(2,2, 50); // tests set value
        m.set(99,99,50); // tests set at invalid index
        assertEquals(true, m.add(m)); // tests adding by itself
        assertEquals(false, m.add(m2)); // tests adding with row/column difference
        assertEquals(true, m2.add(m3)); // tests add to another object
        assertEquals("[[6, 7, 8, 9]]", m2.toString()); // tests print and add
        assertEquals(true, m.add(m4)); // tests adding empty array
        assertEquals(true, m.multiply(m5)); // tests multiplying 3x4 array by 4x3 array
        assertEquals(true, m6.multiply(m6));
        assertEquals("[[400]]", m6.toString());
        m.rotate(); // tests rotating 3x4 array
        m6.rotate(); // tests rotating 1x1 array
        m4.rotate(); // tests rotating empty array
        m3.transpose(); // tests transposing 1x4 array
        m4.transpose(); // tests transposing empty array
        m6.transpose(); // tests transposing 1x1 array
        assertEquals("[]", m4.toString()); // tests printing empty string
        assertEquals("[[5], [5], [5], [5]]", m3.toString()); // tests printing 1x4 array
        assertEquals("[[180, 420, 1362], [160, 368, 1200], [180, 420, 1362]]", m.toString());
    }
}