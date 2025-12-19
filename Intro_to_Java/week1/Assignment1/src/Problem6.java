public class Problem6 {
    /**
     * compute distance traveled base on given variables
     * @param vi initial velocity
     * @param a acceleration in meters per second
     * @param t time in seconds
     * @return distance traveled
     */
    static double distanceTraveled(double vi, double a, double t){
        return t*vi+.5*a*Math.pow(t,2);
    }
}
