package exchangeratesapi.io.client.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * The MIT License (MIT)
 *
 *	Copyright (c) 2022 antlen
 *
 *	Permission is hereby granted, free of charge, to any person obtaining a copy
 *	of this software and associated documentation files (the "Software"), to deal
 *	in the Software without restriction, including without limitation the rights
 *	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *	copies of the Software, and to permit persons to whom the Software is
 *	furnished to do so, subject to the following conditions:
 *
 *	The above copyright notice and this permission notice shall be included in all
 *	copies or substantial portions of the Software.
 *
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *	SOFTWARE.
 *
 * ------------------------------------------------
 * Focused utility class for converting to and from the expected date format for exchange rates.
 *
 * @author antlen
 */
public class RateDateFormatter {
    private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Converts a yyyy-MM-dd string to a LocalDate
     *
     * @param t
     * @return
     */
    public static LocalDate toDateTime(String t){
        return LocalDate.parse(t, DATE_FORMAT);
    }

    /**
     * Converts a local date to a yyyy-MM-dd string or null if the input was null
     *
     * @param date
     * @return
     */
    public static String toString(LocalDate date){
        return date == null? null: date.format(DATE_FORMAT);
    }
}
