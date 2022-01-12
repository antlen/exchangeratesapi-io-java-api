package exchangeratesapi.io.client.service;

import exchangeratesapi.io.domain.conversion.ConversionDetails;
import exchangeratesapi.io.domain.rates.ExchangeRates;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletionStage;

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
 * Interface for the service proxy for making rest api calls.
 *
 * @author antlen
 */
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON})
@Path("/v1")
public interface ExchangeRatesApiV1RestService {

    /**
     *
     * @param apiKey - [required] Your API Key.
     * @param base - [optional] Enter the three-letter currency code of your preferred base currency.
     * @param symbols - [optional] Enter a list of comma-separated currency codes to limit output currencies.
     * @return
     */
    @GET
    @Path("/latest")
    CompletionStage<ExchangeRates> getExchangeRates(@QueryParam("access_key") String apiKey,
                                                     @QueryParam("base") String base,
                                                     @QueryParam("symbols") String symbols);

    /**
     *
     * @param apiKey - [required] Your API Key.
     * @param base - [optional] Enter the three-letter currency code of your preferred base currency.
     * @param symbols - [optional] Enter a list of comma-separated currency codes to limit output currencies.
     * @return
     */
    @GET
    @Path("/{date}")
    CompletionStage<ExchangeRates> getHistoricalRates(@QueryParam("access_key") String apiKey,
                                                    @PathParam("date") String date,
                                                    @QueryParam("base") String base,
                                                    @QueryParam("symbols") String symbols);

    /**
     *
     * @param apiKey - [required] Your API Key.
     * @param from - [required] The three-letter currency code of the currency you would like to convert from.
     * @param to -  [required] The three-letter currency code of the currency you would like to convert to.
     * @param amount - [required] The amount to be converted.
     * @param date -  date	[optional] Specify a date (format YYYY-MM-DD) to use historical rates for this conversion.
     * @return
     */
    @GET
    @Path("/convert")
    CompletionStage<ConversionDetails> getConversion(@QueryParam("access_key") String apiKey,
                                                     @QueryParam("from") String from,
                                                     @QueryParam("to") String to,
                                                     @QueryParam("amount") double amount,
                                                     @QueryParam("date") String date);

    ;
}
