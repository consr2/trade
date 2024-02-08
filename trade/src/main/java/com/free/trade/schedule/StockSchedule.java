package com.free.trade.schedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.free.trade.item.StockService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class StockSchedule {

	@Autowired
	private WebClient webClient;
	
	@Autowired
	private StockService stockService;
	
	@Value("${stock.apiKey}")
	private String apiKey;
	
	private int page = 1;
	
	@Scheduled(fixedDelay = 1000 * 60 * 10)
	public void getStock() throws Exception{
		int now = Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(new Date())) - 1;
		for(int i=1; i < 10; i++) {
			Mono<String> response = webClient.get()
					.uri(uriBuilder -> uriBuilder
							.path("/getStockPriceInfo")
							.queryParam("serviceKey", apiKey)
							.queryParam("numOfRows", "100")
							.queryParam("pageNo", pageSquence())
							.queryParam("resultType", "json")
							.queryParam("mrktCls", "KOSPI")
							.queryParam("basDt", "20240206")
							.build())
					.retrieve()
					.bodyToMono(String.class);
			
			response.subscribe(responseData -> {
				ObjectMapper objMapper = new ObjectMapper();
				List<Map<String, String>> data = new ArrayList<>();
				
				try {
					JsonNode jsonNode = jsonNode = objMapper.readTree(responseData)
							.get("response")
							.get("body")
							.get("items")
							.get("item");
					data = objMapper.convertValue(jsonNode, List.class);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				};
				
				stockService.scheduleSave(data);
			});
		}
	}
	
	public int pageSquence() {
		if(page < 10) {
			return page;
		}else {
			page++;
			return page;
		}
	}
}
