package uit.Util;

import java.text.DecimalFormat;

public class NumberFormat {
    public static String doubleFormat(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return decimalFormat.format(number);
    }

    public static String inputDoubleFormat(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(number);
    }

    public static String intFormat(int number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        return decimalFormat.format(number);
    }

    public static String inputIntFormat(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("0");
        return decimalFormat.format(number);
    }
}
