package com.nik.tripfinder.util;

import java.util.Calendar;
import java.util.Date;

public class Timestamp {

    public static Long getMidnightTimestamp(Long ts) {
		Date date = new Date(ts);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		date = cal.getTime();
		return date.getTime();
	}

    public static Long todaysTimestamp() {
		Date today = new Date();
		return getMidnightTimestamp(today.getTime());
	}
    
}
