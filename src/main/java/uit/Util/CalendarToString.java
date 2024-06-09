package uit.Util;

public class CalendarToString {
    public static String calendarToString(java.util.Calendar calendar) {
        return calendar.get(java.util.Calendar.DAY_OF_MONTH) + "/" + (calendar.get(java.util.Calendar.MONTH) + 1) + "/" + calendar.get(java.util.Calendar.YEAR) + " " + calendar.get(java.util.Calendar.HOUR_OF_DAY) + ":" + calendar.get(java.util.Calendar.MINUTE) + ":" + calendar.get(java.util.Calendar.SECOND);
    }
}
