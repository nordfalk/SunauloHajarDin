package com.example.sunmadinepal.dateconverter;



import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.DurationFieldType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Converter {

    //Change this when you get more previous database.
    public static final int START_ENGLISH_YEAR = 1943;
    public static final int START_ENGLISH_MONTH = 4;
    public static final int START_ENGLISH_DAY = 14;
    //Change this to equivalent nepali start date
    public static final int START_NEPALI_YEAR = 2000;
    public static final int START_NEPALI_MONTH = 1;
    public static final int START_NEPALI_DAY = 1;

    public Converter() {

    }


    public NepaliDate getNepaliDate(int eYear, int eMonth, int eDay) {
        int nDay = START_NEPALI_DAY, nMonth = START_NEPALI_MONTH, nYear = START_NEPALI_YEAR;
        DateTime start = new DateTime(START_ENGLISH_YEAR, START_ENGLISH_MONTH, START_ENGLISH_DAY, 0, 0);
        DateTime end = new DateTime(eYear, eMonth, eDay, 0, 0);
        int deltaDays = Days.daysBetween(start, end).getDays();
        NepaliDate nepaliDate = new NepaliDate();
        for (int i = 0; i < deltaDays; i++) {
            if (nDay < nepaliDate.getDaysOf(nYear, nMonth)) {
                nDay++;
            } else if (nMonth < 12) {
                nDay = 1;
                nMonth++;
            } else if (nMonth == 12) {
                nYear++;
                nMonth = 1;
                nDay = 1;
            }
        }
        String week_day = "" + end.dayOfWeek().getAsText();
        return new NepaliDate(nYear, nMonth, nDay, week_day);
    }


    public EnglishDate getEnglishDate(int nYear, int nMonth, int nDay) {
        int l_day = START_NEPALI_DAY, l_month = START_NEPALI_MONTH, l_year = START_NEPALI_YEAR;
        int deltaDays = 0;
        boolean isReached = false;
        NepaliDate nepaliDate = new NepaliDate();
        while (!isReached) {
            if (nYear == l_year && nMonth == l_month && nDay == l_day) {
                isReached = true;
                deltaDays--;
            }
            deltaDays++;
            if (l_day < nepaliDate.getDaysOf(l_year, l_month)) {
                l_day++;
            } else if (l_month < 12) {
                l_day = 1;
                l_month++;
            } else if (l_month == 12) {
                l_year++;
                l_month = 1;
                l_day = 1;
            }
        }
        DateTime dateTime = new DateTime(START_ENGLISH_YEAR, START_ENGLISH_MONTH, START_ENGLISH_DAY, 0, 0);
        return new EnglishDate(dateTime.withFieldAdded(DurationFieldType.days(), deltaDays));
    }

    public List<NepaliDate> getFullNepaliMonthOf(int eYear, int eMonth, int eDay) {

        NepaliDate temp = getNepaliDate(eYear, eMonth, eDay);
        int nDay = temp.getGatey();
        int nYear = temp.getSaal();
        int nMonth = temp.getMahina();
        String nWeekDay = temp.getBaar();
        List<NepaliDate> nepaliDates = new ArrayList<NepaliDate>();

        int weekIndex = EnglishDate.getWeekIndex(nWeekDay);
        for (int i = nDay; i > 0; i--) {
            nepaliDates.add(new NepaliDate(nYear, nMonth, i,
                    EnglishDate.WEEK_DAYS[weekIndex - 1]));
            if (weekIndex > 1) {
                weekIndex--;
            } else {
                weekIndex = 7;
            }
        }
        weekIndex = EnglishDate.getWeekIndex(nWeekDay);
        if (weekIndex < 7) {
            weekIndex++;
        } else {
            weekIndex = 1;
        }
        for (int i = nDay + 1; i <= temp.getDaysOf(nYear, nMonth); i++) {
            nepaliDates.add(new NepaliDate(nYear, nMonth, i,
                    EnglishDate.WEEK_DAYS[weekIndex - 1]));
            if (weekIndex < 7) {
                weekIndex++;
            } else {
                weekIndex = 1;
            }
        }
        Collections.sort(nepaliDates, new Comparator<NepaliDate>() {
            @Override
            public int compare(NepaliDate p1, NepaliDate p2) {
                return p1.getGatey() - p2.getGatey(); // Ascending
            }

        });
        return nepaliDates;
    }


    public List<NepaliDate> getFullNepaliMonthWithEnglishDate(int eYear, int eMonth, int eDay) {

        NepaliDate temp = getNepaliDate(eYear, eMonth, eDay);
        int nDay = temp.getGatey();
        int nYear = temp.getSaal();
        int nMonth = temp.getMahina();
        String nWeekDay = temp.getBaar();
        List<NepaliDate> nepaliDates = new ArrayList<NepaliDate>();
        NepaliDate nepaliDate = new NepaliDate();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.DATE, eDay);
        calendar1.set(Calendar.MONTH, eMonth - 1);
        calendar1.set(Calendar.YEAR, eYear);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.DATE, eDay + 1);
        calendar2.set(Calendar.MONTH, eMonth - 1);
        calendar2.set(Calendar.YEAR, eYear);


        int weekIndex = EnglishDate.getWeekIndex(nWeekDay);

        for (int i = nDay; i > 0; i--) {

            nepaliDates.add(0, new NepaliDate(nYear, nMonth, i,
                    EnglishDate.WEEK_DAYS[weekIndex - 1],
                    calendar1.get(Calendar.DATE), calendar1.get(Calendar.MONTH) + 1, calendar1.get(Calendar.YEAR),false));
            calendar1.add(Calendar.DATE, -1);

            if (i > 1) {
                if (weekIndex > 1) {
                    weekIndex--;
                } else {
                    weekIndex = 7;
                }
            }


            if (i == 1 && !EnglishDate.WEEK_DAYS[weekIndex - 1].equals("Sunday")) {
                int pYear, pMonth, tPMDays;
                if (nMonth == 1) {
                    pYear = nYear - 1;
                    pMonth = 12;
                    tPMDays = nepaliDate.getDaysOf(pYear, pMonth);
                } else {
                    pYear = nYear;
                    pMonth = nMonth - 1;
                    tPMDays = nepaliDate.getDaysOf(pYear, pMonth);
                }

                for (int j = weekIndex; j > 1; j--) {
                    nepaliDates.add(0, new NepaliDate(pYear, pMonth, tPMDays,
                            EnglishDate.WEEK_DAYS[weekIndex - 1],
                            calendar1.get(Calendar.DATE), calendar1.get(Calendar.MONTH) + 1, calendar1.get(Calendar.YEAR),true));
                    calendar1.add(Calendar.DATE, -1);
                    tPMDays--;

                    if (weekIndex > 1) {
                        weekIndex--;
                    } else {
                        weekIndex = 7;
                    }
                }
            }
        }

        weekIndex = EnglishDate.getWeekIndex(nWeekDay);
        if (weekIndex < 7) {
            weekIndex++;
        } else {
            weekIndex = 1;
        }
        int totalDateThisMonths = temp.getDaysOf(nYear, nMonth);
        for (int i = nDay + 1; i <= totalDateThisMonths; i++) {

            nepaliDates.add(new NepaliDate(nYear, nMonth, i,
                    EnglishDate.WEEK_DAYS[weekIndex - 1],
                    calendar2.get(Calendar.DATE), calendar2.get(Calendar.MONTH) + 1, calendar2.get(Calendar.YEAR) ,false
            ));
            calendar2.add(Calendar.DATE, 1);

            if (i < totalDateThisMonths) {
                if (weekIndex < 7) {
                    weekIndex++;
                } else {
                    weekIndex = 1;
                }
            }

            if (i == totalDateThisMonths && !EnglishDate.WEEK_DAYS[weekIndex - 1].equals("Saturday")) {
                int neYear, neMonth, tNMDays = 1;
                if (nMonth == 12) {
                    neYear = nYear + 1;
                    neMonth = 1;
                } else {
                    neYear = nYear;
                    neMonth = nMonth + 1;
                }

                for (int j = weekIndex - 1; j < 6; j++) {
                    nepaliDates.add(new NepaliDate(neYear, neMonth, tNMDays,
                            EnglishDate.WEEK_DAYS[weekIndex - 1],
                            calendar1.get(Calendar.DATE), calendar1.get(Calendar.MONTH) + 1, calendar1.get(Calendar.YEAR),true));
                    calendar1.add(Calendar.DATE, -1);
                    tNMDays++;
                    if (weekIndex < 7) {
                        weekIndex++;
                    } else {
                        weekIndex = 1;
                    }
                }
            }
        }
        return nepaliDates;
    }
}
