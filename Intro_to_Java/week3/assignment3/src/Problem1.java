import java.util.Arrays;

public class Problem1 {
    /**
     * Iterate through min to max, and if the iteration is divisible by 3 add Fizz to the string, if it is divisible by 5 add Buzz to the string, and if it is divisible by 3 and 5 add FizzBuzz to the string
     * @param min low end interval
     * @param max high end interval
     * @return formatted string
     */
    static String[] fizzBuzz(int min, int max){
        String[] s= new String[max-min+1];
        int i=min;
        int counter=0;
        while (i<=max){
            if (i%3==0 && i%5==0)
                s[counter]="FizzBuzz";
            else if (i%3==0)
                s[counter]="Fizz";
            else if (i%5==0)
                s[counter]="Buzz";
            else
                s[counter]=String.valueOf(i);
            counter++;
            i++;
        }
        return s;
    }
}
