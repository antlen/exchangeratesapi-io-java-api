package exchangeratesapi.io.client;

import exchangeratesapi.io.domain.conversion.ConversionDetails;
import exchangeratesapi.io.domain.rates.ExchangeRates;
import java.time.LocalDate;

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
 * Basic interface for a synchronous rest client
 *
 * @author antlen
 */
public interface ApiRestClient {

    /**
     *
     * https://api.exchangeratesapi.io/v1/latest
     *     ? access_key = API_KEY
     *
     * Returns the exchanges rates assuming EUR is the base
     * @return
     */
    ExchangeRates getExchangeRates();

    /**
     * https://api.exchangeratesapi.io/v1/latest
     *     ? access_key = API_KEY
     *     & base = USD
     *
     * Returns the exchanges rates for the base currency.
     * <B>Not available using the Free API Key</B>
     * @param base
     * @return
     */
    ExchangeRates getExchangeRates(String base);

    /**
     * This endpoint, depending on your subscription plan will return real-time exchange rate data which gets updated every 60 minutes, every 10 minutes, or every 60 seconds.
     *
     * API Request:
     *
     * https://api.exchangeratesapi.io/v1/latest
     *     ? access_key = API_KEY
     *     & base = USD
     *     & symbols = GBP,JPY,EUR
     *
     * Returns the exchanges rates for the currencies listed vs the base currency.
     * <B>Not available using the Free API Key</B>
     * @param base
     * @param symbols
     * @return
     */
    ExchangeRates getExchangeRates(String base, String ... symbols);

    /**
     * https://api.exchangeratesapi.io/v1/2013-12-24
     *     ? access_key = API_KEY
     *
     * Returns the exchanges rates on the date provided assuming EUR is the base
      * @param date
     * @return
     */
    ExchangeRates getHistoricalRates(LocalDate date);

    /**
     *
     * https://api.exchangeratesapi.io/v1/2013-12-24
     *     ? access_key = API_KEY
     *     & base = GBP
     *
     * Returns the exchanges rates on the date provided for the base currency.
     * <B>Not available using the Free API Key</B>
     * @param date
     * @param base
     * @return
     */
    ExchangeRates getHistoricalRates(LocalDate date, String base);

    /**
     * Historical Rates Endpoint
     * With this endpoint we have the possibility to see historical rates of the currencies back to 1999,
     * most of the currencies data are available until 1999.
     * You can query the Exchangerates API for historical rates by appending a date (format YYYY-MM-DD)
     * to the base URL.
     *
     * API Request:
     *
     * https://api.exchangeratesapi.io/v1/2013-12-24
     *     ? access_key = API_KEY
     *     & base = GBP
     *     & symbols = USD,CAD,EUR
     *
     * Returns the exchanges rates on the date provided for the currencies listed vs the base currency.
     * <B>Not available using the Free API Key</B>
     * @param date
     * @param base
     * @param symbols
     * @return
     */
    ExchangeRates getHistoricalRates(LocalDate date, String base, String ... symbols);

    /**
     * This is a separate endpoint for currency conversion that comes with the Exchangerates API
     * and can be used to convert an amount from one currency to another.
     * Please use this API endpoint for the conversion of any currency.
     *
     * It is also possible to convert currencies using historical exchange rate data.
     * To do this, please also use the API's date parameter and set it to your preferred date.
     * (format YYYY-MM-DD).
     *
     * API Request:
     *
     * https://api.exchangeratesapi.io/v1/convert
     *     ? access_key = API_KEY
     *     & from = GBP
     *     & to = JPY
     *     & amount = 25
     *     & date = 2019-10-09
     *
     * Converts the amount from one currency to another using the rate on the date provided
     * @param from
     * @param to
     * @param amount
     * @param date
     * @return
     */
    ConversionDetails getConversion(String from, String to, double amount, LocalDate date);

    /**
     * This is a separate endpoint for currency conversion that comes with the Exchangerates API
     * and can be used to convert an amount from one currency to another.
     * Please use this API endpoint for the conversion of any currency.
     *
     * It is also possible to convert currencies using historical exchange rate data.
     * To do this, please also use the API's date parameter and set it to your preferred date.
     * (format YYYY-MM-DD).
     *
     * API Request:
     *
     * https://api.exchangeratesapi.io/v1/convert
     *     ? access_key = API_KEY
     *     & from = GBP
     *     & to = JPY
     *     & amount = 25
     *
     * Converts the amount from one currency to another using the current rate
     * @param from
     * @param to
     * @param amount
     * @return
     */
    ConversionDetails getConversion(String from, String to, double amount);
}
