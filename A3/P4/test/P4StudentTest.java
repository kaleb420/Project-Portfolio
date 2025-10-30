import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class P4StudentTest {

    @Test
    void decreasingRadii() {
        CirclePacker packer = new OrderedCirclePacker();
        double w = packer.packWidth(new double[]{3,2,1});
        assertTrue(w > 0);
        assertTrue(w >= 3.0);
    }

    @Test
    void identical() {
        CirclePacker packer = new OrderedCirclePacker();
        double w = packer.packWidth(new double[]{2,2,2,2});
        assertTrue(w > 0);
        assertTrue(w >= 2.0);
    }

    @Test
    void emptyArray() {
        CirclePacker packer = new OrderedCirclePacker();
        double w = packer.packWidth(new double[]{});
        assertEquals(0.0, w, 1e-9);
    }
}
