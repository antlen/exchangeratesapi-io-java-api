package exchangeratesapi.io.client.service;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

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
 * Generated a service proxy for the Exchange Rate service.
 *
 * @author antlen
 */
public class ServiceFactory {

    public static final String SSL_URI = "https://api.exchangeratesapi.io";
    public static final String URI = "http://api.exchangeratesapi.io";

    /**
     * Return a non encrypted rest service.
     * @return
     */
    public ExchangeRatesApiV1RestService buildV1RestService(){
        return buildProxy(ClientBuilder.newClient(), URI);
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
    public ExchangeRatesApiV1RestService buildV1SSLRestService(SSLContext ctx){
        Client client = ClientBuilder.newBuilder().sslContext(ctx).newClient();
        return buildProxy(client, SSL_URI);
    }

    /**
     * If you set SSL to true then ensure that your local ssl certs are upto date.
     *
     * Update the keystore in your local JVM:  'keytool -import -alias <Replace certificate Alias name> -keystore $JAVA_HOME\jre\lib\security\cacerts -file <Replace your Certificate file location>'
     *
     * The other alternative is to use ServiceFactory(SSLContext)
     */
    public ExchangeRatesApiV1RestService buildV1SSLRestService(){
        return buildProxy(ClientBuilder.newClient(), SSL_URI);
    }

    private ExchangeRatesApiV1RestService buildProxy(Client client, String url) {
        return ((ResteasyWebTarget)client.target(url)).proxy(ExchangeRatesApiV1RestService.class);
    }
}
