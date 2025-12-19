public class DroneDelivery extends Delivery{
    private double distance;
    private double weight;
    private Delivery identifier;

    /**
     * constructor to initialize the instance variables
     * @param s identifier
     * @param distance distance
     * @param weight weight
     */
    public DroneDelivery(String s, double distance, double weight){
        super(s);
        this.distance=distance;
        this.weight=weight;
    }
    /**
     * calculates the estimated time of arrival
     * @return estimated time of arrival
     */
    @Override
    double calculateEta() {
        return distance/60;
    }

    /**
     * calculates the cost of delivery
     * @return cost of delivery
     */
    @Override
    double calculateCost() {
        return 1.5*distance+2*weight;
    }
}
