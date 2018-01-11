package uk.sk.kafka.messaging.kafkapublisher;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import uk.sk.kafka.messaging.kafkapublisher.pojos.Application;
import uk.sk.kafka.messaging.kafkapublisher.pojos.Customer;


@Configuration
public class KafkaProducerConfig {
	Logger logger = LoggerFactory.getLogger(this.getClass());
 
	@Value(value = "${kafka.bootstrapAddress}") private String bootStrapAddress;
	
    @Bean
    public ProducerFactory<String, Customer> customerPublisherFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }
 
    @Bean(name="customerPublisherTemplate")
    public KafkaTemplate<String, Customer> kafkaCustomerTemplate() {
        KafkaTemplate<String, Customer> kafkaTemplate = new KafkaTemplate<>(customerPublisherFactory());
        /*kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
			@Override
			public void onSuccess(String topic, Integer partition, String key, String value, RecordMetadata recordMetadata) {
				logger.info("Success: topic: {}, partition:: {}, key: {}, value: {}, offset: {}", topic, partition, key, value, recordMetadata.offset());
			}
			
			@Override
			public void onError(String topic, Integer partition, String key, String value, Exception exception) {
				logger.info("Failure: topic: {}, partition:: {}, key: {}, value: {}, Exception: {}", topic, partition, key, value, exception.getMessage());
			}
			
			@Override
			public boolean isInterestedInSuccess() {
				return true;
			}
		});*/
        return kafkaTemplate;
    }
    
    @Bean
    public ProducerFactory<String, Application> applicationPublisherFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }
    
    @Bean(name="applicationPublisherTemplate")
    public KafkaTemplate<String, Application> kafkaApplicationTemplate() {
        KafkaTemplate<String, Application> kafkaTemplate = new KafkaTemplate<>(applicationPublisherFactory());
        return kafkaTemplate;
    }
}