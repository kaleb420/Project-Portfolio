public class TempConverter {
    /**
     * Converts a given temperature in degrees
     * Fahrenheit to degrees Celsius.
     * @param f temperature in Fahrenheit.
     * @return equal temperature in Celsius.
     */
    public static double fToC(double f){
        return (f-32)*(5.0/9.0);
    }

    public static void main(String[] args) {
        double f=212;
        System.out.println(f+"f = " + fToC(f)+"c");
    }
}