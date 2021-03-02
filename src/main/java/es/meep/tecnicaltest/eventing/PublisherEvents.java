package es.meep.tecnicaltest.eventing;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import es.meep.tecnicaltest.eventing.events.GenericMeepEvent;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PublisherEvents {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	public void publishEvent(Object event) {
		applicationEventPublisher.publishEvent(event);
	}

	public void publishEventFromModelClass(Object modelObject, Class eventClass, Object source) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		Object instanceEvent = eventClass.getConstructor(Object.class).newInstance(source);
		Method method = eventClass.getMethod(GenericMeepEvent.METHOD_TO_SET_MODEL, modelObject.getClass());
		method.invoke(instanceEvent, modelObject);
		applicationEventPublisher.publishEvent(instanceEvent);
	}

	public void publishEvents(List<Object> events) {
		events.forEach(event -> {
			applicationEventPublisher.publishEvent(event);
		});
	}

	public void publishEventsFromModelClass(List modelObjects, Class eventClass, Object source) {
		modelObjects.forEach(modelObject -> {
			Object instanceEvent;
			try {
				instanceEvent = eventClass.getConstructor(Object.class).newInstance(source);
				Method method = eventClass.getMethod(GenericMeepEvent.METHOD_TO_SET_MODEL, modelObject.getClass());
				method.invoke(instanceEvent, modelObject);
				applicationEventPublisher.publishEvent(instanceEvent);
			} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
				log.error("ERROR SENDING EVENTS: ", e);
			}
			
		});
	}

}
