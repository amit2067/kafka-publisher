package uk.sk.kafka.messaging.kafkapublisher.controllers;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import uk.sk.kafka.messaging.kafkapublisher.pojos.Application;
import uk.sk.kafka.messaging.kafkapublisher.pojos.Customer;

@Api(value="Kafka Publisher service")
@RestController
public class KafkaPublishController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired @Qualifier("customerPublisherTemplate") private KafkaTemplate<String, Customer> customerTemplate;
	@Autowired @Qualifier("applicationPublisherTemplate") private KafkaTemplate<String, Application> applicationTemplate;
	
	
	private ProducerRecord<String, Customer> createCustomerMessage(Customer customer) {
		return new ProducerRecord<String, Customer>("CUSTOMER", customer);
	}
	
	@ApiOperation(value = "Get the heart beat!",response = String.class)
	@RequestMapping(method= {RequestMethod.GET}, path="/heartbeat")
	public ResponseEntity<?> ping() {
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
	
	@ApiOperation(value = "Publish the customer to kafka broker",response = String.class)
	@RequestMapping(method= {RequestMethod.POST}, path="/publishCust", consumes="application/json")
	public ResponseEntity<?> publishCustomer(@RequestBody Customer value) {
		ListenableFuture<SendResult<String, Customer>> lFuture = customerTemplate.send(createCustomerMessage(value));
		lFuture.addCallback(new ListenableFutureCallback<SendResult<String, Customer>>() {
			
		    @Override
		    public void onFailure(Throwable ex) {
		    	logger.info("Failure received (CUSTOMER) in (ListenableFuture): "+ ex.getMessage());
		    }

			@Override
			public void onSuccess(SendResult<String, Customer> arg0) {
				logger.info("Success received (CUSTOMER) in (ListenableFuture): "+ arg0.getProducerRecord().topic());
			}

		});
		return new ResponseEntity<String>("Success", HttpStatus.ACCEPTED);
	}
	
	private ProducerRecord<String, Application> createApplicationMessage(Application application) {
		return new ProducerRecord<String, Application>("APPLICATION", application);
	}
	
	@ApiOperation(value = "Publish the application to kafka broker",response = String.class)
	@RequestMapping(method= {RequestMethod.POST}, path="/publishApp", consumes="application/json")
	public ResponseEntity<?> publishApplication(@RequestBody Application application) {
		ListenableFuture<SendResult<String, Application>> lFuture = applicationTemplate.send(createApplicationMessage(application));
		lFuture.addCallback(new ListenableFutureCallback<SendResult<String, Application>>() {
			
		    @Override
		    public void onFailure(Throwable ex) {
		    	logger.info("Failure received (APPLICATION) in (ListenableFuture): "+ ex.getMessage());
		    }

			@Override
			public void onSuccess(SendResult<String, Application> arg0) {
				logger.info("Success received (APPLICATION) in (ListenableFuture): "+ arg0.getProducerRecord().topic());
			}

		});
		return new ResponseEntity<String>("Success", HttpStatus.ACCEPTED);
	}
}
