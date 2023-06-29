package br.com.evasion.watch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class EvasionWatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvasionWatchApplication.class, args);
	}

}
