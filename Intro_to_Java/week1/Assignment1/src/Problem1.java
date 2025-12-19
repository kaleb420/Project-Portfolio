public class Problem1 {
    /** converts distance from gigameters to light seconds
     *
     * @param gm inputted gigameters
     * @return light seconds
     */
    static double gigameterToLightsecond(double gm){
        double meters=1000000000*gm;
        double lightSpeed=299792458;
        return meters/lightSpeed;
    }
}
