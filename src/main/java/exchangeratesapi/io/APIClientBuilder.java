package exchangeratesapi.io;

import exchangeratesapi.io.client.ApiAsyncRestClient;
import exchangeratesapi.io.client.ApiRestClient;
import exchangeratesapi.io.client.impl.ApiAsyncRestClientImpl;
import exchangeratesapi.io.client.impl.ApiRestClientImpl;
import exchangeratesapi.io.client.service.ExchangeRatesApiV1RestService;
import exchangeratesapi.io.client.service.ServiceFactory;

import javax.net.ssl.SSLContext;
import java.util.concurrent.ExecutorService;

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
 * This is the starting point to create a Exchange Rate API Client.
 *
 * @author antlen
 */
public class APIClientBuilder {
    private final ServiceFactory factory = new ServiceFactory();
    private final String apiKey;
    private boolean isSSL;
    private SSLContext sslContext;

    public APIClientBuilder(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Returns a ApiAsyncRestClient.
     *
     * @return
     */
    public ApiAsyncRestClient buildAsyncRestClient(){
        return new ApiAsyncRestClientImpl(buildService(), apiKey);
    }

    /**
     * Returns a ApiAsyncRestClient that will process responses using the ExecutorService
     *
     * @return
     */
    public ApiAsyncRestClient buildAsyncRestClient(ExecutorService responseService){
        return new ApiAsyncRestClientImpl(buildService(), responseService,apiKey);
    }

    /**
     * Returns a synchronous rest client
     * @return
     */
    public ApiRestClient buildRestClient(){
        return new ApiRestClientImpl(buildService(), apiKey);
    }

    private ExchangeRatesApiV1RestService buildService() {
        if(sslContext !=null){
            return factory.buildV1SSLRestService(sslContext);
        }else if(isSSL){
            return factory.buildV1SSLRestService();
        }else{
            return factory.buildV1RestService();
        }
    }


    /**
     * If you set SSL to true then ensure that your local ssl certs are upto date.
     *
     * If you get an error source updo date certificates and run:
     * <pre>
     * Update the keystore in your local JVM:  'keytool -import -alias *ALIAS_NAME* -keystore $JAVA_HOME\jre\lib\security\cacerts -file *CERT_FILE_LOCATION*'
     *</pre>
     * The other alternative is to use ServiceFactory(SSLContext)
     */
    public APIClientBuilder setSSL(boolean SSL) {
        isSSL = SSL;
        return this;
    }

    /**
     * If you cannot (or do not want to) update the JVM certificate then you can pass the SSL context into
     * the service factory.  One way to do this is:
     *
     * https://stackoverflow.com/questions/11646039/does-resteasy-client-support-tls-ssl
     *
     * <code>
     *      // load the certificate
     *     InputStream fis = this.getClass().getResourceAsStream("file/path/to/your/certificate.crt");
     *     CertificateFactory cf = CertificateFactory.getInstance("X.509");
     *     Certificate cert = cf.generateCertificate(fis);
     *
     *     // load the keystore that includes self-signed cert as a "trusted" entry
     *     KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
     *     keyStore.load(null, null);
     *     TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
     *     keyStore.setCertificateEntry("cert-alias", cert);
     *     tmf.init(keyStore);
     *     SSLContext ctx = SSLContext.getInstance("TLS");
     *     ctx.init(null, tmf.getTrustManagers(), null);`
     * </code>
     * @param ctx
     * @return
     */
    public APIClientBuilder setSslContext(SSLContext ctx) {
        this.sslContext = ctx;
        return this;
    }
}
