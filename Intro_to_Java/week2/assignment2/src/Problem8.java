public class Problem8 {
    /**
     * reorder the files so the lower number returns -1, and higher returns 1, otherwise use the compareTo function to determine what should be returned
     * @param f1 file 1
     * @param f2 file 2
     * @return -1 if f1<f2, 0 if f1==f2, and 1 if f1>f2
     */
    static int compareFiles(String f1, String f2){
        int index=0;
        int index2=0;
        int num=0;
        double f1Num=0;
        double f2Num=0;
        double multiplier=1;
        int length=1;
        for (int j=0; j<f1.length(); j++){
            if (j>=f2.length())
                return 1;
            if ((f1.charAt(j)>=48 && f1.charAt(j)<=57) && (f2.charAt(j)>=48 && f2.charAt(j)<=57)) {
                num = j;
                break;
            }
            else if (f1.charAt(j) != f2.charAt(j)) {
                if (f1.compareTo(f2)>0)
                    return 1;
                else if (f1.compareTo(f2)<0)
                    return -1;
            }
        }
        for (int i = num; num < f1.length(); i++) {
            if (f1.charAt(i)=='.'){
                break;
            }
            f1Num+=(f1.charAt(i)-48)*multiplier;
            multiplier/=10;
            length*=10;
        }
        f1Num*=length;
        multiplier=1;
        length=1;
        for (int i = num; i < f2.length(); i++) {
            if (f2.charAt(i)=='.'){
                break;
            }
            f2Num+=(f2.charAt(i)-48)*multiplier;
            multiplier/=10;
            length*=10;
        }
        f2Num*=length;
        if (f1Num<f2Num)
            return -1;
        else if (f1Num==f2Num) {
            if (f1.contains(".")) {
                index = f1.indexOf(".");
            }
            if (f2.contains(".")) {
                index2 = f2.indexOf(".");
            }
            String nF1 = f1.substring(index);
            String nF2 = f2.substring(index2);
            if (nF1.compareTo(nF2) > 0)
                return 1;
            else if (nF1.compareTo(nF2) < 0)
                return -1;
            else if (nF1.compareTo(nF2) == 0)
                return 0;
        }
        else if (f1Num>f2Num)
            return 1;
        return 1;
    }
}