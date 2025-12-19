import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    @org.junit.jupiter.api.Test
    void TicketTests() {
        Ticket t = new StandardTicket(10);
        Ticket t2= new DiscountedTicket(10,.3);
        Ticket t3= new VirtualTicket(10);
        Ticket t4= new StandardTicket(10);
        assertEquals(10, t.getPrice());
        assertEquals(0, t.getId());
        assertEquals(7, t2.getPrice());
        assertEquals(1, t2.getId());
        assertEquals(12.5, t3.getPrice());
        assertEquals(2, t3.getId());
        assertEquals(true, t.equals(t));
        assertEquals(false, t.equals(t4));
    }
}