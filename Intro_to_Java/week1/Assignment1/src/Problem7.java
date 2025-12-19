public class Problem7 {
    /**
     * Find the username in the email
     * @param email email inputted
     * @return username
     */
    static String cutUsername(String email){
        int j=email.indexOf("@");
        return email.substring(0,j);
    }
}
