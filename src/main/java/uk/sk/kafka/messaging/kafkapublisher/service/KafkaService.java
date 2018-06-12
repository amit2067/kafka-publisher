package uk.sk.kafka.messaging.kafkapublisher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import uk.sk.kafka.messaging.kafkapublisher.pojos.Application;
import uk.sk.kafka.messaging.kafkapublisher.pojos.Customer;

@Service
public class KafkaService {

	@Autowired
	private RestTemplate restTemplate;
	
	public static final String ELASTIC_CUSTOMER_URL = "http://localhost:9183/pushCustomer";
	public static final String ELASTIC_APPLICATION_URL = "http://localhost:9183/pushApplication";
	
	public void publishToElastic(Customer customer) {
		restTemplate.postForObject(ELASTIC_CUSTOMER_URL, customer, String.class);
	}
	
	public void publishToElastic(Application application) {
		restTemplate.postForObject(ELASTIC_APPLICATION_URL, application, String.class);
	}
	
}
