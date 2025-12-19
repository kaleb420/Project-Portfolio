public class Problem4 {
    /**
     * determine if a z value is an extreme outlier
     * @param x data point
     * @param avg mean
     * @param stddev standard deviation
     * @return z score and determine if it is an extreme outlier
     */
    static boolean isExtremeOutlier(double x, double avg, double stddev){
        if ((x-avg)/stddev<=3 && (x-avg)/stddev>=-3)
            return false;
        else
            return true;

    }
}
