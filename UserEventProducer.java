package com.nt.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.nt.dto.UserCreatedEvent;

@Service
public class UserEventProducer {
	@Autowired
	private KafkaTemplate<String,UserCreatedEvent> kafkaTemplate;
	
	private static final String Topic="user-registered";
	
	public void publish(UserCreatedEvent event) {
		System.out.println("===========PRODUCER=========");
		System.out.println("Publishing to Kafka: "+event);
		kafkaTemplate.send(Topic, event).whenComplete((result,ex)->{
			if(ex!=null) {
				ex.printStackTrace();
			}else {
				System.out.println("Kafka message sent successfully");
			}
		});
		System.out.println("Event published successfully");
	}
}
