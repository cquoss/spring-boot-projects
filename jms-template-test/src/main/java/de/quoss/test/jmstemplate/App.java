package de.quoss.test.jmstemplate;

import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class App implements CommandLineRunner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
	
	@Autowired
	JmsTemplate template;
	
	public static void main(String...args) {
		LOGGER.info("Start");
		SpringApplication.run(App.class, args);
		LOGGER.info("End");
	}
	
	@Override
	public void run(String...args) {
		for (int i = 0; i < args.length; i++) {
			LOGGER.info("args[{}]: {}", i, args[i]);
		}
		LOGGER.info("[template={}]", template);
		template.convertAndSend("foo");
		Message message = template.receive();
		LOGGER.info("[message={}]", message);
	}

}
