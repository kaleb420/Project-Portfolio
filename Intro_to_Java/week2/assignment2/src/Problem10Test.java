import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem10Test {

    @Test
    void circleArea() {
        double delta=.01;
        assertEquals(13.98, Problem10.circleArea(2,.5), delta);
        assertEquals(20.65, Problem10.circleArea(2.5,.25), delta);
        assertEquals(330.45, Problem10.circleArea(10,1), delta);
        assertEquals(0, Problem10.circleArea(0,.05), delta);
        assertEquals(0, Problem10.circleArea(1,0), delta);
        assertEquals(0, Problem10.circleArea(0,0), delta);
    }
}