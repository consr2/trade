package com.free.trade.item;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class StockService {
	
	@Autowired
	private StockRepository itemRepository;

	public void save(Map<String, String> data) {
		Stock item = new Stock(data);
		itemRepository.save(item);
	}
	
	public void scheduleSave(List<Map<String, String>> response) {
		int now = Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(new Date())) - 1;

		for(Map<String, String> data : response) {
			Optional<Stock> stock = itemRepository.findTop1ByCodeOrderByDateDesc(data.get("srtnCd"));
			
			if(stock.isEmpty()) {
				save(data);
			}else {
				//오늘날짜 -1로 저장된 데이터가 없으면 저장
				if(!stock.get().getDate().equals(String.valueOf(now))) {
					save(data);
				}
			}
		}
	}
}
