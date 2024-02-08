package com.free.trade.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.DefaultUriBuilderFactory.EncodingMode;

@Configuration
public class WebClientConfig {

	private final String BASE_URL = "https://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService";
	@Bean
	public WebClient webClient() {
		DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(BASE_URL);
		factory.setEncodingMode(EncodingMode.VALUES_ONLY);
	
		return WebClient.builder()
				.uriBuilderFactory(factory)
				.baseUrl(BASE_URL)
				.build();
	}
	
}
