import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest1 {

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
    public void test0501() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0501");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.Months months4 = org.joda.time.Months.FIVE;
        org.joda.time.Period period5 = period3.minus((org.joda.time.ReadablePeriod) months4);
        org.joda.time.Period period7 = period3.plusMinutes((int) ' ');
        org.joda.time.DurationFieldType[] durationFieldTypeArray8 = period3.getFieldTypes();
        org.joda.time.PeriodType periodType9 = period3.getPeriodType();
        org.joda.time.Period period11 = period3.withDays(32);
        org.junit.Assert.assertNotNull(months4);
        org.junit.Assert.assertNotNull(period5);
        org.junit.Assert.assertNotNull(period7);
        org.junit.Assert.assertNotNull(durationFieldTypeArray8);
        org.junit.Assert.assertNotNull(periodType9);
        org.junit.Assert.assertNotNull(period11);
    }

    @Test
    public void test0502() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0502");
        org.joda.time.Weeks weeks0 = org.joda.time.Weeks.ZERO;
        org.junit.Assert.assertNotNull(weeks0);
    }

    @Test
    public void test0503() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0503");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime9 = property7.addWrapField((int) (short) -1);
        org.joda.time.MutableDateTime.Property property10 = mutableDateTime9.monthOfYear();
        org.joda.time.Duration duration12 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Duration duration14 = org.joda.time.Duration.standardDays((long) (short) 1);
        org.joda.time.Duration duration16 = duration12.withDurationAdded((org.joda.time.ReadableDuration) duration14, (int) (byte) -1);
        org.joda.time.MutableInterval mutableInterval17 = new org.joda.time.MutableInterval((org.joda.time.ReadableInstant) mutableDateTime9, (org.joda.time.ReadableDuration) duration14);
        mutableInterval17.setInterval((long) '4', 2700000L);
        org.joda.time.Chronology chronology22 = null;
        org.joda.time.DateMidnight dateMidnight23 = new org.joda.time.DateMidnight((long) (short) -1, chronology22);
        org.joda.time.DateMidnight dateMidnight25 = dateMidnight23.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property26 = dateMidnight23.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime27 = dateMidnight23.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property28 = mutableDateTime27.centuryOfEra();
        mutableDateTime27.setSecondOfMinute(24);
        org.joda.time.DateTime dateTime31 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone32 = null;
        org.joda.time.DateTime dateTime33 = dateTime31.withZoneRetainFields(dateTimeZone32);
        boolean boolean34 = mutableDateTime27.isAfter((org.joda.time.ReadableInstant) dateTime31);
        mutableInterval17.setEnd((org.joda.time.ReadableInstant) dateTime31);
        org.joda.time.Chronology chronology37 = null;
        org.joda.time.DateMidnight dateMidnight38 = new org.joda.time.DateMidnight((long) (short) -1, chronology37);
        org.joda.time.MutableDateTime mutableDateTime39 = dateMidnight38.toMutableDateTime();
        org.joda.time.PeriodType periodType41 = null;
        org.joda.time.Chronology chronology42 = null;
        org.joda.time.Period period43 = new org.joda.time.Period((long) (short) 10, periodType41, chronology42);
        org.joda.time.Months months44 = org.joda.time.Months.FIVE;
        org.joda.time.Period period45 = period43.minus((org.joda.time.ReadablePeriod) months44);
        org.joda.time.Chronology chronology47 = null;
        org.joda.time.DateMidnight dateMidnight48 = new org.joda.time.DateMidnight((long) (short) -1, chronology47);
        org.joda.time.DateMidnight dateMidnight50 = dateMidnight48.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology52 = null;
        org.joda.time.DateMidnight dateMidnight53 = new org.joda.time.DateMidnight((long) (short) -1, chronology52);
        org.joda.time.DateMidnight dateMidnight55 = dateMidnight53.minusMonths((int) (byte) 100);
        int int56 = dateMidnight50.compareTo((org.joda.time.ReadableInstant) dateMidnight53);
        org.joda.time.Duration duration57 = period45.toDurationTo((org.joda.time.ReadableInstant) dateMidnight53);
        org.joda.time.DateMidnight dateMidnight58 = dateMidnight38.minus((org.joda.time.ReadableDuration) duration57);
        org.joda.time.Duration duration60 = duration57.minus((long) 3600);
        // The following exception was thrown during execution in test generation
        try {
            mutableInterval17.setDurationAfterStart((org.joda.time.ReadableDuration) duration60);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The end instant must be greater than the start instant");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(mutableDateTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(duration12);
        org.junit.Assert.assertNotNull(duration14);
        org.junit.Assert.assertNotNull(duration16);
        org.junit.Assert.assertNotNull(dateMidnight25);
        org.junit.Assert.assertNotNull(property26);
        org.junit.Assert.assertNotNull(mutableDateTime27);
        org.junit.Assert.assertNotNull(property28);
        org.junit.Assert.assertNotNull(dateTime33);
        org.junit.Assert.assertTrue("'" + boolean34 + "' != '" + false + "'", boolean34 == false);
        org.junit.Assert.assertNotNull(mutableDateTime39);
        org.junit.Assert.assertNotNull(months44);
        org.junit.Assert.assertNotNull(period45);
        org.junit.Assert.assertNotNull(dateMidnight50);
        org.junit.Assert.assertNotNull(dateMidnight55);
        org.junit.Assert.assertTrue("'" + int56 + "' != '" + (-1) + "'", int56 == (-1));
        org.junit.Assert.assertNotNull(duration57);
        org.junit.Assert.assertNotNull(dateMidnight58);
        org.junit.Assert.assertNotNull(duration60);
    }

    @Test
    public void test0504() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0504");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.timeElementParser();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone6 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology7 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone6);
        org.joda.time.LocalDate localDate8 = new org.joda.time.LocalDate(1L, (org.joda.time.DateTimeZone) fixedDateTimeZone6);
        org.joda.time.format.DateTimeFormatter dateTimeFormatter9 = dateTimeFormatter0.withZone((org.joda.time.DateTimeZone) fixedDateTimeZone6);
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
        org.junit.Assert.assertNotNull(copticChronology7);
        org.junit.Assert.assertNotNull(dateTimeFormatter9);
    }

    @Test
    public void test0505() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0505");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.TimeOfDay timeOfDay4 = new org.joda.time.TimeOfDay(2026, 51, (int) (byte) -1, 4);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 2026 for hourOfDay must not be larger than 23");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0506() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0506");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.YearMonthDay yearMonthDay5 = dateTime0.toYearMonthDay();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone10 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.DateTime dateTime11 = yearMonthDay5.toDateTimeAtMidnight((org.joda.time.DateTimeZone) fixedDateTimeZone10);
        org.joda.time.LocalDate localDate12 = yearMonthDay5.toLocalDate();
        org.joda.time.LocalDate localDate14 = localDate12.minusMonths(0);
        int int15 = localDate12.getYear();
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(yearMonthDay5);
        org.junit.Assert.assertNotNull(dateTime11);
        org.junit.Assert.assertNotNull(localDate12);
        org.junit.Assert.assertNotNull(localDate14);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 2026 + "'", int15 == 2026);
    }

    @Test
    public void test0507() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0507");
        org.joda.time.LocalTime localTime1 = new org.joda.time.LocalTime((long) (-1));
        org.joda.time.LocalTime localTime3 = localTime1.withSecondOfMinute(1);
        org.joda.time.LocalTime localTime5 = localTime1.plusSeconds(0);
        org.joda.time.LocalTime localTime7 = localTime1.plusSeconds(86400000);
        int int9 = localTime1.getValue((int) (byte) 0);
        org.junit.Assert.assertNotNull(localTime3);
        org.junit.Assert.assertNotNull(localTime5);
        org.junit.Assert.assertNotNull(localTime7);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
    }

    @Test
    public void test0508() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0508");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Interval interval2 = yearMonth0.toInterval(dateTimeZone1);
        org.joda.time.Months months3 = org.joda.time.Months.ONE;
        org.joda.time.Interval interval4 = interval2.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) months3);
        org.joda.time.Duration duration5 = interval4.toDuration();
        long long6 = duration5.getStandardSeconds();
        org.joda.time.Duration duration8 = org.joda.time.Duration.standardDays((long) (short) 1);
        org.joda.time.Chronology chronology10 = null;
        org.joda.time.DateMidnight dateMidnight11 = new org.joda.time.DateMidnight((long) (short) -1, chronology10);
        org.joda.time.DateMidnight dateMidnight13 = dateMidnight11.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight16 = dateMidnight11.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight18 = dateMidnight16.withYear(100);
        org.joda.time.PeriodType periodType20 = null;
        org.joda.time.Chronology chronology21 = null;
        org.joda.time.Period period22 = new org.joda.time.Period((long) (short) 10, periodType20, chronology21);
        org.joda.time.Months months23 = org.joda.time.Months.FIVE;
        org.joda.time.Period period24 = period22.minus((org.joda.time.ReadablePeriod) months23);
        org.joda.time.Chronology chronology26 = null;
        org.joda.time.DateMidnight dateMidnight27 = new org.joda.time.DateMidnight((long) (short) -1, chronology26);
        org.joda.time.DateMidnight dateMidnight29 = dateMidnight27.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology31 = null;
        org.joda.time.DateMidnight dateMidnight32 = new org.joda.time.DateMidnight((long) (short) -1, chronology31);
        org.joda.time.DateMidnight dateMidnight34 = dateMidnight32.minusMonths((int) (byte) 100);
        int int35 = dateMidnight29.compareTo((org.joda.time.ReadableInstant) dateMidnight32);
        org.joda.time.Duration duration36 = period24.toDurationTo((org.joda.time.ReadableInstant) dateMidnight32);
        org.joda.time.Duration duration37 = duration36.negated();
        org.joda.time.PeriodType periodType38 = org.joda.time.PeriodType.yearDay();
        org.joda.time.PeriodType periodType39 = periodType38.withSecondsRemoved();
        org.joda.time.MutablePeriod mutablePeriod40 = new org.joda.time.MutablePeriod((org.joda.time.ReadableInstant) dateMidnight16, (org.joda.time.ReadableDuration) duration36, periodType39);
        int int41 = duration8.compareTo((org.joda.time.ReadableDuration) duration36);
        int int42 = duration5.compareTo((org.joda.time.ReadableDuration) duration36);
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertNotNull(months3);
        org.junit.Assert.assertNotNull(interval4);
        org.junit.Assert.assertNotNull(duration5);
        org.junit.Assert.assertTrue("'" + long6 + "' != '" + 2678400L + "'", long6 == 2678400L);
        org.junit.Assert.assertNotNull(duration8);
        org.junit.Assert.assertNotNull(dateMidnight13);
        org.junit.Assert.assertNotNull(dateMidnight16);
        org.junit.Assert.assertNotNull(dateMidnight18);
        org.junit.Assert.assertNotNull(months23);
        org.junit.Assert.assertNotNull(period24);
        org.junit.Assert.assertNotNull(dateMidnight29);
        org.junit.Assert.assertNotNull(dateMidnight34);
        org.junit.Assert.assertTrue("'" + int35 + "' != '" + (-1) + "'", int35 == (-1));
        org.junit.Assert.assertNotNull(duration36);
        org.junit.Assert.assertNotNull(duration37);
        org.junit.Assert.assertNotNull(periodType38);
        org.junit.Assert.assertNotNull(periodType39);
        org.junit.Assert.assertTrue("'" + int41 + "' != '" + 1 + "'", int41 == 1);
        org.junit.Assert.assertTrue("'" + int42 + "' != '" + 1 + "'", int42 == 1);
    }

    @Test
    public void test0509() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0509");
        org.joda.time.IllegalInstantException illegalInstantException1 = new org.joda.time.IllegalInstantException("40256602");
    }

    @Test
    public void test0510() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0510");
        org.joda.time.ReadableInterval readableInterval0 = null;
        org.joda.time.Seconds seconds1 = org.joda.time.Seconds.secondsIn(readableInterval0);
        org.joda.time.ReadableInterval readableInterval2 = null;
        org.joda.time.Seconds seconds3 = org.joda.time.Seconds.secondsIn(readableInterval2);
        org.joda.time.Seconds seconds4 = seconds1.plus(seconds3);
        int int5 = seconds1.getSeconds();
        java.lang.String str6 = seconds1.toString();
        org.junit.Assert.assertNotNull(seconds1);
        org.junit.Assert.assertNotNull(seconds3);
        org.junit.Assert.assertNotNull(seconds4);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 0 + "'", int5 == 0);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "PT0S" + "'", str6, "PT0S");
    }

    @Test
    public void test0511() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0511");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.minusHours((int) (byte) -1);
        org.joda.time.ReadableInterval readableInterval4 = null;
        org.joda.time.Seconds seconds5 = org.joda.time.Seconds.secondsIn(readableInterval4);
        org.joda.time.ReadableInterval readableInterval6 = null;
        org.joda.time.Seconds seconds7 = org.joda.time.Seconds.secondsIn(readableInterval6);
        org.joda.time.Seconds seconds8 = seconds5.plus(seconds7);
        org.joda.time.PeriodType periodType9 = seconds5.getPeriodType();
        org.joda.time.Seconds seconds10 = org.joda.time.Seconds.TWO;
        boolean boolean11 = seconds5.isGreaterThan(seconds10);
        org.joda.time.LocalDateTime localDateTime12 = localDateTime1.minus((org.joda.time.ReadablePeriod) seconds5);
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(seconds5);
        org.junit.Assert.assertNotNull(seconds7);
        org.junit.Assert.assertNotNull(seconds8);
        org.junit.Assert.assertNotNull(periodType9);
        org.junit.Assert.assertNotNull(seconds10);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNotNull(localDateTime12);
    }

    @Test
    public void test0512() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0512");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.MonthDay monthDay4 = monthDay2.minusDays((int) '4');
        int[] intArray5 = monthDay4.getValues();
        int int6 = monthDay4.size();
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(monthDay4);
        org.junit.Assert.assertNotNull(intArray5);
        org.junit.Assert.assertArrayEquals(intArray5, new int[] { 11, 10 });
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 2 + "'", int6 == 2);
    }

    @Test
    public void test0513() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0513");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.ReadableInterval readableInterval4 = null;
        org.joda.time.Seconds seconds5 = org.joda.time.Seconds.secondsIn(readableInterval4);
        org.joda.time.DurationFieldType durationFieldType6 = seconds5.getFieldType();
        int int7 = period3.get(durationFieldType6);
        org.joda.time.Period period9 = period3.withWeeks(12);
        org.junit.Assert.assertNotNull(seconds5);
        org.junit.Assert.assertNotNull(durationFieldType6);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertNotNull(period9);
    }

    @Test
    public void test0514() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0514");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        int int10 = dateMidnight4.compareTo((org.joda.time.ReadableInstant) dateMidnight7);
        org.joda.time.MutableDateTime mutableDateTime11 = dateMidnight7.toMutableDateTime();
        org.joda.time.PeriodType periodType13 = null;
        org.joda.time.Chronology chronology14 = null;
        org.joda.time.Period period15 = new org.joda.time.Period((long) (short) 10, periodType13, chronology14);
        org.joda.time.Months months16 = org.joda.time.Months.FIVE;
        org.joda.time.Period period17 = period15.minus((org.joda.time.ReadablePeriod) months16);
        org.joda.time.Period period19 = period15.plusMinutes((int) ' ');
        org.joda.time.DurationFieldType[] durationFieldTypeArray20 = period15.getFieldTypes();
        org.joda.time.Period period22 = period15.minusDays((int) (short) 100);
        org.joda.time.DateMidnight dateMidnight23 = dateMidnight7.minus((org.joda.time.ReadablePeriod) period22);
        int int24 = dateMidnight23.getYearOfCentury();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + (-1) + "'", int10 == (-1));
        org.junit.Assert.assertNotNull(mutableDateTime11);
        org.junit.Assert.assertNotNull(months16);
        org.junit.Assert.assertNotNull(period17);
        org.junit.Assert.assertNotNull(period19);
        org.junit.Assert.assertNotNull(durationFieldTypeArray20);
        org.junit.Assert.assertNotNull(period22);
        org.junit.Assert.assertNotNull(dateMidnight23);
        org.junit.Assert.assertTrue("'" + int24 + "' != '" + 70 + "'", int24 == 70);
    }

    @Test
    public void test0515() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0515");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.MonthDay monthDay4 = monthDay2.minusDays((int) '4');
        org.joda.time.MonthDay.Property property5 = monthDay2.monthOfYear();
        java.lang.String str6 = property5.getName();
        org.joda.time.DateTimeField dateTimeField7 = property5.getField();
        int int8 = property5.getMaximumValueOverall();
        java.util.Locale locale10 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MonthDay monthDay11 = property5.setCopy("PeriodType[Millis]", locale10);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value \"PeriodType[Millis]\" for monthOfYear is not supported");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(monthDay4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "monthOfYear" + "'", str6, "monthOfYear");
        org.junit.Assert.assertNotNull(dateTimeField7);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 12 + "'", int8 == 12);
    }

    @Test
    public void test0516() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0516");
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
        org.joda.time.chrono.CopticChronology copticChronology21 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        java.lang.String str22 = copticChronology21.toString();
        org.junit.Assert.assertNotNull(copticChronology5);
        org.junit.Assert.assertNotNull(leapYearPatternType6);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(property12);
        org.junit.Assert.assertNotNull(mutableDateTime13);
        org.junit.Assert.assertNotNull(property14);
        org.junit.Assert.assertNotNull(mutableDateTime16);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(islamicChronology18);
        org.junit.Assert.assertNotNull(copticChronology21);
        org.junit.Assert.assertEquals("'" + str22 + "' != '" + "CopticChronology[2026-08-06T11:10:36.726]" + "'", str22, "CopticChronology[2026-08-06T11:10:36.726]");
    }

    @Test
    public void test0517() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0517");
        org.joda.time.IllegalFieldValueException illegalFieldValueException2 = new org.joda.time.IllegalFieldValueException("2026-08-06T11:10:39.499", "hi!");
        java.lang.String str3 = illegalFieldValueException2.toString();
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "org.joda.time.IllegalFieldValueException: Value \"hi!\" for 2026-08-06T11:10:39.499 is not supported" + "'", str3, "org.joda.time.IllegalFieldValueException: Value \"hi!\" for 2026-08-06T11:10:39.499 is not supported");
    }

    @Test
    public void test0518() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0518");
        org.joda.time.Hours hours1 = org.joda.time.Hours.hours(100);
        org.joda.time.Hours hours3 = hours1.minus((int) (byte) 0);
        org.joda.time.Hours hours5 = hours1.plus(4);
        org.junit.Assert.assertNotNull(hours1);
        org.junit.Assert.assertNotNull(hours3);
        org.junit.Assert.assertNotNull(hours5);
    }

    @Test
    public void test0519() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0519");
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
        org.joda.time.PeriodType periodType15 = null;
        org.joda.time.Chronology chronology16 = null;
        org.joda.time.Period period17 = new org.joda.time.Period((long) (short) 10, periodType15, chronology16);
        org.joda.time.ReadableInterval readableInterval18 = null;
        org.joda.time.Seconds seconds19 = org.joda.time.Seconds.secondsIn(readableInterval18);
        org.joda.time.DurationFieldType durationFieldType20 = seconds19.getFieldType();
        int int21 = period17.get(durationFieldType20);
        mutablePeriod13.add(durationFieldType20, 3600);
        org.junit.Assert.assertNotNull(duration1);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(dateTime6);
        org.junit.Assert.assertNotNull(localDateTime10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(dateTime12);
        org.junit.Assert.assertNotNull(seconds19);
        org.junit.Assert.assertNotNull(durationFieldType20);
        org.junit.Assert.assertTrue("'" + int21 + "' != '" + 0 + "'", int21 == 0);
    }

    @Test
    public void test0520() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0520");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime8 = property7.roundHalfCeiling();
        org.joda.time.MutableDateTime mutableDateTime9 = property7.getMutableDateTime();
        org.joda.time.MutableDateTime.Property property10 = mutableDateTime9.minuteOfDay();
        mutableDateTime9.setWeekyear((int) 'a');
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(mutableDateTime8);
        org.junit.Assert.assertNotNull(mutableDateTime9);
        org.junit.Assert.assertNotNull(property10);
    }

    @Test
    public void test0521() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0521");
        org.joda.time.convert.ConverterManager converterManager0 = org.joda.time.convert.ConverterManager.getInstance();
        org.joda.time.convert.PartialConverter partialConverter1 = null;
        org.joda.time.convert.PartialConverter partialConverter2 = converterManager0.addPartialConverter(partialConverter1);
        org.joda.time.convert.PartialConverter partialConverter3 = null;
        org.joda.time.convert.PartialConverter partialConverter4 = converterManager0.addPartialConverter(partialConverter3);
        org.joda.time.format.PeriodFormatter periodFormatter5 = org.joda.time.format.PeriodFormat.wordBased();
        org.joda.time.format.PeriodParser periodParser6 = periodFormatter5.getParser();
        boolean boolean7 = periodFormatter5.isPrinter();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.convert.InstantConverter instantConverter8 = converterManager0.getInstantConverter((java.lang.Object) periodFormatter5);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No instant converter found for type: org.joda.time.format.PeriodFormatter");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(converterManager0);
        org.junit.Assert.assertNull(partialConverter2);
        org.junit.Assert.assertNull(partialConverter4);
        org.junit.Assert.assertNotNull(periodFormatter5);
        org.junit.Assert.assertNotNull(periodParser6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
    }

    @Test
    public void test0522() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0522");
        org.joda.time.Duration duration1 = org.joda.time.Duration.standardSeconds((-3599891L));
        org.junit.Assert.assertNotNull(duration1);
    }

    @Test
    public void test0523() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0523");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.MonthDay monthDay4 = monthDay2.minusDays((int) '4');
        org.joda.time.MonthDay.Property property5 = monthDay2.monthOfYear();
        java.lang.String str6 = property5.toString();
        org.joda.time.MonthDay monthDay8 = property5.setCopy((int) (short) 10);
        org.joda.time.ReadableInstant readableInstant9 = null;
        // The following exception was thrown during execution in test generation
        try {
            int int10 = property5.compareTo(readableInstant9);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The instant must not be null");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(monthDay4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "Property[monthOfYear]" + "'", str6, "Property[monthOfYear]");
        org.junit.Assert.assertNotNull(monthDay8);
    }

    @Test
    public void test0524() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0524");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField1 = julianChronology0.clockhourOfDay();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj3 = null;
        boolean boolean4 = gJChronology2.equals(obj3);
        org.joda.time.DurationField durationField5 = gJChronology2.days();
        org.joda.time.chrono.GJChronology gJChronology6 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology7 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology6);
        org.joda.time.chrono.LenientChronology lenientChronology8 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology6);
        org.joda.time.DateTimeField dateTimeField9 = lenientChronology8.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField10 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology2, dateTimeField9);
        org.joda.time.DateTimeFieldType dateTimeFieldType11 = skipUndoDateTimeField10.getType();
        org.joda.time.field.DividedDateTimeField dividedDateTimeField13 = new org.joda.time.field.DividedDateTimeField(dateTimeField1, dateTimeFieldType11, (int) 'a');
        org.joda.time.DurationField durationField14 = dividedDateTimeField13.getRangeDurationField();
        int int15 = dividedDateTimeField13.getMaximumValue();
        // The following exception was thrown during execution in test generation
        try {
            long long17 = dividedDateTimeField13.roundHalfFloor((-262800000L));
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 0 for clockhourOfDay must be in the range [1,24]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(dateTimeField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(durationField5);
        org.junit.Assert.assertNotNull(gJChronology6);
        org.junit.Assert.assertNotNull(lenientChronology7);
        org.junit.Assert.assertNotNull(lenientChronology8);
        org.junit.Assert.assertNotNull(dateTimeField9);
        org.junit.Assert.assertNotNull(dateTimeFieldType11);
        org.junit.Assert.assertNotNull(durationField14);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 0 + "'", int15 == 0);
    }

    @Test
    public void test0525() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0525");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.Months months4 = org.joda.time.Months.FIVE;
        org.joda.time.Period period5 = period3.minus((org.joda.time.ReadablePeriod) months4);
        org.joda.time.Period period7 = period3.plusMinutes((int) ' ');
        org.joda.time.DurationFieldType[] durationFieldTypeArray8 = period3.getFieldTypes();
        org.joda.time.Period period10 = period3.minusDays((int) (short) 100);
        org.joda.time.MutablePeriod mutablePeriod11 = period3.toMutablePeriod();
        org.joda.time.Duration duration13 = org.joda.time.Duration.standardDays((long) (short) 1);
        mutablePeriod11.add((org.joda.time.ReadableDuration) duration13);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MutableInterval mutableInterval15 = new org.joda.time.MutableInterval((java.lang.Object) duration13);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No interval converter found for type: org.joda.time.Duration");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(months4);
        org.junit.Assert.assertNotNull(period5);
        org.junit.Assert.assertNotNull(period7);
        org.junit.Assert.assertNotNull(durationFieldTypeArray8);
        org.junit.Assert.assertNotNull(period10);
        org.junit.Assert.assertNotNull(mutablePeriod11);
        org.junit.Assert.assertNotNull(duration13);
    }

    @Test
    public void test0526() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0526");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime3.yearOfCentury();
        org.joda.time.LocalDateTime localDateTime5 = property4.withMinimumValue();
        org.joda.time.DateTimeField dateTimeField6 = property4.getField();
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(localDateTime5);
        org.junit.Assert.assertNotNull(dateTimeField6);
    }

    @Test
    public void test0527() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0527");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Interval interval2 = yearMonth0.toInterval(dateTimeZone1);
        org.joda.time.Months months3 = org.joda.time.Months.ONE;
        org.joda.time.Interval interval4 = interval2.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) months3);
        org.joda.time.DateTime dateTime5 = interval2.getEnd();
        org.joda.time.DateTime dateTime8 = dateTime5.withDurationAdded((long) (byte) 100, 60);
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertNotNull(months3);
        org.junit.Assert.assertNotNull(interval4);
        org.junit.Assert.assertNotNull(dateTime5);
        org.junit.Assert.assertNotNull(dateTime8);
    }

    @Test
    public void test0528() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0528");
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
        org.joda.time.DateTime.Property property14 = dateTime10.secondOfDay();
        org.joda.time.DateTime dateTime15 = dateTime10.withEarlierOffsetAtOverlap();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(dateTime12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(property14);
        org.junit.Assert.assertNotNull(dateTime15);
    }

    @Test
    public void test0529() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0529");
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
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone22 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology23 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone22);
        org.joda.time.LocalDateTime localDateTime24 = new org.joda.time.LocalDateTime((long) (-1), (org.joda.time.DateTimeZone) fixedDateTimeZone22);
        org.joda.time.LocalDateTime localDateTime25 = new org.joda.time.LocalDateTime((org.joda.time.DateTimeZone) fixedDateTimeZone22);
        boolean boolean26 = org.joda.time.field.FieldUtils.equals((java.lang.Object) interval13, (java.lang.Object) localDateTime25);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(period15);
        org.junit.Assert.assertNotNull(interval16);
        org.junit.Assert.assertNotNull(gregorianChronology23);
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + false + "'", boolean26 == false);
    }

    @Test
    public void test0530() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0530");
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
        org.joda.time.DateTimeField dateTimeField11 = org.joda.time.field.StrictDateTimeField.getInstance((org.joda.time.DateTimeField) skipUndoDateTimeField8);
        org.joda.time.field.FieldUtils.verifyValueBounds((org.joda.time.DateTimeField) skipUndoDateTimeField8, 59, 10, 69);
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
    public void test0531() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0531");
        org.joda.time.Years years1 = org.joda.time.Years.years((int) (byte) 10);
        org.joda.time.Years years3 = years1.dividedBy(1);
        org.joda.time.Years years5 = years1.plus(60);
        org.junit.Assert.assertNotNull(years1);
        org.junit.Assert.assertNotNull(years3);
        org.junit.Assert.assertNotNull(years5);
    }

    @Test
    public void test0532() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0532");
        org.joda.time.MutablePeriod mutablePeriod8 = new org.joda.time.MutablePeriod((int) (byte) 1, (int) (short) 0, (int) (byte) -1, (int) (short) -1, (int) (byte) 1, 0, 100, 3);
        org.joda.time.format.PeriodFormatter periodFormatter9 = org.joda.time.format.PeriodFormat.wordBased();
        org.joda.time.format.PeriodParser periodParser10 = periodFormatter9.getParser();
        java.lang.String str11 = mutablePeriod8.toString(periodFormatter9);
        org.joda.time.PeriodType periodType13 = null;
        org.joda.time.Chronology chronology14 = null;
        org.joda.time.Period period15 = new org.joda.time.Period((long) (short) 10, periodType13, chronology14);
        org.joda.time.ReadableInterval readableInterval16 = null;
        org.joda.time.Seconds seconds17 = org.joda.time.Seconds.secondsIn(readableInterval16);
        org.joda.time.DurationFieldType durationFieldType18 = seconds17.getFieldType();
        int int19 = period15.get(durationFieldType18);
        org.joda.time.Period period21 = period15.minusHours(86400000);
        org.joda.time.Period period23 = period21.minusMonths(9);
        java.lang.String str24 = periodFormatter9.print((org.joda.time.ReadablePeriod) period21);
        org.junit.Assert.assertNotNull(periodFormatter9);
        org.junit.Assert.assertNotNull(periodParser10);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "1 year, -1 weeks, -1 days, 1 hour, 100 seconds and 3 milliseconds" + "'", str11, "1 year, -1 weeks, -1 days, 1 hour, 100 seconds and 3 milliseconds");
        org.junit.Assert.assertNotNull(seconds17);
        org.junit.Assert.assertNotNull(durationFieldType18);
        org.junit.Assert.assertTrue("'" + int19 + "' != '" + 0 + "'", int19 == 0);
        org.junit.Assert.assertNotNull(period21);
        org.junit.Assert.assertNotNull(period23);
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "-86400000 hours and 10 milliseconds" + "'", str24, "-86400000 hours and 10 milliseconds");
    }

    @Test
    public void test0533() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0533");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.Months months4 = org.joda.time.Months.FIVE;
        org.joda.time.Period period5 = period3.minus((org.joda.time.ReadablePeriod) months4);
        org.joda.time.Period period7 = period3.plusMinutes((int) ' ');
        org.joda.time.DurationFieldType[] durationFieldTypeArray8 = period3.getFieldTypes();
        org.joda.time.Period period10 = period3.minusDays((int) (short) 100);
        org.joda.time.Period period12 = period3.withHours(9);
        org.junit.Assert.assertNotNull(months4);
        org.junit.Assert.assertNotNull(period5);
        org.junit.Assert.assertNotNull(period7);
        org.junit.Assert.assertNotNull(durationFieldTypeArray8);
        org.junit.Assert.assertNotNull(period10);
        org.junit.Assert.assertNotNull(period12);
    }

    @Test
    public void test0534() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0534");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.withYear(100);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.plusDays(10);
        org.joda.time.DateMidnight dateMidnight13 = dateMidnight11.plusYears((int) (byte) 10);
        org.joda.time.DateMidnight dateMidnight15 = dateMidnight13.minusMonths(40260602);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(dateMidnight13);
        org.junit.Assert.assertNotNull(dateMidnight15);
    }

    @Test
    public void test0535() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0535");
        org.joda.time.PeriodType periodType0 = org.joda.time.PeriodType.hours();
        org.junit.Assert.assertNotNull(periodType0);
    }

    @Test
    public void test0536() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0536");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Interval interval2 = yearMonth0.toInterval(dateTimeZone1);
        org.joda.time.Months months3 = org.joda.time.Months.ONE;
        org.joda.time.Interval interval4 = interval2.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) months3);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Interval interval6 = interval2.withEndMillis(32010L);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The end instant must be greater than the start instant");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertNotNull(months3);
        org.junit.Assert.assertNotNull(interval4);
    }

    @Test
    public void test0537() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0537");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Months months1 = org.joda.time.Months.parseMonths("seconds");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid format: \"seconds\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0538() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0538");
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
        org.joda.time.Minutes minutes17 = org.joda.time.Minutes.MAX_VALUE;
        org.joda.time.Minutes minutes18 = org.joda.time.Minutes.MAX_VALUE;
        boolean boolean19 = minutes17.isLessThan(minutes18);
        int int20 = minutes18.getMinutes();
        org.joda.time.Minutes minutes21 = org.joda.time.Minutes.MAX_VALUE;
        org.joda.time.Minutes minutes22 = org.joda.time.Minutes.MAX_VALUE;
        boolean boolean23 = minutes21.isLessThan(minutes22);
        int int24 = minutes18.compareTo((org.joda.time.base.BaseSingleFieldPeriod) minutes21);
        org.joda.time.Minutes minutes25 = null;
        org.joda.time.Minutes minutes26 = minutes18.plus(minutes25);
        org.joda.time.Minutes minutes27 = minutes16.plus(minutes26);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MonthDay monthDay28 = new org.joda.time.MonthDay((java.lang.Object) minutes16);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No partial converter found for type: org.joda.time.Minutes");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(dateMidnight14);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + (-1) + "'", int15 == (-1));
        org.junit.Assert.assertNotNull(minutes16);
        org.junit.Assert.assertNotNull(minutes17);
        org.junit.Assert.assertNotNull(minutes18);
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
        org.junit.Assert.assertTrue("'" + int20 + "' != '" + 2147483647 + "'", int20 == 2147483647);
        org.junit.Assert.assertNotNull(minutes21);
        org.junit.Assert.assertNotNull(minutes22);
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
        org.junit.Assert.assertTrue("'" + int24 + "' != '" + 0 + "'", int24 == 0);
        org.junit.Assert.assertNotNull(minutes26);
        org.junit.Assert.assertNotNull(minutes27);
    }

    @Test
    public void test0539() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0539");
        int int0 = org.joda.time.YearMonthDay.YEAR;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 0 + "'", int0 == 0);
    }

    @Test
    public void test0540() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0540");
        org.joda.time.Minutes minutes1 = org.joda.time.Minutes.minutes(70);
        org.joda.time.Period period2 = minutes1.toPeriod();
        int int3 = minutes1.getMinutes();
        org.junit.Assert.assertNotNull(minutes1);
        org.junit.Assert.assertNotNull(period2);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 70 + "'", int3 == 70);
    }

    @Test
    public void test0541() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0541");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        org.joda.time.LocalDateTime localDateTime5 = property4.roundHalfFloorCopy();
        org.joda.time.LocalDateTime localDateTime7 = localDateTime5.minusSeconds((int) (byte) 10);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDateTime localDateTime11 = localDateTime5.withDate((int) (byte) 0, 0, (int) (short) 1);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 0 for monthOfYear must be in the range [1,12]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(localDateTime5);
        org.junit.Assert.assertNotNull(localDateTime7);
    }

    @Test
    public void test0542() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0542");
        org.joda.time.Seconds seconds0 = org.joda.time.Seconds.TWO;
        org.joda.time.PeriodType periodType1 = seconds0.getPeriodType();
        org.junit.Assert.assertNotNull(seconds0);
        org.junit.Assert.assertNotNull(periodType1);
    }

    @Test
    public void test0543() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0543");
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
        org.joda.time.DateTime dateTime20 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone21 = null;
        org.joda.time.DateTime dateTime22 = dateTime20.withZoneRetainFields(dateTimeZone21);
        org.joda.time.DateTime dateTime24 = dateTime22.plusHours(0);
        org.joda.time.LocalDateTime localDateTime25 = dateTime22.toLocalDateTime();
        org.joda.time.DateTimeComparator dateTimeComparator26 = org.joda.time.DateTimeComparator.getDateOnlyInstance();
        org.joda.time.DateTimeComparator dateTimeComparator27 = org.joda.time.DateTimeComparator.getTimeOnlyInstance();
        java.util.Comparator<java.lang.Object> objComparator28 = dateTimeComparator26.thenComparing((java.util.Comparator<java.lang.Object>) dateTimeComparator27);
        org.joda.time.DateTimeFieldType dateTimeFieldType29 = dateTimeComparator27.getUpperLimit();
        org.joda.time.LocalDateTime.Property property30 = localDateTime25.property(dateTimeFieldType29);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MonthDay.Property property31 = monthDay19.property(dateTimeFieldType29);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Field 'dayOfYear' is not supported");
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
        org.junit.Assert.assertNotNull(dateTime22);
        org.junit.Assert.assertNotNull(dateTime24);
        org.junit.Assert.assertNotNull(localDateTime25);
        org.junit.Assert.assertNotNull(dateTimeComparator26);
        org.junit.Assert.assertNotNull(dateTimeComparator27);
        org.junit.Assert.assertNotNull(objComparator28);
        org.junit.Assert.assertNotNull(dateTimeFieldType29);
        org.junit.Assert.assertNotNull(property30);
    }

    @Test
    public void test0544() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0544");
        org.joda.time.Hours hours0 = org.joda.time.Hours.EIGHT;
        org.joda.time.Hours hours2 = hours0.dividedBy((int) (byte) 10);
        org.junit.Assert.assertNotNull(hours0);
        org.junit.Assert.assertNotNull(hours2);
    }

    @Test
    public void test0545() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0545");
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder0 = new org.joda.time.format.PeriodFormatterBuilder();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder3 = periodFormatterBuilder0.appendPrefix("PeriodType[Millis]", "");
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder5 = periodFormatterBuilder3.minimumPrintedDigits((int) (short) -1);
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder6 = periodFormatterBuilder5.appendMinutes();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder7 = periodFormatterBuilder6.printZeroRarelyLast();
        org.junit.Assert.assertNotNull(periodFormatterBuilder3);
        org.junit.Assert.assertNotNull(periodFormatterBuilder5);
        org.junit.Assert.assertNotNull(periodFormatterBuilder6);
        org.junit.Assert.assertNotNull(periodFormatterBuilder7);
    }

    @Test
    public void test0546() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0546");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime8 = property7.roundHalfCeiling();
        org.joda.time.MutableDateTime mutableDateTime9 = property7.getMutableDateTime();
        mutableDateTime9.addMinutes(40237996);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(mutableDateTime8);
        org.junit.Assert.assertNotNull(mutableDateTime9);
    }

    @Test
    public void test0547() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0547");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        int int8 = skipUndoDateTimeField6.get((long) 10);
        org.joda.time.DurationField durationField9 = skipUndoDateTimeField6.getRangeDurationField();
        org.joda.time.field.OffsetDateTimeField offsetDateTimeField11 = new org.joda.time.field.OffsetDateTimeField((org.joda.time.DateTimeField) skipUndoDateTimeField6, 8);
        int int13 = skipUndoDateTimeField6.get((long) 100);
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 2 + "'", int8 == 2);
        org.junit.Assert.assertNotNull(durationField9);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 2 + "'", int13 == 2);
    }

    @Test
    public void test0548() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0548");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.YearMonthDay yearMonthDay5 = dateTime0.toYearMonthDay();
        org.joda.time.DateTime dateTime6 = yearMonthDay5.toDateTimeAtCurrentTime();
        org.joda.time.DateTime dateTime8 = dateTime6.minusYears(40260602);
        org.joda.time.DateTime.Property property9 = dateTime6.millisOfSecond();
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(yearMonthDay5);
        org.junit.Assert.assertNotNull(dateTime6);
        org.junit.Assert.assertNotNull(dateTime8);
        org.junit.Assert.assertNotNull(property9);
    }

    @Test
    public void test0549() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0549");
        java.lang.StringBuffer stringBuffer0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.format.FormatUtils.appendPaddedInteger(stringBuffer0, 7254093240000000L, 8);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0550() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0550");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.DateMidnight dateMidnight7 = property5.addWrapFieldToCopy(86400000);
        org.joda.time.DateMidnight dateMidnight9 = property5.addToCopy((int) (short) 100);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(dateMidnight9);
    }

    @Test
    public void test0551() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0551");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.DateMidnight dateMidnight7 = property5.setCopy((int) (byte) 100);
        org.joda.time.LocalDateTime localDateTime8 = new org.joda.time.LocalDateTime((java.lang.Object) dateMidnight7);
        org.joda.time.DateMidnight dateMidnight10 = dateMidnight7.withMonthOfYear(12);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(dateMidnight10);
    }

    @Test
    public void test0552() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0552");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone5 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology6 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.LocalDate localDate7 = new org.joda.time.LocalDate(1L, (org.joda.time.DateTimeZone) fixedDateTimeZone5);
        int int8 = localDate7.getWeekOfWeekyear();
        org.junit.Assert.assertNotNull(copticChronology6);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 1 + "'", int8 == 1);
    }

    @Test
    public void test0553() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0553");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.Minutes minutes3 = org.joda.time.Minutes.ONE;
        org.joda.time.Minutes minutes5 = minutes3.minus((-1));
        org.joda.time.DateTime dateTime6 = dateTime0.plus((org.joda.time.ReadablePeriod) minutes5);
        int int7 = dateTime6.getYearOfEra();
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(minutes3);
        org.junit.Assert.assertNotNull(minutes5);
        org.junit.Assert.assertNotNull(dateTime6);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 2026 + "'", int7 == 2026);
    }

    @Test
    public void test0554() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0554");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone11 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology12 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone11);
        java.lang.String str13 = copticChronology12.toString();
        org.joda.time.DateTimeField dateTimeField14 = copticChronology12.clockhourOfDay();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone20 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology21 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone20);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType22 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology24 = null;
        org.joda.time.DateMidnight dateMidnight25 = new org.joda.time.DateMidnight((long) (short) -1, chronology24);
        org.joda.time.DateMidnight dateMidnight27 = dateMidnight25.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property28 = dateMidnight25.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime29 = dateMidnight25.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property30 = mutableDateTime29.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime32 = property30.addWrapField((int) (short) -1);
        boolean boolean33 = leapYearPatternType22.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology34 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone20, leapYearPatternType22);
        org.joda.time.MutableDateTime mutableDateTime35 = new org.joda.time.MutableDateTime((long) (byte) 10, (org.joda.time.DateTimeZone) fixedDateTimeZone20);
        boolean boolean36 = copticChronology12.equals((java.lang.Object) mutableDateTime35);
        org.joda.time.DurationField durationField37 = copticChronology12.halfdays();
        org.joda.time.DateTimeZone dateTimeZone38 = null;
        org.joda.time.LocalDateTime localDateTime39 = new org.joda.time.LocalDateTime(dateTimeZone38);
        int int40 = localDateTime39.getYear();
        org.joda.time.LocalDateTime localDateTime42 = localDateTime39.plusMillis(10);
        org.joda.time.chrono.GJChronology gJChronology43 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj44 = null;
        boolean boolean45 = gJChronology43.equals(obj44);
        org.joda.time.DurationField durationField46 = gJChronology43.days();
        org.joda.time.chrono.GJChronology gJChronology47 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology48 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology47);
        org.joda.time.chrono.LenientChronology lenientChronology49 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology47);
        org.joda.time.DateTimeField dateTimeField50 = lenientChronology49.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField51 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology43, dateTimeField50);
        org.joda.time.DateTimeFieldType dateTimeFieldType52 = skipUndoDateTimeField51.getType();
        int int53 = skipUndoDateTimeField51.getMinimumValue();
        java.lang.String str54 = skipUndoDateTimeField51.getName();
        org.joda.time.DateTimeFieldType dateTimeFieldType55 = skipUndoDateTimeField51.getType();
        org.joda.time.LocalDateTime localDateTime57 = localDateTime39.withField(dateTimeFieldType55, 24);
        org.joda.time.field.RemainderDateTimeField remainderDateTimeField59 = new org.joda.time.field.RemainderDateTimeField(dateTimeField4, durationField37, dateTimeFieldType55, (int) (byte) 10);
        boolean boolean61 = remainderDateTimeField59.isLeap((long) ' ');
        long long64 = remainderDateTimeField59.add((long) 1000, (long) 3600);
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertNotNull(copticChronology12);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "CopticChronology[2026-08-06T11:10:36.726]" + "'", str13, "CopticChronology[2026-08-06T11:10:36.726]");
        org.junit.Assert.assertNotNull(dateTimeField14);
        org.junit.Assert.assertNotNull(copticChronology21);
        org.junit.Assert.assertNotNull(leapYearPatternType22);
        org.junit.Assert.assertNotNull(dateMidnight27);
        org.junit.Assert.assertNotNull(property28);
        org.junit.Assert.assertNotNull(mutableDateTime29);
        org.junit.Assert.assertNotNull(property30);
        org.junit.Assert.assertNotNull(mutableDateTime32);
        org.junit.Assert.assertTrue("'" + boolean33 + "' != '" + false + "'", boolean33 == false);
        org.junit.Assert.assertNotNull(islamicChronology34);
        org.junit.Assert.assertTrue("'" + boolean36 + "' != '" + false + "'", boolean36 == false);
        org.junit.Assert.assertNotNull(durationField37);
        org.junit.Assert.assertTrue("'" + int40 + "' != '" + 2026 + "'", int40 == 2026);
        org.junit.Assert.assertNotNull(localDateTime42);
        org.junit.Assert.assertNotNull(gJChronology43);
        org.junit.Assert.assertTrue("'" + boolean45 + "' != '" + false + "'", boolean45 == false);
        org.junit.Assert.assertNotNull(durationField46);
        org.junit.Assert.assertNotNull(gJChronology47);
        org.junit.Assert.assertNotNull(lenientChronology48);
        org.junit.Assert.assertNotNull(lenientChronology49);
        org.junit.Assert.assertNotNull(dateTimeField50);
        org.junit.Assert.assertNotNull(dateTimeFieldType52);
        org.junit.Assert.assertTrue("'" + int53 + "' != '" + 0 + "'", int53 == 0);
        org.junit.Assert.assertEquals("'" + str54 + "' != '" + "yearOfCentury" + "'", str54, "yearOfCentury");
        org.junit.Assert.assertNotNull(dateTimeFieldType55);
        org.junit.Assert.assertNotNull(localDateTime57);
        org.junit.Assert.assertTrue("'" + boolean61 + "' != '" + false + "'", boolean61 == false);
        org.junit.Assert.assertTrue("'" + long64 + "' != '" + 2177280001000L + "'", long64 == 2177280001000L);
    }

    @Test
    public void test0555() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0555");
        // The following exception was thrown during execution in test generation
        try {
            int int4 = org.joda.time.field.FieldUtils.getWrappedValue((int) (byte) 0, 5, (int) (short) 1, (int) (short) 0);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: MIN > MAX");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0556() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0556");
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
        org.joda.time.DateTime dateTime17 = dateTime15.plusYears(40237996);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTime dateTime19 = dateTime17.withEra((int) ' ');
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 32 for era must be in the range [0,1]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(dateTime12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(dateTime15);
        org.junit.Assert.assertNotNull(dateTime17);
    }

    @Test
    public void test0557() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0557");
        int int0 = org.joda.time.TimeOfDay.MILLIS_OF_SECOND;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 3 + "'", int0 == 3);
    }

    @Test
    public void test0558() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0558");
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
        org.joda.time.MutableDateTime.Property property14 = mutableDateTime12.secondOfDay();
        mutableDateTime12.addSeconds(1);
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(dateMidnight10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(mutableDateTime12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(property14);
    }

    @Test
    public void test0559() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0559");
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
        // The following exception was thrown during execution in test generation
        try {
            long long13 = skipUndoDateTimeField8.set((long) 100, 3600);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 3600 for yearOfCentury must be in the range [0,100]");
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
        org.junit.Assert.assertNotNull(dateTimeFieldType9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
    }

    @Test
    public void test0560() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0560");
        org.joda.time.ReadableInterval readableInterval0 = null;
        org.joda.time.Seconds seconds1 = org.joda.time.Seconds.secondsIn(readableInterval0);
        org.joda.time.DurationFieldType durationFieldType2 = seconds1.getFieldType();
        org.joda.time.field.PreciseDurationField preciseDurationField4 = new org.joda.time.field.PreciseDurationField(durationFieldType2, (long) 59);
        int int6 = preciseDurationField4.getValue((long) 'a');
        long long9 = preciseDurationField4.getValueAsLong((long) (byte) -1, (long) 100);
        java.lang.String str10 = preciseDurationField4.toString();
        org.junit.Assert.assertNotNull(seconds1);
        org.junit.Assert.assertNotNull(durationFieldType2);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 1 + "'", int6 == 1);
        org.junit.Assert.assertTrue("'" + long9 + "' != '" + 0L + "'", long9 == 0L);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "DurationField[seconds]" + "'", str10, "DurationField[seconds]");
    }

    @Test
    public void test0561() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0561");
        org.joda.time.TimeOfDay timeOfDay1 = new org.joda.time.TimeOfDay((long) 100);
        org.joda.time.TimeOfDay timeOfDay3 = timeOfDay1.plusHours((int) (byte) -1);
        org.joda.time.TimeOfDay.Property property4 = timeOfDay3.millisOfSecond();
        java.lang.String str5 = property4.getName();
        org.junit.Assert.assertNotNull(timeOfDay3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "millisOfSecond" + "'", str5, "millisOfSecond");
    }

    @Test
    public void test0562() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0562");
        org.joda.time.LocalTime localTime1 = new org.joda.time.LocalTime((long) (-1));
        org.joda.time.LocalTime localTime3 = localTime1.withSecondOfMinute(1);
        org.joda.time.LocalTime localTime5 = localTime1.plusSeconds(0);
        org.joda.time.LocalTime localTime7 = localTime1.plusSeconds(86400000);
        org.joda.time.LocalTime.Property property8 = localTime1.hourOfDay();
        java.util.Locale locale10 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalTime localTime11 = property8.setCopy("1 year, -1 weeks, -1 days, 1 hour, 100 seconds and 3 milliseconds", locale10);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value \"1 year, -1 weeks, -1 days, 1 hour, 100 seconds and 3 milliseconds\" for hourOfDay is not supported");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(localTime3);
        org.junit.Assert.assertNotNull(localTime5);
        org.junit.Assert.assertNotNull(localTime7);
        org.junit.Assert.assertNotNull(property8);
    }

    @Test
    public void test0563() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0563");
        org.joda.time.TimeOfDay timeOfDay0 = org.joda.time.TimeOfDay.MIDNIGHT;
        org.junit.Assert.assertNotNull(timeOfDay0);
    }

    @Test
    public void test0564() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0564");
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
        int int20 = dateMidnight19.getDayOfWeek();
        org.joda.time.ReadablePartial readablePartial21 = null;
        org.joda.time.DateMidnight dateMidnight22 = dateMidnight19.withFields(readablePartial21);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(dateMidnight14);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + (-1) + "'", int15 == (-1));
        org.junit.Assert.assertNotNull(minutes16);
        org.junit.Assert.assertNotNull(dateMidnight19);
        org.junit.Assert.assertTrue("'" + int20 + "' != '" + 5 + "'", int20 == 5);
        org.junit.Assert.assertNotNull(dateMidnight22);
    }

    @Test
    public void test0565() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0565");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField1 = julianChronology0.clockhourOfDay();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj3 = null;
        boolean boolean4 = gJChronology2.equals(obj3);
        org.joda.time.DurationField durationField5 = gJChronology2.days();
        org.joda.time.chrono.GJChronology gJChronology6 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology7 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology6);
        org.joda.time.chrono.LenientChronology lenientChronology8 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology6);
        org.joda.time.DateTimeField dateTimeField9 = lenientChronology8.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField10 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology2, dateTimeField9);
        org.joda.time.DateTimeFieldType dateTimeFieldType11 = skipUndoDateTimeField10.getType();
        org.joda.time.field.DividedDateTimeField dividedDateTimeField13 = new org.joda.time.field.DividedDateTimeField(dateTimeField1, dateTimeFieldType11, (int) 'a');
        org.joda.time.DurationField durationField14 = dividedDateTimeField13.getRangeDurationField();
        int int15 = dividedDateTimeField13.getMaximumValue();
        // The following exception was thrown during execution in test generation
        try {
            long long18 = dividedDateTimeField13.addWrapField((long) 53, 168);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: MIN > MAX");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(dateTimeField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(durationField5);
        org.junit.Assert.assertNotNull(gJChronology6);
        org.junit.Assert.assertNotNull(lenientChronology7);
        org.junit.Assert.assertNotNull(lenientChronology8);
        org.junit.Assert.assertNotNull(dateTimeField9);
        org.junit.Assert.assertNotNull(dateTimeFieldType11);
        org.junit.Assert.assertNotNull(durationField14);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 0 + "'", int15 == 0);
    }

    @Test
    public void test0566() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0566");
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
        org.joda.time.YearMonthDay yearMonthDay30 = new org.joda.time.YearMonthDay((org.joda.time.DateTimeZone) fixedDateTimeZone15);
        org.joda.time.YearMonthDay yearMonthDay32 = yearMonthDay30.plusYears((int) 'a');
        org.joda.time.DateTimeZone dateTimeZone34 = null;
        org.joda.time.LocalDateTime localDateTime35 = new org.joda.time.LocalDateTime(dateTimeZone34);
        java.lang.String str36 = localDateTime35.toString();
        int[] intArray37 = localDateTime35.getValues();
        java.util.Locale locale39 = null;
        // The following exception was thrown during execution in test generation
        try {
            int[] intArray40 = skipUndoDateTimeField8.set((org.joda.time.ReadablePartial) yearMonthDay30, (int) (short) -1, intArray37, "monthOfYear", locale39);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value \"monthOfYear\" for yearOfCentury is not supported");
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
        org.junit.Assert.assertNotNull(dateTimeFieldType9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
        org.junit.Assert.assertNotNull(copticChronology16);
        org.junit.Assert.assertNotNull(leapYearPatternType17);
        org.junit.Assert.assertNotNull(dateMidnight22);
        org.junit.Assert.assertNotNull(property23);
        org.junit.Assert.assertNotNull(mutableDateTime24);
        org.junit.Assert.assertNotNull(property25);
        org.junit.Assert.assertNotNull(mutableDateTime27);
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + false + "'", boolean28 == false);
        org.junit.Assert.assertNotNull(islamicChronology29);
        org.junit.Assert.assertNotNull(yearMonthDay32);
// flaky "1) test0566(RegressionTest1)":         org.junit.Assert.assertEquals("'" + str36 + "' != '" + "2026-08-06T11:10:56.115" + "'", str36, "2026-08-06T11:10:56.115");
        org.junit.Assert.assertNotNull(intArray37);
// flaky "1) test0566(RegressionTest1)":         org.junit.Assert.assertArrayEquals(intArray37, new int[] { 2026, 8, 6, 40256115 });
    }

    @Test
    public void test0567() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0567");
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
        org.joda.time.Period period24 = org.joda.time.Period.weeks((int) (short) 100);
        int int25 = period24.getDays();
        org.joda.time.Period period27 = period24.minusWeeks(100);
        mutableInterval22.setPeriodBeforeEnd((org.joda.time.ReadablePeriod) period24);
        org.joda.time.Chronology chronology29 = mutableInterval22.getChronology();
        org.joda.time.Chronology chronology31 = null;
        org.joda.time.DateMidnight dateMidnight32 = new org.joda.time.DateMidnight((long) (short) -1, chronology31);
        org.joda.time.DateMidnight dateMidnight34 = dateMidnight32.minusYears(100);
        org.joda.time.DateMidnight.Property property35 = dateMidnight32.monthOfYear();
        org.joda.time.DateMidnight dateMidnight36 = property35.roundHalfEvenCopy();
        org.joda.time.Chronology chronology38 = null;
        org.joda.time.DateMidnight dateMidnight39 = new org.joda.time.DateMidnight((long) (short) -1, chronology38);
        org.joda.time.DateMidnight dateMidnight41 = dateMidnight39.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology43 = null;
        org.joda.time.DateMidnight dateMidnight44 = new org.joda.time.DateMidnight((long) (short) -1, chronology43);
        org.joda.time.DateMidnight dateMidnight46 = dateMidnight44.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology48 = null;
        org.joda.time.DateMidnight dateMidnight49 = new org.joda.time.DateMidnight((long) (short) -1, chronology48);
        org.joda.time.DateMidnight dateMidnight51 = dateMidnight49.minusMonths((int) (byte) 100);
        int int52 = dateMidnight46.compareTo((org.joda.time.ReadableInstant) dateMidnight49);
        org.joda.time.Minutes minutes53 = org.joda.time.Minutes.minutesBetween((org.joda.time.ReadableInstant) dateMidnight39, (org.joda.time.ReadableInstant) dateMidnight46);
        org.joda.time.DateMidnight dateMidnight56 = dateMidnight46.withDurationAdded((long) '4', 100);
        org.joda.time.DateMidnight dateMidnight58 = dateMidnight56.withWeekOfWeekyear((int) '#');
        org.joda.time.MutableDateTime mutableDateTime59 = dateMidnight56.toMutableDateTimeISO();
        // The following exception was thrown during execution in test generation
        try {
            mutableInterval22.setInterval((org.joda.time.ReadableInstant) dateMidnight36, (org.joda.time.ReadableInstant) mutableDateTime59);
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
        org.junit.Assert.assertNotNull(period24);
        org.junit.Assert.assertTrue("'" + int25 + "' != '" + 0 + "'", int25 == 0);
        org.junit.Assert.assertNotNull(period27);
        org.junit.Assert.assertNotNull(chronology29);
        org.junit.Assert.assertNotNull(dateMidnight34);
        org.junit.Assert.assertNotNull(property35);
        org.junit.Assert.assertNotNull(dateMidnight36);
        org.junit.Assert.assertNotNull(dateMidnight41);
        org.junit.Assert.assertNotNull(dateMidnight46);
        org.junit.Assert.assertNotNull(dateMidnight51);
        org.junit.Assert.assertTrue("'" + int52 + "' != '" + (-1) + "'", int52 == (-1));
        org.junit.Assert.assertNotNull(minutes53);
        org.junit.Assert.assertNotNull(dateMidnight56);
        org.junit.Assert.assertNotNull(dateMidnight58);
        org.junit.Assert.assertNotNull(mutableDateTime59);
    }

    @Test
    public void test0568() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0568");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) 2000, chronology1);
    }

    @Test
    public void test0569() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0569");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        org.joda.time.ReadableInterval readableInterval5 = null;
        org.joda.time.Seconds seconds6 = org.joda.time.Seconds.secondsIn(readableInterval5);
        org.joda.time.DurationFieldType durationFieldType7 = seconds6.getFieldType();
        org.joda.time.LocalDateTime localDateTime9 = localDateTime1.withFieldAdded(durationFieldType7, 86400000);
        org.joda.time.DateTimeField[] dateTimeFieldArray10 = localDateTime9.getFields();
        int int11 = localDateTime9.getYearOfCentury();
        java.util.Locale locale13 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str14 = localDateTime9.toString("1961W355T000000.000+0100", locale13);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Illegal pattern component: W");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(seconds6);
        org.junit.Assert.assertNotNull(durationFieldType7);
        org.junit.Assert.assertNotNull(localDateTime9);
        org.junit.Assert.assertNotNull(dateTimeFieldArray10);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 29 + "'", int11 == 29);
    }

    @Test
    public void test0570() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0570");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone6 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology7 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone6);
        org.joda.time.LocalDateTime localDateTime8 = new org.joda.time.LocalDateTime((long) (-1), (org.joda.time.DateTimeZone) fixedDateTimeZone6);
        org.joda.time.tz.CachedDateTimeZone cachedDateTimeZone9 = org.joda.time.tz.CachedDateTimeZone.forZone((org.joda.time.DateTimeZone) fixedDateTimeZone6);
        long long11 = fixedDateTimeZone6.previousTransition(1L);
        java.lang.String str13 = fixedDateTimeZone6.getName((long) '#');
        org.joda.time.DateTime dateTime14 = new org.joda.time.DateTime((long) 2026, (org.joda.time.DateTimeZone) fixedDateTimeZone6);
        int int16 = fixedDateTimeZone6.getStandardOffset((-1L));
        org.joda.time.chrono.IslamicChronology islamicChronology17 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone6);
        org.junit.Assert.assertNotNull(gregorianChronology7);
        org.junit.Assert.assertNotNull(cachedDateTimeZone9);
        org.junit.Assert.assertTrue("'" + long11 + "' != '" + 1L + "'", long11 == 1L);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "+00:00:00.100" + "'", str13, "+00:00:00.100");
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 0 + "'", int16 == 0);
        org.junit.Assert.assertNotNull(islamicChronology17);
    }

    @Test
    public void test0571() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0571");
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
        int int12 = property11.get();
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(localDateTime8);
        org.junit.Assert.assertNotNull(property9);
        org.junit.Assert.assertNotNull(dateTime10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 2026 + "'", int12 == 2026);
    }

    @Test
    public void test0572() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0572");
        java.lang.ClassLoader classLoader1 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.tz.ZoneInfoProvider zoneInfoProvider2 = new org.joda.time.tz.ZoneInfoProvider("[]", classLoader1);
            org.junit.Assert.fail("Expected exception of type java.io.IOException; message: Resource not found: \"[]/ZoneInfoMap\" ClassLoader: system");
        } catch (java.io.IOException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0573() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0573");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone4 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.ISOChronology iSOChronology5 = org.joda.time.chrono.ISOChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        org.joda.time.chrono.GregorianChronology gregorianChronology6 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        long long9 = fixedDateTimeZone4.adjustOffset((-262800000L), false);
        org.junit.Assert.assertNotNull(iSOChronology5);
        org.junit.Assert.assertNotNull(gregorianChronology6);
        org.junit.Assert.assertTrue("'" + long9 + "' != '" + (-262800000L) + "'", long9 == (-262800000L));
    }

    @Test
    public void test0574() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0574");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTime dateTime7 = new org.joda.time.DateTime(20, (int) (byte) -1, 9, 12, (-1), 3, (int) '4');
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value -1 for minuteOfHour must be in the range [0,59]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0575() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0575");
        int int0 = org.joda.time.YearMonthDay.DAY_OF_MONTH;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 2 + "'", int0 == 2);
    }

    @Test
    public void test0576() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0576");
        org.joda.time.PeriodType periodType8 = org.joda.time.PeriodType.yearDay();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MutablePeriod mutablePeriod9 = new org.joda.time.MutablePeriod(70, 70, (int) (short) -1, 12, 1970, 86400, 3600, (int) (byte) -1, periodType8);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Period does not support field 'months'");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(periodType8);
    }

    @Test
    public void test0577() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0577");
        org.joda.time.ReadableInterval readableInterval0 = null;
        org.joda.time.Seconds seconds1 = org.joda.time.Seconds.secondsIn(readableInterval0);
        org.joda.time.ReadableInterval readableInterval2 = null;
        org.joda.time.Seconds seconds3 = org.joda.time.Seconds.secondsIn(readableInterval2);
        org.joda.time.Seconds seconds4 = seconds1.plus(seconds3);
        org.joda.time.PeriodType periodType5 = seconds1.getPeriodType();
        org.joda.time.DurationFieldType durationFieldType6 = null;
        boolean boolean7 = periodType5.isSupported(durationFieldType6);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDateTime localDateTime8 = new org.joda.time.LocalDateTime((java.lang.Object) boolean7);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No partial converter found for type: java.lang.Boolean");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(seconds1);
        org.junit.Assert.assertNotNull(seconds3);
        org.junit.Assert.assertNotNull(seconds4);
        org.junit.Assert.assertNotNull(periodType5);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
    }

    @Test
    public void test0578() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0578");
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
        java.lang.String str21 = property20.getName();
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
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "monthOfYear" + "'", str21, "monthOfYear");
    }

    @Test
    public void test0579() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0579");
        long long2 = org.joda.time.field.FieldUtils.safeMultiply((long) 'a', 3);
        org.junit.Assert.assertTrue("'" + long2 + "' != '" + 291L + "'", long2 == 291L);
    }

    @Test
    public void test0580() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0580");
        org.joda.time.PeriodType periodType0 = org.joda.time.PeriodType.yearDay();
        org.joda.time.PeriodType periodType1 = periodType0.withSecondsRemoved();
        org.joda.time.DateTimeZone dateTimeZone2 = null;
        org.joda.time.LocalDateTime localDateTime3 = new org.joda.time.LocalDateTime(dateTimeZone2);
        org.joda.time.LocalDateTime localDateTime5 = localDateTime3.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property6 = localDateTime3.weekOfWeekyear();
        org.joda.time.LocalDateTime localDateTime7 = property6.roundHalfFloorCopy();
        org.joda.time.LocalDateTime localDateTime9 = localDateTime7.minusSeconds((int) (byte) 10);
        boolean boolean10 = periodType1.equals((java.lang.Object) localDateTime7);
        int int11 = localDateTime7.getMillisOfSecond();
        org.junit.Assert.assertNotNull(periodType0);
        org.junit.Assert.assertNotNull(periodType1);
        org.junit.Assert.assertNotNull(localDateTime5);
        org.junit.Assert.assertNotNull(property6);
        org.junit.Assert.assertNotNull(localDateTime7);
        org.junit.Assert.assertNotNull(localDateTime9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
    }

    @Test
    public void test0581() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0581");
        org.joda.time.MutablePeriod mutablePeriod2 = new org.joda.time.MutablePeriod((long) (short) -1, (long) (short) 1);
        org.joda.time.Chronology chronology4 = null;
        org.joda.time.DateMidnight dateMidnight5 = new org.joda.time.DateMidnight((long) (short) -1, chronology4);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight5.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight10 = dateMidnight5.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight12 = dateMidnight10.withYear(100);
        org.joda.time.Chronology chronology14 = null;
        org.joda.time.DateMidnight dateMidnight15 = new org.joda.time.DateMidnight((long) (short) -1, chronology14);
        org.joda.time.Interval interval16 = new org.joda.time.Interval((org.joda.time.ReadableInstant) dateMidnight12, (org.joda.time.ReadableInstant) dateMidnight15);
        mutablePeriod2.setPeriod((org.joda.time.ReadableInterval) interval16);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(dateMidnight10);
        org.junit.Assert.assertNotNull(dateMidnight12);
    }

    @Test
    public void test0582() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0582");
        org.joda.time.PeriodType periodType1 = org.joda.time.PeriodType.millis();
        org.joda.time.PeriodType periodType2 = periodType1.withWeeksRemoved();
        org.joda.time.MutablePeriod mutablePeriod3 = new org.joda.time.MutablePeriod((long) (byte) 10, periodType2);
        int int4 = mutablePeriod3.getDays();
        // The following exception was thrown during execution in test generation
        try {
            mutablePeriod3.setMinutes(6);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Period does not support field 'minutes'");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(periodType1);
        org.junit.Assert.assertNotNull(periodType2);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
    }

    @Test
    public void test0583() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0583");
        int int0 = org.joda.time.DateTimeConstants.WEDNESDAY;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 3 + "'", int0 == 3);
    }

    @Test
    public void test0584() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0584");
        org.joda.time.MutablePeriod mutablePeriod4 = new org.joda.time.MutablePeriod((int) 'a', 29, (int) (byte) 100, 40243947);
        mutablePeriod4.add((long) 13);
    }

    @Test
    public void test0585() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0585");
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
        org.joda.time.TimeOfDay timeOfDay20 = org.joda.time.TimeOfDay.fromMillisOfDay((long) 13, (org.joda.time.Chronology) islamicChronology19);
        org.junit.Assert.assertNotNull(copticChronology6);
        org.junit.Assert.assertNotNull(leapYearPatternType7);
        org.junit.Assert.assertNotNull(dateMidnight12);
        org.junit.Assert.assertNotNull(property13);
        org.junit.Assert.assertNotNull(mutableDateTime14);
        org.junit.Assert.assertNotNull(property15);
        org.junit.Assert.assertNotNull(mutableDateTime17);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertNotNull(islamicChronology19);
        org.junit.Assert.assertNotNull(timeOfDay20);
    }

    @Test
    public void test0586() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0586");
        org.joda.time.ReadablePeriod readablePeriod0 = null;
        org.joda.time.Hours hours1 = org.joda.time.Hours.standardHoursIn(readablePeriod0);
        org.junit.Assert.assertNotNull(hours1);
    }

    @Test
    public void test0587() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0587");
        int int3 = org.joda.time.field.FieldUtils.getWrappedValue(40237996, 0, 20);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 1 + "'", int3 == 1);
    }

    @Test
    public void test0588() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0588");
        org.joda.time.Days days0 = org.joda.time.Days.ZERO;
        org.junit.Assert.assertNotNull(days0);
    }

    @Test
    public void test0589() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0589");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.DateTimeFormat.shortDateTime();
        org.joda.time.format.DateTimeFormatter dateTimeFormatter2 = dateTimeFormatter0.withDefaultYear((int) ' ');
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
        org.junit.Assert.assertNotNull(dateTimeFormatter2);
    }

    @Test
    public void test0590() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0590");
        org.joda.time.convert.ConverterManager converterManager0 = org.joda.time.convert.ConverterManager.getInstance();
        org.joda.time.convert.PartialConverter partialConverter1 = null;
        org.joda.time.convert.PartialConverter partialConverter2 = converterManager0.addPartialConverter(partialConverter1);
        org.joda.time.convert.InstantConverter instantConverter3 = null;
        org.joda.time.convert.InstantConverter instantConverter4 = converterManager0.addInstantConverter(instantConverter3);
        org.joda.time.convert.PeriodConverter periodConverter5 = null;
        org.joda.time.convert.PeriodConverter periodConverter6 = converterManager0.addPeriodConverter(periodConverter5);
        org.junit.Assert.assertNotNull(converterManager0);
        org.junit.Assert.assertNull(partialConverter2);
        org.junit.Assert.assertNull(instantConverter4);
        org.junit.Assert.assertNull(periodConverter6);
    }

    @Test
    public void test0591() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0591");
        org.joda.time.MutablePeriod mutablePeriod2 = new org.joda.time.MutablePeriod((long) (short) -1, (long) (short) 1);
        mutablePeriod2.addMinutes(0);
    }

    @Test
    public void test0592() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0592");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime2.plusHours(0);
        org.joda.time.LocalDateTime localDateTime5 = dateTime2.toLocalDateTime();
        org.joda.time.DateTimeComparator dateTimeComparator6 = org.joda.time.DateTimeComparator.getDateOnlyInstance();
        org.joda.time.DateTimeComparator dateTimeComparator7 = org.joda.time.DateTimeComparator.getTimeOnlyInstance();
        java.util.Comparator<java.lang.Object> objComparator8 = dateTimeComparator6.thenComparing((java.util.Comparator<java.lang.Object>) dateTimeComparator7);
        org.joda.time.DateTimeFieldType dateTimeFieldType9 = dateTimeComparator7.getUpperLimit();
        org.joda.time.LocalDateTime.Property property10 = localDateTime5.property(dateTimeFieldType9);
        org.joda.time.LocalDateTime.Property property11 = localDateTime5.weekyear();
        org.joda.time.LocalDateTime localDateTime13 = localDateTime5.minusDays(0);
        int int14 = localDateTime5.size();
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(localDateTime5);
        org.junit.Assert.assertNotNull(dateTimeComparator6);
        org.junit.Assert.assertNotNull(dateTimeComparator7);
        org.junit.Assert.assertNotNull(objComparator8);
        org.junit.Assert.assertNotNull(dateTimeFieldType9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(localDateTime13);
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 4 + "'", int14 == 4);
    }

    @Test
    public void test0593() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0593");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology2 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology1);
        org.joda.time.TimeOfDay timeOfDay3 = org.joda.time.TimeOfDay.fromMillisOfDay((long) 2026, (org.joda.time.Chronology) lenientChronology2);
        org.joda.time.DateTimeField dateTimeField4 = lenientChronology2.monthOfYear();
        org.joda.time.field.FieldUtils.verifyValueBounds(dateTimeField4, (int) 'a', 59, 40260602);
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(lenientChronology2);
        org.junit.Assert.assertNotNull(timeOfDay3);
        org.junit.Assert.assertNotNull(dateTimeField4);
    }

    @Test
    public void test0594() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0594");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = property3.addToCopy(1);
        org.joda.time.DateTime dateTime6 = localDate5.toDateTimeAtCurrentTime();
        org.joda.time.LocalDate.Property property7 = localDate5.monthOfYear();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDate localDate9 = localDate5.withMonthOfYear(53);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 53 for monthOfYear must be in the range [1,12]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(dateTime6);
        org.junit.Assert.assertNotNull(property7);
    }

    @Test
    public void test0595() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0595");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        mutableDateTime6.setSecondOfMinute(24);
        org.joda.time.chrono.JulianChronology julianChronology10 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField11 = julianChronology10.weeks();
        org.joda.time.chrono.GJChronology gJChronology12 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology13 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology12);
        org.joda.time.DateTimeField dateTimeField14 = gJChronology12.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField16 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology10, dateTimeField14, (int) '4');
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone21 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology22 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone21);
        java.lang.String str23 = copticChronology22.toString();
        org.joda.time.DateTimeField dateTimeField24 = copticChronology22.clockhourOfDay();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone30 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology31 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone30);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType32 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology34 = null;
        org.joda.time.DateMidnight dateMidnight35 = new org.joda.time.DateMidnight((long) (short) -1, chronology34);
        org.joda.time.DateMidnight dateMidnight37 = dateMidnight35.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property38 = dateMidnight35.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime39 = dateMidnight35.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property40 = mutableDateTime39.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime42 = property40.addWrapField((int) (short) -1);
        boolean boolean43 = leapYearPatternType32.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology44 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone30, leapYearPatternType32);
        org.joda.time.MutableDateTime mutableDateTime45 = new org.joda.time.MutableDateTime((long) (byte) 10, (org.joda.time.DateTimeZone) fixedDateTimeZone30);
        boolean boolean46 = copticChronology22.equals((java.lang.Object) mutableDateTime45);
        org.joda.time.DurationField durationField47 = copticChronology22.halfdays();
        org.joda.time.DateTimeZone dateTimeZone48 = null;
        org.joda.time.LocalDateTime localDateTime49 = new org.joda.time.LocalDateTime(dateTimeZone48);
        int int50 = localDateTime49.getYear();
        org.joda.time.LocalDateTime localDateTime52 = localDateTime49.plusMillis(10);
        org.joda.time.chrono.GJChronology gJChronology53 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj54 = null;
        boolean boolean55 = gJChronology53.equals(obj54);
        org.joda.time.DurationField durationField56 = gJChronology53.days();
        org.joda.time.chrono.GJChronology gJChronology57 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology58 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology57);
        org.joda.time.chrono.LenientChronology lenientChronology59 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology57);
        org.joda.time.DateTimeField dateTimeField60 = lenientChronology59.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField61 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology53, dateTimeField60);
        org.joda.time.DateTimeFieldType dateTimeFieldType62 = skipUndoDateTimeField61.getType();
        int int63 = skipUndoDateTimeField61.getMinimumValue();
        java.lang.String str64 = skipUndoDateTimeField61.getName();
        org.joda.time.DateTimeFieldType dateTimeFieldType65 = skipUndoDateTimeField61.getType();
        org.joda.time.LocalDateTime localDateTime67 = localDateTime49.withField(dateTimeFieldType65, 24);
        org.joda.time.field.RemainderDateTimeField remainderDateTimeField69 = new org.joda.time.field.RemainderDateTimeField(dateTimeField14, durationField47, dateTimeFieldType65, (int) (byte) 10);
        // The following exception was thrown during execution in test generation
        try {
            mutableDateTime6.set(dateTimeFieldType65, 2026);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 2026 for yearOfCentury must be in the range [0,99]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(julianChronology10);
        org.junit.Assert.assertNotNull(durationField11);
        org.junit.Assert.assertNotNull(gJChronology12);
        org.junit.Assert.assertNotNull(lenientChronology13);
        org.junit.Assert.assertNotNull(dateTimeField14);
        org.junit.Assert.assertNotNull(copticChronology22);
        org.junit.Assert.assertEquals("'" + str23 + "' != '" + "CopticChronology[2026-08-06T11:10:36.726]" + "'", str23, "CopticChronology[2026-08-06T11:10:36.726]");
        org.junit.Assert.assertNotNull(dateTimeField24);
        org.junit.Assert.assertNotNull(copticChronology31);
        org.junit.Assert.assertNotNull(leapYearPatternType32);
        org.junit.Assert.assertNotNull(dateMidnight37);
        org.junit.Assert.assertNotNull(property38);
        org.junit.Assert.assertNotNull(mutableDateTime39);
        org.junit.Assert.assertNotNull(property40);
        org.junit.Assert.assertNotNull(mutableDateTime42);
        org.junit.Assert.assertTrue("'" + boolean43 + "' != '" + false + "'", boolean43 == false);
        org.junit.Assert.assertNotNull(islamicChronology44);
        org.junit.Assert.assertTrue("'" + boolean46 + "' != '" + false + "'", boolean46 == false);
        org.junit.Assert.assertNotNull(durationField47);
        org.junit.Assert.assertTrue("'" + int50 + "' != '" + 2026 + "'", int50 == 2026);
        org.junit.Assert.assertNotNull(localDateTime52);
        org.junit.Assert.assertNotNull(gJChronology53);
        org.junit.Assert.assertTrue("'" + boolean55 + "' != '" + false + "'", boolean55 == false);
        org.junit.Assert.assertNotNull(durationField56);
        org.junit.Assert.assertNotNull(gJChronology57);
        org.junit.Assert.assertNotNull(lenientChronology58);
        org.junit.Assert.assertNotNull(lenientChronology59);
        org.junit.Assert.assertNotNull(dateTimeField60);
        org.junit.Assert.assertNotNull(dateTimeFieldType62);
        org.junit.Assert.assertTrue("'" + int63 + "' != '" + 0 + "'", int63 == 0);
        org.junit.Assert.assertEquals("'" + str64 + "' != '" + "yearOfCentury" + "'", str64, "yearOfCentury");
        org.junit.Assert.assertNotNull(dateTimeFieldType65);
        org.junit.Assert.assertNotNull(localDateTime67);
    }

    @Test
    public void test0596() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0596");
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
        org.joda.time.TimeOfDay timeOfDay25 = dateTime22.toTimeOfDay();
        int int26 = dateTime22.getYear();
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
        org.junit.Assert.assertNotNull(timeOfDay25);
        org.junit.Assert.assertTrue("'" + int26 + "' != '" + 2026 + "'", int26 == 2026);
    }

    @Test
    public void test0597() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0597");
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder0 = new org.joda.time.format.PeriodFormatterBuilder();
        periodFormatterBuilder0.clear();
        org.joda.time.format.PeriodFormatter periodFormatter2 = org.joda.time.format.ISOPeriodFormat.alternateWithWeeks();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder3 = periodFormatterBuilder0.append(periodFormatter2);
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder5 = periodFormatterBuilder3.rejectSignedValues(true);
        org.junit.Assert.assertNotNull(periodFormatter2);
        org.junit.Assert.assertNotNull(periodFormatterBuilder3);
        org.junit.Assert.assertNotNull(periodFormatterBuilder5);
    }

    @Test
    public void test0598() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0598");
        org.joda.time.TimeOfDay timeOfDay1 = new org.joda.time.TimeOfDay((long) 100);
        org.joda.time.TimeOfDay timeOfDay3 = timeOfDay1.minusMinutes((int) (short) 1);
        org.joda.time.LocalTime localTime4 = timeOfDay3.toLocalTime();
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight12 = dateMidnight7.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight14 = dateMidnight12.withYear(100);
        org.joda.time.PeriodType periodType16 = null;
        org.joda.time.Chronology chronology17 = null;
        org.joda.time.Period period18 = new org.joda.time.Period((long) (short) 10, periodType16, chronology17);
        org.joda.time.Months months19 = org.joda.time.Months.FIVE;
        org.joda.time.Period period20 = period18.minus((org.joda.time.ReadablePeriod) months19);
        org.joda.time.Chronology chronology22 = null;
        org.joda.time.DateMidnight dateMidnight23 = new org.joda.time.DateMidnight((long) (short) -1, chronology22);
        org.joda.time.DateMidnight dateMidnight25 = dateMidnight23.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology27 = null;
        org.joda.time.DateMidnight dateMidnight28 = new org.joda.time.DateMidnight((long) (short) -1, chronology27);
        org.joda.time.DateMidnight dateMidnight30 = dateMidnight28.minusMonths((int) (byte) 100);
        int int31 = dateMidnight25.compareTo((org.joda.time.ReadableInstant) dateMidnight28);
        org.joda.time.Duration duration32 = period20.toDurationTo((org.joda.time.ReadableInstant) dateMidnight28);
        org.joda.time.Duration duration33 = duration32.negated();
        org.joda.time.PeriodType periodType34 = org.joda.time.PeriodType.yearDay();
        org.joda.time.PeriodType periodType35 = periodType34.withSecondsRemoved();
        org.joda.time.MutablePeriod mutablePeriod36 = new org.joda.time.MutablePeriod((org.joda.time.ReadableInstant) dateMidnight12, (org.joda.time.ReadableDuration) duration32, periodType35);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MutablePeriod mutablePeriod37 = new org.joda.time.MutablePeriod((java.lang.Object) timeOfDay3, periodType35);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No period converter found for type: org.joda.time.TimeOfDay");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(timeOfDay3);
        org.junit.Assert.assertNotNull(localTime4);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(dateMidnight12);
        org.junit.Assert.assertNotNull(dateMidnight14);
        org.junit.Assert.assertNotNull(months19);
        org.junit.Assert.assertNotNull(period20);
        org.junit.Assert.assertNotNull(dateMidnight25);
        org.junit.Assert.assertNotNull(dateMidnight30);
        org.junit.Assert.assertTrue("'" + int31 + "' != '" + (-1) + "'", int31 == (-1));
        org.junit.Assert.assertNotNull(duration32);
        org.junit.Assert.assertNotNull(duration33);
        org.junit.Assert.assertNotNull(periodType34);
        org.junit.Assert.assertNotNull(periodType35);
    }

    @Test
    public void test0599() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0599");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.YearMonth yearMonth2 = new org.joda.time.YearMonth(70, (int) (short) 100);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 100 for monthOfYear must not be larger than 12");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0600() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0600");
        org.joda.time.Days days0 = org.joda.time.Days.ONE;
        org.joda.time.DurationFieldType durationFieldType1 = days0.getFieldType();
        java.lang.String str2 = days0.toString();
        org.joda.time.Days days3 = org.joda.time.Days.THREE;
        org.joda.time.Days days4 = days0.minus(days3);
        org.joda.time.DurationFieldType durationFieldType5 = days0.getFieldType();
        org.junit.Assert.assertNotNull(days0);
        org.junit.Assert.assertNotNull(durationFieldType1);
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "P1D" + "'", str2, "P1D");
        org.junit.Assert.assertNotNull(days3);
        org.junit.Assert.assertNotNull(days4);
        org.junit.Assert.assertNotNull(durationFieldType5);
    }

    @Test
    public void test0601() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0601");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.PeriodType periodType5 = null;
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.Period period7 = new org.joda.time.Period((long) (short) 10, periodType5, chronology6);
        org.joda.time.ReadableInterval readableInterval8 = null;
        org.joda.time.Seconds seconds9 = org.joda.time.Seconds.secondsIn(readableInterval8);
        org.joda.time.DurationFieldType durationFieldType10 = seconds9.getFieldType();
        int int11 = period7.get(durationFieldType10);
        org.joda.time.Period period13 = period7.minusHours(86400000);
        org.joda.time.Period period14 = period13.negated();
        org.joda.time.Period period15 = period3.minus((org.joda.time.ReadablePeriod) period13);
        org.joda.time.Period period17 = period3.minusMillis(24);
        org.junit.Assert.assertNotNull(seconds9);
        org.junit.Assert.assertNotNull(durationFieldType10);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertNotNull(period13);
        org.junit.Assert.assertNotNull(period14);
        org.junit.Assert.assertNotNull(period15);
        org.junit.Assert.assertNotNull(period17);
    }

    @Test
    public void test0602() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0602");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalTime localTime3 = new org.joda.time.LocalTime(1970, 2, 13);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 1970 for hourOfDay must be in the range [0,23]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0603() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0603");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateMidnight dateMidnight1 = org.joda.time.DateMidnight.parse("org/joda/time/tz/data");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid format: \"org/joda/time/tz/data\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0604() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0604");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.dateTimeParser();
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
    }

    @Test
    public void test0605() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0605");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter1 = org.joda.time.format.ISODateTimeFormat.basicDate();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MonthDay monthDay2 = org.joda.time.MonthDay.parse("1440", dateTimeFormatter1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid format: \"1440\" is too short");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeFormatter1);
    }

    @Test
    public void test0606() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0606");
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
        org.joda.time.DateMidnight dateMidnight24 = dateMidnight22.minusYears(100);
        org.joda.time.chrono.GJChronology gJChronology26 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology27 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology26);
        org.joda.time.TimeOfDay timeOfDay28 = org.joda.time.TimeOfDay.fromMillisOfDay((long) 2026, (org.joda.time.Chronology) lenientChronology27);
        org.joda.time.Chronology chronology29 = lenientChronology27.withUTC();
        org.joda.time.DateTime dateTime30 = dateMidnight24.toDateTime((org.joda.time.Chronology) lenientChronology27);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDate localDate31 = new org.joda.time.LocalDate((java.lang.Object) interval13, (org.joda.time.Chronology) lenientChronology27);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No partial converter found for type: org.joda.time.Interval");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(period15);
        org.junit.Assert.assertNotNull(interval16);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertTrue("'" + long19 + "' != '" + (-59011462664000L) + "'", long19 == (-59011462664000L));
        org.junit.Assert.assertNotNull(dateMidnight24);
        org.junit.Assert.assertNotNull(gJChronology26);
        org.junit.Assert.assertNotNull(lenientChronology27);
        org.junit.Assert.assertNotNull(timeOfDay28);
        org.junit.Assert.assertNotNull(chronology29);
        org.junit.Assert.assertNotNull(dateTime30);
    }

    @Test
    public void test0607() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0607");
        org.joda.time.YearMonth yearMonth0 = org.joda.time.YearMonth.now();
        org.junit.Assert.assertNotNull(yearMonth0);
    }

    @Test
    public void test0608() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0608");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.DateMidnight dateMidnight6 = property5.getDateMidnight();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateMidnight dateMidnight8 = property5.setCopy("ISOChronology[2026-08-06T11:10:36.726]");
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value \"ISOChronology[2026-08-06T11:10:36.726]\" for centuryOfEra is not supported");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(dateMidnight6);
    }

    @Test
    public void test0609() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0609");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime3.yearOfCentury();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDateTime localDateTime6 = localDateTime3.withEra((-1));
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value -1 for era must be in the range [0,1]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
    }

    @Test
    public void test0610() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0610");
        org.joda.time.tz.ZoneInfoLogger.set(true);
    }

    @Test
    public void test0611() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0611");
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
        java.lang.String str41 = fixedDateTimeZone4.getName(60000L);
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
        org.junit.Assert.assertEquals("'" + str41 + "' != '" + "+00:00:00.100" + "'", str41, "+00:00:00.100");
    }

    @Test
    public void test0612() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0612");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.basicOrdinalDate();
        java.lang.StringBuilder stringBuilder1 = null;
        org.joda.time.chrono.JulianChronology julianChronology3 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate4 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology3);
        org.joda.time.LocalDate.Property property5 = localDate4.yearOfCentury();
        org.joda.time.LocalDate localDate7 = localDate4.withWeekyear((int) (byte) 100);
        org.joda.time.Chronology chronology9 = null;
        org.joda.time.DateMidnight dateMidnight10 = new org.joda.time.DateMidnight((long) (short) -1, chronology9);
        org.joda.time.DateMidnight dateMidnight12 = dateMidnight10.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property13 = dateMidnight10.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime14 = dateMidnight10.toMutableDateTimeISO();
        boolean boolean15 = localDate7.equals((java.lang.Object) mutableDateTime14);
        // The following exception was thrown during execution in test generation
        try {
            dateTimeFormatter0.printTo(stringBuilder1, (org.joda.time.ReadableInstant) mutableDateTime14);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
        org.junit.Assert.assertNotNull(julianChronology3);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(localDate7);
        org.junit.Assert.assertNotNull(dateMidnight12);
        org.junit.Assert.assertNotNull(property13);
        org.junit.Assert.assertNotNull(mutableDateTime14);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test0613() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0613");
        org.joda.time.YearMonthDay yearMonthDay3 = new org.joda.time.YearMonthDay(70, 7, 1);
    }

    @Test
    public void test0614() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0614");
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
        org.joda.time.Chronology chronology18 = null;
        org.joda.time.DateMidnight dateMidnight19 = new org.joda.time.DateMidnight((long) (short) -1, chronology18);
        org.joda.time.DateMidnight dateMidnight21 = dateMidnight19.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight24 = dateMidnight19.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight26 = dateMidnight24.withYear(100);
        org.joda.time.Chronology chronology28 = null;
        org.joda.time.DateMidnight dateMidnight29 = new org.joda.time.DateMidnight((long) (short) -1, chronology28);
        org.joda.time.Interval interval30 = new org.joda.time.Interval((org.joda.time.ReadableInstant) dateMidnight26, (org.joda.time.ReadableInstant) dateMidnight29);
        org.joda.time.Period period32 = org.joda.time.Period.weeks((int) (short) 100);
        org.joda.time.Interval interval33 = interval30.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) period32);
        org.joda.time.ReadableInterval readableInterval34 = null;
        boolean boolean35 = interval30.abuts(readableInterval34);
        long long36 = interval30.getStartMillis();
        boolean boolean37 = interval16.abuts((org.joda.time.ReadableInterval) interval30);
        org.joda.time.Interval interval38 = interval16.toInterval();
        org.joda.time.Chronology chronology39 = org.joda.time.DateTimeUtils.getIntervalChronology((org.joda.time.ReadableInterval) interval16);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(period15);
        org.junit.Assert.assertNotNull(interval16);
        org.junit.Assert.assertNotNull(dateMidnight21);
        org.junit.Assert.assertNotNull(dateMidnight24);
        org.junit.Assert.assertNotNull(dateMidnight26);
        org.junit.Assert.assertNotNull(period32);
        org.junit.Assert.assertNotNull(interval33);
        org.junit.Assert.assertTrue("'" + boolean35 + "' != '" + false + "'", boolean35 == false);
        org.junit.Assert.assertTrue("'" + long36 + "' != '" + (-59011462664000L) + "'", long36 == (-59011462664000L));
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + false + "'", boolean37 == false);
        org.junit.Assert.assertNotNull(interval38);
        org.junit.Assert.assertNotNull(chronology39);
    }

    @Test
    public void test0615() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0615");
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
        org.joda.time.DateTime dateTime23 = dateTime0.toDateTime();
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(months7);
        org.junit.Assert.assertNotNull(period8);
        org.junit.Assert.assertNotNull(dateMidnight13);
        org.junit.Assert.assertNotNull(dateMidnight18);
        org.junit.Assert.assertTrue("'" + int19 + "' != '" + (-1) + "'", int19 == (-1));
        org.junit.Assert.assertNotNull(duration20);
        org.junit.Assert.assertNotNull(duration21);
        org.junit.Assert.assertNotNull(dateTime22);
        org.junit.Assert.assertNotNull(dateTime23);
    }

    @Test
    public void test0616() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0616");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.MonthDay monthDay4 = monthDay2.minusDays((int) '4');
        org.joda.time.MonthDay.Property property5 = monthDay2.monthOfYear();
        java.lang.String str6 = property5.getAsText();
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(monthDay4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "January" + "'", str6, "January");
    }

    @Test
    public void test0617() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0617");
        org.joda.time.DateTimeComparator dateTimeComparator0 = org.joda.time.DateTimeComparator.getDateOnlyInstance();
        org.joda.time.DateTimeComparator dateTimeComparator1 = org.joda.time.DateTimeComparator.getTimeOnlyInstance();
        java.util.Comparator<java.lang.Object> objComparator2 = dateTimeComparator0.thenComparing((java.util.Comparator<java.lang.Object>) dateTimeComparator1);
        org.joda.time.DateTimeFieldType dateTimeFieldType3 = dateTimeComparator1.getUpperLimit();
        org.joda.time.DateTimeComparator dateTimeComparator4 = org.joda.time.DateTimeComparator.getInstance(dateTimeFieldType3);
        org.joda.time.Chronology chronology7 = null;
        org.joda.time.DateMidnight dateMidnight8 = new org.joda.time.DateMidnight((long) (short) -1, chronology7);
        org.joda.time.MutableDateTime mutableDateTime9 = dateMidnight8.toMutableDateTime();
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
        org.joda.time.DateMidnight dateMidnight28 = dateMidnight8.minus((org.joda.time.ReadableDuration) duration27);
        org.joda.time.Duration duration30 = duration27.minus((long) 3600);
        // The following exception was thrown during execution in test generation
        try {
            int int31 = dateTimeComparator4.compare((java.lang.Object) 60, (java.lang.Object) duration27);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No instant converter found for type: java.lang.Integer");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeComparator0);
        org.junit.Assert.assertNotNull(dateTimeComparator1);
        org.junit.Assert.assertNotNull(objComparator2);
        org.junit.Assert.assertNotNull(dateTimeFieldType3);
        org.junit.Assert.assertNotNull(dateTimeComparator4);
        org.junit.Assert.assertNotNull(mutableDateTime9);
        org.junit.Assert.assertNotNull(months14);
        org.junit.Assert.assertNotNull(period15);
        org.junit.Assert.assertNotNull(dateMidnight20);
        org.junit.Assert.assertNotNull(dateMidnight25);
        org.junit.Assert.assertTrue("'" + int26 + "' != '" + (-1) + "'", int26 == (-1));
        org.junit.Assert.assertNotNull(duration27);
        org.junit.Assert.assertNotNull(dateMidnight28);
        org.junit.Assert.assertNotNull(duration30);
    }

    @Test
    public void test0618() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0618");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        int int10 = dateMidnight4.compareTo((org.joda.time.ReadableInstant) dateMidnight7);
        java.lang.String str11 = dateMidnight4.toString();
        int int12 = dateMidnight4.getDayOfMonth();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + (-1) + "'", int10 == (-1));
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "1961-09-01T00:00:00.000+01:00" + "'", str11, "1961-09-01T00:00:00.000+01:00");
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 1 + "'", int12 == 1);
    }

    @Test
    public void test0619() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0619");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight.Property property3 = dateMidnight2.monthOfYear();
        int int4 = property3.getLeapAmount();
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
    }

    @Test
    public void test0620() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0620");
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
        int int19 = period18.getMinutes();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(period15);
        org.junit.Assert.assertNotNull(interval16);
        org.junit.Assert.assertNotNull(period18);
        org.junit.Assert.assertTrue("'" + int19 + "' != '" + 0 + "'", int19 == 0);
    }

    @Test
    public void test0621() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0621");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalTime localTime2 = new org.joda.time.LocalTime((int) '4', (int) (short) 1);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 52 for hourOfDay must be in the range [0,23]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0622() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0622");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        int int2 = localDateTime1.getYear();
        org.joda.time.LocalDateTime localDateTime4 = localDateTime1.plusMillis(10);
        int int5 = localDateTime1.getYearOfEra();
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 2026 + "'", int2 == 2026);
        org.junit.Assert.assertNotNull(localDateTime4);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 2026 + "'", int5 == 2026);
    }

    @Test
    public void test0623() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0623");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime9 = property7.addWrapField((int) (short) -1);
        org.joda.time.MutableDateTime.Property property10 = mutableDateTime9.monthOfYear();
        org.joda.time.MutableDateTime mutableDateTime11 = property10.roundHalfFloor();
        java.lang.String str12 = property10.getAsText();
        org.joda.time.DateTimeField dateTimeField13 = property10.getField();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(mutableDateTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(mutableDateTime11);
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "January" + "'", str12, "January");
        org.junit.Assert.assertNotNull(dateTimeField13);
    }

    @Test
    public void test0624() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0624");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.basicOrdinalDateTime();
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
    }

    @Test
    public void test0625() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0625");
        org.joda.time.Years years1 = org.joda.time.Years.years((int) (byte) 10);
        org.joda.time.Years years3 = years1.dividedBy(1);
        org.joda.time.Years years5 = org.joda.time.Years.years((int) (byte) 10);
        org.joda.time.Years years7 = years5.dividedBy(1);
        boolean boolean8 = years3.isLessThan(years7);
        org.joda.time.Years years10 = years7.multipliedBy(0);
        org.joda.time.Days days11 = org.joda.time.Days.ONE;
        org.joda.time.DurationFieldType durationFieldType12 = days11.getFieldType();
        // The following exception was thrown during execution in test generation
        try {
            int int13 = years10.compareTo((org.joda.time.base.BaseSingleFieldPeriod) days11);
            org.junit.Assert.fail("Expected exception of type java.lang.ClassCastException; message: class org.joda.time.Years cannot be compared to class org.joda.time.Days");
        } catch (java.lang.ClassCastException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(years1);
        org.junit.Assert.assertNotNull(years3);
        org.junit.Assert.assertNotNull(years5);
        org.junit.Assert.assertNotNull(years7);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(years10);
        org.junit.Assert.assertNotNull(days11);
        org.junit.Assert.assertNotNull(durationFieldType12);
    }

    @Test
    public void test0626() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0626");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone11 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology12 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone11);
        java.lang.String str13 = copticChronology12.toString();
        org.joda.time.DateTimeField dateTimeField14 = copticChronology12.clockhourOfDay();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone20 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology21 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone20);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType22 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology24 = null;
        org.joda.time.DateMidnight dateMidnight25 = new org.joda.time.DateMidnight((long) (short) -1, chronology24);
        org.joda.time.DateMidnight dateMidnight27 = dateMidnight25.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property28 = dateMidnight25.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime29 = dateMidnight25.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property30 = mutableDateTime29.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime32 = property30.addWrapField((int) (short) -1);
        boolean boolean33 = leapYearPatternType22.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology34 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone20, leapYearPatternType22);
        org.joda.time.MutableDateTime mutableDateTime35 = new org.joda.time.MutableDateTime((long) (byte) 10, (org.joda.time.DateTimeZone) fixedDateTimeZone20);
        boolean boolean36 = copticChronology12.equals((java.lang.Object) mutableDateTime35);
        org.joda.time.DurationField durationField37 = copticChronology12.halfdays();
        org.joda.time.DateTimeZone dateTimeZone38 = null;
        org.joda.time.LocalDateTime localDateTime39 = new org.joda.time.LocalDateTime(dateTimeZone38);
        int int40 = localDateTime39.getYear();
        org.joda.time.LocalDateTime localDateTime42 = localDateTime39.plusMillis(10);
        org.joda.time.chrono.GJChronology gJChronology43 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj44 = null;
        boolean boolean45 = gJChronology43.equals(obj44);
        org.joda.time.DurationField durationField46 = gJChronology43.days();
        org.joda.time.chrono.GJChronology gJChronology47 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology48 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology47);
        org.joda.time.chrono.LenientChronology lenientChronology49 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology47);
        org.joda.time.DateTimeField dateTimeField50 = lenientChronology49.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField51 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology43, dateTimeField50);
        org.joda.time.DateTimeFieldType dateTimeFieldType52 = skipUndoDateTimeField51.getType();
        int int53 = skipUndoDateTimeField51.getMinimumValue();
        java.lang.String str54 = skipUndoDateTimeField51.getName();
        org.joda.time.DateTimeFieldType dateTimeFieldType55 = skipUndoDateTimeField51.getType();
        org.joda.time.LocalDateTime localDateTime57 = localDateTime39.withField(dateTimeFieldType55, 24);
        org.joda.time.field.RemainderDateTimeField remainderDateTimeField59 = new org.joda.time.field.RemainderDateTimeField(dateTimeField4, durationField37, dateTimeFieldType55, (int) (byte) 10);
        boolean boolean61 = remainderDateTimeField59.isLeap((long) ' ');
        long long63 = remainderDateTimeField59.roundCeiling((long) 86400000);
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertNotNull(copticChronology12);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "CopticChronology[2026-08-06T11:10:36.726]" + "'", str13, "CopticChronology[2026-08-06T11:10:36.726]");
        org.junit.Assert.assertNotNull(dateTimeField14);
        org.junit.Assert.assertNotNull(copticChronology21);
        org.junit.Assert.assertNotNull(leapYearPatternType22);
        org.junit.Assert.assertNotNull(dateMidnight27);
        org.junit.Assert.assertNotNull(property28);
        org.junit.Assert.assertNotNull(mutableDateTime29);
        org.junit.Assert.assertNotNull(property30);
        org.junit.Assert.assertNotNull(mutableDateTime32);
        org.junit.Assert.assertTrue("'" + boolean33 + "' != '" + false + "'", boolean33 == false);
        org.junit.Assert.assertNotNull(islamicChronology34);
        org.junit.Assert.assertTrue("'" + boolean36 + "' != '" + false + "'", boolean36 == false);
        org.junit.Assert.assertNotNull(durationField37);
        org.junit.Assert.assertTrue("'" + int40 + "' != '" + 2026 + "'", int40 == 2026);
        org.junit.Assert.assertNotNull(localDateTime42);
        org.junit.Assert.assertNotNull(gJChronology43);
        org.junit.Assert.assertTrue("'" + boolean45 + "' != '" + false + "'", boolean45 == false);
        org.junit.Assert.assertNotNull(durationField46);
        org.junit.Assert.assertNotNull(gJChronology47);
        org.junit.Assert.assertNotNull(lenientChronology48);
        org.junit.Assert.assertNotNull(lenientChronology49);
        org.junit.Assert.assertNotNull(dateTimeField50);
        org.junit.Assert.assertNotNull(dateTimeFieldType52);
        org.junit.Assert.assertTrue("'" + int53 + "' != '" + 0 + "'", int53 == 0);
        org.junit.Assert.assertEquals("'" + str54 + "' != '" + "yearOfCentury" + "'", str54, "yearOfCentury");
        org.junit.Assert.assertNotNull(dateTimeFieldType55);
        org.junit.Assert.assertNotNull(localDateTime57);
        org.junit.Assert.assertTrue("'" + boolean61 + "' != '" + false + "'", boolean61 == false);
        org.junit.Assert.assertTrue("'" + long63 + "' != '" + 342000000L + "'", long63 == 342000000L);
    }

    @Test
    public void test0627() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0627");
        org.joda.time.PeriodType periodType1 = org.joda.time.PeriodType.millis();
        org.joda.time.PeriodType periodType2 = periodType1.withWeeksRemoved();
        org.joda.time.MutablePeriod mutablePeriod3 = new org.joda.time.MutablePeriod((long) (byte) 10, periodType2);
        org.joda.time.PeriodType periodType4 = periodType2.withMonthsRemoved();
        org.joda.time.MutablePeriod mutablePeriod5 = new org.joda.time.MutablePeriod(periodType4);
        org.joda.time.ReadableInterval readableInterval6 = null;
        org.joda.time.Seconds seconds7 = org.joda.time.Seconds.secondsIn(readableInterval6);
        org.joda.time.DurationFieldType durationFieldType8 = seconds7.getFieldType();
        org.joda.time.field.PreciseDurationField preciseDurationField10 = new org.joda.time.field.PreciseDurationField(durationFieldType8, (long) 59);
        org.joda.time.ReadableInterval readableInterval11 = null;
        org.joda.time.Seconds seconds12 = org.joda.time.Seconds.secondsIn(readableInterval11);
        org.joda.time.DurationFieldType durationFieldType13 = seconds12.getFieldType();
        org.joda.time.field.PreciseDurationField preciseDurationField15 = new org.joda.time.field.PreciseDurationField(durationFieldType13, (long) 59);
        int int16 = preciseDurationField10.compareTo((org.joda.time.DurationField) preciseDurationField15);
        org.joda.time.DurationFieldType durationFieldType17 = preciseDurationField10.getType();
        // The following exception was thrown during execution in test generation
        try {
            mutablePeriod5.set(durationFieldType17, 1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Period does not support field 'seconds'");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(periodType1);
        org.junit.Assert.assertNotNull(periodType2);
        org.junit.Assert.assertNotNull(periodType4);
        org.junit.Assert.assertNotNull(seconds7);
        org.junit.Assert.assertNotNull(durationFieldType8);
        org.junit.Assert.assertNotNull(seconds12);
        org.junit.Assert.assertNotNull(durationFieldType13);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 0 + "'", int16 == 0);
        org.junit.Assert.assertNotNull(durationFieldType17);
    }

    @Test
    public void test0628() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0628");
        org.joda.time.Years years1 = org.joda.time.Years.years((int) (byte) 10);
        org.joda.time.Years years3 = years1.dividedBy(1);
        org.joda.time.DateTime dateTime4 = new org.joda.time.DateTime();
        boolean boolean5 = years1.equals((java.lang.Object) dateTime4);
        org.joda.time.DateTime dateTime7 = dateTime4.withDayOfYear(36);
        org.junit.Assert.assertNotNull(years1);
        org.junit.Assert.assertNotNull(years3);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(dateTime7);
    }

    @Test
    public void test0629() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0629");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.DateTimeFormat.longDate();
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.format.DateTimeFormatter dateTimeFormatter2 = dateTimeFormatter0.withChronology((org.joda.time.Chronology) julianChronology1);
        org.joda.time.format.DateTimePrinter dateTimePrinter3 = dateTimeFormatter2.getPrinter();
        java.lang.Appendable appendable4 = null;
        org.joda.time.DateMidnight dateMidnight6 = org.joda.time.DateMidnight.parse("2026-08-06T11:10:36.726");
        // The following exception was thrown during execution in test generation
        try {
            dateTimeFormatter2.printTo(appendable4, (org.joda.time.ReadableInstant) dateMidnight6);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(dateTimeFormatter2);
        org.junit.Assert.assertNotNull(dateTimePrinter3);
        org.junit.Assert.assertNotNull(dateMidnight6);
    }

    @Test
    public void test0630() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0630");
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
            org.joda.time.MutableInterval mutableInterval29 = new org.joda.time.MutableInterval((java.lang.Object) dateTime28);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No interval converter found for type: org.joda.time.DateTime");
        } catch (java.lang.IllegalArgumentException e) {
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
    public void test0631() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0631");
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
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 0 + "'", int12 == 0);
        org.junit.Assert.assertNotNull(gregorianChronology19);
        org.junit.Assert.assertNotNull(cachedDateTimeZone21);
        org.junit.Assert.assertNotNull(iSOChronology22);
        org.junit.Assert.assertNotNull(chronology23);
        org.junit.Assert.assertNotNull(mutableDateTime24);
    }

    @Test
    public void test0632() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0632");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.ReadableInterval readableInterval4 = null;
        org.joda.time.Seconds seconds5 = org.joda.time.Seconds.secondsIn(readableInterval4);
        org.joda.time.DurationFieldType durationFieldType6 = seconds5.getFieldType();
        int int7 = period3.get(durationFieldType6);
        org.joda.time.PeriodType periodType8 = period3.getPeriodType();
        org.joda.time.Weeks weeks9 = org.joda.time.Weeks.MAX_VALUE;
        org.joda.time.Weeks weeks11 = weeks9.dividedBy(3);
        org.joda.time.PeriodType periodType12 = weeks11.getPeriodType();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Period period13 = period3.withPeriodType(periodType12);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Period does not support field 'millis'");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(seconds5);
        org.junit.Assert.assertNotNull(durationFieldType6);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertNotNull(periodType8);
        org.junit.Assert.assertNotNull(weeks9);
        org.junit.Assert.assertNotNull(weeks11);
        org.junit.Assert.assertNotNull(periodType12);
    }

    @Test
    public void test0633() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0633");
        org.joda.time.chrono.GregorianChronology gregorianChronology0 = org.joda.time.chrono.GregorianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField1 = gregorianChronology0.minuteOfDay();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.minuteOfHour();
        org.joda.time.field.SkipDateTimeField skipDateTimeField6 = new org.joda.time.field.SkipDateTimeField((org.joda.time.Chronology) gregorianChronology0, dateTimeField4, 3);
        // The following exception was thrown during execution in test generation
        try {
            long long11 = gregorianChronology0.getDateTimeMillis(11, 1900, 51, 20);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 1900 for monthOfYear must be in the range [1,12]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gregorianChronology0);
        org.junit.Assert.assertNotNull(dateTimeField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
    }

    @Test
    public void test0634() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0634");
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
        java.lang.String str21 = monthDay19.toString("1");
        org.junit.Assert.assertNotNull(copticChronology5);
        org.junit.Assert.assertNotNull(leapYearPatternType6);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(property12);
        org.junit.Assert.assertNotNull(mutableDateTime13);
        org.junit.Assert.assertNotNull(property14);
        org.junit.Assert.assertNotNull(mutableDateTime16);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(islamicChronology18);
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "1" + "'", str21, "1");
    }

    @Test
    public void test0635() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0635");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstanceUTC();
        org.junit.Assert.assertNotNull(julianChronology0);
    }

    @Test
    public void test0636() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0636");
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
        org.joda.time.Period period24 = org.joda.time.Period.weeks((int) (short) 100);
        int int25 = period24.getDays();
        org.joda.time.Period period27 = period24.minusWeeks(100);
        mutableInterval22.setPeriodBeforeEnd((org.joda.time.ReadablePeriod) period24);
        mutableInterval22.setDurationBeforeEnd((long) 4);
        org.junit.Assert.assertNotNull(duration1);
        org.junit.Assert.assertNotNull(dateMidnight6);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(dateMidnight16);
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + (-1) + "'", int17 == (-1));
        org.junit.Assert.assertNotNull(minutes18);
        org.junit.Assert.assertNotNull(dateMidnight21);
        org.junit.Assert.assertNotNull(period24);
        org.junit.Assert.assertTrue("'" + int25 + "' != '" + 0 + "'", int25 == 0);
        org.junit.Assert.assertNotNull(period27);
    }

    @Test
    public void test0637() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0637");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.Months months4 = org.joda.time.Months.FIVE;
        org.joda.time.Period period5 = period3.minus((org.joda.time.ReadablePeriod) months4);
        org.joda.time.Period period7 = period3.plusMinutes((int) ' ');
        org.joda.time.Period period8 = period3.toPeriod();
        org.junit.Assert.assertNotNull(months4);
        org.junit.Assert.assertNotNull(period5);
        org.junit.Assert.assertNotNull(period7);
        org.junit.Assert.assertNotNull(period8);
    }

    @Test
    public void test0638() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0638");
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
        long long12 = preciseDurationField4.getUnitMillis();
        org.junit.Assert.assertNotNull(seconds1);
        org.junit.Assert.assertNotNull(durationFieldType2);
        org.junit.Assert.assertNotNull(seconds6);
        org.junit.Assert.assertNotNull(durationFieldType7);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
        org.junit.Assert.assertNotNull(durationFieldType11);
        org.junit.Assert.assertTrue("'" + long12 + "' != '" + 59L + "'", long12 == 59L);
    }

    @Test
    public void test0639() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0639");
        org.joda.time.MutablePeriod mutablePeriod1 = new org.joda.time.MutablePeriod((long) 11);
    }

    @Test
    public void test0640() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0640");
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
        org.joda.time.DateTime dateTime26 = dateTime22.minusMinutes(86400000);
        org.joda.time.DateTimeComparator dateTimeComparator27 = org.joda.time.DateTimeComparator.getDateOnlyInstance();
        org.joda.time.DateTimeComparator dateTimeComparator28 = org.joda.time.DateTimeComparator.getTimeOnlyInstance();
        java.util.Comparator<java.lang.Object> objComparator29 = dateTimeComparator27.thenComparing((java.util.Comparator<java.lang.Object>) dateTimeComparator28);
        org.joda.time.DateTimeFieldType dateTimeFieldType30 = dateTimeComparator28.getUpperLimit();
        org.joda.time.DateTime dateTime32 = dateTime22.withField(dateTimeFieldType30, 60);
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
        org.junit.Assert.assertNotNull(dateTime26);
        org.junit.Assert.assertNotNull(dateTimeComparator27);
        org.junit.Assert.assertNotNull(dateTimeComparator28);
        org.junit.Assert.assertNotNull(objComparator29);
        org.junit.Assert.assertNotNull(dateTimeFieldType30);
        org.junit.Assert.assertNotNull(dateTime32);
    }

    @Test
    public void test0641() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0641");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Weeks weeks1 = org.joda.time.Weeks.parseWeeks("+00:00:00.100");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid format: \"+00:00:00.100\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0642() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0642");
        org.joda.time.PeriodType periodType0 = org.joda.time.PeriodType.yearDay();
        org.joda.time.PeriodType periodType1 = periodType0.withSecondsRemoved();
        org.joda.time.DateTimeZone dateTimeZone2 = null;
        org.joda.time.LocalDateTime localDateTime3 = new org.joda.time.LocalDateTime(dateTimeZone2);
        org.joda.time.LocalDateTime localDateTime5 = localDateTime3.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property6 = localDateTime3.weekOfWeekyear();
        org.joda.time.ReadableInterval readableInterval7 = null;
        org.joda.time.Seconds seconds8 = org.joda.time.Seconds.secondsIn(readableInterval7);
        org.joda.time.DurationFieldType durationFieldType9 = seconds8.getFieldType();
        org.joda.time.LocalDateTime localDateTime11 = localDateTime3.withFieldAdded(durationFieldType9, 86400000);
        boolean boolean12 = periodType0.isSupported(durationFieldType9);
        org.joda.time.IllegalFieldValueException illegalFieldValueException16 = new org.joda.time.IllegalFieldValueException(durationFieldType9, (java.lang.Number) 100L, (java.lang.Number) (-1.0f), (java.lang.Number) 1);
        java.lang.String str17 = illegalFieldValueException16.getFieldName();
        org.junit.Assert.assertNotNull(periodType0);
        org.junit.Assert.assertNotNull(periodType1);
        org.junit.Assert.assertNotNull(localDateTime5);
        org.junit.Assert.assertNotNull(property6);
        org.junit.Assert.assertNotNull(seconds8);
        org.junit.Assert.assertNotNull(durationFieldType9);
        org.junit.Assert.assertNotNull(localDateTime11);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "seconds" + "'", str17, "seconds");
    }

    @Test
    public void test0643() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0643");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MutableDateTime mutableDateTime7 = new org.joda.time.MutableDateTime((int) (short) 10, 11, 2147483647, (int) (byte) 10, 100, (int) (byte) 100, 168);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 100 for minuteOfHour must be in the range [0,59]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0644() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0644");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.YearMonthDay yearMonthDay5 = dateTime0.toYearMonthDay();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone10 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.DateTime dateTime11 = yearMonthDay5.toDateTimeAtMidnight((org.joda.time.DateTimeZone) fixedDateTimeZone10);
        org.joda.time.DateTimeZone dateTimeZone12 = null;
        org.joda.time.LocalDateTime localDateTime13 = new org.joda.time.LocalDateTime(dateTimeZone12);
        org.joda.time.LocalDateTime localDateTime15 = localDateTime13.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property16 = localDateTime13.weekOfWeekyear();
        org.joda.time.ReadableInterval readableInterval17 = null;
        org.joda.time.Seconds seconds18 = org.joda.time.Seconds.secondsIn(readableInterval17);
        org.joda.time.DurationFieldType durationFieldType19 = seconds18.getFieldType();
        org.joda.time.LocalDateTime localDateTime21 = localDateTime13.withFieldAdded(durationFieldType19, 86400000);
        org.joda.time.LocalDateTime localDateTime23 = localDateTime21.plusMillis((int) (byte) 10);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Weeks weeks24 = org.joda.time.Weeks.weeksBetween((org.joda.time.ReadablePartial) yearMonthDay5, (org.joda.time.ReadablePartial) localDateTime21);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: ReadablePartial objects must have the same set of fields");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(yearMonthDay5);
        org.junit.Assert.assertNotNull(dateTime11);
        org.junit.Assert.assertNotNull(localDateTime15);
        org.junit.Assert.assertNotNull(property16);
        org.junit.Assert.assertNotNull(seconds18);
        org.junit.Assert.assertNotNull(durationFieldType19);
        org.junit.Assert.assertNotNull(localDateTime21);
        org.junit.Assert.assertNotNull(localDateTime23);
    }

    @Test
    public void test0645() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0645");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        int int8 = skipUndoDateTimeField6.get((long) 10);
        org.joda.time.DurationField durationField9 = skipUndoDateTimeField6.getRangeDurationField();
        org.joda.time.field.OffsetDateTimeField offsetDateTimeField11 = new org.joda.time.field.OffsetDateTimeField((org.joda.time.DateTimeField) skipUndoDateTimeField6, 8);
        long long13 = offsetDateTimeField11.roundHalfEven(0L);
        int int15 = offsetDateTimeField11.getLeapAmount((long) 2026);
        java.lang.String str16 = offsetDateTimeField11.getName();
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 2 + "'", int8 == 2);
        org.junit.Assert.assertNotNull(durationField9);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + (-262800000L) + "'", long13 == (-262800000L));
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 0 + "'", int15 == 0);
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "weekOfWeekyear" + "'", str16, "weekOfWeekyear");
    }

    @Test
    public void test0646() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0646");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = property3.addToCopy(1);
        int int6 = localDate5.getEra();
        org.joda.time.Chronology chronology8 = null;
        org.joda.time.DateMidnight dateMidnight9 = new org.joda.time.DateMidnight((long) (short) -1, chronology8);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight14 = dateMidnight9.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar15 = dateMidnight14.toGregorianCalendar();
        org.joda.time.LocalTime localTime16 = org.joda.time.LocalTime.fromCalendarFields((java.util.Calendar) gregorianCalendar15);
        org.joda.time.LocalTime.Property property17 = localTime16.minuteOfHour();
        org.joda.time.LocalTime.Property property18 = localTime16.millisOfSecond();
        org.joda.time.LocalTime localTime20 = localTime16.withSecondOfMinute(9);
        org.joda.time.LocalTime.Property property21 = localTime16.hourOfDay();
        org.joda.time.Days days23 = org.joda.time.Days.days(40256602);
        org.joda.time.LocalTime localTime25 = localTime16.withPeriodAdded((org.joda.time.ReadablePeriod) days23, 0);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDateTime localDateTime26 = localDate5.toLocalDateTime(localTime25);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The chronology of the time does not match");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 1 + "'", int6 == 1);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(dateMidnight14);
        org.junit.Assert.assertNotNull(gregorianCalendar15);
        org.junit.Assert.assertNotNull(localTime16);
        org.junit.Assert.assertNotNull(property17);
        org.junit.Assert.assertNotNull(property18);
        org.junit.Assert.assertNotNull(localTime20);
        org.junit.Assert.assertNotNull(property21);
        org.junit.Assert.assertNotNull(days23);
        org.junit.Assert.assertNotNull(localTime25);
    }

    @Test
    public void test0647() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0647");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        org.joda.time.ReadableInterval readableInterval5 = null;
        org.joda.time.Seconds seconds6 = org.joda.time.Seconds.secondsIn(readableInterval5);
        org.joda.time.DurationFieldType durationFieldType7 = seconds6.getFieldType();
        org.joda.time.LocalDateTime localDateTime9 = localDateTime1.withFieldAdded(durationFieldType7, 86400000);
        boolean boolean11 = localDateTime9.equals((java.lang.Object) "2026-08-06T11:10:39.499");
        java.lang.String str12 = localDateTime9.toString();
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(seconds6);
        org.junit.Assert.assertNotNull(durationFieldType7);
        org.junit.Assert.assertNotNull(localDateTime9);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
// flaky "2) test0647(RegressionTest1)":         org.junit.Assert.assertEquals("'" + str12 + "' != '" + "2029-05-02T11:10:56.115" + "'", str12, "2029-05-02T11:10:56.115");
    }

    @Test
    public void test0648() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0648");
        org.joda.time.format.PeriodPrinter periodPrinter0 = null;
        org.joda.time.format.PeriodPrinter periodPrinter1 = null;
        org.joda.time.format.PeriodFormatter periodFormatter2 = org.joda.time.format.PeriodFormat.wordBased();
        org.joda.time.format.PeriodParser periodParser3 = periodFormatter2.getParser();
        org.joda.time.format.PeriodFormatter periodFormatter4 = new org.joda.time.format.PeriodFormatter(periodPrinter1, periodParser3);
        org.joda.time.format.PeriodFormatter periodFormatter5 = new org.joda.time.format.PeriodFormatter(periodPrinter0, periodParser3);
        org.joda.time.PeriodType periodType6 = periodFormatter5.getParseType();
        org.joda.time.PeriodType periodType7 = org.joda.time.PeriodType.yearDay();
        org.joda.time.PeriodType periodType8 = periodType7.withSecondsRemoved();
        org.joda.time.format.PeriodFormatter periodFormatter9 = periodFormatter5.withParseType(periodType7);
        org.junit.Assert.assertNotNull(periodFormatter2);
        org.junit.Assert.assertNotNull(periodParser3);
        org.junit.Assert.assertNull(periodType6);
        org.junit.Assert.assertNotNull(periodType7);
        org.junit.Assert.assertNotNull(periodType8);
        org.junit.Assert.assertNotNull(periodFormatter9);
    }

    @Test
    public void test0649() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0649");
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder0 = new org.joda.time.format.PeriodFormatterBuilder();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder3 = periodFormatterBuilder0.appendPrefix("PeriodType[Millis]", "");
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder5 = periodFormatterBuilder3.minimumPrintedDigits((int) (short) -1);
        org.joda.time.format.PeriodPrinter periodPrinter6 = periodFormatterBuilder3.toPrinter();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder7 = periodFormatterBuilder3.appendYears();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder9 = periodFormatterBuilder7.rejectSignedValues(false);
        org.junit.Assert.assertNotNull(periodFormatterBuilder3);
        org.junit.Assert.assertNotNull(periodFormatterBuilder5);
        org.junit.Assert.assertNotNull(periodPrinter6);
        org.junit.Assert.assertNotNull(periodFormatterBuilder7);
        org.junit.Assert.assertNotNull(periodFormatterBuilder9);
    }

    @Test
    public void test0650() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0650");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField1 = julianChronology0.clockhourOfDay();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj3 = null;
        boolean boolean4 = gJChronology2.equals(obj3);
        org.joda.time.DurationField durationField5 = gJChronology2.days();
        org.joda.time.chrono.GJChronology gJChronology6 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology7 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology6);
        org.joda.time.chrono.LenientChronology lenientChronology8 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology6);
        org.joda.time.DateTimeField dateTimeField9 = lenientChronology8.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField10 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology2, dateTimeField9);
        org.joda.time.DateTimeFieldType dateTimeFieldType11 = skipUndoDateTimeField10.getType();
        org.joda.time.field.DividedDateTimeField dividedDateTimeField13 = new org.joda.time.field.DividedDateTimeField(dateTimeField1, dateTimeFieldType11, (int) 'a');
        org.joda.time.DurationField durationField14 = dividedDateTimeField13.getLeapDurationField();
        java.util.Locale locale15 = null;
        int int16 = dividedDateTimeField13.getMaximumTextLength(locale15);
        int int17 = dividedDateTimeField13.getMinimumValue();
        java.util.Locale locale18 = null;
        int int19 = dividedDateTimeField13.getMaximumShortTextLength(locale18);
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(dateTimeField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(durationField5);
        org.junit.Assert.assertNotNull(gJChronology6);
        org.junit.Assert.assertNotNull(lenientChronology7);
        org.junit.Assert.assertNotNull(lenientChronology8);
        org.junit.Assert.assertNotNull(dateTimeField9);
        org.junit.Assert.assertNotNull(dateTimeFieldType11);
        org.junit.Assert.assertNull(durationField14);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 1 + "'", int16 == 1);
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + 0 + "'", int17 == 0);
        org.junit.Assert.assertTrue("'" + int19 + "' != '" + 1 + "'", int19 == 1);
    }

    @Test
    public void test0651() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0651");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
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
        org.joda.time.MonthDay monthDay20 = monthDay2.minus((org.joda.time.ReadablePeriod) minutes19);
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(dateMidnight12);
        org.junit.Assert.assertNotNull(dateMidnight17);
        org.junit.Assert.assertTrue("'" + int18 + "' != '" + (-1) + "'", int18 == (-1));
        org.junit.Assert.assertNotNull(minutes19);
        org.junit.Assert.assertNotNull(monthDay20);
    }

    @Test
    public void test0652() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0652");
        org.joda.time.Seconds seconds0 = org.joda.time.Seconds.ONE;
        org.joda.time.Seconds seconds2 = seconds0.minus((int) '4');
        org.junit.Assert.assertNotNull(seconds0);
        org.junit.Assert.assertNotNull(seconds2);
    }

    @Test
    public void test0653() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0653");
        org.joda.time.LocalTime localTime1 = new org.joda.time.LocalTime((long) (-1));
        org.joda.time.Chronology chronology2 = localTime1.getChronology();
        int int3 = localTime1.getSecondOfMinute();
        org.joda.time.LocalTime.Property property4 = localTime1.secondOfMinute();
        int int5 = localTime1.getMillisOfSecond();
        org.junit.Assert.assertNotNull(chronology2);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 59 + "'", int3 == 59);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 999 + "'", int5 == 999);
    }

    @Test
    public void test0654() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0654");
        java.util.TimeZone timeZone0 = null;
        org.joda.time.DateTimeZone dateTimeZone1 = org.joda.time.DateTimeZone.forTimeZone(timeZone0);
        org.joda.time.chrono.EthiopicChronology ethiopicChronology3 = org.joda.time.chrono.EthiopicChronology.getInstance(dateTimeZone1, 2);
        // The following exception was thrown during execution in test generation
        try {
            long long11 = ethiopicChronology3.getDateTimeMillis(168, 69, 168, 168, 2, (int) 'a', (int) (byte) 100);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 168 for hourOfDay must be in the range [0,23]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeZone1);
        org.junit.Assert.assertNotNull(ethiopicChronology3);
    }

    @Test
    public void test0655() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0655");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.DurationField durationField6 = property5.getLeapDurationField();
        org.joda.time.DateMidnight dateMidnight7 = property5.roundHalfFloorCopy();
        org.joda.time.ReadablePeriod readablePeriod8 = null;
        org.joda.time.DateMidnight dateMidnight10 = dateMidnight7.withPeriodAdded(readablePeriod8, (int) (short) 0);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNull(durationField6);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(dateMidnight10);
    }

    @Test
    public void test0656() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0656");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.MonthDay monthDay4 = monthDay2.minusDays((int) '4');
        org.joda.time.MonthDay monthDay6 = monthDay4.minusDays((int) (short) 100);
        org.joda.time.Days days7 = org.joda.time.Days.ONE;
        org.joda.time.DurationFieldType durationFieldType8 = days7.getFieldType();
        org.joda.time.MonthDay monthDay10 = monthDay4.withFieldAdded(durationFieldType8, 1);
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(monthDay4);
        org.junit.Assert.assertNotNull(monthDay6);
        org.junit.Assert.assertNotNull(days7);
        org.junit.Assert.assertNotNull(durationFieldType8);
        org.junit.Assert.assertNotNull(monthDay10);
    }

    @Test
    public void test0657() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0657");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime3.yearOfCentury();
        org.joda.time.LocalDateTime localDateTime5 = property4.withMinimumValue();
        org.joda.time.LocalDateTime localDateTime7 = localDateTime5.withHourOfDay(13);
        org.joda.time.LocalDateTime localDateTime9 = localDateTime7.withDayOfWeek(1);
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray10 = localDateTime9.getFieldTypes();
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(localDateTime5);
        org.junit.Assert.assertNotNull(localDateTime7);
        org.junit.Assert.assertNotNull(localDateTime9);
        org.junit.Assert.assertNotNull(dateTimeFieldTypeArray10);
    }

    @Test
    public void test0658() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0658");
        org.joda.time.LocalTime localTime1 = new org.joda.time.LocalTime((long) (-1));
        org.joda.time.Chronology chronology2 = localTime1.getChronology();
        org.joda.time.ReadableInterval readableInterval3 = null;
        org.joda.time.Seconds seconds4 = org.joda.time.Seconds.secondsIn(readableInterval3);
        org.joda.time.DurationFieldType durationFieldType5 = seconds4.getFieldType();
        org.joda.time.Seconds seconds7 = seconds4.dividedBy((int) ' ');
        org.joda.time.LocalTime localTime8 = localTime1.minus((org.joda.time.ReadablePeriod) seconds7);
        org.joda.time.DateTime dateTime9 = localTime8.toDateTimeToday();
        org.junit.Assert.assertNotNull(chronology2);
        org.junit.Assert.assertNotNull(seconds4);
        org.junit.Assert.assertNotNull(durationFieldType5);
        org.junit.Assert.assertNotNull(seconds7);
        org.junit.Assert.assertNotNull(localTime8);
        org.junit.Assert.assertNotNull(dateTime9);
    }

    @Test
    public void test0659() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0659");
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
        org.joda.time.Partial partial11 = new org.joda.time.Partial(dateTimeFieldType9, 9);
        org.joda.time.Instant instant12 = org.joda.time.Instant.now();
        org.joda.time.Instant instant14 = instant12.withMillis((long) 10);
        boolean boolean15 = partial11.isMatch((org.joda.time.ReadableInstant) instant12);
        org.joda.time.MutableDateTime mutableDateTime16 = instant12.toMutableDateTime();
        org.junit.Assert.assertNotNull(gJChronology0);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(durationField3);
        org.junit.Assert.assertNotNull(gJChronology4);
        org.junit.Assert.assertNotNull(lenientChronology5);
        org.junit.Assert.assertNotNull(lenientChronology6);
        org.junit.Assert.assertNotNull(dateTimeField7);
        org.junit.Assert.assertNotNull(dateTimeFieldType9);
        org.junit.Assert.assertNotNull(instant12);
        org.junit.Assert.assertNotNull(instant14);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNotNull(mutableDateTime16);
    }

    @Test
    public void test0660() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0660");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = property3.addToCopy(1);
        org.joda.time.LocalDate localDate7 = localDate5.minusDays((int) (short) 0);
        java.lang.String str9 = localDate5.toString("[]");
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(localDate7);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "[]" + "'", str9, "[]");
    }

    @Test
    public void test0661() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0661");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.PeriodType periodType5 = null;
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.Period period7 = new org.joda.time.Period((long) (short) 10, periodType5, chronology6);
        org.joda.time.ReadableInterval readableInterval8 = null;
        org.joda.time.Seconds seconds9 = org.joda.time.Seconds.secondsIn(readableInterval8);
        org.joda.time.DurationFieldType durationFieldType10 = seconds9.getFieldType();
        int int11 = period7.get(durationFieldType10);
        org.joda.time.Period period13 = period7.minusHours(86400000);
        org.joda.time.Period period14 = period13.negated();
        org.joda.time.Period period15 = period3.minus((org.joda.time.ReadablePeriod) period13);
        java.lang.String str16 = period15.toString();
        org.junit.Assert.assertNotNull(seconds9);
        org.junit.Assert.assertNotNull(durationFieldType10);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertNotNull(period13);
        org.junit.Assert.assertNotNull(period14);
        org.junit.Assert.assertNotNull(period15);
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "PT86400000H" + "'", str16, "PT86400000H");
    }

    @Test
    public void test0662() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0662");
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
        int int24 = minutes23.size();
        org.junit.Assert.assertNotNull(duration1);
        org.junit.Assert.assertNotNull(dateMidnight6);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(dateMidnight16);
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + (-1) + "'", int17 == (-1));
        org.junit.Assert.assertNotNull(minutes18);
        org.junit.Assert.assertNotNull(dateMidnight21);
        org.junit.Assert.assertNotNull(minutes23);
        org.junit.Assert.assertTrue("'" + int24 + "' != '" + 1 + "'", int24 == 1);
    }

    @Test
    public void test0663() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0663");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.MonthDay.Property property3 = monthDay2.dayOfMonth();
        int int4 = monthDay2.getDayOfMonth();
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 1 + "'", int4 == 1);
    }

    @Test
    public void test0664() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0664");
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
        org.joda.time.Period period24 = org.joda.time.Period.weeks((int) (short) 100);
        int int25 = period24.getDays();
        org.joda.time.Period period27 = period24.minusWeeks(100);
        boolean boolean28 = iSOChronology9.equals((java.lang.Object) 100);
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
        org.junit.Assert.assertNotNull(period24);
        org.junit.Assert.assertTrue("'" + int25 + "' != '" + 0 + "'", int25 == 0);
        org.junit.Assert.assertNotNull(period27);
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + false + "'", boolean28 == false);
    }

    @Test
    public void test0665() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0665");
        org.joda.time.convert.ConverterManager converterManager0 = org.joda.time.convert.ConverterManager.getInstance();
        org.joda.time.convert.PartialConverter partialConverter1 = null;
        org.joda.time.convert.PartialConverter partialConverter2 = converterManager0.addPartialConverter(partialConverter1);
        org.joda.time.convert.PeriodConverter periodConverter3 = null;
        org.joda.time.convert.PeriodConverter periodConverter4 = converterManager0.removePeriodConverter(periodConverter3);
        org.joda.time.Chronology chronology5 = null;
        org.joda.time.chrono.GJChronology gJChronology6 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj7 = null;
        boolean boolean8 = gJChronology6.equals(obj7);
        org.joda.time.DurationField durationField9 = gJChronology6.days();
        org.joda.time.chrono.GJChronology gJChronology10 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology11 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology10);
        org.joda.time.chrono.LenientChronology lenientChronology12 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology10);
        org.joda.time.DateTimeField dateTimeField13 = lenientChronology12.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField14 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology6, dateTimeField13);
        long long16 = skipUndoDateTimeField14.remainder((long) 59);
        org.joda.time.field.SkipDateTimeField skipDateTimeField18 = new org.joda.time.field.SkipDateTimeField(chronology5, (org.joda.time.DateTimeField) skipUndoDateTimeField14, 40260602);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.convert.InstantConverter instantConverter19 = converterManager0.getInstantConverter((java.lang.Object) 40260602);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No instant converter found for type: java.lang.Integer");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(converterManager0);
        org.junit.Assert.assertNull(partialConverter2);
        org.junit.Assert.assertNull(periodConverter4);
        org.junit.Assert.assertNotNull(gJChronology6);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(durationField9);
        org.junit.Assert.assertNotNull(gJChronology10);
        org.junit.Assert.assertNotNull(lenientChronology11);
        org.junit.Assert.assertNotNull(lenientChronology12);
        org.junit.Assert.assertNotNull(dateTimeField13);
        org.junit.Assert.assertTrue("'" + long16 + "' != '" + 3600059L + "'", long16 == 3600059L);
    }

    @Test
    public void test0666() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0666");
        org.joda.time.Chronology chronology8 = null;
        org.joda.time.DateMidnight dateMidnight9 = new org.joda.time.DateMidnight((long) (short) -1, chronology8);
        org.joda.time.chrono.JulianChronology julianChronology10 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField11 = julianChronology10.weeks();
        org.joda.time.Chronology chronology12 = julianChronology10.withUTC();
        org.joda.time.DateTime dateTime13 = dateMidnight9.toDateTime((org.joda.time.Chronology) julianChronology10);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MutableDateTime mutableDateTime14 = new org.joda.time.MutableDateTime(168, (int) (byte) 0, 5, 100, 0, (int) (short) -1, 0, (org.joda.time.Chronology) julianChronology10);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 100 for hourOfDay must be in the range [0,23]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(julianChronology10);
        org.junit.Assert.assertNotNull(durationField11);
        org.junit.Assert.assertNotNull(chronology12);
        org.junit.Assert.assertNotNull(dateTime13);
    }

    @Test
    public void test0667() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0667");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight.Property property3 = dateMidnight2.monthOfYear();
        long long4 = property3.remainder();
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertTrue("'" + long4 + "' != '" + 0L + "'", long4 == 0L);
    }

    @Test
    public void test0668() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0668");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.MonthDay monthDay4 = monthDay2.minusDays((int) '4');
        org.joda.time.MonthDay.Property property5 = monthDay2.monthOfYear();
        java.lang.String str6 = property5.getName();
        org.joda.time.DateTimeField dateTimeField7 = property5.getField();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MonthDay monthDay9 = property5.setCopy("PT86400000H");
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value \"PT86400000H\" for monthOfYear is not supported");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(monthDay4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "monthOfYear" + "'", str6, "monthOfYear");
        org.junit.Assert.assertNotNull(dateTimeField7);
    }

    @Test
    public void test0669() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0669");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray1 = yearMonth0.getFieldTypes();
        org.joda.time.YearMonth yearMonth3 = yearMonth0.withMonthOfYear(1);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDate localDate5 = yearMonth3.toLocalDate((int) (short) 100);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 100 for dayOfMonth must be in the range [1,31]: year: 2026 month: 1");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeFieldTypeArray1);
        org.junit.Assert.assertNotNull(yearMonth3);
    }

    @Test
    public void test0670() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0670");
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
        org.joda.time.chrono.GJChronology gJChronology19 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay20 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology19);
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray21 = monthDay20.getFieldTypes();
        boolean boolean22 = period5.equals((java.lang.Object) dateTimeFieldTypeArray21);
        int[] intArray27 = new int[] { 40256602, 29, 29, 10 };
        org.joda.time.chrono.GJChronology gJChronology28 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj29 = null;
        boolean boolean30 = gJChronology28.equals(obj29);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Partial partial31 = new org.joda.time.Partial(dateTimeFieldTypeArray21, intArray27, (org.joda.time.Chronology) gJChronology28);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Values array must be the same length as the types array");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(months4);
        org.junit.Assert.assertNotNull(period5);
        org.junit.Assert.assertNotNull(dateMidnight10);
        org.junit.Assert.assertNotNull(dateMidnight15);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + (-1) + "'", int16 == (-1));
        org.junit.Assert.assertNotNull(duration17);
        org.junit.Assert.assertNotNull(gJChronology19);
        org.junit.Assert.assertNotNull(dateTimeFieldTypeArray21);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + false + "'", boolean22 == false);
        org.junit.Assert.assertNotNull(intArray27);
        org.junit.Assert.assertArrayEquals(intArray27, new int[] { 40256602, 29, 29, 10 });
        org.junit.Assert.assertNotNull(gJChronology28);
        org.junit.Assert.assertTrue("'" + boolean30 + "' != '" + false + "'", boolean30 == false);
    }

    @Test
    public void test0671() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0671");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone4 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.LocalTime localTime5 = org.joda.time.LocalTime.now((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        java.lang.Class<?> wildcardClass6 = fixedDateTimeZone4.getClass();
        org.junit.Assert.assertNotNull(localTime5);
        org.junit.Assert.assertNotNull(wildcardClass6);
    }

    @Test
    public void test0672() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0672");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone4 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology5 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        org.joda.time.chrono.CopticChronology copticChronology7 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4, 7);
        org.junit.Assert.assertNotNull(copticChronology5);
        org.junit.Assert.assertNotNull(copticChronology7);
    }

    @Test
    public void test0673() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0673");
        org.joda.time.Days days0 = org.joda.time.Days.ONE;
        org.joda.time.DurationFieldType durationFieldType1 = days0.getFieldType();
        java.lang.String str2 = days0.toString();
        org.joda.time.Days days3 = org.joda.time.Days.THREE;
        org.joda.time.Days days4 = days0.minus(days3);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Days days6 = days4.dividedBy(0);
            org.junit.Assert.fail("Expected exception of type java.lang.ArithmeticException; message: / by zero");
        } catch (java.lang.ArithmeticException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(days0);
        org.junit.Assert.assertNotNull(durationFieldType1);
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "P1D" + "'", str2, "P1D");
        org.junit.Assert.assertNotNull(days3);
        org.junit.Assert.assertNotNull(days4);
    }

    @Test
    public void test0674() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0674");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.DateMidnight dateMidnight7 = property5.setCopy((int) (byte) 100);
        org.joda.time.LocalDateTime localDateTime8 = new org.joda.time.LocalDateTime((java.lang.Object) dateMidnight7);
        org.joda.time.MutableDateTime mutableDateTime9 = dateMidnight7.toMutableDateTimeISO();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(mutableDateTime9);
    }

    @Test
    public void test0675() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0675");
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
        org.joda.time.DateTime dateTime29 = dateTime0.withDurationAdded((org.joda.time.ReadableDuration) duration6, 32);
        java.math.RoundingMode roundingMode31 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Duration duration32 = duration6.dividedBy(0L, roundingMode31);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
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
        org.junit.Assert.assertNotNull(dateTime29);
    }

    @Test
    public void test0676() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0676");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        int int8 = skipUndoDateTimeField6.get((long) 10);
        int int10 = skipUndoDateTimeField6.getMaximumValue((long) 53);
        boolean boolean12 = skipUndoDateTimeField6.isLeap((long) 60);
        org.joda.time.Chronology chronology14 = null;
        org.joda.time.DateMidnight dateMidnight15 = new org.joda.time.DateMidnight((long) (short) -1, chronology14);
        org.joda.time.DateMidnight dateMidnight17 = dateMidnight15.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property18 = dateMidnight15.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime19 = dateMidnight15.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property20 = mutableDateTime19.centuryOfEra();
        mutableDateTime19.setSecondOfMinute(24);
        org.joda.time.DateTime dateTime23 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone24 = null;
        org.joda.time.DateTime dateTime25 = dateTime23.withZoneRetainFields(dateTimeZone24);
        boolean boolean26 = mutableDateTime19.isAfter((org.joda.time.ReadableInstant) dateTime23);
        org.joda.time.DateTime dateTime28 = dateTime23.minusMillis(3);
        org.joda.time.DateTime dateTime30 = dateTime28.minusMillis(4);
        org.joda.time.LocalDate localDate31 = dateTime30.toLocalDate();
        java.util.Locale locale33 = null;
        java.lang.String str34 = skipUndoDateTimeField6.getAsText((org.joda.time.ReadablePartial) localDate31, (int) (byte) -1, locale33);
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 2 + "'", int8 == 2);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 53 + "'", int10 == 53);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertNotNull(dateMidnight17);
        org.junit.Assert.assertNotNull(property18);
        org.junit.Assert.assertNotNull(mutableDateTime19);
        org.junit.Assert.assertNotNull(property20);
        org.junit.Assert.assertNotNull(dateTime25);
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + false + "'", boolean26 == false);
        org.junit.Assert.assertNotNull(dateTime28);
        org.junit.Assert.assertNotNull(dateTime30);
        org.junit.Assert.assertNotNull(localDate31);
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "-1" + "'", str34, "-1");
    }

    @Test
    public void test0677() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0677");
        org.joda.time.ReadableInterval readableInterval0 = null;
        org.joda.time.Seconds seconds1 = org.joda.time.Seconds.secondsIn(readableInterval0);
        org.joda.time.DurationFieldType durationFieldType2 = seconds1.getFieldType();
        org.joda.time.field.PreciseDurationField preciseDurationField4 = new org.joda.time.field.PreciseDurationField(durationFieldType2, (long) 59);
        org.joda.time.ReadableInterval readableInterval5 = null;
        org.joda.time.Seconds seconds6 = org.joda.time.Seconds.secondsIn(readableInterval5);
        org.joda.time.DurationFieldType durationFieldType7 = seconds6.getFieldType();
        org.joda.time.field.PreciseDurationField preciseDurationField9 = new org.joda.time.field.PreciseDurationField(durationFieldType7, (long) 59);
        int int10 = preciseDurationField4.compareTo((org.joda.time.DurationField) preciseDurationField9);
        int int13 = preciseDurationField9.getValue((long) 0, (long) (short) 0);
        long long16 = preciseDurationField9.add((-1L), (long) 40237996);
        long long19 = preciseDurationField9.getDifferenceAsLong((long) (short) 100, (long) (byte) 0);
        org.junit.Assert.assertNotNull(seconds1);
        org.junit.Assert.assertNotNull(durationFieldType2);
        org.junit.Assert.assertNotNull(seconds6);
        org.junit.Assert.assertNotNull(durationFieldType7);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 0 + "'", int13 == 0);
        org.junit.Assert.assertTrue("'" + long16 + "' != '" + 2374041763L + "'", long16 == 2374041763L);
        org.junit.Assert.assertTrue("'" + long19 + "' != '" + 1L + "'", long19 == 1L);
    }

    @Test
    public void test0678() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0678");
        org.joda.time.YearMonth yearMonth1 = new org.joda.time.YearMonth(1786007462709L);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDate localDate3 = yearMonth1.toLocalDate((int) '#');
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 35 for dayOfMonth must be in the range [1,31]: year: 2026 month: 8");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0679() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0679");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.DateTimeFormat.longDate();
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.format.DateTimeFormatter dateTimeFormatter2 = dateTimeFormatter0.withChronology((org.joda.time.Chronology) julianChronology1);
        org.joda.time.format.DateTimePrinter dateTimePrinter3 = dateTimeFormatter2.getPrinter();
        org.joda.time.Chronology chronology5 = null;
        org.joda.time.DateMidnight dateMidnight6 = new org.joda.time.DateMidnight((long) (short) -1, chronology5);
        org.joda.time.DateMidnight dateMidnight8 = dateMidnight6.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property9 = dateMidnight6.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime10 = dateMidnight6.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property11 = mutableDateTime10.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime12 = property11.roundFloor();
        org.joda.time.Chronology chronology14 = null;
        org.joda.time.DateMidnight dateMidnight15 = new org.joda.time.DateMidnight((long) (short) -1, chronology14);
        org.joda.time.DateMidnight dateMidnight17 = dateMidnight15.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property18 = dateMidnight15.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime19 = dateMidnight15.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property20 = mutableDateTime19.centuryOfEra();
        mutableDateTime19.setSecondOfMinute(24);
        org.joda.time.DateTime dateTime23 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone24 = null;
        org.joda.time.DateTime dateTime25 = dateTime23.withZoneRetainFields(dateTimeZone24);
        boolean boolean26 = mutableDateTime19.isAfter((org.joda.time.ReadableInstant) dateTime23);
        org.joda.time.DateTime dateTime28 = dateTime23.minusMillis(3);
        org.joda.time.DateTime dateTime30 = dateTime28.minusMillis(4);
        org.joda.time.LocalDate localDate31 = dateTime30.toLocalDate();
        org.joda.time.DateTime dateTime32 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone33 = null;
        org.joda.time.DateTime dateTime34 = dateTime32.withZoneRetainFields(dateTimeZone33);
        org.joda.time.Minutes minutes35 = org.joda.time.Minutes.ONE;
        org.joda.time.Minutes minutes37 = minutes35.minus((-1));
        org.joda.time.DateTime dateTime38 = dateTime32.plus((org.joda.time.ReadablePeriod) minutes37);
        boolean boolean39 = dateTime30.isAfter((org.joda.time.ReadableInstant) dateTime38);
        mutableDateTime12.setDate((org.joda.time.ReadableInstant) dateTime30);
        int int43 = dateTimeFormatter2.parseInto((org.joda.time.ReadWritableInstant) mutableDateTime12, "+00:00:00.100", (int) '4');
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(dateTimeFormatter2);
        org.junit.Assert.assertNotNull(dateTimePrinter3);
        org.junit.Assert.assertNotNull(dateMidnight8);
        org.junit.Assert.assertNotNull(property9);
        org.junit.Assert.assertNotNull(mutableDateTime10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(mutableDateTime12);
        org.junit.Assert.assertNotNull(dateMidnight17);
        org.junit.Assert.assertNotNull(property18);
        org.junit.Assert.assertNotNull(mutableDateTime19);
        org.junit.Assert.assertNotNull(property20);
        org.junit.Assert.assertNotNull(dateTime25);
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + false + "'", boolean26 == false);
        org.junit.Assert.assertNotNull(dateTime28);
        org.junit.Assert.assertNotNull(dateTime30);
        org.junit.Assert.assertNotNull(localDate31);
        org.junit.Assert.assertNotNull(dateTime34);
        org.junit.Assert.assertNotNull(minutes35);
        org.junit.Assert.assertNotNull(minutes37);
        org.junit.Assert.assertNotNull(dateTime38);
        org.junit.Assert.assertTrue("'" + boolean39 + "' != '" + false + "'", boolean39 == false);
        org.junit.Assert.assertTrue("'" + int43 + "' != '" + (-53) + "'", int43 == (-53));
    }

    @Test
    public void test0680() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0680");
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
        org.joda.time.DurationField durationField19 = islamicChronology18.millis();
        int int20 = islamicChronology18.getMinimumDaysInFirstWeek();
        // The following exception was thrown during execution in test generation
        try {
            long long28 = islamicChronology18.getDateTimeMillis(7, (int) (short) 100, 40237996, (int) (short) 100, (int) (short) 100, (int) 'a', 0);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 100 for hourOfDay must be in the range [0,23]");
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
        org.junit.Assert.assertNotNull(durationField19);
        org.junit.Assert.assertTrue("'" + int20 + "' != '" + 4 + "'", int20 == 4);
    }

    @Test
    public void test0681() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0681");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = property3.addToCopy(1);
        org.joda.time.LocalDate localDate7 = localDate5.minusDays((int) (short) 0);
        org.joda.time.DateTime dateTime8 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone9 = null;
        org.joda.time.DateTime dateTime10 = dateTime8.withZoneRetainFields(dateTimeZone9);
        org.joda.time.DateTime dateTime12 = dateTime8.withYear((int) (byte) 0);
        org.joda.time.YearMonthDay yearMonthDay13 = dateTime12.toYearMonthDay();
        boolean boolean14 = localDate5.equals((java.lang.Object) dateTime12);
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(localDate7);
        org.junit.Assert.assertNotNull(dateTime10);
        org.junit.Assert.assertNotNull(dateTime12);
        org.junit.Assert.assertNotNull(yearMonthDay13);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
    }

    @Test
    public void test0682() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0682");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar8 = dateMidnight7.toGregorianCalendar();
        org.joda.time.LocalTime localTime9 = org.joda.time.LocalTime.fromCalendarFields((java.util.Calendar) gregorianCalendar8);
        org.joda.time.LocalTime.Property property10 = localTime9.minuteOfHour();
        org.joda.time.LocalTime localTime11 = property10.withMaximumValue();
        org.joda.time.LocalTime localTime13 = property10.addNoWrapToCopy(4);
        int int14 = localTime13.getSecondOfMinute();
        org.joda.time.LocalTime localTime16 = localTime13.plusMinutes(2147483647);
        org.joda.time.LocalTime.Property property17 = localTime13.minuteOfHour();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(gregorianCalendar8);
        org.junit.Assert.assertNotNull(localTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(localTime11);
        org.junit.Assert.assertNotNull(localTime13);
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 0 + "'", int14 == 0);
        org.junit.Assert.assertNotNull(localTime16);
        org.junit.Assert.assertNotNull(property17);
    }

    @Test
    public void test0683() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0683");
        org.joda.time.Years years0 = org.joda.time.Years.ZERO;
        org.junit.Assert.assertNotNull(years0);
    }

    @Test
    public void test0684() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0684");
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
        org.joda.time.Duration duration22 = duration21.negated();
        mutableDateTime3.add((org.joda.time.ReadableDuration) duration22, 0);
        org.junit.Assert.assertNotNull(mutableDateTime3);
        org.junit.Assert.assertNotNull(months8);
        org.junit.Assert.assertNotNull(period9);
        org.junit.Assert.assertNotNull(dateMidnight14);
        org.junit.Assert.assertNotNull(dateMidnight19);
        org.junit.Assert.assertTrue("'" + int20 + "' != '" + (-1) + "'", int20 == (-1));
        org.junit.Assert.assertNotNull(duration21);
        org.junit.Assert.assertNotNull(duration22);
    }

    @Test
    public void test0685() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0685");
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
        org.joda.time.LocalDateTime localDateTime15 = localDateTime13.plusYears(168);
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(localDateTime8);
        org.junit.Assert.assertNotNull(property9);
        org.junit.Assert.assertNotNull(dateTime10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(localDateTime13);
        org.junit.Assert.assertNotNull(localDateTime15);
    }

    @Test
    public void test0686() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0686");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar8 = dateMidnight7.toGregorianCalendar();
        org.joda.time.TimeOfDay timeOfDay9 = org.joda.time.TimeOfDay.fromCalendarFields((java.util.Calendar) gregorianCalendar8);
        org.joda.time.LocalTime localTime10 = timeOfDay9.toLocalTime();
        org.joda.time.TimeOfDay timeOfDay12 = timeOfDay9.minusSeconds(40256602);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(gregorianCalendar8);
        org.junit.Assert.assertNotNull(timeOfDay9);
        org.junit.Assert.assertNotNull(localTime10);
        org.junit.Assert.assertNotNull(timeOfDay12);
    }

    @Test
    public void test0687() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0687");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter1 = org.joda.time.format.ISODateTimeFormat.dateHour();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.YearMonth yearMonth2 = org.joda.time.YearMonth.parse("org.joda.time.IllegalFieldValueException: Value \"hi!\" for 2026-08-06T11:10:39.499 is not supported", dateTimeFormatter1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid format: \"org.joda.time.IllegalFieldValueE...\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeFormatter1);
    }

    @Test
    public void test0688() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0688");
        org.joda.time.Days days2 = org.joda.time.Days.ONE;
        org.joda.time.DurationFieldType durationFieldType3 = days2.getFieldType();
        org.joda.time.PeriodType periodType4 = days2.getPeriodType();
        org.joda.time.PeriodType periodType5 = days2.getPeriodType();
        org.joda.time.Period period6 = new org.joda.time.Period(2374041763L, 24349612086000008L, periodType5);
        org.junit.Assert.assertNotNull(days2);
        org.junit.Assert.assertNotNull(durationFieldType3);
        org.junit.Assert.assertNotNull(periodType4);
        org.junit.Assert.assertNotNull(periodType5);
    }

    @Test
    public void test0689() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0689");
        org.joda.time.DateTimeUtils.setCurrentMillisOffset((long) 2026);
    }

    @Test
    public void test0690() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0690");
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
        org.joda.time.DateMidnight dateMidnight21 = dateMidnight19.withWeekOfWeekyear((int) '#');
        org.joda.time.MutableDateTime mutableDateTime22 = dateMidnight19.toMutableDateTimeISO();
        org.joda.time.DateTimeZone dateTimeZone23 = dateMidnight19.getZone();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(dateMidnight14);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + (-1) + "'", int15 == (-1));
        org.junit.Assert.assertNotNull(minutes16);
        org.junit.Assert.assertNotNull(dateMidnight19);
        org.junit.Assert.assertNotNull(dateMidnight21);
        org.junit.Assert.assertNotNull(mutableDateTime22);
        org.junit.Assert.assertNotNull(dateTimeZone23);
    }

    @Test
    public void test0691() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0691");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusYears(100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.monthOfYear();
        org.joda.time.ReadablePeriod readablePeriod6 = null;
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.plus(readablePeriod6);
        org.joda.time.ReadableInstant readableInstant8 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Seconds seconds9 = org.joda.time.Seconds.secondsBetween((org.joda.time.ReadableInstant) dateMidnight2, readableInstant8);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: ReadableInstant objects must not be null");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(dateMidnight7);
    }

    @Test
    public void test0692() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0692");
        org.joda.time.LocalTime localTime1 = new org.joda.time.LocalTime((long) (-1));
        org.joda.time.Chronology chronology2 = localTime1.getChronology();
        org.joda.time.ReadableInterval readableInterval3 = null;
        org.joda.time.Seconds seconds4 = org.joda.time.Seconds.secondsIn(readableInterval3);
        org.joda.time.DurationFieldType durationFieldType5 = seconds4.getFieldType();
        org.joda.time.Seconds seconds7 = seconds4.dividedBy((int) ' ');
        org.joda.time.LocalTime localTime8 = localTime1.minus((org.joda.time.ReadablePeriod) seconds7);
        org.joda.time.ReadableInterval readableInterval9 = null;
        org.joda.time.Seconds seconds10 = org.joda.time.Seconds.secondsIn(readableInterval9);
        org.joda.time.ReadableInterval readableInterval11 = null;
        org.joda.time.Seconds seconds12 = org.joda.time.Seconds.secondsIn(readableInterval11);
        org.joda.time.Seconds seconds13 = seconds10.plus(seconds12);
        boolean boolean14 = seconds7.isGreaterThan(seconds10);
        org.junit.Assert.assertNotNull(chronology2);
        org.junit.Assert.assertNotNull(seconds4);
        org.junit.Assert.assertNotNull(durationFieldType5);
        org.junit.Assert.assertNotNull(seconds7);
        org.junit.Assert.assertNotNull(localTime8);
        org.junit.Assert.assertNotNull(seconds10);
        org.junit.Assert.assertNotNull(seconds12);
        org.junit.Assert.assertNotNull(seconds13);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
    }

    @Test
    public void test0693() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0693");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        int int10 = dateMidnight4.compareTo((org.joda.time.ReadableInstant) dateMidnight7);
        org.joda.time.MutableDateTime mutableDateTime11 = dateMidnight7.toMutableDateTime();
        org.joda.time.PeriodType periodType13 = null;
        org.joda.time.Chronology chronology14 = null;
        org.joda.time.Period period15 = new org.joda.time.Period((long) (short) 10, periodType13, chronology14);
        org.joda.time.Months months16 = org.joda.time.Months.FIVE;
        org.joda.time.Period period17 = period15.minus((org.joda.time.ReadablePeriod) months16);
        org.joda.time.Period period19 = period15.plusMinutes((int) ' ');
        org.joda.time.DurationFieldType[] durationFieldTypeArray20 = period15.getFieldTypes();
        org.joda.time.Period period22 = period15.minusDays((int) (short) 100);
        org.joda.time.DateMidnight dateMidnight23 = dateMidnight7.minus((org.joda.time.ReadablePeriod) period22);
        org.joda.time.LocalTime localTime24 = new org.joda.time.LocalTime((java.lang.Object) dateMidnight7);
        org.joda.time.DateMidnight dateMidnight26 = dateMidnight7.withDayOfWeek(2);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + (-1) + "'", int10 == (-1));
        org.junit.Assert.assertNotNull(mutableDateTime11);
        org.junit.Assert.assertNotNull(months16);
        org.junit.Assert.assertNotNull(period17);
        org.junit.Assert.assertNotNull(period19);
        org.junit.Assert.assertNotNull(durationFieldTypeArray20);
        org.junit.Assert.assertNotNull(period22);
        org.junit.Assert.assertNotNull(dateMidnight23);
        org.junit.Assert.assertNotNull(dateMidnight26);
    }

    @Test
    public void test0694() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0694");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.MonthDay monthDay4 = monthDay2.minusDays((int) '4');
        org.joda.time.MonthDay.Property property5 = monthDay2.monthOfYear();
        java.lang.String str6 = property5.getName();
        org.joda.time.DateTimeField dateTimeField7 = property5.getField();
        int int8 = property5.getMaximumValueOverall();
        org.joda.time.DateTimeField dateTimeField9 = property5.getField();
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(monthDay4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "monthOfYear" + "'", str6, "monthOfYear");
        org.junit.Assert.assertNotNull(dateTimeField7);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 12 + "'", int8 == 12);
        org.junit.Assert.assertNotNull(dateTimeField9);
    }

    @Test
    public void test0695() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0695");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.DurationField durationField3 = gJChronology1.months();
        org.joda.time.DateMidnight dateMidnight5 = org.joda.time.DateMidnight.parse("2026-08-06T11:10:36.726");
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight5.minusYears((int) (byte) 100);
        org.joda.time.ReadableDateTime readableDateTime8 = null;
        org.joda.time.chrono.LimitChronology limitChronology9 = org.joda.time.chrono.LimitChronology.getInstance((org.joda.time.Chronology) gJChronology1, (org.joda.time.ReadableDateTime) dateMidnight5, readableDateTime8);
        org.joda.time.DateTime dateTime10 = limitChronology9.getLowerLimit();
        org.joda.time.DateTime dateTime11 = limitChronology9.getUpperLimit();
        org.joda.time.DateTimeField dateTimeField12 = limitChronology9.hourOfDay();
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(durationField3);
        org.junit.Assert.assertNotNull(dateMidnight5);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(limitChronology9);
        org.junit.Assert.assertNotNull(dateTime10);
        org.junit.Assert.assertNull(dateTime11);
        org.junit.Assert.assertNotNull(dateTimeField12);
    }

    @Test
    public void test0696() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0696");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.Months months4 = org.joda.time.Months.FIVE;
        org.joda.time.Period period5 = period3.minus((org.joda.time.ReadablePeriod) months4);
        org.joda.time.Period period7 = period3.plusMillis((-1));
        org.joda.time.YearMonth yearMonth8 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone9 = null;
        org.joda.time.Interval interval10 = yearMonth8.toInterval(dateTimeZone9);
        org.joda.time.Months months11 = org.joda.time.Months.ONE;
        org.joda.time.Interval interval12 = interval10.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) months11);
        org.joda.time.DateTime dateTime13 = interval12.getStart();
        org.joda.time.Duration duration14 = period7.toDurationTo((org.joda.time.ReadableInstant) dateTime13);
        org.junit.Assert.assertNotNull(months4);
        org.junit.Assert.assertNotNull(period5);
        org.junit.Assert.assertNotNull(period7);
        org.junit.Assert.assertNotNull(interval10);
        org.junit.Assert.assertNotNull(months11);
        org.junit.Assert.assertNotNull(interval12);
        org.junit.Assert.assertNotNull(dateTime13);
        org.junit.Assert.assertNotNull(duration14);
    }

    @Test
    public void test0697() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0697");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone5 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology6 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.LocalDateTime localDateTime7 = new org.joda.time.LocalDateTime((long) (-1), (org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.tz.CachedDateTimeZone cachedDateTimeZone8 = org.joda.time.tz.CachedDateTimeZone.forZone((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.chrono.ISOChronology iSOChronology9 = org.joda.time.chrono.ISOChronology.getInstance((org.joda.time.DateTimeZone) cachedDateTimeZone8);
        java.lang.String str11 = cachedDateTimeZone8.getNameKey((long) 3600);
        int int13 = cachedDateTimeZone8.getOffset(1786007462167L);
        org.junit.Assert.assertNotNull(gregorianChronology6);
        org.junit.Assert.assertNotNull(cachedDateTimeZone8);
        org.junit.Assert.assertNotNull(iSOChronology9);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "hi!" + "'", str11, "hi!");
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 100 + "'", int13 == 100);
    }

    @Test
    public void test0698() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0698");
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder0 = new org.joda.time.format.PeriodFormatterBuilder();
        periodFormatterBuilder0.clear();
        org.joda.time.format.PeriodFormatter periodFormatter2 = org.joda.time.format.ISOPeriodFormat.alternateWithWeeks();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder3 = periodFormatterBuilder0.append(periodFormatter2);
        org.joda.time.PeriodType periodType4 = org.joda.time.PeriodType.yearDay();
        org.joda.time.PeriodType periodType5 = periodType4.withSecondsRemoved();
        int int6 = periodType4.size();
        org.joda.time.format.PeriodFormatter periodFormatter7 = periodFormatter2.withParseType(periodType4);
        org.junit.Assert.assertNotNull(periodFormatter2);
        org.junit.Assert.assertNotNull(periodFormatterBuilder3);
        org.junit.Assert.assertNotNull(periodType4);
        org.junit.Assert.assertNotNull(periodType5);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 2 + "'", int6 == 2);
        org.junit.Assert.assertNotNull(periodFormatter7);
    }

    @Test
    public void test0699() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0699");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar8 = dateMidnight7.toGregorianCalendar();
        org.joda.time.LocalTime localTime9 = org.joda.time.LocalTime.fromCalendarFields((java.util.Calendar) gregorianCalendar8);
        org.joda.time.LocalTime.Property property10 = localTime9.minuteOfHour();
        org.joda.time.LocalTime localTime11 = property10.withMaximumValue();
        org.joda.time.LocalTime localTime13 = property10.addNoWrapToCopy(4);
        int int14 = localTime13.getSecondOfMinute();
        org.joda.time.LocalTime localTime16 = localTime13.plusMinutes(2147483647);
        org.joda.time.LocalTime localTime18 = localTime16.minusSeconds(1);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(gregorianCalendar8);
        org.junit.Assert.assertNotNull(localTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(localTime11);
        org.junit.Assert.assertNotNull(localTime13);
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 0 + "'", int14 == 0);
        org.junit.Assert.assertNotNull(localTime16);
        org.junit.Assert.assertNotNull(localTime18);
    }

    @Test
    public void test0700() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0700");
        org.joda.time.Years years1 = org.joda.time.Years.years((int) (byte) 10);
        org.joda.time.Years years3 = years1.dividedBy(1);
        org.joda.time.Years years5 = org.joda.time.Years.years((int) (byte) 10);
        org.joda.time.Years years7 = years5.dividedBy(1);
        boolean boolean8 = years3.isLessThan(years7);
        org.joda.time.Years years10 = years7.multipliedBy(0);
        org.joda.time.Years years11 = org.joda.time.Years.MAX_VALUE;
        org.joda.time.Years years12 = null;
        org.joda.time.Years years13 = years11.minus(years12);
        org.joda.time.Years years14 = years10.minus(years11);
        org.junit.Assert.assertNotNull(years1);
        org.junit.Assert.assertNotNull(years3);
        org.junit.Assert.assertNotNull(years5);
        org.junit.Assert.assertNotNull(years7);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(years10);
        org.junit.Assert.assertNotNull(years11);
        org.junit.Assert.assertNotNull(years13);
        org.junit.Assert.assertNotNull(years14);
    }

    @Test
    public void test0701() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0701");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField1 = julianChronology0.clockhourOfDay();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj3 = null;
        boolean boolean4 = gJChronology2.equals(obj3);
        org.joda.time.DurationField durationField5 = gJChronology2.days();
        org.joda.time.chrono.GJChronology gJChronology6 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology7 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology6);
        org.joda.time.chrono.LenientChronology lenientChronology8 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology6);
        org.joda.time.DateTimeField dateTimeField9 = lenientChronology8.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField10 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology2, dateTimeField9);
        org.joda.time.DateTimeFieldType dateTimeFieldType11 = skipUndoDateTimeField10.getType();
        org.joda.time.field.DividedDateTimeField dividedDateTimeField13 = new org.joda.time.field.DividedDateTimeField(dateTimeField1, dateTimeFieldType11, (int) 'a');
        org.joda.time.DurationField durationField14 = dividedDateTimeField13.getLeapDurationField();
        java.util.Locale locale15 = null;
        int int16 = dividedDateTimeField13.getMaximumTextLength(locale15);
        org.joda.time.chrono.GJChronology gJChronology17 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj18 = null;
        boolean boolean19 = gJChronology17.equals(obj18);
        org.joda.time.DurationField durationField20 = gJChronology17.days();
        org.joda.time.chrono.GJChronology gJChronology21 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology22 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology21);
        org.joda.time.chrono.LenientChronology lenientChronology23 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology21);
        org.joda.time.DateTimeField dateTimeField24 = lenientChronology23.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField25 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology17, dateTimeField24);
        org.joda.time.DateTimeFieldType dateTimeFieldType26 = skipUndoDateTimeField25.getType();
        org.joda.time.Partial partial28 = new org.joda.time.Partial(dateTimeFieldType26, 9);
        org.joda.time.Partial partial30 = new org.joda.time.Partial(dateTimeFieldType26, 53);
        java.util.Locale locale32 = null;
        java.lang.String str33 = dividedDateTimeField13.getAsText((org.joda.time.ReadablePartial) partial30, 1440, locale32);
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(dateTimeField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(durationField5);
        org.junit.Assert.assertNotNull(gJChronology6);
        org.junit.Assert.assertNotNull(lenientChronology7);
        org.junit.Assert.assertNotNull(lenientChronology8);
        org.junit.Assert.assertNotNull(dateTimeField9);
        org.junit.Assert.assertNotNull(dateTimeFieldType11);
        org.junit.Assert.assertNull(durationField14);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 1 + "'", int16 == 1);
        org.junit.Assert.assertNotNull(gJChronology17);
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
        org.junit.Assert.assertNotNull(durationField20);
        org.junit.Assert.assertNotNull(gJChronology21);
        org.junit.Assert.assertNotNull(lenientChronology22);
        org.junit.Assert.assertNotNull(lenientChronology23);
        org.junit.Assert.assertNotNull(dateTimeField24);
        org.junit.Assert.assertNotNull(dateTimeFieldType26);
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "1440" + "'", str33, "1440");
    }

    @Test
    public void test0702() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0702");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.DateTimeFormat.longDate();
        org.joda.time.format.DateTimeFormatter dateTimeFormatter2 = dateTimeFormatter0.withPivotYear((java.lang.Integer) 2026);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDate localDate4 = dateTimeFormatter2.parseLocalDate("2026-09-01T00:01:40.000+02:00");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid format: \"2026-09-01T00:01:40.000+02:00\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
        org.junit.Assert.assertNotNull(dateTimeFormatter2);
    }

    @Test
    public void test0703() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0703");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.minusHours((int) (byte) -1);
        // The following exception was thrown during execution in test generation
        try {
            int int5 = localDateTime3.getValue(12);
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: Invalid index: 12");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(localDateTime3);
    }

    @Test
    public void test0704() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0704");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        org.joda.time.ReadableInterval readableInterval5 = null;
        org.joda.time.Seconds seconds6 = org.joda.time.Seconds.secondsIn(readableInterval5);
        org.joda.time.DurationFieldType durationFieldType7 = seconds6.getFieldType();
        org.joda.time.LocalDateTime localDateTime9 = localDateTime1.withFieldAdded(durationFieldType7, 86400000);
        org.joda.time.LocalDateTime localDateTime11 = localDateTime9.plusMillis((int) (byte) 10);
        org.joda.time.LocalDateTime localDateTime13 = localDateTime11.withYear((int) (short) 0);
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(seconds6);
        org.junit.Assert.assertNotNull(durationFieldType7);
        org.junit.Assert.assertNotNull(localDateTime9);
        org.junit.Assert.assertNotNull(localDateTime11);
        org.junit.Assert.assertNotNull(localDateTime13);
    }

    @Test
    public void test0705() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0705");
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
        org.joda.time.DurationField durationField19 = islamicChronology18.millis();
        java.lang.String str20 = islamicChronology18.toString();
        org.joda.time.LocalDate localDate21 = new org.joda.time.LocalDate((org.joda.time.Chronology) islamicChronology18);
        org.junit.Assert.assertNotNull(copticChronology5);
        org.junit.Assert.assertNotNull(leapYearPatternType6);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(property12);
        org.junit.Assert.assertNotNull(mutableDateTime13);
        org.junit.Assert.assertNotNull(property14);
        org.junit.Assert.assertNotNull(mutableDateTime16);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(islamicChronology18);
        org.junit.Assert.assertNotNull(durationField19);
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "IslamicChronology[2026-08-06T11:10:36.726]" + "'", str20, "IslamicChronology[2026-08-06T11:10:36.726]");
    }

    @Test
    public void test0706() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0706");
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
        org.joda.time.Instant instant29 = instant0.withDurationAdded((long) 69, 13);
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
        org.junit.Assert.assertNotNull(instant29);
    }

    @Test
    public void test0707() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0707");
        org.joda.time.chrono.GJChronology gJChronology0 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField1 = gJChronology0.weekyearOfCentury();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj3 = null;
        boolean boolean4 = gJChronology2.equals(obj3);
        org.joda.time.DurationField durationField5 = gJChronology2.days();
        org.joda.time.chrono.GJChronology gJChronology6 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology7 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology6);
        org.joda.time.chrono.LenientChronology lenientChronology8 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology6);
        org.joda.time.DateTimeField dateTimeField9 = lenientChronology8.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField10 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology2, dateTimeField9);
        org.joda.time.DateTimeFieldType dateTimeFieldType11 = skipUndoDateTimeField10.getType();
        org.joda.time.DurationField durationField12 = skipUndoDateTimeField10.getRangeDurationField();
        org.joda.time.DateTimeComparator dateTimeComparator13 = org.joda.time.DateTimeComparator.getDateOnlyInstance();
        org.joda.time.DateTimeComparator dateTimeComparator14 = org.joda.time.DateTimeComparator.getTimeOnlyInstance();
        java.util.Comparator<java.lang.Object> objComparator15 = dateTimeComparator13.thenComparing((java.util.Comparator<java.lang.Object>) dateTimeComparator14);
        org.joda.time.DateTimeFieldType dateTimeFieldType16 = dateTimeComparator14.getUpperLimit();
        org.joda.time.DateTimeComparator dateTimeComparator17 = org.joda.time.DateTimeComparator.getInstance(dateTimeFieldType16);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.field.RemainderDateTimeField remainderDateTimeField19 = new org.joda.time.field.RemainderDateTimeField(dateTimeField1, durationField12, dateTimeFieldType16, (-53));
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The divisor must be at least 2");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gJChronology0);
        org.junit.Assert.assertNotNull(dateTimeField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(durationField5);
        org.junit.Assert.assertNotNull(gJChronology6);
        org.junit.Assert.assertNotNull(lenientChronology7);
        org.junit.Assert.assertNotNull(lenientChronology8);
        org.junit.Assert.assertNotNull(dateTimeField9);
        org.junit.Assert.assertNotNull(dateTimeFieldType11);
        org.junit.Assert.assertNotNull(durationField12);
        org.junit.Assert.assertNotNull(dateTimeComparator13);
        org.junit.Assert.assertNotNull(dateTimeComparator14);
        org.junit.Assert.assertNotNull(objComparator15);
        org.junit.Assert.assertNotNull(dateTimeFieldType16);
        org.junit.Assert.assertNotNull(dateTimeComparator17);
    }

    @Test
    public void test0708() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0708");
        org.joda.time.LocalTime localTime1 = new org.joda.time.LocalTime((long) (-1));
        org.joda.time.LocalTime localTime3 = localTime1.withSecondOfMinute(1);
        org.joda.time.LocalTime localTime5 = localTime1.plusSeconds(0);
        org.joda.time.LocalTime localTime7 = localTime1.plusSeconds(86400000);
        org.joda.time.LocalTime.Property property8 = localTime7.hourOfDay();
        org.joda.time.LocalTime.Property property9 = localTime7.millisOfDay();
        org.junit.Assert.assertNotNull(localTime3);
        org.junit.Assert.assertNotNull(localTime5);
        org.junit.Assert.assertNotNull(localTime7);
        org.junit.Assert.assertNotNull(property8);
        org.junit.Assert.assertNotNull(property9);
    }

    @Test
    public void test0709() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0709");
        org.joda.time.ReadableInstant readableInstant0 = null;
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Chronology chronology3 = null;
        org.joda.time.DateMidnight dateMidnight4 = new org.joda.time.DateMidnight((long) (short) -1, chronology3);
        org.joda.time.DateMidnight dateMidnight6 = dateMidnight4.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology8 = null;
        org.joda.time.DateMidnight dateMidnight9 = new org.joda.time.DateMidnight((long) (short) -1, chronology8);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.minusMonths((int) (byte) 100);
        int int12 = dateMidnight6.compareTo((org.joda.time.ReadableInstant) dateMidnight9);
        org.joda.time.chrono.GJChronology gJChronology14 = org.joda.time.chrono.GJChronology.getInstance(dateTimeZone1, (org.joda.time.ReadableInstant) dateMidnight9, 7);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Days days15 = org.joda.time.Days.daysBetween(readableInstant0, (org.joda.time.ReadableInstant) dateMidnight9);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: ReadableInstant objects must not be null");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight6);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + (-1) + "'", int12 == (-1));
        org.junit.Assert.assertNotNull(gJChronology14);
    }

    @Test
    public void test0710() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0710");
        org.joda.time.convert.ConverterManager converterManager0 = org.joda.time.convert.ConverterManager.getInstance();
        org.joda.time.convert.PartialConverter partialConverter1 = null;
        org.joda.time.convert.PartialConverter partialConverter2 = converterManager0.addPartialConverter(partialConverter1);
        org.joda.time.convert.PeriodConverter periodConverter3 = null;
        org.joda.time.convert.PeriodConverter periodConverter4 = converterManager0.removePeriodConverter(periodConverter3);
        org.joda.time.convert.DurationConverter[] durationConverterArray5 = converterManager0.getDurationConverters();
        org.joda.time.convert.ConverterManager converterManager6 = org.joda.time.convert.ConverterManager.getInstance();
        org.joda.time.convert.PartialConverter partialConverter7 = null;
        org.joda.time.convert.PartialConverter partialConverter8 = converterManager6.addPartialConverter(partialConverter7);
        org.joda.time.convert.PartialConverter partialConverter9 = null;
        org.joda.time.convert.PartialConverter partialConverter10 = converterManager6.addPartialConverter(partialConverter9);
        org.joda.time.Instant instant11 = org.joda.time.Instant.EPOCH;
        org.joda.time.Instant instant14 = instant11.withDurationAdded((-1L), 100);
        org.joda.time.convert.DurationConverter durationConverter15 = converterManager6.getDurationConverter((java.lang.Object) (-1L));
        org.joda.time.convert.DurationConverter durationConverter16 = converterManager0.addDurationConverter(durationConverter15);
        org.joda.time.convert.IntervalConverter[] intervalConverterArray17 = converterManager0.getIntervalConverters();
        org.junit.Assert.assertNotNull(converterManager0);
        org.junit.Assert.assertNull(partialConverter2);
        org.junit.Assert.assertNull(periodConverter4);
        org.junit.Assert.assertNotNull(durationConverterArray5);
        org.junit.Assert.assertNotNull(converterManager6);
        org.junit.Assert.assertNull(partialConverter8);
        org.junit.Assert.assertNull(partialConverter10);
        org.junit.Assert.assertNotNull(instant11);
        org.junit.Assert.assertNotNull(instant14);
        org.junit.Assert.assertNotNull(durationConverter15);
        org.junit.Assert.assertNull(durationConverter16);
        org.junit.Assert.assertNotNull(intervalConverterArray17);
    }

    @Test
    public void test0711() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0711");
        org.joda.time.IllegalFieldValueException illegalFieldValueException4 = new org.joda.time.IllegalFieldValueException("ISOChronology[2026-08-06T11:10:36.726]", (java.lang.Number) 100.0f, (java.lang.Number) (byte) 1, (java.lang.Number) 51);
        java.lang.Number number5 = illegalFieldValueException4.getIllegalNumberValue();
        org.junit.Assert.assertEquals("'" + number5 + "' != '" + 100.0f + "'", number5, 100.0f);
    }

    @Test
    public void test0712() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0712");
        java.util.Map<java.lang.String, org.joda.time.DateTimeZone> strMap0 = org.joda.time.DateTimeUtils.getDefaultTimeZoneNames();
        org.joda.time.DateTimeUtils.setDefaultTimeZoneNames(strMap0);
        org.junit.Assert.assertNotNull(strMap0);
    }

    @Test
    public void test0713() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0713");
        int int0 = org.joda.time.MutableDateTime.ROUND_NONE;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 0 + "'", int0 == 0);
    }

    @Test
    public void test0714() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0714");
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
        org.joda.time.DateTime dateTime17 = dateTime10.minusDays((-1));
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(dateTime12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(dateTime15);
        org.junit.Assert.assertNotNull(dateTime17);
    }

    @Test
    public void test0715() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0715");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.TimeOfDay timeOfDay4 = new org.joda.time.TimeOfDay((int) '#', 3, 69, 51);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 35 for hourOfDay must not be larger than 23");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0716() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0716");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        mutableDateTime6.setSecondOfMinute(0);
        mutableDateTime6.add((long) 86400000);
        int int12 = mutableDateTime6.getMinuteOfHour();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone17 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology18 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone17);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType19 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology21 = null;
        org.joda.time.DateMidnight dateMidnight22 = new org.joda.time.DateMidnight((long) (short) -1, chronology21);
        org.joda.time.DateMidnight dateMidnight24 = dateMidnight22.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property25 = dateMidnight22.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime26 = dateMidnight22.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property27 = mutableDateTime26.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime29 = property27.addWrapField((int) (short) -1);
        boolean boolean30 = leapYearPatternType19.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology31 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone17, leapYearPatternType19);
        org.joda.time.YearMonthDay yearMonthDay32 = new org.joda.time.YearMonthDay((org.joda.time.DateTimeZone) fixedDateTimeZone17);
        org.joda.time.DateTime dateTime33 = org.joda.time.DateTime.now((org.joda.time.DateTimeZone) fixedDateTimeZone17);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTime dateTime34 = new org.joda.time.DateTime((java.lang.Object) int12, (org.joda.time.DateTimeZone) fixedDateTimeZone17);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No instant converter found for type: java.lang.Integer");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 0 + "'", int12 == 0);
        org.junit.Assert.assertNotNull(copticChronology18);
        org.junit.Assert.assertNotNull(leapYearPatternType19);
        org.junit.Assert.assertNotNull(dateMidnight24);
        org.junit.Assert.assertNotNull(property25);
        org.junit.Assert.assertNotNull(mutableDateTime26);
        org.junit.Assert.assertNotNull(property27);
        org.junit.Assert.assertNotNull(mutableDateTime29);
        org.junit.Assert.assertTrue("'" + boolean30 + "' != '" + false + "'", boolean30 == false);
        org.junit.Assert.assertNotNull(islamicChronology31);
        org.junit.Assert.assertNotNull(dateTime33);
    }

    @Test
    public void test0717() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0717");
        org.joda.time.Years years0 = org.joda.time.Years.MAX_VALUE;
        org.joda.time.Years years1 = null;
        org.joda.time.Years years2 = years0.minus(years1);
        org.joda.time.Years years4 = years0.dividedBy((int) ' ');
        org.junit.Assert.assertNotNull(years0);
        org.junit.Assert.assertNotNull(years2);
        org.junit.Assert.assertNotNull(years4);
    }

    @Test
    public void test0718() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0718");
        org.joda.time.YearMonth yearMonth1 = new org.joda.time.YearMonth((long) (short) 100);
    }

    @Test
    public void test0719() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0719");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField1 = julianChronology0.clockhourOfDay();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj3 = null;
        boolean boolean4 = gJChronology2.equals(obj3);
        org.joda.time.DurationField durationField5 = gJChronology2.days();
        org.joda.time.chrono.GJChronology gJChronology6 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology7 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology6);
        org.joda.time.chrono.LenientChronology lenientChronology8 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology6);
        org.joda.time.DateTimeField dateTimeField9 = lenientChronology8.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField10 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology2, dateTimeField9);
        org.joda.time.DateTimeFieldType dateTimeFieldType11 = skipUndoDateTimeField10.getType();
        org.joda.time.field.DividedDateTimeField dividedDateTimeField13 = new org.joda.time.field.DividedDateTimeField(dateTimeField1, dateTimeFieldType11, (int) 'a');
        // The following exception was thrown during execution in test generation
        try {
            long long15 = dividedDateTimeField13.roundHalfCeiling((long) 40243947);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 0 for clockhourOfDay must be in the range [1,24]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(dateTimeField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(durationField5);
        org.junit.Assert.assertNotNull(gJChronology6);
        org.junit.Assert.assertNotNull(lenientChronology7);
        org.junit.Assert.assertNotNull(lenientChronology8);
        org.junit.Assert.assertNotNull(dateTimeField9);
        org.junit.Assert.assertNotNull(dateTimeFieldType11);
    }

    @Test
    public void test0720() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0720");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.timeElementParser();
        java.lang.StringBuilder stringBuilder1 = null;
        org.joda.time.Chronology chronology3 = null;
        org.joda.time.DateMidnight dateMidnight4 = new org.joda.time.DateMidnight((long) (short) -1, chronology3);
        org.joda.time.DateMidnight dateMidnight6 = dateMidnight4.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology8 = null;
        org.joda.time.DateMidnight dateMidnight9 = new org.joda.time.DateMidnight((long) (short) -1, chronology8);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.minusMonths((int) (byte) 100);
        int int12 = dateMidnight6.compareTo((org.joda.time.ReadableInstant) dateMidnight9);
        org.joda.time.ReadableInterval readableInterval13 = null;
        org.joda.time.Seconds seconds14 = org.joda.time.Seconds.secondsIn(readableInterval13);
        org.joda.time.DurationFieldType durationFieldType15 = seconds14.getFieldType();
        org.joda.time.DateMidnight dateMidnight17 = dateMidnight6.withFieldAdded(durationFieldType15, (int) (byte) 0);
        org.joda.time.DateMidnight.Property property18 = dateMidnight17.dayOfMonth();
        // The following exception was thrown during execution in test generation
        try {
            dateTimeFormatter0.printTo(stringBuilder1, (org.joda.time.ReadableInstant) dateMidnight17);
            org.junit.Assert.fail("Expected exception of type java.lang.UnsupportedOperationException; message: Printing not supported");
        } catch (java.lang.UnsupportedOperationException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
        org.junit.Assert.assertNotNull(dateMidnight6);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + (-1) + "'", int12 == (-1));
        org.junit.Assert.assertNotNull(seconds14);
        org.junit.Assert.assertNotNull(durationFieldType15);
        org.junit.Assert.assertNotNull(dateMidnight17);
        org.junit.Assert.assertNotNull(property18);
    }

    @Test
    public void test0721() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0721");
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
        int int20 = period14.getMinutes();
        org.joda.time.Period period22 = period14.multipliedBy(86400);
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
        org.junit.Assert.assertTrue("'" + int20 + "' != '" + 0 + "'", int20 == 0);
        org.junit.Assert.assertNotNull(period22);
    }

    @Test
    public void test0722() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0722");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        int int5 = localDateTime1.getYear();
        int int6 = localDateTime1.getDayOfWeek();
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 2026 + "'", int5 == 2026);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 4 + "'", int6 == 4);
    }

    @Test
    public void test0723() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0723");
        org.joda.time.Hours hours0 = org.joda.time.Hours.FOUR;
        org.joda.time.Hours hours2 = hours0.minus(1900);
        org.joda.time.Hours hours3 = org.joda.time.Hours.SEVEN;
        org.joda.time.Hours hours4 = hours0.plus(hours3);
        org.junit.Assert.assertNotNull(hours0);
        org.junit.Assert.assertNotNull(hours2);
        org.junit.Assert.assertNotNull(hours3);
        org.junit.Assert.assertNotNull(hours4);
    }

    @Test
    public void test0724() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0724");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Interval interval2 = yearMonth0.toInterval(dateTimeZone1);
        int int3 = yearMonth0.size();
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 2 + "'", int3 == 2);
    }

    @Test
    public void test0725() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0725");
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
        org.joda.time.chrono.GJChronology gJChronology23 = org.joda.time.chrono.GJChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5);
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
        org.junit.Assert.assertNotNull(gJChronology23);
    }

    @Test
    public void test0726() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0726");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray3 = monthDay2.getFieldTypes();
        org.joda.time.chrono.GJChronology gJChronology4 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology5 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology4);
        org.joda.time.chrono.LenientChronology lenientChronology6 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology4);
        org.joda.time.MonthDay monthDay7 = new org.joda.time.MonthDay((org.joda.time.Chronology) lenientChronology6);
        org.joda.time.MonthDay monthDay8 = monthDay2.withChronologyRetainFields((org.joda.time.Chronology) lenientChronology6);
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(dateTimeFieldTypeArray3);
        org.junit.Assert.assertNotNull(gJChronology4);
        org.junit.Assert.assertNotNull(lenientChronology5);
        org.junit.Assert.assertNotNull(lenientChronology6);
        org.junit.Assert.assertNotNull(monthDay8);
    }

    @Test
    public void test0727() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0727");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone4 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.ISOChronology iSOChronology5 = org.joda.time.chrono.ISOChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        org.joda.time.chrono.GregorianChronology gregorianChronology6 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        org.joda.time.DateTimeZone dateTimeZone7 = gregorianChronology6.getZone();
        org.junit.Assert.assertNotNull(iSOChronology5);
        org.junit.Assert.assertNotNull(gregorianChronology6);
        org.junit.Assert.assertNotNull(dateTimeZone7);
    }

    @Test
    public void test0728() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0728");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Interval interval2 = yearMonth0.toInterval(dateTimeZone1);
        org.joda.time.YearMonth yearMonth4 = yearMonth0.plusMonths((int) 'a');
        org.joda.time.YearMonth.Property property5 = yearMonth0.year();
        java.lang.String str6 = property5.getAsText();
        org.joda.time.YearMonth yearMonth8 = property5.setCopy("1440");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTimeFieldType dateTimeFieldType10 = yearMonth8.getFieldType((int) 'a');
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 97");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertNotNull(yearMonth4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "2026" + "'", str6, "2026");
        org.junit.Assert.assertNotNull(yearMonth8);
    }

    @Test
    public void test0729() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0729");
        org.joda.time.PeriodType periodType3 = null;
        org.joda.time.Chronology chronology4 = null;
        org.joda.time.Period period5 = new org.joda.time.Period((long) (short) 10, periodType3, chronology4);
        org.joda.time.Months months6 = org.joda.time.Months.FIVE;
        org.joda.time.Period period7 = period5.minus((org.joda.time.ReadablePeriod) months6);
        org.joda.time.Period period9 = period5.plusMinutes((int) ' ');
        org.joda.time.DurationFieldType[] durationFieldTypeArray10 = period5.getFieldTypes();
        org.joda.time.PeriodType periodType11 = period5.getPeriodType();
        org.joda.time.chrono.JulianChronology julianChronology13 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField14 = julianChronology13.weeks();
        org.joda.time.LocalTime localTime15 = new org.joda.time.LocalTime(8L, (org.joda.time.Chronology) julianChronology13);
        org.joda.time.MutablePeriod mutablePeriod16 = new org.joda.time.MutablePeriod((long) 1970, (long) (short) 10, periodType11, (org.joda.time.Chronology) julianChronology13);
        mutablePeriod16.addMonths(53);
        org.junit.Assert.assertNotNull(months6);
        org.junit.Assert.assertNotNull(period7);
        org.junit.Assert.assertNotNull(period9);
        org.junit.Assert.assertNotNull(durationFieldTypeArray10);
        org.junit.Assert.assertNotNull(periodType11);
        org.junit.Assert.assertNotNull(julianChronology13);
        org.junit.Assert.assertNotNull(durationField14);
    }

    @Test
    public void test0730() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0730");
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
        mutableDateTime6.addWeekyears(3);
        org.joda.time.ReadableInterval readableInterval16 = null;
        org.joda.time.Seconds seconds17 = org.joda.time.Seconds.secondsIn(readableInterval16);
        org.joda.time.ReadableInterval readableInterval18 = null;
        org.joda.time.Seconds seconds19 = org.joda.time.Seconds.secondsIn(readableInterval18);
        org.joda.time.Seconds seconds20 = seconds17.plus(seconds19);
        org.joda.time.PeriodType periodType21 = seconds17.getPeriodType();
        org.joda.time.Seconds seconds22 = org.joda.time.Seconds.TWO;
        boolean boolean23 = seconds17.isGreaterThan(seconds22);
        org.joda.time.Duration duration24 = seconds17.toStandardDuration();
        org.joda.time.Interval interval25 = new org.joda.time.Interval((org.joda.time.ReadableInstant) mutableDateTime6, (org.joda.time.ReadableDuration) duration24);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(dateTime12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(seconds17);
        org.junit.Assert.assertNotNull(seconds19);
        org.junit.Assert.assertNotNull(seconds20);
        org.junit.Assert.assertNotNull(periodType21);
        org.junit.Assert.assertNotNull(seconds22);
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
        org.junit.Assert.assertNotNull(duration24);
    }

    @Test
    public void test0731() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0731");
        org.joda.time.chrono.CopticChronology copticChronology0 = org.joda.time.chrono.CopticChronology.getInstance();
        org.junit.Assert.assertNotNull(copticChronology0);
    }

    @Test
    public void test0732() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0732");
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
        org.joda.time.YearMonthDay.Property property22 = yearMonthDay21.dayOfMonth();
        org.joda.time.DateTimeField dateTimeField23 = property22.getField();
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
        org.junit.Assert.assertNotNull(property22);
        org.junit.Assert.assertNotNull(dateTimeField23);
    }

    @Test
    public void test0733() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0733");
        org.joda.time.Period period1 = org.joda.time.Period.weeks(4);
        org.junit.Assert.assertNotNull(period1);
    }

    @Test
    public void test0734() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0734");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Interval interval2 = yearMonth0.toInterval(dateTimeZone1);
        org.joda.time.YearMonth yearMonth4 = yearMonth0.plusMonths((int) 'a');
        org.joda.time.YearMonth.Property property5 = yearMonth0.year();
        java.lang.String str6 = property5.getAsText();
        java.util.Locale locale7 = null;
        int int8 = property5.getMaximumShortTextLength(locale7);
        org.joda.time.YearMonth yearMonth10 = property5.addToCopy((int) (byte) -1);
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertNotNull(yearMonth4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "2026" + "'", str6, "2026");
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 9 + "'", int8 == 9);
        org.junit.Assert.assertNotNull(yearMonth10);
    }

    @Test
    public void test0735() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0735");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Interval interval2 = yearMonth0.toInterval(dateTimeZone1);
        org.joda.time.Months months3 = org.joda.time.Months.ONE;
        org.joda.time.Interval interval4 = interval2.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) months3);
        org.joda.time.DateTime dateTime5 = interval4.getStart();
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
        org.joda.time.ReadableInterval readableInterval23 = null;
        boolean boolean24 = interval19.abuts(readableInterval23);
        long long25 = interval19.getStartMillis();
        boolean boolean26 = interval4.abuts((org.joda.time.ReadableInterval) interval19);
        org.joda.time.Chronology chronology28 = null;
        org.joda.time.DateMidnight dateMidnight29 = new org.joda.time.DateMidnight((long) (short) -1, chronology28);
        org.joda.time.DateMidnight dateMidnight31 = dateMidnight29.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight34 = dateMidnight29.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight36 = dateMidnight34.withYear(100);
        org.joda.time.Chronology chronology38 = null;
        org.joda.time.DateMidnight dateMidnight39 = new org.joda.time.DateMidnight((long) (short) -1, chronology38);
        org.joda.time.Interval interval40 = new org.joda.time.Interval((org.joda.time.ReadableInstant) dateMidnight36, (org.joda.time.ReadableInstant) dateMidnight39);
        org.joda.time.Chronology chronology42 = null;
        org.joda.time.DateMidnight dateMidnight43 = new org.joda.time.DateMidnight((long) (short) -1, chronology42);
        org.joda.time.DateMidnight dateMidnight45 = dateMidnight43.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight48 = dateMidnight43.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight50 = dateMidnight48.withYear(100);
        org.joda.time.Chronology chronology52 = null;
        org.joda.time.DateMidnight dateMidnight53 = new org.joda.time.DateMidnight((long) (short) -1, chronology52);
        org.joda.time.Interval interval54 = new org.joda.time.Interval((org.joda.time.ReadableInstant) dateMidnight50, (org.joda.time.ReadableInstant) dateMidnight53);
        org.joda.time.Period period56 = org.joda.time.Period.weeks((int) (short) 100);
        org.joda.time.Interval interval57 = interval54.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) period56);
        org.joda.time.Chronology chronology59 = null;
        org.joda.time.DateMidnight dateMidnight60 = new org.joda.time.DateMidnight((long) (short) -1, chronology59);
        org.joda.time.DateMidnight dateMidnight62 = dateMidnight60.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight65 = dateMidnight60.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight67 = dateMidnight65.withYear(100);
        org.joda.time.Chronology chronology69 = null;
        org.joda.time.DateMidnight dateMidnight70 = new org.joda.time.DateMidnight((long) (short) -1, chronology69);
        org.joda.time.Interval interval71 = new org.joda.time.Interval((org.joda.time.ReadableInstant) dateMidnight67, (org.joda.time.ReadableInstant) dateMidnight70);
        org.joda.time.Period period73 = org.joda.time.Period.weeks((int) (short) 100);
        org.joda.time.Interval interval74 = interval71.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) period73);
        org.joda.time.ReadableInterval readableInterval75 = null;
        boolean boolean76 = interval71.abuts(readableInterval75);
        long long77 = interval71.getStartMillis();
        boolean boolean78 = interval57.abuts((org.joda.time.ReadableInterval) interval71);
        org.joda.time.Interval interval79 = interval40.gap((org.joda.time.ReadableInterval) interval57);
        org.joda.time.Interval interval80 = interval19.overlap((org.joda.time.ReadableInterval) interval40);
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertNotNull(months3);
        org.junit.Assert.assertNotNull(interval4);
        org.junit.Assert.assertNotNull(dateTime5);
        org.junit.Assert.assertNotNull(dateMidnight10);
        org.junit.Assert.assertNotNull(dateMidnight13);
        org.junit.Assert.assertNotNull(dateMidnight15);
        org.junit.Assert.assertNotNull(period21);
        org.junit.Assert.assertNotNull(interval22);
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
        org.junit.Assert.assertTrue("'" + long25 + "' != '" + (-59011462664000L) + "'", long25 == (-59011462664000L));
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + false + "'", boolean26 == false);
        org.junit.Assert.assertNotNull(dateMidnight31);
        org.junit.Assert.assertNotNull(dateMidnight34);
        org.junit.Assert.assertNotNull(dateMidnight36);
        org.junit.Assert.assertNotNull(dateMidnight45);
        org.junit.Assert.assertNotNull(dateMidnight48);
        org.junit.Assert.assertNotNull(dateMidnight50);
        org.junit.Assert.assertNotNull(period56);
        org.junit.Assert.assertNotNull(interval57);
        org.junit.Assert.assertNotNull(dateMidnight62);
        org.junit.Assert.assertNotNull(dateMidnight65);
        org.junit.Assert.assertNotNull(dateMidnight67);
        org.junit.Assert.assertNotNull(period73);
        org.junit.Assert.assertNotNull(interval74);
        org.junit.Assert.assertTrue("'" + boolean76 + "' != '" + false + "'", boolean76 == false);
        org.junit.Assert.assertTrue("'" + long77 + "' != '" + (-59011462664000L) + "'", long77 == (-59011462664000L));
        org.junit.Assert.assertTrue("'" + boolean78 + "' != '" + false + "'", boolean78 == false);
        org.junit.Assert.assertNull(interval79);
        org.junit.Assert.assertNotNull(interval80);
    }

    @Test
    public void test0736() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0736");
        org.joda.time.Weeks weeks0 = org.joda.time.Weeks.MAX_VALUE;
        org.joda.time.Weeks weeks2 = weeks0.dividedBy(3);
        org.joda.time.Duration duration3 = weeks0.toStandardDuration();
        org.joda.time.Duration duration5 = duration3.minus((-262800000L));
        org.junit.Assert.assertNotNull(weeks0);
        org.junit.Assert.assertNotNull(weeks2);
        org.junit.Assert.assertNotNull(duration3);
        org.junit.Assert.assertNotNull(duration5);
    }

    @Test
    public void test0737() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0737");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTime dateTime7 = new org.joda.time.DateTime(59, 3600, 3600, 36, (int) (short) 100, (int) '4', 40260602);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 36 for hourOfDay must be in the range [0,23]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0738() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0738");
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
        org.joda.time.LocalDateTime.Property property13 = localDateTime12.weekOfWeekyear();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDateTime localDateTime15 = property13.setCopy("org.joda.time.IllegalFieldValueException: Value \"CopticChronology[2026-08-06T11:10:36.726]\" for PT60S is not supported");
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value \"org.joda.time.IllegalFieldValueException: Value \"CopticChronology[2026-08-06T11:10:36.726]\" for PT60S is not supported\" for weekOfWeekyear is not supported");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(localDateTime8);
        org.junit.Assert.assertNotNull(property9);
        org.junit.Assert.assertNotNull(dateTime10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(localDateTime12);
        org.junit.Assert.assertNotNull(property13);
    }

    @Test
    public void test0739() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0739");
        org.joda.time.TimeOfDay timeOfDay1 = new org.joda.time.TimeOfDay((long) 100);
        org.joda.time.TimeOfDay timeOfDay3 = timeOfDay1.minusMinutes((int) (short) 1);
        org.joda.time.TimeOfDay timeOfDay5 = timeOfDay1.plusMillis((int) (short) 100);
        org.joda.time.DateTimeComparator dateTimeComparator6 = org.joda.time.DateTimeComparator.getDateOnlyInstance();
        org.joda.time.DateTimeComparator dateTimeComparator7 = org.joda.time.DateTimeComparator.getTimeOnlyInstance();
        java.util.Comparator<java.lang.Object> objComparator8 = dateTimeComparator6.thenComparing((java.util.Comparator<java.lang.Object>) dateTimeComparator7);
        org.joda.time.DateTimeFieldType dateTimeFieldType9 = dateTimeComparator7.getUpperLimit();
        org.joda.time.DateTimeComparator dateTimeComparator10 = org.joda.time.DateTimeComparator.getInstance(dateTimeFieldType9);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.TimeOfDay.Property property11 = timeOfDay1.property(dateTimeFieldType9);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Field 'dayOfYear' is not supported");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(timeOfDay3);
        org.junit.Assert.assertNotNull(timeOfDay5);
        org.junit.Assert.assertNotNull(dateTimeComparator6);
        org.junit.Assert.assertNotNull(dateTimeComparator7);
        org.junit.Assert.assertNotNull(objComparator8);
        org.junit.Assert.assertNotNull(dateTimeFieldType9);
        org.junit.Assert.assertNotNull(dateTimeComparator10);
    }

    @Test
    public void test0740() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0740");
        org.joda.time.convert.ConverterManager converterManager0 = org.joda.time.convert.ConverterManager.getInstance();
        org.joda.time.convert.PartialConverter partialConverter1 = null;
        org.joda.time.convert.PartialConverter partialConverter2 = converterManager0.addPartialConverter(partialConverter1);
        org.joda.time.convert.PartialConverter partialConverter3 = null;
        org.joda.time.convert.PartialConverter partialConverter4 = converterManager0.addPartialConverter(partialConverter3);
        org.joda.time.convert.InstantConverter[] instantConverterArray5 = converterManager0.getInstantConverters();
        org.joda.time.convert.PartialConverter[] partialConverterArray6 = converterManager0.getPartialConverters();
        org.junit.Assert.assertNotNull(converterManager0);
        org.junit.Assert.assertNull(partialConverter2);
        org.junit.Assert.assertNull(partialConverter4);
        org.junit.Assert.assertNotNull(instantConverterArray5);
        org.junit.Assert.assertNotNull(partialConverterArray6);
    }

    @Test
    public void test0741() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0741");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTime.Property property1 = dateTime0.millisOfDay();
        org.joda.time.DateTime dateTime3 = property1.setCopy(40237996);
        org.joda.time.PeriodType periodType5 = null;
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.Period period7 = new org.joda.time.Period((long) (short) 10, periodType5, chronology6);
        org.joda.time.DateTime dateTime9 = dateTime3.withPeriodAdded((org.joda.time.ReadablePeriod) period7, 0);
        org.junit.Assert.assertNotNull(property1);
        org.junit.Assert.assertNotNull(dateTime3);
        org.junit.Assert.assertNotNull(dateTime9);
    }

    @Test
    public void test0742() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0742");
        int int0 = org.joda.time.TimeOfDay.HOUR_OF_DAY;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 0 + "'", int0 == 0);
    }

    @Test
    public void test0743() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0743");
        org.joda.time.Period period8 = new org.joda.time.Period(40260602, 1440, (int) '4', (int) (short) -1, (int) '4', (int) (short) 1, 51, (int) (short) -1);
    }

    @Test
    public void test0744() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0744");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        mutableDateTime6.addMinutes(1);
        // The following exception was thrown during execution in test generation
        try {
            mutableDateTime6.setDayOfWeek(29);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 29 for dayOfWeek must be in the range [1,7]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
    }

    @Test
    public void test0745() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0745");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.Months months4 = org.joda.time.Months.FIVE;
        org.joda.time.Period period5 = period3.minus((org.joda.time.ReadablePeriod) months4);
        org.joda.time.Period period7 = period3.plusMinutes((int) ' ');
        org.joda.time.DurationFieldType[] durationFieldTypeArray8 = period3.getFieldTypes();
        org.joda.time.Period period10 = period3.minusDays((int) (short) 100);
        boolean boolean12 = period3.equals((java.lang.Object) "org.joda.time.IllegalFieldValueException: Value \"hi!\" for 2026-08-06T11:10:39.499 is not supported");
        org.junit.Assert.assertNotNull(months4);
        org.junit.Assert.assertNotNull(period5);
        org.junit.Assert.assertNotNull(period7);
        org.junit.Assert.assertNotNull(durationFieldTypeArray8);
        org.junit.Assert.assertNotNull(period10);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
    }

    @Test
    public void test0746() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0746");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime9 = property7.addWrapField((int) (short) -1);
        org.joda.time.MutableDateTime.Property property10 = mutableDateTime9.monthOfYear();
        org.joda.time.Duration duration12 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Duration duration14 = org.joda.time.Duration.standardDays((long) (short) 1);
        org.joda.time.Duration duration16 = duration12.withDurationAdded((org.joda.time.ReadableDuration) duration14, (int) (byte) -1);
        org.joda.time.MutableInterval mutableInterval17 = new org.joda.time.MutableInterval((org.joda.time.ReadableInstant) mutableDateTime9, (org.joda.time.ReadableDuration) duration14);
        mutableInterval17.setInterval((long) '4', 2700000L);
        org.joda.time.Chronology chronology22 = null;
        org.joda.time.DateMidnight dateMidnight23 = new org.joda.time.DateMidnight((long) (short) -1, chronology22);
        org.joda.time.DateMidnight dateMidnight25 = dateMidnight23.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property26 = dateMidnight23.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime27 = dateMidnight23.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property28 = mutableDateTime27.centuryOfEra();
        mutableDateTime27.setSecondOfMinute(24);
        org.joda.time.DateTime dateTime31 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone32 = null;
        org.joda.time.DateTime dateTime33 = dateTime31.withZoneRetainFields(dateTimeZone32);
        boolean boolean34 = mutableDateTime27.isAfter((org.joda.time.ReadableInstant) dateTime31);
        mutableInterval17.setEnd((org.joda.time.ReadableInstant) dateTime31);
        org.joda.time.MutableDateTime mutableDateTime36 = dateTime31.toMutableDateTime();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(mutableDateTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(duration12);
        org.junit.Assert.assertNotNull(duration14);
        org.junit.Assert.assertNotNull(duration16);
        org.junit.Assert.assertNotNull(dateMidnight25);
        org.junit.Assert.assertNotNull(property26);
        org.junit.Assert.assertNotNull(mutableDateTime27);
        org.junit.Assert.assertNotNull(property28);
        org.junit.Assert.assertNotNull(dateTime33);
        org.junit.Assert.assertTrue("'" + boolean34 + "' != '" + false + "'", boolean34 == false);
        org.junit.Assert.assertNotNull(mutableDateTime36);
    }

    @Test
    public void test0747() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0747");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Weeks weeks1 = org.joda.time.Weeks.parseWeeks("1961-09-01T00:00:00.000+01:00");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid format: \"1961-09-01T00:00:00.000+01:00\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0748() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0748");
        org.joda.time.LocalDate localDate0 = org.joda.time.LocalDate.now();
        org.junit.Assert.assertNotNull(localDate0);
    }

    @Test
    public void test0749() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0749");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.timeNoMillis();
        org.joda.time.format.DateTimeFormatter dateTimeFormatter2 = dateTimeFormatter0.withDefaultYear((int) 'a');
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
        org.junit.Assert.assertNotNull(dateTimeFormatter2);
    }

    @Test
    public void test0750() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0750");
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
        org.joda.time.Partial partial11 = new org.joda.time.Partial(dateTimeFieldType9, 9);
        org.joda.time.chrono.GJChronology gJChronology13 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology14 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology13);
        org.joda.time.chrono.LenientChronology lenientChronology15 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology13);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Partial partial16 = new org.joda.time.Partial(dateTimeFieldType9, 3600, (org.joda.time.Chronology) gJChronology13);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 3600 for yearOfCentury must not be larger than 100");
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
        org.junit.Assert.assertNotNull(dateTimeFieldType9);
        org.junit.Assert.assertNotNull(gJChronology13);
        org.junit.Assert.assertNotNull(lenientChronology14);
        org.junit.Assert.assertNotNull(lenientChronology15);
    }

    @Test
    public void test0751() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0751");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        int int10 = dateMidnight4.compareTo((org.joda.time.ReadableInstant) dateMidnight7);
        org.joda.time.MutableDateTime mutableDateTime11 = dateMidnight7.toMutableDateTime();
        org.joda.time.PeriodType periodType13 = null;
        org.joda.time.Chronology chronology14 = null;
        org.joda.time.Period period15 = new org.joda.time.Period((long) (short) 10, periodType13, chronology14);
        org.joda.time.Months months16 = org.joda.time.Months.FIVE;
        org.joda.time.Period period17 = period15.minus((org.joda.time.ReadablePeriod) months16);
        org.joda.time.Period period19 = period15.plusMinutes((int) ' ');
        org.joda.time.DurationFieldType[] durationFieldTypeArray20 = period15.getFieldTypes();
        org.joda.time.Period period22 = period15.minusDays((int) (short) 100);
        org.joda.time.DateMidnight dateMidnight23 = dateMidnight7.minus((org.joda.time.ReadablePeriod) period22);
        org.joda.time.DateMidnight dateMidnight25 = dateMidnight7.withYearOfCentury(13);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + (-1) + "'", int10 == (-1));
        org.junit.Assert.assertNotNull(mutableDateTime11);
        org.junit.Assert.assertNotNull(months16);
        org.junit.Assert.assertNotNull(period17);
        org.junit.Assert.assertNotNull(period19);
        org.junit.Assert.assertNotNull(durationFieldTypeArray20);
        org.junit.Assert.assertNotNull(period22);
        org.junit.Assert.assertNotNull(dateMidnight23);
        org.junit.Assert.assertNotNull(dateMidnight25);
    }

    @Test
    public void test0752() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0752");
        org.joda.time.convert.ConverterManager converterManager0 = org.joda.time.convert.ConverterManager.getInstance();
        org.joda.time.convert.PartialConverter partialConverter1 = null;
        org.joda.time.convert.PartialConverter partialConverter2 = converterManager0.addPartialConverter(partialConverter1);
        org.joda.time.convert.PartialConverter partialConverter3 = null;
        org.joda.time.convert.PartialConverter partialConverter4 = converterManager0.addPartialConverter(partialConverter3);
        org.joda.time.convert.InstantConverter instantConverter5 = null;
        org.joda.time.convert.InstantConverter instantConverter6 = converterManager0.addInstantConverter(instantConverter5);
        org.joda.time.convert.InstantConverter[] instantConverterArray7 = converterManager0.getInstantConverters();
        org.junit.Assert.assertNotNull(converterManager0);
        org.junit.Assert.assertNull(partialConverter2);
        org.junit.Assert.assertNull(partialConverter4);
        org.junit.Assert.assertNull(instantConverter6);
        org.junit.Assert.assertNotNull(instantConverterArray7);
    }

    @Test
    public void test0753() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0753");
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
        org.joda.time.Minutes minutes17 = org.joda.time.Minutes.MAX_VALUE;
        org.joda.time.Minutes minutes18 = org.joda.time.Minutes.MAX_VALUE;
        org.joda.time.Minutes minutes19 = minutes17.minus(minutes18);
        org.joda.time.Minutes minutes20 = org.joda.time.Minutes.MAX_VALUE;
        org.joda.time.Minutes minutes21 = org.joda.time.Minutes.MAX_VALUE;
        org.joda.time.Minutes minutes22 = minutes20.minus(minutes21);
        org.joda.time.Minutes minutes23 = minutes19.plus(minutes22);
        org.joda.time.Minutes minutes24 = minutes16.minus(minutes19);
        org.joda.time.Minutes minutes25 = minutes16.negated();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(dateMidnight14);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + (-1) + "'", int15 == (-1));
        org.junit.Assert.assertNotNull(minutes16);
        org.junit.Assert.assertNotNull(minutes17);
        org.junit.Assert.assertNotNull(minutes18);
        org.junit.Assert.assertNotNull(minutes19);
        org.junit.Assert.assertNotNull(minutes20);
        org.junit.Assert.assertNotNull(minutes21);
        org.junit.Assert.assertNotNull(minutes22);
        org.junit.Assert.assertNotNull(minutes23);
        org.junit.Assert.assertNotNull(minutes24);
        org.junit.Assert.assertNotNull(minutes25);
    }

    @Test
    public void test0754() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0754");
        org.joda.time.PeriodType periodType0 = org.joda.time.PeriodType.yearMonthDayTime();
        org.junit.Assert.assertNotNull(periodType0);
    }

    @Test
    public void test0755() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0755");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTime.Property property1 = dateTime0.millisOfDay();
        org.joda.time.DateTime dateTime2 = property1.roundHalfFloorCopy();
        boolean boolean3 = dateTime2.isAfterNow();
        org.joda.time.chrono.JulianChronology julianChronology5 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate6 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology5);
        org.joda.time.LocalDate.Property property7 = localDate6.yearOfCentury();
        org.joda.time.LocalDate localDate9 = localDate6.withWeekyear((int) (byte) 100);
        org.joda.time.Chronology chronology11 = null;
        org.joda.time.DateMidnight dateMidnight12 = new org.joda.time.DateMidnight((long) (short) -1, chronology11);
        org.joda.time.DateMidnight dateMidnight14 = dateMidnight12.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property15 = dateMidnight12.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime16 = dateMidnight12.toMutableDateTimeISO();
        boolean boolean17 = localDate9.equals((java.lang.Object) mutableDateTime16);
        org.joda.time.LocalDate localDate19 = localDate9.plusMonths(10);
        int int20 = localDate9.getWeekOfWeekyear();
        org.joda.time.chrono.JulianChronology julianChronology22 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate23 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology22);
        org.joda.time.LocalDate.Property property24 = localDate23.yearOfCentury();
        org.joda.time.LocalDate localDate26 = localDate23.withWeekyear((int) (byte) 100);
        org.joda.time.Chronology chronology28 = null;
        org.joda.time.DateMidnight dateMidnight29 = new org.joda.time.DateMidnight((long) (short) -1, chronology28);
        org.joda.time.DateMidnight dateMidnight31 = dateMidnight29.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property32 = dateMidnight29.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime33 = dateMidnight29.toMutableDateTimeISO();
        boolean boolean34 = localDate26.equals((java.lang.Object) mutableDateTime33);
        org.joda.time.LocalDate localDate36 = localDate26.plusMonths(10);
        boolean boolean37 = localDate9.isEqual((org.joda.time.ReadablePartial) localDate26);
        org.joda.time.DateTime dateTime38 = dateTime2.withDate(localDate26);
        org.junit.Assert.assertNotNull(property1);
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(julianChronology5);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(localDate9);
        org.junit.Assert.assertNotNull(dateMidnight14);
        org.junit.Assert.assertNotNull(property15);
        org.junit.Assert.assertNotNull(mutableDateTime16);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(localDate19);
        org.junit.Assert.assertTrue("'" + int20 + "' != '" + 51 + "'", int20 == 51);
        org.junit.Assert.assertNotNull(julianChronology22);
        org.junit.Assert.assertNotNull(property24);
        org.junit.Assert.assertNotNull(localDate26);
        org.junit.Assert.assertNotNull(dateMidnight31);
        org.junit.Assert.assertNotNull(property32);
        org.junit.Assert.assertNotNull(mutableDateTime33);
        org.junit.Assert.assertTrue("'" + boolean34 + "' != '" + false + "'", boolean34 == false);
        org.junit.Assert.assertNotNull(localDate36);
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + true + "'", boolean37 == true);
        org.junit.Assert.assertNotNull(dateTime38);
    }

    @Test
    public void test0756() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0756");
        org.joda.time.Days days1 = org.joda.time.Days.days(40256602);
        org.joda.time.Days days2 = org.joda.time.Days.ONE;
        org.joda.time.DurationFieldType durationFieldType3 = days2.getFieldType();
        org.joda.time.PeriodType periodType4 = days2.getPeriodType();
        org.joda.time.PeriodType periodType5 = days2.getPeriodType();
        org.joda.time.Days days6 = days1.plus(days2);
        org.joda.time.PeriodType periodType7 = days1.getPeriodType();
        org.junit.Assert.assertNotNull(days1);
        org.junit.Assert.assertNotNull(days2);
        org.junit.Assert.assertNotNull(durationFieldType3);
        org.junit.Assert.assertNotNull(periodType4);
        org.junit.Assert.assertNotNull(periodType5);
        org.junit.Assert.assertNotNull(days6);
        org.junit.Assert.assertNotNull(periodType7);
    }

    @Test
    public void test0757() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0757");
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder0 = new org.joda.time.format.PeriodFormatterBuilder();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder3 = periodFormatterBuilder0.appendPrefix("PeriodType[Millis]", "");
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder5 = periodFormatterBuilder3.minimumPrintedDigits((int) (short) -1);
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder6 = periodFormatterBuilder5.appendMinutes();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder7 = periodFormatterBuilder5.printZeroRarelyLast();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder9 = periodFormatterBuilder5.appendSeparatorIfFieldsAfter("GregorianChronology[Europe/Prague]");
        org.junit.Assert.assertNotNull(periodFormatterBuilder3);
        org.junit.Assert.assertNotNull(periodFormatterBuilder5);
        org.junit.Assert.assertNotNull(periodFormatterBuilder6);
        org.junit.Assert.assertNotNull(periodFormatterBuilder7);
        org.junit.Assert.assertNotNull(periodFormatterBuilder9);
    }

    @Test
    public void test0758() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0758");
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
        org.joda.time.Period period24 = org.joda.time.Period.weeks((int) (short) 100);
        int int25 = period24.getDays();
        org.joda.time.Period period27 = period24.minusWeeks(100);
        mutableInterval22.setPeriodBeforeEnd((org.joda.time.ReadablePeriod) period24);
        org.joda.time.Chronology chronology30 = null;
        org.joda.time.DateMidnight dateMidnight31 = new org.joda.time.DateMidnight((long) (short) -1, chronology30);
        org.joda.time.DateMidnight dateMidnight33 = dateMidnight31.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight36 = dateMidnight31.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight38 = dateMidnight36.withYear(100);
        org.joda.time.Chronology chronology40 = null;
        org.joda.time.DateMidnight dateMidnight41 = new org.joda.time.DateMidnight((long) (short) -1, chronology40);
        org.joda.time.Interval interval42 = new org.joda.time.Interval((org.joda.time.ReadableInstant) dateMidnight38, (org.joda.time.ReadableInstant) dateMidnight41);
        org.joda.time.Chronology chronology44 = null;
        org.joda.time.DateMidnight dateMidnight45 = new org.joda.time.DateMidnight((long) (short) -1, chronology44);
        org.joda.time.DateMidnight dateMidnight47 = dateMidnight45.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight50 = dateMidnight45.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight52 = dateMidnight50.withYear(100);
        org.joda.time.Chronology chronology54 = null;
        org.joda.time.DateMidnight dateMidnight55 = new org.joda.time.DateMidnight((long) (short) -1, chronology54);
        org.joda.time.Interval interval56 = new org.joda.time.Interval((org.joda.time.ReadableInstant) dateMidnight52, (org.joda.time.ReadableInstant) dateMidnight55);
        org.joda.time.Period period58 = org.joda.time.Period.weeks((int) (short) 100);
        org.joda.time.Interval interval59 = interval56.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) period58);
        org.joda.time.Chronology chronology61 = null;
        org.joda.time.DateMidnight dateMidnight62 = new org.joda.time.DateMidnight((long) (short) -1, chronology61);
        org.joda.time.DateMidnight dateMidnight64 = dateMidnight62.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight67 = dateMidnight62.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight69 = dateMidnight67.withYear(100);
        org.joda.time.Chronology chronology71 = null;
        org.joda.time.DateMidnight dateMidnight72 = new org.joda.time.DateMidnight((long) (short) -1, chronology71);
        org.joda.time.Interval interval73 = new org.joda.time.Interval((org.joda.time.ReadableInstant) dateMidnight69, (org.joda.time.ReadableInstant) dateMidnight72);
        org.joda.time.Period period75 = org.joda.time.Period.weeks((int) (short) 100);
        org.joda.time.Interval interval76 = interval73.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) period75);
        org.joda.time.ReadableInterval readableInterval77 = null;
        boolean boolean78 = interval73.abuts(readableInterval77);
        long long79 = interval73.getStartMillis();
        boolean boolean80 = interval59.abuts((org.joda.time.ReadableInterval) interval73);
        org.joda.time.Interval interval81 = interval42.gap((org.joda.time.ReadableInterval) interval59);
        boolean boolean82 = mutableInterval22.isAfter((org.joda.time.ReadableInterval) interval59);
        org.joda.time.MutableInterval mutableInterval83 = mutableInterval22.copy();
        org.junit.Assert.assertNotNull(duration1);
        org.junit.Assert.assertNotNull(dateMidnight6);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(dateMidnight16);
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + (-1) + "'", int17 == (-1));
        org.junit.Assert.assertNotNull(minutes18);
        org.junit.Assert.assertNotNull(dateMidnight21);
        org.junit.Assert.assertNotNull(period24);
        org.junit.Assert.assertTrue("'" + int25 + "' != '" + 0 + "'", int25 == 0);
        org.junit.Assert.assertNotNull(period27);
        org.junit.Assert.assertNotNull(dateMidnight33);
        org.junit.Assert.assertNotNull(dateMidnight36);
        org.junit.Assert.assertNotNull(dateMidnight38);
        org.junit.Assert.assertNotNull(dateMidnight47);
        org.junit.Assert.assertNotNull(dateMidnight50);
        org.junit.Assert.assertNotNull(dateMidnight52);
        org.junit.Assert.assertNotNull(period58);
        org.junit.Assert.assertNotNull(interval59);
        org.junit.Assert.assertNotNull(dateMidnight64);
        org.junit.Assert.assertNotNull(dateMidnight67);
        org.junit.Assert.assertNotNull(dateMidnight69);
        org.junit.Assert.assertNotNull(period75);
        org.junit.Assert.assertNotNull(interval76);
        org.junit.Assert.assertTrue("'" + boolean78 + "' != '" + false + "'", boolean78 == false);
        org.junit.Assert.assertTrue("'" + long79 + "' != '" + (-59011462664000L) + "'", long79 == (-59011462664000L));
        org.junit.Assert.assertTrue("'" + boolean80 + "' != '" + false + "'", boolean80 == false);
        org.junit.Assert.assertNull(interval81);
        org.junit.Assert.assertTrue("'" + boolean82 + "' != '" + false + "'", boolean82 == false);
        org.junit.Assert.assertNotNull(mutableInterval83);
    }

    @Test
    public void test0759() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0759");
        int int0 = org.joda.time.DateTimeConstants.DAYS_PER_WEEK;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 7 + "'", int0 == 7);
    }

    @Test
    public void test0760() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0760");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone11 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology12 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone11);
        java.lang.String str13 = copticChronology12.toString();
        org.joda.time.DateTimeField dateTimeField14 = copticChronology12.clockhourOfDay();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone20 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology21 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone20);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType22 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology24 = null;
        org.joda.time.DateMidnight dateMidnight25 = new org.joda.time.DateMidnight((long) (short) -1, chronology24);
        org.joda.time.DateMidnight dateMidnight27 = dateMidnight25.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property28 = dateMidnight25.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime29 = dateMidnight25.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property30 = mutableDateTime29.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime32 = property30.addWrapField((int) (short) -1);
        boolean boolean33 = leapYearPatternType22.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology34 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone20, leapYearPatternType22);
        org.joda.time.MutableDateTime mutableDateTime35 = new org.joda.time.MutableDateTime((long) (byte) 10, (org.joda.time.DateTimeZone) fixedDateTimeZone20);
        boolean boolean36 = copticChronology12.equals((java.lang.Object) mutableDateTime35);
        org.joda.time.DurationField durationField37 = copticChronology12.halfdays();
        org.joda.time.DateTimeZone dateTimeZone38 = null;
        org.joda.time.LocalDateTime localDateTime39 = new org.joda.time.LocalDateTime(dateTimeZone38);
        int int40 = localDateTime39.getYear();
        org.joda.time.LocalDateTime localDateTime42 = localDateTime39.plusMillis(10);
        org.joda.time.chrono.GJChronology gJChronology43 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj44 = null;
        boolean boolean45 = gJChronology43.equals(obj44);
        org.joda.time.DurationField durationField46 = gJChronology43.days();
        org.joda.time.chrono.GJChronology gJChronology47 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology48 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology47);
        org.joda.time.chrono.LenientChronology lenientChronology49 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology47);
        org.joda.time.DateTimeField dateTimeField50 = lenientChronology49.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField51 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology43, dateTimeField50);
        org.joda.time.DateTimeFieldType dateTimeFieldType52 = skipUndoDateTimeField51.getType();
        int int53 = skipUndoDateTimeField51.getMinimumValue();
        java.lang.String str54 = skipUndoDateTimeField51.getName();
        org.joda.time.DateTimeFieldType dateTimeFieldType55 = skipUndoDateTimeField51.getType();
        org.joda.time.LocalDateTime localDateTime57 = localDateTime39.withField(dateTimeFieldType55, 24);
        org.joda.time.field.RemainderDateTimeField remainderDateTimeField59 = new org.joda.time.field.RemainderDateTimeField(dateTimeField4, durationField37, dateTimeFieldType55, (int) (byte) 10);
        org.joda.time.chrono.GJChronology gJChronology60 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj61 = null;
        boolean boolean62 = gJChronology60.equals(obj61);
        org.joda.time.DurationField durationField63 = gJChronology60.days();
        org.joda.time.chrono.GJChronology gJChronology64 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology65 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology64);
        org.joda.time.chrono.LenientChronology lenientChronology66 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology64);
        org.joda.time.DateTimeField dateTimeField67 = lenientChronology66.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField68 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology60, dateTimeField67);
        org.joda.time.DateTimeFieldType dateTimeFieldType69 = skipUndoDateTimeField68.getType();
        org.joda.time.field.DividedDateTimeField dividedDateTimeField70 = new org.joda.time.field.DividedDateTimeField(remainderDateTimeField59, dateTimeFieldType69);
        boolean boolean72 = remainderDateTimeField59.isLeap((long) 0);
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertNotNull(copticChronology12);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "CopticChronology[2026-08-06T11:10:36.726]" + "'", str13, "CopticChronology[2026-08-06T11:10:36.726]");
        org.junit.Assert.assertNotNull(dateTimeField14);
        org.junit.Assert.assertNotNull(copticChronology21);
        org.junit.Assert.assertNotNull(leapYearPatternType22);
        org.junit.Assert.assertNotNull(dateMidnight27);
        org.junit.Assert.assertNotNull(property28);
        org.junit.Assert.assertNotNull(mutableDateTime29);
        org.junit.Assert.assertNotNull(property30);
        org.junit.Assert.assertNotNull(mutableDateTime32);
        org.junit.Assert.assertTrue("'" + boolean33 + "' != '" + false + "'", boolean33 == false);
        org.junit.Assert.assertNotNull(islamicChronology34);
        org.junit.Assert.assertTrue("'" + boolean36 + "' != '" + false + "'", boolean36 == false);
        org.junit.Assert.assertNotNull(durationField37);
        org.junit.Assert.assertTrue("'" + int40 + "' != '" + 2026 + "'", int40 == 2026);
        org.junit.Assert.assertNotNull(localDateTime42);
        org.junit.Assert.assertNotNull(gJChronology43);
        org.junit.Assert.assertTrue("'" + boolean45 + "' != '" + false + "'", boolean45 == false);
        org.junit.Assert.assertNotNull(durationField46);
        org.junit.Assert.assertNotNull(gJChronology47);
        org.junit.Assert.assertNotNull(lenientChronology48);
        org.junit.Assert.assertNotNull(lenientChronology49);
        org.junit.Assert.assertNotNull(dateTimeField50);
        org.junit.Assert.assertNotNull(dateTimeFieldType52);
        org.junit.Assert.assertTrue("'" + int53 + "' != '" + 0 + "'", int53 == 0);
        org.junit.Assert.assertEquals("'" + str54 + "' != '" + "yearOfCentury" + "'", str54, "yearOfCentury");
        org.junit.Assert.assertNotNull(dateTimeFieldType55);
        org.junit.Assert.assertNotNull(localDateTime57);
        org.junit.Assert.assertNotNull(gJChronology60);
        org.junit.Assert.assertTrue("'" + boolean62 + "' != '" + false + "'", boolean62 == false);
        org.junit.Assert.assertNotNull(durationField63);
        org.junit.Assert.assertNotNull(gJChronology64);
        org.junit.Assert.assertNotNull(lenientChronology65);
        org.junit.Assert.assertNotNull(lenientChronology66);
        org.junit.Assert.assertNotNull(dateTimeField67);
        org.junit.Assert.assertNotNull(dateTimeFieldType69);
        org.junit.Assert.assertTrue("'" + boolean72 + "' != '" + false + "'", boolean72 == false);
    }

    @Test
    public void test0761() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0761");
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
        int int15 = dateMidnight2.getCenturyOfEra();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(interval8);
        org.junit.Assert.assertNotNull(months9);
        org.junit.Assert.assertNotNull(interval10);
        org.junit.Assert.assertNotNull(dateTime11);
        org.junit.Assert.assertNotNull(dateTime13);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 19 + "'", int15 == 19);
    }

    @Test
    public void test0762() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0762");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime9 = property7.addWrapField((int) (short) -1);
        org.joda.time.MutableDateTime.Property property10 = mutableDateTime9.monthOfYear();
        org.joda.time.Duration duration12 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Duration duration14 = org.joda.time.Duration.standardDays((long) (short) 1);
        org.joda.time.Duration duration16 = duration12.withDurationAdded((org.joda.time.ReadableDuration) duration14, (int) (byte) -1);
        org.joda.time.MutableInterval mutableInterval17 = new org.joda.time.MutableInterval((org.joda.time.ReadableInstant) mutableDateTime9, (org.joda.time.ReadableDuration) duration14);
        mutableDateTime9.addWeekyears((int) (byte) 0);
        org.joda.time.Chronology chronology21 = null;
        org.joda.time.DateMidnight dateMidnight22 = new org.joda.time.DateMidnight((long) (short) -1, chronology21);
        org.joda.time.DateMidnight dateMidnight24 = dateMidnight22.minusYears(100);
        org.joda.time.DateMidnight.Property property25 = dateMidnight22.monthOfYear();
        org.joda.time.ReadablePeriod readablePeriod26 = null;
        org.joda.time.DateMidnight dateMidnight27 = dateMidnight22.plus(readablePeriod26);
        org.joda.time.Chronology chronology28 = org.joda.time.DateTimeUtils.getIntervalChronology((org.joda.time.ReadableInstant) mutableDateTime9, (org.joda.time.ReadableInstant) dateMidnight27);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(mutableDateTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(duration12);
        org.junit.Assert.assertNotNull(duration14);
        org.junit.Assert.assertNotNull(duration16);
        org.junit.Assert.assertNotNull(dateMidnight24);
        org.junit.Assert.assertNotNull(property25);
        org.junit.Assert.assertNotNull(dateMidnight27);
        org.junit.Assert.assertNotNull(chronology28);
    }

    @Test
    public void test0763() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0763");
        org.joda.time.tz.DateTimeZoneBuilder dateTimeZoneBuilder0 = new org.joda.time.tz.DateTimeZoneBuilder();
        org.joda.time.tz.DateTimeZoneBuilder dateTimeZoneBuilder2 = dateTimeZoneBuilder0.setStandardOffset(40243947);
        java.io.OutputStream outputStream4 = null;
        // The following exception was thrown during execution in test generation
        try {
            dateTimeZoneBuilder0.writeTo("Jan", outputStream4);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeZoneBuilder2);
    }

    @Test
    public void test0764() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0764");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Interval interval2 = yearMonth0.toInterval(dateTimeZone1);
        org.joda.time.Months months3 = org.joda.time.Months.ONE;
        org.joda.time.Interval interval4 = interval2.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) months3);
        org.joda.time.Months months6 = months3.plus(40243947);
        org.joda.time.Months months8 = months3.dividedBy(2);
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertNotNull(months3);
        org.junit.Assert.assertNotNull(interval4);
        org.junit.Assert.assertNotNull(months6);
        org.junit.Assert.assertNotNull(months8);
    }

    @Test
    public void test0765() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0765");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime2.plusHours(0);
        org.joda.time.LocalDateTime localDateTime5 = dateTime2.toLocalDateTime();
        org.joda.time.DateTimeComparator dateTimeComparator6 = org.joda.time.DateTimeComparator.getDateOnlyInstance();
        org.joda.time.DateTimeComparator dateTimeComparator7 = org.joda.time.DateTimeComparator.getTimeOnlyInstance();
        java.util.Comparator<java.lang.Object> objComparator8 = dateTimeComparator6.thenComparing((java.util.Comparator<java.lang.Object>) dateTimeComparator7);
        org.joda.time.DateTimeFieldType dateTimeFieldType9 = dateTimeComparator7.getUpperLimit();
        org.joda.time.LocalDateTime.Property property10 = localDateTime5.property(dateTimeFieldType9);
        org.joda.time.LocalDateTime.Property property11 = localDateTime5.weekyear();
        org.joda.time.LocalDateTime localDateTime13 = localDateTime5.minusDays(0);
        org.joda.time.LocalDateTime localDateTime15 = localDateTime5.withYearOfEra(9);
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(localDateTime5);
        org.junit.Assert.assertNotNull(dateTimeComparator6);
        org.junit.Assert.assertNotNull(dateTimeComparator7);
        org.junit.Assert.assertNotNull(objComparator8);
        org.junit.Assert.assertNotNull(dateTimeFieldType9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(localDateTime13);
        org.junit.Assert.assertNotNull(localDateTime15);
    }

    @Test
    public void test0766() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0766");
        org.joda.time.PeriodType periodType1 = org.joda.time.PeriodType.millis();
        org.joda.time.PeriodType periodType2 = periodType1.withWeeksRemoved();
        org.joda.time.MutablePeriod mutablePeriod3 = new org.joda.time.MutablePeriod((long) (byte) 10, periodType2);
        mutablePeriod3.addWeeks(0);
        mutablePeriod3.setPeriod(0L);
        org.junit.Assert.assertNotNull(periodType1);
        org.junit.Assert.assertNotNull(periodType2);
    }

    @Test
    public void test0767() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0767");
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder0 = new org.joda.time.format.PeriodFormatterBuilder();
        periodFormatterBuilder0.clear();
        org.joda.time.format.PeriodFormatter periodFormatter2 = org.joda.time.format.ISOPeriodFormat.alternateWithWeeks();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder3 = periodFormatterBuilder0.append(periodFormatter2);
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder4 = periodFormatterBuilder3.appendYears();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder7 = periodFormatterBuilder4.appendSuffix("2029-05-02T11:10:53.327", "40256602");
        org.junit.Assert.assertNotNull(periodFormatter2);
        org.junit.Assert.assertNotNull(periodFormatterBuilder3);
        org.junit.Assert.assertNotNull(periodFormatterBuilder4);
        org.junit.Assert.assertNotNull(periodFormatterBuilder7);
    }

    @Test
    public void test0768() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0768");
        boolean boolean0 = org.joda.time.tz.ZoneInfoLogger.verbose();
        org.junit.Assert.assertTrue("'" + boolean0 + "' != '" + true + "'", boolean0 == true);
    }

    @Test
    public void test0769() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0769");
        org.joda.time.Period period1 = org.joda.time.Period.weeks((int) (short) 100);
        int int2 = period1.getYears();
        int int3 = period1.getMillis();
        org.joda.time.Period period5 = period1.plusSeconds(100);
        org.joda.time.chrono.GJChronology gJChronology7 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology8 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology7);
        org.joda.time.TimeOfDay timeOfDay9 = org.joda.time.TimeOfDay.fromMillisOfDay((long) 2026, (org.joda.time.Chronology) lenientChronology8);
        org.joda.time.Chronology chronology10 = lenientChronology8.withUTC();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateMidnight dateMidnight11 = new org.joda.time.DateMidnight((java.lang.Object) period1, chronology10);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No instant converter found for type: org.joda.time.Period");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(period1);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
        org.junit.Assert.assertNotNull(period5);
        org.junit.Assert.assertNotNull(gJChronology7);
        org.junit.Assert.assertNotNull(lenientChronology8);
        org.junit.Assert.assertNotNull(timeOfDay9);
        org.junit.Assert.assertNotNull(chronology10);
    }

    @Test
    public void test0770() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0770");
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
        org.joda.time.YearMonthDay.Property property22 = yearMonthDay21.dayOfMonth();
        org.joda.time.YearMonthDay yearMonthDay23 = property22.getYearMonthDay();
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
        org.junit.Assert.assertNotNull(property22);
        org.junit.Assert.assertNotNull(yearMonthDay23);
    }

    @Test
    public void test0771() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0771");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.weekDateTimeNoMillis();
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
    }

    @Test
    public void test0772() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0772");
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
        org.joda.time.Partial partial27 = new org.joda.time.Partial((org.joda.time.Chronology) gJChronology23);
        org.joda.time.DateTimeField dateTimeField28 = gJChronology23.minuteOfDay();
        org.joda.time.DurationField durationField29 = gJChronology23.halfdays();
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
        org.junit.Assert.assertNotNull(dateTimeField28);
        org.junit.Assert.assertNotNull(durationField29);
    }

    @Test
    public void test0773() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0773");
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder0 = new org.joda.time.format.PeriodFormatterBuilder();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder3 = periodFormatterBuilder0.appendPrefix("PeriodType[Millis]", "");
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder5 = periodFormatterBuilder3.minimumPrintedDigits((int) (short) -1);
        org.joda.time.format.PeriodPrinter periodPrinter6 = periodFormatterBuilder3.toPrinter();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder7 = periodFormatterBuilder3.appendWeeks();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder8 = periodFormatterBuilder3.printZeroNever();
        org.junit.Assert.assertNotNull(periodFormatterBuilder3);
        org.junit.Assert.assertNotNull(periodFormatterBuilder5);
        org.junit.Assert.assertNotNull(periodPrinter6);
        org.junit.Assert.assertNotNull(periodFormatterBuilder7);
        org.junit.Assert.assertNotNull(periodFormatterBuilder8);
    }

    @Test
    public void test0774() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0774");
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
        org.joda.time.Instant instant32 = org.joda.time.Instant.EPOCH;
        org.joda.time.chrono.GJChronology gJChronology33 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj34 = null;
        boolean boolean35 = gJChronology33.equals(obj34);
        org.joda.time.DurationField durationField36 = gJChronology33.days();
        org.joda.time.chrono.GJChronology gJChronology37 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology38 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology37);
        org.joda.time.chrono.LenientChronology lenientChronology39 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology37);
        org.joda.time.DateTimeField dateTimeField40 = lenientChronology39.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField41 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology33, dateTimeField40);
        org.joda.time.DateTimeFieldType dateTimeFieldType42 = skipUndoDateTimeField41.getType();
        org.joda.time.Partial partial44 = new org.joda.time.Partial(dateTimeFieldType42, 9);
        org.joda.time.Instant instant45 = org.joda.time.Instant.now();
        org.joda.time.Instant instant47 = instant45.withMillis((long) 10);
        boolean boolean48 = partial44.isMatch((org.joda.time.ReadableInstant) instant45);
        mutablePeriod31.setPeriod((org.joda.time.ReadableInstant) instant32, (org.joda.time.ReadableInstant) instant45);
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
        org.junit.Assert.assertNotNull(instant32);
        org.junit.Assert.assertNotNull(gJChronology33);
        org.junit.Assert.assertTrue("'" + boolean35 + "' != '" + false + "'", boolean35 == false);
        org.junit.Assert.assertNotNull(durationField36);
        org.junit.Assert.assertNotNull(gJChronology37);
        org.junit.Assert.assertNotNull(lenientChronology38);
        org.junit.Assert.assertNotNull(lenientChronology39);
        org.junit.Assert.assertNotNull(dateTimeField40);
        org.junit.Assert.assertNotNull(dateTimeFieldType42);
        org.junit.Assert.assertNotNull(instant45);
        org.junit.Assert.assertNotNull(instant47);
        org.junit.Assert.assertTrue("'" + boolean48 + "' != '" + false + "'", boolean48 == false);
    }

    @Test
    public void test0775() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0775");
        org.joda.time.format.PeriodPrinter periodPrinter1 = null;
        org.joda.time.format.PeriodPrinter periodPrinter2 = null;
        org.joda.time.format.PeriodFormatter periodFormatter3 = org.joda.time.format.PeriodFormat.wordBased();
        org.joda.time.format.PeriodParser periodParser4 = periodFormatter3.getParser();
        org.joda.time.format.PeriodFormatter periodFormatter5 = new org.joda.time.format.PeriodFormatter(periodPrinter2, periodParser4);
        org.joda.time.format.PeriodFormatter periodFormatter6 = new org.joda.time.format.PeriodFormatter(periodPrinter1, periodParser4);
        org.joda.time.PeriodType periodType7 = periodFormatter6.getParseType();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Period period8 = org.joda.time.Period.parse("CopticChronology[2026-08-06T11:10:36.726]", periodFormatter6);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid format: \"CopticChronology[2026-08-06T11:1...\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(periodFormatter3);
        org.junit.Assert.assertNotNull(periodParser4);
        org.junit.Assert.assertNull(periodType7);
    }

    @Test
    public void test0776() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0776");
        org.joda.time.chrono.GJChronology gJChronology0 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology1 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology0);
        org.joda.time.DateTimeField dateTimeField2 = gJChronology0.minuteOfHour();
        org.joda.time.DateTimeField dateTimeField3 = gJChronology0.minuteOfHour();
        org.joda.time.DateTimeZone dateTimeZone4 = null;
        org.joda.time.LocalDateTime localDateTime5 = new org.joda.time.LocalDateTime(dateTimeZone4);
        int int6 = localDateTime5.getYear();
        org.joda.time.LocalDateTime localDateTime8 = localDateTime5.plusMillis(10);
        org.joda.time.chrono.GJChronology gJChronology9 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj10 = null;
        boolean boolean11 = gJChronology9.equals(obj10);
        org.joda.time.DurationField durationField12 = gJChronology9.days();
        org.joda.time.chrono.GJChronology gJChronology13 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology14 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology13);
        org.joda.time.chrono.LenientChronology lenientChronology15 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology13);
        org.joda.time.DateTimeField dateTimeField16 = lenientChronology15.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField17 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology9, dateTimeField16);
        org.joda.time.DateTimeFieldType dateTimeFieldType18 = skipUndoDateTimeField17.getType();
        int int19 = skipUndoDateTimeField17.getMinimumValue();
        java.lang.String str20 = skipUndoDateTimeField17.getName();
        org.joda.time.DateTimeFieldType dateTimeFieldType21 = skipUndoDateTimeField17.getType();
        org.joda.time.LocalDateTime localDateTime23 = localDateTime5.withField(dateTimeFieldType21, 24);
        int[] intArray25 = gJChronology0.get((org.joda.time.ReadablePartial) localDateTime5, (-1L));
        org.junit.Assert.assertNotNull(gJChronology0);
        org.junit.Assert.assertNotNull(lenientChronology1);
        org.junit.Assert.assertNotNull(dateTimeField2);
        org.junit.Assert.assertNotNull(dateTimeField3);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 2026 + "'", int6 == 2026);
        org.junit.Assert.assertNotNull(localDateTime8);
        org.junit.Assert.assertNotNull(gJChronology9);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNotNull(durationField12);
        org.junit.Assert.assertNotNull(gJChronology13);
        org.junit.Assert.assertNotNull(lenientChronology14);
        org.junit.Assert.assertNotNull(lenientChronology15);
        org.junit.Assert.assertNotNull(dateTimeField16);
        org.junit.Assert.assertNotNull(dateTimeFieldType18);
        org.junit.Assert.assertTrue("'" + int19 + "' != '" + 0 + "'", int19 == 0);
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "yearOfCentury" + "'", str20, "yearOfCentury");
        org.junit.Assert.assertNotNull(dateTimeFieldType21);
        org.junit.Assert.assertNotNull(localDateTime23);
        org.junit.Assert.assertNotNull(intArray25);
        org.junit.Assert.assertArrayEquals(intArray25, new int[] { 1970, 1, 1, 3599999 });
    }

    @Test
    public void test0777() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0777");
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
        org.joda.time.DateTimeField dateTimeField11 = org.joda.time.field.StrictDateTimeField.getInstance((org.joda.time.DateTimeField) skipUndoDateTimeField8);
        java.util.Locale locale13 = null;
        java.lang.String str14 = skipUndoDateTimeField8.getAsShortText(0, locale13);
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
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "0" + "'", str14, "0");
    }

    @Test
    public void test0778() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0778");
        org.joda.time.chrono.GJChronology gJChronology5 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology6 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology5);
        org.joda.time.DateTimeField dateTimeField7 = gJChronology5.minuteOfHour();
        org.joda.time.DateTimeField dateTimeField8 = gJChronology5.halfdayOfDay();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTime dateTime9 = new org.joda.time.DateTime((int) (byte) 100, (int) (byte) 100, 0, 29, 3600, (org.joda.time.Chronology) gJChronology5);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 29 for hourOfDay must be in the range [0,23]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gJChronology5);
        org.junit.Assert.assertNotNull(lenientChronology6);
        org.junit.Assert.assertNotNull(dateTimeField7);
        org.junit.Assert.assertNotNull(dateTimeField8);
    }

    @Test
    public void test0779() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0779");
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder0 = new org.joda.time.format.PeriodFormatterBuilder();
        periodFormatterBuilder0.clear();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder2 = periodFormatterBuilder0.printZeroRarelyFirst();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder3 = new org.joda.time.format.PeriodFormatterBuilder();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder6 = periodFormatterBuilder3.appendPrefix("PeriodType[Millis]", "");
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder8 = periodFormatterBuilder6.minimumPrintedDigits((int) (short) -1);
        org.joda.time.format.PeriodPrinter periodPrinter9 = periodFormatterBuilder6.toPrinter();
        org.joda.time.format.PeriodFormatter periodFormatter10 = org.joda.time.format.PeriodFormat.wordBased();
        org.joda.time.format.PeriodPrinter periodPrinter11 = periodFormatter10.getPrinter();
        org.joda.time.format.PeriodPrinter periodPrinter12 = null;
        org.joda.time.format.PeriodPrinter periodPrinter13 = null;
        org.joda.time.format.PeriodFormatter periodFormatter14 = org.joda.time.format.PeriodFormat.wordBased();
        org.joda.time.format.PeriodParser periodParser15 = periodFormatter14.getParser();
        org.joda.time.format.PeriodFormatter periodFormatter16 = new org.joda.time.format.PeriodFormatter(periodPrinter13, periodParser15);
        org.joda.time.format.PeriodFormatter periodFormatter17 = new org.joda.time.format.PeriodFormatter(periodPrinter12, periodParser15);
        org.joda.time.format.PeriodFormatter periodFormatter18 = new org.joda.time.format.PeriodFormatter(periodPrinter11, periodParser15);
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder19 = periodFormatterBuilder0.append(periodPrinter9, periodParser15);
        org.junit.Assert.assertNotNull(periodFormatterBuilder2);
        org.junit.Assert.assertNotNull(periodFormatterBuilder6);
        org.junit.Assert.assertNotNull(periodFormatterBuilder8);
        org.junit.Assert.assertNotNull(periodPrinter9);
        org.junit.Assert.assertNotNull(periodFormatter10);
        org.junit.Assert.assertNotNull(periodPrinter11);
        org.junit.Assert.assertNotNull(periodFormatter14);
        org.junit.Assert.assertNotNull(periodParser15);
        org.junit.Assert.assertNotNull(periodFormatterBuilder19);
    }

    @Test
    public void test0780() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0780");
        org.joda.time.Months months0 = org.joda.time.Months.ONE;
        org.joda.time.Months months2 = months0.plus(8);
        org.junit.Assert.assertNotNull(months0);
        org.junit.Assert.assertNotNull(months2);
    }

    @Test
    public void test0781() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0781");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        org.joda.time.ReadableInterval readableInterval5 = null;
        org.joda.time.Seconds seconds6 = org.joda.time.Seconds.secondsIn(readableInterval5);
        org.joda.time.DurationFieldType durationFieldType7 = seconds6.getFieldType();
        org.joda.time.LocalDateTime localDateTime9 = localDateTime1.withFieldAdded(durationFieldType7, 86400000);
        org.joda.time.LocalDateTime localDateTime11 = localDateTime1.plusYears(1970);
        int int12 = localDateTime11.getWeekyear();
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(seconds6);
        org.junit.Assert.assertNotNull(durationFieldType7);
        org.junit.Assert.assertNotNull(localDateTime9);
        org.junit.Assert.assertNotNull(localDateTime11);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 3996 + "'", int12 == 3996);
    }

    @Test
    public void test0782() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0782");
        org.joda.time.Chronology chronology0 = null;
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj2 = null;
        boolean boolean3 = gJChronology1.equals(obj2);
        org.joda.time.DurationField durationField4 = gJChronology1.days();
        org.joda.time.chrono.GJChronology gJChronology5 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology6 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology5);
        org.joda.time.chrono.LenientChronology lenientChronology7 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology5);
        org.joda.time.DateTimeField dateTimeField8 = lenientChronology7.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField9 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology1, dateTimeField8);
        long long11 = skipUndoDateTimeField9.remainder((long) 59);
        org.joda.time.field.SkipDateTimeField skipDateTimeField13 = new org.joda.time.field.SkipDateTimeField(chronology0, (org.joda.time.DateTimeField) skipUndoDateTimeField9, 40260602);
        int int15 = skipDateTimeField13.get((long) 13);
        java.util.Locale locale18 = null;
        // The following exception was thrown during execution in test generation
        try {
            long long19 = skipDateTimeField13.set((long) 168, "10 milliseconds", locale18);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value \"10 milliseconds\" for yearOfCentury is not supported");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(durationField4);
        org.junit.Assert.assertNotNull(gJChronology5);
        org.junit.Assert.assertNotNull(lenientChronology6);
        org.junit.Assert.assertNotNull(lenientChronology7);
        org.junit.Assert.assertNotNull(dateTimeField8);
        org.junit.Assert.assertTrue("'" + long11 + "' != '" + 3600059L + "'", long11 == 3600059L);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 69 + "'", int15 == 69);
    }

    @Test
    public void test0783() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0783");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.ReadableInterval readableInterval4 = null;
        org.joda.time.Seconds seconds5 = org.joda.time.Seconds.secondsIn(readableInterval4);
        org.joda.time.DurationFieldType durationFieldType6 = seconds5.getFieldType();
        int int7 = period3.get(durationFieldType6);
        org.joda.time.PeriodType periodType8 = period3.getPeriodType();
        org.joda.time.Period period9 = period3.normalizedStandard();
        int int10 = period9.getMinutes();
        org.junit.Assert.assertNotNull(seconds5);
        org.junit.Assert.assertNotNull(durationFieldType6);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertNotNull(periodType8);
        org.junit.Assert.assertNotNull(period9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
    }

    @Test
    public void test0784() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0784");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.Months months4 = org.joda.time.Months.FIVE;
        org.joda.time.Period period5 = period3.minus((org.joda.time.ReadablePeriod) months4);
        org.joda.time.Period period7 = period3.plusMillis((-1));
        org.joda.time.Period period8 = period3.toPeriod();
        org.joda.time.Period period10 = period8.minusSeconds((int) (byte) 1);
        org.joda.time.YearMonth yearMonth11 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone12 = null;
        org.joda.time.Interval interval13 = yearMonth11.toInterval(dateTimeZone12);
        org.joda.time.Months months14 = org.joda.time.Months.ONE;
        org.joda.time.Interval interval15 = interval13.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) months14);
        org.joda.time.Months months17 = months14.plus(40243947);
        org.joda.time.Months months19 = months14.plus(40243947);
        org.joda.time.Period period20 = period8.minus((org.joda.time.ReadablePeriod) months14);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DurationFieldType durationFieldType22 = period20.getFieldType(2026);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 2026");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(months4);
        org.junit.Assert.assertNotNull(period5);
        org.junit.Assert.assertNotNull(period7);
        org.junit.Assert.assertNotNull(period8);
        org.junit.Assert.assertNotNull(period10);
        org.junit.Assert.assertNotNull(interval13);
        org.junit.Assert.assertNotNull(months14);
        org.junit.Assert.assertNotNull(interval15);
        org.junit.Assert.assertNotNull(months17);
        org.junit.Assert.assertNotNull(months19);
        org.junit.Assert.assertNotNull(period20);
    }

    @Test
    public void test0785() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0785");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        org.joda.time.ReadableInterval readableInterval5 = null;
        org.joda.time.Seconds seconds6 = org.joda.time.Seconds.secondsIn(readableInterval5);
        org.joda.time.DurationFieldType durationFieldType7 = seconds6.getFieldType();
        org.joda.time.LocalDateTime localDateTime9 = localDateTime1.withFieldAdded(durationFieldType7, 86400000);
        org.joda.time.DateTimeField[] dateTimeFieldArray10 = localDateTime9.getFields();
        org.joda.time.LocalDateTime.Property property11 = localDateTime9.dayOfWeek();
        org.joda.time.DateTimeZone dateTimeZone12 = null;
        org.joda.time.LocalDateTime localDateTime13 = new org.joda.time.LocalDateTime(dateTimeZone12);
        org.joda.time.LocalDateTime localDateTime15 = localDateTime13.withDayOfYear((int) ' ');
        int int16 = localDateTime13.getMillisOfDay();
        org.joda.time.ReadableInterval readableInterval17 = null;
        org.joda.time.Seconds seconds18 = org.joda.time.Seconds.secondsIn(readableInterval17);
        org.joda.time.DurationFieldType durationFieldType19 = seconds18.getFieldType();
        org.joda.time.field.PreciseDurationField preciseDurationField21 = new org.joda.time.field.PreciseDurationField(durationFieldType19, (long) 59);
        boolean boolean22 = localDateTime13.isSupported(durationFieldType19);
        org.joda.time.LocalDateTime localDateTime24 = localDateTime9.withFieldAdded(durationFieldType19, 1970);
        org.joda.time.LocalDateTime localDateTime26 = localDateTime9.minusSeconds(12);
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(seconds6);
        org.junit.Assert.assertNotNull(durationFieldType7);
        org.junit.Assert.assertNotNull(localDateTime9);
        org.junit.Assert.assertNotNull(dateTimeFieldArray10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(localDateTime15);
// flaky "3) test0785(RegressionTest1)":         org.junit.Assert.assertTrue("'" + int16 + "' != '" + 40308915 + "'", int16 == 40308915);
        org.junit.Assert.assertNotNull(seconds18);
        org.junit.Assert.assertNotNull(durationFieldType19);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
        org.junit.Assert.assertNotNull(localDateTime24);
        org.junit.Assert.assertNotNull(localDateTime26);
    }

    @Test
    public void test0786() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0786");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone4 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology5 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        org.joda.time.DateTime dateTime6 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone7 = null;
        org.joda.time.DateTime dateTime8 = dateTime6.withZoneRetainFields(dateTimeZone7);
        org.joda.time.DateTime dateTime10 = dateTime6.withYear((int) (byte) 0);
        org.joda.time.DateTime dateTime12 = dateTime6.withWeekyear((int) (byte) 0);
        org.joda.time.DateTime dateTime14 = dateTime6.minusMonths(8);
        org.joda.time.chrono.GJChronology gJChronology15 = org.joda.time.chrono.GJChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4, (org.joda.time.ReadableInstant) dateTime6);
        org.joda.time.DateTime dateTime17 = dateTime6.withMillis((long) (short) 100);
        org.joda.time.DateTime.Property property18 = dateTime6.millisOfDay();
        org.joda.time.DateTime dateTime19 = property18.roundHalfEvenCopy();
        org.junit.Assert.assertNotNull(gregorianChronology5);
        org.junit.Assert.assertNotNull(dateTime8);
        org.junit.Assert.assertNotNull(dateTime10);
        org.junit.Assert.assertNotNull(dateTime12);
        org.junit.Assert.assertNotNull(dateTime14);
        org.junit.Assert.assertNotNull(gJChronology15);
        org.junit.Assert.assertNotNull(dateTime17);
        org.junit.Assert.assertNotNull(property18);
        org.junit.Assert.assertNotNull(dateTime19);
    }

    @Test
    public void test0787() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0787");
        org.joda.time.Period period1 = org.joda.time.Period.weeks((int) (short) 100);
        int int2 = period1.getYears();
        org.joda.time.Period period4 = period1.minusWeeks((int) (byte) 10);
        org.junit.Assert.assertNotNull(period1);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertNotNull(period4);
    }

    @Test
    public void test0788() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0788");
        org.joda.time.chrono.GregorianChronology gregorianChronology0 = org.joda.time.chrono.GregorianChronology.getInstanceUTC();
        // The following exception was thrown during execution in test generation
        try {
            long long8 = gregorianChronology0.getDateTimeMillis(13, 2000, 53, 5, 0, 3600, 24);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 3600 for secondOfMinute must be in the range [0,59]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gregorianChronology0);
    }

    @Test
    public void test0789() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0789");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.Months months4 = org.joda.time.Months.FIVE;
        org.joda.time.Period period5 = period3.minus((org.joda.time.ReadablePeriod) months4);
        org.joda.time.format.PeriodFormatter periodFormatter6 = org.joda.time.format.PeriodFormat.wordBased();
        org.joda.time.format.PeriodParser periodParser7 = periodFormatter6.getParser();
        java.lang.String str8 = period3.toString(periodFormatter6);
        org.joda.time.Period period10 = periodFormatter6.parsePeriod("");
        org.junit.Assert.assertNotNull(months4);
        org.junit.Assert.assertNotNull(period5);
        org.junit.Assert.assertNotNull(periodFormatter6);
        org.junit.Assert.assertNotNull(periodParser7);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "10 milliseconds" + "'", str8, "10 milliseconds");
        org.junit.Assert.assertNotNull(period10);
    }

    @Test
    public void test0790() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0790");
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
        org.joda.time.LocalDate localDate35 = localDate5.minusYears((int) (byte) 100);
        org.joda.time.LocalDate.Property property36 = localDate35.yearOfEra();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDate localDate38 = property36.setCopy("2029-05-02T11:10:56.733");
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value \"2029-05-02T11:10:56.733\" for yearOfEra is not supported");
        } catch (org.joda.time.IllegalFieldValueException e) {
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
        org.junit.Assert.assertNotNull(localDate35);
        org.junit.Assert.assertNotNull(property36);
    }

    @Test
    public void test0791() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0791");
        org.joda.time.PeriodType periodType0 = org.joda.time.PeriodType.weeks();
        org.joda.time.PeriodType periodType1 = periodType0.withHoursRemoved();
        org.junit.Assert.assertNotNull(periodType0);
        org.junit.Assert.assertNotNull(periodType1);
    }

    @Test
    public void test0792() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0792");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = property3.addToCopy(1);
        int int6 = localDate5.getEra();
        int int7 = localDate5.getYearOfCentury();
        org.joda.time.ReadableInterval readableInterval8 = null;
        org.joda.time.Seconds seconds9 = org.joda.time.Seconds.secondsIn(readableInterval8);
        org.joda.time.ReadableInterval readableInterval10 = null;
        org.joda.time.Seconds seconds11 = org.joda.time.Seconds.secondsIn(readableInterval10);
        org.joda.time.Seconds seconds12 = seconds9.plus(seconds11);
        org.joda.time.PeriodType periodType13 = seconds9.getPeriodType();
        org.joda.time.LocalDate localDate15 = localDate5.withPeriodAdded((org.joda.time.ReadablePeriod) seconds9, (int) 'a');
        org.joda.time.LocalDate.Property property16 = localDate5.weekyear();
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 1 + "'", int6 == 1);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 70 + "'", int7 == 70);
        org.junit.Assert.assertNotNull(seconds9);
        org.junit.Assert.assertNotNull(seconds11);
        org.junit.Assert.assertNotNull(seconds12);
        org.junit.Assert.assertNotNull(periodType13);
        org.junit.Assert.assertNotNull(localDate15);
        org.junit.Assert.assertNotNull(property16);
    }

    @Test
    public void test0793() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0793");
        org.joda.time.Years years0 = org.joda.time.Years.MIN_VALUE;
        org.junit.Assert.assertNotNull(years0);
    }

    @Test
    public void test0794() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0794");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.Months months4 = org.joda.time.Months.FIVE;
        org.joda.time.Period period5 = period3.minus((org.joda.time.ReadablePeriod) months4);
        org.joda.time.Period period7 = period3.plusMinutes((int) ' ');
        org.joda.time.DurationFieldType[] durationFieldTypeArray8 = period3.getFieldTypes();
        org.joda.time.PeriodType periodType9 = period3.getPeriodType();
        org.joda.time.Period period11 = period3.plusMonths(13);
        org.joda.time.Period period13 = period11.minusHours((int) ' ');
        org.joda.time.Period period15 = period11.plusDays(59);
        org.junit.Assert.assertNotNull(months4);
        org.junit.Assert.assertNotNull(period5);
        org.junit.Assert.assertNotNull(period7);
        org.junit.Assert.assertNotNull(durationFieldTypeArray8);
        org.junit.Assert.assertNotNull(periodType9);
        org.junit.Assert.assertNotNull(period11);
        org.junit.Assert.assertNotNull(period13);
        org.junit.Assert.assertNotNull(period15);
    }

    @Test
    public void test0795() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0795");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology2 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology1);
        org.joda.time.TimeOfDay timeOfDay3 = new org.joda.time.TimeOfDay((long) (byte) 100, (org.joda.time.Chronology) lenientChronology2);
        org.joda.time.DateTimeField dateTimeField4 = lenientChronology2.weekOfWeekyear();
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(lenientChronology2);
        org.junit.Assert.assertNotNull(dateTimeField4);
    }

    @Test
    public void test0796() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0796");
        org.joda.time.PeriodType periodType0 = org.joda.time.PeriodType.time();
        org.junit.Assert.assertNotNull(periodType0);
    }

    @Test
    public void test0797() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0797");
        org.joda.time.DateTimeComparator dateTimeComparator0 = org.joda.time.DateTimeComparator.getDateOnlyInstance();
        org.joda.time.DateTimeComparator dateTimeComparator1 = org.joda.time.DateTimeComparator.getTimeOnlyInstance();
        java.util.Comparator<java.lang.Object> objComparator2 = dateTimeComparator0.thenComparing((java.util.Comparator<java.lang.Object>) dateTimeComparator1);
        org.joda.time.DateTimeFieldType dateTimeFieldType3 = dateTimeComparator1.getUpperLimit();
        org.joda.time.IllegalFieldValueException illegalFieldValueException5 = new org.joda.time.IllegalFieldValueException(dateTimeFieldType3, "29");
        org.junit.Assert.assertNotNull(dateTimeComparator0);
        org.junit.Assert.assertNotNull(dateTimeComparator1);
        org.junit.Assert.assertNotNull(objComparator2);
        org.junit.Assert.assertNotNull(dateTimeFieldType3);
    }

    @Test
    public void test0798() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0798");
        org.joda.time.DateTimeZone dateTimeZone0 = org.joda.time.DateTimeZone.getDefault();
        org.junit.Assert.assertNotNull(dateTimeZone0);
    }

    @Test
    public void test0799() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0799");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        org.joda.time.ReadableInterval readableInterval5 = null;
        org.joda.time.Seconds seconds6 = org.joda.time.Seconds.secondsIn(readableInterval5);
        org.joda.time.DurationFieldType durationFieldType7 = seconds6.getFieldType();
        org.joda.time.LocalDateTime localDateTime9 = localDateTime1.withFieldAdded(durationFieldType7, 86400000);
        org.joda.time.DateTimeField[] dateTimeFieldArray10 = localDateTime9.getFields();
        org.joda.time.LocalDateTime.Property property11 = localDateTime9.dayOfWeek();
        org.joda.time.LocalDateTime.Property property12 = localDateTime9.yearOfCentury();
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(seconds6);
        org.junit.Assert.assertNotNull(durationFieldType7);
        org.junit.Assert.assertNotNull(localDateTime9);
        org.junit.Assert.assertNotNull(dateTimeFieldArray10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(property12);
    }

    @Test
    public void test0800() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0800");
        org.joda.time.IllegalInstantException illegalInstantException2 = new org.joda.time.IllegalInstantException(2440588L, "PT86400000H");
    }

    @Test
    public void test0801() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0801");
        org.joda.time.Seconds seconds0 = org.joda.time.Seconds.MIN_VALUE;
        org.joda.time.MutablePeriod mutablePeriod1 = seconds0.toMutablePeriod();
        org.junit.Assert.assertNotNull(seconds0);
        org.junit.Assert.assertNotNull(mutablePeriod1);
    }

    @Test
    public void test0802() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0802");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime9 = property7.addWrapField((int) (short) -1);
        org.joda.time.MutableDateTime.Property property10 = mutableDateTime9.monthOfYear();
        java.lang.String str11 = property10.getAsString();
        org.joda.time.MutableDateTime mutableDateTime13 = property10.add((long) (short) 10);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(mutableDateTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "1" + "'", str11, "1");
        org.junit.Assert.assertNotNull(mutableDateTime13);
    }

    @Test
    public void test0803() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0803");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar8 = dateMidnight7.toGregorianCalendar();
        org.joda.time.LocalTime localTime9 = org.joda.time.LocalTime.fromCalendarFields((java.util.Calendar) gregorianCalendar8);
        org.joda.time.LocalTime.Property property10 = localTime9.minuteOfHour();
        org.joda.time.LocalTime localTime11 = property10.withMaximumValue();
        org.joda.time.LocalTime localTime13 = property10.addNoWrapToCopy(4);
        int int14 = localTime13.getSecondOfMinute();
        org.joda.time.LocalTime localTime16 = localTime13.plusMinutes(2147483647);
        org.joda.time.LocalTime localTime18 = localTime16.plusSeconds(0);
        int int19 = localTime18.getMillisOfDay();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(gregorianCalendar8);
        org.junit.Assert.assertNotNull(localTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(localTime11);
        org.junit.Assert.assertNotNull(localTime13);
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 0 + "'", int14 == 0);
        org.junit.Assert.assertNotNull(localTime16);
        org.junit.Assert.assertNotNull(localTime18);
        org.junit.Assert.assertTrue("'" + int19 + "' != '" + 7860000 + "'", int19 == 7860000);
    }

    @Test
    public void test0804() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0804");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        mutableDateTime6.setSecondOfMinute(0);
        org.joda.time.MutableDateTime.Property property10 = mutableDateTime6.minuteOfDay();
        org.joda.time.MutableDateTime.Property property11 = mutableDateTime6.minuteOfDay();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(property11);
    }

    @Test
    public void test0805() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0805");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime9 = property7.addWrapField((int) (short) -1);
        org.joda.time.MutableDateTime.Property property10 = mutableDateTime9.monthOfYear();
        org.joda.time.Duration duration12 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Duration duration14 = org.joda.time.Duration.standardDays((long) (short) 1);
        org.joda.time.Duration duration16 = duration12.withDurationAdded((org.joda.time.ReadableDuration) duration14, (int) (byte) -1);
        org.joda.time.MutableInterval mutableInterval17 = new org.joda.time.MutableInterval((org.joda.time.ReadableInstant) mutableDateTime9, (org.joda.time.ReadableDuration) duration14);
        mutableInterval17.setInterval((long) '4', 2700000L);
        org.joda.time.MutableInterval mutableInterval21 = mutableInterval17.copy();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(mutableDateTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(duration12);
        org.junit.Assert.assertNotNull(duration14);
        org.junit.Assert.assertNotNull(duration16);
        org.junit.Assert.assertNotNull(mutableInterval21);
    }

    @Test
    public void test0806() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0806");
        org.joda.time.MutableDateTime mutableDateTime0 = new org.joda.time.MutableDateTime();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone5 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology6 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        mutableDateTime0.setChronology((org.joda.time.Chronology) gregorianChronology6);
        mutableDateTime0.setDate(291L);
        org.junit.Assert.assertNotNull(gregorianChronology6);
    }

    @Test
    public void test0807() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0807");
        int int0 = org.joda.time.DateTimeConstants.MILLIS_PER_HOUR;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 3600000 + "'", int0 == 3600000);
    }

    @Test
    public void test0808() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0808");
        org.joda.time.tz.DateTimeZoneBuilder dateTimeZoneBuilder0 = new org.joda.time.tz.DateTimeZoneBuilder();
        org.joda.time.tz.DateTimeZoneBuilder dateTimeZoneBuilder3 = dateTimeZoneBuilder0.setFixedSavings("", (int) ' ');
        org.joda.time.tz.DateTimeZoneBuilder dateTimeZoneBuilder6 = dateTimeZoneBuilder3.setFixedSavings("2029-05-02T11:10:53.327", (int) (short) 0);
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            dateTimeZoneBuilder3.writeTo("ISOChronology[2026-08-06T11:10:36.726]", outputStream8);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeZoneBuilder3);
        org.junit.Assert.assertNotNull(dateTimeZoneBuilder6);
    }

    @Test
    public void test0809() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0809");
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
        org.joda.time.Duration duration22 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Chronology chronology24 = null;
        org.joda.time.DateMidnight dateMidnight25 = new org.joda.time.DateMidnight((long) (short) -1, chronology24);
        org.joda.time.DateMidnight dateMidnight27 = dateMidnight25.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology29 = null;
        org.joda.time.DateMidnight dateMidnight30 = new org.joda.time.DateMidnight((long) (short) -1, chronology29);
        org.joda.time.DateMidnight dateMidnight32 = dateMidnight30.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology34 = null;
        org.joda.time.DateMidnight dateMidnight35 = new org.joda.time.DateMidnight((long) (short) -1, chronology34);
        org.joda.time.DateMidnight dateMidnight37 = dateMidnight35.minusMonths((int) (byte) 100);
        int int38 = dateMidnight32.compareTo((org.joda.time.ReadableInstant) dateMidnight35);
        org.joda.time.Minutes minutes39 = org.joda.time.Minutes.minutesBetween((org.joda.time.ReadableInstant) dateMidnight25, (org.joda.time.ReadableInstant) dateMidnight32);
        org.joda.time.DateMidnight dateMidnight42 = dateMidnight32.withDurationAdded((long) '4', 100);
        org.joda.time.MutableInterval mutableInterval43 = new org.joda.time.MutableInterval((org.joda.time.ReadableDuration) duration22, (org.joda.time.ReadableInstant) dateMidnight42);
        org.joda.time.chrono.GJChronology gJChronology44 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField45 = gJChronology44.dayOfYear();
        org.joda.time.DurationField durationField46 = gJChronology44.years();
        org.joda.time.Period period47 = duration22.toPeriod((org.joda.time.Chronology) gJChronology44);
        org.joda.time.Period period49 = period47.plusMinutes((int) (short) 0);
        org.joda.time.MutableInterval mutableInterval50 = new org.joda.time.MutableInterval((org.joda.time.ReadableInstant) mutableDateTime20, (org.joda.time.ReadablePeriod) period47);
        mutableInterval50.setStartMillis((long) 0);
        org.junit.Assert.assertNotNull(copticChronology6);
        org.junit.Assert.assertNotNull(leapYearPatternType7);
        org.junit.Assert.assertNotNull(dateMidnight12);
        org.junit.Assert.assertNotNull(property13);
        org.junit.Assert.assertNotNull(mutableDateTime14);
        org.junit.Assert.assertNotNull(property15);
        org.junit.Assert.assertNotNull(mutableDateTime17);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertNotNull(islamicChronology19);
        org.junit.Assert.assertNotNull(duration22);
        org.junit.Assert.assertNotNull(dateMidnight27);
        org.junit.Assert.assertNotNull(dateMidnight32);
        org.junit.Assert.assertNotNull(dateMidnight37);
        org.junit.Assert.assertTrue("'" + int38 + "' != '" + (-1) + "'", int38 == (-1));
        org.junit.Assert.assertNotNull(minutes39);
        org.junit.Assert.assertNotNull(dateMidnight42);
        org.junit.Assert.assertNotNull(gJChronology44);
        org.junit.Assert.assertNotNull(dateTimeField45);
        org.junit.Assert.assertNotNull(durationField46);
        org.junit.Assert.assertNotNull(period47);
        org.junit.Assert.assertNotNull(period49);
    }

    @Test
    public void test0810() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0810");
        org.joda.time.chrono.GregorianChronology gregorianChronology0 = org.joda.time.chrono.GregorianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField1 = gregorianChronology0.minuteOfDay();
        org.joda.time.Chronology chronology2 = gregorianChronology0.withUTC();
        org.joda.time.MutableDateTime mutableDateTime3 = new org.joda.time.MutableDateTime(chronology2);
        org.joda.time.chrono.JulianChronology julianChronology4 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField5 = julianChronology4.clockhourOfDay();
        org.joda.time.chrono.GJChronology gJChronology6 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj7 = null;
        boolean boolean8 = gJChronology6.equals(obj7);
        org.joda.time.DurationField durationField9 = gJChronology6.days();
        org.joda.time.chrono.GJChronology gJChronology10 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology11 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology10);
        org.joda.time.chrono.LenientChronology lenientChronology12 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology10);
        org.joda.time.DateTimeField dateTimeField13 = lenientChronology12.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField14 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology6, dateTimeField13);
        org.joda.time.DateTimeFieldType dateTimeFieldType15 = skipUndoDateTimeField14.getType();
        org.joda.time.field.DividedDateTimeField dividedDateTimeField17 = new org.joda.time.field.DividedDateTimeField(dateTimeField5, dateTimeFieldType15, (int) 'a');
        org.joda.time.field.SkipDateTimeField skipDateTimeField18 = new org.joda.time.field.SkipDateTimeField(chronology2, dateTimeField5);
        org.junit.Assert.assertNotNull(gregorianChronology0);
        org.junit.Assert.assertNotNull(dateTimeField1);
        org.junit.Assert.assertNotNull(chronology2);
        org.junit.Assert.assertNotNull(julianChronology4);
        org.junit.Assert.assertNotNull(dateTimeField5);
        org.junit.Assert.assertNotNull(gJChronology6);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(durationField9);
        org.junit.Assert.assertNotNull(gJChronology10);
        org.junit.Assert.assertNotNull(lenientChronology11);
        org.junit.Assert.assertNotNull(lenientChronology12);
        org.junit.Assert.assertNotNull(dateTimeField13);
        org.junit.Assert.assertNotNull(dateTimeFieldType15);
    }

    @Test
    public void test0811() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0811");
        org.joda.time.chrono.GJChronology gJChronology0 = org.joda.time.chrono.GJChronology.getInstance();
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
        org.joda.time.Chronology chronology21 = gJChronology0.withZone((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.junit.Assert.assertNotNull(gJChronology0);
        org.junit.Assert.assertNotNull(copticChronology6);
        org.junit.Assert.assertNotNull(leapYearPatternType7);
        org.junit.Assert.assertNotNull(dateMidnight12);
        org.junit.Assert.assertNotNull(property13);
        org.junit.Assert.assertNotNull(mutableDateTime14);
        org.junit.Assert.assertNotNull(property15);
        org.junit.Assert.assertNotNull(mutableDateTime17);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertNotNull(islamicChronology19);
        org.junit.Assert.assertNotNull(chronology21);
    }

    @Test
    public void test0812() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0812");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone4 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology5 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        long long8 = fixedDateTimeZone4.convertLocalToUTC((long) 3, true);
        org.junit.Assert.assertNotNull(gregorianChronology5);
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-97L) + "'", long8 == (-97L));
    }

    @Test
    public void test0813() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0813");
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
        org.joda.time.LocalDate.Property property16 = localDate15.centuryOfEra();
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(dateMidnight10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(mutableDateTime12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(localDate15);
        org.junit.Assert.assertNotNull(property16);
    }

    @Test
    public void test0814() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0814");
        org.joda.time.Days days0 = org.joda.time.Days.SIX;
        org.junit.Assert.assertNotNull(days0);
    }

    @Test
    public void test0815() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0815");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.DateTimeFormat.fullDate();
        java.util.Locale locale1 = dateTimeFormatter0.getLocale();
        java.lang.StringBuilder stringBuilder2 = null;
        org.joda.time.Chronology chronology4 = null;
        org.joda.time.DateMidnight dateMidnight5 = new org.joda.time.DateMidnight((long) (short) -1, chronology4);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight5.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight10 = dateMidnight5.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight12 = dateMidnight10.withYear(100);
        org.joda.time.DateMidnight dateMidnight14 = dateMidnight12.plusDays(10);
        org.joda.time.DateMidnight dateMidnight16 = dateMidnight14.plusYears((int) (byte) 10);
        int int17 = dateMidnight16.getCenturyOfEra();
        // The following exception was thrown during execution in test generation
        try {
            dateTimeFormatter0.printTo(stringBuilder2, (org.joda.time.ReadableInstant) dateMidnight16);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
        org.junit.Assert.assertNull(locale1);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(dateMidnight10);
        org.junit.Assert.assertNotNull(dateMidnight12);
        org.junit.Assert.assertNotNull(dateMidnight14);
        org.junit.Assert.assertNotNull(dateMidnight16);
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + 1 + "'", int17 == 1);
    }

    @Test
    public void test0816() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0816");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.DateTimeFormat.longDate();
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.format.DateTimeFormatter dateTimeFormatter2 = dateTimeFormatter0.withChronology((org.joda.time.Chronology) julianChronology1);
        java.util.Locale locale3 = dateTimeFormatter2.getLocale();
        boolean boolean4 = dateTimeFormatter2.isOffsetParsed();
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(dateTimeFormatter2);
        org.junit.Assert.assertNull(locale3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
    }

    @Test
    public void test0817() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0817");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTime dateTime6 = new org.joda.time.DateTime(8, (int) (byte) -1, 40256602, 69, 0, (int) (short) -1);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 69 for hourOfDay must be in the range [0,23]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0818() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0818");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.DurationField durationField3 = gJChronology1.months();
        org.joda.time.DateMidnight dateMidnight5 = org.joda.time.DateMidnight.parse("2026-08-06T11:10:36.726");
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight5.minusYears((int) (byte) 100);
        org.joda.time.ReadableDateTime readableDateTime8 = null;
        org.joda.time.chrono.LimitChronology limitChronology9 = org.joda.time.chrono.LimitChronology.getInstance((org.joda.time.Chronology) gJChronology1, (org.joda.time.ReadableDateTime) dateMidnight5, readableDateTime8);
        org.joda.time.DateTime dateTime10 = limitChronology9.getLowerLimit();
        org.joda.time.DateTime dateTime11 = limitChronology9.getUpperLimit();
        org.joda.time.DateTimeField dateTimeField12 = limitChronology9.hourOfHalfday();
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(durationField3);
        org.junit.Assert.assertNotNull(dateMidnight5);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(limitChronology9);
        org.junit.Assert.assertNotNull(dateTime10);
        org.junit.Assert.assertNull(dateTime11);
        org.junit.Assert.assertNotNull(dateTimeField12);
    }

    @Test
    public void test0819() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0819");
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
        org.joda.time.YearMonthDay yearMonthDay22 = property20.getYearMonthDay();
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
        org.junit.Assert.assertNotNull(yearMonthDay22);
    }

    @Test
    public void test0820() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0820");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.DateTime dateTime6 = dateTime0.withWeekyear((int) (byte) 0);
        org.joda.time.DateTime.Property property7 = dateTime0.yearOfCentury();
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(dateTime6);
        org.junit.Assert.assertNotNull(property7);
    }

    @Test
    public void test0821() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0821");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime localDateTime5 = localDateTime1.withMinuteOfHour((int) '#');
        org.joda.time.LocalDateTime.Property property6 = localDateTime1.monthOfYear();
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(localDateTime5);
        org.junit.Assert.assertNotNull(property6);
    }

    @Test
    public void test0822() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0822");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.weekOfWeekyear();
        int int6 = dateMidnight2.getDayOfYear();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 1 + "'", int6 == 1);
    }

    @Test
    public void test0823() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0823");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        int int8 = skipUndoDateTimeField6.get((long) 10);
        org.joda.time.DurationField durationField9 = skipUndoDateTimeField6.getRangeDurationField();
        org.joda.time.field.OffsetDateTimeField offsetDateTimeField11 = new org.joda.time.field.OffsetDateTimeField((org.joda.time.DateTimeField) skipUndoDateTimeField6, 8);
        long long13 = offsetDateTimeField11.roundHalfEven(0L);
        int int15 = offsetDateTimeField11.getLeapAmount((long) 2026);
        org.joda.time.DateTimeFieldType dateTimeFieldType16 = offsetDateTimeField11.getType();
        java.util.Locale locale18 = null;
        java.lang.String str19 = offsetDateTimeField11.getAsText((long) 40237996, locale18);
        org.joda.time.DateTimeField dateTimeField20 = offsetDateTimeField11.getWrappedField();
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 2 + "'", int8 == 2);
        org.junit.Assert.assertNotNull(durationField9);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + (-262800000L) + "'", long13 == (-262800000L));
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 0 + "'", int15 == 0);
        org.junit.Assert.assertNotNull(dateTimeFieldType16);
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "10" + "'", str19, "10");
        org.junit.Assert.assertNotNull(dateTimeField20);
    }

    @Test
    public void test0824() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0824");
        org.joda.time.Chronology chronology0 = null;
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj2 = null;
        boolean boolean3 = gJChronology1.equals(obj2);
        org.joda.time.DurationField durationField4 = gJChronology1.days();
        org.joda.time.chrono.GJChronology gJChronology5 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology6 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology5);
        org.joda.time.chrono.LenientChronology lenientChronology7 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology5);
        org.joda.time.DateTimeField dateTimeField8 = lenientChronology7.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField9 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology1, dateTimeField8);
        long long11 = skipUndoDateTimeField9.remainder((long) 59);
        org.joda.time.field.SkipDateTimeField skipDateTimeField13 = new org.joda.time.field.SkipDateTimeField(chronology0, (org.joda.time.DateTimeField) skipUndoDateTimeField9, 40260602);
        int int15 = skipDateTimeField13.get((long) 13);
        long long17 = skipDateTimeField13.roundHalfEven((long) (-1));
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(durationField4);
        org.junit.Assert.assertNotNull(gJChronology5);
        org.junit.Assert.assertNotNull(lenientChronology6);
        org.junit.Assert.assertNotNull(lenientChronology7);
        org.junit.Assert.assertNotNull(dateTimeField8);
        org.junit.Assert.assertTrue("'" + long11 + "' != '" + 3600059L + "'", long11 == 3600059L);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 69 + "'", int15 == 69);
        org.junit.Assert.assertTrue("'" + long17 + "' != '" + (-3600000L) + "'", long17 == (-3600000L));
    }

    @Test
    public void test0825() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0825");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.Months months4 = org.joda.time.Months.FIVE;
        org.joda.time.Period period5 = period3.minus((org.joda.time.ReadablePeriod) months4);
        org.joda.time.Period period7 = period3.plusMillis((-1));
        org.joda.time.Period period8 = period3.toPeriod();
        org.joda.time.Period period10 = period8.withMonths(100);
        org.junit.Assert.assertNotNull(months4);
        org.junit.Assert.assertNotNull(period5);
        org.junit.Assert.assertNotNull(period7);
        org.junit.Assert.assertNotNull(period8);
        org.junit.Assert.assertNotNull(period10);
    }

    @Test
    public void test0826() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0826");
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
        java.util.GregorianCalendar gregorianCalendar21 = dateMidnight7.toGregorianCalendar();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(gregorianChronology14);
        org.junit.Assert.assertNotNull(cachedDateTimeZone16);
        org.junit.Assert.assertTrue("'" + long19 + "' != '" + (-1L) + "'", long19 == (-1L));
        org.junit.Assert.assertNotNull(dateMidnight20);
        org.junit.Assert.assertNotNull(gregorianCalendar21);
    }

    @Test
    public void test0827() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0827");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        int int8 = skipUndoDateTimeField6.get((long) 10);
        org.joda.time.DurationField durationField9 = skipUndoDateTimeField6.getRangeDurationField();
        org.joda.time.field.OffsetDateTimeField offsetDateTimeField11 = new org.joda.time.field.OffsetDateTimeField((org.joda.time.DateTimeField) skipUndoDateTimeField6, 8);
        long long13 = offsetDateTimeField11.roundHalfEven(0L);
        int int15 = offsetDateTimeField11.getLeapAmount((long) 2026);
        org.joda.time.Chronology chronology17 = null;
        org.joda.time.DateMidnight dateMidnight18 = new org.joda.time.DateMidnight((long) (short) -1, chronology17);
        org.joda.time.DateMidnight dateMidnight20 = dateMidnight18.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight23 = dateMidnight18.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar24 = dateMidnight23.toGregorianCalendar();
        org.joda.time.LocalTime localTime25 = org.joda.time.LocalTime.fromCalendarFields((java.util.Calendar) gregorianCalendar24);
        org.joda.time.LocalTime.Property property26 = localTime25.minuteOfHour();
        org.joda.time.LocalTime.Property property27 = localTime25.millisOfSecond();
        org.joda.time.LocalTime.Property property28 = localTime25.millisOfSecond();
        org.joda.time.format.DateTimeFormatter dateTimeFormatter29 = org.joda.time.format.DateTimeFormat.longDate();
        org.joda.time.chrono.JulianChronology julianChronology30 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.format.DateTimeFormatter dateTimeFormatter31 = dateTimeFormatter29.withChronology((org.joda.time.Chronology) julianChronology30);
        boolean boolean32 = localTime25.equals((java.lang.Object) dateTimeFormatter29);
        org.joda.time.DateTimeZone dateTimeZone34 = null;
        org.joda.time.LocalDateTime localDateTime35 = new org.joda.time.LocalDateTime(dateTimeZone34);
        java.lang.String str36 = localDateTime35.toString();
        int[] intArray37 = localDateTime35.getValues();
        // The following exception was thrown during execution in test generation
        try {
            int[] intArray39 = offsetDateTimeField11.add((org.joda.time.ReadablePartial) localTime25, 40243947, intArray37, (int) 'a');
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 40243947");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 2 + "'", int8 == 2);
        org.junit.Assert.assertNotNull(durationField9);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + (-262800000L) + "'", long13 == (-262800000L));
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 0 + "'", int15 == 0);
        org.junit.Assert.assertNotNull(dateMidnight20);
        org.junit.Assert.assertNotNull(dateMidnight23);
        org.junit.Assert.assertNotNull(gregorianCalendar24);
        org.junit.Assert.assertNotNull(localTime25);
        org.junit.Assert.assertNotNull(property26);
        org.junit.Assert.assertNotNull(property27);
        org.junit.Assert.assertNotNull(property28);
        org.junit.Assert.assertNotNull(dateTimeFormatter29);
        org.junit.Assert.assertNotNull(julianChronology30);
        org.junit.Assert.assertNotNull(dateTimeFormatter31);
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + false + "'", boolean32 == false);
// flaky "4) test0827(RegressionTest1)":         org.junit.Assert.assertEquals("'" + str36 + "' != '" + "2026-08-06T11:11:52.253" + "'", str36, "2026-08-06T11:11:52.253");
        org.junit.Assert.assertNotNull(intArray37);
// flaky "2) test0827(RegressionTest1)":         org.junit.Assert.assertArrayEquals(intArray37, new int[] { 2026, 8, 6, 40312253 });
    }

    @Test
    public void test0828() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0828");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTime.Property property1 = dateTime0.millisOfDay();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTime dateTime3 = dateTime0.withDayOfYear(999);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 999 for dayOfYear must be in the range [1,365]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(property1);
    }

    @Test
    public void test0829() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0829");
        org.joda.time.chrono.ISOChronology iSOChronology2 = org.joda.time.chrono.ISOChronology.getInstanceUTC();
        org.joda.time.Interval interval3 = new org.joda.time.Interval(1786007462709L, 2177280001000L, (org.joda.time.Chronology) iSOChronology2);
        org.junit.Assert.assertNotNull(iSOChronology2);
    }

    @Test
    public void test0830() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0830");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Interval interval2 = yearMonth0.toInterval(dateTimeZone1);
        org.joda.time.Months months3 = org.joda.time.Months.ONE;
        org.joda.time.Interval interval4 = interval2.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) months3);
        org.joda.time.DateTime dateTime5 = interval2.getEnd();
        org.joda.time.DateTime dateTime7 = dateTime5.plusSeconds(100);
        org.joda.time.DateTime dateTime9 = dateTime5.plusMillis(24);
        org.joda.time.Chronology chronology11 = null;
        org.joda.time.DateMidnight dateMidnight12 = new org.joda.time.DateMidnight((long) (short) -1, chronology11);
        org.joda.time.MutableDateTime mutableDateTime13 = dateMidnight12.toMutableDateTime();
        mutableDateTime13.addWeeks(5);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MutableInterval mutableInterval16 = new org.joda.time.MutableInterval((org.joda.time.ReadableInstant) dateTime9, (org.joda.time.ReadableInstant) mutableDateTime13);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The end instant must be greater than the start instant");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertNotNull(months3);
        org.junit.Assert.assertNotNull(interval4);
        org.junit.Assert.assertNotNull(dateTime5);
        org.junit.Assert.assertNotNull(dateTime7);
        org.junit.Assert.assertNotNull(dateTime9);
        org.junit.Assert.assertNotNull(mutableDateTime13);
    }

    @Test
    public void test0831() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0831");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        int int3 = dateTime0.getMonthOfYear();
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 8 + "'", int3 == 8);
    }

    @Test
    public void test0832() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0832");
        org.joda.time.Months months0 = org.joda.time.Months.TEN;
        org.joda.time.chrono.BuddhistChronology buddhistChronology1 = org.joda.time.chrono.BuddhistChronology.getInstanceUTC();
        org.joda.time.MutablePeriod mutablePeriod2 = new org.joda.time.MutablePeriod((java.lang.Object) months0, (org.joda.time.Chronology) buddhistChronology1);
        org.junit.Assert.assertNotNull(months0);
        org.junit.Assert.assertNotNull(buddhistChronology1);
    }

    @Test
    public void test0833() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0833");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        int int10 = dateMidnight4.compareTo((org.joda.time.ReadableInstant) dateMidnight7);
        org.joda.time.ReadableInterval readableInterval11 = null;
        org.joda.time.Seconds seconds12 = org.joda.time.Seconds.secondsIn(readableInterval11);
        org.joda.time.DurationFieldType durationFieldType13 = seconds12.getFieldType();
        org.joda.time.DateMidnight dateMidnight15 = dateMidnight4.withFieldAdded(durationFieldType13, (int) (byte) 0);
        org.joda.time.DateMidnight.Property property16 = dateMidnight15.dayOfMonth();
        org.joda.time.DateMidnight dateMidnight17 = property16.roundHalfFloorCopy();
        org.joda.time.DateMidnight dateMidnight18 = property16.roundHalfFloorCopy();
        org.joda.time.Interval interval19 = dateMidnight18.toInterval();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + (-1) + "'", int10 == (-1));
        org.junit.Assert.assertNotNull(seconds12);
        org.junit.Assert.assertNotNull(durationFieldType13);
        org.junit.Assert.assertNotNull(dateMidnight15);
        org.junit.Assert.assertNotNull(property16);
        org.junit.Assert.assertNotNull(dateMidnight17);
        org.junit.Assert.assertNotNull(dateMidnight18);
        org.junit.Assert.assertNotNull(interval19);
    }

    @Test
    public void test0834() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0834");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.ordinalDate();
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
    }

    @Test
    public void test0835() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0835");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.MonthDay monthDay4 = monthDay2.minusDays((int) '4');
        org.joda.time.MonthDay.Property property5 = monthDay2.monthOfYear();
        java.lang.String str6 = property5.toString();
        java.lang.String str7 = property5.getAsShortText();
        org.joda.time.MonthDay monthDay9 = property5.addWrapFieldToCopy(1440);
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(monthDay4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "Property[monthOfYear]" + "'", str6, "Property[monthOfYear]");
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "Jan" + "'", str7, "Jan");
        org.junit.Assert.assertNotNull(monthDay9);
    }

    @Test
    public void test0836() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0836");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone4 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology5 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        org.joda.time.DateTime dateTime6 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone7 = null;
        org.joda.time.DateTime dateTime8 = dateTime6.withZoneRetainFields(dateTimeZone7);
        org.joda.time.DateTime dateTime10 = dateTime6.withYear((int) (byte) 0);
        org.joda.time.DateTime dateTime12 = dateTime6.withWeekyear((int) (byte) 0);
        org.joda.time.DateTime dateTime14 = dateTime6.minusMonths(8);
        org.joda.time.chrono.GJChronology gJChronology15 = org.joda.time.chrono.GJChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone4, (org.joda.time.ReadableInstant) dateTime6);
        org.joda.time.DateTime dateTime17 = dateTime6.withMillis((long) (short) 100);
        org.joda.time.DateTime.Property property18 = dateTime6.era();
        org.junit.Assert.assertNotNull(gregorianChronology5);
        org.junit.Assert.assertNotNull(dateTime8);
        org.junit.Assert.assertNotNull(dateTime10);
        org.junit.Assert.assertNotNull(dateTime12);
        org.junit.Assert.assertNotNull(dateTime14);
        org.junit.Assert.assertNotNull(gJChronology15);
        org.junit.Assert.assertNotNull(dateTime17);
        org.junit.Assert.assertNotNull(property18);
    }

    @Test
    public void test0837() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0837");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray1 = yearMonth0.getFieldTypes();
        org.joda.time.YearMonth yearMonth3 = yearMonth0.withMonthOfYear(1);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDate localDate5 = yearMonth3.toLocalDate(40260602);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 40260602 for dayOfMonth must be in the range [1,31]: year: 2026 month: 1");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeFieldTypeArray1);
        org.junit.Assert.assertNotNull(yearMonth3);
    }

    @Test
    public void test0838() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0838");
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
        org.junit.Assert.assertNotNull(converterManager0);
        org.junit.Assert.assertNull(partialConverter2);
        org.junit.Assert.assertNull(partialConverter4);
        org.junit.Assert.assertNotNull(instant5);
        org.junit.Assert.assertNotNull(instant8);
        org.junit.Assert.assertNotNull(durationConverter9);
        org.junit.Assert.assertNotNull(julianChronology11);
        org.junit.Assert.assertNotNull(property13);
        org.junit.Assert.assertNotNull(localDate15);
        org.junit.Assert.assertNotNull(dateMidnight20);
        org.junit.Assert.assertNotNull(property21);
        org.junit.Assert.assertNotNull(mutableDateTime22);
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
        org.junit.Assert.assertNotNull(localDate25);
        org.junit.Assert.assertNotNull(partialConverter26);
        org.junit.Assert.assertNull(periodConverter28);
    }

    @Test
    public void test0839() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0839");
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
        org.joda.time.DurationField durationField30 = copticChronology5.halfdays();
        org.joda.time.Instant instant31 = org.joda.time.Instant.EPOCH;
        org.joda.time.Instant instant34 = instant31.withDurationAdded((-1L), 100);
        org.joda.time.Chronology chronology36 = null;
        org.joda.time.DateMidnight dateMidnight37 = new org.joda.time.DateMidnight((long) (short) -1, chronology36);
        org.joda.time.DateMidnight dateMidnight39 = dateMidnight37.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property40 = dateMidnight37.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime41 = dateMidnight37.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property42 = mutableDateTime41.centuryOfEra();
        mutableDateTime41.setSecondOfMinute(24);
        org.joda.time.Months months45 = org.joda.time.Months.monthsBetween((org.joda.time.ReadableInstant) instant31, (org.joda.time.ReadableInstant) mutableDateTime41);
        org.joda.time.Chronology chronology47 = null;
        org.joda.time.DateMidnight dateMidnight48 = new org.joda.time.DateMidnight((long) (short) -1, chronology47);
        org.joda.time.DateMidnight dateMidnight50 = dateMidnight48.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight53 = dateMidnight48.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight55 = dateMidnight53.withYear(100);
        org.joda.time.PeriodType periodType57 = null;
        org.joda.time.Chronology chronology58 = null;
        org.joda.time.Period period59 = new org.joda.time.Period((long) (short) 10, periodType57, chronology58);
        org.joda.time.Months months60 = org.joda.time.Months.FIVE;
        org.joda.time.Period period61 = period59.minus((org.joda.time.ReadablePeriod) months60);
        org.joda.time.Chronology chronology63 = null;
        org.joda.time.DateMidnight dateMidnight64 = new org.joda.time.DateMidnight((long) (short) -1, chronology63);
        org.joda.time.DateMidnight dateMidnight66 = dateMidnight64.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology68 = null;
        org.joda.time.DateMidnight dateMidnight69 = new org.joda.time.DateMidnight((long) (short) -1, chronology68);
        org.joda.time.DateMidnight dateMidnight71 = dateMidnight69.minusMonths((int) (byte) 100);
        int int72 = dateMidnight66.compareTo((org.joda.time.ReadableInstant) dateMidnight69);
        org.joda.time.Duration duration73 = period61.toDurationTo((org.joda.time.ReadableInstant) dateMidnight69);
        org.joda.time.Duration duration74 = duration73.negated();
        org.joda.time.PeriodType periodType75 = org.joda.time.PeriodType.yearDay();
        org.joda.time.PeriodType periodType76 = periodType75.withSecondsRemoved();
        org.joda.time.MutablePeriod mutablePeriod77 = new org.joda.time.MutablePeriod((org.joda.time.ReadableInstant) dateMidnight53, (org.joda.time.ReadableDuration) duration73, periodType76);
        org.joda.time.Period period78 = new org.joda.time.Period((org.joda.time.ReadableInstant) instant31, (org.joda.time.ReadableDuration) duration73);
        boolean boolean79 = copticChronology5.equals((java.lang.Object) instant31);
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
        org.junit.Assert.assertNotNull(durationField30);
        org.junit.Assert.assertNotNull(instant31);
        org.junit.Assert.assertNotNull(instant34);
        org.junit.Assert.assertNotNull(dateMidnight39);
        org.junit.Assert.assertNotNull(property40);
        org.junit.Assert.assertNotNull(mutableDateTime41);
        org.junit.Assert.assertNotNull(property42);
        org.junit.Assert.assertNotNull(months45);
        org.junit.Assert.assertNotNull(dateMidnight50);
        org.junit.Assert.assertNotNull(dateMidnight53);
        org.junit.Assert.assertNotNull(dateMidnight55);
        org.junit.Assert.assertNotNull(months60);
        org.junit.Assert.assertNotNull(period61);
        org.junit.Assert.assertNotNull(dateMidnight66);
        org.junit.Assert.assertNotNull(dateMidnight71);
        org.junit.Assert.assertTrue("'" + int72 + "' != '" + (-1) + "'", int72 == (-1));
        org.junit.Assert.assertNotNull(duration73);
        org.junit.Assert.assertNotNull(duration74);
        org.junit.Assert.assertNotNull(periodType75);
        org.junit.Assert.assertNotNull(periodType76);
        org.junit.Assert.assertTrue("'" + boolean79 + "' != '" + false + "'", boolean79 == false);
    }

    @Test
    public void test0840() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0840");
        org.joda.time.Months months0 = org.joda.time.Months.THREE;
        org.joda.time.DurationFieldType durationFieldType1 = months0.getFieldType();
        org.junit.Assert.assertNotNull(months0);
        org.junit.Assert.assertNotNull(durationFieldType1);
    }

    @Test
    public void test0841() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0841");
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
        org.joda.time.YearMonthDay yearMonthDay16 = dateMidnight12.toYearMonthDay();
        int int17 = yearMonthDay16.size();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTimeFieldType dateTimeFieldType19 = yearMonthDay16.getFieldType((int) '#');
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 35");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(dateMidnight14);
        org.junit.Assert.assertNotNull(months15);
        org.junit.Assert.assertNotNull(yearMonthDay16);
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + 3 + "'", int17 == 3);
    }

    @Test
    public void test0842() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0842");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.DateTime dateTime6 = dateTime0.withWeekyear((int) (byte) 0);
        org.joda.time.DateTime dateTime7 = dateTime6.toDateTime();
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(dateTime6);
        org.junit.Assert.assertNotNull(dateTime7);
    }

    @Test
    public void test0843() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0843");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.minusMinutes((int) ' ');
        org.joda.time.DateTime dateTime5 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone6 = null;
        org.joda.time.DateTime dateTime7 = dateTime5.withZoneRetainFields(dateTimeZone6);
        org.joda.time.DateTime dateTime9 = dateTime5.withYear((int) (byte) 0);
        org.joda.time.Duration duration11 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Chronology chronology13 = null;
        org.joda.time.DateMidnight dateMidnight14 = new org.joda.time.DateMidnight((long) (short) -1, chronology13);
        org.joda.time.DateMidnight dateMidnight16 = dateMidnight14.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology18 = null;
        org.joda.time.DateMidnight dateMidnight19 = new org.joda.time.DateMidnight((long) (short) -1, chronology18);
        org.joda.time.DateMidnight dateMidnight21 = dateMidnight19.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology23 = null;
        org.joda.time.DateMidnight dateMidnight24 = new org.joda.time.DateMidnight((long) (short) -1, chronology23);
        org.joda.time.DateMidnight dateMidnight26 = dateMidnight24.minusMonths((int) (byte) 100);
        int int27 = dateMidnight21.compareTo((org.joda.time.ReadableInstant) dateMidnight24);
        org.joda.time.Minutes minutes28 = org.joda.time.Minutes.minutesBetween((org.joda.time.ReadableInstant) dateMidnight14, (org.joda.time.ReadableInstant) dateMidnight21);
        org.joda.time.DateMidnight dateMidnight31 = dateMidnight21.withDurationAdded((long) '4', 100);
        org.joda.time.MutableInterval mutableInterval32 = new org.joda.time.MutableInterval((org.joda.time.ReadableDuration) duration11, (org.joda.time.ReadableInstant) dateMidnight31);
        org.joda.time.DateTime dateTime33 = dateTime9.minus((org.joda.time.ReadableDuration) duration11);
        org.joda.time.DateTime dateTime34 = dateTime4.plus((org.joda.time.ReadableDuration) duration11);
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(dateTime7);
        org.junit.Assert.assertNotNull(dateTime9);
        org.junit.Assert.assertNotNull(duration11);
        org.junit.Assert.assertNotNull(dateMidnight16);
        org.junit.Assert.assertNotNull(dateMidnight21);
        org.junit.Assert.assertNotNull(dateMidnight26);
        org.junit.Assert.assertTrue("'" + int27 + "' != '" + (-1) + "'", int27 == (-1));
        org.junit.Assert.assertNotNull(minutes28);
        org.junit.Assert.assertNotNull(dateMidnight31);
        org.junit.Assert.assertNotNull(dateTime33);
        org.junit.Assert.assertNotNull(dateTime34);
    }

    @Test
    public void test0844() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0844");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        org.joda.time.ReadableInterval readableInterval5 = null;
        org.joda.time.Seconds seconds6 = org.joda.time.Seconds.secondsIn(readableInterval5);
        org.joda.time.DurationFieldType durationFieldType7 = seconds6.getFieldType();
        org.joda.time.LocalDateTime localDateTime9 = localDateTime1.withFieldAdded(durationFieldType7, 86400000);
        org.joda.time.LocalDateTime localDateTime11 = localDateTime9.withMillisOfSecond((int) (short) 1);
        org.joda.time.LocalTime localTime13 = new org.joda.time.LocalTime((long) (-1));
        org.joda.time.LocalTime localTime15 = localTime13.withSecondOfMinute(1);
        org.joda.time.LocalTime localTime17 = localTime13.plusSeconds(0);
        org.joda.time.LocalTime localTime19 = localTime13.plusSeconds(86400000);
        org.joda.time.LocalTime.Property property20 = localTime13.hourOfDay();
        org.joda.time.LocalTime localTime22 = property20.addWrapFieldToCopy(40299577);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Weeks weeks23 = org.joda.time.Weeks.weeksBetween((org.joda.time.ReadablePartial) localDateTime11, (org.joda.time.ReadablePartial) localTime22);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: ReadablePartial objects must have the same set of fields");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(seconds6);
        org.junit.Assert.assertNotNull(durationFieldType7);
        org.junit.Assert.assertNotNull(localDateTime9);
        org.junit.Assert.assertNotNull(localDateTime11);
        org.junit.Assert.assertNotNull(localTime15);
        org.junit.Assert.assertNotNull(localTime17);
        org.junit.Assert.assertNotNull(localTime19);
        org.junit.Assert.assertNotNull(property20);
        org.junit.Assert.assertNotNull(localTime22);
    }

    @Test
    public void test0845() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0845");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.TimeOfDay timeOfDay2 = new org.joda.time.TimeOfDay(1970, (int) (short) -1);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 1970 for hourOfDay must not be larger than 23");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0846() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0846");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusYears(100);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateMidnight dateMidnight6 = dateMidnight4.withCenturyOfEra(2147483647);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 2147483647 for centuryOfEra must be in the range [0,2922789]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
    }

    @Test
    public void test0847() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0847");
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
        org.joda.time.TimeOfDay.Property property22 = timeOfDay9.hourOfDay();
        org.joda.time.TimeOfDay timeOfDay23 = property22.withMinimumValue();
        org.joda.time.TimeOfDay timeOfDay25 = timeOfDay23.withMinuteOfHour(0);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.TimeOfDay timeOfDay27 = timeOfDay25.withSecondOfMinute((int) 'a');
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 97 for secondOfMinute must be in the range [0,59]");
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
        org.junit.Assert.assertNotNull(property22);
        org.junit.Assert.assertNotNull(timeOfDay23);
        org.junit.Assert.assertNotNull(timeOfDay25);
    }

    @Test
    public void test0848() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0848");
        org.joda.time.chrono.GJChronology gJChronology0 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology1 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology0);
        org.joda.time.DateTimeZone dateTimeZone2 = null;
        org.joda.time.Chronology chronology3 = lenientChronology1.withZone(dateTimeZone2);
        long long9 = lenientChronology1.getDateTimeMillis((long) (short) 100, 40299577, 53, (int) (byte) -1, 7860000);
        org.junit.Assert.assertNotNull(gJChronology0);
        org.junit.Assert.assertNotNull(lenientChronology1);
        org.junit.Assert.assertNotNull(chronology3);
        org.junit.Assert.assertTrue("'" + long9 + "' != '" + 145078481039000L + "'", long9 == 145078481039000L);
    }

    @Test
    public void test0849() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0849");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone11 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology12 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone11);
        java.lang.String str13 = copticChronology12.toString();
        org.joda.time.DateTimeField dateTimeField14 = copticChronology12.clockhourOfDay();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone20 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology21 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone20);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType22 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology24 = null;
        org.joda.time.DateMidnight dateMidnight25 = new org.joda.time.DateMidnight((long) (short) -1, chronology24);
        org.joda.time.DateMidnight dateMidnight27 = dateMidnight25.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property28 = dateMidnight25.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime29 = dateMidnight25.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property30 = mutableDateTime29.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime32 = property30.addWrapField((int) (short) -1);
        boolean boolean33 = leapYearPatternType22.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology34 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone20, leapYearPatternType22);
        org.joda.time.MutableDateTime mutableDateTime35 = new org.joda.time.MutableDateTime((long) (byte) 10, (org.joda.time.DateTimeZone) fixedDateTimeZone20);
        boolean boolean36 = copticChronology12.equals((java.lang.Object) mutableDateTime35);
        org.joda.time.DurationField durationField37 = copticChronology12.halfdays();
        org.joda.time.DateTimeZone dateTimeZone38 = null;
        org.joda.time.LocalDateTime localDateTime39 = new org.joda.time.LocalDateTime(dateTimeZone38);
        int int40 = localDateTime39.getYear();
        org.joda.time.LocalDateTime localDateTime42 = localDateTime39.plusMillis(10);
        org.joda.time.chrono.GJChronology gJChronology43 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj44 = null;
        boolean boolean45 = gJChronology43.equals(obj44);
        org.joda.time.DurationField durationField46 = gJChronology43.days();
        org.joda.time.chrono.GJChronology gJChronology47 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology48 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology47);
        org.joda.time.chrono.LenientChronology lenientChronology49 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology47);
        org.joda.time.DateTimeField dateTimeField50 = lenientChronology49.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField51 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology43, dateTimeField50);
        org.joda.time.DateTimeFieldType dateTimeFieldType52 = skipUndoDateTimeField51.getType();
        int int53 = skipUndoDateTimeField51.getMinimumValue();
        java.lang.String str54 = skipUndoDateTimeField51.getName();
        org.joda.time.DateTimeFieldType dateTimeFieldType55 = skipUndoDateTimeField51.getType();
        org.joda.time.LocalDateTime localDateTime57 = localDateTime39.withField(dateTimeFieldType55, 24);
        org.joda.time.field.RemainderDateTimeField remainderDateTimeField59 = new org.joda.time.field.RemainderDateTimeField(dateTimeField4, durationField37, dateTimeFieldType55, (int) (byte) 10);
        org.joda.time.chrono.GJChronology gJChronology60 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj61 = null;
        boolean boolean62 = gJChronology60.equals(obj61);
        org.joda.time.DurationField durationField63 = gJChronology60.days();
        org.joda.time.chrono.GJChronology gJChronology64 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology65 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology64);
        org.joda.time.chrono.LenientChronology lenientChronology66 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology64);
        org.joda.time.DateTimeField dateTimeField67 = lenientChronology66.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField68 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology60, dateTimeField67);
        org.joda.time.DateTimeFieldType dateTimeFieldType69 = skipUndoDateTimeField68.getType();
        org.joda.time.field.DividedDateTimeField dividedDateTimeField70 = new org.joda.time.field.DividedDateTimeField(remainderDateTimeField59, dateTimeFieldType69);
        long long72 = remainderDateTimeField59.roundCeiling((long) (short) 10);
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertNotNull(copticChronology12);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "CopticChronology[2026-08-06T11:10:36.726]" + "'", str13, "CopticChronology[2026-08-06T11:10:36.726]");
        org.junit.Assert.assertNotNull(dateTimeField14);
        org.junit.Assert.assertNotNull(copticChronology21);
        org.junit.Assert.assertNotNull(leapYearPatternType22);
        org.junit.Assert.assertNotNull(dateMidnight27);
        org.junit.Assert.assertNotNull(property28);
        org.junit.Assert.assertNotNull(mutableDateTime29);
        org.junit.Assert.assertNotNull(property30);
        org.junit.Assert.assertNotNull(mutableDateTime32);
        org.junit.Assert.assertTrue("'" + boolean33 + "' != '" + false + "'", boolean33 == false);
        org.junit.Assert.assertNotNull(islamicChronology34);
        org.junit.Assert.assertTrue("'" + boolean36 + "' != '" + false + "'", boolean36 == false);
        org.junit.Assert.assertNotNull(durationField37);
        org.junit.Assert.assertTrue("'" + int40 + "' != '" + 2026 + "'", int40 == 2026);
        org.junit.Assert.assertNotNull(localDateTime42);
        org.junit.Assert.assertNotNull(gJChronology43);
        org.junit.Assert.assertTrue("'" + boolean45 + "' != '" + false + "'", boolean45 == false);
        org.junit.Assert.assertNotNull(durationField46);
        org.junit.Assert.assertNotNull(gJChronology47);
        org.junit.Assert.assertNotNull(lenientChronology48);
        org.junit.Assert.assertNotNull(lenientChronology49);
        org.junit.Assert.assertNotNull(dateTimeField50);
        org.junit.Assert.assertNotNull(dateTimeFieldType52);
        org.junit.Assert.assertTrue("'" + int53 + "' != '" + 0 + "'", int53 == 0);
        org.junit.Assert.assertEquals("'" + str54 + "' != '" + "yearOfCentury" + "'", str54, "yearOfCentury");
        org.junit.Assert.assertNotNull(dateTimeFieldType55);
        org.junit.Assert.assertNotNull(localDateTime57);
        org.junit.Assert.assertNotNull(gJChronology60);
        org.junit.Assert.assertTrue("'" + boolean62 + "' != '" + false + "'", boolean62 == false);
        org.junit.Assert.assertNotNull(durationField63);
        org.junit.Assert.assertNotNull(gJChronology64);
        org.junit.Assert.assertNotNull(lenientChronology65);
        org.junit.Assert.assertNotNull(lenientChronology66);
        org.junit.Assert.assertNotNull(dateTimeField67);
        org.junit.Assert.assertNotNull(dateTimeFieldType69);
        org.junit.Assert.assertTrue("'" + long72 + "' != '" + 342000000L + "'", long72 == 342000000L);
    }

    @Test
    public void test0850() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0850");
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
        org.joda.time.DateTime.Property property29 = dateTime28.millisOfDay();
        org.joda.time.DateMidnight dateMidnight30 = dateTime28.toDateMidnight();
        org.joda.time.DateTime.Property property31 = dateTime28.era();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTime dateTime35 = dateTime28.withDate(0, (int) (short) 1, 40237996);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 40237996 for dayOfMonth must be in the range [1,31]: year: 0 month: 1");
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
        org.junit.Assert.assertNotNull(property29);
        org.junit.Assert.assertNotNull(dateMidnight30);
        org.junit.Assert.assertNotNull(property31);
    }

    @Test
    public void test0851() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0851");
        org.joda.time.TimeOfDay timeOfDay1 = new org.joda.time.TimeOfDay((long) 100);
        org.joda.time.TimeOfDay timeOfDay3 = timeOfDay1.minusMinutes((int) (short) 1);
        int int4 = timeOfDay1.getHourOfDay();
        org.junit.Assert.assertNotNull(timeOfDay3);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 1 + "'", int4 == 1);
    }

    @Test
    public void test0852() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0852");
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
        org.joda.time.DateTime dateTime23 = dateTime21.minusMillis((int) 'a');
        org.junit.Assert.assertNotNull(copticChronology6);
        org.junit.Assert.assertNotNull(leapYearPatternType7);
        org.junit.Assert.assertNotNull(dateMidnight12);
        org.junit.Assert.assertNotNull(property13);
        org.junit.Assert.assertNotNull(mutableDateTime14);
        org.junit.Assert.assertNotNull(property15);
        org.junit.Assert.assertNotNull(mutableDateTime17);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertNotNull(islamicChronology19);
        org.junit.Assert.assertNotNull(dateTime23);
    }

    @Test
    public void test0853() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0853");
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
        org.joda.time.Chronology chronology37 = null;
        org.joda.time.DateMidnight dateMidnight38 = new org.joda.time.DateMidnight((long) (short) -1, chronology37);
        org.joda.time.DateMidnight dateMidnight40 = dateMidnight38.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology42 = null;
        org.joda.time.DateMidnight dateMidnight43 = new org.joda.time.DateMidnight((long) (short) -1, chronology42);
        org.joda.time.DateMidnight dateMidnight45 = dateMidnight43.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology47 = null;
        org.joda.time.DateMidnight dateMidnight48 = new org.joda.time.DateMidnight((long) (short) -1, chronology47);
        org.joda.time.DateMidnight dateMidnight50 = dateMidnight48.minusMonths((int) (byte) 100);
        int int51 = dateMidnight45.compareTo((org.joda.time.ReadableInstant) dateMidnight48);
        org.joda.time.Minutes minutes52 = org.joda.time.Minutes.minutesBetween((org.joda.time.ReadableInstant) dateMidnight38, (org.joda.time.ReadableInstant) dateMidnight45);
        org.joda.time.DateMidnight dateMidnight55 = dateMidnight45.withDurationAdded((long) '4', 100);
        org.joda.time.MutablePeriod mutablePeriod56 = new org.joda.time.MutablePeriod((org.joda.time.ReadableDuration) duration1, (org.joda.time.ReadableInstant) dateMidnight45);
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
        org.junit.Assert.assertNotNull(dateMidnight40);
        org.junit.Assert.assertNotNull(dateMidnight45);
        org.junit.Assert.assertNotNull(dateMidnight50);
        org.junit.Assert.assertTrue("'" + int51 + "' != '" + (-1) + "'", int51 == (-1));
        org.junit.Assert.assertNotNull(minutes52);
        org.junit.Assert.assertNotNull(dateMidnight55);
    }

    @Test
    public void test0854() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0854");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        int int5 = localDateTime1.getYear();
        // The following exception was thrown during execution in test generation
        try {
            int int7 = localDateTime1.getValue(1000);
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: Invalid index: 1000");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 2026 + "'", int5 == 2026);
    }

    @Test
    public void test0855() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0855");
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
        org.joda.time.TimeOfDay.Property property22 = timeOfDay9.hourOfDay();
        org.joda.time.TimeOfDay timeOfDay24 = timeOfDay9.plusMinutes(29);
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
        org.junit.Assert.assertNotNull(property22);
        org.junit.Assert.assertNotNull(timeOfDay24);
    }

    @Test
    public void test0856() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0856");
        org.joda.time.Days days1 = org.joda.time.Days.days(7);
        org.junit.Assert.assertNotNull(days1);
    }

    @Test
    public void test0857() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0857");
        org.joda.time.chrono.GregorianChronology gregorianChronology0 = org.joda.time.chrono.GregorianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField1 = gregorianChronology0.minuteOfDay();
        org.joda.time.Chronology chronology2 = gregorianChronology0.withUTC();
        org.joda.time.YearMonthDay yearMonthDay3 = new org.joda.time.YearMonthDay((org.joda.time.Chronology) gregorianChronology0);
        org.junit.Assert.assertNotNull(gregorianChronology0);
        org.junit.Assert.assertNotNull(dateTimeField1);
        org.junit.Assert.assertNotNull(chronology2);
    }

    @Test
    public void test0858() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0858");
        org.joda.time.Days days1 = org.joda.time.Days.days(32);
        org.junit.Assert.assertNotNull(days1);
    }

    @Test
    public void test0859() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0859");
        org.joda.time.TimeOfDay timeOfDay1 = new org.joda.time.TimeOfDay((long) 100);
        org.joda.time.TimeOfDay timeOfDay3 = timeOfDay1.minusMinutes((int) (short) 1);
        org.joda.time.TimeOfDay.Property property4 = timeOfDay3.secondOfMinute();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.TimeOfDay timeOfDay6 = property4.setCopy(40308915);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 40308915 for secondOfMinute must be in the range [0,59]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(timeOfDay3);
        org.junit.Assert.assertNotNull(property4);
    }

    @Test
    public void test0860() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0860");
        org.joda.time.ReadableInterval readableInterval0 = null;
        org.joda.time.Seconds seconds1 = org.joda.time.Seconds.secondsIn(readableInterval0);
        org.joda.time.DurationFieldType durationFieldType2 = seconds1.getFieldType();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Duration duration3 = new org.joda.time.Duration((java.lang.Object) seconds1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No duration converter found for type: org.joda.time.Seconds");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(seconds1);
        org.junit.Assert.assertNotNull(durationFieldType2);
    }

    @Test
    public void test0861() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0861");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        int int8 = skipUndoDateTimeField6.get((long) 10);
        org.joda.time.DurationField durationField9 = skipUndoDateTimeField6.getRangeDurationField();
        org.joda.time.field.OffsetDateTimeField offsetDateTimeField11 = new org.joda.time.field.OffsetDateTimeField((org.joda.time.DateTimeField) skipUndoDateTimeField6, 8);
        java.util.Locale locale13 = null;
        java.lang.String str14 = offsetDateTimeField11.getAsText(2147483647, locale13);
        long long16 = offsetDateTimeField11.roundCeiling(2678400L);
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 2 + "'", int8 == 2);
        org.junit.Assert.assertNotNull(durationField9);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "2147483647" + "'", str14, "2147483647");
        org.junit.Assert.assertTrue("'" + long16 + "' != '" + 342000000L + "'", long16 == 342000000L);
    }

    @Test
    public void test0862() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0862");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar8 = dateMidnight7.toGregorianCalendar();
        org.joda.time.LocalTime localTime9 = org.joda.time.LocalTime.fromCalendarFields((java.util.Calendar) gregorianCalendar8);
        org.joda.time.LocalTime.Property property10 = localTime9.minuteOfHour();
        org.joda.time.LocalTime.Property property11 = localTime9.millisOfSecond();
        org.joda.time.LocalTime.Property property12 = localTime9.millisOfSecond();
        org.joda.time.format.DateTimeFormatter dateTimeFormatter13 = org.joda.time.format.DateTimeFormat.longDate();
        org.joda.time.chrono.JulianChronology julianChronology14 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.format.DateTimeFormatter dateTimeFormatter15 = dateTimeFormatter13.withChronology((org.joda.time.Chronology) julianChronology14);
        boolean boolean16 = localTime9.equals((java.lang.Object) dateTimeFormatter13);
        org.joda.time.ReadableInterval readableInterval17 = null;
        org.joda.time.Seconds seconds18 = org.joda.time.Seconds.secondsIn(readableInterval17);
        org.joda.time.DurationFieldType durationFieldType19 = seconds18.getFieldType();
        org.joda.time.field.PreciseDurationField preciseDurationField21 = new org.joda.time.field.PreciseDurationField(durationFieldType19, (long) 59);
        org.joda.time.ReadableInterval readableInterval22 = null;
        org.joda.time.Seconds seconds23 = org.joda.time.Seconds.secondsIn(readableInterval22);
        org.joda.time.DurationFieldType durationFieldType24 = seconds23.getFieldType();
        org.joda.time.field.PreciseDurationField preciseDurationField26 = new org.joda.time.field.PreciseDurationField(durationFieldType24, (long) 59);
        int int27 = preciseDurationField21.compareTo((org.joda.time.DurationField) preciseDurationField26);
        org.joda.time.DurationFieldType durationFieldType28 = preciseDurationField21.getType();
        boolean boolean29 = localTime9.isSupported(durationFieldType28);
        org.joda.time.IllegalFieldValueException illegalFieldValueException31 = new org.joda.time.IllegalFieldValueException(durationFieldType28, "");
        java.lang.Throwable[] throwableArray32 = illegalFieldValueException31.getSuppressed();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(gregorianCalendar8);
        org.junit.Assert.assertNotNull(localTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(property12);
        org.junit.Assert.assertNotNull(dateTimeFormatter13);
        org.junit.Assert.assertNotNull(julianChronology14);
        org.junit.Assert.assertNotNull(dateTimeFormatter15);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertNotNull(seconds18);
        org.junit.Assert.assertNotNull(durationFieldType19);
        org.junit.Assert.assertNotNull(seconds23);
        org.junit.Assert.assertNotNull(durationFieldType24);
        org.junit.Assert.assertTrue("'" + int27 + "' != '" + 0 + "'", int27 == 0);
        org.junit.Assert.assertNotNull(durationFieldType28);
        org.junit.Assert.assertTrue("'" + boolean29 + "' != '" + true + "'", boolean29 == true);
        org.junit.Assert.assertNotNull(throwableArray32);
        org.junit.Assert.assertArrayEquals(throwableArray32, new java.lang.Throwable[] {});
    }

    @Test
    public void test0863() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0863");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField1 = julianChronology0.clockhourOfDay();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj3 = null;
        boolean boolean4 = gJChronology2.equals(obj3);
        org.joda.time.DurationField durationField5 = gJChronology2.days();
        org.joda.time.chrono.GJChronology gJChronology6 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology7 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology6);
        org.joda.time.chrono.LenientChronology lenientChronology8 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology6);
        org.joda.time.DateTimeField dateTimeField9 = lenientChronology8.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField10 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology2, dateTimeField9);
        org.joda.time.DateTimeFieldType dateTimeFieldType11 = skipUndoDateTimeField10.getType();
        org.joda.time.field.DividedDateTimeField dividedDateTimeField13 = new org.joda.time.field.DividedDateTimeField(dateTimeField1, dateTimeFieldType11, (int) 'a');
        org.joda.time.DurationField durationField14 = dividedDateTimeField13.getLeapDurationField();
        java.util.Locale locale15 = null;
        int int16 = dividedDateTimeField13.getMaximumTextLength(locale15);
        int int17 = dividedDateTimeField13.getMinimumValue();
        org.joda.time.DateTimeField dateTimeField18 = org.joda.time.field.StrictDateTimeField.getInstance((org.joda.time.DateTimeField) dividedDateTimeField13);
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(dateTimeField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(durationField5);
        org.junit.Assert.assertNotNull(gJChronology6);
        org.junit.Assert.assertNotNull(lenientChronology7);
        org.junit.Assert.assertNotNull(lenientChronology8);
        org.junit.Assert.assertNotNull(dateTimeField9);
        org.junit.Assert.assertNotNull(dateTimeFieldType11);
        org.junit.Assert.assertNull(durationField14);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 1 + "'", int16 == 1);
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + 0 + "'", int17 == 0);
        org.junit.Assert.assertNotNull(dateTimeField18);
    }

    @Test
    public void test0864() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0864");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Interval interval2 = yearMonth0.toInterval(dateTimeZone1);
        org.joda.time.Months months3 = org.joda.time.Months.ONE;
        org.joda.time.Interval interval4 = interval2.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) months3);
        org.joda.time.Duration duration5 = interval4.toDuration();
        long long6 = duration5.getStandardSeconds();
        org.joda.time.Duration duration8 = duration5.plus((long) 2);
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertNotNull(months3);
        org.junit.Assert.assertNotNull(interval4);
        org.junit.Assert.assertNotNull(duration5);
        org.junit.Assert.assertTrue("'" + long6 + "' != '" + 2678400L + "'", long6 == 2678400L);
        org.junit.Assert.assertNotNull(duration8);
    }

    @Test
    public void test0865() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0865");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Interval interval2 = yearMonth0.toInterval(dateTimeZone1);
        org.joda.time.Months months3 = org.joda.time.Months.ONE;
        org.joda.time.Interval interval4 = interval2.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) months3);
        org.joda.time.Months months6 = months3.plus(40243947);
        org.joda.time.DurationFieldType durationFieldType7 = months3.getFieldType();
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertNotNull(months3);
        org.junit.Assert.assertNotNull(interval4);
        org.junit.Assert.assertNotNull(months6);
        org.junit.Assert.assertNotNull(durationFieldType7);
    }

    @Test
    public void test0866() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0866");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.DateMidnight dateMidnight7 = property5.setCopy((int) (byte) 100);
        int int8 = property5.getMinimumValue();
        org.joda.time.DateMidnight dateMidnight10 = property5.addToCopy((long) '4');
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
        org.junit.Assert.assertNotNull(dateMidnight10);
    }

    @Test
    public void test0867() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0867");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar8 = dateMidnight7.toGregorianCalendar();
        org.joda.time.LocalTime localTime9 = org.joda.time.LocalTime.fromCalendarFields((java.util.Calendar) gregorianCalendar8);
        org.joda.time.LocalTime.Property property10 = localTime9.minuteOfHour();
        org.joda.time.LocalTime.Property property11 = localTime9.millisOfSecond();
        org.joda.time.LocalTime localTime13 = property11.addCopy(0L);
        org.joda.time.LocalTime.Property property14 = localTime13.millisOfDay();
        org.joda.time.LocalTime localTime16 = property14.addWrapFieldToCopy(8);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(gregorianCalendar8);
        org.junit.Assert.assertNotNull(localTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(localTime13);
        org.junit.Assert.assertNotNull(property14);
        org.junit.Assert.assertNotNull(localTime16);
    }

    @Test
    public void test0868() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0868");
        int int0 = org.joda.time.chrono.BuddhistChronology.BE;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 1 + "'", int0 == 1);
    }

    @Test
    public void test0869() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0869");
        java.lang.ClassLoader classLoader1 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.tz.ZoneInfoProvider zoneInfoProvider2 = new org.joda.time.tz.ZoneInfoProvider("-86400000 hours and 10 milliseconds", classLoader1);
            org.junit.Assert.fail("Expected exception of type java.io.IOException; message: Resource not found: \"-86400000 hours and 10 milliseconds/ZoneInfoMap\" ClassLoader: system");
        } catch (java.io.IOException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0870() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0870");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        org.joda.time.DateTimeZone dateTimeZone7 = julianChronology0.getZone();
        org.joda.time.DateTime dateTime8 = new org.joda.time.DateTime(dateTimeZone7);
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertNotNull(dateTimeZone7);
    }

    @Test
    public void test0871() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0871");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        org.joda.time.LocalDateTime.Property property5 = localDateTime1.millisOfDay();
        int int6 = localDateTime1.getCenturyOfEra();
        int int7 = localDateTime1.getDayOfMonth();
        org.joda.time.LocalDateTime localDateTime9 = localDateTime1.withMinuteOfHour(24);
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 20 + "'", int6 == 20);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 6 + "'", int7 == 6);
        org.junit.Assert.assertNotNull(localDateTime9);
    }

    @Test
    public void test0872() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0872");
        org.joda.time.IllegalFieldValueException illegalFieldValueException2 = new org.joda.time.IllegalFieldValueException("PT60S", "CopticChronology[2026-08-06T11:10:36.726]");
        java.lang.Number number3 = illegalFieldValueException2.getIllegalNumberValue();
        org.junit.Assert.assertNull(number3);
    }

    @Test
    public void test0873() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0873");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone11 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology12 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone11);
        java.lang.String str13 = copticChronology12.toString();
        org.joda.time.DateTimeField dateTimeField14 = copticChronology12.clockhourOfDay();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone20 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology21 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone20);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType22 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology24 = null;
        org.joda.time.DateMidnight dateMidnight25 = new org.joda.time.DateMidnight((long) (short) -1, chronology24);
        org.joda.time.DateMidnight dateMidnight27 = dateMidnight25.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property28 = dateMidnight25.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime29 = dateMidnight25.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property30 = mutableDateTime29.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime32 = property30.addWrapField((int) (short) -1);
        boolean boolean33 = leapYearPatternType22.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology34 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone20, leapYearPatternType22);
        org.joda.time.MutableDateTime mutableDateTime35 = new org.joda.time.MutableDateTime((long) (byte) 10, (org.joda.time.DateTimeZone) fixedDateTimeZone20);
        boolean boolean36 = copticChronology12.equals((java.lang.Object) mutableDateTime35);
        org.joda.time.DurationField durationField37 = copticChronology12.halfdays();
        org.joda.time.DateTimeZone dateTimeZone38 = null;
        org.joda.time.LocalDateTime localDateTime39 = new org.joda.time.LocalDateTime(dateTimeZone38);
        int int40 = localDateTime39.getYear();
        org.joda.time.LocalDateTime localDateTime42 = localDateTime39.plusMillis(10);
        org.joda.time.chrono.GJChronology gJChronology43 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj44 = null;
        boolean boolean45 = gJChronology43.equals(obj44);
        org.joda.time.DurationField durationField46 = gJChronology43.days();
        org.joda.time.chrono.GJChronology gJChronology47 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology48 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology47);
        org.joda.time.chrono.LenientChronology lenientChronology49 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology47);
        org.joda.time.DateTimeField dateTimeField50 = lenientChronology49.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField51 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology43, dateTimeField50);
        org.joda.time.DateTimeFieldType dateTimeFieldType52 = skipUndoDateTimeField51.getType();
        int int53 = skipUndoDateTimeField51.getMinimumValue();
        java.lang.String str54 = skipUndoDateTimeField51.getName();
        org.joda.time.DateTimeFieldType dateTimeFieldType55 = skipUndoDateTimeField51.getType();
        org.joda.time.LocalDateTime localDateTime57 = localDateTime39.withField(dateTimeFieldType55, 24);
        org.joda.time.field.RemainderDateTimeField remainderDateTimeField59 = new org.joda.time.field.RemainderDateTimeField(dateTimeField4, durationField37, dateTimeFieldType55, (int) (byte) 10);
        org.joda.time.chrono.GJChronology gJChronology60 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj61 = null;
        boolean boolean62 = gJChronology60.equals(obj61);
        org.joda.time.DurationField durationField63 = gJChronology60.days();
        org.joda.time.chrono.GJChronology gJChronology64 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology65 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology64);
        org.joda.time.chrono.LenientChronology lenientChronology66 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology64);
        org.joda.time.DateTimeField dateTimeField67 = lenientChronology66.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField68 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology60, dateTimeField67);
        org.joda.time.DateTimeFieldType dateTimeFieldType69 = skipUndoDateTimeField68.getType();
        org.joda.time.field.DividedDateTimeField dividedDateTimeField70 = new org.joda.time.field.DividedDateTimeField(remainderDateTimeField59, dateTimeFieldType69);
        org.joda.time.DurationField durationField71 = dividedDateTimeField70.getRangeDurationField();
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertNotNull(copticChronology12);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "CopticChronology[2026-08-06T11:10:36.726]" + "'", str13, "CopticChronology[2026-08-06T11:10:36.726]");
        org.junit.Assert.assertNotNull(dateTimeField14);
        org.junit.Assert.assertNotNull(copticChronology21);
        org.junit.Assert.assertNotNull(leapYearPatternType22);
        org.junit.Assert.assertNotNull(dateMidnight27);
        org.junit.Assert.assertNotNull(property28);
        org.junit.Assert.assertNotNull(mutableDateTime29);
        org.junit.Assert.assertNotNull(property30);
        org.junit.Assert.assertNotNull(mutableDateTime32);
        org.junit.Assert.assertTrue("'" + boolean33 + "' != '" + false + "'", boolean33 == false);
        org.junit.Assert.assertNotNull(islamicChronology34);
        org.junit.Assert.assertTrue("'" + boolean36 + "' != '" + false + "'", boolean36 == false);
        org.junit.Assert.assertNotNull(durationField37);
        org.junit.Assert.assertTrue("'" + int40 + "' != '" + 2026 + "'", int40 == 2026);
        org.junit.Assert.assertNotNull(localDateTime42);
        org.junit.Assert.assertNotNull(gJChronology43);
        org.junit.Assert.assertTrue("'" + boolean45 + "' != '" + false + "'", boolean45 == false);
        org.junit.Assert.assertNotNull(durationField46);
        org.junit.Assert.assertNotNull(gJChronology47);
        org.junit.Assert.assertNotNull(lenientChronology48);
        org.junit.Assert.assertNotNull(lenientChronology49);
        org.junit.Assert.assertNotNull(dateTimeField50);
        org.junit.Assert.assertNotNull(dateTimeFieldType52);
        org.junit.Assert.assertTrue("'" + int53 + "' != '" + 0 + "'", int53 == 0);
        org.junit.Assert.assertEquals("'" + str54 + "' != '" + "yearOfCentury" + "'", str54, "yearOfCentury");
        org.junit.Assert.assertNotNull(dateTimeFieldType55);
        org.junit.Assert.assertNotNull(localDateTime57);
        org.junit.Assert.assertNotNull(gJChronology60);
        org.junit.Assert.assertTrue("'" + boolean62 + "' != '" + false + "'", boolean62 == false);
        org.junit.Assert.assertNotNull(durationField63);
        org.junit.Assert.assertNotNull(gJChronology64);
        org.junit.Assert.assertNotNull(lenientChronology65);
        org.junit.Assert.assertNotNull(lenientChronology66);
        org.junit.Assert.assertNotNull(dateTimeField67);
        org.junit.Assert.assertNotNull(dateTimeFieldType69);
        org.junit.Assert.assertNotNull(durationField71);
    }

    @Test
    public void test0874() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0874");
        org.joda.time.Partial partial0 = new org.joda.time.Partial();
        org.joda.time.format.DateTimeFormatter dateTimeFormatter1 = partial0.getFormatter();
        org.joda.time.Minutes minutes2 = org.joda.time.Minutes.MAX_VALUE;
        org.joda.time.Minutes minutes3 = org.joda.time.Minutes.MAX_VALUE;
        boolean boolean4 = minutes2.isLessThan(minutes3);
        org.joda.time.Partial partial5 = partial0.plus((org.joda.time.ReadablePeriod) minutes2);
        java.lang.String str6 = partial0.toStringList();
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray7 = partial0.getFieldTypes();
        org.junit.Assert.assertNull(dateTimeFormatter1);
        org.junit.Assert.assertNotNull(minutes2);
        org.junit.Assert.assertNotNull(minutes3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(partial5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "[]" + "'", str6, "[]");
        org.junit.Assert.assertNotNull(dateTimeFieldTypeArray7);
        org.junit.Assert.assertArrayEquals(dateTimeFieldTypeArray7, new org.joda.time.DateTimeFieldType[] {});
    }

    @Test
    public void test0875() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0875");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Interval interval2 = yearMonth0.toInterval(dateTimeZone1);
        org.joda.time.Months months3 = org.joda.time.Months.ONE;
        org.joda.time.Interval interval4 = interval2.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) months3);
        org.joda.time.DateTime dateTime5 = interval2.getEnd();
        org.joda.time.DateTime dateTime7 = dateTime5.plusSeconds(100);
        org.joda.time.DateTime dateTime9 = dateTime5.plusMillis(24);
        org.joda.time.DateTime dateTime11 = dateTime5.plus(1786007462167L);
        org.joda.time.DateTime dateTime12 = dateTime11.toDateTime();
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertNotNull(months3);
        org.junit.Assert.assertNotNull(interval4);
        org.junit.Assert.assertNotNull(dateTime5);
        org.junit.Assert.assertNotNull(dateTime7);
        org.junit.Assert.assertNotNull(dateTime9);
        org.junit.Assert.assertNotNull(dateTime11);
        org.junit.Assert.assertNotNull(dateTime12);
    }

    @Test
    public void test0876() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0876");
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
        org.joda.time.Partial partial27 = new org.joda.time.Partial((org.joda.time.Chronology) gJChronology23);
        org.joda.time.Instant instant28 = gJChronology23.getGregorianCutover();
        org.joda.time.DateTimeField dateTimeField29 = gJChronology23.dayOfWeek();
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
        org.junit.Assert.assertNotNull(instant28);
        org.junit.Assert.assertNotNull(dateTimeField29);
    }

    @Test
    public void test0877() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0877");
        org.joda.time.chrono.GJChronology gJChronology0 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField1 = gJChronology0.dayOfYear();
        org.joda.time.Instant instant2 = gJChronology0.getGregorianCutover();
        org.junit.Assert.assertNotNull(gJChronology0);
        org.junit.Assert.assertNotNull(dateTimeField1);
        org.junit.Assert.assertNotNull(instant2);
    }

    @Test
    public void test0878() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0878");
        org.joda.time.Minutes minutes0 = org.joda.time.Minutes.MAX_VALUE;
        org.joda.time.Minutes minutes1 = org.joda.time.Minutes.MAX_VALUE;
        boolean boolean2 = minutes0.isLessThan(minutes1);
        org.joda.time.Minutes minutes3 = org.joda.time.Minutes.MAX_VALUE;
        org.joda.time.Minutes minutes4 = org.joda.time.Minutes.MAX_VALUE;
        boolean boolean5 = minutes3.isLessThan(minutes4);
        int int6 = minutes4.getMinutes();
        org.joda.time.Minutes minutes7 = org.joda.time.Minutes.MAX_VALUE;
        org.joda.time.Minutes minutes8 = org.joda.time.Minutes.MAX_VALUE;
        boolean boolean9 = minutes7.isLessThan(minutes8);
        int int10 = minutes4.compareTo((org.joda.time.base.BaseSingleFieldPeriod) minutes7);
        org.joda.time.Minutes minutes11 = minutes1.minus(minutes4);
        org.joda.time.Minutes minutes12 = org.joda.time.Minutes.MAX_VALUE;
        org.joda.time.Minutes minutes13 = org.joda.time.Minutes.MAX_VALUE;
        org.joda.time.Minutes minutes14 = minutes12.minus(minutes13);
        org.joda.time.Minutes minutes16 = minutes14.minus(100);
        boolean boolean17 = minutes1.isLessThan(minutes14);
        org.junit.Assert.assertNotNull(minutes0);
        org.junit.Assert.assertNotNull(minutes1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(minutes3);
        org.junit.Assert.assertNotNull(minutes4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 2147483647 + "'", int6 == 2147483647);
        org.junit.Assert.assertNotNull(minutes7);
        org.junit.Assert.assertNotNull(minutes8);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
        org.junit.Assert.assertNotNull(minutes11);
        org.junit.Assert.assertNotNull(minutes12);
        org.junit.Assert.assertNotNull(minutes13);
        org.junit.Assert.assertNotNull(minutes14);
        org.junit.Assert.assertNotNull(minutes16);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
    }

    @Test
    public void test0879() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0879");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = localDate2.withWeekyear((int) (byte) 100);
        org.joda.time.LocalDate localDate7 = localDate2.withWeekyear((int) (short) -1);
        org.joda.time.LocalDate.Property property8 = localDate2.yearOfEra();
        org.joda.time.LocalDate localDate9 = property8.roundHalfCeilingCopy();
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(localDate7);
        org.junit.Assert.assertNotNull(property8);
        org.junit.Assert.assertNotNull(localDate9);
    }

    @Test
    public void test0880() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0880");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField1 = julianChronology0.clockhourOfDay();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj3 = null;
        boolean boolean4 = gJChronology2.equals(obj3);
        org.joda.time.DurationField durationField5 = gJChronology2.days();
        org.joda.time.chrono.GJChronology gJChronology6 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology7 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology6);
        org.joda.time.chrono.LenientChronology lenientChronology8 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology6);
        org.joda.time.DateTimeField dateTimeField9 = lenientChronology8.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField10 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology2, dateTimeField9);
        org.joda.time.DateTimeFieldType dateTimeFieldType11 = skipUndoDateTimeField10.getType();
        org.joda.time.field.DividedDateTimeField dividedDateTimeField13 = new org.joda.time.field.DividedDateTimeField(dateTimeField1, dateTimeFieldType11, (int) 'a');
        int int15 = dividedDateTimeField13.get(8L);
        org.joda.time.DurationField durationField16 = dividedDateTimeField13.getDurationField();
        // The following exception was thrown during execution in test generation
        try {
            long long18 = dividedDateTimeField13.roundHalfCeiling((-3155673464001L));
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 0 for clockhourOfDay must be in the range [1,24]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(dateTimeField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(durationField5);
        org.junit.Assert.assertNotNull(gJChronology6);
        org.junit.Assert.assertNotNull(lenientChronology7);
        org.junit.Assert.assertNotNull(lenientChronology8);
        org.junit.Assert.assertNotNull(dateTimeField9);
        org.junit.Assert.assertNotNull(dateTimeFieldType11);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 0 + "'", int15 == 0);
        org.junit.Assert.assertNotNull(durationField16);
    }

    @Test
    public void test0881() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0881");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        int int8 = skipUndoDateTimeField6.get((long) 10);
        org.joda.time.DurationField durationField9 = skipUndoDateTimeField6.getRangeDurationField();
        org.joda.time.field.OffsetDateTimeField offsetDateTimeField11 = new org.joda.time.field.OffsetDateTimeField((org.joda.time.DateTimeField) skipUndoDateTimeField6, 8);
        long long13 = offsetDateTimeField11.roundHalfEven(0L);
        long long15 = offsetDateTimeField11.roundHalfFloor((long) 1000);
        int int16 = offsetDateTimeField11.getMaximumValue();
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 2 + "'", int8 == 2);
        org.junit.Assert.assertNotNull(durationField9);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + (-262800000L) + "'", long13 == (-262800000L));
        org.junit.Assert.assertTrue("'" + long15 + "' != '" + (-262800000L) + "'", long15 == (-262800000L));
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 61 + "'", int16 == 61);
    }

    @Test
    public void test0882() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0882");
        int int0 = org.joda.time.chrono.IslamicChronology.AH;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 1 + "'", int0 == 1);
    }

    @Test
    public void test0883() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0883");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.MutableDateTime mutableDateTime3 = dateMidnight2.toMutableDateTime();
        mutableDateTime3.addWeeks(5);
        org.joda.time.Chronology chronology6 = mutableDateTime3.getChronology();
        org.junit.Assert.assertNotNull(mutableDateTime3);
        org.junit.Assert.assertNotNull(chronology6);
    }

    @Test
    public void test0884() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0884");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.DateMidnight dateMidnight7 = property5.setCopy((int) (byte) 100);
        int int8 = property5.getMinimumValue();
        org.joda.time.DateMidnight dateMidnight10 = property5.setCopy(0);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
        org.junit.Assert.assertNotNull(dateMidnight10);
    }

    @Test
    public void test0885() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0885");
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
        org.joda.time.Interval interval30 = dateMidnight27.toInterval();
        int int31 = dateMidnight27.getDayOfWeek();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(dateMidnight14);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + (-1) + "'", int15 == (-1));
        org.junit.Assert.assertNotNull(minutes16);
        org.junit.Assert.assertNotNull(dateMidnight19);
        org.junit.Assert.assertNotNull(dateMidnight24);
        org.junit.Assert.assertNotNull(dateMidnight27);
        org.junit.Assert.assertNotNull(gregorianCalendar28);
        org.junit.Assert.assertNotNull(interval30);
        org.junit.Assert.assertTrue("'" + int31 + "' != '" + 4 + "'", int31 == 4);
    }

    @Test
    public void test0886() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0886");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime2.plusHours(0);
        org.joda.time.LocalDateTime localDateTime5 = dateTime2.toLocalDateTime();
        org.joda.time.DateTimeComparator dateTimeComparator6 = org.joda.time.DateTimeComparator.getDateOnlyInstance();
        org.joda.time.DateTimeComparator dateTimeComparator7 = org.joda.time.DateTimeComparator.getTimeOnlyInstance();
        java.util.Comparator<java.lang.Object> objComparator8 = dateTimeComparator6.thenComparing((java.util.Comparator<java.lang.Object>) dateTimeComparator7);
        org.joda.time.DateTimeFieldType dateTimeFieldType9 = dateTimeComparator7.getUpperLimit();
        org.joda.time.LocalDateTime.Property property10 = localDateTime5.property(dateTimeFieldType9);
        org.joda.time.DateTimeField dateTimeField11 = property10.getField();
        org.joda.time.LocalDateTime localDateTime12 = property10.roundHalfCeilingCopy();
        org.joda.time.LocalDateTime localDateTime13 = property10.withMinimumValue();
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(localDateTime5);
        org.junit.Assert.assertNotNull(dateTimeComparator6);
        org.junit.Assert.assertNotNull(dateTimeComparator7);
        org.junit.Assert.assertNotNull(objComparator8);
        org.junit.Assert.assertNotNull(dateTimeFieldType9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(dateTimeField11);
        org.junit.Assert.assertNotNull(localDateTime12);
        org.junit.Assert.assertNotNull(localDateTime13);
    }

    @Test
    public void test0887() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0887");
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
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(mutableDateTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(copticChronology16);
        org.junit.Assert.assertNotNull(leapYearPatternType17);
        org.junit.Assert.assertNotNull(dateMidnight22);
        org.junit.Assert.assertNotNull(property23);
        org.junit.Assert.assertNotNull(mutableDateTime24);
        org.junit.Assert.assertNotNull(property25);
        org.junit.Assert.assertNotNull(mutableDateTime27);
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + false + "'", boolean28 == false);
        org.junit.Assert.assertNotNull(islamicChronology29);
        org.junit.Assert.assertNotNull(instant32);
        org.junit.Assert.assertNotNull(instant35);
        org.junit.Assert.assertNotNull(dateMidnight40);
        org.junit.Assert.assertNotNull(property41);
        org.junit.Assert.assertNotNull(mutableDateTime42);
        org.junit.Assert.assertNotNull(property43);
        org.junit.Assert.assertNotNull(months46);
        org.junit.Assert.assertNotNull(dateMidnight51);
        org.junit.Assert.assertNotNull(dateMidnight54);
        org.junit.Assert.assertNotNull(dateMidnight56);
        org.junit.Assert.assertNotNull(months61);
        org.junit.Assert.assertNotNull(period62);
        org.junit.Assert.assertNotNull(dateMidnight67);
        org.junit.Assert.assertNotNull(dateMidnight72);
        org.junit.Assert.assertTrue("'" + int73 + "' != '" + (-1) + "'", int73 == (-1));
        org.junit.Assert.assertNotNull(duration74);
        org.junit.Assert.assertNotNull(duration75);
        org.junit.Assert.assertNotNull(periodType76);
        org.junit.Assert.assertNotNull(periodType77);
        org.junit.Assert.assertNotNull(dateMidnight81);
    }

    @Test
    public void test0888() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0888");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.DateTimeFormat.shortTime();
        org.joda.time.Chronology chronology1 = dateTimeFormatter0.getChronolgy();
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
        org.junit.Assert.assertNull(chronology1);
    }

    @Test
    public void test0889() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0889");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.MutableDateTime mutableDateTime3 = dateMidnight2.toMutableDateTime();
        mutableDateTime3.addWeeks(5);
        mutableDateTime3.setMillis(8L);
        org.junit.Assert.assertNotNull(mutableDateTime3);
    }

    @Test
    public void test0890() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0890");
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
        org.joda.time.Period period40 = org.joda.time.Period.weeks((int) (short) 100);
        int int41 = period40.getDays();
        org.joda.time.Period period43 = period40.minusWeeks(100);
        org.joda.time.Duration duration44 = period43.toStandardDuration();
        org.joda.time.MonthDay monthDay45 = monthDay19.minus((org.joda.time.ReadablePeriod) period43);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MonthDay monthDay47 = monthDay45.withMonthOfYear(19);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 19 for monthOfYear must be in the range [1,12]");
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
        org.junit.Assert.assertNotNull(months24);
        org.junit.Assert.assertNotNull(period25);
        org.junit.Assert.assertNotNull(dateMidnight30);
        org.junit.Assert.assertNotNull(dateMidnight35);
        org.junit.Assert.assertTrue("'" + int36 + "' != '" + (-1) + "'", int36 == (-1));
        org.junit.Assert.assertNotNull(duration37);
        org.junit.Assert.assertNotNull(monthDay38);
        org.junit.Assert.assertNotNull(period40);
        org.junit.Assert.assertTrue("'" + int41 + "' != '" + 0 + "'", int41 == 0);
        org.junit.Assert.assertNotNull(period43);
        org.junit.Assert.assertNotNull(duration44);
        org.junit.Assert.assertNotNull(monthDay45);
    }

    @Test
    public void test0891() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0891");
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
        mutablePeriod11.setMonths((int) 'a');
        org.junit.Assert.assertNotNull(months4);
        org.junit.Assert.assertNotNull(period5);
        org.junit.Assert.assertNotNull(period7);
        org.junit.Assert.assertNotNull(durationFieldTypeArray8);
        org.junit.Assert.assertNotNull(period10);
        org.junit.Assert.assertNotNull(mutablePeriod11);
    }

    @Test
    public void test0892() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0892");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = property3.addWrapFieldToCopy(29);
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone10 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology11 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone10);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalTime localTime12 = new org.joda.time.LocalTime((java.lang.Object) property3, (org.joda.time.Chronology) copticChronology11);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No partial converter found for type: org.joda.time.LocalDate$Property");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(copticChronology11);
    }

    @Test
    public void test0893() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0893");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.format.DateTimeFormatter dateTimeFormatter1 = org.joda.time.format.DateTimeFormat.forStyle("monthOfYear");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid style specification: monthOfYear");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0894() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0894");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime3.yearOfCentury();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDateTime localDateTime8 = localDateTime3.withDate((int) '#', (-53), (int) (short) 100);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value -53 for monthOfYear must be in the range [1,12]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
    }

    @Test
    public void test0895() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0895");
        org.joda.time.IllegalFieldValueException illegalFieldValueException2 = new org.joda.time.IllegalFieldValueException("PT60S", "CopticChronology[2026-08-06T11:10:36.726]");
        java.lang.String str3 = illegalFieldValueException2.getIllegalStringValue();
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "CopticChronology[2026-08-06T11:10:36.726]" + "'", str3, "CopticChronology[2026-08-06T11:10:36.726]");
    }

    @Test
    public void test0896() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0896");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar8 = dateMidnight7.toGregorianCalendar();
        org.joda.time.LocalTime localTime9 = org.joda.time.LocalTime.fromCalendarFields((java.util.Calendar) gregorianCalendar8);
        org.joda.time.LocalTime.Property property10 = localTime9.minuteOfHour();
        org.joda.time.LocalTime.Property property11 = localTime9.millisOfSecond();
        org.joda.time.LocalTime localTime13 = property11.addCopy(0L);
        org.joda.time.chrono.GJChronology gJChronology14 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj15 = null;
        boolean boolean16 = gJChronology14.equals(obj15);
        boolean boolean17 = localTime13.equals((java.lang.Object) gJChronology14);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(gregorianCalendar8);
        org.junit.Assert.assertNotNull(localTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(localTime13);
        org.junit.Assert.assertNotNull(gJChronology14);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
    }

    @Test
    public void test0897() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0897");
        org.joda.time.Months months0 = org.joda.time.Months.ZERO;
        org.junit.Assert.assertNotNull(months0);
    }

    @Test
    public void test0898() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0898");
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
        mutableDateTime6.addWeekyears(3);
        java.lang.Object obj16 = mutableDateTime6.clone();
        // The following exception was thrown during execution in test generation
        try {
            mutableDateTime6.setDayOfWeek(100);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 100 for dayOfWeek must be in the range [1,7]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(dateTime12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(obj16);
        org.junit.Assert.assertEquals(obj16.toString(), "1973-01-04T00:00:24.000+01:00");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj16), "1973-01-04T00:00:24.000+01:00");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj16), "1973-01-04T00:00:24.000+01:00");
    }

    @Test
    public void test0899() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0899");
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
        org.joda.time.YearMonth yearMonth15 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone16 = null;
        org.joda.time.Interval interval17 = yearMonth15.toInterval(dateTimeZone16);
        org.joda.time.Months months18 = org.joda.time.Months.ONE;
        org.joda.time.Interval interval19 = interval17.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) months18);
        org.joda.time.DateTime dateTime20 = interval17.getEnd();
        org.joda.time.DateTime dateTime22 = dateTime20.minusMillis((int) (byte) 0);
        org.joda.time.Duration duration23 = new org.joda.time.Duration((org.joda.time.ReadableInstant) dateMidnight2, (org.joda.time.ReadableInstant) dateTime22);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(interval8);
        org.junit.Assert.assertNotNull(months9);
        org.junit.Assert.assertNotNull(interval10);
        org.junit.Assert.assertNotNull(dateTime11);
        org.junit.Assert.assertNotNull(dateTime13);
        org.junit.Assert.assertNotNull(interval17);
        org.junit.Assert.assertNotNull(months18);
        org.junit.Assert.assertNotNull(interval19);
        org.junit.Assert.assertNotNull(dateTime20);
        org.junit.Assert.assertNotNull(dateTime22);
    }

    @Test
    public void test0900() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0900");
        org.joda.time.chrono.GJChronology gJChronology0 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology1 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology0);
        org.joda.time.chrono.LenientChronology lenientChronology2 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology0);
        org.joda.time.DateTimeField dateTimeField3 = lenientChronology2.yearOfCentury();
        org.joda.time.DurationField durationField4 = lenientChronology2.eras();
        org.junit.Assert.assertNotNull(gJChronology0);
        org.junit.Assert.assertNotNull(lenientChronology1);
        org.junit.Assert.assertNotNull(lenientChronology2);
        org.junit.Assert.assertNotNull(dateTimeField3);
        org.junit.Assert.assertNotNull(durationField4);
    }

    @Test
    public void test0901() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0901");
        java.lang.StringBuffer stringBuffer0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.format.FormatUtils.appendPaddedInteger(stringBuffer0, 12, 51);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0902() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0902");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone5 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology6 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.LocalDate localDate7 = new org.joda.time.LocalDate(1L, (org.joda.time.DateTimeZone) fixedDateTimeZone5);
        int int8 = localDate7.getYearOfEra();
        int int9 = localDate7.getMonthOfYear();
        org.joda.time.LocalDate.Property property10 = localDate7.year();
        org.junit.Assert.assertNotNull(copticChronology6);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 1970 + "'", int8 == 1970);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 1 + "'", int9 == 1);
        org.junit.Assert.assertNotNull(property10);
    }

    @Test
    public void test0903() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0903");
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
        int int21 = islamicChronology19.getMinimumDaysInFirstWeek();
        org.joda.time.ReadableInterval readableInterval22 = null;
        org.joda.time.Seconds seconds23 = org.joda.time.Seconds.secondsIn(readableInterval22);
        org.joda.time.DurationFieldType durationFieldType24 = seconds23.getFieldType();
        boolean boolean25 = islamicChronology19.equals((java.lang.Object) durationFieldType24);
        org.joda.time.LocalTime localTime26 = new org.joda.time.LocalTime((long) 59, (org.joda.time.Chronology) islamicChronology19);
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
        org.junit.Assert.assertTrue("'" + int21 + "' != '" + 4 + "'", int21 == 4);
        org.junit.Assert.assertNotNull(seconds23);
        org.junit.Assert.assertNotNull(durationFieldType24);
        org.junit.Assert.assertTrue("'" + boolean25 + "' != '" + false + "'", boolean25 == false);
    }

    @Test
    public void test0904() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0904");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.MonthDay monthDay4 = monthDay2.minusDays((int) '4');
        org.joda.time.MonthDay.Property property5 = monthDay2.monthOfYear();
        java.lang.String str6 = property5.toString();
        int int7 = property5.getMaximumValue();
        int int8 = property5.getMaximumValueOverall();
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(monthDay4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "Property[monthOfYear]" + "'", str6, "Property[monthOfYear]");
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 12 + "'", int7 == 12);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 12 + "'", int8 == 12);
    }

    @Test
    public void test0905() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0905");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        int int2 = localDateTime1.getYear();
        org.joda.time.LocalDateTime localDateTime4 = localDateTime1.plusMillis(10);
        org.joda.time.DateTimeZone dateTimeZone5 = null;
        org.joda.time.LocalDateTime localDateTime6 = new org.joda.time.LocalDateTime(dateTimeZone5);
        org.joda.time.LocalDateTime localDateTime8 = localDateTime6.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property9 = localDateTime8.yearOfCentury();
        org.joda.time.LocalDateTime localDateTime10 = property9.withMinimumValue();
        org.joda.time.LocalDateTime localDateTime12 = localDateTime10.withHourOfDay(13);
        org.joda.time.LocalDateTime localDateTime14 = localDateTime10.plusSeconds((int) (byte) 100);
        org.joda.time.LocalDateTime localDateTime16 = localDateTime14.withDayOfMonth(3);
        org.joda.time.Days days17 = org.joda.time.Days.daysBetween((org.joda.time.ReadablePartial) localDateTime4, (org.joda.time.ReadablePartial) localDateTime14);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 2026 + "'", int2 == 2026);
        org.junit.Assert.assertNotNull(localDateTime4);
        org.junit.Assert.assertNotNull(localDateTime8);
        org.junit.Assert.assertNotNull(property9);
        org.junit.Assert.assertNotNull(localDateTime10);
        org.junit.Assert.assertNotNull(localDateTime12);
        org.junit.Assert.assertNotNull(localDateTime14);
        org.junit.Assert.assertNotNull(localDateTime16);
        org.junit.Assert.assertNotNull(days17);
    }

    @Test
    public void test0906() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0906");
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
        org.joda.time.DateTime dateTime16 = dateTime13.minusSeconds(999);
        int int17 = dateTime16.getHourOfDay();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(interval8);
        org.junit.Assert.assertNotNull(months9);
        org.junit.Assert.assertNotNull(interval10);
        org.junit.Assert.assertNotNull(dateTime11);
        org.junit.Assert.assertNotNull(dateTime13);
        org.junit.Assert.assertNotNull(dateTime16);
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + 23 + "'", int17 == 23);
    }

    @Test
    public void test0907() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0907");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime2.plusHours(0);
        org.joda.time.DateTime dateTime6 = dateTime4.minusYears(0);
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(dateTime6);
    }

    @Test
    public void test0908() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0908");
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
        int int14 = mutableDateTime12.getYearOfCentury();
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(dateMidnight10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(mutableDateTime12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 70 + "'", int14 == 70);
    }

    @Test
    public void test0909() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0909");
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
        org.joda.time.PeriodType periodType38 = periodType32.withMinutesRemoved();
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
        org.junit.Assert.assertNotNull(periodType38);
    }

    @Test
    public void test0910() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0910");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        org.joda.time.DateTimeField dateTimeField5 = property4.getField();
        org.joda.time.LocalDateTime localDateTime7 = property4.addWrapFieldToCopy((int) (short) 100);
        org.joda.time.Minutes minutes9 = org.joda.time.Minutes.minutes(2000);
        org.joda.time.LocalDateTime localDateTime10 = localDateTime7.plus((org.joda.time.ReadablePeriod) minutes9);
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(dateTimeField5);
        org.junit.Assert.assertNotNull(localDateTime7);
        org.junit.Assert.assertNotNull(minutes9);
        org.junit.Assert.assertNotNull(localDateTime10);
    }

    @Test
    public void test0911() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0911");
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
        org.joda.time.Partial partial27 = new org.joda.time.Partial((org.joda.time.Chronology) gJChronology23);
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray28 = partial27.getFieldTypes();
        org.joda.time.chrono.GJChronology gJChronology29 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj30 = null;
        boolean boolean31 = gJChronology29.equals(obj30);
        org.joda.time.Partial partial32 = new org.joda.time.Partial((org.joda.time.Chronology) gJChronology29);
        org.joda.time.Partial partial33 = partial27.withChronologyRetainFields((org.joda.time.Chronology) gJChronology29);
        boolean boolean35 = gJChronology29.equals((java.lang.Object) (-3155673464001L));
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
        org.junit.Assert.assertNotNull(dateTimeFieldTypeArray28);
        org.junit.Assert.assertArrayEquals(dateTimeFieldTypeArray28, new org.joda.time.DateTimeFieldType[] {});
        org.junit.Assert.assertNotNull(gJChronology29);
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + false + "'", boolean31 == false);
        org.junit.Assert.assertNotNull(partial33);
        org.junit.Assert.assertTrue("'" + boolean35 + "' != '" + false + "'", boolean35 == false);
    }

    @Test
    public void test0912() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0912");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        int int8 = skipUndoDateTimeField6.get((long) 10);
        org.joda.time.DurationField durationField9 = skipUndoDateTimeField6.getRangeDurationField();
        org.joda.time.field.OffsetDateTimeField offsetDateTimeField11 = new org.joda.time.field.OffsetDateTimeField((org.joda.time.DateTimeField) skipUndoDateTimeField6, 8);
        long long13 = offsetDateTimeField11.roundHalfEven(0L);
        int int15 = offsetDateTimeField11.get((long) 2147483647);
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 2 + "'", int8 == 2);
        org.junit.Assert.assertNotNull(durationField9);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + (-262800000L) + "'", long13 == (-262800000L));
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 13 + "'", int15 == 13);
    }

    @Test
    public void test0913() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0913");
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
        org.joda.time.Partial partial27 = new org.joda.time.Partial((org.joda.time.Chronology) gJChronology23);
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray28 = partial27.getFieldTypes();
        java.util.Locale locale30 = null;
        java.lang.String str31 = partial27.toString("2026", locale30);
        org.joda.time.chrono.GJChronology gJChronology34 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology35 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology34);
        org.joda.time.chrono.LenientChronology lenientChronology36 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology34);
        org.joda.time.Period period37 = new org.joda.time.Period((long) 1, 100L, (org.joda.time.Chronology) gJChronology34);
        org.joda.time.Partial partial39 = partial27.withPeriodAdded((org.joda.time.ReadablePeriod) period37, (int) (short) 100);
        org.joda.time.chrono.GJChronology gJChronology41 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay42 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology41);
        org.joda.time.MutablePeriod mutablePeriod51 = new org.joda.time.MutablePeriod((int) (byte) 1, (int) (short) 0, (int) (byte) -1, (int) (short) -1, (int) (byte) 1, 0, 100, 3);
        org.joda.time.format.PeriodFormatter periodFormatter52 = org.joda.time.format.PeriodFormat.wordBased();
        org.joda.time.format.PeriodParser periodParser53 = periodFormatter52.getParser();
        java.lang.String str54 = mutablePeriod51.toString(periodFormatter52);
        mutablePeriod51.add(0L);
        org.joda.time.Months months57 = org.joda.time.Months.SEVEN;
        org.joda.time.Months months58 = null;
        org.joda.time.Months months59 = months57.minus(months58);
        org.joda.time.DurationFieldType durationFieldType60 = months57.getFieldType();
        boolean boolean61 = mutablePeriod51.isSupported(durationFieldType60);
        org.joda.time.MonthDay monthDay63 = monthDay42.withFieldAdded(durationFieldType60, 20);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Partial partial65 = partial39.withFieldAddWrapped(durationFieldType60, (-1));
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Field 'months' is not supported");
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
        org.junit.Assert.assertNotNull(gJChronology23);
        org.junit.Assert.assertNotNull(dateTimeField24);
        org.junit.Assert.assertNotNull(durationField25);
        org.junit.Assert.assertNotNull(period26);
        org.junit.Assert.assertNotNull(dateTimeFieldTypeArray28);
        org.junit.Assert.assertArrayEquals(dateTimeFieldTypeArray28, new org.joda.time.DateTimeFieldType[] {});
        org.junit.Assert.assertEquals("'" + str31 + "' != '" + "2026" + "'", str31, "2026");
        org.junit.Assert.assertNotNull(gJChronology34);
        org.junit.Assert.assertNotNull(lenientChronology35);
        org.junit.Assert.assertNotNull(lenientChronology36);
        org.junit.Assert.assertNotNull(partial39);
        org.junit.Assert.assertNotNull(gJChronology41);
        org.junit.Assert.assertNotNull(periodFormatter52);
        org.junit.Assert.assertNotNull(periodParser53);
        org.junit.Assert.assertEquals("'" + str54 + "' != '" + "1 year, -1 weeks, -1 days, 1 hour, 100 seconds and 3 milliseconds" + "'", str54, "1 year, -1 weeks, -1 days, 1 hour, 100 seconds and 3 milliseconds");
        org.junit.Assert.assertNotNull(months57);
        org.junit.Assert.assertNotNull(months59);
        org.junit.Assert.assertNotNull(durationFieldType60);
        org.junit.Assert.assertTrue("'" + boolean61 + "' != '" + true + "'", boolean61 == true);
        org.junit.Assert.assertNotNull(monthDay63);
    }

    @Test
    public void test0914() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0914");
        org.joda.time.Period period1 = org.joda.time.Period.seconds(168);
        org.junit.Assert.assertNotNull(period1);
    }

    @Test
    public void test0915() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0915");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.YearMonthDay yearMonthDay5 = dateTime0.toYearMonthDay();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone10 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.DateTime dateTime11 = yearMonthDay5.toDateTimeAtMidnight((org.joda.time.DateTimeZone) fixedDateTimeZone10);
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone16 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.DateTime dateTime17 = yearMonthDay5.toDateTimeAtMidnight((org.joda.time.DateTimeZone) fixedDateTimeZone16);
        // The following exception was thrown during execution in test generation
        try {
            int int19 = yearMonthDay5.getValue(9);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 9");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(yearMonthDay5);
        org.junit.Assert.assertNotNull(dateTime11);
        org.junit.Assert.assertNotNull(dateTime17);
    }

    @Test
    public void test0916() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0916");
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
        org.joda.time.MutableDateTime.Property property40 = mutableDateTime27.yearOfEra();
        boolean boolean42 = mutableDateTime27.isAfter((long) (short) -1);
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
        org.junit.Assert.assertNotNull(property40);
        org.junit.Assert.assertTrue("'" + boolean42 + "' != '" + false + "'", boolean42 == false);
    }

    @Test
    public void test0917() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0917");
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
        org.joda.time.LocalDateTime localDateTime12 = property11.roundHalfEvenCopy();
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(localDateTime8);
        org.junit.Assert.assertNotNull(property9);
        org.junit.Assert.assertNotNull(dateTime10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(localDateTime12);
    }

    @Test
    public void test0918() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0918");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        int int8 = skipUndoDateTimeField6.get((long) 10);
        org.joda.time.DurationField durationField9 = skipUndoDateTimeField6.getRangeDurationField();
        org.joda.time.field.OffsetDateTimeField offsetDateTimeField11 = new org.joda.time.field.OffsetDateTimeField((org.joda.time.DateTimeField) skipUndoDateTimeField6, 8);
        long long13 = offsetDateTimeField11.roundHalfEven(0L);
        long long15 = offsetDateTimeField11.roundHalfFloor((long) (byte) -1);
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 2 + "'", int8 == 2);
        org.junit.Assert.assertNotNull(durationField9);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + (-262800000L) + "'", long13 == (-262800000L));
        org.junit.Assert.assertTrue("'" + long15 + "' != '" + (-262800000L) + "'", long15 == (-262800000L));
    }

    @Test
    public void test0919() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0919");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.Months months4 = org.joda.time.Months.FIVE;
        org.joda.time.Period period5 = period3.minus((org.joda.time.ReadablePeriod) months4);
        org.joda.time.Period period7 = period3.plusMinutes((int) ' ');
        org.joda.time.DurationFieldType[] durationFieldTypeArray8 = period3.getFieldTypes();
        org.joda.time.Period period10 = period3.minusDays((int) (short) 100);
        org.joda.time.MutablePeriod mutablePeriod11 = period3.toMutablePeriod();
        org.joda.time.MutableDateTime mutableDateTime13 = new org.joda.time.MutableDateTime();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone18 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology19 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone18);
        mutableDateTime13.setChronology((org.joda.time.Chronology) gregorianChronology19);
        org.joda.time.DurationField durationField21 = gregorianChronology19.centuries();
        mutablePeriod11.setPeriod(2177280001000L, (org.joda.time.Chronology) gregorianChronology19);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Seconds seconds23 = org.joda.time.Seconds.standardSecondsIn((org.joda.time.ReadablePeriod) mutablePeriod11);
            org.junit.Assert.fail("Expected exception of type java.lang.ArithmeticException; message: Value cannot fit in an int: 2177280001");
        } catch (java.lang.ArithmeticException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(months4);
        org.junit.Assert.assertNotNull(period5);
        org.junit.Assert.assertNotNull(period7);
        org.junit.Assert.assertNotNull(durationFieldTypeArray8);
        org.junit.Assert.assertNotNull(period10);
        org.junit.Assert.assertNotNull(mutablePeriod11);
        org.junit.Assert.assertNotNull(gregorianChronology19);
        org.junit.Assert.assertNotNull(durationField21);
    }

    @Test
    public void test0920() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0920");
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
        org.joda.time.Partial partial11 = new org.joda.time.Partial(dateTimeFieldType9, 9);
        org.joda.time.Partial partial13 = new org.joda.time.Partial(dateTimeFieldType9, 53);
        java.lang.String str14 = partial13.toString();
        org.junit.Assert.assertNotNull(gJChronology0);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(durationField3);
        org.junit.Assert.assertNotNull(gJChronology4);
        org.junit.Assert.assertNotNull(lenientChronology5);
        org.junit.Assert.assertNotNull(lenientChronology6);
        org.junit.Assert.assertNotNull(dateTimeField7);
        org.junit.Assert.assertNotNull(dateTimeFieldType9);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "[yearOfCentury=53]" + "'", str14, "[yearOfCentury=53]");
    }

    @Test
    public void test0921() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0921");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.DateMidnight dateMidnight7 = property5.setCopy((int) (byte) 100);
        int int8 = property5.getMinimumValue();
        org.joda.time.DateMidnight dateMidnight9 = property5.roundHalfFloorCopy();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
        org.junit.Assert.assertNotNull(dateMidnight9);
    }

    @Test
    public void test0922() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0922");
        org.joda.time.Months months0 = org.joda.time.Months.FOUR;
        org.junit.Assert.assertNotNull(months0);
    }

    @Test
    public void test0923() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0923");
        org.joda.time.ReadableInterval readableInterval0 = null;
        org.joda.time.Seconds seconds1 = org.joda.time.Seconds.secondsIn(readableInterval0);
        org.joda.time.DurationFieldType durationFieldType2 = seconds1.getFieldType();
        org.joda.time.IllegalFieldValueException illegalFieldValueException6 = new org.joda.time.IllegalFieldValueException(durationFieldType2, (java.lang.Number) 2700000L, (java.lang.Number) 10L, (java.lang.Number) (short) 1);
        java.lang.Number number7 = illegalFieldValueException6.getUpperBound();
        boolean boolean8 = org.joda.time.IllegalInstantException.isIllegalInstant((java.lang.Throwable) illegalFieldValueException6);
        org.junit.Assert.assertNotNull(seconds1);
        org.junit.Assert.assertNotNull(durationFieldType2);
        org.junit.Assert.assertEquals("'" + number7 + "' != '" + (short) 1 + "'", number7, (short) 1);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0924() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0924");
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
        org.joda.time.MutableDateTime.Property property40 = mutableDateTime27.yearOfEra();
        mutableDateTime27.addMillis((int) (byte) 0);
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
        org.junit.Assert.assertNotNull(property40);
    }

    @Test
    public void test0925() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0925");
        java.lang.ClassLoader classLoader1 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.tz.ZoneInfoProvider zoneInfoProvider2 = new org.joda.time.tz.ZoneInfoProvider("2029-05-02T11:10:56.733", classLoader1);
            org.junit.Assert.fail("Expected exception of type java.io.IOException; message: Resource not found: \"2029-05-02T11:10:56.733/ZoneInfoMap\" ClassLoader: system");
        } catch (java.io.IOException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0926() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0926");
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
        org.joda.time.LocalDateTime.Property property14 = localDateTime13.dayOfMonth();
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(localDateTime8);
        org.junit.Assert.assertNotNull(property9);
        org.junit.Assert.assertNotNull(dateTime10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(localDateTime13);
        org.junit.Assert.assertNotNull(property14);
    }

    @Test
    public void test0927() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0927");
        org.joda.time.MutableDateTime mutableDateTime0 = new org.joda.time.MutableDateTime();
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj2 = null;
        boolean boolean3 = gJChronology1.equals(obj2);
        org.joda.time.DurationField durationField4 = gJChronology1.days();
        org.joda.time.chrono.GJChronology gJChronology5 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology6 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology5);
        org.joda.time.chrono.LenientChronology lenientChronology7 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology5);
        org.joda.time.DateTimeField dateTimeField8 = lenientChronology7.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField9 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology1, dateTimeField8);
        org.joda.time.DateTimeFieldType dateTimeFieldType10 = skipUndoDateTimeField9.getType();
        org.joda.time.Partial partial12 = new org.joda.time.Partial(dateTimeFieldType10, 9);
        org.joda.time.Partial partial14 = new org.joda.time.Partial(dateTimeFieldType10, 53);
        // The following exception was thrown during execution in test generation
        try {
            mutableDateTime0.set(dateTimeFieldType10, 86400000);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 86400000 for yearOfCentury must be in the range [0,99]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(durationField4);
        org.junit.Assert.assertNotNull(gJChronology5);
        org.junit.Assert.assertNotNull(lenientChronology6);
        org.junit.Assert.assertNotNull(lenientChronology7);
        org.junit.Assert.assertNotNull(dateTimeField8);
        org.junit.Assert.assertNotNull(dateTimeFieldType10);
    }

    @Test
    public void test0928() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0928");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime9 = property7.addWrapField((int) (short) -1);
        org.joda.time.MutableDateTime.Property property10 = mutableDateTime9.monthOfYear();
        org.joda.time.Duration duration12 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Duration duration14 = org.joda.time.Duration.standardDays((long) (short) 1);
        org.joda.time.Duration duration16 = duration12.withDurationAdded((org.joda.time.ReadableDuration) duration14, (int) (byte) -1);
        org.joda.time.MutableInterval mutableInterval17 = new org.joda.time.MutableInterval((org.joda.time.ReadableInstant) mutableDateTime9, (org.joda.time.ReadableDuration) duration14);
        mutableInterval17.setInterval((long) '4', 2700000L);
        org.joda.time.Chronology chronology21 = mutableInterval17.getChronology();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.TimeOfDay timeOfDay22 = new org.joda.time.TimeOfDay((java.lang.Object) chronology21);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No partial converter found for type: org.joda.time.chrono.ISOChronology");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(mutableDateTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(duration12);
        org.junit.Assert.assertNotNull(duration14);
        org.junit.Assert.assertNotNull(duration16);
        org.junit.Assert.assertNotNull(chronology21);
    }

    @Test
    public void test0929() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0929");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Duration duration1 = org.joda.time.Duration.standardMinutes(7254093240000000L);
            org.junit.Assert.fail("Expected exception of type java.lang.ArithmeticException; message: Multiplication overflows a long: 7254093240000000 * 60000");
        } catch (java.lang.ArithmeticException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0930() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0930");
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
        int int10 = minutes1.getMinutes();
        org.junit.Assert.assertNotNull(minutes0);
        org.junit.Assert.assertNotNull(minutes1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 2147483647 + "'", int3 == 2147483647);
        org.junit.Assert.assertNotNull(minutes4);
        org.junit.Assert.assertNotNull(minutes5);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertNotNull(minutes9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 2147483647 + "'", int10 == 2147483647);
    }

    @Test
    public void test0931() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0931");
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder0 = new org.joda.time.format.PeriodFormatterBuilder();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder3 = periodFormatterBuilder0.appendPrefix("PeriodType[Millis]", "");
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder4 = periodFormatterBuilder0.printZeroRarelyFirst();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder5 = periodFormatterBuilder4.printZeroNever();
        org.junit.Assert.assertNotNull(periodFormatterBuilder3);
        org.junit.Assert.assertNotNull(periodFormatterBuilder4);
        org.junit.Assert.assertNotNull(periodFormatterBuilder5);
    }

    @Test
    public void test0932() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0932");
        org.joda.time.YearMonth yearMonth1 = new org.joda.time.YearMonth((long) 3);
        org.joda.time.YearMonth yearMonth3 = yearMonth1.plusYears((int) (byte) 10);
        org.joda.time.Seconds seconds4 = org.joda.time.Seconds.TWO;
        org.joda.time.Seconds seconds6 = seconds4.minus((int) (byte) -1);
        org.joda.time.YearMonth yearMonth8 = yearMonth1.withPeriodAdded((org.joda.time.ReadablePeriod) seconds6, 3);
        org.junit.Assert.assertNotNull(yearMonth3);
        org.junit.Assert.assertNotNull(seconds4);
        org.junit.Assert.assertNotNull(seconds6);
        org.junit.Assert.assertNotNull(yearMonth8);
    }

    @Test
    public void test0933() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0933");
        org.joda.time.Hours hours1 = org.joda.time.Hours.hours(100);
        org.joda.time.Duration duration2 = hours1.toStandardDuration();
        org.joda.time.YearMonth yearMonth3 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray4 = yearMonth3.getFieldTypes();
        org.joda.time.YearMonth yearMonth6 = yearMonth3.withMonthOfYear(1);
        org.joda.time.Hours hours8 = org.joda.time.Hours.hours(100);
        org.joda.time.Hours hours10 = hours8.minus((int) (byte) 0);
        org.joda.time.YearMonth yearMonth12 = yearMonth3.withPeriodAdded((org.joda.time.ReadablePeriod) hours10, 40256602);
        boolean boolean13 = hours1.isLessThan(hours10);
        org.junit.Assert.assertNotNull(hours1);
        org.junit.Assert.assertNotNull(duration2);
        org.junit.Assert.assertNotNull(dateTimeFieldTypeArray4);
        org.junit.Assert.assertNotNull(yearMonth6);
        org.junit.Assert.assertNotNull(hours8);
        org.junit.Assert.assertNotNull(hours10);
        org.junit.Assert.assertNotNull(yearMonth12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
    }

    @Test
    public void test0934() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0934");
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
        org.joda.time.TimeOfDay timeOfDay25 = dateTime22.toTimeOfDay();
        org.joda.time.TimeOfDay timeOfDay27 = timeOfDay25.minusMillis(23);
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
        org.junit.Assert.assertNotNull(timeOfDay25);
        org.junit.Assert.assertNotNull(timeOfDay27);
    }

    @Test
    public void test0935() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0935");
        org.joda.time.tz.NameProvider nameProvider0 = org.joda.time.DateTimeZone.getNameProvider();
        org.junit.Assert.assertNotNull(nameProvider0);
    }

    @Test
    public void test0936() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0936");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray3 = monthDay2.getFieldTypes();
        int int4 = monthDay2.getDayOfMonth();
        int int5 = monthDay2.getDayOfMonth();
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(dateTimeFieldTypeArray3);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 1 + "'", int4 == 1);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 1 + "'", int5 == 1);
    }

    @Test
    public void test0937() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0937");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField2 = julianChronology1.clockhourOfDay();
        org.joda.time.Chronology chronology3 = julianChronology1.withUTC();
        java.util.Locale locale4 = null;
        org.joda.time.format.DateTimeParserBucket dateTimeParserBucket6 = new org.joda.time.format.DateTimeParserBucket(2177280001000L, chronology3, locale4, (java.lang.Integer) 40243947);
        org.joda.time.DateTimeZone dateTimeZone7 = dateTimeParserBucket6.getZone();
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(dateTimeField2);
        org.junit.Assert.assertNotNull(chronology3);
        org.junit.Assert.assertNotNull(dateTimeZone7);
    }

    @Test
    public void test0938() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0938");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = property3.addWrapFieldToCopy(61);
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
    }

    @Test
    public void test0939() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0939");
        org.joda.time.Duration duration1 = org.joda.time.Duration.millis((long) 4);
        org.joda.time.DateTime dateTime2 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone3 = null;
        org.joda.time.DateTime dateTime4 = dateTime2.withZoneRetainFields(dateTimeZone3);
        org.joda.time.PeriodType periodType6 = null;
        org.joda.time.Chronology chronology7 = null;
        org.joda.time.Period period8 = new org.joda.time.Period((long) (short) 10, periodType6, chronology7);
        org.joda.time.Months months9 = org.joda.time.Months.FIVE;
        org.joda.time.Period period10 = period8.minus((org.joda.time.ReadablePeriod) months9);
        org.joda.time.Chronology chronology12 = null;
        org.joda.time.DateMidnight dateMidnight13 = new org.joda.time.DateMidnight((long) (short) -1, chronology12);
        org.joda.time.DateMidnight dateMidnight15 = dateMidnight13.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology17 = null;
        org.joda.time.DateMidnight dateMidnight18 = new org.joda.time.DateMidnight((long) (short) -1, chronology17);
        org.joda.time.DateMidnight dateMidnight20 = dateMidnight18.minusMonths((int) (byte) 100);
        int int21 = dateMidnight15.compareTo((org.joda.time.ReadableInstant) dateMidnight18);
        org.joda.time.Duration duration22 = period10.toDurationTo((org.joda.time.ReadableInstant) dateMidnight18);
        org.joda.time.Duration duration23 = duration22.negated();
        org.joda.time.DateTime dateTime24 = dateTime2.minus((org.joda.time.ReadableDuration) duration23);
        org.joda.time.DateTime dateTime26 = dateTime24.minus((long) (short) 1);
        boolean boolean28 = dateTime26.isBefore(0L);
        org.joda.time.Interval interval29 = duration1.toIntervalTo((org.joda.time.ReadableInstant) dateTime26);
        org.junit.Assert.assertNotNull(duration1);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(months9);
        org.junit.Assert.assertNotNull(period10);
        org.junit.Assert.assertNotNull(dateMidnight15);
        org.junit.Assert.assertNotNull(dateMidnight20);
        org.junit.Assert.assertTrue("'" + int21 + "' != '" + (-1) + "'", int21 == (-1));
        org.junit.Assert.assertNotNull(duration22);
        org.junit.Assert.assertNotNull(duration23);
        org.junit.Assert.assertNotNull(dateTime24);
        org.junit.Assert.assertNotNull(dateTime26);
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + false + "'", boolean28 == false);
        org.junit.Assert.assertNotNull(interval29);
    }

    @Test
    public void test0940() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0940");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime9 = property7.addWrapField((int) (short) -1);
        org.joda.time.MutableDateTime.Property property10 = mutableDateTime9.monthOfYear();
        org.joda.time.Duration duration12 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Duration duration14 = org.joda.time.Duration.standardDays((long) (short) 1);
        org.joda.time.Duration duration16 = duration12.withDurationAdded((org.joda.time.ReadableDuration) duration14, (int) (byte) -1);
        org.joda.time.MutableInterval mutableInterval17 = new org.joda.time.MutableInterval((org.joda.time.ReadableInstant) mutableDateTime9, (org.joda.time.ReadableDuration) duration14);
        mutableInterval17.setInterval((long) '4', 2700000L);
        java.lang.Object obj21 = mutableInterval17.clone();
        boolean boolean23 = mutableInterval17.contains(59L);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(mutableDateTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(duration12);
        org.junit.Assert.assertNotNull(duration14);
        org.junit.Assert.assertNotNull(duration16);
        org.junit.Assert.assertNotNull(obj21);
        org.junit.Assert.assertEquals(obj21.toString(), "1970-01-01T01:00:00.052+01:00/1970-01-01T01:45:00.000+01:00");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj21), "1970-01-01T01:00:00.052+01:00/1970-01-01T01:45:00.000+01:00");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj21), "1970-01-01T01:00:00.052+01:00/1970-01-01T01:45:00.000+01:00");
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + true + "'", boolean23 == true);
    }

    @Test
    public void test0941() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0941");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        int int8 = skipUndoDateTimeField6.get((long) 10);
        org.joda.time.DurationField durationField9 = skipUndoDateTimeField6.getRangeDurationField();
        org.joda.time.field.OffsetDateTimeField offsetDateTimeField11 = new org.joda.time.field.OffsetDateTimeField((org.joda.time.DateTimeField) skipUndoDateTimeField6, 8);
        long long13 = offsetDateTimeField11.roundHalfEven(0L);
        long long15 = offsetDateTimeField11.roundHalfCeiling((long) 2000);
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 2 + "'", int8 == 2);
        org.junit.Assert.assertNotNull(durationField9);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + (-262800000L) + "'", long13 == (-262800000L));
        org.junit.Assert.assertTrue("'" + long15 + "' != '" + (-262800000L) + "'", long15 == (-262800000L));
    }

    @Test
    public void test0942() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0942");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime2.plusHours(0);
        org.joda.time.LocalDateTime localDateTime5 = dateTime2.toLocalDateTime();
        org.joda.time.DateTimeComparator dateTimeComparator6 = org.joda.time.DateTimeComparator.getDateOnlyInstance();
        org.joda.time.DateTimeComparator dateTimeComparator7 = org.joda.time.DateTimeComparator.getTimeOnlyInstance();
        java.util.Comparator<java.lang.Object> objComparator8 = dateTimeComparator6.thenComparing((java.util.Comparator<java.lang.Object>) dateTimeComparator7);
        org.joda.time.DateTimeFieldType dateTimeFieldType9 = dateTimeComparator7.getUpperLimit();
        org.joda.time.LocalDateTime.Property property10 = localDateTime5.property(dateTimeFieldType9);
        org.joda.time.LocalDateTime.Property property11 = localDateTime5.weekyear();
        org.joda.time.LocalDateTime localDateTime13 = localDateTime5.minusDays(0);
        int int14 = localDateTime13.size();
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(localDateTime5);
        org.junit.Assert.assertNotNull(dateTimeComparator6);
        org.junit.Assert.assertNotNull(dateTimeComparator7);
        org.junit.Assert.assertNotNull(objComparator8);
        org.junit.Assert.assertNotNull(dateTimeFieldType9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(localDateTime13);
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 4 + "'", int14 == 4);
    }

    @Test
    public void test0943() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0943");
        org.joda.time.LocalTime localTime1 = new org.joda.time.LocalTime((long) (-1));
        org.joda.time.LocalTime localTime3 = localTime1.withSecondOfMinute(1);
        org.joda.time.LocalTime localTime5 = localTime1.plusSeconds(0);
        int int6 = localTime5.getHourOfDay();
        org.junit.Assert.assertNotNull(localTime3);
        org.junit.Assert.assertNotNull(localTime5);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
    }

    @Test
    public void test0944() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0944");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField1 = julianChronology0.clockhourOfDay();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj3 = null;
        boolean boolean4 = gJChronology2.equals(obj3);
        org.joda.time.DurationField durationField5 = gJChronology2.days();
        org.joda.time.chrono.GJChronology gJChronology6 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology7 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology6);
        org.joda.time.chrono.LenientChronology lenientChronology8 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology6);
        org.joda.time.DateTimeField dateTimeField9 = lenientChronology8.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField10 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology2, dateTimeField9);
        org.joda.time.DateTimeFieldType dateTimeFieldType11 = skipUndoDateTimeField10.getType();
        org.joda.time.field.DividedDateTimeField dividedDateTimeField13 = new org.joda.time.field.DividedDateTimeField(dateTimeField1, dateTimeFieldType11, (int) 'a');
        org.joda.time.DurationField durationField14 = dividedDateTimeField13.getLeapDurationField();
        java.util.Locale locale15 = null;
        int int16 = dividedDateTimeField13.getMaximumTextLength(locale15);
        long long19 = dividedDateTimeField13.getDifferenceAsLong((-3155673464001L), (long) 1000);
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(dateTimeField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(durationField5);
        org.junit.Assert.assertNotNull(gJChronology6);
        org.junit.Assert.assertNotNull(lenientChronology7);
        org.junit.Assert.assertNotNull(lenientChronology8);
        org.junit.Assert.assertNotNull(dateTimeField9);
        org.junit.Assert.assertNotNull(dateTimeFieldType11);
        org.junit.Assert.assertNull(durationField14);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 1 + "'", int16 == 1);
        org.junit.Assert.assertTrue("'" + long19 + "' != '" + (-9036L) + "'", long19 == (-9036L));
    }

    @Test
    public void test0945() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0945");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField2 = julianChronology1.clockhourOfDay();
        org.joda.time.Chronology chronology3 = julianChronology1.withUTC();
        java.util.Locale locale4 = null;
        org.joda.time.format.DateTimeParserBucket dateTimeParserBucket6 = new org.joda.time.format.DateTimeParserBucket(2177280001000L, chronology3, locale4, (java.lang.Integer) 40243947);
        java.util.Locale locale7 = dateTimeParserBucket6.getLocale();
        org.joda.time.chrono.JulianChronology julianChronology8 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField9 = julianChronology8.weeks();
        org.joda.time.chrono.GJChronology gJChronology10 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology11 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology10);
        org.joda.time.DateTimeField dateTimeField12 = gJChronology10.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField14 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology8, dateTimeField12, (int) '4');
        java.lang.String str16 = skipUndoDateTimeField14.getAsText((long) 40256602);
        dateTimeParserBucket6.saveField((org.joda.time.DateTimeField) skipUndoDateTimeField14, 1);
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(dateTimeField2);
        org.junit.Assert.assertNotNull(chronology3);
        org.junit.Assert.assertNotNull(locale7);
        org.junit.Assert.assertEquals(locale7.toString(), "en_US");
        org.junit.Assert.assertNotNull(julianChronology8);
        org.junit.Assert.assertNotNull(durationField9);
        org.junit.Assert.assertNotNull(gJChronology10);
        org.junit.Assert.assertNotNull(lenientChronology11);
        org.junit.Assert.assertNotNull(dateTimeField12);
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "1" + "'", str16, "1");
    }

    @Test
    public void test0946() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0946");
        org.joda.time.TimeOfDay timeOfDay1 = new org.joda.time.TimeOfDay((long) 100);
        org.joda.time.TimeOfDay timeOfDay3 = timeOfDay1.minusMinutes((int) (short) 1);
        org.joda.time.TimeOfDay.Property property4 = timeOfDay3.secondOfMinute();
        org.joda.time.TimeOfDay timeOfDay5 = property4.getTimeOfDay();
        org.joda.time.TimeOfDay timeOfDay7 = property4.addToCopy(2147483647);
        org.junit.Assert.assertNotNull(timeOfDay3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(timeOfDay5);
        org.junit.Assert.assertNotNull(timeOfDay7);
    }

    @Test
    public void test0947() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0947");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Interval interval2 = yearMonth0.toInterval(dateTimeZone1);
        org.joda.time.Months months3 = org.joda.time.Months.ONE;
        org.joda.time.Interval interval4 = interval2.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) months3);
        org.joda.time.DateTime dateTime5 = interval2.getEnd();
        org.joda.time.DateTime dateTime7 = dateTime5.minusMillis((int) (byte) 0);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str9 = dateTime7.toString("CopticChronology[2026-08-06T11:10:36.726]");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Illegal pattern component: o");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertNotNull(months3);
        org.junit.Assert.assertNotNull(interval4);
        org.junit.Assert.assertNotNull(dateTime5);
        org.junit.Assert.assertNotNull(dateTime7);
    }

    @Test
    public void test0948() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0948");
        org.joda.time.MutableDateTime mutableDateTime1 = org.joda.time.MutableDateTime.parse("40258294");
        mutableDateTime1.setTime(30412800000L);
        org.junit.Assert.assertNotNull(mutableDateTime1);
    }

    @Test
    public void test0949() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0949");
        org.joda.time.Duration duration1 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Period period3 = org.joda.time.Period.weeks((int) (short) 100);
        int int4 = period3.getDays();
        org.joda.time.Period period6 = period3.minusWeeks(100);
        org.joda.time.Duration duration7 = period6.toStandardDuration();
        org.joda.time.Duration duration9 = duration1.withDurationAdded((org.joda.time.ReadableDuration) duration7, (int) '4');
        org.junit.Assert.assertNotNull(duration1);
        org.junit.Assert.assertNotNull(period3);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertNotNull(period6);
        org.junit.Assert.assertNotNull(duration7);
        org.junit.Assert.assertNotNull(duration9);
    }

    @Test
    public void test0950() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0950");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalTime localTime2 = new org.joda.time.LocalTime((-1), (-1));
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value -1 for hourOfDay must be in the range [0,23]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0951() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0951");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        int int8 = skipUndoDateTimeField6.get((long) 10);
        org.joda.time.DurationField durationField9 = skipUndoDateTimeField6.getRangeDurationField();
        org.joda.time.field.OffsetDateTimeField offsetDateTimeField11 = new org.joda.time.field.OffsetDateTimeField((org.joda.time.DateTimeField) skipUndoDateTimeField6, 8);
        long long13 = offsetDateTimeField11.roundHalfEven(0L);
        int int15 = offsetDateTimeField11.getLeapAmount((long) 2026);
        org.joda.time.DateTimeFieldType dateTimeFieldType16 = offsetDateTimeField11.getType();
        long long18 = offsetDateTimeField11.roundFloor((long) 53);
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 2 + "'", int8 == 2);
        org.junit.Assert.assertNotNull(durationField9);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + (-262800000L) + "'", long13 == (-262800000L));
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 0 + "'", int15 == 0);
        org.junit.Assert.assertNotNull(dateTimeFieldType16);
        org.junit.Assert.assertTrue("'" + long18 + "' != '" + (-262800000L) + "'", long18 == (-262800000L));
    }

    @Test
    public void test0952() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0952");
        org.joda.time.LocalTime localTime1 = new org.joda.time.LocalTime((long) (-1));
        org.joda.time.LocalTime localTime3 = localTime1.withSecondOfMinute(1);
        org.joda.time.LocalTime localTime5 = localTime1.plusSeconds(0);
        org.joda.time.LocalTime localTime7 = localTime1.plusSeconds(86400000);
        org.joda.time.LocalTime.Property property8 = localTime1.hourOfDay();
        org.joda.time.LocalTime localTime10 = property8.addNoWrapToCopy((int) (short) 1);
        org.junit.Assert.assertNotNull(localTime3);
        org.junit.Assert.assertNotNull(localTime5);
        org.junit.Assert.assertNotNull(localTime7);
        org.junit.Assert.assertNotNull(property8);
        org.junit.Assert.assertNotNull(localTime10);
    }

    @Test
    public void test0953() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0953");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField1 = julianChronology0.clockhourOfDay();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj3 = null;
        boolean boolean4 = gJChronology2.equals(obj3);
        org.joda.time.DurationField durationField5 = gJChronology2.days();
        org.joda.time.chrono.GJChronology gJChronology6 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology7 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology6);
        org.joda.time.chrono.LenientChronology lenientChronology8 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology6);
        org.joda.time.DateTimeField dateTimeField9 = lenientChronology8.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField10 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology2, dateTimeField9);
        org.joda.time.DateTimeFieldType dateTimeFieldType11 = skipUndoDateTimeField10.getType();
        org.joda.time.field.DividedDateTimeField dividedDateTimeField13 = new org.joda.time.field.DividedDateTimeField(dateTimeField1, dateTimeFieldType11, (int) 'a');
        org.joda.time.DurationField durationField14 = dividedDateTimeField13.getRangeDurationField();
        int int15 = dividedDateTimeField13.getMaximumValue();
        int int17 = dividedDateTimeField13.get(3600059L);
        // The following exception was thrown during execution in test generation
        try {
            long long20 = dividedDateTimeField13.addWrapField((long) ' ', 2);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: MIN > MAX");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(dateTimeField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(durationField5);
        org.junit.Assert.assertNotNull(gJChronology6);
        org.junit.Assert.assertNotNull(lenientChronology7);
        org.junit.Assert.assertNotNull(lenientChronology8);
        org.junit.Assert.assertNotNull(dateTimeField9);
        org.junit.Assert.assertNotNull(dateTimeFieldType11);
        org.junit.Assert.assertNotNull(durationField14);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 0 + "'", int15 == 0);
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + 0 + "'", int17 == 0);
    }

    @Test
    public void test0954() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0954");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone11 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology12 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone11);
        java.lang.String str13 = copticChronology12.toString();
        org.joda.time.DateTimeField dateTimeField14 = copticChronology12.clockhourOfDay();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone20 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology21 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone20);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType22 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology24 = null;
        org.joda.time.DateMidnight dateMidnight25 = new org.joda.time.DateMidnight((long) (short) -1, chronology24);
        org.joda.time.DateMidnight dateMidnight27 = dateMidnight25.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property28 = dateMidnight25.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime29 = dateMidnight25.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property30 = mutableDateTime29.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime32 = property30.addWrapField((int) (short) -1);
        boolean boolean33 = leapYearPatternType22.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology34 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone20, leapYearPatternType22);
        org.joda.time.MutableDateTime mutableDateTime35 = new org.joda.time.MutableDateTime((long) (byte) 10, (org.joda.time.DateTimeZone) fixedDateTimeZone20);
        boolean boolean36 = copticChronology12.equals((java.lang.Object) mutableDateTime35);
        org.joda.time.DurationField durationField37 = copticChronology12.halfdays();
        org.joda.time.DateTimeZone dateTimeZone38 = null;
        org.joda.time.LocalDateTime localDateTime39 = new org.joda.time.LocalDateTime(dateTimeZone38);
        int int40 = localDateTime39.getYear();
        org.joda.time.LocalDateTime localDateTime42 = localDateTime39.plusMillis(10);
        org.joda.time.chrono.GJChronology gJChronology43 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj44 = null;
        boolean boolean45 = gJChronology43.equals(obj44);
        org.joda.time.DurationField durationField46 = gJChronology43.days();
        org.joda.time.chrono.GJChronology gJChronology47 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology48 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology47);
        org.joda.time.chrono.LenientChronology lenientChronology49 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology47);
        org.joda.time.DateTimeField dateTimeField50 = lenientChronology49.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField51 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology43, dateTimeField50);
        org.joda.time.DateTimeFieldType dateTimeFieldType52 = skipUndoDateTimeField51.getType();
        int int53 = skipUndoDateTimeField51.getMinimumValue();
        java.lang.String str54 = skipUndoDateTimeField51.getName();
        org.joda.time.DateTimeFieldType dateTimeFieldType55 = skipUndoDateTimeField51.getType();
        org.joda.time.LocalDateTime localDateTime57 = localDateTime39.withField(dateTimeFieldType55, 24);
        org.joda.time.field.RemainderDateTimeField remainderDateTimeField59 = new org.joda.time.field.RemainderDateTimeField(dateTimeField4, durationField37, dateTimeFieldType55, (int) (byte) 10);
        org.joda.time.chrono.GJChronology gJChronology60 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj61 = null;
        boolean boolean62 = gJChronology60.equals(obj61);
        org.joda.time.DurationField durationField63 = gJChronology60.days();
        org.joda.time.chrono.GJChronology gJChronology64 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology65 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology64);
        org.joda.time.chrono.LenientChronology lenientChronology66 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology64);
        org.joda.time.DateTimeField dateTimeField67 = lenientChronology66.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField68 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology60, dateTimeField67);
        org.joda.time.DateTimeFieldType dateTimeFieldType69 = skipUndoDateTimeField68.getType();
        org.joda.time.field.DividedDateTimeField dividedDateTimeField70 = new org.joda.time.field.DividedDateTimeField(remainderDateTimeField59, dateTimeFieldType69);
        java.lang.String str71 = remainderDateTimeField59.toString();
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertNotNull(copticChronology12);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "CopticChronology[2026-08-06T11:10:36.726]" + "'", str13, "CopticChronology[2026-08-06T11:10:36.726]");
        org.junit.Assert.assertNotNull(dateTimeField14);
        org.junit.Assert.assertNotNull(copticChronology21);
        org.junit.Assert.assertNotNull(leapYearPatternType22);
        org.junit.Assert.assertNotNull(dateMidnight27);
        org.junit.Assert.assertNotNull(property28);
        org.junit.Assert.assertNotNull(mutableDateTime29);
        org.junit.Assert.assertNotNull(property30);
        org.junit.Assert.assertNotNull(mutableDateTime32);
        org.junit.Assert.assertTrue("'" + boolean33 + "' != '" + false + "'", boolean33 == false);
        org.junit.Assert.assertNotNull(islamicChronology34);
        org.junit.Assert.assertTrue("'" + boolean36 + "' != '" + false + "'", boolean36 == false);
        org.junit.Assert.assertNotNull(durationField37);
        org.junit.Assert.assertTrue("'" + int40 + "' != '" + 2026 + "'", int40 == 2026);
        org.junit.Assert.assertNotNull(localDateTime42);
        org.junit.Assert.assertNotNull(gJChronology43);
        org.junit.Assert.assertTrue("'" + boolean45 + "' != '" + false + "'", boolean45 == false);
        org.junit.Assert.assertNotNull(durationField46);
        org.junit.Assert.assertNotNull(gJChronology47);
        org.junit.Assert.assertNotNull(lenientChronology48);
        org.junit.Assert.assertNotNull(lenientChronology49);
        org.junit.Assert.assertNotNull(dateTimeField50);
        org.junit.Assert.assertNotNull(dateTimeFieldType52);
        org.junit.Assert.assertTrue("'" + int53 + "' != '" + 0 + "'", int53 == 0);
        org.junit.Assert.assertEquals("'" + str54 + "' != '" + "yearOfCentury" + "'", str54, "yearOfCentury");
        org.junit.Assert.assertNotNull(dateTimeFieldType55);
        org.junit.Assert.assertNotNull(localDateTime57);
        org.junit.Assert.assertNotNull(gJChronology60);
        org.junit.Assert.assertTrue("'" + boolean62 + "' != '" + false + "'", boolean62 == false);
        org.junit.Assert.assertNotNull(durationField63);
        org.junit.Assert.assertNotNull(gJChronology64);
        org.junit.Assert.assertNotNull(lenientChronology65);
        org.junit.Assert.assertNotNull(lenientChronology66);
        org.junit.Assert.assertNotNull(dateTimeField67);
        org.junit.Assert.assertNotNull(dateTimeFieldType69);
        org.junit.Assert.assertEquals("'" + str71 + "' != '" + "DateTimeField[yearOfCentury]" + "'", str71, "DateTimeField[yearOfCentury]");
    }

    @Test
    public void test0955() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0955");
        org.joda.time.LocalTime localTime1 = new org.joda.time.LocalTime((long) 59);
        org.joda.time.LocalTime localTime3 = localTime1.plusSeconds(29);
        org.junit.Assert.assertNotNull(localTime3);
    }

    @Test
    public void test0956() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0956");
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder0 = new org.joda.time.format.PeriodFormatterBuilder();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder3 = periodFormatterBuilder0.appendPrefix("PeriodType[Millis]", "");
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder5 = periodFormatterBuilder3.minimumPrintedDigits((int) (short) -1);
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder6 = periodFormatterBuilder5.appendMinutes();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder7 = periodFormatterBuilder5.printZeroRarelyLast();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder8 = periodFormatterBuilder7.printZeroIfSupported();
        org.junit.Assert.assertNotNull(periodFormatterBuilder3);
        org.junit.Assert.assertNotNull(periodFormatterBuilder5);
        org.junit.Assert.assertNotNull(periodFormatterBuilder6);
        org.junit.Assert.assertNotNull(periodFormatterBuilder7);
        org.junit.Assert.assertNotNull(periodFormatterBuilder8);
    }

    @Test
    public void test0957() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0957");
        org.joda.time.LocalTime localTime1 = new org.joda.time.LocalTime((long) (-1));
        org.joda.time.LocalTime localTime3 = localTime1.withSecondOfMinute(1);
        org.joda.time.LocalTime localTime5 = localTime1.plusSeconds(0);
        org.joda.time.LocalTime localTime7 = localTime1.plusSeconds(86400000);
        org.joda.time.LocalTime localTime9 = localTime1.minusHours((int) (short) 1);
        org.junit.Assert.assertNotNull(localTime3);
        org.junit.Assert.assertNotNull(localTime5);
        org.junit.Assert.assertNotNull(localTime7);
        org.junit.Assert.assertNotNull(localTime9);
    }

    @Test
    public void test0958() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0958");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime9 = property7.addWrapField((int) (short) -1);
        org.joda.time.MutableDateTime.Property property10 = mutableDateTime9.monthOfYear();
        org.joda.time.Duration duration12 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Duration duration14 = org.joda.time.Duration.standardDays((long) (short) 1);
        org.joda.time.Duration duration16 = duration12.withDurationAdded((org.joda.time.ReadableDuration) duration14, (int) (byte) -1);
        org.joda.time.MutableInterval mutableInterval17 = new org.joda.time.MutableInterval((org.joda.time.ReadableInstant) mutableDateTime9, (org.joda.time.ReadableDuration) duration14);
        mutableInterval17.setInterval((long) '4', 2700000L);
        org.joda.time.Chronology chronology22 = null;
        org.joda.time.DateMidnight dateMidnight23 = new org.joda.time.DateMidnight((long) (short) -1, chronology22);
        org.joda.time.DateMidnight dateMidnight25 = dateMidnight23.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology27 = null;
        org.joda.time.DateMidnight dateMidnight28 = new org.joda.time.DateMidnight((long) (short) -1, chronology27);
        org.joda.time.DateMidnight dateMidnight30 = dateMidnight28.minusMonths((int) (byte) 100);
        int int31 = dateMidnight25.compareTo((org.joda.time.ReadableInstant) dateMidnight28);
        org.joda.time.MutableDateTime mutableDateTime32 = dateMidnight28.toMutableDateTime();
        org.joda.time.ReadableInstant readableInstant33 = null;
        mutableInterval17.setInterval((org.joda.time.ReadableInstant) dateMidnight28, readableInstant33);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(mutableDateTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(duration12);
        org.junit.Assert.assertNotNull(duration14);
        org.junit.Assert.assertNotNull(duration16);
        org.junit.Assert.assertNotNull(dateMidnight25);
        org.junit.Assert.assertNotNull(dateMidnight30);
        org.junit.Assert.assertTrue("'" + int31 + "' != '" + (-1) + "'", int31 == (-1));
        org.junit.Assert.assertNotNull(mutableDateTime32);
    }

    @Test
    public void test0959() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0959");
        org.joda.time.IllegalFieldValueException illegalFieldValueException2 = new org.joda.time.IllegalFieldValueException("[yearOfCentury=53]", "1");
    }

    @Test
    public void test0960() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0960");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Interval interval2 = yearMonth0.toInterval(dateTimeZone1);
        org.joda.time.YearMonth yearMonth4 = yearMonth0.plusMonths((int) 'a');
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray5 = yearMonth0.getFieldTypes();
        java.util.Locale locale7 = null;
        java.lang.String str8 = yearMonth0.toString("2147483647", locale7);
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertNotNull(yearMonth4);
        org.junit.Assert.assertNotNull(dateTimeFieldTypeArray5);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "2147483647" + "'", str8, "2147483647");
    }

    @Test
    public void test0961() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0961");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        int int8 = skipUndoDateTimeField6.get((long) 10);
        org.joda.time.DurationField durationField9 = skipUndoDateTimeField6.getRangeDurationField();
        org.joda.time.field.OffsetDateTimeField offsetDateTimeField11 = new org.joda.time.field.OffsetDateTimeField((org.joda.time.DateTimeField) skipUndoDateTimeField6, 8);
        long long13 = offsetDateTimeField11.roundHalfEven(0L);
        int int15 = offsetDateTimeField11.getLeapAmount((long) 2026);
        org.joda.time.DateTimeFieldType dateTimeFieldType16 = offsetDateTimeField11.getType();
        int int18 = offsetDateTimeField11.get((long) 86400);
        org.joda.time.DateTime dateTime19 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone20 = null;
        org.joda.time.DateTime dateTime21 = dateTime19.withZoneRetainFields(dateTimeZone20);
        org.joda.time.DateTime dateTime23 = dateTime19.withYear((int) (byte) 0);
        org.joda.time.YearMonthDay yearMonthDay24 = dateTime19.toYearMonthDay();
        org.joda.time.PeriodType periodType26 = null;
        org.joda.time.Chronology chronology27 = null;
        org.joda.time.Period period28 = new org.joda.time.Period((long) (short) 10, periodType26, chronology27);
        org.joda.time.Months months29 = org.joda.time.Months.FIVE;
        org.joda.time.Period period30 = period28.minus((org.joda.time.ReadablePeriod) months29);
        org.joda.time.Period period32 = period28.plusMillis((-1));
        org.joda.time.Period period33 = period28.toPeriod();
        org.joda.time.Period period35 = period33.minusSeconds((int) (byte) 1);
        org.joda.time.Period period37 = period33.withDays((int) (short) 100);
        org.joda.time.YearMonthDay yearMonthDay38 = yearMonthDay24.minus((org.joda.time.ReadablePeriod) period33);
        org.joda.time.YearMonthDay.Property property39 = yearMonthDay24.monthOfYear();
        org.joda.time.YearMonthDay yearMonthDay40 = property39.withMaximumValue();
        org.joda.time.YearMonthDay.Property property41 = yearMonthDay40.dayOfMonth();
        int int42 = offsetDateTimeField11.getMaximumValue((org.joda.time.ReadablePartial) yearMonthDay40);
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 2 + "'", int8 == 2);
        org.junit.Assert.assertNotNull(durationField9);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + (-262800000L) + "'", long13 == (-262800000L));
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 0 + "'", int15 == 0);
        org.junit.Assert.assertNotNull(dateTimeFieldType16);
        org.junit.Assert.assertTrue("'" + int18 + "' != '" + 10 + "'", int18 == 10);
        org.junit.Assert.assertNotNull(dateTime21);
        org.junit.Assert.assertNotNull(dateTime23);
        org.junit.Assert.assertNotNull(yearMonthDay24);
        org.junit.Assert.assertNotNull(months29);
        org.junit.Assert.assertNotNull(period30);
        org.junit.Assert.assertNotNull(period32);
        org.junit.Assert.assertNotNull(period33);
        org.junit.Assert.assertNotNull(period35);
        org.junit.Assert.assertNotNull(period37);
        org.junit.Assert.assertNotNull(yearMonthDay38);
        org.junit.Assert.assertNotNull(property39);
        org.junit.Assert.assertNotNull(yearMonthDay40);
        org.junit.Assert.assertNotNull(property41);
        org.junit.Assert.assertTrue("'" + int42 + "' != '" + 61 + "'", int42 == 61);
    }

    @Test
    public void test0962() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0962");
        org.joda.time.IllegalInstantException illegalInstantException2 = new org.joda.time.IllegalInstantException((long) 1970, "+00:00:00.100");
        boolean boolean3 = org.joda.time.IllegalInstantException.isIllegalInstant((java.lang.Throwable) illegalInstantException2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + true + "'", boolean3 == true);
    }

    @Test
    public void test0963() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0963");
        org.joda.time.Hours hours1 = org.joda.time.Hours.hours(100);
        org.joda.time.Duration duration2 = hours1.toStandardDuration();
        org.joda.time.Duration duration3 = hours1.toStandardDuration();
        org.junit.Assert.assertNotNull(hours1);
        org.junit.Assert.assertNotNull(duration2);
        org.junit.Assert.assertNotNull(duration3);
    }

    @Test
    public void test0964() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0964");
        org.joda.time.Duration duration0 = org.joda.time.Duration.ZERO;
        org.junit.Assert.assertNotNull(duration0);
    }

    @Test
    public void test0965() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0965");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = localDate2.withWeekyear((int) (byte) 100);
        org.joda.time.LocalDate localDate7 = localDate2.withWeekyear((int) (short) -1);
        org.joda.time.LocalDate.Property property8 = localDate2.yearOfEra();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDate localDate10 = property8.setCopy("2147483647");
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 2147483647 for yearOfEra must be in the range [1,292272992]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(localDate7);
        org.junit.Assert.assertNotNull(property8);
    }

    @Test
    public void test0966() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0966");
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
        org.joda.time.DurationField durationField27 = gJChronology23.years();
        org.joda.time.DateTimeField dateTimeField28 = gJChronology23.yearOfEra();
        org.joda.time.field.DelegatedDateTimeField delegatedDateTimeField29 = new org.joda.time.field.DelegatedDateTimeField(dateTimeField28);
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
        org.junit.Assert.assertNotNull(durationField27);
        org.junit.Assert.assertNotNull(dateTimeField28);
    }

    @Test
    public void test0967() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0967");
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
        org.joda.time.TimeOfDay.Property property22 = timeOfDay9.hourOfDay();
        org.joda.time.chrono.JulianChronology julianChronology25 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField26 = julianChronology25.clockhourOfDay();
        org.joda.time.Chronology chronology27 = julianChronology25.withUTC();
        java.util.Locale locale28 = null;
        org.joda.time.format.DateTimeParserBucket dateTimeParserBucket30 = new org.joda.time.format.DateTimeParserBucket(2177280001000L, chronology27, locale28, (java.lang.Integer) 40243947);
        java.util.Locale locale31 = dateTimeParserBucket30.getLocale();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.TimeOfDay timeOfDay32 = property22.setCopy("org.joda.time.IllegalFieldValueException: Value \"CopticChronology[2026-08-06T11:10:36.726]\" for PT60S is not supported", locale31);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value \"org.joda.time.IllegalFieldValueException: Value \"CopticChronology[2026-08-06T11:10:36.726]\" for PT60S is not supported\" for hourOfDay is not supported");
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
        org.junit.Assert.assertNotNull(property22);
        org.junit.Assert.assertNotNull(julianChronology25);
        org.junit.Assert.assertNotNull(dateTimeField26);
        org.junit.Assert.assertNotNull(chronology27);
        org.junit.Assert.assertNotNull(locale31);
        org.junit.Assert.assertEquals(locale31.toString(), "en_US");
    }

    @Test
    public void test0968() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0968");
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
        org.joda.time.LocalDate.Property property27 = localDate25.centuryOfEra();
        org.junit.Assert.assertNotNull(converterManager0);
        org.junit.Assert.assertNull(partialConverter2);
        org.junit.Assert.assertNull(partialConverter4);
        org.junit.Assert.assertNotNull(instant5);
        org.junit.Assert.assertNotNull(instant8);
        org.junit.Assert.assertNotNull(durationConverter9);
        org.junit.Assert.assertNotNull(julianChronology11);
        org.junit.Assert.assertNotNull(property13);
        org.junit.Assert.assertNotNull(localDate15);
        org.junit.Assert.assertNotNull(dateMidnight20);
        org.junit.Assert.assertNotNull(property21);
        org.junit.Assert.assertNotNull(mutableDateTime22);
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
        org.junit.Assert.assertNotNull(localDate25);
        org.junit.Assert.assertNotNull(partialConverter26);
        org.junit.Assert.assertNotNull(property27);
    }

    @Test
    public void test0969() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0969");
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
        org.joda.time.DateTime dateTime29 = dateTime0.withDurationAdded((org.joda.time.ReadableDuration) duration6, 32);
        long long30 = duration6.getStandardMinutes();
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(duration6);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(dateMidnight16);
        org.junit.Assert.assertNotNull(dateMidnight21);
        org.junit.Assert.assertTrue("'" + int22 + "' != '" + (-1) + "'", int22 == (-1));
        org.junit.Assert.assertNotNull(minutes23);
        org.junit.Assert.assertNotNull(dateMidnight26);
        org.junit.Assert.assertNotNull(dateTime29);
        org.junit.Assert.assertTrue("'" + long30 + "' != '" + 1L + "'", long30 == 1L);
    }

    @Test
    public void test0970() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0970");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.DurationField durationField3 = gJChronology1.months();
        org.joda.time.DurationField durationField4 = gJChronology1.years();
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(durationField3);
        org.junit.Assert.assertNotNull(durationField4);
    }

    @Test
    public void test0971() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0971");
        org.joda.time.format.PeriodFormatter periodFormatter0 = org.joda.time.format.PeriodFormat.wordBased();
        org.joda.time.format.PeriodParser periodParser1 = periodFormatter0.getParser();
        boolean boolean2 = periodFormatter0.isPrinter();
        org.joda.time.MutablePeriod mutablePeriod11 = new org.joda.time.MutablePeriod((int) (byte) 1, (int) (short) 0, (int) (byte) -1, (int) (short) -1, (int) (byte) 1, 0, 100, 3);
        org.joda.time.format.PeriodFormatter periodFormatter12 = org.joda.time.format.PeriodFormat.wordBased();
        org.joda.time.format.PeriodParser periodParser13 = periodFormatter12.getParser();
        java.lang.String str14 = mutablePeriod11.toString(periodFormatter12);
        org.joda.time.Chronology chronology16 = null;
        org.joda.time.DateMidnight dateMidnight17 = new org.joda.time.DateMidnight((long) (short) -1, chronology16);
        org.joda.time.MutableDateTime mutableDateTime18 = dateMidnight17.toMutableDateTime();
        org.joda.time.PeriodType periodType20 = null;
        org.joda.time.Chronology chronology21 = null;
        org.joda.time.Period period22 = new org.joda.time.Period((long) (short) 10, periodType20, chronology21);
        org.joda.time.Months months23 = org.joda.time.Months.FIVE;
        org.joda.time.Period period24 = period22.minus((org.joda.time.ReadablePeriod) months23);
        org.joda.time.Chronology chronology26 = null;
        org.joda.time.DateMidnight dateMidnight27 = new org.joda.time.DateMidnight((long) (short) -1, chronology26);
        org.joda.time.DateMidnight dateMidnight29 = dateMidnight27.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology31 = null;
        org.joda.time.DateMidnight dateMidnight32 = new org.joda.time.DateMidnight((long) (short) -1, chronology31);
        org.joda.time.DateMidnight dateMidnight34 = dateMidnight32.minusMonths((int) (byte) 100);
        int int35 = dateMidnight29.compareTo((org.joda.time.ReadableInstant) dateMidnight32);
        org.joda.time.Duration duration36 = period24.toDurationTo((org.joda.time.ReadableInstant) dateMidnight32);
        org.joda.time.DateMidnight dateMidnight37 = dateMidnight17.minus((org.joda.time.ReadableDuration) duration36);
        org.joda.time.Duration duration39 = duration36.minus((long) 3600);
        org.joda.time.Duration duration41 = org.joda.time.Duration.standardDays((long) (short) 1);
        org.joda.time.Chronology chronology43 = null;
        org.joda.time.DateMidnight dateMidnight44 = new org.joda.time.DateMidnight((long) (short) -1, chronology43);
        org.joda.time.DateMidnight dateMidnight46 = dateMidnight44.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight49 = dateMidnight44.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight51 = dateMidnight49.withYear(100);
        org.joda.time.PeriodType periodType53 = null;
        org.joda.time.Chronology chronology54 = null;
        org.joda.time.Period period55 = new org.joda.time.Period((long) (short) 10, periodType53, chronology54);
        org.joda.time.Months months56 = org.joda.time.Months.FIVE;
        org.joda.time.Period period57 = period55.minus((org.joda.time.ReadablePeriod) months56);
        org.joda.time.Chronology chronology59 = null;
        org.joda.time.DateMidnight dateMidnight60 = new org.joda.time.DateMidnight((long) (short) -1, chronology59);
        org.joda.time.DateMidnight dateMidnight62 = dateMidnight60.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology64 = null;
        org.joda.time.DateMidnight dateMidnight65 = new org.joda.time.DateMidnight((long) (short) -1, chronology64);
        org.joda.time.DateMidnight dateMidnight67 = dateMidnight65.minusMonths((int) (byte) 100);
        int int68 = dateMidnight62.compareTo((org.joda.time.ReadableInstant) dateMidnight65);
        org.joda.time.Duration duration69 = period57.toDurationTo((org.joda.time.ReadableInstant) dateMidnight65);
        org.joda.time.Duration duration70 = duration69.negated();
        org.joda.time.PeriodType periodType71 = org.joda.time.PeriodType.yearDay();
        org.joda.time.PeriodType periodType72 = periodType71.withSecondsRemoved();
        org.joda.time.MutablePeriod mutablePeriod73 = new org.joda.time.MutablePeriod((org.joda.time.ReadableInstant) dateMidnight49, (org.joda.time.ReadableDuration) duration69, periodType72);
        int int74 = duration41.compareTo((org.joda.time.ReadableDuration) duration69);
        org.joda.time.Duration duration75 = duration39.minus((org.joda.time.ReadableDuration) duration69);
        mutablePeriod11.add((org.joda.time.ReadableDuration) duration75);
        int int79 = periodFormatter0.parseInto((org.joda.time.ReadWritablePeriod) mutablePeriod11, "IslamicChronology[2026-08-06T11:10:36.726]", (int) '#');
        mutablePeriod11.addMillis((int) (short) 10);
        org.junit.Assert.assertNotNull(periodFormatter0);
        org.junit.Assert.assertNotNull(periodParser1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + true + "'", boolean2 == true);
        org.junit.Assert.assertNotNull(periodFormatter12);
        org.junit.Assert.assertNotNull(periodParser13);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "1 year, -1 weeks, -1 days, 1 hour, 100 seconds and 3 milliseconds" + "'", str14, "1 year, -1 weeks, -1 days, 1 hour, 100 seconds and 3 milliseconds");
        org.junit.Assert.assertNotNull(mutableDateTime18);
        org.junit.Assert.assertNotNull(months23);
        org.junit.Assert.assertNotNull(period24);
        org.junit.Assert.assertNotNull(dateMidnight29);
        org.junit.Assert.assertNotNull(dateMidnight34);
        org.junit.Assert.assertTrue("'" + int35 + "' != '" + (-1) + "'", int35 == (-1));
        org.junit.Assert.assertNotNull(duration36);
        org.junit.Assert.assertNotNull(dateMidnight37);
        org.junit.Assert.assertNotNull(duration39);
        org.junit.Assert.assertNotNull(duration41);
        org.junit.Assert.assertNotNull(dateMidnight46);
        org.junit.Assert.assertNotNull(dateMidnight49);
        org.junit.Assert.assertNotNull(dateMidnight51);
        org.junit.Assert.assertNotNull(months56);
        org.junit.Assert.assertNotNull(period57);
        org.junit.Assert.assertNotNull(dateMidnight62);
        org.junit.Assert.assertNotNull(dateMidnight67);
        org.junit.Assert.assertTrue("'" + int68 + "' != '" + (-1) + "'", int68 == (-1));
        org.junit.Assert.assertNotNull(duration69);
        org.junit.Assert.assertNotNull(duration70);
        org.junit.Assert.assertNotNull(periodType71);
        org.junit.Assert.assertNotNull(periodType72);
        org.junit.Assert.assertTrue("'" + int74 + "' != '" + 1 + "'", int74 == 1);
        org.junit.Assert.assertNotNull(duration75);
        org.junit.Assert.assertTrue("'" + int79 + "' != '" + 35 + "'", int79 == 35);
    }

    @Test
    public void test0972() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0972");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar8 = dateMidnight7.toGregorianCalendar();
        org.joda.time.LocalTime localTime9 = org.joda.time.LocalTime.fromCalendarFields((java.util.Calendar) gregorianCalendar8);
        org.joda.time.LocalTime.Property property10 = localTime9.minuteOfHour();
        org.joda.time.LocalTime.Property property11 = localTime9.millisOfSecond();
        org.joda.time.LocalTime localTime13 = property11.addCopy(0L);
        org.joda.time.LocalTime.Property property14 = localTime13.millisOfDay();
        int int15 = localTime13.size();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(gregorianCalendar8);
        org.junit.Assert.assertNotNull(localTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(localTime13);
        org.junit.Assert.assertNotNull(property14);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 4 + "'", int15 == 4);
    }

    @Test
    public void test0973() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0973");
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
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone17 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology18 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone17);
        org.joda.time.chrono.JulianChronology julianChronology19 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField20 = julianChronology19.weeks();
        org.joda.time.chrono.GJChronology gJChronology21 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology22 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology21);
        org.joda.time.DateTimeField dateTimeField23 = gJChronology21.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField25 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology19, dateTimeField23, (int) '4');
        org.joda.time.DateTimeZone dateTimeZone26 = julianChronology19.getZone();
        long long28 = fixedDateTimeZone17.getMillisKeepLocal(dateTimeZone26, (long) 9);
        boolean boolean30 = dateTimeZone26.isStandardOffset((long) 35);
        org.joda.time.DateTime dateTime31 = localDateTime9.toDateTime(dateTimeZone26);
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(seconds6);
        org.junit.Assert.assertNotNull(durationFieldType7);
        org.junit.Assert.assertNotNull(localDateTime9);
// flaky "5) test0973(RegressionTest1)":         org.junit.Assert.assertEquals("'" + str10 + "' != '" + "2029-05-02T11:12:07.648" + "'", str10, "2029-05-02T11:12:07.648");
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertNotNull(gregorianChronology18);
        org.junit.Assert.assertNotNull(julianChronology19);
        org.junit.Assert.assertNotNull(durationField20);
        org.junit.Assert.assertNotNull(gJChronology21);
        org.junit.Assert.assertNotNull(lenientChronology22);
        org.junit.Assert.assertNotNull(dateTimeField23);
        org.junit.Assert.assertNotNull(dateTimeZone26);
        org.junit.Assert.assertTrue("'" + long28 + "' != '" + (-3599891L) + "'", long28 == (-3599891L));
        org.junit.Assert.assertTrue("'" + boolean30 + "' != '" + true + "'", boolean30 == true);
        org.junit.Assert.assertNotNull(dateTime31);
    }

    @Test
    public void test0974() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0974");
        int int0 = org.joda.time.TimeOfDay.MINUTE_OF_HOUR;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 1 + "'", int0 == 1);
    }

    @Test
    public void test0975() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0975");
        org.joda.time.chrono.GJChronology gJChronology0 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology1 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology0);
        org.joda.time.DateTimeField dateTimeField2 = gJChronology0.minuteOfHour();
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
        org.joda.time.DurationField durationField22 = islamicChronology21.millis();
        org.joda.time.DateTimeField dateTimeField23 = islamicChronology21.yearOfEra();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField24 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology0, dateTimeField23);
        long long27 = skipUndoDateTimeField24.addWrapField((long) 53, 32);
        org.junit.Assert.assertNotNull(gJChronology0);
        org.junit.Assert.assertNotNull(lenientChronology1);
        org.junit.Assert.assertNotNull(dateTimeField2);
        org.junit.Assert.assertNotNull(copticChronology8);
        org.junit.Assert.assertNotNull(leapYearPatternType9);
        org.junit.Assert.assertNotNull(dateMidnight14);
        org.junit.Assert.assertNotNull(property15);
        org.junit.Assert.assertNotNull(mutableDateTime16);
        org.junit.Assert.assertNotNull(property17);
        org.junit.Assert.assertNotNull(mutableDateTime19);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
        org.junit.Assert.assertNotNull(islamicChronology21);
        org.junit.Assert.assertNotNull(durationField22);
        org.junit.Assert.assertNotNull(dateTimeField23);
        org.junit.Assert.assertTrue("'" + long27 + "' != '" + 979776000053L + "'", long27 == 979776000053L);
    }

    @Test
    public void test0976() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0976");
        org.joda.time.Period period1 = new org.joda.time.Period((long) 168);
        org.joda.time.Chronology chronology3 = null;
        org.joda.time.DateMidnight dateMidnight4 = new org.joda.time.DateMidnight((long) (short) -1, chronology3);
        org.joda.time.DateMidnight dateMidnight6 = dateMidnight4.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property7 = dateMidnight4.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime8 = dateMidnight4.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property9 = mutableDateTime8.centuryOfEra();
        mutableDateTime8.setSecondOfMinute(24);
        org.joda.time.DateTime dateTime12 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone13 = null;
        org.joda.time.DateTime dateTime14 = dateTime12.withZoneRetainFields(dateTimeZone13);
        boolean boolean15 = mutableDateTime8.isAfter((org.joda.time.ReadableInstant) dateTime12);
        mutableDateTime8.addWeekyears(3);
        java.lang.Object obj18 = mutableDateTime8.clone();
        org.joda.time.Hours hours19 = org.joda.time.Hours.MIN_VALUE;
        mutableDateTime8.add((org.joda.time.ReadablePeriod) hours19);
        org.joda.time.Period period21 = period1.minus((org.joda.time.ReadablePeriod) hours19);
        org.junit.Assert.assertNotNull(dateMidnight6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(mutableDateTime8);
        org.junit.Assert.assertNotNull(property9);
        org.junit.Assert.assertNotNull(dateTime14);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNotNull(obj18);
        org.junit.Assert.assertEquals(obj18.toString(), "1973-01-04T00:00:24.000+01:00");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj18), "1973-01-04T00:00:24.000+01:00");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj18), "1973-01-04T00:00:24.000+01:00");
        org.junit.Assert.assertNotNull(hours19);
        org.junit.Assert.assertNotNull(period21);
    }

    @Test
    public void test0977() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0977");
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
        org.joda.time.Duration duration26 = org.joda.time.Duration.standardDays((long) (short) 1);
        org.joda.time.PeriodType periodType28 = null;
        org.joda.time.Chronology chronology29 = null;
        org.joda.time.Period period30 = new org.joda.time.Period((long) (short) 10, periodType28, chronology29);
        org.joda.time.Months months31 = org.joda.time.Months.FIVE;
        org.joda.time.Period period32 = period30.minus((org.joda.time.ReadablePeriod) months31);
        org.joda.time.Chronology chronology34 = null;
        org.joda.time.DateMidnight dateMidnight35 = new org.joda.time.DateMidnight((long) (short) -1, chronology34);
        org.joda.time.DateMidnight dateMidnight37 = dateMidnight35.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology39 = null;
        org.joda.time.DateMidnight dateMidnight40 = new org.joda.time.DateMidnight((long) (short) -1, chronology39);
        org.joda.time.DateMidnight dateMidnight42 = dateMidnight40.minusMonths((int) (byte) 100);
        int int43 = dateMidnight37.compareTo((org.joda.time.ReadableInstant) dateMidnight40);
        org.joda.time.Duration duration44 = period32.toDurationTo((org.joda.time.ReadableInstant) dateMidnight40);
        org.joda.time.Duration duration45 = duration44.negated();
        boolean boolean46 = duration26.isShorterThan((org.joda.time.ReadableDuration) duration44);
        org.joda.time.Instant instant47 = instant21.plus((org.joda.time.ReadableDuration) duration26);
        org.joda.time.MutablePeriod mutablePeriod48 = new org.joda.time.MutablePeriod((org.joda.time.ReadableInstant) dateMidnight20, (org.joda.time.ReadableInstant) instant21);
        org.joda.time.DateMidnight dateMidnight50 = dateMidnight20.plusYears((int) (byte) 0);
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
        org.junit.Assert.assertNotNull(duration26);
        org.junit.Assert.assertNotNull(months31);
        org.junit.Assert.assertNotNull(period32);
        org.junit.Assert.assertNotNull(dateMidnight37);
        org.junit.Assert.assertNotNull(dateMidnight42);
        org.junit.Assert.assertTrue("'" + int43 + "' != '" + (-1) + "'", int43 == (-1));
        org.junit.Assert.assertNotNull(duration44);
        org.junit.Assert.assertNotNull(duration45);
        org.junit.Assert.assertTrue("'" + boolean46 + "' != '" + false + "'", boolean46 == false);
        org.junit.Assert.assertNotNull(instant47);
        org.junit.Assert.assertNotNull(dateMidnight50);
    }

    @Test
    public void test0978() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0978");
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
        boolean boolean12 = skipUndoDateTimeField8.isLeap((long) 0);
        int int13 = skipUndoDateTimeField8.getMaximumValue();
        org.joda.time.chrono.JulianChronology julianChronology15 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate16 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology15);
        org.joda.time.LocalDate.Property property17 = localDate16.yearOfCentury();
        org.joda.time.LocalDate localDate19 = property17.addToCopy(1);
        org.joda.time.LocalDate localDate21 = localDate19.minusDays((int) (short) 0);
        int[] intArray28 = new int[] { 1970, (short) 10, 40237996, 1000, (short) -1 };
        int[] intArray30 = skipUndoDateTimeField8.add((org.joda.time.ReadablePartial) localDate19, 2000, intArray28, 0);
        org.junit.Assert.assertNotNull(gJChronology0);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(durationField3);
        org.junit.Assert.assertNotNull(gJChronology4);
        org.junit.Assert.assertNotNull(lenientChronology5);
        org.junit.Assert.assertNotNull(lenientChronology6);
        org.junit.Assert.assertNotNull(dateTimeField7);
        org.junit.Assert.assertNotNull(dateTimeFieldType9);
        org.junit.Assert.assertNotNull(durationField10);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 100 + "'", int13 == 100);
        org.junit.Assert.assertNotNull(julianChronology15);
        org.junit.Assert.assertNotNull(property17);
        org.junit.Assert.assertNotNull(localDate19);
        org.junit.Assert.assertNotNull(localDate21);
        org.junit.Assert.assertNotNull(intArray28);
        org.junit.Assert.assertArrayEquals(intArray28, new int[] { 1970, 10, 40237996, 1000, (-1) });
        org.junit.Assert.assertNotNull(intArray30);
        org.junit.Assert.assertArrayEquals(intArray30, new int[] { 1970, 10, 40237996, 1000, (-1) });
    }

    @Test
    public void test0979() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0979");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone5 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology6 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.LocalDateTime localDateTime7 = new org.joda.time.LocalDateTime((long) (-1), (org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.tz.CachedDateTimeZone cachedDateTimeZone8 = org.joda.time.tz.CachedDateTimeZone.forZone((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        long long10 = fixedDateTimeZone5.previousTransition(1L);
        java.lang.String str12 = fixedDateTimeZone5.getName((long) '#');
        boolean boolean13 = fixedDateTimeZone5.isFixed();
        org.junit.Assert.assertNotNull(gregorianChronology6);
        org.junit.Assert.assertNotNull(cachedDateTimeZone8);
        org.junit.Assert.assertTrue("'" + long10 + "' != '" + 1L + "'", long10 == 1L);
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "+00:00:00.100" + "'", str12, "+00:00:00.100");
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + true + "'", boolean13 == true);
    }

    @Test
    public void test0980() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0980");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = localDate2.withWeekyear((int) (byte) 100);
        org.joda.time.LocalDate.Property property6 = localDate5.yearOfCentury();
        org.joda.time.LocalDate localDate8 = localDate5.minusDays(40299577);
        org.joda.time.Chronology chronology10 = null;
        org.joda.time.DateMidnight dateMidnight11 = new org.joda.time.DateMidnight((long) (short) -1, chronology10);
        org.joda.time.DateMidnight dateMidnight13 = dateMidnight11.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight16 = dateMidnight11.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar17 = dateMidnight16.toGregorianCalendar();
        org.joda.time.LocalTime localTime18 = org.joda.time.LocalTime.fromCalendarFields((java.util.Calendar) gregorianCalendar17);
        org.joda.time.LocalTime.Property property19 = localTime18.minuteOfHour();
        org.joda.time.LocalTime localTime20 = property19.withMaximumValue();
        org.joda.time.LocalTime localTime22 = property19.addNoWrapToCopy(4);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalDateTime localDateTime23 = localDate5.toLocalDateTime(localTime22);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The chronology of the time does not match");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(property6);
        org.junit.Assert.assertNotNull(localDate8);
        org.junit.Assert.assertNotNull(dateMidnight13);
        org.junit.Assert.assertNotNull(dateMidnight16);
        org.junit.Assert.assertNotNull(gregorianCalendar17);
        org.junit.Assert.assertNotNull(localTime18);
        org.junit.Assert.assertNotNull(property19);
        org.junit.Assert.assertNotNull(localTime20);
        org.junit.Assert.assertNotNull(localTime22);
    }

    @Test
    public void test0981() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0981");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime2.plusHours(0);
        org.joda.time.LocalDateTime localDateTime5 = dateTime2.toLocalDateTime();
        org.joda.time.DateTimeComparator dateTimeComparator6 = org.joda.time.DateTimeComparator.getDateOnlyInstance();
        org.joda.time.DateTimeComparator dateTimeComparator7 = org.joda.time.DateTimeComparator.getTimeOnlyInstance();
        java.util.Comparator<java.lang.Object> objComparator8 = dateTimeComparator6.thenComparing((java.util.Comparator<java.lang.Object>) dateTimeComparator7);
        org.joda.time.DateTimeFieldType dateTimeFieldType9 = dateTimeComparator7.getUpperLimit();
        org.joda.time.LocalDateTime.Property property10 = localDateTime5.property(dateTimeFieldType9);
        org.joda.time.LocalDateTime.Property property11 = localDateTime5.weekyear();
        org.joda.time.LocalDateTime localDateTime12 = property11.withMinimumValue();
        org.joda.time.LocalDateTime localDateTime13 = property11.withMaximumValue();
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(localDateTime5);
        org.junit.Assert.assertNotNull(dateTimeComparator6);
        org.junit.Assert.assertNotNull(dateTimeComparator7);
        org.junit.Assert.assertNotNull(objComparator8);
        org.junit.Assert.assertNotNull(dateTimeFieldType9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(localDateTime12);
        org.junit.Assert.assertNotNull(localDateTime13);
    }

    @Test
    public void test0982() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0982");
        org.joda.time.ReadableInterval readableInterval0 = null;
        org.joda.time.Seconds seconds1 = org.joda.time.Seconds.secondsIn(readableInterval0);
        org.joda.time.ReadableInterval readableInterval2 = null;
        org.joda.time.Seconds seconds3 = org.joda.time.Seconds.secondsIn(readableInterval2);
        org.joda.time.Seconds seconds4 = seconds1.plus(seconds3);
        org.joda.time.ReadableInterval readableInterval5 = null;
        org.joda.time.Seconds seconds6 = org.joda.time.Seconds.secondsIn(readableInterval5);
        org.joda.time.ReadableInterval readableInterval7 = null;
        org.joda.time.Seconds seconds8 = org.joda.time.Seconds.secondsIn(readableInterval7);
        org.joda.time.Seconds seconds9 = seconds6.plus(seconds8);
        int int10 = seconds6.getSeconds();
        org.joda.time.ReadableInterval readableInterval11 = null;
        org.joda.time.Seconds seconds12 = org.joda.time.Seconds.secondsIn(readableInterval11);
        org.joda.time.ReadableInterval readableInterval13 = null;
        org.joda.time.Seconds seconds14 = org.joda.time.Seconds.secondsIn(readableInterval13);
        org.joda.time.Seconds seconds15 = seconds12.plus(seconds14);
        org.joda.time.Seconds seconds16 = seconds12.negated();
        org.joda.time.Seconds seconds17 = seconds6.plus(seconds16);
        org.joda.time.Seconds seconds18 = seconds4.plus(seconds16);
        org.junit.Assert.assertNotNull(seconds1);
        org.junit.Assert.assertNotNull(seconds3);
        org.junit.Assert.assertNotNull(seconds4);
        org.junit.Assert.assertNotNull(seconds6);
        org.junit.Assert.assertNotNull(seconds8);
        org.junit.Assert.assertNotNull(seconds9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
        org.junit.Assert.assertNotNull(seconds12);
        org.junit.Assert.assertNotNull(seconds14);
        org.junit.Assert.assertNotNull(seconds15);
        org.junit.Assert.assertNotNull(seconds16);
        org.junit.Assert.assertNotNull(seconds17);
        org.junit.Assert.assertNotNull(seconds18);
    }

    @Test
    public void test0983() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0983");
        org.joda.time.LocalTime localTime1 = new org.joda.time.LocalTime((long) (-1));
        org.joda.time.LocalTime localTime3 = localTime1.withSecondOfMinute(1);
        org.joda.time.LocalTime localTime5 = localTime1.plusSeconds(0);
        org.joda.time.LocalTime localTime7 = localTime1.plusSeconds(86400000);
        org.joda.time.LocalTime.Property property8 = localTime7.hourOfDay();
        org.joda.time.LocalTime localTime9 = property8.withMinimumValue();
        org.junit.Assert.assertNotNull(localTime3);
        org.junit.Assert.assertNotNull(localTime5);
        org.junit.Assert.assertNotNull(localTime7);
        org.junit.Assert.assertNotNull(property8);
        org.junit.Assert.assertNotNull(localTime9);
    }

    @Test
    public void test0984() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0984");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Interval interval2 = yearMonth0.toInterval(dateTimeZone1);
        org.joda.time.Months months3 = org.joda.time.Months.ONE;
        org.joda.time.Interval interval4 = interval2.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) months3);
        org.joda.time.DateTime dateTime5 = interval2.getEnd();
        org.joda.time.Chronology chronology6 = org.joda.time.DateTimeUtils.getIntervalChronology((org.joda.time.ReadableInterval) interval2);
        org.joda.time.DateTime dateTime7 = new org.joda.time.DateTime();
        org.joda.time.DateTime.Property property8 = dateTime7.millisOfDay();
        org.joda.time.DateTime dateTime10 = property8.addToCopy(12);
        long long11 = org.joda.time.DateTimeUtils.getInstantMillis((org.joda.time.ReadableInstant) dateTime10);
        boolean boolean12 = interval2.isBefore((org.joda.time.ReadableInstant) dateTime10);
        org.joda.time.Chronology chronology14 = null;
        org.joda.time.DateMidnight dateMidnight15 = new org.joda.time.DateMidnight((long) (short) -1, chronology14);
        org.joda.time.DateMidnight dateMidnight17 = dateMidnight15.minusYears(100);
        org.joda.time.DateMidnight.Property property18 = dateMidnight15.monthOfYear();
        org.joda.time.ReadablePeriod readablePeriod19 = null;
        org.joda.time.DateMidnight dateMidnight20 = dateMidnight15.plus(readablePeriod19);
        boolean boolean21 = interval2.isBefore((org.joda.time.ReadableInstant) dateMidnight15);
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertNotNull(months3);
        org.junit.Assert.assertNotNull(interval4);
        org.junit.Assert.assertNotNull(dateTime5);
        org.junit.Assert.assertNotNull(chronology6);
        org.junit.Assert.assertNotNull(property8);
        org.junit.Assert.assertNotNull(dateTime10);
// flaky "6) test0984(RegressionTest1)":         org.junit.Assert.assertTrue("'" + long11 + "' != '" + 1786007528110L + "'", long11 == 1786007528110L);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertNotNull(dateMidnight17);
        org.junit.Assert.assertNotNull(property18);
        org.junit.Assert.assertNotNull(dateMidnight20);
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + false + "'", boolean21 == false);
    }

    @Test
    public void test0985() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0985");
        org.joda.time.PeriodType periodType1 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.Period period3 = new org.joda.time.Period((long) (short) 10, periodType1, chronology2);
        org.joda.time.Months months4 = org.joda.time.Months.FIVE;
        org.joda.time.Period period5 = period3.minus((org.joda.time.ReadablePeriod) months4);
        org.joda.time.Period period7 = period3.plusMinutes((int) ' ');
        org.joda.time.DurationFieldType[] durationFieldTypeArray8 = period3.getFieldTypes();
        org.joda.time.Period period10 = period3.minusDays((int) (short) 100);
        org.joda.time.MutablePeriod mutablePeriod11 = period3.toMutablePeriod();
        org.joda.time.MutableDateTime mutableDateTime13 = new org.joda.time.MutableDateTime();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone18 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology19 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone18);
        mutableDateTime13.setChronology((org.joda.time.Chronology) gregorianChronology19);
        org.joda.time.DurationField durationField21 = gregorianChronology19.centuries();
        mutablePeriod11.setPeriod(2177280001000L, (org.joda.time.Chronology) gregorianChronology19);
        org.joda.time.ReadableInterval readableInterval23 = null;
        mutablePeriod11.add(readableInterval23);
        org.junit.Assert.assertNotNull(months4);
        org.junit.Assert.assertNotNull(period5);
        org.junit.Assert.assertNotNull(period7);
        org.junit.Assert.assertNotNull(durationFieldTypeArray8);
        org.junit.Assert.assertNotNull(period10);
        org.junit.Assert.assertNotNull(mutablePeriod11);
        org.junit.Assert.assertNotNull(gregorianChronology19);
        org.junit.Assert.assertNotNull(durationField21);
    }

    @Test
    public void test0986() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0986");
        int int0 = org.joda.time.DateTimeConstants.MILLIS_PER_MINUTE;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 60000 + "'", int0 == 60000);
    }

    @Test
    public void test0987() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0987");
        org.joda.time.Chronology chronology0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MonthDay monthDay1 = org.joda.time.MonthDay.now(chronology0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Chronology must not be null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0988() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0988");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Interval interval2 = yearMonth0.toInterval(dateTimeZone1);
        org.joda.time.YearMonth yearMonth4 = yearMonth0.plusMonths((int) 'a');
        org.joda.time.YearMonth.Property property5 = yearMonth0.year();
        java.lang.String str6 = property5.getAsText();
        org.joda.time.YearMonth yearMonth8 = property5.setCopy("1440");
        org.joda.time.DurationField durationField9 = property5.getDurationField();
        org.joda.time.YearMonth yearMonth10 = property5.getYearMonth();
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertNotNull(yearMonth4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "2026" + "'", str6, "2026");
        org.junit.Assert.assertNotNull(yearMonth8);
        org.junit.Assert.assertNotNull(durationField9);
        org.junit.Assert.assertNotNull(yearMonth10);
    }

    @Test
    public void test0989() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0989");
        org.joda.time.YearMonth yearMonth1 = new org.joda.time.YearMonth(40238396L);
    }

    @Test
    public void test0990() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0990");
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
            org.joda.time.DurationFieldType durationFieldType33 = periodType30.getFieldType((int) ' ');
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 32");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
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
    public void test0991() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0991");
        org.joda.time.DateTimeUtils.MillisProvider millisProvider0 = org.joda.time.DateTimeUtils.SYSTEM_MILLIS_PROVIDER;
        long long1 = millisProvider0.getMillis();
        org.joda.time.DateTimeUtils.setCurrentMillisProvider(millisProvider0);
        org.junit.Assert.assertNotNull(millisProvider0);
// flaky "7) test0991(RegressionTest1)":         org.junit.Assert.assertTrue("'" + long1 + "' != '" + 1786007526282L + "'", long1 == 1786007526282L);
    }

    @Test
    public void test0992() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0992");
        org.joda.time.Weeks weeks0 = org.joda.time.Weeks.MAX_VALUE;
        org.joda.time.Weeks weeks2 = weeks0.dividedBy(3);
        org.joda.time.Duration duration3 = weeks2.toStandardDuration();
        org.junit.Assert.assertNotNull(weeks0);
        org.junit.Assert.assertNotNull(weeks2);
        org.junit.Assert.assertNotNull(duration3);
    }

    @Test
    public void test0993() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0993");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.withYear(100);
        org.joda.time.DateMidnight.Property property10 = dateMidnight7.era();
        org.joda.time.DateMidnight dateMidnight12 = dateMidnight7.minus(0L);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(dateMidnight12);
    }

    @Test
    public void test0994() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0994");
        org.joda.time.convert.ConverterManager converterManager0 = org.joda.time.convert.ConverterManager.getInstance();
        org.joda.time.convert.PartialConverter partialConverter1 = null;
        org.joda.time.convert.PartialConverter partialConverter2 = converterManager0.addPartialConverter(partialConverter1);
        org.joda.time.convert.PartialConverter partialConverter3 = null;
        org.joda.time.convert.PartialConverter partialConverter4 = converterManager0.addPartialConverter(partialConverter3);
        org.joda.time.convert.IntervalConverter intervalConverter5 = null;
        org.joda.time.convert.IntervalConverter intervalConverter6 = converterManager0.addIntervalConverter(intervalConverter5);
        org.junit.Assert.assertNotNull(converterManager0);
        org.junit.Assert.assertNull(partialConverter2);
        org.junit.Assert.assertNull(partialConverter4);
        org.junit.Assert.assertNull(intervalConverter6);
    }

    @Test
    public void test0995() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0995");
        org.joda.time.Years years1 = org.joda.time.Years.years((int) (byte) 10);
        org.joda.time.Years years3 = years1.dividedBy(1);
        org.joda.time.Years years5 = org.joda.time.Years.years((int) (byte) 10);
        org.joda.time.Years years7 = years5.dividedBy(1);
        boolean boolean8 = years3.isLessThan(years7);
        org.joda.time.Years years10 = years7.multipliedBy(0);
        org.joda.time.DurationFieldType durationFieldType11 = years7.getFieldType();
        org.junit.Assert.assertNotNull(years1);
        org.junit.Assert.assertNotNull(years3);
        org.junit.Assert.assertNotNull(years5);
        org.junit.Assert.assertNotNull(years7);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(years10);
        org.junit.Assert.assertNotNull(durationFieldType11);
    }

    @Test
    public void test0996() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0996");
        org.joda.time.PeriodType periodType1 = org.joda.time.PeriodType.millis();
        org.joda.time.PeriodType periodType2 = periodType1.withWeeksRemoved();
        org.joda.time.MutablePeriod mutablePeriod3 = new org.joda.time.MutablePeriod((long) (byte) 10, periodType2);
        int int4 = mutablePeriod3.getSeconds();
        mutablePeriod3.setMillis((int) (byte) 1);
        org.junit.Assert.assertNotNull(periodType1);
        org.junit.Assert.assertNotNull(periodType2);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
    }

    @Test
    public void test0997() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0997");
        org.joda.time.chrono.EthiopicChronology ethiopicChronology0 = org.joda.time.chrono.EthiopicChronology.getInstanceUTC();
        org.joda.time.Chronology chronology1 = ethiopicChronology0.withUTC();
        // The following exception was thrown during execution in test generation
        try {
            long long6 = ethiopicChronology0.getDateTimeMillis(2026, 0, (-53), (int) (byte) 0);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 0 for monthOfYear must be in the range [1,13]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(ethiopicChronology0);
        org.junit.Assert.assertNotNull(chronology1);
    }

    @Test
    public void test0998() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0998");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Interval interval2 = yearMonth0.toInterval(dateTimeZone1);
        org.joda.time.Months months3 = org.joda.time.Months.ONE;
        org.joda.time.Interval interval4 = interval2.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) months3);
        org.joda.time.DateTime dateTime5 = interval2.getEnd();
        org.joda.time.chrono.GJChronology gJChronology6 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj7 = null;
        boolean boolean8 = gJChronology6.equals(obj7);
        org.joda.time.DurationField durationField9 = gJChronology6.days();
        org.joda.time.chrono.GJChronology gJChronology10 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology11 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology10);
        org.joda.time.chrono.LenientChronology lenientChronology12 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology10);
        org.joda.time.DateTimeField dateTimeField13 = lenientChronology12.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField14 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology6, dateTimeField13);
        org.joda.time.Interval interval15 = interval2.withChronology((org.joda.time.Chronology) gJChronology6);
        org.joda.time.Chronology chronology17 = null;
        org.joda.time.DateMidnight dateMidnight18 = new org.joda.time.DateMidnight((long) (short) -1, chronology17);
        org.joda.time.DateMidnight dateMidnight20 = dateMidnight18.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property21 = dateMidnight18.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime22 = dateMidnight18.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property23 = mutableDateTime22.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime25 = property23.addWrapField((int) (short) -1);
        org.joda.time.MutableDateTime.Property property26 = mutableDateTime25.monthOfYear();
        org.joda.time.Duration duration28 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Duration duration30 = org.joda.time.Duration.standardDays((long) (short) 1);
        org.joda.time.Duration duration32 = duration28.withDurationAdded((org.joda.time.ReadableDuration) duration30, (int) (byte) -1);
        org.joda.time.MutableInterval mutableInterval33 = new org.joda.time.MutableInterval((org.joda.time.ReadableInstant) mutableDateTime25, (org.joda.time.ReadableDuration) duration30);
        mutableInterval33.setInterval((long) '4', 2700000L);
        org.joda.time.Interval interval37 = interval15.overlap((org.joda.time.ReadableInterval) mutableInterval33);
        org.joda.time.Chronology chronology39 = null;
        org.joda.time.DateMidnight dateMidnight40 = new org.joda.time.DateMidnight((long) (short) -1, chronology39);
        org.joda.time.DateMidnight dateMidnight42 = dateMidnight40.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight45 = dateMidnight40.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight47 = dateMidnight45.withYear(100);
        org.joda.time.Chronology chronology49 = null;
        org.joda.time.DateMidnight dateMidnight50 = new org.joda.time.DateMidnight((long) (short) -1, chronology49);
        org.joda.time.Interval interval51 = new org.joda.time.Interval((org.joda.time.ReadableInstant) dateMidnight47, (org.joda.time.ReadableInstant) dateMidnight50);
        org.joda.time.Period period53 = org.joda.time.Period.weeks((int) (short) 100);
        org.joda.time.Interval interval54 = interval51.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) period53);
        org.joda.time.Chronology chronology56 = null;
        org.joda.time.DateMidnight dateMidnight57 = new org.joda.time.DateMidnight((long) (short) -1, chronology56);
        org.joda.time.DateMidnight dateMidnight59 = dateMidnight57.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight62 = dateMidnight57.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight64 = dateMidnight62.withYear(100);
        org.joda.time.Chronology chronology66 = null;
        org.joda.time.DateMidnight dateMidnight67 = new org.joda.time.DateMidnight((long) (short) -1, chronology66);
        org.joda.time.Interval interval68 = new org.joda.time.Interval((org.joda.time.ReadableInstant) dateMidnight64, (org.joda.time.ReadableInstant) dateMidnight67);
        org.joda.time.Period period70 = org.joda.time.Period.weeks((int) (short) 100);
        org.joda.time.Interval interval71 = interval68.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) period70);
        org.joda.time.ReadableInterval readableInterval72 = null;
        boolean boolean73 = interval68.abuts(readableInterval72);
        long long74 = interval68.getStartMillis();
        boolean boolean75 = interval54.abuts((org.joda.time.ReadableInterval) interval68);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean76 = interval37.abuts((org.joda.time.ReadableInterval) interval54);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertNotNull(months3);
        org.junit.Assert.assertNotNull(interval4);
        org.junit.Assert.assertNotNull(dateTime5);
        org.junit.Assert.assertNotNull(gJChronology6);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(durationField9);
        org.junit.Assert.assertNotNull(gJChronology10);
        org.junit.Assert.assertNotNull(lenientChronology11);
        org.junit.Assert.assertNotNull(lenientChronology12);
        org.junit.Assert.assertNotNull(dateTimeField13);
        org.junit.Assert.assertNotNull(interval15);
        org.junit.Assert.assertNotNull(dateMidnight20);
        org.junit.Assert.assertNotNull(property21);
        org.junit.Assert.assertNotNull(mutableDateTime22);
        org.junit.Assert.assertNotNull(property23);
        org.junit.Assert.assertNotNull(mutableDateTime25);
        org.junit.Assert.assertNotNull(property26);
        org.junit.Assert.assertNotNull(duration28);
        org.junit.Assert.assertNotNull(duration30);
        org.junit.Assert.assertNotNull(duration32);
        org.junit.Assert.assertNull(interval37);
        org.junit.Assert.assertNotNull(dateMidnight42);
        org.junit.Assert.assertNotNull(dateMidnight45);
        org.junit.Assert.assertNotNull(dateMidnight47);
        org.junit.Assert.assertNotNull(period53);
        org.junit.Assert.assertNotNull(interval54);
        org.junit.Assert.assertNotNull(dateMidnight59);
        org.junit.Assert.assertNotNull(dateMidnight62);
        org.junit.Assert.assertNotNull(dateMidnight64);
        org.junit.Assert.assertNotNull(period70);
        org.junit.Assert.assertNotNull(interval71);
        org.junit.Assert.assertTrue("'" + boolean73 + "' != '" + false + "'", boolean73 == false);
        org.junit.Assert.assertTrue("'" + long74 + "' != '" + (-59011462664000L) + "'", long74 == (-59011462664000L));
        org.junit.Assert.assertTrue("'" + boolean75 + "' != '" + false + "'", boolean75 == false);
    }

    @Test
    public void test0999() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0999");
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
        int int22 = yearMonthDay19.getYear();
        org.joda.time.Chronology chronology23 = yearMonthDay19.getChronology();
        org.joda.time.Period period25 = org.joda.time.Period.weeks((int) (short) 100);
        int int26 = period25.getDays();
        org.joda.time.YearMonthDay yearMonthDay28 = yearMonthDay19.withPeriodAdded((org.joda.time.ReadablePeriod) period25, 0);
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
        org.junit.Assert.assertTrue("'" + int22 + "' != '" + 2026 + "'", int22 == 2026);
        org.junit.Assert.assertNotNull(chronology23);
        org.junit.Assert.assertNotNull(period25);
        org.junit.Assert.assertTrue("'" + int26 + "' != '" + 0 + "'", int26 == 0);
        org.junit.Assert.assertNotNull(yearMonthDay28);
    }

    @Test
    public void test1000() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test1000");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter1 = org.joda.time.format.ISODateTimeFormat.tTimeNoMillis();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MutableDateTime mutableDateTime2 = org.joda.time.MutableDateTime.parse("2029-05-02T11:12:07.648", dateTimeFormatter1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid format: \"2029-05-02T11:12:07.648\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeFormatter1);
    }
}
