public class Problem1 {
    /**
     * Take's full name and year and converts it to the first five characters of the last name, and first character of first name, and last 2 digits of year.
     * @param first first name
     * @param last last name
     * @param year year
     * @return formatted version
     */
    static String userId(String first, String last, int year){
        String fname=first.substring(0,1);
        String lname=last.substring(0,5);
        int lasttwo=year%10;
        year=year/10;
        lasttwo+=(year%10)*10;
        return lname + fname + lasttwo;
    }
}
