package com.xkzhangsan.time.converter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Objects;

/**
 * 日期转换
 * 包含Date、LocalDate、LocalDateTime、LocalTime、Instant和ZonedDateTime的互相转换
 * 
 * 注意，ZonedDateTime相关的转换，尤其是其他时间转ZonedDateTime，要注意时间和对应时区一致。
* @ClassName: DateTimeConverterUtil 
* @Description: DateTime Converter
* @author xkzhangsan
* @date 2019年12月1日
*
 */
public class DateTimeConverterUtil {
	
	private DateTimeConverterUtil(){
	}

	/**
	 * LocalDateTime转Date
	 * @param localDateTime
	 * @return
	 */
	public static Date toDate(LocalDateTime localDateTime) {
		Objects.requireNonNull(localDateTime, "localDateTime");
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * LocalDate转Date
	 * @param localDate
	 * @return
	 */
	public static Date toDate(LocalDate localDate) {
		Objects.requireNonNull(localDate, "localDate");
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
	
	/**
	 * LocalTime转Date
	 * 以当天的日期+LocalTime组成新的LocalDateTime转换为Date
	 * @param localTime
	 * @return
	 */
	public static Date toDate(LocalTime localTime) {
		Objects.requireNonNull(localTime, "localTime");
		return Date.from(LocalDate.now().atTime(localTime).atZone(ZoneId.systemDefault()).toInstant());
	}	

	/**
	 * Instant转Date
	 * @param instant
	 * @return
	 */
	public static Date toDate(Instant instant) {
		return Date.from(instant);
	}
	
	/**
	 * epochMilli毫秒转Date
	 * @param epochMilli
	 * @return
	 */
	public static Date toDate(long epochMilli){
		Objects.requireNonNull(epochMilli, "epochMilli");
		return new Date(epochMilli);
	}
	
	/**
	 * ZonedDateTime转Date
	 * 注意时间对应的时区和默认时区差异
	 * @param zonedDateTime
	 * @return
	 */
	public static Date toDate(ZonedDateTime zonedDateTime) {
		Objects.requireNonNull(zonedDateTime, "zonedDateTime");
		return Date.from(zonedDateTime.toInstant());
	}
	
	/**
	 * YearMonth转Date
	 * 注意dayOfMonth范围：1到31之间，最大值根据月份确定特殊情况，如2月闰年29，非闰年28
	 * 如果要转换为当月最后一天，可以使用下面方法：toDateEndOfMonth(YearMonth)
	 * @param yearMonth
	 * @param dayOfMonth
	 * @return
	 */
	public static Date toDate(YearMonth yearMonth, int dayOfMonth) {
		Objects.requireNonNull(yearMonth, "yearMonth");
		return toDate(yearMonth.atDay(dayOfMonth));
	}
	
	/**
	 * YearMonth转Date，转换为当月第一天
	 * @param yearMonth
	 * @return
	 */
	public static Date toDateStartOfMonth(YearMonth yearMonth) {
		return toDate(yearMonth, 1);
	}
	
	/**
	 * YearMonth转Date，转换为当月最后一天
	 * @param yearMonth
	 * @return
	 */
	public static Date toDateEndOfMonth(YearMonth yearMonth) {
		Objects.requireNonNull(yearMonth, "yearMonth");
		return toDate(yearMonth.atEndOfMonth());
	}

	/**
	 * Date转LocalDateTime
	 * @param date
	 * @return
	 */
	public static LocalDateTime toLocalDateTime(Date date) {
		Objects.requireNonNull(date, "date");
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	/**
	 * LocalDate转LocalDateTime
	 * @param localDate
	 * @return
	 */
	public static LocalDateTime toLocalDateTime(LocalDate localDate) {
		Objects.requireNonNull(localDate, "localDate");
		return localDate.atStartOfDay();
	}
	
	/**
	 * LocalTime转LocalDateTime
	 * 以当天的日期+LocalTime组成新的LocalDateTime
	 * @param localTime
	 * @return
	 */
	public static LocalDateTime toLocalDateTime(LocalTime localTime) {
		Objects.requireNonNull(localTime, "localTime");
		return LocalDate.now().atTime(localTime);
	}

	/**
	 * Instant转LocalDateTime
	 * @param instant
	 * @return
	 */
	public static LocalDateTime toLocalDateTime(Instant instant) {
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	}
	
	/**
	 * epochMilli毫秒转LocalDateTime
	 * @param epochMilli
	 * @return
	 */
	public static LocalDateTime toLocalDateTime(long epochMilli) {
		Objects.requireNonNull(epochMilli, "epochMilli");
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault());
	}
	
	/**
	 * temporal转LocalDateTime
	 * @param temporal
	 * @return
	 */
	public static LocalDateTime toLocalDateTime(TemporalAccessor temporal) {
		return LocalDateTime.from(temporal);
	}
	
	/**
	 * ZonedDateTime转LocalDateTime
	 * 注意时间对应的时区和默认时区差异
	 * @param zonedDateTime
	 * @return
	 */
	public static LocalDateTime toLocalDateTime(ZonedDateTime zonedDateTime) {
		Objects.requireNonNull(zonedDateTime, "zonedDateTime");
		return zonedDateTime.toLocalDateTime();
	}

	/**
	 * Date转LocalDate
	 * @param date
	 * @return
	 */
	public static LocalDate toLocalDate(Date date) {
		return toLocalDateTime(date).toLocalDate();
	}

	/**
	 * LocalDateTime转LocalDate
	 * @param localDateTime
	 * @return
	 */
	public static LocalDate toLocalDate(LocalDateTime localDateTime) {
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.toLocalDate();
	}

	/**
	 * Instant转LocalDate
	 * @param instant
	 * @return
	 */
	public static LocalDate toLocalDate(Instant instant) {
		return toLocalDateTime(instant).toLocalDate();
	}
	
	/**
	 * temporal转LocalDate
	 * @param temporal
	 * @return
	 */
	public static LocalDate toLocalDate(TemporalAccessor temporal) {
		return LocalDate.from(temporal);
	}
	
	/**
	 * ZonedDateTime转LocalDate
	 * 注意时间对应的时区和默认时区差异
	 * @param zonedDateTime
	 * @return
	 */
	public static LocalDate toLocalDate(ZonedDateTime zonedDateTime) {
		Objects.requireNonNull(zonedDateTime, "zonedDateTime");
		return zonedDateTime.toLocalDate();
	}
	
	/**
	 * YearMonth转LocalDate
	 * 注意dayOfMonth范围：1到31之间，最大值根据月份确定特殊情况，如2月闰年29，非闰年28
	 * 如果要转换为当月最后一天，可以使用下面方法：toLocalDateEndOfMonth(YearMonth)
	 * @param yearMonth
	 * @param dayOfMonth
	 * @return
	 */
	public static LocalDate toLocalDate(YearMonth yearMonth, int dayOfMonth) {
		Objects.requireNonNull(yearMonth, "yearMonth");
		return yearMonth.atDay(dayOfMonth);
	}
	
	/**
	 * YearMonth转LocalDate，转换为当月第一天
	 * @param yearMonth
	 * @return
	 */
	public static LocalDate toLocalDateStartOfMonth(YearMonth yearMonth) {
		return toLocalDate(yearMonth, 1);
	}
	
	/**
	 * YearMonth转LocalDate，转换为当月最后一天
	 * @param yearMonth
	 * @return
	 */
	public static LocalDate toLocalDateEndOfMonth(YearMonth yearMonth) {
		Objects.requireNonNull(yearMonth, "yearMonth");
		return yearMonth.atEndOfMonth();
	}

	/**
	 * Date转LocalTime
	 * @param date
	 * @return
	 */
	public static LocalTime toLocalTime(Date date) {
		return toLocalDateTime(date).toLocalTime();
	}

	/**
	 * LocalDateTime转LocalTime
	 * @param localDateTime
	 * @return
	 */
	public static LocalTime toLocalTime(LocalDateTime localDateTime) {
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.toLocalTime();
	}

	/**
	 * Instant转LocalTime
	 * @param instant
	 * @return
	 */
	public static LocalTime toLocalTime(Instant instant) {
		return toLocalDateTime(instant).toLocalTime();
	}
	
	/**
	 * temporal转LocalTime
	 * @param temporal
	 * @return
	 */
	public static LocalTime toLocalTime(TemporalAccessor temporal) {
		return LocalTime.from(temporal);
	}
	
	/**
	 * ZonedDateTime转LocalTime
	 * 注意时间对应的时区和默认时区差异
	 * @param zonedDateTime
	 * @return
	 */
	public static LocalTime toLocalTime(ZonedDateTime zonedDateTime) {
		Objects.requireNonNull(zonedDateTime, "zonedDateTime");
		return zonedDateTime.toLocalTime();
	}

	/**
	 * Date转Instant
	 * @param date
	 * @return
	 */
	public static Instant toInstant(Date date) {
		Objects.requireNonNull(date, "date");
		return date.toInstant();
	}

	/**
	 * LocalDateTime转Instant
	 * @param localDateTime
	 * @return
	 */
	public static Instant toInstant(LocalDateTime localDateTime) {
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
	}

	/**
	 * LocalDate转Instant
	 * @param localDate
	 * @return
	 */
	public static Instant toInstant(LocalDate localDate) {
		return toLocalDateTime(localDate).atZone(ZoneId.systemDefault()).toInstant();
	}
	
	/**
	 * LocalTime转Instant
	 * 以当天的日期+LocalTime组成新的LocalDateTime转换为Instant
	 * @param localTime
	 * @return
	 */
	public static Instant toInstant(LocalTime localTime) {
		return toLocalDateTime(localTime).atZone(ZoneId.systemDefault()).toInstant();
	}
	
	/**
	 * epochMilli毫秒转Instant
	 * @param epochMilli
	 * @return
	 */
	public static Instant toInstant(long epochMilli) {
		Objects.requireNonNull(epochMilli, "epochMilli");
		return Instant.ofEpochMilli(epochMilli);
	}
	
	/**
	 * temporal转Instant
	 * @param temporal
	 * @return
	 */
	public static Instant toInstant(TemporalAccessor temporal) {
		return Instant.from(temporal);
	}
	
	/**
	 * ZonedDateTime转Instant
	 * 注意，zonedDateTime时区必须和当前系统时区一致，不然会出现问题
	 * @param zonedDateTime
	 * @return
	 */
	public static Instant toInstant(ZonedDateTime zonedDateTime) {
		Objects.requireNonNull(zonedDateTime, "zonedDateTime");
		return zonedDateTime.toInstant();
	}
	
	/**
	 * Date转毫秒值
	 * 从1970-01-01T00:00:00Z开始的毫秒值
	 * @param date
	 * @return
	 */
	public static long toEpochMilli(Date date){
		Objects.requireNonNull(date, "date");
		return date.getTime();
	}
	
	/**
	 * LocalDateTime转毫秒值
	 * 从1970-01-01T00:00:00Z开始的毫秒值
	 * @param localDateTime
	 * @return
	 */
	public static long toEpochMilli(LocalDateTime localDateTime){
		return toInstant(localDateTime).toEpochMilli();
	}
	
	/**
	 * LocalDate转毫秒值
	 * 从1970-01-01T00:00:00Z开始的毫秒值
	 * @param localDate
	 * @return
	 */
	public static long toEpochMilli(LocalDate localDate){
		return toInstant(localDate).toEpochMilli();
	}
	
	/**
	 * Instant转毫秒值
	 * 从1970-01-01T00:00:00Z开始的毫秒值
	 * @param instant
	 * @return
	 */
	public static long toEpochMilli(Instant instant){
		Objects.requireNonNull(instant, "instant");
		return instant.toEpochMilli();
	}
	
	/**
	 * ZonedDateTime转毫秒值，注意，zonedDateTime时区必须和当前系统时区一致，不然会出现问题
	 * 从1970-01-01T00:00:00Z开始的毫秒值
	 * @param zonedDateTime
	 * @return
	 */
	public static long toEpochMilli(ZonedDateTime zonedDateTime) {
		Objects.requireNonNull(zonedDateTime, "zonedDateTime");
		return zonedDateTime.toInstant().toEpochMilli();
	}
	
	/**
	 * Date转ZonedDateTime，时区为系统默认时区
	 * @param date
	 * @return
	 */
	public static ZonedDateTime toZonedDateTime(Date date) {
		Objects.requireNonNull(date, "date");
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime()
				.atZone(ZoneId.systemDefault());
	}
	
	/**
	 * LocalDateTime转ZonedDateTime，时区为系统默认时区
	 * @param localDateTime
	 * @return
	 */
	public static ZonedDateTime toZonedDateTime(LocalDateTime localDateTime) {
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.atZone(ZoneId.systemDefault());
	}
	
	/**
	 * LocalDateTime转ZonedDateTime，时区为zoneId对应时区
	 * 注意，需要保证localDateTime和zoneId是对应的，不然会出现错误
	 * 
	 * @param localDateTime
	 * @param zoneId
	 * @return
	 */
	public static ZonedDateTime toZonedDateTime(LocalDateTime localDateTime, String zoneId) {
		Objects.requireNonNull(localDateTime, "localDateTime");
		Objects.requireNonNull(zoneId, "zoneId");
		return localDateTime.atZone(ZoneId.of(zoneId));
	}	

	/**
	 * LocalDate转ZonedDateTime，时区为系统默认时区
	 * @param localDate
	 * @return such as 2020-02-19T00:00+08:00[Asia/Shanghai]
	 */
	public static ZonedDateTime toZonedDateTime(LocalDate localDate) {
		Objects.requireNonNull(localDate, "localDate");
		return localDate.atStartOfDay().atZone(ZoneId.systemDefault());
	}
	
	/**
	 * LocalTime转ZonedDateTime
	 * 以当天的日期+LocalTime组成新的ZonedDateTime，时区为系统默认时区
	 * @param localTime
	 * @return
	 */
	public static ZonedDateTime toZonedDateTime(LocalTime localTime) {
		Objects.requireNonNull(localTime, "localTime");
		return LocalDate.now().atTime(localTime).atZone(ZoneId.systemDefault());
	}

	/**
	 * Instant转ZonedDateTime，时区为系统默认时区
	 * @param instant
	 * @return
	 */
	public static ZonedDateTime toZonedDateTime(Instant instant) {
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).atZone(ZoneId.systemDefault());
	}
	
	/**
	 * epochMilli毫秒转ZonedDateTime，时区为系统默认时区
	 * @param epochMilli
	 * @return
	 */
	public static ZonedDateTime toZonedDateTime(long epochMilli) {
		Objects.requireNonNull(epochMilli, "epochMilli");
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault())
				.atZone(ZoneId.systemDefault());
	}
	
	/**
	 * temporal转ZonedDateTime，时区为系统默认时区
	 * @param temporal
	 * @return
	 */
	public static ZonedDateTime toZonedDateTime(TemporalAccessor temporal) {
		return LocalDateTime.from(temporal).atZone(ZoneId.systemDefault());
	}
	
	/**
	 * Date转YearMonth
	 * @param date
	 * @return
	 */
	public static YearMonth toYearMonth(Date date){
		LocalDate localDate = toLocalDate(date);
		return YearMonth.of(localDate.getYear(), localDate.getMonthValue());
	}
	
	/**
	 * LocalDateTime转YearMonth
	 * @param localDateTime
	 * @return
	 */
	public static YearMonth toYearMonth(LocalDateTime localDateTime){
		LocalDate localDate = toLocalDate(localDateTime);
		return YearMonth.of(localDate.getYear(), localDate.getMonthValue());
	}
	
	/**
	 * LocalDate转YearMonth
	 * @param localDate
	 * @return
	 */
	public static YearMonth toYearMonth(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return YearMonth.of(localDate.getYear(), localDate.getMonthValue());
	}
	
	/**
	 * Instant转YearMonth
	 * @param instant
	 * @return
	 */
	public static YearMonth toYearMonth(Instant instant){
		LocalDate localDate = toLocalDate(instant);
		return YearMonth.of(localDate.getYear(), localDate.getMonthValue());
	}
	
	/**
	 * ZonedDateTime转YearMonth
	 * @param zonedDateTime
	 * @return
	 */
	public static YearMonth toYearMonth(ZonedDateTime zonedDateTime){
		LocalDate localDate = toLocalDate(zonedDateTime);
		return YearMonth.of(localDate.getYear(), localDate.getMonthValue());
	}

}
