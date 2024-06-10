package uit.Util;

import java.util.Date;

public class DateFormat {
    public static String vietnameseDateFormat(Date date) {
        return date.getDate() + "/" + (date.getMonth() + 1) + "/" + (date.getYear() + 1900) + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
    }

    public static String vietnameseDateFormatWithoutTime(Date date) {
        return date.getDate() + "/" + (date.getMonth() + 1) + "/" + (date.getYear() + 1900);
    }
}
