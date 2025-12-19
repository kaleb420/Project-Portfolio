public class StringsDemo {
    /**
     * receive a name in the firstname middlename lastname format
     * and retunr it in lastname, firstinitial middleinitial
     * @param s
     * @return
     */
    public static String nameFormatter(String s){
         int i=s.indexOf(" ");
         String fname=s.substring(0,i);
         int j=s.lastIndexOf(" ");
         String lname=s.substring(j+1);
         String mname=s.substring(i+1,j);
         return lname + ", " + fname.charAt(0) + ". " + mname.charAt(0) + ". ";
    }
    public static void main(String[] args) {
        /*
        String s1="hello";
        String s2=new String("hello");
        String s3="hello";
        String s4= new String("hello");
        System.out.println("s1==s2: " + (s1==s2));
        System.out.println("s1==s3: " + (s1==s3));
        System.out.println("s2==s4: " + s2.equals(s4));
         */
        System.out.println(nameFormatter("Martin Luther King"));
    }
}