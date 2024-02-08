package com.free.trade.item;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer>{

	Optional<Stock> findTop1ByCodeOrderByDateDesc(String code);
}
