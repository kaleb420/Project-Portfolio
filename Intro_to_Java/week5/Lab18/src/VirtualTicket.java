public class VirtualTicket extends Ticket{

    /**
     * constructor to initialize the price
     * @param price of ticket
     */
    public VirtualTicket(double price) {
        super(price+2.5);
    }
}
