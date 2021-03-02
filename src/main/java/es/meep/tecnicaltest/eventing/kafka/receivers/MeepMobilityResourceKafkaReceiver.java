package es.meep.tecnicaltest.eventing.kafka.receivers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MeepMobilityResourceKafkaReceiver {

	@KafkaListener(id = "meepIdConsumer", topics = "meep_topic")
	public void listen(String in) {
		System.out.println(in);
	}
	
}
