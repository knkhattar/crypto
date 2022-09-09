package com.example.demo;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AppStartupRunner implements ApplicationRunner {

	@Autowired
	private RestTemplate restTemplateWithTrustStore;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("call https url with self signed cert");
		ResponseEntity<String> response = restTemplateWithTrustStore.getForEntity("https://self-signed.badssl.com/",
				String.class, Collections.emptyMap());

		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
	}
}