package thangnd15.microservices.currencyclientservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyClientController {
	
	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyClientBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		Map<String, String> uriVariables = new HashMap<String, String>();
		
		uriVariables.put("from", from);
		
		uriVariables.put("to", to);
		
		ResponseEntity<CurrencyClientBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8001/currency-exchange/from/{from}/to/{to}",
				CurrencyClientBean.class,
				uriVariables);
		
		CurrencyClientBean responseBean = responseEntity.getBody();

		
		return new CurrencyClientBean(responseBean.getId(), from, to, responseBean.getConversionMultiple(), 
				quantity, quantity.multiply(responseBean.getConversionMultiple()), responseBean.getPort());
	}
	
	@GetMapping("/currency-converter-test/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyClientBean convertCurrencyTest(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		
		CurrencyClientBean responseBean = proxy.retrieveExchangeValue(from, to);
		
		return new CurrencyClientBean(responseBean.getId(), from, to, responseBean.getConversionMultiple(), 
				quantity, quantity.multiply(responseBean.getConversionMultiple()), responseBean.getPort());
	}
	
}
