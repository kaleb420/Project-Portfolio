public class TruckDelivery extends Delivery{

    private double distance;
    private boolean express;
    private Delivery identifier;

    /**
     * constructor to initiate instance variables
     * @param s String given
     * @param distance distance from house
     * @param express if its express or regular delivery
     */
    public TruckDelivery(String s, double distance, boolean express){
        super(s);
        this.distance=distance;
        this.express=express;
    }
    /**
     * calculates the estimated time of arrival
     * @return estimated time of arrival
     */
    @Override
    double calculateEta() {
        if (express)
            return distance/75;
        else
            return distance/40;
    }

    /**
     * calculates delivery cost
     * @return delivery cost
     */
    @Override
    double calculateCost() {
        if (express) {
            double base=distance*.75;
            return (.2*base)+base;
        }
        else
            return distance*.75;
    }
}
