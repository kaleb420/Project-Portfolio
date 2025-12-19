public class Problem14 {
    /**
     * Determines whether or not we can convert a given string into
     * an integer datatype.
     * @param n input string.
     * @return true if we can convert n to an int, false otherwise.
     */
    static boolean isNumeric(String n){
        try {
            Integer.parseInt(n);
            return true;
        }
        catch (NumberFormatException ex){
            return false;
        }
    }
    /**
     * Reuses code to not have code repeated in the isValidIp4 method
     * @param ip ip address at current stage of checking
     * @return formatted ip address at current checkpoint
     */
    static String currentFirst(String ip){
        if (!ip.contains("."))
            return "-1";
        int first=ip.indexOf('.');
        return ip.substring(0,first);
    }
    /**
     * determines if an inputted ip is valid
     * @param ip ip address
     * @return true if the ip address is valid, false otherwise
     */
    static boolean isValidIpv4(String ip){
        int totalLength=0;
        if (ip.equals(""))
            return false;
        String current=currentFirst(ip);
        if (isNumeric(current) && Integer.parseInt(current)<=255 && Integer.parseInt(current)>=0){
            totalLength+=current.length()+1;
            String nIp=ip.substring(totalLength);
            String nCurrent=currentFirst(nIp);
            if (isNumeric(nCurrent) && Integer.parseInt(nCurrent)<=255 && Integer.parseInt(nCurrent)>=0){
                totalLength+=nCurrent.length()+1;
                String nnIp=ip.substring(totalLength);
                String nnCurrent=currentFirst(nnIp);
                if (isNumeric(nnCurrent) && Integer.parseInt(nnCurrent)<=255 && Integer.parseInt(nnCurrent)>=0){
                    totalLength+=nnCurrent.length()+1;
                    String nnnCurrent=ip.substring(totalLength);
                    if (isNumeric(nnnCurrent) && Integer.parseInt(nnnCurrent)<=255 && Integer.parseInt(nnnCurrent)>=0)
                        return true;
                }
            }
        }
        return false;
    }
}
