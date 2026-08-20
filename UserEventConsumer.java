package com.nt.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Service;

import com.nt.dto.UserCreatedEvent;
import com.nt.service.EmailService;


@Service
public class UserEventConsumer {
	@Autowired
	private EmailService emailService;
	
	@RetryableTopic(
			attempts="4",
			dltTopicSuffix=".DLT"
			)
	
	@KafkaListener(
			topics="user-registered",
			groupId="notification-group")
	public void consume(UserCreatedEvent event) {
		System.out.println("Received Event: "+event);
		
		emailService.sendEmail(
                event.getEmail(),
                "Registration Successful",
                "Hello " + event.getFirstName()
                        + ", your registration was successful."
        );
	}	
	
	@DltHandler
	public void handleDlt(UserCreatedEvent event) {
		System.out.println("Message moved to DLT: "+event);
	}
}
