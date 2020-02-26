package com.course.practicaljava.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDateUtil {

	private static final long minDay = LocalDate.of(2010, Month.JANUARY, 1).toEpochDay();
	private static final long maxDay = LocalDate.now().toEpochDay();

	public static LocalDate generateRandomLocalDate() {
		long randomDay = minDay + ThreadLocalRandom.current().nextLong(maxDay - minDay);
		return LocalDate.ofEpochDay(randomDay);
	}

	public static Date generateRandomDate() {
		return Date.from(generateRandomLocalDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
}
