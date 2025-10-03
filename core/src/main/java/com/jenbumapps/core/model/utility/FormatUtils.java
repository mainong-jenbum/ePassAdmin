package com.jenbumapps.core.model.utility;

import java.text.DecimalFormat;

public class FormatUtils {
    /** 00.0 % */
    public final static DecimalFormat PERCENT = new DecimalFormat("00.00 %");
    //public final static DecimalFormat BILL_NUMBER = new DecimalFormat("000000");
    /** INR ##,##,##,##0.00 */
    public final static DecimalFormat MONEY = new DecimalFormat("\u20B9"+" ##,##,##,##0.00"); //df.setMaximumFractionDigits(2);
    public final static DecimalFormat BALANCE_MONEY = new DecimalFormat("\u20B9"+" ##,##,##,##0.00");
    /** ##,##,##,##0.00 */
    public final static DecimalFormat VALUE = new DecimalFormat("##,##,##,##0.00");
    /** Round of the value */
    public final static DecimalFormat ROUND_OFF = new DecimalFormat("0.00");

    /**
     * Round off the specified number (upto two decimal places)
     * @param d Number
     * @return number with two decimal places
     */
    public static double roundOffNumber(Double d) {
        return (double) Math.round(d * 100) / 100;
    }
}
