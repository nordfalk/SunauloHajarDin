package com.example.sunmadinepal.dateconverter;

/**
 * Created by pradip Acharya on 7/27/20.
 */

public class NepaliDate {

    private int mahina, gatey, saal;
    private String baar;
    private int engDay, engMonth, engYear;
    private String engMonths;
    private Boolean isOtherMonths;
    public static final String MONTHS[] = {"बैशाख", "जेष्ठ", "आषाढ", "श्रावण", "भाद्र", "आश्विन", "कार्तिक", "मंसिर",
            "पौष", "माघ", "फाल्गुन", "चैत्र"};

    public static final String ENGMONTHS[] = {"Apr/May", "May/Jun", "Jun/Jul", "Jul/Aug",
            "Aug/Sep", "Sep/Oct", "Oct/Nov", "Nov/Dec",
            "Dec/Jan", "Jan/Feb", "Feb/Mar", "Mar/Apr"};

    int[][] numberOfDaysPerYear = {
            {30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31},  // FROM 2000
            {31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 31, 32, 32, 31, 30, 29, 30, 30, 29, 30, 30},
            {31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},
            {30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31},
            {31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30},
            {31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},
            {31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31},
            {31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30}, // 2010
            {31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},//
            {31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30},
            {31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30}, //
            {31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},//
            {31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30},
            {31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30},
            {31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31},
            {31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30},
            {31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31},
            {31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},
            {30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31},
            {31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30},
            {31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},
            {30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31},
            {31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30},//
            {31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},//
            {30, 32, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31},
            {31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30},//
            {31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},//
            {31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30},
            {31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30},//
            {31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},//
            {31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30},
            {31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30},
            {31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},
            {31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30},
            {31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31},
            {31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30},
            {31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31},
            {31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30},
            {31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},
            {30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31},
            {31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30},
            {31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},
            {31, 31, 31, 32, 31, 31, 29, 30, 29, 30, 29, 31},
            {31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 31, 32, 32, 31, 30, 29, 30, 30, 29, 30, 30},
            {31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},
            {31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31},
            {31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30},
            {31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},
            {31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30},
            {31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30},
            {31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},
            {31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30},
            {31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31},
            {31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            {31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30},
            {31, 31, 32, 32, 31, 30, 30, 30, 29, 30, 30, 30},
            {30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30},
            {31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30},
            {31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30},
            {31, 32, 31, 32, 30, 31, 30, 30, 29, 30, 30, 30},
            {30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30},
            {31, 31, 32, 31, 31, 31, 30, 30, 29, 30, 30, 30},
            {30, 31, 32, 32, 30, 31, 30, 30, 29, 30, 30, 30},
            {30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30},
            {30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30},
            {31, 31, 32, 31, 31, 31, 30, 30, 29, 30, 30, 30},
            {30, 31, 32, 32, 31, 30, 30, 30, 29, 30, 30, 30},
            {30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30},
            {31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30},
            {31, 31, 32, 31, 31, 31, 30, 29, 30, 30, 30, 30},
            {30, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30},
            {31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30},
            {31, 31, 32, 31, 31, 31, 29, 30, 29, 30, 29, 31},
            {31, 31, 32, 31, 31, 31, 30, 29, 29, 30, 30, 31} //2099
    };

    public NepaliDate() {

    }

    public NepaliDate(int saal, int mahina, int gatey, String baar) {
        this.mahina = mahina;
        this.gatey = gatey;
        this.saal = saal;
        this.baar = baar;
    }

    public NepaliDate(int saal, int mahina, int gatey, String baar, int engDay, int engMonth, int engYear,Boolean isOtherMonths) {
        this.mahina = mahina;
        this.gatey = gatey;
        this.saal = saal;
        this.baar = baar;
        this.engDay = engDay;
        this.engMonth = engMonth;
        this.engYear = engYear;
        this.isOtherMonths = isOtherMonths;
    }

    public int getMahina() {
        return mahina;
    }

    public int getGatey() {
        return gatey;
    }

    public int getSaal() {
        return saal;
    }

    public String getBaar() {
        return baar;
    }

    public String getMahinaInWords() {
        return MONTHS[mahina - 1];
    }

    public int getEngDay() {
        return engDay;
    }

    public int getEngMonth() {
        return engMonth;
    }

    public int getEngYear() {
        return engYear;
    }

    public String getEngMonths() {
        return engMonths;
    }

    public Boolean getOtherMonths() {
        return isOtherMonths;
    }

    public int getDaysOf(int year, int month) {
        return numberOfDaysPerYear[year - Converter.START_NEPALI_YEAR][month - 1];
    }

    public String toString() {
        return saal + " / " + mahina + " / " + gatey + " " + baar;
    }

    public String getEnglishMonthDay(int nepaliMonths) {
        return ENGMONTHS[nepaliMonths - 1];
    }
}