package com.free.trade.item;

import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Stock {

	public Stock(Map<String, String> data) {
		this.code = data.get("srtnCd");
		this.name = data.get("itmsNm");
		this.market = data.get("mrktCtg");
		this.startPrice = data.get("mkp");
		this.endPrice = data.get("clpr");
		this.dept = data.get("vs");
		this.deptPer = data.get("fltRt");
		this.lowest = data.get("lopr");
		this.heightest = data.get("hipr");
		this.tradeCount = data.get("trqu");
		this.stockAmount = data.get("mrktTotAmt");
		this.date = data.get("basDt");
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String code;
	private String name;
	private String market;
	private String startPrice;
	private String endPrice;
	private String dept;
	private String deptPer;
	private String lowest;
	private String heightest;
	private String tradeCount;
	private String stockAmount;
	private String date;
}
