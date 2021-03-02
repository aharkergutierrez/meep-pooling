package es.meep.tecnicaltest.eventing.kafka.producers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PublisherKafkaEvents {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	private void sendMessage(Object msg) {

		ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("meep_topic", msg);

		future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

			@Override
			public void onSuccess(SendResult<String, Object> result) {
				System.out.println(
						"Sent message=[" + msg + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			}

			@Override
			public void onFailure(Throwable ex) {
				System.out.println("Unable to send message=[" + msg + "] due to : " + ex.getMessage());
			}
		});

	}

	public void publishEvent(Object event) {
		String jsonString = new Gson().toJson(event);
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class); // parse
		jsonObject.addProperty("_class_kafka_", event.getClass().getName()); // modify
		String jsonStringComplete = new Gson().toJson(jsonObject);
		sendMessage(event);
	}

	public void publishEvents(List<Object> events) {
		events.forEach(event -> {
			String jsonString = new Gson().toJson(event);
			Gson gson = new Gson();
			JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class); // parse
			jsonObject.addProperty("_class_kafka_", event.getClass().getName()); // modify
			String jsonStringComplete = new Gson().toJson(jsonObject);
			sendMessage(event);
		});
	}

}
