public class StandardTicket extends Ticket{

    /**
     * constructor to initialize the price
     * @param price of ticket
     */
    public StandardTicket(double price) {
        super(price);
    }

    public double getPrice(){
        return super.getPrice();
    }
}