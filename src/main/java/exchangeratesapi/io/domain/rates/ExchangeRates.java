package exchangeratesapi.io.domain.rates;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import exchangeratesapi.io.client.util.RateDateFormatter;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;

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
 * JSON Pojo for representing the Exchange Rate resposne
 *
 * @author antlen
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "success",
        "timestamp",
        "base",
        "date",
        "rates"
})
@Generated("jsonschema2pojo")
public class ExchangeRates {

    @JsonProperty("success")
    private boolean success;
    @JsonProperty("historical")
    private boolean historical;
    @JsonProperty("timestamp")
    private Integer timestamp;
    @JsonProperty("base")
    private String base;
    @JsonProperty("date")
    private String date;
    @JsonProperty("rates")
    private Map<String,Double> rates;
    @JsonIgnore
    private LocalDate localDate;

    @JsonProperty("success")
    public boolean isSuccess() {
        return success;
    }

    @JsonProperty("historical")
    public boolean isHistorical() {
        return historical;
    }

    @JsonProperty("timestamp")
    public Integer getTimestamp() {
        return timestamp;
    }

    @JsonProperty("base")
    public String getBase() {
        return base;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonIgnore
    public LocalDate getLocalDate() {
        if(localDate == null && date !=null){
            localDate = RateDateFormatter.toDateTime(date);
        }
        return localDate;
    }

    @JsonProperty("rates")
    public Map<String,Double>  getRates() {
        return Collections.unmodifiableMap(rates);
    }

    @Override
    public String toString() {
        return "ExchangeRates{" +
                "success=" + success +
                ", historical=" + historical +
                ", timestamp=" + timestamp +
                ", base='" + base + '\'' +
                ", date='" + date + '\'' +
                ", rates=" + rates +
                ", localDate=" + localDate +
                '}';
    }
}