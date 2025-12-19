import java.util.Scanner;

public class TaxCalculator {
    public static void main(String[] args) {
        double tax = 0;
        String status;
        double income;
        final double RATE1 = .1;
        final double RATE2 = .25;
        final double Single = 32000;
        final double Married = 64000;
        Scanner in = new Scanner(System.in);
        System.out.println("enter status; s for single, m for married");
        status=in.next();
        System.out.println("enter income");
        income=in.nextDouble();
        if (status.equals("s")){
            if (income>=32000) {
                tax = RATE1*income;
            }
            else {
                tax=RATE1*+RATE2*(income-Single);
            }
        }
        else if (status.equals("m")){
            if (income>=Married){
                tax= RATE1*income;
            }
            else {
                tax=RATE1*Married+RATE2*(income-Married);
            }
        }
    System.out.println("Tax: " + tax);
    }
}
