public class Problem5 {
    /**
     * finds the first found instance of an integer within a string, keeping the sign and breaking if an integer is not immediately after an operator
     * @param s String given
     * @return integer found
     */
    static int atoi(String s){
        double number=0;
        double multiplier=0;
        int length=1;
        boolean negative=false;
        for (int i = 0; i < s.length(); i++) {
            if ((s.charAt(i)==45 || s.charAt(i)==37 || s.charAt(i)==42 || s.charAt(i)==43 || s.charAt(i)==47 || s.charAt(i)==94) && i+1<s.length() && !(s.charAt(i+1)>=48 && s.charAt(i+1)<=57)) // if a operator is found and there is no number afterward break
                break;
            if (s.charAt(i)>=48 && s.charAt(i)<=57){
                if (i!=0 && s.charAt(i-1)=='-')
                    negative=true;
                while (i<s.length() && ((s.charAt(i)>=48 && s.charAt(i)<=57) || (s.charAt(i)==45 || s.charAt(i)==37 || s.charAt(i)==42 || s.charAt(i)==43 || s.charAt(i)==47 || s.charAt(i)==94))) { // check if the next variable is a number or an operator
                    if (s.charAt(i)!=45 && s.charAt(i)!=37 && s.charAt(i)!=42 && s.charAt(i)!=43 && s.charAt(i)!=47 && s.charAt(i)!=94) { // check if it is not an operator, if not then add the number to the total
                        multiplier-=1;
                        number+=(s.charAt(i) - 48)*Math.pow(10, multiplier);
                        length*=10;
                    }
                    else if(s.charAt(i)==45)
                        negative=true;
                    i+=1;
                    if (number!=0 && i<s.length() && (s.charAt(i)==45 || s.charAt(i)==37 || s.charAt(i)==42 || s.charAt(i)==43 || s.charAt(i)==47 || s.charAt(i)==94)) // if there are no leading zeros, and the next character is an operator, break sequence
                        break;
                }
                break;
            }
        }
        if (negative)
            return (int) -Math.round(number*length);
        return (int) Math.round(number*length);
    }
}