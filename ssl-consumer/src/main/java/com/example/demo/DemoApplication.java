package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

	@Bean
	public RestTemplate restTemplateWithTrustStore(RestTemplateBuilder builder) throws IOException,
			CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		File file = new File("badssl-com.p12");
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(file, "changeit".toCharArray()).build();
		SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);

		HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();

		return builder.requestFactory(() -> new HttpComponentsClientHttpRequestFactory(httpClient)).build();
	}

}
