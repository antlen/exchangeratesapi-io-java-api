package exchangeratesapi.io.client;

import exchangeratesapi.io.APIClientBuilder;
import exchangeratesapi.io.client.impl.ApiAsyncRestClientImpl;
import exchangeratesapi.io.client.service.ExchangeRatesApiV1RestService;
import exchangeratesapi.io.client.service.ServiceFactory;
import exchangeratesapi.io.domain.rates.ExchangeRates;

import javax.ws.rs.client.InvocationCallback;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class RestClientTest {

    /**
     * Test if you API Key is working.  Pass as a VM param -DapiKey=xxxx
     * Then uncomment the test you want to try, or add your own.
     * @param args
     */
    public static void main(String args[]){
        RestClientTest test = new RestClientTest(System.getProperty("apiKey"));
        CompletableFuture<ExchangeRates> future = null;
        // printExchangeRates(test.client.getExchangeRates());
        // printExchangeRates(test.client.getExchangeRates("USD"));
        // printExchangeRates(test.client.getExchangeRates("USD", "HKD", "JPY"));
        // future = test.async.getExchangeRates(new PrintCallback());
       // future = test.async.getHistoricalRates(RateDateFormatter.toDateTime("2021-10-09"),new PrintCallback());

        // because this is a text the jvm will exit so need to wait for the response
        if (future!=null)future.join();
    }


    private final ApiRestClient client;
    private final ApiAsyncRestClient async;

    public RestClientTest(String apiKey) {
        APIClientBuilder builder = new APIClientBuilder(apiKey);
        client= builder.buildRestClient();
        async = builder.buildAsyncRestClient();
    }

    private static void printExchangeRates(ExchangeRates rate) {
        System.out.println("********************************************" );
        System.out.println("********************************************" );
        System.out.println("********************************************" );
        System.out.println("Date: " + rate.getDate());
        System.out.println("Base: " + rate.getBase());
        System.out.println("Historical: " + (rate.isHistorical()?"YES": "NO"));
        for(Map.Entry<String, Double> rates : rate.getRates().entrySet()){
            System.out.println(rates.getKey() +" = " +rates.getValue());
        }
    }

    private static class PrintCallback extends Callback<ExchangeRates>{
        @Override
        public void completed(ExchangeRates exchangeRates) {
            printExchangeRates(exchangeRates);
        }
    }

    private static abstract class Callback<T> implements InvocationCallback<T>{
        @Override
        public void failed(Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
