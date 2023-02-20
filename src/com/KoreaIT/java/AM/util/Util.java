package com.KoreaIT.java.AM.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	/** 현재 날짜, 시각 정보 String 리턴 */
	public static String getNowDateTimeStr() {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date now = new Date();

		return sdf1.format(now);
	}
}