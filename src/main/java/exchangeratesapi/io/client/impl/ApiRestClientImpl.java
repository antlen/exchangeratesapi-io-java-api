package exchangeratesapi.io.client.impl;

import exchangeratesapi.io.client.ApiRestClient;
import exchangeratesapi.io.client.service.ExchangeRatesApiV1RestService;
import exchangeratesapi.io.client.util.RateDateFormatter;
import exchangeratesapi.io.client.util.SymbolFormatter;
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
 * The default implementation for ApiRestClient
 *
 * @author antlen
 */
public class ApiRestClientImpl implements ApiRestClient {
    private final ExchangeRatesApiV1RestService service;
    private final String apiKey;

    public ApiRestClientImpl(ExchangeRatesApiV1RestService service, String apiKey) {
        this.service = service;
        this.apiKey = apiKey;
    }

    @Override
    public ExchangeRates getExchangeRates() {
        return getExchangeRates(null, null);
    }

    @Override
    public ExchangeRates getExchangeRates(String base) {
        return getExchangeRates(base, null);
    }

    @Override
    public ExchangeRates getExchangeRates(String base, String[] symbols) {
        return service.getExchangeRates(apiKey, base, SymbolFormatter.getSymbols(symbols)).toCompletableFuture().join();
    }

    @Override
    public ExchangeRates getHistoricalRates(LocalDate date) {
        return getHistoricalRates(date, null, null);
    }

    @Override
    public ExchangeRates getHistoricalRates(LocalDate date, String base) {
        return getHistoricalRates(date, base, null);
    }

    @Override
    public ExchangeRates getHistoricalRates(LocalDate date, String base, String... symbols) {
        return service.getHistoricalRates(apiKey, RateDateFormatter.toString(date),
                                            base, SymbolFormatter.getSymbols(symbols)).toCompletableFuture().join();
    }

    @Override
    public ConversionDetails getConversion(String from, String to, double amount, LocalDate date) {
        return service.getConversion(apiKey, from, to, amount,
                RateDateFormatter.toString(date)).toCompletableFuture().join();
    }

    @Override
    public ConversionDetails getConversion(String from, String to, double amount) {
        return getConversion(from, to, amount, null);
    }
}
