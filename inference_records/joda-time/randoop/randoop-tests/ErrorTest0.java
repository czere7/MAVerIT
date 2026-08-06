import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ErrorTest0 {

    public static boolean debug = false;

    public void assertBooleanArrayEquals(boolean[] expectedArray, boolean[] actualArray) {
        if (expectedArray.length != actualArray.length) {
            throw new AssertionError("Array lengths differ: " + expectedArray.length + " != " + actualArray.length);
        }
        for (int i = 0; i < expectedArray.length; i++) {
            if (expectedArray[i] != actualArray[i]) {
                throw new AssertionError("Arrays differ at index " + i + ": " + expectedArray[i] + " != " + actualArray[i]);
            }
        }
    }

    @Test
    public void test01() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test01");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        int int10 = dateMidnight4.compareTo((org.joda.time.ReadableInstant) dateMidnight7);
        org.joda.time.chrono.GJChronology gJChronology11 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTime dateTime12 = dateMidnight7.toDateTime((org.joda.time.Chronology) gJChronology11);
        org.joda.time.DateTime dateTime14 = dateTime12.plusMillis((int) (short) 0);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateMidnight2 and dateTime14", (dateMidnight2.compareTo(dateTime14) == 0) == dateMidnight2.equals(dateTime14));
    }

    @Test
    public void test02() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test02");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        int int10 = dateMidnight4.compareTo((org.joda.time.ReadableInstant) dateMidnight7);
        org.joda.time.Chronology chronology12 = null;
        org.joda.time.DateMidnight dateMidnight13 = new org.joda.time.DateMidnight((long) (short) -1, chronology12);
        org.joda.time.DateMidnight dateMidnight15 = dateMidnight13.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology17 = null;
        org.joda.time.DateMidnight dateMidnight18 = new org.joda.time.DateMidnight((long) (short) -1, chronology17);
        org.joda.time.DateMidnight dateMidnight20 = dateMidnight18.minusMonths((int) (byte) 100);
        int int21 = dateMidnight15.compareTo((org.joda.time.ReadableInstant) dateMidnight18);
        org.joda.time.chrono.GJChronology gJChronology22 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTime dateTime23 = dateMidnight18.toDateTime((org.joda.time.Chronology) gJChronology22);
        boolean boolean24 = dateMidnight4.isEqual((org.joda.time.ReadableInstant) dateTime23);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateMidnight2 and dateTime23", (dateMidnight2.compareTo(dateTime23) == 0) == dateMidnight2.equals(dateTime23));
    }

    @Test
    public void test03() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test03");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        int int10 = dateMidnight4.compareTo((org.joda.time.ReadableInstant) dateMidnight7);
        org.joda.time.chrono.GJChronology gJChronology11 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTime dateTime12 = dateMidnight7.toDateTime((org.joda.time.Chronology) gJChronology11);
        org.joda.time.DateTimeZone dateTimeZone13 = null;
        org.joda.time.LocalDateTime localDateTime14 = new org.joda.time.LocalDateTime(dateTimeZone13);
        int int15 = localDateTime14.getYear();
        org.joda.time.DateTime dateTime16 = dateTime12.withFields((org.joda.time.ReadablePartial) localDateTime14);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateMidnight2 and dateTime12", (dateMidnight2.compareTo(dateTime12) == 0) == dateMidnight2.equals(dateTime12));
    }

    @Test
    public void test04() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test04");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        int int10 = dateMidnight4.compareTo((org.joda.time.ReadableInstant) dateMidnight7);
        org.joda.time.chrono.GJChronology gJChronology11 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTime dateTime12 = dateMidnight7.toDateTime((org.joda.time.Chronology) gJChronology11);
        org.joda.time.DateTimeZone dateTimeZone13 = null;
        org.joda.time.DateTime dateTime14 = dateTime12.toDateTime(dateTimeZone13);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateMidnight2 and dateTime14", (dateMidnight2.compareTo(dateTime14) == 0) == dateMidnight2.equals(dateTime14));
    }

    @Test
    public void test05() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test05");
        org.joda.time.Chronology chronology3 = null;
        org.joda.time.DateMidnight dateMidnight4 = new org.joda.time.DateMidnight((long) (short) -1, chronology3);
        org.joda.time.DateMidnight dateMidnight6 = dateMidnight4.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology8 = null;
        org.joda.time.DateMidnight dateMidnight9 = new org.joda.time.DateMidnight((long) (short) -1, chronology8);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.minusMonths((int) (byte) 100);
        int int12 = dateMidnight6.compareTo((org.joda.time.ReadableInstant) dateMidnight9);
        org.joda.time.chrono.GJChronology gJChronology13 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTime dateTime14 = dateMidnight9.toDateTime((org.joda.time.Chronology) gJChronology13);
        org.joda.time.MutablePeriod mutablePeriod15 = new org.joda.time.MutablePeriod((long) 2147483647, (long) 2026, (org.joda.time.Chronology) gJChronology13);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateMidnight4 and dateTime14", (dateMidnight4.compareTo(dateTime14) == 0) == dateMidnight4.equals(dateTime14));
    }

    @Test
    public void test06() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test06");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        int int10 = dateMidnight4.compareTo((org.joda.time.ReadableInstant) dateMidnight7);
        org.joda.time.chrono.GJChronology gJChronology11 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTime dateTime12 = dateMidnight7.toDateTime((org.joda.time.Chronology) gJChronology11);
        org.joda.time.Chronology chronology14 = null;
        org.joda.time.DateMidnight dateMidnight15 = new org.joda.time.DateMidnight((long) (short) -1, chronology14);
        org.joda.time.DateMidnight dateMidnight17 = dateMidnight15.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology19 = null;
        org.joda.time.DateMidnight dateMidnight20 = new org.joda.time.DateMidnight((long) (short) -1, chronology19);
        org.joda.time.DateMidnight dateMidnight22 = dateMidnight20.minusMonths((int) (byte) 100);
        int int23 = dateMidnight17.compareTo((org.joda.time.ReadableInstant) dateMidnight20);
        org.joda.time.chrono.GJChronology gJChronology24 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTime dateTime25 = dateMidnight20.toDateTime((org.joda.time.Chronology) gJChronology24);
        org.joda.time.Hours hours26 = org.joda.time.Hours.hoursBetween((org.joda.time.ReadableInstant) dateTime12, (org.joda.time.ReadableInstant) dateTime25);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateMidnight2 and dateTime12", (dateMidnight2.compareTo(dateTime12) == 0) == dateMidnight2.equals(dateTime12));
    }

    @Test
    public void test07() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test07");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        int int10 = dateMidnight4.compareTo((org.joda.time.ReadableInstant) dateMidnight7);
        org.joda.time.chrono.GJChronology gJChronology11 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTime dateTime12 = dateMidnight7.toDateTime((org.joda.time.Chronology) gJChronology11);
        org.joda.time.MutableDateTime mutableDateTime13 = new org.joda.time.MutableDateTime((java.lang.Object) dateTime12);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateMidnight2 and dateTime12", (dateMidnight2.compareTo(dateTime12) == 0) == dateMidnight2.equals(dateTime12));
    }

    @Test
    public void test08() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test08");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        int int10 = dateMidnight4.compareTo((org.joda.time.ReadableInstant) dateMidnight7);
        org.joda.time.chrono.GJChronology gJChronology11 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTime dateTime12 = dateMidnight7.toDateTime((org.joda.time.Chronology) gJChronology11);
        org.joda.time.Chronology chronology14 = null;
        org.joda.time.DateMidnight dateMidnight15 = new org.joda.time.DateMidnight((long) (short) -1, chronology14);
        org.joda.time.DateMidnight dateMidnight17 = dateMidnight15.minusMonths((int) (byte) 100);
        org.joda.time.ReadableDateTime readableDateTime18 = null;
        org.joda.time.chrono.LimitChronology limitChronology19 = org.joda.time.chrono.LimitChronology.getInstance((org.joda.time.Chronology) gJChronology11, (org.joda.time.ReadableDateTime) dateMidnight15, readableDateTime18);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateMidnight2 and dateTime12", (dateMidnight2.compareTo(dateTime12) == 0) == dateMidnight2.equals(dateTime12));
    }

    @Test
    public void test09() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test09");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        int int10 = dateMidnight4.compareTo((org.joda.time.ReadableInstant) dateMidnight7);
        org.joda.time.chrono.GJChronology gJChronology11 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTime dateTime12 = dateMidnight7.toDateTime((org.joda.time.Chronology) gJChronology11);
        org.joda.time.DateTime dateTime14 = dateTime12.plusYears((int) (short) 10);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateMidnight2 and dateTime12", (dateMidnight2.compareTo(dateTime12) == 0) == dateMidnight2.equals(dateTime12));
    }

    @Test
    public void test10() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test10");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        int int10 = dateMidnight4.compareTo((org.joda.time.ReadableInstant) dateMidnight7);
        org.joda.time.chrono.GJChronology gJChronology11 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTime dateTime12 = dateMidnight7.toDateTime((org.joda.time.Chronology) gJChronology11);
        org.joda.time.Duration duration14 = org.joda.time.Duration.standardDays((long) (short) 1);
        org.joda.time.DateTime dateTime16 = dateTime12.withDurationAdded((org.joda.time.ReadableDuration) duration14, 0);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateMidnight2 and dateTime16", (dateMidnight2.compareTo(dateTime16) == 0) == dateMidnight2.equals(dateTime16));
    }

    @Test
    public void test11() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test11");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        int int10 = dateMidnight4.compareTo((org.joda.time.ReadableInstant) dateMidnight7);
        org.joda.time.chrono.GJChronology gJChronology11 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTime dateTime12 = dateMidnight7.toDateTime((org.joda.time.Chronology) gJChronology11);
        org.joda.time.DateTime dateTime13 = dateTime12.toDateTime();
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateMidnight2 and dateTime13", (dateMidnight2.compareTo(dateTime13) == 0) == dateMidnight2.equals(dateTime13));
    }

    @Test
    public void test12() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test12");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        int int10 = dateMidnight4.compareTo((org.joda.time.ReadableInstant) dateMidnight7);
        org.joda.time.chrono.GJChronology gJChronology11 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTime dateTime12 = dateMidnight7.toDateTime((org.joda.time.Chronology) gJChronology11);
        org.joda.time.DateTime dateTime14 = dateTime12.plusYears((int) '4');
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateMidnight2 and dateTime12", (dateMidnight2.compareTo(dateTime12) == 0) == dateMidnight2.equals(dateTime12));
    }

    @Test
    public void test13() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test13");
        org.joda.time.Partial partial0 = new org.joda.time.Partial();
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.DateMidnight dateMidnight3 = new org.joda.time.DateMidnight((long) (short) -1, chronology2);
        org.joda.time.DateMidnight dateMidnight5 = dateMidnight3.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology7 = null;
        org.joda.time.DateMidnight dateMidnight8 = new org.joda.time.DateMidnight((long) (short) -1, chronology7);
        org.joda.time.DateMidnight dateMidnight10 = dateMidnight8.minusMonths((int) (byte) 100);
        int int11 = dateMidnight5.compareTo((org.joda.time.ReadableInstant) dateMidnight8);
        org.joda.time.chrono.GJChronology gJChronology12 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTime dateTime13 = dateMidnight8.toDateTime((org.joda.time.Chronology) gJChronology12);
        boolean boolean14 = partial0.isMatch((org.joda.time.ReadableInstant) dateMidnight8);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateMidnight3 and dateTime13", (dateMidnight3.compareTo(dateTime13) == 0) == dateMidnight3.equals(dateTime13));
    }

    @Test
    public void test14() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test14");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        int int10 = dateMidnight4.compareTo((org.joda.time.ReadableInstant) dateMidnight7);
        org.joda.time.chrono.GJChronology gJChronology11 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTime dateTime12 = dateMidnight7.toDateTime((org.joda.time.Chronology) gJChronology11);
        org.joda.time.Period period14 = org.joda.time.Period.weeks((int) (short) 100);
        int[] intArray16 = gJChronology11.get((org.joda.time.ReadablePeriod) period14, (long) 1);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateMidnight2 and dateTime12", (dateMidnight2.compareTo(dateTime12) == 0) == dateMidnight2.equals(dateTime12));
    }

    @Test
    public void test15() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test15");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        mutableDateTime6.setSecondOfMinute(0);
        org.joda.time.Chronology chronology11 = null;
        org.joda.time.DateMidnight dateMidnight12 = new org.joda.time.DateMidnight((long) (short) -1, chronology11);
        org.joda.time.DateMidnight dateMidnight14 = dateMidnight12.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology16 = null;
        org.joda.time.DateMidnight dateMidnight17 = new org.joda.time.DateMidnight((long) (short) -1, chronology16);
        org.joda.time.DateMidnight dateMidnight19 = dateMidnight17.minusMonths((int) (byte) 100);
        int int20 = dateMidnight14.compareTo((org.joda.time.ReadableInstant) dateMidnight17);
        org.joda.time.chrono.GJChronology gJChronology21 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTime dateTime22 = dateMidnight17.toDateTime((org.joda.time.Chronology) gJChronology21);
        org.joda.time.DateTime dateTime23 = new org.joda.time.DateTime((java.lang.Object) mutableDateTime6, (org.joda.time.Chronology) gJChronology21);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateMidnight2 and dateTime22", (dateMidnight2.compareTo(dateTime22) == 0) == dateMidnight2.equals(dateTime22));
    }

    @Test
    public void test16() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test16");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        int int10 = dateMidnight4.compareTo((org.joda.time.ReadableInstant) dateMidnight7);
        org.joda.time.chrono.GJChronology gJChronology11 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTime dateTime12 = dateMidnight7.toDateTime((org.joda.time.Chronology) gJChronology11);
        org.joda.time.YearMonth yearMonth13 = org.joda.time.YearMonth.now((org.joda.time.Chronology) gJChronology11);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateMidnight2 and dateTime12", (dateMidnight2.compareTo(dateTime12) == 0) == dateMidnight2.equals(dateTime12));
    }

    @Test
    public void test17() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test17");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone9 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.DateTime dateTime10 = dateTime4.toDateTime((org.joda.time.DateTimeZone) fixedDateTimeZone9);
        org.joda.time.tz.CachedDateTimeZone cachedDateTimeZone11 = org.joda.time.tz.CachedDateTimeZone.forZone((org.joda.time.DateTimeZone) fixedDateTimeZone9);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateTime4 and dateTime10", (dateTime4.compareTo(dateTime10) == 0) == dateTime4.equals(dateTime10));
    }

    @Test
    public void test18() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test18");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone9 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.DateTime dateTime10 = dateTime4.toDateTime((org.joda.time.DateTimeZone) fixedDateTimeZone9);
        org.joda.time.TimeOfDay timeOfDay11 = new org.joda.time.TimeOfDay((org.joda.time.DateTimeZone) fixedDateTimeZone9);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateTime4 and dateTime10", (dateTime4.compareTo(dateTime10) == 0) == dateTime4.equals(dateTime10));
    }

    @Test
    public void test19() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test19");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone9 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.DateTime dateTime10 = dateTime4.toDateTime((org.joda.time.DateTimeZone) fixedDateTimeZone9);
        org.joda.time.DateTime dateTime12 = dateTime4.withYear(24);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateTime4 and dateTime10", (dateTime4.compareTo(dateTime10) == 0) == dateTime4.equals(dateTime10));
    }

    @Test
    public void test20() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test20");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.Duration duration6 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Chronology chronology8 = null;
        org.joda.time.DateMidnight dateMidnight9 = new org.joda.time.DateMidnight((long) (short) -1, chronology8);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology13 = null;
        org.joda.time.DateMidnight dateMidnight14 = new org.joda.time.DateMidnight((long) (short) -1, chronology13);
        org.joda.time.DateMidnight dateMidnight16 = dateMidnight14.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology18 = null;
        org.joda.time.DateMidnight dateMidnight19 = new org.joda.time.DateMidnight((long) (short) -1, chronology18);
        org.joda.time.DateMidnight dateMidnight21 = dateMidnight19.minusMonths((int) (byte) 100);
        int int22 = dateMidnight16.compareTo((org.joda.time.ReadableInstant) dateMidnight19);
        org.joda.time.Minutes minutes23 = org.joda.time.Minutes.minutesBetween((org.joda.time.ReadableInstant) dateMidnight9, (org.joda.time.ReadableInstant) dateMidnight16);
        org.joda.time.DateMidnight dateMidnight26 = dateMidnight16.withDurationAdded((long) '4', 100);
        org.joda.time.MutableInterval mutableInterval27 = new org.joda.time.MutableInterval((org.joda.time.ReadableDuration) duration6, (org.joda.time.ReadableInstant) dateMidnight26);
        org.joda.time.DateTime dateTime28 = dateTime4.minus((org.joda.time.ReadableDuration) duration6);
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone33 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology34 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone33);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType35 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology37 = null;
        org.joda.time.DateMidnight dateMidnight38 = new org.joda.time.DateMidnight((long) (short) -1, chronology37);
        org.joda.time.DateMidnight dateMidnight40 = dateMidnight38.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property41 = dateMidnight38.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime42 = dateMidnight38.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property43 = mutableDateTime42.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime45 = property43.addWrapField((int) (short) -1);
        boolean boolean46 = leapYearPatternType35.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology47 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone33, leapYearPatternType35);
        org.joda.time.YearMonthDay yearMonthDay48 = new org.joda.time.YearMonthDay((org.joda.time.DateTimeZone) fixedDateTimeZone33);
        org.joda.time.DateTime dateTime49 = dateTime28.toDateTime((org.joda.time.DateTimeZone) fixedDateTimeZone33);
        java.lang.String str51 = fixedDateTimeZone33.getNameKey((long) 3600);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateTime28 and dateTime49", (dateTime28.compareTo(dateTime49) == 0) == dateTime28.equals(dateTime49));
    }

    @Test
    public void test21() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test21");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        int int10 = dateMidnight4.compareTo((org.joda.time.ReadableInstant) dateMidnight7);
        org.joda.time.chrono.GJChronology gJChronology11 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTime dateTime12 = dateMidnight7.toDateTime((org.joda.time.Chronology) gJChronology11);
        org.joda.time.DateMidnight dateMidnight14 = dateMidnight7.plusYears(4);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateMidnight2 and dateTime12", (dateMidnight2.compareTo(dateTime12) == 0) == dateMidnight2.equals(dateTime12));
    }

    @Test
    public void test22() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test22");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.Duration duration6 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Chronology chronology8 = null;
        org.joda.time.DateMidnight dateMidnight9 = new org.joda.time.DateMidnight((long) (short) -1, chronology8);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology13 = null;
        org.joda.time.DateMidnight dateMidnight14 = new org.joda.time.DateMidnight((long) (short) -1, chronology13);
        org.joda.time.DateMidnight dateMidnight16 = dateMidnight14.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology18 = null;
        org.joda.time.DateMidnight dateMidnight19 = new org.joda.time.DateMidnight((long) (short) -1, chronology18);
        org.joda.time.DateMidnight dateMidnight21 = dateMidnight19.minusMonths((int) (byte) 100);
        int int22 = dateMidnight16.compareTo((org.joda.time.ReadableInstant) dateMidnight19);
        org.joda.time.Minutes minutes23 = org.joda.time.Minutes.minutesBetween((org.joda.time.ReadableInstant) dateMidnight9, (org.joda.time.ReadableInstant) dateMidnight16);
        org.joda.time.DateMidnight dateMidnight26 = dateMidnight16.withDurationAdded((long) '4', 100);
        org.joda.time.MutableInterval mutableInterval27 = new org.joda.time.MutableInterval((org.joda.time.ReadableDuration) duration6, (org.joda.time.ReadableInstant) dateMidnight26);
        org.joda.time.DateTime dateTime28 = dateTime4.minus((org.joda.time.ReadableDuration) duration6);
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone33 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology34 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone33);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType35 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology37 = null;
        org.joda.time.DateMidnight dateMidnight38 = new org.joda.time.DateMidnight((long) (short) -1, chronology37);
        org.joda.time.DateMidnight dateMidnight40 = dateMidnight38.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property41 = dateMidnight38.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime42 = dateMidnight38.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property43 = mutableDateTime42.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime45 = property43.addWrapField((int) (short) -1);
        boolean boolean46 = leapYearPatternType35.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology47 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone33, leapYearPatternType35);
        org.joda.time.YearMonthDay yearMonthDay48 = new org.joda.time.YearMonthDay((org.joda.time.DateTimeZone) fixedDateTimeZone33);
        org.joda.time.DateTime dateTime49 = dateTime28.toDateTime((org.joda.time.DateTimeZone) fixedDateTimeZone33);
        org.joda.time.DateTime.Property property50 = dateTime28.minuteOfDay();
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateTime28 and dateTime49", (dateTime28.compareTo(dateTime49) == 0) == dateTime28.equals(dateTime49));
    }

    @Test
    public void test23() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test23");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        int int10 = dateMidnight4.compareTo((org.joda.time.ReadableInstant) dateMidnight7);
        org.joda.time.chrono.GJChronology gJChronology11 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTime dateTime12 = dateMidnight7.toDateTime((org.joda.time.Chronology) gJChronology11);
        java.lang.String str13 = dateTime12.toString();
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateMidnight2 and dateTime12", (dateMidnight2.compareTo(dateTime12) == 0) == dateMidnight2.equals(dateTime12));
    }

    @Test
    public void test24() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test24");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.Duration duration2 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Chronology chronology4 = null;
        org.joda.time.DateMidnight dateMidnight5 = new org.joda.time.DateMidnight((long) (short) -1, chronology4);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight5.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology9 = null;
        org.joda.time.DateMidnight dateMidnight10 = new org.joda.time.DateMidnight((long) (short) -1, chronology9);
        org.joda.time.DateMidnight dateMidnight12 = dateMidnight10.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology14 = null;
        org.joda.time.DateMidnight dateMidnight15 = new org.joda.time.DateMidnight((long) (short) -1, chronology14);
        org.joda.time.DateMidnight dateMidnight17 = dateMidnight15.minusMonths((int) (byte) 100);
        int int18 = dateMidnight12.compareTo((org.joda.time.ReadableInstant) dateMidnight15);
        org.joda.time.Minutes minutes19 = org.joda.time.Minutes.minutesBetween((org.joda.time.ReadableInstant) dateMidnight5, (org.joda.time.ReadableInstant) dateMidnight12);
        org.joda.time.DateMidnight dateMidnight22 = dateMidnight12.withDurationAdded((long) '4', 100);
        org.joda.time.MutableInterval mutableInterval23 = new org.joda.time.MutableInterval((org.joda.time.ReadableDuration) duration2, (org.joda.time.ReadableInstant) dateMidnight22);
        org.joda.time.chrono.GJChronology gJChronology24 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField25 = gJChronology24.dayOfYear();
        org.joda.time.DurationField durationField26 = gJChronology24.years();
        org.joda.time.Period period27 = duration2.toPeriod((org.joda.time.Chronology) gJChronology24);
        org.joda.time.YearMonth yearMonth28 = yearMonth0.withChronologyRetainFields((org.joda.time.Chronology) gJChronology24);
        int int29 = yearMonth0.size();
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on yearMonth0 and yearMonth28", (yearMonth0.compareTo(yearMonth28) == 0) == yearMonth0.equals(yearMonth28));
    }

    @Test
    public void test25() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test25");
        org.joda.time.Duration duration1 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.DateTime dateTime2 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone3 = null;
        org.joda.time.DateTime dateTime4 = dateTime2.withZoneRetainFields(dateTimeZone3);
        org.joda.time.DateTime dateTime6 = dateTime4.plusHours(0);
        org.joda.time.DateTimeZone dateTimeZone7 = null;
        org.joda.time.LocalDateTime localDateTime8 = new org.joda.time.LocalDateTime(dateTimeZone7);
        org.joda.time.LocalDateTime localDateTime10 = localDateTime8.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property11 = localDateTime8.weekOfWeekyear();
        org.joda.time.DateTime dateTime12 = dateTime6.withFields((org.joda.time.ReadablePartial) localDateTime8);
        org.joda.time.MutablePeriod mutablePeriod13 = new org.joda.time.MutablePeriod((org.joda.time.ReadableDuration) duration1, (org.joda.time.ReadableInstant) dateTime6);
        org.joda.time.Chronology chronology15 = null;
        org.joda.time.DateMidnight dateMidnight16 = new org.joda.time.DateMidnight((long) (short) -1, chronology15);
        org.joda.time.chrono.JulianChronology julianChronology17 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField18 = julianChronology17.weeks();
        org.joda.time.Chronology chronology19 = julianChronology17.withUTC();
        org.joda.time.DateTime dateTime20 = dateMidnight16.toDateTime((org.joda.time.Chronology) julianChronology17);
        org.joda.time.Interval interval21 = new org.joda.time.Interval((org.joda.time.ReadablePeriod) mutablePeriod13, (org.joda.time.ReadableInstant) dateTime20);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateMidnight16 and dateTime20", (dateMidnight16.compareTo(dateTime20) == 0) == dateMidnight16.equals(dateTime20));
    }

    @Test
    public void test26() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test26");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.Duration duration2 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Chronology chronology4 = null;
        org.joda.time.DateMidnight dateMidnight5 = new org.joda.time.DateMidnight((long) (short) -1, chronology4);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight5.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology9 = null;
        org.joda.time.DateMidnight dateMidnight10 = new org.joda.time.DateMidnight((long) (short) -1, chronology9);
        org.joda.time.DateMidnight dateMidnight12 = dateMidnight10.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology14 = null;
        org.joda.time.DateMidnight dateMidnight15 = new org.joda.time.DateMidnight((long) (short) -1, chronology14);
        org.joda.time.DateMidnight dateMidnight17 = dateMidnight15.minusMonths((int) (byte) 100);
        int int18 = dateMidnight12.compareTo((org.joda.time.ReadableInstant) dateMidnight15);
        org.joda.time.Minutes minutes19 = org.joda.time.Minutes.minutesBetween((org.joda.time.ReadableInstant) dateMidnight5, (org.joda.time.ReadableInstant) dateMidnight12);
        org.joda.time.DateMidnight dateMidnight22 = dateMidnight12.withDurationAdded((long) '4', 100);
        org.joda.time.MutableInterval mutableInterval23 = new org.joda.time.MutableInterval((org.joda.time.ReadableDuration) duration2, (org.joda.time.ReadableInstant) dateMidnight22);
        org.joda.time.chrono.GJChronology gJChronology24 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField25 = gJChronology24.dayOfYear();
        org.joda.time.DurationField durationField26 = gJChronology24.years();
        org.joda.time.Period period27 = duration2.toPeriod((org.joda.time.Chronology) gJChronology24);
        org.joda.time.YearMonth yearMonth28 = yearMonth0.withChronologyRetainFields((org.joda.time.Chronology) gJChronology24);
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone33 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.ISOChronology iSOChronology34 = org.joda.time.chrono.ISOChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone33);
        org.joda.time.YearMonth yearMonth35 = yearMonth0.withChronologyRetainFields((org.joda.time.Chronology) iSOChronology34);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on yearMonth35 and yearMonth28", (yearMonth35.compareTo(yearMonth28) == 0) == yearMonth35.equals(yearMonth28));
    }

    @Test
    public void test27() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test27");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone9 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.DateTime dateTime10 = dateTime4.toDateTime((org.joda.time.DateTimeZone) fixedDateTimeZone9);
        org.joda.time.DateTime dateTime12 = dateTime10.minusDays(3600);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateTime4 and dateTime10", (dateTime4.compareTo(dateTime10) == 0) == dateTime4.equals(dateTime10));
    }

    @Test
    public void test28() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test28");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        mutableDateTime6.setSecondOfMinute(0);
        mutableDateTime6.add((long) 86400000);
        int int12 = mutableDateTime6.getMinuteOfHour();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone18 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology19 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone18);
        org.joda.time.LocalDateTime localDateTime20 = new org.joda.time.LocalDateTime((long) (-1), (org.joda.time.DateTimeZone) fixedDateTimeZone18);
        org.joda.time.tz.CachedDateTimeZone cachedDateTimeZone21 = org.joda.time.tz.CachedDateTimeZone.forZone((org.joda.time.DateTimeZone) fixedDateTimeZone18);
        org.joda.time.chrono.ISOChronology iSOChronology22 = org.joda.time.chrono.ISOChronology.getInstance((org.joda.time.DateTimeZone) cachedDateTimeZone21);
        org.joda.time.Chronology chronology23 = iSOChronology22.withUTC();
        org.joda.time.MutableDateTime mutableDateTime24 = mutableDateTime6.toMutableDateTime((org.joda.time.Chronology) iSOChronology22);
        org.joda.time.MutableDateTime.Property property25 = mutableDateTime24.monthOfYear();
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on mutableDateTime6 and mutableDateTime24", (mutableDateTime6.compareTo(mutableDateTime24) == 0) == mutableDateTime6.equals(mutableDateTime24));
    }

    @Test
    public void test29() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test29");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray3 = monthDay2.getFieldTypes();
        org.joda.time.chrono.GJChronology gJChronology4 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology5 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology4);
        org.joda.time.chrono.LenientChronology lenientChronology6 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology4);
        org.joda.time.MonthDay monthDay7 = new org.joda.time.MonthDay((org.joda.time.Chronology) lenientChronology6);
        org.joda.time.MonthDay monthDay8 = monthDay2.withChronologyRetainFields((org.joda.time.Chronology) lenientChronology6);
        org.joda.time.Chronology chronology9 = lenientChronology6.withUTC();
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on monthDay2 and monthDay8", (monthDay2.compareTo(monthDay8) == 0) == monthDay2.equals(monthDay8));
    }

    @Test
    public void test30() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test30");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.DateTime dateTime6 = dateTime0.withWeekyear((int) (byte) 0);
        org.joda.time.DateTime dateTime8 = dateTime6.plusSeconds((int) (byte) -1);
        org.joda.time.DateTime.Property property9 = dateTime8.year();
        org.joda.time.DateTime dateTime10 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone11 = null;
        org.joda.time.DateTime dateTime12 = dateTime10.withZoneRetainFields(dateTimeZone11);
        org.joda.time.DateTime dateTime14 = dateTime10.withYear((int) (byte) 0);
        org.joda.time.DateTime dateTime16 = dateTime10.withWeekyear((int) (byte) 0);
        org.joda.time.DateTime dateTime18 = dateTime16.plusSeconds((int) (byte) -1);
        org.joda.time.DateTime.Property property19 = dateTime18.year();
        int int20 = property9.compareTo((org.joda.time.ReadableInstant) dateTime18);
        org.joda.time.DateTime dateTime21 = dateTime18.withTimeAtStartOfDay();
        org.joda.time.chrono.GJChronology gJChronology23 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay24 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology23);
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray25 = monthDay24.getFieldTypes();
        org.joda.time.chrono.GJChronology gJChronology26 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology27 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology26);
        org.joda.time.chrono.LenientChronology lenientChronology28 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology26);
        org.joda.time.MonthDay monthDay29 = new org.joda.time.MonthDay((org.joda.time.Chronology) lenientChronology28);
        org.joda.time.MonthDay monthDay30 = monthDay24.withChronologyRetainFields((org.joda.time.Chronology) lenientChronology28);
        org.joda.time.DateTime dateTime31 = dateTime21.withChronology((org.joda.time.Chronology) lenientChronology28);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on monthDay24 and monthDay30", (monthDay24.compareTo(monthDay30) == 0) == monthDay24.equals(monthDay30));
    }

    @Test
    public void test31() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test31");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone9 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.DateTime dateTime10 = dateTime4.toDateTime((org.joda.time.DateTimeZone) fixedDateTimeZone9);
        org.joda.time.DateTimeZone dateTimeZone11 = org.joda.time.DateTimeUtils.getZone((org.joda.time.DateTimeZone) fixedDateTimeZone9);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateTime4 and dateTime10", (dateTime4.compareTo(dateTime10) == 0) == dateTime4.equals(dateTime10));
    }

    @Test
    public void test32() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test32");
        org.joda.time.Chronology chronology3 = null;
        org.joda.time.DateMidnight dateMidnight4 = new org.joda.time.DateMidnight((long) (short) -1, chronology3);
        org.joda.time.chrono.JulianChronology julianChronology5 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField6 = julianChronology5.weeks();
        org.joda.time.Chronology chronology7 = julianChronology5.withUTC();
        org.joda.time.DateTime dateTime8 = dateMidnight4.toDateTime((org.joda.time.Chronology) julianChronology5);
        org.joda.time.Period period9 = new org.joda.time.Period((long) 36, (-1640995200001L), (org.joda.time.Chronology) julianChronology5);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateMidnight4 and dateTime8", (dateMidnight4.compareTo(dateTime8) == 0) == dateMidnight4.equals(dateTime8));
    }

    @Test
    public void test33() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test33");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray3 = monthDay2.getFieldTypes();
        org.joda.time.chrono.GJChronology gJChronology4 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology5 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology4);
        org.joda.time.chrono.LenientChronology lenientChronology6 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology4);
        org.joda.time.MonthDay monthDay7 = new org.joda.time.MonthDay((org.joda.time.Chronology) lenientChronology6);
        org.joda.time.MonthDay monthDay8 = monthDay2.withChronologyRetainFields((org.joda.time.Chronology) lenientChronology6);
        java.lang.String str9 = lenientChronology6.toString();
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on monthDay2 and monthDay8", (monthDay2.compareTo(monthDay8) == 0) == monthDay2.equals(monthDay8));
    }

    @Test
    public void test34() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test34");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray3 = monthDay2.getFieldTypes();
        org.joda.time.chrono.GJChronology gJChronology4 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology5 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology4);
        org.joda.time.chrono.LenientChronology lenientChronology6 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology4);
        org.joda.time.MonthDay monthDay7 = new org.joda.time.MonthDay((org.joda.time.Chronology) lenientChronology6);
        org.joda.time.MonthDay monthDay8 = monthDay2.withChronologyRetainFields((org.joda.time.Chronology) lenientChronology6);
        boolean boolean10 = lenientChronology6.equals((java.lang.Object) "PT60S");
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on monthDay2 and monthDay8", (monthDay2.compareTo(monthDay8) == 0) == monthDay2.equals(monthDay8));
    }

    @Test
    public void test35() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test35");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime9 = property7.addWrapField((int) (short) -1);
        org.joda.time.MutableDateTime.Property property10 = mutableDateTime9.monthOfYear();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone15 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology16 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone15);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType17 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology19 = null;
        org.joda.time.DateMidnight dateMidnight20 = new org.joda.time.DateMidnight((long) (short) -1, chronology19);
        org.joda.time.DateMidnight dateMidnight22 = dateMidnight20.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property23 = dateMidnight20.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime24 = dateMidnight20.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property25 = mutableDateTime24.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime27 = property25.addWrapField((int) (short) -1);
        boolean boolean28 = leapYearPatternType17.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology29 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone15, leapYearPatternType17);
        org.joda.time.MonthDay monthDay30 = new org.joda.time.MonthDay((org.joda.time.DateTimeZone) fixedDateTimeZone15);
        org.joda.time.DateMidnight dateMidnight31 = new org.joda.time.DateMidnight((org.joda.time.DateTimeZone) fixedDateTimeZone15);
        org.joda.time.Instant instant32 = org.joda.time.Instant.EPOCH;
        org.joda.time.Instant instant35 = instant32.withDurationAdded((-1L), 100);
        org.joda.time.Chronology chronology37 = null;
        org.joda.time.DateMidnight dateMidnight38 = new org.joda.time.DateMidnight((long) (short) -1, chronology37);
        org.joda.time.DateMidnight dateMidnight40 = dateMidnight38.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property41 = dateMidnight38.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime42 = dateMidnight38.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property43 = mutableDateTime42.centuryOfEra();
        mutableDateTime42.setSecondOfMinute(24);
        org.joda.time.Months months46 = org.joda.time.Months.monthsBetween((org.joda.time.ReadableInstant) instant32, (org.joda.time.ReadableInstant) mutableDateTime42);
        org.joda.time.Chronology chronology48 = null;
        org.joda.time.DateMidnight dateMidnight49 = new org.joda.time.DateMidnight((long) (short) -1, chronology48);
        org.joda.time.DateMidnight dateMidnight51 = dateMidnight49.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight54 = dateMidnight49.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight56 = dateMidnight54.withYear(100);
        org.joda.time.PeriodType periodType58 = null;
        org.joda.time.Chronology chronology59 = null;
        org.joda.time.Period period60 = new org.joda.time.Period((long) (short) 10, periodType58, chronology59);
        org.joda.time.Months months61 = org.joda.time.Months.FIVE;
        org.joda.time.Period period62 = period60.minus((org.joda.time.ReadablePeriod) months61);
        org.joda.time.Chronology chronology64 = null;
        org.joda.time.DateMidnight dateMidnight65 = new org.joda.time.DateMidnight((long) (short) -1, chronology64);
        org.joda.time.DateMidnight dateMidnight67 = dateMidnight65.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology69 = null;
        org.joda.time.DateMidnight dateMidnight70 = new org.joda.time.DateMidnight((long) (short) -1, chronology69);
        org.joda.time.DateMidnight dateMidnight72 = dateMidnight70.minusMonths((int) (byte) 100);
        int int73 = dateMidnight67.compareTo((org.joda.time.ReadableInstant) dateMidnight70);
        org.joda.time.Duration duration74 = period62.toDurationTo((org.joda.time.ReadableInstant) dateMidnight70);
        org.joda.time.Duration duration75 = duration74.negated();
        org.joda.time.PeriodType periodType76 = org.joda.time.PeriodType.yearDay();
        org.joda.time.PeriodType periodType77 = periodType76.withSecondsRemoved();
        org.joda.time.MutablePeriod mutablePeriod78 = new org.joda.time.MutablePeriod((org.joda.time.ReadableInstant) dateMidnight54, (org.joda.time.ReadableDuration) duration74, periodType77);
        org.joda.time.Period period79 = new org.joda.time.Period((org.joda.time.ReadableInstant) instant32, (org.joda.time.ReadableDuration) duration74);
        org.joda.time.DateMidnight dateMidnight81 = dateMidnight31.withDurationAdded((org.joda.time.ReadableDuration) duration74, (int) '4');
        mutableDateTime9.add((org.joda.time.ReadableDuration) duration74);
        org.joda.time.MutableDateTime.Property property83 = mutableDateTime9.secondOfDay();
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on dateMidnight2 and instant35", (dateMidnight2.compareTo(instant35) == 0) == dateMidnight2.equals(instant35));
    }

    @Test
    public void test36() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test36");
        org.joda.time.Instant instant0 = org.joda.time.Instant.EPOCH;
        org.joda.time.Instant instant3 = instant0.withDurationAdded((-1L), 100);
        org.joda.time.Chronology chronology5 = null;
        org.joda.time.DateMidnight dateMidnight6 = new org.joda.time.DateMidnight((long) (short) -1, chronology5);
        org.joda.time.DateMidnight dateMidnight8 = dateMidnight6.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property9 = dateMidnight6.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime10 = dateMidnight6.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property11 = mutableDateTime10.centuryOfEra();
        mutableDateTime10.setSecondOfMinute(24);
        org.joda.time.Months months14 = org.joda.time.Months.monthsBetween((org.joda.time.ReadableInstant) instant0, (org.joda.time.ReadableInstant) mutableDateTime10);
        org.joda.time.Chronology chronology16 = null;
        org.joda.time.DateMidnight dateMidnight17 = new org.joda.time.DateMidnight((long) (short) -1, chronology16);
        org.joda.time.DateMidnight dateMidnight19 = dateMidnight17.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight22 = dateMidnight17.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight24 = dateMidnight22.withYear(100);
        org.joda.time.PeriodType periodType26 = null;
        org.joda.time.Chronology chronology27 = null;
        org.joda.time.Period period28 = new org.joda.time.Period((long) (short) 10, periodType26, chronology27);
        org.joda.time.Months months29 = org.joda.time.Months.FIVE;
        org.joda.time.Period period30 = period28.minus((org.joda.time.ReadablePeriod) months29);
        org.joda.time.Chronology chronology32 = null;
        org.joda.time.DateMidnight dateMidnight33 = new org.joda.time.DateMidnight((long) (short) -1, chronology32);
        org.joda.time.DateMidnight dateMidnight35 = dateMidnight33.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology37 = null;
        org.joda.time.DateMidnight dateMidnight38 = new org.joda.time.DateMidnight((long) (short) -1, chronology37);
        org.joda.time.DateMidnight dateMidnight40 = dateMidnight38.minusMonths((int) (byte) 100);
        int int41 = dateMidnight35.compareTo((org.joda.time.ReadableInstant) dateMidnight38);
        org.joda.time.Duration duration42 = period30.toDurationTo((org.joda.time.ReadableInstant) dateMidnight38);
        org.joda.time.Duration duration43 = duration42.negated();
        org.joda.time.PeriodType periodType44 = org.joda.time.PeriodType.yearDay();
        org.joda.time.PeriodType periodType45 = periodType44.withSecondsRemoved();
        org.joda.time.MutablePeriod mutablePeriod46 = new org.joda.time.MutablePeriod((org.joda.time.ReadableInstant) dateMidnight22, (org.joda.time.ReadableDuration) duration42, periodType45);
        org.joda.time.Period period47 = new org.joda.time.Period((org.joda.time.ReadableInstant) instant0, (org.joda.time.ReadableDuration) duration42);
        org.joda.time.Instant instant48 = org.joda.time.Instant.EPOCH;
        org.joda.time.Instant instant51 = instant48.withDurationAdded((-1L), 100);
        org.joda.time.Duration duration53 = org.joda.time.Duration.standardDays((long) (short) 1);
        org.joda.time.PeriodType periodType55 = null;
        org.joda.time.Chronology chronology56 = null;
        org.joda.time.Period period57 = new org.joda.time.Period((long) (short) 10, periodType55, chronology56);
        org.joda.time.Months months58 = org.joda.time.Months.FIVE;
        org.joda.time.Period period59 = period57.minus((org.joda.time.ReadablePeriod) months58);
        org.joda.time.Chronology chronology61 = null;
        org.joda.time.DateMidnight dateMidnight62 = new org.joda.time.DateMidnight((long) (short) -1, chronology61);
        org.joda.time.DateMidnight dateMidnight64 = dateMidnight62.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology66 = null;
        org.joda.time.DateMidnight dateMidnight67 = new org.joda.time.DateMidnight((long) (short) -1, chronology66);
        org.joda.time.DateMidnight dateMidnight69 = dateMidnight67.minusMonths((int) (byte) 100);
        int int70 = dateMidnight64.compareTo((org.joda.time.ReadableInstant) dateMidnight67);
        org.joda.time.Duration duration71 = period59.toDurationTo((org.joda.time.ReadableInstant) dateMidnight67);
        org.joda.time.Duration duration72 = duration71.negated();
        boolean boolean73 = duration53.isShorterThan((org.joda.time.ReadableDuration) duration71);
        org.joda.time.Instant instant74 = instant48.plus((org.joda.time.ReadableDuration) duration53);
        org.joda.time.Instant instant75 = instant0.minus((org.joda.time.ReadableDuration) duration53);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on instant3 and dateMidnight6", (instant3.compareTo(dateMidnight6) == 0) == instant3.equals(dateMidnight6));
    }

    @Test
    public void test37() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test37");
        org.joda.time.convert.ConverterManager converterManager0 = org.joda.time.convert.ConverterManager.getInstance();
        org.joda.time.convert.PartialConverter partialConverter1 = null;
        org.joda.time.convert.PartialConverter partialConverter2 = converterManager0.addPartialConverter(partialConverter1);
        org.joda.time.convert.PartialConverter partialConverter3 = null;
        org.joda.time.convert.PartialConverter partialConverter4 = converterManager0.addPartialConverter(partialConverter3);
        org.joda.time.Instant instant5 = org.joda.time.Instant.EPOCH;
        org.joda.time.Instant instant8 = instant5.withDurationAdded((-1L), 100);
        org.joda.time.convert.DurationConverter durationConverter9 = converterManager0.getDurationConverter((java.lang.Object) (-1L));
        org.joda.time.chrono.JulianChronology julianChronology11 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate12 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology11);
        org.joda.time.LocalDate.Property property13 = localDate12.yearOfCentury();
        org.joda.time.LocalDate localDate15 = localDate12.withWeekyear((int) (byte) 100);
        org.joda.time.Chronology chronology17 = null;
        org.joda.time.DateMidnight dateMidnight18 = new org.joda.time.DateMidnight((long) (short) -1, chronology17);
        org.joda.time.DateMidnight dateMidnight20 = dateMidnight18.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property21 = dateMidnight18.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime22 = dateMidnight18.toMutableDateTimeISO();
        boolean boolean23 = localDate15.equals((java.lang.Object) mutableDateTime22);
        org.joda.time.LocalDate localDate25 = localDate15.plusMonths(10);
        org.joda.time.convert.PartialConverter partialConverter26 = converterManager0.getPartialConverter((java.lang.Object) localDate25);
        org.joda.time.convert.PeriodConverter periodConverter27 = null;
        org.joda.time.convert.PeriodConverter periodConverter28 = converterManager0.addPeriodConverter(periodConverter27);
        org.joda.time.convert.InstantConverter[] instantConverterArray29 = converterManager0.getInstantConverters();
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on instant8 and dateMidnight18", (instant8.compareTo(dateMidnight18) == 0) == instant8.equals(dateMidnight18));
    }

    @Test
    public void test38() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test38");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray3 = monthDay2.getFieldTypes();
        org.joda.time.chrono.GJChronology gJChronology4 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology5 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology4);
        org.joda.time.chrono.LenientChronology lenientChronology6 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology4);
        org.joda.time.MonthDay monthDay7 = new org.joda.time.MonthDay((org.joda.time.Chronology) lenientChronology6);
        org.joda.time.MonthDay monthDay8 = monthDay2.withChronologyRetainFields((org.joda.time.Chronology) lenientChronology6);
        org.joda.time.Chronology chronology10 = null;
        org.joda.time.DateMidnight dateMidnight11 = new org.joda.time.DateMidnight((long) (short) -1, chronology10);
        org.joda.time.MutableDateTime mutableDateTime12 = dateMidnight11.toMutableDateTime();
        mutableDateTime12.addWeeks(5);
        org.joda.time.Chronology chronology15 = mutableDateTime12.getChronology();
        boolean boolean16 = lenientChronology6.equals((java.lang.Object) chronology15);
        org.junit.Assert.assertTrue("Contract failed: compareTo-equals on monthDay2 and monthDay8", (monthDay2.compareTo(monthDay8) == 0) == monthDay2.equals(monthDay8));
    }
}

