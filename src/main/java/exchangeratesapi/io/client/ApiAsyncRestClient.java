package exchangeratesapi.io.client;

import exchangeratesapi.io.domain.rates.ExchangeRates;

import javax.ws.rs.client.InvocationCallback;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

/**
 * The MIT License (MIT)
 *
 *	Copyright (c) 2021 antlen
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
 * Basic interface for an Asynchronous rest client
 *
 * @author antlen
 */
public interface ApiAsyncRestClient {

    /**
     * Returns the exchanges rates assuming EUR is the base
     * @param callback
     * @return
     */
    CompletableFuture<ExchangeRates> getExchangeRates(InvocationCallback<ExchangeRates> callback);

    /**
     * Returns the exchanges rates for the currencies listed vs the base currency.
     * <B>Not available using the Free API Key</B>
     * @param base
     * @param callback
     * @return
     */
    CompletableFuture<ExchangeRates> getExchangeRates(String base, InvocationCallback<ExchangeRates> callback);

    /**
     * Returns the exchanges rates for the currencies listed vs the base currency.
     * <B>Not available using the Free API Key</B>
     * @param base
     * @param callback
     * @param symbols
     * @return
     */
    CompletableFuture<ExchangeRates> getExchangeRates(String base,InvocationCallback<ExchangeRates> callback,String ... symbols);

    /**
     * Returns the exchanges rates on the date provided assuming EUR is the base
     * @param date
     * @param callback
     * @return
     */
    CompletableFuture<ExchangeRates> getHistoricalRates(LocalDate date, InvocationCallback<ExchangeRates> callback);

    /**
     * Returns the exchanges rates on the date providedfor the base currency.
     * <B>Not available using the Free API Key</B>
     * @param date
     * @param base
     * @param callback
     * @return
     */
    CompletableFuture<ExchangeRates> getHistoricalRates(LocalDate date, String base,InvocationCallback<ExchangeRates> callback);

    /**
     * Returns the exchanges rates on the date provided for the currencies listed vs the base currency.
     * <B>Not available using the Free API Key</B>
     * @param date
     * @param base
     * @param callback
     * @param symbols
     * @return
     */
    CompletableFuture<ExchangeRates> getHistoricalRates(LocalDate date, String base, InvocationCallback<ExchangeRates> callback, String ... symbols);
}
