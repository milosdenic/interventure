package md.listings.config;

import md.listings.repository.ListingRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import static org.mockito.Mockito.mock;

@Configuration
@ComponentScan(basePackages = { "md.listings.kafka", "md.listings.service" })
@EnableAutoConfiguration
public class TestConfig {

    @Bean
    public ElasticsearchOperations elasticsearchOperations() {
        return mock(ElasticsearchOperations.class);
    }

    @Bean
    public ListingRepository listingRepository() {
        return mock(ListingRepository.class);
    }

}
