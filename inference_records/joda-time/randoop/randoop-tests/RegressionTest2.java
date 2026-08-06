import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest2 {

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
    public void test1001() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1001");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.withYear(100);
        org.joda.time.Chronology chronology11 = null;
        org.joda.time.DateMidnight dateMidnight12 = new org.joda.time.DateMidnight((long) (short) -1, chronology11);
        org.joda.time.Interval interval13 = new org.joda.time.Interval((org.joda.time.ReadableInstant) dateMidnight9, (org.joda.time.ReadableInstant) dateMidnight12);
        org.joda.time.Chronology chronology15 = null;
        org.joda.time.DateMidnight dateMidnight16 = new org.joda.time.DateMidnight((long) (short) -1, chronology15);
        org.joda.time.DateMidnight dateMidnight18 = dateMidnight16.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight21 = dateMidnight16.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight23 = dateMidnight21.withYear(100);
        org.joda.time.Chronology chronology25 = null;
        org.joda.time.DateMidnight dateMidnight26 = new org.joda.time.DateMidnight((long) (short) -1, chronology25);
        org.joda.time.Interval interval27 = new org.joda.time.Interval((org.joda.time.ReadableInstant) dateMidnight23, (org.joda.time.ReadableInstant) dateMidnight26);
        org.joda.time.Period period29 = org.joda.time.Period.weeks((int) (short) 100);
        org.joda.time.Interval interval30 = interval27.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) period29);
        org.joda.time.Chronology chronology32 = null;
        org.joda.time.DateMidnight dateMidnight33 = new org.joda.time.DateMidnight((long) (short) -1, chronology32);
        org.joda.time.DateMidnight dateMidnight35 = dateMidnight33.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight38 = dateMidnight33.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight40 = dateMidnight38.withYear(100);
        org.joda.time.Chronology chronology42 = null;
        org.joda.time.DateMidnight dateMidnight43 = new org.joda.time.DateMidnight((long) (short) -1, chronology42);
        org.joda.time.Interval interval44 = new org.joda.time.Interval((org.joda.time.ReadableInstant) dateMidnight40, (org.joda.time.ReadableInstant) dateMidnight43);
        org.joda.time.Period period46 = org.joda.time.Period.weeks((int) (short) 100);
        org.joda.time.Interval interval47 = interval44.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) period46);
        org.joda.time.ReadableInterval readableInterval48 = null;
        boolean boolean49 = interval44.abuts(readableInterval48);
        long long50 = interval44.getStartMillis();
        boolean boolean51 = interval30.abuts((org.joda.time.ReadableInterval) interval44);
        org.joda.time.Interval interval52 = interval13.gap((org.joda.time.ReadableInterval) interval30);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Interval interval54 = interval52.withStartMillis(200L);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(dateMidnight18);
        org.junit.Assert.assertNotNull(dateMidnight21);
        org.junit.Assert.assertNotNull(dateMidnight23);
        org.junit.Assert.assertNotNull(period29);
        org.junit.Assert.assertNotNull(interval30);
        org.junit.Assert.assertNotNull(dateMidnight35);
        org.junit.Assert.assertNotNull(dateMidnight38);
        org.junit.Assert.assertNotNull(dateMidnight40);
        org.junit.Assert.assertNotNull(period46);
        org.junit.Assert.assertNotNull(interval47);
        org.junit.Assert.assertTrue("'" + boolean49 + "' != '" + false + "'", boolean49 == false);
        org.junit.Assert.assertTrue("'" + long50 + "' != '" + (-59011462664000L) + "'", long50 == (-59011462664000L));
        org.junit.Assert.assertTrue("'" + boolean51 + "' != '" + false + "'", boolean51 == false);
        org.junit.Assert.assertNull(interval52);
    }

    @Test
    public void test1002() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1002");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone5 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology6 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.LocalDate localDate7 = new org.joda.time.LocalDate(1L, (org.joda.time.DateTimeZone) fixedDateTimeZone5);
        int int9 = localDate7.getValue((int) (short) 0);
        org.joda.time.LocalDate localDate11 = localDate7.withWeekyear(1970);
        org.junit.Assert.assertNotNull(copticChronology6);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 1970 + "'", int9 == 1970);
        org.junit.Assert.assertNotNull(localDate11);
    }

    @Test
    public void test1003() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1003");
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
        org.joda.time.DurationField durationField71 = remainderDateTimeField59.getDurationField();
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
    public void test1004() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1004");
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
        org.joda.time.YearMonth yearMonth13 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone14 = null;
        org.joda.time.Interval interval15 = yearMonth13.toInterval(dateTimeZone14);
        org.joda.time.Months months16 = org.joda.time.Months.ONE;
        org.joda.time.Interval interval17 = interval15.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) months16);
        org.joda.time.DateTime dateTime18 = interval15.getEnd();
        org.joda.time.DateTime dateTime19 = new org.joda.time.DateTime();
        org.joda.time.DateTime.Property property20 = dateTime19.millisOfDay();
        org.joda.time.DateTime dateTime22 = property20.addToCopy(12);
        org.joda.time.Interval interval23 = interval15.withStart((org.joda.time.ReadableInstant) dateTime22);
        boolean boolean24 = interval2.abuts((org.joda.time.ReadableInterval) interval23);
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertNotNull(months3);
        org.junit.Assert.assertNotNull(interval4);
        org.junit.Assert.assertNotNull(dateTime5);
        org.junit.Assert.assertNotNull(chronology6);
        org.junit.Assert.assertNotNull(property8);
        org.junit.Assert.assertNotNull(dateTime10);
// flaky "1) test1004(RegressionTest2)":         org.junit.Assert.assertTrue("'" + long11 + "' != '" + 1786007526787L + "'", long11 == 1786007526787L);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertNotNull(interval15);
        org.junit.Assert.assertNotNull(months16);
        org.junit.Assert.assertNotNull(interval17);
        org.junit.Assert.assertNotNull(dateTime18);
        org.junit.Assert.assertNotNull(property20);
        org.junit.Assert.assertNotNull(dateTime22);
        org.junit.Assert.assertNotNull(interval23);
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
    }

    @Test
    public void test1005() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1005");
        org.joda.time.LocalTime localTime0 = org.joda.time.LocalTime.MIDNIGHT;
        org.junit.Assert.assertNotNull(localTime0);
    }

    @Test
    public void test1006() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1006");
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
        long long17 = offsetDateTimeField11.roundHalfFloor((long) (short) 1);
        long long19 = offsetDateTimeField11.roundHalfCeiling((long) 53);
        org.joda.time.DateTimeZone dateTimeZone20 = null;
        org.joda.time.LocalDateTime localDateTime21 = new org.joda.time.LocalDateTime(dateTimeZone20);
        int int22 = localDateTime21.getYear();
        org.joda.time.LocalDateTime localDateTime24 = localDateTime21.plusMillis(10);
        org.joda.time.chrono.GJChronology gJChronology25 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj26 = null;
        boolean boolean27 = gJChronology25.equals(obj26);
        org.joda.time.DurationField durationField28 = gJChronology25.days();
        org.joda.time.chrono.GJChronology gJChronology29 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology30 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology29);
        org.joda.time.chrono.LenientChronology lenientChronology31 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology29);
        org.joda.time.DateTimeField dateTimeField32 = lenientChronology31.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField33 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology25, dateTimeField32);
        org.joda.time.DateTimeFieldType dateTimeFieldType34 = skipUndoDateTimeField33.getType();
        int int35 = skipUndoDateTimeField33.getMinimumValue();
        java.lang.String str36 = skipUndoDateTimeField33.getName();
        org.joda.time.DateTimeFieldType dateTimeFieldType37 = skipUndoDateTimeField33.getType();
        org.joda.time.LocalDateTime localDateTime39 = localDateTime21.withField(dateTimeFieldType37, 24);
        int int40 = offsetDateTimeField11.getMinimumValue((org.joda.time.ReadablePartial) localDateTime21);
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 2 + "'", int8 == 2);
        org.junit.Assert.assertNotNull(durationField9);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + (-262800000L) + "'", long13 == (-262800000L));
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 0 + "'", int15 == 0);
        org.junit.Assert.assertTrue("'" + long17 + "' != '" + (-262800000L) + "'", long17 == (-262800000L));
        org.junit.Assert.assertTrue("'" + long19 + "' != '" + (-262800000L) + "'", long19 == (-262800000L));
        org.junit.Assert.assertTrue("'" + int22 + "' != '" + 2026 + "'", int22 == 2026);
        org.junit.Assert.assertNotNull(localDateTime24);
        org.junit.Assert.assertNotNull(gJChronology25);
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + false + "'", boolean27 == false);
        org.junit.Assert.assertNotNull(durationField28);
        org.junit.Assert.assertNotNull(gJChronology29);
        org.junit.Assert.assertNotNull(lenientChronology30);
        org.junit.Assert.assertNotNull(lenientChronology31);
        org.junit.Assert.assertNotNull(dateTimeField32);
        org.junit.Assert.assertNotNull(dateTimeFieldType34);
        org.junit.Assert.assertTrue("'" + int35 + "' != '" + 0 + "'", int35 == 0);
        org.junit.Assert.assertEquals("'" + str36 + "' != '" + "yearOfCentury" + "'", str36, "yearOfCentury");
        org.junit.Assert.assertNotNull(dateTimeFieldType37);
        org.junit.Assert.assertNotNull(localDateTime39);
        org.junit.Assert.assertTrue("'" + int40 + "' != '" + 10 + "'", int40 == 10);
    }

    @Test
    public void test1007() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1007");
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
        org.joda.time.DateTimeZone dateTimeZone28 = gJChronology23.getZone();
        org.joda.time.DurationField durationField29 = gJChronology23.centuries();
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
        org.junit.Assert.assertNotNull(dateTimeZone28);
        org.junit.Assert.assertNotNull(durationField29);
    }

    @Test
    public void test1008() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1008");
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
        org.joda.time.Chronology chronology30 = copticChronology5.withUTC();
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
        org.junit.Assert.assertNotNull(chronology30);
    }

    @Test
    public void test1009() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1009");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.DateTimeFormat.shortDateTime();
        java.lang.String str2 = dateTimeFormatter0.print(2419232010L);
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "1/29/70 1:00 AM" + "'", str2, "1/29/70 1:00 AM");
    }

    @Test
    public void test1010() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1010");
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
        int int46 = dateMidnight40.compareTo((org.joda.time.ReadableInstant) dateMidnight43);
        org.joda.time.MutableDateTime mutableDateTime47 = dateMidnight43.toMutableDateTime();
        org.joda.time.PeriodType periodType48 = org.joda.time.PeriodType.yearDay();
        org.joda.time.PeriodType periodType49 = periodType48.withSecondsRemoved();
        org.joda.time.Period period50 = duration1.toPeriodFrom((org.joda.time.ReadableInstant) dateMidnight43, periodType49);
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
        org.junit.Assert.assertTrue("'" + int46 + "' != '" + (-1) + "'", int46 == (-1));
        org.junit.Assert.assertNotNull(mutableDateTime47);
        org.junit.Assert.assertNotNull(periodType48);
        org.junit.Assert.assertNotNull(periodType49);
        org.junit.Assert.assertNotNull(period50);
    }

    @Test
    public void test1011() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1011");
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
        long long22 = fixedDateTimeZone5.convertUTCToLocal(100L);
        org.joda.time.DateTimeZone dateTimeZone23 = null;
        org.joda.time.LocalDateTime localDateTime24 = new org.joda.time.LocalDateTime(dateTimeZone23);
        java.lang.String str25 = localDateTime24.toString();
        int[] intArray26 = localDateTime24.getValues();
        boolean boolean27 = fixedDateTimeZone5.isLocalDateTimeGap(localDateTime24);
        org.junit.Assert.assertNotNull(copticChronology6);
        org.junit.Assert.assertNotNull(leapYearPatternType7);
        org.junit.Assert.assertNotNull(dateMidnight12);
        org.junit.Assert.assertNotNull(property13);
        org.junit.Assert.assertNotNull(mutableDateTime14);
        org.junit.Assert.assertNotNull(property15);
        org.junit.Assert.assertNotNull(mutableDateTime17);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertNotNull(islamicChronology19);
        org.junit.Assert.assertTrue("'" + long22 + "' != '" + 200L + "'", long22 == 200L);
// flaky "2) test1011(RegressionTest2)":         org.junit.Assert.assertEquals("'" + str25 + "' != '" + "2026-08-06T11:12:07.050" + "'", str25, "2026-08-06T11:12:07.050");
        org.junit.Assert.assertNotNull(intArray26);
// flaky "1) test1011(RegressionTest2)":         org.junit.Assert.assertArrayEquals(intArray26, new int[] { 2026, 8, 6, 40327050 });
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + false + "'", boolean27 == false);
    }

    @Test
    public void test1012() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1012");
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
        long long73 = remainderDateTimeField59.set(32010L, 5);
        int int75 = remainderDateTimeField59.getLeapAmount((-262800000L));
        org.joda.time.DateTimeComparator dateTimeComparator76 = org.joda.time.DateTimeComparator.getDateOnlyInstance();
        org.joda.time.DateTimeComparator dateTimeComparator77 = org.joda.time.DateTimeComparator.getTimeOnlyInstance();
        java.util.Comparator<java.lang.Object> objComparator78 = dateTimeComparator76.thenComparing((java.util.Comparator<java.lang.Object>) dateTimeComparator77);
        org.joda.time.DateTimeFieldType dateTimeFieldType79 = dateTimeComparator77.getUpperLimit();
        org.joda.time.field.DividedDateTimeField dividedDateTimeField80 = new org.joda.time.field.DividedDateTimeField(remainderDateTimeField59, dateTimeFieldType79);
        org.joda.time.chrono.GJChronology gJChronology81 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj82 = null;
        boolean boolean83 = gJChronology81.equals(obj82);
        org.joda.time.DurationField durationField84 = gJChronology81.days();
        org.joda.time.chrono.GJChronology gJChronology85 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology86 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology85);
        org.joda.time.chrono.LenientChronology lenientChronology87 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology85);
        org.joda.time.DateTimeField dateTimeField88 = lenientChronology87.yearOfCentury();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField89 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) gJChronology81, dateTimeField88);
        org.joda.time.DateTimeFieldType dateTimeFieldType90 = skipUndoDateTimeField89.getType();
        org.joda.time.field.DividedDateTimeField dividedDateTimeField91 = new org.joda.time.field.DividedDateTimeField(remainderDateTimeField59, dateTimeFieldType90);
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
        org.junit.Assert.assertTrue("'" + long73 + "' != '" + 2419232010L + "'", long73 == 2419232010L);
        org.junit.Assert.assertTrue("'" + int75 + "' != '" + 0 + "'", int75 == 0);
        org.junit.Assert.assertNotNull(dateTimeComparator76);
        org.junit.Assert.assertNotNull(dateTimeComparator77);
        org.junit.Assert.assertNotNull(objComparator78);
        org.junit.Assert.assertNotNull(dateTimeFieldType79);
        org.junit.Assert.assertNotNull(gJChronology81);
        org.junit.Assert.assertTrue("'" + boolean83 + "' != '" + false + "'", boolean83 == false);
        org.junit.Assert.assertNotNull(durationField84);
        org.junit.Assert.assertNotNull(gJChronology85);
        org.junit.Assert.assertNotNull(lenientChronology86);
        org.junit.Assert.assertNotNull(lenientChronology87);
        org.junit.Assert.assertNotNull(dateTimeField88);
        org.junit.Assert.assertNotNull(dateTimeFieldType90);
    }

    @Test
    public void test1013() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1013");
        org.joda.time.Duration duration1 = org.joda.time.Duration.standardMinutes((long) 10);
        org.joda.time.Duration duration2 = duration1.negated();
        org.junit.Assert.assertNotNull(duration1);
        org.junit.Assert.assertNotNull(duration2);
    }

    @Test
    public void test1014() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1014");
        org.joda.time.chrono.GregorianChronology gregorianChronology0 = org.joda.time.chrono.GregorianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField1 = gregorianChronology0.minuteOfDay();
        org.joda.time.DateTime dateTime2 = org.joda.time.DateTime.now((org.joda.time.Chronology) gregorianChronology0);
        int int3 = gregorianChronology0.getMinimumDaysInFirstWeek();
        org.joda.time.DateTimeZone dateTimeZone4 = gregorianChronology0.getZone();
        long long7 = dateTimeZone4.adjustOffset((long) 70, true);
        org.joda.time.LocalDate localDate8 = org.joda.time.LocalDate.now(dateTimeZone4);
        org.junit.Assert.assertNotNull(gregorianChronology0);
        org.junit.Assert.assertNotNull(dateTimeField1);
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 4 + "'", int3 == 4);
        org.junit.Assert.assertNotNull(dateTimeZone4);
        org.junit.Assert.assertTrue("'" + long7 + "' != '" + 70L + "'", long7 == 70L);
        org.junit.Assert.assertNotNull(localDate8);
    }

    @Test
    public void test1015() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1015");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        int int8 = skipUndoDateTimeField6.get((long) 10);
        int int9 = skipUndoDateTimeField6.getMinimumValue();
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 2 + "'", int8 == 2);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 2 + "'", int9 == 2);
    }

    @Test
    public void test1016() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1016");
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
        java.lang.String str30 = partial27.toString("+00:00:00.100");
        org.joda.time.Chronology chronology31 = partial27.getChronology();
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
        org.junit.Assert.assertEquals("'" + str30 + "' != '" + "+00:00:00.100" + "'", str30, "+00:00:00.100");
        org.junit.Assert.assertNotNull(chronology31);
    }

    @Test
    public void test1017() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1017");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.dateElementParser();
        org.joda.time.Chronology chronology1 = dateTimeFormatter0.getChronolgy();
        boolean boolean2 = dateTimeFormatter0.isParser();
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
        org.junit.Assert.assertNull(chronology1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + true + "'", boolean2 == true);
    }

    @Test
    public void test1018() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1018");
        int int2 = org.joda.time.field.FieldUtils.safeAdd(20, 86400000);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 86400020 + "'", int2 == 86400020);
    }

    @Test
    public void test1019() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1019");
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
        org.joda.time.DateTimeFieldType dateTimeFieldType36 = skipUndoDateTimeField32.getType();
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
        org.junit.Assert.assertNotNull(dateTimeFieldType36);
    }

    @Test
    public void test1020() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1020");
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
        org.joda.time.MutablePeriod mutablePeriod22 = new org.joda.time.MutablePeriod((int) (byte) 1, (int) (short) 0, (int) (byte) -1, (int) (short) -1, (int) (byte) 1, 0, 100, 3);
        org.joda.time.format.PeriodFormatter periodFormatter23 = org.joda.time.format.PeriodFormat.wordBased();
        org.joda.time.format.PeriodParser periodParser24 = periodFormatter23.getParser();
        java.lang.String str25 = mutablePeriod22.toString(periodFormatter23);
        mutablePeriod22.add(0L);
        org.joda.time.Months months28 = org.joda.time.Months.SEVEN;
        org.joda.time.Months months29 = null;
        org.joda.time.Months months30 = months28.minus(months29);
        org.joda.time.DurationFieldType durationFieldType31 = months28.getFieldType();
        boolean boolean32 = mutablePeriod22.isSupported(durationFieldType31);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Partial partial34 = partial13.withPeriodAdded((org.joda.time.ReadablePeriod) mutablePeriod22, 69);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Maximum value exceeded for add");
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
        org.junit.Assert.assertNotNull(periodFormatter23);
        org.junit.Assert.assertNotNull(periodParser24);
        org.junit.Assert.assertEquals("'" + str25 + "' != '" + "1 year, -1 weeks, -1 days, 1 hour, 100 seconds and 3 milliseconds" + "'", str25, "1 year, -1 weeks, -1 days, 1 hour, 100 seconds and 3 milliseconds");
        org.junit.Assert.assertNotNull(months28);
        org.junit.Assert.assertNotNull(months30);
        org.junit.Assert.assertNotNull(durationFieldType31);
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + true + "'", boolean32 == true);
    }

    @Test
    public void test1021() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1021");
        org.joda.time.tz.DateTimeZoneBuilder dateTimeZoneBuilder0 = new org.joda.time.tz.DateTimeZoneBuilder();
        org.joda.time.tz.DateTimeZoneBuilder dateTimeZoneBuilder3 = dateTimeZoneBuilder0.setFixedSavings("", (int) ' ');
        java.io.OutputStream outputStream5 = null;
        // The following exception was thrown during execution in test generation
        try {
            dateTimeZoneBuilder3.writeTo("2147483647", outputStream5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeZoneBuilder3);
    }

    @Test
    public void test1022() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1022");
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
        org.joda.time.MutablePeriod mutablePeriod17 = minutes16.toMutablePeriod();
        mutablePeriod17.setHours((int) '#');
        mutablePeriod17.setWeeks((int) (short) 100);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(dateMidnight14);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + (-1) + "'", int15 == (-1));
        org.junit.Assert.assertNotNull(minutes16);
        org.junit.Assert.assertNotNull(mutablePeriod17);
    }

    @Test
    public void test1023() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1023");
        org.joda.time.PeriodType periodType1 = org.joda.time.PeriodType.yearDay();
        org.joda.time.MutablePeriod mutablePeriod2 = new org.joda.time.MutablePeriod((long) (byte) 10, periodType1);
        org.joda.time.PeriodType periodType4 = null;
        org.joda.time.Chronology chronology5 = null;
        org.joda.time.Period period6 = new org.joda.time.Period((long) (short) 10, periodType4, chronology5);
        org.joda.time.Months months7 = org.joda.time.Months.FIVE;
        org.joda.time.Period period8 = period6.minus((org.joda.time.ReadablePeriod) months7);
        org.joda.time.Period period10 = period6.plusMinutes((int) ' ');
        org.joda.time.DurationFieldType[] durationFieldTypeArray11 = period6.getFieldTypes();
        org.joda.time.PeriodType periodType12 = period6.getPeriodType();
        org.joda.time.Period period14 = period6.plusMonths(13);
        // The following exception was thrown during execution in test generation
        try {
            mutablePeriod2.mergePeriod((org.joda.time.ReadablePeriod) period14);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Period does not support field 'months'");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(periodType1);
        org.junit.Assert.assertNotNull(months7);
        org.junit.Assert.assertNotNull(period8);
        org.junit.Assert.assertNotNull(period10);
        org.junit.Assert.assertNotNull(durationFieldTypeArray11);
        org.junit.Assert.assertNotNull(periodType12);
        org.junit.Assert.assertNotNull(period14);
    }

    @Test
    public void test1024() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1024");
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
        int int20 = localDateTime19.getDayOfYear();
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
        org.junit.Assert.assertTrue("'" + int20 + "' != '" + 219 + "'", int20 == 219);
    }

    @Test
    public void test1025() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1025");
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Months months1 = org.joda.time.Months.parseMonths("weekOfWeekyear");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Invalid format: \"weekOfWeekyear\"");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test1026() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1026");
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
        org.joda.time.DateTime.Property property18 = dateTime17.centuryOfEra();
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
    public void test1027() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1027");
        org.joda.time.LocalTime localTime1 = new org.joda.time.LocalTime((long) (-1));
        org.joda.time.Chronology chronology2 = localTime1.getChronology();
        java.lang.String str3 = localTime1.toString();
        org.joda.time.PeriodType periodType5 = null;
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.Period period7 = new org.joda.time.Period((long) (short) 10, periodType5, chronology6);
        org.joda.time.ReadableInterval readableInterval8 = null;
        org.joda.time.Seconds seconds9 = org.joda.time.Seconds.secondsIn(readableInterval8);
        org.joda.time.DurationFieldType durationFieldType10 = seconds9.getFieldType();
        int int11 = period7.get(durationFieldType10);
        org.joda.time.PeriodType periodType12 = period7.getPeriodType();
        org.joda.time.Period period14 = period7.multipliedBy(4);
        org.joda.time.Duration duration15 = period7.toStandardDuration();
        org.joda.time.LocalTime localTime16 = localTime1.plus((org.joda.time.ReadablePeriod) period7);
        org.junit.Assert.assertNotNull(chronology2);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "00:59:59.999" + "'", str3, "00:59:59.999");
        org.junit.Assert.assertNotNull(seconds9);
        org.junit.Assert.assertNotNull(durationFieldType10);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertNotNull(periodType12);
        org.junit.Assert.assertNotNull(period14);
        org.junit.Assert.assertNotNull(duration15);
        org.junit.Assert.assertNotNull(localTime16);
    }

    @Test
    public void test1028() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1028");
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
        org.joda.time.DateTime dateTime17 = dateTime15.minusMillis(4);
        org.joda.time.LocalDate localDate18 = dateTime17.toLocalDate();
        org.joda.time.DateTime dateTime19 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone20 = null;
        org.joda.time.DateTime dateTime21 = dateTime19.withZoneRetainFields(dateTimeZone20);
        org.joda.time.Minutes minutes22 = org.joda.time.Minutes.ONE;
        org.joda.time.Minutes minutes24 = minutes22.minus((-1));
        org.joda.time.DateTime dateTime25 = dateTime19.plus((org.joda.time.ReadablePeriod) minutes24);
        boolean boolean26 = dateTime17.isAfter((org.joda.time.ReadableInstant) dateTime25);
        org.joda.time.DateTime dateTime28 = dateTime17.withYearOfCentury(35);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(dateTime12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(dateTime15);
        org.junit.Assert.assertNotNull(dateTime17);
        org.junit.Assert.assertNotNull(localDate18);
        org.junit.Assert.assertNotNull(dateTime21);
        org.junit.Assert.assertNotNull(minutes22);
        org.junit.Assert.assertNotNull(minutes24);
        org.junit.Assert.assertNotNull(dateTime25);
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + false + "'", boolean26 == false);
        org.junit.Assert.assertNotNull(dateTime28);
    }

    @Test
    public void test1029() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1029");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateMidnight dateMidnight7 = new org.joda.time.DateMidnight((long) (short) -1, chronology6);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.minusMonths((int) (byte) 100);
        int int10 = dateMidnight4.compareTo((org.joda.time.ReadableInstant) dateMidnight7);
        org.joda.time.DateTime dateTime11 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone12 = null;
        org.joda.time.DateTime dateTime13 = dateTime11.withZoneRetainFields(dateTimeZone12);
        org.joda.time.DateTime dateTime15 = dateTime11.withYear((int) (byte) 0);
        org.joda.time.Duration duration17 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Chronology chronology19 = null;
        org.joda.time.DateMidnight dateMidnight20 = new org.joda.time.DateMidnight((long) (short) -1, chronology19);
        org.joda.time.DateMidnight dateMidnight22 = dateMidnight20.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology24 = null;
        org.joda.time.DateMidnight dateMidnight25 = new org.joda.time.DateMidnight((long) (short) -1, chronology24);
        org.joda.time.DateMidnight dateMidnight27 = dateMidnight25.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology29 = null;
        org.joda.time.DateMidnight dateMidnight30 = new org.joda.time.DateMidnight((long) (short) -1, chronology29);
        org.joda.time.DateMidnight dateMidnight32 = dateMidnight30.minusMonths((int) (byte) 100);
        int int33 = dateMidnight27.compareTo((org.joda.time.ReadableInstant) dateMidnight30);
        org.joda.time.Minutes minutes34 = org.joda.time.Minutes.minutesBetween((org.joda.time.ReadableInstant) dateMidnight20, (org.joda.time.ReadableInstant) dateMidnight27);
        org.joda.time.DateMidnight dateMidnight37 = dateMidnight27.withDurationAdded((long) '4', 100);
        org.joda.time.MutableInterval mutableInterval38 = new org.joda.time.MutableInterval((org.joda.time.ReadableDuration) duration17, (org.joda.time.ReadableInstant) dateMidnight37);
        org.joda.time.DateTime dateTime39 = dateTime15.minus((org.joda.time.ReadableDuration) duration17);
        org.joda.time.DateTime.Property property40 = dateTime39.millisOfDay();
        boolean boolean42 = dateTime39.isAfter((long) 1970);
        org.joda.time.DateTime dateTime44 = dateTime39.minusWeeks(6);
        boolean boolean45 = dateMidnight4.isEqual((org.joda.time.ReadableInstant) dateTime44);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + (-1) + "'", int10 == (-1));
        org.junit.Assert.assertNotNull(dateTime13);
        org.junit.Assert.assertNotNull(dateTime15);
        org.junit.Assert.assertNotNull(duration17);
        org.junit.Assert.assertNotNull(dateMidnight22);
        org.junit.Assert.assertNotNull(dateMidnight27);
        org.junit.Assert.assertNotNull(dateMidnight32);
        org.junit.Assert.assertTrue("'" + int33 + "' != '" + (-1) + "'", int33 == (-1));
        org.junit.Assert.assertNotNull(minutes34);
        org.junit.Assert.assertNotNull(dateMidnight37);
        org.junit.Assert.assertNotNull(dateTime39);
        org.junit.Assert.assertNotNull(property40);
        org.junit.Assert.assertTrue("'" + boolean42 + "' != '" + false + "'", boolean42 == false);
        org.junit.Assert.assertNotNull(dateTime44);
        org.junit.Assert.assertTrue("'" + boolean45 + "' != '" + false + "'", boolean45 == false);
    }

    @Test
    public void test1030() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1030");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.DurationField durationField3 = gJChronology1.months();
        org.joda.time.DateMidnight dateMidnight5 = org.joda.time.DateMidnight.parse("2026-08-06T11:10:36.726");
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight5.minusYears((int) (byte) 100);
        org.joda.time.ReadableDateTime readableDateTime8 = null;
        org.joda.time.chrono.LimitChronology limitChronology9 = org.joda.time.chrono.LimitChronology.getInstance((org.joda.time.Chronology) gJChronology1, (org.joda.time.ReadableDateTime) dateMidnight5, readableDateTime8);
        org.joda.time.chrono.StrictChronology strictChronology10 = org.joda.time.chrono.StrictChronology.getInstance((org.joda.time.Chronology) limitChronology9);
        org.joda.time.Chronology chronology11 = strictChronology10.withUTC();
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(durationField3);
        org.junit.Assert.assertNotNull(dateMidnight5);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(limitChronology9);
        org.junit.Assert.assertNotNull(strictChronology10);
        org.junit.Assert.assertNotNull(chronology11);
    }

    @Test
    public void test1031() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1031");
        org.joda.time.Years years0 = org.joda.time.Years.ONE;
        org.joda.time.DurationFieldType durationFieldType1 = years0.getFieldType();
        org.junit.Assert.assertNotNull(years0);
        org.junit.Assert.assertNotNull(durationFieldType1);
    }

    @Test
    public void test1032() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1032");
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
        mutableInterval17.setDurationAfterStart((long) 9);
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
    }

    @Test
    public void test1033() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1033");
        org.joda.time.LocalTime localTime1 = new org.joda.time.LocalTime((long) (-1));
        int int2 = localTime1.getMinuteOfHour();
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 59 + "'", int2 == 59);
    }

    @Test
    public void test1034() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1034");
        org.joda.time.PeriodType periodType1 = org.joda.time.PeriodType.millis();
        org.joda.time.PeriodType periodType2 = periodType1.withWeeksRemoved();
        org.joda.time.ReadableInterval readableInterval3 = null;
        org.joda.time.Seconds seconds4 = org.joda.time.Seconds.secondsIn(readableInterval3);
        org.joda.time.DurationFieldType durationFieldType5 = seconds4.getFieldType();
        org.joda.time.IllegalFieldValueException illegalFieldValueException9 = new org.joda.time.IllegalFieldValueException(durationFieldType5, (java.lang.Number) 2700000L, (java.lang.Number) 10L, (java.lang.Number) (short) 1);
        boolean boolean10 = periodType2.isSupported(durationFieldType5);
        org.joda.time.Period period11 = new org.joda.time.Period((long) 1440, periodType2);
        org.joda.time.Period period12 = period11.toPeriod();
        org.junit.Assert.assertNotNull(periodType1);
        org.junit.Assert.assertNotNull(periodType2);
        org.junit.Assert.assertNotNull(seconds4);
        org.junit.Assert.assertNotNull(durationFieldType5);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(period12);
    }

    @Test
    public void test1035() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1035");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight7 = dateMidnight2.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight9 = dateMidnight7.withYear(100);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight9.plusDays(10);
        org.joda.time.DateMidnight dateMidnight13 = dateMidnight11.plusYears((int) (byte) 10);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateMidnight dateMidnight15 = dateMidnight11.withWeekOfWeekyear(60);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 60 for weekOfWeekyear must be in the range [1,52]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(dateMidnight7);
        org.junit.Assert.assertNotNull(dateMidnight9);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(dateMidnight13);
    }

    @Test
    public void test1036() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1036");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone5 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology6 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.LocalDateTime localDateTime7 = new org.joda.time.LocalDateTime((long) (-1), (org.joda.time.DateTimeZone) fixedDateTimeZone5);
        org.joda.time.tz.CachedDateTimeZone cachedDateTimeZone8 = org.joda.time.tz.CachedDateTimeZone.forZone((org.joda.time.DateTimeZone) fixedDateTimeZone5);
        boolean boolean9 = cachedDateTimeZone8.isFixed();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone14 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology15 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone14);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType16 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology18 = null;
        org.joda.time.DateMidnight dateMidnight19 = new org.joda.time.DateMidnight((long) (short) -1, chronology18);
        org.joda.time.DateMidnight dateMidnight21 = dateMidnight19.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property22 = dateMidnight19.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime23 = dateMidnight19.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property24 = mutableDateTime23.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime26 = property24.addWrapField((int) (short) -1);
        boolean boolean27 = leapYearPatternType16.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology28 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone14, leapYearPatternType16);
        org.joda.time.chrono.IslamicChronology islamicChronology29 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) cachedDateTimeZone8, leapYearPatternType16);
        org.joda.time.format.DateTimeFormatter dateTimeFormatter30 = org.joda.time.format.DateTimeFormat.longDate();
        org.joda.time.chrono.JulianChronology julianChronology31 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.format.DateTimeFormatter dateTimeFormatter32 = dateTimeFormatter30.withChronology((org.joda.time.Chronology) julianChronology31);
        boolean boolean33 = leapYearPatternType16.equals((java.lang.Object) julianChronology31);
        org.junit.Assert.assertNotNull(gregorianChronology6);
        org.junit.Assert.assertNotNull(cachedDateTimeZone8);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertNotNull(copticChronology15);
        org.junit.Assert.assertNotNull(leapYearPatternType16);
        org.junit.Assert.assertNotNull(dateMidnight21);
        org.junit.Assert.assertNotNull(property22);
        org.junit.Assert.assertNotNull(mutableDateTime23);
        org.junit.Assert.assertNotNull(property24);
        org.junit.Assert.assertNotNull(mutableDateTime26);
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + false + "'", boolean27 == false);
        org.junit.Assert.assertNotNull(islamicChronology28);
        org.junit.Assert.assertNotNull(islamicChronology29);
        org.junit.Assert.assertNotNull(dateTimeFormatter30);
        org.junit.Assert.assertNotNull(julianChronology31);
        org.junit.Assert.assertNotNull(dateTimeFormatter32);
        org.junit.Assert.assertTrue("'" + boolean33 + "' != '" + false + "'", boolean33 == false);
    }

    @Test
    public void test1037() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1037");
        org.joda.time.TimeOfDay timeOfDay1 = new org.joda.time.TimeOfDay((long) 100);
        org.joda.time.TimeOfDay timeOfDay3 = timeOfDay1.minusMinutes((int) (short) 1);
        // The following exception was thrown during execution in test generation
        try {
            int int5 = timeOfDay1.getValue(4);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 4");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(timeOfDay3);
    }

    @Test
    public void test1038() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1038");
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
        int int17 = mutablePeriod16.getMonths();
        org.junit.Assert.assertNotNull(months6);
        org.junit.Assert.assertNotNull(period7);
        org.junit.Assert.assertNotNull(period9);
        org.junit.Assert.assertNotNull(durationFieldTypeArray10);
        org.junit.Assert.assertNotNull(periodType11);
        org.junit.Assert.assertNotNull(julianChronology13);
        org.junit.Assert.assertNotNull(durationField14);
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + 0 + "'", int17 == 0);
    }

    @Test
    public void test1039() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1039");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Interval interval2 = yearMonth0.toInterval(dateTimeZone1);
        org.joda.time.YearMonth yearMonth4 = yearMonth0.plusMonths((int) 'a');
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray5 = yearMonth0.getFieldTypes();
        org.joda.time.Partial partial6 = new org.joda.time.Partial();
        org.joda.time.format.DateTimeFormatter dateTimeFormatter7 = partial6.getFormatter();
        java.lang.String str8 = partial6.toStringList();
        int[] intArray9 = partial6.getValues();
        org.joda.time.chrono.GJChronology gJChronology11 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay12 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology11);
        org.joda.time.DurationField durationField13 = gJChronology11.months();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Partial partial14 = new org.joda.time.Partial(dateTimeFieldTypeArray5, intArray9, (org.joda.time.Chronology) gJChronology11);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Values array must be the same length as the types array");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertNotNull(yearMonth4);
        org.junit.Assert.assertNotNull(dateTimeFieldTypeArray5);
        org.junit.Assert.assertNull(dateTimeFormatter7);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "[]" + "'", str8, "[]");
        org.junit.Assert.assertNotNull(intArray9);
        org.junit.Assert.assertArrayEquals(intArray9, new int[] {});
        org.junit.Assert.assertNotNull(gJChronology11);
        org.junit.Assert.assertNotNull(durationField13);
    }

    @Test
    public void test1040() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1040");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        java.lang.String str2 = localDateTime1.toString();
        int[] intArray3 = localDateTime1.getValues();
        int int4 = localDateTime1.getYear();
// flaky "3) test1040(RegressionTest2)":         org.junit.Assert.assertEquals("'" + str2 + "' != '" + "2026-08-06T11:12:08.147" + "'", str2, "2026-08-06T11:12:08.147");
        org.junit.Assert.assertNotNull(intArray3);
// flaky "2) test1040(RegressionTest2)":         org.junit.Assert.assertArrayEquals(intArray3, new int[] { 2026, 8, 6, 40328147 });
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 2026 + "'", int4 == 2026);
    }

    @Test
    public void test1041() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1041");
        org.joda.time.chrono.GJChronology gJChronology0 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology1 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology0);
        org.joda.time.DurationField durationField2 = lenientChronology1.years();
        org.joda.time.DateTimeField dateTimeField3 = lenientChronology1.weekyear();
        org.junit.Assert.assertNotNull(gJChronology0);
        org.junit.Assert.assertNotNull(lenientChronology1);
        org.junit.Assert.assertNotNull(durationField2);
        org.junit.Assert.assertNotNull(dateTimeField3);
    }

    @Test
    public void test1042() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1042");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.MonthDay.Property property3 = monthDay2.dayOfMonth();
        org.joda.time.MonthDay monthDay5 = monthDay2.withMonthOfYear(3);
        org.joda.time.MonthDay.Property property6 = monthDay5.dayOfMonth();
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(monthDay5);
        org.junit.Assert.assertNotNull(property6);
    }

    @Test
    public void test1043() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1043");
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
        int int14 = skipDateTimeField13.getMinimumValue();
        org.joda.time.chrono.JulianChronology julianChronology16 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField17 = julianChronology16.clockhourOfDay();
        org.joda.time.Chronology chronology18 = julianChronology16.withUTC();
        java.util.Locale locale19 = null;
        org.joda.time.format.DateTimeParserBucket dateTimeParserBucket21 = new org.joda.time.format.DateTimeParserBucket(2177280001000L, chronology18, locale19, (java.lang.Integer) 40243947);
        java.util.Locale locale22 = dateTimeParserBucket21.getLocale();
        int int23 = skipDateTimeField13.getMaximumShortTextLength(locale22);
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(durationField4);
        org.junit.Assert.assertNotNull(gJChronology5);
        org.junit.Assert.assertNotNull(lenientChronology6);
        org.junit.Assert.assertNotNull(lenientChronology7);
        org.junit.Assert.assertNotNull(dateTimeField8);
        org.junit.Assert.assertTrue("'" + long11 + "' != '" + 3600059L + "'", long11 == 3600059L);
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + (-1) + "'", int14 == (-1));
        org.junit.Assert.assertNotNull(julianChronology16);
        org.junit.Assert.assertNotNull(dateTimeField17);
        org.junit.Assert.assertNotNull(chronology18);
        org.junit.Assert.assertNotNull(locale22);
        org.junit.Assert.assertEquals(locale22.toString(), "en_US");
        org.junit.Assert.assertTrue("'" + int23 + "' != '" + 3 + "'", int23 == 3);
    }

    @Test
    public void test1044() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1044");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTime.Property property1 = dateTime0.millisOfDay();
        org.joda.time.DateTime dateTime2 = property1.roundHalfFloorCopy();
        java.util.Locale locale3 = null;
        java.lang.String str4 = property1.getAsShortText(locale3);
        org.joda.time.DateTime dateTime5 = property1.roundHalfEvenCopy();
        org.joda.time.Chronology chronology6 = null;
        org.joda.time.DateTime dateTime7 = dateTime5.withChronology(chronology6);
        org.junit.Assert.assertNotNull(property1);
        org.junit.Assert.assertNotNull(dateTime2);
// flaky "4) test1044(RegressionTest2)":         org.junit.Assert.assertEquals("'" + str4 + "' != '" + "40328267" + "'", str4, "40328267");
        org.junit.Assert.assertNotNull(dateTime5);
        org.junit.Assert.assertNotNull(dateTime7);
    }

    @Test
    public void test1045() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1045");
        int int3 = org.joda.time.field.FieldUtils.getWrappedValue((int) (short) 1, 0, 2);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 1 + "'", int3 == 1);
    }

    @Test
    public void test1046() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1046");
        org.joda.time.chrono.GJChronology gJChronology1 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.MonthDay monthDay2 = new org.joda.time.MonthDay((java.lang.Object) 1L, (org.joda.time.Chronology) gJChronology1);
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray3 = monthDay2.getFieldTypes();
        org.joda.time.MonthDay monthDay5 = monthDay2.minusMonths(69);
        org.junit.Assert.assertNotNull(gJChronology1);
        org.junit.Assert.assertNotNull(dateTimeFieldTypeArray3);
        org.junit.Assert.assertNotNull(monthDay5);
    }

    @Test
    public void test1047() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1047");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.YearMonthDay yearMonthDay5 = dateTime0.toYearMonthDay();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone10 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.DateTime dateTime11 = yearMonthDay5.toDateTimeAtMidnight((org.joda.time.DateTimeZone) fixedDateTimeZone10);
        org.joda.time.LocalDate localDate12 = yearMonthDay5.toLocalDate();
        org.joda.time.YearMonthDay yearMonthDay14 = yearMonthDay5.plusYears((int) '4');
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray15 = yearMonthDay5.getFieldTypes();
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(yearMonthDay5);
        org.junit.Assert.assertNotNull(dateTime11);
        org.junit.Assert.assertNotNull(localDate12);
        org.junit.Assert.assertNotNull(yearMonthDay14);
        org.junit.Assert.assertNotNull(dateTimeFieldTypeArray15);
    }

    @Test
    public void test1048() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1048");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        int int8 = skipUndoDateTimeField6.get((long) 10);
        int int10 = skipUndoDateTimeField6.getMaximumValue((long) 53);
        int int12 = skipUndoDateTimeField6.get((long) 7);
        org.joda.time.Chronology chronology14 = null;
        org.joda.time.DateMidnight dateMidnight15 = new org.joda.time.DateMidnight((long) (short) -1, chronology14);
        org.joda.time.DateMidnight dateMidnight17 = dateMidnight15.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight20 = dateMidnight15.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight22 = dateMidnight15.minusMonths((int) (byte) 1);
        org.joda.time.TimeOfDay timeOfDay23 = new org.joda.time.TimeOfDay((java.lang.Object) dateMidnight15);
        org.joda.time.chrono.JulianChronology julianChronology25 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField26 = julianChronology25.clockhourOfDay();
        org.joda.time.Chronology chronology27 = julianChronology25.withUTC();
        java.util.Locale locale28 = null;
        org.joda.time.format.DateTimeParserBucket dateTimeParserBucket30 = new org.joda.time.format.DateTimeParserBucket(2177280001000L, chronology27, locale28, (java.lang.Integer) 40243947);
        java.util.Locale locale31 = dateTimeParserBucket30.getLocale();
        java.util.Locale locale32 = dateTimeParserBucket30.getLocale();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str33 = skipUndoDateTimeField6.getAsText((org.joda.time.ReadablePartial) timeOfDay23, locale32);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Field 'weekOfWeekyear' is not supported");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 2 + "'", int8 == 2);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 53 + "'", int10 == 53);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 2 + "'", int12 == 2);
        org.junit.Assert.assertNotNull(dateMidnight17);
        org.junit.Assert.assertNotNull(dateMidnight20);
        org.junit.Assert.assertNotNull(dateMidnight22);
        org.junit.Assert.assertNotNull(julianChronology25);
        org.junit.Assert.assertNotNull(dateTimeField26);
        org.junit.Assert.assertNotNull(chronology27);
        org.junit.Assert.assertNotNull(locale31);
        org.junit.Assert.assertEquals(locale31.toString(), "en_US");
        org.junit.Assert.assertNotNull(locale32);
        org.junit.Assert.assertEquals(locale32.toString(), "en_US");
    }

    @Test
    public void test1049() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1049");
        org.joda.time.convert.ConverterManager converterManager0 = org.joda.time.convert.ConverterManager.getInstance();
        org.joda.time.convert.PartialConverter partialConverter1 = null;
        org.joda.time.convert.PartialConverter partialConverter2 = converterManager0.addPartialConverter(partialConverter1);
        org.joda.time.convert.PartialConverter partialConverter3 = null;
        org.joda.time.convert.PartialConverter partialConverter4 = converterManager0.addPartialConverter(partialConverter3);
        org.joda.time.convert.InstantConverter instantConverter5 = null;
        org.joda.time.convert.InstantConverter instantConverter6 = converterManager0.addInstantConverter(instantConverter5);
        org.joda.time.convert.ConverterManager converterManager7 = org.joda.time.convert.ConverterManager.getInstance();
        org.joda.time.convert.PartialConverter partialConverter8 = null;
        org.joda.time.convert.PartialConverter partialConverter9 = converterManager7.addPartialConverter(partialConverter8);
        org.joda.time.convert.PartialConverter partialConverter10 = null;
        org.joda.time.convert.PartialConverter partialConverter11 = converterManager7.addPartialConverter(partialConverter10);
        org.joda.time.Instant instant12 = org.joda.time.Instant.EPOCH;
        org.joda.time.Instant instant15 = instant12.withDurationAdded((-1L), 100);
        org.joda.time.convert.DurationConverter durationConverter16 = converterManager7.getDurationConverter((java.lang.Object) (-1L));
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
        org.joda.time.convert.PartialConverter partialConverter33 = converterManager7.getPartialConverter((java.lang.Object) localDate32);
        org.joda.time.convert.PartialConverter partialConverter34 = converterManager0.addPartialConverter(partialConverter33);
        org.junit.Assert.assertNotNull(converterManager0);
        org.junit.Assert.assertNull(partialConverter2);
        org.junit.Assert.assertNull(partialConverter4);
        org.junit.Assert.assertNull(instantConverter6);
        org.junit.Assert.assertNotNull(converterManager7);
        org.junit.Assert.assertNull(partialConverter9);
        org.junit.Assert.assertNull(partialConverter11);
        org.junit.Assert.assertNotNull(instant12);
        org.junit.Assert.assertNotNull(instant15);
        org.junit.Assert.assertNotNull(durationConverter16);
        org.junit.Assert.assertNotNull(julianChronology18);
        org.junit.Assert.assertNotNull(property20);
        org.junit.Assert.assertNotNull(localDate22);
        org.junit.Assert.assertNotNull(dateMidnight27);
        org.junit.Assert.assertNotNull(property28);
        org.junit.Assert.assertNotNull(mutableDateTime29);
        org.junit.Assert.assertTrue("'" + boolean30 + "' != '" + false + "'", boolean30 == false);
        org.junit.Assert.assertNotNull(localDate32);
        org.junit.Assert.assertNotNull(partialConverter33);
        org.junit.Assert.assertNull(partialConverter34);
    }

    @Test
    public void test1050() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1050");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.Months months5 = org.joda.time.Months.MIN_VALUE;
        org.joda.time.Months months7 = months5.plus(40256602);
        org.joda.time.DateTime dateTime8 = dateTime0.plus((org.joda.time.ReadablePeriod) months7);
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(months5);
        org.junit.Assert.assertNotNull(months7);
        org.junit.Assert.assertNotNull(dateTime8);
    }

    @Test
    public void test1051() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1051");
        int int2 = org.joda.time.field.FieldUtils.safeAdd(9, 40243947);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 40243956 + "'", int2 == 40243956);
    }

    @Test
    public void test1052() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1052");
        org.joda.time.chrono.GregorianChronology gregorianChronology0 = org.joda.time.chrono.GregorianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField1 = gregorianChronology0.weekyearOfCentury();
        org.junit.Assert.assertNotNull(gregorianChronology0);
        org.junit.Assert.assertNotNull(dateTimeField1);
    }

    @Test
    public void test1053() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1053");
        org.joda.time.LocalTime localTime1 = new org.joda.time.LocalTime((long) (-1));
        org.joda.time.LocalTime localTime3 = localTime1.withSecondOfMinute(1);
        org.joda.time.LocalTime localTime5 = localTime1.plusSeconds(0);
        org.joda.time.LocalTime localTime7 = localTime1.plusSeconds(86400000);
        org.joda.time.LocalTime.Property property8 = localTime7.hourOfDay();
        org.joda.time.Chronology chronology10 = null;
        org.joda.time.DateMidnight dateMidnight11 = new org.joda.time.DateMidnight((long) (short) -1, chronology10);
        org.joda.time.DateMidnight dateMidnight13 = dateMidnight11.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property14 = dateMidnight11.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime15 = dateMidnight11.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property16 = mutableDateTime15.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime17 = property16.roundHalfCeiling();
        org.joda.time.MutableDateTime mutableDateTime18 = property16.getMutableDateTime();
        org.joda.time.DateTimeFieldType dateTimeFieldType19 = property16.getFieldType();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.LocalTime.Property property20 = localTime7.property(dateTimeFieldType19);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Field 'centuryOfEra' is not supported");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(localTime3);
        org.junit.Assert.assertNotNull(localTime5);
        org.junit.Assert.assertNotNull(localTime7);
        org.junit.Assert.assertNotNull(property8);
        org.junit.Assert.assertNotNull(dateMidnight13);
        org.junit.Assert.assertNotNull(property14);
        org.junit.Assert.assertNotNull(mutableDateTime15);
        org.junit.Assert.assertNotNull(property16);
        org.junit.Assert.assertNotNull(mutableDateTime17);
        org.junit.Assert.assertNotNull(mutableDateTime18);
        org.junit.Assert.assertNotNull(dateTimeFieldType19);
    }

    @Test
    public void test1054() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1054");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        mutableDateTime6.addMonths(7860000);
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
    }

    @Test
    public void test1055() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1055");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = property3.addToCopy(1);
        int int6 = localDate5.getEra();
        org.joda.time.LocalDate localDate8 = localDate5.plusYears(20);
        org.joda.time.Chronology chronology10 = null;
        org.joda.time.DateMidnight dateMidnight11 = new org.joda.time.DateMidnight((long) (short) -1, chronology10);
        org.joda.time.DateMidnight dateMidnight13 = dateMidnight11.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight16 = dateMidnight11.withDurationAdded((long) 10, 3);
        java.util.GregorianCalendar gregorianCalendar17 = dateMidnight16.toGregorianCalendar();
        org.joda.time.LocalTime localTime18 = org.joda.time.LocalTime.fromCalendarFields((java.util.Calendar) gregorianCalendar17);
        org.joda.time.LocalTime.Property property19 = localTime18.minuteOfHour();
        org.joda.time.LocalTime.Property property20 = localTime18.millisOfSecond();
        org.joda.time.LocalTime localTime22 = property20.addCopy(0L);
        org.joda.time.LocalTime.Property property23 = localTime22.millisOfDay();
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone29 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology30 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone29);
        org.joda.time.LocalDateTime localDateTime31 = new org.joda.time.LocalDateTime((long) (-1), (org.joda.time.DateTimeZone) fixedDateTimeZone29);
        org.joda.time.tz.CachedDateTimeZone cachedDateTimeZone32 = org.joda.time.tz.CachedDateTimeZone.forZone((org.joda.time.DateTimeZone) fixedDateTimeZone29);
        org.joda.time.chrono.ISOChronology iSOChronology33 = org.joda.time.chrono.ISOChronology.getInstance((org.joda.time.DateTimeZone) cachedDateTimeZone32);
        java.lang.String str35 = cachedDateTimeZone32.getNameKey((long) 3600);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.DateTime dateTime36 = localDate8.toDateTime(localTime22, (org.joda.time.DateTimeZone) cachedDateTimeZone32);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: The chronology of the time does not match");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 1 + "'", int6 == 1);
        org.junit.Assert.assertNotNull(localDate8);
        org.junit.Assert.assertNotNull(dateMidnight13);
        org.junit.Assert.assertNotNull(dateMidnight16);
        org.junit.Assert.assertNotNull(gregorianCalendar17);
        org.junit.Assert.assertNotNull(localTime18);
        org.junit.Assert.assertNotNull(property19);
        org.junit.Assert.assertNotNull(property20);
        org.junit.Assert.assertNotNull(localTime22);
        org.junit.Assert.assertNotNull(property23);
        org.junit.Assert.assertNotNull(gregorianChronology30);
        org.junit.Assert.assertNotNull(cachedDateTimeZone32);
        org.junit.Assert.assertNotNull(iSOChronology33);
        org.junit.Assert.assertEquals("'" + str35 + "' != '" + "hi!" + "'", str35, "hi!");
    }

    @Test
    public void test1056() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1056");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        org.joda.time.LocalDateTime.Property property5 = localDateTime1.millisOfDay();
        int int6 = localDateTime1.getCenturyOfEra();
        int int7 = localDateTime1.getDayOfMonth();
        org.joda.time.chrono.GJChronology gJChronology8 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField9 = gJChronology8.dayOfYear();
        org.joda.time.DurationField durationField10 = gJChronology8.years();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MutablePeriod mutablePeriod11 = new org.joda.time.MutablePeriod((java.lang.Object) int7, (org.joda.time.Chronology) gJChronology8);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: No period converter found for type: java.lang.Integer");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 20 + "'", int6 == 20);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 6 + "'", int7 == 6);
        org.junit.Assert.assertNotNull(gJChronology8);
        org.junit.Assert.assertNotNull(dateTimeField9);
        org.junit.Assert.assertNotNull(durationField10);
    }

    @Test
    public void test1057() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1057");
        org.joda.time.Instant instant0 = org.joda.time.Instant.now();
        org.joda.time.Instant instant1 = org.joda.time.Instant.EPOCH;
        org.joda.time.Instant instant4 = instant1.withDurationAdded((-1L), 100);
        org.joda.time.Duration duration6 = org.joda.time.Duration.standardDays((long) (short) 1);
        org.joda.time.PeriodType periodType8 = null;
        org.joda.time.Chronology chronology9 = null;
        org.joda.time.Period period10 = new org.joda.time.Period((long) (short) 10, periodType8, chronology9);
        org.joda.time.Months months11 = org.joda.time.Months.FIVE;
        org.joda.time.Period period12 = period10.minus((org.joda.time.ReadablePeriod) months11);
        org.joda.time.Chronology chronology14 = null;
        org.joda.time.DateMidnight dateMidnight15 = new org.joda.time.DateMidnight((long) (short) -1, chronology14);
        org.joda.time.DateMidnight dateMidnight17 = dateMidnight15.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology19 = null;
        org.joda.time.DateMidnight dateMidnight20 = new org.joda.time.DateMidnight((long) (short) -1, chronology19);
        org.joda.time.DateMidnight dateMidnight22 = dateMidnight20.minusMonths((int) (byte) 100);
        int int23 = dateMidnight17.compareTo((org.joda.time.ReadableInstant) dateMidnight20);
        org.joda.time.Duration duration24 = period12.toDurationTo((org.joda.time.ReadableInstant) dateMidnight20);
        org.joda.time.Duration duration25 = duration24.negated();
        boolean boolean26 = duration6.isShorterThan((org.joda.time.ReadableDuration) duration24);
        org.joda.time.Instant instant27 = instant1.plus((org.joda.time.ReadableDuration) duration6);
        org.joda.time.Instant instant28 = instant0.plus((org.joda.time.ReadableDuration) duration6);
        boolean boolean30 = instant28.isAfter((long) 86400000);
        org.junit.Assert.assertNotNull(instant0);
        org.junit.Assert.assertNotNull(instant1);
        org.junit.Assert.assertNotNull(instant4);
        org.junit.Assert.assertNotNull(duration6);
        org.junit.Assert.assertNotNull(months11);
        org.junit.Assert.assertNotNull(period12);
        org.junit.Assert.assertNotNull(dateMidnight17);
        org.junit.Assert.assertNotNull(dateMidnight22);
        org.junit.Assert.assertTrue("'" + int23 + "' != '" + (-1) + "'", int23 == (-1));
        org.junit.Assert.assertNotNull(duration24);
        org.junit.Assert.assertNotNull(duration25);
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + false + "'", boolean26 == false);
        org.junit.Assert.assertNotNull(instant27);
        org.junit.Assert.assertNotNull(instant28);
        org.junit.Assert.assertTrue("'" + boolean30 + "' != '" + true + "'", boolean30 == true);
    }

    @Test
    public void test1058() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1058");
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
        org.joda.time.DateTimeFieldType dateTimeFieldType12 = skipUndoDateTimeField8.getType();
        org.joda.time.chrono.JulianChronology julianChronology16 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField17 = julianChronology16.clockhourOfDay();
        org.joda.time.Chronology chronology18 = julianChronology16.withUTC();
        java.util.Locale locale19 = null;
        org.joda.time.format.DateTimeParserBucket dateTimeParserBucket21 = new org.joda.time.format.DateTimeParserBucket(2177280001000L, chronology18, locale19, (java.lang.Integer) 40243947);
        java.util.Locale locale22 = dateTimeParserBucket21.getLocale();
        // The following exception was thrown during execution in test generation
        try {
            long long23 = skipUndoDateTimeField8.set((long) 'a', "Europe/Prague", locale22);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value \"Europe/Prague\" for yearOfCentury is not supported");
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
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "yearOfCentury" + "'", str11, "yearOfCentury");
        org.junit.Assert.assertNotNull(dateTimeFieldType12);
        org.junit.Assert.assertNotNull(julianChronology16);
        org.junit.Assert.assertNotNull(dateTimeField17);
        org.junit.Assert.assertNotNull(chronology18);
        org.junit.Assert.assertNotNull(locale22);
        org.junit.Assert.assertEquals(locale22.toString(), "en_US");
    }

    @Test
    public void test1059() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1059");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.basicDate();
        java.lang.Appendable appendable1 = null;
        // The following exception was thrown during execution in test generation
        try {
            dateTimeFormatter0.printTo(appendable1, (long) 100);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
    }

    @Test
    public void test1060() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1060");
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
        org.joda.time.DateMidnight dateMidnight22 = org.joda.time.DateMidnight.now((org.joda.time.DateTimeZone) fixedDateTimeZone4);
        org.joda.time.DateTimeZone.setDefault((org.joda.time.DateTimeZone) fixedDateTimeZone4);
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
        org.junit.Assert.assertNotNull(dateMidnight22);
    }

    @Test
    public void test1061() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1061");
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
        org.joda.time.DurationField durationField14 = offsetDateTimeField11.getLeapDurationField();
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 2 + "'", int8 == 2);
        org.junit.Assert.assertNotNull(durationField9);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + (-259200100L) + "'", long13 == (-259200100L));
        org.junit.Assert.assertNull(durationField14);
    }

    @Test
    public void test1062() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1062");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime9 = property7.addWrapField((int) (short) -1);
        org.joda.time.MutableDateTime mutableDateTime10 = property7.roundHalfFloor();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(mutableDateTime9);
        org.junit.Assert.assertNotNull(mutableDateTime10);
    }

    @Test
    public void test1063() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1063");
        int int1 = org.joda.time.format.FormatUtils.calculateDigitCount((long) 86400000);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 8 + "'", int1 == 8);
    }

    @Test
    public void test1064() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1064");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DateTime dateTime1 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone2 = null;
        org.joda.time.DateTime dateTime3 = dateTime1.withZoneRetainFields(dateTimeZone2);
        org.joda.time.DateTime dateTime5 = dateTime1.withYear((int) (byte) 0);
        org.joda.time.DateTime dateTime7 = dateTime1.withWeekyear((int) (byte) 0);
        org.joda.time.DateTime dateTime9 = dateTime7.plusSeconds((int) (byte) -1);
        org.joda.time.DateTime.Property property10 = dateTime9.year();
        org.joda.time.DateTime dateTime11 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone12 = null;
        org.joda.time.DateTime dateTime13 = dateTime11.withZoneRetainFields(dateTimeZone12);
        org.joda.time.DateTime dateTime15 = dateTime11.withYear((int) (byte) 0);
        org.joda.time.DateTime dateTime17 = dateTime11.withWeekyear((int) (byte) 0);
        org.joda.time.DateTime dateTime19 = dateTime17.plusSeconds((int) (byte) -1);
        org.joda.time.DateTime.Property property20 = dateTime19.year();
        int int21 = property10.compareTo((org.joda.time.ReadableInstant) dateTime19);
        org.joda.time.DateTime dateTime22 = dateTime19.withTimeAtStartOfDay();
        boolean boolean23 = julianChronology0.equals((java.lang.Object) dateTime19);
        org.joda.time.DateTime.Property property24 = dateTime19.minuteOfDay();
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(dateTime3);
        org.junit.Assert.assertNotNull(dateTime5);
        org.junit.Assert.assertNotNull(dateTime7);
        org.junit.Assert.assertNotNull(dateTime9);
        org.junit.Assert.assertNotNull(property10);
        org.junit.Assert.assertNotNull(dateTime13);
        org.junit.Assert.assertNotNull(dateTime15);
        org.junit.Assert.assertNotNull(dateTime17);
        org.junit.Assert.assertNotNull(dateTime19);
        org.junit.Assert.assertNotNull(property20);
        org.junit.Assert.assertTrue("'" + int21 + "' != '" + 0 + "'", int21 == 0);
        org.junit.Assert.assertNotNull(dateTime22);
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
        org.junit.Assert.assertNotNull(property24);
    }

    @Test
    public void test1065() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1065");
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
        long long17 = dividedDateTimeField13.add((-3599891L), (long) 20);
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
        org.junit.Assert.assertTrue("'" + long17 + "' != '" + 6980400109L + "'", long17 == 6980400109L);
    }

    @Test
    public void test1066() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1066");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = localDate2.withWeekyear((int) (byte) 100);
        org.joda.time.LocalDate.Property property6 = localDate5.yearOfCentury();
        org.joda.time.LocalDate localDate8 = localDate5.minusDays(40299577);
        org.joda.time.chrono.JulianChronology julianChronology10 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate11 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology10);
        org.joda.time.LocalDate.Property property12 = localDate11.yearOfCentury();
        org.joda.time.LocalDate localDate14 = localDate11.withWeekyear((int) (byte) 100);
        org.joda.time.Chronology chronology16 = null;
        org.joda.time.DateMidnight dateMidnight17 = new org.joda.time.DateMidnight((long) (short) -1, chronology16);
        org.joda.time.DateMidnight dateMidnight19 = dateMidnight17.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property20 = dateMidnight17.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime21 = dateMidnight17.toMutableDateTimeISO();
        boolean boolean22 = localDate14.equals((java.lang.Object) mutableDateTime21);
        org.joda.time.LocalDate localDate24 = localDate14.plusMonths(10);
        int int25 = localDate14.getWeekOfWeekyear();
        org.joda.time.chrono.JulianChronology julianChronology27 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate28 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology27);
        org.joda.time.LocalDate.Property property29 = localDate28.yearOfCentury();
        org.joda.time.LocalDate localDate31 = localDate28.withWeekyear((int) (byte) 100);
        org.joda.time.Chronology chronology33 = null;
        org.joda.time.DateMidnight dateMidnight34 = new org.joda.time.DateMidnight((long) (short) -1, chronology33);
        org.joda.time.DateMidnight dateMidnight36 = dateMidnight34.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property37 = dateMidnight34.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime38 = dateMidnight34.toMutableDateTimeISO();
        boolean boolean39 = localDate31.equals((java.lang.Object) mutableDateTime38);
        org.joda.time.LocalDate localDate41 = localDate31.plusMonths(10);
        boolean boolean42 = localDate14.isEqual((org.joda.time.ReadablePartial) localDate31);
        org.joda.time.Years years43 = org.joda.time.Years.yearsBetween((org.joda.time.ReadablePartial) localDate5, (org.joda.time.ReadablePartial) localDate31);
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(property6);
        org.junit.Assert.assertNotNull(localDate8);
        org.junit.Assert.assertNotNull(julianChronology10);
        org.junit.Assert.assertNotNull(property12);
        org.junit.Assert.assertNotNull(localDate14);
        org.junit.Assert.assertNotNull(dateMidnight19);
        org.junit.Assert.assertNotNull(property20);
        org.junit.Assert.assertNotNull(mutableDateTime21);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + false + "'", boolean22 == false);
        org.junit.Assert.assertNotNull(localDate24);
        org.junit.Assert.assertTrue("'" + int25 + "' != '" + 51 + "'", int25 == 51);
        org.junit.Assert.assertNotNull(julianChronology27);
        org.junit.Assert.assertNotNull(property29);
        org.junit.Assert.assertNotNull(localDate31);
        org.junit.Assert.assertNotNull(dateMidnight36);
        org.junit.Assert.assertNotNull(property37);
        org.junit.Assert.assertNotNull(mutableDateTime38);
        org.junit.Assert.assertTrue("'" + boolean39 + "' != '" + false + "'", boolean39 == false);
        org.junit.Assert.assertNotNull(localDate41);
        org.junit.Assert.assertTrue("'" + boolean42 + "' != '" + true + "'", boolean42 == true);
        org.junit.Assert.assertNotNull(years43);
    }

    @Test
    public void test1067() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1067");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime8 = property7.roundFloor();
        long long9 = mutableDateTime8.getMillis();
        int int10 = mutableDateTime8.getWeekyear();
        // The following exception was thrown during execution in test generation
        try {
            mutableDateTime8.setSecondOfDay(40299577);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 40299577 for secondOfDay must be in the range [0,86399]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(mutableDateTime8);
        org.junit.Assert.assertTrue("'" + long9 + "' != '" + (-2208988800100L) + "'", long9 == (-2208988800100L));
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 1900 + "'", int10 == 1900);
    }

    @Test
    public void test1068() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1068");
        org.joda.time.Minutes minutes0 = org.joda.time.Minutes.MAX_VALUE;
        org.joda.time.Minutes minutes1 = org.joda.time.Minutes.MAX_VALUE;
        org.joda.time.Minutes minutes2 = minutes0.minus(minutes1);
        org.joda.time.Minutes minutes4 = minutes2.minus(100);
        org.joda.time.Duration duration5 = minutes4.toStandardDuration();
        org.joda.time.Chronology chronology7 = null;
        org.joda.time.DateMidnight dateMidnight8 = new org.joda.time.DateMidnight((long) (short) -1, chronology7);
        org.joda.time.DateMidnight dateMidnight10 = dateMidnight8.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property11 = dateMidnight8.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime12 = dateMidnight8.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property13 = mutableDateTime12.centuryOfEra();
        mutableDateTime12.setSecondOfMinute(24);
        org.joda.time.DateTime dateTime16 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone17 = null;
        org.joda.time.DateTime dateTime18 = dateTime16.withZoneRetainFields(dateTimeZone17);
        boolean boolean19 = mutableDateTime12.isAfter((org.joda.time.ReadableInstant) dateTime16);
        org.joda.time.DateTime dateTime21 = dateTime16.minusMillis(3);
        org.joda.time.DateTime dateTime23 = dateTime21.minusMillis(4);
        org.joda.time.LocalDate localDate24 = dateTime23.toLocalDate();
        org.joda.time.Period period25 = new org.joda.time.Period((org.joda.time.ReadableDuration) duration5, (org.joda.time.ReadableInstant) dateTime23);
        org.junit.Assert.assertNotNull(minutes0);
        org.junit.Assert.assertNotNull(minutes1);
        org.junit.Assert.assertNotNull(minutes2);
        org.junit.Assert.assertNotNull(minutes4);
        org.junit.Assert.assertNotNull(duration5);
        org.junit.Assert.assertNotNull(dateMidnight10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(mutableDateTime12);
        org.junit.Assert.assertNotNull(property13);
        org.junit.Assert.assertNotNull(dateTime18);
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
        org.junit.Assert.assertNotNull(dateTime21);
        org.junit.Assert.assertNotNull(dateTime23);
        org.junit.Assert.assertNotNull(localDate24);
    }

    @Test
    public void test1069() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1069");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = localDate2.withWeekyear((int) (byte) 100);
        org.joda.time.LocalDate.Property property6 = localDate5.yearOfCentury();
        org.joda.time.LocalDate localDate7 = property6.withMaximumValue();
        org.joda.time.LocalDate.Property property8 = localDate7.monthOfYear();
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(property6);
        org.junit.Assert.assertNotNull(localDate7);
        org.junit.Assert.assertNotNull(property8);
    }

    @Test
    public void test1070() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1070");
        org.joda.time.format.DateTimeFormatterBuilder dateTimeFormatterBuilder0 = new org.joda.time.format.DateTimeFormatterBuilder();
        org.joda.time.format.DateTimeFormatterBuilder dateTimeFormatterBuilder2 = dateTimeFormatterBuilder0.appendDayOfYear(32);
        org.joda.time.format.DateTimeFormatter dateTimeFormatter3 = org.joda.time.format.DateTimeFormat.longDate();
        org.joda.time.format.DateTimePrinter dateTimePrinter4 = dateTimeFormatter3.getPrinter();
        org.joda.time.format.DateTimeParser[] dateTimeParserArray5 = new org.joda.time.format.DateTimeParser[] {};
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.format.DateTimeFormatterBuilder dateTimeFormatterBuilder6 = dateTimeFormatterBuilder0.append(dateTimePrinter4, dateTimeParserArray5);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 0");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeFormatterBuilder2);
        org.junit.Assert.assertNotNull(dateTimeFormatter3);
        org.junit.Assert.assertNotNull(dateTimePrinter4);
        org.junit.Assert.assertNotNull(dateTimeParserArray5);
        org.junit.Assert.assertArrayEquals(dateTimeParserArray5, new org.joda.time.format.DateTimeParser[] {});
    }

    @Test
    public void test1071() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1071");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate5 = localDate2.withWeekyear((int) (byte) 100);
        org.joda.time.LocalDate.Property property6 = localDate5.yearOfCentury();
        org.joda.time.LocalDate localDate8 = localDate5.minusDays(40299577);
        org.joda.time.DateTime dateTime9 = localDate8.toDateTimeAtStartOfDay();
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(property6);
        org.junit.Assert.assertNotNull(localDate8);
        org.junit.Assert.assertNotNull(dateTime9);
    }

    @Test
    public void test1072() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1072");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Interval interval2 = yearMonth0.toInterval(dateTimeZone1);
        org.joda.time.Months months3 = org.joda.time.Months.ONE;
        org.joda.time.Interval interval4 = interval2.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) months3);
        org.joda.time.DateTime dateTime5 = interval2.getEnd();
        org.joda.time.DateTime dateTime7 = dateTime5.plusSeconds(100);
        java.util.Date date8 = dateTime5.toDate();
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertNotNull(months3);
        org.junit.Assert.assertNotNull(interval4);
        org.junit.Assert.assertNotNull(dateTime5);
        org.junit.Assert.assertNotNull(dateTime7);
        org.junit.Assert.assertNotNull(date8);
        org.junit.Assert.assertEquals(date8.toString(), "Tue Sep 01 01:59:59 CEST 2026");
    }

    @Test
    public void test1073() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1073");
        long long2 = org.joda.time.field.FieldUtils.safeSubtract((long) 219, (long) 5);
        org.junit.Assert.assertTrue("'" + long2 + "' != '" + 214L + "'", long2 == 214L);
    }

    @Test
    public void test1074() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1074");
        int int0 = org.joda.time.MutableDateTime.ROUND_HALF_EVEN;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 5 + "'", int0 == 5);
    }

    @Test
    public void test1075() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1075");
        org.joda.time.chrono.EthiopicChronology ethiopicChronology0 = org.joda.time.chrono.EthiopicChronology.getInstanceUTC();
        org.joda.time.Chronology chronology1 = ethiopicChronology0.withUTC();
        org.joda.time.DateTimeField dateTimeField2 = ethiopicChronology0.secondOfDay();
        org.junit.Assert.assertNotNull(ethiopicChronology0);
        org.junit.Assert.assertNotNull(chronology1);
        org.junit.Assert.assertNotNull(dateTimeField2);
    }

    @Test
    public void test1076() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1076");
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
        mutableDateTime6.addMillis(2000);
        int int18 = mutableDateTime6.getWeekyear();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(dateMidnight14);
        org.junit.Assert.assertNotNull(months15);
        org.junit.Assert.assertTrue("'" + int18 + "' != '" + 1970 + "'", int18 == 1970);
    }

    @Test
    public void test1077() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1077");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter1 = org.joda.time.format.ISODateTimeFormat.localDateParser();
        org.joda.time.DateMidnight dateMidnight2 = org.joda.time.DateMidnight.parse("40256602", dateTimeFormatter1);
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone8 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.GregorianChronology gregorianChronology9 = org.joda.time.chrono.GregorianChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone8);
        org.joda.time.LocalDateTime localDateTime10 = new org.joda.time.LocalDateTime((long) (-1), (org.joda.time.DateTimeZone) fixedDateTimeZone8);
        org.joda.time.tz.CachedDateTimeZone cachedDateTimeZone11 = org.joda.time.tz.CachedDateTimeZone.forZone((org.joda.time.DateTimeZone) fixedDateTimeZone8);
        long long13 = fixedDateTimeZone8.previousTransition(1L);
        org.joda.time.format.DateTimeFormatter dateTimeFormatter14 = dateTimeFormatter1.withZone((org.joda.time.DateTimeZone) fixedDateTimeZone8);
        boolean boolean15 = dateTimeFormatter14.isPrinter();
        org.junit.Assert.assertNotNull(dateTimeFormatter1);
        org.junit.Assert.assertNotNull(dateMidnight2);
        org.junit.Assert.assertNotNull(gregorianChronology9);
        org.junit.Assert.assertNotNull(cachedDateTimeZone11);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + 1L + "'", long13 == 1L);
        org.junit.Assert.assertNotNull(dateTimeFormatter14);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test1078() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1078");
        org.joda.time.chrono.GregorianChronology gregorianChronology1 = org.joda.time.chrono.GregorianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField2 = gregorianChronology1.minuteOfDay();
        org.joda.time.DateTime dateTime3 = org.joda.time.DateTime.now((org.joda.time.Chronology) gregorianChronology1);
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone8 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology9 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone8);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType10 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology12 = null;
        org.joda.time.DateMidnight dateMidnight13 = new org.joda.time.DateMidnight((long) (short) -1, chronology12);
        org.joda.time.DateMidnight dateMidnight15 = dateMidnight13.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property16 = dateMidnight13.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime17 = dateMidnight13.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property18 = mutableDateTime17.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime20 = property18.addWrapField((int) (short) -1);
        boolean boolean21 = leapYearPatternType10.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology22 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone8, leapYearPatternType10);
        org.joda.time.MonthDay monthDay23 = new org.joda.time.MonthDay((org.joda.time.DateTimeZone) fixedDateTimeZone8);
        org.joda.time.DateMidnight dateMidnight24 = new org.joda.time.DateMidnight((org.joda.time.DateTimeZone) fixedDateTimeZone8);
        org.joda.time.chrono.CopticChronology copticChronology25 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone8);
        org.joda.time.DateMidnight dateMidnight26 = org.joda.time.DateMidnight.now((org.joda.time.DateTimeZone) fixedDateTimeZone8);
        org.joda.time.Chronology chronology27 = gregorianChronology1.withZone((org.joda.time.DateTimeZone) fixedDateTimeZone8);
        org.joda.time.Period period28 = new org.joda.time.Period((long) (short) 100, chronology27);
        org.joda.time.MutableDateTime mutableDateTime29 = new org.joda.time.MutableDateTime(chronology27);
        org.junit.Assert.assertNotNull(gregorianChronology1);
        org.junit.Assert.assertNotNull(dateTimeField2);
        org.junit.Assert.assertNotNull(dateTime3);
        org.junit.Assert.assertNotNull(copticChronology9);
        org.junit.Assert.assertNotNull(leapYearPatternType10);
        org.junit.Assert.assertNotNull(dateMidnight15);
        org.junit.Assert.assertNotNull(property16);
        org.junit.Assert.assertNotNull(mutableDateTime17);
        org.junit.Assert.assertNotNull(property18);
        org.junit.Assert.assertNotNull(mutableDateTime20);
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + false + "'", boolean21 == false);
        org.junit.Assert.assertNotNull(islamicChronology22);
        org.junit.Assert.assertNotNull(copticChronology25);
        org.junit.Assert.assertNotNull(dateMidnight26);
        org.junit.Assert.assertNotNull(chronology27);
    }

    @Test
    public void test1079() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1079");
        int int0 = org.joda.time.DateTimeConstants.AD;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 1 + "'", int0 == 1);
    }

    @Test
    public void test1080() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1080");
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
        org.joda.time.format.PeriodFormatter periodFormatter80 = org.joda.time.format.ISOPeriodFormat.alternateExtendedWithWeeks();
        java.lang.String str81 = mutablePeriod11.toString(periodFormatter80);
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
        org.junit.Assert.assertNotNull(periodFormatter80);
        org.junit.Assert.assertEquals("'" + str81 + "' != '" + "P0001-W-01--01T01:00:96.403" + "'", str81, "P0001-W-01--01T01:00:96.403");
    }

    @Test
    public void test1081() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1081");
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
        org.joda.time.Chronology chronology32 = null;
        org.joda.time.DateMidnight dateMidnight33 = new org.joda.time.DateMidnight((long) (short) -1, chronology32);
        org.joda.time.DateMidnight dateMidnight35 = dateMidnight33.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property36 = dateMidnight33.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime37 = dateMidnight33.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property38 = mutableDateTime37.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime40 = property38.addWrapField((int) (short) -1);
        org.joda.time.MutableDateTime.Property property41 = mutableDateTime40.monthOfYear();
        org.joda.time.Duration duration43 = org.joda.time.Duration.standardMinutes((long) (short) 1);
        org.joda.time.Duration duration45 = org.joda.time.Duration.standardDays((long) (short) 1);
        org.joda.time.Duration duration47 = duration43.withDurationAdded((org.joda.time.ReadableDuration) duration45, (int) (byte) -1);
        org.joda.time.MutableInterval mutableInterval48 = new org.joda.time.MutableInterval((org.joda.time.ReadableInstant) mutableDateTime40, (org.joda.time.ReadableDuration) duration45);
        mutableInterval48.setInterval((long) '4', 2700000L);
        org.joda.time.Chronology chronology52 = mutableInterval48.getChronology();
        boolean boolean53 = interval30.isBefore((org.joda.time.ReadableInterval) mutableInterval48);
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
        org.junit.Assert.assertNotNull(dateMidnight35);
        org.junit.Assert.assertNotNull(property36);
        org.junit.Assert.assertNotNull(mutableDateTime37);
        org.junit.Assert.assertNotNull(property38);
        org.junit.Assert.assertNotNull(mutableDateTime40);
        org.junit.Assert.assertNotNull(property41);
        org.junit.Assert.assertNotNull(duration43);
        org.junit.Assert.assertNotNull(duration45);
        org.junit.Assert.assertNotNull(duration47);
        org.junit.Assert.assertNotNull(chronology52);
        org.junit.Assert.assertTrue("'" + boolean53 + "' != '" + false + "'", boolean53 == false);
    }

    @Test
    public void test1082() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1082");
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
        org.joda.time.LocalDateTime.Property property14 = localDateTime12.weekyear();
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(localDateTime8);
        org.junit.Assert.assertNotNull(property9);
        org.junit.Assert.assertNotNull(dateTime10);
        org.junit.Assert.assertNotNull(property11);
        org.junit.Assert.assertNotNull(localDateTime12);
        org.junit.Assert.assertNotNull(property13);
        org.junit.Assert.assertNotNull(property14);
    }

    @Test
    public void test1083() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1083");
        org.joda.time.Weeks weeks1 = org.joda.time.Weeks.weeks(20);
        org.joda.time.DurationFieldType durationFieldType2 = weeks1.getFieldType();
        org.junit.Assert.assertNotNull(weeks1);
        org.junit.Assert.assertNotNull(durationFieldType2);
    }

    @Test
    public void test1084() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1084");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.LocalDate localDate2 = new org.joda.time.LocalDate((long) 8, (org.joda.time.Chronology) julianChronology1);
        org.joda.time.LocalDate.Property property3 = localDate2.yearOfCentury();
        org.joda.time.LocalDate localDate4 = property3.roundHalfFloorCopy();
        org.joda.time.LocalDate localDate5 = property3.roundHalfCeilingCopy();
        org.joda.time.YearMonth yearMonth6 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeFieldType[] dateTimeFieldTypeArray7 = yearMonth6.getFieldTypes();
        org.joda.time.YearMonth yearMonth9 = yearMonth6.withMonthOfYear(1);
        org.joda.time.Hours hours11 = org.joda.time.Hours.hours(100);
        org.joda.time.Hours hours13 = hours11.minus((int) (byte) 0);
        org.joda.time.YearMonth yearMonth15 = yearMonth6.withPeriodAdded((org.joda.time.ReadablePeriod) hours13, 40256602);
        org.joda.time.YearMonth yearMonth17 = yearMonth6.withYear((int) (short) -1);
        // The following exception was thrown during execution in test generation
        try {
            int int18 = localDate5.compareTo((org.joda.time.ReadablePartial) yearMonth17);
            org.junit.Assert.fail("Expected exception of type java.lang.ClassCastException; message: ReadablePartial objects must have matching field types");
        } catch (java.lang.ClassCastException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(localDate4);
        org.junit.Assert.assertNotNull(localDate5);
        org.junit.Assert.assertNotNull(dateTimeFieldTypeArray7);
        org.junit.Assert.assertNotNull(yearMonth9);
        org.junit.Assert.assertNotNull(hours11);
        org.junit.Assert.assertNotNull(hours13);
        org.junit.Assert.assertNotNull(yearMonth15);
        org.junit.Assert.assertNotNull(yearMonth17);
    }

    @Test
    public void test1085() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1085");
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder0 = new org.joda.time.format.PeriodFormatterBuilder();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder3 = periodFormatterBuilder0.appendPrefix("PeriodType[Millis]", "");
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder5 = periodFormatterBuilder3.minimumPrintedDigits((int) (short) -1);
        org.joda.time.format.PeriodPrinter periodPrinter6 = periodFormatterBuilder3.toPrinter();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder7 = periodFormatterBuilder3.appendYears();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder9 = periodFormatterBuilder7.appendSeparatorIfFieldsBefore("[yearOfCentury=53]");
        org.junit.Assert.assertNotNull(periodFormatterBuilder3);
        org.junit.Assert.assertNotNull(periodFormatterBuilder5);
        org.junit.Assert.assertNotNull(periodPrinter6);
        org.junit.Assert.assertNotNull(periodFormatterBuilder7);
        org.junit.Assert.assertNotNull(periodFormatterBuilder9);
    }

    @Test
    public void test1086() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1086");
        org.joda.time.chrono.JulianChronology julianChronology1 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField2 = julianChronology1.clockhourOfDay();
        org.joda.time.Chronology chronology3 = julianChronology1.withUTC();
        java.util.Locale locale4 = null;
        org.joda.time.format.DateTimeParserBucket dateTimeParserBucket6 = new org.joda.time.format.DateTimeParserBucket(2177280001000L, chronology3, locale4, (java.lang.Integer) 40243947);
        java.lang.Integer int7 = dateTimeParserBucket6.getOffsetInteger();
        org.junit.Assert.assertNotNull(julianChronology1);
        org.junit.Assert.assertNotNull(dateTimeField2);
        org.junit.Assert.assertNotNull(chronology3);
        org.junit.Assert.assertNull(int7);
    }

    @Test
    public void test1087() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1087");
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder0 = new org.joda.time.format.PeriodFormatterBuilder();
        periodFormatterBuilder0.clear();
        org.joda.time.format.PeriodFormatter periodFormatter2 = org.joda.time.format.ISOPeriodFormat.alternateWithWeeks();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder3 = periodFormatterBuilder0.append(periodFormatter2);
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder4 = periodFormatterBuilder3.appendYears();
        org.joda.time.format.PeriodFormatterBuilder periodFormatterBuilder7 = periodFormatterBuilder3.appendPrefix("PT86400000H", "2147483647");
        org.junit.Assert.assertNotNull(periodFormatter2);
        org.junit.Assert.assertNotNull(periodFormatterBuilder3);
        org.junit.Assert.assertNotNull(periodFormatterBuilder4);
        org.junit.Assert.assertNotNull(periodFormatterBuilder7);
    }

    @Test
    public void test1088() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1088");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.Chronology chronology2 = null;
        org.joda.time.DateMidnight dateMidnight3 = new org.joda.time.DateMidnight((long) (short) -1, chronology2);
        org.joda.time.DateMidnight dateMidnight5 = dateMidnight3.minusMonths((int) (byte) 100);
        org.joda.time.Chronology chronology7 = null;
        org.joda.time.DateMidnight dateMidnight8 = new org.joda.time.DateMidnight((long) (short) -1, chronology7);
        org.joda.time.DateMidnight dateMidnight10 = dateMidnight8.minusMonths((int) (byte) 100);
        int int11 = dateMidnight5.compareTo((org.joda.time.ReadableInstant) dateMidnight8);
        org.joda.time.chrono.GJChronology gJChronology13 = org.joda.time.chrono.GJChronology.getInstance(dateTimeZone0, (org.joda.time.ReadableInstant) dateMidnight8, 7);
        org.joda.time.DateMidnight dateMidnight15 = dateMidnight8.withYear((int) '#');
        java.util.GregorianCalendar gregorianCalendar16 = dateMidnight8.toGregorianCalendar();
        org.junit.Assert.assertNotNull(dateMidnight5);
        org.junit.Assert.assertNotNull(dateMidnight10);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + (-1) + "'", int11 == (-1));
        org.junit.Assert.assertNotNull(gJChronology13);
        org.junit.Assert.assertNotNull(dateMidnight15);
        org.junit.Assert.assertNotNull(gregorianCalendar16);
    }

    @Test
    public void test1089() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1089");
        org.joda.time.chrono.JulianChronology julianChronology0 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DurationField durationField1 = julianChronology0.weeks();
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.chrono.LenientChronology lenientChronology3 = org.joda.time.chrono.LenientChronology.getInstance((org.joda.time.Chronology) gJChronology2);
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.weekOfWeekyear();
        org.joda.time.field.SkipUndoDateTimeField skipUndoDateTimeField6 = new org.joda.time.field.SkipUndoDateTimeField((org.joda.time.Chronology) julianChronology0, dateTimeField4, (int) '4');
        org.joda.time.DurationField durationField7 = julianChronology0.eras();
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertNotNull(durationField7);
    }

    @Test
    public void test1090() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1090");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight.Property property3 = dateMidnight2.monthOfYear();
        org.joda.time.DurationField durationField4 = property3.getDurationField();
        org.joda.time.DateMidnight dateMidnight6 = property3.addToCopy(86400020);
        org.junit.Assert.assertNotNull(property3);
        org.junit.Assert.assertNotNull(durationField4);
        org.junit.Assert.assertNotNull(dateMidnight6);
    }

    @Test
    public void test1091() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1091");
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
        long long73 = remainderDateTimeField59.set(32010L, 5);
        int int75 = remainderDateTimeField59.getLeapAmount((-262800000L));
        org.joda.time.DateTimeComparator dateTimeComparator76 = org.joda.time.DateTimeComparator.getDateOnlyInstance();
        org.joda.time.DateTimeComparator dateTimeComparator77 = org.joda.time.DateTimeComparator.getTimeOnlyInstance();
        java.util.Comparator<java.lang.Object> objComparator78 = dateTimeComparator76.thenComparing((java.util.Comparator<java.lang.Object>) dateTimeComparator77);
        org.joda.time.DateTimeFieldType dateTimeFieldType79 = dateTimeComparator77.getUpperLimit();
        org.joda.time.field.DividedDateTimeField dividedDateTimeField80 = new org.joda.time.field.DividedDateTimeField(remainderDateTimeField59, dateTimeFieldType79);
        // The following exception was thrown during execution in test generation
        try {
            long long83 = remainderDateTimeField59.setExtended((long) 'a', 100);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 100 for yearOfCentury must be in the range [0,9]");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
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
        org.junit.Assert.assertTrue("'" + long73 + "' != '" + 2419232010L + "'", long73 == 2419232010L);
        org.junit.Assert.assertTrue("'" + int75 + "' != '" + 0 + "'", int75 == 0);
        org.junit.Assert.assertNotNull(dateTimeComparator76);
        org.junit.Assert.assertNotNull(dateTimeComparator77);
        org.junit.Assert.assertNotNull(objComparator78);
        org.junit.Assert.assertNotNull(dateTimeFieldType79);
    }

    @Test
    public void test1092() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1092");
        // The following exception was thrown during execution in test generation
        try {
            int int3 = org.joda.time.field.FieldUtils.getWrappedValue(86400020, 7, 4);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: MIN > MAX");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test1093() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1093");
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
        java.util.Locale locale72 = null;
        java.lang.String str73 = remainderDateTimeField59.getAsText(29, locale72);
        int int74 = remainderDateTimeField59.getDivisor();
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
        org.junit.Assert.assertEquals("'" + str73 + "' != '" + "29" + "'", str73, "29");
        org.junit.Assert.assertTrue("'" + int74 + "' != '" + 10 + "'", int74 == 10);
    }

    @Test
    public void test1094() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1094");
        org.joda.time.Duration duration1 = org.joda.time.Duration.standardHours((long) 7);
        org.junit.Assert.assertNotNull(duration1);
    }

    @Test
    public void test1095() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1095");
        org.joda.time.chrono.GJChronology gJChronology2 = org.joda.time.chrono.GJChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField3 = gJChronology2.dayOfYear();
        org.joda.time.DateTimeField dateTimeField4 = gJChronology2.minuteOfHour();
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.MonthDay monthDay5 = new org.joda.time.MonthDay(0, 60, (org.joda.time.Chronology) gJChronology2);
            org.junit.Assert.fail("Expected exception of type org.joda.time.IllegalFieldValueException; message: Value 0 for monthOfYear must not be smaller than 1");
        } catch (org.joda.time.IllegalFieldValueException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(dateTimeField3);
        org.junit.Assert.assertNotNull(dateTimeField4);
    }

    @Test
    public void test1096() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1096");
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
        org.joda.time.MutableDateTime.Property property17 = mutableDateTime6.yearOfCentury();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(dateTime12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(obj16);
        org.junit.Assert.assertEquals(obj16.toString(), "1973-01-04T00:00:24.000+00:00:00.100");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj16), "1973-01-04T00:00:24.000+00:00:00.100");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj16), "1973-01-04T00:00:24.000+00:00:00.100");
        org.junit.Assert.assertNotNull(property17);
    }

    @Test
    public void test1097() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1097");
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
        java.lang.Object obj51 = mutableInterval50.clone();
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
        org.junit.Assert.assertNotNull(obj51);
        org.junit.Assert.assertEquals(obj51.toString(), "1970-01-01T00:00:00.110+00:00:00.100/1970-01-01T00:01:00.110+00:00:00.100");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj51), "1970-01-01T00:00:00.110+00:00:00.100/1970-01-01T00:01:00.110+00:00:00.100");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj51), "1970-01-01T00:00:00.110+00:00:00.100/1970-01-01T00:01:00.110+00:00:00.100");
    }

    @Test
    public void test1098() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1098");
        org.joda.time.PeriodType periodType0 = org.joda.time.PeriodType.yearDay();
        org.joda.time.PeriodType periodType1 = periodType0.withSecondsRemoved();
        int int2 = periodType0.size();
        org.joda.time.PeriodType periodType3 = periodType0.withDaysRemoved();
        org.junit.Assert.assertNotNull(periodType0);
        org.junit.Assert.assertNotNull(periodType1);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 2 + "'", int2 == 2);
        org.junit.Assert.assertNotNull(periodType3);
    }

    @Test
    public void test1099() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1099");
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
        long long19 = preciseDurationField9.add(2440588L, 1);
        org.junit.Assert.assertNotNull(seconds1);
        org.junit.Assert.assertNotNull(durationFieldType2);
        org.junit.Assert.assertNotNull(seconds6);
        org.junit.Assert.assertNotNull(durationFieldType7);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 0 + "'", int13 == 0);
        org.junit.Assert.assertTrue("'" + long16 + "' != '" + 2374041763L + "'", long16 == 2374041763L);
        org.junit.Assert.assertTrue("'" + long19 + "' != '" + 2440647L + "'", long19 == 2440647L);
    }

    @Test
    public void test1100() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1100");
        org.joda.time.Duration duration1 = org.joda.time.Duration.millis((long) 12);
        org.junit.Assert.assertNotNull(duration1);
    }

    @Test
    public void test1101() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1101");
        org.joda.time.chrono.GregorianChronology gregorianChronology0 = org.joda.time.chrono.GregorianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField1 = gregorianChronology0.minuteOfDay();
        org.joda.time.DateTimeField dateTimeField2 = gregorianChronology0.monthOfYear();
        org.junit.Assert.assertNotNull(gregorianChronology0);
        org.junit.Assert.assertNotNull(dateTimeField1);
        org.junit.Assert.assertNotNull(dateTimeField2);
    }

    @Test
    public void test1102() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1102");
        org.joda.time.format.DateTimeFormatterBuilder dateTimeFormatterBuilder0 = new org.joda.time.format.DateTimeFormatterBuilder();
        org.joda.time.format.DateTimeFormatterBuilder dateTimeFormatterBuilder2 = dateTimeFormatterBuilder0.appendDayOfYear(32);
        org.joda.time.format.DateTimeFormatterBuilder dateTimeFormatterBuilder5 = dateTimeFormatterBuilder0.appendFractionOfHour(999, 23);
        org.junit.Assert.assertNotNull(dateTimeFormatterBuilder2);
        org.junit.Assert.assertNotNull(dateTimeFormatterBuilder5);
    }

    @Test
    public void test1103() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1103");
        org.joda.time.DateTimeZone dateTimeZone0 = null;
        org.joda.time.LocalDateTime localDateTime1 = new org.joda.time.LocalDateTime(dateTimeZone0);
        org.joda.time.LocalDateTime localDateTime3 = localDateTime1.withDayOfYear((int) ' ');
        org.joda.time.LocalDateTime.Property property4 = localDateTime1.weekOfWeekyear();
        org.joda.time.ReadableInterval readableInterval5 = null;
        org.joda.time.Seconds seconds6 = org.joda.time.Seconds.secondsIn(readableInterval5);
        org.joda.time.DurationFieldType durationFieldType7 = seconds6.getFieldType();
        org.joda.time.LocalDateTime localDateTime9 = localDateTime1.withFieldAdded(durationFieldType7, 86400000);
        org.joda.time.DateTimeField[] dateTimeFieldArray10 = localDateTime9.getFields();
        org.joda.time.LocalDateTime.Property property11 = localDateTime9.era();
        org.junit.Assert.assertNotNull(localDateTime3);
        org.junit.Assert.assertNotNull(property4);
        org.junit.Assert.assertNotNull(seconds6);
        org.junit.Assert.assertNotNull(durationFieldType7);
        org.junit.Assert.assertNotNull(localDateTime9);
        org.junit.Assert.assertNotNull(dateTimeFieldArray10);
        org.junit.Assert.assertNotNull(property11);
    }

    @Test
    public void test1104() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1104");
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
        org.joda.time.PeriodType periodType22 = org.joda.time.PeriodType.yearDay();
        org.joda.time.PeriodType periodType23 = periodType22.withSecondsRemoved();
        int int24 = periodType22.size();
        org.joda.time.Period period25 = mutableInterval17.toPeriod(periodType22);
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
        org.junit.Assert.assertEquals(obj21.toString(), "1970-01-01T00:00:00.152+00:00:00.100/1970-01-01T00:45:00.100+00:00:00.100");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj21), "1970-01-01T00:00:00.152+00:00:00.100/1970-01-01T00:45:00.100+00:00:00.100");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj21), "1970-01-01T00:00:00.152+00:00:00.100/1970-01-01T00:45:00.100+00:00:00.100");
        org.junit.Assert.assertNotNull(periodType22);
        org.junit.Assert.assertNotNull(periodType23);
        org.junit.Assert.assertTrue("'" + int24 + "' != '" + 2 + "'", int24 == 2);
        org.junit.Assert.assertNotNull(period25);
    }

    @Test
    public void test1105() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1105");
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
        org.joda.time.DateTime dateTime20 = dateTime6.withDayOfYear((int) (short) 1);
        org.junit.Assert.assertNotNull(gregorianChronology5);
        org.junit.Assert.assertNotNull(dateTime8);
        org.junit.Assert.assertNotNull(dateTime10);
        org.junit.Assert.assertNotNull(dateTime12);
        org.junit.Assert.assertNotNull(dateTime14);
        org.junit.Assert.assertNotNull(gJChronology15);
        org.junit.Assert.assertNotNull(dateTime17);
        org.junit.Assert.assertNotNull(property18);
        org.junit.Assert.assertNotNull(dateTime20);
    }

    @Test
    public void test1106() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1106");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.DateTime dateTime2 = dateTime0.withZoneRetainFields(dateTimeZone1);
        org.joda.time.DateTime dateTime4 = dateTime0.withYear((int) (byte) 0);
        org.joda.time.DateTime dateTime6 = dateTime0.withWeekyear((int) (byte) 0);
        org.joda.time.DateTime dateTime8 = dateTime6.plusSeconds((int) (byte) -1);
        org.joda.time.DateTime.Property property9 = dateTime8.yearOfEra();
        org.joda.time.DateTime dateTime11 = property9.addToCopy((long) (byte) 1);
        org.junit.Assert.assertNotNull(dateTime2);
        org.junit.Assert.assertNotNull(dateTime4);
        org.junit.Assert.assertNotNull(dateTime6);
        org.junit.Assert.assertNotNull(dateTime8);
        org.junit.Assert.assertNotNull(property9);
        org.junit.Assert.assertNotNull(dateTime11);
    }

    @Test
    public void test1107() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1107");
        org.joda.time.tz.FixedDateTimeZone fixedDateTimeZone6 = new org.joda.time.tz.FixedDateTimeZone("2026-08-06T11:10:36.726", "hi!", 100, 0);
        org.joda.time.chrono.CopticChronology copticChronology7 = org.joda.time.chrono.CopticChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone6);
        org.joda.time.chrono.IslamicChronology.LeapYearPatternType leapYearPatternType8 = org.joda.time.chrono.IslamicChronology.LEAP_YEAR_INDIAN;
        org.joda.time.Chronology chronology10 = null;
        org.joda.time.DateMidnight dateMidnight11 = new org.joda.time.DateMidnight((long) (short) -1, chronology10);
        org.joda.time.DateMidnight dateMidnight13 = dateMidnight11.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property14 = dateMidnight11.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime15 = dateMidnight11.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property16 = mutableDateTime15.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime18 = property16.addWrapField((int) (short) -1);
        boolean boolean19 = leapYearPatternType8.equals((java.lang.Object) (short) -1);
        org.joda.time.chrono.IslamicChronology islamicChronology20 = org.joda.time.chrono.IslamicChronology.getInstance((org.joda.time.DateTimeZone) fixedDateTimeZone6, leapYearPatternType8);
        org.joda.time.MutableDateTime mutableDateTime21 = new org.joda.time.MutableDateTime((long) (byte) 10, (org.joda.time.DateTimeZone) fixedDateTimeZone6);
        org.joda.time.LocalDate localDate22 = new org.joda.time.LocalDate((long) 86400, (org.joda.time.DateTimeZone) fixedDateTimeZone6);
        org.junit.Assert.assertNotNull(copticChronology7);
        org.junit.Assert.assertNotNull(leapYearPatternType8);
        org.junit.Assert.assertNotNull(dateMidnight13);
        org.junit.Assert.assertNotNull(property14);
        org.junit.Assert.assertNotNull(mutableDateTime15);
        org.junit.Assert.assertNotNull(property16);
        org.junit.Assert.assertNotNull(mutableDateTime18);
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
        org.junit.Assert.assertNotNull(islamicChronology20);
    }

    @Test
    public void test1108() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1108");
        org.joda.time.DateTime dateTime0 = new org.joda.time.DateTime();
        org.joda.time.DateTime.Property property1 = dateTime0.millisOfDay();
        org.joda.time.DateTime dateTime3 = property1.setCopy(40237996);
        org.joda.time.DateTime dateTime5 = property1.addToCopy(30412800000L);
        org.joda.time.DateTime dateTime7 = dateTime5.plus((-97L));
        org.junit.Assert.assertNotNull(property1);
        org.junit.Assert.assertNotNull(dateTime3);
        org.junit.Assert.assertNotNull(dateTime5);
        org.junit.Assert.assertNotNull(dateTime7);
    }

    @Test
    public void test1109() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1109");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.weekyear();
        java.lang.Appendable appendable1 = null;
        // The following exception was thrown during execution in test generation
        try {
            dateTimeFormatter0.printTo(appendable1, (long) 1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
    }

    @Test
    public void test1110() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1110");
        org.joda.time.format.PeriodFormatter periodFormatter0 = org.joda.time.format.PeriodFormat.getDefault();
        org.junit.Assert.assertNotNull(periodFormatter0);
    }

    @Test
    public void test1111() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1111");
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
        org.joda.time.TimeOfDay timeOfDay16 = new org.joda.time.TimeOfDay((long) 100);
        org.joda.time.TimeOfDay timeOfDay18 = timeOfDay16.minusMinutes((int) (short) 1);
        org.joda.time.TimeOfDay timeOfDay20 = timeOfDay16.plusMillis((int) (short) 100);
        int int21 = offsetDateTimeField11.getMaximumValue((org.joda.time.ReadablePartial) timeOfDay20);
        org.junit.Assert.assertNotNull(julianChronology0);
        org.junit.Assert.assertNotNull(durationField1);
        org.junit.Assert.assertNotNull(gJChronology2);
        org.junit.Assert.assertNotNull(lenientChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 2 + "'", int8 == 2);
        org.junit.Assert.assertNotNull(durationField9);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "2147483647" + "'", str14, "2147483647");
        org.junit.Assert.assertNotNull(timeOfDay18);
        org.junit.Assert.assertNotNull(timeOfDay20);
        org.junit.Assert.assertTrue("'" + int21 + "' != '" + 61 + "'", int21 == 61);
    }

    @Test
    public void test1112() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1112");
        org.joda.time.Days days1 = org.joda.time.Days.ONE;
        org.joda.time.DurationFieldType durationFieldType2 = days1.getFieldType();
        org.joda.time.PeriodType periodType3 = days1.getPeriodType();
        org.joda.time.Chronology chronology5 = null;
        org.joda.time.DateMidnight dateMidnight6 = new org.joda.time.DateMidnight((long) (short) -1, chronology5);
        org.joda.time.DateMidnight dateMidnight8 = dateMidnight6.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight dateMidnight11 = dateMidnight6.withDurationAdded((long) 10, 3);
        org.joda.time.DateMidnight dateMidnight13 = dateMidnight11.withYear(100);
        org.joda.time.Chronology chronology15 = null;
        org.joda.time.DateMidnight dateMidnight16 = new org.joda.time.DateMidnight((long) (short) -1, chronology15);
        org.joda.time.Interval interval17 = new org.joda.time.Interval((org.joda.time.ReadableInstant) dateMidnight13, (org.joda.time.ReadableInstant) dateMidnight16);
        org.joda.time.Period period19 = org.joda.time.Period.weeks((int) (short) 100);
        org.joda.time.Interval interval20 = interval17.withPeriodBeforeEnd((org.joda.time.ReadablePeriod) period19);
        org.joda.time.Chronology chronology21 = org.joda.time.DateTimeUtils.getIntervalChronology((org.joda.time.ReadableInterval) interval17);
        org.joda.time.Period period22 = new org.joda.time.Period((-59011462664000L), periodType3, chronology21);
        // The following exception was thrown during execution in test generation
        try {
            org.joda.time.Period period24 = period22.withHours(1970);
            org.junit.Assert.fail("Expected exception of type java.lang.UnsupportedOperationException; message: Field is not supported");
        } catch (java.lang.UnsupportedOperationException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(days1);
        org.junit.Assert.assertNotNull(durationFieldType2);
        org.junit.Assert.assertNotNull(periodType3);
        org.junit.Assert.assertNotNull(dateMidnight8);
        org.junit.Assert.assertNotNull(dateMidnight11);
        org.junit.Assert.assertNotNull(dateMidnight13);
        org.junit.Assert.assertNotNull(period19);
        org.junit.Assert.assertNotNull(interval20);
        org.junit.Assert.assertNotNull(chronology21);
    }

    @Test
    public void test1113() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1113");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.DateMidnight dateMidnight2 = new org.joda.time.DateMidnight((long) (short) -1, chronology1);
        org.joda.time.DateMidnight dateMidnight4 = dateMidnight2.minusMonths((int) (byte) 100);
        org.joda.time.DateMidnight.Property property5 = dateMidnight2.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime6 = dateMidnight2.toMutableDateTimeISO();
        org.joda.time.MutableDateTime.Property property7 = mutableDateTime6.centuryOfEra();
        org.joda.time.MutableDateTime mutableDateTime8 = property7.roundFloor();
        long long9 = mutableDateTime8.getMillis();
        int int10 = mutableDateTime8.getRoundingMode();
        org.junit.Assert.assertNotNull(dateMidnight4);
        org.junit.Assert.assertNotNull(property5);
        org.junit.Assert.assertNotNull(mutableDateTime6);
        org.junit.Assert.assertNotNull(property7);
        org.junit.Assert.assertNotNull(mutableDateTime8);
        org.junit.Assert.assertTrue("'" + long9 + "' != '" + (-2208988800100L) + "'", long9 == (-2208988800100L));
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
    }

    @Test
    public void test1114() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1114");
        org.joda.time.ReadableInterval readableInterval0 = null;
        org.joda.time.Seconds seconds1 = org.joda.time.Seconds.secondsIn(readableInterval0);
        org.joda.time.DurationFieldType durationFieldType2 = seconds1.getFieldType();
        org.joda.time.IllegalFieldValueException illegalFieldValueException6 = new org.joda.time.IllegalFieldValueException(durationFieldType2, (java.lang.Number) 2700000L, (java.lang.Number) 10L, (java.lang.Number) (short) 1);
        org.joda.time.field.UnsupportedDurationField unsupportedDurationField7 = org.joda.time.field.UnsupportedDurationField.getInstance(durationFieldType2);
        // The following exception was thrown during execution in test generation
        try {
            long long10 = unsupportedDurationField7.getDifferenceAsLong(1786007456115L, 1786007456115L);
            org.junit.Assert.fail("Expected exception of type java.lang.UnsupportedOperationException; message: seconds field is unsupported");
        } catch (java.lang.UnsupportedOperationException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(seconds1);
        org.junit.Assert.assertNotNull(durationFieldType2);
        org.junit.Assert.assertNotNull(unsupportedDurationField7);
    }

    @Test
    public void test1115() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1115");
        org.joda.time.chrono.GJChronology gJChronology0 = org.joda.time.chrono.GJChronology.getInstance();
        java.lang.Object obj1 = null;
        boolean boolean2 = gJChronology0.equals(obj1);
        org.joda.time.DurationField durationField3 = gJChronology0.days();
        org.joda.time.DateTimeZone dateTimeZone4 = gJChronology0.getZone();
        org.junit.Assert.assertNotNull(gJChronology0);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(durationField3);
        org.junit.Assert.assertNotNull(dateTimeZone4);
    }

    @Test
    public void test1116() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1116");
        org.joda.time.Minutes minutes1 = org.joda.time.Minutes.minutes((-53));
        org.junit.Assert.assertNotNull(minutes1);
    }

    @Test
    public void test1117() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1117");
        org.joda.time.Chronology chronology1 = null;
        org.joda.time.chrono.JulianChronology julianChronology3 = org.joda.time.chrono.JulianChronology.getInstance();
        org.joda.time.DateTimeField dateTimeField4 = julianChronology3.clockhourOfDay();
        org.joda.time.Chronology chronology5 = julianChronology3.withUTC();
        java.util.Locale locale6 = null;
        org.joda.time.format.DateTimeParserBucket dateTimeParserBucket8 = new org.joda.time.format.DateTimeParserBucket(2177280001000L, chronology5, locale6, (java.lang.Integer) 40243947);
        java.util.Locale locale9 = dateTimeParserBucket8.getLocale();
        org.joda.time.format.DateTimeParserBucket dateTimeParserBucket10 = new org.joda.time.format.DateTimeParserBucket((long) 53, chronology1, locale9);
        long long11 = dateTimeParserBucket10.computeMillis();
        org.junit.Assert.assertNotNull(julianChronology3);
        org.junit.Assert.assertNotNull(dateTimeField4);
        org.junit.Assert.assertNotNull(chronology5);
        org.junit.Assert.assertNotNull(locale9);
        org.junit.Assert.assertEquals(locale9.toString(), "en_US");
        org.junit.Assert.assertTrue("'" + long11 + "' != '" + (-47L) + "'", long11 == (-47L));
    }

    @Test
    public void test1118() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1118");
        org.joda.time.format.DateTimeFormatter dateTimeFormatter0 = org.joda.time.format.ISODateTimeFormat.dateTime();
        org.junit.Assert.assertNotNull(dateTimeFormatter0);
    }

    @Test
    public void test1119() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1119");
        org.joda.time.YearMonth yearMonth0 = new org.joda.time.YearMonth();
        org.joda.time.DateTimeZone dateTimeZone1 = null;
        org.joda.time.Interval interval2 = yearMonth0.toInterval(dateTimeZone1);
        org.joda.time.YearMonth yearMonth4 = yearMonth0.plusMonths((int) 'a');
        org.joda.time.Interval interval5 = yearMonth0.toInterval();
        boolean boolean7 = interval5.isAfter((long) 40260602);
        org.junit.Assert.assertNotNull(interval2);
        org.junit.Assert.assertNotNull(yearMonth4);
        org.junit.Assert.assertNotNull(interval5);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
    }

    @Test
    public void test1120() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1120");
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
        org.joda.time.DateMidnight dateMidnight25 = dateMidnight7.plus((long) (short) 1);
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
    public void test1121() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest2.test1121");
        org.joda.time.IllegalFieldValueException illegalFieldValueException4 = new org.joda.time.IllegalFieldValueException("2147483647", (java.lang.Number) 40256602, (java.lang.Number) (-47L), (java.lang.Number) 1440);
    }
}
