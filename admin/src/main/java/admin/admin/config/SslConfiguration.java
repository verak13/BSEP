package admin.admin.config;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SslConfiguration {
	
	@Value("${trust-store}")
	private Resource trustStore;

	@Value("${trust-store-password}")
	private String trustStorePassword;
    
    @Bean(name="appRestClient")

    public RestTemplate restTemplate() throws Exception {
        SSLContext sslContext = new SSLContextBuilder()
          .loadKeyMaterial(trustStore.getURL(), trustStorePassword.toCharArray(), trustStorePassword.toCharArray())
          .loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray())
          .build();

        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);

        HttpClient httpClient = HttpClients.custom()
          .setSSLSocketFactory(socketFactory)
          .build();

        HttpComponentsClientHttpRequestFactory factory = 
          new HttpComponentsClientHttpRequestFactory(httpClient);

        return new RestTemplate(factory);
    }

}