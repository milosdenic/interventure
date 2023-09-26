package md.listings.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@EnableKafka
@EnableRetry
@ComponentScan(basePackages = { "md.listings.controller", "md.listings.kafka", "md.listings.service" })
@EnableElasticsearchRepositories(basePackages = "md.listings.repository")
public class Config {
}
