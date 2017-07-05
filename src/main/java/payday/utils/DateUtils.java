package payday.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author myeongju.jung
 */
public final class DateUtils {
    private DateUtils() {
        throw new UnsupportedOperationException();
    }

    public static boolean between(Date target, Date start, Date end) {
        return !(target.before(start) || target.after(end));
    }

    public static Calendar toCalendar(Date date) {
        Calendar result = Calendar.getInstance();
        result.setTime(date);
        return result;
    }
}
