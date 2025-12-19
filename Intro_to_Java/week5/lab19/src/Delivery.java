public abstract class Delivery {

    private String identifier;

    /**
     * constructor to initialize identifier
     * @param s string inputted
     */
    public Delivery(String s){
        this.identifier=s;
    }

    String getIdentifier(){
        return identifier;
    }
    void setIdentifier(String s){
        identifier=s;
    }

    /**
     * returns the string "Preparing package (packageId)
     * @return
     */
    String prepare(){
        String s = "";
        s+="Preparing package [" + identifier +"]";
        return s;
    }

    /**
     * abstract class to implement estimated time of arrival
     * @return estimated time of arrival
     */
    abstract double calculateEta();

    /**
     * abstract class to implement cost of delivery
     * @return cost of delivery
     */
    abstract double calculateCost();
}
