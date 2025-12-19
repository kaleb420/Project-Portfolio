public class DiscountedTicket extends Ticket{

    /**
     * constructor to initialize the price
     * @param price of ticket
     */
    public DiscountedTicket(double price, double discount) {
        super(price*(1-discount));
    }
}
