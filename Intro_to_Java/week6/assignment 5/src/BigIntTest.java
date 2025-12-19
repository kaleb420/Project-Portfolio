import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class BigIntTest {

    @Test
    void BigIntTests() {
        BigInt b = new BigInt("042");
        BigInt b2 = new BigInt("0");
        BigInt b3 = new BigInt("-42");
        BigInt b4 = new BigInt("0000420000");
        BigInt b5 = new BigInt("+42");
        BigInt b6 = new BigInt("00042");
        BigInt b7 = new BigInt("-00043");
        BigInt b8 = new BigInt("000");
        BigInt b9 = new BigInt("-5");
        BigInt b10 = new BigInt("938745813415974185817459081481951");
        BigInt b11 = new BigInt("-1");
        BigInt b12 = new BigInt("50");
        BigInt b13 = new BigInt("321");
        BigInt b14 = new BigInt("-389");
        BigInt b15 = new BigInt("86");
        BigInt b16 = new BigInt("847");
        BigInt b17 = new BigInt("-466");
        BigInt b18 = new BigInt("312");
        BigInt b19 = new BigInt("-344");
        BigInt b20 = new BigInt("604");
        BigInt b21 = new BigInt("489");
        BigInt b22 = new BigInt("-97");
        BigInt b23 = new BigInt("-197");
        BigInt b24 = new BigInt("99999");
        BigInt b25 = new BigInt("999999");
        BigInt b26 = new BigInt("1111111");
        BigInt b27 = new BigInt("-34");
        BigInt b28 = new BigInt("1021");
        BigInt b29 = new BigInt("-7");
        BigInt b30 = new BigInt("-493");
        assertEquals(true, b.equals(b));
        assertEquals(true, b.equals(b6));
        assertEquals(false, b.equals(b3));
        assertEquals(true, b.equals(b5));
        assertEquals(false, b.equals(b4));
        assertEquals(true, b2.equals(b8));
        assertEquals("0", b2.toString());
        assertEquals("42", b.toString());
        assertEquals("-42", b3.toString());
        assertEquals("420000", b4.toString());
        assertEquals(0, b.compareTo(b5));
        assertEquals(-1, b3.compareTo(b5));
        assertEquals(1, b5.compareTo(b3));
        assertEquals(-1, new BigInt("-3").compareHelper(new BigInt("5")));
        /*assertEquals(new BigInt("420042"), b.addPositive(b4)); // checks adding numbers where B.length>A.length
        assertEquals(new BigInt("100"), b12.addPositive(b12)); // checks adding numbers where A.length>=B.length and it carries over
        assertEquals(new BigInt("199998"), b24.addPositive(b24)); // checks adding numbers with carry over for every variable where A.length>=B.length
        assertEquals(new BigInt("1099998"), b24.addPositive(b25)); // same as last but B.length>A.length
        assertEquals(new BigInt("392"), b21.subPositive(b22)); // checks subtracting numbers where A.length>=B.length
        assertEquals(new BigInt("111112"), b26.subPositive(b25)); // checks subtracting numbers where A.length>=B.length and carry over every number
        assertEquals(new BigInt("999999"), b25.subPositive(b2)); // checks subtracting numbers where A.lenth>=B.length and no carry
        assertEquals(new BigInt("987"), b28.subPositive(new BigInt("34"))); // checks subtracting where total length is shrunk
        assertEquals(new BigInt("1055"), b27.addPositive(b28)); */
        assertEquals(new BigInt("-500"), b29.add(b30));
        assertEquals(new BigInt("420042"), b.add(b4)); // checks adding two positive numbers
        assertEquals(new BigInt("100"), b12.add(b12)); // checks adding when carry is going to go over length
        assertEquals(new BigInt("0"), b.add(b3)); // checks adding a positive number and a negative number when A>=B
        assertEquals(new BigInt("-42"), b2.add(b3)); // checks adding a positive number and a negative number when A<B
        assertEquals(new BigInt("0"), b3.add(b6)); // checks adding a negative number and a positive number
        assertEquals(new BigInt("-6"), b9.add(b11));  // checks adding a negative number and a negative number
        assertEquals(new BigInt("-68"), b13.add(b14)); // additional test
        assertEquals(new BigInt("933"), b15.add(b16)); // additional test
        assertEquals(new BigInt("-154"), b17.add(b18)); // additional test
        assertEquals(new BigInt("260"), b19.add(b20)); // additional test
        assertEquals(new BigInt("-294"), b22.add(b23)); // additional test
        assertEquals(new BigInt("392"), b21.add(b22)); // additional test
        assertEquals(new BigInt("987"), b27.add(b28)); // additional test
        assertEquals(new BigInt("0"), b.sub(b)); // checks subtracting two positive numbers of the same value
        assertEquals(new BigInt("710"), b13.sub(b14)); // additional test
        assertEquals(new BigInt("85"), b6.sub(b7)); // checks subtracting a positive number to a negative number
        assertEquals(new BigInt("-85"), b7.sub(b6)); // checks subtracting a negative number to a positive number when A>=B
        assertEquals(new BigInt("-363"), b3.sub(b13)); // checks subtracting a negative number to a positive number when A<B
        assertEquals(new BigInt("0"), b3.sub(b3)); // checks subtracting two negative numbers when A>=B
        assertEquals(new BigInt("37"), b9.sub(b3)); // checks subtracting two negative numbers when A<B
        assertEquals(new BigInt("-1"), b7.sub(b3)); // additional test
        assertEquals(new BigInt("-100"), b23.sub(b22)); // additional test
        assertEquals(new BigInt("-5"), b9.sub(b8)); // additional test
        assertEquals(new BigInt("535"), b16.sub(b18)); // additional test
        assertEquals(new BigInt("656"), b18.sub(b19)); // additional test
        assertEquals(new BigInt("938745813415974185817459081481951"), b10.add(b8));
        //assertEquals(new BigInt("1764"), b6.mul(b6)); // checks multiplying two numbers with the same sign
        //assertEquals(new BigInt("-210"), b9.mul(b3)); // checks multiplying two numbers with different signs
    }
}