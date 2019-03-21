package thangnd15.microservices.currencyclientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("thangnd15.microservices.currencyclientservice")
public class CurrencyClientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyClientServiceApplication.class, args);
	}

}