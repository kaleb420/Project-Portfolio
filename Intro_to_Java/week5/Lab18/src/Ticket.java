import java.util.*;

public abstract class Ticket {
    private double price;
    private int id;
    private static int counter=0;

    /**
     * constructor to initialize the instance variables
     * @param price of ticket
     */
    public Ticket(double price){
        this.price=price;
        this.id =counter++;
    }

    double getPrice(){
        return price;
    }
    int getId(){
        return id;
    }

    /**
     * override the hashcode function to provide the memory address of the Id and Price
     * @return hashcode
     */
    @Override
    public int hashCode(){
        return Objects.hash(id,price);
    }

    /**
     * override the equals method to determine if the given object equals the ticket id
     * @return true if they are the same, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Ticket) {
            Ticket compare = (Ticket) o;
            return id == compare.getId();
        }
        return false;
    }
}
