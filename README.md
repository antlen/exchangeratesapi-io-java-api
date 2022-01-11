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
client.getHistoricalRates(date);
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

       
       
