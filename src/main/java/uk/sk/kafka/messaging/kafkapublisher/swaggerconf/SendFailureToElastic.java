package uk.sk.kafka.messaging.kafkapublisher.swaggerconf;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.sk.kafka.messaging.kafkapublisher.SendFailuresRunnable;

@Component
public class SendFailureToElastic {
	
	@Autowired
	private SendFailuresRunnable SendFailuresRunnable;
	
	@PostConstruct
	public void init() {
		Thread thread = new Thread(SendFailuresRunnable);		
		thread.start();
	}

}
