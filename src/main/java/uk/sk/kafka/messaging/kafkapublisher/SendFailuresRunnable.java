package uk.sk.kafka.messaging.kafkapublisher;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import uk.sk.kafka.messaging.kafkapublisher.pojos.ConsumerFailure;

@Component
public class SendFailuresRunnable implements Runnable {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void run() {
		while (true) {
			try {
				Random random = new Random();
				int low = 1;
				int high = 100;
				int result = random.nextInt(high - low) + low;

				for (int i = 0; i < result; i++) {
					ConsumerFailure consumerFailure = new ConsumerFailure("Customers", "CUSTOMER", "HE_S", 10,
							"Data failure");
					restTemplate.postForObject("http://localhost:9183/createLog", consumerFailure, String.class);
				}

				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
