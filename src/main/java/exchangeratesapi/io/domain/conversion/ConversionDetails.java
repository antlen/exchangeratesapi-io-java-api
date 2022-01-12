package exchangeratesapi.io.domain.conversion;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
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
 * JSON Pojo for representing an exchange conversion
 *
 * @author antlen
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "success",
        "query",
        "info",
        "historical",
        "date",
        "result"
})
@Generated("jsonschema2pojo")
public class ConversionDetails {

    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("query")
    private ConversionQuery query;
    @JsonProperty("info")
    private ConversionInfo info;
    @JsonProperty("historical")
    private boolean historical;
    @JsonProperty("date")
    private String date;
    @JsonProperty("result")
    private double result;

    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @JsonProperty("query")
    public ConversionQuery getQuery() {
        return query;
    }

    @JsonProperty("query")
    public void setQuery(ConversionQuery query) {
        this.query = query;
    }

    @JsonProperty("info")
    public ConversionInfo getInfo() {
        return info;
    }

    @JsonProperty("info")
    public void setInfo(ConversionInfo info) {
        this.info = info;
    }

    @JsonProperty("historical")
    public boolean getHistorical() {
        return historical;
    }

    @JsonProperty("historical")
    public void setHistorical(boolean historical) {
        this.historical = historical;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("result")
    public double getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(double result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ConversionDetails{" +
                "success=" + success +
                ", query=" + query +
                ", info=" + info +
                ", historical='" + historical + '\'' +
                ", date='" + date + '\'' +
                ", result=" + result +
                '}';
    }
}