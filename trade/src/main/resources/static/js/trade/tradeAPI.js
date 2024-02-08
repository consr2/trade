const apiURL = 'https://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService'
const apiKey = 'g8Lr%2BJ3YRNNXtN87jK9C4BnKYWsKgqzJz%2F8nUMaLEYpMM0%2BTIamwBtDeOGSDGSKJ%2BtB1awOVHV5d6VJ5V3Lo6w%3D%3D'
const row = 10
const pageNo = 1
const resultType = 'json'

document.addEventListener('DOMContentLoaded', function() {
	
	let tradeBtn = document.querySelector("#tradeBtn")
	tradeBtn.addEventListener('click', function(e){
		console.log('실행')
		let requestOption = {
			method: "GET"
			,mod: "no-cors"
			,cache: "no-cache"
		}
		
		let response = fetch(
			apiURL + '/getStockPriceInfo'
			+ '?serviceKey=' + apiKey
			+ '&Rows=' + row
			+ '&pageNo=' + pageNo
			+ '&resultType=' + resultType, requestOption
			)
		.then((res) => res.json())
		.then(function(data){
		 	let items = getResponseItem(data)
		 	appendItem(items, "#tradeTable tbody")
		})
		.catch((err) => console.log(err))
		.finally(console.log('끝!'))
		
	})
	
	
})


function responseCheck(data){
	console.log(data)
}

function getResponseItem(data){
	return data.response.body.items.item
}

function appendItem(item, selector){
	let tradeTableBody = document.querySelector(selector)
	
	item.forEach(data => {
		let table = '<tr>' +
				'<td>' + data.srtnCd + '</td>' +
				'<td>' + data.itmsNm + '</td>' +
				'<td>' + data.mrktCtg + '</td>' +
				'<td>' + data.mkp + '</td>' +
				'<td>' + data.clpr + '</td>' +
				'<td>' + data.vs + '</td>' +
				'<td>' + data.fltRt + '</td>' +
				'<td>' + data.lopr + '</td>' +
				'<td>' + data.hipr + '</td>' +
				'<td>' + data.trqu + '</td>' +
				'<td>' + data.mrktTotAmt + '</td>' +
				'</tr>'
		
		tradeTableBody.innerHTML += table
	})
}


	



