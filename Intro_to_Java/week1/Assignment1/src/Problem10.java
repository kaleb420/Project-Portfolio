public class Problem10 {
    /**
     * if the string ends in "try" remove it, otherwise return original string
     * @param s original string
     * @return original or formatted string
     */
    static String cutTry(String s){
        int length=s.length();
        if (length>=2){
            String last3=s.substring(length-3);
            if (last3.equals("try"))
                return s.substring(0,length-3);
        }
        return s;
    }
}
