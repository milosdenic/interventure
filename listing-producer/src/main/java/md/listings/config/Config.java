package md.listings.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
@ComponentScan(basePackages = { "md.listings.controller", "md.listings.kafka" })
public class Config {
}
