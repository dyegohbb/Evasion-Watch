package br.com.evasion.watch.config.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaAdminConfig {

	@Bean
	KafkaAdmin kafkaAdmin(KafkaProperties properties) {
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
		return new KafkaAdmin(configs);
	}

	@Bean
	KafkaAdmin.NewTopics topics() {
		return new KafkaAdmin.NewTopics(
				TopicBuilder.name(KafkaTopics.FULL_ANALYSIS.getDescription()).partitions(2).replicas(1).build());
	}

}