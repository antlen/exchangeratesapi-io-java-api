# exchangeratesapi-io-java-api
# coinbase-java-api

### Maven
```
        <dependency>
            <groupId>org.estonlabs</groupId>
            <artifactId>exchangeratesapi-io-java-api</artifactId>
            <version>1.3</version>
        </dependency>
```

## How to use

### Create a synchronous client 
``` 
APIClientBuilder builder = new APIClientBuilder(apiKey); 
ApiRestClient client = builder.buildRestClient(); 
```
### Create an asynchronous client 
``` 
APIClientBuilder builder = new APIClientBuilder(apiKey); 
ApiAsyncRestClient client = builder.buildAsyncRestClient();
``` 
or if you want all responses to be served to your own ExecutorService
``` 
APIClientBuilder builder = new APIClientBuilder(apiKey); 
ApiAsyncRestClient client = builder.buildAsyncRestClient(Executors.newSingleThreadScheduledExecutor());
``` 
### Use SSL
If you want to connect over ssl then there are two options:<BR>

1. To use the SSL certificates bundled with the JVM (should be good enough for recent jvn versions:
``` 
builder.setSSL(true);
``` 
2. Or if you do not have recent certificates installed with the JVM and will not or cannot update the ssl certificates then:
``` 
SSLContext ctx = getSSLContext();
builder.setSSL(true);
``` 
The details are in the javadoc for creating a SSLContext.


### Exchange Rates

#### Synchrronous
``` 
client.getExchangeRates();
client.getExchangeRates("USD");
client.getExchangeRates("USD", "HKD", "JPY");
``` 
#### Asynchronous

Use a callback...
``` 
client.getExchangeRates(new InvocationCallback<ExchangeRates>() {
       @Override
       public void completed(ExchangeRates exchangeRates) {
           System.out.println(exchangeRates);
       }

       @Override
       public void failed(Throwable throwable) {
              throwable.printStackTrace();
       }
   });
``` 
or use a CompletableFuture..
``` 
client.getExchangeRates(null)
     .thenAccept(exchangeRates -> System.out.println(exchangeRates))
     .exceptionally(throwable -> {
                    throwable.printStackTrace();
                    return null;
     });
``` 
### Historical Exchange Rates
#### Synchrronous
``` 
LocalDate date = RateDateFormatter.toDateTime("2021-10-09");
client.getHistoricalRates(date);
client.getHistoricalRates(date, "EUR");
client.getHistoricalRates(date, "EUR", "HKD", "JPY");
``` 
#### Asynchronous

Use a callback...
``` 
LocalDate date = RateDateFormatter.toDateTime("2021-10-09");
client.getHistoricalRates(date, new InvocationCallback<>() {
     @Override
      public void completed(ExchangeRates exchangeRates) {
              System.out.println(exchangeRates);
       }

        @Override
         public void failed(Throwable throwable) {
              throwable.printStackTrace();
         }
   });
``` 
or use a CompletableFuture..
``` 
async.getHistoricalRates(date, null)
     .thenAccept(exchangeRates -> System.out.println(exchangeRates))
     .exceptionally(throwable -> {
                    throwable.printStackTrace();
                    return null;
     });
``` 

### Conversions
#### Synchrronous
``` 
LocalDate date = RateDateFormatter.toDateTime("2021-10-09");
client.getConversion("JPY", "USD", 2000000.0);
client.getConversion("JPY", "USD", 2000000.0, date);
``` 
#### Asynchronous

Use a callback...
``` 
LocalDate date = RateDateFormatter.toDateTime("2021-10-09");
client.getConversion("JPY", "USD", 2000000.0, date, new InvocationCallback<>() {
     @Override
      public void completed(ConversionDetails conversion) {
              System.out.println(conversion);
       }

        @Override
         public void failed(Throwable throwable) {
              throwable.printStackTrace();
         }
   });
``` 
or use a CompletableFuture..
``` 
async.getConversion("JPY", "USD", 2000000.0, date,  null)
     .thenAccept(conversion -> System.out.println(conversion))
     .exceptionally(throwable -> {
                    throwable.printStackTrace();
                    return null;
     });
```    
