package tuition;

import java.util.Calendar;
import java.util.StringTokenizer;
/**
 * Checks if DOB object dates are valid.
 * Dates are declared invalid if they do not follow the mm/dd/yyyy format, are a false leap year,
 * go beyond today's date, age < 16, and or have invalid months, days, or years.
 * @author Sharukh Khan, Hamad Naveed
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int FEBRUARY_LEAP_YEAR_MONTH_END = 29;
    public static final int MONTH_START = 1;
    public static final int MONTH_END = 31;
    public static final int MIN_AGE = 16;

    /**
     * Creates Date object using user input date.
     * @param date entered by user.
     */
    public Date(String date) {
        StringTokenizer st = new StringTokenizer(date, "/");
        month = Integer.parseInt(st.nextToken());
        day = Integer.parseInt(st.nextToken());
        year = Integer.parseInt(st.nextToken());
    }

    /**
     * Checks if date is valid.
     * Checks if student's age is <16
     * For leap year dates calls isLeapYear method to validate the date.
     * @return false if date is invalid or age < 16, true if date is valid
     */
    public boolean isValid() {
        Calendar studentDate = Calendar.getInstance();
        studentDate.set(year, month - 1, day);
        Calendar currentDate = Calendar.getInstance();
        int age = currentDate.getWeekYear() - year;
        String studentDateString = String.format("%02d/%02d/%04d", month, day, year);
        if ((month > currentDate.get(Calendar.MONTH))
                || (month == currentDate.get(Calendar.MONTH) &&
                day > currentDate.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }
        if (studentDate.after(currentDate) || (age < MIN_AGE)) {
            System.out.println("DOB invalid: " + studentDateString + " younger than 16 years old.");
            return false;
        }

        if (day > MONTH_END || day < MONTH_START) {
            System.out.println("DOB invalid: 2/29/2003 not a valid calendar date!");
            return false;
        }

        switch (month - 1) {
            case Calendar.JANUARY:
            case Calendar.MARCH:
            case Calendar.MAY:
            case Calendar.OCTOBER:
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.DECEMBER:
                if (day <= MONTH_END) {
                    return true;
                } else {
                    System.out.println("DOB invalid: 2/29/2003 not a valid calendar date!");
                    return false;
                }
            case Calendar.APRIL:
            case Calendar.JUNE:
            case Calendar.SEPTEMBER:
            case Calendar.NOVEMBER:
                if (day != MONTH_END) {
                    return true;
                } else {
                    System.out.println("DOB invalid: " + studentDateString + " not a valid calendar date!");
                    return false;
                }
            case Calendar.FEBRUARY:
                if (day > FEBRUARY_LEAP_YEAR_MONTH_END) {
                    System.out.println("DOB invalid: " + studentDateString + " not a valid calendar date!");
                    return false;
                } else if (isLeapYear()) {
                    if (day <= FEBRUARY_LEAP_YEAR_MONTH_END) {
                        return true;
                    } else {
                        System.out.println("DOB invalid: " + studentDateString + " not a valid calendar date!");
                        return false;
                    }
                } else {
                    if (day < FEBRUARY_LEAP_YEAR_MONTH_END) {
                        return true;
                    } else {
                        System.out.println("DOB invalid: " + studentDateString + " not a valid calendar date!");
                        return false;
                    }
                }
            default:
                System.out.println("DOB invalid: " + studentDateString + " not a valid calendar date!");
                return false;
        }
    }

    /**
     * Verifies if the user date is a valid leap year date.
     * @return true if date is valid a leap year date, false if date is an invalid leap year date.
     */
    public boolean isLeapYear() {
        if (year % QUATERCENTENNIAL == 0) {
            return false;
        } else if (year % QUADRENNIAL == 0 || year % CENTENNIAL == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * String formed by combining month, day, and year into date format.
     * @return String
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return month + "/" + day + "/" + year;
    }

    /**
     * Compares this date to another date.
     * @param other The date to compare to.
     * @return A negative integer, zero, .
     */
    @Override
    public int compareTo(Date other) {
        if (this.year != other.year) {
            return this.year - other.year;
        } else if (this.month != other.month) {
            return this.month - other.month;
        } else {
            return this.day - other.day;
        }
    }

    /**
     * Compares date objects.
     * @param o
     * @returns true if date objects are equal, returns false if they are different.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Date)) {
            return false;
        }
        Date other = (Date) o;
        return this.year == other.year && this.month == other.month && this.day == other.day;
    }

}