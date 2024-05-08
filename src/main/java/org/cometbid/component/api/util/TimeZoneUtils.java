/*
 * The MIT License
 *
 * Copyright 2024 samueladebowale.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.cometbid.component.api.util;

import java.time.Clock;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.ThreadContext;

/**
 *
 * @author samueladebowale
 */
@Log4j2
public class TimeZoneUtils {

    private static final TimeZoneUtils ONE_INSTANCE = new TimeZoneUtils();

    // ==========================================================================================//
    private static final String GENERIC_DATE_FORMAT = "[yyyyMMdd][yyyy-MM-dd][yyyy-DDD]['T'[HHmmss][HHmm][HH:mm:ss][HH:mm][.SSSSSSSSS][.SSSSSS][.SSS][.SS][.S]][OOOO][O][z][XXXXX][XXXX]['['VV']']";
    public static final DateTimeFormatter GENERIC_DATE_FORMATTER = DateTimeFormatter.ofPattern(GENERIC_DATE_FORMAT);

    public static final DateTimeFormatter UTC_DATE_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd hh:mm:ssa ")
            .appendLiteral("UTC")
            // .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true)
            // .appendOffsetId()
            .toFormatter(Locale.ROOT);

    // ========================================================================================== //
    public static final String UTC_TIMEZONE = "UTC";
    public static final ZoneId UTC_ZONEID = ZoneId.of(UTC_TIMEZONE);
    private static final ZoneOffset UTC_ZONE_OFFSET = UTC_ZONEID.getRules().getOffset(LocalDateTime.now());
    // ========================================================================================== //
    public static String DEFAULT_TIMEZONE = "UTC";
    public static final ZoneId DEFAULT_ZONEID = ZoneId.of(DEFAULT_TIMEZONE);
    private static final ZoneOffset DEFAULT_ZONE_OFFSET = DEFAULT_ZONEID.getRules().getOffset(LocalDateTime.now());
    // ========================================================================================== //

    public static final String THREAD_CONTEXT_TIMEZONE_KEY = "timezone";
    private final static Set<String> ALL_ZONE_IDS;

    static {
        ALL_ZONE_IDS = ZoneId.getAvailableZoneIds();
    }

    private TimeZoneUtils() {
    }

    /**
     *
     * @return
     */
    public static TimeZoneUtils getInstance() {
        return ONE_INSTANCE;
    }

    /**
     *
     * @param fromTimeZone
     * @param toNewTimeZone
     * @return
     */
    public static ZonedDateTime convert(ZonedDateTime fromTimeZone, ZoneId toNewTimeZone) {
        return ZonedDateTime.ofInstant(fromTimeZone.toInstant(), toNewTimeZone);
    }

    /**
     *
     * @param fromTimeZone
     * @param toNewTimeZone
     * @return
     */
    public static OffsetDateTime convertOffset(OffsetDateTime fromTimeZone, ZoneId toNewTimeZone) {
        return OffsetDateTime.ofInstant(fromTimeZone.toInstant(), toNewTimeZone);
    }

    /**
     *
     * @return
     */
    public static ZonedDateTime getZonedDateTimeInUTC() {
        return Instant.now().atZone(UTC_ZONEID);
    }

    /**
     *
     * @return
     */
    public static OffsetDateTime getOffsetDateTimeInUTC() {
        return OffsetDateTime.now(UTC_ZONE_OFFSET);
    }

    /**
     *
     * @return
     */
    public static OffsetTime getOffsetTimeInUTC() {
        return OffsetTime.now(UTC_ZONE_OFFSET);
    }

    /**
     *
     * @return
     */
    public static Set<String> getAvailableTimeZoneIds() {
        return new TreeSet<>(ZoneId.getAvailableZoneIds());
    }

    /**
     *
     * @return
     */
    public static Map<ZoneOffset, List<ZoneId>> getTimeZonesGroupByOffset() {
        return ALL_ZONE_IDS.stream()
                .map(ZoneId::of)
                .collect(Collectors.groupingBy(x -> x.getRules().getOffset(Instant.now())));
    }

    /**
     *
     * @param timeZoneId
     * @return
     */
    public static ZoneId getZoneId(String timeZoneId) {
        boolean foundZone = ALL_ZONE_IDS.stream().anyMatch(e -> e.equalsIgnoreCase(timeZoneId));

        if (foundZone) {
            return ZoneId.of(timeZoneId);
        }

        return ZoneId.systemDefault();
    }

    /**
     *
     * @param timeZoneId
     * @return
     */
    public static ZoneOffset getZoneOffset(String timeZoneId) {
        ZoneId zoneId = getZoneId(timeZoneId);
        return zoneId.getRules().getOffset(Instant.now());
    }

    /**
     *
     * @return
     */
    public static ZoneOffset getContextZoneOffset() {
        return ZonedDateTime.ofInstant(Instant.now(), getContextZoneId()).getOffset();
    }

    /**
     *
     */
    public static void getTimeZonesGroupByOffsetWithFormatting() {

        getTimeZonesGroupByOffset().entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    System.out.println(entry.getKey());
                    for (var zone : entry.getValue()) {
                        System.out.print("    ");
                        System.out.println(zone.getId());
                    }
                });
    }

    /**
     *
     * @return @throws DateTimeException
     */
    public static ZoneId getContextZoneId() throws DateTimeException {
        String timezone = ThreadContext.get(THREAD_CONTEXT_TIMEZONE_KEY);

        log.info("User context timezone {}", timezone);
        return ZoneId.of(timezone);
    }

    /**
     *
     * @param timezone
     * @return
     * @throws DateTimeException
     */
    public static ZoneId setContextZoneId(String timezone) throws DateTimeException {
        ThreadContext.put(THREAD_CONTEXT_TIMEZONE_KEY, timezone);

        log.info("User context timezone {}", timezone);
        return getContextZoneId();
    }

    public static void main(String... args) {
        ZonedDateTime utcTime = getZonedDateTimeInUTC();
        ZonedDateTime localTime = convert(utcTime, Clock.systemDefaultZone().getZone());
        System.out.println(utcTime + " = " + localTime);

        getTimeZonesGroupByOffsetWithFormatting();
    }

}
