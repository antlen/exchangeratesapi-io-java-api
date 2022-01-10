package exchangeratesapi.io.client.impl;

import exchangeratesapi.io.client.ApiAsyncRestClient;
import exchangeratesapi.io.client.service.ExchangeRatesApiV1RestService;
import exchangeratesapi.io.client.util.RateDateFormatter;
import exchangeratesapi.io.domain.rates.ExchangeRates;

import javax.ws.rs.client.InvocationCallback;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;

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
 * The default implementation for ApiAsyncRestClient
 *
 * @author antlen
 */
public class ApiAsyncRestClientImpl implements ApiAsyncRestClient {
    private final ExchangeRatesApiV1RestService service;
    private final ExecutorService responseService;
    private final String apiKey;

    public ApiAsyncRestClientImpl(ExchangeRatesApiV1RestService service, String apiKey) {
        this(service, null, apiKey);
    }
    public ApiAsyncRestClientImpl(ExchangeRatesApiV1RestService service, ExecutorService responseService, String apiKey) {
        this.service = service;
        this.responseService = responseService;
        this.apiKey = apiKey;
    }

    private <T> CompletionStage<T> respond(CompletionStage<T> s, Function<? super T,? extends T> action){
        if(responseService != null){
            return s.thenApplyAsync(action, responseService);
        }else{
            return s.thenApplyAsync(action);
        }
    }

    private <T> CompletionStage<T> handleCallbackAndExceptions(InvocationCallback<T> callback, CompletionStage<T>  f){
        if(callback == null){
            f.toCompletableFuture().exceptionally(throwable -> {
                throwable.printStackTrace();;
                return null;
            });
            return f;
        }else{
            f = f.thenApplyAsync(v -> {
                callback.completed(v);
                return v;
            }, responseService);

            f.toCompletableFuture().exceptionally(throwable -> {
                responseService.submit( () ->callback.failed(throwable));
                return null;
            });
            return f;
        }
    }

    @Override
    public CompletableFuture<ExchangeRates> getExchangeRates(InvocationCallback<ExchangeRates> callback) {
        return getExchangeRates(null, callback);
    }

    @Override
    public CompletableFuture<ExchangeRates> getExchangeRates(String base, InvocationCallback<ExchangeRates> callback) {
        return getExchangeRates(null, callback, null);
    }

    @Override
    public CompletableFuture<ExchangeRates> getExchangeRates(String base, InvocationCallback<ExchangeRates> callback,String ... symbols) {
        return handleCallbackAndExceptions(callback,  service.getExchangeRates(apiKey, base, symbols)).toCompletableFuture();
    }

    @Override
    public CompletableFuture<ExchangeRates> getHistoricalRates(LocalDate date, InvocationCallback<ExchangeRates> callback) {
        return getHistoricalRates(date, null, callback);
    }

    @Override
    public CompletableFuture<ExchangeRates> getHistoricalRates(LocalDate date, String base, InvocationCallback<ExchangeRates> callback) {
        return getHistoricalRates(date, null, callback, null);
    }

    @Override
    public CompletableFuture<ExchangeRates> getHistoricalRates(LocalDate date, String base, InvocationCallback<ExchangeRates> callback, String... symbols) {
        return handleCallbackAndExceptions(callback, service.getHistoricalRates(apiKey, RateDateFormatter.toString(date),
                                                                        base, symbols)).toCompletableFuture();
    }
}
