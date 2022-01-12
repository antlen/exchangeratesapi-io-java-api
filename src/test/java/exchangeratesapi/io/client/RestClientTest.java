package exchangeratesapi.io.client;

import exchangeratesapi.io.APIClientBuilder;
import exchangeratesapi.io.client.impl.ApiAsyncRestClientImpl;
import exchangeratesapi.io.client.service.ExchangeRatesApiV1RestService;
import exchangeratesapi.io.client.service.ServiceFactory;
import exchangeratesapi.io.client.util.RateDateFormatter;
import exchangeratesapi.io.domain.conversion.ConversionDetails;
import exchangeratesapi.io.domain.rates.ExchangeRates;

import javax.ws.rs.client.InvocationCallback;
import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

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
      /*   test.async.getExchangeRates("JPY",null, "USD", "GBP")
                 .thenAccept(exchangeRates -> System.out.println(exchangeRates))
                 .exceptionally(throwable -> {
                     throwable.printStackTrace();
                     return null;
         });
*/

        LocalDate date = RateDateFormatter.toDateTime("2021-10-09");
      //   test.async.getConversion("JPY", "USD", 2000000.0, new PrintCallback<>() );
      //  test.async.getConversion("JPY", "USD", 2000000.0, date, new PrintCallback<>() );
       // future = test.async.getHistoricalRates(RateDateFormatter.toDateTime("2021-10-09"),new PrintCallback());

        // because this is a text the jvm will exit so need to wait for the response
        if (future!=null)future.join();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private final ApiRestClient client;
    private final ApiAsyncRestClient async;

    public RestClientTest(String apiKey) {
        APIClientBuilder builder = new APIClientBuilder(apiKey);
        client= builder.buildRestClient();
        async = builder.buildAsyncRestClient();
    }


    private static class PrintCallback<T> extends Callback<T>{
        @Override
        public void completed(T t) {
            System.out.println(t);
        }
    }

    private static abstract class Callback<T> implements InvocationCallback<T>{
        @Override
        public void failed(Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
