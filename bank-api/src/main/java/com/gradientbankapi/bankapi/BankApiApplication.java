package com.gradientbankapi.bankapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
public class BankApiApplication {

	@Scheduled(fixedRate = 3000)
	public void runEveryThirtySeconds() {
//		RestTemplate restTemplate = new RestTemplate();
//		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
//		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
//		messageConverters.add(converter);
//		restTemplate.setMessageConverters(messageConverters);
//		CoinDesk coinDesk = restTemplate.getForObject("https://api.coindesk.com/v1/bpi/currentprice.json", CoinDesk.class);
//		log.info("The time is now {}", dateFormat.format(new Date()));
//		log.info(coinDesk.toString());
	}

	public static void main(String[] args) {
		SpringApplication.run(BankApiApplication.class, args);
	}



}
