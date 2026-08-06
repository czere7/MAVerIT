import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0 {

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
    public void test0001() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0001");
        org.joda.time.DateTimeField dateTimeField0 = null;
        org.joda.time.DateTimeFieldType dateTimeFieldType1 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.field.DividedDateTimeField dividedDateTimeField3 = new org.joda.time.field.DividedDateTimeField(dateTimeField0, dateTimeFieldType1, (int) 'a');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0002() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0002");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Interval interval1 = org.joda.time.Interval.parseWithOffset("");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Format requires a '/' separator: ");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0003() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0003");
        java.lang.Appendable appendable0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.format.FormatUtils.appendPaddedInteger(appendable0, (int) (byte) 0, (int) (short) 1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0004() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0004");
        org.joda.time.Period period8 = new org.joda.time.Period((int) (byte) 100, 10, 0, (int) (short) 1, 0, (int) (byte) 10, (int) (byte) -1, (int) (byte) 100);
    }

    @Test
    public void test0005() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0005");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MutableInterval mutableInterval1 = org.joda.time.MutableInterval.parse("");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Format requires a '/' separator: ");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0006() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0006");
        int int0 = org.joda.time.DateTimeConstants.AM;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 0 + "'", int0 == 0);
    }

    @Test
    public void test0007() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0007");
        int int0 = org.joda.time.DateTimeConstants.MONDAY;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 1 + "'", int0 == 1);
    }

    @Test
    public void test0008() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0008");
        java.util.Date date0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalTime localTime1 = org.joda.time.LocalTime.fromDateFields(date0);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The date must not be null");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0009() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0009");
        java.io.Writer writer0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.format.FormatUtils.writeUnpaddedInteger(writer0, (int) (byte) -1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0010() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0010");
        int int0 = org.joda.time.DateTimeConstants.MILLIS_PER_DAY;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 86400000 + "'", int0 == 86400000);
    }

    @Test
    public void test0011() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0011");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((int) '#', 0);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 35 for monthOfYear must not be larger than 12");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0012() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0012");
        int int0 = org.joda.time.DateTimeConstants.HOURS_PER_DAY;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 24 + "'", int0 == 24);
    }

    @Test
    public void test0013() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0013");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateMidnight dateMidnight3 = new org.joda.time.DateMidnight(100, 0, 2147483647);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 0 for monthOfYear must be in the range [1,12]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0014() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0014");
        org.joda.time.tz.ZoneInfoCompiler zoneInfoCompiler0 = new org.joda.time.tz.ZoneInfoCompiler();
    }

    @Test
    public void test0015() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0015");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        int int10 = dateMidnight4.compareTo((org.joda.time.ReadableInstant) dateMidnight7);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateMidnight dateMidnight12 = dateMidnight4.withDayOfYear(0);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 0 for dayOfYear must be in the range [1,365]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + (-1) + "'", int10 == (-1));
    }

    @Test
    public void test0016() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0016");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.DateTimeFormat.longDate();
        java.lang.StringBuilder stringBuilder1 = null;
        org.joda.time.Chronology chronology3 = null;
        org.joda.time.DateMidnight dateMidnight4 = new org.joda.time.DateMidnight((long) (short) -1, chronology3);
        org.joda.time.DateMidnight dateMidnight6 = dateMidnight4.minusMonths((int) (byte) 100);
        // The following exception was thrown during execution in test generation
        try {
            dateTimeFormatter0.printTo(stringBuilder1, (org.joda.time.ReadableInstant) dateMidnight4);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
        org.junit.Assert.assertNotNull(dateMidnight6);
    }

    @Test
    public void test0017() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0017");
        org.joda.time.Instant instant1 = org.joda.time.Instant.ofEpochSecond((long) (short) -1);
        org.junit.Assert.assertNotNull(instant1);
    }

    @Test
    public void test0018() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0018");
        java.util.Calendar calendar0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.TimeOfDay timeOfDay1 = org.joda.time.TimeOfDay.fromCalendarFields(calendar0);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The calendar must not be null");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0019() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0019");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        int int2 = localDateTime1.getYear();
        org.joda.time.chrono.GJChronology gJChronology4 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay5 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology4);
        org.joda.time.MonthDay monthDay7 = monthDay5.minusDays((int) '4');
        org.joda.time.MonthDay.Property property8 = monthDay5.monthOfYear();
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean9 = localDateTime1.isEqual((org.joda.time.ReadablePartial) monthDay5);
            org.junit.Assert.fail("Expected exception of type java.lang.ClassCastException; message: ReadablePartial objects must have matching field types");
        } catch (java.lang.ClassCastException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 2026 + "'", int2 == 2026);
        org.junit.Assert.assertNotNull(gJChronology4);
        org.junit.Assert.assertNotNull(monthDay7);
        org.junit.Assert.assertNotNull(property8);
    }

    @Test
    public void test0020() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0020");
        int int0 = org.joda.time.MutableDateTime.ROUND_HALF_FLOOR;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 3 + "'", int0 == 3);
    }

    @Test
    public void test0021() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0021");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.dateElementParser();
        java.lang.StringBuilder stringBuilder1 = null;
        // The following exception was thrown during execution in test generation
        try {
            dateTimeFormatter0.printTo(stringBuilder1, (long) 1);
            org.junit.Assert.fail("Expected exception of type java.lang.UnsupportedOperationException; message: Printing not supported");
        } catch (java.lang.UnsupportedOperationException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
    }

    @Test
    public void test0022() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0022");
        java.lang.Appendable appendable0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.format.FormatUtils.appendPaddedInteger(appendable0, (int) (byte) 10, (int) (byte) -1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0023() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0023");
        java.io.Writer writer0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.format.FormatUtils.writePaddedInteger(writer0, 100L, (int) '#');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0024() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0024");
        int int0 = org.joda.time.DateTimeConstants.PM;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 1 + "'", int0 == 1);
    }

    @Test
    public void test0025() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0025");
        org.joda.time.DateTimeField dateTimeField0 = null;
        org.joda.time.DurationField durationField1 = null;
        org.joda.time.DateTimeFieldType dateTimeFieldType2 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.field.DividedDateTimeField dividedDateTimeField4 = new org.joda.time.field.DividedDateTimeField(dateTimeField0, durationField1, dateTimeFieldType2, (int) (short) 0);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The type must not be null");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0026() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0026");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        int int10 = dateMidnight4.compareTo((org.joda.time.ReadableInstant) dateMidnight7);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateMidnight dateMidnight12 = dateMidnight7.withDayOfMonth((int) (short) 0);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 0 for dayOfMonth must be in the range [1,31]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + (-1) + "'", int10 == (-1));
    }

    @Test
    public void test0027() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0027");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateTimeFieldType dateTimeFieldType3 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateMidnight dateMidnight5 = dateMidnight2.withField(dateTimeFieldType3, 2026);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Field must not be null");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0028() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0028");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.dateElementParser();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str2 = dateTimeFormatter0.print((long) (short) 100);
            org.junit.Assert.fail("Expected exception of type java.lang.UnsupportedOperationException; message: Printing not supported");
        } catch (java.lang.UnsupportedOperationException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
    }

    @Test
    public void test0029() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0029");
        org.joda.time.PeriodType periodType0 = org.joda.time.PeriodType.yearMonthDay();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DurationFieldType durationFieldType2 = periodType0.getFieldType((-1));
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: -1");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(periodType0);
    }

    @Test
    public void test0030() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0030");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.DurationFieldType durationFieldType2 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDateTime localDateTime4 = localDateTime1.withFieldAdded(durationFieldType2, (int) (short) -1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Field must not be null");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0031() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0031");
        org.joda.time.Partial partial0 = new org.joda.time.Partial();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.LocalDateTime localDateTime2 = new org.joda.time.LocalDateTime(dateTimeZone1);
        org.joda.time.LocalDateTime localDateTime4 = localDateTime2.withDayOfYear((int) ' ');
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean5 = partial0.isBefore((org.joda.time.ReadablePartial) localDateTime4);
            org.junit.Assert.fail("Expected exception of type java.lang.ClassCastException; message: ReadablePartial objects must have matching field types");
        } catch (java.lang.ClassCastException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(localDateTime4);
    }

    @Test
    public void test0032() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0032");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear(2026);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 2026 for dayOfYear must be in the range [1,365]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0033() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0033");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTimeZone.setDefault(dateTimeZone0);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The datetime zone must not be null");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0034() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0034");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        int int10 = dateMidnight4.compareTo((org.joda.time.ReadableInstant) dateMidnight7);
        org.joda.time.chrono.GJChronology gJChronology11 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTime dateTime12 = dateMidnight7.toDateTime((org.joda.time.Chronology) gJChronology11);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTime dateTime14 = dateTime12.withYearOfCentury(0);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 0 for yearOfCentury must be in the range [1,100]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + (-1) + "'", int10 == (-1));
        org.junit.Assert.assertNotNull(gJChronology11);
        org.junit.Assert.assertNotNull(dateTime12);
    }

    @Test
    public void test0035() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0035");
        java.util.Date date0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDateTime localDateTime1 = org.joda.time.LocalDateTime.fromDateFields(date0);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The date must not be null");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0036() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0036");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.Months months4 = org.joda.time.Months.FIVE;
        org.joda.time.Period period5 = period3.minus((org.joda.time.ReadablePeriod) months4);
        org.joda.time.Chronology chronology7 = null;
        org.joda.time.DateMidnight dateMidnight8 = new org.joda.time.DateMidnight((long) (short) -1, chronology7);
        org.joda.time.DateMidnight dateMidnight10 = dateMidnight8.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology12 = null;
        org.joda.time.DateMidnight dateMidnight13 = new org.joda.time.DateMidnight((long) (short) -1, chronology12);
        org.joda.time.DateMidnight dateMidnight15 = dateMidnight13.minusMonths((int) (byte) 100);
        int int16 = dateMidnight10.compareTo((org.joda.time.ReadableInstant) dateMidnight13);
        org.joda.time.Duration duration17 = period5.toDurationTo((org.joda.time.ReadableInstant) dateMidnight13);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.YearMonth yearMonth18 = new org.joda.time.YearMonth((java.lang.Object) duration17);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No partial converter found for type: org.joda.time.Duration");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(months4);
        org.junit.Assert.assertNotNull(period5);
        org.junit.Assert.assertNotNull(dateMidnight10);
        org.junit.Assert.assertNotNull(dateMidnight15);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + (-1) + "'", int16 == (-1));
        org.junit.Assert.assertNotNull(duration17);
    }

    @Test
    public void test0037() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0037");
        org.joda.time.Months months0 = org.joda.time.Months.MAX_VALUE;
        org.junit.Assert.assertNotNull(months0);
    }

    @Test
    public void test0038() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0038");
        org.joda.time.field.DividedDateTimeField dividedDateTimeField0 = null;
        org.joda.time.DurationField durationField1 = null;
        org.joda.time.DateTimeFieldType dateTimeFieldType2 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.field.RemainderDateTimeField remainderDateTimeField3 = new org.joda.time.field.RemainderDateTimeField(dividedDateTimeField0, durationField1, dateTimeFieldType2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0039() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0039");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.MonthDay.Property property3 = monthDay2.dayOfMonth();
        int int4 = monthDay2.size();
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 2 + "'", int4 == 2);
    }

    @Test
    public void test0040() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0040");
        int int0 = org.joda.time.YearMonthDay.MONTH_OF_YEAR;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 1 + "'", int0 == 1);
    }

    @Test
    public void test0041() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0041");
        org.joda.time.tz.ZoneInfoLogger zoneInfoLogger0 = new org.joda.time.tz.ZoneInfoLogger();
    }

    @Test
    public void test0042() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0042");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.DateMidnight dateMidnight1 = new org.joda.time.DateMidnight(dateTimeZone0);
    }

    @Test
    public void test0043() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0043");
        long long2 = org.joda.time.field.FieldUtils.safeMultiply((long) ' ', 0L);
        org.junit.Assert.assertTrue("'" + long2 + "' != '" + 0L + "'", long2 == 0L);
    }

    @Test
    public void test0044() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0044");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((java.lang.Object) (-1.0d), (org.joda.time.Chronology) gJChronology1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No partial converter found for type: java.lang.Double");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gJChronology1);
    }

    @Test
    public void test0045() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0045");
        org.joda.time.chrono.GJChronology gJChronology0 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField1 = gJChronology0.dayOfYear();
        org.joda.time.DurationField durationField2 = gJChronology0.years();
        // The following exception was thrown during execution in test generation
        try {
            long long7 = gJChronology0.getDateTimeMillis((int) (short) 10, (-1), 2026, (int) (byte) 100);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value -1 for monthOfYear must be in the range [1,12]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gJChronology0);
        org.junit.Assert.assertNotNull(dateTimeField1);
        org.junit.Assert.assertNotNull(durationField2);
    }

    @Test
    public void test0046() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0046");
        java.io.File file0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.tz.ZoneInfoProvider zoneInfoProvider1 = new org.joda.time.tz.ZoneInfoProvider(file0);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No file directory provided");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0047() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0047");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateMidnight dateMidnight3 = new org.joda.time.DateMidnight(2147483647, (int) ' ', 0);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 2147483647 for year must be in the range [-292275055,292278994]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0048() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0048");
        org.joda.time.ReadableInterval readableInterval0 = null;
        org.joda.time.Chronology chronology1 = org.joda.time.DateTimeUtils.getIntervalChronology(readableInterval0);
        org.junit.Assert.assertNotNull(chronology1);
    }

    @Test
    public void test0049() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0049");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTime dateTime4 = dateTime0.withHourOfDay(100);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 100 for hourOfDay must be in the range [0,23]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTime2);
    }

    @Test
    public void test0050() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0050");
        org.joda.time.DateTimeFieldType dateTimeFieldType0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.IllegalFieldValueException illegalFieldValueException4 = new org.joda.time.IllegalFieldValueException(dateTimeFieldType0, (java.lang.Number) 10.0d, (java.lang.Number) 2147483647, (java.lang.Number) (-1L));
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0051() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0051");
        java.io.Writer writer0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.format.FormatUtils.writeUnpaddedInteger(writer0, 2147483647);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0052() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0052");
        java.util.Locale locale0 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.text.DateFormatSymbols dateFormatSymbols1 = org.joda.time.DateTimeUtils.getDateFormatSymbols(locale0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0053() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0053");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        int int5 = localDateTime1.getYear();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDateTime localDateTime7 = localDateTime1.withDayOfWeek(2147483647);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 2147483647 for dayOfWeek must be in the range [1,7]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 2026 + "'", int5 == 2026);
    }

    @Test
    public void test0054() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0054");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.Months months4 = org.joda.time.Months.FIVE;
        org.joda.time.Period period5 = period3.minus((org.joda.time.ReadablePeriod) months4);
        org.joda.time.Period period7 = period3.plusMillis((-1));
        org.joda.time.Period period8 = period3.toPeriod();
        org.joda.time.chrono.GJChronology gJChronology9 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology10 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology9);
        org.joda.time.chrono.LenientChronology lenientChronology11 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology9);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalTime localTime12 = new org.joda.time.LocalTime((java.lang.Object) period3, (org.joda.time.Chronology) lenientChronology11);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No partial converter found for type: org.joda.time.Period");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(months4);
        org.junit.Assert.assertNotNull(period5);
        org.junit.Assert.assertNotNull(period7);
        org.junit.Assert.assertNotNull(period8);
        org.junit.Assert.assertNotNull(gJChronology9);
        org.junit.Assert.assertNotNull(lenientChronology10);
        org.junit.Assert.assertNotNull(lenientChronology11);
    }

    @Test
    public void test0055() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0055");
        org.joda.time.DateTimeZone dateTimeZone7 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MutableDateTime mutableDateTime8 = new org.joda.time.MutableDateTime(3, 10, (int) ' ', (int) (short) 10, 40237996, (int) (short) 1, (int) (short) 10, dateTimeZone7);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 40237996 for minuteOfHour must be in the range [0,59]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0056() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0056");
        java.math.RoundingMode roundingMode2 = null;
        // The following exception was thrown during execution in test generation
        try {
            long long3 = org.joda.time.field.FieldUtils.safeDivide((long) (-1), 0L, roundingMode2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0057() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0057");
        long long2 = org.joda.time.field.FieldUtils.safeDivide((long) 86400000, (long) ' ');
        org.junit.Assert.assertTrue("'" + long2 + "' != '" + 2700000L + "'", long2 == 2700000L);
    }

    @Test
    public void test0058() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0058");
        org.joda.time.chrono.GJChronology gJChronology4 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField5 = gJChronology4.dayOfYear();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalTime localTime6 = new org.joda.time.LocalTime((int) (short) 0, (int) 'a', 24, 100, (org.joda.time.Chronology) gJChronology4);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 97 for minuteOfHour must be in the range [0,59]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gJChronology4);
        org.junit.Assert.assertNotNull(dateTimeField5);
    }

    @Test
    public void test0059() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0059");
        org.joda.time.LocalTime localTime1 = new org.joda.time.LocalTime((long) (-1));
        org.joda.time.DateTimeFieldType dateTimeFieldType2 = null;
        // The following exception was thrown during execution in test generation
        try {
            int int3 = localTime1.get(dateTimeFieldType2);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The DateTimeFieldType must not be null");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0060() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0060");
        org.joda.time.format.PeriodFormatter periodFormatter0 = org.joda.time.format.PeriodFormat.wordBased();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Period period2 = periodFormatter0.parsePeriod("2026-08-06T11:10:36.726");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid format: \"2026-08-06T11:10:36.726\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(periodFormatter0);
    }

    @Test
    public void test0061() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0061");
        org.joda.time.Hours hours0 = org.joda.time.Hours.TWO;
        org.junit.Assert.assertNotNull(hours0);
    }

    @Test
    public void test0062() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0062");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.chrono.GregorianChronology gregorianChronology2 = org.joda.time.chrono.GregorianChronology.getInstance(dateTimeZone0, 24);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid min days in first week: 24");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0063() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0063");
        org.joda.time.tz.Provider provider0 = org.joda.time.DateTimeZone.getProvider();
        org.junit.Assert.assertNotNull(provider0);
    }

    @Test
    public void test0064() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0064");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.DurationField durationField3 = gJChronology1.months();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MonthDay monthDay4 = new org.joda.time.MonthDay((java.lang.Object) gJChronology1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No partial converter found for type: org.joda.time.chrono.GJChronology");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(durationField3);
    }

    @Test
    public void test0065() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0065");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        java.util.TimeZone timeZone2 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.util.Date date3 = localDateTime1.toDate(timeZone2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0066() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0066");
        org.joda.time.chrono.GJChronology gJChronology0 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField1 = gJChronology0.dayOfYear();
        org.joda.time.DurationField durationField2 = gJChronology0.years();
        long long5 = durationField2.subtract((long) (-1), (long) (byte) 100);
        long long8 = durationField2.subtract((-1L), (int) '4');
        org.junit.Assert.assertNotNull(gJChronology0);
        org.junit.Assert.assertNotNull(dateTimeField1);
        org.junit.Assert.assertNotNull(durationField2);
        org.junit.Assert.assertTrue("'" + long5 + "' != '" + (-3155673464001L) + "'", long5 == (-3155673464001L));
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1640995200001L) + "'", long8 == (-1640995200001L));
    }

    @Test
    public void test0067() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0067");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter1 = org.joda.time.format.DateTimeFormat.longDate();
        org.joda.time.chrono.JulianChronology julianChronology2 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.format.DateTimeFormatter dateTimeFormatter3 = dateTimeFormatter1.withChronology((org.joda.time.Chronology) julianChronology2);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalTime localTime4 = org.joda.time.LocalTime.parse("2026-08-06T11:10:39.499", dateTimeFormatter3);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid format: \"2026-08-06T11:10:39.499\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeFormatter1);
        org.junit.Assert.assertNotNull(julianChronology2);
        org.junit.Assert.assertNotNull(dateTimeFormatter3);
    }

    @Test
    public void test0068() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0068");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.Months months4 = org.joda.time.Months.FIVE;
        org.joda.time.Period period5 = period3.minus((org.joda.time.ReadablePeriod) months4);
        org.joda.time.Chronology chronology7 = null;
        org.joda.time.DateMidnight dateMidnight8 = new org.joda.time.DateMidnight((long) (short) -1, chronology7);
        org.joda.time.DateMidnight dateMidnight10 = dateMidnight8.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology12 = null;
        org.joda.time.DateMidnight dateMidnight13 = new org.joda.time.DateMidnight((long) (short) -1, chronology12);
        org.joda.time.DateMidnight dateMidnight15 = dateMidnight13.minusMonths((int) (byte) 100);
        int int16 = dateMidnight10.compareTo((org.joda.time.ReadableInstant) dateMidnight13);
        org.joda.time.Duration duration17 = period5.toDurationTo((org.joda.time.ReadableInstant) dateMidnight13);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Weeks weeks18 = org.joda.time.Weeks.standardWeeksIn((org.joda.time.ReadablePeriod) period5);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Cannot convert period to duration as months is not precise in the period P-5MT0.010S");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(months4);
        org.junit.Assert.assertNotNull(period5);
        org.junit.Assert.assertNotNull(dateMidnight10);
        org.junit.Assert.assertNotNull(dateMidnight15);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + (-1) + "'", int16 == (-1));
        org.junit.Assert.assertNotNull(duration17);
    }

    @Test
    public void test0069() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0069");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.dateElementParser();
        org.joda.time.Chronology chronology1 = dateTimeFormatter0.getChronolgy();
        java.lang.StringBuilder stringBuilder2 = null;
        org.joda.time.Chronology chronology4 = null;
        org.joda.time.DateMidnight dateMidnight5 = new org.joda.time.DateMidnight((long) (short) -1, chronology4);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight5.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight10 = dateMidnight5.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight12 = dateMidnight10.withYear(100);
        org.joda.time.Chronology chronology14 = null;
        org.joda.time.DateMidnight dateMidnight15 = new org.joda.time.DateMidnight((long) (short) -1, chronology14);
        org.joda.time.Interval interval16 = new org.joda.time.Interval((org.joda.time.ReadableInstant) dateMidnight12, (org.joda.time.ReadableInstant) dateMidnight15);
        // The following exception was thrown during execution in test generation
        try {
            dateTimeFormatter0.printTo(stringBuilder2, (org.joda.time.ReadableInstant) dateMidnight12);
            org.junit.Assert.fail("Expected exception of type java.lang.UnsupportedOperationException; message: Printing not supported");
        } catch (java.lang.UnsupportedOperationException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
        org.junit.Assert.assertNull(chronology1);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(dateMidnight10);
        org.junit.Assert.assertNotNull(dateMidnight12);
    }

    @Test
    public void test0070() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0070");
        org.joda.time.Partial partial0 = new org.joda.time.Partial();
        java.util.Locale locale2 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str3 = partial0.toString("2026-08-06T11:10:36.726", locale2);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Illegal pattern component: T");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0071() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0071");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.MutableDateTime mutableDateTime3 = dateMidnight2.toMutableDateTime();
        org.joda.time.chrono.GJChronology gJChronology4 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField5 = gJChronology4.dayOfYear();
        mutableDateTime3.setRounding(dateTimeField5);
        org.junit.Assert.assertNotNull(mutableDateTime3);
        org.junit.Assert.assertNotNull(gJChronology4);
        org.junit.Assert.assertNotNull(dateTimeField5);
    }

    @Test
    public void test0072() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0072");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray3 = monthDay2.getFieldTypes();
        org.joda.time.DateTimeFieldType dateTimeFieldType4 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MonthDay monthDay6 = monthDay2.withField(dateTimeFieldType4, 10);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Field 'null' is not supported");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(dateTimeFieldTypeArray3);
    }

    @Test
    public void test0073() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0073");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.TimeOfDay timeOfDay3 = new org.joda.time.TimeOfDay(2147483647, (int) (short) 0, 2147483647);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 2147483647 for hourOfDay must not be larger than 23");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0074() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0074");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone9 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.DateTime dateTime10 = dateTime4.toDateTime((org.joda.time.DateTimeZone) fixedDateTimeZone9);
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(dateTime10);
    }

    @Test
    public void test0075() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0075");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        // The following exception was thrown during execution in test generation
        try {
            long long8 = julianChronology0.getDateTimeMillis((int) '#', 0, (int) (short) 10, (int) (short) 0, 86400000, (int) '#', (int) '#');
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 86400000 for minuteOfHour must be in the range [0,59]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(julianChronology0);
    }

    @Test
    public void test0076() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0076");
        int int0 = org.joda.time.DateTimeConstants.AUGUST;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 8 + "'", int0 == 8);
    }

    @Test
    public void test0077() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0077");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar8 = dateMidnight7.toGregorianCalendar();
        org.joda.time.TimeOfDay timeOfDay9 = org.joda.time.TimeOfDay.fromCalendarFields((java.util.Calendar) gregorianCalendar8);
        org.joda.time.TimeOfDay timeOfDay11 = timeOfDay9.withMinuteOfHour(0);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.TimeOfDay timeOfDay13 = timeOfDay11.withSecondOfMinute((-1));
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value -1 for secondOfMinute must be in the range [0,59]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(gregorianCalendar8);
        org.junit.Assert.assertNotNull(timeOfDay9);
        org.junit.Assert.assertNotNull(timeOfDay11);
    }

    @Test
    public void test0078() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0078");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTimeFieldType dateTimeFieldType4 = monthDay2.getFieldType((int) (byte) -1);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: -1");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gJChronology1);
    }

    @Test
    public void test0079() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0079");
        org.joda.time.TimeOfDay timeOfDay1 = new org.joda.time.TimeOfDay((long) 100);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.TimeOfDay timeOfDay3 = timeOfDay1.withHourOfDay(40237996);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 40237996 for hourOfDay must be in the range [0,23]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0080() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0080");
        org.joda.time.chrono.GJChronology gJChronology0 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology1 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology0);
        org.joda.time.chrono.LenientChronology lenientChronology2 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology0);
        org.joda.time.DateTimeField dateTimeField3 = lenientChronology2.yearOfCentury();
        org.joda.time.LocalTime localTime5 = new org.joda.time.LocalTime((long) (-1));
        org.joda.time.Chronology chronology6 = localTime5.getChronology();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDate localDate7 = new org.joda.time.LocalDate((java.lang.Object) dateTimeField3, chronology6);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No partial converter found for type: org.joda.time.field.LenientDateTimeField");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gJChronology0);
        org.junit.Assert.assertNotNull(lenientChronology1);
        org.junit.Assert.assertNotNull(lenientChronology2);
        org.junit.Assert.assertNotNull(dateTimeField3);
        org.junit.Assert.assertNotNull(chronology6);
    }

    @Test
    public void test0081() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0081");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        mutableDateTime6.setMillisOfSecond(100);
        org.joda.time.DateTimeFieldType dateTimeFieldType9 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MutableDateTime.Property property10 = mutableDateTime6.property(dateTimeFieldType9);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The DateTimeFieldType must not be null");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
    }

    @Test
    public void test0082() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0082");
        java.lang.StringBuffer stringBuffer0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.format.FormatUtils.appendUnpaddedInteger(stringBuffer0, 2026);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0083() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0083");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        int int4 = localDate2.getEra();
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 1 + "'", int4 == 1);
    }

    @Test
    public void test0084() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0084");
        org.joda.time.Minutes minutes1 = org.joda.time.Minutes.minutes(100);
        org.junit.Assert.assertNotNull(minutes1);
    }

    @Test
    public void test0085() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0085");
        org.joda.time.DateTimeField dateTimeField0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.field.DelegatedDateTimeField delegatedDateTimeField1 = new org.joda.time.field.DelegatedDateTimeField(dateTimeField0);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The field must not be null");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0086() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0086");
        org.joda.time.format.PeriodFormatter periodFormatter0 = org.joda.time.format.PeriodFormat.wordBased();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay3 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology2);
        org.joda.time.DurationField durationField4 = gJChronology2.months();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MutablePeriod mutablePeriod5 = new org.joda.time.MutablePeriod((java.lang.Object) periodFormatter0, (org.joda.time.Chronology) gJChronology2);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No period converter found for type: org.joda.time.format.PeriodFormatter");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(periodFormatter0);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(durationField4);
    }

    @Test
    public void test0087() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0087");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        mutableDateTime6.setSecondOfMinute(0);
        org.joda.time.MutableDateTime.Property property10 = mutableDateTime6.minuteOfDay();
        int int11 = mutableDateTime6.getYearOfEra();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 1970 + "'", int11 == 1970);
    }

    @Test
    public void test0088() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0088");
        org.joda.time.Partial partial0 = new org.joda.time.Partial();
        org.joda.time.PeriodType periodType2 = null;
        org.joda.time.Chronology chronology3 = null;
        org.joda.time.Period period4 = new org.joda.time.Period((long) (short) 10, periodType2, chronology3);
        org.joda.time.ReadableInterval readableInterval5 = null;
        org.joda.time.Seconds seconds6 = org.joda.time.Seconds.secondsIn(readableInterval5);
        org.joda.time.DurationFieldType durationFieldType7 = seconds6.getFieldType();
        int int8 = period4.get(durationFieldType7);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Partial partial10 = partial0.withFieldAddWrapped(durationFieldType7, (-1));
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Field 'seconds' is not supported");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(seconds6);
        org.junit.Assert.assertNotNull(durationFieldType7);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
    }

    @Test
    public void test0089() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0089");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone7 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology8 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone7);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateMidnight dateMidnight9 = new org.joda.time.DateMidnight(100, (int) '#', (int) (short) 0, (org.joda.time.DateTimeZone) fixedDateTimeZone7);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 35 for monthOfYear must be in the range [1,12]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(copticChronology8);
    }

    @Test
    public void test0090() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0090");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDateTime localDateTime1 = org.joda.time.LocalDateTime.parse("Property[monthOfYear]");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid format: \"Property[monthOfYear]\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0091() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0091");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.DateTimeFormat.longDate();
        org.joda.time.format.DateTimePrinter dateTimePrinter1 = dateTimeFormatter0.getPrinter();
        java.lang.StringBuilder stringBuilder2 = null;
        // The following exception was thrown during execution in test generation
        try {
            dateTimeFormatter0.printTo(stringBuilder2, (long) ' ');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
        org.junit.Assert.assertNotNull(dateTimePrinter1);
    }

    @Test
    public void test0092() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0092");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        int int4 = localDateTime1.getMillisOfDay();
        int int5 = localDateTime1.getMonthOfYear();
        org.junit.Assert.assertNotNull(localDateTime3);
// flaky "1) test0092(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int4 + "' != '" + 40243947 + "'", int4 == 40243947);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 8 + "'", int5 == 8);
    }

    @Test
    public void test0093() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0093");
        org.joda.time.Minutes minutes0 = org.joda.time.Minutes.MAX_VALUE;
        org.joda.time.Minutes minutes1 = org.joda.time.Minutes.MAX_VALUE;
        boolean boolean2 = minutes0.isLessThan(minutes1);
        int int3 = minutes1.getMinutes();
        org.joda.time.Minutes minutes4 = org.joda.time.Minutes.MAX_VALUE;
        org.joda.time.Minutes minutes5 = org.joda.time.Minutes.MAX_VALUE;
        boolean boolean6 = minutes4.isLessThan(minutes5);
        int int7 = minutes1.compareTo((org.joda.time.base.BaseSingleFieldPeriod) minutes4);
        org.joda.time.chrono.JulianChronology julianChronology9 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate10 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology9);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDateTime localDateTime11 = new org.joda.time.LocalDateTime((java.lang.Object) int7, (org.joda.time.Chronology) julianChronology9);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No partial converter found for type: java.lang.Integer");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(minutes0);
        org.junit.Assert.assertNotNull(minutes1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 2147483647 + "'", int3 == 2147483647);
        org.junit.Assert.assertNotNull(minutes4);
        org.junit.Assert.assertNotNull(minutes5);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertNotNull(julianChronology9);
    }

    @Test
    public void test0094() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0094");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        int int4 = localDateTime1.getMillisOfDay();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDateTime localDateTime6 = localDateTime1.withEra(10);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 10 for era must be in the range [0,1]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(localDateTime3);
// flaky "2) test0094(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int4 + "' != '" + 40244215 + "'", int4 == 40244215);
    }

    @Test
    public void test0095() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0095");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.withYear(100);
        org.joda.time.Chronology chronology11 = null;
        org.joda.time.DateMidnight dateMidnight12 = new org.joda.time.DateMidnight((long) (short) -1, chronology11);
        org.joda.time.Interval interval13 = new org.joda.time.Interval((org.joda.time.ReadableInstant) dateMidnight9, (org.joda.time.ReadableInstant) dateMidnight12);
        org.joda.time.Period period15 = org.joda.time.Period.weeks((int) (short) 100);
        org.joda.time.Interval interval16 = interval13.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) period15);
        org.joda.time.YearMonth yearMonth17 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone18 = null;
        org.joda.time.Interval interval19 = yearMonth17.toInterval(dateTimeZone18);
        boolean boolean20 = interval13.isAfter((org.joda.time.ReadableInterval) interval19);
        org.joda.time.ReadableInterval readableInterval21 = null;
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean22 = interval13.isEqual(readableInterval21);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(period15);
        org.junit.Assert.assertNotNull(interval16);
        org.junit.Assert.assertNotNull(interval19);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
    }

    @Test
    public void test0096() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0096");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.dateHourMinuteSecondMillis();
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
    }

    @Test
    public void test0097() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0097");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = property3.addToCopy(1);
        org.joda.time.LocalDate localDate6 = property3.withMaximumValue();
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(localDate6);
    }

    @Test
    public void test0098() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0098");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDate localDate1 = org.joda.time.LocalDate.parse("2026-08-06T11:10:36.726");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid format: \"2026-08-06T11:10:36.726\" is malformed at \"T11:10:36.726\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0099() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0099");
        java.util.Date date0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.TimeOfDay timeOfDay1 = org.joda.time.TimeOfDay.fromDateFields(date0);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The date must not be null");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0100() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0100");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.DurationField durationField6 = property5.getLeapDurationField();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateMidnight dateMidnight8 = property5.setCopy("Property[monthOfYear]");
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value \"Property[monthOfYear]\" for centuryOfEra is not supported");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNull(durationField6);
    }

    @Test
    public void test0101() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0101");
        org.joda.time.Partial partial0 = new org.joda.time.Partial();
        org.joda.time.DateTimeFieldType dateTimeFieldType1 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Partial partial3 = partial0.withField(dateTimeFieldType1, 2);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Field 'null' is not supported");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0102() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0102");
        int int0 = org.joda.time.DateTimeConstants.JANUARY;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 1 + "'", int0 == 1);
    }

    @Test
    public void test0103() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0103");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        mutableDateTime6.setSecondOfMinute(0);
        org.joda.time.MutableDateTime.Property property10 = mutableDateTime6.minuteOfDay();
        // The following exception was thrown during execution in test generation
        try {
            mutableDateTime6.setHourOfDay((int) ' ');
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 32 for hourOfDay must be in the range [0,23]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(property10);
    }

    @Test
    public void test0104() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0104");
        org.joda.time.PeriodType periodType0 = org.joda.time.PeriodType.seconds();
        org.junit.Assert.assertNotNull(periodType0);
    }

    @Test
    public void test0105() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0105");
        org.joda.time.Chronology chronology3 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateMidnight dateMidnight4 = new org.joda.time.DateMidnight((-1), 1, (int) 'a', chronology3);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 97 for dayOfMonth must be in the range [1,31]: year: -1 month: 1");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0106() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0106");
        org.joda.time.Weeks weeks0 = org.joda.time.Weeks.MIN_VALUE;
        org.joda.time.Weeks weeks1 = org.joda.time.Weeks.MIN_VALUE;
        boolean boolean2 = weeks0.isGreaterThan(weeks1);
        org.joda.time.Weeks weeks3 = org.joda.time.Weeks.TWO;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Weeks weeks4 = weeks1.minus(weeks3);
            org.junit.Assert.fail("Expected exception of type java.lang.ArithmeticException; message: The calculation caused an overflow: -2147483648 + -2");
        } catch (java.lang.ArithmeticException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(weeks0);
        org.junit.Assert.assertNotNull(weeks1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(weeks3);
    }

    @Test
    public void test0107() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0107");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.DateTimeFormat.longDate();
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.format.DateTimeFormatter dateTimeFormatter2 = dateTimeFormatter0.withChronology((org.joda.time.Chronology) julianChronology1);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MonthDay monthDay3 = new org.joda.time.MonthDay((java.lang.Object) dateTimeFormatter0);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No partial converter found for type: org.joda.time.format.DateTimeFormatter");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(dateTimeFormatter2);
    }

    @Test
    public void test0108() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0108");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = property3.addToCopy(1);
        org.joda.time.LocalDate localDate7 = property3.setCopy((int) ' ');
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(localDate7);
    }

    @Test
    public void test0109() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0109");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.withDayOfMonth(100);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 100 for dayOfMonth must be in the range [1,31]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0110() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0110");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.MutableDateTime mutableDateTime3 = dateMidnight2.toMutableDateTime();
        java.lang.Object obj4 = mutableDateTime3.clone();
        org.junit.Assert.assertNotNull(mutableDateTime3);
        org.junit.Assert.assertNotNull(obj4);
        org.junit.Assert.assertEquals(obj4.toString(), "1970-01-01T00:00:00.000+01:00");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj4), "1970-01-01T00:00:00.000+01:00");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj4), "1970-01-01T00:00:00.000+01:00");
    }

    @Test
    public void test0111() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0111");
        java.io.DataInput dataInput0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTimeZone dateTimeZone2 = org.joda.time.tz.DateTimeZoneBuilder.readFrom(dataInput0, "2029-05-02T11:10:41.857");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0112() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0112");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        java.util.Locale locale6 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDateTime localDateTime7 = property4.setCopy("", locale6);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value \"\" for weekOfWeekyear is not supported");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
    }

    @Test
    public void test0113() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0113");
        org.joda.time.PeriodType periodType0 = org.joda.time.PeriodType.yearWeekDay();
        org.junit.Assert.assertNotNull(periodType0);
    }

    @Test
    public void test0114() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0114");
        org.joda.time.YearMonthDay yearMonthDay0 = new org.joda.time.YearMonthDay();
    }

    @Test
    public void test0115() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0115");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.DateTimeFormat.shortDate();
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
    }

    @Test
    public void test0116() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0116");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.hourMinuteSecondMillis();
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
    }

    @Test
    public void test0117() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0117");
        org.joda.time.tz.DateTimeZoneBuilder dateTimeZoneBuilder0 = new org.joda.time.tz.DateTimeZoneBuilder();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.tz.DateTimeZoneBuilder dateTimeZoneBuilder11 = dateTimeZoneBuilder0.addRecurringSavings("monthOfYear", 2147483647, (int) (byte) 1, 24, '4', (int) (short) 100, 100, 100, false, 8);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Unknown mode: 4");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0118() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0118");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime2.plusHours(0);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTime dateTime6 = dateTime4.withMinuteOfHour(1970);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 1970 for minuteOfHour must be in the range [0,59]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
    }

    @Test
    public void test0119() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0119");
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
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(duration6);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(dateMidnight16);
        org.junit.Assert.assertNotNull(dateMidnight21);
        org.junit.Assert.assertTrue("'" + int22 + "' != '" + (-1) + "'", int22 == (-1));
        org.junit.Assert.assertNotNull(minutes23);
        org.junit.Assert.assertNotNull(dateMidnight26);
        org.junit.Assert.assertNotNull(dateTime28);
        org.junit.Assert.assertNotNull(copticChronology34);
        org.junit.Assert.assertNotNull(leapYearPatternType35);
        org.junit.Assert.assertNotNull(dateMidnight40);
        org.junit.Assert.assertNotNull(property41);
        org.junit.Assert.assertNotNull(mutableDateTime42);
        org.junit.Assert.assertNotNull(property43);
        org.junit.Assert.assertNotNull(mutableDateTime45);
        org.junit.Assert.assertTrue("'" + boolean46 + "' != '" + false + "'", boolean46 == false);
        org.junit.Assert.assertNotNull(islamicChronology47);
        org.junit.Assert.assertNotNull(dateTime49);
    }

    @Test
    public void test0120() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0120");
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
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTime dateTime32 = dateTime4.withDate(1, (int) (short) -1, 40243947);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value -1 for monthOfYear must be in the range [1,12]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(duration6);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(dateMidnight16);
        org.junit.Assert.assertNotNull(dateMidnight21);
        org.junit.Assert.assertTrue("'" + int22 + "' != '" + (-1) + "'", int22 == (-1));
        org.junit.Assert.assertNotNull(minutes23);
        org.junit.Assert.assertNotNull(dateMidnight26);
        org.junit.Assert.assertNotNull(dateTime28);
    }

    @Test
    public void test0121() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0121");
        org.joda.time.LocalTime localTime2 = new org.joda.time.LocalTime(10, 10);
        java.util.Locale locale4 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str5 = localTime2.toString("hi!", locale4);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Illegal pattern component: i");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0122() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0122");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Duration duration1 = org.joda.time.Duration.parse("2029-05-02T11:10:41.857");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid format: \"2029-05-02T11:10:41.857\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0123() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0123");
        org.joda.time.chrono.GregorianChronology gregorianChronology0 = org.joda.time.chrono.GregorianChronology.getInstance();
        // The following exception was thrown during execution in test generation
        try {
            long long5 = gregorianChronology0.getDateTimeMillis((-1), 29, 1970, (int) '4');
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 29 for monthOfYear must be in the range [1,12]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gregorianChronology0);
    }

    @Test
    public void test0124() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0124");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = property3.addToCopy(1);
        org.joda.time.DateTime dateTime6 = localDate5.toDateTimeAtCurrentTime();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDate localDate8 = localDate5.withDayOfWeek((int) (short) 0);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 0 for dayOfWeek must be in the range [1,7]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(dateTime6);
    }

    @Test
    public void test0125() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0125");
        org.joda.time.TimeOfDay timeOfDay0 = new org.joda.time.TimeOfDay();
    }

    @Test
    public void test0126() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0126");
        org.joda.time.Partial partial0 = new org.joda.time.Partial();
        org.joda.time.format.DateTimeFormatter dateTimeFormatter1 = partial0.getFormatter();
        org.joda.time.Minutes minutes2 = org.joda.time.Minutes.MAX_VALUE;
        org.joda.time.Minutes minutes3 = org.joda.time.Minutes.MAX_VALUE;
        boolean boolean4 = minutes2.isLessThan(minutes3);
        org.joda.time.Partial partial5 = partial0.plus((org.joda.time.ReadablePeriod) minutes2);
        java.util.Locale locale7 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str8 = partial5.toString("2026-08-06T11:10:39.499", locale7);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Illegal pattern component: T");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(dateTimeFormatter1);
        org.junit.Assert.assertNotNull(minutes2);
        org.junit.Assert.assertNotNull(minutes3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(partial5);
    }

    @Test
    public void test0127() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0127");
        org.joda.time.Partial partial0 = new org.joda.time.Partial();
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.DateMidnight dateMidnight3 = new org.joda.time.DateMidnight((long) (short) -1, chronology2);
        org.joda.time.DateMidnight dateMidnight5 = dateMidnight3.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight8 = dateMidnight3.withDurationAdded((long) 10, 3);
        boolean boolean9 = partial0.isMatch((org.joda.time.ReadableInstant) dateMidnight3);
        org.junit.Assert.assertNotNull(dateMidnight5);
        org.junit.Assert.assertNotNull(dateMidnight8);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
    }

    @Test
    public void test0128() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0128");
        long long1 = org.joda.time.DateTimeUtils.toJulianDayNumber((long) 3);
        org.junit.Assert.assertTrue("'" + long1 + "' != '" + 2440588L + "'", long1 == 2440588L);
    }

    @Test
    public void test0129() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0129");
        org.joda.time.Duration duration1 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Chronology chronology3 = null;
        org.joda.time.DateMidnight dateMidnight4 = new org.joda.time.DateMidnight((long) (short) -1, chronology3);
        org.joda.time.DateMidnight dateMidnight6 = dateMidnight4.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology8 = null;
        org.joda.time.DateMidnight dateMidnight9 = new org.joda.time.DateMidnight((long) (short) -1, chronology8);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology13 = null;
        org.joda.time.DateMidnight dateMidnight14 = new org.joda.time.DateMidnight((long) (short) -1, chronology13);
        org.joda.time.DateMidnight dateMidnight16 = dateMidnight14.minusMonths((int) (byte) 100);
        int int17 = dateMidnight11.compareTo((org.joda.time.ReadableInstant) dateMidnight14);
        org.joda.time.Minutes minutes18 = org.joda.time.Minutes.minutesBetween((org.joda.time.ReadableInstant) dateMidnight4, (org.joda.time.ReadableInstant) dateMidnight11);
        org.joda.time.DateMidnight dateMidnight21 = dateMidnight11.withDurationAdded((long) '4', 100);
        org.joda.time.MutableInterval mutableInterval22 = new org.joda.time.MutableInterval((org.joda.time.ReadableDuration) duration1, (org.joda.time.ReadableInstant) dateMidnight21);
        // The following exception was thrown during execution in test generation
        try {
            mutableInterval22.setStartMillis(0L);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The end instant must be greater than the start instant");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(duration1);
        org.junit.Assert.assertNotNull(dateMidnight6);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(dateMidnight16);
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + (-1) + "'", int17 == (-1));
        org.junit.Assert.assertNotNull(minutes18);
        org.junit.Assert.assertNotNull(dateMidnight21);
    }

    @Test
    public void test0130() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0130");
        int int0 = org.joda.time.DateTimeConstants.BCE;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 0 + "'", int0 == 0);
    }

    @Test
    public void test0131() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0131");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.withYear(100);
        org.joda.time.PeriodType periodType11 = null;
        org.joda.time.Chronology chronology12 = null;
        org.joda.time.Period period13 = new org.joda.time.Period((long) (short) 10, periodType11, chronology12);
        org.joda.time.Months months14 = org.joda.time.Months.FIVE;
        org.joda.time.Period period15 = period13.minus((org.joda.time.ReadablePeriod) months14);
        org.joda.time.Chronology chronology17 = null;
        org.joda.time.DateMidnight dateMidnight18 = new org.joda.time.DateMidnight((long) (short) -1, chronology17);
        org.joda.time.DateMidnight dateMidnight20 = dateMidnight18.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology22 = null;
        org.joda.time.DateMidnight dateMidnight23 = new org.joda.time.DateMidnight((long) (short) -1, chronology22);
        org.joda.time.DateMidnight dateMidnight25 = dateMidnight23.minusMonths((int) (byte) 100);
        int int26 = dateMidnight20.compareTo((org.joda.time.ReadableInstant) dateMidnight23);
        org.joda.time.Duration duration27 = period15.toDurationTo((org.joda.time.ReadableInstant) dateMidnight23);
        org.joda.time.Duration duration28 = duration27.negated();
        org.joda.time.PeriodType periodType29 = org.joda.time.PeriodType.yearDay();
        org.joda.time.PeriodType periodType30 = periodType29.withSecondsRemoved();
        org.joda.time.MutablePeriod mutablePeriod31 = new org.joda.time.MutablePeriod((org.joda.time.ReadableInstant) dateMidnight7, (org.joda.time.ReadableDuration) duration27, periodType30);
        // The following exception was thrown during execution in test generation
        try {
            mutablePeriod31.add(24, 2, (int) ' ', (int) (byte) 100, (int) 'a', 8, (int) (byte) 10, 0);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Period does not support field 'months'");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(months14);
        org.junit.Assert.assertNotNull(period15);
        org.junit.Assert.assertNotNull(dateMidnight20);
        org.junit.Assert.assertNotNull(dateMidnight25);
        org.junit.Assert.assertTrue("'" + int26 + "' != '" + (-1) + "'", int26 == (-1));
        org.junit.Assert.assertNotNull(duration27);
        org.junit.Assert.assertNotNull(duration28);
        org.junit.Assert.assertNotNull(periodType29);
        org.junit.Assert.assertNotNull(periodType30);
    }

    @Test
    public void test0132() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0132");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.chrono.GJChronology gJChronology5 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay6 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology5);
        org.joda.time.MonthDay monthDay8 = monthDay6.minusDays((int) '4');
        org.joda.time.MonthDay monthDay10 = monthDay8.minusDays((int) (short) 100);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Months months11 = org.joda.time.Months.monthsBetween((org.joda.time.ReadablePartial) localDateTime3, (org.joda.time.ReadablePartial) monthDay8);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: ReadablePartial objects must have the same set of fields");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(gJChronology5);
        org.junit.Assert.assertNotNull(monthDay8);
        org.junit.Assert.assertNotNull(monthDay10);
    }

    @Test
    public void test0133() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0133");
        org.joda.time.Minutes minutes0 = org.joda.time.Minutes.TWO;
        org.junit.Assert.assertNotNull(minutes0);
    }

    @Test
    public void test0134() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0134");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = property3.addToCopy(1);
        org.joda.time.Chronology chronology7 = null;
        org.joda.time.DateMidnight dateMidnight8 = new org.joda.time.DateMidnight((long) (short) -1, chronology7);
        org.joda.time.DateMidnight dateMidnight10 = dateMidnight8.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology12 = null;
        org.joda.time.DateMidnight dateMidnight13 = new org.joda.time.DateMidnight((long) (short) -1, chronology12);
        org.joda.time.DateMidnight dateMidnight15 = dateMidnight13.minusMonths((int) (byte) 100);
        int int16 = dateMidnight10.compareTo((org.joda.time.ReadableInstant) dateMidnight13);
        long long17 = property3.getDifferenceAsLong((org.joda.time.ReadableInstant) dateMidnight10);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateMidnight dateMidnight19 = dateMidnight10.withYearOfCentury(100);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 100 for yearOfCentury must be in the range [0,99]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(dateMidnight10);
        org.junit.Assert.assertNotNull(dateMidnight15);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + (-1) + "'", int16 == (-1));
        org.junit.Assert.assertTrue("'" + long17 + "' != '" + 8L + "'", long17 == 8L);
    }

    @Test
    public void test0135() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0135");
        java.lang.Appendable appendable0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.format.FormatUtils.appendPaddedInteger(appendable0, 8L, 3);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0136() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0136");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Years years1 = org.joda.time.Years.parseYears("2029-05-02T11:10:43.261");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid format: \"2029-05-02T11:10:43.261\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0137() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0137");
        org.joda.time.Duration duration1 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Chronology chronology3 = null;
        org.joda.time.DateMidnight dateMidnight4 = new org.joda.time.DateMidnight((long) (short) -1, chronology3);
        org.joda.time.DateMidnight dateMidnight6 = dateMidnight4.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology8 = null;
        org.joda.time.DateMidnight dateMidnight9 = new org.joda.time.DateMidnight((long) (short) -1, chronology8);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology13 = null;
        org.joda.time.DateMidnight dateMidnight14 = new org.joda.time.DateMidnight((long) (short) -1, chronology13);
        org.joda.time.DateMidnight dateMidnight16 = dateMidnight14.minusMonths((int) (byte) 100);
        int int17 = dateMidnight11.compareTo((org.joda.time.ReadableInstant) dateMidnight14);
        org.joda.time.Minutes minutes18 = org.joda.time.Minutes.minutesBetween((org.joda.time.ReadableInstant) dateMidnight4, (org.joda.time.ReadableInstant) dateMidnight11);
        org.joda.time.DateMidnight dateMidnight21 = dateMidnight11.withDurationAdded((long) '4', 100);
        org.joda.time.MutableInterval mutableInterval22 = new org.joda.time.MutableInterval((org.joda.time.ReadableDuration) duration1, (org.joda.time.ReadableInstant) dateMidnight21);
        java.lang.String str23 = duration1.toString();
        org.junit.Assert.assertNotNull(duration1);
        org.junit.Assert.assertNotNull(dateMidnight6);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(dateMidnight16);
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + (-1) + "'", int17 == (-1));
        org.junit.Assert.assertNotNull(minutes18);
        org.junit.Assert.assertNotNull(dateMidnight21);
        org.junit.Assert.assertEquals("'" + str23 + "' != '" + "PT60S" + "'", str23, "PT60S");
    }

    @Test
    public void test0138() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0138");
        int int0 = org.joda.time.DateTimeConstants.SECONDS_PER_MINUTE;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 60 + "'", int0 == 60);
    }

    @Test
    public void test0139() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0139");
        int int0 = org.joda.time.DateTimeConstants.APRIL;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 4 + "'", int0 == 4);
    }

    @Test
    public void test0140() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0140");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Hours hours1 = org.joda.time.Hours.parseHours("1961-09-01T00:00:00.000+01:00");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid format: \"1961-09-01T00:00:00.000+01:00\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0141() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0141");
        java.lang.Appendable appendable0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.format.FormatUtils.appendPaddedInteger(appendable0, (long) (short) -1, (int) (short) 1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0142() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0142");
        org.joda.time.Duration duration1 = org.joda.time.Duration.standardDays((long) (short) 1);
        org.joda.time.Chronology chronology3 = null;
        org.joda.time.DateMidnight dateMidnight4 = new org.joda.time.DateMidnight((long) (short) -1, chronology3);
        org.joda.time.DateMidnight dateMidnight6 = dateMidnight4.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight4.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.withYear(100);
        org.joda.time.PeriodType periodType13 = null;
        org.joda.time.Chronology chronology14 = null;
        org.joda.time.Period period15 = new org.joda.time.Period((long) (short) 10, periodType13, chronology14);
        org.joda.time.Months months16 = org.joda.time.Months.FIVE;
        org.joda.time.Period period17 = period15.minus((org.joda.time.ReadablePeriod) months16);
        org.joda.time.Chronology chronology19 = null;
        org.joda.time.DateMidnight dateMidnight20 = new org.joda.time.DateMidnight((long) (short) -1, chronology19);
        org.joda.time.DateMidnight dateMidnight22 = dateMidnight20.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology24 = null;
        org.joda.time.DateMidnight dateMidnight25 = new org.joda.time.DateMidnight((long) (short) -1, chronology24);
        org.joda.time.DateMidnight dateMidnight27 = dateMidnight25.minusMonths((int) (byte) 100);
        int int28 = dateMidnight22.compareTo((org.joda.time.ReadableInstant) dateMidnight25);
        org.joda.time.Duration duration29 = period17.toDurationTo((org.joda.time.ReadableInstant) dateMidnight25);
        org.joda.time.Duration duration30 = duration29.negated();
        org.joda.time.PeriodType periodType31 = org.joda.time.PeriodType.yearDay();
        org.joda.time.PeriodType periodType32 = periodType31.withSecondsRemoved();
        org.joda.time.MutablePeriod mutablePeriod33 = new org.joda.time.MutablePeriod((org.joda.time.ReadableInstant) dateMidnight9, (org.joda.time.ReadableDuration) duration29, periodType32);
        int int34 = duration1.compareTo((org.joda.time.ReadableDuration) duration29);
        org.joda.time.DateTime dateTime35 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone36 = null;
        org.joda.time.DateTime dateTime37 = dateTime35.withZoneRetainFields(dateTimeZone36);
        org.joda.time.DateTime dateTime39 = dateTime35.withYear((int) (byte) 0);
        org.joda.time.Duration duration41 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Chronology chronology43 = null;
        org.joda.time.DateMidnight dateMidnight44 = new org.joda.time.DateMidnight((long) (short) -1, chronology43);
        org.joda.time.DateMidnight dateMidnight46 = dateMidnight44.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology48 = null;
        org.joda.time.DateMidnight dateMidnight49 = new org.joda.time.DateMidnight((long) (short) -1, chronology48);
        org.joda.time.DateMidnight dateMidnight51 = dateMidnight49.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology53 = null;
        org.joda.time.DateMidnight dateMidnight54 = new org.joda.time.DateMidnight((long) (short) -1, chronology53);
        org.joda.time.DateMidnight dateMidnight56 = dateMidnight54.minusMonths((int) (byte) 100);
        int int57 = dateMidnight51.compareTo((org.joda.time.ReadableInstant) dateMidnight54);
        org.joda.time.Minutes minutes58 = org.joda.time.Minutes.minutesBetween((org.joda.time.ReadableInstant) dateMidnight44, (org.joda.time.ReadableInstant) dateMidnight51);
        org.joda.time.DateMidnight dateMidnight61 = dateMidnight51.withDurationAdded((long) '4', 100);
        org.joda.time.MutableInterval mutableInterval62 = new org.joda.time.MutableInterval((org.joda.time.ReadableDuration) duration41, (org.joda.time.ReadableInstant) dateMidnight61);
        org.joda.time.DateTime dateTime63 = dateTime39.minus((org.joda.time.ReadableDuration) duration41);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MutableInterval mutableInterval64 = new org.joda.time.MutableInterval((org.joda.time.ReadableDuration) duration29, (org.joda.time.ReadableInstant) dateTime39);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The end instant must be greater than the start instant");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(duration1);
        org.junit.Assert.assertNotNull(dateMidnight6);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(months16);
        org.junit.Assert.assertNotNull(period17);
        org.junit.Assert.assertNotNull(dateMidnight22);
        org.junit.Assert.assertNotNull(dateMidnight27);
        org.junit.Assert.assertTrue("'" + int28 + "' != '" + (-1) + "'", int28 == (-1));
        org.junit.Assert.assertNotNull(duration29);
        org.junit.Assert.assertNotNull(duration30);
        org.junit.Assert.assertNotNull(periodType31);
        org.junit.Assert.assertNotNull(periodType32);
        org.junit.Assert.assertTrue("'" + int34 + "' != '" + 1 + "'", int34 == 1);
        org.junit.Assert.assertNotNull(dateTime37);
        org.junit.Assert.assertNotNull(dateTime39);
        org.junit.Assert.assertNotNull(duration41);
        org.junit.Assert.assertNotNull(dateMidnight46);
        org.junit.Assert.assertNotNull(dateMidnight51);
        org.junit.Assert.assertNotNull(dateMidnight56);
        org.junit.Assert.assertTrue("'" + int57 + "' != '" + (-1) + "'", int57 == (-1));
        org.junit.Assert.assertNotNull(minutes58);
        org.junit.Assert.assertNotNull(dateMidnight61);
        org.junit.Assert.assertNotNull(dateTime63);
    }

    @Test
    public void test0143() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0143");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = localDate2.withWeekyear((int) (byte) 100);
        org.joda.time.LocalDate localDate7 = localDate2.withWeekyear((int) (short) -1);
        int int8 = localDate7.getWeekyear();
        org.joda.time.DateTimeFieldType dateTimeFieldType9 = null;
        // The following exception was thrown during execution in test generation
        try {
            int int10 = localDate7.get(dateTimeFieldType9);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The DateTimeFieldType must not be null");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(localDate7);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + (-1) + "'", int8 == (-1));
    }

    @Test
    public void test0144() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0144");
        int int0 = org.joda.time.TimeOfDay.SECOND_OF_MINUTE;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 2 + "'", int0 == 2);
    }

    @Test
    public void test0145() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0145");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology11 = null;
        org.joda.time.DateMidnight dateMidnight12 = new org.joda.time.DateMidnight((long) (short) -1, chronology11);
        org.joda.time.DateMidnight dateMidnight14 = dateMidnight12.minusMonths((int) (byte) 100);
        int int15 = dateMidnight9.compareTo((org.joda.time.ReadableInstant) dateMidnight12);
        org.joda.time.Minutes minutes16 = org.joda.time.Minutes.minutesBetween((org.joda.time.ReadableInstant) dateMidnight2, (org.joda.time.ReadableInstant) dateMidnight9);
        org.joda.time.DateMidnight dateMidnight19 = dateMidnight9.withDurationAdded((long) '4', 100);
        org.joda.time.Chronology chronology21 = null;
        org.joda.time.DateMidnight dateMidnight22 = new org.joda.time.DateMidnight((long) (short) -1, chronology21);
        org.joda.time.DateMidnight dateMidnight24 = dateMidnight22.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight27 = dateMidnight22.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar28 = dateMidnight27.toGregorianCalendar();
        org.joda.time.MutableInterval mutableInterval29 = new org.joda.time.MutableInterval((org.joda.time.ReadableInstant) dateMidnight9, (org.joda.time.ReadableInstant) dateMidnight27);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateMidnight dateMidnight31 = dateMidnight27.withEra(2);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 2 for era must be in the range [0,1]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(dateMidnight14);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + (-1) + "'", int15 == (-1));
        org.junit.Assert.assertNotNull(minutes16);
        org.junit.Assert.assertNotNull(dateMidnight19);
        org.junit.Assert.assertNotNull(dateMidnight24);
        org.junit.Assert.assertNotNull(dateMidnight27);
        org.junit.Assert.assertNotNull(gregorianCalendar28);
    }

    @Test
    public void test0146() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0146");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone5 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology6 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.LocalDateTime localDateTime7 = new org.joda.time.LocalDateTime((long) (-1), (org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.tz.CachedDateTimeZone cachedDateTimeZone8 = org.joda.time.tz.CachedDateTimeZone.forZone((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.chrono.JulianChronology julianChronology10 = org.joda.time.chrono.JulianChronology.getInstance((org.joda.time.DateTimeZone) cachedDateTimeZone8, (int) '4');
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid min days in first week: 52");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gregorianChronology6);
        org.junit.Assert.assertNotNull(cachedDateTimeZone8);
    }

    @Test
    public void test0147() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0147");
        org.joda.time.tz.DateTimeZoneBuilder dateTimeZoneBuilder0 = new org.joda.time.tz.DateTimeZoneBuilder();
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.DateMidnight dateMidnight3 = new org.joda.time.DateMidnight((long) (short) -1, chronology2);
        org.joda.time.DateMidnight dateMidnight5 = dateMidnight3.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology7 = null;
        org.joda.time.DateMidnight dateMidnight8 = new org.joda.time.DateMidnight((long) (short) -1, chronology7);
        org.joda.time.DateMidnight dateMidnight10 = dateMidnight8.minusMonths((int) (byte) 100);
        int int11 = dateMidnight5.compareTo((org.joda.time.ReadableInstant) dateMidnight8);
        org.joda.time.chrono.GJChronology gJChronology12 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTime dateTime13 = dateMidnight8.toDateTime((org.joda.time.Chronology) gJChronology12);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MutableDateTime mutableDateTime14 = new org.joda.time.MutableDateTime((java.lang.Object) dateTimeZoneBuilder0, (org.joda.time.Chronology) gJChronology12);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No instant converter found for type: org.joda.time.tz.DateTimeZoneBuilder");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight5);
        org.junit.Assert.assertNotNull(dateMidnight10);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + (-1) + "'", int11 == (-1));
        org.junit.Assert.assertNotNull(gJChronology12);
        org.junit.Assert.assertNotNull(dateTime13);
    }

    @Test
    public void test0148() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0148");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone5 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology6 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType7 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology9 = null;
        org.joda.time.DateMidnight dateMidnight10 = new org.joda.time.DateMidnight((long) (short) -1, chronology9);
        org.joda.time.DateMidnight dateMidnight12 = dateMidnight10.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property13 = dateMidnight10.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime14 = dateMidnight10.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property15 = mutableDateTime14.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime17 = property15.addWrapField((int) (short) -1);
        boolean boolean18 = leapYearPatternType7.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology19 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5, leapYearPatternType7);
        org.joda.time.DurationField durationField20 = islamicChronology19.millis();
        org.joda.time.DateTimeField dateTimeField21 = islamicChronology19.yearOfEra();
        org.joda.time.LocalTime localTime22 = new org.joda.time.LocalTime((long) '#', (org.joda.time.Chronology) islamicChronology19);
        org.junit.Assert.assertNotNull(copticChronology6);
        org.junit.Assert.assertNotNull(leapYearPatternType7);
        org.junit.Assert.assertNotNull(dateMidnight12);
        org.junit.Assert.assertNotNull(property13);
        org.junit.Assert.assertNotNull(mutableDateTime14);
        org.junit.Assert.assertNotNull(property15);
        org.junit.Assert.assertNotNull(mutableDateTime17);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertNotNull(islamicChronology19);
        org.junit.Assert.assertNotNull(durationField20);
        org.junit.Assert.assertNotNull(dateTimeField21);
    }

    @Test
    public void test0149() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0149");
        int int0 = org.joda.time.DateTimeConstants.SECONDS_PER_HOUR;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 3600 + "'", int0 == 3600);
    }

    @Test
    public void test0150() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0150");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        mutableDateTime6.setSecondOfMinute(24);
        mutableDateTime6.setMinuteOfDay(59);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
    }

    @Test
    public void test0151() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0151");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Years years1 = org.joda.time.Years.parseYears("monthOfYear");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid format: \"monthOfYear\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0152() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0152");
        org.joda.time.LocalTime localTime1 = new org.joda.time.LocalTime((long) (-1));
        org.joda.time.LocalTime localTime3 = localTime1.withSecondOfMinute(1);
        // The following exception was thrown during execution in test generation
        try {
            int int5 = localTime1.getValue((-1));
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: Invalid index: -1");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(localTime3);
    }

    @Test
    public void test0153() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0153");
        int int0 = org.joda.time.DateTimeConstants.FEBRUARY;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 2 + "'", int0 == 2);
    }

    @Test
    public void test0154() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0154");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.tz.ZoneInfoProvider zoneInfoProvider1 = new org.joda.time.tz.ZoneInfoProvider("2026-08-06T11:10:36.726");
            org.junit.Assert.fail("Expected exception of type java.io.IOException; message: Resource not found: \"2026-08-06T11:10:36.726/ZoneInfoMap\" ClassLoader: sun.misc.Launcher$AppClassLoader@4e25154f");
        } catch (java.io.IOException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0155() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0155");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar8 = dateMidnight7.toGregorianCalendar();
        org.joda.time.TimeOfDay timeOfDay9 = org.joda.time.TimeOfDay.fromCalendarFields((java.util.Calendar) gregorianCalendar8);
        org.joda.time.Chronology chronology11 = null;
        org.joda.time.DateMidnight dateMidnight12 = new org.joda.time.DateMidnight((long) (short) -1, chronology11);
        org.joda.time.DateMidnight dateMidnight14 = dateMidnight12.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight17 = dateMidnight12.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar18 = dateMidnight17.toGregorianCalendar();
        org.joda.time.TimeOfDay timeOfDay19 = org.joda.time.TimeOfDay.fromCalendarFields((java.util.Calendar) gregorianCalendar18);
        int int20 = timeOfDay19.getSecondOfMinute();
        org.joda.time.Years years21 = org.joda.time.Years.yearsBetween((org.joda.time.ReadablePartial) timeOfDay9, (org.joda.time.ReadablePartial) timeOfDay19);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.TimeOfDay timeOfDay23 = timeOfDay9.withHourOfDay(59);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 59 for hourOfDay must be in the range [0,23]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(gregorianCalendar8);
        org.junit.Assert.assertNotNull(timeOfDay9);
        org.junit.Assert.assertNotNull(dateMidnight14);
        org.junit.Assert.assertNotNull(dateMidnight17);
        org.junit.Assert.assertNotNull(gregorianCalendar18);
        org.junit.Assert.assertNotNull(timeOfDay19);
        org.junit.Assert.assertTrue("'" + int20 + "' != '" + 0 + "'", int20 == 0);
        org.junit.Assert.assertNotNull(years21);
    }

    @Test
    public void test0156() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0156");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = property3.addToCopy(1);
        org.joda.time.Chronology chronology7 = null;
        org.joda.time.DateMidnight dateMidnight8 = new org.joda.time.DateMidnight((long) (short) -1, chronology7);
        org.joda.time.DateMidnight dateMidnight10 = dateMidnight8.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology12 = null;
        org.joda.time.DateMidnight dateMidnight13 = new org.joda.time.DateMidnight((long) (short) -1, chronology12);
        org.joda.time.DateMidnight dateMidnight15 = dateMidnight13.minusMonths((int) (byte) 100);
        int int16 = dateMidnight10.compareTo((org.joda.time.ReadableInstant) dateMidnight13);
        long long17 = property3.getDifferenceAsLong((org.joda.time.ReadableInstant) dateMidnight10);
        int int18 = property3.getMaximumValueOverall();
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(dateMidnight10);
        org.junit.Assert.assertNotNull(dateMidnight15);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + (-1) + "'", int16 == (-1));
        org.junit.Assert.assertTrue("'" + long17 + "' != '" + 8L + "'", long17 == 8L);
        org.junit.Assert.assertTrue("'" + int18 + "' != '" + 100 + "'", int18 == 100);
    }

    @Test
    public void test0157() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0157");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.chrono.CopticChronology copticChronology2 = org.joda.time.chrono.CopticChronology.getInstance(dateTimeZone0, (int) (byte) -1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid min days in first week: -1");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0158() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0158");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.hour();
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
    }

    @Test
    public void test0159() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0159");
        int int0 = org.joda.time.DateTimeConstants.SEPTEMBER;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 9 + "'", int0 == 9);
    }

    @Test
    public void test0160() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0160");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.weekyearWeek();
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
    }

    @Test
    public void test0161() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0161");
        org.joda.time.chrono.GJChronology gJChronology0 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj1 = null;
        boolean boolean2 = gJChronology0.equals(obj1);
        org.joda.time.DurationField durationField3 = gJChronology0.days();
        org.joda.time.chrono.GJChronology gJChronology4 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology5 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology4);
        org.joda.time.chrono.LenientChronology lenientChronology6 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology4);
        org.joda.time.DateTimeField dateTimeField7 = lenientChronology6.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField8 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology0, dateTimeField7);
        org.joda.time.YearMonth yearMonth9 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray10 = yearMonth9.getFieldTypes();
        java.util.Locale locale11 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str12 = skipUndoDateTimeField8.getAsShortText((org.joda.time.ReadablePartial) yearMonth9, locale11);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Field 'yearOfCentury' is not supported");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gJChronology0);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(durationField3);
        org.junit.Assert.assertNotNull(gJChronology4);
        org.junit.Assert.assertNotNull(lenientChronology5);
        org.junit.Assert.assertNotNull(lenientChronology6);
        org.junit.Assert.assertNotNull(dateTimeField7);
        org.junit.Assert.assertNotNull(dateTimeFieldTypeArray10);
    }

    @Test
    public void test0162() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0162");
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray0 = new org.joda.time.DateTimeFieldType[] {};
        java.util.ArrayList<org.joda.time.DateTimeFieldType> dateTimeFieldTypeList1 = new java.util.ArrayList<org.joda.time.DateTimeFieldType>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.joda.time.DateTimeFieldType>) dateTimeFieldTypeList1, dateTimeFieldTypeArray0);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.format.DateTimeFormatter dateTimeFormatter5 = org.joda.time.format.ISODateTimeFormat.forFields((java.util.Collection<org.joda.time.DateTimeFieldType>) dateTimeFieldTypeList1, true, false);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The fields must not be null or empty");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeFieldTypeArray0);
        org.junit.Assert.assertArrayEquals(dateTimeFieldTypeArray0, new org.joda.time.DateTimeFieldType[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
    }

    @Test
    public void test0163() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0163");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.YearMonth yearMonth2 = new org.joda.time.YearMonth(10L, chronology1);
    }

    @Test
    public void test0164() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0164");
        org.joda.time.chrono.GJChronology gJChronology0 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj1 = null;
        boolean boolean2 = gJChronology0.equals(obj1);
        org.joda.time.DurationField durationField3 = gJChronology0.days();
        org.joda.time.chrono.GJChronology gJChronology4 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology5 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology4);
        org.joda.time.chrono.LenientChronology lenientChronology6 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology4);
        org.joda.time.DateTimeField dateTimeField7 = lenientChronology6.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField8 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology0, dateTimeField7);
        org.joda.time.TimeOfDay timeOfDay10 = new org.joda.time.TimeOfDay((long) 100);
        int[] intArray14 = new int[] { (short) 10, 29, 51 };
        // The following exception was thrown during execution in test generation
        try {
            int int15 = skipUndoDateTimeField8.getMaximumValue((org.joda.time.ReadablePartial) timeOfDay10, intArray14);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 3");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gJChronology0);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(durationField3);
        org.junit.Assert.assertNotNull(gJChronology4);
        org.junit.Assert.assertNotNull(lenientChronology5);
        org.junit.Assert.assertNotNull(lenientChronology6);
        org.junit.Assert.assertNotNull(dateTimeField7);
        org.junit.Assert.assertNotNull(intArray14);
        org.junit.Assert.assertArrayEquals(intArray14, new int[] { 10, 29, 51 });
    }

    @Test
    public void test0165() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0165");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.TimeOfDay timeOfDay3 = new org.joda.time.TimeOfDay(8, 12, (int) (byte) -1);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value -1 for secondOfMinute must not be smaller than 0");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0166() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0166");
        org.joda.time.LocalTime localTime1 = org.joda.time.LocalTime.fromMillisOfDay((long) '4');
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalTime localTime3 = localTime1.withSecondOfMinute(40237996);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 40237996 for secondOfMinute must be in the range [0,59]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(localTime1);
    }

    @Test
    public void test0167() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0167");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone5 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology6 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.LocalDateTime localDateTime7 = new org.joda.time.LocalDateTime((long) (-1), (org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.tz.CachedDateTimeZone cachedDateTimeZone8 = org.joda.time.tz.CachedDateTimeZone.forZone((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        long long10 = fixedDateTimeZone5.previousTransition(1L);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.chrono.EthiopicChronology ethiopicChronology12 = org.joda.time.chrono.EthiopicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5, (int) (short) 10);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid min days in first week: 10");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gregorianChronology6);
        org.junit.Assert.assertNotNull(cachedDateTimeZone8);
        org.junit.Assert.assertTrue("'" + long10 + "' != '" + 1L + "'", long10 == 1L);
    }

    @Test
    public void test0168() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0168");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.MonthDay monthDay4 = monthDay2.minusDays((int) '4');
        org.joda.time.MonthDay.Property property5 = monthDay2.monthOfYear();
        java.lang.String str6 = property5.toString();
        int int7 = property5.getMaximumValue();
        java.util.Locale locale8 = null;
        java.lang.String str9 = property5.getAsShortText(locale8);
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(monthDay4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "Property[monthOfYear]" + "'", str6, "Property[monthOfYear]");
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 12 + "'", int7 == 12);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "Jan" + "'", str9, "Jan");
    }

    @Test
    public void test0169() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0169");
        int int0 = org.joda.time.DateTimeConstants.THURSDAY;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 4 + "'", int0 == 4);
    }

    @Test
    public void test0170() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0170");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.MonthDay monthDay4 = monthDay2.minusDays((int) '4');
        org.joda.time.MonthDay.Property property5 = monthDay2.monthOfYear();
        int int6 = monthDay2.getMonthOfYear();
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(monthDay4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 1 + "'", int6 == 1);
    }

    @Test
    public void test0171() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0171");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology2 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology1);
        org.joda.time.TimeOfDay timeOfDay3 = org.joda.time.TimeOfDay.fromMillisOfDay((long) 2026, (org.joda.time.Chronology) lenientChronology2);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MonthDay monthDay4 = new org.joda.time.MonthDay((java.lang.Object) 2026);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No partial converter found for type: java.lang.Integer");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(lenientChronology2);
        org.junit.Assert.assertNotNull(timeOfDay3);
    }

    @Test
    public void test0172() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0172");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone5 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology6 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.LocalDateTime localDateTime7 = new org.joda.time.LocalDateTime((long) (-1), (org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.tz.CachedDateTimeZone cachedDateTimeZone8 = org.joda.time.tz.CachedDateTimeZone.forZone((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        long long10 = cachedDateTimeZone8.previousTransition((long) (byte) 10);
        org.junit.Assert.assertNotNull(gregorianChronology6);
        org.junit.Assert.assertNotNull(cachedDateTimeZone8);
        org.junit.Assert.assertTrue("'" + long10 + "' != '" + 10L + "'", long10 == 10L);
    }

    @Test
    public void test0173() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0173");
        int int0 = org.joda.time.DateTimeConstants.MINUTES_PER_HOUR;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 60 + "'", int0 == 60);
    }

    @Test
    public void test0174() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0174");
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
        org.joda.time.MutableDateTime.Property property15 = mutableDateTime10.dayOfWeek();
        org.junit.Assert.assertNotNull(instant0);
        org.junit.Assert.assertNotNull(instant3);
        org.junit.Assert.assertNotNull(dateMidnight8);
        org.junit.Assert.assertNotNull(property9);
        org.junit.Assert.assertNotNull(mutableDateTime10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(months14);
        org.junit.Assert.assertNotNull(property15);
    }

    @Test
    public void test0175() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0175");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone4 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology5 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType6 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology8 = null;
        org.joda.time.DateMidnight dateMidnight9 = new org.joda.time.DateMidnight((long) (short) -1, chronology8);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property12 = dateMidnight9.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime13 = dateMidnight9.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property14 = mutableDateTime13.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime16 = property14.addWrapField((int) (short) -1);
        boolean boolean17 = leapYearPatternType6.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology18 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4, leapYearPatternType6);
        org.joda.time.MonthDay monthDay19 = new org.joda.time.MonthDay((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        org.joda.time.DateMidnight dateMidnight20 = new org.joda.time.DateMidnight((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        org.joda.time.Chronology chronology22 = null;
        org.joda.time.DateMidnight dateMidnight23 = new org.joda.time.DateMidnight((long) (short) -1, chronology22);
        org.joda.time.DateMidnight dateMidnight25 = dateMidnight23.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property26 = dateMidnight23.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime27 = dateMidnight23.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property28 = mutableDateTime27.centuryOfEra();
        mutableDateTime27.setSecondOfMinute(0);
        org.joda.time.Chronology chronology32 = null;
        org.joda.time.DateMidnight dateMidnight33 = new org.joda.time.DateMidnight((long) (short) -1, chronology32);
        org.joda.time.DateMidnight dateMidnight35 = dateMidnight33.minusMonths((int) (byte) 100);
        org.joda.time.Months months36 = org.joda.time.Months.monthsBetween((org.joda.time.ReadableInstant) mutableDateTime27, (org.joda.time.ReadableInstant) dateMidnight33);
        mutableDateTime27.setSecondOfDay(2026);
        int int39 = fixedDateTimeZone4.getOffset((org.joda.time.ReadableInstant) mutableDateTime27);
        org.joda.time.Chronology chronology41 = null;
        org.joda.time.DateMidnight dateMidnight42 = new org.joda.time.DateMidnight((long) (short) -1, chronology41);
        org.joda.time.DateMidnight dateMidnight44 = dateMidnight42.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight47 = dateMidnight42.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight49 = dateMidnight47.withYear(100);
        org.joda.time.Chronology chronology51 = null;
        org.joda.time.DateMidnight dateMidnight52 = new org.joda.time.DateMidnight((long) (short) -1, chronology51);
        org.joda.time.Interval interval53 = new org.joda.time.Interval((org.joda.time.ReadableInstant) dateMidnight49, (org.joda.time.ReadableInstant) dateMidnight52);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.chrono.GJChronology gJChronology55 = org.joda.time.chrono.GJChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4, (org.joda.time.ReadableInstant) dateMidnight49, 24);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid min days in first week: 24");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(copticChronology5);
        org.junit.Assert.assertNotNull(leapYearPatternType6);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(property12);
        org.junit.Assert.assertNotNull(mutableDateTime13);
        org.junit.Assert.assertNotNull(property14);
        org.junit.Assert.assertNotNull(mutableDateTime16);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(islamicChronology18);
        org.junit.Assert.assertNotNull(dateMidnight25);
        org.junit.Assert.assertNotNull(property26);
        org.junit.Assert.assertNotNull(mutableDateTime27);
        org.junit.Assert.assertNotNull(property28);
        org.junit.Assert.assertNotNull(dateMidnight35);
        org.junit.Assert.assertNotNull(months36);
        org.junit.Assert.assertTrue("'" + int39 + "' != '" + 100 + "'", int39 == 100);
        org.junit.Assert.assertNotNull(dateMidnight44);
        org.junit.Assert.assertNotNull(dateMidnight47);
        org.junit.Assert.assertNotNull(dateMidnight49);
    }

    @Test
    public void test0176() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0176");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MutablePeriod mutablePeriod1 = new org.joda.time.MutablePeriod((java.lang.Object) (byte) 1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No period converter found for type: java.lang.Byte");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0177() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0177");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        mutableDateTime6.setSecondOfMinute(0);
        mutableDateTime6.add((long) 86400000);
        org.joda.time.MutableDateTime.Property property12 = mutableDateTime6.millisOfDay();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(property12);
    }

    @Test
    public void test0178() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0178");
        org.joda.time.Partial partial0 = new org.joda.time.Partial();
        org.joda.time.format.DateTimeFormatter dateTimeFormatter1 = partial0.getFormatter();
        org.joda.time.Minutes minutes2 = org.joda.time.Minutes.MAX_VALUE;
        org.joda.time.Minutes minutes3 = org.joda.time.Minutes.MAX_VALUE;
        boolean boolean4 = minutes2.isLessThan(minutes3);
        org.joda.time.Partial partial5 = partial0.plus((org.joda.time.ReadablePeriod) minutes2);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTimeFieldType dateTimeFieldType7 = partial5.getFieldType((int) (byte) 10);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 10");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(dateTimeFormatter1);
        org.junit.Assert.assertNotNull(minutes2);
        org.junit.Assert.assertNotNull(minutes3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(partial5);
    }

    @Test
    public void test0179() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0179");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone5 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology6 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.LocalDateTime localDateTime7 = new org.joda.time.LocalDateTime((long) (-1), (org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.LocalDateTime localDateTime8 = new org.joda.time.LocalDateTime((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDateTime localDateTime10 = localDateTime8.withMonthOfYear(100);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 100 for monthOfYear must be in the range [1,12]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gregorianChronology6);
    }

    @Test
    public void test0180() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0180");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.LocalTime localTime2 = new org.joda.time.LocalTime((long) (-1));
        org.joda.time.Chronology chronology3 = localTime2.getChronology();
        int int4 = localTime2.getSecondOfMinute();
        org.joda.time.LocalTime.Property property5 = localTime2.secondOfMinute();
        // The following exception was thrown during execution in test generation
        try {
            int int6 = yearMonth0.compareTo((org.joda.time.ReadablePartial) localTime2);
            org.junit.Assert.fail("Expected exception of type java.lang.ClassCastException; message: ReadablePartial objects must have matching field types");
        } catch (java.lang.ClassCastException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(chronology3);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 59 + "'", int4 == 59);
        org.junit.Assert.assertNotNull(property5);
    }

    @Test
    public void test0181() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0181");
        org.joda.time.Period period1 = org.joda.time.Period.months(2147483647);
        org.junit.Assert.assertNotNull(period1);
    }

    @Test
    public void test0182() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0182");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.YearMonthDay yearMonthDay5 = dateTime0.toYearMonthDay();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone10 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.DateTime dateTime11 = yearMonthDay5.toDateTimeAtMidnight((org.joda.time.DateTimeZone) fixedDateTimeZone10);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTime dateTime13 = dateTime11.withMonthOfYear(86400000);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 86400000 for monthOfYear must be in the range [1,12]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(yearMonthDay5);
        org.junit.Assert.assertNotNull(dateTime11);
    }

    @Test
    public void test0183() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0183");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = localDate2.withWeekyear((int) (byte) 100);
        org.joda.time.LocalDate localDate7 = localDate2.withWeekyear((int) (short) -1);
        int int8 = localDate7.getWeekyear();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDate localDate10 = localDate7.withEra(40237996);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 40237996 for era must be in the range [0,1]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(localDate7);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + (-1) + "'", int8 == (-1));
    }

    @Test
    public void test0184() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0184");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        org.joda.time.ReadableInterval readableInterval5 = null;
        org.joda.time.Seconds seconds6 = org.joda.time.Seconds.secondsIn(readableInterval5);
        org.joda.time.DurationFieldType durationFieldType7 = seconds6.getFieldType();
        org.joda.time.LocalDateTime localDateTime9 = localDateTime1.withFieldAdded(durationFieldType7, 86400000);
        java.lang.String str10 = localDateTime9.toString();
        boolean boolean12 = localDateTime9.equals((java.lang.Object) (-1.0d));
        int int13 = localDateTime9.size();
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(seconds6);
        org.junit.Assert.assertNotNull(durationFieldType7);
        org.junit.Assert.assertNotNull(localDateTime9);
// flaky "3) test0184(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str10 + "' != '" + "2029-05-02T11:10:53.327" + "'", str10, "2029-05-02T11:10:53.327");
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 4 + "'", int13 == 4);
    }

    @Test
    public void test0185() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0185");
        org.joda.time.MutablePeriod mutablePeriod8 = new org.joda.time.MutablePeriod((int) (byte) 1, (int) (short) 0, (int) (byte) -1, (int) (short) -1, (int) (byte) 1, 0, 100, 3);
        org.joda.time.format.PeriodFormatter periodFormatter9 = org.joda.time.format.PeriodFormat.wordBased();
        org.joda.time.format.PeriodParser periodParser10 = periodFormatter9.getParser();
        java.lang.String str11 = mutablePeriod8.toString(periodFormatter9);
        mutablePeriod8.clear();
        org.junit.Assert.assertNotNull(periodFormatter9);
        org.junit.Assert.assertNotNull(periodParser10);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "1 year, -1 weeks, -1 days, 1 hour, 100 seconds and 3 milliseconds" + "'", str11, "1 year, -1 weeks, -1 days, 1 hour, 100 seconds and 3 milliseconds");
    }

    @Test
    public void test0186() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0186");
        int int0 = org.joda.time.DateTimeConstants.MINUTES_PER_DAY;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 1440 + "'", int0 == 1440);
    }

    @Test
    public void test0187() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0187");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar8 = dateMidnight7.toGregorianCalendar();
        org.joda.time.TimeOfDay timeOfDay9 = org.joda.time.TimeOfDay.fromCalendarFields((java.util.Calendar) gregorianCalendar8);
        org.joda.time.Chronology chronology11 = null;
        org.joda.time.DateMidnight dateMidnight12 = new org.joda.time.DateMidnight((long) (short) -1, chronology11);
        org.joda.time.DateMidnight dateMidnight14 = dateMidnight12.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight17 = dateMidnight12.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar18 = dateMidnight17.toGregorianCalendar();
        org.joda.time.TimeOfDay timeOfDay19 = org.joda.time.TimeOfDay.fromCalendarFields((java.util.Calendar) gregorianCalendar18);
        int int20 = timeOfDay19.getSecondOfMinute();
        org.joda.time.Years years21 = org.joda.time.Years.yearsBetween((org.joda.time.ReadablePartial) timeOfDay9, (org.joda.time.ReadablePartial) timeOfDay19);
        org.joda.time.chrono.GJChronology gJChronology22 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj23 = null;
        boolean boolean24 = gJChronology22.equals(obj23);
        org.joda.time.DurationField durationField25 = gJChronology22.days();
        org.joda.time.chrono.GJChronology gJChronology26 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology27 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology26);
        org.joda.time.chrono.LenientChronology lenientChronology28 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology26);
        org.joda.time.DateTimeField dateTimeField29 = lenientChronology28.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField30 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology22, dateTimeField29);
        org.joda.time.DateTimeFieldType dateTimeFieldType31 = skipUndoDateTimeField30.getType();
        // The following exception was thrown during execution in test generation
        try {
            int int32 = timeOfDay19.get(dateTimeFieldType31);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Field 'yearOfCentury' is not supported");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(gregorianCalendar8);
        org.junit.Assert.assertNotNull(timeOfDay9);
        org.junit.Assert.assertNotNull(dateMidnight14);
        org.junit.Assert.assertNotNull(dateMidnight17);
        org.junit.Assert.assertNotNull(gregorianCalendar18);
        org.junit.Assert.assertNotNull(timeOfDay19);
        org.junit.Assert.assertTrue("'" + int20 + "' != '" + 0 + "'", int20 == 0);
        org.junit.Assert.assertNotNull(years21);
        org.junit.Assert.assertNotNull(gJChronology22);
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
        org.junit.Assert.assertNotNull(durationField25);
        org.junit.Assert.assertNotNull(gJChronology26);
        org.junit.Assert.assertNotNull(lenientChronology27);
        org.junit.Assert.assertNotNull(lenientChronology28);
        org.junit.Assert.assertNotNull(dateTimeField29);
        org.junit.Assert.assertNotNull(dateTimeFieldType31);
    }

    @Test
    public void test0188() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0188");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.minusHours((int) (byte) -1);
        int int4 = localDateTime3.getWeekOfWeekyear();
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 32 + "'", int4 == 32);
    }

    @Test
    public void test0189() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0189");
        int int0 = org.joda.time.DateTimeConstants.SUNDAY;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 7 + "'", int0 == 7);
    }

    @Test
    public void test0190() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0190");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        int int4 = period3.getMonths();
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
    }

    @Test
    public void test0191() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0191");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone5 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology6 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.LocalDate localDate7 = new org.joda.time.LocalDate(1L, (org.joda.time.DateTimeZone) fixedDateTimeZone5);
        java.lang.String str8 = fixedDateTimeZone5.toString();
        org.junit.Assert.assertNotNull(copticChronology6);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "2026-08-06T11:10:36.726" + "'", str8, "2026-08-06T11:10:36.726");
    }

    @Test
    public void test0192() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0192");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = localDate2.withWeekyear((int) (byte) 100);
        org.joda.time.Chronology chronology7 = null;
        org.joda.time.DateMidnight dateMidnight8 = new org.joda.time.DateMidnight((long) (short) -1, chronology7);
        org.joda.time.DateMidnight dateMidnight10 = dateMidnight8.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property11 = dateMidnight8.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime12 = dateMidnight8.toMutableDateTimeISO();
        boolean boolean13 = localDate5.equals((java.lang.Object) mutableDateTime12);
        org.joda.time.LocalDate localDate15 = localDate5.plusMonths(10);
        int int16 = localDate5.getWeekOfWeekyear();
        org.joda.time.chrono.JulianChronology julianChronology18 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate19 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology18);
        org.joda.time.LocalDate.Property property20 = localDate19.yearOfCentury();
        org.joda.time.LocalDate localDate22 = localDate19.withWeekyear((int) (byte) 100);
        org.joda.time.Chronology chronology24 = null;
        org.joda.time.DateMidnight dateMidnight25 = new org.joda.time.DateMidnight((long) (short) -1, chronology24);
        org.joda.time.DateMidnight dateMidnight27 = dateMidnight25.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property28 = dateMidnight25.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime29 = dateMidnight25.toMutableDateTimeISO();
        boolean boolean30 = localDate22.equals((java.lang.Object) mutableDateTime29);
        org.joda.time.LocalDate localDate32 = localDate22.plusMonths(10);
        boolean boolean33 = localDate5.isEqual((org.joda.time.ReadablePartial) localDate22);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.YearMonthDay yearMonthDay34 = new org.joda.time.YearMonthDay((java.lang.Object) boolean33);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No partial converter found for type: java.lang.Boolean");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(dateMidnight10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(mutableDateTime12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(localDate15);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 51 + "'", int16 == 51);
        org.junit.Assert.assertNotNull(julianChronology18);
        org.junit.Assert.assertNotNull(property20);
        org.junit.Assert.assertNotNull(localDate22);
        org.junit.Assert.assertNotNull(dateMidnight27);
        org.junit.Assert.assertNotNull(property28);
        org.junit.Assert.assertNotNull(mutableDateTime29);
        org.junit.Assert.assertTrue("'" + boolean30 + "' != '" + false + "'", boolean30 == false);
        org.junit.Assert.assertNotNull(localDate32);
        org.junit.Assert.assertTrue("'" + boolean33 + "' != '" + true + "'", boolean33 == true);
    }

    @Test
    public void test0193() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0193");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        int int8 = skipUndoDateTimeField6.get((long) 10);
        org.joda.time.chrono.JulianChronology julianChronology9 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField10 = julianChronology9.weeks();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTime dateTime11 = new org.joda.time.DateTime((java.lang.Object) skipUndoDateTimeField6, (org.joda.time.Chronology) julianChronology9);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No instant converter found for type: org.joda.time.field.SkipUndoDateTimeField");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 2 + "'", int8 == 2);
        org.junit.Assert.assertNotNull(julianChronology9);
        org.junit.Assert.assertNotNull(durationField10);
    }

    @Test
    public void test0194() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0194");
        org.joda.time.Years years1 = org.joda.time.Years.years((int) (byte) 10);
        org.joda.time.Years years3 = years1.dividedBy(1);
        org.joda.time.Years years5 = years3.multipliedBy((int) '4');
        int int6 = years3.getYears();
        org.junit.Assert.assertNotNull(years1);
        org.junit.Assert.assertNotNull(years3);
        org.junit.Assert.assertNotNull(years5);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 10 + "'", int6 == 10);
    }

    @Test
    public void test0195() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0195");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.DurationField durationField3 = gJChronology1.months();
        org.joda.time.DateTimeZone dateTimeZone4 = gJChronology1.getZone();
        java.lang.String str5 = dateTimeZone4.getID();
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(durationField3);
        org.junit.Assert.assertNotNull(dateTimeZone4);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "Europe/Prague" + "'", str5, "Europe/Prague");
    }

    @Test
    public void test0196() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0196");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTime dateTime1 = org.joda.time.DateTime.parse("[]");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid format: \"[]\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0197() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0197");
        org.joda.time.IllegalInstantException illegalInstantException1 = new org.joda.time.IllegalInstantException("2029-05-02T11:10:53.327");
    }

    @Test
    public void test0198() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0198");
        org.joda.time.Duration duration1 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Chronology chronology3 = null;
        org.joda.time.DateMidnight dateMidnight4 = new org.joda.time.DateMidnight((long) (short) -1, chronology3);
        org.joda.time.DateMidnight dateMidnight6 = dateMidnight4.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology8 = null;
        org.joda.time.DateMidnight dateMidnight9 = new org.joda.time.DateMidnight((long) (short) -1, chronology8);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology13 = null;
        org.joda.time.DateMidnight dateMidnight14 = new org.joda.time.DateMidnight((long) (short) -1, chronology13);
        org.joda.time.DateMidnight dateMidnight16 = dateMidnight14.minusMonths((int) (byte) 100);
        int int17 = dateMidnight11.compareTo((org.joda.time.ReadableInstant) dateMidnight14);
        org.joda.time.Minutes minutes18 = org.joda.time.Minutes.minutesBetween((org.joda.time.ReadableInstant) dateMidnight4, (org.joda.time.ReadableInstant) dateMidnight11);
        org.joda.time.DateMidnight dateMidnight21 = dateMidnight11.withDurationAdded((long) '4', 100);
        org.joda.time.MutableInterval mutableInterval22 = new org.joda.time.MutableInterval((org.joda.time.ReadableDuration) duration1, (org.joda.time.ReadableInstant) dateMidnight21);
        org.joda.time.Chronology chronology24 = null;
        org.joda.time.DateMidnight dateMidnight25 = new org.joda.time.DateMidnight((long) (short) -1, chronology24);
        org.joda.time.DateMidnight dateMidnight27 = dateMidnight25.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight30 = dateMidnight25.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight32 = dateMidnight30.withYear(100);
        org.joda.time.Chronology chronology34 = null;
        org.joda.time.DateMidnight dateMidnight35 = new org.joda.time.DateMidnight((long) (short) -1, chronology34);
        org.joda.time.Interval interval36 = new org.joda.time.Interval((org.joda.time.ReadableInstant) dateMidnight32, (org.joda.time.ReadableInstant) dateMidnight35);
        // The following exception was thrown during execution in test generation
        try {
            mutableInterval22.setStart((org.joda.time.ReadableInstant) dateMidnight35);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The end instant must be greater than the start instant");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(duration1);
        org.junit.Assert.assertNotNull(dateMidnight6);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(dateMidnight16);
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + (-1) + "'", int17 == (-1));
        org.junit.Assert.assertNotNull(minutes18);
        org.junit.Assert.assertNotNull(dateMidnight21);
        org.junit.Assert.assertNotNull(dateMidnight27);
        org.junit.Assert.assertNotNull(dateMidnight30);
        org.junit.Assert.assertNotNull(dateMidnight32);
    }

    @Test
    public void test0199() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0199");
        org.joda.time.Minutes minutes0 = org.joda.time.Minutes.MAX_VALUE;
        org.joda.time.Minutes minutes1 = org.joda.time.Minutes.MAX_VALUE;
        boolean boolean2 = minutes0.isLessThan(minutes1);
        int int3 = minutes1.getMinutes();
        org.joda.time.Minutes minutes4 = org.joda.time.Minutes.MAX_VALUE;
        org.joda.time.Minutes minutes5 = org.joda.time.Minutes.MAX_VALUE;
        boolean boolean6 = minutes4.isLessThan(minutes5);
        int int7 = minutes1.compareTo((org.joda.time.base.BaseSingleFieldPeriod) minutes4);
        org.joda.time.Minutes minutes8 = null;
        org.joda.time.Minutes minutes9 = minutes1.plus(minutes8);
        org.joda.time.Minutes minutes11 = minutes9.minus(1);
        org.joda.time.Minutes minutes13 = minutes9.dividedBy(3600);
        org.junit.Assert.assertNotNull(minutes0);
        org.junit.Assert.assertNotNull(minutes1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 2147483647 + "'", int3 == 2147483647);
        org.junit.Assert.assertNotNull(minutes4);
        org.junit.Assert.assertNotNull(minutes5);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertNotNull(minutes9);
        org.junit.Assert.assertNotNull(minutes11);
        org.junit.Assert.assertNotNull(minutes13);
    }

    @Test
    public void test0200() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0200");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        org.joda.time.ReadableInterval readableInterval5 = null;
        org.joda.time.Seconds seconds6 = org.joda.time.Seconds.secondsIn(readableInterval5);
        org.joda.time.DurationFieldType durationFieldType7 = seconds6.getFieldType();
        org.joda.time.LocalDateTime localDateTime9 = localDateTime1.withFieldAdded(durationFieldType7, 86400000);
        org.joda.time.field.PreciseDurationField preciseDurationField11 = new org.joda.time.field.PreciseDurationField(durationFieldType7, (long) '4');
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(seconds6);
        org.junit.Assert.assertNotNull(durationFieldType7);
        org.junit.Assert.assertNotNull(localDateTime9);
    }

    @Test
    public void test0201() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0201");
        java.util.Date date0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDate localDate1 = org.joda.time.LocalDate.fromDateFields(date0);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The date must not be null");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0202() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0202");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        int int8 = skipUndoDateTimeField6.get((long) 10);
        int int10 = skipUndoDateTimeField6.getMaximumValue((long) (short) -1);
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 2 + "'", int8 == 2);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 53 + "'", int10 == 53);
    }

    @Test
    public void test0203() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0203");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        mutableDateTime6.setSecondOfMinute(0);
        org.joda.time.MutableDateTime.Property property10 = mutableDateTime6.minuteOfDay();
        // The following exception was thrown during execution in test generation
        try {
            mutableDateTime6.setDayOfMonth(1970);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 1970 for dayOfMonth must be in the range [1,31]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(property10);
    }

    @Test
    public void test0204() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0204");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar8 = dateMidnight7.toGregorianCalendar();
        org.joda.time.LocalTime localTime9 = org.joda.time.LocalTime.fromCalendarFields((java.util.Calendar) gregorianCalendar8);
        org.joda.time.LocalTime.Property property10 = localTime9.minuteOfHour();
        org.joda.time.LocalTime.Property property11 = localTime9.millisOfSecond();
        org.joda.time.LocalTime localTime13 = localTime9.withSecondOfMinute(9);
        org.joda.time.LocalTime localTime15 = localTime9.plusMillis(1970);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(gregorianCalendar8);
        org.junit.Assert.assertNotNull(localTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(localTime13);
        org.junit.Assert.assertNotNull(localTime15);
    }

    @Test
    public void test0205() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0205");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.MutableDateTime mutableDateTime3 = dateMidnight2.toMutableDateTime();
        org.joda.time.DateMidnight dateMidnight5 = dateMidnight2.withWeekyear(100);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withEra((int) 'a');
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 97 for era must be in the range [0,1]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(mutableDateTime3);
        org.junit.Assert.assertNotNull(dateMidnight5);
    }

    @Test
    public void test0206() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0206");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Hours hours1 = org.joda.time.Hours.parseHours("2026-08-06T11:10:39.499");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid format: \"2026-08-06T11:10:39.499\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0207() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0207");
        org.joda.time.MutablePeriod mutablePeriod8 = new org.joda.time.MutablePeriod((int) (short) -1, (int) (byte) 100, (int) '4', 40243947, 1, 0, 3, (int) (byte) 0);
    }

    @Test
    public void test0208() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0208");
        org.joda.time.LocalTime localTime1 = new org.joda.time.LocalTime((long) (-1));
        org.joda.time.Chronology chronology2 = localTime1.getChronology();
        int int3 = localTime1.getSecondOfMinute();
        org.joda.time.LocalTime.Property property4 = localTime1.secondOfMinute();
        java.util.Locale locale5 = null;
        int int6 = property4.getMaximumShortTextLength(locale5);
        org.junit.Assert.assertNotNull(chronology2);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 59 + "'", int3 == 59);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 2 + "'", int6 == 2);
    }

    @Test
    public void test0209() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0209");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.YearMonth yearMonth6 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone7 = null;
        org.joda.time.Interval interval8 = yearMonth6.toInterval(dateTimeZone7);
        org.joda.time.Months months9 = org.joda.time.Months.ONE;
        org.joda.time.Interval interval10 = interval8.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) months9);
        org.joda.time.DateTime dateTime11 = interval8.getEnd();
        org.joda.time.DateTime dateTime13 = dateTime11.plusSeconds(100);
        org.joda.time.Interval interval14 = new org.joda.time.Interval((org.joda.time.ReadableInstant) dateMidnight2, (org.joda.time.ReadableInstant) dateTime13);
        java.lang.String str15 = dateTime13.toString();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(interval8);
        org.junit.Assert.assertNotNull(months9);
        org.junit.Assert.assertNotNull(interval10);
        org.junit.Assert.assertNotNull(dateTime11);
        org.junit.Assert.assertNotNull(dateTime13);
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "2026-09-01T00:01:40.000+02:00" + "'", str15, "2026-09-01T00:01:40.000+02:00");
    }

    @Test
    public void test0210() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0210");
        org.joda.time.PeriodType periodType1 = org.joda.time.PeriodType.yearDay();
        org.joda.time.MutablePeriod mutablePeriod2 = new org.joda.time.MutablePeriod((long) (byte) 10, periodType1);
        // The following exception was thrown during execution in test generation
        try {
            mutablePeriod2.setMillis(3);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Period does not support field 'millis'");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(periodType1);
    }

    @Test
    public void test0211() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0211");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar8 = dateMidnight7.toGregorianCalendar();
        org.joda.time.LocalTime localTime9 = org.joda.time.LocalTime.fromCalendarFields((java.util.Calendar) gregorianCalendar8);
        org.joda.time.LocalTime.Property property10 = localTime9.minuteOfHour();
        org.joda.time.LocalTime.Property property11 = localTime9.millisOfSecond();
        org.joda.time.LocalTime localTime13 = property11.addCopy(0L);
        java.util.Locale locale15 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str16 = localTime13.toString("2029-05-02T11:10:53.327", locale15);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Illegal pattern component: T");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(gregorianCalendar8);
        org.junit.Assert.assertNotNull(localTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(localTime13);
    }

    @Test
    public void test0212() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0212");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime2.plusHours(0);
        org.joda.time.LocalDateTime localDateTime5 = dateTime2.toLocalDateTime();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDateTime localDateTime7 = localDateTime5.withDayOfMonth(51);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 51 for dayOfMonth must be in the range [1,31]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(localDateTime5);
    }

    @Test
    public void test0213() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0213");
        org.joda.time.Duration duration1 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Chronology chronology3 = null;
        org.joda.time.DateMidnight dateMidnight4 = new org.joda.time.DateMidnight((long) (short) -1, chronology3);
        org.joda.time.DateMidnight dateMidnight6 = dateMidnight4.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology8 = null;
        org.joda.time.DateMidnight dateMidnight9 = new org.joda.time.DateMidnight((long) (short) -1, chronology8);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology13 = null;
        org.joda.time.DateMidnight dateMidnight14 = new org.joda.time.DateMidnight((long) (short) -1, chronology13);
        org.joda.time.DateMidnight dateMidnight16 = dateMidnight14.minusMonths((int) (byte) 100);
        int int17 = dateMidnight11.compareTo((org.joda.time.ReadableInstant) dateMidnight14);
        org.joda.time.Minutes minutes18 = org.joda.time.Minutes.minutesBetween((org.joda.time.ReadableInstant) dateMidnight4, (org.joda.time.ReadableInstant) dateMidnight11);
        org.joda.time.DateMidnight dateMidnight21 = dateMidnight11.withDurationAdded((long) '4', 100);
        org.joda.time.MutableInterval mutableInterval22 = new org.joda.time.MutableInterval((org.joda.time.ReadableDuration) duration1, (org.joda.time.ReadableInstant) dateMidnight21);
        mutableInterval22.setDurationAfterStart((long) 1440);
        org.junit.Assert.assertNotNull(duration1);
        org.junit.Assert.assertNotNull(dateMidnight6);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(dateMidnight16);
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + (-1) + "'", int17 == (-1));
        org.junit.Assert.assertNotNull(minutes18);
        org.junit.Assert.assertNotNull(dateMidnight21);
    }

    @Test
    public void test0214() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0214");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Months months1 = org.joda.time.Months.parseMonths("[]");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid format: \"[]\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0215() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0215");
        org.joda.time.MutablePeriod mutablePeriod8 = new org.joda.time.MutablePeriod((int) (byte) 1, (int) (short) 0, (int) (byte) -1, (int) (short) -1, (int) (byte) 1, 0, 100, 3);
        org.joda.time.format.PeriodFormatter periodFormatter9 = org.joda.time.format.PeriodFormat.wordBased();
        org.joda.time.format.PeriodParser periodParser10 = periodFormatter9.getParser();
        java.lang.String str11 = mutablePeriod8.toString(periodFormatter9);
        boolean boolean12 = periodFormatter9.isParser();
        org.junit.Assert.assertNotNull(periodFormatter9);
        org.junit.Assert.assertNotNull(periodParser10);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "1 year, -1 weeks, -1 days, 1 hour, 100 seconds and 3 milliseconds" + "'", str11, "1 year, -1 weeks, -1 days, 1 hour, 100 seconds and 3 milliseconds");
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
    }

    @Test
    public void test0216() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0216");
        long long0 = org.joda.time.DateTimeUtils.currentTimeMillis();
// flaky "4) test0216(RegressionTest0)":         org.junit.Assert.assertTrue("'" + long0 + "' != '" + 1786007456115L + "'", long0 == 1786007456115L);
    }

    @Test
    public void test0217() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0217");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone4 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology5 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        java.lang.String str6 = copticChronology5.toString();
        org.joda.time.DateTimeField dateTimeField7 = copticChronology5.clockhourOfDay();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone13 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology14 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone13);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType15 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology17 = null;
        org.joda.time.DateMidnight dateMidnight18 = new org.joda.time.DateMidnight((long) (short) -1, chronology17);
        org.joda.time.DateMidnight dateMidnight20 = dateMidnight18.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property21 = dateMidnight18.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime22 = dateMidnight18.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property23 = mutableDateTime22.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime25 = property23.addWrapField((int) (short) -1);
        boolean boolean26 = leapYearPatternType15.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology27 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone13, leapYearPatternType15);
        org.joda.time.MutableDateTime mutableDateTime28 = new org.joda.time.MutableDateTime((long) (byte) 10, (org.joda.time.DateTimeZone) fixedDateTimeZone13);
        boolean boolean29 = copticChronology5.equals((java.lang.Object) mutableDateTime28);
        // The following exception was thrown during execution in test generation
        try {
            long long34 = copticChronology5.getDateTimeMillis(1440, 0, 0, (int) (byte) -1);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value -1 for millisOfDay must be in the range [0,86399999]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(copticChronology5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "CopticChronology[2026-08-06T11:10:36.726]" + "'", str6, "CopticChronology[2026-08-06T11:10:36.726]");
        org.junit.Assert.assertNotNull(dateTimeField7);
        org.junit.Assert.assertNotNull(copticChronology14);
        org.junit.Assert.assertNotNull(leapYearPatternType15);
        org.junit.Assert.assertNotNull(dateMidnight20);
        org.junit.Assert.assertNotNull(property21);
        org.junit.Assert.assertNotNull(mutableDateTime22);
        org.junit.Assert.assertNotNull(property23);
        org.junit.Assert.assertNotNull(mutableDateTime25);
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + false + "'", boolean26 == false);
        org.junit.Assert.assertNotNull(islamicChronology27);
        org.junit.Assert.assertTrue("'" + boolean29 + "' != '" + false + "'", boolean29 == false);
    }

    @Test
    public void test0218() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0218");
        org.joda.time.DateTimeComparator dateTimeComparator0 = org.joda.time.DateTimeComparator.getDateOnlyInstance();
        org.joda.time.DateTimeComparator dateTimeComparator1 = org.joda.time.DateTimeComparator.getTimeOnlyInstance();
        java.util.Comparator<java.lang.Object> objComparator2 = dateTimeComparator0.thenComparing((java.util.Comparator<java.lang.Object>) dateTimeComparator1);
        org.joda.time.DateTimeFieldType dateTimeFieldType3 = dateTimeComparator1.getUpperLimit();
        org.joda.time.DateTimeComparator dateTimeComparator4 = org.joda.time.DateTimeComparator.getInstance(dateTimeFieldType3);
        org.joda.time.DateTimeFieldType dateTimeFieldType5 = dateTimeComparator4.getUpperLimit();
        org.junit.Assert.assertNotNull(dateTimeComparator0);
        org.junit.Assert.assertNotNull(dateTimeComparator1);
        org.junit.Assert.assertNotNull(objComparator2);
        org.junit.Assert.assertNotNull(dateTimeFieldType3);
        org.junit.Assert.assertNotNull(dateTimeComparator4);
        org.junit.Assert.assertNull(dateTimeFieldType5);
    }

    @Test
    public void test0219() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0219");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.PeriodType periodType4 = null;
        org.joda.time.Chronology chronology5 = null;
        org.joda.time.Period period6 = new org.joda.time.Period((long) (short) 10, periodType4, chronology5);
        org.joda.time.Months months7 = org.joda.time.Months.FIVE;
        org.joda.time.Period period8 = period6.minus((org.joda.time.ReadablePeriod) months7);
        org.joda.time.Chronology chronology10 = null;
        org.joda.time.DateMidnight dateMidnight11 = new org.joda.time.DateMidnight((long) (short) -1, chronology10);
        org.joda.time.DateMidnight dateMidnight13 = dateMidnight11.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology15 = null;
        org.joda.time.DateMidnight dateMidnight16 = new org.joda.time.DateMidnight((long) (short) -1, chronology15);
        org.joda.time.DateMidnight dateMidnight18 = dateMidnight16.minusMonths((int) (byte) 100);
        int int19 = dateMidnight13.compareTo((org.joda.time.ReadableInstant) dateMidnight16);
        org.joda.time.Duration duration20 = period8.toDurationTo((org.joda.time.ReadableInstant) dateMidnight16);
        org.joda.time.Duration duration21 = duration20.negated();
        org.joda.time.DateTime dateTime22 = dateTime0.minus((org.joda.time.ReadableDuration) duration21);
        org.joda.time.DateTime dateTime24 = dateTime22.minus((long) (short) 1);
        org.joda.time.DateTime.Property property25 = dateTime24.minuteOfHour();
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(months7);
        org.junit.Assert.assertNotNull(period8);
        org.junit.Assert.assertNotNull(dateMidnight13);
        org.junit.Assert.assertNotNull(dateMidnight18);
        org.junit.Assert.assertTrue("'" + int19 + "' != '" + (-1) + "'", int19 == (-1));
        org.junit.Assert.assertNotNull(duration20);
        org.junit.Assert.assertNotNull(duration21);
        org.junit.Assert.assertNotNull(dateTime22);
        org.junit.Assert.assertNotNull(dateTime24);
        org.junit.Assert.assertNotNull(property25);
    }

    @Test
    public void test0220() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0220");
        org.joda.time.tz.UTCProvider uTCProvider0 = new org.joda.time.tz.UTCProvider();
        org.joda.time.DateTimeZone dateTimeZone2 = uTCProvider0.getZone("");
        org.junit.Assert.assertNull(dateTimeZone2);
    }

    @Test
    public void test0221() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0221");
        long long2 = org.joda.time.field.FieldUtils.safeMultiply(0L, (-59011462664000L));
        org.junit.Assert.assertTrue("'" + long2 + "' != '" + 0L + "'", long2 == 0L);
    }

    @Test
    public void test0222() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0222");
        java.lang.ClassLoader classLoader1 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.tz.ZoneInfoProvider zoneInfoProvider2 = new org.joda.time.tz.ZoneInfoProvider("+00:00:00.100", classLoader1);
            org.junit.Assert.fail("Expected exception of type java.io.IOException; message: Resource not found: \"+00:00:00.100/ZoneInfoMap\" ClassLoader: system");
        } catch (java.io.IOException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0223() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0223");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology11 = null;
        org.joda.time.DateMidnight dateMidnight12 = new org.joda.time.DateMidnight((long) (short) -1, chronology11);
        org.joda.time.DateMidnight dateMidnight14 = dateMidnight12.minusMonths((int) (byte) 100);
        int int15 = dateMidnight9.compareTo((org.joda.time.ReadableInstant) dateMidnight12);
        org.joda.time.Minutes minutes16 = org.joda.time.Minutes.minutesBetween((org.joda.time.ReadableInstant) dateMidnight2, (org.joda.time.ReadableInstant) dateMidnight9);
        org.joda.time.DateMidnight dateMidnight19 = dateMidnight9.withDurationAdded((long) '4', 100);
        org.joda.time.Chronology chronology21 = null;
        org.joda.time.DateMidnight dateMidnight22 = new org.joda.time.DateMidnight((long) (short) -1, chronology21);
        org.joda.time.DateMidnight dateMidnight24 = dateMidnight22.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight27 = dateMidnight22.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar28 = dateMidnight27.toGregorianCalendar();
        org.joda.time.MutableInterval mutableInterval29 = new org.joda.time.MutableInterval((org.joda.time.ReadableInstant) dateMidnight9, (org.joda.time.ReadableInstant) dateMidnight27);
        org.joda.time.DateMidnight.Property property30 = dateMidnight9.weekyear();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(dateMidnight14);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + (-1) + "'", int15 == (-1));
        org.junit.Assert.assertNotNull(minutes16);
        org.junit.Assert.assertNotNull(dateMidnight19);
        org.junit.Assert.assertNotNull(dateMidnight24);
        org.junit.Assert.assertNotNull(dateMidnight27);
        org.junit.Assert.assertNotNull(gregorianCalendar28);
        org.junit.Assert.assertNotNull(property30);
    }

    @Test
    public void test0224() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0224");
        int int0 = org.joda.time.DateTimeConstants.TUESDAY;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 2 + "'", int0 == 2);
    }

    @Test
    public void test0225() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0225");
        org.joda.time.MutablePeriod mutablePeriod8 = new org.joda.time.MutablePeriod((int) (byte) 1, (int) (short) 0, (int) (byte) -1, (int) (short) -1, (int) (byte) 1, 0, 100, 3);
        org.joda.time.ReadableInterval readableInterval9 = null;
        org.joda.time.Seconds seconds10 = org.joda.time.Seconds.secondsIn(readableInterval9);
        org.joda.time.DurationFieldType durationFieldType11 = seconds10.getFieldType();
        org.joda.time.field.PreciseDurationField preciseDurationField13 = new org.joda.time.field.PreciseDurationField(durationFieldType11, (long) 59);
        org.joda.time.ReadableInterval readableInterval14 = null;
        org.joda.time.Seconds seconds15 = org.joda.time.Seconds.secondsIn(readableInterval14);
        org.joda.time.DurationFieldType durationFieldType16 = seconds15.getFieldType();
        org.joda.time.field.PreciseDurationField preciseDurationField18 = new org.joda.time.field.PreciseDurationField(durationFieldType16, (long) 59);
        int int19 = preciseDurationField13.compareTo((org.joda.time.DurationField) preciseDurationField18);
        org.joda.time.DurationFieldType durationFieldType20 = preciseDurationField13.getType();
        boolean boolean21 = mutablePeriod8.isSupported(durationFieldType20);
        org.junit.Assert.assertNotNull(seconds10);
        org.junit.Assert.assertNotNull(durationFieldType11);
        org.junit.Assert.assertNotNull(seconds15);
        org.junit.Assert.assertNotNull(durationFieldType16);
        org.junit.Assert.assertTrue("'" + int19 + "' != '" + 0 + "'", int19 == 0);
        org.junit.Assert.assertNotNull(durationFieldType20);
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + true + "'", boolean21 == true);
    }

    @Test
    public void test0226() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0226");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.withYear(100);
        org.joda.time.Chronology chronology11 = null;
        org.joda.time.DateMidnight dateMidnight12 = new org.joda.time.DateMidnight((long) (short) -1, chronology11);
        org.joda.time.Interval interval13 = new org.joda.time.Interval((org.joda.time.ReadableInstant) dateMidnight9, (org.joda.time.ReadableInstant) dateMidnight12);
        org.joda.time.Period period15 = org.joda.time.Period.weeks((int) (short) 100);
        org.joda.time.Interval interval16 = interval13.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) period15);
        org.joda.time.ReadableInterval readableInterval17 = null;
        boolean boolean18 = interval13.abuts(readableInterval17);
        long long19 = interval13.getStartMillis();
        org.joda.time.Chronology chronology21 = null;
        org.joda.time.DateMidnight dateMidnight22 = new org.joda.time.DateMidnight((long) (short) -1, chronology21);
        org.joda.time.DateMidnight dateMidnight24 = dateMidnight22.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology26 = null;
        org.joda.time.DateMidnight dateMidnight27 = new org.joda.time.DateMidnight((long) (short) -1, chronology26);
        org.joda.time.DateMidnight dateMidnight29 = dateMidnight27.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology31 = null;
        org.joda.time.DateMidnight dateMidnight32 = new org.joda.time.DateMidnight((long) (short) -1, chronology31);
        org.joda.time.DateMidnight dateMidnight34 = dateMidnight32.minusMonths((int) (byte) 100);
        int int35 = dateMidnight29.compareTo((org.joda.time.ReadableInstant) dateMidnight32);
        org.joda.time.Minutes minutes36 = org.joda.time.Minutes.minutesBetween((org.joda.time.ReadableInstant) dateMidnight22, (org.joda.time.ReadableInstant) dateMidnight29);
        org.joda.time.DateMidnight dateMidnight39 = dateMidnight29.withDurationAdded((long) '4', 100);
        org.joda.time.Chronology chronology41 = null;
        org.joda.time.DateMidnight dateMidnight42 = new org.joda.time.DateMidnight((long) (short) -1, chronology41);
        org.joda.time.DateMidnight dateMidnight44 = dateMidnight42.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight47 = dateMidnight42.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar48 = dateMidnight47.toGregorianCalendar();
        org.joda.time.MutableInterval mutableInterval49 = new org.joda.time.MutableInterval((org.joda.time.ReadableInstant) dateMidnight29, (org.joda.time.ReadableInstant) dateMidnight47);
        org.joda.time.Interval interval50 = dateMidnight47.toInterval();
        boolean boolean51 = interval13.isEqual((org.joda.time.ReadableInterval) interval50);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(period15);
        org.junit.Assert.assertNotNull(interval16);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertTrue("'" + long19 + "' != '" + (-59011462664000L) + "'", long19 == (-59011462664000L));
        org.junit.Assert.assertNotNull(dateMidnight24);
        org.junit.Assert.assertNotNull(dateMidnight29);
        org.junit.Assert.assertNotNull(dateMidnight34);
        org.junit.Assert.assertTrue("'" + int35 + "' != '" + (-1) + "'", int35 == (-1));
        org.junit.Assert.assertNotNull(minutes36);
        org.junit.Assert.assertNotNull(dateMidnight39);
        org.junit.Assert.assertNotNull(dateMidnight44);
        org.junit.Assert.assertNotNull(dateMidnight47);
        org.junit.Assert.assertNotNull(gregorianCalendar48);
        org.junit.Assert.assertNotNull(interval50);
        org.junit.Assert.assertTrue("'" + boolean51 + "' != '" + false + "'", boolean51 == false);
    }

    @Test
    public void test0227() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0227");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.YearMonthDay yearMonthDay5 = dateTime0.toYearMonthDay();
        org.joda.time.PeriodType periodType7 = null;
        org.joda.time.Chronology chronology8 = null;
        org.joda.time.Period period9 = new org.joda.time.Period((long) (short) 10, periodType7, chronology8);
        org.joda.time.Months months10 = org.joda.time.Months.FIVE;
        org.joda.time.Period period11 = period9.minus((org.joda.time.ReadablePeriod) months10);
        org.joda.time.Period period13 = period9.plusMillis((-1));
        org.joda.time.Period period14 = period9.toPeriod();
        org.joda.time.Period period16 = period14.minusSeconds((int) (byte) 1);
        org.joda.time.Period period18 = period14.withDays((int) (short) 100);
        org.joda.time.YearMonthDay yearMonthDay19 = yearMonthDay5.minus((org.joda.time.ReadablePeriod) period14);
        org.joda.time.YearMonthDay.Property property20 = yearMonthDay5.monthOfYear();
        org.joda.time.chrono.GregorianChronology gregorianChronology21 = org.joda.time.chrono.GregorianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField22 = gregorianChronology21.minuteOfDay();
        org.joda.time.DateTime dateTime23 = org.joda.time.DateTime.now((org.joda.time.Chronology) gregorianChronology21);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Interval interval24 = new org.joda.time.Interval((java.lang.Object) property20, (org.joda.time.Chronology) gregorianChronology21);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No interval converter found for type: org.joda.time.YearMonthDay$Property");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(yearMonthDay5);
        org.junit.Assert.assertNotNull(months10);
        org.junit.Assert.assertNotNull(period11);
        org.junit.Assert.assertNotNull(period13);
        org.junit.Assert.assertNotNull(period14);
        org.junit.Assert.assertNotNull(period16);
        org.junit.Assert.assertNotNull(period18);
        org.junit.Assert.assertNotNull(yearMonthDay19);
        org.junit.Assert.assertNotNull(property20);
        org.junit.Assert.assertNotNull(gregorianChronology21);
        org.junit.Assert.assertNotNull(dateTimeField22);
        org.junit.Assert.assertNotNull(dateTime23);
    }

    @Test
    public void test0228() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0228");
        java.lang.String str0 = org.joda.time.DateTimeZone.DEFAULT_TZ_DATA_PATH;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "org/joda/time/tz/data" + "'", str0, "org/joda/time/tz/data");
    }

    @Test
    public void test0229() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0229");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone4 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology5 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType6 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology8 = null;
        org.joda.time.DateMidnight dateMidnight9 = new org.joda.time.DateMidnight((long) (short) -1, chronology8);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property12 = dateMidnight9.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime13 = dateMidnight9.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property14 = mutableDateTime13.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime16 = property14.addWrapField((int) (short) -1);
        boolean boolean17 = leapYearPatternType6.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology18 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4, leapYearPatternType6);
        org.joda.time.YearMonthDay yearMonthDay19 = new org.joda.time.YearMonthDay((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        org.joda.time.YearMonthDay yearMonthDay21 = yearMonthDay19.plusYears((int) 'a');
        org.joda.time.DateTimeZone dateTimeZone22 = null;
        org.joda.time.LocalDateTime localDateTime23 = new org.joda.time.LocalDateTime(dateTimeZone22);
        org.joda.time.LocalDateTime localDateTime25 = localDateTime23.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property26 = localDateTime23.weekOfWeekyear();
        org.joda.time.ReadableInterval readableInterval27 = null;
        org.joda.time.Seconds seconds28 = org.joda.time.Seconds.secondsIn(readableInterval27);
        org.joda.time.DurationFieldType durationFieldType29 = seconds28.getFieldType();
        org.joda.time.LocalDateTime localDateTime31 = localDateTime23.withFieldAdded(durationFieldType29, 86400000);
        org.joda.time.LocalDateTime localDateTime33 = localDateTime23.plusYears(1970);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean34 = yearMonthDay19.isEqual((org.joda.time.ReadablePartial) localDateTime23);
            org.junit.Assert.fail("Expected exception of type java.lang.ClassCastException; message: ReadablePartial objects must have matching field types");
        } catch (java.lang.ClassCastException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(copticChronology5);
        org.junit.Assert.assertNotNull(leapYearPatternType6);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(property12);
        org.junit.Assert.assertNotNull(mutableDateTime13);
        org.junit.Assert.assertNotNull(property14);
        org.junit.Assert.assertNotNull(mutableDateTime16);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(islamicChronology18);
        org.junit.Assert.assertNotNull(yearMonthDay21);
        org.junit.Assert.assertNotNull(localDateTime25);
        org.junit.Assert.assertNotNull(property26);
        org.junit.Assert.assertNotNull(seconds28);
        org.junit.Assert.assertNotNull(durationFieldType29);
        org.junit.Assert.assertNotNull(localDateTime31);
        org.junit.Assert.assertNotNull(localDateTime33);
    }

    @Test
    public void test0230() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0230");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Interval interval2 = yearMonth0.toInterval(dateTimeZone1);
        org.joda.time.YearMonth yearMonth4 = yearMonth0.plusYears((int) (byte) 1);
        int int5 = yearMonth0.getMonthOfYear();
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertNotNull(yearMonth4);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 8 + "'", int5 == 8);
    }

    @Test
    public void test0231() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0231");
        org.joda.time.LocalTime localTime1 = new org.joda.time.LocalTime((long) 9);
    }

    @Test
    public void test0232() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0232");
        java.io.DataInput dataInput0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTimeZone dateTimeZone2 = org.joda.time.tz.DateTimeZoneBuilder.readFrom(dataInput0, "monthOfYear");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0233() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0233");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.TimeOfDay timeOfDay2 = new org.joda.time.TimeOfDay(29, 100);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 29 for hourOfDay must not be larger than 23");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0234() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0234");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        org.joda.time.ReadableInterval readableInterval5 = null;
        org.joda.time.Seconds seconds6 = org.joda.time.Seconds.secondsIn(readableInterval5);
        org.joda.time.DurationFieldType durationFieldType7 = seconds6.getFieldType();
        org.joda.time.LocalDateTime localDateTime9 = localDateTime1.withFieldAdded(durationFieldType7, 86400000);
        java.lang.String str10 = localDateTime9.toString();
        boolean boolean12 = localDateTime9.equals((java.lang.Object) (-1.0d));
        org.joda.time.LocalDateTime.Property property13 = localDateTime9.hourOfDay();
        org.joda.time.LocalDateTime.Property property14 = localDateTime9.dayOfMonth();
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(seconds6);
        org.junit.Assert.assertNotNull(durationFieldType7);
        org.junit.Assert.assertNotNull(localDateTime9);
// flaky "5) test0234(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str10 + "' != '" + "2029-05-02T11:10:57.360" + "'", str10, "2029-05-02T11:10:57.360");
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertNotNull(property13);
        org.junit.Assert.assertNotNull(property14);
    }

    @Test
    public void test0235() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0235");
        org.joda.time.DateTimeUtils.setCurrentMillisSystem();
    }

    @Test
    public void test0236() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0236");
        org.joda.time.tz.DateTimeZoneBuilder dateTimeZoneBuilder0 = new org.joda.time.tz.DateTimeZoneBuilder();
        java.io.DataOutput dataOutput2 = null;
        // The following exception was thrown during execution in test generation
        try {
            dateTimeZoneBuilder0.writeTo("[]", dataOutput2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0237() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0237");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone7 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology8 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone7);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType9 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology11 = null;
        org.joda.time.DateMidnight dateMidnight12 = new org.joda.time.DateMidnight((long) (short) -1, chronology11);
        org.joda.time.DateMidnight dateMidnight14 = dateMidnight12.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property15 = dateMidnight12.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime16 = dateMidnight12.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property17 = mutableDateTime16.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime19 = property17.addWrapField((int) (short) -1);
        boolean boolean20 = leapYearPatternType9.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology21 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone7, leapYearPatternType9);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateMidnight dateMidnight22 = new org.joda.time.DateMidnight(3600, 59, (int) (byte) 1, (org.joda.time.DateTimeZone) fixedDateTimeZone7);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 59 for monthOfYear must be in the range [1,12]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(copticChronology8);
        org.junit.Assert.assertNotNull(leapYearPatternType9);
        org.junit.Assert.assertNotNull(dateMidnight14);
        org.junit.Assert.assertNotNull(property15);
        org.junit.Assert.assertNotNull(mutableDateTime16);
        org.junit.Assert.assertNotNull(property17);
        org.junit.Assert.assertNotNull(mutableDateTime19);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
        org.junit.Assert.assertNotNull(islamicChronology21);
    }

    @Test
    public void test0238() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0238");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusYears(100);
        org.joda.time.chrono.JulianChronology julianChronology5 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField6 = julianChronology5.clockhourOfDay();
        org.joda.time.Chronology chronology7 = julianChronology5.withUTC();
        org.joda.time.DateMidnight dateMidnight8 = dateMidnight2.withChronology((org.joda.time.Chronology) julianChronology5);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(julianChronology5);
        org.junit.Assert.assertNotNull(dateTimeField6);
        org.junit.Assert.assertNotNull(chronology7);
        org.junit.Assert.assertNotNull(dateMidnight8);
    }

    @Test
    public void test0239() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0239");
        org.joda.time.IllegalFieldValueException illegalFieldValueException2 = new org.joda.time.IllegalFieldValueException("PT60S", "CopticChronology[2026-08-06T11:10:36.726]");
        java.lang.String str3 = illegalFieldValueException2.toString();
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "org.joda.time.IllegalFieldValueException: Value \"CopticChronology[2026-08-06T11:10:36.726]\" for PT60S is not supported" + "'", str3, "org.joda.time.IllegalFieldValueException: Value \"CopticChronology[2026-08-06T11:10:36.726]\" for PT60S is not supported");
    }

    @Test
    public void test0240() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0240");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTimeZone dateTimeZone1 = org.joda.time.DateTimeZone.forID("2029-05-02T11:10:41.857");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The datetime zone id '2029-05-02T11:10:41.857' is not recognised");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0241() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0241");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        org.joda.time.LocalDateTime localDateTime5 = property4.roundHalfFloorCopy();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDateTime localDateTime9 = localDateTime5.withDate(12, 3600, 4);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 3600 for monthOfYear must be in the range [1,12]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(localDateTime5);
    }

    @Test
    public void test0242() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0242");
        org.joda.time.DateTimeComparator dateTimeComparator0 = org.joda.time.DateTimeComparator.getDateOnlyInstance();
        org.joda.time.DateTimeComparator dateTimeComparator1 = org.joda.time.DateTimeComparator.getTimeOnlyInstance();
        java.util.Comparator<java.lang.Object> objComparator2 = dateTimeComparator0.thenComparing((java.util.Comparator<java.lang.Object>) dateTimeComparator1);
        org.joda.time.DateTimeFieldType dateTimeFieldType3 = dateTimeComparator1.getUpperLimit();
        org.joda.time.Chronology chronology5 = null;
        org.joda.time.DateMidnight dateMidnight6 = new org.joda.time.DateMidnight((long) (short) -1, chronology5);
        org.joda.time.DateMidnight dateMidnight8 = dateMidnight6.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property9 = dateMidnight6.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime10 = dateMidnight6.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property11 = mutableDateTime10.centuryOfEra();
        mutableDateTime10.setSecondOfMinute(0);
        org.joda.time.Chronology chronology15 = null;
        org.joda.time.DateMidnight dateMidnight16 = new org.joda.time.DateMidnight((long) (short) -1, chronology15);
        org.joda.time.DateMidnight dateMidnight18 = dateMidnight16.minusMonths((int) (byte) 100);
        org.joda.time.Months months19 = org.joda.time.Months.monthsBetween((org.joda.time.ReadableInstant) mutableDateTime10, (org.joda.time.ReadableInstant) dateMidnight16);
        org.joda.time.MutableDateTime.Property property20 = mutableDateTime10.millisOfSecond();
        boolean boolean21 = dateTimeComparator1.equals((java.lang.Object) property20);
        org.junit.Assert.assertNotNull(dateTimeComparator0);
        org.junit.Assert.assertNotNull(dateTimeComparator1);
        org.junit.Assert.assertNotNull(objComparator2);
        org.junit.Assert.assertNotNull(dateTimeFieldType3);
        org.junit.Assert.assertNotNull(dateMidnight8);
        org.junit.Assert.assertNotNull(property9);
        org.junit.Assert.assertNotNull(mutableDateTime10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(dateMidnight18);
        org.junit.Assert.assertNotNull(months19);
        org.junit.Assert.assertNotNull(property20);
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + false + "'", boolean21 == false);
    }

    @Test
    public void test0243() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0243");
        org.joda.time.Instant instant0 = org.joda.time.Instant.EPOCH;
        org.joda.time.Instant instant3 = instant0.withDurationAdded((-1L), 100);
        org.joda.time.Instant instant6 = instant0.withDurationAdded((long) (short) 10, 53);
        org.junit.Assert.assertNotNull(instant0);
        org.junit.Assert.assertNotNull(instant3);
        org.junit.Assert.assertNotNull(instant6);
    }

    @Test
    public void test0244() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0244");
        org.joda.time.Years years1 = org.joda.time.Years.years((int) (byte) 10);
        org.joda.time.Years years3 = years1.dividedBy(1);
        org.joda.time.DateTime dateTime4 = new org.joda.time.DateTime();
        boolean boolean5 = years1.equals((java.lang.Object) dateTime4);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Hours hours6 = org.joda.time.Hours.standardHoursIn((org.joda.time.ReadablePeriod) years1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Cannot convert period to duration as years is not precise in the period P10Y");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(years1);
        org.junit.Assert.assertNotNull(years3);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test0245() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0245");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar8 = dateMidnight7.toGregorianCalendar();
        org.joda.time.LocalTime localTime9 = org.joda.time.LocalTime.fromCalendarFields((java.util.Calendar) gregorianCalendar8);
        org.joda.time.LocalTime.Property property10 = localTime9.minuteOfHour();
        org.joda.time.LocalTime.Property property11 = localTime9.millisOfSecond();
        org.joda.time.LocalTime localTime13 = localTime9.withSecondOfMinute(9);
        org.joda.time.DateTimeComparator dateTimeComparator14 = org.joda.time.DateTimeComparator.getDateOnlyInstance();
        org.joda.time.DateTimeComparator dateTimeComparator15 = org.joda.time.DateTimeComparator.getTimeOnlyInstance();
        java.util.Comparator<java.lang.Object> objComparator16 = dateTimeComparator14.thenComparing((java.util.Comparator<java.lang.Object>) dateTimeComparator15);
        org.joda.time.DateTimeFieldType dateTimeFieldType17 = dateTimeComparator15.getUpperLimit();
        org.joda.time.DateTimeComparator dateTimeComparator18 = org.joda.time.DateTimeComparator.getInstance(dateTimeFieldType17);
        // The following exception was thrown during execution in test generation
        try {
            int int19 = localTime13.get(dateTimeFieldType17);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Field 'dayOfYear' is not supported");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(gregorianCalendar8);
        org.junit.Assert.assertNotNull(localTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(localTime13);
        org.junit.Assert.assertNotNull(dateTimeComparator14);
        org.junit.Assert.assertNotNull(dateTimeComparator15);
        org.junit.Assert.assertNotNull(objComparator16);
        org.junit.Assert.assertNotNull(dateTimeFieldType17);
        org.junit.Assert.assertNotNull(dateTimeComparator18);
    }

    @Test
    public void test0246() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0246");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone4 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology5 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType6 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology8 = null;
        org.joda.time.DateMidnight dateMidnight9 = new org.joda.time.DateMidnight((long) (short) -1, chronology8);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property12 = dateMidnight9.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime13 = dateMidnight9.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property14 = mutableDateTime13.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime16 = property14.addWrapField((int) (short) -1);
        boolean boolean17 = leapYearPatternType6.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology18 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4, leapYearPatternType6);
        org.joda.time.MonthDay monthDay19 = new org.joda.time.MonthDay((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        org.joda.time.PeriodType periodType21 = null;
        org.joda.time.Chronology chronology22 = null;
        org.joda.time.Period period23 = new org.joda.time.Period((long) (short) 10, periodType21, chronology22);
        org.joda.time.Months months24 = org.joda.time.Months.FIVE;
        org.joda.time.Period period25 = period23.minus((org.joda.time.ReadablePeriod) months24);
        org.joda.time.Chronology chronology27 = null;
        org.joda.time.DateMidnight dateMidnight28 = new org.joda.time.DateMidnight((long) (short) -1, chronology27);
        org.joda.time.DateMidnight dateMidnight30 = dateMidnight28.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology32 = null;
        org.joda.time.DateMidnight dateMidnight33 = new org.joda.time.DateMidnight((long) (short) -1, chronology32);
        org.joda.time.DateMidnight dateMidnight35 = dateMidnight33.minusMonths((int) (byte) 100);
        int int36 = dateMidnight30.compareTo((org.joda.time.ReadableInstant) dateMidnight33);
        org.joda.time.Duration duration37 = period25.toDurationTo((org.joda.time.ReadableInstant) dateMidnight33);
        org.joda.time.MonthDay monthDay38 = monthDay19.plus((org.joda.time.ReadablePeriod) period25);
        org.joda.time.MonthDay monthDay40 = monthDay19.plusDays((int) (byte) -1);
        org.junit.Assert.assertNotNull(copticChronology5);
        org.junit.Assert.assertNotNull(leapYearPatternType6);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(property12);
        org.junit.Assert.assertNotNull(mutableDateTime13);
        org.junit.Assert.assertNotNull(property14);
        org.junit.Assert.assertNotNull(mutableDateTime16);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(islamicChronology18);
        org.junit.Assert.assertNotNull(months24);
        org.junit.Assert.assertNotNull(period25);
        org.junit.Assert.assertNotNull(dateMidnight30);
        org.junit.Assert.assertNotNull(dateMidnight35);
        org.junit.Assert.assertTrue("'" + int36 + "' != '" + (-1) + "'", int36 == (-1));
        org.junit.Assert.assertNotNull(duration37);
        org.junit.Assert.assertNotNull(monthDay38);
        org.junit.Assert.assertNotNull(monthDay40);
    }

    @Test
    public void test0247() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0247");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        int int2 = localDateTime1.getYear();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDateTime localDateTime4 = localDateTime1.withMinuteOfHour(40256602);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 40256602 for minuteOfHour must be in the range [0,59]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 2026 + "'", int2 == 2026);
    }

    @Test
    public void test0248() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0248");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTime.Property property1 = dateTime0.millisOfDay();
        org.joda.time.DateTime dateTime2 = property1.roundHalfFloorCopy();
        org.joda.time.DateTimeField dateTimeField3 = property1.getField();
        org.joda.time.DateTime dateTime4 = property1.roundHalfEvenCopy();
        int int5 = property1.getMinimumValue();
        org.junit.Assert.assertNotNull(property1);
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTimeField3);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 0 + "'", int5 == 0);
    }

    @Test
    public void test0249() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0249");
        org.joda.time.PeriodType periodType0 = org.joda.time.PeriodType.yearDayTime();
        org.junit.Assert.assertNotNull(periodType0);
    }

    @Test
    public void test0250() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0250");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = localDate2.withWeekyear((int) (byte) 100);
        org.joda.time.Chronology chronology7 = null;
        org.joda.time.DateMidnight dateMidnight8 = new org.joda.time.DateMidnight((long) (short) -1, chronology7);
        org.joda.time.DateMidnight dateMidnight10 = dateMidnight8.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property11 = dateMidnight8.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime12 = dateMidnight8.toMutableDateTimeISO();
        boolean boolean13 = localDate5.equals((java.lang.Object) mutableDateTime12);
        org.joda.time.LocalDate localDate15 = localDate5.plusMonths(10);
        int int16 = localDate5.getWeekOfWeekyear();
        org.joda.time.LocalDate.Property property17 = localDate5.centuryOfEra();
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(dateMidnight10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(mutableDateTime12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(localDate15);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 51 + "'", int16 == 51);
        org.junit.Assert.assertNotNull(property17);
    }

    @Test
    public void test0251() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0251");
        org.joda.time.PeriodType periodType0 = org.joda.time.PeriodType.days();
        org.junit.Assert.assertNotNull(periodType0);
    }

    @Test
    public void test0252() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0252");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.dateParser();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MutableDateTime mutableDateTime2 = dateTimeFormatter0.parseMutableDateTime("1 year, -1 weeks, -1 days, 1 hour, 100 seconds and 3 milliseconds");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid format: \"1 year, -1 weeks, -1 days, 1 hour...\" is malformed at \" year, -1 weeks, -1 days, 1 hour...\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
    }

    @Test
    public void test0253() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0253");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.DurationField durationField3 = gJChronology1.months();
        org.joda.time.DateMidnight dateMidnight5 = org.joda.time.DateMidnight.parse("2026-08-06T11:10:36.726");
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight5.minusYears((int) (byte) 100);
        org.joda.time.ReadableDateTime readableDateTime8 = null;
        org.joda.time.chrono.LimitChronology limitChronology9 = org.joda.time.chrono.LimitChronology.getInstance((org.joda.time.Chronology) gJChronology1, (org.joda.time.ReadableDateTime) dateMidnight5, readableDateTime8);
        // The following exception was thrown during execution in test generation
        try {
            long long15 = limitChronology9.getDateTimeMillis(0L, 40243947, (int) (short) 100, 12, (int) (short) 100);
            org.junit.Assert.fail("Expected exception of type org.joda.time.chrono.LimitChronology.LimitException; message: The instant is below the supported minimum of 2026-08-06T00:00:00.000+02:00 (GJChronology[Europe/Prague])");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(durationField3);
        org.junit.Assert.assertNotNull(dateMidnight5);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(limitChronology9);
    }

    @Test
    public void test0254() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0254");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.YearMonthDay yearMonthDay5 = dateTime0.toYearMonthDay();
        java.util.Locale locale6 = null;
        java.util.Calendar calendar7 = dateTime0.toCalendar(locale6);
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(yearMonthDay5);
        org.junit.Assert.assertNotNull(calendar7);
// flaky "6) test0254(RegressionTest0)":         org.junit.Assert.assertEquals(calendar7.toString(), "java.util.GregorianCalendar[time=1786007458861,areFieldsSet=true,areAllFieldsSet=true,lenient=true,zone=sun.util.calendar.ZoneInfo[id=\"Europe/Prague\",offset=3600000,dstSavings=3600000,useDaylight=true,transitions=143,lastRule=java.util.SimpleTimeZone[id=Europe/Prague,offset=3600000,dstSavings=3600000,useDaylight=true,startYear=0,startMode=2,startMonth=2,startDay=-1,startDayOfWeek=1,startTime=3600000,startTimeMode=2,endMode=2,endMonth=9,endDay=-1,endDayOfWeek=1,endTime=3600000,endTimeMode=2]],firstDayOfWeek=1,minimalDaysInFirstWeek=1,ERA=1,YEAR=2026,MONTH=7,WEEK_OF_YEAR=32,WEEK_OF_MONTH=2,DAY_OF_MONTH=6,DAY_OF_YEAR=218,DAY_OF_WEEK=5,DAY_OF_WEEK_IN_MONTH=1,AM_PM=0,HOUR=11,HOUR_OF_DAY=11,MINUTE=10,SECOND=58,MILLISECOND=861,ZONE_OFFSET=3600000,DST_OFFSET=3600000]");
    }

    @Test
    public void test0255() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0255");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        mutableDateTime6.setSecondOfMinute(0);
        org.joda.time.DateTimeField dateTimeField10 = mutableDateTime6.getRoundingField();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNull(dateTimeField10);
    }

    @Test
    public void test0256() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0256");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        int int10 = dateMidnight4.compareTo((org.joda.time.ReadableInstant) dateMidnight7);
        org.joda.time.format.DateTimeFormatter dateTimeFormatter11 = org.joda.time.format.ISODateTimeFormat.basicWeekDateTime();
        java.lang.String str12 = dateMidnight4.toString(dateTimeFormatter11);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + (-1) + "'", int10 == (-1));
        org.junit.Assert.assertNotNull(dateTimeFormatter11);
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "1961W355T000000.000+0100" + "'", str12, "1961W355T000000.000+0100");
    }

    @Test
    public void test0257() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0257");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.dateHourMinuteSecond();
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
    }

    @Test
    public void test0258() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0258");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.YearMonthDay yearMonthDay5 = dateTime0.toYearMonthDay();
        org.joda.time.PeriodType periodType7 = null;
        org.joda.time.Chronology chronology8 = null;
        org.joda.time.Period period9 = new org.joda.time.Period((long) (short) 10, periodType7, chronology8);
        org.joda.time.Months months10 = org.joda.time.Months.FIVE;
        org.joda.time.Period period11 = period9.minus((org.joda.time.ReadablePeriod) months10);
        org.joda.time.Period period13 = period9.plusMillis((-1));
        org.joda.time.Period period14 = period9.toPeriod();
        org.joda.time.Period period16 = period14.minusSeconds((int) (byte) 1);
        org.joda.time.Period period18 = period14.withDays((int) (short) 100);
        org.joda.time.YearMonthDay yearMonthDay19 = yearMonthDay5.minus((org.joda.time.ReadablePeriod) period14);
        org.joda.time.YearMonthDay.Property property20 = yearMonthDay5.monthOfYear();
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray21 = yearMonthDay5.getFieldTypes();
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(yearMonthDay5);
        org.junit.Assert.assertNotNull(months10);
        org.junit.Assert.assertNotNull(period11);
        org.junit.Assert.assertNotNull(period13);
        org.junit.Assert.assertNotNull(period14);
        org.junit.Assert.assertNotNull(period16);
        org.junit.Assert.assertNotNull(period18);
        org.junit.Assert.assertNotNull(yearMonthDay19);
        org.junit.Assert.assertNotNull(property20);
        org.junit.Assert.assertNotNull(dateTimeFieldTypeArray21);
    }

    @Test
    public void test0259() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0259");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone4 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology5 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType6 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology8 = null;
        org.joda.time.DateMidnight dateMidnight9 = new org.joda.time.DateMidnight((long) (short) -1, chronology8);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property12 = dateMidnight9.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime13 = dateMidnight9.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property14 = mutableDateTime13.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime16 = property14.addWrapField((int) (short) -1);
        boolean boolean17 = leapYearPatternType6.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology18 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4, leapYearPatternType6);
        org.joda.time.MonthDay monthDay19 = new org.joda.time.MonthDay((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        org.joda.time.DateTimeZone dateTimeZone20 = null;
        org.joda.time.LocalDateTime localDateTime21 = new org.joda.time.LocalDateTime(dateTimeZone20);
        org.joda.time.LocalDateTime localDateTime23 = localDateTime21.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property24 = localDateTime21.weekOfWeekyear();
        org.joda.time.ReadableInterval readableInterval25 = null;
        org.joda.time.Seconds seconds26 = org.joda.time.Seconds.secondsIn(readableInterval25);
        org.joda.time.DurationFieldType durationFieldType27 = seconds26.getFieldType();
        org.joda.time.LocalDateTime localDateTime29 = localDateTime21.withFieldAdded(durationFieldType27, 86400000);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MonthDay monthDay31 = monthDay19.withFieldAdded(durationFieldType27, 10);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Field 'seconds' is not supported");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(copticChronology5);
        org.junit.Assert.assertNotNull(leapYearPatternType6);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(property12);
        org.junit.Assert.assertNotNull(mutableDateTime13);
        org.junit.Assert.assertNotNull(property14);
        org.junit.Assert.assertNotNull(mutableDateTime16);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(islamicChronology18);
        org.junit.Assert.assertNotNull(localDateTime23);
        org.junit.Assert.assertNotNull(property24);
        org.junit.Assert.assertNotNull(seconds26);
        org.junit.Assert.assertNotNull(durationFieldType27);
        org.junit.Assert.assertNotNull(localDateTime29);
    }

    @Test
    public void test0260() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0260");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.MutableDateTime mutableDateTime3 = dateMidnight2.toMutableDateTime();
        org.joda.time.PeriodType periodType5 = null;
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.Period period7 = new org.joda.time.Period((long) (short) 10, periodType5, chronology6);
        org.joda.time.Months months8 = org.joda.time.Months.FIVE;
        org.joda.time.Period period9 = period7.minus((org.joda.time.ReadablePeriod) months8);
        org.joda.time.Chronology chronology11 = null;
        org.joda.time.DateMidnight dateMidnight12 = new org.joda.time.DateMidnight((long) (short) -1, chronology11);
        org.joda.time.DateMidnight dateMidnight14 = dateMidnight12.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology16 = null;
        org.joda.time.DateMidnight dateMidnight17 = new org.joda.time.DateMidnight((long) (short) -1, chronology16);
        org.joda.time.DateMidnight dateMidnight19 = dateMidnight17.minusMonths((int) (byte) 100);
        int int20 = dateMidnight14.compareTo((org.joda.time.ReadableInstant) dateMidnight17);
        org.joda.time.Duration duration21 = period9.toDurationTo((org.joda.time.ReadableInstant) dateMidnight17);
        org.joda.time.DateMidnight dateMidnight22 = dateMidnight2.minus((org.joda.time.ReadableDuration) duration21);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateMidnight dateMidnight24 = dateMidnight22.withYearOfEra((int) (byte) -1);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value -1 for yearOfEra must be in the range [1,292278993]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(mutableDateTime3);
        org.junit.Assert.assertNotNull(months8);
        org.junit.Assert.assertNotNull(period9);
        org.junit.Assert.assertNotNull(dateMidnight14);
        org.junit.Assert.assertNotNull(dateMidnight19);
        org.junit.Assert.assertTrue("'" + int20 + "' != '" + (-1) + "'", int20 == (-1));
        org.junit.Assert.assertNotNull(duration21);
        org.junit.Assert.assertNotNull(dateMidnight22);
    }

    @Test
    public void test0261() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0261");
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
        long long48 = instant0.getMillis();
        org.junit.Assert.assertNotNull(instant0);
        org.junit.Assert.assertNotNull(instant3);
        org.junit.Assert.assertNotNull(dateMidnight8);
        org.junit.Assert.assertNotNull(property9);
        org.junit.Assert.assertNotNull(mutableDateTime10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(months14);
        org.junit.Assert.assertNotNull(dateMidnight19);
        org.junit.Assert.assertNotNull(dateMidnight22);
        org.junit.Assert.assertNotNull(dateMidnight24);
        org.junit.Assert.assertNotNull(months29);
        org.junit.Assert.assertNotNull(period30);
        org.junit.Assert.assertNotNull(dateMidnight35);
        org.junit.Assert.assertNotNull(dateMidnight40);
        org.junit.Assert.assertTrue("'" + int41 + "' != '" + (-1) + "'", int41 == (-1));
        org.junit.Assert.assertNotNull(duration42);
        org.junit.Assert.assertNotNull(duration43);
        org.junit.Assert.assertNotNull(periodType44);
        org.junit.Assert.assertNotNull(periodType45);
        org.junit.Assert.assertTrue("'" + long48 + "' != '" + 0L + "'", long48 == 0L);
    }

    @Test
    public void test0262() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0262");
        org.joda.time.Years years1 = org.joda.time.Years.years((int) (byte) 10);
        org.joda.time.Years years3 = years1.dividedBy(1);
        org.joda.time.DateTime dateTime4 = new org.joda.time.DateTime();
        boolean boolean5 = years1.equals((java.lang.Object) dateTime4);
        org.joda.time.DateMidnight dateMidnight6 = dateTime4.toDateMidnight();
        org.joda.time.chrono.GJChronology gJChronology8 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay9 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology8);
        org.joda.time.DurationField durationField10 = gJChronology8.months();
        org.joda.time.DateMidnight dateMidnight12 = org.joda.time.DateMidnight.parse("2026-08-06T11:10:36.726");
        org.joda.time.DateMidnight dateMidnight14 = dateMidnight12.minusYears((int) (byte) 100);
        org.joda.time.ReadableDateTime readableDateTime15 = null;
        org.joda.time.chrono.LimitChronology limitChronology16 = org.joda.time.chrono.LimitChronology.getInstance((org.joda.time.Chronology) gJChronology8, (org.joda.time.ReadableDateTime) dateMidnight12, readableDateTime15);
        org.joda.time.DateMidnight dateMidnight17 = dateMidnight6.withChronology((org.joda.time.Chronology) limitChronology16);
        org.junit.Assert.assertNotNull(years1);
        org.junit.Assert.assertNotNull(years3);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(dateMidnight6);
        org.junit.Assert.assertNotNull(gJChronology8);
        org.junit.Assert.assertNotNull(durationField10);
        org.junit.Assert.assertNotNull(dateMidnight12);
        org.junit.Assert.assertNotNull(dateMidnight14);
        org.junit.Assert.assertNotNull(limitChronology16);
        org.junit.Assert.assertNotNull(dateMidnight17);
    }

    @Test
    public void test0263() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0263");
        org.joda.time.Chronology chronology3 = null;
        org.joda.time.DateMidnight dateMidnight4 = new org.joda.time.DateMidnight((long) (short) -1, chronology3);
        org.joda.time.DateMidnight dateMidnight6 = dateMidnight4.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight4.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.withYear(100);
        org.joda.time.PeriodType periodType13 = null;
        org.joda.time.Chronology chronology14 = null;
        org.joda.time.Period period15 = new org.joda.time.Period((long) (short) 10, periodType13, chronology14);
        org.joda.time.Months months16 = org.joda.time.Months.FIVE;
        org.joda.time.Period period17 = period15.minus((org.joda.time.ReadablePeriod) months16);
        org.joda.time.Chronology chronology19 = null;
        org.joda.time.DateMidnight dateMidnight20 = new org.joda.time.DateMidnight((long) (short) -1, chronology19);
        org.joda.time.DateMidnight dateMidnight22 = dateMidnight20.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology24 = null;
        org.joda.time.DateMidnight dateMidnight25 = new org.joda.time.DateMidnight((long) (short) -1, chronology24);
        org.joda.time.DateMidnight dateMidnight27 = dateMidnight25.minusMonths((int) (byte) 100);
        int int28 = dateMidnight22.compareTo((org.joda.time.ReadableInstant) dateMidnight25);
        org.joda.time.Duration duration29 = period17.toDurationTo((org.joda.time.ReadableInstant) dateMidnight25);
        org.joda.time.Duration duration30 = duration29.negated();
        org.joda.time.PeriodType periodType31 = org.joda.time.PeriodType.yearDay();
        org.joda.time.PeriodType periodType32 = periodType31.withSecondsRemoved();
        org.joda.time.MutablePeriod mutablePeriod33 = new org.joda.time.MutablePeriod((org.joda.time.ReadableInstant) dateMidnight9, (org.joda.time.ReadableDuration) duration29, periodType32);
        org.joda.time.chrono.GregorianChronology gregorianChronology34 = org.joda.time.chrono.GregorianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField35 = gregorianChronology34.minuteOfDay();
        org.joda.time.DateTime dateTime36 = org.joda.time.DateTime.now((org.joda.time.Chronology) gregorianChronology34);
        org.joda.time.MutablePeriod mutablePeriod37 = new org.joda.time.MutablePeriod((-59011462664000L), (long) 1, periodType32, (org.joda.time.Chronology) gregorianChronology34);
        mutablePeriod37.add((long) (byte) 100);
        org.junit.Assert.assertNotNull(dateMidnight6);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(months16);
        org.junit.Assert.assertNotNull(period17);
        org.junit.Assert.assertNotNull(dateMidnight22);
        org.junit.Assert.assertNotNull(dateMidnight27);
        org.junit.Assert.assertTrue("'" + int28 + "' != '" + (-1) + "'", int28 == (-1));
        org.junit.Assert.assertNotNull(duration29);
        org.junit.Assert.assertNotNull(duration30);
        org.junit.Assert.assertNotNull(periodType31);
        org.junit.Assert.assertNotNull(periodType32);
        org.junit.Assert.assertNotNull(gregorianChronology34);
        org.junit.Assert.assertNotNull(dateTimeField35);
        org.junit.Assert.assertNotNull(dateTime36);
    }

    @Test
    public void test0264() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0264");
        org.joda.time.Weeks weeks0 = org.joda.time.Weeks.MIN_VALUE;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Days days1 = weeks0.toStandardDays();
            org.junit.Assert.fail("Expected exception of type java.lang.ArithmeticException; message: Multiplication overflows an int: -2147483648 * 7");
        } catch (java.lang.ArithmeticException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(weeks0);
    }

    @Test
    public void test0265() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0265");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        mutableDateTime6.setSecondOfMinute(0);
        mutableDateTime6.add((long) 86400000);
        // The following exception was thrown during execution in test generation
        try {
            mutableDateTime6.setDayOfMonth(70);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 70 for dayOfMonth must be in the range [1,31]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
    }

    @Test
    public void test0266() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0266");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.MonthDay monthDay4 = monthDay2.minusDays((int) '4');
        org.joda.time.MonthDay.Property property5 = monthDay2.monthOfYear();
        java.lang.String str6 = property5.getName();
        java.lang.String str7 = property5.getAsText();
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(monthDay4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "monthOfYear" + "'", str6, "monthOfYear");
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "January" + "'", str7, "January");
    }

    @Test
    public void test0267() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0267");
        org.joda.time.IllegalFieldValueException illegalFieldValueException2 = new org.joda.time.IllegalFieldValueException("org.joda.time.IllegalFieldValueException: Value \"CopticChronology[2026-08-06T11:10:36.726]\" for PT60S is not supported", "");
    }

    @Test
    public void test0268() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0268");
        org.joda.time.PeriodType periodType1 = org.joda.time.PeriodType.millis();
        org.joda.time.PeriodType periodType2 = periodType1.withWeeksRemoved();
        org.joda.time.MutablePeriod mutablePeriod3 = new org.joda.time.MutablePeriod((long) (byte) 10, periodType2);
        org.joda.time.PeriodType periodType4 = periodType2.withMonthsRemoved();
        java.lang.String str5 = periodType2.toString();
        org.junit.Assert.assertNotNull(periodType1);
        org.junit.Assert.assertNotNull(periodType2);
        org.junit.Assert.assertNotNull(periodType4);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "PeriodType[Millis]" + "'", str5, "PeriodType[Millis]");
    }

    @Test
    public void test0269() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0269");
        org.joda.time.Months months0 = org.joda.time.Months.EIGHT;
        org.junit.Assert.assertNotNull(months0);
    }

    @Test
    public void test0270() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0270");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight.Property property3 = dateMidnight2.monthOfYear();
        org.joda.time.DurationField durationField4 = property3.getDurationField();
        int int5 = property3.getMinimumValue();
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(durationField4);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 1 + "'", int5 == 1);
    }

    @Test
    public void test0271() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0271");
        java.lang.StringBuffer stringBuffer0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.format.FormatUtils.appendPaddedInteger(stringBuffer0, 60, (int) (short) 100);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0272() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0272");
        org.joda.time.DateTimeField dateTimeField0 = null;
        org.joda.time.chrono.IslamicChronology islamicChronology1 = org.joda.time.chrono.IslamicChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField2 = org.joda.time.field.LenientDateTimeField.getInstance(dateTimeField0, (org.joda.time.Chronology) islamicChronology1);
        org.junit.Assert.assertNotNull(islamicChronology1);
        org.junit.Assert.assertNull(dateTimeField2);
    }

    @Test
    public void test0273() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0273");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        org.joda.time.ReadableInterval readableInterval5 = null;
        org.joda.time.Seconds seconds6 = org.joda.time.Seconds.secondsIn(readableInterval5);
        org.joda.time.DurationFieldType durationFieldType7 = seconds6.getFieldType();
        org.joda.time.LocalDateTime localDateTime9 = localDateTime1.withFieldAdded(durationFieldType7, 86400000);
        java.lang.String str10 = localDateTime9.toString();
        boolean boolean12 = localDateTime9.equals((java.lang.Object) (-1.0d));
        org.joda.time.LocalDateTime localDateTime14 = localDateTime9.minusMonths((int) (short) 10);
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(seconds6);
        org.junit.Assert.assertNotNull(durationFieldType7);
        org.junit.Assert.assertNotNull(localDateTime9);
// flaky "7) test0273(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str10 + "' != '" + "2029-05-02T11:10:59.796" + "'", str10, "2029-05-02T11:10:59.796");
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertNotNull(localDateTime14);
    }

    @Test
    public void test0274() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0274");
        org.joda.time.TimeOfDay timeOfDay1 = new org.joda.time.TimeOfDay((long) 100);
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray2 = timeOfDay1.getFieldTypes();
        org.joda.time.chrono.GregorianChronology gregorianChronology3 = org.joda.time.chrono.GregorianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField4 = gregorianChronology3.minuteOfDay();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDateTime localDateTime5 = new org.joda.time.LocalDateTime((java.lang.Object) dateTimeFieldTypeArray2, (org.joda.time.Chronology) gregorianChronology3);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No partial converter found for type: [Lorg.joda.time.DateTimeFieldType;");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeFieldTypeArray2);
        org.junit.Assert.assertNotNull(gregorianChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
    }

    @Test
    public void test0275() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0275");
        org.joda.time.Partial partial0 = new org.joda.time.Partial();
        org.joda.time.format.DateTimeFormatter dateTimeFormatter1 = partial0.getFormatter();
        java.lang.String str2 = partial0.toStringList();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.YearMonthDay yearMonthDay3 = new org.joda.time.YearMonthDay((java.lang.Object) partial0);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Field 'year' is not supported");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(dateTimeFormatter1);
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "[]" + "'", str2, "[]");
    }

    @Test
    public void test0276() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0276");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.Months months4 = org.joda.time.Months.FIVE;
        org.joda.time.Period period5 = period3.minus((org.joda.time.ReadablePeriod) months4);
        org.joda.time.Period period7 = period3.plusMillis((-1));
        org.joda.time.DateTime dateTime8 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone9 = null;
        org.joda.time.DateTime dateTime10 = dateTime8.withZoneRetainFields(dateTimeZone9);
        org.joda.time.DateTime dateTime12 = dateTime8.withYear((int) (byte) 0);
        org.joda.time.DateTime dateTime14 = dateTime8.withWeekyear((int) (byte) 0);
        org.joda.time.DateTime dateTime16 = dateTime14.plusSeconds((int) (byte) -1);
        boolean boolean17 = period3.equals((java.lang.Object) (byte) -1);
        org.joda.time.Period period19 = period3.plusYears(5);
        org.junit.Assert.assertNotNull(months4);
        org.junit.Assert.assertNotNull(period5);
        org.junit.Assert.assertNotNull(period7);
        org.junit.Assert.assertNotNull(dateTime10);
        org.junit.Assert.assertNotNull(dateTime12);
        org.junit.Assert.assertNotNull(dateTime14);
        org.junit.Assert.assertNotNull(dateTime16);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(period19);
    }

    @Test
    public void test0277() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0277");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone5 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology6 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.LocalDateTime localDateTime7 = new org.joda.time.LocalDateTime((long) (-1), (org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.LocalDateTime localDateTime8 = new org.joda.time.LocalDateTime((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.DateMidnight dateMidnight9 = org.joda.time.DateMidnight.now((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.junit.Assert.assertNotNull(gregorianChronology6);
        org.junit.Assert.assertNotNull(dateMidnight9);
    }

    @Test
    public void test0278() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0278");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone5 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology6 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType7 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology9 = null;
        org.joda.time.DateMidnight dateMidnight10 = new org.joda.time.DateMidnight((long) (short) -1, chronology9);
        org.joda.time.DateMidnight dateMidnight12 = dateMidnight10.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property13 = dateMidnight10.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime14 = dateMidnight10.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property15 = mutableDateTime14.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime17 = property15.addWrapField((int) (short) -1);
        boolean boolean18 = leapYearPatternType7.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology19 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5, leapYearPatternType7);
        org.joda.time.MonthDay monthDay20 = new org.joda.time.MonthDay((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.DateTime dateTime21 = new org.joda.time.DateTime(2440588L, (org.joda.time.DateTimeZone) fixedDateTimeZone5);
        int int23 = fixedDateTimeZone5.getOffsetFromLocal((long) 7);
        org.junit.Assert.assertNotNull(copticChronology6);
        org.junit.Assert.assertNotNull(leapYearPatternType7);
        org.junit.Assert.assertNotNull(dateMidnight12);
        org.junit.Assert.assertNotNull(property13);
        org.junit.Assert.assertNotNull(mutableDateTime14);
        org.junit.Assert.assertNotNull(property15);
        org.junit.Assert.assertNotNull(mutableDateTime17);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertNotNull(islamicChronology19);
        org.junit.Assert.assertTrue("'" + int23 + "' != '" + 100 + "'", int23 == 100);
    }

    @Test
    public void test0279() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0279");
        org.joda.time.Seconds seconds0 = org.joda.time.Seconds.THREE;
        org.junit.Assert.assertNotNull(seconds0);
    }

    @Test
    public void test0280() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0280");
        org.joda.time.tz.ZoneInfoProvider zoneInfoProvider0 = new org.joda.time.tz.ZoneInfoProvider();
    }

    @Test
    public void test0281() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0281");
        org.joda.time.DateTime dateTime3 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone4 = null;
        org.joda.time.DateTime dateTime5 = dateTime3.withZoneRetainFields(dateTimeZone4);
        org.joda.time.DateTime dateTime7 = dateTime3.withYear((int) (byte) 0);
        org.joda.time.YearMonthDay yearMonthDay8 = dateTime3.toYearMonthDay();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone13 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.DateTime dateTime14 = yearMonthDay8.toDateTimeAtMidnight((org.joda.time.DateTimeZone) fixedDateTimeZone13);
        org.joda.time.chrono.GJChronology gJChronology17 = org.joda.time.chrono.GJChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone13, (long) 3, 3);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateMidnight dateMidnight18 = new org.joda.time.DateMidnight(24, 32, (int) '#', (org.joda.time.DateTimeZone) fixedDateTimeZone13);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 32 for monthOfYear must be in the range [1,12]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTime5);
        org.junit.Assert.assertNotNull(dateTime7);
        org.junit.Assert.assertNotNull(yearMonthDay8);
        org.junit.Assert.assertNotNull(dateTime14);
        org.junit.Assert.assertNotNull(gJChronology17);
    }

    @Test
    public void test0282() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0282");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        org.joda.time.ReadableInterval readableInterval5 = null;
        org.joda.time.Seconds seconds6 = org.joda.time.Seconds.secondsIn(readableInterval5);
        org.joda.time.DurationFieldType durationFieldType7 = seconds6.getFieldType();
        org.joda.time.LocalDateTime localDateTime9 = localDateTime1.withFieldAdded(durationFieldType7, 86400000);
        int int10 = localDateTime9.getMillisOfDay();
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(seconds6);
        org.junit.Assert.assertNotNull(durationFieldType7);
        org.junit.Assert.assertNotNull(localDateTime9);
// flaky "8) test0282(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int10 + "' != '" + 40260602 + "'", int10 == 40260602);
    }

    @Test
    public void test0283() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0283");
        org.joda.time.Months months0 = org.joda.time.Months.TWO;
        org.junit.Assert.assertNotNull(months0);
    }

    @Test
    public void test0284() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0284");
        org.joda.time.Chronology chronology3 = null;
        org.joda.time.DateMidnight dateMidnight4 = new org.joda.time.DateMidnight((long) (short) -1, chronology3);
        org.joda.time.DateMidnight dateMidnight6 = dateMidnight4.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight4.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.withYear(100);
        org.joda.time.PeriodType periodType13 = null;
        org.joda.time.Chronology chronology14 = null;
        org.joda.time.Period period15 = new org.joda.time.Period((long) (short) 10, periodType13, chronology14);
        org.joda.time.Months months16 = org.joda.time.Months.FIVE;
        org.joda.time.Period period17 = period15.minus((org.joda.time.ReadablePeriod) months16);
        org.joda.time.Chronology chronology19 = null;
        org.joda.time.DateMidnight dateMidnight20 = new org.joda.time.DateMidnight((long) (short) -1, chronology19);
        org.joda.time.DateMidnight dateMidnight22 = dateMidnight20.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology24 = null;
        org.joda.time.DateMidnight dateMidnight25 = new org.joda.time.DateMidnight((long) (short) -1, chronology24);
        org.joda.time.DateMidnight dateMidnight27 = dateMidnight25.minusMonths((int) (byte) 100);
        int int28 = dateMidnight22.compareTo((org.joda.time.ReadableInstant) dateMidnight25);
        org.joda.time.Duration duration29 = period17.toDurationTo((org.joda.time.ReadableInstant) dateMidnight25);
        org.joda.time.Duration duration30 = duration29.negated();
        org.joda.time.PeriodType periodType31 = org.joda.time.PeriodType.yearDay();
        org.joda.time.PeriodType periodType32 = periodType31.withSecondsRemoved();
        org.joda.time.MutablePeriod mutablePeriod33 = new org.joda.time.MutablePeriod((org.joda.time.ReadableInstant) dateMidnight9, (org.joda.time.ReadableDuration) duration29, periodType32);
        org.joda.time.chrono.GregorianChronology gregorianChronology34 = org.joda.time.chrono.GregorianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField35 = gregorianChronology34.minuteOfDay();
        org.joda.time.DateTime dateTime36 = org.joda.time.DateTime.now((org.joda.time.Chronology) gregorianChronology34);
        org.joda.time.MutablePeriod mutablePeriod37 = new org.joda.time.MutablePeriod((-59011462664000L), (long) 1, periodType32, (org.joda.time.Chronology) gregorianChronology34);
        org.joda.time.PeriodType periodType39 = null;
        org.joda.time.Chronology chronology40 = null;
        org.joda.time.Period period41 = new org.joda.time.Period((long) (short) 10, periodType39, chronology40);
        org.joda.time.Months months42 = org.joda.time.Months.FIVE;
        org.joda.time.Period period43 = period41.minus((org.joda.time.ReadablePeriod) months42);
        org.joda.time.Period period45 = period41.plusMillis((-1));
        org.joda.time.PeriodType periodType46 = org.joda.time.PeriodType.yearDay();
        org.joda.time.PeriodType periodType47 = periodType46.withSecondsRemoved();
        org.joda.time.Period period48 = period41.normalizedStandard(periodType47);
        // The following exception was thrown during execution in test generation
        try {
            mutablePeriod37.setPeriod((org.joda.time.ReadablePeriod) period41);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Period does not support field 'millis'");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight6);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(months16);
        org.junit.Assert.assertNotNull(period17);
        org.junit.Assert.assertNotNull(dateMidnight22);
        org.junit.Assert.assertNotNull(dateMidnight27);
        org.junit.Assert.assertTrue("'" + int28 + "' != '" + (-1) + "'", int28 == (-1));
        org.junit.Assert.assertNotNull(duration29);
        org.junit.Assert.assertNotNull(duration30);
        org.junit.Assert.assertNotNull(periodType31);
        org.junit.Assert.assertNotNull(periodType32);
        org.junit.Assert.assertNotNull(gregorianChronology34);
        org.junit.Assert.assertNotNull(dateTimeField35);
        org.junit.Assert.assertNotNull(dateTime36);
        org.junit.Assert.assertNotNull(months42);
        org.junit.Assert.assertNotNull(period43);
        org.junit.Assert.assertNotNull(period45);
        org.junit.Assert.assertNotNull(periodType46);
        org.junit.Assert.assertNotNull(periodType47);
        org.junit.Assert.assertNotNull(period48);
    }

    @Test
    public void test0285() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0285");
        org.joda.time.Months months0 = org.joda.time.Months.FIVE;
        int int1 = months0.getMonths();
        org.junit.Assert.assertNotNull(months0);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 5 + "'", int1 == 5);
    }

    @Test
    public void test0286() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0286");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.DateTimeFormat.mediumTime();
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
    }

    @Test
    public void test0287() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0287");
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder0 = new org.joda.time.format.PeriodFormatterBuilder();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder3 = periodFormatterBuilder0.appendSuffix("Property[monthOfYear]", "40258294");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: No field to apply suffix to");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0288() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0288");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.tTime();
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
    }

    @Test
    public void test0289() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0289");
        org.joda.time.chrono.ISOChronology iSOChronology0 = org.joda.time.chrono.ISOChronology.getInstance();
        org.junit.Assert.assertNotNull(iSOChronology0);
    }

    @Test
    public void test0290() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0290");
        org.joda.time.Instant instant0 = new org.joda.time.Instant();
    }

    @Test
    public void test0291() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0291");
        org.joda.time.Duration duration1 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Chronology chronology3 = null;
        org.joda.time.DateMidnight dateMidnight4 = new org.joda.time.DateMidnight((long) (short) -1, chronology3);
        org.joda.time.DateMidnight dateMidnight6 = dateMidnight4.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology8 = null;
        org.joda.time.DateMidnight dateMidnight9 = new org.joda.time.DateMidnight((long) (short) -1, chronology8);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology13 = null;
        org.joda.time.DateMidnight dateMidnight14 = new org.joda.time.DateMidnight((long) (short) -1, chronology13);
        org.joda.time.DateMidnight dateMidnight16 = dateMidnight14.minusMonths((int) (byte) 100);
        int int17 = dateMidnight11.compareTo((org.joda.time.ReadableInstant) dateMidnight14);
        org.joda.time.Minutes minutes18 = org.joda.time.Minutes.minutesBetween((org.joda.time.ReadableInstant) dateMidnight4, (org.joda.time.ReadableInstant) dateMidnight11);
        org.joda.time.DateMidnight dateMidnight21 = dateMidnight11.withDurationAdded((long) '4', 100);
        org.joda.time.MutableInterval mutableInterval22 = new org.joda.time.MutableInterval((org.joda.time.ReadableDuration) duration1, (org.joda.time.ReadableInstant) dateMidnight21);
        long long23 = org.joda.time.DateTimeUtils.getDurationMillis((org.joda.time.ReadableDuration) duration1);
        org.junit.Assert.assertNotNull(duration1);
        org.junit.Assert.assertNotNull(dateMidnight6);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(dateMidnight16);
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + (-1) + "'", int17 == (-1));
        org.junit.Assert.assertNotNull(minutes18);
        org.junit.Assert.assertNotNull(dateMidnight21);
        org.junit.Assert.assertTrue("'" + long23 + "' != '" + 60000L + "'", long23 == 60000L);
    }

    @Test
    public void test0292() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0292");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight.Property property3 = dateMidnight2.monthOfYear();
        org.joda.time.DurationField durationField4 = property3.getDurationField();
        org.joda.time.DateMidnight dateMidnight5 = property3.roundFloorCopy();
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(durationField4);
        org.junit.Assert.assertNotNull(dateMidnight5);
    }

    @Test
    public void test0293() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0293");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTime.Property property1 = dateTime0.millisOfDay();
        org.joda.time.DateTime dateTime3 = property1.setCopy(40237996);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTime dateTime5 = property1.setCopy("2029-05-02T11:10:53.327");
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value \"2029-05-02T11:10:53.327\" for millisOfDay is not supported");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(property1);
        org.junit.Assert.assertNotNull(dateTime3);
    }

    @Test
    public void test0294() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0294");
        org.joda.time.chrono.GregorianChronology gregorianChronology0 = org.joda.time.chrono.GregorianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField1 = gregorianChronology0.minuteOfDay();
        org.joda.time.DateTime dateTime2 = org.joda.time.DateTime.now((org.joda.time.Chronology) gregorianChronology0);
        long long6 = gregorianChronology0.add((long) 40237996, (long) (byte) 100, 4);
        org.junit.Assert.assertNotNull(gregorianChronology0);
        org.junit.Assert.assertNotNull(dateTimeField1);
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertTrue("'" + long6 + "' != '" + 40238396L + "'", long6 == 40238396L);
    }

    @Test
    public void test0295() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0295");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone5 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology6 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.LocalDateTime localDateTime7 = new org.joda.time.LocalDateTime((long) (-1), (org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.tz.CachedDateTimeZone cachedDateTimeZone8 = org.joda.time.tz.CachedDateTimeZone.forZone((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.chrono.ISOChronology iSOChronology9 = org.joda.time.chrono.ISOChronology.getInstance((org.joda.time.DateTimeZone) cachedDateTimeZone8);
        long long11 = cachedDateTimeZone8.previousTransition((long) 100);
        org.junit.Assert.assertNotNull(gregorianChronology6);
        org.junit.Assert.assertNotNull(cachedDateTimeZone8);
        org.junit.Assert.assertNotNull(iSOChronology9);
        org.junit.Assert.assertTrue("'" + long11 + "' != '" + 100L + "'", long11 == 100L);
    }

    @Test
    public void test0296() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0296");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.MonthDay monthDay4 = monthDay2.minusDays((int) '4');
        org.joda.time.MonthDay.Property property5 = monthDay2.monthOfYear();
        java.lang.String str6 = property5.getName();
        java.lang.String str7 = property5.getName();
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(monthDay4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "monthOfYear" + "'", str6, "monthOfYear");
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "monthOfYear" + "'", str7, "monthOfYear");
    }

    @Test
    public void test0297() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0297");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime2.plusHours(0);
        org.joda.time.DateTimeZone dateTimeZone5 = null;
        org.joda.time.LocalDateTime localDateTime6 = new org.joda.time.LocalDateTime(dateTimeZone5);
        org.joda.time.LocalDateTime localDateTime8 = localDateTime6.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property9 = localDateTime6.weekOfWeekyear();
        org.joda.time.DateTime dateTime10 = dateTime4.withFields((org.joda.time.ReadablePartial) localDateTime6);
        org.joda.time.LocalDateTime.Property property11 = localDateTime6.weekyear();
        org.joda.time.LocalDateTime localDateTime12 = property11.roundHalfFloorCopy();
        org.joda.time.LocalDateTime localDateTime13 = property11.withMinimumValue();
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(localDateTime8);
        org.junit.Assert.assertNotNull(property9);
        org.junit.Assert.assertNotNull(dateTime10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(localDateTime12);
        org.junit.Assert.assertNotNull(localDateTime13);
    }

    @Test
    public void test0298() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0298");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.withYear(100);
        int int10 = dateMidnight9.getWeekOfWeekyear();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 53 + "'", int10 == 53);
    }

    @Test
    public void test0299() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0299");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Interval interval2 = yearMonth0.toInterval(dateTimeZone1);
        org.joda.time.YearMonth yearMonth4 = yearMonth0.plusYears((int) (byte) 1);
        org.joda.time.LocalTime localTime6 = new org.joda.time.LocalTime((long) (-1));
        org.joda.time.LocalTime localTime8 = localTime6.withSecondOfMinute(1);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean9 = yearMonth4.isAfter((org.joda.time.ReadablePartial) localTime6);
            org.junit.Assert.fail("Expected exception of type java.lang.ClassCastException; message: ReadablePartial objects must have matching field types");
        } catch (java.lang.ClassCastException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertNotNull(yearMonth4);
        org.junit.Assert.assertNotNull(localTime8);
    }

    @Test
    public void test0300() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0300");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTime dateTime7 = new org.joda.time.DateTime(70, 8, 29, 60, 29, (int) (byte) 100, 1440);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 60 for hourOfDay must be in the range [0,23]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0301() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0301");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        org.joda.time.ReadableInterval readableInterval5 = null;
        org.joda.time.Seconds seconds6 = org.joda.time.Seconds.secondsIn(readableInterval5);
        org.joda.time.DurationFieldType durationFieldType7 = seconds6.getFieldType();
        org.joda.time.LocalDateTime localDateTime9 = localDateTime1.withFieldAdded(durationFieldType7, 86400000);
        org.joda.time.LocalDateTime localDateTime11 = localDateTime9.plusMillis((int) (byte) 10);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDateTime localDateTime13 = localDateTime11.withDayOfWeek(20);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 20 for dayOfWeek must be in the range [1,7]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(seconds6);
        org.junit.Assert.assertNotNull(durationFieldType7);
        org.junit.Assert.assertNotNull(localDateTime9);
        org.junit.Assert.assertNotNull(localDateTime11);
    }

    @Test
    public void test0302() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0302");
        double double1 = org.joda.time.DateTimeUtils.toJulianDay(100L);
        org.junit.Assert.assertTrue("'" + double1 + "' != '" + 2440587.5000011576d + "'", double1 == 2440587.5000011576d);
    }

    @Test
    public void test0303() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0303");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.dateOptionalTimeParser();
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
    }

    @Test
    public void test0304() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0304");
        org.joda.time.DateMidnight dateMidnight0 = org.joda.time.DateMidnight.now();
        org.junit.Assert.assertNotNull(dateMidnight0);
    }

    @Test
    public void test0305() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0305");
        org.joda.time.Seconds seconds0 = org.joda.time.Seconds.ONE;
        java.lang.String str1 = seconds0.toString();
        org.junit.Assert.assertNotNull(seconds0);
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "PT1S" + "'", str1, "PT1S");
    }

    @Test
    public void test0306() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0306");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        // The following exception was thrown during execution in test generation
        try {
            mutableDateTime6.setDayOfWeek(70);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 70 for dayOfWeek must be in the range [1,7]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
    }

    @Test
    public void test0307() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0307");
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.TimeOfDay timeOfDay4 = new org.joda.time.TimeOfDay(1970, 0, (org.joda.time.Chronology) lenientChronology3);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 1970 for hourOfDay must not be larger than 23");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
    }

    @Test
    public void test0308() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0308");
        long long1 = org.joda.time.DateTimeUtils.fromJulianDay((double) 86400000);
        org.junit.Assert.assertTrue("'" + long1 + "' != '" + 7254093240000000L + "'", long1 == 7254093240000000L);
    }

    @Test
    public void test0309() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0309");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime9 = property7.addWrapField((int) (short) -1);
        org.joda.time.MutableDateTime.Property property10 = mutableDateTime9.monthOfYear();
        org.joda.time.MutableDateTime mutableDateTime11 = property10.roundHalfFloor();
        org.joda.time.MutableDateTime.Property property12 = mutableDateTime11.hourOfDay();
        java.util.Locale locale14 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MutableDateTime mutableDateTime15 = property12.set("2026-09-01T00:01:40.000+02:00", locale14);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value \"2026-09-01T00:01:40.000+02:00\" for hourOfDay is not supported");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(mutableDateTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(mutableDateTime11);
        org.junit.Assert.assertNotNull(property12);
    }

    @Test
    public void test0310() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0310");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        org.joda.time.ReadableInterval readableInterval5 = null;
        org.joda.time.Seconds seconds6 = org.joda.time.Seconds.secondsIn(readableInterval5);
        org.joda.time.DurationFieldType durationFieldType7 = seconds6.getFieldType();
        org.joda.time.LocalDateTime localDateTime9 = localDateTime1.withFieldAdded(durationFieldType7, 86400000);
        org.joda.time.LocalDateTime.Property property10 = localDateTime9.year();
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(seconds6);
        org.junit.Assert.assertNotNull(durationFieldType7);
        org.junit.Assert.assertNotNull(localDateTime9);
        org.junit.Assert.assertNotNull(property10);
    }

    @Test
    public void test0311() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0311");
        int int0 = org.joda.time.DateTimeConstants.MILLIS_PER_SECOND;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 1000 + "'", int0 == 1000);
    }

    @Test
    public void test0312() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0312");
        org.joda.time.PeriodType periodType1 = org.joda.time.PeriodType.millis();
        org.joda.time.chrono.GJChronology gJChronology4 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology5 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology4);
        org.joda.time.chrono.LenientChronology lenientChronology6 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology4);
        org.joda.time.Period period7 = new org.joda.time.Period((long) 1, 100L, (org.joda.time.Chronology) gJChronology4);
        org.joda.time.MutablePeriod mutablePeriod8 = new org.joda.time.MutablePeriod((long) 2147483647, periodType1, (org.joda.time.Chronology) gJChronology4);
        org.junit.Assert.assertNotNull(periodType1);
        org.junit.Assert.assertNotNull(gJChronology4);
        org.junit.Assert.assertNotNull(lenientChronology5);
        org.junit.Assert.assertNotNull(lenientChronology6);
    }

    @Test
    public void test0313() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0313");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.weekyearWeekDay();
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
    }

    @Test
    public void test0314() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0314");
        org.joda.time.LocalTime localTime1 = new org.joda.time.LocalTime((long) (-1));
        org.joda.time.Chronology chronology2 = localTime1.getChronology();
        int int3 = localTime1.getSecondOfMinute();
        org.joda.time.LocalTime.Property property4 = localTime1.secondOfMinute();
        org.joda.time.LocalTime localTime5 = property4.roundHalfCeilingCopy();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalTime localTime7 = property4.setCopy(2026);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 2026 for secondOfMinute must be in the range [0,59]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(chronology2);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 59 + "'", int3 == 59);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(localTime5);
    }

    @Test
    public void test0315() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0315");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusYears(100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.monthOfYear();
        org.joda.time.DateTimeField dateTimeField6 = property5.getField();
        org.joda.time.DateMidnight dateMidnight8 = property5.setCopy((int) (byte) 1);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(dateTimeField6);
        org.junit.Assert.assertNotNull(dateMidnight8);
    }

    @Test
    public void test0316() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0316");
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
        org.joda.time.DateTime dateTime14 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone15 = null;
        org.joda.time.DateTime dateTime16 = dateTime14.withZoneRetainFields(dateTimeZone15);
        org.joda.time.DateTime dateTime18 = dateTime14.withYear((int) (byte) 0);
        org.joda.time.Duration duration20 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Chronology chronology22 = null;
        org.joda.time.DateMidnight dateMidnight23 = new org.joda.time.DateMidnight((long) (short) -1, chronology22);
        org.joda.time.DateMidnight dateMidnight25 = dateMidnight23.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology27 = null;
        org.joda.time.DateMidnight dateMidnight28 = new org.joda.time.DateMidnight((long) (short) -1, chronology27);
        org.joda.time.DateMidnight dateMidnight30 = dateMidnight28.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology32 = null;
        org.joda.time.DateMidnight dateMidnight33 = new org.joda.time.DateMidnight((long) (short) -1, chronology32);
        org.joda.time.DateMidnight dateMidnight35 = dateMidnight33.minusMonths((int) (byte) 100);
        int int36 = dateMidnight30.compareTo((org.joda.time.ReadableInstant) dateMidnight33);
        org.joda.time.Minutes minutes37 = org.joda.time.Minutes.minutesBetween((org.joda.time.ReadableInstant) dateMidnight23, (org.joda.time.ReadableInstant) dateMidnight30);
        org.joda.time.DateMidnight dateMidnight40 = dateMidnight30.withDurationAdded((long) '4', 100);
        org.joda.time.MutableInterval mutableInterval41 = new org.joda.time.MutableInterval((org.joda.time.ReadableDuration) duration20, (org.joda.time.ReadableInstant) dateMidnight40);
        org.joda.time.DateTime dateTime42 = dateTime18.minus((org.joda.time.ReadableDuration) duration20);
        org.joda.time.DateTime.Property property43 = dateTime42.millisOfDay();
        org.joda.time.DateMidnight dateMidnight44 = dateTime42.toDateMidnight();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Seconds seconds45 = org.joda.time.Seconds.secondsBetween((org.joda.time.ReadableInstant) dateTime6, (org.joda.time.ReadableInstant) dateTime42);
            org.junit.Assert.fail("Expected exception of type java.lang.ArithmeticException; message: Value cannot fit in an int: -63934354724");
        } catch (java.lang.ArithmeticException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(duration1);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(dateTime6);
        org.junit.Assert.assertNotNull(localDateTime10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(dateTime12);
        org.junit.Assert.assertNotNull(dateTime16);
        org.junit.Assert.assertNotNull(dateTime18);
        org.junit.Assert.assertNotNull(duration20);
        org.junit.Assert.assertNotNull(dateMidnight25);
        org.junit.Assert.assertNotNull(dateMidnight30);
        org.junit.Assert.assertNotNull(dateMidnight35);
        org.junit.Assert.assertTrue("'" + int36 + "' != '" + (-1) + "'", int36 == (-1));
        org.junit.Assert.assertNotNull(minutes37);
        org.junit.Assert.assertNotNull(dateMidnight40);
        org.junit.Assert.assertNotNull(dateTime42);
        org.junit.Assert.assertNotNull(property43);
        org.junit.Assert.assertNotNull(dateMidnight44);
    }

    @Test
    public void test0317() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0317");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = localDate2.withWeekyear((int) (byte) 100);
        org.joda.time.LocalDate localDate7 = localDate2.withWeekyear((int) (short) -1);
        int int8 = localDate7.size();
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(localDate7);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 3 + "'", int8 == 3);
    }

    @Test
    public void test0318() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0318");
        org.joda.time.Weeks weeks1 = org.joda.time.Weeks.weeks(86400000);
        org.junit.Assert.assertNotNull(weeks1);
    }

    @Test
    public void test0319() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0319");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Instant instant1 = org.joda.time.Instant.parse("1 year, -1 weeks, -1 days, 1 hour, 100 seconds and 3 milliseconds");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid format: \"1 year, -1 weeks, -1 days, 1 hour...\" is malformed at \" year, -1 weeks, -1 days, 1 hour...\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0320() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0320");
        org.joda.time.DateTimeZone dateTimeZone1 = org.joda.time.DateTimeZone.forOffsetMillis(0);
        org.junit.Assert.assertNotNull(dateTimeZone1);
    }

    @Test
    public void test0321() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0321");
        org.joda.time.tz.NameProvider nameProvider0 = null;
        org.joda.time.DateTimeZone.setNameProvider(nameProvider0);
    }

    @Test
    public void test0322() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0322");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.PeriodType periodType4 = null;
        org.joda.time.Chronology chronology5 = null;
        org.joda.time.Period period6 = new org.joda.time.Period((long) (short) 10, periodType4, chronology5);
        org.joda.time.Months months7 = org.joda.time.Months.FIVE;
        org.joda.time.Period period8 = period6.minus((org.joda.time.ReadablePeriod) months7);
        org.joda.time.Chronology chronology10 = null;
        org.joda.time.DateMidnight dateMidnight11 = new org.joda.time.DateMidnight((long) (short) -1, chronology10);
        org.joda.time.DateMidnight dateMidnight13 = dateMidnight11.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology15 = null;
        org.joda.time.DateMidnight dateMidnight16 = new org.joda.time.DateMidnight((long) (short) -1, chronology15);
        org.joda.time.DateMidnight dateMidnight18 = dateMidnight16.minusMonths((int) (byte) 100);
        int int19 = dateMidnight13.compareTo((org.joda.time.ReadableInstant) dateMidnight16);
        org.joda.time.Duration duration20 = period8.toDurationTo((org.joda.time.ReadableInstant) dateMidnight16);
        org.joda.time.Duration duration21 = duration20.negated();
        org.joda.time.DateTime dateTime22 = dateTime0.minus((org.joda.time.ReadableDuration) duration21);
        org.joda.time.DateTime dateTime24 = dateTime22.minus((long) (short) 1);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTime dateTime26 = dateTime24.withWeekyear(2147483647);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 2147483647 for weekyear must be in the range [-292275054,292278993]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(months7);
        org.junit.Assert.assertNotNull(period8);
        org.junit.Assert.assertNotNull(dateMidnight13);
        org.junit.Assert.assertNotNull(dateMidnight18);
        org.junit.Assert.assertTrue("'" + int19 + "' != '" + (-1) + "'", int19 == (-1));
        org.junit.Assert.assertNotNull(duration20);
        org.junit.Assert.assertNotNull(duration21);
        org.junit.Assert.assertNotNull(dateTime22);
        org.junit.Assert.assertNotNull(dateTime24);
    }

    @Test
    public void test0323() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0323");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone5 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology6 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.LocalDateTime localDateTime7 = new org.joda.time.LocalDateTime((long) (-1), (org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.LocalDateTime localDateTime8 = new org.joda.time.LocalDateTime((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        int int10 = fixedDateTimeZone5.getStandardOffset((long) ' ');
        org.junit.Assert.assertNotNull(gregorianChronology6);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
    }

    @Test
    public void test0324() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0324");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        int int8 = skipUndoDateTimeField6.get((long) 10);
        int int10 = skipUndoDateTimeField6.getMaximumValue((long) 53);
        long long13 = skipUndoDateTimeField6.add(8L, (long) 40260602);
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 2 + "'", int8 == 2);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 53 + "'", int10 == 53);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + 24349612086000008L + "'", long13 == 24349612086000008L);
    }

    @Test
    public void test0325() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0325");
        org.joda.time.Weeks weeks0 = org.joda.time.Weeks.MIN_VALUE;
        org.joda.time.Weeks weeks1 = org.joda.time.Weeks.MIN_VALUE;
        boolean boolean2 = weeks0.isGreaterThan(weeks1);
        org.joda.time.DurationFieldType durationFieldType3 = weeks0.getFieldType();
        org.joda.time.Weeks weeks4 = org.joda.time.Weeks.MIN_VALUE;
        boolean boolean5 = weeks0.isGreaterThan(weeks4);
        org.junit.Assert.assertNotNull(weeks0);
        org.junit.Assert.assertNotNull(weeks1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(durationFieldType3);
        org.junit.Assert.assertNotNull(weeks4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test0326() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0326");
        org.joda.time.DateTimeZone dateTimeZone2 = org.joda.time.DateTimeZone.forOffsetHoursMinutes((int) (short) 0, 0);
        org.junit.Assert.assertNotNull(dateTimeZone2);
    }

    @Test
    public void test0327() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0327");
        org.joda.time.Duration duration3 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Chronology chronology5 = null;
        org.joda.time.DateMidnight dateMidnight6 = new org.joda.time.DateMidnight((long) (short) -1, chronology5);
        org.joda.time.DateMidnight dateMidnight8 = dateMidnight6.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology10 = null;
        org.joda.time.DateMidnight dateMidnight11 = new org.joda.time.DateMidnight((long) (short) -1, chronology10);
        org.joda.time.DateMidnight dateMidnight13 = dateMidnight11.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology15 = null;
        org.joda.time.DateMidnight dateMidnight16 = new org.joda.time.DateMidnight((long) (short) -1, chronology15);
        org.joda.time.DateMidnight dateMidnight18 = dateMidnight16.minusMonths((int) (byte) 100);
        int int19 = dateMidnight13.compareTo((org.joda.time.ReadableInstant) dateMidnight16);
        org.joda.time.Minutes minutes20 = org.joda.time.Minutes.minutesBetween((org.joda.time.ReadableInstant) dateMidnight6, (org.joda.time.ReadableInstant) dateMidnight13);
        org.joda.time.DateMidnight dateMidnight23 = dateMidnight13.withDurationAdded((long) '4', 100);
        org.joda.time.MutableInterval mutableInterval24 = new org.joda.time.MutableInterval((org.joda.time.ReadableDuration) duration3, (org.joda.time.ReadableInstant) dateMidnight23);
        org.joda.time.chrono.GJChronology gJChronology25 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField26 = gJChronology25.dayOfYear();
        org.joda.time.DurationField durationField27 = gJChronology25.years();
        org.joda.time.Period period28 = duration3.toPeriod((org.joda.time.Chronology) gJChronology25);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MonthDay monthDay29 = new org.joda.time.MonthDay(1000, 1970, (org.joda.time.Chronology) gJChronology25);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 1000 for monthOfYear must not be larger than 12");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(duration3);
        org.junit.Assert.assertNotNull(dateMidnight8);
        org.junit.Assert.assertNotNull(dateMidnight13);
        org.junit.Assert.assertNotNull(dateMidnight18);
        org.junit.Assert.assertTrue("'" + int19 + "' != '" + (-1) + "'", int19 == (-1));
        org.junit.Assert.assertNotNull(minutes20);
        org.junit.Assert.assertNotNull(dateMidnight23);
        org.junit.Assert.assertNotNull(gJChronology25);
        org.junit.Assert.assertNotNull(dateTimeField26);
        org.junit.Assert.assertNotNull(durationField27);
        org.junit.Assert.assertNotNull(period28);
    }

    @Test
    public void test0328() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0328");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.withYear(100);
        org.joda.time.Chronology chronology11 = null;
        org.joda.time.DateMidnight dateMidnight12 = new org.joda.time.DateMidnight((long) (short) -1, chronology11);
        org.joda.time.Interval interval13 = new org.joda.time.Interval((org.joda.time.ReadableInstant) dateMidnight9, (org.joda.time.ReadableInstant) dateMidnight12);
        org.joda.time.Period period15 = org.joda.time.Period.weeks((int) (short) 100);
        org.joda.time.Interval interval16 = interval13.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) period15);
        org.joda.time.YearMonth yearMonth17 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone18 = null;
        org.joda.time.Interval interval19 = yearMonth17.toInterval(dateTimeZone18);
        boolean boolean20 = interval13.isAfter((org.joda.time.ReadableInterval) interval19);
        org.joda.time.Weeks weeks21 = org.joda.time.Weeks.weeksIn((org.joda.time.ReadableInterval) interval19);
        org.joda.time.Weeks weeks22 = weeks21.negated();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(period15);
        org.junit.Assert.assertNotNull(interval16);
        org.junit.Assert.assertNotNull(interval19);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
        org.junit.Assert.assertNotNull(weeks21);
        org.junit.Assert.assertNotNull(weeks22);
    }

    @Test
    public void test0329() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0329");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone4 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology5 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType6 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology8 = null;
        org.joda.time.DateMidnight dateMidnight9 = new org.joda.time.DateMidnight((long) (short) -1, chronology8);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property12 = dateMidnight9.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime13 = dateMidnight9.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property14 = mutableDateTime13.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime16 = property14.addWrapField((int) (short) -1);
        boolean boolean17 = leapYearPatternType6.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology18 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4, leapYearPatternType6);
        org.joda.time.MonthDay monthDay19 = new org.joda.time.MonthDay((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        org.joda.time.DateMidnight dateMidnight20 = new org.joda.time.DateMidnight((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        org.joda.time.Instant instant21 = org.joda.time.Instant.EPOCH;
        org.joda.time.Instant instant24 = instant21.withDurationAdded((-1L), 100);
        org.joda.time.Chronology chronology26 = null;
        org.joda.time.DateMidnight dateMidnight27 = new org.joda.time.DateMidnight((long) (short) -1, chronology26);
        org.joda.time.DateMidnight dateMidnight29 = dateMidnight27.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property30 = dateMidnight27.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime31 = dateMidnight27.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property32 = mutableDateTime31.centuryOfEra();
        mutableDateTime31.setSecondOfMinute(24);
        org.joda.time.Months months35 = org.joda.time.Months.monthsBetween((org.joda.time.ReadableInstant) instant21, (org.joda.time.ReadableInstant) mutableDateTime31);
        org.joda.time.Chronology chronology37 = null;
        org.joda.time.DateMidnight dateMidnight38 = new org.joda.time.DateMidnight((long) (short) -1, chronology37);
        org.joda.time.DateMidnight dateMidnight40 = dateMidnight38.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight43 = dateMidnight38.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight45 = dateMidnight43.withYear(100);
        org.joda.time.PeriodType periodType47 = null;
        org.joda.time.Chronology chronology48 = null;
        org.joda.time.Period period49 = new org.joda.time.Period((long) (short) 10, periodType47, chronology48);
        org.joda.time.Months months50 = org.joda.time.Months.FIVE;
        org.joda.time.Period period51 = period49.minus((org.joda.time.ReadablePeriod) months50);
        org.joda.time.Chronology chronology53 = null;
        org.joda.time.DateMidnight dateMidnight54 = new org.joda.time.DateMidnight((long) (short) -1, chronology53);
        org.joda.time.DateMidnight dateMidnight56 = dateMidnight54.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology58 = null;
        org.joda.time.DateMidnight dateMidnight59 = new org.joda.time.DateMidnight((long) (short) -1, chronology58);
        org.joda.time.DateMidnight dateMidnight61 = dateMidnight59.minusMonths((int) (byte) 100);
        int int62 = dateMidnight56.compareTo((org.joda.time.ReadableInstant) dateMidnight59);
        org.joda.time.Duration duration63 = period51.toDurationTo((org.joda.time.ReadableInstant) dateMidnight59);
        org.joda.time.Duration duration64 = duration63.negated();
        org.joda.time.PeriodType periodType65 = org.joda.time.PeriodType.yearDay();
        org.joda.time.PeriodType periodType66 = periodType65.withSecondsRemoved();
        org.joda.time.MutablePeriod mutablePeriod67 = new org.joda.time.MutablePeriod((org.joda.time.ReadableInstant) dateMidnight43, (org.joda.time.ReadableDuration) duration63, periodType66);
        org.joda.time.Period period68 = new org.joda.time.Period((org.joda.time.ReadableInstant) instant21, (org.joda.time.ReadableDuration) duration63);
        org.joda.time.DateMidnight dateMidnight70 = dateMidnight20.withDurationAdded((org.joda.time.ReadableDuration) duration63, (int) '4');
        org.joda.time.DateMidnight.Property property71 = dateMidnight20.yearOfEra();
        org.junit.Assert.assertNotNull(copticChronology5);
        org.junit.Assert.assertNotNull(leapYearPatternType6);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(property12);
        org.junit.Assert.assertNotNull(mutableDateTime13);
        org.junit.Assert.assertNotNull(property14);
        org.junit.Assert.assertNotNull(mutableDateTime16);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(islamicChronology18);
        org.junit.Assert.assertNotNull(instant21);
        org.junit.Assert.assertNotNull(instant24);
        org.junit.Assert.assertNotNull(dateMidnight29);
        org.junit.Assert.assertNotNull(property30);
        org.junit.Assert.assertNotNull(mutableDateTime31);
        org.junit.Assert.assertNotNull(property32);
        org.junit.Assert.assertNotNull(months35);
        org.junit.Assert.assertNotNull(dateMidnight40);
        org.junit.Assert.assertNotNull(dateMidnight43);
        org.junit.Assert.assertNotNull(dateMidnight45);
        org.junit.Assert.assertNotNull(months50);
        org.junit.Assert.assertNotNull(period51);
        org.junit.Assert.assertNotNull(dateMidnight56);
        org.junit.Assert.assertNotNull(dateMidnight61);
        org.junit.Assert.assertTrue("'" + int62 + "' != '" + (-1) + "'", int62 == (-1));
        org.junit.Assert.assertNotNull(duration63);
        org.junit.Assert.assertNotNull(duration64);
        org.junit.Assert.assertNotNull(periodType65);
        org.junit.Assert.assertNotNull(periodType66);
        org.junit.Assert.assertNotNull(dateMidnight70);
        org.junit.Assert.assertNotNull(property71);
    }

    @Test
    public void test0330() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0330");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Interval interval2 = yearMonth0.toInterval(dateTimeZone1);
        org.joda.time.Months months3 = org.joda.time.Months.ONE;
        org.joda.time.Interval interval4 = interval2.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) months3);
        org.joda.time.Months months6 = months3.plus(40243947);
        org.joda.time.Months months8 = months3.dividedBy((int) (short) 10);
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertNotNull(months3);
        org.junit.Assert.assertNotNull(interval4);
        org.junit.Assert.assertNotNull(months6);
        org.junit.Assert.assertNotNull(months8);
    }

    @Test
    public void test0331() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0331");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.format.DateTimeFormatter dateTimeFormatter1 = org.joda.time.format.DateTimeFormat.forPattern("org/joda/time/tz/data");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Illegal pattern component: o");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0332() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0332");
        int int0 = org.joda.time.DateTimeConstants.SECONDS_PER_DAY;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 86400 + "'", int0 == 86400);
    }

    @Test
    public void test0333() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0333");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.DateTime dateTime6 = dateTime0.withWeekyear((int) (byte) 0);
        org.joda.time.DateTime dateTime8 = dateTime6.plusSeconds((int) (byte) -1);
        org.joda.time.DateTime.Property property9 = dateTime8.year();
        org.joda.time.DateTimeField dateTimeField10 = property9.getField();
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(dateTime6);
        org.junit.Assert.assertNotNull(dateTime8);
        org.junit.Assert.assertNotNull(property9);
        org.junit.Assert.assertNotNull(dateTimeField10);
    }

    @Test
    public void test0334() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0334");
        int int0 = org.joda.time.MutableDateTime.ROUND_CEILING;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 2 + "'", int0 == 2);
    }

    @Test
    public void test0335() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0335");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.YearMonthDay yearMonthDay5 = dateTime0.toYearMonthDay();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone10 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.DateTime dateTime11 = yearMonthDay5.toDateTimeAtMidnight((org.joda.time.DateTimeZone) fixedDateTimeZone10);
        org.joda.time.LocalDate localDate12 = yearMonthDay5.toLocalDate();
        org.joda.time.TimeOfDay timeOfDay13 = null;
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone19 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology20 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone19);
        org.joda.time.LocalDate localDate21 = new org.joda.time.LocalDate(1L, (org.joda.time.DateTimeZone) fixedDateTimeZone19);
        org.joda.time.DateTime dateTime22 = yearMonthDay5.toDateTime(timeOfDay13, (org.joda.time.DateTimeZone) fixedDateTimeZone19);
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(yearMonthDay5);
        org.junit.Assert.assertNotNull(dateTime11);
        org.junit.Assert.assertNotNull(localDate12);
        org.junit.Assert.assertNotNull(copticChronology20);
        org.junit.Assert.assertNotNull(dateTime22);
    }

    @Test
    public void test0336() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0336");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = property3.addWrapFieldToCopy(29);
        org.joda.time.LocalDate localDate6 = property3.withMinimumValue();
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(localDate6);
    }

    @Test
    public void test0337() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0337");
        org.joda.time.Days days0 = org.joda.time.Days.MIN_VALUE;
        org.joda.time.Days days1 = org.joda.time.Days.MAX_VALUE;
        org.joda.time.Days days2 = days0.plus(days1);
        org.junit.Assert.assertNotNull(days0);
        org.junit.Assert.assertNotNull(days1);
        org.junit.Assert.assertNotNull(days2);
    }

    @Test
    public void test0338() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0338");
        int int0 = org.joda.time.DateTimeConstants.JUNE;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 6 + "'", int0 == 6);
    }

    @Test
    public void test0339() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0339");
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
        org.joda.time.Months months15 = org.joda.time.Months.monthsBetween((org.joda.time.ReadableInstant) mutableDateTime6, (org.joda.time.ReadableInstant) dateMidnight12);
        org.joda.time.DateMidnight dateMidnight18 = dateMidnight12.withDurationAdded((long) 40237996, 4);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(dateMidnight14);
        org.junit.Assert.assertNotNull(months15);
        org.junit.Assert.assertNotNull(dateMidnight18);
    }

    @Test
    public void test0340() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0340");
        org.joda.time.Minutes minutes1 = org.joda.time.Minutes.minutes(86400000);
        org.junit.Assert.assertNotNull(minutes1);
    }

    @Test
    public void test0341() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0341");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.DateTime dateTime6 = dateTime0.withWeekyear((int) (byte) 0);
        org.joda.time.DateTime dateTime8 = dateTime0.minusMonths(8);
        int int9 = dateTime8.getSecondOfMinute();
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(dateTime6);
        org.junit.Assert.assertNotNull(dateTime8);
// flaky "9) test0341(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int9 + "' != '" + 4 + "'", int9 == 4);
    }

    @Test
    public void test0342() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0342");
        org.joda.time.ReadableInterval readableInterval0 = null;
        org.joda.time.Seconds seconds1 = org.joda.time.Seconds.secondsIn(readableInterval0);
        org.joda.time.DurationFieldType durationFieldType2 = seconds1.getFieldType();
        org.joda.time.field.PreciseDurationField preciseDurationField4 = new org.joda.time.field.PreciseDurationField(durationFieldType2, (long) 59);
        org.joda.time.ReadableInterval readableInterval5 = null;
        org.joda.time.Seconds seconds6 = org.joda.time.Seconds.secondsIn(readableInterval5);
        org.joda.time.DurationFieldType durationFieldType7 = seconds6.getFieldType();
        org.joda.time.field.PreciseDurationField preciseDurationField9 = new org.joda.time.field.PreciseDurationField(durationFieldType7, (long) 59);
        int int10 = preciseDurationField4.compareTo((org.joda.time.DurationField) preciseDurationField9);
        org.joda.time.DurationFieldType durationFieldType11 = preciseDurationField4.getType();
        org.joda.time.DurationFieldType durationFieldType12 = preciseDurationField4.getType();
        org.junit.Assert.assertNotNull(seconds1);
        org.junit.Assert.assertNotNull(durationFieldType2);
        org.junit.Assert.assertNotNull(seconds6);
        org.junit.Assert.assertNotNull(durationFieldType7);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
        org.junit.Assert.assertNotNull(durationFieldType11);
        org.junit.Assert.assertNotNull(durationFieldType12);
    }

    @Test
    public void test0343() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0343");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.basicWeekDateTimeNoMillis();
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
    }

    @Test
    public void test0344() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0344");
        org.joda.time.convert.ConverterManager converterManager0 = org.joda.time.convert.ConverterManager.getInstance();
        org.joda.time.convert.PartialConverter partialConverter1 = null;
        org.joda.time.convert.PartialConverter partialConverter2 = converterManager0.addPartialConverter(partialConverter1);
        org.joda.time.convert.InstantConverter instantConverter3 = null;
        org.joda.time.convert.InstantConverter instantConverter4 = converterManager0.addInstantConverter(instantConverter3);
        org.joda.time.convert.InstantConverter[] instantConverterArray5 = converterManager0.getInstantConverters();
        org.junit.Assert.assertNotNull(converterManager0);
        org.junit.Assert.assertNull(partialConverter2);
        org.junit.Assert.assertNull(instantConverter4);
        org.junit.Assert.assertNotNull(instantConverterArray5);
    }

    @Test
    public void test0345() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0345");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.Months months4 = org.joda.time.Months.FIVE;
        org.joda.time.Period period5 = period3.minus((org.joda.time.ReadablePeriod) months4);
        org.joda.time.Period period7 = period3.plusMillis((-1));
        int int8 = period7.getMinutes();
        org.junit.Assert.assertNotNull(months4);
        org.junit.Assert.assertNotNull(period5);
        org.junit.Assert.assertNotNull(period7);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
    }

    @Test
    public void test0346() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0346");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = property3.addToCopy(1);
        int int6 = localDate5.getEra();
        int int7 = localDate5.getCenturyOfEra();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDate localDate9 = localDate5.withCenturyOfEra((int) (short) -1);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value -1 for centuryOfEra must be in the range [1,2922730]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 1 + "'", int6 == 1);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 20 + "'", int7 == 20);
    }

    @Test
    public void test0347() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0347");
        int int0 = org.joda.time.DateTimeConstants.HOURS_PER_WEEK;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 168 + "'", int0 == 168);
    }

    @Test
    public void test0348() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0348");
        int int0 = org.joda.time.YearMonth.YEAR;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 0 + "'", int0 == 0);
    }

    @Test
    public void test0349() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0349");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone6 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology7 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone6);
        org.joda.time.LocalDateTime localDateTime8 = new org.joda.time.LocalDateTime((long) (-1), (org.joda.time.DateTimeZone) fixedDateTimeZone6);
        org.joda.time.tz.CachedDateTimeZone cachedDateTimeZone9 = org.joda.time.tz.CachedDateTimeZone.forZone((org.joda.time.DateTimeZone) fixedDateTimeZone6);
        long long11 = fixedDateTimeZone6.previousTransition(1L);
        java.lang.String str13 = fixedDateTimeZone6.getName((long) '#');
        org.joda.time.DateTime dateTime14 = new org.joda.time.DateTime((long) 2026, (org.joda.time.DateTimeZone) fixedDateTimeZone6);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTime dateTime16 = dateTime14.withEra(86400);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 86400 for era must be in the range [0,1]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gregorianChronology7);
        org.junit.Assert.assertNotNull(cachedDateTimeZone9);
        org.junit.Assert.assertTrue("'" + long11 + "' != '" + 1L + "'", long11 == 1L);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "+00:00:00.100" + "'", str13, "+00:00:00.100");
    }

    @Test
    public void test0350() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0350");
        java.util.TimeZone timeZone0 = null;
        org.joda.time.DateTimeZone dateTimeZone1 = org.joda.time.DateTimeZone.forTimeZone(timeZone0);
        org.joda.time.chrono.EthiopicChronology ethiopicChronology3 = org.joda.time.chrono.EthiopicChronology.getInstance(dateTimeZone1, 2);
        boolean boolean5 = ethiopicChronology3.equals((java.lang.Object) 2147483647);
        org.junit.Assert.assertNotNull(dateTimeZone1);
        org.junit.Assert.assertNotNull(ethiopicChronology3);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test0351() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0351");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar8 = dateMidnight7.toGregorianCalendar();
        org.joda.time.TimeOfDay timeOfDay9 = org.joda.time.TimeOfDay.fromCalendarFields((java.util.Calendar) gregorianCalendar8);
        org.joda.time.LocalTime localTime10 = org.joda.time.LocalTime.fromCalendarFields((java.util.Calendar) gregorianCalendar8);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(gregorianCalendar8);
        org.junit.Assert.assertNotNull(timeOfDay9);
        org.junit.Assert.assertNotNull(localTime10);
    }

    @Test
    public void test0352() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0352");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.YearMonthDay yearMonthDay5 = dateTime0.toYearMonthDay();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone10 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.DateTime dateTime11 = yearMonthDay5.toDateTimeAtMidnight((org.joda.time.DateTimeZone) fixedDateTimeZone10);
        org.joda.time.YearMonthDay yearMonthDay13 = yearMonthDay5.plusMonths((int) (byte) 100);
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(yearMonthDay5);
        org.junit.Assert.assertNotNull(dateTime11);
        org.junit.Assert.assertNotNull(yearMonthDay13);
    }

    @Test
    public void test0353() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0353");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime3.yearOfCentury();
        org.joda.time.LocalDateTime localDateTime5 = property4.withMinimumValue();
        org.joda.time.LocalDateTime localDateTime7 = localDateTime5.plusYears(2026);
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(localDateTime5);
        org.junit.Assert.assertNotNull(localDateTime7);
    }

    @Test
    public void test0354() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0354");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology2 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology1);
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology1);
        org.joda.time.MonthDay monthDay4 = new org.joda.time.MonthDay((org.joda.time.Chronology) lenientChronology3);
        org.joda.time.MutableDateTime mutableDateTime5 = new org.joda.time.MutableDateTime(1786007462709L, (org.joda.time.Chronology) lenientChronology3);
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(lenientChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
    }

    @Test
    public void test0355() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0355");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Interval interval2 = yearMonth0.toInterval(dateTimeZone1);
        org.joda.time.DateTimeZone dateTimeZone3 = null;
        org.joda.time.LocalDateTime localDateTime4 = new org.joda.time.LocalDateTime(dateTimeZone3);
        int int5 = localDateTime4.getYear();
        org.joda.time.LocalDateTime localDateTime7 = localDateTime4.plusMillis(10);
        org.joda.time.chrono.GJChronology gJChronology8 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj9 = null;
        boolean boolean10 = gJChronology8.equals(obj9);
        org.joda.time.DurationField durationField11 = gJChronology8.days();
        org.joda.time.chrono.GJChronology gJChronology12 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology13 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology12);
        org.joda.time.chrono.LenientChronology lenientChronology14 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology12);
        org.joda.time.DateTimeField dateTimeField15 = lenientChronology14.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField16 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology8, dateTimeField15);
        org.joda.time.DateTimeFieldType dateTimeFieldType17 = skipUndoDateTimeField16.getType();
        int int18 = skipUndoDateTimeField16.getMinimumValue();
        java.lang.String str19 = skipUndoDateTimeField16.getName();
        org.joda.time.DateTimeFieldType dateTimeFieldType20 = skipUndoDateTimeField16.getType();
        org.joda.time.LocalDateTime localDateTime22 = localDateTime4.withField(dateTimeFieldType20, 24);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.YearMonth yearMonth24 = yearMonth0.withField(dateTimeFieldType20, 1440);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Field 'yearOfCentury' is not supported");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 2026 + "'", int5 == 2026);
        org.junit.Assert.assertNotNull(localDateTime7);
        org.junit.Assert.assertNotNull(gJChronology8);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(durationField11);
        org.junit.Assert.assertNotNull(gJChronology12);
        org.junit.Assert.assertNotNull(lenientChronology13);
        org.junit.Assert.assertNotNull(lenientChronology14);
        org.junit.Assert.assertNotNull(dateTimeField15);
        org.junit.Assert.assertNotNull(dateTimeFieldType17);
        org.junit.Assert.assertTrue("'" + int18 + "' != '" + 0 + "'", int18 == 0);
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "yearOfCentury" + "'", str19, "yearOfCentury");
        org.junit.Assert.assertNotNull(dateTimeFieldType20);
        org.junit.Assert.assertNotNull(localDateTime22);
    }

    @Test
    public void test0356() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0356");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTimeZone dateTimeZone1 = org.joda.time.DateTimeZone.forOffsetHours(2026);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Hours out of range: 2026");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0357() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0357");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = localDate2.withWeekyear((int) (byte) 100);
        org.joda.time.Chronology chronology7 = null;
        org.joda.time.DateMidnight dateMidnight8 = new org.joda.time.DateMidnight((long) (short) -1, chronology7);
        org.joda.time.DateMidnight dateMidnight10 = dateMidnight8.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property11 = dateMidnight8.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime12 = dateMidnight8.toMutableDateTimeISO();
        boolean boolean13 = localDate5.equals((java.lang.Object) mutableDateTime12);
        org.joda.time.LocalDate localDate15 = localDate5.plusMonths(10);
        org.joda.time.DateTime dateTime16 = localDate15.toDateTimeAtMidnight();
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(dateMidnight10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(mutableDateTime12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(localDate15);
        org.junit.Assert.assertNotNull(dateTime16);
    }

    @Test
    public void test0358() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0358");
        org.joda.time.PeriodType periodType1 = org.joda.time.PeriodType.millis();
        org.joda.time.PeriodType periodType2 = periodType1.withWeeksRemoved();
        org.joda.time.MutablePeriod mutablePeriod3 = new org.joda.time.MutablePeriod((long) (byte) 10, periodType2);
        int int4 = mutablePeriod3.getSeconds();
        // The following exception was thrown during execution in test generation
        try {
            mutablePeriod3.setWeeks(3);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Period does not support field 'weeks'");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(periodType1);
        org.junit.Assert.assertNotNull(periodType2);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
    }

    @Test
    public void test0359() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0359");
        org.joda.time.field.DividedDateTimeField dividedDateTimeField0 = null;
        org.joda.time.ReadableInterval readableInterval1 = null;
        org.joda.time.Seconds seconds2 = org.joda.time.Seconds.secondsIn(readableInterval1);
        org.joda.time.DurationFieldType durationFieldType3 = seconds2.getFieldType();
        org.joda.time.field.PreciseDurationField preciseDurationField5 = new org.joda.time.field.PreciseDurationField(durationFieldType3, (long) 59);
        org.joda.time.ReadableInterval readableInterval6 = null;
        org.joda.time.Seconds seconds7 = org.joda.time.Seconds.secondsIn(readableInterval6);
        org.joda.time.DurationFieldType durationFieldType8 = seconds7.getFieldType();
        org.joda.time.field.PreciseDurationField preciseDurationField10 = new org.joda.time.field.PreciseDurationField(durationFieldType8, (long) 59);
        int int11 = preciseDurationField5.compareTo((org.joda.time.DurationField) preciseDurationField10);
        org.joda.time.DurationFieldType durationFieldType12 = preciseDurationField5.getType();
        org.joda.time.DateTimeZone dateTimeZone13 = null;
        org.joda.time.LocalDateTime localDateTime14 = new org.joda.time.LocalDateTime(dateTimeZone13);
        int int15 = localDateTime14.getYear();
        org.joda.time.LocalDateTime localDateTime17 = localDateTime14.plusMillis(10);
        org.joda.time.chrono.GJChronology gJChronology18 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj19 = null;
        boolean boolean20 = gJChronology18.equals(obj19);
        org.joda.time.DurationField durationField21 = gJChronology18.days();
        org.joda.time.chrono.GJChronology gJChronology22 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology23 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology22);
        org.joda.time.chrono.LenientChronology lenientChronology24 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology22);
        org.joda.time.DateTimeField dateTimeField25 = lenientChronology24.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField26 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology18, dateTimeField25);
        org.joda.time.DateTimeFieldType dateTimeFieldType27 = skipUndoDateTimeField26.getType();
        int int28 = skipUndoDateTimeField26.getMinimumValue();
        java.lang.String str29 = skipUndoDateTimeField26.getName();
        org.joda.time.DateTimeFieldType dateTimeFieldType30 = skipUndoDateTimeField26.getType();
        org.joda.time.LocalDateTime localDateTime32 = localDateTime14.withField(dateTimeFieldType30, 24);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.field.RemainderDateTimeField remainderDateTimeField33 = new org.joda.time.field.RemainderDateTimeField(dividedDateTimeField0, (org.joda.time.DurationField) preciseDurationField5, dateTimeFieldType30);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(seconds2);
        org.junit.Assert.assertNotNull(durationFieldType3);
        org.junit.Assert.assertNotNull(seconds7);
        org.junit.Assert.assertNotNull(durationFieldType8);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertNotNull(durationFieldType12);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 2026 + "'", int15 == 2026);
        org.junit.Assert.assertNotNull(localDateTime17);
        org.junit.Assert.assertNotNull(gJChronology18);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
        org.junit.Assert.assertNotNull(durationField21);
        org.junit.Assert.assertNotNull(gJChronology22);
        org.junit.Assert.assertNotNull(lenientChronology23);
        org.junit.Assert.assertNotNull(lenientChronology24);
        org.junit.Assert.assertNotNull(dateTimeField25);
        org.junit.Assert.assertNotNull(dateTimeFieldType27);
        org.junit.Assert.assertTrue("'" + int28 + "' != '" + 0 + "'", int28 == 0);
        org.junit.Assert.assertEquals("'" + str29 + "' != '" + "yearOfCentury" + "'", str29, "yearOfCentury");
        org.junit.Assert.assertNotNull(dateTimeFieldType30);
        org.junit.Assert.assertNotNull(localDateTime32);
    }

    @Test
    public void test0360() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0360");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.dateTimeNoMillis();
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
    }

    @Test
    public void test0361() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0361");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        int int5 = dateMidnight2.getDayOfYear();
        org.joda.time.DateMidnight.Property property6 = dateMidnight2.yearOfCentury();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 1 + "'", int5 == 1);
        org.junit.Assert.assertNotNull(property6);
    }

    @Test
    public void test0362() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0362");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.DurationField durationField3 = gJChronology1.months();
        org.joda.time.DateMidnight dateMidnight5 = org.joda.time.DateMidnight.parse("2026-08-06T11:10:36.726");
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight5.minusYears((int) (byte) 100);
        org.joda.time.ReadableDateTime readableDateTime8 = null;
        org.joda.time.chrono.LimitChronology limitChronology9 = org.joda.time.chrono.LimitChronology.getInstance((org.joda.time.Chronology) gJChronology1, (org.joda.time.ReadableDateTime) dateMidnight5, readableDateTime8);
        org.joda.time.DateTime dateTime10 = limitChronology9.getUpperLimit();
        // The following exception was thrown during execution in test generation
        try {
            long long15 = limitChronology9.getDateTimeMillis(12, 86400000, 1000, 8);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 86400000 for monthOfYear must be in the range [1,12]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(durationField3);
        org.junit.Assert.assertNotNull(dateMidnight5);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(limitChronology9);
        org.junit.Assert.assertNull(dateTime10);
    }

    @Test
    public void test0363() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0363");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        int int10 = dateMidnight4.compareTo((org.joda.time.ReadableInstant) dateMidnight7);
        org.joda.time.chrono.GJChronology gJChronology11 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTime dateTime12 = dateMidnight7.toDateTime((org.joda.time.Chronology) gJChronology11);
        // The following exception was thrown during execution in test generation
        try {
            long long17 = gJChronology11.getDateTimeMillis(60, 9, 29, 86400000);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 86400000 for millisOfDay must be in the range [0,86399999]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + (-1) + "'", int10 == (-1));
        org.junit.Assert.assertNotNull(gJChronology11);
        org.junit.Assert.assertNotNull(dateTime12);
    }

    @Test
    public void test0364() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0364");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone4 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology5 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        java.lang.String str6 = copticChronology5.toString();
        org.joda.time.DateTimeField dateTimeField7 = copticChronology5.clockhourOfDay();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone13 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology14 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone13);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType15 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology17 = null;
        org.joda.time.DateMidnight dateMidnight18 = new org.joda.time.DateMidnight((long) (short) -1, chronology17);
        org.joda.time.DateMidnight dateMidnight20 = dateMidnight18.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property21 = dateMidnight18.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime22 = dateMidnight18.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property23 = mutableDateTime22.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime25 = property23.addWrapField((int) (short) -1);
        boolean boolean26 = leapYearPatternType15.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology27 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone13, leapYearPatternType15);
        org.joda.time.MutableDateTime mutableDateTime28 = new org.joda.time.MutableDateTime((long) (byte) 10, (org.joda.time.DateTimeZone) fixedDateTimeZone13);
        boolean boolean29 = copticChronology5.equals((java.lang.Object) mutableDateTime28);
        // The following exception was thrown during execution in test generation
        try {
            long long34 = copticChronology5.getDateTimeMillis(100, (int) (byte) -1, 59, 10);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value -1 for monthOfYear must be in the range [1,13]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(copticChronology5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "CopticChronology[2026-08-06T11:10:36.726]" + "'", str6, "CopticChronology[2026-08-06T11:10:36.726]");
        org.junit.Assert.assertNotNull(dateTimeField7);
        org.junit.Assert.assertNotNull(copticChronology14);
        org.junit.Assert.assertNotNull(leapYearPatternType15);
        org.junit.Assert.assertNotNull(dateMidnight20);
        org.junit.Assert.assertNotNull(property21);
        org.junit.Assert.assertNotNull(mutableDateTime22);
        org.junit.Assert.assertNotNull(property23);
        org.junit.Assert.assertNotNull(mutableDateTime25);
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + false + "'", boolean26 == false);
        org.junit.Assert.assertNotNull(islamicChronology27);
        org.junit.Assert.assertTrue("'" + boolean29 + "' != '" + false + "'", boolean29 == false);
    }

    @Test
    public void test0365() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0365");
        org.joda.time.Days days0 = org.joda.time.Days.SEVEN;
        org.junit.Assert.assertNotNull(days0);
    }

    @Test
    public void test0366() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0366");
        int int0 = org.joda.time.DateTimeConstants.SATURDAY;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 6 + "'", int0 == 6);
    }

    @Test
    public void test0367() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0367");
        org.joda.time.chrono.GJChronology gJChronology0 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj1 = null;
        boolean boolean2 = gJChronology0.equals(obj1);
        org.joda.time.DurationField durationField3 = gJChronology0.days();
        org.joda.time.chrono.GJChronology gJChronology4 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology5 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology4);
        org.joda.time.chrono.LenientChronology lenientChronology6 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology4);
        org.joda.time.DateTimeField dateTimeField7 = lenientChronology6.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField8 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology0, dateTimeField7);
        org.joda.time.DateTimeFieldType dateTimeFieldType9 = skipUndoDateTimeField8.getType();
        int int10 = skipUndoDateTimeField8.getMinimumValue();
        org.joda.time.DateTimeField dateTimeField11 = skipUndoDateTimeField8.getWrappedField();
        org.junit.Assert.assertNotNull(gJChronology0);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(durationField3);
        org.junit.Assert.assertNotNull(gJChronology4);
        org.junit.Assert.assertNotNull(lenientChronology5);
        org.junit.Assert.assertNotNull(lenientChronology6);
        org.junit.Assert.assertNotNull(dateTimeField7);
        org.junit.Assert.assertNotNull(dateTimeFieldType9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
        org.junit.Assert.assertNotNull(dateTimeField11);
    }

    @Test
    public void test0368() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0368");
        org.joda.time.ReadableInterval readableInterval0 = null;
        org.joda.time.Seconds seconds1 = org.joda.time.Seconds.secondsIn(readableInterval0);
        org.joda.time.DurationFieldType durationFieldType2 = seconds1.getFieldType();
        org.joda.time.field.PreciseDurationField preciseDurationField4 = new org.joda.time.field.PreciseDurationField(durationFieldType2, (long) 59);
        java.lang.String str5 = preciseDurationField4.getName();
        long long8 = preciseDurationField4.add((-59011462664000L), 8);
        org.junit.Assert.assertNotNull(seconds1);
        org.junit.Assert.assertNotNull(durationFieldType2);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "seconds" + "'", str5, "seconds");
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-59011462663528L) + "'", long8 == (-59011462663528L));
    }

    @Test
    public void test0369() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0369");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.withYear(100);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.plusDays(10);
        boolean boolean12 = dateMidnight9.isEqualNow();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
    }

    @Test
    public void test0370() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0370");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter1 = org.joda.time.format.ISODateTimeFormat.dateHourMinuteSecondFraction();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MonthDay monthDay2 = org.joda.time.MonthDay.parse("hi!", dateTimeFormatter1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid format: \"hi!\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeFormatter1);
    }

    @Test
    public void test0371() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0371");
        org.joda.time.Weeks weeks0 = org.joda.time.Weeks.MIN_VALUE;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Weeks weeks2 = weeks0.multipliedBy((int) (byte) 100);
            org.junit.Assert.fail("Expected exception of type java.lang.ArithmeticException; message: Multiplication overflows an int: -2147483648 * 100");
        } catch (java.lang.ArithmeticException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(weeks0);
    }

    @Test
    public void test0372() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0372");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.DateMidnight dateMidnight7 = property5.addWrapFieldToCopy(86400000);
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone13 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology14 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone13);
        org.joda.time.LocalDateTime localDateTime15 = new org.joda.time.LocalDateTime((long) (-1), (org.joda.time.DateTimeZone) fixedDateTimeZone13);
        org.joda.time.tz.CachedDateTimeZone cachedDateTimeZone16 = org.joda.time.tz.CachedDateTimeZone.forZone((org.joda.time.DateTimeZone) fixedDateTimeZone13);
        long long19 = cachedDateTimeZone16.adjustOffset((long) (short) -1, true);
        org.joda.time.DateMidnight dateMidnight20 = dateMidnight7.withZoneRetainFields((org.joda.time.DateTimeZone) cachedDateTimeZone16);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MonthDay monthDay21 = new org.joda.time.MonthDay((java.lang.Object) cachedDateTimeZone16);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No partial converter found for type: org.joda.time.tz.CachedDateTimeZone");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(gregorianChronology14);
        org.junit.Assert.assertNotNull(cachedDateTimeZone16);
        org.junit.Assert.assertTrue("'" + long19 + "' != '" + (-1L) + "'", long19 == (-1L));
        org.junit.Assert.assertNotNull(dateMidnight20);
    }

    @Test
    public void test0373() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0373");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = property3.addToCopy(1);
        org.joda.time.Chronology chronology7 = null;
        org.joda.time.DateMidnight dateMidnight8 = new org.joda.time.DateMidnight((long) (short) -1, chronology7);
        org.joda.time.DateMidnight dateMidnight10 = dateMidnight8.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology12 = null;
        org.joda.time.DateMidnight dateMidnight13 = new org.joda.time.DateMidnight((long) (short) -1, chronology12);
        org.joda.time.DateMidnight dateMidnight15 = dateMidnight13.minusMonths((int) (byte) 100);
        int int16 = dateMidnight10.compareTo((org.joda.time.ReadableInstant) dateMidnight13);
        long long17 = property3.getDifferenceAsLong((org.joda.time.ReadableInstant) dateMidnight10);
        org.joda.time.DateMidnight.Property property18 = dateMidnight10.yearOfEra();
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(dateMidnight10);
        org.junit.Assert.assertNotNull(dateMidnight15);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + (-1) + "'", int16 == (-1));
        org.junit.Assert.assertTrue("'" + long17 + "' != '" + 8L + "'", long17 == 8L);
        org.junit.Assert.assertNotNull(property18);
    }

    @Test
    public void test0374() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0374");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime8 = property7.roundFloor();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MutableDateTime mutableDateTime10 = property7.set("[]");
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value \"[]\" for centuryOfEra is not supported");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(mutableDateTime8);
    }

    @Test
    public void test0375() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0375");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar8 = dateMidnight7.toGregorianCalendar();
        org.joda.time.LocalTime localTime9 = org.joda.time.LocalTime.fromCalendarFields((java.util.Calendar) gregorianCalendar8);
        org.joda.time.LocalTime.Property property10 = localTime9.minuteOfHour();
        org.joda.time.LocalTime.Property property11 = localTime9.millisOfSecond();
        org.joda.time.LocalTime localTime13 = property11.addCopy(0L);
        int int14 = localTime13.getMinuteOfHour();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(gregorianCalendar8);
        org.junit.Assert.assertNotNull(localTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(localTime13);
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 0 + "'", int14 == 0);
    }

    @Test
    public void test0376() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0376");
        org.joda.time.chrono.GJChronology gJChronology0 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology1 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology0);
        org.joda.time.chrono.LenientChronology lenientChronology2 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology0);
        org.joda.time.LocalDate localDate3 = new org.joda.time.LocalDate((org.joda.time.Chronology) gJChronology0);
        org.junit.Assert.assertNotNull(gJChronology0);
        org.junit.Assert.assertNotNull(lenientChronology1);
        org.junit.Assert.assertNotNull(lenientChronology2);
    }

    @Test
    public void test0377() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0377");
        org.joda.time.Days days0 = org.joda.time.Days.ONE;
        org.joda.time.DurationFieldType durationFieldType1 = days0.getFieldType();
        org.joda.time.Days days2 = days0.negated();
        org.joda.time.Days days3 = org.joda.time.Days.ONE;
        boolean boolean4 = days2.isGreaterThan(days3);
        org.joda.time.MonthDay monthDay5 = org.joda.time.MonthDay.now();
        org.joda.time.Days days6 = org.joda.time.Days.ONE;
        org.joda.time.MonthDay monthDay8 = monthDay5.withPeriodAdded((org.joda.time.ReadablePeriod) days6, 40237996);
        org.joda.time.Days days9 = days3.plus(days6);
        org.junit.Assert.assertNotNull(days0);
        org.junit.Assert.assertNotNull(durationFieldType1);
        org.junit.Assert.assertNotNull(days2);
        org.junit.Assert.assertNotNull(days3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(monthDay5);
        org.junit.Assert.assertNotNull(days6);
        org.junit.Assert.assertNotNull(monthDay8);
        org.junit.Assert.assertNotNull(days9);
    }

    @Test
    public void test0378() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0378");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        boolean boolean8 = property7.isLeap();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0379() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0379");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.withYear(100);
        org.joda.time.Chronology chronology11 = null;
        org.joda.time.DateMidnight dateMidnight12 = new org.joda.time.DateMidnight((long) (short) -1, chronology11);
        org.joda.time.Interval interval13 = new org.joda.time.Interval((org.joda.time.ReadableInstant) dateMidnight9, (org.joda.time.ReadableInstant) dateMidnight12);
        org.joda.time.Period period15 = org.joda.time.Period.weeks((int) (short) 100);
        org.joda.time.Interval interval16 = interval13.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) period15);
        org.joda.time.MutablePeriod mutablePeriod21 = new org.joda.time.MutablePeriod((int) 'a', 29, (int) (byte) 100, 40243947);
        org.joda.time.YearMonth yearMonth22 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone23 = null;
        org.joda.time.Interval interval24 = yearMonth22.toInterval(dateTimeZone23);
        org.joda.time.Months months25 = org.joda.time.Months.ONE;
        org.joda.time.Interval interval26 = interval24.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) months25);
        mutablePeriod21.add((org.joda.time.ReadableInterval) interval24);
        boolean boolean28 = interval16.overlaps((org.joda.time.ReadableInterval) interval24);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(period15);
        org.junit.Assert.assertNotNull(interval16);
        org.junit.Assert.assertNotNull(interval24);
        org.junit.Assert.assertNotNull(months25);
        org.junit.Assert.assertNotNull(interval26);
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + false + "'", boolean28 == false);
    }

    @Test
    public void test0380() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0380");
        org.joda.time.chrono.GJChronology gJChronology0 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj1 = null;
        boolean boolean2 = gJChronology0.equals(obj1);
        org.joda.time.DurationField durationField3 = gJChronology0.days();
        org.joda.time.chrono.GJChronology gJChronology4 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology5 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology4);
        org.joda.time.chrono.LenientChronology lenientChronology6 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology4);
        org.joda.time.DateTimeField dateTimeField7 = lenientChronology6.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField8 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology0, dateTimeField7);
        long long10 = skipUndoDateTimeField8.remainder((long) 59);
        // The following exception was thrown during execution in test generation
        try {
            long long13 = skipUndoDateTimeField8.set(40238396L, "1 year, -1 weeks, -1 days, 1 hour, 100 seconds and 3 milliseconds");
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value \"1 year, -1 weeks, -1 days, 1 hour, 100 seconds and 3 milliseconds\" for yearOfCentury is not supported");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gJChronology0);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(durationField3);
        org.junit.Assert.assertNotNull(gJChronology4);
        org.junit.Assert.assertNotNull(lenientChronology5);
        org.junit.Assert.assertNotNull(lenientChronology6);
        org.junit.Assert.assertNotNull(dateTimeField7);
        org.junit.Assert.assertTrue("'" + long10 + "' != '" + 3600059L + "'", long10 == 3600059L);
    }

    @Test
    public void test0381() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0381");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = property3.addToCopy(1);
        int int6 = property3.get();
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 69 + "'", int6 == 69);
    }

    @Test
    public void test0382() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0382");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.MonthDay monthDay4 = monthDay2.minusDays((int) '4');
        org.joda.time.MonthDay.Property property5 = monthDay2.monthOfYear();
        org.joda.time.Chronology chronology7 = null;
        org.joda.time.DateMidnight dateMidnight8 = new org.joda.time.DateMidnight((long) (short) -1, chronology7);
        org.joda.time.DateMidnight dateMidnight10 = dateMidnight8.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property11 = dateMidnight8.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime12 = dateMidnight8.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property13 = mutableDateTime12.centuryOfEra();
        mutableDateTime12.setSecondOfMinute(0);
        mutableDateTime12.add((long) 86400000);
        int int18 = property5.compareTo((org.joda.time.ReadableInstant) mutableDateTime12);
        org.joda.time.MutableDateTime.Property property19 = mutableDateTime12.dayOfMonth();
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(monthDay4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(dateMidnight10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(mutableDateTime12);
        org.junit.Assert.assertNotNull(property13);
        org.junit.Assert.assertTrue("'" + int18 + "' != '" + 0 + "'", int18 == 0);
        org.junit.Assert.assertNotNull(property19);
    }

    @Test
    public void test0383() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0383");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = localDate2.withWeekyear((int) (byte) 100);
        org.joda.time.LocalDate.Property property6 = localDate5.yearOfCentury();
        org.joda.time.LocalDate localDate7 = property6.withMinimumValue();
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(property6);
        org.junit.Assert.assertNotNull(localDate7);
    }

    @Test
    public void test0384() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0384");
        org.joda.time.IllegalFieldValueException illegalFieldValueException2 = new org.joda.time.IllegalFieldValueException("2026-08-06T11:10:39.499", "hi!");
        java.lang.Number number3 = illegalFieldValueException2.getIllegalNumberValue();
        org.junit.Assert.assertNull(number3);
    }

    @Test
    public void test0385() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0385");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.MonthDay monthDay4 = monthDay2.minusDays((int) '4');
        org.joda.time.MonthDay.Property property5 = monthDay2.monthOfYear();
        java.lang.String str6 = property5.getName();
        org.joda.time.DateTimeField dateTimeField7 = property5.getField();
        java.util.Locale locale8 = null;
        java.lang.String str9 = property5.getAsText(locale8);
        org.joda.time.MonthDay monthDay11 = property5.addToCopy((int) (short) 100);
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(monthDay4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "monthOfYear" + "'", str6, "monthOfYear");
        org.junit.Assert.assertNotNull(dateTimeField7);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "January" + "'", str9, "January");
        org.junit.Assert.assertNotNull(monthDay11);
    }

    @Test
    public void test0386() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0386");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone8 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology9 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone8);
        org.joda.time.LocalDateTime localDateTime10 = new org.joda.time.LocalDateTime((long) (-1), (org.joda.time.DateTimeZone) fixedDateTimeZone8);
        org.joda.time.tz.CachedDateTimeZone cachedDateTimeZone11 = org.joda.time.tz.CachedDateTimeZone.forZone((org.joda.time.DateTimeZone) fixedDateTimeZone8);
        long long13 = fixedDateTimeZone8.previousTransition(1L);
        java.lang.String str15 = fixedDateTimeZone8.getName((long) '#');
        org.joda.time.DateTime dateTime16 = new org.joda.time.DateTime((long) 2026, (org.joda.time.DateTimeZone) fixedDateTimeZone8);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Interval interval17 = new org.joda.time.Interval(40238396L, (long) (short) 10, (org.joda.time.DateTimeZone) fixedDateTimeZone8);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The end instant must be greater than the start instant");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gregorianChronology9);
        org.junit.Assert.assertNotNull(cachedDateTimeZone11);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + 1L + "'", long13 == 1L);
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "+00:00:00.100" + "'", str15, "+00:00:00.100");
    }

    @Test
    public void test0387() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0387");
        int int0 = org.joda.time.DateTimeConstants.MARCH;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 3 + "'", int0 == 3);
    }

    @Test
    public void test0388() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0388");
        int int0 = org.joda.time.YearMonth.MONTH_OF_YEAR;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 1 + "'", int0 == 1);
    }

    @Test
    public void test0389() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0389");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone5 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology6 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType7 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology9 = null;
        org.joda.time.DateMidnight dateMidnight10 = new org.joda.time.DateMidnight((long) (short) -1, chronology9);
        org.joda.time.DateMidnight dateMidnight12 = dateMidnight10.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property13 = dateMidnight10.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime14 = dateMidnight10.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property15 = mutableDateTime14.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime17 = property15.addWrapField((int) (short) -1);
        boolean boolean18 = leapYearPatternType7.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology19 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5, leapYearPatternType7);
        org.joda.time.MutableDateTime mutableDateTime20 = new org.joda.time.MutableDateTime((long) (byte) 10, (org.joda.time.DateTimeZone) fixedDateTimeZone5);
        int int22 = fixedDateTimeZone5.getOffsetFromLocal((long) 5);
        int int24 = fixedDateTimeZone5.getOffsetFromLocal((long) 20);
        org.junit.Assert.assertNotNull(copticChronology6);
        org.junit.Assert.assertNotNull(leapYearPatternType7);
        org.junit.Assert.assertNotNull(dateMidnight12);
        org.junit.Assert.assertNotNull(property13);
        org.junit.Assert.assertNotNull(mutableDateTime14);
        org.junit.Assert.assertNotNull(property15);
        org.junit.Assert.assertNotNull(mutableDateTime17);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertNotNull(islamicChronology19);
        org.junit.Assert.assertTrue("'" + int22 + "' != '" + 100 + "'", int22 == 100);
        org.junit.Assert.assertTrue("'" + int24 + "' != '" + 100 + "'", int24 == 100);
    }

    @Test
    public void test0390() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0390");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.Period period5 = period3.plusMinutes((int) (short) 100);
        org.joda.time.Period period7 = period3.plusMinutes((int) (short) 0);
        org.junit.Assert.assertNotNull(period5);
        org.junit.Assert.assertNotNull(period7);
    }

    @Test
    public void test0391() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0391");
        java.lang.Object obj0 = null;
        org.joda.time.YearMonth yearMonth1 = new org.joda.time.YearMonth(obj0);
    }

    @Test
    public void test0392() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0392");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Interval interval2 = yearMonth0.toInterval(dateTimeZone1);
        org.joda.time.Months months3 = org.joda.time.Months.ONE;
        org.joda.time.Interval interval4 = interval2.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) months3);
        org.joda.time.Months months6 = months3.plus(40243947);
        org.joda.time.Months months7 = org.joda.time.Months.SEVEN;
        org.joda.time.Months months8 = null;
        org.joda.time.Months months9 = months7.minus(months8);
        org.joda.time.Months months10 = months6.minus(months8);
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertNotNull(months3);
        org.junit.Assert.assertNotNull(interval4);
        org.junit.Assert.assertNotNull(months6);
        org.junit.Assert.assertNotNull(months7);
        org.junit.Assert.assertNotNull(months9);
        org.junit.Assert.assertNotNull(months10);
    }

    @Test
    public void test0393() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0393");
        org.joda.time.ReadableInterval readableInterval0 = null;
        org.joda.time.Seconds seconds1 = org.joda.time.Seconds.secondsIn(readableInterval0);
        org.joda.time.ReadableInterval readableInterval2 = null;
        org.joda.time.Seconds seconds3 = org.joda.time.Seconds.secondsIn(readableInterval2);
        org.joda.time.Seconds seconds4 = seconds1.plus(seconds3);
        org.joda.time.Duration duration5 = seconds4.toStandardDuration();
        org.junit.Assert.assertNotNull(seconds1);
        org.junit.Assert.assertNotNull(seconds3);
        org.junit.Assert.assertNotNull(seconds4);
        org.junit.Assert.assertNotNull(duration5);
    }

    @Test
    public void test0394() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0394");
        org.joda.time.chrono.GregorianChronology gregorianChronology0 = org.joda.time.chrono.GregorianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField1 = gregorianChronology0.minuteOfDay();
        org.joda.time.DateTime dateTime2 = org.joda.time.DateTime.now((org.joda.time.Chronology) gregorianChronology0);
        int int3 = gregorianChronology0.getMinimumDaysInFirstWeek();
        java.lang.String str4 = gregorianChronology0.toString();
        org.junit.Assert.assertNotNull(gregorianChronology0);
        org.junit.Assert.assertNotNull(dateTimeField1);
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 4 + "'", int3 == 4);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "GregorianChronology[Europe/Prague]" + "'", str4, "GregorianChronology[Europe/Prague]");
    }

    @Test
    public void test0395() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0395");
        org.joda.time.Minutes minutes0 = org.joda.time.Minutes.ZERO;
        org.junit.Assert.assertNotNull(minutes0);
    }

    @Test
    public void test0396() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0396");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusYears(100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.monthOfYear();
        org.joda.time.DateTimeField dateTimeField6 = property5.getField();
        java.util.Locale locale8 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateMidnight dateMidnight9 = property5.setCopy("2029-05-02T11:10:57.360", locale8);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value \"2029-05-02T11:10:57.360\" for monthOfYear is not supported");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(dateTimeField6);
    }

    @Test
    public void test0397() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0397");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone5 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology6 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType7 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology9 = null;
        org.joda.time.DateMidnight dateMidnight10 = new org.joda.time.DateMidnight((long) (short) -1, chronology9);
        org.joda.time.DateMidnight dateMidnight12 = dateMidnight10.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property13 = dateMidnight10.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime14 = dateMidnight10.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property15 = mutableDateTime14.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime17 = property15.addWrapField((int) (short) -1);
        boolean boolean18 = leapYearPatternType7.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology19 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5, leapYearPatternType7);
        org.joda.time.MutableDateTime mutableDateTime20 = new org.joda.time.MutableDateTime((long) (byte) 10, (org.joda.time.DateTimeZone) fixedDateTimeZone5);
        mutableDateTime20.setSecondOfDay(32);
        long long23 = mutableDateTime20.getMillis();
        org.joda.time.chrono.GJChronology gJChronology24 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj25 = null;
        boolean boolean26 = gJChronology24.equals(obj25);
        org.joda.time.DurationField durationField27 = gJChronology24.days();
        org.joda.time.chrono.GJChronology gJChronology28 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology29 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology28);
        org.joda.time.chrono.LenientChronology lenientChronology30 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology28);
        org.joda.time.DateTimeField dateTimeField31 = lenientChronology30.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField32 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology24, dateTimeField31);
        org.joda.time.DateTimeFieldType dateTimeFieldType33 = skipUndoDateTimeField32.getType();
        int int34 = skipUndoDateTimeField32.getMinimumValue();
        int int35 = mutableDateTime20.get((org.joda.time.DateTimeField) skipUndoDateTimeField32);
        // The following exception was thrown during execution in test generation
        try {
            mutableDateTime20.setDayOfYear(1000);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 1000 for dayOfYear must be in the range [1,365]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(copticChronology6);
        org.junit.Assert.assertNotNull(leapYearPatternType7);
        org.junit.Assert.assertNotNull(dateMidnight12);
        org.junit.Assert.assertNotNull(property13);
        org.junit.Assert.assertNotNull(mutableDateTime14);
        org.junit.Assert.assertNotNull(property15);
        org.junit.Assert.assertNotNull(mutableDateTime17);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertNotNull(islamicChronology19);
        org.junit.Assert.assertTrue("'" + long23 + "' != '" + 32010L + "'", long23 == 32010L);
        org.junit.Assert.assertNotNull(gJChronology24);
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + false + "'", boolean26 == false);
        org.junit.Assert.assertNotNull(durationField27);
        org.junit.Assert.assertNotNull(gJChronology28);
        org.junit.Assert.assertNotNull(lenientChronology29);
        org.junit.Assert.assertNotNull(lenientChronology30);
        org.junit.Assert.assertNotNull(dateTimeField31);
        org.junit.Assert.assertNotNull(dateTimeFieldType33);
        org.junit.Assert.assertTrue("'" + int34 + "' != '" + 0 + "'", int34 == 0);
        org.junit.Assert.assertTrue("'" + int35 + "' != '" + 70 + "'", int35 == 70);
    }

    @Test
    public void test0398() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0398");
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
        org.joda.time.Months months15 = org.joda.time.Months.monthsBetween((org.joda.time.ReadableInstant) mutableDateTime6, (org.joda.time.ReadableInstant) dateMidnight12);
        mutableDateTime6.setSecondOfDay(2026);
        mutableDateTime6.addSeconds(0);
        // The following exception was thrown during execution in test generation
        try {
            mutableDateTime6.setMinuteOfHour(1000);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 1000 for minuteOfHour must be in the range [0,59]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(dateMidnight14);
        org.junit.Assert.assertNotNull(months15);
    }

    @Test
    public void test0399() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0399");
        org.joda.time.Chronology chronology3 = null;
        org.joda.time.DateMidnight dateMidnight4 = new org.joda.time.DateMidnight((int) '4', 7, 7, chronology3);
    }

    @Test
    public void test0400() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0400");
        org.joda.time.format.PeriodFormatter periodFormatter1 = org.joda.time.format.ISOPeriodFormat.alternate();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Period period2 = org.joda.time.Period.parse("+00:00:00.100", periodFormatter1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid format: \"+00:00:00.100\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(periodFormatter1);
    }

    @Test
    public void test0401() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0401");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone4 = new org.joda.time.tz.FixedDateTimeZone("GregorianChronology[Europe/Prague]", "2026-08-06T11:10:36.726", 100, (int) (short) 1);
    }

    @Test
    public void test0402() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0402");
        org.joda.time.chrono.BuddhistChronology buddhistChronology0 = org.joda.time.chrono.BuddhistChronology.getInstance();
        org.junit.Assert.assertNotNull(buddhistChronology0);
    }

    @Test
    public void test0403() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0403");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        int int8 = skipUndoDateTimeField6.get((long) 10);
        int int10 = skipUndoDateTimeField6.getMaximumValue((long) 53);
        int int11 = skipUndoDateTimeField6.getMinimumValue();
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 2 + "'", int8 == 2);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 53 + "'", int10 == 53);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 2 + "'", int11 == 2);
    }

    @Test
    public void test0404() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0404");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar8 = dateMidnight7.toGregorianCalendar();
        org.joda.time.LocalTime localTime9 = org.joda.time.LocalTime.fromCalendarFields((java.util.Calendar) gregorianCalendar8);
        org.joda.time.LocalTime.Property property10 = localTime9.minuteOfHour();
        org.joda.time.LocalTime.Property property11 = localTime9.millisOfSecond();
        org.joda.time.LocalTime localTime13 = localTime9.withSecondOfMinute(9);
        org.joda.time.LocalTime.Property property14 = localTime9.hourOfDay();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str16 = localTime9.toString("org.joda.time.IllegalFieldValueException: Value \"CopticChronology[2026-08-06T11:10:36.726]\" for PT60S is not supported");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Illegal pattern component: o");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(gregorianCalendar8);
        org.junit.Assert.assertNotNull(localTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(localTime13);
        org.junit.Assert.assertNotNull(property14);
    }

    @Test
    public void test0405() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0405");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.MonthDay monthDay4 = monthDay2.minusDays((int) '4');
        org.joda.time.MonthDay.Property property5 = monthDay2.monthOfYear();
        java.lang.String str6 = property5.toString();
        java.lang.String str7 = property5.getAsShortText();
        int int8 = property5.getMinimumValueOverall();
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(monthDay4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "Property[monthOfYear]" + "'", str6, "Property[monthOfYear]");
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "Jan" + "'", str7, "Jan");
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 1 + "'", int8 == 1);
    }

    @Test
    public void test0406() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0406");
        java.lang.StringBuffer stringBuffer0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.format.FormatUtils.appendPaddedInteger(stringBuffer0, 1L, 0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0407() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0407");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.withYear(100);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.plusDays(10);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateMidnight dateMidnight13 = dateMidnight11.withYearOfCentury(86400);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 86400 for yearOfCentury must be in the range [0,99]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(dateMidnight11);
    }

    @Test
    public void test0408() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0408");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        org.joda.time.ReadableInterval readableInterval5 = null;
        org.joda.time.Seconds seconds6 = org.joda.time.Seconds.secondsIn(readableInterval5);
        org.joda.time.DurationFieldType durationFieldType7 = seconds6.getFieldType();
        org.joda.time.LocalDateTime localDateTime9 = localDateTime1.withFieldAdded(durationFieldType7, 86400000);
        org.joda.time.LocalDateTime localDateTime11 = localDateTime9.plusMillis((int) (byte) 10);
        int int12 = localDateTime11.getMinuteOfHour();
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(seconds6);
        org.junit.Assert.assertNotNull(durationFieldType7);
        org.junit.Assert.assertNotNull(localDateTime9);
        org.junit.Assert.assertNotNull(localDateTime11);
// flaky "10) test0408(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int12 + "' != '" + 11 + "'", int12 == 11);
    }

    @Test
    public void test0409() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0409");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        org.joda.time.ReadableInterval readableInterval5 = null;
        org.joda.time.Seconds seconds6 = org.joda.time.Seconds.secondsIn(readableInterval5);
        org.joda.time.DurationFieldType durationFieldType7 = seconds6.getFieldType();
        org.joda.time.LocalDateTime localDateTime9 = localDateTime1.withFieldAdded(durationFieldType7, 86400000);
        java.lang.String str10 = localDateTime9.toString();
        boolean boolean12 = localDateTime9.equals((java.lang.Object) (-1.0d));
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str14 = localDateTime9.toString("seconds");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Illegal pattern component: c");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(seconds6);
        org.junit.Assert.assertNotNull(durationFieldType7);
        org.junit.Assert.assertNotNull(localDateTime9);
// flaky "11) test0409(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str10 + "' != '" + "2029-05-02T11:11:08.513" + "'", str10, "2029-05-02T11:11:08.513");
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
    }

    @Test
    public void test0410() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0410");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        mutableDateTime6.setSecondOfMinute(24);
        org.joda.time.DateTime dateTime10 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone11 = null;
        org.joda.time.DateTime dateTime12 = dateTime10.withZoneRetainFields(dateTimeZone11);
        boolean boolean13 = mutableDateTime6.isAfter((org.joda.time.ReadableInstant) dateTime10);
        org.joda.time.DateTime dateTime15 = dateTime10.minusMillis(3);
        org.joda.time.DateTime.Property property16 = dateTime10.millisOfSecond();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(dateTime12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(dateTime15);
        org.junit.Assert.assertNotNull(property16);
    }

    @Test
    public void test0411() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0411");
        org.joda.time.Hours hours0 = org.joda.time.Hours.ONE;
        org.junit.Assert.assertNotNull(hours0);
    }

    @Test
    public void test0412() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0412");
        org.joda.time.DateTimeUtils.setCurrentMillisFixed(1786007456115L);
    }

    @Test
    public void test0413() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0413");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.MutableDateTime mutableDateTime3 = dateMidnight2.toMutableDateTime();
        org.joda.time.MutableDateTime.Property property4 = mutableDateTime3.millisOfSecond();
        org.joda.time.MutableDateTime.Property property5 = mutableDateTime3.monthOfYear();
        org.joda.time.Chronology chronology7 = null;
        org.joda.time.DateMidnight dateMidnight8 = new org.joda.time.DateMidnight((long) (short) -1, chronology7);
        org.joda.time.DateMidnight dateMidnight10 = dateMidnight8.minusYears(100);
        org.joda.time.DateMidnight.Property property11 = dateMidnight8.monthOfYear();
        org.joda.time.Chronology chronology12 = org.joda.time.DateTimeUtils.getIntervalChronology((org.joda.time.ReadableInstant) mutableDateTime3, (org.joda.time.ReadableInstant) dateMidnight8);
        org.junit.Assert.assertNotNull(mutableDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(dateMidnight10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(chronology12);
    }

    @Test
    public void test0414() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0414");
        org.joda.time.Minutes minutes1 = org.joda.time.Minutes.minutes(70);
        org.joda.time.format.PeriodFormatter periodFormatter2 = org.joda.time.format.PeriodFormat.wordBased();
        org.joda.time.format.PeriodParser periodParser3 = periodFormatter2.getParser();
        boolean boolean4 = minutes1.equals((java.lang.Object) periodParser3);
        org.junit.Assert.assertNotNull(minutes1);
        org.junit.Assert.assertNotNull(periodFormatter2);
        org.junit.Assert.assertNotNull(periodParser3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
    }

    @Test
    public void test0415() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0415");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.hourMinuteSecondFraction();
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
    }

    @Test
    public void test0416() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0416");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.withYear(100);
        org.joda.time.Chronology chronology11 = null;
        org.joda.time.DateMidnight dateMidnight12 = new org.joda.time.DateMidnight((long) (short) -1, chronology11);
        org.joda.time.Interval interval13 = new org.joda.time.Interval((org.joda.time.ReadableInstant) dateMidnight9, (org.joda.time.ReadableInstant) dateMidnight12);
        org.joda.time.Period period15 = org.joda.time.Period.weeks((int) (short) 100);
        org.joda.time.Interval interval16 = interval13.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) period15);
        org.joda.time.Period period18 = period15.withHours(70);
        org.joda.time.Period period19 = period18.normalizedStandard();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(period15);
        org.junit.Assert.assertNotNull(interval16);
        org.junit.Assert.assertNotNull(period18);
        org.junit.Assert.assertNotNull(period19);
    }

    @Test
    public void test0417() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0417");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.Months months4 = org.joda.time.Months.FIVE;
        org.joda.time.Period period5 = period3.minus((org.joda.time.ReadablePeriod) months4);
        org.joda.time.Period period7 = period3.plusMinutes((int) ' ');
        org.joda.time.DurationFieldType[] durationFieldTypeArray8 = period3.getFieldTypes();
        org.joda.time.Period period10 = period3.minusDays((int) (short) 100);
        int int11 = period3.getHours();
        org.junit.Assert.assertNotNull(months4);
        org.junit.Assert.assertNotNull(period5);
        org.junit.Assert.assertNotNull(period7);
        org.junit.Assert.assertNotNull(durationFieldTypeArray8);
        org.junit.Assert.assertNotNull(period10);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
    }

    @Test
    public void test0418() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0418");
        org.joda.time.chrono.GJChronology gJChronology0 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology1 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology0);
        org.joda.time.chrono.LenientChronology lenientChronology2 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology0);
        org.joda.time.MonthDay monthDay3 = new org.joda.time.MonthDay((org.joda.time.Chronology) lenientChronology2);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTimeFieldType dateTimeFieldType5 = monthDay3.getFieldType(59);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 59");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gJChronology0);
        org.junit.Assert.assertNotNull(lenientChronology1);
        org.junit.Assert.assertNotNull(lenientChronology2);
    }

    @Test
    public void test0419() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0419");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter2 = org.joda.time.format.DateTimeFormat.longDate();
        org.joda.time.chrono.JulianChronology julianChronology3 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.format.DateTimeFormatter dateTimeFormatter4 = dateTimeFormatter2.withChronology((org.joda.time.Chronology) julianChronology3);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MutableInterval mutableInterval5 = new org.joda.time.MutableInterval((long) 1440, (long) 5, (org.joda.time.Chronology) julianChronology3);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The end instant must be greater than the start instant");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeFormatter2);
        org.junit.Assert.assertNotNull(julianChronology3);
        org.junit.Assert.assertNotNull(dateTimeFormatter4);
    }

    @Test
    public void test0420() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0420");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.MutableDateTime mutableDateTime3 = dateMidnight2.toMutableDateTime();
        mutableDateTime3.addMillis(69);
        org.junit.Assert.assertNotNull(mutableDateTime3);
    }

    @Test
    public void test0421() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0421");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.localDateParser();
        boolean boolean1 = dateTimeFormatter0.isOffsetParsed();
        int int2 = dateTimeFormatter0.getDefaultYear();
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 2000 + "'", int2 == 2000);
    }

    @Test
    public void test0422() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0422");
        org.joda.time.MutablePeriod mutablePeriod4 = new org.joda.time.MutablePeriod((int) 'a', 29, (int) (byte) 100, 40243947);
        org.joda.time.YearMonth yearMonth5 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone6 = null;
        org.joda.time.Interval interval7 = yearMonth5.toInterval(dateTimeZone6);
        org.joda.time.Months months8 = org.joda.time.Months.ONE;
        org.joda.time.Interval interval9 = interval7.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) months8);
        mutablePeriod4.add((org.joda.time.ReadableInterval) interval7);
        boolean boolean12 = interval7.isBefore(10L);
        org.junit.Assert.assertNotNull(interval7);
        org.junit.Assert.assertNotNull(months8);
        org.junit.Assert.assertNotNull(interval9);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
    }

    @Test
    public void test0423() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0423");
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType0 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_15_BASED;
        org.junit.Assert.assertNotNull(leapYearPatternType0);
    }

    @Test
    public void test0424() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0424");
        org.joda.time.Period period1 = org.joda.time.Period.minutes(40260602);
        org.junit.Assert.assertNotNull(period1);
    }

    @Test
    public void test0425() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0425");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.YearMonthDay yearMonthDay5 = dateTime0.toYearMonthDay();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone10 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.DateTime dateTime11 = yearMonthDay5.toDateTimeAtMidnight((org.joda.time.DateTimeZone) fixedDateTimeZone10);
        org.joda.time.LocalDate localDate12 = yearMonthDay5.toLocalDate();
        org.joda.time.DateTime dateTime13 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone14 = null;
        org.joda.time.DateTime dateTime15 = dateTime13.withZoneRetainFields(dateTimeZone14);
        org.joda.time.DateTime dateTime17 = dateTime13.withYear((int) (byte) 0);
        org.joda.time.YearMonthDay yearMonthDay18 = dateTime13.toYearMonthDay();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone23 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.DateTime dateTime24 = yearMonthDay18.toDateTimeAtMidnight((org.joda.time.DateTimeZone) fixedDateTimeZone23);
        org.joda.time.chrono.GJChronology gJChronology27 = org.joda.time.chrono.GJChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone23, (long) 3, 3);
        org.joda.time.Interval interval28 = yearMonthDay5.toInterval((org.joda.time.DateTimeZone) fixedDateTimeZone23);
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(yearMonthDay5);
        org.junit.Assert.assertNotNull(dateTime11);
        org.junit.Assert.assertNotNull(localDate12);
        org.junit.Assert.assertNotNull(dateTime15);
        org.junit.Assert.assertNotNull(dateTime17);
        org.junit.Assert.assertNotNull(yearMonthDay18);
        org.junit.Assert.assertNotNull(dateTime24);
        org.junit.Assert.assertNotNull(gJChronology27);
        org.junit.Assert.assertNotNull(interval28);
    }

    @Test
    public void test0426() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0426");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.YearMonthDay yearMonthDay5 = dateTime0.toYearMonthDay();
        org.joda.time.PeriodType periodType7 = null;
        org.joda.time.Chronology chronology8 = null;
        org.joda.time.Period period9 = new org.joda.time.Period((long) (short) 10, periodType7, chronology8);
        org.joda.time.Months months10 = org.joda.time.Months.FIVE;
        org.joda.time.Period period11 = period9.minus((org.joda.time.ReadablePeriod) months10);
        org.joda.time.Period period13 = period9.plusMillis((-1));
        org.joda.time.Period period14 = period9.toPeriod();
        org.joda.time.Period period16 = period14.minusSeconds((int) (byte) 1);
        org.joda.time.Period period18 = period14.withDays((int) (short) 100);
        org.joda.time.YearMonthDay yearMonthDay19 = yearMonthDay5.minus((org.joda.time.ReadablePeriod) period14);
        org.joda.time.YearMonthDay.Property property20 = yearMonthDay5.monthOfYear();
        org.joda.time.YearMonthDay yearMonthDay21 = property20.withMaximumValue();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.YearMonthDay yearMonthDay23 = property20.setCopy(51);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 51 for monthOfYear must be in the range [1,12]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(yearMonthDay5);
        org.junit.Assert.assertNotNull(months10);
        org.junit.Assert.assertNotNull(period11);
        org.junit.Assert.assertNotNull(period13);
        org.junit.Assert.assertNotNull(period14);
        org.junit.Assert.assertNotNull(period16);
        org.junit.Assert.assertNotNull(period18);
        org.junit.Assert.assertNotNull(yearMonthDay19);
        org.junit.Assert.assertNotNull(property20);
        org.junit.Assert.assertNotNull(yearMonthDay21);
    }

    @Test
    public void test0427() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0427");
        org.joda.time.Duration duration1 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Chronology chronology3 = null;
        org.joda.time.DateMidnight dateMidnight4 = new org.joda.time.DateMidnight((long) (short) -1, chronology3);
        org.joda.time.DateMidnight dateMidnight6 = dateMidnight4.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology8 = null;
        org.joda.time.DateMidnight dateMidnight9 = new org.joda.time.DateMidnight((long) (short) -1, chronology8);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology13 = null;
        org.joda.time.DateMidnight dateMidnight14 = new org.joda.time.DateMidnight((long) (short) -1, chronology13);
        org.joda.time.DateMidnight dateMidnight16 = dateMidnight14.minusMonths((int) (byte) 100);
        int int17 = dateMidnight11.compareTo((org.joda.time.ReadableInstant) dateMidnight14);
        org.joda.time.Minutes minutes18 = org.joda.time.Minutes.minutesBetween((org.joda.time.ReadableInstant) dateMidnight4, (org.joda.time.ReadableInstant) dateMidnight11);
        org.joda.time.DateMidnight dateMidnight21 = dateMidnight11.withDurationAdded((long) '4', 100);
        org.joda.time.MutableInterval mutableInterval22 = new org.joda.time.MutableInterval((org.joda.time.ReadableDuration) duration1, (org.joda.time.ReadableInstant) dateMidnight21);
        org.joda.time.Minutes minutes23 = duration1.toStandardMinutes();
        org.joda.time.DurationFieldType durationFieldType24 = minutes23.getFieldType();
        org.junit.Assert.assertNotNull(duration1);
        org.junit.Assert.assertNotNull(dateMidnight6);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(dateMidnight16);
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + (-1) + "'", int17 == (-1));
        org.junit.Assert.assertNotNull(minutes18);
        org.junit.Assert.assertNotNull(dateMidnight21);
        org.junit.Assert.assertNotNull(minutes23);
        org.junit.Assert.assertNotNull(durationFieldType24);
    }

    @Test
    public void test0428() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0428");
        org.joda.time.format.PeriodFormatter periodFormatter0 = org.joda.time.format.PeriodFormat.wordBased();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MutablePeriod mutablePeriod2 = periodFormatter0.parseMutablePeriod("2029-05-02T11:10:43.261");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid format: \"2029-05-02T11:10:43.261\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(periodFormatter0);
    }

    @Test
    public void test0429() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0429");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        org.joda.time.ReadableInterval readableInterval5 = null;
        org.joda.time.Seconds seconds6 = org.joda.time.Seconds.secondsIn(readableInterval5);
        org.joda.time.DurationFieldType durationFieldType7 = seconds6.getFieldType();
        org.joda.time.LocalDateTime localDateTime9 = localDateTime1.withFieldAdded(durationFieldType7, 86400000);
        boolean boolean11 = localDateTime9.equals((java.lang.Object) "2026-08-06T11:10:39.499");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDateTime localDateTime13 = localDateTime9.withDayOfMonth((int) (short) -1);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value -1 for dayOfMonth must be in the range [1,31]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(seconds6);
        org.junit.Assert.assertNotNull(durationFieldType7);
        org.junit.Assert.assertNotNull(localDateTime9);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test0430() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0430");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone4 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology5 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType6 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology8 = null;
        org.joda.time.DateMidnight dateMidnight9 = new org.joda.time.DateMidnight((long) (short) -1, chronology8);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property12 = dateMidnight9.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime13 = dateMidnight9.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property14 = mutableDateTime13.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime16 = property14.addWrapField((int) (short) -1);
        boolean boolean17 = leapYearPatternType6.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology18 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4, leapYearPatternType6);
        org.joda.time.YearMonthDay yearMonthDay19 = new org.joda.time.YearMonthDay((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray20 = yearMonthDay19.getFieldTypes();
        org.junit.Assert.assertNotNull(copticChronology5);
        org.junit.Assert.assertNotNull(leapYearPatternType6);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(property12);
        org.junit.Assert.assertNotNull(mutableDateTime13);
        org.junit.Assert.assertNotNull(property14);
        org.junit.Assert.assertNotNull(mutableDateTime16);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(islamicChronology18);
        org.junit.Assert.assertNotNull(dateTimeFieldTypeArray20);
    }

    @Test
    public void test0431() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0431");
        int int0 = org.joda.time.MonthDay.DAY_OF_MONTH;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 1 + "'", int0 == 1);
    }

    @Test
    public void test0432() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0432");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone5 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology6 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.LocalDateTime localDateTime7 = new org.joda.time.LocalDateTime((long) (-1), (org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.tz.CachedDateTimeZone cachedDateTimeZone8 = org.joda.time.tz.CachedDateTimeZone.forZone((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.chrono.ISOChronology iSOChronology9 = org.joda.time.chrono.ISOChronology.getInstance((org.joda.time.DateTimeZone) cachedDateTimeZone8);
        org.joda.time.DateMidnight dateMidnight10 = new org.joda.time.DateMidnight((org.joda.time.DateTimeZone) cachedDateTimeZone8);
        int int12 = cachedDateTimeZone8.getStandardOffset((long) 70);
        org.junit.Assert.assertNotNull(gregorianChronology6);
        org.junit.Assert.assertNotNull(cachedDateTimeZone8);
        org.junit.Assert.assertNotNull(iSOChronology9);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 0 + "'", int12 == 0);
    }

    @Test
    public void test0433() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0433");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTime.Property property1 = dateTime0.millisOfDay();
        org.joda.time.DateTime dateTime3 = property1.addToCopy(12);
        int int4 = property1.getLeapAmount();
        org.junit.Assert.assertNotNull(property1);
        org.junit.Assert.assertNotNull(dateTime3);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
    }

    @Test
    public void test0434() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0434");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Interval interval1 = org.joda.time.Interval.parse("2029-05-02T11:10:53.327");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Format requires a '/' separator: 2029-05-02T11:10:53.327");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0435() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0435");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = property3.addToCopy(1);
        long long6 = property3.remainder();
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertTrue("'" + long6 + "' != '" + 30412800000L + "'", long6 == 30412800000L);
    }

    @Test
    public void test0436() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0436");
        org.joda.time.TimeOfDay timeOfDay1 = new org.joda.time.TimeOfDay((long) 100);
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray2 = timeOfDay1.getFieldTypes();
        org.joda.time.chrono.GregorianChronology gregorianChronology3 = org.joda.time.chrono.GregorianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField4 = gregorianChronology3.minuteOfDay();
        org.joda.time.chrono.GJChronology gJChronology5 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology6 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology5);
        org.joda.time.DateTimeField dateTimeField7 = gJChronology5.minuteOfHour();
        org.joda.time.field.SkipDateTimeField skipDateTimeField9 = new org.joda.time.field.SkipDateTimeField((org.joda.time.Chronology) gregorianChronology3, dateTimeField7, 3);
        int int11 = skipDateTimeField9.get(3600059L);
        org.joda.time.chrono.JulianChronology julianChronology13 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate14 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology13);
        org.joda.time.LocalDate.Property property15 = localDate14.yearOfCentury();
        org.joda.time.LocalDate localDate17 = localDate14.withWeekyear((int) (byte) 100);
        java.util.Locale locale19 = null;
        java.lang.String str20 = skipDateTimeField9.getAsShortText((org.joda.time.ReadablePartial) localDate17, 40256602, locale19);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Hours hours21 = org.joda.time.Hours.hoursBetween((org.joda.time.ReadablePartial) timeOfDay1, (org.joda.time.ReadablePartial) localDate17);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: ReadablePartial objects must have the same set of fields");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeFieldTypeArray2);
        org.junit.Assert.assertNotNull(gregorianChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertNotNull(gJChronology5);
        org.junit.Assert.assertNotNull(lenientChronology6);
        org.junit.Assert.assertNotNull(dateTimeField7);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + (-1) + "'", int11 == (-1));
        org.junit.Assert.assertNotNull(julianChronology13);
        org.junit.Assert.assertNotNull(property15);
        org.junit.Assert.assertNotNull(localDate17);
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "40256602" + "'", str20, "40256602");
    }

    @Test
    public void test0437() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0437");
        org.joda.time.Weeks weeks0 = org.joda.time.Weeks.MAX_VALUE;
        org.joda.time.Weeks weeks2 = weeks0.dividedBy(3);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Seconds seconds3 = weeks2.toStandardSeconds();
            org.junit.Assert.fail("Expected exception of type java.lang.ArithmeticException; message: Multiplication overflows an int: 715827882 * 604800");
        } catch (java.lang.ArithmeticException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(weeks0);
        org.junit.Assert.assertNotNull(weeks2);
    }

    @Test
    public void test0438() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0438");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone5 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology6 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType7 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology9 = null;
        org.joda.time.DateMidnight dateMidnight10 = new org.joda.time.DateMidnight((long) (short) -1, chronology9);
        org.joda.time.DateMidnight dateMidnight12 = dateMidnight10.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property13 = dateMidnight10.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime14 = dateMidnight10.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property15 = mutableDateTime14.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime17 = property15.addWrapField((int) (short) -1);
        boolean boolean18 = leapYearPatternType7.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology19 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5, leapYearPatternType7);
        org.joda.time.MonthDay monthDay20 = new org.joda.time.MonthDay((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.DateTime dateTime21 = new org.joda.time.DateTime(2440588L, (org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.chrono.JulianChronology julianChronology23 = org.joda.time.chrono.JulianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5, (int) (short) 1);
        org.junit.Assert.assertNotNull(copticChronology6);
        org.junit.Assert.assertNotNull(leapYearPatternType7);
        org.junit.Assert.assertNotNull(dateMidnight12);
        org.junit.Assert.assertNotNull(property13);
        org.junit.Assert.assertNotNull(mutableDateTime14);
        org.junit.Assert.assertNotNull(property15);
        org.junit.Assert.assertNotNull(mutableDateTime17);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertNotNull(islamicChronology19);
        org.junit.Assert.assertNotNull(julianChronology23);
    }

    @Test
    public void test0439() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0439");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.DateTime dateTime6 = dateTime0.withWeekyear((int) (byte) 0);
        org.joda.time.DateTime dateTime8 = dateTime6.plusSeconds((int) (byte) -1);
        org.joda.time.DateTime.Property property9 = dateTime8.year();
        java.util.Locale locale11 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTime dateTime12 = property9.setCopy("GregorianChronology[Europe/Prague]", locale11);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value \"GregorianChronology[Europe/Prague]\" for year is not supported");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(dateTime6);
        org.junit.Assert.assertNotNull(dateTime8);
        org.junit.Assert.assertNotNull(property9);
    }

    @Test
    public void test0440() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0440");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.MutableDateTime mutableDateTime3 = dateMidnight2.toMutableDateTime();
        org.joda.time.PeriodType periodType5 = null;
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.Period period7 = new org.joda.time.Period((long) (short) 10, periodType5, chronology6);
        org.joda.time.Months months8 = org.joda.time.Months.FIVE;
        org.joda.time.Period period9 = period7.minus((org.joda.time.ReadablePeriod) months8);
        org.joda.time.Chronology chronology11 = null;
        org.joda.time.DateMidnight dateMidnight12 = new org.joda.time.DateMidnight((long) (short) -1, chronology11);
        org.joda.time.DateMidnight dateMidnight14 = dateMidnight12.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology16 = null;
        org.joda.time.DateMidnight dateMidnight17 = new org.joda.time.DateMidnight((long) (short) -1, chronology16);
        org.joda.time.DateMidnight dateMidnight19 = dateMidnight17.minusMonths((int) (byte) 100);
        int int20 = dateMidnight14.compareTo((org.joda.time.ReadableInstant) dateMidnight17);
        org.joda.time.Duration duration21 = period9.toDurationTo((org.joda.time.ReadableInstant) dateMidnight17);
        org.joda.time.DateMidnight dateMidnight22 = dateMidnight2.minus((org.joda.time.ReadableDuration) duration21);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateMidnight dateMidnight24 = dateMidnight22.withWeekOfWeekyear(0);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 0 for weekOfWeekyear must be in the range [1,52]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(mutableDateTime3);
        org.junit.Assert.assertNotNull(months8);
        org.junit.Assert.assertNotNull(period9);
        org.junit.Assert.assertNotNull(dateMidnight14);
        org.junit.Assert.assertNotNull(dateMidnight19);
        org.junit.Assert.assertTrue("'" + int20 + "' != '" + (-1) + "'", int20 == (-1));
        org.junit.Assert.assertNotNull(duration21);
        org.junit.Assert.assertNotNull(dateMidnight22);
    }

    @Test
    public void test0441() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0441");
        org.joda.time.Months months0 = org.joda.time.Months.TWELVE;
        int int1 = months0.getMonths();
        org.junit.Assert.assertNotNull(months0);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 12 + "'", int1 == 12);
    }

    @Test
    public void test0442() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0442");
        org.joda.time.PeriodType periodType1 = org.joda.time.PeriodType.millis();
        org.joda.time.PeriodType periodType2 = periodType1.withWeeksRemoved();
        org.joda.time.MutablePeriod mutablePeriod3 = new org.joda.time.MutablePeriod((long) (byte) 10, periodType2);
        mutablePeriod3.addWeeks(0);
        org.joda.time.Period period6 = mutablePeriod3.toPeriod();
        org.junit.Assert.assertNotNull(periodType1);
        org.junit.Assert.assertNotNull(periodType2);
        org.junit.Assert.assertNotNull(period6);
    }

    @Test
    public void test0443() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0443");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology11 = null;
        org.joda.time.DateMidnight dateMidnight12 = new org.joda.time.DateMidnight((long) (short) -1, chronology11);
        org.joda.time.DateMidnight dateMidnight14 = dateMidnight12.minusMonths((int) (byte) 100);
        int int15 = dateMidnight9.compareTo((org.joda.time.ReadableInstant) dateMidnight12);
        org.joda.time.Minutes minutes16 = org.joda.time.Minutes.minutesBetween((org.joda.time.ReadableInstant) dateMidnight2, (org.joda.time.ReadableInstant) dateMidnight9);
        org.joda.time.DateMidnight dateMidnight19 = dateMidnight9.withDurationAdded((long) '4', 100);
        org.joda.time.Chronology chronology21 = null;
        org.joda.time.DateMidnight dateMidnight22 = new org.joda.time.DateMidnight((long) (short) -1, chronology21);
        org.joda.time.DateMidnight dateMidnight24 = dateMidnight22.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight27 = dateMidnight22.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar28 = dateMidnight27.toGregorianCalendar();
        org.joda.time.MutableInterval mutableInterval29 = new org.joda.time.MutableInterval((org.joda.time.ReadableInstant) dateMidnight9, (org.joda.time.ReadableInstant) dateMidnight27);
        org.joda.time.chrono.GJChronology gJChronology31 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay32 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology31);
        org.joda.time.DurationField durationField33 = gJChronology31.months();
        org.joda.time.DateMidnight dateMidnight35 = org.joda.time.DateMidnight.parse("2026-08-06T11:10:36.726");
        org.joda.time.DateMidnight dateMidnight37 = dateMidnight35.minusYears((int) (byte) 100);
        org.joda.time.ReadableDateTime readableDateTime38 = null;
        org.joda.time.chrono.LimitChronology limitChronology39 = org.joda.time.chrono.LimitChronology.getInstance((org.joda.time.Chronology) gJChronology31, (org.joda.time.ReadableDateTime) dateMidnight35, readableDateTime38);
        org.joda.time.DateTime dateTime40 = limitChronology39.getLowerLimit();
        // The following exception was thrown during execution in test generation
        try {
            mutableInterval29.setStart((org.joda.time.ReadableInstant) dateTime40);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The end instant must be greater than the start instant");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(dateMidnight14);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + (-1) + "'", int15 == (-1));
        org.junit.Assert.assertNotNull(minutes16);
        org.junit.Assert.assertNotNull(dateMidnight19);
        org.junit.Assert.assertNotNull(dateMidnight24);
        org.junit.Assert.assertNotNull(dateMidnight27);
        org.junit.Assert.assertNotNull(gregorianCalendar28);
        org.junit.Assert.assertNotNull(gJChronology31);
        org.junit.Assert.assertNotNull(durationField33);
        org.junit.Assert.assertNotNull(dateMidnight35);
        org.junit.Assert.assertNotNull(dateMidnight37);
        org.junit.Assert.assertNotNull(limitChronology39);
        org.junit.Assert.assertNotNull(dateTime40);
    }

    @Test
    public void test0444() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0444");
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
        org.joda.time.DateTimeZone dateTimeZone29 = null;
        org.joda.time.LocalDateTime localDateTime30 = new org.joda.time.LocalDateTime(dateTimeZone29);
        int int31 = localDateTime30.getYear();
        org.joda.time.LocalDateTime localDateTime33 = localDateTime30.plusMillis(10);
        org.joda.time.chrono.GJChronology gJChronology34 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj35 = null;
        boolean boolean36 = gJChronology34.equals(obj35);
        org.joda.time.DurationField durationField37 = gJChronology34.days();
        org.joda.time.chrono.GJChronology gJChronology38 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology39 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology38);
        org.joda.time.chrono.LenientChronology lenientChronology40 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology38);
        org.joda.time.DateTimeField dateTimeField41 = lenientChronology40.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField42 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology34, dateTimeField41);
        org.joda.time.DateTimeFieldType dateTimeFieldType43 = skipUndoDateTimeField42.getType();
        int int44 = skipUndoDateTimeField42.getMinimumValue();
        java.lang.String str45 = skipUndoDateTimeField42.getName();
        org.joda.time.DateTimeFieldType dateTimeFieldType46 = skipUndoDateTimeField42.getType();
        org.joda.time.LocalDateTime localDateTime48 = localDateTime30.withField(dateTimeFieldType46, 24);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.YearMonth yearMonth50 = yearMonth0.withField(dateTimeFieldType46, (int) (short) 1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Field 'yearOfCentury' is not supported");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(duration2);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(dateMidnight12);
        org.junit.Assert.assertNotNull(dateMidnight17);
        org.junit.Assert.assertTrue("'" + int18 + "' != '" + (-1) + "'", int18 == (-1));
        org.junit.Assert.assertNotNull(minutes19);
        org.junit.Assert.assertNotNull(dateMidnight22);
        org.junit.Assert.assertNotNull(gJChronology24);
        org.junit.Assert.assertNotNull(dateTimeField25);
        org.junit.Assert.assertNotNull(durationField26);
        org.junit.Assert.assertNotNull(period27);
        org.junit.Assert.assertNotNull(yearMonth28);
        org.junit.Assert.assertTrue("'" + int31 + "' != '" + 2026 + "'", int31 == 2026);
        org.junit.Assert.assertNotNull(localDateTime33);
        org.junit.Assert.assertNotNull(gJChronology34);
        org.junit.Assert.assertTrue("'" + boolean36 + "' != '" + false + "'", boolean36 == false);
        org.junit.Assert.assertNotNull(durationField37);
        org.junit.Assert.assertNotNull(gJChronology38);
        org.junit.Assert.assertNotNull(lenientChronology39);
        org.junit.Assert.assertNotNull(lenientChronology40);
        org.junit.Assert.assertNotNull(dateTimeField41);
        org.junit.Assert.assertNotNull(dateTimeFieldType43);
        org.junit.Assert.assertTrue("'" + int44 + "' != '" + 0 + "'", int44 == 0);
        org.junit.Assert.assertEquals("'" + str45 + "' != '" + "yearOfCentury" + "'", str45, "yearOfCentury");
        org.junit.Assert.assertNotNull(dateTimeFieldType46);
        org.junit.Assert.assertNotNull(localDateTime48);
    }

    @Test
    public void test0445() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0445");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.DateTime dateTime6 = dateTime0.withWeekyear((int) (byte) 0);
        org.joda.time.DateTime dateTime8 = dateTime6.plusSeconds((int) (byte) -1);
        org.joda.time.DateTime.Property property9 = dateTime8.year();
        org.joda.time.DateTime dateTime11 = dateTime8.minusSeconds(59);
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(dateTime6);
        org.junit.Assert.assertNotNull(dateTime8);
        org.junit.Assert.assertNotNull(property9);
        org.junit.Assert.assertNotNull(dateTime11);
    }

    @Test
    public void test0446() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0446");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.Months months4 = org.joda.time.Months.FIVE;
        org.joda.time.Period period5 = period3.minus((org.joda.time.ReadablePeriod) months4);
        org.joda.time.Period period7 = period3.plusMinutes((int) ' ');
        org.joda.time.DurationFieldType[] durationFieldTypeArray8 = period3.getFieldTypes();
        org.joda.time.PeriodType periodType9 = org.joda.time.PeriodType.forFields(durationFieldTypeArray8);
        org.junit.Assert.assertNotNull(months4);
        org.junit.Assert.assertNotNull(period5);
        org.junit.Assert.assertNotNull(period7);
        org.junit.Assert.assertNotNull(durationFieldTypeArray8);
        org.junit.Assert.assertNotNull(periodType9);
    }

    @Test
    public void test0447() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0447");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.DurationField durationField3 = gJChronology1.eras();
        org.joda.time.DateTimeField dateTimeField4 = gJChronology1.era();
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(durationField3);
        org.junit.Assert.assertNotNull(dateTimeField4);
    }

    @Test
    public void test0448() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0448");
        org.joda.time.LocalTime localTime0 = new org.joda.time.LocalTime();
    }

    @Test
    public void test0449() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0449");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.field.FieldUtils.verifyValueBounds("CopticChronology[2026-08-06T11:10:36.726]", (-1), 6, (int) (short) 0);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value -1 for CopticChronology[2026-08-06T11:10:36.726] must be in the range [6,0]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0450() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0450");
        org.joda.time.Hours hours0 = org.joda.time.Hours.EIGHT;
        org.joda.time.Hours hours1 = org.joda.time.Hours.MIN_VALUE;
        boolean boolean2 = hours0.isGreaterThan(hours1);
        org.junit.Assert.assertNotNull(hours0);
        org.junit.Assert.assertNotNull(hours1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + true + "'", boolean2 == true);
    }

    @Test
    public void test0451() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0451");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.Months months4 = org.joda.time.Months.FIVE;
        org.joda.time.Period period5 = period3.minus((org.joda.time.ReadablePeriod) months4);
        org.joda.time.Period period7 = period3.plusMillis((-1));
        org.joda.time.Period period8 = period3.toPeriod();
        org.joda.time.Period period10 = period3.minusMonths(0);
        org.joda.time.Period period12 = period10.minusDays(86400000);
        org.junit.Assert.assertNotNull(months4);
        org.junit.Assert.assertNotNull(period5);
        org.junit.Assert.assertNotNull(period7);
        org.junit.Assert.assertNotNull(period8);
        org.junit.Assert.assertNotNull(period10);
        org.junit.Assert.assertNotNull(period12);
    }

    @Test
    public void test0452() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0452");
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
        org.joda.time.DurationField durationField28 = gJChronology24.years();
        org.joda.time.DateTimeZone dateTimeZone29 = gJChronology24.getZone();
        org.joda.time.MutableDateTime mutableDateTime30 = new org.joda.time.MutableDateTime((long) 5, (org.joda.time.Chronology) gJChronology24);
        org.junit.Assert.assertNotNull(duration2);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(dateMidnight12);
        org.junit.Assert.assertNotNull(dateMidnight17);
        org.junit.Assert.assertTrue("'" + int18 + "' != '" + (-1) + "'", int18 == (-1));
        org.junit.Assert.assertNotNull(minutes19);
        org.junit.Assert.assertNotNull(dateMidnight22);
        org.junit.Assert.assertNotNull(gJChronology24);
        org.junit.Assert.assertNotNull(dateTimeField25);
        org.junit.Assert.assertNotNull(durationField26);
        org.junit.Assert.assertNotNull(period27);
        org.junit.Assert.assertNotNull(durationField28);
        org.junit.Assert.assertNotNull(dateTimeZone29);
    }

    @Test
    public void test0453() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0453");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.Months months4 = org.joda.time.Months.FIVE;
        org.joda.time.Period period5 = period3.minus((org.joda.time.ReadablePeriod) months4);
        org.joda.time.Period period7 = period3.plusMinutes((int) ' ');
        org.joda.time.DurationFieldType[] durationFieldTypeArray8 = period3.getFieldTypes();
        org.joda.time.Period period10 = period3.minusDays((int) (short) 100);
        org.joda.time.MutablePeriod mutablePeriod11 = period3.toMutablePeriod();
        mutablePeriod11.add((int) '4', 2, (int) '4', 9, (int) (byte) 10, 86400000, (int) (byte) -1, 2);
        mutablePeriod11.setWeeks(10);
        org.junit.Assert.assertNotNull(months4);
        org.junit.Assert.assertNotNull(period5);
        org.junit.Assert.assertNotNull(period7);
        org.junit.Assert.assertNotNull(durationFieldTypeArray8);
        org.junit.Assert.assertNotNull(period10);
        org.junit.Assert.assertNotNull(mutablePeriod11);
    }

    @Test
    public void test0454() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0454");
        org.joda.time.Interval interval2 = new org.joda.time.Interval(0L, 0L);
    }

    @Test
    public void test0455() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0455");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.YearMonthDay yearMonthDay5 = dateTime0.toYearMonthDay();
        org.joda.time.Interval interval6 = yearMonthDay5.toInterval();
        org.joda.time.YearMonthDay yearMonthDay8 = yearMonthDay5.minusYears(5);
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(yearMonthDay5);
        org.junit.Assert.assertNotNull(interval6);
        org.junit.Assert.assertNotNull(yearMonthDay8);
    }

    @Test
    public void test0456() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0456");
        org.joda.time.Instant instant1 = org.joda.time.Instant.ofEpochMilli((long) (byte) 0);
        org.junit.Assert.assertNotNull(instant1);
    }

    @Test
    public void test0457() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0457");
        org.joda.time.chrono.GregorianChronology gregorianChronology0 = org.joda.time.chrono.GregorianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField1 = gregorianChronology0.dayOfYear();
        org.junit.Assert.assertNotNull(gregorianChronology0);
        org.junit.Assert.assertNotNull(dateTimeField1);
    }

    @Test
    public void test0458() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0458");
        org.joda.time.MutablePeriod mutablePeriod8 = new org.joda.time.MutablePeriod((int) (short) -1, 86400, 100, 24, 0, 12, 11, 1000);
    }

    @Test
    public void test0459() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0459");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.Months months4 = org.joda.time.Months.FIVE;
        org.joda.time.Period period5 = period3.minus((org.joda.time.ReadablePeriod) months4);
        org.joda.time.Chronology chronology7 = null;
        org.joda.time.DateMidnight dateMidnight8 = new org.joda.time.DateMidnight((long) (short) -1, chronology7);
        org.joda.time.DateMidnight dateMidnight10 = dateMidnight8.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology12 = null;
        org.joda.time.DateMidnight dateMidnight13 = new org.joda.time.DateMidnight((long) (short) -1, chronology12);
        org.joda.time.DateMidnight dateMidnight15 = dateMidnight13.minusMonths((int) (byte) 100);
        int int16 = dateMidnight10.compareTo((org.joda.time.ReadableInstant) dateMidnight13);
        org.joda.time.Duration duration17 = period5.toDurationTo((org.joda.time.ReadableInstant) dateMidnight13);
        org.joda.time.Period period19 = period5.minusYears(40237996);
        org.junit.Assert.assertNotNull(months4);
        org.junit.Assert.assertNotNull(period5);
        org.junit.Assert.assertNotNull(dateMidnight10);
        org.junit.Assert.assertNotNull(dateMidnight15);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + (-1) + "'", int16 == (-1));
        org.junit.Assert.assertNotNull(duration17);
        org.junit.Assert.assertNotNull(period19);
    }

    @Test
    public void test0460() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0460");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.DateTime dateTime6 = dateTime0.withWeekyear((int) (byte) 0);
        org.joda.time.DateTime dateTime8 = dateTime0.minusMonths(8);
        org.joda.time.chrono.JulianChronology julianChronology10 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate11 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology10);
        org.joda.time.LocalDate.Property property12 = localDate11.yearOfCentury();
        org.joda.time.LocalDate localDate14 = localDate11.withWeekyear((int) (byte) 100);
        org.joda.time.LocalDate localDate16 = localDate11.withWeekyear((int) (short) -1);
        org.joda.time.DateTime dateTime17 = dateTime8.withDate(localDate11);
        org.joda.time.DateTime.Property property18 = dateTime8.weekOfWeekyear();
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(dateTime6);
        org.junit.Assert.assertNotNull(dateTime8);
        org.junit.Assert.assertNotNull(julianChronology10);
        org.junit.Assert.assertNotNull(property12);
        org.junit.Assert.assertNotNull(localDate14);
        org.junit.Assert.assertNotNull(localDate16);
        org.junit.Assert.assertNotNull(dateTime17);
        org.junit.Assert.assertNotNull(property18);
    }

    @Test
    public void test0461() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0461");
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
        org.joda.time.DateTime.Property property21 = dateTime18.era();
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(dateTime6);
        org.junit.Assert.assertNotNull(dateTime8);
        org.junit.Assert.assertNotNull(property9);
        org.junit.Assert.assertNotNull(dateTime12);
        org.junit.Assert.assertNotNull(dateTime14);
        org.junit.Assert.assertNotNull(dateTime16);
        org.junit.Assert.assertNotNull(dateTime18);
        org.junit.Assert.assertNotNull(property19);
        org.junit.Assert.assertTrue("'" + int20 + "' != '" + 0 + "'", int20 == 0);
        org.junit.Assert.assertNotNull(property21);
    }

    @Test
    public void test0462() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0462");
        org.joda.time.Hours hours0 = org.joda.time.Hours.SEVEN;
        int int1 = hours0.getHours();
        org.junit.Assert.assertNotNull(hours0);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 7 + "'", int1 == 7);
    }

    @Test
    public void test0463() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0463");
        org.joda.time.Period period1 = org.joda.time.Period.weeks((int) (short) 100);
        int int2 = period1.getDays();
        org.joda.time.Period period4 = period1.minusWeeks(100);
        org.joda.time.Duration duration5 = period4.toStandardDuration();
        org.joda.time.Duration duration7 = duration5.plus((long) (byte) -1);
        org.junit.Assert.assertNotNull(period1);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertNotNull(period4);
        org.junit.Assert.assertNotNull(duration5);
        org.junit.Assert.assertNotNull(duration7);
    }

    @Test
    public void test0464() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0464");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar8 = dateMidnight7.toGregorianCalendar();
        org.joda.time.LocalTime localTime9 = org.joda.time.LocalTime.fromCalendarFields((java.util.Calendar) gregorianCalendar8);
        org.joda.time.LocalTime.Property property10 = localTime9.minuteOfHour();
        org.joda.time.LocalTime localTime12 = localTime9.withMillisOfSecond((int) (short) 100);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(gregorianCalendar8);
        org.junit.Assert.assertNotNull(localTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(localTime12);
    }

    @Test
    public void test0465() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0465");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar8 = dateMidnight7.toGregorianCalendar();
        org.joda.time.LocalTime localTime9 = org.joda.time.LocalTime.fromCalendarFields((java.util.Calendar) gregorianCalendar8);
        org.joda.time.LocalTime.Property property10 = localTime9.minuteOfHour();
        org.joda.time.LocalTime.Property property11 = localTime9.millisOfSecond();
        org.joda.time.LocalTime localTime13 = property11.addCopy(0L);
        org.joda.time.LocalTime.Property property14 = localTime13.millisOfSecond();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(gregorianCalendar8);
        org.junit.Assert.assertNotNull(localTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(localTime13);
        org.junit.Assert.assertNotNull(property14);
    }

    @Test
    public void test0466() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0466");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime9 = property7.addWrapField((int) (short) -1);
        org.joda.time.Interval interval10 = property7.toInterval();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(mutableDateTime9);
        org.junit.Assert.assertNotNull(interval10);
    }

    @Test
    public void test0467() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0467");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        int int2 = localDateTime1.getYear();
        org.joda.time.LocalDateTime localDateTime4 = localDateTime1.plusMillis(10);
        org.joda.time.chrono.GJChronology gJChronology5 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj6 = null;
        boolean boolean7 = gJChronology5.equals(obj6);
        org.joda.time.DurationField durationField8 = gJChronology5.days();
        org.joda.time.chrono.GJChronology gJChronology9 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology10 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology9);
        org.joda.time.chrono.LenientChronology lenientChronology11 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology9);
        org.joda.time.DateTimeField dateTimeField12 = lenientChronology11.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField13 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology5, dateTimeField12);
        org.joda.time.DateTimeFieldType dateTimeFieldType14 = skipUndoDateTimeField13.getType();
        int int15 = skipUndoDateTimeField13.getMinimumValue();
        java.lang.String str16 = skipUndoDateTimeField13.getName();
        org.joda.time.DateTimeFieldType dateTimeFieldType17 = skipUndoDateTimeField13.getType();
        org.joda.time.LocalDateTime localDateTime19 = localDateTime1.withField(dateTimeFieldType17, 24);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDateTime localDateTime23 = localDateTime1.withDate(1970, 1440, (int) (short) 0);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 1440 for monthOfYear must be in the range [1,12]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 2026 + "'", int2 == 2026);
        org.junit.Assert.assertNotNull(localDateTime4);
        org.junit.Assert.assertNotNull(gJChronology5);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(durationField8);
        org.junit.Assert.assertNotNull(gJChronology9);
        org.junit.Assert.assertNotNull(lenientChronology10);
        org.junit.Assert.assertNotNull(lenientChronology11);
        org.junit.Assert.assertNotNull(dateTimeField12);
        org.junit.Assert.assertNotNull(dateTimeFieldType14);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 0 + "'", int15 == 0);
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "yearOfCentury" + "'", str16, "yearOfCentury");
        org.junit.Assert.assertNotNull(dateTimeFieldType17);
        org.junit.Assert.assertNotNull(localDateTime19);
    }

    @Test
    public void test0468() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0468");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.DateTime dateTime6 = dateTime0.withWeekyear((int) (byte) 0);
        org.joda.time.DateTime dateTime8 = dateTime0.minusMonths(8);
        org.joda.time.chrono.JulianChronology julianChronology10 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate11 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology10);
        org.joda.time.LocalDate.Property property12 = localDate11.yearOfCentury();
        org.joda.time.LocalDate localDate14 = localDate11.withWeekyear((int) (byte) 100);
        org.joda.time.LocalDate localDate16 = localDate11.withWeekyear((int) (short) -1);
        org.joda.time.DateTime dateTime17 = dateTime8.withDate(localDate11);
        org.joda.time.Chronology chronology19 = null;
        org.joda.time.DateMidnight dateMidnight20 = new org.joda.time.DateMidnight((long) (short) -1, chronology19);
        org.joda.time.DateMidnight dateMidnight22 = dateMidnight20.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology24 = null;
        org.joda.time.DateMidnight dateMidnight25 = new org.joda.time.DateMidnight((long) (short) -1, chronology24);
        org.joda.time.DateMidnight dateMidnight27 = dateMidnight25.minusMonths((int) (byte) 100);
        int int28 = dateMidnight22.compareTo((org.joda.time.ReadableInstant) dateMidnight25);
        org.joda.time.ReadableInterval readableInterval29 = null;
        org.joda.time.Seconds seconds30 = org.joda.time.Seconds.secondsIn(readableInterval29);
        org.joda.time.DurationFieldType durationFieldType31 = seconds30.getFieldType();
        org.joda.time.DateMidnight dateMidnight33 = dateMidnight22.withFieldAdded(durationFieldType31, (int) (byte) 0);
        org.joda.time.DateMidnight.Property property34 = dateMidnight33.dayOfMonth();
        org.joda.time.DateMidnight dateMidnight35 = property34.roundHalfFloorCopy();
        org.joda.time.DateMidnight dateMidnight36 = property34.roundHalfFloorCopy();
        boolean boolean37 = dateTime8.isBefore((org.joda.time.ReadableInstant) dateMidnight36);
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(dateTime6);
        org.junit.Assert.assertNotNull(dateTime8);
        org.junit.Assert.assertNotNull(julianChronology10);
        org.junit.Assert.assertNotNull(property12);
        org.junit.Assert.assertNotNull(localDate14);
        org.junit.Assert.assertNotNull(localDate16);
        org.junit.Assert.assertNotNull(dateTime17);
        org.junit.Assert.assertNotNull(dateMidnight22);
        org.junit.Assert.assertNotNull(dateMidnight27);
        org.junit.Assert.assertTrue("'" + int28 + "' != '" + (-1) + "'", int28 == (-1));
        org.junit.Assert.assertNotNull(seconds30);
        org.junit.Assert.assertNotNull(durationFieldType31);
        org.junit.Assert.assertNotNull(dateMidnight33);
        org.junit.Assert.assertNotNull(property34);
        org.junit.Assert.assertNotNull(dateMidnight35);
        org.junit.Assert.assertNotNull(dateMidnight36);
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + false + "'", boolean37 == false);
    }

    @Test
    public void test0469() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0469");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone4 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology5 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType6 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology8 = null;
        org.joda.time.DateMidnight dateMidnight9 = new org.joda.time.DateMidnight((long) (short) -1, chronology8);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property12 = dateMidnight9.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime13 = dateMidnight9.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property14 = mutableDateTime13.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime16 = property14.addWrapField((int) (short) -1);
        boolean boolean17 = leapYearPatternType6.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology18 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4, leapYearPatternType6);
        org.joda.time.MonthDay monthDay19 = new org.joda.time.MonthDay((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        org.joda.time.DateMidnight dateMidnight20 = new org.joda.time.DateMidnight((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        org.joda.time.Chronology chronology22 = null;
        org.joda.time.DateMidnight dateMidnight23 = new org.joda.time.DateMidnight((long) (short) -1, chronology22);
        org.joda.time.DateMidnight dateMidnight25 = dateMidnight23.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property26 = dateMidnight23.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime27 = dateMidnight23.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property28 = mutableDateTime27.centuryOfEra();
        mutableDateTime27.setSecondOfMinute(0);
        org.joda.time.Chronology chronology32 = null;
        org.joda.time.DateMidnight dateMidnight33 = new org.joda.time.DateMidnight((long) (short) -1, chronology32);
        org.joda.time.DateMidnight dateMidnight35 = dateMidnight33.minusMonths((int) (byte) 100);
        org.joda.time.Months months36 = org.joda.time.Months.monthsBetween((org.joda.time.ReadableInstant) mutableDateTime27, (org.joda.time.ReadableInstant) dateMidnight33);
        mutableDateTime27.setSecondOfDay(2026);
        int int39 = fixedDateTimeZone4.getOffset((org.joda.time.ReadableInstant) mutableDateTime27);
        // The following exception was thrown during execution in test generation
        try {
            mutableDateTime27.setWeekOfWeekyear((int) (short) -1);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value -1 for weekOfWeekyear must be in the range [1,52]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(copticChronology5);
        org.junit.Assert.assertNotNull(leapYearPatternType6);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(property12);
        org.junit.Assert.assertNotNull(mutableDateTime13);
        org.junit.Assert.assertNotNull(property14);
        org.junit.Assert.assertNotNull(mutableDateTime16);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(islamicChronology18);
        org.junit.Assert.assertNotNull(dateMidnight25);
        org.junit.Assert.assertNotNull(property26);
        org.junit.Assert.assertNotNull(mutableDateTime27);
        org.junit.Assert.assertNotNull(property28);
        org.junit.Assert.assertNotNull(dateMidnight35);
        org.junit.Assert.assertNotNull(months36);
        org.junit.Assert.assertTrue("'" + int39 + "' != '" + 100 + "'", int39 == 100);
    }

    @Test
    public void test0470() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0470");
        org.joda.time.Period period0 = new org.joda.time.Period();
        org.joda.time.Period period2 = period0.minusHours(2);
        org.joda.time.Period period4 = period2.minusMonths(7);
        org.joda.time.Period period6 = period4.plusWeeks(12);
        org.junit.Assert.assertNotNull(period2);
        org.junit.Assert.assertNotNull(period4);
        org.junit.Assert.assertNotNull(period6);
    }

    @Test
    public void test0471() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0471");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.DateTimeFormat.mediumDate();
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
    }

    @Test
    public void test0472() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0472");
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay3 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology2);
        org.joda.time.DurationField durationField4 = gJChronology2.months();
        org.joda.time.DateMidnight dateMidnight6 = org.joda.time.DateMidnight.parse("2026-08-06T11:10:36.726");
        org.joda.time.DateMidnight dateMidnight8 = dateMidnight6.minusYears((int) (byte) 100);
        org.joda.time.ReadableDateTime readableDateTime9 = null;
        org.joda.time.chrono.LimitChronology limitChronology10 = org.joda.time.chrono.LimitChronology.getInstance((org.joda.time.Chronology) gJChronology2, (org.joda.time.ReadableDateTime) dateMidnight6, readableDateTime9);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MonthDay monthDay11 = new org.joda.time.MonthDay(2700000L, (org.joda.time.Chronology) limitChronology10);
            org.junit.Assert.fail("Expected exception of type org.joda.time.chrono.LimitChronology.LimitException; message: The instant is below the supported minimum of 2026-08-06T00:00:00.000+02:00 (GJChronology[Europe/Prague])");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(durationField4);
        org.junit.Assert.assertNotNull(dateMidnight6);
        org.junit.Assert.assertNotNull(dateMidnight8);
        org.junit.Assert.assertNotNull(limitChronology10);
    }

    @Test
    public void test0473() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0473");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight.Property property8 = dateMidnight7.weekOfWeekyear();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(property8);
    }

    @Test
    public void test0474() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0474");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = property3.addToCopy(1);
        org.joda.time.DateMidnight dateMidnight6 = localDate5.toDateMidnight();
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(dateMidnight6);
    }

    @Test
    public void test0475() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0475");
        org.joda.time.DateTimeComparator dateTimeComparator0 = org.joda.time.DateTimeComparator.getDateOnlyInstance();
        org.joda.time.DateTimeComparator dateTimeComparator1 = org.joda.time.DateTimeComparator.getTimeOnlyInstance();
        java.util.Comparator<java.lang.Object> objComparator2 = dateTimeComparator0.thenComparing((java.util.Comparator<java.lang.Object>) dateTimeComparator1);
        org.joda.time.DateTimeFieldType dateTimeFieldType3 = dateTimeComparator1.getUpperLimit();
        org.joda.time.MutablePeriod mutablePeriod8 = new org.joda.time.MutablePeriod((int) 'a', 29, (int) (byte) 100, 40243947);
        org.joda.time.DateTime dateTime9 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone10 = null;
        org.joda.time.DateTime dateTime11 = dateTime9.withZoneRetainFields(dateTimeZone10);
        org.joda.time.PeriodType periodType13 = null;
        org.joda.time.Chronology chronology14 = null;
        org.joda.time.Period period15 = new org.joda.time.Period((long) (short) 10, periodType13, chronology14);
        org.joda.time.Months months16 = org.joda.time.Months.FIVE;
        org.joda.time.Period period17 = period15.minus((org.joda.time.ReadablePeriod) months16);
        org.joda.time.Chronology chronology19 = null;
        org.joda.time.DateMidnight dateMidnight20 = new org.joda.time.DateMidnight((long) (short) -1, chronology19);
        org.joda.time.DateMidnight dateMidnight22 = dateMidnight20.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology24 = null;
        org.joda.time.DateMidnight dateMidnight25 = new org.joda.time.DateMidnight((long) (short) -1, chronology24);
        org.joda.time.DateMidnight dateMidnight27 = dateMidnight25.minusMonths((int) (byte) 100);
        int int28 = dateMidnight22.compareTo((org.joda.time.ReadableInstant) dateMidnight25);
        org.joda.time.Duration duration29 = period17.toDurationTo((org.joda.time.ReadableInstant) dateMidnight25);
        org.joda.time.Duration duration30 = duration29.negated();
        org.joda.time.DateTime dateTime31 = dateTime9.minus((org.joda.time.ReadableDuration) duration30);
        // The following exception was thrown during execution in test generation
        try {
            int int32 = dateTimeComparator1.compare((java.lang.Object) 'a', (java.lang.Object) duration30);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No instant converter found for type: java.lang.Character");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeComparator0);
        org.junit.Assert.assertNotNull(dateTimeComparator1);
        org.junit.Assert.assertNotNull(objComparator2);
        org.junit.Assert.assertNotNull(dateTimeFieldType3);
        org.junit.Assert.assertNotNull(dateTime11);
        org.junit.Assert.assertNotNull(months16);
        org.junit.Assert.assertNotNull(period17);
        org.junit.Assert.assertNotNull(dateMidnight22);
        org.junit.Assert.assertNotNull(dateMidnight27);
        org.junit.Assert.assertTrue("'" + int28 + "' != '" + (-1) + "'", int28 == (-1));
        org.junit.Assert.assertNotNull(duration29);
        org.junit.Assert.assertNotNull(duration30);
        org.junit.Assert.assertNotNull(dateTime31);
    }

    @Test
    public void test0476() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0476");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.date();
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
    }

    @Test
    public void test0477() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0477");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.DateMidnight dateMidnight6 = property5.getDateMidnight();
        org.joda.time.DateMidnight dateMidnight7 = property5.withMaximumValue();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(dateMidnight6);
        org.junit.Assert.assertNotNull(dateMidnight7);
    }

    @Test
    public void test0478() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0478");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.DateMidnight dateMidnight3 = new org.joda.time.DateMidnight((long) (short) -1, chronology2);
        org.joda.time.DateMidnight dateMidnight5 = dateMidnight3.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology7 = null;
        org.joda.time.DateMidnight dateMidnight8 = new org.joda.time.DateMidnight((long) (short) -1, chronology7);
        org.joda.time.DateMidnight dateMidnight10 = dateMidnight8.minusMonths((int) (byte) 100);
        int int11 = dateMidnight5.compareTo((org.joda.time.ReadableInstant) dateMidnight8);
        org.joda.time.chrono.GJChronology gJChronology13 = org.joda.time.chrono.GJChronology.getInstance(dateTimeZone0, (org.joda.time.ReadableInstant) dateMidnight8, 7);
        org.joda.time.DateTimeField dateTimeField14 = gJChronology13.minuteOfDay();
        org.junit.Assert.assertNotNull(dateMidnight5);
        org.junit.Assert.assertNotNull(dateMidnight10);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + (-1) + "'", int11 == (-1));
        org.junit.Assert.assertNotNull(gJChronology13);
        org.junit.Assert.assertNotNull(dateTimeField14);
    }

    @Test
    public void test0479() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0479");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone5 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology6 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.LocalDateTime localDateTime7 = new org.joda.time.LocalDateTime((long) (-1), (org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.tz.CachedDateTimeZone cachedDateTimeZone8 = org.joda.time.tz.CachedDateTimeZone.forZone((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.chrono.ISOChronology iSOChronology9 = org.joda.time.chrono.ISOChronology.getInstance((org.joda.time.DateTimeZone) cachedDateTimeZone8);
        java.lang.String str11 = cachedDateTimeZone8.getNameKey((long) 3600);
        boolean boolean12 = cachedDateTimeZone8.isFixed();
        org.junit.Assert.assertNotNull(gregorianChronology6);
        org.junit.Assert.assertNotNull(cachedDateTimeZone8);
        org.junit.Assert.assertNotNull(iSOChronology9);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "hi!" + "'", str11, "hi!");
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
    }

    @Test
    public void test0480() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0480");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        int int8 = skipUndoDateTimeField6.get((long) 10);
        org.joda.time.DurationField durationField9 = skipUndoDateTimeField6.getRangeDurationField();
        org.joda.time.DurationField durationField10 = skipUndoDateTimeField6.getLeapDurationField();
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 2 + "'", int8 == 2);
        org.junit.Assert.assertNotNull(durationField9);
        org.junit.Assert.assertNull(durationField10);
    }

    @Test
    public void test0481() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0481");
        org.joda.time.DateTimeComparator dateTimeComparator0 = org.joda.time.DateTimeComparator.getDateOnlyInstance();
        org.joda.time.DateTimeComparator dateTimeComparator1 = org.joda.time.DateTimeComparator.getTimeOnlyInstance();
        java.util.Comparator<java.lang.Object> objComparator2 = dateTimeComparator0.thenComparing((java.util.Comparator<java.lang.Object>) dateTimeComparator1);
        java.util.Comparator<java.lang.Object> objComparator3 = objComparator2.reversed();
        org.junit.Assert.assertNotNull(dateTimeComparator0);
        org.junit.Assert.assertNotNull(dateTimeComparator1);
        org.junit.Assert.assertNotNull(objComparator2);
        org.junit.Assert.assertNotNull(objComparator3);
    }

    @Test
    public void test0482() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0482");
        org.joda.time.Instant instant0 = org.joda.time.Instant.EPOCH;
        org.joda.time.Instant instant3 = instant0.withDurationAdded((-1L), 100);
        org.joda.time.Duration duration5 = org.joda.time.Duration.standardDays((long) (short) 1);
        org.joda.time.PeriodType periodType7 = null;
        org.joda.time.Chronology chronology8 = null;
        org.joda.time.Period period9 = new org.joda.time.Period((long) (short) 10, periodType7, chronology8);
        org.joda.time.Months months10 = org.joda.time.Months.FIVE;
        org.joda.time.Period period11 = period9.minus((org.joda.time.ReadablePeriod) months10);
        org.joda.time.Chronology chronology13 = null;
        org.joda.time.DateMidnight dateMidnight14 = new org.joda.time.DateMidnight((long) (short) -1, chronology13);
        org.joda.time.DateMidnight dateMidnight16 = dateMidnight14.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology18 = null;
        org.joda.time.DateMidnight dateMidnight19 = new org.joda.time.DateMidnight((long) (short) -1, chronology18);
        org.joda.time.DateMidnight dateMidnight21 = dateMidnight19.minusMonths((int) (byte) 100);
        int int22 = dateMidnight16.compareTo((org.joda.time.ReadableInstant) dateMidnight19);
        org.joda.time.Duration duration23 = period11.toDurationTo((org.joda.time.ReadableInstant) dateMidnight19);
        org.joda.time.Duration duration24 = duration23.negated();
        boolean boolean25 = duration5.isShorterThan((org.joda.time.ReadableDuration) duration23);
        org.joda.time.Instant instant26 = instant0.plus((org.joda.time.ReadableDuration) duration5);
        org.joda.time.Duration duration28 = duration5.multipliedBy((long) (short) 1);
        org.junit.Assert.assertNotNull(instant0);
        org.junit.Assert.assertNotNull(instant3);
        org.junit.Assert.assertNotNull(duration5);
        org.junit.Assert.assertNotNull(months10);
        org.junit.Assert.assertNotNull(period11);
        org.junit.Assert.assertNotNull(dateMidnight16);
        org.junit.Assert.assertNotNull(dateMidnight21);
        org.junit.Assert.assertTrue("'" + int22 + "' != '" + (-1) + "'", int22 == (-1));
        org.junit.Assert.assertNotNull(duration23);
        org.junit.Assert.assertNotNull(duration24);
        org.junit.Assert.assertTrue("'" + boolean25 + "' != '" + false + "'", boolean25 == false);
        org.junit.Assert.assertNotNull(instant26);
        org.junit.Assert.assertNotNull(duration28);
    }

    @Test
    public void test0483() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0483");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime2.plusHours(0);
        org.joda.time.DateTimeZone dateTimeZone5 = null;
        org.joda.time.LocalDateTime localDateTime6 = new org.joda.time.LocalDateTime(dateTimeZone5);
        org.joda.time.LocalDateTime localDateTime8 = localDateTime6.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property9 = localDateTime6.weekOfWeekyear();
        org.joda.time.DateTime dateTime10 = dateTime4.withFields((org.joda.time.ReadablePartial) localDateTime6);
        org.joda.time.LocalDateTime.Property property11 = localDateTime6.weekyear();
        org.joda.time.LocalDateTime localDateTime13 = property11.addToCopy((long) (byte) 10);
        int int14 = localDateTime13.getYearOfCentury();
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(localDateTime8);
        org.junit.Assert.assertNotNull(property9);
        org.junit.Assert.assertNotNull(dateTime10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(localDateTime13);
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 36 + "'", int14 == 36);
    }

    @Test
    public void test0484() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0484");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone5 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology6 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.LocalDateTime localDateTime7 = new org.joda.time.LocalDateTime((long) (-1), (org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.tz.CachedDateTimeZone cachedDateTimeZone8 = org.joda.time.tz.CachedDateTimeZone.forZone((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.chrono.ISOChronology iSOChronology9 = org.joda.time.chrono.ISOChronology.getInstance((org.joda.time.DateTimeZone) cachedDateTimeZone8);
        org.joda.time.DateMidnight dateMidnight10 = new org.joda.time.DateMidnight((org.joda.time.DateTimeZone) cachedDateTimeZone8);
        org.joda.time.DateMidnight dateMidnight11 = org.joda.time.DateMidnight.now((org.joda.time.DateTimeZone) cachedDateTimeZone8);
        org.junit.Assert.assertNotNull(gregorianChronology6);
        org.junit.Assert.assertNotNull(cachedDateTimeZone8);
        org.junit.Assert.assertNotNull(iSOChronology9);
        org.junit.Assert.assertNotNull(dateMidnight11);
    }

    @Test
    public void test0485() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0485");
        int int1 = org.joda.time.format.FormatUtils.calculateDigitCount(1786007462709L);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 13 + "'", int1 == 13);
    }

    @Test
    public void test0486() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0486");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone6 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology7 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone6);
        org.joda.time.LocalDateTime localDateTime8 = new org.joda.time.LocalDateTime((long) (-1), (org.joda.time.DateTimeZone) fixedDateTimeZone6);
        org.joda.time.tz.CachedDateTimeZone cachedDateTimeZone9 = org.joda.time.tz.CachedDateTimeZone.forZone((org.joda.time.DateTimeZone) fixedDateTimeZone6);
        long long11 = fixedDateTimeZone6.previousTransition(1L);
        java.lang.String str13 = fixedDateTimeZone6.getName((long) '#');
        org.joda.time.DateTime dateTime14 = new org.joda.time.DateTime((long) 2026, (org.joda.time.DateTimeZone) fixedDateTimeZone6);
        java.lang.String str16 = fixedDateTimeZone6.getName((long) 59);
        org.joda.time.chrono.ISOChronology iSOChronology17 = org.joda.time.chrono.ISOChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone6);
        org.junit.Assert.assertNotNull(gregorianChronology7);
        org.junit.Assert.assertNotNull(cachedDateTimeZone9);
        org.junit.Assert.assertTrue("'" + long11 + "' != '" + 1L + "'", long11 == 1L);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "+00:00:00.100" + "'", str13, "+00:00:00.100");
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "+00:00:00.100" + "'", str16, "+00:00:00.100");
        org.junit.Assert.assertNotNull(iSOChronology17);
    }

    @Test
    public void test0487() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0487");
        org.joda.time.chrono.GJChronology gJChronology0 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj1 = null;
        boolean boolean2 = gJChronology0.equals(obj1);
        org.joda.time.DurationField durationField3 = gJChronology0.days();
        org.joda.time.chrono.GJChronology gJChronology4 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology5 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology4);
        org.joda.time.chrono.LenientChronology lenientChronology6 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology4);
        org.joda.time.DateTimeField dateTimeField7 = lenientChronology6.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField8 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology0, dateTimeField7);
        org.joda.time.DateTimeFieldType dateTimeFieldType9 = skipUndoDateTimeField8.getType();
        int int10 = skipUndoDateTimeField8.getMinimumValue();
        java.lang.String str11 = skipUndoDateTimeField8.getName();
        java.util.Locale locale13 = null;
        java.lang.String str14 = skipUndoDateTimeField8.getAsShortText(1440, locale13);
        org.junit.Assert.assertNotNull(gJChronology0);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(durationField3);
        org.junit.Assert.assertNotNull(gJChronology4);
        org.junit.Assert.assertNotNull(lenientChronology5);
        org.junit.Assert.assertNotNull(lenientChronology6);
        org.junit.Assert.assertNotNull(dateTimeField7);
        org.junit.Assert.assertNotNull(dateTimeFieldType9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "yearOfCentury" + "'", str11, "yearOfCentury");
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "1440" + "'", str14, "1440");
    }

    @Test
    public void test0488() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0488");
        org.joda.time.Duration duration1 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Chronology chronology3 = null;
        org.joda.time.DateMidnight dateMidnight4 = new org.joda.time.DateMidnight((long) (short) -1, chronology3);
        org.joda.time.DateMidnight dateMidnight6 = dateMidnight4.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology8 = null;
        org.joda.time.DateMidnight dateMidnight9 = new org.joda.time.DateMidnight((long) (short) -1, chronology8);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology13 = null;
        org.joda.time.DateMidnight dateMidnight14 = new org.joda.time.DateMidnight((long) (short) -1, chronology13);
        org.joda.time.DateMidnight dateMidnight16 = dateMidnight14.minusMonths((int) (byte) 100);
        int int17 = dateMidnight11.compareTo((org.joda.time.ReadableInstant) dateMidnight14);
        org.joda.time.Minutes minutes18 = org.joda.time.Minutes.minutesBetween((org.joda.time.ReadableInstant) dateMidnight4, (org.joda.time.ReadableInstant) dateMidnight11);
        org.joda.time.DateMidnight dateMidnight21 = dateMidnight11.withDurationAdded((long) '4', 100);
        org.joda.time.MutableInterval mutableInterval22 = new org.joda.time.MutableInterval((org.joda.time.ReadableDuration) duration1, (org.joda.time.ReadableInstant) dateMidnight21);
        org.joda.time.chrono.GJChronology gJChronology23 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField24 = gJChronology23.dayOfYear();
        org.joda.time.DurationField durationField25 = gJChronology23.years();
        org.joda.time.Period period26 = duration1.toPeriod((org.joda.time.Chronology) gJChronology23);
        org.joda.time.chrono.JulianChronology julianChronology27 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField28 = julianChronology27.weeks();
        org.joda.time.chrono.GJChronology gJChronology29 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology30 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology29);
        org.joda.time.DateTimeField dateTimeField31 = gJChronology29.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField33 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology27, dateTimeField31, (int) '4');
        org.joda.time.DateTimeZone dateTimeZone34 = julianChronology27.getZone();
        org.joda.time.Period period35 = duration1.toPeriod((org.joda.time.Chronology) julianChronology27);
        java.lang.String str36 = julianChronology27.toString();
        org.junit.Assert.assertNotNull(duration1);
        org.junit.Assert.assertNotNull(dateMidnight6);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(dateMidnight16);
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + (-1) + "'", int17 == (-1));
        org.junit.Assert.assertNotNull(minutes18);
        org.junit.Assert.assertNotNull(dateMidnight21);
        org.junit.Assert.assertNotNull(gJChronology23);
        org.junit.Assert.assertNotNull(dateTimeField24);
        org.junit.Assert.assertNotNull(durationField25);
        org.junit.Assert.assertNotNull(period26);
        org.junit.Assert.assertNotNull(julianChronology27);
        org.junit.Assert.assertNotNull(durationField28);
        org.junit.Assert.assertNotNull(gJChronology29);
        org.junit.Assert.assertNotNull(lenientChronology30);
        org.junit.Assert.assertNotNull(dateTimeField31);
        org.junit.Assert.assertNotNull(dateTimeZone34);
        org.junit.Assert.assertNotNull(period35);
        org.junit.Assert.assertEquals("'" + str36 + "' != '" + "JulianChronology[Europe/Prague]" + "'", str36, "JulianChronology[Europe/Prague]");
    }

    @Test
    public void test0489() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0489");
        org.joda.time.Days days3 = org.joda.time.Days.ONE;
        org.joda.time.DurationFieldType durationFieldType4 = days3.getFieldType();
        org.joda.time.PeriodType periodType5 = days3.getPeriodType();
        org.joda.time.Chronology chronology7 = null;
        org.joda.time.DateMidnight dateMidnight8 = new org.joda.time.DateMidnight((long) (short) -1, chronology7);
        org.joda.time.DateMidnight dateMidnight10 = dateMidnight8.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight13 = dateMidnight8.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight15 = dateMidnight13.withYear(100);
        org.joda.time.Chronology chronology17 = null;
        org.joda.time.DateMidnight dateMidnight18 = new org.joda.time.DateMidnight((long) (short) -1, chronology17);
        org.joda.time.Interval interval19 = new org.joda.time.Interval((org.joda.time.ReadableInstant) dateMidnight15, (org.joda.time.ReadableInstant) dateMidnight18);
        org.joda.time.Period period21 = org.joda.time.Period.weeks((int) (short) 100);
        org.joda.time.Interval interval22 = interval19.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) period21);
        org.joda.time.Chronology chronology23 = org.joda.time.DateTimeUtils.getIntervalChronology((org.joda.time.ReadableInterval) interval19);
        org.joda.time.Period period24 = new org.joda.time.Period((-59011462664000L), periodType5, chronology23);
        org.joda.time.Period period25 = new org.joda.time.Period((long) 70, 2700000L, periodType5);
        org.junit.Assert.assertNotNull(days3);
        org.junit.Assert.assertNotNull(durationFieldType4);
        org.junit.Assert.assertNotNull(periodType5);
        org.junit.Assert.assertNotNull(dateMidnight10);
        org.junit.Assert.assertNotNull(dateMidnight13);
        org.junit.Assert.assertNotNull(dateMidnight15);
        org.junit.Assert.assertNotNull(period21);
        org.junit.Assert.assertNotNull(interval22);
        org.junit.Assert.assertNotNull(chronology23);
    }

    @Test
    public void test0490() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0490");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar8 = dateMidnight7.toGregorianCalendar();
        org.joda.time.LocalTime localTime9 = org.joda.time.LocalTime.fromCalendarFields((java.util.Calendar) gregorianCalendar8);
        org.joda.time.LocalTime.Property property10 = localTime9.minuteOfHour();
        org.joda.time.LocalTime localTime11 = property10.withMaximumValue();
        org.joda.time.LocalTime localTime13 = property10.addNoWrapToCopy(4);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalTime localTime15 = localTime13.withHourOfDay((int) '4');
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 52 for hourOfDay must be in the range [0,23]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(gregorianCalendar8);
        org.junit.Assert.assertNotNull(localTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(localTime11);
        org.junit.Assert.assertNotNull(localTime13);
    }

    @Test
    public void test0491() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0491");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone5 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology6 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.LocalDateTime localDateTime7 = new org.joda.time.LocalDateTime((long) (-1), (org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.tz.CachedDateTimeZone cachedDateTimeZone8 = org.joda.time.tz.CachedDateTimeZone.forZone((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.chrono.ISOChronology iSOChronology9 = org.joda.time.chrono.ISOChronology.getInstance((org.joda.time.DateTimeZone) cachedDateTimeZone8);
        org.joda.time.chrono.GJChronology gJChronology10 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj11 = null;
        boolean boolean12 = gJChronology10.equals(obj11);
        org.joda.time.DurationField durationField13 = gJChronology10.days();
        org.joda.time.chrono.GJChronology gJChronology14 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology15 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology14);
        org.joda.time.chrono.LenientChronology lenientChronology16 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology14);
        org.joda.time.DateTimeField dateTimeField17 = lenientChronology16.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField18 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology10, dateTimeField17);
        org.joda.time.DateTimeFieldType dateTimeFieldType19 = skipUndoDateTimeField18.getType();
        org.joda.time.DurationField durationField20 = skipUndoDateTimeField18.getRangeDurationField();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField22 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) iSOChronology9, (org.joda.time.DateTimeField) skipUndoDateTimeField18, 2);
        org.joda.time.Chronology chronology23 = iSOChronology9.withUTC();
        java.lang.String str24 = iSOChronology9.toString();
        org.junit.Assert.assertNotNull(gregorianChronology6);
        org.junit.Assert.assertNotNull(cachedDateTimeZone8);
        org.junit.Assert.assertNotNull(iSOChronology9);
        org.junit.Assert.assertNotNull(gJChronology10);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertNotNull(durationField13);
        org.junit.Assert.assertNotNull(gJChronology14);
        org.junit.Assert.assertNotNull(lenientChronology15);
        org.junit.Assert.assertNotNull(lenientChronology16);
        org.junit.Assert.assertNotNull(dateTimeField17);
        org.junit.Assert.assertNotNull(dateTimeFieldType19);
        org.junit.Assert.assertNotNull(durationField20);
        org.junit.Assert.assertNotNull(chronology23);
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "ISOChronology[2026-08-06T11:10:36.726]" + "'", str24, "ISOChronology[2026-08-06T11:10:36.726]");
    }

    @Test
    public void test0492() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0492");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.monthOfYear();
        java.util.Locale locale5 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = localDate2.toString("2029-05-02T11:10:41.857", locale5);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Illegal pattern component: T");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
    }

    @Test
    public void test0493() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0493");
        org.joda.time.chrono.GJChronology gJChronology0 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj1 = null;
        boolean boolean2 = gJChronology0.equals(obj1);
        org.joda.time.DurationField durationField3 = gJChronology0.days();
        org.joda.time.chrono.GJChronology gJChronology4 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology5 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology4);
        org.joda.time.chrono.LenientChronology lenientChronology6 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology4);
        org.joda.time.DateTimeField dateTimeField7 = lenientChronology6.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField8 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology0, dateTimeField7);
        org.joda.time.DateTimeFieldType dateTimeFieldType9 = skipUndoDateTimeField8.getType();
        org.joda.time.DurationField durationField10 = skipUndoDateTimeField8.getRangeDurationField();
        int int11 = skipUndoDateTimeField8.getMaximumValue();
        org.joda.time.Chronology chronology13 = null;
        org.joda.time.DateMidnight dateMidnight14 = new org.joda.time.DateMidnight((long) (short) -1, chronology13);
        org.joda.time.DateMidnight dateMidnight16 = dateMidnight14.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property17 = dateMidnight14.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime18 = dateMidnight14.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property19 = mutableDateTime18.centuryOfEra();
        mutableDateTime18.setSecondOfMinute(0);
        org.joda.time.Chronology chronology23 = null;
        org.joda.time.DateMidnight dateMidnight24 = new org.joda.time.DateMidnight((long) (short) -1, chronology23);
        org.joda.time.DateMidnight dateMidnight26 = dateMidnight24.minusMonths((int) (byte) 100);
        org.joda.time.Months months27 = org.joda.time.Months.monthsBetween((org.joda.time.ReadableInstant) mutableDateTime18, (org.joda.time.ReadableInstant) dateMidnight24);
        org.joda.time.YearMonthDay yearMonthDay28 = dateMidnight24.toYearMonthDay();
        java.util.Locale locale29 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str30 = skipUndoDateTimeField8.getAsShortText((org.joda.time.ReadablePartial) yearMonthDay28, locale29);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Field 'yearOfCentury' is not supported");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gJChronology0);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(durationField3);
        org.junit.Assert.assertNotNull(gJChronology4);
        org.junit.Assert.assertNotNull(lenientChronology5);
        org.junit.Assert.assertNotNull(lenientChronology6);
        org.junit.Assert.assertNotNull(dateTimeField7);
        org.junit.Assert.assertNotNull(dateTimeFieldType9);
        org.junit.Assert.assertNotNull(durationField10);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 100 + "'", int11 == 100);
        org.junit.Assert.assertNotNull(dateMidnight16);
        org.junit.Assert.assertNotNull(property17);
        org.junit.Assert.assertNotNull(mutableDateTime18);
        org.junit.Assert.assertNotNull(property19);
        org.junit.Assert.assertNotNull(dateMidnight26);
        org.junit.Assert.assertNotNull(months27);
        org.junit.Assert.assertNotNull(yearMonthDay28);
    }

    @Test
    public void test0494() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0494");
        int int2 = org.joda.time.field.FieldUtils.safeMultiply(7, (int) (byte) 1);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 7 + "'", int2 == 7);
    }

    @Test
    public void test0495() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0495");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Interval interval2 = yearMonth0.toInterval(dateTimeZone1);
        org.joda.time.Months months3 = org.joda.time.Months.ONE;
        org.joda.time.Interval interval4 = interval2.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) months3);
        org.joda.time.Months months6 = months3.plus(40243947);
        org.joda.time.PeriodType periodType8 = null;
        org.joda.time.Chronology chronology9 = null;
        org.joda.time.Period period10 = new org.joda.time.Period((long) (short) 10, periodType8, chronology9);
        org.joda.time.Months months11 = org.joda.time.Months.FIVE;
        org.joda.time.Period period12 = period10.minus((org.joda.time.ReadablePeriod) months11);
        org.joda.time.Months months13 = months6.plus(months11);
        java.lang.String str14 = months6.toString();
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertNotNull(months3);
        org.junit.Assert.assertNotNull(interval4);
        org.junit.Assert.assertNotNull(months6);
        org.junit.Assert.assertNotNull(months11);
        org.junit.Assert.assertNotNull(period12);
        org.junit.Assert.assertNotNull(months13);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "P40243948M" + "'", str14, "P40243948M");
    }

    @Test
    public void test0496() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0496");
        org.joda.time.Hours hours0 = org.joda.time.Hours.ZERO;
        org.junit.Assert.assertNotNull(hours0);
    }

    @Test
    public void test0497() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0497");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        int int5 = localDateTime1.getYear();
        org.joda.time.ReadablePeriod readablePeriod6 = null;
        org.joda.time.LocalDateTime localDateTime7 = localDateTime1.plus(readablePeriod6);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDateTime localDateTime11 = localDateTime1.withDate(0, 168, (int) (short) 1);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 168 for monthOfYear must be in the range [1,12]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 2026 + "'", int5 == 2026);
        org.junit.Assert.assertNotNull(localDateTime7);
    }

    @Test
    public void test0498() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0498");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone5 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology6 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.LocalDateTime localDateTime7 = new org.joda.time.LocalDateTime((long) (-1), (org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.tz.CachedDateTimeZone cachedDateTimeZone8 = org.joda.time.tz.CachedDateTimeZone.forZone((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.chrono.ISOChronology iSOChronology9 = org.joda.time.chrono.ISOChronology.getInstance((org.joda.time.DateTimeZone) cachedDateTimeZone8);
        org.joda.time.MutableDateTime mutableDateTime10 = new org.joda.time.MutableDateTime((org.joda.time.DateTimeZone) cachedDateTimeZone8);
        org.junit.Assert.assertNotNull(gregorianChronology6);
        org.junit.Assert.assertNotNull(cachedDateTimeZone8);
        org.junit.Assert.assertNotNull(iSOChronology9);
    }

    @Test
    public void test0499() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0499");
        org.joda.time.Years years1 = org.joda.time.Years.years(7);
        org.junit.Assert.assertNotNull(years1);
    }

    @Test
    public void test0500() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0500");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.ReadableInterval readableInterval4 = null;
        org.joda.time.Seconds seconds5 = org.joda.time.Seconds.secondsIn(readableInterval4);
        org.joda.time.DurationFieldType durationFieldType6 = seconds5.getFieldType();
        int int7 = period3.get(durationFieldType6);
        org.joda.time.PeriodType periodType8 = period3.getPeriodType();
        org.joda.time.Period period10 = period3.withMinutes((int) (short) 0);
        org.junit.Assert.assertNotNull(seconds5);
        org.junit.Assert.assertNotNull(durationFieldType6);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertNotNull(periodType8);
        org.junit.Assert.assertNotNull(period10);
    }
}
