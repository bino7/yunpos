package com.yunpos.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Strings;

/**
 * 
 * 功能描述：日期工具
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月12日
 * @author Devin_Yang 修改日期：2015年8月12日
 *
 */
public class DateUtil {
	/** yyyy-MM-dd */
	public static String YYYYMMDD = "yyyy-MM-dd";
	/** yyyyMMdd */
	public static String yyyyMMdd = "yyyyMMdd";
	/** yyyyMMddHHmmss */
	public static String yyyyMMddHHmmss = "yyyyMMddHHmmss";
	/** yyyy-MM-dd HH:mm:ss **/
	public static String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

	public static String DDMMMYYYY = "ddMMMyyyy";
	public static String yyyyMMddHHmm = "yyyy-MM-dd HH:mm";

	/**
	 * 返回当前时间字符串 YYYYMMDDHHMMSS
	 * 
	 * @return
	 */
	public static String getNow() {
		return format(new Date(), YYYYMMDDHHMMSS);
	}

	public static Date getToday() {
		Calendar now = Calendar.getInstance();
		return getYYYYMMDD(format(now.getTime(), YYYYMMDD));
	}

	/**
	 * <p>
	 * Description:将字符串转化为日期
	 * </p>
	 * 
	 * @author xumiao
	 * @param dateString
	 *            日期字符串
	 * @return yyyy-MM-dd
	 * @throws Exception
	 */
	public static Date getYYYYMMDD(String dateString) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(YYYYMMDD);
		Date dateTime = null;
		try {
			dateTime = dateFormat.parse(dateString);
		} catch (ParseException e) {
			dateTime = null;
		} // END TRY
		return dateTime;
	}

	public static Date getYYYYMMDDHHMMSS(String dateString) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(YYYYMMDDHHMMSS);
		Date dateTime = null;
		try {
			dateTime = dateFormat.parse(dateString);
		} catch (ParseException e) {
			dateTime = null;
		} // END TRY
		return dateTime;
	}

	/**
	 * Get Date from String "yyyy-MM-dd HH:mm"
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date getYYYYMMDDHHmm(String dateString) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(yyyyMMddHHmm);
		Date dateTime = null;
		try {
			dateTime = dateFormat.parse(dateString);
		} catch (ParseException e) {
			dateTime = null;
		} // END TRY
		return dateTime;
	}

	/**
	 * 得到當前日期為周幾
	 * 
	 * @param date
	 * @return
	 */
	public static int dayOfWeek(Date date) {
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(date);
		int x = aCalendar.get(Calendar.DAY_OF_WEEK);
		return x;
	}

	/**
	 * 得到當前日期為周幾
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	/**
	 * 得到几天前的时间
	 */

	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}

	/**
	 * 返回字符型日期时间
	 * 
	 * @param date
	 *            日期
	 * @return 返回字符型日期时间
	 */
	public static String getDateTime(java.util.Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 格式化输出日期
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            格式
	 * @return 返回字符型日期
	 */
	public static String format(java.util.Date date, String format) {
		String result = "";
		try {
			if (date != null) {
				java.text.DateFormat df = new java.text.SimpleDateFormat(format);
				result = df.format(date);
			}
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 将日期类型格式化为字符串
	 * 
	 * @param date
	 *            yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String format(java.util.Date date) {
		return format(date, YYYYMMDDHHMMSS);
	}

	public static String format(Long millis) {
		Calendar now = Calendar.getInstance();
		now.setTimeInMillis(millis);
		return format(now.getTime(), YYYYMMDDHHMMSS);
	}

	/**
	 * 得到几天后的时间
	 */

	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	/**
	 * 得到几天后的时间
	 */
	public static String getDateAfter(String d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(formatDate(d, DateUtil.YYYYMMDD));
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return format(now.getTime(), DateUtil.YYYYMMDD);
	}

	/**
	 * 得到几天后的时间
	 */
	public static String getDateAfter(String d, String format, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(formatDate(d, format));
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return format(now.getTime(), format);
	}

	/**
	 * @description: 根据日期，类型，数值得到加减后的日期
	 * @author: huangtzh
	 * @createDate: 2013-12-27
	 * @param date
	 *            日期
	 * @param type
	 *            类型，为year,month,day,hour.....
	 * @param amount
	 *            时间加减的数值
	 * @return:
	 */
	public static Date getDateAdd(Date date, String type, int amount) {
		if (date == null) {
			return date;
		} else {
			Calendar now = Calendar.getInstance();
			now.setTime(date);
			if ("year".equalsIgnoreCase(type)) {
				now.add(Calendar.YEAR, amount);
			} else if ("month".equalsIgnoreCase(type)) {
				now.add(Calendar.MONTH, amount);
			} else if ("day".equalsIgnoreCase(type)) {
				now.add(Calendar.DATE, amount);
			} else if ("hour".equalsIgnoreCase(type)) {
				now.add(Calendar.HOUR_OF_DAY, amount);
			} else if ("minute".equalsIgnoreCase(type)) {
				now.add(Calendar.MINUTE, amount);
			} else if ("second".equalsIgnoreCase(type)) {
				now.add(Calendar.SECOND, amount);
			} else if ("millsecond".equalsIgnoreCase(type)) {
				now.add(Calendar.MILLISECOND, amount);
			}
			return now.getTime();
		}
	}

	/**
	 * 两个时间之间的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static long getDays(String date1, String date2) throws ParseException {
		if (date1 == null || date1.equals(""))
			return 0;
		if (date2 == null || date2.equals(""))
			return 0;
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat(YYYYMMDD);
		java.util.Date date = null;
		java.util.Date mydate = null;
		date = myFormatter.parse(date1);
		mydate = myFormatter.parse(date2);
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	public static long getHours(Date date1, Date date2) {
		if (date1 == null)
			return 0;
		if (date2 == null)
			return 0;

		long hours = (date1.getTime() - date2.getTime()) / (60 * 60 * 1000);
		return hours;
	}

	public static long getMins(Date date1, Date date2) {
		if (date1 == null)
			return 0;
		if (date2 == null)
			return 0;

		long mins = (date1.getTime() - date2.getTime()) / (60 * 1000);
		return mins;
	}

	/**
	 * 两个时间之间的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDays(Date date1, Date date2) {
		if (date1 == null)
			return 0;
		if (date2 == null)
			return 0;

		long day = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date formatDate(String date) {
		Date d = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 按指定格式格式化日期
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date formatDate(String date, String format) {
		Date d = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 将yyyy-MM-dd时间格式转换成ddMMMyy
	 * 
	 * @param dateString
	 * @return
	 */
	public static String stringToDateddMMMyy(String dateString) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyy", Locale.US);
		try {

			Date dateTime = sf.parse(dateString);
			dateString = sdf.format(dateTime);
		} catch (ParseException e) {

			throw new RuntimeException("Not a date!");
		}
		return dateString;
	}

	/**
	 * 将ddMMMyy时间格式转换成yyyy-MM-dd
	 * 
	 * @param dateString
	 * @return
	 */
	public static String ddMMMyyToYYYYMMDD(String dateString) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyy", Locale.US);
		try {

			Date dateTime = sdf.parse(dateString);
			dateString = sf.format(dateTime);
		} catch (ParseException e) {

			throw new RuntimeException("Not a date!");
		}
		return dateString;
	}

	/**
	 * 将yyyy-MM-dd时间格式转换成MMMyy
	 * 
	 * @param dateString
	 * @return
	 */
	public static String stringToDateMMMyy(String dateString) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("MMMyy", Locale.US);
		try {

			Date dateTime = sf.parse(dateString);
			dateString = sdf.format(dateTime);
		} catch (ParseException e) {

			throw new RuntimeException("Not a date!");
		}
		return dateString;
	}

	/**
	 * 将时间格式转换成ddMMMyy
	 * 
	 * @param dateString
	 * @return
	 */
	public static String dateToDateddMMMyy(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyy", Locale.US);
		String dateString = sdf.format(date);
		return dateString;
	}

	/**
	 * ddMMMyy时间格式转为Date
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date ddMMMyyToDate(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyy", Locale.US);
		try {
			return sdf.parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 将时间格式转换成ddMMM
	 * 
	 * @param dateString
	 * @return
	 */
	public static String dateToDateddMMM(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("ddMMM", Locale.US);
		String dateString = sdf.format(date);
		return dateString;
	}

	/**
	 * ddMMMyyHHmm时间格式转为Date
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date ddMMMyyHHmmToDate(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyyHHmm", Locale.US);
		try {
			return sdf.parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * ddMMMyyyyHHmm时间格式转为Date
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date ddMMMyyyyHHmmToDate(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyyyyHHmm", Locale.US);
		try {
			return sdf.parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 计算某个日期的前n天
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Date getCountDate(Date date, int count) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		long timeCount = c.getTimeInMillis() - count * 1000 * 60 * 60 * 24;// .getTime().getTime();
		c.setTimeInMillis(timeCount);
		return c.getTime();
	}

	/**
	 * 计算某个日期与当天的相差天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int countDays(Date date1) {
		Calendar c = Calendar.getInstance();
		Calendar c1 = Calendar.getInstance();
		c.setTime(date1);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c1.setTime(new Date());
		c1.set(Calendar.HOUR, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		long count = c.getTimeInMillis() - c1.getTimeInMillis();
		int day = (int) Math.ceil(count / 86400000);
		return day;
	}

	public static final Pattern eeDDMMyy = Pattern.compile("^([a-zA-Z]{2})([0-9]{2}[a-zA-Z]{3})([0-9]{2})$");
	public static final Pattern eeDDMMM = Pattern.compile("^([a-zA-Z]{2})([0-9]{2}[a-zA-Z]{3})$");
	public static final Pattern ddMMM = Pattern.compile("^([0-9]{2})([a-zA-Z]{3})$");
	public static final Pattern ddMMMyy = Pattern.compile("^([0-9]{2})([a-zA-Z]{3})([0-9]{2})$");

	/**
	 * 解析黑屏PNR返回的日期格式 解析规则：默认使用当前年份，若得到的日期中星期与PNR中星期不对应 则使用下一年和上一年的年份，否则返回null
	 * 
	 * @param pnrDateStr
	 *            必须是 Th25OCT 或者Th25OCT13 或者 ddMMM ddMMMyy 格式
	 * @return 格式不满足条件则返回null
	 */
	public static Date parseDateFormPnr(String pnrDateStr) {
		if (pnrDateStr == null || pnrDateStr.isEmpty()) {
			return null;
		}

		Matcher matchereeDDMMM = eeDDMMM.matcher(pnrDateStr);
		Matcher matchereeDDMMMyy = eeDDMMyy.matcher(pnrDateStr);
		Matcher matcherddMMM = ddMMM.matcher(pnrDateStr);
		Matcher matcherddMMMyy = ddMMMyy.matcher(pnrDateStr);
		// 匹配EEDDMMM
		if (matchereeDDMMM.matches()) {
			String week = matchereeDDMMM.group(1);

			Calendar calendar = Calendar.getInstance();
			String ddmmmyyyyStr = matchereeDDMMM.group(2) + calendar.get(Calendar.YEAR);
			SimpleDateFormat sdf = new SimpleDateFormat(DDMMMYYYY, Locale.US);
			Date date;
			try {
				date = sdf.parse(ddmmmyyyyStr);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
			calendar.setTime(date);

			int calendarWeek = parseDayOfWeek(week);
			if (calendar.get(Calendar.DAY_OF_WEEK) == calendarWeek) {
				return date;
			} else {
				calendar.add(Calendar.YEAR, 1);
				if (calendar.get(Calendar.DAY_OF_WEEK) == calendarWeek) {
					return calendar.getTime();
				} else {
					calendar.add(Calendar.YEAR, -2);
					if (calendar.get(Calendar.DAY_OF_WEEK) == calendarWeek) {
						return calendar.getTime();
					} else {
						return null;
					}
				}
			}

		}
		// 匹配EEDDMMMyy
		if (matchereeDDMMMyy.matches()) {

			String ddmmmyyStr = matchereeDDMMMyy.group(2) + matchereeDDMMMyy.group(3);

			SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyy", Locale.US);
			Date date;
			try {
				date = sdf.parse(ddmmmyyStr);
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
		// 匹配DDMMM
		if (matcherddMMM.matches()) {

			String ddmmmyyStr = pnrDateStr;
			Calendar c = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMM", Locale.US);
			Date date;
			try {
				int year = c.get(Calendar.YEAR);
				date = sdf.parse(ddmmmyyStr);
				c.setTime(date);
				c.set(Calendar.YEAR, year);
				return c.getTime();
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
		// 匹配ddMMMyy
		if (matcherddMMMyy.matches()) {

			String ddmmmyyStr = pnrDateStr;
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyy", Locale.US);
			Date date;
			try {

				date = sdf.parse(ddmmmyyStr);
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	/**
	 * 转化为 yyyy-MM-dd格式
	 * 
	 * @param pnrDateStr
	 * @return
	 */
	public static String formatDateFormPnr(String pnrDateStr) {
		Date date = parseDateFormPnr(pnrDateStr);
		if (date != null) {
			return format(date, "yyyy-MM-dd");
		}
		return null;
	}

	/**
	 * 转化为 黑屏返回的HHmm格式航班日期
	 * 
	 * @param pnrTimeStr
	 *            格式：四位 0900
	 * @return
	 */
	public static String formatTimeFormPnr(String pnrTimeStr) {
		if (pnrTimeStr == null || pnrTimeStr.length() != 4) {
			return null;
		}
		return pnrTimeStr.substring(0, 2) + ":" + pnrTimeStr.substring(2, 4);
	}

	/**
	 * 格式化pnr 起飞达到时间
	 * 
	 * @param flightDate
	 *            pnr航班日期字符串
	 * @param time
	 *            pnr起飞或到达时间字符串
	 * @return yyyy-MM-dd HH:ss OR ""
	 */
	public static String formatDateTimeFormPnr(String flightDate, String time) {
		String date = formatDateFormPnr(flightDate);
		if (!Strings.isNullOrEmpty(date) && time.length() >= 4) {
			int index = time.indexOf("+1");
			if (index >= 0) {
				time = time.substring(0, index);
				date = getDateAfter(date, 1);
			}
			time = time.substring(0, 2) + ":" + time.substring(2, 4);
			return date + " " + time;
		} else {
			return "";
		}
	}

	/**
	 * 转化为 黑屏返回的HHmm格式航班日期
	 * 
	 * @param pnrTimeStr
	 *            格式：四位 0900
	 * @return
	 */
	public static String formatYYYYMMDDToEEDDMMM(String date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD);
		try {
			Date pdate = sdf.parse(date);
			SimpleDateFormat sdf2 = new SimpleDateFormat("EEEddMMM", Locale.US);
			String str = sdf2.format(pdate);
			return (str.substring(0, 2) + str.substring(3)).toUpperCase();
		} catch (ParseException e) {
			e.printStackTrace();
			return date;
		}
	}

	/**
	 * 解析未使用的客票中客票日期 传入格式为 ddMMMHHmm
	 * 
	 * @return 格式不满足条件则返回null
	 */
	public static final Pattern ddMMMHHmm = Pattern.compile("^([0-9]{2}[a-zA-Z]{3})([0-9]{4})$");

	public static Date parseDateFormTicketCoupon(String couponDate) {
		Matcher m = ddMMMHHmm.matcher(couponDate);
		if (m.matches()) {
			String ddMMM = m.group(1);
			String HHmm = m.group(2);
			Calendar cal = Calendar.getInstance();
			Date date = ddMMMyyyyHHmmToDate(ddMMM + cal.get(Calendar.YEAR) + HHmm);
			if (date.before(cal.getTime())) {
				date = ddMMMyyyyHHmmToDate(ddMMM + (cal.get(Calendar.YEAR) + 1) + HHmm);
			}
			return date;
		}
		return null;
	}

	/**
	 * 转化黑屏返回的星期格式
	 * 
	 * @param week
	 * @return
	 */
	private static int parseDayOfWeek(String week) {

		if ("SU".equalsIgnoreCase(week)) {
			return Calendar.SUNDAY;
		} else if ("MO".equalsIgnoreCase(week)) {
			return Calendar.MONDAY;
		} else if ("TU".equalsIgnoreCase(week)) {
			return Calendar.TUESDAY;
		} else if ("WE".equalsIgnoreCase(week)) {
			return Calendar.WEDNESDAY;
		} else if ("TH".equalsIgnoreCase(week)) {
			return Calendar.THURSDAY;
		} else if ("FR".equalsIgnoreCase(week)) {
			return Calendar.FRIDAY;
		} else if ("SA".equalsIgnoreCase(week)) {
			return Calendar.SATURDAY;
		}

		return 0;
	}

	/**
	 * 获取经停分钟数
	 * 
	 * @return
	 */
	public static int getStopMins(String arriveTime, String departTime) {
		if (!Strings.isNullOrEmpty(arriveTime) && arriveTime.length() >= 4 && !Strings.isNullOrEmpty(departTime)
				&& departTime.length() >= 4) {
			int arriveHour = Integer.parseInt(arriveTime.substring(0, 2));
			int arriveMin = Integer.parseInt(arriveTime.substring(2, 4));
			int departHour = Integer.parseInt(departTime.substring(0, 2));
			int departMin = Integer.parseInt(departTime.substring(2, 4));
			return ((departHour - arriveHour) * 60) + (departMin - arriveMin);
		}
		return 0;
	}

	/**
	 * @Title: getBeforDay @Description: 获取当前时间的前一天 @param @param
	 * date @param @return @return Date @throws
	 */

	public static String getBeforOneDay(Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		String dateStr = formatDate(date, format);
		try {
			date = dateFormat.parse(dateStr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, -1);
			date = calendar.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateStr = formatDate(date, format);
	}

	/**
	 * 把字符串格式化成日期
	 * 
	 * @param argDateStr
	 * @param argFormat
	 * @return
	 */
	public static Date formatStringToDate(String argDateStr, String argFormat) {
		if (argDateStr == null || argDateStr.trim().length() < 1) {
			return null;
		}

		SimpleDateFormat sdfFormat = null;
		Date result = null;

		try {
			String strFormat = argFormat;
			if (Strings.isNullOrEmpty(strFormat)) {
				strFormat = yyyyMMdd;
				if (argDateStr.length() > 16) {
					strFormat = YYYYMMDDHHMMSS;
				} else if (argDateStr.length() > 10) {
					strFormat = yyyyMMddHHmm;
				}
			}
			sdfFormat = new SimpleDateFormat(strFormat);
			result = sdfFormat.parse(argDateStr);
		} catch (Exception e) {
			result = null;
		} finally {
			sdfFormat = null;
		}

		return result;
	}

	/**
	 * 格式化日期格式
	 * 
	 * @param argDate
	 * @param argFormat
	 * @return 格式化后的日期字符串
	 */
	public static String formatDate(Date argDate, String argFormat) {
		if (argDate == null) {
			return "";
		}
		SimpleDateFormat sdfFrom = null;
		String strResult = null;

		try {
			sdfFrom = new SimpleDateFormat(argFormat);
			strResult = sdfFrom.format(argDate).toString();
		} catch (Exception e) {
			strResult = "";
		} finally {
			sdfFrom = null;
		}
		return strResult;
	}

	// 获取当天时间的前n天时间
	public static Date getBeforDay(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -day);
		date = calendar.getTime();
		return date;
	}

	public static void main(String[] args) {
		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat("ddMMM", Locale.US);
		 * System.out.println(sdf.format(new Date()).toUpperCase());
		 */
		/*
		 * System.out.println(parseDateFormPnr("TU23APR"));
		 * System.out.println(parseDateFormPnr("WE23APR14"));
		 * System.out.println(parseDateFormPnr("23APR"));
		 * System.out.println(parseDateFormPnr("23APR13"));
		 */
		// System.out.println(getBeforDay(new Date(),"yyyy-MM-dd"));
		// System.out.println( getBeforOneDay( new Date(), "yyyy-MM-dd" ) );
		SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ms");
		System.out.println(fomat.format(getToday()));

	}

	/**
	 * 计算并格式化消耗时间<br>
	 * generate by: vakin jiang at 2012-2-16
	 * 
	 * @param startPoint
	 * @return
	 */
	public static String formatTimeConsumingInfo(long startPoint) {
		StringBuffer buff = new StringBuffer();
		long totalMilTimes = System.currentTimeMillis() - startPoint;
		int mi = (int) Math.floor(totalMilTimes / 60000);
		int se = (int) Math.floor((totalMilTimes - 60000 * mi) / 1000);
		if (mi > 0)
			buff.append(mi).append("mins ");
		buff.append(se).append("s");
		return buff.toString();
	}
}
