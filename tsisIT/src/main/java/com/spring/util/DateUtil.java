package com.spring.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Date 값을 화면으로 출력하기 위해서 포맷팅하는 클래스이다. <BR>
 * 사용법은 다음과 같다.
 * <pre><code>
 *	public class test                                                    
 *	{                                                                    
 *	                                                                     
 * 		public void getDate(long p_IDate, String pm_sPattern)             
 *		{                                                                
 *			String sTime;                                                
 *			                                                             
 *			sTime = DateConverter.getDateFormat(p_IDate, pm_sPattern);            
 *			System.out.println(sTime);                                   
 *		}                                                                
 *		                                                                 
 *		                                                                 
 *		public static void main(String[] args)                           
 *		{                                                                
 *			long lDate;                                                  
 *			                                                             
 *			test otest = new test();                                     
 *			lDate = (new Date()).getTime();                              
 *			otest.getDate(lDate, "yyyy년 MM월 dd일");                    
 *			System.out.println(DateConverter.longToString(lDate));       
 *			System.out.println(DateConverter.stringToLong("2000-10-12"));
 *  	}                                                                
 * 	}                                                                    
 * </code>
 * Pattern :
 *                                                                  
 * Symbol   Meaning                 Presentation        Example
 * ------   -------                 ------------        -------
 * G        era designator          (Text)              AD
 * y        year                    (Number)            1996
 * M        month in year           (Text & Number)     July & 07
 * d        day in month            (Number)            10
 * h        hour in am/pm (1~12)    (Number)            12
 * H        hour in day (0~23)      (Number)            0
 * m        minute in hour          (Number)            30
 * s        second in minute        (Number)            55
 * S        millisecond             (Number)            978
 * E        day in week             (Text)              Tuesday
 * D        day in year             (Number)            189
 * F        day of week in month    (Number)            2 (2nd Wed in July)
 * w        week in year            (Number)            27
 * W        week in month           (Number)            2
 * a        am/pm marker            (Text)              PM
 * k        hour in day (1~24)      (Number)            24
 * K        hour in am/pm (0~11)    (Number)            0
 * z        time zone               (Text)              Pacific Standard Time
 * '        escape for text         (Delimiter)
 * ''       single quote            (Literal)           '
 *
 * Examples Using the US Locale: 
 *
 *  Format Pattern                         Result
 *  --------------                         -------
 *  "yyyy.MM.dd G 'at' hh:mm:ss z"    ->>  1996.07.10 AD at 15:08:56 PDT
 *  "EEE, MMM d, ''yy"                ->>  Wed, July 10, '96
 *  "h:mm a"                          ->>  12:08 PM
 *  "hh 'o''clock' a, zzzz"           ->>  12 o'clock PM, Pacific Daylight Time
 *  "K:mm a, z"                       ->>  0:00 PM, PST
 *  "yyyyy.MMMMM.dd GGG hh:mm aaa"    ->>  1996.July.10 AD 12:08 PM
 *
 * </pre>
 * 주의 사항 : <BR>
 * 1. Pattern에서 문자의 대소문자를 구분하기 때문에 정확한 문자를 
 *                입력해야 한다. 그렇지 않으면 java.lang.IllegalArgumentException
 *                이 일어난다.<BR>
 *             2. stringToLong()함수에서 입력 스트링 값은 항상 yyyy-MM-dd로 
 *                구성되어야 한다 그렇지 않으면 java.lang.IllegalArgumentException
 *                이 일어난다.
 *
 * @version	1.0, 
 * @see		java.text.SimpleDateFormat
 * @see		java.util.Calendar
 */ 
public class DateUtil {
	/**
	 * ISO 8601 date formats. Order is important, must be from more specific to
	 * less specific.
	 */
	private static final String[] DATE_PATTERNS = {
			"yyyy-MM-dd'T'HH:mm:ssZ",
			"yyyy-MM-dd'T'HH:mm:ss.S",
			"yyyy-MM-dd'T'HH:mm:ss",
			"yyyy-MM-dd'T'HH:mmZ",
			"yyyy-MM-dd'T'HH:mm",
			"yyyy-MM-dd HH:mm:ssZ",
			"yyyy-MM-dd HH:mm:ss.S",
			"yyyy-MM-dd HH:mm:ss",
			"yyyy-MM-dd HH:mmZ",
			"yyyy-MM-dd HH:mm",
			"yyyy-MM-dd",
			"yyyy-MM",
			"yyyy",};
	
	public static String getDateFormat() {
 		return getDateFormat(System.currentTimeMillis(), "yyyy-MM-dd");
	}

	/**
	 * 날짜의 모양을 원하는 패턴을 주면 그것으로 날짜 포멧으로 변환하여 
	 * 문자열 값으로 리턴하는 함수이다.
	 * @param pm_lDate		long 타입으로 날짜값이 들어온다.
	 * @param pm_sPattern	String 타입으로 원하는 날짜 모양의 패턴이 들어온다.
	 * @return 	포맷팅된 날짜 문자열
	 */
	public static String getDateFormat(long pm_lDate, String pm_sPattern) {
		SimpleDateFormat lm_oFormat = new SimpleDateFormat(pm_sPattern);
 		return lm_oFormat.format(new Date(pm_lDate));
	}
	
	/**
	 * 날짜의 값중에 long값을 스트링 값으로 변환하여 주는 함수이다.<BR>
	 * getDateFormat(pm_lData, "yyyy-MM-dd") 와 같은 결과를 반환한다.
	 * @param pm_lDate	long 타입으로 날짜값이 들어온다.
	 * @return 	기본적인 날짜 포맷팅의 문자열
	 * @see		java.util.Date#toString()
	 * 
	 */
	public static String longToString(long pm_lDate)	{
		Date lm_oDate = new Date(pm_lDate);
		return lm_oDate.toString();
	}
	
	/**
	 * 날짜의 값중에 String값을 long 값으로 변환하여 주는 함수이다.
	 * @param pm_sDate	String 타입으로 날짜값이 들어온다. (항상 yyyy-MM-dd로
	 *                  구성되어야 한다.
	 * @return 	문자열 값을 변환하여 날짜값을 long값으로 반환한다.
	 */
	public static long stringToLong(String pm_sDate)	{
		return java.sql.Date.valueOf(pm_sDate).getTime();
	}

	/**
	 * yyyymmddhh24miss 형태로 전달되는 문자열을 long type으로 변환한다.
	 * @param pm_sDate : String 타입으로 날짜값이 들어온다. 
	 *					to_char(createdate, 'yyyymmddhh24miss')로 변환되어서 
	 *					얻어진 값을 사용한다.(반드시 14자여야 한다.)
	 * @return 	String값을 변환하여 날짜값을 long값으로 반환한다.
	 */
	public static long charToLong(String pm_sDate) {
		String lm_sYear 	= pm_sDate.substring(0, 4);		//년
		String lm_sMonth 	= pm_sDate.substring(4, 6);		//월
		String lm_sDay 		= pm_sDate.substring(6, 8);		//월
		String lm_sHour 	= pm_sDate.substring(8, 10);		//시
		String lm_sMinute 	= pm_sDate.substring(10,12);		//분
		String lm_sSec 		= pm_sDate.substring(12,14);		//초
		Calendar lm_oCalendar = Calendar.getInstance();
		
		lm_oCalendar.set(Calendar.YEAR, 		Integer.parseInt(lm_sYear));
		lm_oCalendar.set(Calendar.MONTH, 		Integer.parseInt(lm_sMonth) - 1);
		lm_oCalendar.set(Calendar.DAY_OF_MONTH,	Integer.parseInt(lm_sDay));
		lm_oCalendar.set(Calendar.HOUR_OF_DAY,	Integer.parseInt(lm_sHour));
		lm_oCalendar.set(Calendar.MINUTE, 		Integer.parseInt(lm_sMinute));
		lm_oCalendar.set(Calendar.SECOND, 		Integer.parseInt(lm_sSec));

		long lm_lDate = lm_oCalendar.getTime().getTime();
		return lm_lDate;
	}
	
	/**
	 * ISO 8601 형태의 날짜 문자열을 변환하여 long type으로 변환한다.
	 * @param pm_sDate	ISO 8601 형태의 날짜 문자열
	 * @return	날짜값 long. ISO 8601 형태의 날짜형태가 아닌 경우는 -1을 반환한다.
	 */
	public static long convertISO8601(String pm_sDate) {
		SimpleDateFormat lm_oDateFormat = new SimpleDateFormat();
		for (int i = 0; i < DATE_PATTERNS.length; i++) {
			try {
				lm_oDateFormat.applyPattern(DATE_PATTERNS[i]);
				return lm_oDateFormat.parse(pm_sDate).getTime();
			} catch (Exception e) {
				// ignore
			}
		}
		return -1L;
	}
	
	/**
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addDay(Date date, int amount) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, amount);
		return (Date)c.getTime();
	}
}	