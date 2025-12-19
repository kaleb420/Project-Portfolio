import static org.junit.jupiter.api.Assertions.*;

class DeliveryTest {

    @org.junit.jupiter.api.Test
    void DeliveryTests() {
        DroneDelivery d = new DroneDelivery("1", 10, 2);
        DroneDelivery d2 = new DroneDelivery("2", 0, 5);
        DroneDelivery d3 = new DroneDelivery("3", 5, 0);
        TruckDelivery td = new TruckDelivery("4", 10, false);
        TruckDelivery td2 = new TruckDelivery("5", 10, true);
        TruckDelivery td3 = new TruckDelivery("4", 0, false);
        TruckDelivery td4 = new TruckDelivery("5", 0, true);
        assertEquals(.1667,d.calculateEta(), .001);
        assertEquals(19, d.calculateCost());
        assertEquals(0, d2.calculateEta());
        assertEquals(10, d2.calculateCost());
        assertEquals(.0833, d3.calculateEta(), .001);
        assertEquals(7.5, d3.calculateCost());
        assertEquals(.25, td.calculateEta());
        assertEquals(7.5,td.calculateCost());
        assertEquals(.13333,td2.calculateEta(), .001);
        assertEquals(9 ,td2.calculateCost());
        assertEquals(0,td3.calculateEta(), .001);
        assertEquals(0 ,td3.calculateCost());
        assertEquals(0,td4.calculateEta(), .001);
        assertEquals(0 ,td4.calculateCost());
    }
}