import java.util.*;
import java.io.*;

public class BigInt implements Comparable<BigInt>{

    private List<Integer> l = new ArrayList<>();
    private boolean negative=false;

    /**
     * constructor to copy the reverse order digits in string s to an instance variable list
     * @param s string given
     */
    public BigInt(String s){
        int temp;
        String tempString="";
        if (s.charAt(0)=='-')
            negative=true;
        while (s.charAt(0)==48){ // remove leading zeroes
            if (s.length()==1)
                break;
            s=s.substring(1);
        }
        for (int i = 0; i < s.length(); i++) { // create a temp string with only integers to make the next part easier
            if (s.charAt(i)>=48 && s.charAt(i)<=57)
                tempString+=s.charAt(i);
        }
        for (int j = tempString.length()-1; j >= 0; j--) { // add each integer to the list backwards
            temp=tempString.charAt(j)-48;
            l.add(temp);
        }
        if (l.isEmpty())
            l.add(0);
    }

    /**
     * Override the equals method to determine if the given object is equal to the current list
     * @param o object given, may or may not be a big int object
     * @return true if they are the same, false otherwise
     */
    @Override
    public boolean equals(Object o){
        if (o instanceof BigInt){
            BigInt compared= (BigInt) o;
            if (compared.l.size()!=this.l.size())
                return false;
            if (compared.negative!=this.negative)
                return false;
            for (int i = 0; i < l.size(); i++) {
                if (compared.l.get(i)!=this.l.get(i))
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     * override method to compute a stringified version of the new backwards number
     * @return stringified version of the backwards number
     */
    @Override
    public String toString(){
        String s="";
        if (negative)
            s+="-";
        for (int i = l.size()-1; i>= 0; i--) {
            s+=l.get(i);
        }
        return s;
    }

    /**
     * compares the current list to the given object's list
     * @param b2 the object to be compared to
     * @return if this<b2 return -1, if this>b2 return 1, if this==b2 return 0
     */
    @Override
    public int compareTo(BigInt b2) {
        if (this.negative && !b2.negative)
            return -1;
        else if (!this.negative && b2.negative)
            return 1;
        else if (this.negative && b2.negative){
            if (this.l.size()<b2.l.size())
                return 1;
            else if (this.l.size()>b2.l.size())
                return -1;
            for (int i = l.size()-1; i>= 0; i--) {
                if (this.l.get(i)<b2.l.get(i))
                    return 1;
                else if (this.l.get(i)>b2.l.get(i))
                    return -1;
            }
            return 0;
        }
        else if (!this.negative && !b2.negative) {
            if (this.l.size() < b2.l.size())
                return -1;
            else if (this.l.size() > b2.l.size())
                return 1;
            for (int j = l.size() - 1; j >= 0; j--) {
                if (this.l.get(j) < b2.l.get(j))
                    return -1;
                else if (this.l.get(j) > b2.l.get(j))
                    return 1;
            }
        }
        return 0;
    }

    /**
     * creates a deep copy of the passed object's list
     * @return deep copy of BigInt list
     */
    BigInt copy(){
        BigInt c = new BigInt(this.toString());
        return c;
    }

    /**
     * creates a copy of BigInt and negates it
     * @return negated copy of big int
     */
    BigInt negate(){
        BigInt c = this.copy();
        if (this.negative)
            c.negative=false;
        else
            c.negative=true;
        return c;
    }

    /**
     * determines if the current object and given objects have the same sign i.e., positive or negative
     * @param b object given
     * @return true if they have the same signs, false otherwise
     */
    private boolean areDifferentSigns(BigInt b){
        if (b.negative==this.negative)
            return true;
        return false;
    }

    /**
     * assumes this and b are positive numbers, and computes the sum of them
     * @param b BigInt given
     * @return added sum
     */
    private BigInt addPositive(BigInt b) {
        BigInt c = null;
        boolean carry=false;
        if (this.l.size() >= b.l.size()) {
            c = this.copy();
            for (int i = 0; i < b.l.size(); i++) {
                if (carry) {
                    c.l.set(i, c.l.get(i) + b.l.get(i) + 1);
                    carry = false;
                }
                else
                    c.l.set(i, c.l.get(i) + b.l.get(i));
                if (c.l.get(i) >= 10) {
                    c.l.set(i, c.l.get(i) - 10);
                    carry = true;
                }
            }
            int j=b.l.size();
            int temp=0;
            while (carry && j<c.l.size()){
                temp=c.l.get(j)+1;
                if (temp>=10) {
                    carry = true;
                    c.l.set(j, temp-10);
                }
                else {
                    c.l.set(j,temp);
                    carry=false;
                }
                j++;
            }
            if (carry)
                c.l.add(1);
        }
        else {
            c = b.copy();
            for (int i = 0; i < l.size(); i++) {
                if (carry) {
                    c.l.set(i, c.l.get(i) + this.l.get(i) + 1);
                    carry = false;
                }
                else
                    c.l.set(i, c.l.get(i) + l.get(i));
                if (c.l.get(i) >= 10) {
                    c.l.set(i, c.l.get(i) - 10);
                    carry = true;
                }
            }
            int j=l.size();
            int temp=0;
            while (carry && j<c.l.size()){
                temp=c.l.get(j)+1;
                if (temp>=10) {
                    carry = true;
                    c.l.set(j, temp-10);
                }
                else {
                    c.l.set(j,temp);
                    carry=false;
                }
                j++;
            }
            if (carry)
                c.l.add(1);
        }
        String newBigInt=c.toString();
        if (newBigInt.charAt(0)=='-')
            while (newBigInt.length()>1 && newBigInt.charAt(1)=='0')
                newBigInt=newBigInt.charAt(0) + newBigInt.substring(2);
        else
            while (newBigInt.length()>1 && newBigInt.charAt(0)=='0'){
                newBigInt=newBigInt.substring(1);
        }
        if (newBigInt.equals("-"))
            newBigInt="0";
        return new BigInt(newBigInt);
    }

    /**
     * subtract two numbers that are positive, while the left hand number is greater than or equal to the right hand number
     * @param b BigInt given
     * @return difference between numbers
     */
    private BigInt subPositive(BigInt b) {
        BigInt c = null;
        boolean carry = false;
        if (this.l.size() >= b.l.size()) {
            c = this.copy();
            for (int i = 0; i < b.l.size(); i++) {
                if (carry) {
                    c.l.set(i, c.l.get(i) - b.l.get(i) - 1);
                    carry = false;
                }
                else
                    c.l.set(i, c.l.get(i) - b.l.get(i));
                if (c.l.get(i) < 0) {
                    c.l.set(i, c.l.get(i) + 10);
                    carry = true;
                }

                }
            }
        int j=b.l.size();
        int temp=0;
        while (carry && j<c.l.size()) {
            temp=c.l.get(j)-1;
            if (temp<0){
                c.l.set(j,temp+10);
                carry=true;
            }
            else {
                c.l.set(j, temp);
                carry = false;
            }
            j++;
        }
        if (c.l.isEmpty())
            return new BigInt("0");
        String newBigInt = c.toString();
        if (newBigInt.charAt(0)=='-')
            while (newBigInt.length()>1 && newBigInt.charAt(1)=='0')
                newBigInt=newBigInt.charAt(0) + newBigInt.substring(2);
        else
            while (newBigInt.length()>1 && newBigInt.charAt(0) == '0'){
                newBigInt = newBigInt.substring(1);
            }
        if (newBigInt.equals("-"))
            newBigInt="0";
        return new BigInt(newBigInt);
    }

    /**
     * multiply two numbers of the same sign
     * @param b BigInt given
     * @return multiplied numbers
     */
    private BigInt mulPositive(BigInt b){
        int tempProd=0;
        boolean carry=false;
        int carryAmount=0;
        StringBuilder product = new StringBuilder();
        for (int i = 0; i < l.size(); i++) {
            for (int j = 0; j < b.l.size(); j++) {
                tempProd=b.l.get(j)*l.get(i);
            }
        }
        product.reverse();
        return new BigInt(product.toString());
    }

    /**
     * helper function to establish if a number (regardless of sign) is greater, less than, or equal to another
     * @param b2 BigInt given
     * @return if this<b2 return -1, if this>b2 return 1, if this==b2 return 0
     */
    int compareHelper(BigInt b2){
        if (this.l.size() < b2.l.size())
            return -1;
        else if (this.l.size() > b2.l.size())
            return 1;
        for (int j = l.size() - 1; j >= 0; j--) {
            if (this.l.get(j) < b2.l.get(j))
                return -1;
            else if (this.l.get(j) > b2.l.get(j))
                return 1;
        }
        return 0;
    }

    /**
     * determines which private method should be run when adding two numbers
     * @param b BigInt given
     * @return added numbers
     */
    BigInt add(BigInt b){
        if (!this.negative && !b.negative) // if both numbers are positive (A+B)
            return addPositive(b);
        else if (this.negative && b.negative) { // if both numbers are negative -(A+B)
            return addPositive(b);
        }
        else if (this.negative && !b.negative) {
            if (this.compareHelper(b)<0) { // if A is negative and B is positive (B-A) and A<B
                return b.subPositive(this);
            }
            else { // else A>=B and -(A-B)
                return subPositive(b);
            }
        }
        else if (!this.negative && b.negative) // if A is positive and B is negative
            if (this.compareHelper(b)>=0) // A>=B then A-B
                return subPositive(b);
            else { // else then -(A-B)
                return b.subPositive(this);
            }
        return null;
    }

    /**
     * determines which private method should be called when subtracting two numbers
     * @param b BigInt given
     * @return difference of the numbers
     */
    BigInt sub(BigInt b){
        if (!this.negative && b.negative) // if A is positive and B is negative (A-(-B))
            return addPositive(b.negate());
        else if (this.negative && !b.negative) // if A is negative and B is positive -(A+B)
            if (this.compareHelper(b)>=0)
                return addPositive(b);
            else
                return this.negate().addPositive(b).negate();
        else if (this.negative && b.negative) // if both are negative
            if (this.compareHelper(b)>=0) // -(A-B) if A>=B
                return subPositive(b);
            else // (B-A) if A<B
                return b.subPositive(this).negate();
        else if (!this.negative && !b.negative) // if both are positive
            if (this.compareHelper(b)>=0) // (A-B) if A>=B
                return subPositive(b);
            else // -(B-A) if A<B
                return b.subPositive(this).negate();
        return null;
    }

    /**
     * multiplies two numbers from the BigInt class
     * @param b BigInt given
     * @return product of the two numbers
     */
    BigInt mul(BigInt b){
        if (areDifferentSigns(b))
            return mulPositive(b).negate();
        else
            return mulPositive(b);
    }
    BigInt div(BigInt divisor){
        return null;
    }
}