package tuition;

import static org.junit.Assert.*;
import tuition.Date;
import org.junit.Test;

public class DateTest {

    @org.junit.Test
    public void isValid() {
    }

    @Test
    public void test_case1(){
        Date testCase1 = new Date("2/29/2003");
        assertFalse(testCase1.isValid());
    }

    @Test
    public void test_case2(){
        Date testCase2 = new Date("4/31/2003");
        assertFalse(testCase2.isValid());
    }


    @Test
    public void test_case3 (){
        Date testCase3 = new Date("13/31/2003");
        assertFalse(testCase3.isValid());
    }

    @Test
    public void test_case4 (){
        Date testCase4 = new Date("3/32/2003");
        assertFalse(testCase4.isValid());
    }

    @Test
    public void test_case5 (){
        Date testCase5 = new Date("-1/31/2003");
        assertFalse(testCase5.isValid());
    }

    @Test
    public void test_case6 (){
        Date testCase6 = new Date("4/3/2003");
        assertTrue(testCase6.isValid());
    }
    @Test
    public void test_case7 (){
        Date testCase7 = new Date("1/20/2003");
        assertTrue(testCase7.isValid());
    }

}